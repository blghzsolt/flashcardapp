package hu.unideb.inf.flashcardapp.service.impl;

import hu.unideb.inf.flashcardapp.data.entity.FelhasznaloEntity;
import hu.unideb.inf.flashcardapp.data.entity.JogosultsagEntity;
import hu.unideb.inf.flashcardapp.data.repository.FelhasznaloRepository;
import hu.unideb.inf.flashcardapp.data.repository.JogosultsagRepository;
import hu.unideb.inf.flashcardapp.service.AuthService;
import hu.unideb.inf.flashcardapp.service.dto.BejelentkezesDto;
import hu.unideb.inf.flashcardapp.service.dto.RegisztracioDto;
import hu.unideb.inf.flashcardapp.service.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JogosultsagRepository jogRepo;
    private final FelhasznaloRepository felhRepo;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserMapper mapper, PasswordEncoder passwordEncoder,
                           JogosultsagRepository jogRepo, FelhasznaloRepository felhRepo, AuthenticationManager authenticationManager) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.jogRepo = jogRepo;
        this.felhRepo = felhRepo;
        this.authenticationManager = authenticationManager;
    }
    @Override
    public void regisztracio(RegisztracioDto dto) {
        FelhasznaloEntity e = mapper.dtoToEntity(dto);
        e.setJelszo(passwordEncoder.encode(e.getJelszo()));

        JogosultsagEntity jog = jogRepo.findByNev("FELHASZNALO");
        if(jog != null){
            e.setJogosultsagok(List.of(jog));
        } else {
            jog = new JogosultsagEntity();
            jog.setNev("FELHASZNALO");
            jog = jogRepo.save(jog);
            e.setJogosultsagok(List.of(jog));
        }
        felhRepo.save(e);
    }

    @Override
    public Long bejelentkezes(BejelentkezesDto dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getFelhasznalonev(), dto.getJelszo()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        FelhasznaloEntity bejelentkezett = felhRepo.findByFelhasznalonev(dto.getFelhasznalonev());
        if (bejelentkezett != null) {
            return bejelentkezett.getId();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> kijelentkezes(HttpServletRequest httpServletRequest) {
        SecurityContextHolder.clearContext();
        HttpSession session = httpServletRequest.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("Kijelentkezve.", HttpStatus.OK);
    }
}
