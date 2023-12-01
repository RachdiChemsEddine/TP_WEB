package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import RACHDI_RAHMANI.TP_WEB.Service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    private final EvenementService evenementService;
    private final HttpSession httpSession;

    @Autowired
    public EvenementController(EvenementService evenementService, HttpSession httpSession) {
        this.evenementService = evenementService;
        this.httpSession = httpSession;
    }

    @GetMapping
    public ResponseEntity<List<Evenement>> getAllEvenements() {
        List<Evenement> evenements = evenementService.getAllEvenements();
        return ResponseEntity.ok(evenements);
    }

    @GetMapping("/{evenementId}")
    public ResponseEntity<Evenement> getEvenementById(@PathVariable Long evenementId) {
        Evenement evenement = evenementService.getEvenementById(evenementId);
        return ResponseEntity.ok(evenement);
    }

    @PostMapping("/create")
    public ResponseEntity<Evenement> createEvenement(@RequestBody Evenement evenement) {
        if (httpSession.getAttribute("username") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Evenement createdEvenement = evenementService.createEvenement(evenement.getDate(), evenement.getValeur(), evenement.getTag());
        return ResponseEntity.ok(createdEvenement);
    }


    @PutMapping("/{evenementId}")
    public ResponseEntity<Evenement> updateEvenement(@PathVariable Long evenementId, @RequestBody Evenement updatedEvenement) {
        Evenement resultEvenement = evenementService.updateEvenement(evenementId, updatedEvenement);
        return ResponseEntity.ok(resultEvenement);
    }

    @DeleteMapping("/{evenementId}")
    public ResponseEntity<String> deleteEvenement(@PathVariable Long evenementId) {
        evenementService.deleteEvenement(evenementId);
        return ResponseEntity.ok("Evenement deleted successfully");
    }
}
