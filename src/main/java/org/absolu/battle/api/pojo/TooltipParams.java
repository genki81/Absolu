package org.absolu.battle.api.pojo;

import java.io.Serializable;
import java.util.List;

public class TooltipParams implements Serializable {
	private static final long serialVersionUID = 7155352453377042442L;
	private int timewalkerLevel;
	private List<Long> set;
	private long transmogItem;
	private long enchant;
	private long gem0;
	private long gem1;
	private long gem2;
	private long gem3;
	private long suffix;
	private long seed;
	private Upgrade upgrade;
	private boolean extraSocket;
	private long reforge;
	private long tinker;

	public int getTimewalkerLevel() {
		return timewalkerLevel;
	}

	public void setTimewalkerLevel(int timewalkerLevel) {
		this.timewalkerLevel = timewalkerLevel;
	}

	public List<Long> getSet() {
		return set;
	}

	public void setSet(List<Long> set) {
		this.set = set;
	}

	public long getTransmogItem() {
		return transmogItem;
	}

	public void setTransmogItem(long transmogItem) {
		this.transmogItem = transmogItem;
	}

	public long getEnchant() {
		return enchant;
	}

	public void setEnchant(long enchant) {
		this.enchant = enchant;
	}

	public long getGem1() {
		return gem1;
	}

	public void setGem1(long gem1) {
		this.gem1 = gem1;
	}

	public long getGem2() {
		return gem2;
	}

	public void setGem2(long gem2) {
		this.gem2 = gem2;
	}

	public long getGem3() {
		return gem3;
	}

	public void setGem3(long gem3) {
		this.gem3 = gem3;
	}

	public long getSuffix() {
		return suffix;
	}

	public void setSuffix(long suffix) {
		this.suffix = suffix;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public Upgrade getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(Upgrade upgrade) {
		this.upgrade = upgrade;
	}

	public long getGem0() {
		return gem0;
	}

	public void setGem0(long gem0) {
		this.gem0 = gem0;
	}

	public boolean isExtraSocket() {
		return extraSocket;
	}

	public void setExtraSocket(boolean extraSocket) {
		this.extraSocket = extraSocket;
	}

	public long getReforge() {
		return reforge;
	}

	public void setReforge(long reforge) {
		this.reforge = reforge;
	}

	public long getTinker() {
		return tinker;
	}

	public void setTinker(long tinker) {
		this.tinker = tinker;
	}
}
