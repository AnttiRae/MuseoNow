package paketti;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
//import java.util.ArrayList;
//import java.util.Arrays;


public class KysymysMaker {
	private String[] kysymysArray = {};
	private String[] vastausArray = {};
	private String kayttajanVastaus;
	
	public KysymysMaker() {

	}
	public void createQuestions() {
		
		try {
			
			// read the json file
			FileReader reader = new FileReader("kysymykset.json");
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			
			JSONArray lang= (JSONArray) jsonObject.get("kysymykset");
			
			Iterator i = lang.iterator();
			// take each value from the json array separately
			while (i.hasNext()) {
				JSONObject innerObj = (JSONObject) i.next();
				String kysymys = (String) innerObj.get("kysymys");
				String vastaus = (String) innerObj.get("vastaus");
				
				//define new array
				String[] newKysymysArray = new String[kysymysArray.length + 1];
				String[] newVastausArray = new String[vastausArray.length + 1];
				for (int j = 0; j < kysymysArray.length; j++) {
					newKysymysArray[j] = kysymysArray[j];
					newVastausArray[j] = vastausArray[j];
				}
				newKysymysArray[newKysymysArray.length-1] = kysymys;
				newVastausArray[newVastausArray.length-1] = vastaus;
				
				kysymysArray = newKysymysArray;
				vastausArray = newVastausArray;
			}
			
		
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ParseException ex) {
			ex.printStackTrace();
		} catch (NullPointerException ex) {
			ex.printStackTrace();
		}
	}
	
	public String showQuestion(int i) {
		return kysymysArray[i];
	}
	public String giveAnswers(String a) {
		kayttajanVastaus = a;
		return a;
	}
	public boolean checkAnswer(int i) {
		if (vastausArray[i].equals(kayttajanVastaus)) {
			return true;
		}
		else {
			return false;
		}
	}
}
