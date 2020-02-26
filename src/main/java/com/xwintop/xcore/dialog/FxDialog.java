package com.xwintop.xcore.dialog;

import com.xwintop.xcore.util.javafx.FxmlUtil;
import com.xwintop.xcore.util.javafx.JavaFxViewUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Separator;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 自定义对话框
 * @param <T> 对话框的 Controller 类型
 */
public class FxDialog<T> {

    private Stage owner;

    private String bodyFxmlPath;

    private String title;

    private ButtonType[] buttonTypes;

    private Map<ButtonType, BiConsumer<ActionEvent, Stage>> buttonHandlers = new HashMap<>();

    public FxDialog(Stage owner, String bodyFxmlPath, String title, ButtonType... buttonTypes) {
        this.owner = owner;
        this.bodyFxmlPath = bodyFxmlPath;
        this.title = title;
        this.buttonTypes = buttonTypes;
    }

    public void setButtonHandler(ButtonType buttonType, BiConsumer<ActionEvent, Stage> buttonHandler) {
        this.buttonHandlers.put(buttonType, buttonHandler);
    }

    public T show() {
        FXMLLoader fxmlLoader = FxmlUtil.loadFxmlFromResource(this.bodyFxmlPath);
        Stage stage = createStage(fxmlLoader.getRoot());
        stage.show();
        return fxmlLoader.getController();
    }

    private Stage createStage(Parent content) {
        VBox dialogContainer = new VBox(content, new Separator());
        VBox.setVgrow(content, Priority.ALWAYS);

        dialogContainer.setPadding(new Insets(5));
        dialogContainer.setSpacing(5);

        Stage stage = JavaFxViewUtil.jfxStage(
            this.owner, this.title, null, dialogContainer, false, true, true
        );

        dialogContainer.getChildren().add(buttonsPanel(stage));
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
}
