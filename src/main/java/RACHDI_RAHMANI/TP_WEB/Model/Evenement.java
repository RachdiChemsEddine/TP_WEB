package RACHDI_RAHMANI.TP_WEB.Model;

import jakarta.persistence.*;
import lombok.Getter;
import java.util.UUID;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "evenements")
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private LocalDate date;
    private Double valeur;
    @Getter
    @ElementCollection
    private List<String> tags;

    public <T> Evenement(UUID l, LocalDate of, double v, List<T> list) {
        this.uuid = l;
        this.date = of;
        this.valeur = v;
        this.tags = (List<String>) list;
    }

    public Evenement() {

    }

    public Evenement(LocalDate date, Double valeur, List<String> tag) {
        this.date = date;
        this.valeur = valeur;
        this.tags = tag;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID ID) {
        this.uuid = ID;
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
        if (tag != null)
            this.tags.addAll(tag);
    }
}
