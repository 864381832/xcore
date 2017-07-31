package com.xwintop.xcore.util.javafx;

import java.util.Timer;
import java.util.TimerTask;

import org.controlsfx.tools.Utils;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.stage.Window;

public class TooltipUtil {
	public static void showToast(String message) {
		showToast(null,message);
	}
	public static void showToast(Node node, String message) {
		Window window = Utils.getWindow(node);
		double x = 0;
		double y = 0;
		if(node != null){
			x = GetScreenUtil.getScreenX(node)+GetScreenUtil.getWidth(node)/2;
			y = GetScreenUtil.getScreenY(node)+GetScreenUtil.getHeight(node);
		}else{
			x = window.getX() + window.getWidth()/2;
			y = window.getY()+ window.getHeight();
		}
		showToast(window, message, 3000, x, y);
	}

	public static void showToast(Window window, String message, long time, double x, double y) {
		Tooltip tooltip = new Tooltip(message);
		tooltip.setAutoHide(true);
		tooltip.setOpacity(0.9d);
		tooltip.setWrapText(true);
		tooltip.show(window, x, y);
		if (time > 0) {
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							tooltip.hide();
						}
					});
				}
			}, time);
		}
	}
}
