package RACHDI_RAHMANI.TP_WEB.Repository;

import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    Evenement findByDate(Date date);
    Evenement findByTags(String tag);
}


