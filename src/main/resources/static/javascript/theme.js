const toggleText = document.getElementById("theme-toggle");

if (localStorage.getItem("theme") === "dark") {
  document.body.classList.add("dark-mode");
  toggleText.textContent = "☀️ Világos mód";
}

toggleText.addEventListener("click", () => {
  document.body.classList.toggle("dark-mode");
  if (document.body.classList.contains("dark-mode")) {
    localStorage.setItem("theme", "dark");
    toggleText.textContent = "☀️ Világos mód";
  } else {
    localStorage.setItem("theme", "light");
    toggleText.textContent = "🌙 Sötét mód";
  }
});
