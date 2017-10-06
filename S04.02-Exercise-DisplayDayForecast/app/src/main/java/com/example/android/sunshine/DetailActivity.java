package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    public static final String WEATHER_DETAIL = "weather_detail";

    private TextView mWeatherDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherDetailTextView = (TextView) findViewById(R.id.tv_weather_detail);

        // COMPLETED (2) Display the weather forecast that was passed from MainActivity
        Intent intent = getIntent();
        if (intent.hasExtra(WEATHER_DETAIL)) {
            String weatherDetail = intent.getStringExtra(WEATHER_DETAIL);
            if (weatherDetail != null && !weatherDetail.isEmpty()) {
                mWeatherDetailTextView.setText(weatherDetail);
            }
        }
    }
}