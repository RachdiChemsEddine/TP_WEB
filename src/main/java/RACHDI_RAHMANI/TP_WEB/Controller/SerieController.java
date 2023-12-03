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
    public SerieController(SerieService serieService, UserService userService, UserService userService1, HttpSession httpSession) {
        this.serieService = serieService;
        this.userService = userService1;
        this.httpSession = httpSession;
    }

    @GetMapping
    public ResponseEntity<List<Serie>> getAllSerie() {
        List<Serie> series = serieService.getAllSerie();
        return ResponseEntity.ok(series);
    }

    @GetMapping("/{serieTitle}")
    public ResponseEntity<Serie> getSerieByTitle(@PathVariable String serieTitle) {
        if (serieService.getSerieByTitle(serieTitle) == null) {
            return ResponseEntity.notFound().build();
        }
        Serie serie = (Serie) serieService.getSerieByTitle(serieTitle);
        return ResponseEntity.ok(serie);
    }

    @PostMapping("/create")
    public ResponseEntity<Serie> createSerie(@RequestBody Serie serie) {
        // Vérification de l'authentification
        if (httpSession.getAttribute("username") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // Vérification de l'existence de la série
        if (serieService.userHasSerie((String) httpSession.getAttribute("username"), serie.getTitle())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        Serie createdSerie = serieService.createSerie(serie.getTitle(), serie.getDescription());
        User user = userService.findUser((String) httpSession.getAttribute("username"));
        // Ajouter la série à la liste ownSeries de l'utilisateur
        user.addOwnedSeries(createdSerie);
        // Mettre à jour l'utilisateur dans la base de données
        userService.updateUser(user.getUsername(), user.getPassword());
        return ResponseEntity.ok(createdSerie);
    }

    @PutMapping("/{serieTitle}")
    public ResponseEntity<Serie> updateSerie(@PathVariable String serieTitle, @RequestBody Serie updatedSerie) {
        if (httpSession.getAttribute("username") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (serieService.getSerieByTitle(serieTitle) == null) {
            return ResponseEntity.notFound().build();
        }
        Serie resultSerie = serieService.updateSerie(serieTitle, updatedSerie);
        return ResponseEntity.ok(resultSerie);
    }

    @DeleteMapping("/{serieTitle}")
    public ResponseEntity<String> deleteSerie(@PathVariable String title) {
        if (httpSession.getAttribute("username") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (serieService.getSerieByTitle(title) == null) {
            return ResponseEntity.notFound().build();
        }
        if (!serieService.userHasSerie(title, (String) httpSession.getAttribute("username"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        serieService.deleteSerieByTitle(title, (String) httpSession.getAttribute("username"));
        return ResponseEntity.ok("Serie deleted successfully");
    }

    @PostMapping("/{serieTitle}/share-with-user")
    public ResponseEntity<String> shareSerieWithUser(
            @PathVariable String serieTitle,
            @RequestParam String username) {
        if (httpSession.getAttribute("username") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // verifier que l'utilisateur possède la série
        if (!serieService.userHasSerie((String) httpSession.getAttribute("username"), serieTitle)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Serie sharedSerie = serieService.shareSerieWithUser(serieTitle, username);

        // Vérifiez si la série a été partagée avec succès
        if (sharedSerie == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Serie shared successfully with user: " + username);
    }
}