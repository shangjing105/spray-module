package com.shang.spray.security;

import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Authenticate a user from the database.
 */
public class CustomUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
		String login = token.getPrincipal().toString();
		String username = login.toLowerCase();

		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

		return new AppUserDetails(username, grantedAuthorities);
	}

}
