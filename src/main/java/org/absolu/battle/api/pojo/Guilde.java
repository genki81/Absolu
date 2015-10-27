package org.absolu.battle.api.pojo;

import java.util.List;

public class Guilde {
	private long lastModified;
	private String name;
	private String realm;
	private String battlegroup;
	private int level;
	private int side;
	private long achievementPoints;
	private List<Membre> members;
	private Embleme emblem;

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getBattlegroup() {
		return battlegroup;
	}

	public void setBattlegroup(String battlegroup) {
		this.battlegroup = battlegroup;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public long getAchievementPoints() {
		return achievementPoints;
	}

	public void setAchievementPoints(long achievementPoints) {
		this.achievementPoints = achievementPoints;
	}

	public List<Membre> getMembers() {
		return members;
	}

	public void setMembers(List<Membre> members) {
		this.members = members;
	}

	public Embleme getEmblem() {
		return emblem;
	}

	public void setEmblem(Embleme emblem) {
		this.emblem = emblem;
	}

}
