package com.rao.service.impl;

import com.rao.config.ResourceFileConfig;
import com.rao.service.ResourceStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pojo.vo.file.FileUploadVO;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件上传 service impl
 *
 * @author raojing
 * @date 2019/8/17 21:37
 */
@Service
public class ResourceStorageServiceImpl implements ResourceStorageService {

    @Resource
    private ResourceFileConfig resourceFileConfig;

    @Override
    public FileUploadVO fileUpload(MultipartFile file, String projectUrl) throws Exception {

        // 根据当前系统，拿到文件存储基础路径
        String os = System.getProperty("os.name");
        String basePath = os.toLowerCase().startsWith("win") ? resourceFileConfig.getWdBasePath() : resourceFileConfig.getLmBasePath();

        // 获取文件名
        String originalFilename = file.getOriginalFilename();
        Integer index = originalFilename.lastIndexOf(".");
        // 获取文件类型
        String type = originalFilename.substring(index + 1);
        // 格式化当日的年月日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());

        // 拼接子目录，根据文件类型和日期
        String childDirPath = "/" + type + "/" + ymd;
        File fileDir = new File(basePath, childDirPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        // 除去基础存储路径的文件地址
        long currentTime = System.currentTimeMillis();
        String filePath = childDirPath + "/" + currentTime + "." + type;
        // 文件访问地址
        String fileUrl = projectUrl + filePath;
        // 文件完整的存储地址
        String fileAddress = basePath + filePath;
        // 上传操作
        if (file.getSize() > 0) {
            File dest = new File(fileAddress);
            file.transferTo(dest);
        } else {
            throw new RuntimeException("文件为空，上传失败");
        }
        return FileUploadVO.builder()
                .fileUrl(fileUrl)
                .filePath(filePath)
                .build();
    }
}
