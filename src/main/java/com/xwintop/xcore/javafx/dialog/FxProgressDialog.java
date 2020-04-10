package com.xwintop.xcore.javafx.dialog;

import static com.xwintop.xcore.javafx.helper.LayoutHelper.hbox;
import static com.xwintop.xcore.javafx.helper.LayoutHelper.vbox;
import static javafx.stage.WindowEvent.WINDOW_SHOWN;

import com.xwintop.xcore.javafx.FxApp;
import java.text.DecimalFormat;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * 执行后台任务的同时打开一个置顶的进度窗口，用户可以通过点击取消按钮中止任务执行
 */
public class FxProgressDialog {

    public static final DecimalFormat FORMAT = new DecimalFormat("#.00%");

    public static void showProgress(Stage owner, ProgressTask progressTask, String message) {
        showProgress0(owner, progressTask, message, false);
    }

    public static void showProgressAndWait(Stage owner, ProgressTask progressTask, String message) {
        showProgress0(owner, progressTask, message, true);
    }

    public static void showProgress0(Stage owner, ProgressTask progressTask, String message, boolean wait) {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return progressTask;
            }
        };

        FxDialog<Void> fxDialog = new FxDialog<Void>()
            .setOwner(owner)
            .setBody(progressBody(progressTask, message))
            .setButtonTypes(ButtonType.CANCEL)
            .setButtonHandler(ButtonType.CANCEL, (event, stage) -> service.cancel())
            .setCloseable(false)
            .withStage(stage -> {
                stage.addEventHandler(WINDOW_SHOWN, event -> service.start());
                service.setOnSucceeded(event -> stage.close());
                service.setOnCancelled(event -> stage.close());
                service.setOnFailed(event -> stage.close());
            });

        if (wait) {
            fxDialog.showAndWait();
        } else {
            fxDialog.show();
        }
    }

    private static Parent progressBody(ProgressTask progressTask, String message) {
        Label progressLabel = new Label();
        ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefHeight(25);
        progressBar.setPrefWidth(300);

        progressTask.progressProperty().addListener(
            (observable, oldValue, newValue) -> updateProgress(progressTask, progressBar, progressLabel)
        );

        Label messageLabel = new Label();
        messageLabel.textProperty().bind(progressTask.messageProperty());
        progressTask.updateMessage(message);

        return vbox(0, 5, Pos.CENTER,
            hbox(5, 0, messageLabel),
            new StackPane(progressBar, progressLabel)
        );
    }

    private static void updateProgress(
        ProgressTask progressTask, ProgressBar progressBar, Label progressLabel
    ) {
        FxApp.runLater(() -> {
            double progress = progressTask.getProgress();
            progressBar.setProgress(progress);
            progressLabel.setText(FORMAT.format(progress));
        });
    }
}
