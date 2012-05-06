package com.guitartool.chordbase;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.guitartool.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

@SuppressWarnings("unused")
public class Parser extends Activity {
	private static final int REQUEST_CODE = 111;
	public static final String RESPONSE = "Response";
	private String inputString;
	private int note,chord,offset;
	ArrayList <String> tokens;
	ArrayList <Integer> chordInt;
	StringTokenizer st;
	boolean split, noWsp;   	//parametry dotycz¹ce budowy Stinga wejœciowego.
								// split - true oznacza ze nuta oddzielona jest od znaku '#' lub 'b'
								// noWsp - true oznacza, ze nuta nie jest oddzielona od nazwy chwytu
	
	boolean noteFail;			// jeœli note = -1 nie ma sensu parsowac chwytu, bo i tak nie zostanie znaleziony w bazie
	

	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		inputString = getIntent().getStringExtra("inputString");
		note = -1;
		chord = -1;
		parse(inputString);
		Intent intent = new Intent(getApplicationContext(), RequestParser.class);
		intent.putExtra("note", note );
		intent.putExtra("chord", chord);
		startActivityForResult(intent, REQUEST_CODE);
	}
	
	private void prepareResponse(String rtrn) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESPONSE, rtrn);
        setResult(RESULT_OK, resultIntent);
    }
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE == requestCode) {
            String response = data.getStringExtra(RequestParser.RESPONSE);
            prepareResponse(response);
            finish();
        }
    }
	
	private void parse(String in){
		tokens = new ArrayList<String>();
		chordInt = new ArrayList<Integer>();
		st = new StringTokenizer(in);
		while (st.hasMoreTokens()){
			tokens.add(st.nextToken());
		}
		NoteCheck();
		ChordCheck(noteFail);
			
	}
	private void NoteCheck(){
		note = -1;
		if(tokens.size() > 0){
			
			if (tokens.get(0).equalsIgnoreCase("a")){
				if (tokens.size()>1){
					if ((tokens.get(1).equalsIgnoreCase("b")) || (tokens.get(1).equalsIgnoreCase("es")) ){
						note = 11;
						split = true;
					} else if ((tokens.get(1).equalsIgnoreCase("#")) || (tokens.get(1).equalsIgnoreCase("is")) ){
						note = 1;
						split = true;
					} else note = 0;
				} else note = 0;
			} else if (tokens.get(0).equalsIgnoreCase("b")){
				if (tokens.size()>1){
					if ((tokens.get(1).equalsIgnoreCase("b")) || (tokens.get(1).equalsIgnoreCase("es")) ){
						note = 1;
						split = true;
					} else if ((tokens.get(1).equalsIgnoreCase("#")) || (tokens.get(1).equalsIgnoreCase("is")) ){
						note = -1;
						split = true;
					} else note = 2;
				} else note = 2;
			} else if (tokens.get(0).equalsIgnoreCase("c")){
				if (tokens.size()>1){
					if ((tokens.get(1).equalsIgnoreCase("b")) || (tokens.get(1).equalsIgnoreCase("es")) ){
						note = -1;
						split = true;
					} else if ((tokens.get(1).equalsIgnoreCase("#")) || (tokens.get(1).equalsIgnoreCase("is")) ){
						note = 4;
						split = true;
					} else note = 3;
				} else note = 3;
			} else if (tokens.get(0).equalsIgnoreCase("d")){
				if (tokens.size()>1){
					if ((tokens.get(1).equalsIgnoreCase("b")) || (tokens.get(1).equalsIgnoreCase("es")) ){
						note = 4;
						split = true;
					} else if ((tokens.get(1).equalsIgnoreCase("#")) || (tokens.get(1).equalsIgnoreCase("is")) ){
						note = 6;
						split = true;
					} else note = 5;
				} else note = 5;
			} else if (tokens.get(0).equalsIgnoreCase("e")){
				if (tokens.size()>1){
					if ((tokens.get(1).equalsIgnoreCase("b")) || (tokens.get(1).equalsIgnoreCase("es")) ){
						note = 6;
						split = true;
					} else if ((tokens.get(1).equalsIgnoreCase("#")) || (tokens.get(1).equalsIgnoreCase("is")) ){
						note = -1;
						split = true;
					} else note = 7;
				} else note = 7;
			} else if (tokens.get(0).equalsIgnoreCase("f")){
				if (tokens.size()>1){
					if ((tokens.get(1).equalsIgnoreCase("b")) || (tokens.get(1).equalsIgnoreCase("es")) ){
						note = -1;
						split = true;
					} else if ((tokens.get(1).equalsIgnoreCase("#")) || (tokens.get(1).equalsIgnoreCase("is")) ){
						note = 9;
						split = true;
					} else note = 8;
				} else note = 8;
			} else if (tokens.get(0).equalsIgnoreCase("g")){
				if (tokens.size()>1){
					if ((tokens.get(1).equalsIgnoreCase("b")) || (tokens.get(1).equalsIgnoreCase("es")) ){
						note = 9;
						split = true;
					} else if ((tokens.get(1).equalsIgnoreCase("#")) || (tokens.get(1).equalsIgnoreCase("is")) ){
						note = 11;
						split = true;
					} else note = 10;
				} else note = 10;
				
			} else if (tokens.get(0).equalsIgnoreCase("a#") ||
					   tokens.get(0).equalsIgnoreCase("ais")|| 
					   tokens.get(0).equalsIgnoreCase("bb") || 
					   tokens.get(0).equalsIgnoreCase("bes")){
				note = 1;
			} else if (tokens.get(0).equalsIgnoreCase("c#") ||
					   tokens.get(0).equalsIgnoreCase("cis")|| 
					   tokens.get(0).equalsIgnoreCase("db") || 
					   tokens.get(0).equalsIgnoreCase("des")){
				note = 4;
			} else if (tokens.get(0).equalsIgnoreCase("d#") ||
					   tokens.get(0).equalsIgnoreCase("dis")|| 
					   tokens.get(0).equalsIgnoreCase("eb") || 
					   tokens.get(0).equalsIgnoreCase("es") ){
				note = 6;
			} else if (tokens.get(0).equalsIgnoreCase("f#") ||
					   tokens.get(0).equalsIgnoreCase("fis")|| 
					   tokens.get(0).equalsIgnoreCase("gb") || 
					   tokens.get(0).equalsIgnoreCase("ges")){
				note = 9;
			} else if (tokens.get(0).equalsIgnoreCase("g#") ||
					   tokens.get(0).equalsIgnoreCase("gis")|| 
					   tokens.get(0).equalsIgnoreCase("ab") || 
					   tokens.get(0).equalsIgnoreCase("as") ){
				note = 11;
				
			} else if (tokens.get(0).toLowerCase().startsWith("a")){
				
						if (tokens.get(0).startsWith("a#")||tokens.get(0).startsWith("A#")){
							note = 1;
							offset = 2;
						}else if(tokens.get(0).toLowerCase().startsWith("ais")){
							note = 1;
							offset = 3;
						}else if (tokens.get(0).toLowerCase().startsWith("ab")||tokens.get(0).toLowerCase().startsWith("as")){
							note = 11;
							offset = 2;
						}else {
							note = 0;
							offset = 1;
				}
				noWsp = true;
			} else if (tokens.get(0).toLowerCase().startsWith("b")){
				
						if (tokens.get(0).startsWith("b#")||tokens.get(0).startsWith("B#")){
							note = -1;
							offset = 2;
						}else if(tokens.get(0).toLowerCase().startsWith("bis")){
							note = -1;
							offset = 3;
						}else if (tokens.get(0).toLowerCase().startsWith("bb")){
							note = 1;
							offset = 2;
						}else if (tokens.get(0).toLowerCase().startsWith("bes")){
							note = 1;
							offset = 3;
						}else {
							note = 2;
							offset = 1;
				}
				noWsp = true;
			} else if (tokens.get(0).toLowerCase().startsWith("c")){
				
						if (tokens.get(0).startsWith("c#")||tokens.get(0).startsWith("C#")){
							note = 4;
							offset = 2;
						}else if(tokens.get(0).toLowerCase().startsWith("cis")){
							note = 4;
							offset = 3;
						}else if (tokens.get(0).toLowerCase().startsWith("cb")){
							note = -1;
							offset = 2;
						}else if (tokens.get(0).toLowerCase().startsWith("ces")){
							note = -1;
							offset = 3;
						}else{
							note = 3;
							offset = 1;
						}
				noWsp = true;
			} else if (tokens.get(0).toLowerCase().startsWith("d")){
						
						if (tokens.get(0).startsWith("d#")||tokens.get(0).startsWith("D#")){
							note = 6;
							offset = 2;
						}else if(tokens.get(0).toLowerCase().startsWith("dis")){
							note = 6;
							offset = 3;
						}else if (tokens.get(0).toLowerCase().startsWith("db")){
							note = 4;
							offset = 2;
						}else if (tokens.get(0).toLowerCase().startsWith("des")){
							note = 4;
							offset = 3;
						}else {
							note = 5;
							offset = 1;
						}
				noWsp = true;
			} else if (tokens.get(0).toLowerCase().startsWith("e")){
						
						if (tokens.get(0).startsWith("e#")||tokens.get(0).startsWith("E#")){
							note = -1;
							offset = 2;
						}else if(tokens.get(0).toLowerCase().startsWith("eis")){
							note = -1;
							offset = 3;
						}else if (tokens.get(0).toLowerCase().startsWith("eb")){
							note = 6;
							offset = 2;
						}else if (tokens.get(0).toLowerCase().startsWith("es")){
							note = 6;
							offset = 2;
						}else{
							note = 7;
							offset = 1;
						}
				noWsp = true;
			} else if (tokens.get(0).toLowerCase().startsWith("f")){
						if (tokens.get(0).startsWith("f#")||tokens.get(0).startsWith("F#")){
							note = 9;
							offset = 2;
						}else if(tokens.get(0).toLowerCase().startsWith("fis")){
							note = 9;
							offset = 3;
						}else if (tokens.get(0).toLowerCase().startsWith("fb")){
							note = -1;
							offset = 2;
						}else if (tokens.get(0).toLowerCase().startsWith("fes")){
							note = -1;
							offset = 3;
						}else{
							note = 8;
							offset = 1;
						}
				noWsp = true;
			} else if (tokens.get(0).toLowerCase().startsWith("g")){
				
						if (tokens.get(0).startsWith("g#")||tokens.get(0).startsWith("G#")){
							note = 11;
							offset = 2;
						}else if(tokens.get(0).toLowerCase().startsWith("gis")){
							note = 11;
							offset = 3;
						}else if (tokens.get(0).toLowerCase().startsWith("gb")){
							note = 9;
							offset = 2;
						}else if (tokens.get(0).toLowerCase().startsWith("ges")){
							note = 9;
							offset = 3;
						}else{
							note = 10;
							offset = 1;
						}
				noWsp = true;
			} 
		}
		if (note == -1){
			noteFail = true;
			chord = -1;
		}
	}//eof NoteCheck

	private void ChordCheck(boolean noteFailure){
		if (noteFailure){
			chord = -1;
		}else{
			if(noWsp){
				String tmp = tokens.get(0).substring(offset);
				tokens.remove(0);
				tokens.add(0, tmp);
				ChordParse();
			}else {
				if(split){
					tokens.remove(0);
					tokens.remove(0);
					ChordParse();
				}
				else{
					tokens.remove(0);
					ChordParse();
				}
			}
		}
	}//eof ChordCheck
	
	private void ChordParse(){
		ChordAutomat ca = new ChordAutomat();
		switch (tokens.size()){
		case 0:
			chord = 0;
			break;
		case 1:
			ca.nextState(tokenToChord(tokens.get(0)));
			chord = ca.getState();
			break;
		default :
			chord = 0;
			int tokensSize = tokens.size();
			for(int i = 0; i<tokensSize;i++){
				chordInt.add(tokenToChord(tokens.get(i)));
			}
			for(int i = 0; i < chordInt.size(); i++){
				ca.nextState(chordInt.get(i));
				System.out.println("Token:\t"+chordInt.get(i)+"\tStan:\t"+ca.printState());
			}
			chord = ca.getState();
			
			break;
		}
		if(chord == -1){
			note = -1;
		}
	}//eof ChordParse

	private int tokenToChord(String in){
		String tmp = in.toLowerCase();
		int ch = -1;
		boolean mol;
		
		// '+'
		if(tmp.startsWith("+")){
			ch = 100;				
			offset = 1;
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				switch(tokenToChord(tmp)){
				case 128:	//+5
					ch = 101;
					break;
				case 129:		//+ 57
					ch = 10;
					break;
				case 131:		//+ 5-
					ch = 102;
					break;
				case 133:		//+ 5-9
					ch = 17;
					break;
				case 140:		//+ 7
					ch = 105;
					break;
				case 141:	//+ 79
					ch = 106;
					break;
				case 142:	//+ 713
					ch = 23;
					break;
				case 149:	// +9
					ch = 108;
					break;
				case 159: 	// +11
					ch = 20;
					break;
				default:
					ch = -1;
					break;
				}
			}
			
		// '-'
		}else if(tmp.startsWith("-")){
			ch = 110;
			offset = 1;
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				switch(tokenToChord(tmp)){
				case 128:		//- 5
					ch = 111;
					break;
				case 129:		//- 57
					ch = 112;
					break;
				case 130:		//- 59
					ch = 20;
					break;
				case 131:		//- 5-
					ch = 113;
					break;
				case 132:		//- 5-7
					ch = 116;
					break;
				case 133:		//- 5-9
					ch = 16;
					break;
				case 140:		//-7
					ch = 117;
					break;
				case 145:		//- 7-
					ch = 118;
					break;
				case 146:		//- 7-5
					ch = 119;
					break;
				case 149:		// -9
					ch = 120;
					break;
				case 152:	//- 913
					ch = 22;
					break;
				case 153:	//- 9-
					ch = 121;
					break;
				case 154:	//- 9-5
					ch = 16;
					break;
				case 155:	//- 9+
					ch = 123;
					break;
				case 156:	//- 9+5
					ch = 17;
					break;
				default:
					ch = -1;
					break;
				}
			}
		//11 i 13
		}else if(tmp.startsWith("1")){
			ch = -1;
			if(tmp.startsWith("11")){			//11
				ch = 159;
			}else if(tmp.startsWith("13")){		//13	
				ch = 160;
				offset =2;
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					switch(tokenToChord(tmp)){
					case 110:		//13 -
						ch = 161;
						break;
					case 120: 		//13 -9
						ch = 162;
						break;
					case 100:		//13 +
						ch = 163;
						break;
					case 105:		//13 +7
						ch = 164;
						break;
					default:
						ch = -1;
						break;
					}
				}
			}	
		}else if(tmp.startsWith("2")){			//2
			ch = 126;	
		}else if(tmp.startsWith("4")){			//4
			ch = 127;
		}else if(tmp.startsWith("5")){  		//5 
			ch = 128;
			offset = 1;
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				switch(tokenToChord(tmp)){
				case 140:		//5 7
					ch = 129;
					break;
				case 149:		//5 9
					ch = 130;
					break;
				case 110:		//5 -
					ch = 131;
					break;
				case 117:		//5 -7
					ch = 132;	
					break;
				case 120:		//5 -9
					ch = 133;
					break;
				default:
					ch = -1;
					break;
				}
			}
		}else if(tmp.startsWith("6")){			// 6
			ch = 134;
			offset = 1;
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				switch(tokenToChord(tmp)){
				case 149:
					ch = 135;	// 6 9
					break;
				case 185:		// 6 (
					ch = 136;
					break;
				case 186:		// 6 (add
					ch = 137;
					break;
				case 187:		// 6 (add9
					ch = 138;
					break;
				case 188:		// 6 (add9)
					ch = 139;
					break;
				default:
					ch = -1;
					break;
				}
			}
		}else if(tmp.startsWith("7")){			// 7
			ch = 140;
			offset = 1;
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				switch(tokenToChord(tmp)){
				case 149:		//7 9
					ch = 141;
					break;
				case 160:		//7 13
					ch = 142;
					break;
				case 236:		//7 )
					ch = 143;
					break;
				case 158:		//7 9)
					ch = 144;
					break;
				case 110:		//7 -
					ch = 145;
					break;
				case 111:		//7 -5
					ch = 146;
					break;
				case 100:		//7 +
					ch = 147;
					break;
				case 101:	 	//7 +5
					ch = 148;
					break;
				default:		//inne
					ch = -1;
					break;
				}
			}
		}else if(tmp.startsWith("9")){			// 9
			ch = 149;
			offset = 1;
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				switch(tokenToChord(tmp)){
				case 134:		//9 6
					ch = 150;
					break;
				case 140:		//9 7
					ch = 151;
					break;
				case 160:		//9 13
					ch = 152;
					break;
				case 110:		//9 -
					ch = 153;
					break;
				case 111:		//9 -5
					ch = 154;
					break;
				case 100:		//9 +
					ch = 155;
					break;
				case 101:		//9 +5
					ch = 156;
					break;
				case 105:		//9 +7
					ch = 157;
					break;
				case 236:		//9 )
					ch = 158;
					break;
				default:		//inne
					ch = -1;
					break;
				}
			}
			
		// susy
		}else if(tmp.startsWith("s")){
			if(tmp.startsWith("sus")){
				ch = 165;					// sus
				offset = 3;
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					switch(tokenToChord(tmp)){
					case 126:		// sus2
						ch = 3;
						break;
					case 127:		// sus4
						ch = 4;
						break;	
					case 140:		// sus7
						ch = 5;
						break;
					case 149:		// sus9
						ch = 6;
						break;
					default:
						ch = -1;
						break;
					}
				}
			}else if(tmp.startsWith("sekund¹") || tmp.startsWith("sekunda")){
				ch = 309;
				offset = 7;
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					ch = -1;
				}
			}else if(tmp.startsWith("septym¹")){
				ch = 318;
				offset = 7;
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					ch = -1;
				}
			}else if(tmp.startsWith("septymowy")){
				ch = 313;
				offset = 9;
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					ch = -1;
				}
			}else if(tmp.startsWith("sekst¹")){
				ch = 322;
				offset = 6;
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					ch = -1;
				}
			}else if(tmp.startsWith("sekstowy")){
				ch = 323;
				offset = 8;
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					ch = -1;
				}
			}else if(tmp.startsWith("sekstowa")){
				ch = 324;
				offset = 8;
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					ch = -1;
				}
			}
			
		//wszystko na 'd'
			
		}else if(tmp.startsWith("d")){
			ch = -1;
			if(tmp.startsWith("dur")){				//dur
				ch =  0;
				offset = 3;
				if(tmp.startsWith("durowy")) offset = 6;
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					switch(tokenToChord(tmp)){
					default:
						ch = -1;
						break;
					}
				} 	
			}else if (tmp.startsWith("dim")){		//dim
				ch = 235;
				if(tmp.startsWith("diminished")){
					ch = 235;
				}
			}else if(tmp.startsWith("dom")){		//dominant
				ch = 237;
				offset = 3;
				if (tmp.startsWith("dominant")){
					offset = 8;
					if (tmp.startsWith("dominantowy") || tmp.startsWith("dominantowe")){
						offset = 11;
					}
				}
				tmp = tmp.substring(offset);
				
				if (tmp.length()>0){
					switch(tokenToChord(tmp)){
					case 134:		//dom 6
						ch = 238;
						break;
					case 140:		//dom 7
						ch = 8;
						break;
					case 149:		//dom 9
						ch = 12;
						break;
					case 159:		//dom 11
						ch = 6;
						break;
					case 160:		//dom 13
						ch = 242;
						break;
					default :
						ch = -1;
						break;
					}
				}
			}else if (tmp.startsWith("dodan¹")){
				ch = 308;
				offset = 6;
				tmp = tmp.substring(offset);
				if(tmp.length()>0){
					ch = -1;
				}
			}
		
		//literka 'a'
			
		}else if(tmp.startsWith("a")){
			if(tmp.startsWith("add")){				//add
				ch = 174;
				offset = 3;
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					switch(tokenToChord(tmp)){
					case 149:	//add 9
						ch = 175;
						break;
					case 158:	//add 9)
						ch = 176;
						break;
					default:
						ch = -1;
						break;
					}
				}
			}else if(tmp.startsWith("aug")){		//aug
				ch = 1;
				offset = 3;
				if (tmp.startsWith("augmented")){
					offset = 9;
				}
			}
		
			
		}else if (tmp.startsWith("z")){
			ch = 301;
			offset = 1;
			if (tmp.compareTo("ze") == 0){
				ch = 302;
				offset = 2;
			}
			if(tmp.startsWith("zm")){
				ch = 2;
				offset = 2;
				if(tmp.startsWith("zmniejszony") || 
				   tmp.startsWith("zmniejszona") || 
				   tmp.startsWith("zmniejszone")){
						
					offset = 11;
				}else if(tmp.compareToIgnoreCase("zmniejszon¹") == 0){
					ch = 304;
					offset = 11;
				}
				
			}else if(tmp.startsWith("zw")){
				ch = 1;
				offset = 2;
				if(tmp.startsWith("zwiêkszony") || 
				   tmp.startsWith("zwiêkszony") || 
				   tmp.startsWith("zwiêkszone")){
						
					offset = 10;
				}else if(tmp.compareToIgnoreCase("zwiêkszon¹") == 0){
					ch = 303;
					offset = 10;
				}
				
			}else if (tmp.startsWith("zamiast")){
				ch = 311;
				offset = 7;
			}
			tmp = tmp.substring(offset);
			if(tmp.length()>0){
				ch = -1;
			}
	
		}else if (tmp.startsWith("(")){				//nawias otwieraj¹cy
			ch = 185;
			offset = 1;
			tmp = tmp.substring(offset);
			if(tmp.length()>0){
				switch (tokenToChord(tmp)){
				case 174: 		//( add
					ch = 186;
					break;
				case 175:		//( add9
					ch = 187;
					break;
				case 176:		//( add9)
					ch = 188;
					break;
				case 177:		//( maj
					ch = 189;
					break;
				case 181:		//( maj)
					ch = 190;
					break;
				case 178:		//( maj7
					ch = 191;
					break;
				case 182:		//( maj7)
					ch = 192;
					break;
				case 180:		//( maj79
					ch = 193;
					break;
				case 184:		//( maj79)
					ch = 194;
					break;
				case 179:		//( maj9
					ch = 195;
					break;
				case 183:		//( maj9)
					ch = 196;
					break;
				default:
					ch = -1;
					break;
				}
			}	
			
		}else if(tmp.startsWith("m")){				//zaczynaj¹ce siê na 'm'
			mol = true;
			ch = 197;
			offset = 1;
			if(tmp.startsWith("mol")){
				offset = 3;
				if(tmp.startsWith("molowy")){
					offset = 6;
				}
			}else if(tmp.startsWith("min")){
				offset = 3;
				if(tmp.startsWith("minor")){
					offset = 5;
				}else if(tmp.startsWith("minorowy")){
					offset = 8;
				}else if(tmp.startsWith("minus")){
					ch = 306;
					offset = 5;
					tmp = tmp.substring(offset);
					if(tmp.length()>0) {
						ch = -1;
					}
				}
			}else if(tmp.startsWith("maj")){
				ch = 177;
				offset = 3;
				mol = false;
				if(tmp.startsWith("major")){
					offset = 5;
					if(tmp.startsWith("majorowy")){
						offset = 8;
					}
				}
				tmp = tmp.substring(offset);
				if (tmp.length()>0){
					switch(tokenToChord(tmp)){
					case 140:	//maj 7
						ch = 178;
						break;
					case 149:	//maj 9
						ch = 179;
						break;
					case 141:	//maj 79
						ch = 180;
						break;
					case 236:	//maj)
						ch = 181;
						break;
					case 143:	//maj 7)
						ch = 182;
						break;
					case 158:	//maj 9)
						ch = 183;
						break;
					case 144:	//maj 79)
						ch = 184;
						break;
					case 160:	//maj 13
						ch = 23;
						break;
					case 142:	//maj 713
						ch = 23;
						break;
					default:
						ch = -1;
						break;
					}
				}
			}
			if(mol){
				tmp = tmp.substring(offset); 		//m i co dalej
				if (tmp.length()>0){
					switch(tokenToChord(tmp)){
					case 110:		//m -
						ch = 198;
						break;
					case 111:		//m -5
						ch = 199;
						break;
					case 112:		//m -57
						ch = 26;
						break;
					case 113:		//m -5-
						ch = 201;
						break;
					case 116:		//m -5-7
						ch = 27;
						break;
					case 117:		//m -7
						ch = 203;
						break;
					case 118:		//m -7-
						ch = 204;
						break;
					case 119:		//m -7-5
						ch = 27;
						break;
					case 100:		//m +
						ch = 206;
						break;
					case 105:		//m +7
						ch = 207;
						break;
					case 108:		//m +9
						ch = 33;
						break;
					case 106:		//m +79
						ch = 34;
						break;
					case 134:		//m 6
						ch = 210;
						break;
					case 135:		//m 69
						ch = 32;
						break;
					case 136:		//m 6(
						ch = 212;
						break;
					case 137:		//m 6(add
						ch = 213;
						break;
					case 138:		//m 6(add9
						ch = 214;
						break;
					case 139:		//m 6(add9)
						ch = 32;
						break;
					case 140:		//m 7
						ch = 216;
						break;
					case 145:		//m 7-
						ch = 217;
						break;
					case 146:		//m 7-5
						ch = 26;
						break;
					case 141:		//m 79
						ch = 31;
						break;
					case 149:		//m 9
						ch = 220;
						break;
					case 150:		//m 96
						ch = 32;
						break;
					case 151:		//m 97
						ch = 32;
						break;
					case 155:		//m 9+
						ch = 223;
						break;
					case 157:		//m 9+7
						ch = 34;
						break;
					case 159:		//m 11
						ch = 35;
						break;
					case 185:		//m (
						ch = 226;
						break;
					case 189:		//m (maj
						ch = 227;
						break;
					case 191:		//m (maj7
						ch = 228;
						break;
					case 195:		//m (maj9
						ch = 229;
						break;
					case 193:		//m (maj79
						ch = 230;
						break;
					case 190:		//m (maj)
						ch = 30;
						break;
					case 192:		//m (maj7)
						ch = 30;
						break;
					case 196:		//m (maj9)
						ch = 34;
						break;
					case 194:		//m (maj79)
						ch = 34;
						break;
					default:
						ch = -1;
						break;
					
					}
				}	
			}		
		
		}else if (tmp.startsWith(")")){
			ch = 236;
			offset = 1;
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				ch = -1;
			}
			
			
		}else if (tmp.startsWith("i")){
			ch = 321;
			offset = 1;
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				ch = -1;
			}
			
			
		}else if (tmp.startsWith("k")){
			offset = 1;
			ch = -1;
			if (tmp.startsWith("kwart¹") || tmp.startsWith("kwarta")){
				ch = 310;
				offset = 6;
			}else if (tmp.startsWith("kwint¹") || tmp.startsWith("kwinta")){
				ch = 317;
				offset = 6;
			}
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				ch = -1;
			}
	
			
			
		}else if (tmp.startsWith("n")){
			offset = 1;
			ch = -1;
			if (tmp.startsWith("non¹")){
				ch = 319;
				offset = 4;
			}else if (tmp.startsWith("nonowy")){
				ch = 314;
				offset = 6;
			}
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				ch = -1;
			}
			
		
		
		}else if (tmp.startsWith("p")){
			offset = 1;
			ch = -1;
			if (tmp.startsWith("plus")){
				ch = 305;
				offset = 4;
			}
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				ch = -1;
			}
			
		
		
		}else if (tmp.startsWith("t")){
			offset = 1;
			ch = -1;
			if (tmp.startsWith("tercji")){
				ch = 312;
				offset = 6;
			}else if (tmp.startsWith("terdecymowy")){
				ch = 316;
				offset = 11;
			}else if (tmp.startsWith("terdecym¹")){
				ch = 325;
				offset = 9;
			}
			tmp = tmp.substring(offset);
			if (tmp.length()>0){
				ch = -1;
			}
		}
		
		
		return ch;
	}//eof tokenToChord

	private class ChordAutomat{
		private int state;
		public ChordAutomat(){
			state = -2;
		}
		public int printState(){
			return state;
		}
		public int getState(){
			switch(state){
			case 100:	return 1;	//+
			case 101:	return 1;	//+5
			case 105:	return 11;	//+7
			case 106:	return 19;	//+79
			case 108:	return 18;	//+9
			case 110:	return 2;	//-
			case 111:	return 2;	//-5
			case 112:	return 9;	//-57
			case 120:	return 15;	//-9
			case 126:	return 3;	//2
			case 134:	return 7;	//6
			case 135:	return 14;	//69
			case 139:	return 14;	//6(add9)
			case 140:	return 8;	//7
			case 146:	return 9;	//7-5
			case 148:	return 10;	//7+5	
			case 149:	return 12;	//9
			case 150:	return 14;	//96
			case 154:	return 20;	//9-5
			case 157:	return 19;	//9+7
			case 159:	return 6;	//11
			case 160:	return 21;	//13
			case 162:	return 22;	//13-9
			case 164:	return 23;	//13+7
			case 165:	return 4;	//sus
			case 175:	return 13;	//add9
			case 177:	return 11;	//maj
			case 178:	return 11;	//maj7
			case 179:	return 19;	//maj9
			case 180:	return 19;	//maj79
			case 197:	return 24;	//mol
			case 198:	return 25;	//m-
			case 199:	return 25;	//m-5
			case 207:	return 30;	//m+7
			case 210:	return 28;	//mol6
			case 216:	return 29;	//mol7
			case 220:	return 31;  //mol9
			case 235:	return 27;	//dim
			case 238:	return 7;	//dom6
			case 239:	return 8;	//dom7
			case 240:	return 12;	//dom9
			case 241:	return 6;	//dom11
			case 242:	return 21;	//dom13
			case 401:	return 1;	//ze zwiêkszon¹ kwint¹
			case 510:	return 2;	//ze zmniejszon¹ kwint¹
			case 520:	return 15;	//ze zmniejszon¹ non¹
			case 600:	return 25;	//zmniejszone molowy
			case 715:	return 25;	//molowy ze zmniejszon¹ kwint¹
			default:
				if(state >35)return -1;
				else return state;
			}
		}
		public void nextState(int token){
			if(state != -1){
				switch (state){
				case -2:	//stan pocz¹tkowy
					state = token;
					break;
//*******************************************************************					
				case 0:		// dur		nie wiadomo na razie dokoñczyæ póŸniej
					switch(token){
					case -1:
						state = -1;
						break;
					case 0: //dur dur - Ÿle
						state = -1;
						break;
					default:
						state = -1;
						break;
					}
					break;

//*******************************************************************
				case 1:							//zwiêkszony
					switch(token){
					case 301:	//z
						state = 450;
						break;
					case 302:	//ze
						state = 451;
						break;
					default:
						state  = -1;
						break;
					}
					break;
//*******************************************************************
				case 2:								//zmniejszony
					switch(token){
					case 197:	//molowy
						state = 600;
						break;
					case 301:	//z
						state = 601;
						break;
					case 302:	//ze
						state = 602;
						break;
					default:
						state = -1;
						break;
					}break;
//*******************************************************************
				case 100:							// +
					switch(token){
					case 128:		// + 5
						state = 101;
						break;
					case 140:		// + 7
						state = 105;
						break;
					case 149:		// + 9
						state = 18;
						break;
					case 159:		// + 11
						state = 20;
						break;
					case 129:		// + 57
						state = 10;
						break;
					case 131:		// + 5-
						state = 102;
						break;
					case 133:		// + 5-9
						state = 17;
						break;
					case 141:		// + 113
						state = 19;
						break;
					case 142:		// + 713
						state = 23;
						break;
					default :
						state = -1;
						break;
					}
					break;
//*******************************************************************					
				case 101:							// +5
					switch(token){
					case 140: 		//+5 7
						state = 10;
						break;
					case 110: 		//+5 -
						state = 102;
						break;
					case 120: 		//+5 -9
						state = 17;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 102:							// + 5 -
					state = -1;
					if (token == 149){
						state = 17;
					}
					break;
//*******************************************************************
				case 105:							// +7
					switch(token){
					case 149:		//+7 9
						state = 19;
						break;
					case 160:		//+7 13
						state = 23;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 110:							// -
					switch (token){
					case 128:		//- 5
						state = 111;
						break;
					case 149:		//- 9
						state = 120;
						break;
					case 129:		//- 57
						state = 112;
						break;
					case 131:		//- 5-
						state = 113;
						break;
					case 130:		//- 59
						state = 20;
						break;
					case 152:		//- 913
						state = 22;
						break;
					case 154:		//- 9-5
						state = 16;
						break;
					case 156:		//- 9+5
						state = 17;
						break;
					case 155:		//- 9+
						state = 123;
						break;
					case 153:		//- 9-
						state = 121;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************				
				case 111:		// -5
					switch (token){
					case 140:	//-5 7
						state = 9;
						break;
					case 110:	//-5 -
						state = 113;
						break;
					case 120:	//-5 -9
						state = 16;
						break;
					case 149:	//-5 9
						state = 20;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************				
				case 113:	//-5-
					if(token==149){
						state = 16;
					}else state = -1;
					break;
//*******************************************************************				
				case 120:	//-9
					switch(token){
					case 100:	//-9 +
						state = 123;
						break;
					case 110:	//-9 -
						state = 121;
						break;
					case 160:	//-9 13
						state = 22;
						break;
					case 111:		//-9 -5
						state = 16;
						break;
					case 101:		//-9 +5
						state = 17;
						break;
					}
					break;
//*******************************************************************				
				case 121:	//-9-
					if(token == 128){
						state = 16;
					}else state = -1;
					break;
//*******************************************************************
				case 123:	//-9+
					if(token == 128){
						state = 17;
					}else state = -1;
					break;
//*******************************************************************				
				case 134:	// 6
					switch(token){
					case 149:	//6 9
						state = 14;
						break;
					case 185:	// 6 (
						state = 136;
						break;
					case 186:	//6 (add
						state = 137;
						break;
					case 187:	//6 (add9
						state = 138;
						break;
					case 188:	//6 (add9)
						state = 14;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************				
				case 136:	//6(
					switch (token){
					case 174:	//6( add
						state = 137;
						break;
					case 175:	//6( add9
						state = 138;
						break;
					case 176:	//6( add9)
						state = 14;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************				
				case 137:	//6(add
					switch (token){
					case 149:	// 6(add 9
						state = 138;
						break;
					case 158:	// 6(add 9)
						state = 14;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************				
				case 138:	// 6(add9
					if (token == 236) state = 14;
					else state = -1;
					break;
//*******************************************************************				
				case 140:	// 7
					switch (token){
					case 100:	//7 +
						state = 147;
						break;
					case 101:		//7 +5
						state = 10;
						break;
					case 110:	//7 -
						state = 145;
						break;
					case 111:		//7 -5
						state = 9;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************				
				case 147:	// 7+
					if(token == 128)state = 10;
					else state = -1;
					break;
//*******************************************************************				
				case 145:	// 7-
					if(token == 128)state = 9;
					else state = -1;
					break;
//*******************************************************************				
				case 149:	// 9
					switch (token){
					case 134:	//9 6
						state = 14;
						break;
					case 100:	//9 +
						state = 155;
						break;
					case 110:	//9 -
						state = 153;
						break;
					case 111:		//9 -5
						state = 20;
						break;
					case 107:	//9 +7
						state = 19;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************				
				case 155:	//9+
					if(token == 140)state = 19;
					else state = -1;
					break;
//*******************************************************************			
				case 153:	//9-
					if (token == 128)state = 20;
					else state = -1;
					break;
//*******************************************************************				
				case 165:	// sus
					switch(token){
					case 202:	//sus 2
						state = 3;
						break;
					case 94:	// sus 4
						state = 4;
						break;
					case 140:	// sus 7
						state = 5;
						break;
					case 149:	// sus 9
						state = 6;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************				
				case 177:	//maj || major
					switch(token){
					case 140:	//maj 7
						state = 178;
						break;
					case 79:	//maj 79
						state = 19;
						break;
					case 713:	//maj 713
						state = 23;
						break;
					case 149:	//maj 9
						state = 19;
						break;
					case 160:	//maj 13
						state = 23;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************				
				case 178:	//maj7
					switch(token){
					case 149:	//maj7 9
						state = 19;
						break;
					case 160:	//maj7 13
						state = 23;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************				
				case 160:	// 13
					switch (token){
					case 15:	//13 -9
						state = 22;
						break;
					case 107:	//13 +7
						state = 23;
						break;
					case 100:	//13 +
						state = 131;
						break;
					case 110:	//13 -
						state = 132;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 161:	//13-
					if(token == 149) state = 22;
					else state = -1;
					break;
//*******************************************************************
				case 163:	//13+
					if(token == 140) state = 23;
					else state = -1;
					break;
//*******************************************************************
				case 174:	//add
					if(token == 149) state = 13;
					else state = -1;
					break;
//*******************************************************************				
				case 197:						// m 
					switch(token){
					case 2:			//zmniejszony
						state = 710;
						break;
					case 100:		//m +
						state = 206;
						break;
					case 105:		//m +7
						state = 207;
						break;
					case 108:		//m +9
						state = 33;
						break;
					case 110:		//m -
						state = 198;
						break;
					case 111:		//m -5
						state = 199;
						break;
					case 113:		//m -5-
						state = 201;
						break;
					case 116:		//m	-5-7
						state = 27;
						break;
					case 117:		//m -7
						state = 203;
						break;
					case 118:		//m -7-
						state = 204;
						break;
					case 119:		//m	-7-5
						state = 27;
						break;
					case 134:		//m 6
						state = 210;
						break;
					case 135:		//m 69 
						state = 32;
						break;
					case 136:		//m 6(
						state = 212;
						break;
					case 137:		//m 6(add
						state = 213;
						break;
					case 138:		//m 6(add9
						state = 214;
						break;
					case 139:		//m 6(add9)
						state = 32;
						break;
					case 140:		//m 7
						state = 29;
						break;
					case 141:		//m 79
						state = 31;
						break;
					case 149:		//m 9
						state = 220;
						break;
					case 150:		//m 96
						state = 32;
						break;
					case 151:		//m 97
						state = 31;
						break;
					case 159:		//m 11
						state = 35;
						break;
					case 185:		//m (
						state = 226;
						break;
					case 189:		//m (maj
						state = 227;
						break;
					case 190:		//m (maj)
						state = 30;
						break;
					case 191:		//m (maj7
						state = 228;
						break;
					case 192:		//m (maj7)
						state = 30;
						break;
					case 193:		//m	(maj79
						state = 229;
						break;
					case 194:		//m (maj79)
						state = 34;
						break;
					case 195:		//m	(maj9
						state = 229;
						break;
					case 196:		//m (maj9)
						state = 34;
						break;	
					case 301:		//m z
						state = 700;
						break;
					case 302:		//m ze
						state = 701;
						break;
					case 323:		//m sekstowy
						state = 702;
						break;
					case 313:		//m septymowy
						state = 703;
						break;
					case 314:		//m nonowy
						state = 704;
						break;
					case 315:		//m undecymowy
						state = 35;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 198:	//m-
					switch(token){
					case 128:	//m- 5
						state = 199;
						break;
					case 129:	//m- 57
						state = 26;
						break;
					case 131:	//m- 5-
						state = 201;
						break;
					case 132:	//m- 5-7
						state = 27;
						break;
					case 140:	//m- 7
						state = 203;
						break;
					case 145:	//m- 7-
						state = 204;
						break;
					case 146:	//m- 7-5
						state = 27;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 199:						//m-5
					switch(token){
					case 110:	//m-5 -
						state = 201;
						break;
					case 117:	//m-5 -7
						state = 27;
						break;
					case 140:	//m-5 7
						state = 26;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 201:	//m-5-
					if(token == 140) state = 27;
					else state = -1;
					break;
//*******************************************************************
				case 203:	//m-7
					switch(token){
					case 110:	//m-7 -
						state = 204;
						break;
					case 111:		//m-7 -5
						state = 27;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 204:	//m-7-
					if(token == 128) state = 27;
					else state = -1;
					break;
//*******************************************************************
				case 206:	//m+
					switch(token){
					case 140:	//m+ 7
						state = 817;
						break;
					case 141:	//m+ 79
						state = 34;
						break;
					case 149:	//m+ 9
						state = 33;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 207:	//m+7
					if (token == 149)state = 34;
					else state = -1;
					break;
//*******************************************************************
				case 210:	//m6
					switch(token){
					case 149:	//m6 9
						state = 32;
						break;
					case 185:	//m6 (
						state = 212;
						break;
					case 186:	//m6 (add
						state = 213;
						break;
					case 187:	//m6 (add9
						state = 214;
						break;
					case 188:	//m6 (add9)
						state = 32;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************					
				case 212:	//m6(
					switch(token){
					case 174:	//m6( add
						state = 213;
						break;
					case 175:	//m6( add9
						state = 214;
						break;
					case 176:	//m6( add9)
						state = 32;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 213:	//m6(add
					switch (token){
					case 149:	//m6(add 9
						state = 214;
						break;
					case 158:	//m6(add 9)
						state = 32;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************					
				case 214:	//m6(add9
					if(token == 236) state = 32;
					else state = -1;
					break;
//*******************************************************************
				case 216:	//m7
					switch(token){
					case 110:	//m7 -
						state = 217;
						break;
					case 111:		//m7 -5
						state = 26;
						break;
					case 149:	//m7 9
						state = 31;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************					
				case 217:	//m7-
					if(token == 128) state = 26;
					else state = -1;
					break;
//*******************************************************************
				case 220:	//m9
					switch(token){
					case 134:	//m9 6
						state = 32;
						break;
					case 140:	//m9 7
						state = 31;
						break;
					case 105:	//m9 +7
						state = 34;
						break;
					case 100:	//m9 +
						state = 223;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 223:	//m9+
					if(token == 140) state = 34;
					else state = -1;
					break;
//*******************************************************************
				case 226:	//m(
					switch(token){
					case 177:	//m( maj
						state = 227;
						break;
					case 178:	//m( maj7
						state = 228;
						break;
					case 179:	//m( maj9
						state = 229;
						break;
					case 180:	//m( maj79
						state = 229;
						break;
					case 181:	//m( maj)
						state = 30; 
						break;
					case 182:	//m( maj7)
						state = 30;
						break;
					case 184:	//m( maj79)
						state = 34;
						break;
					case 183:	//m( maj9)
						state = 34;
						break;
					
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************					
				case 227:	//m(maj
					switch(token){
					case 140:	//m(maj 7
						state = 228;
						break;
					case 143:	//m(maj 7)
						state = 30;
						break;
					case 141:	//m(maj 79
						state = 229;
						break;
					case 144:	//m(maj 79)
						state = 34;
						break;
					case 149:	//m(maj 9
						state = 229;
						break;
					case 122:	//m(maj 9)
						state = 34;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************
				case 228:	//m(maj7
					switch(token){
					case 236:	//m(maj7 )
						state = 30;
						break;
					case 149:	//m(maj7 9
						state = 229;
						break;
					case 158:	//m(maj7  9)
						state = 34;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************					
				case 229:	//m(maj9 ||m(maj79
					if(token == 236) state = 34;
					else state = -1;
					break;
//*******************************************************************
				case 237:	//dom
					switch(token){
					case 134:	//6
						state = 7;
						break;
					case 140:	//7
						state = 8;
						break;
					case 149:	//9
						state = 12;
						break;
					case 159:	//11
						state = 6;
						break;
					case 160:	//13
						state = 21;
						break;
					case 323:	//sekstowy
						state = 7;
						break;
					case 313:	//septymowy
						state = 8;
						break;
					case 314:	//nonowy
						state = 12;
						break;
					case 315:	//undecymowy
						state = 6;
						break;
					case 316:	//terdecymowy
						state = 21;
						break;
					default:
						break;
					}
					break;
//*******************************************************************					
				case 301:	//z
					switch(token){
					case 308:	//dodan¹
						state = 330;
						break;
					case 309:	//sekund¹
						state = 340;
						break;
					case 310:	//kwart¹
						state = 345;
						break;
					default:
						state = -1;
						break;
					}
				break;
//*******************************************************************
				case 302:	//ze
					switch(token){
					case 303:	//zwiêkszon¹
						state = 400;
						break;
					case 304:	//zmniejszon¹
						state = 500;
						break;
					default:
						state = -1;
						break;
					}
				break;
//*******************************************************************
				case 323:	//sekstowy
					if(token == 301) state = 360;
					else state = -1;
					break;
					
				case 360:	//sekstowy z
					switch(token){
					case 308:	//dodan¹
						state = 361;
						break;
					case 319:	//non¹
						state = 14;
						break;
					default:
						state = -1;
						break;
					}
					break;
					
				case 361:	//sekstowy z dodan¹
					if(token == 319) state = 14;
					else state = -1;
					break;
//*******************************************************************
				case 313:	//septymowy
					switch(token){
					case 302:	//ze
						state = 370;
						break;
					case 1:		//zwiêkszony
						state = 371;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 370:	//septymowy ze
					switch(token){
					case 303:	//zwiêkszon¹
						state = 372;
						break;
					case 304:	//zmniejszon¹
						state = 373;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 371:	//septymowy zwiêkszony
					if(token == 301) state = 374;
					else state = -1;
					break;
				case 372:	//septymowy ze zwiêkszon¹
					if(token == 317) state = 10; //kwint¹
					else state = -1;
					break;
				case 373:	//septymowy ze zmniejszon¹
					if(token == 317) state = 9;	 //kwint¹
					else state = -1;
					break;
				case 374:	//septymowy zwiêkszony z
					switch (token){
					case 319:	//non¹
						state = 19;
						break;
					case 325:	//terdecym¹
						state = 23;
						break;
					default:
						state = -1;
						break;
					}
					break;
//*******************************************************************	
				case 314:	//nonowy
					switch (token){
					case 301:	//z
						state = 390;
						break;
					case 302:	//ze
						state = 391;
						break;
					case 1:		//zwiêkszony
						state = 18;
						break;
					case 2:		//zmniejszony
						state = 392;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 390:	//nonowy z
					if(token == 322) state = 14;
					else state = -1;
					break;
				case 391:	//nonowy ze
					switch(token){
					case 303:	//zwiêkszon¹
						state = 393;
						break;
					case 304:	//zmniejszon¹
						state = 394;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 392:	//nonowy zmniejszony
					switch (token){
					case 301:	//z
						state = 395;
						break;
					case 302:	//ze
						state = 396;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 393:	//nonowy ze zwiêkszon¹
					if (token == 318) state = 19;
					else state = -1;
					break;
				case 394:	//nonowy ze zmniejszon¹
					if (token == 317)state = 20;
					else state = -1;
					break;
				case 395:	//nonowy zmnieszony z
					if(token == 325) state = 22;
					else state = -1;
					break;
				case 396:	//nonowy zmniejszony ze
					switch(token){
					case 303:	//zwiêkszon¹
						state = 397;
						break;
					case 304:	//zmniejszon¹
						state = 398;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 397:	//nonowy zmnieszony ze zmniejszon¹
					if (token == 317) state = 16;
					else state = -1;
					break;
				case 398:	//nonowy zmniejszony ze zwiêkszon¹
					if (token == 317) state = 17;
					else state = -1;
					break;
//*******************************************************************					
				case 400:	// ze zwiêkszon¹
					switch(token){
					case 317:	//kwint¹
						state = 401;
						break;
					case 318:	//septym¹
						state = 404;
						break;
					case 319:	//non¹
						state = 18;
						break;
					case 320:	//undecym¹
						state = 20;
						break;
					default:
						state = -1;
						break;
					}break;
				case 401:	// ze zwiêkszon¹ kwint¹ 
					if(token == 321) state = 402;	// i
					else state = -1;
					break;
				case 402:	// ze zwiêkszon¹ kwint¹ i 
					switch(token){
					case 304:	//zmniejszon¹
						state = 403;
						break;
					case 318:	//septym¹
						state = 10;
						break;
					default:
						state = -1;
						break;
					}break;
				case 403:	// ze zwiêkszon¹ kwint¹ i zmniejszon¹ 
					if(token == 319) state = 17; // non¹
					else state = -1;
					break;
				case 404:	// ze zwiêkszon¹ septym¹
					if(token == 321) state = 405; // i
					else state = -1;
					break;
				case 405:	// ze zwiêkszon¹ sepptym¹ i
					if(token == 319) state = 19; // non¹
					else state = -1;
					break;
//*******************************************************************
				case 450:	//zwiêkszony z
					if(token == 318) state = 10; // septym¹
					else state = -1;
					break;
				case 451:	//zwiêkszony ze
					if(token == 304) state = 452; // zmniejszon¹
					else state = -1;
					break;
				case 452:	//zwiêkszony ze zmniejszon¹
					if(token == 319) state = 17; // non¹
					else state = -1;
					break;
//*******************************************************************	
				case 500:	//ze zmniejszon¹
					switch(token){
					case 317:	//kwint¹
						state = 510;
						break;
					case 319:	//non¹
						state = 520;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 510:	//ze zmniejszon¹ kwint¹
					if(token == 321) state = 511;	//i
					else state =-1;
					break;
				case 511:	//ze zmniejszon¹ kwint¹ i
					switch(token){
					case 302:	//ze
						state = 512;
						break;
					case 318:	//septym¹
						state = 9;
						break;
					case 319:	//non¹
						state = 20;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 512:	//ze zmniejszon¹ kwint¹ i ze
					if(token == 304) state = 513;	//zmniejszon¹
					else state =-1;
					break;
				case 513:	//ze zmniejszon¹ kwint¹ i ze zmniejszon¹
					if(token == 319) state = 16;	// non¹
					else state =-1;
					break;
					
				case 520:	//ze zmniejszon¹ non¹
					if(token == 321) state = 521;	// i
					else state =-1;
					break;
				case 521:	//ze zmniejszon¹ non¹ i
					switch(token){
					case 302:	//ze
						state = 522;
						break;
					case 325:	//terdecym¹
						state = 22;
						break;
					default:
						state =-1;
						break;
					}
					break;
				case 522:	//ze zmniejszon¹ non¹ i ze
					switch(token){
					case 303:	//zwiêkszon¹
						state = 523;
						break;
					case 304:	//zmniejszon¹
						state = 524;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 523:	//ze zmniejszon¹ non¹ i ze zwiêkszon¹
					if(token == 317) state = 17;	//kwint¹
					else state = -1;
					break;
				case 524:	//ze zmniejszon¹ non¹ i ze zmniejszon¹
					if(token == 317) state = 16;	//kwint¹
					else state = -1;
					break;
//*******************************************************************
				case 600:	//zmniejszony molowy
					switch(token){
					case 301:	//z
						state = 603;
						break;
					case 302:	//ze
						state = 604;
						break;
					default:
						state =-1;
						break;
					}break;
				case 601:	//zmniejszony z
					switch(token){
					case 318:	//septym¹
						state = 9;
						break;
					case 319:	//non¹
						state = 20;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 602:	//zmniejszony ze
					if(token == 304) state = 605;	//zmniejszon¹
					else state = -1;
					break;
				case 603:	//zmniejszony molowy z
					if(token == 318) state = 26;	//septym¹
					else state = -1;
					break;
				case 604:	//zmniejszony molowy ze
					if(token == 304) state = 606;	//zmniejszon¹
					else state = -1;
					break;
				case 605:	//zmniejszony ze zmniejszon¹
					if(token == 319) state = 16;	//non¹
					else state = -1;
					break;
				case 606:	//zmniejszony molowy ze zmniejszon¹
					if(token == 318) state = 27;	//septym¹
					else state = -1;
					break;
//*******************************************************************
				case 700:	//molowy z
					switch(token){
					case 322:	//sekst¹
						state = 705;
						break;
					case 318:	//septym¹
						state = 706;
						break;
					case 319:	//non¹
						state = 707;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 701:	//molowy ze
					switch(token){
					case 303:	//zwiêkszon¹
						state = 708;
						break;
					case 304:	//zmniejszon¹
						state = 709;
						break;
					default:
						state = -1;
						break;
					}
					break;
				case 702:	//molowy sekstowy
					switch(token){
					default:
						state = -1;
						break;
					}
					break;
				case 703:	//molowy septymowy
					switch(token){
					default:
						state = -1;
						break;
					}
					break;
				case 704:	//molowy nonowy
					switch(token){
					default:
						state = -1;
						break;
					}
					break;
				case 705:	//molowy z sekst¹
					if(token == 321) state = 711;	//	i
					else state = -1;
					break;
				case 706:	//molowy z septym¹
					if(token == 321) state = 712;	//	i
					else state = -1;
					break;
				case 707:	//molowy z non¹
					if(token == 321) state = 713;	//	i
					else state = -1;
					break;
				case 708:	//molowy ze zwiêkszon¹
					switch(token){
					case 318:	//septym¹
						state = 714;
						break;
					case 319:	//non¹
						state = 33;
						break;
					default:
						state = -1;
						break;
					}break;
				case 709:	//molowy ze zmniejszon¹
					switch(token){
					case 317:	//kwint¹
						state = 715;
						break;
					case 318:	//septym¹
						state = 716;
						break;
					default:
						state = -1;
						break;
					}break;
				case 710:	//molowy zmniejszony
					switch(token){
					case 301:	// z
						state = 717;
						break;
					case 302:	//ze
						state = 718;
						break;
					default:
						state = -1;
						break;
					}break;
				case 711:	//molowy z sekst¹ i
					switch(token){
					case 308:	//dodan¹
						state = 719;
						break;
					case 319:	//non¹
						state = 32;
						break;
					default:
						state = -1;
						break;
					}break;
				case 712:	//molowy z septym¹ i
					switch(token){
					case 304:	//zmniejszon¹
						state = 720;
						break;
					case 319:	//non¹
						state = 31;
						break;
					default:
						state = -1;
						break;
					}break;
				case 713:	//molowy z non¹ i
					switch(token){
					case 303:	//zwiêkszon¹
						state = 721;
						break;
					case 318:	//septym¹
						state = 31;
						break;
					case 322:	//sekst¹
						state = 32;
						break;
					default:
						state = -1;
						break;
					}break;
				case 714:	//molowy ze zwiêkszon¹ septym¹
					if(token == 321) state = 722;	//	i
					else state = -1;
					break;
				case 715:	//molowy ze zmniejszon¹ kwint¹
					if(token ==321) state = 723;	// i
					else state = -1;
					break;
				case 716:	//molowy ze zmniejszon¹ septym¹
					if(token ==321) state = 724;	// i
					else state = -1;
					break;
				case 717:	//molowy zmniejszony z
					if(token ==318) state = 26;	// septym¹
					else state = -1;
					break;
				case 718:	//molowy zmniejszony ze
					if(token ==304) state = 725;	// zmniejszon¹
					else state = -1;
					break;
				case 719:	//molowy z sekst¹ i dodan¹
					if(token ==319) state = 32;	//	non¹
					else state = -1;
					break;
				case 720:	//molowy z septym¹ i zmniejszon¹
					if(token ==317) state = 26;	//	kwint¹
					else state = -1;
					break;
				case 721:	//molowy z non¹ i zwiêkszon¹
					if(token == 318) state = 34;	//	septym¹
					else state = -1;
					break;
				case 722:	//molowy ze zwiêkszon¹ septym¹ i
					if(token ==319) state = 34;	//	non¹
					else state = -1;
					break;
				case 723:	//molowy ze zmniejszon¹ kwint¹ i
					switch (token){
					case 304:	//zmniejszon¹
						state = 726;
						break;
					case 318:	//septym¹
						state = 26;
						break;
					default:
						state = -1;
						break;
					}break;
				case 724:	//molowy ze zmniejszon¹ septym¹ i
					if(token == 304) state = 727;	// zmniejszon¹
					else state = -1;
					break;
				case 725:	//molowy zmniejszony ze zmniejszon¹
					if(token == 318) state = 27;	// septym¹
					else state = -1;
					break;
				case 726:	//molowy ze zmniejszon¹ kwitn¹ i zmniejszon¹
					if(token == 318) state = 27;	// septym¹
					else state = -1;
					break;
				case 727:	//molowy ze zmniejszon¹ septym¹ i zmniejszon¹
					if(token == 317) state = 27;	// kwint¹
					else state = -1;
					break;
//*******************************************************************					
				default:
					state = -1;
					break;
				}
			}//end of if state != -1
		}//end of nextState function
	}//end of ChordAutomat class

}