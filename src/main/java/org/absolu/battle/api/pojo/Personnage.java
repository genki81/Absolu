package org.absolu.battle.api.pojo;

import java.io.Serializable;
import java.util.List;

import org.absolu.battle.api.utils.BattleApiUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Personnage implements Serializable {
	private static final long serialVersionUID = 4120622827275356452L;

	private String name;
	private String realm;
	private String battlegroup;
	@JsonProperty(value = "class")
	private int classe;
	private int race;
	private int gender;
	private int level;
	private long achievementPoints;
	private String thumbnail;
	private Specialisation spec;
	private String guild;
	private String guildRealm;
	private long lastModified;
	private String calcClass;
	private int faction;
	private Objets items;
	private long totalHonorableKills;
	private List<Talent> talents;

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

	public int getClasse() {
		return classe;
	}

	public void setClasse(int classe) {
		this.classe = classe;
	}

	public int getRace() {
		return race;
	}

	public void setRace(int race) {
		this.race = race;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getAchievementPoints() {
		return achievementPoints;
	}

	public void setAchievementPoints(long achievementPoints) {
		this.achievementPoints = achievementPoints;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Specialisation getSpec() {
		return spec;
	}

	public void setSpec(Specialisation spec) {
		this.spec = spec;
	}

	public String getGuild() {
		return guild;
	}

	public void setGuild(String guild) {
		this.guild = guild;
	}

	public String getGuildRealm() {
		return guildRealm;
	}

	public void setGuildRealm(String guildRealm) {
		this.guildRealm = guildRealm;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public String getCalcClass() {
		return calcClass;
	}

	public void setCalcClass(String calcClass) {
		this.calcClass = calcClass;
	}

	public int getFaction() {
		return faction;
	}

	public void setFaction(int faction) {
		this.faction = faction;
	}

	public Objets getItems() {
		return items;
	}

	public void setItems(Objets items) {
		this.items = items;
	}

	public long getTotalHonorableKills() {
		return totalHonorableKills;
	}

	public void setTotalHonorableKills(long totalHonorableKills) {
		this.totalHonorableKills = totalHonorableKills;
	}

	public List<Talent> getTalents() {
		return talents;
	}

	public void setTalents(List<Talent> talents) {
		this.talents = talents;
	}

	public String getDisplayClasse() {
		return BattleApiUtils.getClasseById(classe).getName();
	}
}
