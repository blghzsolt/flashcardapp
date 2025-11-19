package hu.unideb.inf.flashcardapp.service.Initializer;

import hu.unideb.inf.flashcardapp.data.entity.FelhasznaloEntity;
import hu.unideb.inf.flashcardapp.data.entity.JogosultsagEntity;
import hu.unideb.inf.flashcardapp.data.repository.FelhasznaloRepository;
import hu.unideb.inf.flashcardapp.data.repository.JogosultsagRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminInitializer implements CommandLineRunner {
        final FelhasznaloRepository felhasznaloRepository;
        final PasswordEncoder passwordEncoder;
        final JogosultsagRepository jogosultsagRepository;

    public AdminInitializer(FelhasznaloRepository felhasznaloRepository, PasswordEncoder passwordEncoder, JogosultsagRepository jogosultsagRepository) {
            this.felhasznaloRepository = felhasznaloRepository;
            this.passwordEncoder = passwordEncoder;
            this.jogosultsagRepository = jogosultsagRepository;
        }

        @Override
        public void run(String... args) throws Exception {
            FelhasznaloEntity admin = new FelhasznaloEntity();
            JogosultsagEntity adminJog = new JogosultsagEntity();
            adminJog.setNev("ROLE_ADMIN");
            jogosultsagRepository.save(adminJog);
            admin.setNev("vmi");
            admin.setEmail("valami@valami.com");
            admin.setFelhasznalonev("admin");
            admin.setJelszo(passwordEncoder.encode("admin"));
            admin.setJogosultsagok(List.of(adminJog));
            felhasznaloRepository.save(admin);
        }
    }
//     <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
