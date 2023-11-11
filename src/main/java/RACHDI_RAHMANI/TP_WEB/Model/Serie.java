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
        title = title;
    }

    public void setDescription(String description) {
        description = description;
    }
}
