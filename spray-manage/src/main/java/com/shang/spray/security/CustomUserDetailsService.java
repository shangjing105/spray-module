package com.shang.spray.security;

import com.shang.spray.entity.User;
import com.shang.spray.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * info:
 * Created by shang on 16/7/27.
 */
public class CustomUserDetailsService  implements UserDetailsService {

	@Resource
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("not found");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ADMIN"));
		return new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), authorities);
	}
}
