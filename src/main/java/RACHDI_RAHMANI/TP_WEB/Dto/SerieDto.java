package RACHDI_RAHMANI.TP_WEB.Dto;

import java.util.List;

public class SerieDto {

    private Long id;
    private String title;
    private String description;
    private List EvenementDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List getEvenementDtos() {
        return EvenementDtos;
    }

    public void setEvenementDtos(List evenementDtos) {
        EvenementDtos = evenementDtos;
    }
}
