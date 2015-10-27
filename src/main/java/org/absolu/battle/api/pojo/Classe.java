package org.absolu.battle.api.pojo;

public class Classe {
	private int id;
	private int mask;
	private String powerType;
	private String name;

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

	public String getPowerType() {
		return powerType;
	}

	public void setPowerType(String powerType) {
		this.powerType = powerType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "id:" + this.id + "-mask:" + this.mask + "-powerType:" + this.powerType + "-name:" + this.name;
	}
}
