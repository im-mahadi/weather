package com.example.weather;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.weather.databinding.ActivityMainBinding;
import com.example.weather.viewmodel.MainViewmodel;

public class MainActivity extends AppCompatActivity{
    private ActivityMainBinding binding;
    private MainViewmodel viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBarNavigationBar();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewmodel = new ViewModelProvider(this)
                .get(MainViewmodel.class);

        //input change change the location in viewmodel thus effect the weather data
        binding.input.setOnEditorActionListener((v, actionId, event) -> {
            boolean handled = false;
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewmodel.setLocation(binding.input.getText().toString());
                viewmodel.getWeatherDataFromApi();
                handled = true;
            }
            return handled;
        });

        updateWeather();
    }

    @Override
    protected void onStart() {
        super.onStart();

        //when app starts it update the weather running in background thread
        Thread thread = new Thread(viewmodel);
        thread.start();
    }

    /**
     * initially when app starts it have no weather data to show so its stay black,
     * after retrieving data from api, the weather data changed and thus effect the viewmodel
     * and every changes in viewmodel re render the ui values.
     *
     */
    private void updateWeather() {
        viewmodel.getCurrentWeather().observe(this, currentWeather -> {
            binding.city.setText(currentWeather.getLocation());
            binding.temperature.setText(currentWeather.getTemperature());
            binding.condition.setText(currentWeather.getCondition());
            binding.sunrise.setText(currentWeather.getSunrise());
            binding.sunset.setText(currentWeather.getSunset());
            binding.max.setText(currentWeather.getMax());
            binding.min.setText(currentWeather.getMin());
            binding.visibility.setText(currentWeather.getVisibility());
            binding.pressure.setText(currentWeather.getPressure());
            binding.wind.setText(currentWeather.getWind());
            binding.humidity.setText(currentWeather.getHumidity());
        });
    }

    /**
     * hide status bar and make app fullscreen
     */
    public void hideStatusBarNavigationBar() {
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
    }
}