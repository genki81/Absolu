package org.absolu.battle.api.pojo;

import java.io.Serializable;

public class Stat implements Serializable {
	private static final long serialVersionUID = -1733568836886031330L;
	private int stat;
	private long amount;
	private boolean reforged;
	private long reforgedAmount;

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public boolean isReforged() {
		return reforged;
	}

	public void setReforged(boolean reforged) {
		this.reforged = reforged;
	}

	public long getReforgedAmount() {
		return reforgedAmount;
	}

	public void setReforgedAmount(long reforgedAmount) {
		this.reforgedAmount = reforgedAmount;
	}
}
