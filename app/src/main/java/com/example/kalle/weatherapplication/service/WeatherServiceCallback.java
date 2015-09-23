package com.example.kalle.weatherapplication.service;

import com.example.kalle.weatherapplication.data.Channel;

/**
 * Created by Kalle on 22.09.15.
 */
public interface WeatherServiceCallback {
    void serviceSuccess(Channel channel);
    void serviceFailure(Exception ex);
}
