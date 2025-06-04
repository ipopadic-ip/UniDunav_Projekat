package com.unidunav.sluzbenik.dto;

import java.util.List;

public class UpisRequestDTO {
    private Long studentId;
    private List<Long> predmetIds;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public List<Long> getPredmetIds() {
        return predmetIds;
    }

    public void setPredmetIds(List<Long> predmetIds) {
        this.predmetIds = predmetIds;
    }
}
