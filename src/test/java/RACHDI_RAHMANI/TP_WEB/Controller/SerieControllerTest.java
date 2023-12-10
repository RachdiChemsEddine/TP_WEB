package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.Serie;
import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Service.SerieService;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc

public class SerieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SerieService serieService;

    @MockBean
    private UserService userService;

    @Test
    public void getAllSerieTest() throws Exception {
        Serie serie1 = new Serie();
        serie1.setTitle("Test Titre 1");
        Serie serie2 = new Serie();
        serie2.setTitle("Test Titre 2");

        List<Serie> series = Arrays.asList(serie1, serie2);

        when(serieService.getAllSerie()).thenReturn(series);

        mockMvc.perform(get("/series")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("Test Titre 1"))
                .andExpect(jsonPath("$[1].title").value("Test Titre 2"));
    }

    @Test
    public void getSerieByTitleTest() throws Exception {
        String serieTitle = "Test Titre";
        Serie serie = new Serie();
        serie.setTitle(serieTitle);

        when(serieService.getSerieByTitle(serieTitle)).thenReturn(serie);

        mockMvc.perform(get("/series/" + serieTitle)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titre").value(serieTitle));
    }

    @Test
    public void getSerieByTitleNotFoundTest() throws Exception {
        String serieTitle = "Test Titre";

        when(serieService.getSerieByTitle(serieTitle)).thenReturn(null);

        mockMvc.perform(get("/series/" + serieTitle)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createSerieTest() throws Exception {
        String username = "Test User";
        String serieTitle = "Test Titre";
        String serieDescription = "Test Description";

        User user = new User();
        user.setUsername(username);
        Serie serie = new Serie();
        serie.setTitle(serieTitle);
        serie.setDescription(serieDescription);

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", username);

        when(userService.findUser(username)).thenReturn(user);
        when(serieService.userHasSerie(username, serieTitle)).thenReturn(false);
        when(serieService.createSerie(serieTitle, serieDescription)).thenReturn(serie);

        mockMvc.perform(post("/series/create")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(serie)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(serieTitle))
                .andExpect(jsonPath("$.description").value(serieDescription));
    }

    @Test
    public void createSerieUnauthorizedTest() throws Exception {
        Serie serie = new Serie();
        serie.setTitle("Test Titre");
        serie.setDescription("Test Description");

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", null);

        mockMvc.perform(post("/series/create")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(serie)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void updateSerieUnauthorizedTest() throws Exception {
        Serie updatedSerie = new Serie();
        updatedSerie.setTitle("Updated Title");
        updatedSerie.setDescription("Updated Description");

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", null);

        mockMvc.perform(put("/{serieTitle}", "Test Title")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedSerie)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void updateSerieNotFoundTest() throws Exception {
        String username = "Test User";
        String serieTitle = "Test Title";
        Serie updatedSerie = new Serie();
        updatedSerie.setTitle("Updated Title");
        updatedSerie.setDescription("Updated Description");

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", username);

        when(serieService.getSerieByTitle(serieTitle)).thenReturn(null);

        mockMvc.perform(put("/{serieTitle}", serieTitle)
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedSerie)))
                .andExpect(status().isNotFound());
    }


    @Test
    public void updateSerieTest() throws Exception {
        String username = "Test User";
        String serieTitle = "Test Title";
        Serie updatedSerie = new Serie();
        updatedSerie.setTitle("Updated Title");
        updatedSerie.setDescription("Updated Description");

        Serie resultSerie = new Serie();
        resultSerie.setTitle(updatedSerie.getTitle());
        resultSerie.setDescription(updatedSerie.getDescription());

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", username);

        when(serieService.getSerieByTitle(serieTitle)).thenReturn(new Serie());
        when(serieService.updateSerie(serieTitle, updatedSerie)).thenReturn(resultSerie);

        mockMvc.perform(put("/{serieTitle}", serieTitle)
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(updatedSerie)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(updatedSerie.getTitle()))
                .andExpect(jsonPath("$.description").value(updatedSerie.getDescription()));
    }

    @Test
    public void deleteSerieUnauthorizedTest() throws Exception {
        String serieTitle = "Test Title";

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", null);

        mockMvc.perform(delete("/{serieTitle}", serieTitle)
                        .session(mockHttpSession))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteSerieNotFoundTest() throws Exception {
        String username = "Test User";
        String serieTitle = "Test Title";

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", username);

        when(userService.findUser(username)).thenReturn(new User());
        when(serieService.getSerieByTitle(serieTitle)).thenReturn(null);

        mockMvc.perform(delete("/{serieTitle}", serieTitle)
                        .session(mockHttpSession))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteSerieUserHasNoSerieTest() throws Exception {
        String username = "Test User";
        String serieTitle = "Test Title";

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", username);

        when(userService.findUser(username)).thenReturn(new User());
        when(serieService.getSerieByTitle(serieTitle)).thenReturn(new Serie());
        when(serieService.userHasSerie(username, serieTitle)).thenReturn(false);

        mockMvc.perform(delete("/{serieTitle}", serieTitle)
                        .session(mockHttpSession))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void deleteSerieTest() throws Exception {
        String username = "Test User";
        String serieTitle = "Test Title";

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", username);

        when(userService.findUser(username)).thenReturn(new User());
        when(serieService.getSerieByTitle(serieTitle)).thenReturn(new Serie());
        when(serieService.userHasSerie(username, serieTitle)).thenReturn(true);

        mockMvc.perform(delete("/{serieTitle}", serieTitle)
                        .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(content().string("Serie "+ serieTitle +" deleted successfully"));
    }

    @Test
    public void shareSerieWithUserUnauthorizedTest() throws Exception {
        String serieTitle = "Test Title";
        String username = "Test User";

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", null);

        mockMvc.perform(post("/{serieTitle}/share-with-user", serieTitle)
                        .session(mockHttpSession)
                        .param("username", username))
                .andExpect(status().isUnauthorized());
    }


        @Test
        public void shareSerieWithUserHasNoSerieTest() throws Exception {
            String authUsername = "Auth User";
            String serieTitle = "Test Title";
            String username = "Test User";

            MockHttpSession mockHttpSession = new MockHttpSession();
            mockHttpSession.setAttribute("username", authUsername);

            when(serieService.userHasSerie(authUsername, serieTitle)).thenReturn(false);

            mockMvc.perform(post("/{serieTitle}/share-with-user", serieTitle)
                            .session(mockHttpSession)
                            .param("username", username))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shareSerieWithUserNotFoundTest() throws Exception {
        String authUsername = "Auth User";
        String serieTitle = "Test Title";
        String username = "Test User";

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", authUsername);

        when(serieService.userHasSerie(authUsername, serieTitle)).thenReturn(true);
        when(serieService.shareSerieWithUser(serieTitle, username)).thenReturn(null);

        mockMvc.perform(post("/{serieTitle}/share-with-user", serieTitle)
                        .session(mockHttpSession)
                        .param("username", username))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void shareSerieWithUserTest() throws Exception {
        String authUsername = "Auth User";
        String serieTitle = "Test Title";
        String username = "Test User";

        Serie sharedSerie = new Serie();
        sharedSerie.setTitle(serieTitle);

        MockHttpSession mockHttpSession = new MockHttpSession();
        mockHttpSession.setAttribute("username", authUsername);

        when(serieService.userHasSerie(authUsername, serieTitle)).thenReturn(true);
        when(serieService.shareSerieWithUser(serieTitle, username)).thenReturn(sharedSerie);

        mockMvc.perform(post("/{serieTitle}/share-with-user", serieTitle)
                        .session(mockHttpSession)
                        .param("username", username))
                .andExpect(status().isOk())
                .andExpect(content().string("Serie shared successfully with user: " + username));
    }

}
