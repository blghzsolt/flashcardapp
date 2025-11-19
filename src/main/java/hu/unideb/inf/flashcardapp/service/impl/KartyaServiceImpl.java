package hu.unideb.inf.flashcardapp.service.impl;

import hu.unideb.inf.flashcardapp.data.entity.KartyaEntity;
import hu.unideb.inf.flashcardapp.data.repository.CsomagRepository;
import hu.unideb.inf.flashcardapp.data.repository.KartyaRepository;
import hu.unideb.inf.flashcardapp.service.KartyaService;
import hu.unideb.inf.flashcardapp.service.dto.KartyaDto;
import hu.unideb.inf.flashcardapp.service.mapper.KartyaMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KartyaServiceImpl implements KartyaService {
    final KartyaRepository kartyaRepository;
    final KartyaMapper kartyaMapper;
    final CsomagRepository csomagRepository;

    public KartyaServiceImpl(KartyaRepository kartyaRepository, KartyaMapper kartyaMapper, CsomagRepository csomagRepository) {
        this.kartyaRepository = kartyaRepository;
        this.kartyaMapper = kartyaMapper;
        this.csomagRepository = csomagRepository;
    }

    @Override
    public KartyaDto findById(Long id) {
        return kartyaMapper.kartyaEntityToDto(kartyaRepository.getReferenceById(id));
    }

    @Override
    public List<KartyaDto> findAll() {
        List<KartyaEntity> kartyaEntities = kartyaRepository.findAll();
        return kartyaMapper.kartyaEntitiesToDtos(kartyaEntities);
    }

    @Override
    public List<KartyaDto> findByCsomagId(Long csomagId) {
        return kartyaMapper.kartyaEntitiesToDtos(
                kartyaRepository.findByCsomag_Id(csomagId)
        );
    }

    @Override
    @Modifying
    @Transactional
    public void deleteById(Long id) {
        kartyaRepository.deleteById(id);
    }

    @Override
    @Modifying
    @Transactional
    public KartyaDto save(KartyaDto kartyaDto) {
        if (kartyaDto.getId() == null) {
            KartyaEntity kartyaEntity = kartyaMapper.kartyaDtoToEntity(kartyaDto);

            var csomagEntity = csomagRepository.findById(kartyaDto.getCsomagId())
                    .orElseThrow(() -> new EntityNotFoundException("Nincs ilyen csomag: " + kartyaDto.getCsomagId()));

            kartyaEntity.setCsomag(csomagEntity);

            kartyaEntity = kartyaRepository.save(kartyaEntity);
            return kartyaMapper.kartyaEntityToDto(kartyaEntity);
        }
        else{
            KartyaEntity kartyaEntity = kartyaRepository.findById(kartyaDto.getId())
                    .orElseThrow(()-> new EntityNotFoundException("ERROR"));
            kartyaEntity.setHatlap(kartyaDto.getHatlap());
            kartyaEntity.setElolap(kartyaDto.getElolap());
            kartyaEntity = kartyaRepository.save(kartyaEntity);
            return kartyaMapper.kartyaEntityToDto(kartyaEntity);
        }
    }
}
