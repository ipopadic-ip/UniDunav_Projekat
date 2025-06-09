package com.unidunav.sluzbenik.dto;

public class PrimerakKnjigeDTO {
    private Long id;
    private boolean dostupan;
    private Long knjigaId;
    private String knjigaNaziv;
    
    
	public PrimerakKnjigeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PrimerakKnjigeDTO(Long id, boolean dostupan, Long knjigaId, String knjigaNaziv) {
		super();
		this.id = id;
		this.dostupan = dostupan;
		this.knjigaId = knjigaId;
		this.knjigaNaziv = knjigaNaziv;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isDostupan() {
		return dostupan;
	}
	public void setDostupan(boolean dostupan) {
		this.dostupan = dostupan;
	}
	public Long getKnjigaId() {
		return knjigaId;
	}
	public void setKnjigaId(Long knjigaId) {
		this.knjigaId = knjigaId;
	}
	public String getKnjigaNaziv() {
		return knjigaNaziv;
	}
	public void setKnjigaNaziv(String knjigaNaziv) {
		this.knjigaNaziv = knjigaNaziv;
	}
    
    
}