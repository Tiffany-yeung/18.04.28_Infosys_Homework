package com.qa.util;

public class TextUtil {

	//business methods
    public String sanitize(String textToSanitize) {
        return textToSanitize.replaceAll("\\s+", "");
    }
}
