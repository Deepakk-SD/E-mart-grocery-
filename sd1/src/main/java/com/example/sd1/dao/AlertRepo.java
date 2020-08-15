package com.example.sd1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sd1.UserModel.Alert;
import com.example.sd1.UserModel.Item;

public interface AlertRepo extends JpaRepository<Alert, Integer> {

	List<Alert> findAllByReadreceipt(String string);

}
