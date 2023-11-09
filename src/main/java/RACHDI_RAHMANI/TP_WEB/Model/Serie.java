package RACHDI_RAHMANI.TP_WEB.Model;

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
    public Serie(Long ID, List<Evenement> evenements) {
        this.ID = ID;
        Evenements = evenements;
    }
    public Serie() {
    }
}
