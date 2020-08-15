package com.example.sd1.UserModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Alert {
	@Id
	@GeneratedValue
	private int id;
	private String alertmsg;
	private String readreceipt;
	private String time;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlertmsg() {
		return alertmsg;
	}
	public void setAlertmsg(String alertmsg) {
		this.alertmsg = alertmsg;
	}
	public String getReadreceipt() {
		return readreceipt;
	}
	public void setReadreceipt(String readreceipt) {
		this.readreceipt = readreceipt;
	}
		
}
