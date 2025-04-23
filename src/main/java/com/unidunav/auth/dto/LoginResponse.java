package com.unidunav.auth.dto;

public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
