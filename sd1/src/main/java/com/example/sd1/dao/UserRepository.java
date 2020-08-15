package com.example.sd1.dao;

import com.example.sd1.UserModel.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User , Long> {
	
	User findByUsername(String username);

}
