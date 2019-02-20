package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject sandwichJson = new JSONObject(json);
        JSONObject mainName = sandwichJson.getJSONObject("mainName");
        String placeOfOrigin = sandwichJson.getString("placeOfOrigin");
        String description = sandwichJson.getString("description");
        String image = sandwichJson.getString("image");
        JSONArray ingredients = sandwichJson.getJSONArray("ingredients");
        Sandwich sandwich = new Sandwich();
        return null;
    }
}
