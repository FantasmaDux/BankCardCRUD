package com.example.bankcards.util;

import java.time.LocalDate;
import java.util.Random;

public class CardUtils {

    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int CARD_CVV_LENGTH = 3;
    private static final int CARD_EXPIRE_YEARS = 5;

    private CardUtils() {
    }

    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < CARD_NUMBER_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }

    public static String generateCardCvv() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < CARD_CVV_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static LocalDate generateCardExpireDate() {
        return LocalDate.now().plusYears(CARD_EXPIRE_YEARS);
    }
}
