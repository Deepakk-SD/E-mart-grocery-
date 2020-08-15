package com.example.sd1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sd1.UserModel.Edible;

public interface EdibleRepo extends JpaRepository<Edible,Integer> {
	Edible findByName(String name);

	List<Edible> findAllByName(String string);
}
