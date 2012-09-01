package com.aerohitsaxena.fluentQuery;

import com.aerohitsaxena.fluentQuery.criterions.ComparisonExpression;
import com.aerohitsaxena.fluentQuery.criterions.Criterion;
import com.aerohitsaxena.fluentQuery.criterions.LogicalExpression;
import com.aerohitsaxena.fluentQuery.operators.BinaryComparators;
import com.aerohitsaxena.fluentQuery.operators.LogicalOperators;
import com.aerohitsaxena.fluentQuery.utils.StringUtils;

public class Restriction {
	public static ComparisonExpression eq(String key, String value) {
		return new ComparisonExpression(key, value, BinaryComparators.EQ);
	}

	public static ComparisonExpression neq(String key, String value) {
		return new ComparisonExpression(key, value, BinaryComparators.NEQ);
	}

	public static ComparisonExpression gt(String key, String value) {
		return new ComparisonExpression(key, value, BinaryComparators.GT);
	}

	public static ComparisonExpression gte(String key, String value) {
		return new ComparisonExpression(key, value, BinaryComparators.GTE);
	}

	public static ComparisonExpression lt(String key, String value) {
		return new ComparisonExpression(key, value, BinaryComparators.LT);
	}

	public static ComparisonExpression lte(String key, String value) {
		return new ComparisonExpression(key, value, BinaryComparators.LTE);
	}

	public static LogicalExpression and(Criterion c1, Criterion c2) {
		return new LogicalExpression(c1, c2, LogicalOperators.AND);
	}

	public static LogicalExpression or(Criterion c1, Criterion c2) {
		return new LogicalExpression(c1, c2, LogicalOperators.OR);
	}

	public static Criterion not(Criterion criterion) {
		return fromSql(spaceJoin("not", criterion.toSql()));
	}

	public static Criterion like(final String key, final String value) {
		return fromSql(spaceJoin(key, "like", quote(value)));
	}

	public static Criterion between(final String key, final String low,
			final String high) {
		return fromSql(spaceJoin(key, "between", quote(low), "and", quote(high)));
	}

	public static Criterion fromSql(final String sql) {
		return new Criterion() {
			@Override
			public String toSql() {
				return sql;
			}
		};
	}

	private static String quote(String value) {
		return StringUtils.quote(value);
	}

	private static String spaceJoin(String... strings) {
		return StringUtils.spaceJoin(strings);
	}
}
