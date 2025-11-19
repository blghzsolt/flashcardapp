package hu.unideb.inf.flashcardapp.service;

import hu.unideb.inf.flashcardapp.service.dto.BejelentkezesDto;
import hu.unideb.inf.flashcardapp.service.dto.RegisztracioDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    public void regisztracio(RegisztracioDto dto);
    public Long bejelentkezes(BejelentkezesDto dto);
    public ResponseEntity<?> kijelentkezes(HttpServletRequest httpServletRequest);
}
