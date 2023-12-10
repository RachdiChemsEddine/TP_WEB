package RACHDI_RAHMANI.TP_WEB.Model;

public class RegistrationRequest {
    private String username;
    private String password;
    private String nom;
    private String prenom;

    public RegistrationRequest(String username, String password, String nom, String prenom) {
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
}
