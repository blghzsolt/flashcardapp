package hu.unideb.inf.flashcardapp.data.repository;

import hu.unideb.inf.flashcardapp.data.entity.JogosultsagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JogosultsagRepository extends JpaRepository<JogosultsagEntity,Long> {
    JogosultsagEntity findByNev(String nev);
}
