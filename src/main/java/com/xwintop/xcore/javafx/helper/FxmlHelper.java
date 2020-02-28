package com.xwintop.xcore.javafx.helper;

import com.xwintop.xcore.XCoreException;
import com.xwintop.xcore.util.javafx.FxmlUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FxmlHelper {

    // 根据路径加载 FXML 资源，并返回 FXMLLoader 对象
    public static FXMLLoader loadFromResource(String resourcePath) {
        return loadFromResource(resourcePath, null);
    }

    // 根据路径和 resourceBundleName 加载 FXML 资源，并返回 FXMLLoader 对象
    public static FXMLLoader loadFromResource(String resourcePath, String resourceBundleName) {
        try {
            FXMLLoader fxmlLoader;
            URL url = FxmlUtil.class.getResource(resourcePath);

            if (resourceBundleName != null) {
                fxmlLoader = new FXMLLoader(url, ResourceBundle.getBundle(resourceBundleName));
            } else {
                fxmlLoader = new FXMLLoader(url);
            }

            fxmlLoader.load();
            return fxmlLoader;
        } catch (IOException e) {
            throw new XCoreException(e);
        }
    }

    public static Stage loadIntoStage(String fxml, Stage stage) {
        return loadIntoStage(fxml, stage, null);
    }

    public static Stage loadIntoStage(String fxml, Stage stage, String resourceBundleName) {
        stage.setScene(new Scene(
            loadFromResource(fxml, resourceBundleName).getRoot()
        ));
        return stage;
    }
}
