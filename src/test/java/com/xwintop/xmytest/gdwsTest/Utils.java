package com.xwintop.xmytest.gdwsTest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * ClassName: Utils
 * </p>
 * <p>
 * Description: 基础工具类
 * </p>
 * <p>
 * Author: colick
 * </p>
 * <p>
 * Date: 2015-05-15
 * </p>
 */
public class Utils {

    private static final Logger log = Logger.getLogger(Utils.class);

    /**
     * <p>
     * Description: 判断是否为非空
     * </p>
     *
     * @param obj 对象
     * @return true/false
     */
    public static boolean notNull(Object obj) {
        return (obj != null) && (!"".equals(obj.toString()));
    }

    /**
     * <p>
     * Description: 判断是否为空
     * </p>
     *
     * @param obj 对象
     * @return true/false
     */
    public static boolean isNull(Object obj) {
        if ((obj != null) && (!"".equals(obj))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 从request对象中获取用户IP地址
     *
     * @param request
     * @return IP地址
     */
    public static String getIp(HttpServletRequest request) {
        if (Utils.isNull(request)) {
            return org.apache.commons.lang.StringUtils.EMPTY;
        }
        String forwards = request.getHeader("x-forwarded-for");
        if (org.apache.commons.lang.StringUtils.isBlank(forwards)
                || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getHeader("Proxy-Client-IP");
        }
        if (org.apache.commons.lang.StringUtils.isBlank(forwards)
                || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getHeader("WL-Proxy-Client-IP");
        }
        if (org.apache.commons.lang.StringUtils.isBlank(forwards)
                || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getRemoteAddr();
        }
        if (org.apache.commons.lang.StringUtils.isBlank(forwards)
                || "unknown".equalsIgnoreCase(forwards)) {
            forwards = request.getHeader("X-Real-IP");
        }
        if (forwards != null && forwards.trim().length() > 0) {
            int index = forwards.indexOf(',');
            return (index != -1) ? forwards.substring(0, index) : forwards;
        }

        return forwards;
    }

    /**
     * <p>
     * Description: 拷贝集合(深复制)
     * </p>
     *
     * @param src 源
     * @return 集合
     * @throws IOException            IO异常
     * @throws ClassNotFoundException 类转换异常
     * @auth colick
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> deepCopy(List<T> src) throws IOException,
            ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);
        ByteArrayInputStream byteIn = new ByteArrayInputStream(
                byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        List<T> dest = (List<T>) in.readObject();

        return dest;
    }

    /**
     * Description: ajax输出，配置统一响应头信息
     *
     * @param response current HTTP response
     * @param content  输出内容
     * @param type     请求头类型
     */
    public static void ajax(HttpServletResponse response, String content, String type) {
        try {
            response.setContentType(type + ";charset=utf-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            log.error("ajax output message failed!");
        }
    }

    /**
     * 根据名字获取cookie
     *
     * @param request
     * @param name    cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }


    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }

        return cookieMap;
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();

        return request;
    }
}
