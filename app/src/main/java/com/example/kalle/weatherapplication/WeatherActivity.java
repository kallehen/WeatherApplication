package com.example.kalle.weatherapplication;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kalle.weatherapplication.data.Channel;
import com.example.kalle.weatherapplication.data.Item;
import com.example.kalle.weatherapplication.service.WeatherServiceCallback;
import com.example.kalle.weatherapplication.service.YahooWeatherService;

public class WeatherActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;
    private EditText editLocation;

    private YahooWeatherService service;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        weatherIconImageView = (ImageView)findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditiontextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);
        editLocation = (EditText)findViewById(R.id.editLocation);


        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading.....");
        dialog.show();
        service.refreshWeather("Mikkeli");

    }

    @Override
    public void serviceSuccess(Channel channel) {
    dialog.hide();

        Item item = channel.getItem();
        int resourceId = getResources().getIdentifier("drawable/pic_" + item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageDrawable(weatherIconDrawable);
        temperatureTextView.setText(item.getCondition().getTemperature() + "\u00b0" + channel.getUnits().getTemperature());
        conditionTextView.setText(item.getCondition().getDescription());
        locationTextView.setText(service.getLocation());
    }

    @Override
    public void serviceFailure(Exception ex) {
        dialog.hide();
        Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG).show();
    }
    /** Called when the user touches the button */
    public void sendLocation(View view) {
        // Do something in response to button click
        service.refreshWeather(String.valueOf(editLocation.getText()));


    }
}
