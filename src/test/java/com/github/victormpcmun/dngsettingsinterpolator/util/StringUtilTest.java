package com.github.victormpcmun.dngsettingsinterpolator.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void inBetweenExcludingTest() {
        Assert.assertEquals("is",  StringUtil.inBetweenExcluding("this is a test", "this ", " a test"));
        Assert.assertEquals("is",  StringUtil.inBetweenExcluding("this is a test this is a test", "this ", " a test"));
    }

    @Test
    public void inBetweenIncludingTest() {
        Assert.assertEquals("this is a test",  StringUtil.inBetweenIncluding("HELLO this is a test ggg", "this ", " a test"));
        Assert.assertEquals("this is a test",  StringUtil.inBetweenIncluding("this is a test this is a test", "this ", " a test"));
    }

    @Test
    public void replaceInBetweenTest() {
        Assert.assertEquals("var=\"AAAAAAA\"",  StringUtil.replaceInBetween("var=\"test\"", "\"", "\"", "AAAAAAA"));
    }

    @Test
    public void getRepeatedStringTest() {
        Assert.assertEquals("REPEATEDREPEATEDREPEATEDREPEATED",  StringUtil.getRepeatedString("REPEATED", 4));
    }

}