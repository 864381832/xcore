package com.xwintop.xcore.util.javafx;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class AlertUtil {
	/**
	 * 信息提示框
	 * @param message
	 */
	public static void showInfoAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(message);
		alert.show();
	}
	public static void showInfoAlert(String title,String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setContentText(message);
		alert.show();
	}
	/**
	 * 等待信息提示框
	 * @param message
	 */
	public static void showAndWaitInfoAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/**
	 * 注意提示框
	 * @param message
	 */
	public static void showWarnAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setContentText(message);
		alert.show();
	}

	/**
	 * 异常提示框
	 * @param message
	 */
	public static void showErrorAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(message);
		alert.show();
	}

	/**
	 *确定提示框
	 * @param message
	 */
	public static boolean showConfirmAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setContentText(message);
		Optional<ButtonType> optional = alert.showAndWait();
		if (ButtonType.OK == optional.get()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static String showInputAlert(String message){
		TextField textField = new TextField();
		Alert alert = new Alert(Alert.AlertType.NONE, null, new ButtonType("取消", ButtonBar.ButtonData.NO),
				new ButtonType("确定", ButtonBar.ButtonData.YES));
		alert.setTitle(message);
		alert.setGraphic(textField);
		alert.setWidth(200);
		Optional<ButtonType> _buttonType = alert.showAndWait();
		// 根据点击结果返回
		if (_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
			return textField.getText();
		}
		return null;
	}
	
	public static String[] showInputAlert(String message,String... names){
		int row = 0;
		GridPane page1Grid = new GridPane();
		page1Grid.setVgap(10);
		page1Grid.setHgap(10);

		TextField[] textFields = new TextField[names.length];
		for(String string:names){
			TextField textField = new TextField();
			GridPane.setHgrow(textField, Priority.ALWAYS);
			page1Grid.add(new Label(string), 0, row);
			page1Grid.add(textField, 1, row);
			textFields[row]=textField;
			row++;
		}

		Alert alert = new Alert(Alert.AlertType.NONE, null, new ButtonType("取消", ButtonBar.ButtonData.NO),
				new ButtonType("确定", ButtonBar.ButtonData.YES));
		alert.setTitle(message);
		alert.setGraphic(page1Grid);
		alert.setWidth(200);
		Optional<ButtonType> _buttonType = alert.showAndWait();
		// 根据点击结果返回
		if (_buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
			String[] stringS = new String[names.length]; 
			for(int i=0;i<textFields.length;i++){
				stringS[i] = textFields[i].getText();
			}
			return stringS;
		}
		return null;
	}
}
