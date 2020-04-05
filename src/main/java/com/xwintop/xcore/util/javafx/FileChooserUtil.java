package com.xwintop.xcore.util.javafx;

import com.xwintop.xcore.util.MyLogger;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.apache.commons.io.FileUtils;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.nio.charset.Charset;

/**
 * 文件选择工具
 *
 * @author xufeng
 */
public class FileChooserUtil {

    public static final File HOME_DIRECTORY = FileSystemView.getFileSystemView().getHomeDirectory();

    private static MyLogger myLogger = new MyLogger(FileChooserUtil.class);

    public static File chooseFile() {
        return chooseFile(null);
    }

    public static File chooseFile(ExtensionFilter... extensionFilter) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("请选择文件");
        fileChooser.setInitialDirectory(HOME_DIRECTORY);

        if (extensionFilter != null) {
            fileChooser.getExtensionFilters().addAll(extensionFilter);
        }

        return fileChooser.showOpenDialog(null);
    }

    ///////////////////////////////////////////////////////////////

    public static File chooseSaveFile(ExtensionFilter... extensionFilter) {
        return chooseSaveFile(null, extensionFilter);
    }

    public static File chooseSaveFile(String fileName) {
        return chooseSaveFile(fileName, null);
    }

    public static File chooseSaveFile(String fileName, ExtensionFilter... extensionFilter) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(HOME_DIRECTORY);

        if (fileName != null) {
            fileChooser.setInitialFileName(fileName);
        }

        if (extensionFilter != null) {
            fileChooser.getExtensionFilters().addAll(extensionFilter);
        }

        return fileChooser.showSaveDialog(null);
    }

    public static File chooseSaveCommonImageFile(String fileName) {
        return chooseSaveFile(fileName,
            new ExtensionFilter("All Images", "*.*"),
            new ExtensionFilter("JPG", "*.jpg"),
            new ExtensionFilter("PNG", "*.png"),
            new ExtensionFilter("GIF", "*.gif"),
            new ExtensionFilter("JPEG", "*.jpeg"),
            new ExtensionFilter("BMP", "*.bmp"));
    }

    public static File chooseSaveImageFile(String fileName) {
        return chooseSaveFile(fileName,
            new ExtensionFilter("All Images", "*.*"),
            new ExtensionFilter("JPG", "*.jpg"),
            new ExtensionFilter("PNG", "*.png"),
            new ExtensionFilter("gif", "*.gif"),
            new ExtensionFilter("jpeg", "*.jpeg"),
            new ExtensionFilter("bmp", "*.bmp"),
            new ExtensionFilter("ICO", "*.ico"),
            new ExtensionFilter("RGBE", "*.rgbe")
        );
    }

    ///////////////////////////////////////////////////////////////

    public static File chooseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(null);
    }

    ///////////////////////////////////////////////////////////////

    public static void setOnDrag(TextField textField, FileType fileType) {
        textField.setOnDragOver(event -> {
            if (event.getGestureSource() != textField) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        });

        textField.setOnDragDropped(event -> {
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
        });
    }

    public static void setOnDragByOpenFile(TextInputControl textField) {
        textField.setOnDragOver(event -> {
            if (event.getGestureSource() != textField) {
                event.acceptTransferModes(TransferMode.ANY);
            }
        });
        textField.setOnDragDropped(event -> {
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
        });
    }

    public enum FileType {
        FILE, FOLDER
    }
}
