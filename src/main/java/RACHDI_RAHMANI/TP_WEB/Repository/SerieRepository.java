package RACHDI_RAHMANI.TP_WEB.Repository;

import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Serie findById(long id);
    Serie findByTitle(String title);
    void deleteByTitle(String title);
}
