package hu.unideb.inf.flashcardapp.data.repository;

import hu.unideb.inf.flashcardapp.data.entity.KartyaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KartyaRepository extends JpaRepository<KartyaEntity,Long> {
    List<KartyaEntity> findByCsomag_Id(Long csomagId);
}
