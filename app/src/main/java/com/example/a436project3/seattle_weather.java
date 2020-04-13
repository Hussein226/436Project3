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

public class seattle_weather extends AppCompatActivity {


    TextView STemperature1,STemperature2,STemperature3,STemperature4,STemperature5,
            SWeather1,SWeather2,SWeather3,SWeather4,SWeather5,
            SDescription1,SDescription2,SDescription3,SDescription4,SDescription5;


    private RequestQueue requestQueue;

    String APIURL = "https://api.openweathermap.org/data/2.5/forecast?q=Seattle&appid=5ec024a51d411153537af93a973485b3&units=imperial";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seattle_weather);


        //temperature

        STemperature1 = findViewById(R.id.StemperatureDay1);
        STemperature2 = findViewById(R.id.StemperatureDay2);
        STemperature3 = findViewById(R.id.StemperatureDay3);
        STemperature4 = findViewById(R.id.StemperatureDay4);
        STemperature5 = findViewById(R.id.StemperatureDay5);

        //weather

        SWeather1 = findViewById(R.id.SweatherDay1);
        SWeather2 = findViewById(R.id.SweatherDay2);
        SWeather3 = findViewById(R.id.SweatherDay3);
        SWeather4 = findViewById(R.id.SweatherDay4);
        SWeather5 = findViewById(R.id.SweatherDay5);


        //description


        SDescription1 = findViewById(R.id.SdescriptionDay1);
        SDescription2 = findViewById(R.id.SdescriptionDay2);
        SDescription3 = findViewById(R.id.SdescriptionDay3);
        SDescription4 = findViewById(R.id.SdescriptionDay4);
        SDescription5 = findViewById(R.id.SdescriptionDay5);


        requestQueue = Volley.newRequestQueue(this);

        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, APIURL, null, new Response.Listener<JSONObject>() {
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
                            STemperature1.setText(temperature);
                            SWeather1.setText(weather);
                            SDescription1.setText(description);
                        }
                        else if(i == 1){

                            STemperature2.setText(temperature);
                            SWeather2.setText(weather);
                            SDescription2.setText(description);

                        }
                        else if (i == 2){

                            STemperature3.setText(temperature);
                            SWeather3.setText(weather);
                            SDescription3.setText(description);

                        }
                        else if (i == 3){

                            STemperature4.setText(temperature);
                            SWeather4.setText(weather);
                            SDescription4.setText(description);

                        }
                        else if(i == 4){

                            STemperature5.setText(temperature);
                            SWeather5.setText(weather);
                            SDescription5.setText(description);

                        }



                    }
                }catch(JSONException e){
                    e.printStackTrace();
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
