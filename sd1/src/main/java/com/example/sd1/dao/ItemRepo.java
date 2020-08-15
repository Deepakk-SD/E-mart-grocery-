package com.example.sd1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.sd1.UserModel.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {
	
//	List<Item> findAllByEdible(String edible);
	Item findByName(String name);
}
