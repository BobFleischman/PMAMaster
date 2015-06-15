package com.automateddocsys.pma.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.automateddocsys.pma.repository.WebClientRepository;

@Service("authService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	WebClientRepository webClientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = webClientRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("No user names " +username + " found.");
		} else {
			return user;
		}
	}

}
