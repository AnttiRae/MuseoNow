package museoNow;

import javafx.geometry.Insets;

import java.net.URL;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;


public class MainApp extends Application{

	static QuestionMaker question = new QuestionMaker();
	ScoreHandler scoreHandler = new ScoreHandler();
	int currentQuestion = 0;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public String[] SplitQuestions(String questions) {
		String parts[] = questions.split("\\s");
		return parts;
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
		sceneTitle.setId("text");
		gridMain.add(sceneTitle, 0, 0, 2, 1);
		
		//GAME
		GridPane gridGame = new GridPane();
		//Text gameSceneTitle = new Text("Quizz Game");

		//gameSceneTitle.setId("text");
		Scene sceneGame = new Scene(gridGame, 800, 300);
		//gridGame.add(gameSceneTitle, 2, 1, 2, 1);
		
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
		Scene sceneMap = new Scene(gridMap, 800	,800);
		
		WebView webView = new WebView();
		final WebEngine webEngine = webView.getEngine();
		
		URL url = getClass().getResource("/resources/leaflet.wms-gh-pages/html/index.html");
		//leaflet.wms-gh-pages\examples\index.html
		webEngine.load(url.toExternalForm());
		
		GridPane.setHgrow(webView, Priority.ALWAYS);
		GridPane.setVgrow(webView, Priority.ALWAYS);
		//MAP
		gridMap.setAlignment(Pos.CENTER);
		gridMap.setHgap(10);
		gridMap.setVgap(10);
		gridMap.setPadding(new Insets(10, 0, 0, 0));
		gridMap.add(webView, 0, 1);
		
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
		
		Button gameResetBtn = new Button("Reset Game");
		Button gameStartBtn = new Button("Start Game");
		Button gameChoiceABtn = new Button("A");
		Button gameChoiceBBtn = new Button("B");
		Button gameChoiceCBtn = new Button("C");
		Button gameChoiceDBtn = new Button("D");
		
		gameChoiceABtn.wrapTextProperty().setValue(true);
		gameChoiceBBtn.wrapTextProperty().setValue(true);
		gameChoiceCBtn.wrapTextProperty().setValue(true);
		gameChoiceDBtn.wrapTextProperty().setValue(true);
		
		gridGame.add(gameResetBtn, 0, 2);
		gridGame.add(gameStartBtn, 0, 1);
		gridGame.add(gameChoiceABtn, 1, 4);
		gridGame.add(gameChoiceBBtn, 1, 5);
		gridGame.add(gameChoiceCBtn, 1, 6);
		gridGame.add(gameChoiceDBtn, 1, 7);
		
		if (question.giveQuestionArrayLength() == 0 && question.giveAnswerArrayLength() == 0) {
			//make a function out of this 
			gameChoiceABtn.setDisable(true);
			gameChoiceBBtn.setDisable(true);
			gameChoiceCBtn.setDisable(true);
			gameChoiceDBtn.setDisable(true);
			//
		}
		Text fadingText = new Text();
		FadeTransition ft = new FadeTransition(Duration.millis(1000), fadingText);
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.setCycleCount(1);
		ft.setAutoReverse(true);
		
		Text questionText = new Text();
		Text optionsText = new Text();
		Text scoreText = new Text();
		Text progressText = new Text();

		fadingText.setId("text");
		scoreText.setId("text");
		progressText.setId("text");
		optionsText.setId("text");
		questionText.setId("text");
		
		gridGame.add(fadingText, 0, 4 );
		gridGame.add(questionText, 3, 0);
		gridGame.add(optionsText, 3, 1);
		gridGame.add(progressText, 0, 5, 2, 1);
		gridGame.add(scoreText, 0, 6, 2, 2);
		
		gameResetBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				question.destroyQuestions();
				currentQuestion = 0;
				scoreHandler.resetScore();
				progressText.setText("");
				scoreText.setText("");				
				questionText.setText("Game reset!");
				optionsText.setDisable(false);
				optionsText.setText("Press start game for a new game");
				gameChoiceABtn.setDisable(true);
				gameChoiceBBtn.setDisable(true);
				gameChoiceCBtn.setDisable(true);
				gameChoiceDBtn.setDisable(true);
			}
		});
		
		gameStartBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				fadingText.setText("Game Start!");
				ft.play();
				if (question.giveQuestionArrayLength() == 0 && question.giveAnswerArrayLength() == 0) {
					
					gameChoiceABtn.setDisable(false);
					gameChoiceBBtn.setDisable(false);
					gameChoiceCBtn.setDisable(false);
					gameChoiceDBtn.setDisable(false);
					//make some questions
					question.createQuestions();
					progressText.setText(currentQuestion + "/" + question.giveQuestionArrayLength());
					scoreText.setText("Points: " + scoreHandler.getScore());
					
					String parts[] = SplitQuestions(question.showOptions(currentQuestion));
					
					gameChoiceABtn.setText(parts[0]);
					gameChoiceBBtn.setText(parts[1]);
					gameChoiceCBtn.setText(parts[2]);
					gameChoiceDBtn.setText(parts[3]);
					
					questionText.setText(question.showQuestion(currentQuestion));
					optionsText.setDisable(true);
				}
			}
		});
		gameChoiceABtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				progressText.setText(currentQuestion+ 1 +"/"+ question.giveQuestionArrayLength());
				question.giveAnswers("A");
				if (question.checkAnswer(currentQuestion)) {
					scoreHandler.setScore(100);
					fadingText.setText("Correct!");
					ft.play();
				}else {
					//wrong anwers inform player!
					fadingText.setText("Wrong!");
					ft.play();
				}
				if (currentQuestion < question.giveQuestionArrayLength() - 1) {
					currentQuestion++;
					questionText.setText(question.showQuestion(currentQuestion));
					
					String parts[] = SplitQuestions(question.showOptions(currentQuestion));
					
					gameChoiceABtn.setText(parts[0]);
					gameChoiceBBtn.setText(parts[1]);
					gameChoiceCBtn.setText(parts[2]);
					gameChoiceDBtn.setText(parts[3]);
				}else {
					questionText.setText("Game Over!");
					optionsText.setDisable(false);
					optionsText.setText("Points: "+scoreHandler.getScore());
					gameChoiceABtn.setDisable(true);
					gameChoiceBBtn.setDisable(true);
					gameChoiceCBtn.setDisable(true);
					gameChoiceDBtn.setDisable(true);
				}
				scoreText.setText("Points: " + scoreHandler.getScore());
			}
		});
		gameChoiceBBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				progressText.setText(currentQuestion + 1 + "/"+ question.giveQuestionArrayLength());
				question.giveAnswers("B");
				
				if (question.checkAnswer(currentQuestion)) {
					scoreHandler.setScore(100);
					fadingText.setText("Correct!");
					ft.play();
				}else {
					//wrong anwers inform player!
					fadingText.setText("Wrong!");
					ft.play();
				}
				if (currentQuestion < question.giveQuestionArrayLength() - 1) {
					currentQuestion++;
					questionText.setText(question.showQuestion(currentQuestion));
					
					String parts[] = SplitQuestions(question.showOptions(currentQuestion));
					
					gameChoiceABtn.setText(parts[0]);
					gameChoiceBBtn.setText(parts[1]);
					gameChoiceCBtn.setText(parts[2]);
					gameChoiceDBtn.setText(parts[3]);
				}else {
					questionText.setText("Game Over!");
					optionsText.setDisable(false);
					optionsText.setText("Points: "+scoreHandler.getScore());
					gameChoiceABtn.setDisable(true);
					gameChoiceBBtn.setDisable(true);
					gameChoiceCBtn.setDisable(true);
					gameChoiceDBtn.setDisable(true);
				}
				scoreText.setText("Points: " + scoreHandler.getScore());
			}
		});
		gameChoiceCBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				progressText.setText(currentQuestion + 1 +"/"+ question.giveQuestionArrayLength());
				question.giveAnswers("C");

				
				if (question.checkAnswer(currentQuestion)) {
					scoreHandler.setScore(100);
					fadingText.setText("Correct!");
					ft.play();
				}else {
					//wrong anwers inform player!
					fadingText.setText("Wrong!");
					ft.play();
				}
				if (currentQuestion < question.giveQuestionArrayLength() - 1) {
					currentQuestion++;
					questionText.setText(question.showQuestion(currentQuestion));
					
					String parts[] = SplitQuestions(question.showOptions(currentQuestion));
					
					gameChoiceABtn.setText(parts[0]);
					gameChoiceBBtn.setText(parts[1]);
					gameChoiceCBtn.setText(parts[2]);
					gameChoiceDBtn.setText(parts[3]);
				}else {
					questionText.setText("Game Over!");
					optionsText.setDisable(false);
					optionsText.setText("Points: "+scoreHandler.getScore());
					gameChoiceABtn.setDisable(true);
					gameChoiceBBtn.setDisable(true);
					gameChoiceCBtn.setDisable(true);
					gameChoiceDBtn.setDisable(true);
				}
				scoreText.setText("Points: " + scoreHandler.getScore());
			}
		});
		gameChoiceDBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				progressText.setText(currentQuestion + 1 +"/"+ question.giveQuestionArrayLength());
				question.giveAnswers("D");
				
				if (question.checkAnswer(currentQuestion)) {
					scoreHandler.setScore(100);
					fadingText.setText("Correct!");
					ft.play();
				}else {
					//wrong anwers inform player!
					fadingText.setText("Wrong!");
					ft.play();
				}
				if (currentQuestion < question.giveQuestionArrayLength() - 1) {
					currentQuestion++;
					questionText.setText(question.showQuestion(currentQuestion));
					String parts[] = SplitQuestions(question.showOptions(currentQuestion));
					
					gameChoiceABtn.setText(parts[0]);
					gameChoiceBBtn.setText(parts[1]);
					gameChoiceCBtn.setText(parts[2]);
					gameChoiceDBtn.setText(parts[3]);
				}else {
					questionText.setText("Game Over!");
					optionsText.setDisable(false);
					optionsText.setText("Points: "+scoreHandler.getScore());
					gameChoiceABtn.setDisable(true);
					gameChoiceBBtn.setDisable(true);
					gameChoiceCBtn.setDisable(true);
					gameChoiceDBtn.setDisable(true);
				}
				scoreText.setText("Points: " + scoreHandler.getScore());
			}
		});
		//MAIN MENU BUTTON
		Button mainMenuBtnMap = new Button("Main Menu");
		Button mainMenuBtnGame = new Button("Main Menu");
		
		gridMap.add(mainMenuBtnMap, 0, 0);
		gridGame.add(mainMenuBtnGame, 0, 0);
		
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
		//Add IDs to buttons
		gameChoiceABtn.setId("glass-grey");
		gameChoiceBBtn.setId("glass-grey");
		gameChoiceCBtn.setId("glass-grey");
		gameChoiceDBtn.setId("glass-grey");
		//
		gameBtn.setId("menu-button");
		mapBtn.setId("menu-button");
		mainMenuBtnGame.setId("menu-button");
		mainMenuBtnMap.setId("menu-button");
		gameResetBtn.setId("menu-button");
		gameStartBtn.setId("menu-button");
		//add stylesheets to all scenes
		sceneMain.getStylesheets().add
		(MainApp.class.getResource("/resources/FXStylesheet.css").toExternalForm());
		sceneMap.getStylesheets().add
		(MainApp.class.getResource("/resources/FXStylesheet.css").toExternalForm());
		sceneGame.getStylesheets().add
		(MainApp.class.getResource("/resources/FXStylesheet.css").toExternalForm());
	}
	
}
