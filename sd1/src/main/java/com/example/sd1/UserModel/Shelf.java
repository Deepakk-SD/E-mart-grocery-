package com.example.sd1.UserModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Shelf {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int quantity;
	private int thresholddaily;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getThresholddaily() {
		return thresholddaily;
	}
	public void setThresholddaily(int thresholddaily) {
		this.thresholddaily = thresholddaily;
	}
	
}
