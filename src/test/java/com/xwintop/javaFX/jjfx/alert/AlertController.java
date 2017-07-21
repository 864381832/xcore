/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx.alert;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AlertController {
	public Label messageLabel;
	public VBox vbox;
	private JJDialog mJJDialog;

	public void setJJDialog(JJDialog JJDialog2) {
		this.mJJDialog = JJDialog2;
	}

	public JJDialog getmJJDialog() {
		return mJJDialog;
	}

}