package com.rao.util.common;

import com.rao.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.util.*;

/**
 * 包扫描工具类
 * @author raojing
 * @date 2019/12/19 18:02
 */
@Slf4j
public class PackageScanUtil {

    private static List<Class> classHashSet = new ArrayList<>();

    /**
     * 获取包下所有类
     * @param packageName
     * @return
     */
    public static List<Class> scannerPackage(String packageName){
        packageName = packageName.replace(".","/");
        //得到包 文件夹
        try {
            Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageName);
            //得到包所在的根路径
            String rootPath = Thread.currentThread().getContextClassLoader().getResource(packageName).getPath();
            if (rootPath != null){
                rootPath = rootPath.substring(rootPath.indexOf(packageName));
            }
            while (dirs.hasMoreElements()){
                URL url = dirs.nextElement();
                //判断是不是文件  url 有协议
                if (url.getProtocol().equals("file")){
                    File file = new File(url.getPath());
                    //遍历文件夹下所有文件
                    scannerFolder(file,rootPath);
                }
            }
            return classHashSet;
        } catch (Exception e) {
            log.error("包不存在-->{}", e.getMessage());
            throw BusinessException.operate("包不存在");
        }

    }

    /**
     * 扫描包
     * @param folder
     * @param rootPath
     */
    private static void scannerFolder(File folder, String rootPath) {
        //1.得到文件夹中所有文件
        File[] files = folder.listFiles();
        //2.遍历
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                //3.是文件夹，递归
                scannerFolder(file, rootPath + "/" + file.getName());
            } else {
                //是文件
                String absolutePath = file.getAbsolutePath();
                if (absolutePath.endsWith(".class")) {
                    //4.是类文件
                    absolutePath = absolutePath.replace("\\", "/");
                    String filePath = rootPath + absolutePath.substring(absolutePath.lastIndexOf("/"), absolutePath.indexOf(".class"));
                    filePath = filePath.replace("/", ".");
                    try {
                        classHashSet.add(Class.forName(filePath));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
}
