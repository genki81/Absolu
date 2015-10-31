package org.absolu.battle.api.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Upgrade implements Serializable {
	private static final long serialVersionUID = -4973884600440743681L;
	private int current;
	private int total;
	private long itemLevelIncrement;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public long getItemLevelIncrement() {
		return itemLevelIncrement;
	}

	public void setItemLevelIncrement(long itemLevelIncrement) {
		this.itemLevelIncrement = itemLevelIncrement;
	}
}
