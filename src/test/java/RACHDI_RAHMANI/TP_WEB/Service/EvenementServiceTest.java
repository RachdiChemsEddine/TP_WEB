package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Model.Evenement;
import RACHDI_RAHMANI.TP_WEB.Repository.EvenementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class EvenementServiceTest {
    @Mock
    private EvenementRepository evenementRepository;

    @Mock
    private UserService userService;

    @Mock
    private SerieService serieService;

    @InjectMocks
    private EvenementService evenementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testGetAllEvenements() {
        // Création de quelques événements pour simuler la réponse de la base de données
        List<Evenement> evenements = Arrays.asList(
                new Evenement(UUID.randomUUID(), LocalDate.of(2023, 12, 1), 10.0, new ArrayList<>(List.of("tag1", "tag2"))),
                new Evenement(UUID.randomUUID(), LocalDate.of(2023, 12, 2), 20.0, new ArrayList<>(List.of("tag2", "tag3")))
        );

        // Simulation du comportement du repository
        when(evenementRepository.findAll()).thenReturn(evenements);

        // Appel de la méthode à tester
        List<Evenement> result = evenementService.getAllEvenements();

        // Vérification des résultats
        assertEquals(2, result.size());
        assertEquals(evenements, result);
    }

    @Test
    void testGetEvenementById() {
        UUID id = UUID.randomUUID();
        // Création d'un événement pour simuler la réponse de la base de données
        Evenement evenement = new Evenement(id, LocalDate.of(2023, 12, 1), 10.0, new ArrayList<>(List.of("tag1", "tag2")));

        // Simulation du comportement du repository
        when(evenementRepository.findByUuid(id)).thenReturn(Optional.of(evenement));

        // Appel de la méthode à tester
        Evenement result = evenementService.getEvenementById(id);

        // Vérification des résultats
        assertNotNull(result);
        assertEquals(evenement, result);
    }

    @Test
    void testCreateEvenement() {
    }

    @Test
    void testUpdateEvenement() {
        // Test de la méthode updateEvenement
        // ...
    }

    @Test
    void testDeleteEvenement() {
        // Test de la méthode deleteEvenement
        // ...
    }

    @Test
    void testGetEvenementByTag() {
        // Test de la méthode getEvenementByTag
        // ...
    }
}