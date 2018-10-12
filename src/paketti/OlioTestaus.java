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
	KysymysMaker kysymys = new KysymysMaker();
	int Points = pisteLaskuri.getScore();
	
	
	
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
		Text t = new Text(10, 50, "this is A test");
		t.setFont(new Font(20));
		
		GridPane gridGame = new GridPane();
		Text gameSceneTitle = new Text("Game about stuff");
		Scene sceneGame = new Scene(gridGame, 640, 800);
		
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
		Scene sceneMap = new Scene(gridMap, 640	,800);
		
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
		
		Scene sceneMain = new Scene (gridMain, 640, 800);
		primaryStage.setScene(sceneMain);
		primaryStage.show();
		
		//Buttons for the game screen A/B/C/D
		Button gameStart = new Button("Start Game");
		Button gameChoiceA = new Button("A");
		Button gameChoiceB = new Button("B");
		Button gameChoiceC = new Button("C");
		Button gameChoiceD = new Button("D");
		
		
		gridGame.add(gameStart, 0, 0);
		gridGame.add(gameChoiceA, 1, 4);
		gridGame.add(gameChoiceB, 1, 5);
		gridGame.add(gameChoiceD, 1, 6);
		gridGame.add(gameChoiceC, 1, 7);
		
		gameStart.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				kysymys.createQuestions();
				
			}
		});
		
		gameChoiceA.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				kysymys.giveAnswers("A");
				System.out.println("A");
				System.out.println(kysymys.giveKysymysArrayLength());
				System.out.println(kysymys.giveVastausArrayLength());
			}
		});
		gameChoiceB.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				kysymys.giveAnswers("B");
				System.out.println("B");

				//if (vastaus === oikein) {
				//pisteLaskuri.setScore(250);
				//System.out.println(pisteLaskuri.getScore());
				//}
			}
		});
		gameChoiceC.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				kysymys.giveAnswers("C");
				System.out.println("C");
			}
		});
		gameChoiceD.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Press button >> stuff happens here
				kysymys.giveAnswers("D");
				System.out.println("D");
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
            KysymysMaker kysymys = new KysymysMaker();
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
}
