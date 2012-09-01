package com.aerohitsaxena.fluentQuery.utils;

import com.aerohitsaxena.fluentQuery.exception.SDbFluentQueryException;

public class Preconditions {
	public static void check(boolean condition) {
		if (!condition) {
			throw new SDbFluentQueryException();
		}
	}
}
