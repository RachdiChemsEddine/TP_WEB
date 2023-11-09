package RACHDI_RAHMANI.TP_WEB.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Evenement {
    @Id
    private Long ID;
    private String key;
    private String value;
    private String tag;
}
