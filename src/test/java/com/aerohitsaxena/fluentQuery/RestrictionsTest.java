package com.aerohitsaxena.fluentQuery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.aerohitsaxena.fluentQuery.criterions.ComparisonExpression;
import com.aerohitsaxena.fluentQuery.criterions.Criterion;
import com.aerohitsaxena.fluentQuery.criterions.LogicalExpression;

public class RestrictionsTest {
	@Test
	public void sqlShouldFormCorrectCriterion() {
		Criterion c = Restriction.fromSql("some sql expression");
		assertEquals("some sql expression", c.toSql());
	}

	@Test
	public void eqShouldFormCorrectExpression() {
		ComparisonExpression eq = Restriction.eq("key", "value");
		assertEquals("key = 'value'", eq.toSql());
	}

	@Test
	public void neqShouldFormCorrectExpression() {
		ComparisonExpression neq = Restriction.neq("key", "value");
		assertEquals("key != 'value'", neq.toSql());
	}

	@Test
	public void gtShouldFormCorrectExpression() {
		ComparisonExpression gt = Restriction.gt("key", "value");
		assertEquals("key > 'value'", gt.toSql());
	}

	@Test
	public void gteShouldFormCorrectExpression() {
		ComparisonExpression gte = Restriction.gte("key", "value");
		assertEquals("key >= 'value'", gte.toSql());
	}

	@Test
	public void ltShouldFormCorrectExpression() {
		ComparisonExpression lt = Restriction.lt("key", "value");
		assertEquals("key < 'value'", lt.toSql());
	}

	@Test
	public void lteShouldFormCorrectExpression() {
		ComparisonExpression lte = Restriction.lte("key", "value");
		assertEquals("key <= 'value'", lte.toSql());
	}

	@Test
	public void andShouldCreateCorrectExpression() {
		Criterion c1 = createCriterion("exp1");
		Criterion c2 = createCriterion("exp2");
		LogicalExpression and = Restriction.and(c1, c2);
		assertEquals("exp1 and exp2", and.toSql());
	}

	@Test
	public void orShouldCreateCorrectExpression() {
		Criterion c1 = createCriterion("exp1");
		Criterion c2 = createCriterion("exp2");
		LogicalExpression or = Restriction.or(c1, c2);
		assertEquals("exp1 or exp2", or.toSql());
	}

	@Test
	public void notShouldCreateCorrectExpression() {
		Criterion c = Restriction.not(createCriterion("exp"));
		assertEquals("not exp", c.toSql());
	}

	@Test
	public void likeShouldCreateCorrectExpression() {
		Criterion c = Restriction.like("key", "value%");
		assertEquals("key like 'value%'", c.toSql());
	}

	@Test
	public void betweenShouldCreateCorrectExpression() {
		Criterion c = Restriction.between("key", "lowValue", "highValue");
		assertEquals("key between 'lowValue' and 'highValue'", c.toSql());
	}

	private Criterion createCriterion(final String sql) {
		return Restriction.fromSql(sql);
	}
}
