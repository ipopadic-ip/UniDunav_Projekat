package com.unidunav.administrator.dto;

import jakarta.xml.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@XmlRootElement(name = "profesori")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProfesorXmlList {

    @XmlElement(name = "profesor")
    private List<ProfesorXmlDTO> profesori = new ArrayList<>();

    public ProfesorXmlList() {}

    public ProfesorXmlList(List<ProfesorXmlDTO> profesori) {
        this.profesori = profesori;
    }

    public List<ProfesorXmlDTO> getProfesori() {
        return profesori;
    }

    public void setProfesori(List<ProfesorXmlDTO> profesori) {
        this.profesori = profesori;
    }
}
