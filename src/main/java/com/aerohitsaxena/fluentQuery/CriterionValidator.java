package com.aerohitsaxena.fluentQuery;

public class CriterionValidator {
	public static void validate(Criterion c) {
		Preconditions.check(c != null);
		StringUtils.validateNotEmpty(c.toSql());
	}
}
