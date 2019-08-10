package com.xwintop.xcore.util.javafx;

import java.io.File;
import java.nio.charset.Charset;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.io.FileUtils;

import com.xwintop.xcore.util.MyLogger;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * @author jeremie
 */

/**
 * @ClassName: FileChooserUtil
 * @Description: 文件选择工具
 * @author: xufeng
 * @date: 2017年7月25日 上午10:09:15
 */
public class FileChooserUtil {

    private static MyLogger myLogger = new MyLogger(FileChooserUtil.class);

    /**
     * @Title: chooseFile
     * @Description: 选择文件
     */
    public static File chooseFile() {
        return chooseFile(null);
    }

    /**
     * @Title: chooseFile
     * @Description: 选择文件
     */
    public static File chooseFile(ExtensionFilter... extensionFilter) {
        File file = null;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("请选择文件");
            fileChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
            if (extensionFilter != null) {
                fileChooser.getExtensionFilters().addAll(extensionFilter);
                // fileChooser.getExtensionFilters().addAll(new
                // FileChooser.ExtensionFilter("文本文件 (*.*.txt)", "*.txt"),
                // new FileChooser.ExtensionFilter("二进制的对象文件 (*.*.dat)", "*.dat"));
            }
            file = fileChooser.showOpenDialog(null);
        } catch (NullPointerException e) {
            e.printStackTrace();
            myLogger.error(e);
        }
        return file;
    }

    /**
     * @Title: chooseSaveFile
     * @Description: 选择保存文件
     */
    public static File chooseSaveFile(ExtensionFilter... extensionFilter) {
        return chooseSaveFile(null, extensionFilter);
    }

    /**
     * @Title: chooseSaveFile
     * @Description: 选择保存文件
     */
    public static File chooseSaveFile(String fileName) {
        return chooseSaveFile(fileName, null);
    }

    /**
     * @Title: chooseSaveFile
     * @Description: 选择保存文件
     */
    public static File chooseSaveFile(String fileName, ExtensionFilter... extensionFilter) {
        File file = null;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
            if (fileName != null) {
                fileChooser.setInitialFileName(fileName);
            }
            if (extensionFilter != null) {
                fileChooser.getExtensionFilters().addAll(extensionFilter);
                // fileChooser.getExtensionFilters().addAll(new
                // FileChooser.ExtensionFilter("文本文件 (*.*.txt)", "*.txt"),
                // new FileChooser.ExtensionFilter("二进制的对象文件 (*.*.dat)", "*.dat"));
            }
            file = fileChooser.showSaveDialog(null);
        } catch (NullPointerException e) {
            e.printStackTrace();
            myLogger.error(e);
        }
        return file;
    }

    /**
     * @Title: chooseSaveImageFile
     * @Description: 选择保存图片文件
     */
    public static File chooseSaveCommonImageFile(String fileName) {
        File file = chooseSaveFile(fileName, new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("gif", "*.gif"), new FileChooser.ExtensionFilter("jpeg", "*.jpeg"),
                new FileChooser.ExtensionFilter("bmp", "*.bmp"));
        return file;
    }

    /**
     * @Title: chooseSaveImageFile
     * @Description: 选择保存图片文件
     */
    public static File chooseSaveImageFile(String fileName) {
        File file = chooseSaveFile(fileName, new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("gif", "*.gif"),
                new FileChooser.ExtensionFilter("jpeg", "*.jpeg"),
                new FileChooser.ExtensionFilter("bmp", "*.bmp"),
                new FileChooser.ExtensionFilter("ICO", "*.ico"),
                new FileChooser.ExtensionFilter("RGBE", "*.rgbe"));
        return file;
    }

    /**
     * @return 所选择文件夹
     * @Title: chooseDirectory
     * @Description: 选择文件夹
     */
    public static File chooseDirectory() {
        File file = null;
        try {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            file = directoryChooser.showDialog(null);
        } catch (NullPointerException e) {
            e.printStackTrace();
            myLogger.error(e);
        }
        return file;
    }

    /**
     * @Title: setOnDrag
     * @Description: 添加文件拖拽获取路径
     */
    public static void setOnDrag(TextField textField, FileType fileType) {
        textField.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != textField) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
            }
        });
        textField.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard dragboard = event.getDragboard();
                if (dragboard.hasFiles()) {
                    try {
                        File file = dragboard.getFiles().get(0);
                        if (file != null) {
                            if (fileType == FileType.FILE) {
                                if (file.isFile()) {
                                    textField.setText(file.getAbsolutePath());
                                }
                            } else if (fileType == FileType.FOLDER) {
                                if (file.isDirectory()) {
                                    textField.setText(file.getAbsolutePath());
                                }
                            }
                        }
                    } catch (Exception e) {
                        myLogger.error(e);
                    }
                }
            }
        });
    }

    /**
     * @Title: setOnDrag
     * @Description: 添加文件拖拽获取文件内容
     */
    public static void setOnDragByOpenFile(TextInputControl textField) {
        textField.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                if (event.getGestureSource() != textField) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
            }
        });
        textField.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard dragboard = event.getDragboard();
                if (dragboard.hasFiles()) {
                    try {
                        File file = dragboard.getFiles().get(0);
                        if (file != null) {
                            if (file.isFile()) {
                                textField.setAccessibleText(file.getAbsolutePath());
                                textField.setText(FileUtils.readFileToString(file, Charset.defaultCharset()));
                            }
                        }
                    } catch (Exception e) {
                        myLogger.error(e);
                    }
                }
            }
        });
    }

    public enum FileType {
        FILE, FOLDER
    }
}
