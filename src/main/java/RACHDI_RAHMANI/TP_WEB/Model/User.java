package RACHDI_RAHMANI.TP_WEB.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Entity
@Data@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String nom;
    private String prenom;
    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @OneToMany
    private List<Serie> OwnSeries;
    @OneToMany
    private List<Serie> SharedSeries;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String nom, String prenom) {
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOwnSeries(List<Serie> ownSeries) {
        OwnSeries = ownSeries;
    }

    public void setSharedSeries(List<Serie> sharedSeries) {
        SharedSeries = sharedSeries;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPassword() {
        return password;
    }

    public List<Serie> getOwnSeries() {
        return OwnSeries;
    }

    public List<Serie> getSharedSeries() {
        return SharedSeries;
    }

    public void addOwnedSeries(Serie createdSerie) {
        OwnSeries.add(createdSerie);
    }

    public void removeOwnSeries(Serie serieByTitle) {
        OwnSeries.remove(serieByTitle);
    }
}
