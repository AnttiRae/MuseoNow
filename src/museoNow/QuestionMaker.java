package museoNow;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;


public class QuestionMaker {
	private String[] questionArray = {};
	private String[] answerArray = {};
	private String[] optionsArray = {};
	private String[] placesArray = {};
	private String userAnswer;
	private String currentPlace;

	public void setGamePlace(String place) { currentPlace = place; }

	public String[] getPlaces() {
		return placesArray;
	}

	public void populatePlacesArray(){
		try {

			// read the json file
			FileReader reader = new FileReader("questions.json");

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			System.out.println("Key set");
			// Get place names from json
			Set<String> places = jsonObject.keySet();
			System.out.println(places);
			// cast Set<String> to String[]
			placesArray = places.toArray(new String[places.size()]);

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

	public void createQuestions() {
		
		try {
			
			// read the json file
			FileReader reader = new FileReader("questions.json");
			
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			System.out.println("Key set");
			Set<String> places = jsonObject.keySet();
			System.out.println(places);
			placesArray = places.toArray(new String[places.size()]);

			System.out.println(placesArray[0]);
			//JSONArray array = (JSONArray) jsonObject.get("questions");

			JSONObject testing = (JSONObject) jsonObject.get(currentPlace);
			JSONArray array = (JSONArray) testing.get("questions");
			System.out.print("testing var:");
			System.out.println(testing);
			System.out.println("array var");
			System.out.println(array);
			Iterator iter = array.iterator();
			// take each value from the json array separately
			while (iter.hasNext()) {
				JSONObject innerObj = (JSONObject) iter.next();
				String kysymys = (String) innerObj.get("question");
				String vastaus = (String) innerObj.get("answer");
				String vaihtoehdot = (String) innerObj.get("options");
				
				//define new arrays
				String[] newKysymysArray = new String[questionArray.length + 1];
				String[] newVastausArray = new String[answerArray.length + 1];
				String[] newVaihtoehdotArray = new String[optionsArray.length + 1];
				for (int j = 0; j < questionArray.length; j++) {
					//copy old arrays to new ones
					newKysymysArray[j] = questionArray[j];
					newVastausArray[j] = answerArray[j];
					newVaihtoehdotArray[j] = optionsArray[j];
				}
				//add new value to new arrays
				newKysymysArray[newKysymysArray.length-1] = kysymys;
				newVastausArray[newVastausArray.length-1] = vastaus;
				newVaihtoehdotArray[newVaihtoehdotArray.length-1] = vaihtoehdot;
				
				//new arrays are now old arrays :(
				questionArray = newKysymysArray;
				answerArray = newVastausArray;
				optionsArray = newVaihtoehdotArray;
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
	
	public void destroyQuestions() {
		// make new empty array 
		String[] emptyArray = {};
		// old arrays are now empty 
		questionArray = emptyArray;
		answerArray = emptyArray;
		optionsArray = emptyArray;
	}
	public int giveQuestionArrayLength() {
		return questionArray.length;
	}
	public int giveAnswerArrayLength() {
		return answerArray.length;
	}
	public String showQuestion(int i) {
		return questionArray[i];
	}
	public String showOptions(int i) {
		return optionsArray[i];
	}
	public String giveAnswers(String a) {
		userAnswer = a;
		return a;
	}
	public boolean checkAnswer(int i) {
		if (answerArray[i].equals(userAnswer)) {
			return true;
		}
		else {
			return false;
		}
	}
}
