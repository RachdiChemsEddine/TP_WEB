package RACHDI_RAHMANI.TP_WEB;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String nom;
    private String prenom;
    private String password;
    @OneToMany
    private List<Serie> OwnSeries;
    @OneToMany
    private List<Serie> SharedSeries;
}
