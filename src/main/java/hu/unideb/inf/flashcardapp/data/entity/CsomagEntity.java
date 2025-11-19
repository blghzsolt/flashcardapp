package hu.unideb.inf.flashcardapp.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "decks")
public class CsomagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", unique = true)
    private String nev;
    @ManyToOne
    @JoinColumn(name = "userId")
    private FelhasznaloEntity felhasznalo;
    @OneToMany(mappedBy = "csomag", cascade = CascadeType.ALL, orphanRemoval = true)
    List<KartyaEntity> kartyak;
}
