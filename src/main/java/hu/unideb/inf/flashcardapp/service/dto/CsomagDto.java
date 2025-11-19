package hu.unideb.inf.flashcardapp.service.dto;

import hu.unideb.inf.flashcardapp.data.entity.FelhasznaloEntity;
import hu.unideb.inf.flashcardapp.data.entity.KartyaEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class CsomagDto {
    private Long id;
    private String nev;
    private Long felhasznaloId;
}
