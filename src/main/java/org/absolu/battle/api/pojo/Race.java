package org.absolu.battle.api.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Race implements Serializable {
	private static final long serialVersionUID = -3163312936460985018L;

	private int id;
	private String side;
	private String name;

	public Race() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id:" + this.id + "-side:" + this.side + "-name:" + this.name;
	}
}
