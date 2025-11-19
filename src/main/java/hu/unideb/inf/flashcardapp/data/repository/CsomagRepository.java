package hu.unideb.inf.flashcardapp.data.repository;

import hu.unideb.inf.flashcardapp.data.entity.CsomagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CsomagRepository extends JpaRepository<CsomagEntity,Long> {
    List<CsomagEntity> findByFelhasznaloId(Long id);
}
