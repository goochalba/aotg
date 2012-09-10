package com.aotg.mvc.domain;

import java.io.Serializable;

/**
 * A simple POJO representing a User
 */
public class Users implements Serializable {

	private static final long serialVersionUID = -5527566248002296042L;
	
	private Integer id;
	private String userName;
	private Integer pin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getPin() {
		return pin;
	}

	public void setPin(Integer pin) {
		this.pin = pin;
	}
	
}
