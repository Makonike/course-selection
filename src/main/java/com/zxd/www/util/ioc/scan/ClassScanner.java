package com.zxd.www.util.ioc.scan;


import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * 类扫描的工具类
 * @author Makonike
 * @date 2021-09-15 11:58
 **/
public class ClassScanner {

    /**
     * 全类名
     */
    private static final List<String> CLASS_FULL_NAME = new ArrayList<>();

    /**
     * 扫描类，将类的全类名放入 全类名集合中
     * @param packageName 包名
     */
    public static void scanClasses(String packageName) {
        // 将包路径转换为文件路径
        String path = packageName.replace(".", "/");
        // 获取当前线程的类加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 使用类加载器对象的 getResources(ResourceName) 方法从jar包根目录查找资源
        URL url = classLoader.getResource(path);
        String urlPath = Objects.requireNonNull(url).getPath();
        // 当前路径下的所有文件的路径
        String[] paths = new File(urlPath).list();
        // 防止空指针
        assert paths != null;
        for (String s : paths) {
            String filePath = urlPath+"/"+s;
            File file = new File(filePath);
            if(file.isDirectory()){
                // 递归搜索
                scanClasses(packageName+"."+file.getName());
            } else {
                String className =packageName + "." + file.getName();
                className = className.replace(".class", "");
                // 将全限定类名放入集合
                CLASS_FULL_NAME.add(className);
            }
        }
    }

    public static List<String> getClassFullName(){
        return CLASS_FULL_NAME;
    }

}
