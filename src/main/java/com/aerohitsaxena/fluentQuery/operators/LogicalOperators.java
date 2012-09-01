package com.aerohitsaxena.fluentQuery.operators;

public enum LogicalOperators {
	AND("and"), OR("or");
	private final String m_operand;

	private LogicalOperators(String operand) {
		m_operand = operand;
	}

	public String getOperator() {
		return m_operand;
	}
}
