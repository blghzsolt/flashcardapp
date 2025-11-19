package hu.unideb.inf.flashcardapp.service;

import hu.unideb.inf.flashcardapp.service.dto.CsomagDto;

import java.util.List;

public interface CsomagService {
    CsomagDto findById(Long id);
    List<CsomagDto> findAll();
    List<CsomagDto> findByUserId(Long id);
    void deleteById(Long id);
    CsomagDto save(CsomagDto csomagDto);
}
