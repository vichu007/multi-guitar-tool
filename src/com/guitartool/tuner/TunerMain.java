package com.guitartool.tuner;

import com.guitartool.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TunerMain extends Activity {
private Button backBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuner);
        backBtn =  (Button) this.findViewById(R.id.tunerBackBtn);
        backBtn.setOnClickListener(new OnClickListener()
        {
        	public void onClick(View v)
        	{
        		finish();
        	}
        });
    }
}
