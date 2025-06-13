package com.unidunav.dokument.dto.websocket;

public class EditMessage {
	private Long verzijaId;
    private String sadrzaj;
    private String autorEmail;
    
	public Long getVerzijaId() {
		return verzijaId;
	}
	public void setVerzijaId(Long verzijaId) {
		this.verzijaId = verzijaId;
	}
	public String getSadrzaj() {
		return sadrzaj;
	}
	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}
	public String getAutorEmail() {
		return autorEmail;
	}
	public void setAutorEmail(String autorEmail) {
		this.autorEmail = autorEmail;
	}
    
}
