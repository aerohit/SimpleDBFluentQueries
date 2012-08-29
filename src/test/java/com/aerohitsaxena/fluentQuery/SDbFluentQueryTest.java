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
	public void testSelectWithNoAttributeSpecified() {
		query.from("MyDatabase");
		assertEquals("select * from MyDatabase", query.build());
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectWithNullAttribute() {
		query.select(null).from("MyDatabase").build();
	}

	@Test(expected = SDbFluentQueryException.class)
	public void testSelectWithEmptyAttribute() {
		query.select("   ").from("MyDatabase").build();
	}

	@Test
	public void testSelectWithOneAttribute() {
		query.select("attribute1").from("MyDatabase");
		assertEquals("select attribute1 from MyDatabase", query.build());
	}

	@Test
	public void testSelectWithTwoAttributes() {
		query.select("attribute1").select("attribute2").from("MyDatabase");
		String expected = "select attribute1, attribute2 from MyDatabase";
		assertEquals(expected, query.build());
	}

	@Test
	public void testSelectWithVariableAttributes() {
		query.select("attribute1", "attribute2", "attribute3");
		query.from("MyDatabase");
		String expected = "select attribute1, attribute2, attribute3 from MyDatabase";
		assertEquals(expected, query.build());
	}
}
