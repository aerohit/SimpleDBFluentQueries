package com.aerohitsaxena.fluentQuery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinaryComparisonCriterionTest {
	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptNullAttribute() {
		new BinaryComparisonCriterion(null, "val", BinaryComparators.EQ);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptEmptyAttribute() {
		new BinaryComparisonCriterion("  ", "val", BinaryComparators.EQ);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptNullval() {
		new BinaryComparisonCriterion("attr", null, BinaryComparators.EQ);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptEmptyval() {
		new BinaryComparisonCriterion("attr", "  ", BinaryComparators.EQ);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptNullComparator() {
		new BinaryComparisonCriterion("attr", "val", null);
	}

	@Test
	public void shouldFormValidExpressions() {
		BinaryComparisonCriterion c = new BinaryComparisonCriterion("attr",
				"val", BinaryComparators.EQ);
		assertEquals("attr = 'val'", c.toSql());
	}
}
