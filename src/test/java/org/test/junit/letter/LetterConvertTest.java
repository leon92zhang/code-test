package org.test.junit.letter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.test.letter.ILetterConvertor;
import org.test.letter.impl.RemoveConsecutiveCharacters;
import org.test.letter.impl.ReplaceConsecutiveCharacters;

public class LetterConvertTest {

    private ILetterConvertor removeConsecutiveCharacters;
    private ILetterConvertor replaceConsecutiveCharacters;

    @Before
    public void setUp() {
        this.removeConsecutiveCharacters = new RemoveConsecutiveCharacters();
        this.replaceConsecutiveCharacters = new ReplaceConsecutiveCharacters();
    }

    @Test
    @DisplayName("RemoveConsecutiveCharacters")
    public void testRemoveConsecutiveCharacters() {
        Assert.assertEquals("d", removeConsecutiveCharacters.convert("aabcccbbad"));
        Assert.assertEquals("", removeConsecutiveCharacters.convert("aaa"));
        Assert.assertEquals("", removeConsecutiveCharacters.convert("aaaa"));
        Assert.assertEquals("a", removeConsecutiveCharacters.convert("abbb"));
        Assert.assertEquals("b", removeConsecutiveCharacters.convert("aaab"));
        Assert.assertEquals("ac", removeConsecutiveCharacters.convert("abbbc"));
        Assert.assertEquals("", removeConsecutiveCharacters.convert(""));
        for (int charNumber = 97; charNumber <= 122; charNumber++) {
            Assert.assertEquals("", removeConsecutiveCharacters.convert(new String(new char[]{(char) charNumber, (char) charNumber, (char) charNumber})));
        }
        for (int charNumber = 65; charNumber <= 90; charNumber++) {
            Assert.assertEquals("", removeConsecutiveCharacters.convert(new String(new char[]{(char) charNumber, (char) charNumber, (char) charNumber})));
        }
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            removeConsecutiveCharacters.convert("1");
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            removeConsecutiveCharacters.convert("%");
        });

        Assert.assertNull(removeConsecutiveCharacters.convert(null));
    }

    @Test
    @DisplayName("ReplaceConsecutiveCharacters")
    public void testReplaceConsecutiveCharacters() {
        Assert.assertEquals("`d", replaceConsecutiveCharacters.convert("aabcccbbad"));
        Assert.assertEquals("`", replaceConsecutiveCharacters.convert("aaa"));
        Assert.assertEquals("`", replaceConsecutiveCharacters.convert("aaaa"));
        Assert.assertEquals("aa", replaceConsecutiveCharacters.convert("abbb"));
        Assert.assertEquals("`b", replaceConsecutiveCharacters.convert("aaab"));
        Assert.assertEquals("aac", replaceConsecutiveCharacters.convert("abbbc"));
        Assert.assertEquals("", replaceConsecutiveCharacters.convert(""));
        for (int charNumber = 97; charNumber <= 122; charNumber++) {
            Assert.assertEquals(new String(new char[]{(char) (charNumber - 1)}), replaceConsecutiveCharacters.convert(new String(new char[]{(char) charNumber, (char) charNumber, (char) charNumber}))); // new String(new char[]{'\u0000', '\u0000', '\u0000'})
        }
        for (int charNumber = 65; charNumber <= 90; charNumber++) {
            Assert.assertEquals(new String(new char[]{(char) (charNumber - 1)}), replaceConsecutiveCharacters.convert(new String(new char[]{(char) charNumber, (char) charNumber, (char) charNumber}))); // new String(new char[]{'\u0000', '\u0000', '\u0000'})
        }
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            replaceConsecutiveCharacters.convert("1");
        });
        Assert.assertThrows(IllegalArgumentException.class, () -> {
            replaceConsecutiveCharacters.convert("%");
        });
        Assert.assertNull(replaceConsecutiveCharacters.convert(null));
    }
}
