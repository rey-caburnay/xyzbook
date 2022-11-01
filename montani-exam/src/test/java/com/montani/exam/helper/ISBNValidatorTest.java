package com.montani.exam.helper;

import org.junit.Assert;
import org.junit.Test;

public class ISBNValidatorTest {

    @Test
    public void testValidateISBN13() {
        String isbn13 = "978-1-891830-85-3";
        Assert.assertTrue(ISBNValidator.isISBN13(isbn13));
        Assert.assertTrue(ISBNValidator.isValidISBN13(isbn13));
    }

    @Test
    public void testValidateISBN10() {
        String isbn = "1-891-83085-6";
        String isbn10 = "1-603-09038-X";
        Assert.assertTrue(ISBNValidator.isISBN10(isbn));
        Assert.assertTrue(ISBNValidator.isValidISBN10(isbn));
        Assert.assertTrue(ISBNValidator.isISBN10(isbn10));
    }
}
