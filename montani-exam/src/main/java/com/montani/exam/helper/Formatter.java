package com.montani.exam.helper;

import com.montani.exam.model.Author;
import org.springframework.util.Assert;

public class Formatter {

    public static String formatAuthorName(Author author) {
        Assert.notNull(author, "Author should not be null");
        String fullName = author.getFirstname();
        if(author.getMiddlename() != null && !author.getMiddlename().isEmpty()) {
            //check if it's an initial and add dot.
            if(author.getMiddlename().length() < 2) {
                fullName += author.getMiddlename() + ".";
            } else {
                fullName += " " + author.getMiddlename();
            }

        }
        fullName += " " + author.getLastname();
        return fullName;
    }
}
