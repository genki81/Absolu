package org.absolu.battle.api.pojo;

import java.io.Serializable;

public class WeaponInfo implements Serializable {
	private static final long serialVersionUID = -6038894250684878551L;
	private Damage damage;
	private float weaponSpeed;
	private float dps;

	public Damage getDamage() {
		return damage;
	}

	public void setDamage(Damage damage) {
		this.damage = damage;
	}

	public float getWeaponSpeed() {
		return weaponSpeed;
	}

	public void setWeaponSpeed(float weaponSpeed) {
		this.weaponSpeed = weaponSpeed;
	}

	public float getDps() {
		return dps;
	}

	public void setDps(float dps) {
		this.dps = dps;
	}
}
