package com.aerohitsaxena.fluentQuery.utils;

import com.aerohitsaxena.fluentQuery.criterions.Criterion;

public class CriterionValidator {
	public static void validate(Criterion c) {
		Preconditions.check(c != null);
		StringUtils.validateNotEmpty(c.toSql());
	}
}
