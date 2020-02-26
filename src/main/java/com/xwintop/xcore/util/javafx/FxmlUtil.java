package com.xwintop.xcore.util.javafx;

import com.xwintop.xcore.XCoreException;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class FxmlUtil {

    public static FXMLLoader loadFxmlFromResource(String resourcePath) {
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
}
