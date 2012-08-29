package com.aerohitsaxena.fluentQuery;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class SDbFluentQuery {
	private String m_domainName;
	private List<String> m_selectAttributes;
	private StringUtils m_strUtil;
	private String m_orderByClause;

	public SDbFluentQuery() {
		m_strUtil = new StringUtils();
		m_selectAttributes = Lists.newArrayList();
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
		m_domainName = database;
		return this;
	}

	public String build() {
		Preconditions.check(m_selectAttributes.size() != 0);
		return selectExpression(commaJoin(m_selectAttributes));
	}

	public SDbFluentQuery orderBy(String attribute) {
		checkNotEmpty(attribute);
		m_orderByClause = attribute;
		return this;
	}

	public SDbFluentQuery orderByAscending(String attribute) {
		checkNotEmpty(attribute);
		m_orderByClause = attribute + " asc";
		return this;
	}

	public SDbFluentQuery orderByDescending(String attribute) {
		checkNotEmpty(attribute);
		m_orderByClause = attribute + " desc";
		return this;
	}

	private String selectExpression(String fieldsClause) {
		checkNotEmpty(m_domainName);
		String expression = "select " + fieldsClause + " from " + m_domainName;
		if (m_orderByClause == null) {
			return expression;
		} else {
			return expression + " order by " + m_orderByClause;
		}
	}

	private String commaJoin(List<String> fields) {
		return Joiner.on(", ").join(fields);
	}

	private void addAttribute(String attribute) {
		checkNotEmpty(attribute);
		m_selectAttributes.add(attribute);
	}

	private void checkNotEmpty(String string) {
		Preconditions.check(m_strUtil.isNotEmpty(string));
	}
}
