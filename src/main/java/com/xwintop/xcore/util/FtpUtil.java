package com.xwintop.xcore.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;

@Slf4j
public class FtpUtil {
	/**
	 * Description: 向FTP服务器上传文件 @Version1.0 Jul 27, 2008 4:31:09 PM by
	 * 崔红保（cuihongbao@d-heaven.com）创建
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean uploadFile(String url, int port, String username, String password, String path,
			String filename, InputStream input) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(path);
			ftp.storeFile(filename, input);

			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	/**
	 * Description: 从FTP服务器下载文件 @Version1.0 Jul 27, 2008 5:32:36 PM by
	 * 崔红保（cuihongbao@d-heaven.com）创建
	 * 
	 * @param url
	 *            FTP服务器hostname
	 * @param port
	 *            FTP服务器端口
	 * @param username
	 *            FTP登录账号
	 * @param password
	 *            FTP登录密码
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
	 */
	public static boolean downFile(String url, int port, String username, String password, String remotePath,
			String fileName, String localPath) {
		boolean success = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(url, port);
			// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
			FTPFile[] fs = ftp.listFiles();
			for (FTPFile ff : fs) {
				if (ff.getName().equals(fileName)) {
					File localFile = new File(localPath + "/" + ff.getName());
					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ff.getName(), is);
					is.close();
				}
			}

			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return success;
	}

	/**
	 * 删除指定文件
	 * 
	 * @param filePath 文件路径(含文件名)
	 */
	public static boolean deleteFile(String url, int port, String username, String password, String filePath) {
		try {
			if (StringUtils.isNotEmpty(filePath)) {
				FTPClient ftp = new FTPClient();
				int reply;
				ftp.connect(url, port);
				// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
				ftp.login(username, password);// 登录
				reply = ftp.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftp.disconnect();
					return false;
				}
				if (!ftp.deleteFile(filePath)) {
					return false;
				}
			}
		} catch (IOException e) {
			log.error("删除文件失败：", e);
//			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * @Title: removeDirectory
	 * @Description: 移除目录
	 */
	public static boolean removeDirectory(String url, int port, String username, String password, String pathname) {
		try {
			if (StringUtils.isNotEmpty(pathname)) {
				FTPClient ftp = new FTPClient();
				int reply;
				ftp.connect(url, port);
				// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
				ftp.login(username, password);// 登录
				reply = ftp.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftp.disconnect();
					return false;
				}
				if (!ftp.removeDirectory(pathname)) {
					return false;
				}
			}
		} catch (IOException e) {
			log.error("删除文件夹失败：", e);
//			e.printStackTrace();
			return false;
		}
		return true;
	}
}
