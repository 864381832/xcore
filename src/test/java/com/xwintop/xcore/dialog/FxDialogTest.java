package com.xwintop.xcore.dialog;

import com.xwintop.xcore.FxApp;
import com.xwintop.xcore.util.javafx.FxBuilders;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FxDialogTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FxApp.init(primaryStage, "/icon.png");

        primaryStage.setScene(new Scene(new BorderPane(
            FxBuilders.vbox(10, 10,
                FxBuilders.button("Open Dialog", () -> openDialog(primaryStage)),
                FxBuilders.button("Open Dialog2", () -> openDialog2(primaryStage))
            )
        ), 400, 300));
        primaryStage.show();
    }

    private void openDialog(Stage primaryStage) {

        // 创建对话框
        FxDialog<FxDialogTestController> dialog = new FxDialog<>(
            primaryStage,
            "/sample-dialog-body.fxml",
            "对话框标题",
            ButtonType.OK, ButtonType.CANCEL
        );

        // 通过 Controller 对象初始化对话框内容
        FxDialogTestController controller = dialog.show();
        controller.initName("这是一个名字");

        // 用户如果修改了对话框内容，仍然通过 Controller 来获取
        // 这样避免了父窗体代码直接访问子窗体元素，职责混乱
        dialog.setButtonHandler(ButtonType.OK, (actionEvent, stage) -> {
            System.out.println("新的名字：" + controller.getName());
            stage.close();
        });

        dialog.setButtonHandler(ButtonType.CANCEL, (actionEvent, stage) -> {

        });
    }

    private void openDialog2(Stage primaryStage) {

        // 创建不带按钮的对话框
        FxDialog<FxDialogTestController> dialog = new FxDialog<>(
            primaryStage,
            "/sample-dialog-body.fxml",
            "对话框标题"
        );

        // 通过 Controller 对象初始化对话框内容
        FxDialogTestController controller = dialog.show();
        controller.initName("这是一个名字");
    }
}