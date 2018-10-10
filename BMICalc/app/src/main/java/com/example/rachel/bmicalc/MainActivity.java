package com.example.rachel.bmicalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // field - starts with m to indicate member field
    private BMICalc mCalc;
    private EditText mEtHeight;
    private EditText mEtWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();

        setUpFab();

        // initialize BMI field object
        mCalc = new BMICalc();

        // find view by IDs
        //get reference to edit text objects containing user input
        mEtHeight = findViewById(R.id.etHeight);
        mEtWeight = findViewById(R.id.etWeight);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setUpFab() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strHeight;
                String strWeight;
                double height;
                double weight;

                // get values from edit texts


                // get text from edit texts
                strHeight = mEtHeight.getText().toString();
                strWeight = mEtWeight.getText().toString();

                try
                {
                    height = Double.parseDouble(strHeight);
                    weight = Double.parseDouble(strWeight);

                    // put edit text values into calc object
                    mCalc.setHeight(height);
                    mCalc.setWeight(weight);

                    // show user BMI
                    Snackbar.make(view, "BMI is " + mCalc.getBMI(), Snackbar.LENGTH_LONG)
                            .setAction("More Details...", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getApplicationContext(), ResultsActivity.class);

                                    // serialize the BMI object and store it in the intent
                                    String currentBMIObject = BMICalc.getJSONStringFromObject(mCalc);
                                    intent.putExtra("BMI", currentBMIObject);
                                    // start the Results Activity, passing above results to it
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
                catch (IllegalArgumentException iae)
                {
                    Snackbar.make(view, "Error! Height and Weight must be greater than zero."
                                    , Snackbar.LENGTH_LONG)
                                    .show();
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
