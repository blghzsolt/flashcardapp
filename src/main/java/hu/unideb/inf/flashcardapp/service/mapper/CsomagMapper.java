package hu.unideb.inf.flashcardapp.service.mapper;

import hu.unideb.inf.flashcardapp.data.entity.CsomagEntity;
import hu.unideb.inf.flashcardapp.service.dto.CsomagDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CsomagMapper {
    @Mapping(source = "felhasznalo.id", target = "felhasznaloId")
    CsomagDto csomagEntityToDto(CsomagEntity entity);

    @Mapping(target = "felhasznalo", ignore = true)
    @Mapping(target = "kartyak", ignore = true)
    CsomagEntity csomagDtoToEntity(CsomagDto dto);

    List<CsomagDto> csomagEntitiesToDtos(List<CsomagEntity> entities);
}
