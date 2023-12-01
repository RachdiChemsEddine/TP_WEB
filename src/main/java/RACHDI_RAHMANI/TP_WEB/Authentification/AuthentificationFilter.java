/*
package RACHDI_RAHMANI.TP_WEB.Authentification;


import RACHDI_RAHMANI.TP_WEB.Model.User;
import RACHDI_RAHMANI.TP_WEB.Service.UserService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/user/*")
public class AuthentificationFilter implements Filter {
    private final UserService userService;
    public AuthentificationFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation du filtre, si nécessaire
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        // Vérifie si l'utilisateur est authentifié
        HttpSession session = httpRequest.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            // L'utilisateur est déjà authentifié, continuer la chaîne
            chain.doFilter(request, response);
        } else {
            // Tentative d'authentification
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userService.findUser(username);

            if (user != null && user.getPassword().equals(password)) {
                // Authentification réussie, créer une session
                HttpSession newSession = httpRequest.getSession(true);
                newSession.setAttribute("username", username);
                chain.doFilter(request, response);
            } else {
                // Authentification échouée, renvoyer une réponse non autorisée
                response.getWriter().write("Authentication failed");
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }
    }

    @Override
    public void destroy() {
        // Destruction du filtre, si nécessaire
    }
}

*/
