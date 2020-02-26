package com.xwintop.xcore.util.javafx;

import static com.xwintop.xcore.util.javafx.FxBuilders.label;
import static com.xwintop.xcore.util.javafx.FxBuilders.vbox;

import com.xwintop.xcore.FxApp;
import com.xwintop.xcore.dialog.FxDialog;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AlertUtil {

    /**
     * 信息提示框
     */
    public static void showInfoAlert(String message) {
        showInfoAlert("提示", message);
    }

    public static void showInfoAlert(String title, String message) {
        new FxDialog<>(
            FxApp.primaryStage, vbox(10, 0, label(message)), title
        ).showAndWait();
    }

    /**
     * 确定提示框
     */
    public static boolean showConfirmAlert(String message) {

        FxDialog<Object> dialog = new FxDialog<>(
            FxApp.primaryStage, vbox(10, 0, label(message)), "提示",
            ButtonType.YES, ButtonType.NO
        );

        Boolean[] result = new Boolean[]{false};

        dialog.setButtonHandler(ButtonType.YES, (actionEvent, stage) -> {
            result[0] = true;
            stage.close();
        });
        dialog.setButtonHandler(ButtonType.NO, (actionEvent, stage) -> {
            result[0] = false;
            stage.close();
        });

        dialog.showAndWait();
        return result[0];
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
        String[] result = new String[]{defaultValue};

        TextField textField = FxBuilders.textField(defaultValue, 200);
        VBox body = vbox(10, 10, label(message, 300), textField);

        new FxDialog<>(FxApp.primaryStage, body, "提示", ButtonType.OK, ButtonType.CANCEL)
            .setButtonHandler(ButtonType.OK, (actionEvent, stage) -> {
                result[0] = textField.getText();
                stage.close();
            })
            .setButtonHandler(ButtonType.CANCEL, (actionEvent, stage) -> stage.close())
            .showAndWait();

        return result[0];
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
