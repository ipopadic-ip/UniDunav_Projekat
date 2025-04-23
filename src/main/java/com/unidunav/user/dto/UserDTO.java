package com.unidunav.user.dto;

import com.unidunav.user.model.DomainType;
import com.unidunav.user.model.Role;
import java.util.Set;

public class UserDTO {
	private Long id;
    private String email;
    private Set<Role> roles;
    private DomainType domainType;
    private Long domainId;
    
    
	public UserDTO(Long id, String email, Set<Role> roles, DomainType domainType, Long domainId) {
		super();
		this.id = id;
		this.email = email;
		this.roles = roles;
		this.domainType = domainType;
		this.domainId = domainId;
	}
	public DomainType getDomainType() {
		return domainType;
	}
	public void setDomainType(DomainType domainType) {
		this.domainType = domainType;
	}
	public Long getDomainId() {
		return domainId;
	}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDTO(Long id, String email, Set<Role> roles) {
		super();
		this.id = id;
		this.email = email;
		this.roles = roles;
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
