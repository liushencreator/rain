package com.rao.service;

import com.rao.pojo.vo.file.FileUploadVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传 service
 *
 * @author raojing
 * @date 2019/8/17 21:34
 */
public interface ResourceStorageService {

    /**
     * 资源上传
     *
     * @param file
     * @return
     */
    FileUploadVO fileUpload(MultipartFile file, String projectUrl) throws Exception;

}
