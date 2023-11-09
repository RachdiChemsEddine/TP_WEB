package RACHDI_RAHMANI.TP_WEB.Repository;

import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
    Evenement findByKey(String key);
    //Evenement findByTag(String tag);
}
