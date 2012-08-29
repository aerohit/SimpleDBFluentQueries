package com.aerohitsaxena.fluentQuery;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class SDbFluentQuery {
	private String m_database;
	private List<String> m_fields;
	private StringUtils m_strUtil;

	public SDbFluentQuery() {
		m_strUtil = new StringUtils();
		m_fields = Lists.newArrayList();
	}

	public SDbFluentQuery selectAll() {
		return select("*");
	}

	public SDbFluentQuery select(String attribute, String... moreAttributes) {
		addAttribute(attribute);
		for (int i = 0; i < moreAttributes.length; i++) {
			addAttribute(moreAttributes[i]);
		}
		return this;
	}

	public SDbFluentQuery count() {
		return select("count(*)");
	}

	public SDbFluentQuery from(String database) {
		checkNotEmpty(database);
		m_database = database;
		return this;
	}

	public String build() {
		Preconditions.check(m_fields.size() != 0);
		return selectExpression(commaJoin(m_fields));
	}

	private String selectExpression(String fieldsClause) {
		checkNotEmpty(m_database);
		return "select " + fieldsClause + " from " + m_database;
	}

	private String commaJoin(List<String> fields) {
		return Joiner.on(", ").join(fields);
	}

	private void addAttribute(String attribute) {
		checkNotEmpty(attribute);
		m_fields.add(attribute);
	}

	private void checkNotEmpty(String string) {
		Preconditions.check(m_strUtil.isNotEmpty(string));
	}
}
