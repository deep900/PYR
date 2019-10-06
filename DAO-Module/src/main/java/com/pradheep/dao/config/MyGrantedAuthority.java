package com.pradheep.dao.config;

import org.springframework.security.core.GrantedAuthority;

public class MyGrantedAuthority implements GrantedAuthority {

	String grantedAuthority;

	public MyGrantedAuthority(String role) {
		grantedAuthority = role;
	}

	public String getAuthority() {
		// TODO Auto-generated method stub
		return grantedAuthority;
	}

}
