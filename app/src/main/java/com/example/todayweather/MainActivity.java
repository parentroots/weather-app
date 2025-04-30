package com.example.todayweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    ImageView goBackButton;
    TextView temperatureText,humidityText,temperatureTextMin,feelWeatherText,timeText,sealeveText,windText;
    TextView fridayTemp,saturdayTemp,sundayTemp,mondayTemp,tuesdayTemp,wednesdayTemp,thursdayTemp;
    LinearLayout fridayLayout,saturdayLayout,sundayLayout,mondayLayout,tuesdayLaout,wednesdayLaout,thursdayLaout;
    TextView sunriseText,sunsetText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String API_URL="https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=653979722d5fd3800bddea6c29700d86";

        goBackButton=findViewById(R.id.goBackButton);
        temperatureText=findViewById(R.id.temperatureText);
        temperatureTextMin=findViewById(R.id.temperatureTextMin);
        humidityText=findViewById(R.id.humidityText);
        feelWeatherText=findViewById(R.id.feelWeatherText);
        timeText=findViewById(R.id.time);
        windText=findViewById(R.id.windText);
        sealeveText=findViewById(R.id.sealevelText);


        fridayTemp=findViewById(R.id.fridayTemp);
        saturdayTemp=findViewById(R.id.saturdayTemp);
        sundayTemp=findViewById(R.id.sundayTemp);
        mondayTemp=findViewById(R.id.monedayTemp);
        tuesdayTemp=findViewById(R.id.tuesdayTemp);
        wednesdayTemp=findViewById(R.id.wednesdayTemp);
        thursdayTemp=findViewById(R.id.thursdayTemp);

        //layout finding fo today weathe hilight
         fridayLayout=findViewById(R.id.fridayLayout);
         saturdayLayout=findViewById(R.id.saturdayLyout);
         sundayLayout=findViewById(R.id.sundayLayout);
         mondayLayout=findViewById(R.id.mondayLayout);
         tuesdayLaout=findViewById(R.id.tuesdayLayout);
         wednesdayLaout=findViewById(R.id.wednesdayLayout);
         thursdayLaout=findViewById(R.id.tuesdayLayout);



         sunriseText=findViewById(R.id.sunriseText);
         sunsetText=findViewById(R.id.sunsetText);





        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FirstPageActivity.class);
                startActivity(intent);
            }
        });

        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);


        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {


                    // Main Object থেকে temperature nibo

                    JSONObject mainObject = response.getJSONObject("main");
                    double temp = mainObject.getDouble("temp");
                    double humidity =mainObject.getDouble("humidity");
                    double sealevel=mainObject.getDouble("sea_level");

                    JSONArray weahterArray=response.getJSONArray("weather");

                    JSONObject windObject=response.getJSONObject("wind");

                    double windspeed=windObject.getDouble("speed");

                    JSONObject jsonObject=weahterArray.getJSONObject(0);
                    String description=jsonObject.getString("description");




                    /*
                    JSONObject sunPosition = response.getJSONObject("sys");
                    long sunriseTimestamp = sunPosition.getLong("sunrise") * 1000;
                    long sunsetTimestamp = sunPosition.getLong("sunset") * 1000;
                    Date sunriseDate = new Date(sunriseTimestamp);
                    Date sunsetDate = new Date(sunsetTimestamp);
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                    sdf.setTimeZone(TimeZone.getDefault()); // Set to local timezone
                    String sunriseTimeFormatted = sdf.format(sunriseDate);
                    String sunsetTimeFormatted=sdf.format(sunsetDate);
                    sunriseText.setText("Sunrise: " + sunriseTimeFormatted);
                    sunsetText.setText("Sunset: " + sunsetTimeFormatted);

                    */





                    humidityText.setText(""+humidity);
                    feelWeatherText.setText(""+description);
                    windText.setText(""+windspeed);
                    sealeveText.setText(""+sealevel);









                } catch (JSONException e) {
                    throw new RuntimeException(e);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(jsonObjectRequest);






        String API_URL2="https://api.open-meteo.com/v1/forecast?latitude=22.15&longitude=90.13&daily=temperature_2m_max,temperature_2m_min,precipitation_sum,wind_speed_10m_max&timezone=auto";

         RequestQueue queue1=Volley.newRequestQueue(MainActivity.this);
         
         JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.GET, API_URL2, null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {

                 try {



                     JSONObject daily=response.getJSONObject("daily");



                     JSONArray time    =daily.getJSONArray("time");
                     JSONArray tempMax =daily.getJSONArray("temperature_2m_max");
                     JSONArray tempMin =daily.getJSONArray("temperature_2m_min");

                     String Time=time.getString(0);





                     double friday    = tempMax.getDouble(0);
                     double saturday  = tempMax.getDouble(1);
                     double sunday    = tempMax.getDouble(2);
                     double monday    = tempMax.getDouble(3);
                     double tuesday   = tempMax.getDouble(4);
                     double wednesday  = tempMax.getDouble(5);
                     double thursday   = tempMax.getDouble(6);

                     double fridaymin    = tempMin.getDouble(0);
                     double saturdaymin = tempMin.getDouble(1);
                     double sundaymin = tempMin.getDouble(2);
                     double mondaymin = tempMin.getDouble(3);
                     double tuesdaymin = tempMin.getDouble(4);
                     double wednesdaymin  = tempMin.getDouble(5);
                     double thursdaymin   = tempMin.getDouble(6);




                     fridayTemp.setText(""+friday);
                     saturdayTemp.setText(""+ saturday);
                     sundayTemp.setText(""+ sunday);
                     mondayTemp.setText(""+ monday);
                     tuesdayTemp.setText(""+ tuesday);
                     wednesdayTemp.setText(""+wednesday);
                     thursdayTemp.setText(""+thursday);

                     timeText.setText(""+Time);



                     SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.getDefault());
                     String today = sdf.format(new Date());

                     // Store today's day in SharedPreferences
                     SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                     SharedPreferences.Editor editor = sharedPreferences.edit();
                     editor.putString("today", today); // Store today's day
                     editor.apply();




                     //get sharedPreferences data
                     SharedPreferences sharedPreferences1 = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                     String savedDay = sharedPreferences1.getString("today", "not stored"); // Get stored day



                     // Get today's day again to compare
                     SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE", Locale.getDefault());
                     String today1 = sdf1.format(new Date());

                     if (today1.equals("Friday")) {
                         temperatureText.append("" + friday);
                         temperatureTextMin.append("" + fridaymin);
                     } else if (today1.equals("Saturday")) {
                         temperatureText.append("" + saturday);
                         temperatureTextMin.append("" + saturdaymin);
                     } else if (today1.equals("Sunday")) {
                         temperatureText.append("" + sunday);
                         temperatureTextMin.append("" + sundaymin);
                     } else if (today1.equals("Monday")) {
                         temperatureText.append("" + monday);
                         temperatureTextMin.append("" + mondaymin);
                     } else if (today1.equals("Tuesday")) {
                         temperatureText.append("" + tuesday);
                         temperatureTextMin.append("" + tuesdaymin);
                     } else if (today1.equals("Wednesday")) {
                         temperatureText.append("" + wednesday);
                         temperatureTextMin.append("" + wednesdaymin);
                     } else if (today1.equals("Thursday")) {
                         temperatureText.append("" + thursday);
                         temperatureTextMin.append("" + thursdaymin);
                     }




                     // Reset all layouts to your background drawable
                     fridayLayout.setBackgroundResource(R.drawable.curv_bg_2);
                     saturdayLayout.setBackgroundResource(R.drawable.curv_bg_2);
                     sundayLayout.setBackgroundResource(R.drawable.curv_bg_2);
                     mondayLayout.setBackgroundResource(R.drawable.curv_bg_2);
                     tuesdayLaout.setBackgroundResource(R.drawable.curv_bg_2);
                     wednesdayLaout.setBackgroundResource(R.drawable.curv_bg_2);
                     thursdayLaout.setBackgroundResource(R.drawable.curv_bg_2);

                    // Set today's layout to transparent if it's today
                     if (today1.equals(savedDay)) {
                         // If it's today's day, set background to transparent
                         if (today1.equals("Friday")) {
                             fridayLayout.setBackgroundColor(Color.TRANSPARENT);
                         } else if (today1.equals("Saturday")) {
                             saturdayLayout.setBackgroundColor(Color.TRANSPARENT);
                         } else if (today1.equals("Sunday")) {
                             sundayLayout.setBackgroundColor(Color.TRANSPARENT);
                         } else if (today1.equals("Monday")) {
                             mondayLayout.setBackgroundColor(Color.TRANSPARENT);
                         } else if (today1.equals("Tuesday")) {
                             tuesdayLaout.setBackgroundColor(Color.TRANSPARENT);
                         } else if (today1.equals("Wednesday")) {
                             wednesdayLaout.setBackgroundColor(Color.TRANSPARENT);
                         } else if (today1.equals("Thursday")) {
                             thursdayLaout.setBackgroundColor(Color.TRANSPARENT);
                         }
                     } else {

                         }

                 } catch (JSONException e) {
                     throw new RuntimeException(e);
                 }


             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {

             }
         });


    queue1.add(jsonObjectRequest1);


    }






    
}