package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/serie")
public class SerieController {

    private final SerieService serieService;

    @Autowired
    public SerieController(SerieService serieService) {
        this.serieService = serieService;
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

    @PostMapping
    public ResponseEntity<Serie> createSerie(@RequestBody Serie serie) {
        Serie createdSerie = serieService.createSerie(serie.getTitle(), serie.getDescription());
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