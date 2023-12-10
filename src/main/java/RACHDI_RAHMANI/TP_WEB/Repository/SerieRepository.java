package RACHDI_RAHMANI.TP_WEB.Repository;

import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Serie findByUuid(UUID uuid);
    Serie findByTitle(String title);
    void deleteByTitle(String title);
}
