package com.aerohitsaxena.fluentQuery;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class StringUtilsTest {
	StringUtils util;

	@Before
	public void setUp() {
		util = new StringUtils();
	}

	@Test
	public void testIsEmptyWithNull() {
		assertTrue("Result", util.isEmpty(null));
	}

	@Test
	public void testIsEmptyWithEmptyString() {
		assertTrue("Result", util.isEmpty(""));
	}

	@Test
	public void testIsEmptyWithWhiteSpaces() {
		assertTrue("Result", util.isEmpty("   "));
	}

	@Test
	public void testIsEmptyWithNonEmptyString() {
		assertFalse("Result", util.isEmpty(" xyz  "));
	}

	@Test
	public void testIsNotEmptyWithNull() {
		assertFalse("Result", util.isNotEmpty(null));
	}

	@Test
	public void testIsNotEmptyWithEmptyString() {
		assertFalse("Result", util.isNotEmpty(""));
	}

	@Test
	public void testIsNotEmptyWithWhiteSpaces() {
		assertFalse("Result", util.isNotEmpty("   "));
	}

	@Test
	public void testIsNotEmptyWithNonEmptyString() {
		assertTrue("Result", util.isNotEmpty(" xyz  "));
	}
}
