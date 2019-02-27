package com.udacity.sandwichclub.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.udacity.sandwichclub.utils.StringUtils.listOfStringsToString;
import static org.junit.Assert.*;

public class StringUtilsTest {

    private List<String> fakeListOfStrings;

    @Before
    public void setUp() {
        fakeListOfStrings = new ArrayList<>(5);
        fakeListOfStrings.add("Test1");
        fakeListOfStrings.add("Test2");
        fakeListOfStrings.add("Test3");
        fakeListOfStrings.add("Test4");
        fakeListOfStrings.add("Test5");
    }

    @Test
    public void test_listOfStringsToString_withValidList_returnStringWithAllElementsLineSeparated() {
        int countOfLineBreaks = 4;
        int quantityOfCharsAllElements = fakeListOfStrings
                .toString()
                .replaceAll("\\W", "")
                .length();
        int expectedSize = quantityOfCharsAllElements + countOfLineBreaks;

        String elements = listOfStringsToString(fakeListOfStrings);
        Assert.assertNotNull(elements);
        Assert.assertEquals(expectedSize, elements.length());
    }
}