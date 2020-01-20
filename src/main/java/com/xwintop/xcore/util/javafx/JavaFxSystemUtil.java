package com.xwintop.xcore.util.javafx;

import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Slf4j
public class JavaFxSystemUtil {
    /**
     * @param directoryPath 目录路径
     * @Description 打开目录
     */
    public static void openDirectory(String directoryPath) {
        try {
            Desktop.getDesktop().open(new File(directoryPath));
        } catch (IOException e) {
            log.error("打开目录异常：" + directoryPath, e);
        }
    }
}
