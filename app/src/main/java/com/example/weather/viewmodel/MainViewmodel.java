package com.example.weather.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weather.helper.Formatter;
import com.example.weather.model.CurrentWeather;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainViewmodel extends AndroidViewModel implements Runnable {

    private final MutableLiveData<String> _location = new MutableLiveData<>();

    private final MutableLiveData<CurrentWeather> _currentWeather = new MutableLiveData<>();
    public LiveData<CurrentWeather> getCurrentWeather(){
        return _currentWeather;
    }

    public MainViewmodel(@NonNull Application application) {
        super(application);

        _location.setValue("Dhaka");
    }

    public void setLocation(String location){
        _location.setValue(location);
    }

    @Override
    public void run() {
        getWeatherDataFromApi();
    }


    /**
     * this method bring data from openweather api using volley library, to make the request
     * requires api key and base url. then retrieved jsonObject format data sent to another method
     * to format them properly.
     */
    public void getWeatherDataFromApi(){
        String apiKey = "137d8b17df3d597ee6a93aef967ba9e6";
        String openWeatherURL = "https://api.openweathermap.org/data/2.5/weather?q=";
        String url = openWeatherURL + _location.getValue() + "&appid=" + apiKey;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                setWeatherData(jsonObject);
            } catch (JSONException e) {
                Log.d("err", "getWeatherDataFromApi: fucking error");
            }
        }, Throwable::printStackTrace);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplication());
        requestQueue.add(stringRequest);
    }

    /**
     * after receiving data as json format those data need to be string format
     * so, this method organize them in string format
     * @param jsonObject weather data in jsonObject format
     * @throws JSONException if json is null or can't read properly
     */
    private void setWeatherData(@NonNull JSONObject jsonObject) throws JSONException {
        JSONObject mainObject = jsonObject.getJSONObject("main");
        String temperature = mainObject.getString("temp");

        String condition = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

        JSONObject sun = jsonObject.getJSONObject("sys");
        String sunrise = sun.getString("sunrise");
        String sunset = sun.getString("sunset");

        String max = mainObject.getString("temp_max");
        String min = mainObject.getString("temp_min");
        String visibility = jsonObject.getString("visibility");

        String humidity = jsonObject.getJSONObject("main").getString("humidity");
        String pressure = jsonObject.getJSONObject("main").getString("pressure");
        String wind = jsonObject.getJSONObject("wind").getString("speed");


        _currentWeather.setValue(new CurrentWeather(
                Formatter.setFirstLetterUppercase(Objects.requireNonNull(_location.getValue())),
                Formatter.kelvinToCelcius(temperature),
                Formatter.setFirstLetterUppercase(condition),
                Formatter.TimestampToTime(sunrise),
                Formatter.TimestampToTime(sunset),
                Formatter.kelvinToCelcius(max),
                Formatter.kelvinToCelcius(min),
                visibility, wind, humidity, pressure
        ));
    }
}
