/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package com.xwintop.javaFX.jjfx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.URI;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.controlsfx.control.PopOver;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class JJFx {
	public static final int TOAST_TIME = 700;

	public static void openUrl(String url) {
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void openQQ(String qq) {
		JJFx.openUrl("tencent://message/?uin=" + qq + "&Site=&menu=yes");
	}

	public static void openQun(String key) {
		JJFx.openUrl("http://shang.qq.com/wpa/qunwpa?idkey=" + key);
	}

	public static void showToast(String text, Stage parentState) {
		JJFx.showToast(text, parentState, 700, JJBaseFrom.Location.center);
	}

	public static void showToast(String text, final Stage parentStage, final long hideTime,
			final JJBaseFrom.Location location) {
		final Tooltip tooltip = new Tooltip(text);
		tooltip.setOnShown(new EventHandler<WindowEvent>() {

			public void handle(WindowEvent event) {
				Tooltip stage = (Tooltip) event.getTarget();
				if (stage != null) {
					double y;
					double x;
					switch (location) {
					case up: {
						x = parentStage.getX() + parentStage.getWidth() / 2.0 - stage.getWidth() / 2.0;
						y = parentStage.getY();
						break;
					}
					case down: {
						x = parentStage.getX() + parentStage.getWidth() / 2.0 - stage.getWidth() / 2.0;
						y = parentStage.getY() + parentStage.getHeight() - stage.getHeight() - stage.getHeight() / 2.0;
						break;
					}
					default: {
						x = parentStage.getX() + parentStage.getWidth() / 2.0 - stage.getWidth() / 2.0;
						y = parentStage.getY() + parentStage.getHeight() / 2.0 - stage.getHeight() / 2.0;
					}
					}
					stage.setX(x);
					stage.setY(y);
				}
			}
		});
		tooltip.setFont(new Font(15.0));
		tooltip.setAutoHide(true);
		tooltip.setOpacity(0.9);
		tooltip.setWrapText(true);
		tooltip.show((Window) parentStage);
		if (hideTime > 0) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep(hideTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Platform.runLater(() -> {
						if (tooltip.isShowing()) {
							tooltip.hide();
						}
					});
				}
			}).start();
		}
	}

	public static boolean Screenshot(Node ScreenshotNode, Node showPopoverNode) {
		try {
			WritableImage image = ScreenshotNode.snapshot(null, null);
			Clipboard clipboard = Clipboard.getSystemClipboard();
			ClipboardContent cc = new ClipboardContent();
			cc.putImage((Image) image);
			clipboard.setContent(cc);
			PopOver popOver = new PopOver();
			ImageView imageView = new ImageView((Image) image);
			popOver.setContentNode((Node) imageView);
			popOver.show(showPopoverNode);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean copyToClipboard(String text) {
		try {
			Clipboard clipboard = Clipboard.getSystemClipboard();
			ClipboardContent cc = new ClipboardContent();
			cc.putString(text);
			clipboard.setContent(cc);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean findProcess(String processName) {
		BufferedReader br = null;
		try {
			String line;
			Process proc = Runtime.getRuntime().exec("tasklist");
			br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			System.out.println("Process info:");
			while ((line = br.readLine()) != null) {
				if (!line.contains(processName))
					continue;
				boolean bl = true;
				return bl;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static void showPopOver(String text, Node parentNode) {
		Label label = new Label(text);
		label.setPadding(new Insets(10.0));
		PopOver popOver = new PopOver(label);
		popOver.show(parentNode);
	}

	public static void showPopOver(Node node, Node parentNode) {
		PopOver popOver = new PopOver(node);
		popOver.show(parentNode);
	}

	public static HashMap<String, String> getCookies(URI uri, CookieHandler aDefault) {
		try {
			HashMap<String, String> cookies = new HashMap<String, String>();
			HashMap<String, List<String>> requestHeaders = new HashMap<String, List<String>>();
			Map<String, List<String>> cookiesListMap = aDefault.get(uri, requestHeaders);
			for (List<String> stringList : cookiesListMap.values()) {
				Iterator<String> iterator = stringList.iterator();
				while (iterator.hasNext()) {
					String strings = iterator.next();
					strings = strings.replace(" ", "");
					String[] items = strings.split(";");
					for (String item : items) {
						String[] keyvalues = item.split("=");
						if (keyvalues.length != 2)
							continue;
						cookies.put(keyvalues[0], keyvalues[1]);
					}
				}
			}
			return cookies;
		} catch (IOException e) {
			return new HashMap<String, String>();
		}
	}

	public static void threadInUI(Runnable runnable) {
		Platform.runLater((Runnable) runnable);
	}

	public static <T> void threadAnsy(final OnThreadAnsy<Object> onThreadAnsy) {
		JJFx.threadInUI(() -> {
			final Object before = onThreadAnsy.before();
			new Thread(new Runnable() {
				@Override
				public void run() {
					Object inThread = onThreadAnsy.inThread(before);
					Platform.runLater(() -> {
						onThreadAnsy.after(inThread);
					});
				}
			}).start();
		});
	}

	public static interface OnThreadAnsy<T> {
		public T before();

		public T inThread(T var1);

		public void after(T var1);
	}

}