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

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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
        when(evenementRepository.findByUuid(id)).thenReturn((evenement));

        // Appel de la méthode à tester
        Evenement result = evenementService.getEvenementById(id);

        // Vérification des résultats
        assertNotNull(result);
        assertEquals(evenement, result);
    }

    @Test
    public void testCreateEvenement() {
        // Création d'un événement fictif pour le test
        LocalDate date = LocalDate.now();
        Double valeur = 10.5;
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");

        Evenement evenement = new Evenement(date, valeur, tags);

        // Stubbing du comportement du repository
        when(evenementRepository.save(any(Evenement.class))).thenReturn(evenement);

        // Appel de la méthode à tester
        Evenement createdEvenement = evenementService.createEvenement(date, valeur, tags);

        // Vérification
        assertNotNull(createdEvenement);
        assertEquals(date, createdEvenement.getDate());
        assertEquals(valeur, createdEvenement.getValeur());
        assertEquals(tags, createdEvenement.getTags());
    }

    @Test
    public void testUpdateEvenement() {
        // Création d'un événement fictif pour le test
        UUID evenementId = UUID.randomUUID();
        Evenement updatedEvenement = new Evenement(LocalDate.now(), 20.0, new ArrayList<>());

        // Stubbing du comportement du repository
        when(evenementRepository.findByUuid(evenementId)).thenReturn(updatedEvenement);
        when(evenementRepository.save(any(Evenement.class))).thenReturn(updatedEvenement);

        // Appel de la méthode à tester
        Evenement existingEvenement = evenementService.updateEvenement(evenementId, updatedEvenement);

        // Vérification
        assertNotNull(existingEvenement);
        assertEquals(updatedEvenement.getDate(), existingEvenement.getDate());
        assertEquals(updatedEvenement.getValeur(), existingEvenement.getValeur());
    }

    @Test
    public void testDeleteEvenement() {
        // Création d'un événement fictif pour le test
        UUID evenementId = UUID.randomUUID();
        Evenement evenementToDelete = new Evenement(LocalDate.now(), 30.0, new ArrayList<>());

        // Stubbing du comportement du repository
        when(evenementRepository.findByUuid(evenementId)).thenReturn(evenementToDelete);

        // Appel de la méthode à tester
        evenementService.deleteEvenement(evenementId);

        // Vérification que la méthode de suppression du repository a été appelée
        verify(evenementRepository, times(1)).delete(evenementToDelete);
    }

    @Test
    public void testGetEvenementByTag() {
        // Création d'événements fictifs pour le test
        List<String> tags = new ArrayList<>();
        tags.add("tag1");
        tags.add("tag2");

        Evenement evenement1 = new Evenement(LocalDate.now(), 10.0, tags);
        Evenement evenement2 = new Evenement(LocalDate.now(), 20.0, tags);

        List<Evenement> evenementsWithTag = new ArrayList<>();
        evenementsWithTag.add(evenement1);
        evenementsWithTag.add(evenement2);

        // Stubbing du comportement du repository
        when(evenementRepository.findByTags("tag1")).thenReturn(evenementsWithTag);

        // Appel de la méthode à tester
        List<Evenement> retrievedEvenements = evenementService.getEvenementByTag("tag1");

        // Vérification
        assertNotNull(retrievedEvenements);
        assertEquals(2, retrievedEvenements.size());
        assertEquals(evenement1, retrievedEvenements.get(0));
        assertEquals(evenement2, retrievedEvenements.get(1));
    }
}