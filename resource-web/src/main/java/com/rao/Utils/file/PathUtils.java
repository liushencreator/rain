package com.rao.Utils.file;

import com.rao.pojo.vo.ResourceVO;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created by Lenovo on 2018/9/11.
 */
public class PathUtils {

    private List<ResourceVO> list;

    private String indexServicePath;

    public PathUtils(List<ResourceVO> cList, String indexPath) {
        list = cList;
        indexServicePath = indexPath;
    }

    public List<ResourceVO> pathResult(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                pathResult(f);
            }
            if (f.isFile()) {
                String path = f.getPath();
                String pathLowerCase = path.toLowerCase();
                if (pathLowerCase.lastIndexOf(".mp4") > 0 || pathLowerCase.lastIndexOf(".flv") > 0) {
                    ResourceVO resource = new ResourceVO();
//                    int i = path.indexOf(indexServicePath);
                    int index = path.lastIndexOf("\\") + 1;
                    path = path.substring(index, path.length());
                    resource.setPath(path);
                    resource.setFileSize(getCalculateSize(f));
                    resource.setLastModifyTime(new Date(f.lastModified()));
                    list.add(resource);
                }
            }
        }
        return list;
    }

    /**
     * 计算文件大小
     *
     * @param f
     * @return
     */
    private String getCalculateSize(File f) {
        String fileSize = "";
        Long lengthB = f.length();
        Long lengthKb = lengthB / 1024;
        Long lengthMb = lengthKb / 1024;
        fileSize = lengthMb + "M";
        if (lengthMb.intValue() > 1024) {
            Double lengthGb = lengthMb.doubleValue() / 1024;
            fileSize = String.format("%.2f", lengthGb) + "G";
        }
        return fileSize;
    }


}
