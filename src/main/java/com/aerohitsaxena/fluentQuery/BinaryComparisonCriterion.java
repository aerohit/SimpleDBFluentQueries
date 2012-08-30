package com.aerohitsaxena.fluentQuery;

import com.google.common.base.Joiner;

public class BinaryComparisonCriterion implements Criterion {
	private final String m_attribute;
	private final String m_value;
	private final BinaryComparators m_comparator;

	public BinaryComparisonCriterion(String attribute, String value,
			BinaryComparators comparator) {
		StringUtils.validateNotEmpty(attribute);
		StringUtils.validateNotEmpty(value);
		Preconditions.check(comparator != null);
		m_attribute = attribute;
		m_value = value;
		m_comparator = comparator;
	}

	@Override
	public String toSql() {
		return spaceJoin(m_attribute, m_comparator.getComparator(),
				surrondQuotes(m_value));
	}

	private String surrondQuotes(String value) {
		return "'" + value + "'";
	}

	private String spaceJoin(String... strings) {
		return Joiner.on(" ").join(strings);
	}
}
