package RACHDI_RAHMANI.TP_WEB.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @Column(unique = true)
    private String title;
    private String description;
    @OneToMany
    private List<Evenement> Evenements;

    public void setEvenements(List<Evenement> evenements) {
        Evenements = evenements;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Evenement> getEvenements() {
        return Evenements;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUuid(UUID ID) {
        this.uuid = ID;
    }

    public UUID getId() {
        return uuid;
    }

    public void addEvenement(Evenement createdEvenement) {
        Evenements.add(createdEvenement);
    }

    public void removeEvenement(Evenement evenementToDelete) {
        Evenements.remove(evenementToDelete);
    }
}
