package com.aerohitsaxena.fluentQuery.criterions;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.aerohitsaxena.fluentQuery.SDbFluentQueryException;
import com.aerohitsaxena.fluentQuery.operators.LogicalOperators;

public class LogicalExpressionTest {
	Criterion nullCriterion;
	Criterion emptyCriterion;

	@Before
	public void setUp() {
		nullCriterion = createCriterion(null);
		emptyCriterion = createCriterion("   ");
	}

	private Criterion createCriterion(final String string) {
		return new Criterion() {
			@Override
			public String toSql() {
				return string;
			}
		};
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptNullCriterion() {
		new LogicalExpression(null, null, LogicalOperators.AND);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptCriterionWithNullQuery() {
		new LogicalExpression(nullCriterion, nullCriterion,
				LogicalOperators.AND);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptCriterionWithEmptyQuery() {
		new LogicalExpression(emptyCriterion, emptyCriterion,
				LogicalOperators.AND);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptNullOperator() {
		Criterion c1 = createCriterion("criterion1");
		Criterion c2 = createCriterion("criterion2");
		new LogicalExpression(c1, c2, null);
	}

	@Test
	public void shouldAndCriterion() {
		Criterion c1 = createCriterion("criterion1");
		Criterion c2 = createCriterion("criterion2");
		LogicalExpression le = new LogicalExpression(c1, c2,
				LogicalOperators.AND);
		assertEquals("criterion1 and criterion2", le.toSql());
	}

	@Test
	public void shouldOrCriterion() {
		Criterion c1 = createCriterion("criterion1");
		Criterion c2 = createCriterion("criterion2");
		LogicalExpression le = new LogicalExpression(c1, c2,
				LogicalOperators.OR);
		assertEquals("criterion1 or criterion2", le.toSql());
	}
}
