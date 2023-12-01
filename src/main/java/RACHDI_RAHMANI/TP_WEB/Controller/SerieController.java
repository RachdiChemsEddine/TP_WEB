package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Service.SerieService;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    private final SerieService serieService;
    private final UserService userService;
    private final HttpSession httpSession;

    @Autowired
    public SerieController(SerieService serieService, UserService userService, HttpSession httpSession) {
        this.serieService = serieService;
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @GetMapping
    public ResponseEntity<List<Serie>> getAllSerie() {
        List<Serie> series = serieService.getAllSerie();
        return ResponseEntity.ok(series);
    }

    @GetMapping("/{serieId}")
    public ResponseEntity<Serie> getSerieById(@PathVariable Long serieId) {
        if (serieService.getSerieById(serieId) == null) {
            return ResponseEntity.notFound().build();
        }
        Serie serie = (Serie) serieService.getSerieById(serieId);
        return ResponseEntity.ok(serie);
    }

    @PostMapping("/create")
    public ResponseEntity<Serie> createSerie(@RequestBody Serie serie) {
        // Vérification de l'authentification
        if (httpSession.getAttribute("username") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Serie createdSerie = serieService.createSerie(serie.getTitle(), serie.getDescription());

        // Récupérer l'utilisateur actuellement authentifié
        User user = userService.findUser((String) httpSession.getAttribute("username"));

        // Ajouter la série à la liste ownSeries de l'utilisateur
        user.addOwnedSeries(createdSerie);

        // Mettre à jour l'utilisateur dans la base de données
        userService.updateUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(createdSerie);
    }

    @PutMapping("/{serieId}")
    public ResponseEntity<Serie> updateSerie(@PathVariable Long serieId, @RequestBody Serie updatedSerie) {
        Serie resultSerie = serieService.updateSerie(serieId, updatedSerie);
        return ResponseEntity.ok(resultSerie);
    }

    @PutMapping("/{serieId}/add-existing-evenement/{evenementId}")
    public ResponseEntity<Serie> addExistingEvenementToSerie(@PathVariable Long serieId, @PathVariable Long evenementId) {
        Serie updatedSerie = serieService.addExistingEvenementToSerie(serieId, evenementId);

        if (updatedSerie != null) {
            return ResponseEntity.ok(updatedSerie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{serieId}")
    public ResponseEntity<String> deleteSerie(@PathVariable Long serieId) {
        serieService.deleteSerie(serieId);
        return ResponseEntity.ok("Serie deleted successfully");
    }
}