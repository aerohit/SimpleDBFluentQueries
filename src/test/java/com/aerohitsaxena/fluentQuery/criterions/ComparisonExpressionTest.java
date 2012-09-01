package com.aerohitsaxena.fluentQuery.criterions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.aerohitsaxena.fluentQuery.criterions.ComparisonExpression;
import com.aerohitsaxena.fluentQuery.exception.SDbFluentQueryException;
import com.aerohitsaxena.fluentQuery.operators.BinaryComparators;

public class ComparisonExpressionTest {
	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptNullAttribute() {
		new ComparisonExpression(null, "val", BinaryComparators.EQ);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptEmptyAttribute() {
		new ComparisonExpression("  ", "val", BinaryComparators.EQ);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptNullval() {
		new ComparisonExpression("attr", null, BinaryComparators.EQ);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptEmptyval() {
		new ComparisonExpression("attr", "  ", BinaryComparators.EQ);
	}

	@Test(expected = SDbFluentQueryException.class)
	public void shouldNotAcceptNullComparator() {
		new ComparisonExpression("attr", "val", null);
	}

	@Test
	public void shouldFormValidExpressions() {
		BinaryComparators eq = BinaryComparators.EQ;
		ComparisonExpression c = new ComparisonExpression("attr", "val", eq);
		assertEquals("attr = 'val'", c.toSql());
	}
}
