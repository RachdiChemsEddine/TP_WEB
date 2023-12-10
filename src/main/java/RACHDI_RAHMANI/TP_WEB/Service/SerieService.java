package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Repository.EvenementRepository;
import RACHDI_RAHMANI.TP_WEB.Repository.SerieRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

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

    public Object getSerieById(UUID id) {
        if (serieRepository.findByUuid(id) != null) {
            return serieRepository.findByUuid(id);
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

    public Serie updateSerie(String title, Serie updatedSerie) {
        Serie existingSerie = (Serie) getSerieByTitle(title);

        if (updatedSerie.getTitle() != null){
            existingSerie.setTitle(updatedSerie.getTitle());
        }
        if (updatedSerie.getDescription() != null){
            existingSerie.setDescription(updatedSerie.getDescription());
        }
        return serieRepository.save(existingSerie);
    }

    public void deleteSerie(Long id) {
        serieRepository.deleteById(id);
    }

    public void deleteSerieByTitle(String title) {
        serieRepository.deleteByTitle(title);
    }

    public Boolean userHasSerie(String username, String serieTitle) {
        User user = userService.findUser(username);
        return user.getOwnSeries().contains(getSerieByTitle(serieTitle));
    }

    public Serie getSerieByTitle(String serieTitle) {
        return serieRepository.findByTitle(serieTitle);
    }

    public Serie shareSerieWithUser(String serieTitle, String username) {
        Serie serie = getSerieByTitle(serieTitle);
        User userToShareWith = userService.findUser(username);
        if (serie == null || userToShareWith == null) {
            throw new IllegalArgumentException("Serie or user not found");
        }

        userToShareWith.addSharedSeries(serie);
        userService.updateUser(userToShareWith.getUsername(), userToShareWith);
        return serie;
    }
}
