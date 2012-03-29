package com.guitartool.metronome;

import java.util.ArrayList;

import com.guitartool.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Metronome extends Activity {
	static final int MAX_BPM = 250;
	static final int MIN_BPM = 40;
	
	private Button backBtn;
	private ToggleButton powerBtn;
	private Button minusBtn, plusBtn;
	private SeekBar tempoBar;
	private TextView tempoTv;
	private ClickView clickView;
	private Spinner metrumSp, upbeatSp;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metronome);
        clickView 	= (ClickView) this.findViewById(R.id.clickView);
        clickView.setTestView((TextView) this.findViewById(R.id.testView));
        backBtn	  	= (Button) this.findViewById(R.id.metronomeBackBtn);
        minusBtn  	= (Button) this.findViewById(R.id.m_minus_btn);
        plusBtn	  	= (Button) this.findViewById(R.id.m_plus_btn);
        powerBtn  	= (ToggleButton) this.findViewById(R.id.m_on_off_btn);
        tempoTv	  	= (TextView) this.findViewById(R.id.m_tempo_tv);
        tempoBar  	= (SeekBar) this.findViewById(R.id.m_tempo_sb);
        metrumSp	= (Spinner) this.findViewById(R.id.m_metrum_spinner);
        upbeatSp	= (Spinner) this.findViewById(R.id.m_upbeat_sp);
        
        backBtn.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		finish();
        	}
        });
        
        powerBtn.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				clickView.reset();
				clickView.setRun(isChecked);
				clickView.update();
					
			}});
		
        tempoBar.setMax(MAX_BPM-MIN_BPM);
		tempoBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

			public void onProgressChanged(SeekBar bar, int value, boolean byUser) {
				clickView.setTempo(value+MIN_BPM);
				updateTempoView();
				clickView.reset();
			}
			public void onStartTrackingTouch(SeekBar bar) {
				// TODO Auto-generated method stub
			}
			public void onStopTrackingTouch(SeekBar bar) {
				// TODO Auto-generated method stub	
			}
		});
		tempoBar.setProgress(clickView.getTempo());
		
		plusBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				clickView.increseTempo();
				updateTempoView();
				clickView.reset();
			}});
		
		minusBtn.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				clickView.decreseTempo();
				updateTempoView();
				clickView.reset();
			}}); 
		
		metrumSp.setOnItemSelectedListener(new OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	ArrayList<CharSequence> data = new ArrayList<CharSequence>();
            	switch(pos){
            	case 0:
            		clickView.setCount(1);
            		clickView.setBase(4);
            		for(int i=0; i<1;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		break;
            	case 1:
            		clickView.setCount(2);
            		clickView.setBase(4);
            		for(int i=0; i<2;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
                	break;
            	case 2:
            		clickView.setCount(3);
            		clickView.setBase(4);
            		for(int i=0; i<3;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
                	break;
            	case 3:
            		clickView.setCount(4);
            		clickView.setBase(4);
            		for(int i=0; i<4;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		break;
            	case 4:
            		clickView.setCount(5);
            		clickView.setBase(4);
            		for(int i=0; i<5;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		break;
            	case 5:
            		clickView.setCount(3);
            		clickView.setBase(8);
            		for(int i=0; i<3;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		break;
            	case 6:
            		clickView.setCount(5);
            		clickView.setBase(8);
            		for(int i=0; i<5;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		break;
            	case 7:
            		clickView.setCount(6);
            		clickView.setBase(8);
            		for(int i=0; i<6;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		break;
            	case 8:
            		clickView.setCount(7);
            		clickView.setBase(8);
            		for(int i=0; i<7;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		break;
            	case 9:
            		clickView.setCount(9);
            		clickView.setBase(8);
            		for(int i=0; i<9;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		break;
            	default:
            		clickView.setCount(4);
            		clickView.setBase(4);
            		for(int i=0; i<4;i++){
                		String tmp = Integer.toString(i+1);
                		data.add(tmp);
                	}
            		break;
            	}
            	ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getBaseContext(), android.R.layout.simple_spinner_item, data);
            	upbeatSp.setAdapter(adapter);
            	//clickView.clearGrid();
            	clickView.reset();
            	clickView.refreshDrawableState();
            }

            public void onNothingSelected(AdapterView<?> parent) {
              // Do nothing.
            }
        });
    	
    	upbeatSp.setOnItemSelectedListener(new OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	clickView.setUpbeat(pos);
            	clickView.reset();
            	clickView.refreshDrawableState();
            }

            public void onNothingSelected(AdapterView<?> parent) {
              // Do nothing.
            }
        });
    }
    
	private void updateTempoView(){
		tempoTv.setText(String.valueOf(clickView.getTempo()));
		tempoBar.setProgress(clickView.getTempo()-MIN_BPM);
	}
}
