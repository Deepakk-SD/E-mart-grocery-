package com.example.sd1.UserModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	String name;
	String category;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	private int quantity;
	private int threshold;
	private int edibility;
	private int transportDamage;
	private int expired;
	private int shopppingDamage;
	private int bprice;
	private int sprice;
	


	
	public int getBuyingprice() {
		return bprice;
	}
	public void setBuyingprice(int buyingprice) {
		this.bprice = buyingprice;
	}
	public int getSellingprice() {
		return sprice;
	}
	public void setSellingprice(int sellingprice) {
		this.sprice = sellingprice;
	}
	public int getTransportDamage() {
		return transportDamage;
	}
	public void setTransportDamage(int transportDamage) {
		this.transportDamage = transportDamage;
	}
	public int getExpired() {
		return expired;
	}
	public void setExpired(int expired) {
		this.expired = expired;
	}
	public int getShopppingDamage() {
		return shopppingDamage;
	}
	public void setShopppingDamage(int shopppingDamage) {
		this.shopppingDamage = shopppingDamage;
	}
	public int getEdibility() {
		return edibility;
	}
	public void setEdibility(int edibility) {
		this.edibility = edibility;
	}
	public int getThreshold() {
		return threshold;
	}
	public void setThreshold(int threshold) {
		this.threshold = threshold;
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
