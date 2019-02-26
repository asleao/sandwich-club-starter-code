package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException{
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

    static List<String> jsonArrayToListOfStrings(JSONArray jsonArray) throws JSONException {
        List<String> strings = new ArrayList<>(jsonArray.length());
        for (int index = 0; index < jsonArray.length(); index++) {
            String object = jsonArray.getString(index);
            strings.add(object);
        }
        return strings;
    }
}
