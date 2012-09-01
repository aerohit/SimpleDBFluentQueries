package com.aerohitsaxena.fluentQuery.operators;

public enum BinaryComparators {
	EQ("="), NEQ("!="), GT(">"), GTE(">="), LT("<"), LTE("<=");
	private String m_comparator;

	private BinaryComparators(String comparator) {
		m_comparator = comparator;
	}

	public String getComparator() {
		return m_comparator;
	}
}
