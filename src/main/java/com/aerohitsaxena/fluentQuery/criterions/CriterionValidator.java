package com.aerohitsaxena.fluentQuery.criterions;

import com.aerohitsaxena.fluentQuery.Preconditions;
import com.aerohitsaxena.fluentQuery.StringUtils;

public class CriterionValidator {
	public static void validate(Criterion c) {
		Preconditions.check(c != null);
		StringUtils.validateNotEmpty(c.toSql());
	}
}
