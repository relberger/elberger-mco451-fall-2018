package com.example.rachel.tipcalculator.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rachel.tipcalculator.R;
import com.example.rachel.tipcalculator.classes.KeyPadController;
import com.example.rachel.tipcalculator.classes.TipCalculator;
import com.example.rachel.tipcalculator.classes.Utils;

import static com.example.rachel.tipcalculator.classes.Utils.sREQUEST_CODE_LOCATION_PERMISSION;

public class MainActivity extends AppCompatActivity
{
	private TipCalculator mCurrentTipCalculator;
	private KeyPadController mKeyPadController;
	private View mCurrentView, mSBContainer;

	private ImageView mBackground;
	private EditText mFieldSubTotal, mFieldTipPercent, mFieldTaxAmount, mFieldPayers;

	private EditText[] mFields;
	private Button[] mArrowUpButtons, mArrowDownButtons;
	private Snackbar mSnackBar;

	private View.OnFocusChangeListener mFocusChangeListener;

	private View.OnClickListener mLaunchResultsClickListener;
	private String mCurrentTextBeforeChange;

	// fields and key names for the Settings/Preferences
	private boolean mUsePicBackground, mUseNightMode, mUseAutoCalculate;

	private double mDefaultTaxPercentage, mDefaultTipPercentage;

	private final String mPLUS_SIGN = "+", mMINUS_SIGN = "-";
	private final String mPCT_FORMAT_STRING = "%.2f";
	private final String mKEY_CURRENT_CALC = "CURRENT_CALC";
	private final String mSUBTOTAL_PREF_KEY = "SUBTOTAL", mPAYERS_PREF_KEY = "PAYERS";
	private final String mPREFS_FIELDS = "PREFS_FIELDS";

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putString(mKEY_CURRENT_CALC,
				TipCalculator.getJSONStringFromObject(mCurrentTipCalculator));
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		setDefaultValuesForPreferences();                      // Set defaults before restoring
		restorePreferences();                                  // This will set mUseNightMode

		mUseNightMode = true;

		Utils.getLocationPermission(this,
				sREQUEST_CODE_LOCATION_PERMISSION);  // get actual sunset
		AppCompatDelegate.setDefaultNightMode(mUseNightMode ?
				AppCompatDelegate.MODE_NIGHT_AUTO :
				AppCompatDelegate.MODE_NIGHT_NO);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupToolbar();
		setupContent(savedInstanceState);
		setupFAB();
	}

	private void setupContent(Bundle savedInstanceState)
	{
	}

	private void restorePreferences()
	{
	}

	private void setDefaultValuesForPreferences()
	{
	}

	private void setupToolbar()
	{
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}

	private void setupFAB()
	{
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				calculate(true);
			}
		});
	}

	private void calculate(boolean fromButton)
	{

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		switch (id)
		{
			case R.id.menu_settings:
				showSettings();
				return true;
			case R.id.menu_calculate:
				calculate(true);
				return true;
			case R.id.menu_clearField:
				//To Do
				return true;
			case R.id.menu_resetAll:
				resetAll();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void resetAll()
	{
	}

	private void showSettings()
	{
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
										   @NonNull int[] grantResults)
	{
		boolean permissionDenied = grantResults[0] == -1;

		if (requestCode == sREQUEST_CODE_LOCATION_PERMISSION)
		{
			if (permissionDenied)
			{
				Utils.promptToAllowPermissionRequest(this);
			}
		} else
		{
			super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		}
	}

	public void showAbout(MenuItem item)
	{
		Toast.makeText(getApplicationContext(), "About...", Toast.LENGTH_LONG).show();

		Utils.showInfoDialog(MainActivity.this, "About", "About alert dialog");
        /* same as ^
        AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
        adb.setTitle("About");
        adb.setMessage("About alert dialog");
        adb.setIcon(R.drawable.ic_logo);
        adb.setNeutralButton(getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Clicked ok...", Toast.LENGTH_LONG).show();
            }
        });
        adb.show();*/
	}
}