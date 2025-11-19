package hu.unideb.inf.flashcardapp.service;

import hu.unideb.inf.flashcardapp.service.dto.KartyaDto;

import java.util.List;

public interface KartyaService {
    KartyaDto findById(Long id);
    List<KartyaDto> findAll();
    void deleteById(Long id);
    KartyaDto save(KartyaDto kartyaDto);
    List<KartyaDto> findByCsomagId(Long csomagId);
}
