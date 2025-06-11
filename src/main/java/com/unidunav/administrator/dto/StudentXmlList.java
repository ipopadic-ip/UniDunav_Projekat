package com.unidunav.administrator.dto;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudentXmlList {

    @XmlElement(name = "student")
    private List<StudentXmlDTO> students = new ArrayList<>();

    public StudentXmlList() {}

    public StudentXmlList(List<StudentXmlDTO> students) {
        this.students = students;
    }

    public List<StudentXmlDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentXmlDTO> students) {
        this.students = students;
    }
}

