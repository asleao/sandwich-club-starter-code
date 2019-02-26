package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        if (json != null && !json.isEmpty()) {
            JSONObject sandwichJson = new JSONObject(json);

            JSONObject name = sandwichJson.optJSONObject(NAME);
            String mainName = name.has(MAIN_NAME) ? name.getString(MAIN_NAME) : "";
            List<String> alsoKnownAs = name.has(ALSO_KNOWN_AS) ?
                    jsonArrayToListOfStrings(name.getJSONArray(ALSO_KNOWN_AS)) :
                    new ArrayList<String>(0);
            String placeOfOrigin = sandwichJson.has(PLACE_OF_ORIGIN) ?
                    sandwichJson.getString(PLACE_OF_ORIGIN) : "";
            String description = sandwichJson.has(DESCRIPTION) ?
                    sandwichJson.optString(DESCRIPTION) :
                    "";
            String image = sandwichJson.has(IMAGE) ? sandwichJson.optString(IMAGE) : "";
            List<String> ingredients = sandwichJson.has(INGREDIENTS) ?
                    jsonArrayToListOfStrings(sandwichJson.getJSONArray(INGREDIENTS)) :
                    new ArrayList<String>(0);

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
        }
        return null;
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
