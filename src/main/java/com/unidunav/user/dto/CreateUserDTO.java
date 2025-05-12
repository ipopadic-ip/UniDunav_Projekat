package com.unidunav.user.dto;

import com.unidunav.user.model.Role;
import java.util.Set;

public class CreateUserDTO {
	private String email;
    private String password;
    private Set<Role> roles;
    
   
	public CreateUserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CreateUserDTO(String email, String password, Set<Role> roles) {
		super();
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
}
