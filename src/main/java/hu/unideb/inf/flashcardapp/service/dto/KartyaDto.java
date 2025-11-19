package hu.unideb.inf.flashcardapp.service.dto;

import hu.unideb.inf.flashcardapp.data.entity.CsomagEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Builder
public class KartyaDto {
    private Long id;
    private String elolap;
    private String hatlap;
    private Long csomagId;
}
