package com.xwintop.javaFX;

import org.junit.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxMain extends Application {
	@Override
	public void start(Stage primaryStage) {
//		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root, 400, 400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		try {
			// Read file fxml and draw interface.
			Parent root = FXMLLoader.load(getClass().getResource("/com/xwintop/javaFX/MyScene.fxml"));
			primaryStage.setTitle("My Application");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void launchMain() {
		JavaFxMain.launch(null);
	}
}
