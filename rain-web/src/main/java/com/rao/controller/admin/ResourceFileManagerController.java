package com.rao.controller.admin;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.rao.config.LocalOssConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import pojo.vo.file.FileUploadVO;
import util.result.ResultMessage;

import javax.annotation.Resource;
import java.io.File;
import java.util.UUID;

/**
 * 资源文件管理 controller
 *
 * @author raojing
 * @date 2019/8/18 1:38
 */
@Slf4j
@RestController
@RequestMapping("/resource/manager")
public class ResourceFileManagerController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LocalOssConfig localOssConfig;

    /**
     * 文件上传
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping("/file_upload")
    public ResultMessage fileUpload(@RequestParam(value = "file") MultipartFile file) {
        // 根据当前系统拿到本地 OSS 服务文件上传地址
        String os = System.getProperty("os.name");
        String restApi = os.toLowerCase().startsWith("win") ? localOssConfig.getWdRestApi() : localOssConfig.getLmRestApi();
        File tempFile = new File("");
        try{
            // 在当前服务器中生成临时文件
            String originalFilename = UUID.randomUUID().toString() + file.getOriginalFilename();
            String classpath = ResourceUtils.getURL("classpath:").getPath();
            tempFile = new File(classpath, originalFilename);
            file.transferTo(tempFile);

            // 通过 restTemplate 上传文件到本地 OSS 服务器
            FileSystemResource fileSystemResource = new FileSystemResource(tempFile);
            LinkedMultiValueMap multiValueMap = new LinkedMultiValueMap();
            multiValueMap.add("file", fileSystemResource);
            String uploadInfoJsonStr = restTemplate.postForObject(restApi, multiValueMap, String.class);
            JSONObject uploadInfoJsonObject = JSONUtil.parseObj(uploadInfoJsonStr).getJSONObject("map").getJSONObject("uploadInfo");
            log.info("oss 返回的信息:{}", uploadInfoJsonObject);
            FileUploadVO fileUploadVO = JSONUtil.toBean(uploadInfoJsonObject, FileUploadVO.class);
            // 删除当前服务器的临时文件
            tempFile.delete();
            return ResultMessage.success().add("uploadInfo", fileUploadVO).addMessage("文件上传成功");
        }catch (Exception e){
            return ResultMessage.fail().addMessage("文件上传失败");
        }finally {
            tempFile.delete();
        }
    }

}
