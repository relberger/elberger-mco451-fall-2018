package com.example.rachel.perpetualmotion.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.perpetual_motion.pm_game.IDGame;
import com.example.rachel.perpetualmotion.R;
import com.example.rachel.perpetualmotion.lib.CardPilesAdapter;

public class MainActivity extends AppCompatActivity
{
    private IDGame mCurrentGame;
    private CardPilesAdapter mAdapter;
    // preference boolean fields
    private boolean mPrefUseAutoSave, mPrefShowErrors;
    // preference keys
    private final String mKeyPrefsName = "PREFS";
    private String mKeyAutoSave, mKeyShowErrors, mKeyGame, mKeyCheckedPiles;

    private void setDefaultValuesForPreferences()
    {
        PreferenceManager.setDefaultValues(getApplicationContext(), R.xml.main_prefs, true);
    }

    @Override
    protected void onStop()
    {
        saveToSharedPref();
        super.onStop();
    }

    private void saveToSharedPref()
    {
        SharedPreferences preferences = getSharedPreferences(mKeyPrefsName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.clear();

        saveSettingsToSharedPrefs(editor);
        saveGameAndBoardToSharedPrefsIfAutoSaveIsOn(editor);

        editor.apply();
    }

    private void saveSettingsToSharedPrefs(SharedPreferences.Editor editor)
    {
        editor.putBoolean(mKeyShowErrors, mPrefShowErrors);
        editor.putBoolean(mKeyAutoSave, mPrefUseAutoSave);
    }

    private void saveGameAndBoardToSharedPrefsIfAutoSaveIsOn(SharedPreferences.Editor editor)
    {
        if (mPrefUseAutoSave)
        {
            final boolean CHECKED_PILES[] = mAdapter.getCheckedPiles();
            //editor.putString(mKeyGame, getJSONof(mCurrentGame));

            for (int i = 0; i < mKeyCheckedPiles.length(); i++)
            {
                editor.putBoolean(mKeyCheckedPiles + 1, CHECKED_PILES[i]);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setDefaultValuesForPreferences();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupToolbar();
        setupFab();
        setupBoard();
        restoreAppSettingsFromPreferences();
    }

    private void restoreAppSettingsFromPreferences()
    {
        SharedPreferences preferences = getSharedPreferences(mKeyPrefsName, MODE_PRIVATE);
        mPrefUseAutoSave = preferences.getBoolean(mKeyAutoSave, true);
        mPrefShowErrors = preferences.getBoolean(mKeyShowErrors, true);
    }

    private void setupBoard()
    {
        //create the adapter
        mAdapter = new CardPilesAdapter(getApplicationContext(), R.string.cards_in_stack);

        // get a reference to the RecyclerView
        RecyclerView rvPiles = findViewById(R.id.rv_piles);

        // set the number of columns based on the currently loaded integers.xml
        final int RV_COLUMN_COUNT = getResources().getInteger(R.integer.rv_columns);

        // create the layout manager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, RV_COLUMN_COUNT);

        rvPiles.setHasFixedSize(true);
        rvPiles.setLayoutManager(layoutManager);
        rvPiles.setAdapter(mAdapter);
    }

    private void setupToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupFab()
    {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        menu.findItem(R.id.action_toggle_auto_save).setChecked(mPrefUseAutoSave);
        menu.findItem(R.id.action_turn_show_error_messages).setChecked(mPrefShowErrors);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void turn_action_discard(View view)
    {
    }

    public void turn_action_deal(View view)
    {
    }
}
