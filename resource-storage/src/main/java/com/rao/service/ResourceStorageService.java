package com.rao.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传 service
 *
 * @author raojing
 * @date 2019/8/17 21:34
 */
public interface ResourceStorageService {

    /**
     *
     * @param file
     * @return
     */
    String fileUpload(MultipartFile file) throws Exception;

}
