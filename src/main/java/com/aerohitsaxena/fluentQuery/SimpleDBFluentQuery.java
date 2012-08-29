package com.aerohitsaxena.fluentQuery;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class SimpleDBFluentQuery {
	private String m_domainName;
	private List<String> m_selectAttributes;
	private StringUtils m_strUtil;
	private String m_orderByClause;
	private String m_limitClause;

	public SimpleDBFluentQuery() {
		m_strUtil = new StringUtils();
		m_selectAttributes = Lists.newArrayList();
	}

	public SimpleDBFluentQuery selectAll() {
		return select("*");
	}

	public SimpleDBFluentQuery select(String attribute,
			String... moreAttributes) {
		addAttribute(attribute);
		for (int i = 0; i < moreAttributes.length; i++) {
			addAttribute(moreAttributes[i]);
		}
		return this;
	}

	public SimpleDBFluentQuery count() {
		return select("count(*)");
	}

	public SimpleDBFluentQuery from(String database) {
		checkNotEmpty(database);
		m_domainName = database;
		return this;
	}

	public SimpleDBFluentQuery orderBy(String attribute) {
		checkNotEmpty(attribute);
		setOrderByClause(attribute);
		return this;
	}

	public SimpleDBFluentQuery orderByAscending(String attribute) {
		checkNotEmpty(attribute);
		setOrderByClause(attribute + " asc");
		return this;
	}

	public SimpleDBFluentQuery orderByDescending(String attribute) {
		checkNotEmpty(attribute);
		setOrderByClause(attribute + " desc");
		return this;
	}

	public SimpleDBFluentQuery limit(int limit) {
		Preconditions.check(limit > 0);
		m_limitClause = " limit " + limit;
		return this;
	}

	public String build() {
		Preconditions.check(m_selectAttributes.size() != 0);
		return selectExpression(commaJoin(m_selectAttributes));
	}

	private void setOrderByClause(String attribute) {
		m_orderByClause = " order by " + attribute;
	}

	private String selectExpression(String fieldsClause) {
		checkNotEmpty(m_domainName);
		String query = "select " + fieldsClause + " from " + m_domainName;
		StringBuilder sb = new StringBuilder(query);
		addClauseIfNotEmpty(sb, m_orderByClause);
		addClauseIfNotEmpty(sb, m_limitClause);
		return sb.toString();
	}

	private void addClauseIfNotEmpty(StringBuilder bld, String clause) {
		if (m_strUtil.isNotEmpty(clause)) {
			bld.append(clause);
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
