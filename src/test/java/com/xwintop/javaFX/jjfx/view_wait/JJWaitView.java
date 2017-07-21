/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.view_wait;

import com.xwintop.javaFX.jjfx.JJBaseFrom;
import com.xwintop.javaFX.jjfx.JJBaseView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressIndicator;

public class JJWaitView extends JJBaseView<WaitViewController> {
	public JJWaitView(String text, JJBaseFrom from, OnCancel onCancel) {
		super("javafx/jjfx/WaitView.fxml", from);
		this.init(text, onCancel);
	}

	private void init(String text, final OnCancel onCancel) {
		ProgressIndicator progressIndicator = new ProgressIndicator();
		progressIndicator.setMaxWidth(40.0);
		progressIndicator.setMaxHeight(40.0);
		this.getController().borderpane.setLeft(progressIndicator);
		this.getController().tv_title.setText(text);
		if (onCancel != null) {
			this.getController().link_cancel.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					onCancel.cancel();
				}
			});
		} else {
			this.getController().borderpane.setRight(null);
		}
	}

	public static interface OnCancel {
		public void cancel();
	}

}