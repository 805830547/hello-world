package com.sixstar.mvc.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ClassScanner
 * @Description: 扫描类 加载进内存
 * @author Administrator
 *
 */
public class ClassScanner {
    // 用来存放扫描到的所有类
    public static Map<String, Class<?>> scannerClass(String packagePath) {
        // 将包名中的.替换成/
        String filePath = packagePath.replace(".", "/");
        Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
        try {
            // 取得给定路径的所有Url地址枚举对象
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(filePath);
            // 通过字符串截取 获取到包名路径
            String rootPath = Thread.currentThread().getContextClassLoader().getResource(filePath).getPath();
            if (null != rootPath) {
                rootPath = rootPath.substring(rootPath.lastIndexOf(filePath));
            }
            // 迭代url对象
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                // 通过url 判定此url是文件对象（文件或者文件夹）
                if (url.getProtocol().equals("file")) {
                    // /D:/workspaceVip/smvc/WebContent/WEB-INF/classes/com/sixstar/controller
                    // 通过url的路径产生实际的文件对象
                    File folder = new File(url.getPath().substring(1));
                    // 扫描次文件夹下的所有（包括子目录）的所有类
                    scannFile(folder, rootPath + "/", classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    private static void scannFile(File folder, String rootPath, Map<String, Class<?>> classes) {
        // 找到文件夹下面的所有文件对象（文件或者文件夹）
        File[] files = folder.listFiles();
        for (int i = 0; files != null && i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {// 当是文件夹时 递归调用本身
                scannFile(file, rootPath+ file.getName() + "/"  , classes);
            } else {
                // 拿到当前文件对象的真实路径
                String path = file.getAbsolutePath();
                // 只需要.class 文件/
                if (path.endsWith(".class")) {
                    // 将路径中的\ 替换成 /
                    path = path.replace("\\", "/");
                    // 前面计算出的包名+路径类名 得到 包名+类名的完整路径
                    String className = rootPath+ path.substring(path.lastIndexOf("/") + 1, path.indexOf(".class"));
                    className = className.replace('/', '.');
                    try {
                        // 放入HashMap中
                        classes.put(className, Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Map<String, Class<?>> classes = scannerClass("com.sixstar.controller");
        for (String className : classes.keySet()) {
            System.out.println(className);
        }
    }
}
