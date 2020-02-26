package com.xwintop.xcore.util.javafx;

import com.xwintop.xcore.XCoreException;
import java.net.URL;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * 用于快速创建一些 Node 对象
 */
public class FxBuilders {

    public static TextField textField(String text, double prefWidth) {
        TextField textField = new TextField(text);
        if (prefWidth > 0) {
            textField.setPrefWidth(prefWidth);
        }
        return textField;
    }

    public static Label label(String text) {
        return new Label(text);
    }

    public static Label label(String text, double maxWidth) {
        Label label = label(text);
        if (maxWidth > 0) {
            label.setWrapText(true);
            label.setMaxWidth(maxWidth);
        }
        return label;
    }

    public static VBox vbox(double padding, double spacing, Node... children) {
        VBox vBox = new VBox(spacing, children);
        vBox.setPadding(new Insets(padding));
        return vBox;
    }

    public static Button button(String text, Runnable action) {
        Button button = new Button(text);
        button.setOnAction(event -> action.run());
        return button;
    }

    public static Image icon(String resourcePath) {
        URL resource = FxBuilders.class.getResource(resourcePath);
        if (resource == null) {
            throw new XCoreException("Resource '" + resourcePath + "' not found.");
        }
        return new Image(resource.toExternalForm());
    }

    public static ImageView iconView(Image icon) {
        return new ImageView(icon);
    }

    public static ImageView iconView(Image icon, double size) {
        ImageView imageView = iconView(icon);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        return imageView;
    }
}
