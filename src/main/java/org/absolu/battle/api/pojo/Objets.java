package org.absolu.battle.api.pojo;

import java.io.Serializable;

public class Objets implements Serializable {
	private static final long serialVersionUID = -8770812389514202805L;
	private long averageItemLevel;
	private long averageItemLevelEquipped;
	private Objet head;
	private Objet neck;
	private Objet shoulder;
	private Objet back;
	private Objet chest;
	private Objet shirt;
	private Objet tabard;
	private Objet wrist;
	private Objet hands;
	private Objet waist;
	private Objet legs;
	private Objet feet;
	private Objet finger1;
	private Objet finger2;
	private Objet trinket1;
	private Objet trinket2;
	private Objet mainHand;
	private Objet offHand;

	public long getAverageItemLevel() {
		return averageItemLevel;
	}

	public void setAverageItemLevel(long averageItemLevel) {
		this.averageItemLevel = averageItemLevel;
	}

	public long getAverageItemLevelEquipped() {
		return averageItemLevelEquipped;
	}

	public void setAverageItemLevelEquipped(long averageItemLevelEquipped) {
		this.averageItemLevelEquipped = averageItemLevelEquipped;
	}

	public Objet getHead() {
		return head;
	}

	public void setHead(Objet head) {
		this.head = head;
	}

	public Objet getNeck() {
		return neck;
	}

	public void setNeck(Objet neck) {
		this.neck = neck;
	}

	public Objet getShoulder() {
		return shoulder;
	}

	public void setShoulder(Objet shoulder) {
		this.shoulder = shoulder;
	}

	public Objet getBack() {
		return back;
	}

	public void setBack(Objet back) {
		this.back = back;
	}

	public Objet getChest() {
		return chest;
	}

	public void setChest(Objet chest) {
		this.chest = chest;
	}

	public Objet getShirt() {
		return shirt;
	}

	public void setShirt(Objet shirt) {
		this.shirt = shirt;
	}

	public Objet getWrist() {
		return wrist;
	}

	public void setWrist(Objet wrist) {
		this.wrist = wrist;
	}

	public Objet getHands() {
		return hands;
	}

	public void setHands(Objet hands) {
		this.hands = hands;
	}

	public Objet getWaist() {
		return waist;
	}

	public void setWaist(Objet waist) {
		this.waist = waist;
	}

	public Objet getLegs() {
		return legs;
	}

	public void setLegs(Objet legs) {
		this.legs = legs;
	}

	public Objet getFeet() {
		return feet;
	}

	public void setFeet(Objet feet) {
		this.feet = feet;
	}

	public Objet getFinger1() {
		return finger1;
	}

	public void setFinger1(Objet finger1) {
		this.finger1 = finger1;
	}

	public Objet getFinger2() {
		return finger2;
	}

	public void setFinger2(Objet finger2) {
		this.finger2 = finger2;
	}

	public Objet getTrinket1() {
		return trinket1;
	}

	public void setTrinket1(Objet trinket1) {
		this.trinket1 = trinket1;
	}

	public Objet getTrinket2() {
		return trinket2;
	}

	public void setTrinket2(Objet trinket2) {
		this.trinket2 = trinket2;
	}

	public Objet getMainHand() {
		return mainHand;
	}

	public void setMainHand(Objet mainHand) {
		this.mainHand = mainHand;
	}

	public Objet getTabard() {
		return tabard;
	}

	public void setTabard(Objet tabard) {
		this.tabard = tabard;
	}

	public Objet getOffHand() {
		return offHand;
	}

	public void setOffHand(Objet offHand) {
		this.offHand = offHand;
	}

}
