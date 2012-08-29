package com.aerohitsaxena.fluentQuery;

public class Preconditions {
	public static void check(boolean condition) {
		if (!condition) {
			throw new SDbFluentQueryException();
		}
	}
}
