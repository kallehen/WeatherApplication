package com.example.kalle.weatherapplication.data;

import org.json.JSONObject;

/**
 * Created by Kalle on 22.09.15.
 */
public class Units implements JSONPopulator {
    private String temperature;

    public String getTemperature() {
        return temperature;
    }

    @Override
    public void populate(JSONObject data) {
        temperature = data.optString("temperature");

    }
}
