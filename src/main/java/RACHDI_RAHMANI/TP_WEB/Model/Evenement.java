package RACHDI_RAHMANI.TP_WEB.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

import java.util.Date;

@Entity
@Table(name = "evenements")
public class Evenement {
    @Id
    @Getter
    private Long ID;
    private Date date;
    private Double value;


    @Getter
    private String tag;

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
}
