package com.example.sd1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sd1.UserModel.Shelf;

public interface ShelfRepo extends JpaRepository<Shelf ,Integer> {

	Shelf findByName(String name);

}
