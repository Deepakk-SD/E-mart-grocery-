package com.example.sd1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sd1.dao.UserRepository;
import com.example.sd1.UserModel.User;

import java.io.IOException;

@Controller
public class LoginController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDetailsService userDetailsService;
	
//	@RequestMapping("/")
//	public String home() {
//		return "inventory";
//	}
//	
	@PostMapping("auser")
	public String addUser(User newuser) {
//		newUser.setUsername("deep");
//		newUser.setPassword("deep");
		
		userRepository.save(newuser);
		System.out.println(newuser);
//		userDetailsService.loadUserByUsername(newuser.getUsername());
		return "adduser";
	}
	@RequestMapping("adduser")
		public String adduser() {
			return "newuser";
		}
	
	@RequestMapping("/login")
	public String loginPage(AbstractAuthenticationToken authentication) throws IOException {
		if (authentication != null && authentication.isAuthenticated())
			return "redirect:/";
		return "login";
	}

}
