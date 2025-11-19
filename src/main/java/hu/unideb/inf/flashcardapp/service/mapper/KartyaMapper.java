package hu.unideb.inf.flashcardapp.service.mapper;

import hu.unideb.inf.flashcardapp.data.entity.KartyaEntity;
import hu.unideb.inf.flashcardapp.service.dto.KartyaDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KartyaMapper {
    KartyaEntity kartyaDtoToEntity(KartyaDto dto);
    List<KartyaEntity> kartyaDtosToEntities(List<KartyaDto> dtos);

    KartyaDto kartyaEntityToDto(KartyaEntity e);
    List<KartyaDto> kartyaEntitiesToDtos(List<KartyaEntity> e);
}
