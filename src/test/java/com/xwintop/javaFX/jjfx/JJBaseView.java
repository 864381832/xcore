/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class JJBaseView<T> extends AnchorPane {
	private Parent mRoot;
	private FXMLLoader fxmlLoader;
	private JJBaseFrom<T> mFrom;

	public JJBaseView(String fxmlName) {
		this.init(fxmlName, null);
	}

	public JJBaseView(String fxmlName, JJBaseFrom<T> from) {
		this.init(fxmlName, from);
	}

	private void init(String fxmlName, JJBaseFrom<T> from) {
		this.mFrom = from;
		try {
			this.fxmlLoader = new FXMLLoader(this.getClass().getResource("/" + fxmlName));
			this.mRoot = (Parent) this.fxmlLoader.load();
			this.setView(this.mRoot);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Parent getRoot() {
		return this.mRoot;
	}

	public FXMLLoader getFxmlLoader() {
		return this.fxmlLoader;
	}

	public void setView(Parent node) {
		this.mRoot = node;
		this.getChildren().add(this.mRoot);
		JJBaseView.setBottomAnchor((Node) this.mRoot, (Double) 0.0);
		JJBaseView.setLeftAnchor((Node) this.mRoot, (Double) 0.0);
		JJBaseView.setRightAnchor((Node) this.mRoot, (Double) 0.0);
		JJBaseView.setTopAnchor((Node) this.mRoot, (Double) 0.0);
	}

	public T getController() {
		return this.fxmlLoader.getController();
	}

	public JJBaseFrom<T> getFrom() {
		return this.mFrom;
	}
}