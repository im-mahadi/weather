package com.example.weather.model;

public class CurrentWeather {
    private String location;
    private String temperature;
    private String condition;
    private String sunrise;
    private String sunset;

    public CurrentWeather(String location, String temperature, String condition, String sunrise, String sunset) {
        this.location = location;
        this.temperature = temperature;
        this.condition = condition;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
