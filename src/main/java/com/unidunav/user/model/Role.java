package com.unidunav.user.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ADMIN,
    STUDENT,
    PROFESSOR,
    SLUZBENIK;
	
	 @Override
	    public String getAuthority() {
	        return name();
	    }
}
