package com.xwintop.xcore.javafx.dialog;

import static com.xwintop.xcore.javafx.helper.LayoutHelper.button;
import static com.xwintop.xcore.javafx.helper.LayoutHelper.vbox;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxProgressDialogTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(
            vbox(50, 0, Pos.CENTER, button("开始执行某个重要任务", () -> startProgress(primaryStage))), 300, 100
        ));
        primaryStage.show();
    }

    private void startProgress(Stage primaryStage) {

        ProgressTask progressTask = new ProgressTask() {
            @Override
            protected void execute() throws Exception {
                while (getCurrentProgress() < getMaxProgress()) {
                    addProgress(4);
                    Thread.sleep(200);
                }
            }
        };

        progressTask.setOnCancelled(event -> System.out.println("用户取消了"));

        FxProgressDialog.showProgress(
            primaryStage, progressTask, "正在执行某个重要任务..."
        );
    }
}