package com.example.moez.insuranceandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AjoutActivity extends AppCompatActivity {

    private String Description;
    DatePicker ed_endDate;
    private EditText editDescription;
    private Button addSurvey;
    String entered_dob ;

    SimpleDateFormat dateFormatter ;
    Date d ;

    String url = "http://10.0.2.2:18080/insurance-javaee-web/insurance/survey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);



        ed_endDate = findViewById(R.id.EndDate);
        editDescription =  findViewById(R.id.description);
        addSurvey =  findViewById(R.id.ajoutSurvey) ;







        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.start();
        addSurvey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Survey survey = new Survey();

                int day = ed_endDate.getDayOfMonth();
                int month = ed_endDate.getMonth();
                int year = ed_endDate.getYear()-1900;

                d = new Date(year, month, day);
                dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                entered_dob = dateFormatter.format(d);
                Description =editDescription.getText().toString();
                survey.setEndDate(entered_dob);
                survey.setDescription(Description);
                System.out.println("Description "+Description);
                System.out.println("entered_dob "+entered_dob);
                sendPost(survey);

                Intent i = new Intent(AjoutActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
    public void sendPost(final Survey survey) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    String urljson = "http://10.0.2.2:18080/insurance-javaee-web/insurance/survey";
                    URL url = new URL(urljson);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                    conn.setRequestProperty("Accept","application/json");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    JSONObject jsonParam = new JSONObject();
                    jsonParam.put("endDate", survey.getEndDate());
                    jsonParam.put("description", survey.getDescription());

                    System.out.println("survey"+survey.toString());



                    Log.i("JSON", jsonParam.toString());
                    DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                    //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                    os.writeBytes(jsonParam.toString());

                    os.flush();
                    os.close();

                    Log.i("STATUS", String.valueOf(conn.getResponseCode()));
                    Log.i("MSG" , conn.getResponseMessage());

                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }
}
