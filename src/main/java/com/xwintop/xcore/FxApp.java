package com.xwintop.xcore;

import static com.xwintop.xcore.util.javafx.FxBuilders.icon;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 用于存储 App 全局的相关属性
 */
public class FxApp {

    // 获得 App 主窗体
    public static Stage primaryStage;

    // 获得 App 图标，用于主窗体和对话框上
    public static Image appIcon;

    // 初始化全局属性
    public static void init(Stage primaryStage, String iconPath) {
        FxApp.primaryStage = primaryStage;
        FxApp.appIcon = icon(iconPath);

        primaryStage.getIcons().add(FxApp.appIcon);
    }
}
