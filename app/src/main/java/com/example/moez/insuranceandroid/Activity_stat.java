package com.example.moez.insuranceandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Activity_stat extends AppCompatActivity {

    PieChart pieChart ;
    ArrayList<Entry> entries ;
    ArrayList<String> PieEntryLabels ;
    PieDataSet pieDataSet ;
    PieData pieData ;
     int oui ;
     int non ;

    String url_oui= "http://10.0.2.2:18080/insurance-javaee-web/insurance/survey/Result/oui/1";
    String url_non= "http://10.0.2.2:18080/insurance-javaee-web/insurance/survey/Result/non/1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_oui ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONObject obj = new JSONObject(response);

                            int success = obj.getInt("success");

                            oui = success ;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!"+error);
            }
        }) ;

        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url_non ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                            JSONObject obj = new JSONObject(response);

                            int success = obj.getInt("success");

                            non = success ;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!"+error);
            }
        }) ;


        System.out.println("moooez"+oui);

        RequestQueue queue_oui = Volley.newRequestQueue(Activity_stat.this);
        queue_oui.add(stringRequest);

        RequestQueue queue_non = Volley.newRequestQueue(Activity_stat.this);
        queue_non.add(stringRequest1);



       pieChart = (PieChart) findViewById(R.id.chart1);

        entries = new ArrayList<>();

        PieEntryLabels = new ArrayList<String>();

        AddValuesToPIEENTRY();

        AddValuesToPieEntryLabels();

        pieDataSet = new PieDataSet(entries, "");

        pieData = new PieData(PieEntryLabels, pieDataSet);

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChart.setData(pieData);

        pieChart.animateY(3000);

    }

    public void AddValuesToPIEENTRY(){

        entries.add(new BarEntry(50f, 0));
        entries.add(new BarEntry(50f, 1));

    }

    public void AddValuesToPieEntryLabels(){

        PieEntryLabels.add("Oui");
        PieEntryLabels.add("Non");

    }
}