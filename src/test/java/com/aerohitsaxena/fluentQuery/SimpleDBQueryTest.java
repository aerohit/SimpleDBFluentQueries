package com.aerohitsaxena.fluentQuery;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SimpleDBQueryTest {
	private SimpleDBQuery query;

	@Before
	public void setUp() {
		query = new SimpleDBQuery();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectShouldThrowExceptionWithoutDatabaseName() {
		query.selectAll().toSqlString();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectShouldThrowExceptionWithNullDatabaseName() {
		query.selectAll().from(null).toSqlString();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectShouldThrowExceptionWithEmptyDatabaseName() {
		query.selectAll().from("   ").toSqlString();
	}

	@Test
	public void testSelectAll() {
		query.selectAll().from("DomainName");
		assertEquals("select * from DomainName", query.toSqlString());
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectWithNullAttribute() {
		query.select(null).from("DomainName").toSqlString();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectWithEmptyAttribute() {
		query.select("   ").from("DomainName").toSqlString();
	}

	@Test
	public void testSelectWithOneAttribute() {
		query.select("attribute1").from("DomainName");
		assertEquals("select attribute1 from DomainName", query.toSqlString());
	}

	@Test
	public void testSelectWithTwoAttributes() {
		query.select("attribute1").select("attribute2").from("DomainName");
		String expected = "select attribute1, attribute2 from DomainName";
		assertEquals(expected, query.toSqlString());
	}

	@Test
	public void testSelectWithVariableAttributes() {
		query.select("attribute1", "attribute2", "attribute3");
		query.from("DomainName");
		String expected = "select attribute1, attribute2, attribute3 from DomainName";
		assertEquals(expected, query.toSqlString());
	}

	@Test
	public void testCountResults() {
		query.count().from("DomainName");
		assertEquals("select count(*) from DomainName", query.toSqlString());
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testOrderByNullAttribute() {
		query.selectAll().from("DomainName").orderBy(null).toSqlString();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testOrderByEmptyAttribute() {
		query.selectAll().from("DomainName").orderBy("   ").toSqlString();
	}

	@Test
	public void testOrderBy() {
		query.selectAll().from("DomainName").orderBy("attribute1");
		String expected = "select * from DomainName order by attribute1";
		assertEquals(expected, query.toSqlString());
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testAscendingOrderByNullAttribute() {
		query.selectAll().from("DomainName").orderByAscending(null).toSqlString();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testAscendingOrderByEmptyAttribute() {
		query.selectAll().from("DomainName").orderByAscending("   ").toSqlString();
	}

	@Test
	public void testAscendingOrderBy() {
		query.selectAll().from("DomainName").orderByAscending("attribute1");
		String expected = "select * from DomainName order by attribute1 asc";
		assertEquals(expected, query.toSqlString());
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testDescendingOrderByNullAttribute() {
		query.selectAll().from("DomainName").orderByDescending(null).toSqlString();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testDescendingOrderByEmptyAttribute() {
		query.selectAll().from("DomainName").orderByDescending("   ").toSqlString();
	}

	@Test
	public void testDescendingOrderBy() {
		query.selectAll().from("DomainName").orderByDescending("attribute1");
		String expected = "select * from DomainName order by attribute1 desc";
		assertEquals(expected, query.toSqlString());
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testLimitWithNegative() {
		query.selectAll().from("DomainName").limit(-4).toSqlString();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testLimitWithZero() {
		query.selectAll().from("DomainName").limit(0).toSqlString();
	}

	@Test
	public void testLimitWithPositive() {
		query.selectAll().from("DomainName").limit(5);
		assertEquals("select * from DomainName limit 5", query.toSqlString());
	}

	@Test
	public void testAComplicatedQuery() {
		query.select("attribute1", "attribute2").from("MyDomain")
				.orderByDescending("attribute1").limit(50);
		String expected = "select attribute1, attribute2 from MyDomain order by attribute1 desc limit 50";
		assertEquals(expected, query.toSqlString());
	}
}
