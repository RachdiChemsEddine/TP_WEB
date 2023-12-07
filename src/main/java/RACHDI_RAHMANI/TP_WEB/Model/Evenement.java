package RACHDI_RAHMANI.TP_WEB.Model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "evenements")
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private LocalDate date;
    private Double valeur;
    @Getter
    @ElementCollection
    private List<String> tags;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setValeur(Double value) {
        this.valeur = value;
    }

    public void setTags(String tag) {
        this.tags.add(tag);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getValeur() {
        return valeur;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setAllTags(List<String> tag) {
        this.tags = tag;
    }
}
