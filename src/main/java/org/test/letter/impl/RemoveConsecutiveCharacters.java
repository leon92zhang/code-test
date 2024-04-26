package org.test.letter.impl;

import org.test.letter.ILetterConvertor;

public class RemoveConsecutiveCharacters implements ILetterConvertor {

    @Override
    public String convert(String input) {
        if (input == null) {
            return null;
        }
        boolean match = input.chars()
                .allMatch(c -> {
                    if (c >= 97 && c <= 122) {
                        return true;
                    }
                    if (c >= 65 && c <= 90) {
                        return true;
                    }
                    return false;
                });
        if (!match) {
            throw new IllegalArgumentException("The input string can only contain lowercase and uppercase letters.");
        }
        StringBuilder builder = new StringBuilder(input);
        boolean found = true;

        while (found) {
            found = false;
            int start = 0;
            int end = 0;

            while (end < builder.length()) {
                char current = builder.charAt(end);
                int count = 0;
                while (end < builder.length() && builder.charAt(end) == current) {
                    end++;
                    count++;
                }
                if (count >= 3) {
                    builder.delete(start, end);
                    found = true;
                    break;
                }
                start = end;
            }
        }

        return builder.toString();
    }
}
