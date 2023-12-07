package RACHDI_RAHMANI.TP_WEB;

import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Service.EvenementService;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import RACHDI_RAHMANI.TP_WEB.Service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader {

    private final UserService userService;
    private final SerieService SerieService;
    private final EvenementService evenementService;

    @Autowired
    public DataLoader(UserService userService, SerieService serieService, EvenementService evenementService) {
        this.userService = userService;
        this.SerieService = serieService;
        this.evenementService = evenementService;
    }

    @PostConstruct
    public void loadData() {
        // Exemple : Création d'un utilisateur
        userService.createUser("john_doe", "password", "John", "Doe");

        // Exemple : Création d'une série temporelle et d'événements associés
        Serie serie = SerieService.createSerie("Serie 1", "Description de la série 1");
        List<String> tags = List.of("tag1", "tag2", "tag3");
        List<String> tags1 = List.of("tag1", "tag3");
        evenementService.createEvenement(LocalDate.parse("2023-01-01"), 10.0, tags);
        evenementService.createEvenement(LocalDate.parse("2023-01-02"), 15.0, tags1);
        // ...
    }
}
