package com.example.sd1.UserModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Edible {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int quantity;
	private int threshold;
	private int edibility;
//	private int edibilitythreshold;
//	public int getEdibilitythreshold() {
//		return edibilitythreshold;
//	}
//	public void setEdibilitythreshold(int edibilitythreshold) {
//		this.edibilitythreshold = edibilitythreshold;
//	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	public int getEdibility() {
		return edibility;
	}
	public void setEdibility(int edibility) {
		this.edibility = edibility;
	}
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
}
