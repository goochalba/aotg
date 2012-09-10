package com.aotg.mvc.domain;

import java.io.Serializable;

/**
 * A simple POJO representing a Menu Item
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = -5527566248002296042L;
	
	private Integer id;
	private String controller;
	private String prompt;
	private Integer parentMenuId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getController() {
		return controller;
	}

	public void setController(String controller) {
		this.controller = controller;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
	public Integer getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}
}
