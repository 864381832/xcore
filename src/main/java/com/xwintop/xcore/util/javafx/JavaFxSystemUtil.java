package com.xwintop.xcore.util.javafx;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

//@Slf4j
public class JavaFxSystemUtil {
    private static Logger log = org.slf4j.LoggerFactory.getLogger(JavaFxSystemUtil.class);
    /**
     * @deprecated 使用 {@link com.xwintop.xcore.javafx.FxApp#primaryStage}
     */
    @Deprecated
    public static Stage mainStage = null;

    /**
     * 打开目录
     *
     * @param directoryPath 目录路径
     */
    public static void openDirectory(String directoryPath) {
        try {
            Desktop.getDesktop().open(new File(directoryPath));
        } catch (IOException e) {
            log.error("打开目录异常：" + directoryPath, e);
        }
    }

    /**
     * 获取屏幕尺寸
     *
     * @param width  宽度比
     * @param height 高度比
     * @return 屏幕尺寸
     */
    public static double[] getScreenSizeByScale(double width, double height) {
//        if (WebAPI.isBrowser()) {
//            return new double[]{1280, 900};
//        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.width * width;
        double screenHeight = screenSize.height * height;
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        if (screenWidth > bounds.getWidth() || screenHeight > bounds.getHeight()) {//解决屏幕缩放问题
            screenWidth = bounds.getWidth();
            screenHeight = bounds.getHeight();
        }
        return new double[]{screenWidth, screenHeight};
    }
}
