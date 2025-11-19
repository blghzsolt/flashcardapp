let currentDeckId = null;
let currentDeckOwnerId = null;
let currentCards = [];
let currentIndex = 0;
let isFlipped = false;

const API_DECKS = "api/decks";
const API_CARDS = "api/cards";
const userId = sessionStorage.getItem("userId");

document.onmousemove = resetTimer;
document.onkeydown = resetTimer;
document.onclick = resetTimer;
document.onscroll = resetTimer;

let timeout = 15 * 60 * 1000;
let inactivitytimer;

function resetTimer() {
    clearTimeout(inactivitytimer);
    inactivitytimer = setTimeout(logout, timeout);
}

if (!userId) {
    alert("Jelentkezz be!");
    window.location.href = "login.html";
}

async function logout() {
    await fetch("/auth/kijelentkezes", {
        method: "POST",
        credentials: "include"
    });
    sessionStorage.removeItem("userId");
    alert("Sikeresen kijelentkeztél.");
    window.location.href = "login.html";
}

function setView(viewId) {
    document.querySelectorAll(".view").forEach(v => v.style.display = "none");
    document.getElementById(viewId).style.display = "block";

    document.getElementById("back-to-decks").style.display =
        viewId === "deck-list-view" ? "none" : "block";
}

async function loadAllDecks() {
    const res = await fetch(API_DECKS);
    const decks = await res.json();
    renderDeckList(decks);
}

async function loadMyDecks() {
    const res = await fetch(`${API_DECKS}/findByUserId?id=${userId}`);
    const decks = await res.json();
    renderDeckList(decks);
}

function renderDeckList(decks) {
    const list = document.getElementById("deck-list");
    list.innerHTML = "";

    decks.forEach(deck => {
        const li = document.createElement("li");
        li.className = "deck-item";

        li.innerHTML = `
            <div class="deck-info">
                <strong>${deck.nev}</strong>
            </div>
            <div class="deck-actions">
                <button onclick="openDeck(${deck.id}, '${deck.nev}', ${deck.felhasznaloId})">Megnyitás</button>
                ${deck.felhasznaloId == userId
                    ? `<button onclick="renameDeck(${deck.id}, '${deck.nev}')" style="background:#3498db;">Átnevezés</button>
                    <button onclick="deleteDeck(${deck.id})" style="background:#e74c3c;">Törlés</button>`
                    : ``}
            </div>
        `;

        list.appendChild(li);
    });
}

async function renameDeck(id, oldName) {
    const newName = prompt("Új név:", oldName);

    if (!newName || newName.trim() === "") {
        alert("A név nem lehet üres!");
        return;
    }

    const updatedDeck = {
        id: id,
        nev: newName.trim(),
        felhasznaloId: userId
    };

    const res = await fetch(`${API_DECKS}/save`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(updatedDeck)
    });
    if(!res.ok){
    alert("Ez a csomagnév már létezik!");
            return;}

    loadMyDecks();
}


async function handleAddDeck() {
    const name = document.getElementById("new-deck-name").value.trim();
    if (!name) return alert("Adj meg egy csomagnevet!");

    const deck = {
        id: null,
        nev: name,
        felhasznaloId: userId
    };

    const res = await fetch(`${API_DECKS}/save`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(deck)
    });

    if(!res.ok){
        alert("Ez a csomagnév már létezik!");
                return;
    }

    document.getElementById("new-deck-name").value = "";
    loadMyDecks();
}

async function deleteDeck(id) {
    if (!confirm("Biztos törlöd?")) return;

    await fetch(`${API_DECKS}/deleteById?id=${id}`, {
        method: "DELETE"
    });

    loadMyDecks();
}

function openDeck(id, name, ownerId) {
    currentDeckId = id;
    currentDeckOwnerId = ownerId;

    document.getElementById("cards-view-title").innerText =
        "Kártyák kezelése: " + name;

    loadCards();
    setView("cards-view");
}

async function loadCards() {
    const res = await fetch(`${API_CARDS}/byDeck?deckId=${currentDeckId}`);
    currentCards = await res.json();

    const tbody = document.getElementById("card-table-body");
    tbody.innerHTML = "";

    currentCards.forEach(card => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${card.elolap}</td>
            <td>${card.hatlap}</td>
            <td>
                ${
                    currentDeckOwnerId == userId
                        ? `<button onclick="deleteCard(${card.id})" style="background:#e74c3c;">Törlés</button>`
                        : ``
                }
            </td>
        `;

        tbody.appendChild(tr);
    });
    document.getElementById("new-card-front").disabled = (currentDeckOwnerId != userId);
    document.getElementById("new-card-back").disabled = (currentDeckOwnerId != userId);
    document.querySelector("button[onclick='handleAddCard()']").disabled = (currentDeckOwnerId != userId);
}

async function handleAddCard() {
    const front = document.getElementById("new-card-front").value.trim();
    const back = document.getElementById("new-card-back").value.trim();

    if (!front || !back) return alert("Mindkét oldalra írj valamit!");

    const card = {
        id: null,
        elolap: front,
        hatlap: back,
        csomagId: currentDeckId
    };

    await fetch(`${API_CARDS}/save`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(card)
    });

    document.getElementById("new-card-front").value = "";
    document.getElementById("new-card-back").value = "";

    loadCards();
}

async function deleteCard(id) {
    if (!confirm("Biztos törlöd?")) return;

    await fetch(`${API_CARDS}/deleteById?id=${id}`, {
        method: "DELETE"
    });

    loadCards();
}

function startReview() {
    if (currentCards.length === 0)
        return alert("Nincs kártya ebben a csomagban!");

    currentIndex = 0;
    isFlipped = false;

    document.getElementById("flashcard").classList.remove("flipped");

    updateReviewUI();
    setView("review-view");
}

function updateReviewUI() {
    const card = currentCards[currentIndex];

    document.getElementById("card-front-content").innerText = card.elolap;
    document.getElementById("card-back-content").innerText = card.hatlap;

    document.getElementById("review-status").innerText =
        `${currentIndex + 1}/${currentCards.length}`;
}

function flipCard() {
    isFlipped = !isFlipped;
    document.getElementById("flashcard").classList.toggle("flipped");
}

function nextCard() {
    if (currentIndex < currentCards.length - 1) {
        const flashcard = document.getElementById("flashcard");
        flashcard.style.transition = "none";
        isFlipped = false;
        flashcard.classList.remove("flipped");
        currentIndex++;
        updateReviewUI();
        setTimeout(() => {
            flashcard.style.transition = "";
        }, 10);
    }
}

function prevCard() {
    if (currentIndex > 0) {
        const flashcard = document.getElementById("flashcard");
        flashcard.style.transition = "none";
        isFlipped = false;
        flashcard.classList.remove("flipped");
        currentIndex--;
        updateReviewUI();
        setTimeout(() => {
            flashcard.style.transition = "";
        }, 10);
    }
}

window.onload = () => {
    loadMyDecks();
};

resetTimer();
