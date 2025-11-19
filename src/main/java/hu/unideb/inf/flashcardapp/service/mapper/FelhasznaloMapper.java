package hu.unideb.inf.flashcardapp.service.mapper;

import hu.unideb.inf.flashcardapp.data.entity.FelhasznaloEntity;
import hu.unideb.inf.flashcardapp.service.dto.RegisztracioDto;
import org.mapstruct.Mapper;

import java.rmi.registry.Registry;

@Mapper(componentModel = "spring")
public interface FelhasznaloMapper {
    FelhasznaloEntity dtoToEntity(RegisztracioDto dto);
}
