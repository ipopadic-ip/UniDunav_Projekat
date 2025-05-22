package com.unidunav.predmet.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipovi_evaluacije")
public class TipEvaluacije {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String tip;

    @OneToMany(mappedBy = "tipEvaluacije", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluacijaZnanja> evaluacije;

    public TipEvaluacije() {}

    public TipEvaluacije(String tip) {
        this.tip = tip;
    }

    // Getteri i setteri

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<EvaluacijaZnanja> getEvaluacije() {
        return evaluacije;
    }

    public void setEvaluacije(List<EvaluacijaZnanja> evaluacije) {
        this.evaluacije = evaluacije;
    }
}
