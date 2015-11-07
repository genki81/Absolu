package org.absolu.battle.api.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Objet implements Serializable {
	private static final long serialVersionUID = -3989475047804811313L;
	private long id;
	private String name;
	private String icon;
	private int quality;
	private long itemLevel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public long getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(long itemLevel) {
		this.itemLevel = itemLevel;
	}

}
