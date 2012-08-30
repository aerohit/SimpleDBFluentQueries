package com.aerohitsaxena.fluentQuery;

public class Restriction {
	public static Criterion and(final Criterion c1, final Criterion c2) {
		CriterionValidator.validate(c1);
		CriterionValidator.validate(c2);
		return criterion(spaceJoin(c1.toSql(), "and", c2.toSql()));
	}

	public static Criterion criterion(final String string) {
		StringUtils.validateNotEmpty(string);
		return new Criterion() {
			@Override
			public String toSql() {
				return string;
			}
		};
	}

	public static Criterion or(Criterion c1, Criterion c2) {
		CriterionValidator.validate(c1);
		CriterionValidator.validate(c2);
		return criterion(spaceJoin(c1.toSql(), "or", c2.toSql()));
	}

	private static String spaceJoin(String... strings) {
		return StringUtils.spaceJoin(strings);
	}
}
