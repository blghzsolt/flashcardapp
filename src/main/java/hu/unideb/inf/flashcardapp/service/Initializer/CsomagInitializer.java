package hu.unideb.inf.flashcardapp.service.Initializer;

import hu.unideb.inf.flashcardapp.data.entity.CsomagEntity;
import hu.unideb.inf.flashcardapp.data.entity.KartyaEntity;
import hu.unideb.inf.flashcardapp.data.repository.CsomagRepository;
import hu.unideb.inf.flashcardapp.data.repository.KartyaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CsomagInitializer implements CommandLineRunner {
    private final CsomagRepository csomagRepository;

    public CsomagInitializer(CsomagRepository csomagRepository) {
        this.csomagRepository = csomagRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var csomagEntity = new CsomagEntity();
        csomagEntity.setNev("angolszavak");
        csomagEntity.setFelhasznalo(null);
        var kartya1 = new KartyaEntity();
        kartya1.setElolap("apple");
        kartya1.setHatlap("alma");
        kartya1.setCsomag(csomagEntity);
        var kartya2 = new KartyaEntity();
        kartya2.setElolap("pear");
        kartya2.setHatlap("körte");
        kartya2.setCsomag(csomagEntity);
        csomagEntity.setKartyak(List.of(kartya1,kartya2));
        csomagEntity = csomagRepository.save(csomagEntity);
    }
}
