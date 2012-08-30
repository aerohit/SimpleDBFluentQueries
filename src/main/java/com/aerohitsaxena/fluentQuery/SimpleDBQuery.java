package com.aerohitsaxena.fluentQuery;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

public class SimpleDBQuery {
	private String m_domainName;
	private List<String> m_selectAttributes;
	private String m_orderByClause;
	private String m_limitClause;
	private final Joiner m_commaJoiner;
	private final Joiner m_spaceJoiner;

	public SimpleDBQuery() {
		m_commaJoiner = Joiner.on(", ");
		m_spaceJoiner = Joiner.on(" ");
		m_selectAttributes = Lists.newArrayList();
	}

	public SimpleDBQuery selectAll() {
		return select("*");
	}

	public SimpleDBQuery select(String attribute, String... moreAttributes) {
		addAttribute(attribute);
		for (int i = 0; i < moreAttributes.length; i++) {
			addAttribute(moreAttributes[i]);
		}
		return this;
	}

	public SimpleDBQuery count() {
		return select("count(*)");
	}

	public SimpleDBQuery from(String domainName) {
		checkNotEmpty(domainName);
		m_domainName = domainName;
		return this;
	}

	public SimpleDBQuery orderBy(String attribute) {
		setOrderByClause(attribute, null);
		return this;
	}

	public SimpleDBQuery orderByAscending(String attribute) {
		setOrderByClause(attribute, "asc");
		return this;
	}

	public SimpleDBQuery orderByDescending(String attribute) {
		setOrderByClause(attribute, "desc");
		return this;
	}

	public SimpleDBQuery limit(int limit) {
		Preconditions.check(limit > 0);
		m_limitClause = spaceJoin("limit", Integer.toString(limit));
		return this;
	}

	public String toSqlString() {
		Preconditions.check(m_selectAttributes.size() != 0);
		return selectExpression(commaJoin(m_selectAttributes));
	}

	private void setOrderByClause(String attribute, String order) {
		checkNotEmpty(attribute);
		m_orderByClause = spaceJoin("order by", attribute);
		if (order != null) {
			m_orderByClause = spaceJoin(m_orderByClause, order);
		}
	}

	private String selectExpression(String fieldsClause) {
		checkNotEmpty(m_domainName);
		String query = spaceJoin("select", fieldsClause, "from", m_domainName);
		StringBuilder sb = new StringBuilder(query);
		addClauseIfNotEmpty(sb, m_orderByClause);
		addClauseIfNotEmpty(sb, m_limitClause);
		return sb.toString();
	}

	private void addClauseIfNotEmpty(StringBuilder bld, String clause) {
		if (StringUtils.isNotEmpty(clause)) {
			bld.append(" " + clause);
		}
	}

	private String commaJoin(List<String> fields) {
		return m_commaJoiner.join(fields);
	}

	private String spaceJoin(String... strings) {
		return m_spaceJoiner.join(strings);
	}

	private void addAttribute(String attribute) {
		checkNotEmpty(attribute);
		m_selectAttributes.add(attribute);
	}

	private void checkNotEmpty(String string) {
		StringUtils.validateNotEmpty(string);
	}
}
