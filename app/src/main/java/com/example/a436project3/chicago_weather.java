package com.example.a436project3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class chicago_weather extends AppCompatActivity {

    private RequestQueue requestQueue;


    TextView chicagoTemperature1, chicagoTemperature2, chicagoTemperature3, chicagoTemperature4, chicagoTemperature5,
            chicagoWeather1, chicagoWeather2, chicagoWeather3, chicagoWeather4, chicagoWeather5,
            chicagoDescription1,chicagoDescription2,chicagoDescription3,chicagoDescription4,chicagoDescription5;
    String APIURL = "https://api.openweathermap.org/data/2.5/forecast?q=Chicago&appid=5ec024a51d411153537af93a973485b3&units=imperial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago_weather);




        //temperature initialization

        chicagoTemperature1 = findViewById(R.id.CtemperatureDay1);
        chicagoTemperature2 = findViewById(R.id.CtemperatureDay2);
        chicagoTemperature3 = findViewById(R.id.CtemperatureDay3);
        chicagoTemperature4 = findViewById(R.id.CtemperatureDay4);
        chicagoTemperature5 = findViewById(R.id.CtemperatureDay5);

        //weather initialization


        chicagoWeather1 = findViewById(R.id.CweatherDay1);
        chicagoWeather2 = findViewById(R.id.CweatherDay2);
        chicagoWeather3 = findViewById(R.id.CweatherDay3);
        chicagoWeather4 = findViewById(R.id.CweatherDay4);
        chicagoWeather5 = findViewById(R.id.CweatherDay5);


        //description initialization

        chicagoDescription1 = findViewById(R.id.CdescriptionDay1);
        chicagoDescription2 = findViewById(R.id.CdescriptionDay2);
        chicagoDescription3 = findViewById(R.id.CdescriptionDay3);
        chicagoDescription4 = findViewById(R.id.CdescriptionDay4);
        chicagoDescription5 = findViewById(R.id.CdescriptionDay5);

        requestQueue = Volley.newRequestQueue(this);

        final JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, APIURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                    for (int i = 0; i < 5; i++) {


                        JSONArray list = response.getJSONArray("list");

                        JSONObject Obj = list.getJSONObject(i);

                        JSONObject mainObj = Obj.getJSONObject("main");


                        JSONArray weatherArray = Obj.getJSONArray("weather");
                        JSONObject weatherObj = weatherArray.getJSONObject(0);


                        String temperature = mainObj.getString("temp");
                        String weather = weatherObj.getString("main");
                        String description = weatherObj.getString("description");

                        temperature = roundTemperature(temperature);


                        if (i == 0) {
                            chicagoTemperature1.setText(temperature);
                            chicagoWeather1.setText(weather);
                            chicagoDescription1.setText(description);
                        } else if (i == 1) {

                            chicagoTemperature2.setText(temperature);
                            chicagoWeather2.setText(weather);
                            chicagoDescription2.setText(description);

                        } else if (i == 2) {

                            chicagoTemperature3.setText(temperature);
                            chicagoWeather3.setText(weather);
                            chicagoDescription3.setText(description);

                        } else if (i == 3) {

                            chicagoTemperature4.setText(temperature);
                            chicagoWeather4.setText(weather);
                            chicagoDescription4.setText(description);

                        } else if (i == 4) {

                            chicagoTemperature5.setText(temperature);
                            chicagoWeather5.setText(weather);
                            chicagoDescription5.setText(description);

                        }


                    }

                } catch (JSONException e) {
                    e.printStackTrace();


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });


        requestQueue.add(jsonRequest);
    }


    public String roundTemperature(String init){

        double temp = Double.parseDouble(init);

        int doubleInit = (int)temp;

        String result = Integer.toString(doubleInit);
        return result;

    }

}



