package de.android.ayrathairullin.myapplication;

import android.support.v4.util.Consumer;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class Weather {
    public static class Condition {
        @SerializedName("text")
        public String text;

        public static class Forecats {
            @SerializedName("temp_c")
            public Float temperature;

            @SerializedName("condition")
            public Condition condition;
        }

        public static class ApiResult {
            @SerializedName("current")
            public Forecats current;
        }

        public interface WeatherService {
            @GET("/v1/current.json?key=47bb783fbc3243e8839170436190706")
            Call<ApiResult> getCurrentWeather(@Query("q") String city, @Query("lang") String lang);
        }

        public static void getWeather(String city, String lang, final Consumer<String> callback) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.apixu.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Call<ApiResult> call = retrofit
                    .create(WeatherService.class)
                    .getCurrentWeather(city, lang);
            call.enqueue(new Callback<ApiResult>() {
                @Override
                public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                    ApiResult result = response.body();
                    String text = result.current.condition.text;
                    int temp = result.current.temperature.intValue();
                    String answer = "Там сейчас " + text + ", где то " + temp + " градусов";
                    callback.accept(answer);
                }

                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {
                    Log.d("WEATHER", t.getMessage());
                }
            });
        }
    }
}
