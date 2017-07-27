package com.xwintop.xcore.util.javafx;

import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;

public class TooltipUtil {
	public static void showToast(Node node, String message) {
		showToast(node, message, 3000, node.getLayoutX(), node.getLayoutY());
	}

	public static void showToast(Node node, String message, long time, double x, double y) {
		Tooltip tooltip = new Tooltip(message);
		tooltip.setAutoHide(true);
		tooltip.setOpacity(0.9d);
		tooltip.setWrapText(true);
		tooltip.show(node, x, y);
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
