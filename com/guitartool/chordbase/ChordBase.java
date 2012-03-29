package com.guitartool.chordbase;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.guitartool.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class ChordBase extends Activity {
	private static final int REQUEST_CODE = 11;
	private EditText nameEt;//,VarOut;
	private Button showBtn;
	private Spinner noteSp, chordSp, variantSp;
	private String name,resp;
	private TextView chordnames;
	private LinearLayout variantLay;
	private VariantView Vview;
	//private boolean spinners;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chord_db);
        
        //Czêœæ odpowiedzialna za sformu³owanie zapytania
        nameEt 	= (EditText) this.findViewById(R.id.chordbaseNameStringTf);
        noteSp 	= (Spinner) this.findViewById(R.id.noteIdSp);
        chordSp = (Spinner) this.findViewById(R.id.chordIdSp);
       
        showBtn = (Button) this.findViewById(R.id.showBtn);
        showBtn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				openParseAct();
			}
		});
        
        //Czêœæ wyœwietlaj¹ca wynik
        variantSp = (Spinner) this.findViewById(R.id.vv_variantSp);
        chordnames = (TextView) this.findViewById(R.id.vv_chordNames);
        variantLay	= (LinearLayout) this.findViewById(R.id.vv_main_lay);
        variantLay.setVisibility(LinearLayout.INVISIBLE);
        Vview = (VariantView) this.findViewById(R.id.variantView1);
        Vview.setTextView((TextView)this.findViewById(R.id.vv_output));
    }
 
    private void openParseAct(){
    	getIdFromEditText();
    	Intent intent = new Intent(getApplicationContext(), Parser.class);
		intent.putExtra("inputString", name);
		startActivityForResult(intent, REQUEST_CODE);
    }
    
    
    private void getIdFromEditText()
    {
    	if(nameEt.getText().toString().length() > 0){
	    	name = nameEt.getText().toString();
	    }
	    else{
	    	name = noteSp.getSelectedItem().toString()+" "+chordSp.getSelectedItem().toString();
	   	}
    }
 
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE == requestCode) {
            String response = data.getStringExtra(Parser.RESPONSE);
            resp = response;
            if (response.startsWith("-1")){
        		AlertDialog alert = new AlertDialog.Builder(ChordBase.this)
                .setTitle(R.string.alert_wrong_chord_title)
                .setPositiveButton(R.string.alert_wrong_chord_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        nameEt.setText("");
                    }
                })
                .setMessage("'"+name+"' nie jest poprawn¹ nazw¹ chwytu!\n"+this.getString(R.string.alert_wrong_chord_msg))
        		.create();
        		alert.show();
        	}
            Chord n = new Chord(resp);
            showChord(n);
        }
    }

    private void showChord(Chord in){
    	variantLay.setVisibility(LinearLayout.VISIBLE);
    	Vview.setChord(in);
    	chordnames.setText(in.getChordNames());
    	ArrayList<CharSequence> data = new ArrayList<CharSequence>();
    	for(int i=0; i<in.getVariantCount();i++){
    		String tmp = Integer.toString(i+1);
    		data.add(tmp);
    	}
    	ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item,data);
    	variantSp.setAdapter(adapter);
    	variantSp.setOnItemSelectedListener(new OnItemSelectedListener(){

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            	Vview.update();
            	Vview.setVariant(pos);
            }

            public void onNothingSelected(AdapterView<?> parent) {
              // Do nothing.
            }
        });
    }
    // do usuniêcia
	@SuppressWarnings("unused")
	private void showResponse(String s){
    	if (s.startsWith("-1")){
    		AlertDialog alert = new AlertDialog.Builder(ChordBase.this)
            .setTitle(R.string.alert_wrong_chord_title)
            .setPositiveButton(R.string.alert_wrong_chord_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    nameEt.setText("");
                }
            })
            .setMessage(R.string.alert_wrong_chord_msg)
    		.create();
    		alert.show();
    	}
    	else{
    		AlertDialog alert = new AlertDialog.Builder(ChordBase.this)
            .setTitle(R.string.alert_wrong_chord_title)
            .setPositiveButton(R.string.alert_wrong_chord_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    nameEt.setText("");
                }
            })
            .setMessage(s)
    		.create();
    		alert.show();
    		
    	}
    }

    class Chord{
    	private String note_name;
    	private String chord_names;
    	private String variants_temporary;
    	private int variant_count;
    	ArrayList<Variant> variants;
    	
    	//Konstruktor
    	public Chord(String input){
    		if (input.startsWith("-1")){
    			note_name = "Wrong";
    			chord_names = "Wrong name";
    			variants_temporary = "0:0:0:0:0:0:0:0:0:0:0:0:0:#";
    		}
    		else{
	    		StringTokenizer st = new StringTokenizer(input,";");
	    		note_name = st.nextToken();
	    		chord_names = st.nextToken();
	    		variants_temporary = st.nextToken();
    		}
    		variant_count = 0;
    		variants = new ArrayList<Variant>();
    		separateVariants();
    	}
    	//Metody publiczne do pozyskiwania danych
    	public int getVariantCount(){		
    		return variant_count;}
    	public String getVariantString(){	
    		return variants_temporary;}
    	public String getChordNames(){		
    		return chord_names;}
    	public String getNoteName(){		
    		return note_name;}
    	
    	//Metoda wewnêtrzna klasy tworz¹ca listê dostêpnych wariantów danego chwytu 
    	private void separateVariants(){
    		StringTokenizer st = new StringTokenizer(this.variants_temporary,"#");
    		while(st.hasMoreElements()){
    			variant_count++;
        		variants.add(new Variant(st.nextToken()));
        	}
    	}
    }

    class Variant {
    	private int position;
    	private int[] frets;
    	private int[] fingers;
    	
    	//Konstruktor
    	public Variant(String input){
    		frets = new int[6];
    		fingers = new int[6];
    		StringTokenizer st = new StringTokenizer(input,":");
    		position = Integer.valueOf(st.nextToken());
    		int tmp = 0;
    		while (st.hasMoreTokens()){
    			frets[tmp] = Integer.valueOf(st.nextToken());
    			fingers[tmp] = Integer.valueOf(st.nextToken());
    			tmp++;
    		}
    	}
    	
    	//Metody do pozyskania danych
    	public int getPosition(){
    		return position;}
    	public int[] getFrets(){
    		return frets;}
    	public int[] getFingers(){
    		return fingers;}
    }
}