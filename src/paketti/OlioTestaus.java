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
		Text gameSceneTitle = new Text("Game is under construction ebin");
		Scene sceneGame = new Scene(gridGame, 640, 800);
		
		gameSceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		gridGame.add(gameSceneTitle, 0, 0, 2, 1);
		
		gridGame.setAlignment(Pos.CENTER);
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
		Text mapSceneTitle = new Text("Map is under construction ebin");
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
		
		//MAIN MENU BUTTON
		Button mainMenuBtnMap = new Button("Main Menu");
		Button mainMenuBtnGame = new Button("Main Menu");
		gridMap.add(mainMenuBtnMap, 0, 1);
		gridGame.add(mainMenuBtnGame, 0, 1);
		
		mainMenuBtnMap.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(sceneMain);
				primaryStage.show();
				
			}
		});
		mainMenuBtnGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				primaryStage.setScene(sceneMain);
				primaryStage.show();
				
			}
		});
		
	}
	
	public void visa() {
		KysymysMaker kysymys = new KysymysMaker();
		kysymys.createQuestions();
		System.out.println(kysymys.showQuestion(1));
		System.out.println("\nVastaus:");
		Scanner lukija = new Scanner(System.in);
		kysymys.giveAnswers(lukija.nextLine());
		if (kysymys.checkAnswer(1)) {
			System.out.println("OIKEIN");
		}else {
			System.out.println("V��RIN");
		}
	}
}