package RACHDI_RAHMANI.TP_WEB;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Serie {
    @Id
    private Long ID;
    @OneToMany
    private List<Evenement> Evenements;
}
