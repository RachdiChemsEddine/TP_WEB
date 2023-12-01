package RACHDI_RAHMANI.TP_WEB.Model;



public class LoginRequest {
    private String username;
    private String password;

    // Constructeurs, getters et setters
    public LoginRequest() {
        // Constructeur par défaut nécessaire pour la désérialisation JSON
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters et setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
