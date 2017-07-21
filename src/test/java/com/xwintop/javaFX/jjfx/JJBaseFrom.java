/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.xwintop.javaFX.jjfx.view_wait.JJWaitView;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class JJBaseFrom<T> extends Stage {
	private static final String TITLE = "JJFX";
	protected FXMLLoader subFxmlLoader;
	private BaseFromController mBaseController;
	private FXMLLoader mBaseLoader;
	private Scene mScene;
	private JJWaitView mJJWaitView;

	public JJBaseFrom(String fxmlName) {
		try {
			this.init(fxmlName, -1, -1, Location.center);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JJBaseFrom(String fxmlName, Location location) {
		try {
			this.init(fxmlName, -1, -1, location);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JJBaseFrom(String fxmlName, int width, int height) {
		try {
			this.init(fxmlName, width, height, Location.center);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JJBaseFrom(String fxmlName, int width, int height, Location location) {
		try {
			this.init(fxmlName, width, height, location);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init(String fxmlName, int width, int height, final Location location) throws IOException {
		URL url = JJBaseFrom.class.getResource("/javafx/jjfx/base_from.fxml");
		this.mBaseLoader = new FXMLLoader(url);
		this.mBaseLoader.load();
		this.mBaseController = this.mBaseLoader.getController();
		this.mScene = width == -1 ? new Scene(this.mBaseController.root_anchor_pane)
				: new Scene(this.mBaseController.root_anchor_pane, width, height);
		this.setScene(this.mScene);
		this.subFxmlLoader = new FXMLLoader(this.getClass().getResource("/" + fxmlName));
		Node subRoot = this.subFxmlLoader.load();
		this.mBaseController.root_border_pane.setCenter(subRoot);
		this.setTitle(TITLE);
		this.setOnShown(new EventHandler<WindowEvent>() {
			@Autowired
			public void handle(WindowEvent event) {
				Stage stage = (Stage) event.getTarget();
				Window parentStage = stage.getOwner();
				if (parentStage != null) {
					double x;
					double y;
					switch (location) {
					case up: {
						x = parentStage.getX() + parentStage.getWidth() / 2.0 - stage.getWidth() / 2.0;
						y = parentStage.getY();
						break;
					}
					case down: {
						x = parentStage.getX() + parentStage.getWidth() / 2.0 - stage.getWidth() / 2.0;
						y = parentStage.getY() + parentStage.getHeight() - stage.getHeight() * 2.0;
						break;
					}
					default: {
						x = parentStage.getX() + parentStage.getWidth() / 2.0 - stage.getWidth() / 2.0;
						y = parentStage.getY() + parentStage.getHeight() / 2.0 - stage.getHeight() / 2.0;
					}
					}
					JJBaseFrom.this.setX(x);
					JJBaseFrom.this.setY(y);
				}
			}
		});
	}

	public FXMLLoader getFxmlLoader() {
		return this.subFxmlLoader;
	}

	public T getController() {
		return this.subFxmlLoader.getController();
	}

	public void showWait(String text) {
		this.showWait(text, null);
	}

	public void showWait(String text, JJWaitView.OnCancel onCancel) {
		this.mJJWaitView = new JJWaitView(text, this, onCancel);
		AnchorPane.setBottomAnchor(this.mJJWaitView, 0.0);
		AnchorPane.setLeftAnchor(this.mJJWaitView, 0.0);
		AnchorPane.setRightAnchor(this.mJJWaitView, 0.0);
		AnchorPane.setTopAnchor(this.mJJWaitView, 0.0);
		this.mBaseController.root_anchor_pane.getChildren().add(this.mJJWaitView);
	}

	public void closeWait_ansy() {
		Platform.runLater((Runnable) new Runnable() {

			@Override
			public void run() {
				JJBaseFrom.this.closeWait();
			}
		});
	}

	public void closeWait() {
		try {
			if (this.mJJWaitView != null) {
				this.mBaseController.root_anchor_pane.getChildren().remove(this.mJJWaitView);
			}
		} catch (Exception var1_1) {
			// empty catch block
		}
	}

	public void setIcon(String path) {
		this.getIcons().add(new Image(path));
	}

	public void showToast_center(String text) {
		JJFx.showToast(text, this);
	}

	public void showToast_down(String text) {
		JJFx.showToast(text, this, 700, Location.down);
	}

	public static enum Location {
		up, center, down;
	}

}