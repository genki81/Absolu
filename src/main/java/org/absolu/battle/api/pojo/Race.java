package org.absolu.battle.api.pojo;

import java.io.Serializable;

public class Race implements Serializable {
	private static final long serialVersionUID = -3163312936460985018L;

	private int id;
	private int mask;
	private String side;
	private String name;

	public Race() {
	}

	public Race(int id, int mask, String side, String name) {
		this.id = id;
		this.mask = mask;
		this.side = side;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMask() {
		return mask;
	}

	public void setMask(int mask) {
		this.mask = mask;
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
		return "id:" + this.id + "-mask:" + this.mask + "-side:" + this.side + "-name:" + this.name;
	}
}
