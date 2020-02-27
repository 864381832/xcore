package com.xwintop.xcore.javafx.dialog;

import com.xwintop.xcore.XCoreException;
import com.xwintop.xcore.javafx.FxApp;
import com.xwintop.xcore.util.javafx.FxmlUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Separator;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.lang3.ArrayUtils;

/**
 * 自定义对话框
 *
 * @param <T> 对话框的 Controller 类型
 */
public class FxDialog<T> {

    private boolean modal = true;

    private boolean resizable = false;

    private double prefWidth;

    private double prefHeight;

    private Stage owner;

    private String bodyFxmlPath;

    private Parent body;

    private String title;

    private ButtonType[] buttonTypes;

    private Map<ButtonType, BiConsumer<ActionEvent, Stage>> buttonHandlers = new HashMap<>();

    public FxDialog<T> setResizable(boolean resizable) {
        this.resizable = resizable;
        return this;
    }

    public FxDialog<T> setPrefHeight(double prefHeight) {
        this.prefHeight = prefHeight;
        return this;
    }

    public FxDialog<T> setPrefWidth(double prefWidth) {
        this.prefWidth = prefWidth;
        return this;
    }

    public FxDialog<T> setTitle(String title) {
        this.title = title;
        return this;
    }

    public FxDialog<T> setOwner(Stage owner) {
        this.owner = owner;
        return this;
    }

    public FxDialog<T> setBody(Parent body) {
        this.body = body;
        return this;
    }

    public FxDialog<T> setBodyFxml(String bodyFxmlPath) {
        this.bodyFxmlPath = bodyFxmlPath;
        return this;
    }

    public FxDialog<T> setButtonTypes(ButtonType... buttonTypes) {
        this.buttonTypes = buttonTypes;
        return this;
    }

    public FxDialog<T> setModal(boolean modal) {
        this.modal = modal;
        return this;
    }

    public FxDialog<T> setButtonHandler(ButtonType buttonType, BiConsumer<ActionEvent, Stage> buttonHandler) {
        this.buttonHandlers.put(buttonType, buttonHandler);
        return this;
    }

    public T show() {
        if (this.bodyFxmlPath != null) {
            FXMLLoader fxmlLoader = FxmlUtil.loadFxmlFromResource(this.bodyFxmlPath);
            Stage stage = createStage(fxmlLoader.getRoot());
            stage.show();
            return fxmlLoader.getController();
        }

        if (this.body != null) {
            Stage stage = createStage(this.body);
            stage.show();
            return null;
        }

        throw new XCoreException("bodyFxmlPath 和 body 不能都为空");
    }

    public T showAndWait() {
        if (this.bodyFxmlPath != null) {
            FXMLLoader fxmlLoader = FxmlUtil.loadFxmlFromResource(this.bodyFxmlPath);
            Stage stage = createStage(fxmlLoader.getRoot());
            stage.showAndWait();
            return fxmlLoader.getController();
        }

        if (this.body != null) {
            Stage stage = createStage(this.body);
            stage.showAndWait();
            return null;
        }

        throw new XCoreException("bodyFxmlPath 和 body 不能都为空");
    }

    private Stage createStage(Parent content) {
        VBox dialogContainer = new VBox(content);
        VBox.setVgrow(content, Priority.ALWAYS);

        dialogContainer.setPadding(new Insets(5));
        dialogContainer.setSpacing(5);

        Stage stage = new Stage();
        if (ArrayUtils.isNotEmpty(this.buttonTypes)) {
            dialogContainer.getChildren().add(new Separator());
            dialogContainer.getChildren().add(buttonsPanel(stage));
        }

        stage.setTitle(title);
        stage.setScene(new Scene(dialogContainer));
        stage.setResizable(this.resizable);

        if (FxApp.appIcon != null) {
            stage.getIcons().add(FxApp.appIcon);
        }

        if (this.modal) {
            if (this.owner != null) {
                stage.initOwner(this.owner);
                stage.initModality(Modality.WINDOW_MODAL);
                adjustPosition(stage, owner);
            } else {
                stage.initModality(Modality.APPLICATION_MODAL);
            }
        }

        if (this.prefWidth > 0) {
            stage.setWidth(this.prefWidth);
        }

        if (this.prefHeight > 0) {
            stage.setHeight(this.prefHeight);
        }

        return stage;
    }

    private ButtonBar buttonsPanel(Stage stage) {
        ButtonBar buttonBar = new ButtonBar();
        buttonBar.getButtons().addAll(
            Stream.of(this.buttonTypes)
                .map(buttonType -> createButton(buttonType, stage))
                .collect(Collectors.toList())
        );
        return buttonBar;
    }

    private Button createButton(ButtonType buttonType, Stage stage) {
        final Button button = new Button(buttonType.getText());
        final ButtonData buttonData = buttonType.getButtonData();
        ButtonBar.setButtonData(button, buttonData);
        button.setDefaultButton(buttonData.isDefaultButton());
        button.setCancelButton(buttonData.isCancelButton());
        button.setOnAction(event -> {
            BiConsumer<ActionEvent, Stage> handler = this.buttonHandlers.get(buttonType);
            if (handler != null) {
                handler.accept(event, stage);
            }
        });
        return button;
    }

    private void adjustPosition(Stage dialog, Stage owner) {
        dialog.addEventHandler(WindowEvent.WINDOW_SHOWN, event -> {
            dialog.setX(Math.max(0, owner.getX() + owner.getWidth() / 2 - dialog.getWidth() / 2));
            dialog.setY(Math.max(0, owner.getY() + owner.getHeight() / 2 - dialog.getHeight() / 2));
        });
    }
}
