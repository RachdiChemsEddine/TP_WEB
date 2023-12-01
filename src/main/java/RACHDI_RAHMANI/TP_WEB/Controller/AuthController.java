package RACHDI_RAHMANI.TP_WEB.Controller;

import RACHDI_RAHMANI.TP_WEB.Model.LoginRequest;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final HttpSession httpSession;

    @Autowired
    public AuthController(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest) {
        // Logique d'authentification
        // ...
        return ResponseEntity.ok("Authentification réussie");
    }

    @GetMapping("/session-info")
    public ResponseEntity<String> getSessionInfo(HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            return ResponseEntity.ok("Username in session: " + username);
        } else {
            return ResponseEntity.ok("No username in session");
        }
    }
}
