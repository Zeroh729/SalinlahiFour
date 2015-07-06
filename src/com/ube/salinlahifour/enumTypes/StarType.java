package com.ube.salinlahifour.enumTypes;

public enum StarType {
	BRONZE (1),
	SILVER (2),
	GOLD (3);
	private final int value;
	
	StarType(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public static StarType getStar(String s) {
		if(s.equals(StarType.BRONZE.toString())) {
			return StarType.BRONZE;
		} else if (s.equals(StarType.SILVER.toString())) {
			return StarType.SILVER;
		}else {
			return StarType.GOLD;
		}
	}
}
