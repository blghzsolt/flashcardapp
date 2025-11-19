package hu.unideb.inf.flashcardapp.service.mapper;

import hu.unideb.inf.flashcardapp.data.entity.FelhasznaloEntity;
import hu.unideb.inf.flashcardapp.service.dto.RegisztracioDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    FelhasznaloEntity dtoToEntity(RegisztracioDto dto);
}
