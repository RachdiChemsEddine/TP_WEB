package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Model.RegistrationRequest;
import RACHDI_RAHMANI.TP_WEB.Repository.UserRepository;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, HttpSession httpSession) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        User user = userService.findUser(username);

        if (user != null && user.getPassword().equals(password)) {
            // Authentification réussie, créer une session
            httpSession.setAttribute("username", username);
            return ResponseEntity.ok("Authentication successful");
        } else {
            // Authentification échouée
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    // Autres méthodes du contrôleur...

    @GetMapping("/session-info")
    public ResponseEntity<String> getSessionInfo() {
        String username = (String) httpSession.getAttribute("username");

        if (username != null) {
            return ResponseEntity.ok("Username in session: " + username);
        } else {
            return ResponseEntity.ok("No username in session");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        // Récupérez les détails de la demande d'inscription (par exemple, nom d'utilisateur et mot de passe)
        String username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();
        String nom = registrationRequest.getNom();
        String prenom = registrationRequest.getPrenom();
        if (userRepository.findByUsername(username) != null){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        // Créez un nouvel utilisateur
        userService.createUser(username, password, nom, prenom);

        // Exemple d'utilisation de la session
        httpSession.setAttribute("username", username);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/{username}")
    public ResponseEntity<User> getUserDetails(@PathVariable String username) {
        // Récupérez les détails de l'utilisateur
        if (userService.findUser(username) == null){
            return ResponseEntity.notFound().build();
        }
        User userDetails = userService.findUser(username);

        return ResponseEntity.ok(userDetails);
    }

    @PutMapping("/{username}")
    public ResponseEntity<String> updateUserDetails(@PathVariable String username, @RequestParam String password) {
        // Mettre à jour les détails de l'utilisateur
        if (userService.findUser(username) == null){
            return ResponseEntity.badRequest().body("Utilisateur non trouvé");
        }
        userService.updateUser(username, password);

        // Exemple d'utilisation de la session
        httpSession.setAttribute("username", username);

        return ResponseEntity.ok("Détails utilisateur mis à jour avec succès");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            // Supprimer l'attribut "username" de la session
            session.removeAttribute("username");
            return ResponseEntity.ok("Déconnexion réussie pour l'utilisateur : " + username);
        } else {
            return ResponseEntity.ok("Aucun utilisateur connecté");
        }
    }
}
