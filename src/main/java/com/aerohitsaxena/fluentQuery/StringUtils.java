package com.aerohitsaxena.fluentQuery;

import com.google.common.base.Joiner;

public class StringUtils {
	public static boolean isEmpty(String string) {
		return string == null || string.trim().equals("");
	}

	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}

	public static void validateNotEmpty(String string) {
		Preconditions.check(isNotEmpty(string));
	}

	public static String spaceJoin(String... strings) {
		return Joiner.on(" ").join(strings);
	}
}
