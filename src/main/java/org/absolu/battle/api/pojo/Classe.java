package org.absolu.battle.api.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Classe implements Serializable {
	private static final long serialVersionUID = 6459631380263963061L;

	private int id;
	// private int mask;
	// private String powerType;
	private String name;

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

	@Override
	public String toString() {
		return "id:" + this.id + "-name:" + this.name;
	}
}
