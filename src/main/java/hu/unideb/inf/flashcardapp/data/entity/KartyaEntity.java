package hu.unideb.inf.flashcardapp.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cards")
public class KartyaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "front_text")
    private String elolap;
    @Column(name = "back_text")
    private String hatlap;
    @ManyToOne
    @JoinColumn(name = "deckId")
    private CsomagEntity csomag;
}
