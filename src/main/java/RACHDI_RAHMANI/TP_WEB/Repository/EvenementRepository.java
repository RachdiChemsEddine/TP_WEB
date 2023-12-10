package RACHDI_RAHMANI.TP_WEB.Repository;

import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    Evenement findByDate(Date date);
    List<Evenement> findByTags(String tag);

    Object findByUuid(UUID id);
}


