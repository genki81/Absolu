package org.absolu.battle.api.pojo;

import java.io.Serializable;

public class Membre implements Serializable {
	private static final long serialVersionUID = -8323407276154810970L;

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
