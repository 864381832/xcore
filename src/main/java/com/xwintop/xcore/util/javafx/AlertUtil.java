package com.xwintop.xcore.util.javafx;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.util.Optional;

public class AlertUtil {
    /**
     * 信息提示框
     *
     * @param message
     */
    public static void showInfoAlert(String message) {
        showInfoAlert("提示", message);
    }

    public static void showInfoAlert(String title, String message) {
        Label textArea = new Label(message);
        textArea.setFont(Font.font(18));
        TextFlow textFlow = new TextFlow(textArea);
        textFlow.setTextAlignment(TextAlignment.CENTER);
        textFlow.setPadding(new Insets(15, 15, 15, 15));
        JavaFxViewUtil.openNewWindow(title, null, textFlow, textFlow.getPrefWidth(), textFlow.getPrefHeight());
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setContentText(message);
//        alert.show();
    }

    /**
     * 等待信息提示框
     *
     * @param message
     */
    public static void showAndWaitInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * 注意提示框
     *
     * @param message
     */
    public static void showWarnAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * 异常提示框
     *
     * @param message
     */
    public static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    /**
     * 确定提示框
     *
     * @param message
     */
    public static boolean showConfirmAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(message);
        Optional<ButtonType> optional = alert.showAndWait();
        if (ButtonType.OK == optional.get()) {
            return true;
        } else {
            return false;
        }
    }

    @Deprecated
    public static String showInputAlert(String message) {
        return showInputAlertDefaultValue(message, null);
    }

    @Deprecated
    public static String[] showInputAlert(String message, String... names) {
        return showInputAlertMore(message, names);
    }

    public static String showInputAlertDefaultValue(String message, String defaultValue) {
        TextField textField = new TextField();
        textField.setText(defaultValue);
        textField.setMinWidth(100);
        textField.prefColumnCountProperty().bind(textField.textProperty().length());
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

    public static String[] showInputAlertMore(String message, String... names) {
        return showInputAlertMore(message, names, new String[names.length]);
    }

    public static String[] showInputAlertMore(String message, String[] names, String[] defaultValue) {
        GridPane page1Grid = new GridPane();
        page1Grid.setVgap(10);
        page1Grid.setHgap(10);

        TextField[] textFields = new TextField[names.length];
        for (int i = 0; i < names.length; i++) {
            TextField textField = new TextField();
            textField.setText(defaultValue[i]);
            textField.setMinWidth(100);
            textField.prefColumnCountProperty().bind(textField.textProperty().length());
            GridPane.setHgrow(textField, Priority.ALWAYS);
            page1Grid.add(new Label(names[i]), 0, i);
            page1Grid.add(textField, 1, i);
            textFields[i] = textField;
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
            for (int i = 0; i < textFields.length; i++) {
                stringS[i] = textFields[i].getText();
            }
            return stringS;
        }
        return null;
    }
}
