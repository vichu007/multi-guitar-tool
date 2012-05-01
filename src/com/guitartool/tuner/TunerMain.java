package com.guitartool.tuner;

import com.guitartool.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TunerMain extends Activity {
	
private Button backBtn;
private ToggleButton powerBtn;
private TextView noteTv, freqTv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuner);
        backBtn		=	(Button) this.findViewById(R.id.tunerBackBtn);
        powerBtn	=	(ToggleButton) this.findViewById(R.id.tunerToggleBtn);
        noteTv		=	(TextView)	this.findViewById(R.id.tunerNoteTv);
        freqTv		=	(TextView) this.findViewById(R.id.tunerFreqTv);
        
        backBtn.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		TunerMain.this.finish();
        	}
        });
    }
    
    private class TunerThread extends  AsyncTask<Integer, Long , Void> {

		@Override
		protected Void doInBackground(Integer... params) {
			// TODO g³ówna logika pobieraj¹ca dane z mikrofonu wrzucaj¹ca je do transformatora 
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			Log.w("TunerThread", "onPostExecute -> skoñczy³em w¹tek, tylko ciekawe jak");
		}

		@Override
		protected void onPreExecute() {
			Log.i("TunerThread", "onPreExecute  -> zeruje w¹tek przed startem");
		}

		@Override
		protected void onProgressUpdate(Long... progress) {
			// TODO aktualizacja widoku
		}
	
		@Override
		protected void onCancelled(){
			Log.i("TunerThread", "onCancelled -> anulowa³em w¹tek");
		}
    }
}
