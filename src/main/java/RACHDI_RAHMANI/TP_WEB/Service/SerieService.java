package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Repository.EvenementRepository;
import RACHDI_RAHMANI.TP_WEB.Repository.SerieRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SerieService {

    private final SerieRepository serieRepository;
    private final UserService userService;

    @Autowired
    public SerieService(SerieRepository serieRepository, EvenementRepository evenementRepository, UserService userService, HttpSession httpSession) {
        this.serieRepository = serieRepository;
        this.userService = userService;
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
        Serie serie = new Serie();
        serie.setTitle(title);
        serie.setDescription(description);
        return serieRepository.save(serie);
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

    public void deleteSerie(Long id) {
        serieRepository.deleteById(id);
    }

    public void deleteSerieByTitle(String title, String username) {
        User user = userService.findUser("username");
        // Supprimer la série de la liste ownSeries de l'utilisateur
        user.removeOwnSeries(getSerieByTitle(title));
        serieRepository.deleteByTitle(title);
    }

    public Boolean userHasSerie(String username, String serieTitle) {
        User user = userService.findUser(username);
        return user.getOwnSeries().contains(getSerieByTitle(serieTitle));
    }

    public Serie getSerieByTitle(String serieTitle) {
        return serieRepository.findByTitle(serieTitle);
    }
}
