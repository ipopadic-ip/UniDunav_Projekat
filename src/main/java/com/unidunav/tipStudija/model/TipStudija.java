package com.unidunav.tipStudija.model;

import java.util.ArrayList;

import com.unidunav.katedra.model.Katedra;
import com.unidunav.studijskiProgram.model.StudijskiProgram;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TipStudija {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
    private String tip;
	
	@ManyToOne
    @JoinColumn(name = "katedra_id", nullable = true)
	private Katedra katedra;
	
	@OneToMany(mappedBy = "tipStudija", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<StudijskiProgram> studijskiProgrami = new ArrayList<StudijskiProgram>();

	public TipStudija() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipStudija(Long id, String tip, Katedra katedra, ArrayList<StudijskiProgram> studijskiProgrami) {
		super();
		this.id = id;
		this.tip = tip;
		this.katedra = katedra;
		this.studijskiProgrami = studijskiProgrami;
	}

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

	public Katedra getKatedra() {
		return katedra;
	}

	public void setKatedra(Katedra katedra) {
		this.katedra = katedra;
	}

	public ArrayList<StudijskiProgram> getStudijskiProgrami() {
		return studijskiProgrami;
	}

	public void setStudijskiProgrami(ArrayList<StudijskiProgram> studijskiProgrami) {
		this.studijskiProgrami = studijskiProgrami;
	}
	
	
	
}
