package com.unidunav.user.dto;


import com.unidunav.user.model.Role;
import java.util.Set;

public class UserDTO {
	private Long id;
    private String email;
    private String ime;
    private String prezime;
    private String adresa;
    private String jmbg;
    private Set<Role> roles;
    private boolean deleted;
    
    
    
    

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

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
	
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
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
