package com.montani.exam.helper;

public class ISBNValidator {

    /**
     * The ISBN number is a legal number when
     * 1*Digit1 + 2*Digit2 + 3*Digit3 + 4*Digit4 + 5*Digit5 + 6*Digit6 + 7*Digit7 + 8*Digit8 + 9*Digit9 + 10*Digit10
     * is divisible by 11
     *
     * @param isbn
     * @return
     */
    public static boolean isISBN10(String isbn) {

        if (isbn == null) {
            return false;
        }         //remove any hyphens
        isbn = isbn.replaceAll("-", "");         //must be a 10 digit ISBN
        if (isbn.length() != 10) {
            return false;
        }
        try {
            int tot = 0;
            for (int i = 0; i < 9; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                tot += ((10 - i) * digit);
            }
            String checksum = Integer.toString((11 - (tot % 11)) % 11);
            if ("10".equals(checksum)) {
                checksum = "X";
            }
            return checksum.equals(isbn.substring(9));
        } catch (NumberFormatException nfe) {
            //to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }

    }


    /**
     * The calculation of an ISBN-13 check digit begins with the first twelve digits of the 13-digit ISBN (thus excluding the check digit itself).
     * Each digit, from left to right, is alternately multiplied by 1 or 3, then those products are summed modulo 10 to give a value ranging from 0 to 9.
     * Subtracted from 10, that leaves a result from 1 to 10. A zero replaces a ten, so, in all cases, a single check digit results.
     * For example, the ISBN-13 check digit of 978-0-306-40615-? is calculated as follows:
     * s = 9×1 + 7×3 + 8×1 + 0×3 + 3×1 + 0×3 + 6×1 + 4×3 + 0×1 + 6×3 + 1×1 + 5×3
     * =   9 +  21 +   8 +   0 +   3 +   0 +   6 +  12 +   0 +  18 +   1 +  15
     * = 93
     * 93 / 10 = 9 remainder 3
     * 10 –  3 = 7
     *
     * @param isbn
     * @return
     */
    public static boolean isISBN13(String isbn) {
        if (isbn == null) {
            return false;
        }         //remove any hyphens
        isbn = isbn.replaceAll("-", "");         //must be a 13 digit ISBN
        if (isbn.length() != 13) {
            return false;
        }
        try {
            int tot = 0;
            for (int i = 0; i < 12; i++) {
                int digit = Integer.parseInt(isbn.substring(i, i + 1));
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            }             //checksum must be 0-9. If calculated as 10 then = 0
            int checksum = 10 - (tot % 10);
            if (checksum == 10) {
                checksum = 0;
            }
            return checksum == Integer.parseInt(isbn.substring(12));
        } catch (NumberFormatException nfe) {
            //to catch invalid ISBNs that have non-numeric characters in them
            return false;
        }

    }

    /**
     * using apache commons-validator
     *
     * @return
     */
    public static boolean isValidISBN10(String code) {
        return org.apache.commons.validator.routines.ISBNValidator.getInstance().isValidISBN10(code);
    }

    /**
     * using apache commons validator
     *
     * @param code
     * @return
     */
    public static boolean isValidISBN13(String code) {
        return org.apache.commons.validator.routines.ISBNValidator.getInstance().isValidISBN13(code);
    }


}
