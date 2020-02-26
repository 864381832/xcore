package com.xwintop.xcore.util.javafx;

import static com.xwintop.xcore.util.javafx.AlertUtil.*;
import static com.xwintop.xcore.util.javafx.FxBuilders.button;
import static com.xwintop.xcore.util.javafx.FxBuilders.vbox;

import com.xwintop.xcore.FxApp;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AlertUtilTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FxApp.init(primaryStage, "/icon.png");
        primaryStage.setScene(new Scene(
            vbox(10, 10,
                button("打开 info 对话框", this::alertInfo),
                button("打开 confirm 对话框", this::alertConfirm),
                button("打开 input 对话框", this::alertInput)
            ),
            400, 300
        ));
        primaryStage.show();
    }

    private void alertInput() {
        String result = showInputAlertDefaultValue(""
            + "意大利疫情扩散，部分地区采取“武汉式封城”，"
            + "德国之声记者来到距离隔离区不远的北部村庄Vittadone，"
            + "那里的居民目前处在怎样的生活状态？", "hahahahahahahahaha");

        showInfoAlert("你输入了\n" + result);
    }

    private void alertConfirm() {
        if (showConfirmAlert("黑白橘子能有黑白桃子那么中二么？？")) {
            showInfoAlert("你选择了是。");
        } else {
            showInfoAlert("你选择了否。");
        }
    }

    private void alertInfo() {
        showInfoAlert("华为这里面什么都没有啊");
    }
}