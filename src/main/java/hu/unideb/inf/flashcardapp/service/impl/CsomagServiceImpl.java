package hu.unideb.inf.flashcardapp.service.impl;

import hu.unideb.inf.flashcardapp.data.entity.CsomagEntity;
import hu.unideb.inf.flashcardapp.data.repository.CsomagRepository;
import hu.unideb.inf.flashcardapp.data.repository.FelhasznaloRepository;
import hu.unideb.inf.flashcardapp.service.CsomagService;
import hu.unideb.inf.flashcardapp.service.dto.CsomagDto;
import hu.unideb.inf.flashcardapp.service.mapper.CsomagMapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CsomagServiceImpl implements CsomagService {
    private final CsomagRepository csomagRepository;
    private final CsomagMapper csomagMapper;
    private final FelhasznaloRepository felhasznaloRepository;

    public CsomagServiceImpl(CsomagRepository csomagRepository, CsomagMapper csomagMapper, FelhasznaloRepository felhasznaloRepository) {
        this.csomagRepository = csomagRepository;
        this.csomagMapper = csomagMapper;
        this.felhasznaloRepository = felhasznaloRepository;
    }

    @Override
    public CsomagDto findById(Long id) {
        return csomagMapper.csomagEntityToDto(csomagRepository.getReferenceById(id));
    }

    @Override
    public List<CsomagDto> findByUserId(Long id) {
        return csomagMapper.csomagEntitiesToDtos(csomagRepository.findByFelhasznaloId(id));
    }

    @Override
    public List<CsomagDto> findAll() {
        return csomagMapper.csomagEntitiesToDtos(csomagRepository.findAll());
    }

    @Override
    @Modifying
    @Transactional
    public void deleteById(Long id) {
        csomagRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CsomagDto save(CsomagDto csomagDto) {
        CsomagEntity csomagEntity = csomagMapper.csomagDtoToEntity(csomagDto);

        var felhasznalo = felhasznaloRepository.findById(csomagDto.getFelhasznaloId())
                .orElseThrow(() -> new RuntimeException("Nincs ilyen felhasználó: " + csomagDto.getFelhasznaloId()));
        csomagEntity.setFelhasznalo(felhasznalo);

        csomagEntity = csomagRepository.save(csomagEntity);
        return csomagMapper.csomagEntityToDto(csomagEntity);
    }
}
