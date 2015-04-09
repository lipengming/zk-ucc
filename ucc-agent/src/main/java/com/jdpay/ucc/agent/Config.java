/**
 * Copyright (c) 2014, wylipengming@jd.com|shouli1990@gmail.com. All rights reserved.
 *
 */
package com.jdpay.ucc.agent;

import com.google.gson.Gson;
import com.jdpay.ucc.core.dto.ConfigType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.jdpay.ucc.agent.utils.NetUtils.isConnectAble;
import static com.jdpay.ucc.agent.utils.StringUtils.isEmpty;


/**
 * <b>项目名</b>： ucc <br>
 * <b>包名称</b>： com.jdpay.ucc.agent <br>
 * <b>类名称</b>： Config <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:wylipengming@jd.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>：2015/4/9 13:29<br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public final class Config {
    private static final Logger _LOG = LoggerFactory.getLogger(Config.class);

    public static final String CLASS_PATH = "java.class.path";

    public static final Pattern APP_PATH_TOMCAT_PATTERN = Pattern.compile("\\s*<Context[\\s\\S]*docBase=\"([^\"]+)\"[\\s\\S]*", Pattern.CASE_INSENSITIVE);

    private static final String _BASE_URL = "";

    public static final String APP_NAME;
    public static final AppConfig _APP_CONF;

    static {
        String appName = System.getProperty("app.name",System.getenv("APP_NAME"));
        _LOG.info("APP NAME :" + appName);
        if(isEmpty(appName)){
            _LOG.error("CAN'T GET APP_NAME!");
        }
        APP_NAME = appName;
        _APP_CONF = getAppConfig();
    }


    private Object appConfig;

    private static List<File> getClassPath() {
        List<File> ret = new ArrayList<File>();
        String[] paths = System.getProperty(CLASS_PATH).split(File.pathSeparator);
        String java_home = File.separator + "jre" + File.separator + "lib";
        for (String path : paths) {
            if (!path.contains(java_home)) {
                ret.add(new File(path));
            }
        }
        //兼容tomcat
        String app_home = System.getProperty("catalina.base", System.getenv("catalina.base"));
        if (!isEmpty(app_home)) {
            URL url = getConfigFileRecursion(new File(app_home), "server.xml");
            String path = null;
            try {
                if (url != null)
                    path = readXmlByPattern(url.openStream(), APP_PATH_TOMCAT_PATTERN);
            } catch (Throwable e) {
                _LOG.info("get tomcat docBase error :", e);
            }
            if (!isEmpty(path)) {
                ret.add(new File(path));
            }
            ret.add(new File(app_home));
            _LOG.info("tomcat app home : " + app_home);
        }
        return ret;
    }

    /**
     * 从xml文件中获取Pattern中的数据，eg：<property name="APP" value="xxx"/>
     *
     * @param inputStream 文件输入流
     * @return 应用名称
     * @throws IOException
     */
    private static String readXmlByPattern(InputStream inputStream, Pattern pattern) {
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    return matcher.group(1);
                }
            }
        } catch (Throwable e) {
            _LOG.info("read xml error : ", e);
        } finally {
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    //ignore
                }
            }
        }
        return null;
    }

    /**
     * 递归文件夹获取prefix.xml文件
     *
     * @param dir   文件夹
     * @param fName 文件名
     * @return URL
     */
    private static URL getConfigFileRecursion(File dir, String fName) {
        File[] files = dir.listFiles();
        if (files == null) {
            return null;
        }
        URL url = null;
        for (File f : files) {
            try {
                if (f.isDirectory()) {
                    url = getConfigFileRecursion(f, fName);
                } else {
                    if (f.getName().equalsIgnoreCase(fName)) {
                        _LOG.info("found " + fName + " : " + f.getAbsolutePath());
                        url = f.toURI().toURL();
                    } else if (f.getName().endsWith(".jar")) {
                        url = getFileByJar(f, fName);
                    }
                }
                if (url != null) {
                    return url;
                }
            } catch (Throwable e) {
                _LOG.info("getConfigFileRecursion error : ", e);
            }
        }
        return null;
    }

    /**
     * 获取jar中配置文件应用名
     *
     * @param jarFile java.io.File 文件File
     * @param fName   xml名称
     * @return prefix file URL
     */
    private static URL getFileByJar(File jarFile, String fName) {
        URL url;
        //jar
        JarFile fis = null;
        try {
            fis = new JarFile(jarFile);
            Enumeration<JarEntry> enumeration = fis.entries();
            while (enumeration.hasMoreElements()) {
                JarEntry e = enumeration.nextElement();
                String eName = e.getName();
                if (!eName.endsWith("/")) {
                    int index = eName.lastIndexOf("/");
                    String entryName = index == -1 ? eName : eName.substring(index + 1);
                    if (entryName.equalsIgnoreCase(fName)) {
                        url = new URL("jar:file:/" + jarFile.getAbsolutePath() + "!/" + eName);
                        _LOG.info("found " + fName + " in jar : " + url.toString());
                        return url;
                    }
                }
            }
        } catch (Throwable e1) {
            //ignore
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    //ignore
                }
            }
        }
        return null;
    }

    public static AppConfig getAppConfig() {
        AppConfig app = new AppConfig();
        app.appName = APP_NAME;
        app.type = resole(request());
        return app;
    }

    private static String request() {
        BufferedReader reader = null;
        HttpURLConnection connection = null;

        try {
            String host = _BASE_URL;
            if (!isConnectAble(host, 5000)) {
                _LOG.warn("config center [" + host + "] not connect able at 5s timeout!");
                return null;
            }
            String url = _BASE_URL + APP_NAME;
            _LOG.info("get app config url : " + url);
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                String[] hosts = host.split(":", 2);
                sm.checkConnect(hosts[0], hosts.length == 1 ? 80 : Integer.valueOf(hosts[1]));
            }
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String lines;
            while ((lines = reader.readLine()) != null) {
                sb.append(lines);
            }
            return sb.toString();
        } catch (Throwable e) {
            _LOG.info("get app config error : ", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Throwable e) {
                    //
                }
            }
            if (connection != null) {
                try {
                    // 断开连接
                    connection.disconnect();
                } catch (Throwable e) {
                    //
                }
            }
        }
        return null;
    }

    private static List<ConfigType> resole(String json) {
        if(json == null){}
        return new ArrayList<ConfigType>(0);
    }

    static class AppConfig {
        List<ConfigType> type;
        String appName;
    }
}
