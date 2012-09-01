package com.aerohitsaxena.fluentQuery.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StringUtilsTest {
	@Test
	public void testIsEmptyWithNull() {
		assertTrue("Result", StringUtils.isEmpty(null));
	}

	@Test
	public void testIsEmptyWithEmptyString() {
		assertTrue("Result", StringUtils.isEmpty(""));
	}

	@Test
	public void testIsEmptyWithWhiteSpaces() {
		assertTrue("Result", StringUtils.isEmpty("   "));
	}

	@Test
	public void testIsEmptyWithNonEmptyString() {
		assertFalse("Result", StringUtils.isEmpty(" xyz  "));
	}

	@Test
	public void testIsNotEmptyWithNull() {
		assertFalse("Result", StringUtils.isNotEmpty(null));
	}

	@Test
	public void testIsNotEmptyWithEmptyString() {
		assertFalse("Result", StringUtils.isNotEmpty(""));
	}

	@Test
	public void testIsNotEmptyWithWhiteSpaces() {
		assertFalse("Result", StringUtils.isNotEmpty("   "));
	}

	@Test
	public void testIsNotEmptyWithNonEmptyString() {
		assertTrue("Result", StringUtils.isNotEmpty(" xyz  "));
	}
}
