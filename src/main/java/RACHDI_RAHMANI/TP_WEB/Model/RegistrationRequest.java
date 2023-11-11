package RACHDI_RAHMANI.TP_WEB.Model;

import lombok.Data;

public class RegistrationRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
