package com.example.weather.helper;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Formatter {

    @NonNull
    public static String kelvinToCelcius(String kelvin) {
        int celcius = (int) ((Double.parseDouble(kelvin) - 273));
        return String.valueOf(celcius);
    }

    @NonNull
    public static String TimestampToTime(String time){
        Date date = new Date (Long.parseLong(time) *1000);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
        return sdf.format(date);
    }

    @NonNull
    public static String setFirstLetterUppercase(@NonNull String word){
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }
}
