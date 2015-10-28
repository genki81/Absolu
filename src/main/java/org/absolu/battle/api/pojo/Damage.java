package org.absolu.battle.api.pojo;

import java.io.Serializable;

public class Damage implements Serializable {
	private static final long serialVersionUID = -5061135198516056741L;
	private long min;
	private long max;
	private float exactMin;
	private float exactMax;

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	public float getExactMin() {
		return exactMin;
	}

	public void setExactMin(float exactMin) {
		this.exactMin = exactMin;
	}

	public float getExactMax() {
		return exactMax;
	}

	public void setExactMax(float exactMax) {
		this.exactMax = exactMax;
	}
}
