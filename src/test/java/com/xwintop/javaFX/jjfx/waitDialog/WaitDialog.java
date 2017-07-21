/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.waitDialog;

import org.controlsfx.control.MaskerPane;

import com.xwintop.javaFX.jjfx.JJBaseFrom;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class WaitDialog extends JJBaseFrom {
	private WaitDialogController mController;
	private MaskerPane masker;

	public WaitDialog(Stage parentStage) {
		super("javafx/jjfx/wait_dialog.fxml");
		this.init(parentStage);
	}

	public WaitDialog() {
		super("javafx/jjfx/wait_dialog.fxml");
		this.init(null);
	}

	private void init(Stage parentStage) {
		this.setTitle("请稍后");
		this.initStyle(StageStyle.TRANSPARENT);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setAlwaysOnTop(true);
		this.initOwner((Window) parentStage);
		this.mController = (WaitDialogController) this.getFxmlLoader().getController();
		this.masker = new MaskerPane();
		this.masker.setText("请稍后...");
		this.mController.root.getChildren().add(this.masker);
		Hyperlink link = new Hyperlink("关闭");
		link.setAlignment(Pos.CENTER);
		link.setTextFill((Paint) Color.WHITE);
		this.mController.root.getChildren().add(link);
		AnchorPane.setBottomAnchor((Node) link, (Double) 2.0);
		AnchorPane.setLeftAnchor((Node) link, (Double) 5.0);
		AnchorPane.setRightAnchor((Node) link, (Double) 5.0);
		link.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				WaitDialog.this.close();
			}
		});
	}

	public void setText(String text) {
		this.masker.setText(text);
	}

}