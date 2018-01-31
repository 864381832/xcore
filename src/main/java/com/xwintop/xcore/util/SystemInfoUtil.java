package com.xwintop.xcore.util;

import lombok.extern.java.Log;
/**
 * @ClassName: SystemInfoUtil
 * @Description: 获取系统信息工具类
 * @author: xufeng
 * @date: 2018/1/31 15:09
 */

@Log
public class SystemInfoUtil {
    /**
     * 获取Hosts文件路径
     */
    public static String getHostsFilePath(){
        String fileName = null;
        // 判断系统
        if ("linux".equalsIgnoreCase(System.getProperty("os.name"))) {
            fileName = "/etc/hosts";
        } else {
            fileName = "C://WINDOWS//system32//drivers//etc//hosts";
        }
        log.info("fileName:"+fileName);
        return fileName;
    }
}
