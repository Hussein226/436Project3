package com.example.a436project3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class losangeles_weather extends AppCompatActivity {


    TextView LAtemperature1, LAtemperature2, LAtemperature3, LAtemperature4, LAtemperature5,
            LAweather1, LAweather2, LAweather3, LAweather4, LAweather5,
            LAdescription1,LAdescription2,LAdescription3,LAdescription4,LAdescription5;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_losangeles_weather);

        //temperature initialization

        LAtemperature1 = findViewById(R.id.LAtemperatureDay1);
        LAtemperature2 = findViewById(R.id.LAtemperatureDay2);
        LAtemperature3 = findViewById(R.id.LAtemperatureDay3);
        LAtemperature4 = findViewById(R.id.LAtemperatureDay4);
        LAtemperature5 = findViewById(R.id.LAtemperatureDay5);


        //weather initialization
        LAweather1 = findViewById(R.id.LAweatherDay1);
        LAweather2 = findViewById(R.id.LAweatherDay2);
        LAweather3 = findViewById(R.id.LAweatherDay3);
        LAweather4 = findViewById(R.id.LAweatherDay4);
        LAweather5 = findViewById(R.id.LAweatherDay5);

        //description initialization

        LAdescription1 = findViewById(R.id.LAdescriptionDay1);
        LAdescription2 = findViewById(R.id.LAdescriptionDay2);
        LAdescription3 = findViewById(R.id.LAdescriptionDay3);
        LAdescription4 = findViewById(R.id.LAdescriptionDay4);
        LAdescription5 = findViewById(R.id.LAdescriptionDay5);

        requestQueue = Volley.newRequestQueue(this);


        String APIURL = "https://api.openweathermap.org/data/2.5/forecast?q=California&appid=5ec024a51d411153537af93a973485b3&units=imperial";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, APIURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    for(int i = 0; i < 5; i++){


                        JSONArray list = response.getJSONArray("list");

                        JSONObject Obj = list.getJSONObject(i);

                        JSONObject mainObj = Obj.getJSONObject("main");


                        JSONArray weatherArray = Obj.getJSONArray("weather");
                        JSONObject weatherObj = weatherArray.getJSONObject(0);


                        String temperature = mainObj.getString("temp");
                        String weather = weatherObj.getString("main");
                        String description = weatherObj.getString("description");

                        temperature = roundTemperature(temperature);


                        if(i == 0){
                            LAtemperature1.setText(temperature);
                            LAweather1.setText(weather);
                            LAdescription1.setText(description);
                        }
                        else if(i == 1){

                            LAtemperature2.setText(temperature);
                            LAweather2.setText(weather);
                            LAdescription2.setText(description);

                        }
                        else if (i == 2){

                            LAtemperature3.setText(temperature);
                            LAweather3.setText(weather);
                            LAdescription3.setText(description);

                        }
                        else if (i == 3){

                            LAtemperature4.setText(temperature);
                            LAweather4.setText(weather);
                            LAdescription4.setText(description);

                        }
                        else if(i == 4){

                            LAtemperature5.setText(temperature);
                            LAweather5.setText(weather);
                            LAdescription5.setText(description);

                        }


                    }


                }catch(JSONException e){
                    e.printStackTrace();
                    System.out.println("errorrrororororororor");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue.add(jsonObjectRequest);

    }



    public String roundTemperature(String init){

        double temp = Double.parseDouble(init);

        int doubleInit = (int)temp;

        String result = Integer.toString(doubleInit);
        return result;

    }
}
