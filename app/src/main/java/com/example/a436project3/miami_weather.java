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

public class miami_weather extends AppCompatActivity {

    TextView miamiTemperature1,miamiTemperature2,miamiTemperature3,miamiTemperature4,miamiTemperature5,
            miamiWeather1,miamiWeather2,miamiWeather3,miamiWeather4,miamiWeather5,
            miamiDescription1,miamiDescription2,miamiDescription3,miamiDescription4,miamiDescription5;


    private RequestQueue requestQueue;

    String APIURL = "https://api.openweathermap.org/data/2.5/forecast?q=Miami&appid=5ec024a51d411153537af93a973485b3&units=imperial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miami_weather);


        requestQueue = Volley.newRequestQueue(this);
        //temperature

        miamiTemperature1 = findViewById(R.id.MtemperatureDay1);
        miamiTemperature2 = findViewById(R.id.MtemperatureDay2);
        miamiTemperature3 = findViewById(R.id.MtemperatureDay3);
        miamiTemperature4 = findViewById(R.id.MtemperatureDay4);
        miamiTemperature5 = findViewById(R.id.MtemperatureDay5);

        //weather

        miamiWeather1 = findViewById(R.id.MweatherDay1);
        miamiWeather2 = findViewById(R.id.MweatherDay2);
        miamiWeather3 = findViewById(R.id.MweatherDay3);
        miamiWeather4 = findViewById(R.id.MweatherDay4);
        miamiWeather5 = findViewById(R.id.MweatherDay5);


        //description


        miamiDescription1 = findViewById(R.id.MdescriptionDay1);
        miamiDescription2 = findViewById(R.id.MdescriptionDay2);
        miamiDescription3 = findViewById(R.id.MdescriptionDay3);
        miamiDescription4 = findViewById(R.id.MdescriptionDay4);
        miamiDescription5 = findViewById(R.id.MdescriptionDay5);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, APIURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{

                    JSONArray list = response.getJSONArray("list");

                    for(int i = 0; i < list.length(); i = i + 8){




                        JSONObject Obj = list.getJSONObject(i);

                        JSONObject mainObj = Obj.getJSONObject("main");


                        JSONArray weatherArray = Obj.getJSONArray("weather");
                        JSONObject weatherObj = weatherArray.getJSONObject(0);


                        String temperature = mainObj.getString("temp");
                        String weather = weatherObj.getString("main");
                        String description = weatherObj.getString("description");

                        temperature = roundTemperature(temperature);


                        if(i == 0){
                            miamiTemperature1.setText(temperature);
                            miamiWeather1.setText(weather);
                            miamiDescription1.setText(description);
                        }
                        else if(i == 8){

                            miamiTemperature2.setText(temperature);
                            miamiWeather2.setText(weather);
                            miamiDescription2.setText(description);

                        }
                        else if (i == 16){

                            miamiTemperature3.setText(temperature);
                            miamiWeather3.setText(weather);
                            miamiDescription3.setText(description);

                        }
                        else if (i == 24){

                            miamiTemperature4.setText(temperature);
                            miamiWeather4.setText(weather);
                            miamiDescription4.setText(description);

                        }
                        else if(i == 32){

                            miamiTemperature5.setText(temperature);
                            miamiWeather5.setText(weather);
                            miamiDescription5.setText(description);

                        }



                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
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
