package com.example.a436project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
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


public class detroit_weather extends AppCompatActivity {

    private RequestQueue requestQueue;

    Button homeButton;

    TextView detroitTemperature1,detroitTemperature2,detroitTemperature3,detroitTemperature4,detroitTemperature5,
            detroitWeather1, detroitDescription1, detroitWeather2, detroitDescription2, detroitWeather3, detroitDescription3, detroitWeather4, detroitDescription4, detroitWeather5, detroitDescription5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detroit_weather);

        //temperature initialization
        detroitTemperature1 = findViewById(R.id.DtemperatureDay1);
        detroitTemperature2 = findViewById(R.id.DtemperatureDay2);
        detroitTemperature3 = findViewById(R.id.DtemperatureDay3);
        detroitTemperature4 = findViewById(R.id.DtemperatureDay4);
        detroitTemperature5 = findViewById(R.id.DtemperatureDay5);

        //Weather initialization
        detroitWeather1 = findViewById(R.id.DweatherDay1);
        detroitWeather2 = findViewById(R.id.DweatherDay2);
        detroitWeather3 = findViewById(R.id.DweatherDay3);
        detroitWeather4 = findViewById(R.id.DweatherDay4);
        detroitWeather5 = findViewById(R.id.DweatherDay5);

        //description initialization
        detroitDescription1 = findViewById(R.id.DdescriptionDay1);
        detroitDescription2 = findViewById(R.id.DdescriptionDay2);
        detroitDescription3 = findViewById(R.id.DdescriptionDay3);
        detroitDescription4 = findViewById(R.id.DdescriptionDay4);
        detroitDescription5 = findViewById(R.id.DdescriptionDay5);



        requestQueue = Volley.newRequestQueue(this);
        //create object request


        String urlAPI = "https://api.openweathermap.org/data/2.5/forecast?q=Detroit&appid=5ec024a51d411153537af93a973485b3&units=imperial";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlAPI, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

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
                        detroitTemperature1.setText(temperature);
                        detroitWeather1.setText(weather);
                        detroitDescription1.setText(description);
                    }
                    else if(i == 8){

                        detroitTemperature2.setText(temperature);
                        detroitWeather2.setText(weather);
                        detroitDescription2.setText(description);

                    }
                    else if (i == 16){

                        detroitTemperature3.setText(temperature);
                        detroitWeather3.setText(weather);
                        detroitDescription3.setText(description);

                    }
                    else if (i == 24){

                        detroitTemperature4.setText(temperature);
                        detroitWeather4.setText(weather);
                        detroitDescription4.setText(description);

                    }
                    else if(i == 32){

                        detroitTemperature5.setText(temperature);
                        detroitWeather5.setText(weather);
                        detroitDescription5.setText(description);

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
                error.printStackTrace();
                System.out.println("Still not responding!!!");
            }
        });

        requestQueue.add(jsonObjectRequest);



    }//end onCreate


    public String roundTemperature(String init){

        double temp = Double.parseDouble(init);

        int doubleInit = (int)temp;

        String result = Integer.toString(doubleInit);
        return result;

    }
    public void homeActivity(){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }



}
