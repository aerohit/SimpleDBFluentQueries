package com.aerohitsaxena.fluentQuery;

public class StringUtils {
	public boolean isEmpty(String string) {
		return string == null || string.trim().equals("");
	}

	public boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}
}
