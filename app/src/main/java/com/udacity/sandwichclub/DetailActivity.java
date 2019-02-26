package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.Iterator;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView mOriginLabel;
    private TextView mOriginContent;
    private TextView mAlsoKwonLabel;
    private TextView mAlsoKwonContent;
    private TextView mIngredientsLabel;
    private TextView mIngredientsContent;
    private TextView mDescriptionLabel;
    private TextView mDescriptionContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);
        mOriginLabel = findViewById(R.id.origin_label_tv);
        mOriginContent = findViewById(R.id.origin_tv);
        mAlsoKwonLabel = findViewById(R.id.also_known_as_label_tv);
        mAlsoKwonContent = findViewById(R.id.also_known_as_tv);
        mIngredientsLabel = findViewById(R.id.ingredients_label_tv);
        mIngredientsContent = findViewById(R.id.ingredients_tv);
        mDescriptionLabel = findViewById(R.id.description_label_tv);
        mDescriptionContent = findViewById(R.id.description_tv);


        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
//                .placeholder(R.drawable.)
//                .error()
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        populatePlaceOfOrigin(sandwich.getPlaceOfOrigin());
        populateAlsoKnownAs(sandwich.getAlsoKnownAs());
        populateIngredients(sandwich.getIngredients());
        populateDescription(sandwich.getDescription());
    }

    private void populateDescription(String descrition) {
        if (descrition.isEmpty()) {
            mDescriptionLabel.setVisibility(View.GONE);
            mDescriptionContent.setVisibility(View.GONE);
        } else {
            mDescriptionContent.setText(descrition);
        }
    }

    private void populateIngredients(List<String> listOfIngredients) {
        if (listOfIngredients.isEmpty()) {
            mIngredientsLabel.setVisibility(View.GONE);
            mIngredientsContent.setVisibility(View.GONE);
        } else {
            String ingredients = listOfStringsToString(listOfIngredients);
            mIngredientsContent.setText(ingredients);
        }
    }

    private String listOfStringsToString(List<String> listOfStrings) {
        StringBuilder builder = new StringBuilder(listOfStrings.size());
        for (Iterator<String> it = listOfStrings.iterator(); it.hasNext(); ) {
            builder.append(it.next());
            if (it.hasNext()) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private void populateAlsoKnownAs(List<String> alsoKnownAsList) {
        if (alsoKnownAsList.isEmpty()) {
            mAlsoKwonLabel.setVisibility(View.GONE);
            mAlsoKwonContent.setVisibility(View.GONE);
        } else {
            String alsoKnownAs = listOfStringsToString(alsoKnownAsList);
            mAlsoKwonContent.setText(alsoKnownAs);
        }
    }

    private void populatePlaceOfOrigin(String placeOfOrigin) {
        if (placeOfOrigin.isEmpty()) {
            mOriginLabel.setVisibility(View.GONE);
            mOriginContent.setVisibility(View.GONE);
        } else {
            mOriginContent.setText(placeOfOrigin);
        }
    }
}
