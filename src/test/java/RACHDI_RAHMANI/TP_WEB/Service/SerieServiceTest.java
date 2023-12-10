package RACHDI_RAHMANI.TP_WEB.Service;

import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Repository.SerieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;



@ExtendWith(MockitoExtension.class)
public class SerieServiceTest {

    @Mock
    private SerieRepository serieRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private SerieService serieService;

    @Test
    public void getAllSerieTest() {
        Serie serie = new Serie();
        when(serieRepository.findAll()).thenReturn(Arrays.asList(serie));

        List<Serie> result = serieService.getAllSerie();

        assertEquals(1, result.size());
        assertEquals(serie, result.get(0));
    }

    @Test
    public void getSerieByIdTest() {
        Serie serie = new Serie();
        //when(serieRepository.findById(1L)).thenReturn(Optional.ofNullable(serie));

        Object result = serieService.getSerieById(1L);

        assertEquals(serie, result);
    }

    @Test
    public void createSerieTest() {
        String title = "Test Titre";
        String description = "Test Description";

        Serie serie = new Serie();
        serie.setTitle(title);
        serie.setDescription(description);

        when(serieRepository.save(any(Serie.class))).thenReturn(serie);

        Serie result = serieService.createSerie(title, description);

        assertEquals(serie, result);
    }

    @Test
    public void updateSerieTest() {
        String title = "Titre existant";
        String newTitle = "Nouveau Titre";
        String newDescription = "Nouvelle Description";

        Serie existingSerie = new Serie();
        existingSerie.setTitle(title);

        Serie updatedSerie = new Serie();
        updatedSerie.setTitle(newTitle);
        updatedSerie.setDescription(newDescription);

        when(serieRepository.findByTitle(title)).thenReturn(existingSerie);
        when(serieRepository.save(any(Serie.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Serie result = serieService.updateSerie(title, updatedSerie);

        assertEquals(newTitle, result.getTitle());
        assertEquals(newDescription, result.getDescription());
    }

    @Test
    public void deleteSerieByTitleTest() {
        String title = "Test Titre";

        doNothing().when(serieRepository).deleteByTitle(title);

        serieService.deleteSerieByTitle(title);

        verify(serieRepository, times(1)).deleteByTitle(title);
    }

    @Test
    public void userHasSerieTest() {
        String username = "Test User";
        String serieTitle = "Test Titre";

        User user = mock(User.class);
        Serie serie = mock(Serie.class);
        when(serie.getTitle()).thenReturn(serieTitle);
        List<Serie> series = new ArrayList<>();
        series.add(serie);
        when(user.getOwnSeries()).thenReturn(series);

        when(userService.findUser(username)).thenReturn(user);
        when(serieRepository.findByTitle(serieTitle)).thenReturn(serie);

        Boolean result = serieService.userHasSerie(username, serieTitle);

        assertTrue(result);
    }

    @Test
    public void getSerieByTitleTest() {
        String title = "Test Title";

        Serie serie = new Serie();
        serie.setTitle(title);

        when(serieRepository.findByTitle(title)).thenReturn(serie);

        Serie result = serieService.getSerieByTitle(title);

        assertEquals(serie, result);
    }

    @Test
    public void shareSerieWithUserTest() {
        String username = "Test User";
        String serieTitle = "Test Titre";

        User user = new User();
        user.setUsername(username);
        Serie serie = new Serie();
        serie.setTitle(serieTitle);

        when(serieRepository.findByTitle(serieTitle)).thenReturn(serie);
        when(userService.findUser(username)).thenReturn(user);
        doNothing().when(userService).updateUser(username, user);

        Serie result = serieService.shareSerieWithUser(serieTitle, username);

        verify(userService).updateUser(username, user);
        assertEquals(serie, result);
    }




}