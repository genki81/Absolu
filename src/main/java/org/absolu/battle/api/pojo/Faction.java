package org.absolu.battle.api.pojo;

public enum Faction {
	ALLIANCE(0), HORDE(1), NEUTRAL(2);

	private int id = -1;

	// Constructeur
	Faction(int id) {
		this.id = id;
	}

	public static String findById(int id) {
		for (Faction f : values()) {
			if (f.id == id) {
				return f.toString();
			}
		}
		return "";
	}

}