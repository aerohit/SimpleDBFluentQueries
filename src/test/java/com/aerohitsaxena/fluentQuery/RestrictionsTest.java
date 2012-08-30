package com.aerohitsaxena.fluentQuery;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RestrictionsTest {
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
	public void shouldNotCreateCriterionFromNullSql() {
		Restriction.criterion(null);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotCreateCriterionFromEmptySql() {
		Restriction.criterion("  ");
	}

	@Test
	public void shouldCreateCriterionFromSql() {
		assertEquals("criterion", Restriction.criterion("criterion").toSql());
	}

	@Test(expected = SDbFluentQueryException.class)
	public void andShouldNotAcceptNullCriterions() {
		Restriction.and(null, null);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void andShouldNotAcceptCriterionsWithNullQuery() {
		Restriction.and(nullCriterion, nullCriterion);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void andShouldNotAcceptCriterionsWithEmptyQuery() {
		Restriction.and(emptyCriterion, emptyCriterion);
	}

	@Test
	public void shouldAndCriterions() {
		Criterion c1 = Restriction.criterion("criterion1");
		Criterion c2 = Restriction.criterion("criterion2");
		String expected = "criterion1 and criterion2";
		assertEquals(expected, Restriction.and(c1, c2).toSql());
	}

	@Test(expected = SDbFluentQueryException.class)
	public void orShouldNotAcceptNullCriterions() {
		Restriction.or(null, null);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void orShouldNotAcceptCriterionsWithNullQuery() {
		Restriction.or(nullCriterion, nullCriterion);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void orShouldNotAcceptCriterionsWithEmptyQuery() {
		Restriction.or(emptyCriterion, emptyCriterion);
	}

	@Test
	public void shouldOrCriterions() {
		Criterion c1 = Restriction.criterion("criterion1");
		Criterion c2 = Restriction.criterion("criterion2");
		String expected = "criterion1 or criterion2";
		assertEquals(expected, Restriction.or(c1, c2).toSql());
	}
}
