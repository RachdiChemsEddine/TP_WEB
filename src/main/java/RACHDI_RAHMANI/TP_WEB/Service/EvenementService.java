package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import RACHDI_RAHMANI.TP_WEB.Repository.EvenementRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class EvenementService {

    private final EvenementRepository evenementRepository;
    private final UserService userService;
    private final SerieService serieService;

    @Autowired
    public EvenementService(EvenementRepository evenementRepository, UserService userService, SerieService serieService, HttpSession httpSession) {
        this.evenementRepository = evenementRepository;
        this.userService = userService;
        this.serieService = serieService;
    }

    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    public Evenement getEvenementById(UUID evenementId) {
        if (evenementRepository.findByUuid(evenementId) == null) {
            throw new IllegalStateException("L'événement avec l'ID " + evenementId + " n'existe pas");
        }
        return (Evenement) evenementRepository.findByUuid(evenementId);
    }

    public Evenement createEvenement(LocalDate date, Double valeur, List<String> tag) {
        Evenement evenement = new Evenement(date, valeur, tag);
        return evenementRepository.save(evenement);
    }

    public Evenement updateEvenement(UUID evenementId, Evenement updatedEvenement) {
        Evenement existingEvenement = getEvenementById(evenementId);
        // Ajoutez ici la logique de mise à jour en fonction des besoins de votre application
        existingEvenement.setDate(updatedEvenement.getDate());
        existingEvenement.setValeur(updatedEvenement.getValeur());
        // Mise à jour d'autres champs selon vos besoins

        return evenementRepository.save(existingEvenement);
    }

    public void deleteEvenement(UUID evenementId) {
        Evenement evenementToDelete = getEvenementById(evenementId);
        evenementRepository.delete(evenementToDelete);
    }

    public List<Evenement> getEvenementByTag(String tag) {
        return evenementRepository.findByTags(tag);
    }
}
