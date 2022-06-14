package com.example.weather.model;

public class CurrentWeather {
    private final String location;
    private final String temperature;
    private final String condition;
    private final String sunrise;
    private final String sunset;
    private final String max;
    private final String min;
    private final String visibility;
    private final String wind;
    private final String humidity;
    private final String pressure;

    public CurrentWeather(String location, String temperature, String condition, String sunrise,
                          String sunset, String max, String min, String visibility, String wind, String humidity, String pressure) {
        this.location = location;
        this.temperature = temperature;
        this.condition = condition;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.max = max;
        this.min = min;
        this.visibility = visibility;
        this.wind = wind;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public String getLocation() {
        return location;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getWind() {
        return wind;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }
}
