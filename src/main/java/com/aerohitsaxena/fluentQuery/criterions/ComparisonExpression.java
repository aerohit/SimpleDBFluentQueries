package com.aerohitsaxena.fluentQuery.criterions;

import com.aerohitsaxena.fluentQuery.Preconditions;
import com.aerohitsaxena.fluentQuery.StringUtils;
import com.aerohitsaxena.fluentQuery.operators.BinaryComparators;

public class ComparisonExpression implements Criterion {
	private final String m_attribute;
	private final String m_value;
	private final BinaryComparators m_comparator;

	public ComparisonExpression(String attribute, String value,
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
		return StringUtils.spaceJoin(strings);
	}
}
