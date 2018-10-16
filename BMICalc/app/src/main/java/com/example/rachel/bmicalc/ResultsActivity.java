package com.example.rachel.bmicalc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {
    BMICalc bmiCalc;

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        String toJSON = BMICalc.getJSONStringFromObject(bmiCalc);
        outState.putString("BMI", toJSON);
    }

    @Override
    protected void onRestoreInstanceState (Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        String fromJSON = savedInstanceState.getString("BMI");
        bmiCalc = BMICalc.getObjectFromJSONString(fromJSON);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        setupToolbar();

        setupFAB();
        processDataFromIntent();
    }

    private void processDataFromIntent() {
        Bundle extras = getIntent().getExtras();
        TextView tv_height, tv_weight, tv_BMI, tv_BMIGroup;
        tv_height = findViewById(R.id.tv_height);
        tv_weight = findViewById(R.id.tv_weight);
        tv_BMI = findViewById(R.id.tv_BMI);
        tv_BMIGroup = findViewById(R.id.tv_BMIGroup);
        if (extras != null)
        {
            bmiCalc = BMICalc.getObjectFromJSONString(extras.getString("BMI"));

            tv_height.setText(tv_height.getText().toString() + bmiCalc.getHeight());
            tv_weight.setText(tv_weight.getText().toString() + bmiCalc.getWeight());
            tv_BMI.setText(tv_BMI.getText().toString() + bmiCalc.getBMI());
            tv_BMIGroup.setText(tv_BMIGroup.getText().toString() + bmiCalc.getBMIGroup());
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupFAB() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
