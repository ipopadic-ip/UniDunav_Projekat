package com.unidunav.user.dto;


import com.unidunav.user.model.Role;
import java.util.Set;

public class UserDTO {
	private Long id;
    private String email;
    private Set<Role> roles;
    
    
	public UserDTO(Long id, String email, Set<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.roles = roles;
	}
	
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    
}
