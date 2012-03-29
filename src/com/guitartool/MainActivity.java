package com.guitartool;


import com.guitartool.chordbase.ChordBase;
import com.guitartool.metronome.Metronome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button metronomeBtn,
				   tunerBtn,
				   chordDbBtn,
				   optionsBtn,
				   aboutBtn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        metronomeBtn = (Button) this.findViewById(R.id.metronomeBtn);
    	tunerBtn	 = (Button) this.findViewById(R.id.tunerBtn);
    	chordDbBtn	 = (Button) this.findViewById(R.id.chordbaseBtn);
    	optionsBtn	 = (Button) this.findViewById(R.id.general_optionsBtn);
    	aboutBtn	 = (Button) this.findViewById(R.id.aboutBtn);
    	initButtonsOnClickListeners();
    	
    }
    
	private void initButtonsOnClickListeners() {
		metronomeBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				openMetronomeAct();
			}
		});
		tunerBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				openTunerAct();
			}
		});
		chordDbBtn.setOnClickListener(new OnClickListener()
		{
			public void  onClick(View v)
			{
				openChordbaseAct();
			}
		});
		
		optionsBtn.setOnClickListener(new OnClickListener()
		{
			public void  onClick(View v)
			{
				openOptionsAct();
			}
		});
		
		aboutBtn.setOnClickListener(new OnClickListener()
		{
			public void  onClick(View v)
			{
				openAboutAct();
			}
		});
	}

	private void openMetronomeAct() {
		Intent intent = new Intent(getApplicationContext(), Metronome.class);
		startActivity(intent);
		
	}

	private void openTunerAct() {
		//Intent intent = new Intent(getApplicationContext(), Metronome.class);
		//startActivity(intent);
	}
	
	private void openChordbaseAct(){
		Intent intent = new Intent(getApplicationContext(), ChordBase.class);
		startActivity(intent);
	}
	
	private void openOptionsAct(){
		//Intent intent = new Intent(getApplicationContext(), Metronome.class);
		//startActivity(intent);
	}
	
	private void openAboutAct(){
		//Intent intent = new Intent(getApplicationContext(), Metronome.class);
		//startActivity(intent);
	}

}