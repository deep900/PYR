/**
 * 
 */
package com.pradheep.dao.config;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pradheep.dao.model.UserType;
import com.pry.security.utility.PublicUtility;



/**
 * @author pradheep.p
 * 
 */
public class AppUserDetailService implements UserDetailsService {
	
	private String appKey = "IMXYlDmP4f4=";

	@Autowired
	UserManagementService userManagementService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	private Logger getLogger() {
		Logger logger = Logger.getLogger(this.getClass());
		return logger;
	}	
	
	public UserDetails loadUserByUsername(String userLoginName)
			throws UsernameNotFoundException {
		
		MyUserDetails ud = null;
		try {
			getLogger().info(
					"[User Details Service ] - Trying to authenticate "
							+ userLoginName);
			UserType uType = userManagementService
					.getUserByloginName(userLoginName);
			Collection<GrantedAuthority> authorities = userManagementService
					.getUserAuthorities(userLoginName);
			
			/*
			 * authorities.add(new MyGrantedAuthority("ROLE_ADMIN"));
			 * authorities.add(new MyGrantedAuthority("ROLE_USER"));
			 */
			getLogger().info("Printing authorities " + authorities.toString());
			if (null != uType) {
				ud = new MyUserDetails();
				ud.username = uType.getLoginName();				
				ud.authority = authorities;				
				ud.password = PublicUtility.getInstance(appKey).DecryptText(uType.getPassword());
				ud.isEnabled = true;				
				getLogger().info("Returning the user details " + ud.toString());
			} else {
				getLogger().info("User type not found !");
			}
		} catch (Exception err) {
			err.printStackTrace();
		}
		return ud;
	}

	class MyUserDetails implements UserDetails {

		protected Collection<GrantedAuthority> authority = new ArrayList<GrantedAuthority>();
		protected boolean isEnabled = true;
		protected String password, username;

		public Collection<? extends GrantedAuthority> getAuthorities() {
			// TODO Auto-generated method stub
			return authority;
		}

		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}

		public String getUsername() {
			// TODO Auto-generated method stub
			return username;
		}

		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return isEnabled;
		}

	}

}
