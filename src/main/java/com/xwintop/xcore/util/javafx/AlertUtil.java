package com.xwintop.xcore.util.javafx;

import com.xwintop.xcore.javafx.FxApp;
import com.xwintop.xcore.javafx.dialog.FxDialog;
import com.xwintop.xcore.javafx.helper.LayoutHelper;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import static com.xwintop.xcore.javafx.helper.LayoutHelper.label;
import static com.xwintop.xcore.javafx.helper.LayoutHelper.vbox;

public class AlertUtil {

    /**
     * 信息提示框
     */
    public static void showInfoAlert(String message) {
        showInfoAlert("提示", message);
    }

    /**
     * 信息提示框
     */
    public static void showInfoAlert(String title, String message) {
        new FxDialog<>(
            FxApp.primaryStage, vbox(10, 0, label(message)), title
        ).showAndWait();
    }

    /**
     * 确定提示框
     */
    public static boolean confirmYesNo(String title, String message) {
        return confirm(title, message, ButtonType.YES, ButtonType.NO) == ButtonType.YES;
    }

    /**
     * 确定提示框
     */
    public static boolean confirmOkCancel(String title, String message) {
        return confirm(title, message, ButtonType.OK, ButtonType.CANCEL) == ButtonType.OK;
    }

    /**
     * 确定提示框
     */
    public static ButtonType confirmYesNoCancel(String title, String message) {
        return confirm(title, message, ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    }

    /**
     * 确定提示框
     */
    public static ButtonType confirm(
        String title, String message, ButtonType positiveButtonType, ButtonType... negativeButtonTypes
    ) {

        // 构造 buttonTypes
        ButtonType[] buttonTypes = new ButtonType[
            (negativeButtonTypes == null ? 1 : negativeButtonTypes.length) + 1];

        buttonTypes[0] = positiveButtonType;

        if (negativeButtonTypes != null) {
            System.arraycopy(negativeButtonTypes, 0, buttonTypes, 1, negativeButtonTypes.length);
        }

        // 构造对话框
        FxDialog<Object> dialog = new FxDialog<>(
            FxApp.primaryStage, vbox(10, 0, label(message)), title,
            buttonTypes
        );

        ButtonType[] result = new ButtonType[]{ButtonType.CANCEL};

        dialog.setButtonHandler(positiveButtonType, (actionEvent, stage) -> {
            result[0] = positiveButtonType;
            stage.close();
        });

        if (negativeButtonTypes != null) {
            for (ButtonType negativeButtonType : negativeButtonTypes) {
                dialog.setButtonHandler(negativeButtonType, (actionEvent, stage) -> {
                    result[0] = negativeButtonType;
                    stage.close();
                });
            }
        }

        // 显示对话框
        dialog.showAndWait();
        return result[0];
    }

    /**
     * 输入提示框
     */
    public static String showInputAlertDefaultValue(String message, String defaultValue) {
        String[] result = new String[]{defaultValue};

        TextField textField = LayoutHelper.textField(defaultValue, 200);
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

}
