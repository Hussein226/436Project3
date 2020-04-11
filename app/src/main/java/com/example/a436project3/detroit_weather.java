package com.example.a436project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class detroit_weather extends AppCompatActivity {

    private RequestQueue rq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detroit_weather);


        rq = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET, //the request method
                        "https://api.openweathermap.org/data/2.5/forecast?q=Detroit&appid=5ec024a51d411153537af93a973485b3",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //this prints the WHOLE string
//Log.i("JSON response", response.toString());
                                try {
                                    //get description of weather
                                    JSONArray weather = response.getJSONArray("weather");
//since it's one day of weather,
                                    //there's one object in the array
                                    JSONObject currentWeather = weather.getJSONObject(0);
                                    int id = currentWeather.getInt("id");
                                    String mainWeather = currentWeather.getString("main");
                                    String description =
                                            currentWeather.getString("description");
                                    Log.i("JSON info", "ID: " + id);
                                    Log.i("JSON info", "main weather: " + mainWeather);
                                    Log.i("JSON info", "Description: " + description);
                                } catch (JSONException ex) {
                                    Log.e("JSON Error", ex.getMessage());
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }
                );//end of JSON object request


        rq.add(jsonObjectRequest);
    }//end onCreate

}
