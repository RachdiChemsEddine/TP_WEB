package RACHDI_RAHMANI.TP_WEB;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // Code exécuté lors de la création d'une session
        System.out.println("Session créee: " + se.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Code exécuté lors de la destruction d'une session
        System.out.println("Session détruite: " + se.getSession().getId());
    }
}
