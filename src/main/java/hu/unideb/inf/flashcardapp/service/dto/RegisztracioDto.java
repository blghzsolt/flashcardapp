package hu.unideb.inf.flashcardapp.service.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class RegisztracioDto {
    private String nev;
    private String email;
    private String felhasznalonev;
    private String jelszo;
}
