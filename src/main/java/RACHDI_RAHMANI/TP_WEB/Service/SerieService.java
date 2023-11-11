package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SerieService {

    private final SerieRepository SerieRepository;

    @Autowired
    public SerieService(SerieRepository SerieRepository) {
        this.SerieRepository = SerieRepository;
    }

    public List<Serie> getAllSerie() {
        return SerieRepository.findAll();
    }

    public Serie getSerieById(Long id) {
        return SerieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serie not found"));
    }

    public Serie createSerie(Serie Serie) {
        // Ajoutez la logique métier nécessaire
        return SerieRepository.save(Serie);
    }

    public Serie updateSerie(Long id, Serie updatedSerie) {
        // Ajoutez la logique métier nécessaire
        Serie existingSerie = getSerieById(id);
        // Mettez à jour les propriétés de la série temporelle existante avec les nouvelles valeurs
        existingSerie.setTitle(updatedSerie.getTitle());
        existingSerie.setDescription(updatedSerie.getDescription());
        existingSerie.setEvenements(updatedSerie.getEvenements());

        return SerieRepository.save(existingSerie);
    }

    public void deleteSerie(Long id) {
        SerieRepository.deleteById(id);
    }
}
