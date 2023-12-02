package RACHDI_RAHMANI.TP_WEB.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Entity
@Data
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
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

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getId() {
        return ID;
    }

    public void addEvenement(Evenement createdEvenement) {
        Evenements.add(createdEvenement);
    }
}
