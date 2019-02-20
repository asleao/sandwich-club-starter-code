package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsonUtilsTest {

    private String fakeSandwich;

    @Before
    public void setUp() {
        fakeSandwich = "{\"name\":{\"mainName\":\"Ham and cheese\n" +
                "            sandwich\",\"alsoKnownAs\":[]},\"placeOfOrigin\":\"\",\"description\":\"A ham and cheese\n" +
                "            sandwich is a common type of sandwich. It is made by putting cheese and sliced ham\n" +
                "            between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables\n" +
                "            like lettuce, tomato, onion or pickle slices can also be included. Various kinds of\n" +
                "            mustard and mayonnaise are also\n" +
                "            common.\",\"image\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG\",\"ingredients\":[\"Sliced\n" +
                "            bread\",\"Cheese\",\"Ham\"]}";
    }

    @Test
    public void test_parseSandwichJson_success() throws JSONException {
        Sandwich sandwichObject = JsonUtils.parseSandwichJson(fakeSandwich);
        Assert.assertNotNull(sandwichObject);
    }
}