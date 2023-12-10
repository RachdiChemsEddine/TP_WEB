package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import RACHDI_RAHMANI.TP_WEB.Model.EventRequest;
import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Service.EvenementService;
import RACHDI_RAHMANI.TP_WEB.Service.SerieService;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    private final EvenementService evenementService;
    private final SerieService serieService;
    private final UserService userService;
    private final HttpSession httpSession;

    @Autowired
    public EvenementController(EvenementService evenementService, SerieService serieService, UserService userService, HttpSession httpSession) {
        this.evenementService = evenementService;
        this.serieService = serieService;
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @GetMapping
    public ResponseEntity<List<Evenement>> getAllEvenements() {
        List<Evenement> evenements = evenementService.getAllEvenements();
        return ResponseEntity.ok(evenements);
    }

    @GetMapping("/{evenementId}")
    public ResponseEntity<Evenement> getEvenementById(@PathVariable UUID evenementId) {
        Evenement evenement = evenementService.getEvenementById(evenementId);
        return ResponseEntity.ok(evenement);
    }

    @GetMapping("/{tag}")
    public ResponseEntity<List<Evenement>> getEvenementByTag(@PathVariable String tag) {
        if (httpSession.getAttribute("username") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (evenementService.getEvenementByTag(tag) == null) {
            return ResponseEntity.notFound().build();
        }
        List<Evenement> evenements = evenementService.getEvenementByTag(tag);
        return ResponseEntity.ok(evenements);
    }

    @PostMapping("/create")
    public ResponseEntity<Evenement> createEvenement(@RequestBody EventRequest evenement, @RequestParam String title) {
        User user = userService.findUser((String) httpSession.getAttribute("username"));
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (!serieService.userHasSerie(user.getUsername(), title)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Serie serie = user.getOwnSeries().stream().filter(s -> s.getTitle().equals(title)).findFirst().orElse(null);
        Evenement createdEvenement = evenementService.createEvenement(evenement.getDate(), evenement.getValeur(), evenement.getTags());
        assert serie != null;
        serie.addEvenement(createdEvenement);
        serieService.updateSerie(serie.getTitle(), serie);
        return ResponseEntity.ok(createdEvenement);
    }


    @PutMapping("/{evenementId}")
    public ResponseEntity<Evenement> updateEvenement(@PathVariable UUID evenementId, @RequestBody Evenement updatedEvenement) {
        Evenement resultEvenement = evenementService.updateEvenement(evenementId, updatedEvenement);
        return ResponseEntity.ok(resultEvenement);
    }

    @DeleteMapping("/{evenementId}")
    public ResponseEntity<String> deleteEvenement(@PathVariable UUID evenementId) {
        // Vérification de l'authentification
        if (httpSession.getAttribute("username") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        // Vérification de l'existence de l'événement
        if (evenementService.getEvenementById(evenementId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        evenementService.deleteEvenement(evenementId);
        return ResponseEntity.ok("Evenement deleted successfully");
    }
}
