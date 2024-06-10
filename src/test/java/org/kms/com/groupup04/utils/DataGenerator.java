package org.kms.com.groupup04.utils;

import java.util.Random;

public class DataGenerator {

    public static int generateRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    public static int generateRandomNumber(int length) {
        Random rand = new Random();
        int maxValue = (int) Math.pow(10, length) - 1;
        return rand.nextInt(maxValue);
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }

    public static String generateStringOfCharacters(int length, String charString, Random random) {
        StringBuilder result = new StringBuilder();
        while (length-- > 0) {
            int index = random.nextInt(charString.length());
            result.append(charString.charAt(index));
        }
        return result.toString();
    }
}