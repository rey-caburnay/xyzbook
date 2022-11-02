package com.montani.exam.converter;

import org.junit.Assert;
import org.junit.Test;

public class ISBNConverterTest {

    @Test
    public void testConverterISBN13ToISBN10() {
//        String isbn13 = "978-1-891830-85-3";
        String isbn13 = "978-1-60309-038-4";
        String isbn10 = ISBNConverter.toISBN10(isbn13);
///       System.out.println(isbn10);
        Assert.assertEquals("16030903810", isbn10);
        Assert.assertEquals("1-603-09038-X", ISBNConverter.formatISBN10(isbn10));
//        System.out.println(ISBNConverter.formatISBN10(isbn10));
    }

    @Test
    public void testConverterISBN10TOISBN13() {
        String isbn10 = "1-603-09038-X";

        String isbn13 = ISBNConverter.toISBN13(isbn10);
        System.out.println(isbn13);

        String isbn13Apache = ISBNConverter.convertISBN10(isbn10);
        System.out.println(isbn13Apache);

        Assert.assertEquals("9781603090384", isbn13);
        Assert.assertEquals("978-1-60309-038-4", ISBNConverter.formatISBN13(isbn13));

        Assert.assertEquals("9781603090384", isbn13Apache);
        Assert.assertEquals("978-1-60309-038-4", ISBNConverter.formatISBN13(isbn13Apache));


    }
}
