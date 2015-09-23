package com.example.kalle.weatherapplication.data;

import org.json.JSONObject;

/**
 * Created by Kalle on 22.09.15.
 */
public class Item implements JSONPopulator{
    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {

        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }
}
