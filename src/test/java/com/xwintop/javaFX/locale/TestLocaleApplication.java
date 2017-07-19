package com.xwintop.javaFX.locale;

import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestLocaleApplication extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Locale locale = Locale.UK;
			Parent root = FXMLLoader.load(getClass().getResource("/com/xwintop/javaFX/locale/TestLocaleScene.fxml"),
					ResourceBundle.getBundle("locale.Dorian", locale));
			primaryStage.setTitle("My Application");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void launchMain() {
		TestLocaleApplication.launch(null);
	}
}
