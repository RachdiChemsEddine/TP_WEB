package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import RACHDI_RAHMANI.TP_WEB.Service.EvenementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    private final EvenementService evenementService;

    @Autowired
    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
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

    @PostMapping
    public ResponseEntity<Evenement> createEvenement(@RequestBody Evenement evenement) {
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
