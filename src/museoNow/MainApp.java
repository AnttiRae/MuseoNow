package museoNow;

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



public class MainApp extends Application{

	static QuestionMaker question = new QuestionMaker();
	ScoreHandler scoreHandler = new ScoreHandler();
	int Points = scoreHandler.getScore();
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
		
		//add stylesheets to all scenes
		sceneMain.getStylesheets().add
		 (MainApp.class.getResource("stylesheet.css").toExternalForm());
		sceneMap.getStylesheets().add
		 (MainApp.class.getResource("stylesheet.css").toExternalForm());
		sceneGame.getStylesheets().add
		 (MainApp.class.getResource("stylesheet.css").toExternalForm());
		
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
		
		if (question.giveQuestionArrayLength() == 0 && question.giveAnswerArrayLength() == 0) {
			//better way to do this?
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
				if (question.giveQuestionArrayLength() == 0 && question.giveAnswerArrayLength() == 0) {
					
					gameChoiceABtn.setDisable(false);
					gameChoiceBBtn.setDisable(false);
					gameChoiceCBtn.setDisable(false);
					gameChoiceDBtn.setDisable(false);
					//make some questions
					question.createQuestions();

					questionText.setFont(new Font(20));
					optionsText.setFont(new Font(20));
					System.out.println("kysmys:" + question.showQuestion(currentQuestion)); 
					questionText.setText(question.showQuestion(currentQuestion));
					optionsText.setText(question.showOptions(currentQuestion));
					gridGame.add(questionText, 3, 5);
					gridGame.add(optionsText, 3, 6);
				}
			}
		});
		gameChoiceABtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				System.out.println("A");
				question.giveAnswers("A");
				if (question.checkAnswer(currentQuestion)) {
					System.out.println("correct!");
					scoreHandler.setScore(100);
				}else {
					System.out.println("wrong!");
				}
				if (currentQuestion < question.giveQuestionArrayLength() - 1) {
					currentQuestion++;
					System.out.println("kysmys:" + question.showQuestion(currentQuestion)); 
					questionText.setText(question.showQuestion(currentQuestion));
					optionsText.setText(question.showOptions(currentQuestion));
				}else {
					questionText.setText("Game Over!");
					optionsText.setText("Points: "+scoreHandler.getScore());
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
				question.giveAnswers("B");
				System.out.println("B");
				
				if (question.checkAnswer(currentQuestion)) {
					System.out.println("correct!");
					scoreHandler.setScore(100);
				}else {
					System.out.println("wrong!");
				}
				if (currentQuestion < question.giveQuestionArrayLength() - 1) {
					currentQuestion++;
					System.out.println("kysmys:" + question.showQuestion(currentQuestion)); 
					questionText.setText(question.showQuestion(currentQuestion));
					optionsText.setText(question.showOptions(currentQuestion));
				}else {
					questionText.setText("Game Over!");
					optionsText.setText("Points: "+scoreHandler.getScore());
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
				question.giveAnswers("C");
				System.out.println("C");
				
				if (question.checkAnswer(currentQuestion)) {
					System.out.println("correct!");
					scoreHandler.setScore(100);
				}else {
					System.out.println("wrong!");
				}
				if (currentQuestion < question.giveQuestionArrayLength() - 1) {
					currentQuestion++;
					System.out.println("kysmys:" + question.showQuestion(currentQuestion)); 
					questionText.setText(question.showQuestion(currentQuestion));
					optionsText.setText(question.showOptions(currentQuestion));
				}else {
					questionText.setText("Game Over!");
					optionsText.setText("Points: "+scoreHandler.getScore());
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
				question.giveAnswers("D");
				System.out.println("D");
				
				if (question.checkAnswer(currentQuestion)) {
					System.out.println("correct!");
					scoreHandler.setScore(100);
				}else {
					System.out.println("wrong!");
				}
				if (currentQuestion < question.giveQuestionArrayLength() - 1) {
					currentQuestion++;
					System.out.println("kysmys:" + question.showQuestion(currentQuestion)); 
					questionText.setText(question.showQuestion(currentQuestion));
					optionsText.setText(question.showOptions(currentQuestion));
				}else {
					questionText.setText("Game Over!");
					optionsText.setText("Points: "+scoreHandler.getScore());
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
}
