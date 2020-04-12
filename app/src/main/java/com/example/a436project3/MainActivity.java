package com.example.a436project3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button forecastButton;
    TextView mainTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        forecastButton = (Button) findViewById(R.id.forcastButton);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner_array));


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                final String cityChoice = parent.getItemAtPosition(position).toString();


                forecastButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(cityChoice.equals("Detroit")){
                            openDetroitActivity();
                        }
                        else if(cityChoice.equals("Miami")){
                            openMiamiActivity();
                        }
                        else if(cityChoice.equals("Chicago")){
                            openChicagoActivity();
                        }
                        else if(cityChoice.equals("Seattle")){
                            openSeattleActivity();
                        }
                        else if(cityChoice.equals("Los Angeles")){
                            openLosAngelesActivity();
                        }
                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public void openDetroitActivity(){
        Intent intent = new Intent(this, detroit_weather.class);

        startActivity(intent);
    }

    public void openChicagoActivity(){
        Intent intent = new Intent(this, chicago_weather.class);

        startActivity(intent);
    }

    public void openMiamiActivity(){
        Intent intent = new Intent(this, miami_weather.class);

        startActivity(intent);
    }

    public void openSeattleActivity(){
        Intent intent = new Intent(this, seattle_weather.class);

        startActivity(intent);
    }

    public void openLosAngelesActivity(){
        Intent intent = new Intent(this, losangeles_weather.class);

        startActivity(intent);
    }
}

