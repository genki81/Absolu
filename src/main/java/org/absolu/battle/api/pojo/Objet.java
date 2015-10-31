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

	// private TooltipParams tooltipParams;
	// private List<Stat> stats;
	// private long armor;
	// private WeaponInfo weaponInfo;
	// private String context;
	// private List<Long> bonusLists;

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

	// public TooltipParams getTooltipParams() {
	// return tooltipParams;
	// }
	//
	// public void setTooltipParams(TooltipParams tooltipParams) {
	// this.tooltipParams = tooltipParams;
	// }
	//
	// public List<Stat> getStats() {
	// return stats;
	// }
	//
	// public void setStats(List<Stat> stats) {
	// this.stats = stats;
	// }
	//
	// public long getArmor() {
	// return armor;
	// }
	//
	// public void setArmor(long armor) {
	// this.armor = armor;
	// }
	//
	// public String getContext() {
	// return context;
	// }
	//
	// public void setContext(String context) {
	// this.context = context;
	// }
	//
	// public List<Long> getBonusLists() {
	// return bonusLists;
	// }
	//
	// public void setBonusLists(List<Long> bonusLists) {
	// this.bonusLists = bonusLists;
	// }
	//
	// public WeaponInfo getWeaponInfo() {
	// return weaponInfo;
	// }
	//
	// public void setWeaponInfo(WeaponInfo weaponInfo) {
	// this.weaponInfo = weaponInfo;
	// }
}
