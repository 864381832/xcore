package com.xwintop.xcore.javafx;

import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.xwintop.xcore.javafx.helper.LayoutHelper.icon;

/**
 * 用于存储 App 全局的相关属性
 */
public class FxApp {

    // 获得 App 主窗体
    public static Stage primaryStage;

    // 获得 App 图标，用于主窗体和对话框上
    public static Image appIcon;

    // 获得全局 CSS，用于主窗体和对话框上
    public static final List<String> styleSheets = new ArrayList<>();

    // 初始化全局属性
    public static void init(Stage primaryStage, String iconPath) {
        FxApp.primaryStage = primaryStage;
        FxApp.appIcon = icon(iconPath);

        primaryStage.getIcons().add(FxApp.appIcon);
    }
}
