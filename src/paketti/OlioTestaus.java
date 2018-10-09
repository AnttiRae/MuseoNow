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
		Button btn = new Button();
		btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				visa();
			}
		});
		
		StackPane root = new StackPane();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Text sceneTitle = new Text("Welcome to Museo Now!");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(sceneTitle, 0, 0, 2, 1);
		
		Button gameBtn = new Button("Game");
		Button mapBtn = new Button("Map");
		HBox hbGameBtn = new HBox(10);
		HBox hbMapBtn = new HBox(10);
		hbGameBtn.setAlignment(Pos.BOTTOM_CENTER);
		hbMapBtn.setAlignment(Pos.BOTTOM_CENTER);
		
		hbGameBtn.getChildren().add(gameBtn);
		hbMapBtn.getChildren().add(mapBtn);
		
		grid.add(hbGameBtn, 1, 4);
		grid.add(hbMapBtn, 0, 4);
		
		Scene scene = new Scene (grid, 300, 275);
		primaryStage.setScene(scene);
		primaryStage.show();
		
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
			System.out.println("VÄÄRIN");
		}
	}

}