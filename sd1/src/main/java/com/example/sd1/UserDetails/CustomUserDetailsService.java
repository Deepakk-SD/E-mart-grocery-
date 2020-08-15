package com.example.sd1.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sd1.UserModel.User;
import com.example.sd1.dao.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
		
		@Autowired
		private UserRepository repo;
		
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
			User user=repo.findByUsername(username);
			
			if(user==null) 
				throw new UsernameNotFoundException("User 404");
			
			return new CustomUserDetails(user);
			
		}

}
