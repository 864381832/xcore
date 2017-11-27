package com.xwintop.xcore.util;

import java.io.File;
import java.text.DecimalFormat;

public class FileUtil {
	public static String[] getFileNames(File file) {
		String[] returnStrings = new String[2];
		String[] nameStrings = file.getName().split("\\.");
		if (nameStrings.length > 1) {
			String suffixName = nameStrings[nameStrings.length - 1];
			String fileName = file.getName().split("\\." + suffixName)[0];
			returnStrings[0] = fileName;
			returnStrings[1] = suffixName;
		} else {
			returnStrings[0] = file.getName();
			returnStrings[1] = "";
		}
		return returnStrings;
	}

	public static String getFileName(File file) {
		return getFileNames(file)[0];
	}

	public static String getFileSuffixName(File file) {
		return getFileNames(file)[1];
	}

	public static String getFileSuffixNameAndDot(File file) {
		String suffixName = getFileNames(file)[1];
		if (!"".equals(suffixName)) {
			suffixName = "." + suffixName;
		}
		return suffixName;
	}
	
	public static String getRandomFileName(File file){
		String[] fileNames = getFileNames(file);
		String fileName = fileNames[0]+(""+System.currentTimeMillis()).substring(9);
		if (!"".equals(fileNames[1])) {
			fileName += ("."+fileNames[1]);
		}
		return fileName;
	}

    /**
     * 转换文件的大小以B,KB,M,G等计算
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {// 转换文件大小
        DecimalFormat df = new DecimalFormat("#.000");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }
}
