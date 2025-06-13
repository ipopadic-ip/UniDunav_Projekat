package com.unidunav.dokument.dto.websocket;

public class TypingStatus {
	private Long verzijaId;
    private String autorEmail;
    private boolean typing;
    
	public Long getVerzijaId() {
		return verzijaId;
	}
	public void setVerzijaId(Long verzijaId) {
		this.verzijaId = verzijaId;
	}
	public String getAutorEmail() {
		return autorEmail;
	}
	public void setAutorEmail(String autorEmail) {
		this.autorEmail = autorEmail;
	}
	public boolean isTyping() {
		return typing;
	}
	public void setTyping(boolean typing) {
		this.typing = typing;
	}
    
    
}
