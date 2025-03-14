package model.adresa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Adresa {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false)
	private int broj;
	@Column(nullable=false)
	private String ulica;
	@ManyToOne
	@JoinColumn(name = "mesto_id", nullable = false)
	private Mesto mesto;
	public Adresa() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Adresa(Integer id, int broj, String ulica, Mesto mesto) {
		super();
		this.id = id;
		this.broj = broj;
		this.ulica = ulica;
		this.mesto = mesto;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getBroj() {
		return broj;
	}
	public void setBroj(int broj) {
		this.broj = broj;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public Mesto getMesto() {
		return mesto;
	}
	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
	

}
