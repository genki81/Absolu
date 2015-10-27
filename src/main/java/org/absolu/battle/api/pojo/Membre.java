package org.absolu.battle.api.pojo;

public class Membre {
	private Personnage character;
	private int rank;

	public Personnage getCharacter() {
		return character;
	}

	public void setCharacter(Personnage character) {
		this.character = character;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}
