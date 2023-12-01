package RACHDI_RAHMANI.TP_WEB.Dto;

import java.util.Date;

public class EvenementDto {

    private Long evenementId;

    private Date date;

    private Double value;

    // Getters et Setters

    public Long getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(Long evenementId) {
        this.evenementId = evenementId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
