package com.example.sd1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sd1.UserModel.Update;

public interface UpdateRepo extends JpaRepository<Update, Integer> {
	
}
