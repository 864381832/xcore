/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.alert;

import com.xwintop.javaFX.jjfx.JJBaseFrom;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class JJDialog extends JJBaseFrom {
	AlertController mController;

	public JJDialog(String title, String message, boolean AlwaysOnTop) {
		super("javafx/jjfx/Alert.fxml");
		this.init(title, message, AlwaysOnTop);
	}

	public JJDialog(String title, String message) {
		super("javafx/jjfx/Alert.fxml");
		this.init(title, message, false);
	}

	private void init(String title, String message, boolean AlwaysOnTop) {
		this.initModality(Modality.APPLICATION_MODAL);
		this.setAlwaysOnTop(AlwaysOnTop);
		this.setTitle(title);
		this.mController = (AlertController) this.getFxmlLoader().getController();
		this.mController.setJJDialog(this);
		this.mController.messageLabel.setText(message);
	}

	public void addView(Node node) {
		this.mController.vbox.getChildren().add(node);
	}

	public void hideBtn() {
	}

	public void setMessage(String text) {
		this.mController.messageLabel.setText(text);
	}

	public static JJDialog showAndWait(String msg, Stage owner) {
		return JJDialog.show(msg, true, owner);
	}

	public static JJDialog show(String msg, Stage owner) {
		return JJDialog.show(msg, false, owner);
	}

	public static void show_ansy(final String msg, final Stage owner) {
		Platform.runLater((Runnable) new Runnable() {

			@Override
			public void run() {
				JJDialog.show(msg, false, owner);
			}
		});
	}

	private static JJDialog show(String msg, boolean showAndWait, Stage owner) {
		JJDialog JJDialog2 = null;
		JJDialog2 = new JJDialog("消息", msg);
		if (owner != null) {
			JJDialog2.initOwner((Window) owner);
		}
		if (showAndWait) {
			JJDialog2.showAndWait();
		} else {
			JJDialog2.show();
		}
		return JJDialog2;
	}

	public void setTextAlignment(Pos pos) {
		this.mController.vbox.setAlignment(pos);
	}

}