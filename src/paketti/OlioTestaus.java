package paketti;

import javafx.geometry.Insets;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class OlioTestaus extends Application{

	static KysymysMaker kysymys = new KysymysMaker();
	Pisteet pisteLaskuri = new Pisteet();
	int Points = pisteLaskuri.getScore();
	String playerResponse;
	int currentQuestion = 0;
	
	
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Museo Now!");


		//MAIN MENU
		GridPane gridMain = new GridPane();
		gridMain.setAlignment(Pos.TOP_CENTER);
		gridMain.setHgap(10);
		gridMain.setVgap(10);
		gridMain.setPadding(new Insets(25, 25, 25, 25));
		
		Text sceneTitle = new Text("Welcome to Museo Now!");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		gridMain.add(sceneTitle, 0, 0, 2, 1);
		

		
		
		//GAME
		GridPane gridGame = new GridPane();
		Text gameSceneTitle = new Text("Game about stuff");
		Scene sceneGame = new Scene(gridGame, 800, 300);
		
		gameSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		gridGame.add(gameSceneTitle, 1, 1, 2, 1);
		
		gridGame.setAlignment(Pos.TOP_LEFT);
		gridGame.setHgap(10);
		gridGame.setVgap(10);
		gridGame.setPadding(new Insets(25, 25, 25, 25));
		
		Button gameBtn = new Button("Game");
		

		
		gameBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here

				
				primaryStage.setScene(sceneGame);
				primaryStage.show();
			}
		});
		
		GridPane gridMap = new GridPane();
		Text mapSceneTitle = new Text("Map is under construction");
		Scene sceneMap = new Scene(gridMap, 800	,800);
		
		//MAP
		gridMap.setAlignment(Pos.CENTER);
		gridMap.setHgap(10);
		gridMap.setVgap(10);
		gridMap.setPadding(new Insets(25, 25, 25, 25));
		
		mapSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		gridMap.add(mapSceneTitle, 0, 0, 2, 1);
		
		Button mapBtn = new Button("Map");
		mapBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				primaryStage.setScene(sceneMap);
				primaryStage.show();
			}
		});
		
		//Button in main menu
		gridMain.add(gameBtn, 1, 3);
		gridMain.add(mapBtn, 0, 3);
		
		Scene sceneMain = new Scene (gridMain, 800, 300);
		primaryStage.setScene(sceneMain);
		primaryStage.show();
		
		//Buttons for the game screen A/B/C/D
		Button gameStartBtn = new Button("Start Game");
		Button gameChoiceABtn = new Button("A");
		Button gameChoiceBBtn = new Button("B");
		Button gameChoiceCBtn = new Button("C");
		Button gameChoiceDBtn = new Button("D");
		
		
		gridGame.add(gameStartBtn, 0, 0);
		gridGame.add(gameChoiceABtn, 1, 4);
		gridGame.add(gameChoiceBBtn, 1, 5);
		gridGame.add(gameChoiceCBtn, 1, 6);
		gridGame.add(gameChoiceDBtn, 1, 7);
		
		if (kysymys.giveKysymysArrayLength() == 0 && kysymys.giveVastausArrayLength() == 0) {
			gameChoiceABtn.setDisable(true);
			gameChoiceBBtn.setDisable(true);
			gameChoiceCBtn.setDisable(true);
			gameChoiceDBtn.setDisable(true);
		}
		
		Text questionText = new Text();
		Text optionsText = new Text();

		gameStartBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				if (kysymys.giveKysymysArrayLength() == 0 && kysymys.giveVastausArrayLength() == 0) {
					
					gameChoiceABtn.setDisable(false);
					gameChoiceBBtn.setDisable(false);
					gameChoiceCBtn.setDisable(false);
					gameChoiceDBtn.setDisable(false);
					//gameStartBtn.setDisable(true);
					kysymys.createQuestions();

					questionText.setFont(new Font(20));
					optionsText.setFont(new Font(20));
					System.out.println("kysmys:" + kysymys.showQuestion(currentQuestion)); 
					questionText.setText(kysymys.showQuestion(currentQuestion));
					optionsText.setText(kysymys.showOptions(currentQuestion));
					gridGame.add(questionText, 3, 5);
					gridGame.add(optionsText, 3, 6);
				}
				System.out.println(currentQuestion);
				System.out.println(kysymys.giveKysymysArrayLength());
					
				//questionText.setVisible(false);
				
			}
		});
		
		gameChoiceABtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				System.out.println("A");
				playerResponse = "A";
				kysymys.giveAnswers("A");
				if (kysymys.checkAnswer(currentQuestion)) {
					System.out.println("correct!");
				}else {
					System.out.println("wrong!");
				}
				if (currentQuestion < kysymys.giveKysymysArrayLength() - 1) {
					currentQuestion++;
					System.out.println("kysmys:" + kysymys.showQuestion(currentQuestion)); 
					questionText.setText(kysymys.showQuestion(currentQuestion));
					optionsText.setText(kysymys.showOptions(currentQuestion));
				}else {
					questionText.setText("Game Over!");
					optionsText.setText("Points: 500000");
					gameChoiceABtn.setDisable(true);
					gameChoiceBBtn.setDisable(true);
					gameChoiceCBtn.setDisable(true);
					gameChoiceDBtn.setDisable(true);
				}
			}
		});
		gameChoiceBBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				playerResponse = "B";
				kysymys.giveAnswers("B");
				System.out.println("B");
				
				if (kysymys.checkAnswer(currentQuestion)) {
					System.out.println("correct!");
				}else {
					System.out.println("wrong!");
				}
				if (currentQuestion < kysymys.giveKysymysArrayLength() - 1) {
					currentQuestion++;
					System.out.println("kysmys:" + kysymys.showQuestion(currentQuestion)); 
					questionText.setText(kysymys.showQuestion(currentQuestion));
					optionsText.setText(kysymys.showOptions(currentQuestion));
				}else {
					questionText.setText("Game Over!");
					optionsText.setText("Points: 500000");
					gameChoiceABtn.setDisable(true);
					gameChoiceBBtn.setDisable(true);
					gameChoiceCBtn.setDisable(true);
					gameChoiceDBtn.setDisable(true);
				}
				
			}
		});
		gameChoiceCBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				playerResponse = "C";
				kysymys.giveAnswers("C");
				System.out.println("C");
				
				if (kysymys.checkAnswer(currentQuestion)) {
					System.out.println("correct!");
				}else {
					System.out.println("wrong!");
				}
				if (currentQuestion < kysymys.giveKysymysArrayLength() - 1) {
					currentQuestion++;
					System.out.println("kysmys:" + kysymys.showQuestion(currentQuestion)); 
					questionText.setText(kysymys.showQuestion(currentQuestion));
					optionsText.setText(kysymys.showOptions(currentQuestion));
				}else {
					questionText.setText("Game Over!");
					optionsText.setText("Points: 500000");
					gameChoiceABtn.setDisable(true);
					gameChoiceBBtn.setDisable(true);
					gameChoiceCBtn.setDisable(true);
					gameChoiceDBtn.setDisable(true);
				}
			}
		});
		gameChoiceDBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				playerResponse = "D";
				kysymys.giveAnswers("D");
				System.out.println("D");
				
				if (kysymys.checkAnswer(currentQuestion)) {
					System.out.println("correct!");
				}else {
					System.out.println("wrong!");
				}
				if (currentQuestion < kysymys.giveKysymysArrayLength() - 1) {
					currentQuestion++;
					System.out.println("kysmys:" + kysymys.showQuestion(currentQuestion)); 
					questionText.setText(kysymys.showQuestion(currentQuestion));
					optionsText.setText(kysymys.showOptions(currentQuestion));
				}else {
					questionText.setText("Game Over!");
					optionsText.setText("Points: 500000");
					gameChoiceABtn.setDisable(true);
					gameChoiceBBtn.setDisable(true);
					gameChoiceCBtn.setDisable(true);
					gameChoiceDBtn.setDisable(true);
				}
			}
		});
		//MAIN MENU BUTTON
		Button mainMenuBtnMap = new Button("Main Menu");
		Button mainMenuBtnGame = new Button("Main Menu");
		gridMap.add(mainMenuBtnMap, 0, 1);
		gridGame.add(mainMenuBtnGame, 0, 1);
		
		mainMenuBtnMap.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				//Map screen main Menu button  event handler
				primaryStage.setScene(sceneMain);
				primaryStage.show();
				
			}
		});
		mainMenuBtnGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			//Game screen main menu button event handler
			public void handle(ActionEvent event) {
				primaryStage.setScene(sceneMain);
				primaryStage.show();
				
			}
		});
		
	}
	
	public void visa() {
        int KysymysNumero = 0;
        int kysymysArrayLenght = kysymys.giveKysymysArrayLength();
        for (KysymysNumero=0; KysymysNumero>kysymysArrayLenght; KysymysNumero++){
            kysymys.createQuestions();
            System.out.println(kysymys.showQuestion(KysymysNumero));
            System.out.println("\nVastaus:");
            Scanner lukija = new Scanner(System.in);
            kysymys.giveAnswers(lukija.nextLine());
        if (kysymys.checkAnswer(KysymysNumero)) {
            System.out.println("Right");
            pisteLaskuri.setScore(100);
			System.out.println("Pisteet: "+pisteLaskuri.getScore());
        }else {
            System.out.println("Wrong");
        }
        }
    }
//	public void changeGameText(int questionIndex object ) {
//		System.out.println("kysmys:" + kysymys.showQuestion(questionIndex)); 
//		questionText.setText(kysymys.showQuestion(questionIndex));
//		optionsText.setText(kysymys.showOptions(questionIndex));
//		gridGame.add(questionText, 3, 5);
//		gridGame.add(optionsText, 3, 6);
//	}
}
