package com.example.rachel.homework2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Random;

//import com.example.rachel.homework2.R;

public class MainActivity extends AppCompatActivity
{

	private Random mGenerator;
	private int mWinningNumber;
	private View mSB_container;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		mGenerator = new Random();
		mSB_container = findViewById(R.id.activity_main);

		startGame();
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				startGame();
			}
		});
	}

	public void startGame()
	{
		int min = 0;
		int max = 2;
		mWinningNumber = mGenerator.nextInt((max - min) + 1) + min;
		Snackbar.make(findViewById(R.id.activity_main), R.string.welcome_new_game, Snackbar.LENGTH_SHORT)
				.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings)
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void checkIfWinner(View view)
	{
		String button = ((Button) view).getText().toString();
		int buttonValue = Integer.parseInt(button) - 1;

		if (buttonValue == mWinningNumber)
		{
			Snackbar.make(findViewById(R.id.activity_main), R.string.win_game, Snackbar.LENGTH_SHORT)
					.show();
		} else
		{
			Snackbar.make(findViewById(R.id.activity_main), R.string.lose_game, Snackbar.LENGTH_SHORT)
					.show();
		}
	}
}
