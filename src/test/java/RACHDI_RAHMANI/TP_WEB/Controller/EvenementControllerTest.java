package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import RACHDI_RAHMANI.TP_WEB.Model.EventRequest;
import RACHDI_RAHMANI.TP_WEB.Service.EvenementService;
import RACHDI_RAHMANI.TP_WEB.Service.SerieService;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EvenementControllerTest {

    private EvenementController evenementController;
    private EvenementService evenementService;
    private SerieService serieService;
    private UserService userService;
    private HttpSession httpSession;

    @BeforeEach
    public void setUp() {
        evenementService = mock(EvenementService.class);
        serieService = mock(SerieService.class);
        userService = mock(UserService.class);
        httpSession = mock(HttpSession.class);

        evenementController = new EvenementController(evenementService, serieService, userService, httpSession);
    }

    @Test
    public void testGetAllEvenements() {
        // Création de données de test
        List<Evenement> mockEvenements = new ArrayList<>();
        // Simuler le comportement du service pour renvoyer des données simulées lors de l'appel
        when(evenementService.getAllEvenements()).thenReturn(mockEvenements);

        ResponseEntity<List<Evenement>> response = evenementController.getAllEvenements();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEvenements, response.getBody());
    }

    // Ajoutez d'autres tests pour les autres méthodes comme getEvenementById, createEvenement, etc.
    // En simulant différents scénarios et en testant les réponses attendues.

    @Test
    public void testCreateEvenement_UnauthorizedUser() {
        // Simuler un utilisateur non autorisé
        when(httpSession.getAttribute("username")).thenReturn(null);

        ResponseEntity<Evenement> response = evenementController.createEvenement(new EventRequest(), "title");

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testGetEvenementById() {
        UUID eventId = UUID.randomUUID();
        Evenement mockEvenement = new Evenement(); // Créer un événement simulé pour les tests
        when(evenementService.getEvenementById(eventId)).thenReturn(mockEvenement);

        ResponseEntity<Evenement> response = evenementController.getEvenementById(eventId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockEvenement, response.getBody());
    }

    @Test
    public void testGetEvenementByTag_UnauthorizedUser() {
        String tag = "someTag";
        when(httpSession.getAttribute("username")).thenReturn(null);

        ResponseEntity<List<Evenement>> response = evenementController.getEvenementByTag(tag);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testGetEvenementByTag_NotFound() {
        String tag = "nonExistingTag";
        when(httpSession.getAttribute("username")).thenReturn("someUser");
        when(evenementService.getEvenementByTag(tag)).thenReturn(null);

        ResponseEntity<List<Evenement>> response = evenementController.getEvenementByTag(tag);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
