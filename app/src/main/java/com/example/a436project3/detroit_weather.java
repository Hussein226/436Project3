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

    TextView detroitWeather1, detroitDescription1, detroitWeather2, detroitDescription2, detroitWeather3, detroitDescription3, detroitWeather4, detroitDescription4, detroitWeather5, detroitDescription5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detroit_weather);

        homeButton = (Button) findViewById(R.id.homeDetroit);
        detroitWeather1 = (TextView) findViewById(R.id.detroitWeather1);
        detroitDescription1 = (TextView) findViewById(R.id.detroitDescription1);
        detroitWeather2 = (TextView) findViewById(R.id.detroitWeather2);
        detroitDescription2 = (TextView) findViewById(R.id.detroitDescription2);
        detroitWeather3 = (TextView) findViewById(R.id.detroitWeather3);
        detroitDescription3 = (TextView) findViewById(R.id.detroitDescription3);
        detroitWeather4 = (TextView) findViewById(R.id.detroitWeather4);
        detroitDescription4 = (TextView) findViewById(R.id.detroitDescription4);
        detroitWeather5 = (TextView) findViewById(R.id.detroitWeather5);
        detroitDescription5 = (TextView) findViewById(R.id.detroitDescription5);

        requestQueue = Volley.newRequestQueue(this);
        //create object request
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(
                        Request.Method.GET, //the request method
                        "https://api.openweathermap.org/data/2.5/forecast?q=Detroit&appid=5ec024a51d411153537af93a973485b3",
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {


                                    for (int i = 0; i< 33; i = i +8){

                                        JSONArray list = response.getJSONArray("list");
                                        JSONObject firstElement = list.getJSONObject(i);
                                        JSONArray weatherArray = firstElement.getJSONArray("weather");



                                        //there's one object in the array
                                        JSONObject currentWeather = weatherArray.getJSONObject(0);
                                        //int id = currentWeather.getInt("id");
                                        String mainWeather = currentWeather.getString("main");
                                        String description =
                                                currentWeather.getString("description");
//                                        Log.i("JSON info", "ID: " + id);
//                                        Log.i("JSON info", "main weather: " + mainWeather);
//                                        Log.i("JSON info", "Description: " + description);

                                        if( i == 0){
                                            detroitWeather1.setText("Weather: " + mainWeather);
                                            detroitDescription1.setText("Description: " + description);

                                        }
                                        else if( i == 8){

                                        }
                                        else if (i == 16){

                                        }
                                        else if(i == 24){

                                        }
                                        else if (i == 32){

                                        }
                                    }
                                    //get description of weather

                                }
                                catch(JSONException ex) {
                                    Log.e("JSON Error", ex.getMessage());
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error) {
                            }
                        }
                );//end of JSON object request
        requestQueue.add(jsonObjectRequest);



    }//end onCreate


    public void homeActivity(){
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }



}
