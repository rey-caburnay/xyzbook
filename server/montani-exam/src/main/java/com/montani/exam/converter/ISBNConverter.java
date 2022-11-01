package com.montani.exam.converter;

import org.apache.commons.validator.routines.ISBNValidator;
import org.apache.commons.validator.routines.checkdigit.CheckDigitException;

public class ISBNConverter {

    /**
     * convert isbn 13 to isbn 10
     *
     * @param isbn13
     * @return
     */
    public static String toISBN10(String isbn13) {
        {
            //remove hypen if there's any
            isbn13 = isbn13.replace("-", "");

            int index, value, total = 0;
            int count = 10;
            String st = isbn13.trim().substring(3, 12);
            for (index = 0; index < st.length(); index++) {
                value = Integer.parseInt(st.charAt(index) + "");
                total = total + value * count;
                count--;
            }
            total = (11 - (total % 11)) % 11;
//            //replace check digit of 10 to X
//            if(total ==  10 ) {
//                return st+"X";
//            }
            return st + "" + total;
        }
    }

    /**
     * convert isbn 10 to isbn 13
     * @param isbn10
     * @return
     */
    public static String toISBN13( String isbn10 ) {
        final String CheckDigits = new String("0123456789X0");
        if (isbn10 == null) {
            return null;
        }

        String input = isbn10.replace("-","").trim();
        if (input.length() != 10) {
            throw new IllegalArgumentException("Invalid length " + input.length() + " for '" + input + "'");
        }

        String s12;
        int i, n, v;
        boolean hasError = false;
        s12 = "978" + input.substring(0, 9);
        n = 0;
        for (i=0; i<12; i++) {
            if (!hasError) {
                v = Character.getNumericValue(s12.charAt(i));
                if (v==-1) hasError = true;
                else {
                    if ((i % 2)==0) n = n + v;
                    else n = n + 3*v;
                }
            }
        }
        if (hasError) return "ERROR";
        else {
            n = n % 10;
            if (n!=0) n = 10 - n;
            return s12 + CheckDigits.substring(n, n+1);
        }
    }



    /**
     * convert isbn 10 to 13
     * using apache commons
     * @param isbn10
     * @return
     */
    public static String convertISBN10(String isbn10) {
        isbn10 = isbn10.replace("-","");
        return ISBNValidator.getInstance().convertToISBN13(isbn10);

    }

    /**
     * convert isbn 13 to isbn 10
     * using apache commons
     * @param isbn13
     * @return
     */
    public static String convertISBN13(String isbn13) {
//        isbn13 = isbn13.replace("-","");
        return toISBN10(isbn13);
    }

    /**
     * format the isbn into x-xxx-xxxxx-x
     *
     * @param isbn
     * @return
     */
    public static String formatISBN10(String isbn) {
        //get checkdigit
        if(isbn.length() == 11) {
            return String.format("%s-%s-%s-%s", isbn.charAt(0), isbn.substring(1, 4), isbn.substring(4, 9), "X");
        } else {
            return String.format("%s-%s-%s-%s", isbn.charAt(0), isbn.substring(1, 4), isbn.substring(4, 9), isbn.charAt(9));
        }

    }

    /**
     * @param isbn
     * @return
     */
    public static String formatISBN13(String isbn) {
        String formatted = String.format("%s-%s-%s-%s-%s", isbn.substring(0, 3), isbn.charAt(3), isbn.substring(4, 9),
                isbn.substring(9, 12), isbn.charAt(12));
        return formatted;
    }
}
