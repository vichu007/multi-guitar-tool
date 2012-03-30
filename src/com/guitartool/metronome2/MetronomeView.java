package com.guitartool.metronome2;

import com.guitartool.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class MetronomeView extends View {
	//sta³e
	public static final int UPBEAT_OFF = 2;
	public static final int UPBEAT_ON = 3;
	public static final int DOWNBEAT_OFF = 0;
	public static final int DOWNBEAT_ON = 1;
	
	//pola, typy podstawowe
	private int metrum_base, upbeat, step;
	
	//klasy
	private MediaPlayer uptone,tone;
	private TextView tv;
	
	/*
	 * Konstruktory
	 */
	public MetronomeView(Context context) {
		super(context);
		init();
	}
	public MetronomeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public MetronomeView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	/*
	 * Metody wewnêtrzne
	 */
	private void init(){
		//Ustawienie wartoœci startowych
		metrum_base = 4;
		upbeat = 0;
		step = -1;	//jeszcze nie zaczêliœmy graæ
		//za³adowanie dŸwiêków i wrzucenie ich do odtwarzaczy
		Resources r = this.getContext().getResources();
		tone	= MediaPlayer.create(this.getContext(), R.raw.da_click);
		uptone	= MediaPlayer.create(this.getContext(), R.raw.u_click);
	}
	private void playTone(int stp) {
		if (step == upbeat){
			uptone.start();
		}else{
			tone.start();
		}
	}
	private void repaint(int stp) {
		tv.setText(String.valueOf(stp+1));
	}
	/*
	 * Metody publiczne
	 */
	public void setTextView(TextView textview){
		tv = textview;
	}
	/**
	 * Ustawia wartoœæ pola upbeat na podany parametr.
	 * W przypadku gdy jest on wiêkszy od metrum_base,
	 * lub mniejszy od zera, upbeat przyjmuje wartoœæ 0.
	 * 
	 * @param ub Akcentowany krok 
	 */
	public void setUpbeat(int ub){
		if(ub>metrum_base){
			upbeat = 0;
		}else if(ub<0){
			upbeat = 0;
		}else{
			upbeat = ub;
		}
	}
	
	public void setBase(int base){
		if(base <0){
			metrum_base =0;
		}else{
			metrum_base = base;
		}
	}
	
	public void nextStep(){
		step++;
		step = step % metrum_base;
	}
	
	public void refresh(){
		nextStep();
		playTone(step);
		repaint(step);
	}
	public void onDraw(Canvas canvas){
		//TODO blablabla
	}
	
	
	
}
