package com.nashtech.assetmanagement.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringUtil {
    public static String randomPasword() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }
}
