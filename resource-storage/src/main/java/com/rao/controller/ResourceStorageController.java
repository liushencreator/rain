package com.rao.controller;

import com.rao.service.ResourceStorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import util.result.ResultMessage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * 资源存储controller
 *
 * @author raojing
 * @date 2019/8/17 20:19
 */
@RestController
@RequestMapping("/resource/storage")
public class ResourceStorageController {

    @Resource
    private ResourceStorageService resourceStorageService;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/file_upload")
    public ResultMessage fileUpload(@RequestParam(value = "file") MultipartFile file,
                                    HttpServletRequest request) throws Exception {
        String filePath = resourceStorageService.fileUpload(file);
        return ResultMessage.success().add("filePath", filePath);
    }

}
