package com.xwintop.xcore.javafx.helper;

import com.xwintop.xcore.XCoreException;
import com.xwintop.xcore.util.javafx.FxmlUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FxmlHelper {

    public static FXMLLoader loadFromResource(String resourcePath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    FxmlUtil.class.getResource(resourcePath)
            );
            fxmlLoader.load();
            return fxmlLoader;
        } catch (IOException e) {
            throw new XCoreException(e);
        }
    }

    public static Stage loadIntoStage(String fxml, Stage stage) {
        stage.setScene(new Scene(
                loadFromResource(fxml).getRoot()
        ));
        return stage;
    }
}
