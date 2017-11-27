package com.example.moez.insuranceandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AfficheActivity extends AppCompatActivity {


    TextView tv_description, tv_date;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche);

        SharedPreferences reportingPref = getSharedPreferences("reporting_app", MODE_PRIVATE);



        tv_description = findViewById(R.id.description);
        tv_date = findViewById(R.id.endDate);

        tv_description.setText(reportingPref.getString("description", ""));
        tv_date.setText(reportingPref.getString("date", ""));



        button = findViewById(R.id.DetailsVote);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AfficheActivity.this,Activity_stat.class);
                startActivity(i);
            }
        });


    }
}
