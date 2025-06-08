package com.solid.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import com.solid.enums.EErrorCode;
import com.solid.exceptions.HelperException;

/**
 * Utility class for generating unique business identifiers.
 * The identifier format is based on the current date and three random 2-digit
 * numbers.
 * Format example: 250606-246709
 *
 * Author: Samano CASTRE
 * Date: 2025-06-08
 */
public final class IdentifierHelper {
    private static final int RANDOM_2_DIGIT_LIMIT = 100;
    private static final String DATE_FORMAT = "yyMMdd"; // 250606
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private static final String BUSINESS_IDENTIFIER_FORMAT = "%s-%02d%02d%02d"; // 250606-246709

    private IdentifierHelper() {
    }

    private static int generateRandomInt(int limit) {
        return ThreadLocalRandom.current().nextInt(limit);
    }

    /**
     * Generates a unique business identifier using the current date and three
     * 2-digit random numbers.
     *
     * @return A formatted identifier string (e.g., "250606-123456")
     * @throws HelperException if the identifier generation fails.
     */
    public static String create() {
        try {
            String todayDate = DATE_TIME_FORMATTER.format(LocalDate.now());

            int randomNumber1 = generateRandomInt(RANDOM_2_DIGIT_LIMIT);
            int randomNumber2 = generateRandomInt(RANDOM_2_DIGIT_LIMIT);
            int randomNumber3 = generateRandomInt(RANDOM_2_DIGIT_LIMIT);

            return String.format(BUSINESS_IDENTIFIER_FORMAT,
                    todayDate,
                    randomNumber1,
                    randomNumber2,
                    randomNumber3);

        } catch (RuntimeException exception) {
            throw new HelperException(EErrorCode.INTERNAL_ERROR,
                    "Failed to create business identifier",
                    exception);
        }
    }
}
