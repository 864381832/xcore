/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.testJjfxUi;

import org.junit.Test;

import javafx.application.Application;
import javafx.stage.Stage;

public class TestApp extends Application {
	public void init() throws Exception {
		super.init();
	}

	public void start(Stage primaryStage) throws Exception {
		new TestForm().show();
	}

	@Test
	public void show() {
		TestApp.launch(null);
	}
}