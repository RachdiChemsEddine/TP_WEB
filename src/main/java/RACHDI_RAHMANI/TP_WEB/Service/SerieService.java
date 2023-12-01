package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Repository.EvenementRepository;
import RACHDI_RAHMANI.TP_WEB.Repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SerieService {

    private final SerieRepository serieRepository;
    private final EvenementRepository evenementRepository;

    @Autowired
    public SerieService(SerieRepository serieRepository, EvenementRepository evenementRepository) {
        this.serieRepository = serieRepository;
        this.evenementRepository = evenementRepository;
    }

    public List<Serie> getAllSerie() {
        return serieRepository.findAll();
    }

    public Object getSerieById(Long id) {
        if (serieRepository.findById(id).isPresent()) {
            return serieRepository.findById(id).get();
        } else {
            return null;
        }
    }

    public Serie createSerie(String title, String description) {
        Serie Serie = new Serie();
        Serie.setTitle(title);
        Serie.setDescription(description);
        // Ajoutez la logique métier nécessaire
        return serieRepository.save(Serie);
    }

    public Serie updateSerie(Long id, Serie updatedSerie) {
        // Ajoutez la logique métier nécessaire
        Serie existingSerie = (Serie) getSerieById(id);
        // Mettez à jour les propriétés de la série temporelle existante avec les nouvelles valeurs
        existingSerie.setTitle(updatedSerie.getTitle());
        existingSerie.setDescription(updatedSerie.getDescription());
        existingSerie.setEvenements(updatedSerie.getEvenements());

        return serieRepository.save(existingSerie);
    }

    public Serie addExistingEvenementToSerie(Long serieId, Long evenementId) {
        Optional<Serie> optionalSerie = serieRepository.findById(serieId);
        Optional<Evenement> optionalEvenement = evenementRepository.findById(evenementId);

        if (optionalSerie.isPresent() && optionalEvenement.isPresent()) {
            Serie serieToUpdate = optionalSerie.get();
            Evenement existingEvenement = optionalEvenement.get();

            List<Evenement> serieEvenements = serieToUpdate.getEvenements();
            serieEvenements.add(existingEvenement);
            serieToUpdate.setEvenements(serieEvenements);

            return serieRepository.save(serieToUpdate);
        } else {
            // Gérez ici les cas où la série ou l'événement n'est pas trouvé
            return null;
        }
    }


    public void deleteSerie(Long id) {
        serieRepository.deleteById(id);
    }
}
