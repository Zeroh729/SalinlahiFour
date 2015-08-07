package com.ube.salinlahifour.enumTypes;

public enum StarType {
	NONE(0), BRONZE(1), SILVER(2), GOLD(3);
	private final int value;

	StarType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static StarType getStar(String s) {
		if(s != null) {
			if(s.equals(StarType.BRONZE.toString())) {
				return StarType.BRONZE;
			} else if(s.equals(StarType.SILVER.toString())) {
				return StarType.SILVER;
			} else if(s.equals(StarType.GOLD.toString())) {
				return StarType.GOLD;
			} else {
				return StarType.NONE;
			}
		} else {
			return StarType.NONE;
		}
	}
}
