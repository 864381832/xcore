/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.testJjfxUi;

import com.xwintop.javaFX.jjfx.JJBaseFrom;
import com.xwintop.javaFX.jjfx.view_wait.JJWaitView;
import com.xwintop.javaFX.jjfx.view_wait.JJWaitView.OnCancel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TestForm extends JJBaseFrom<TestFormController> {
	public TestForm() {
		super("javafx/jjfx/TestForm.fxml");
		this.getController().combox.setEditable(false);
		this.getController().combox.getItems()
				.addAll(new String[] { "awseftasdf", "awseftasdf", "awseftasdf", "awseftasdf", "awseftasdf" });
		this.getController().test.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				TestForm.this.showToast_center("我爱你");
				TestForm.this.showToast_down("呵呵呵");
//				new JJWaitView("test", TestForm.this, new OnCancel() {
//					@Override
//					public void cancel() {
//						System.out.println("cancel");
//					}
//				});
			}
		});
	}

}