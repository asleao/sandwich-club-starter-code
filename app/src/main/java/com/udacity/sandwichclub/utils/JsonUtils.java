package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.exceptions.EmptyParameterException;
import com.udacity.sandwichclub.exceptions.NullParameterException;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException, NullParameterException, EmptyParameterException {
        validateNullParameter(json);
        validateEmptyParameter(json);
        JSONObject sandwichJson = new JSONObject(json);
        JSONObject name = sandwichJson.getJSONObject("name");
        List<String> alsoKnownAs = jsonArrayToListOfStrings(name.getJSONArray("alsoKnownAs"));
        String placeOfOrigin = sandwichJson.getString("placeOfOrigin");
        String description = sandwichJson.getString("description");
        String image = sandwichJson.getString("image");
        JSONArray ingredientsArray = sandwichJson.getJSONArray("ingredients");
        List<String> ingredients = jsonArrayToListOfStrings(ingredientsArray);

        return new Sandwich(name.getString("mainName"), alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }

    private static void validateNullParameter(String json) throws NullParameterException {
        if (json == null) {
            throw new NullParameterException("Json parameter should not be null.");
        }
    }

    private static void validateEmptyParameter(String json) throws EmptyParameterException {
        if (json.isEmpty()) {
            throw new EmptyParameterException("Json parameter should not be empty.");
        }
    }

    private static List<String> jsonArrayToListOfStrings(JSONArray jsonArray) throws JSONException {
        List<String> strings = new ArrayList<>(jsonArray.length());
        for (int index = 0; index < jsonArray.length(); index++) {
            String object = jsonArray.getString(index);
            strings.add(object);
        }
        return strings;
    }
}
