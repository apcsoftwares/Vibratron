package com.phobos.vibratron;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Vibrator;
import android.os.Build;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.ToggleButton;

public class MainActivity extends Activity {


	final int maxInt = 300;
	long pattern[]  = {0,100,maxInt/2};
	ToggleButton vibrate;

	Vibrator vib;
	SeekBar intensity;

	@Override
	protected void onStop()
	{
		vib.cancel();
		super.onStop();
	}

	@Override
	protected void onResume()
	{
		vibrate.setChecked(false);
		super.onResume();
	}

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		/*
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
		vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		vibrate = (ToggleButton) findViewById(R.id.toggleButton);

		intensity = (SeekBar) findViewById(R.id.seekBar);
		intensity.setMax(maxInt);
		intensity.setProgress(maxInt/2);

		vibrate.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (vibrate.isChecked())
				{
					vib.vibrate(pattern,0);
				}else
				{
					vib.cancel();
				}
			}
		});

		intensity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
			{
				pattern[2] = maxInt-intensity.getProgress();
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				if (vibrate.isChecked()) vib.vibrate(pattern,0);
			}
		});

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

}
