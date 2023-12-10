package RACHDI_RAHMANI.TP_WEB.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventRequest {
    private LocalDate date;
    private Double valeur;
    private ArrayList<String> tags;

    public EventRequest(LocalDate date, Double valeur, ArrayList<String> tags) {
        this.date = date;
        this.valeur = valeur;
        this.tags = tags;
    }

    public EventRequest() {

    }

    public LocalDate getDate() {
        return date;
    }

    public Double getValeur() {
        return valeur;
    }

    public ArrayList<String> getTags() {
        return tags;
    }
}
