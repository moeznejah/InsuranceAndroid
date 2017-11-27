package com.example.moez.insuranceandroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class SurveyAdapter extends ArrayAdapter<Survey> {

    public SurveyAdapter(Context context, int resource, List<Survey> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        Survey c = getItem(position);
        if (v == null) {
            final LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_survey, null);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences reportingPref = getContext().getSharedPreferences("reporting_app", getContext().MODE_PRIVATE);
                    SharedPreferences.Editor prefEditor = reportingPref.edit();

                    prefEditor.putInt("idSurvey", getItem(position).getIdSurvey());
                    prefEditor.putString("description", getItem(position).getDescription());

                    prefEditor.putString("date", getItem(position).getEndDate());
                    prefEditor.commit();

                    Intent i = new Intent(getContext(), AfficheActivity.class);
                    getContext().startActivity(i);
                }
            });

        }


        if (c != null) {

            TextView description = v.findViewById(R.id.description);
            description.setText(c.getDescription());


        }


        return v;
    }

}
