package com.aerohitsaxena.fluentQuery;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class SDbFluentQueryTest {
	private SDbFluentQuery query;

	@Before
	public void setUp() {
		query = new SDbFluentQuery();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectShouldThrowExceptionWithoutDatabaseName() {
		query.build();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectShouldThrowExceptionWithNullDatabaseName() {
		query.from(null).build();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectShouldThrowExceptionWithEmptyDatabaseName() {
		query.from("   ").build();
	}

	@Test
	public void testSelectAll() {
		query.selectAll().from("DomainName");
		assertEquals("select * from DomainName", query.build());
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectWithNullAttribute() {
		query.select(null).from("DomainName").build();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectWithEmptyAttribute() {
		query.select("   ").from("DomainName").build();
	}

	@Test
	public void testSelectWithOneAttribute() {
		query.select("attribute1").from("DomainName");
		assertEquals("select attribute1 from DomainName", query.build());
	}

	@Test
	public void testSelectWithTwoAttributes() {
		query.select("attribute1").select("attribute2").from("DomainName");
		String expected = "select attribute1, attribute2 from DomainName";
		assertEquals(expected, query.build());
	}

	@Test
	public void testSelectWithVariableAttributes() {
		query.select("attribute1", "attribute2", "attribute3");
		query.from("DomainName");
		String expected = "select attribute1, attribute2, attribute3 from DomainName";
		assertEquals(expected, query.build());
	}

	@Test
	public void testCountResults() {
		query.count().from("DomainName");
		assertEquals("select count(*) from DomainName", query.build());
	}
}
