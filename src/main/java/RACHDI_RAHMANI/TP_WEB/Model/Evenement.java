package RACHDI_RAHMANI.TP_WEB.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "evenements")
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private LocalDate date;
    private Double valeur;
    @Getter
    @Column(nullable = true)
    private String tag;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setValeur(Double value) {
        this.valeur = value;
    }

    public void setTag(String tag) {
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }
}
