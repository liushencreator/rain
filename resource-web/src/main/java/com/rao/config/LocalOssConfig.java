package com.rao.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.helper.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 本地 OSS 服务地址
 *
 * @author raojing
 * @date 2019/8/17 23:38
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "local-oss")
public class LocalOssConfig {

    /**
     * windows OSS 服务地址
     */
    private String wdUrl;

    /**
     * linux 或 Mac 服务地址
     */
    private String lmUrl;

    /**
     * windows OSS文件上传接口地址
     */
    private String wdRestApi;

    /**
     * linux 或 Mac OSS文件上传接口地址
     */
    private String lmRestApi;

    /**
     * OSS是否在本机
     */
    private Boolean localOss;


    @PostConstruct
    private void initConfig(){
        if(localOss){
            try {
                String hostAddress = InetAddress.getLocalHost().getHostAddress();

                wdUrl = "http://" + hostAddress + ":8082/storage/storage";
                lmUrl = "http://" + hostAddress + ":8082/storage/storage";
                wdRestApi = "http://" + hostAddress + ":8082/storage/resource/storage/file_upload.html";
                lmRestApi = "http://" + hostAddress + ":8082/storage/resource/storage/file_upload.html";
            } catch (UnknownHostException e) {
                log.error("初始化oss失败");
            }
        }
        log.info("wdUrl配置:{}", wdUrl);
        log.info("lmUrl配置:{}", lmUrl);
        log.info("wdRestApi配置:{}", wdRestApi);
        log.info("lmRestApi配置:{}", lmRestApi);
    }

    public String getFullPath(String path){
        if(StringUtil.isBlank(path)){
            return "";
        }
        String os = System.getProperty("os.name");
        return (os.toLowerCase().startsWith("win") ? wdUrl : lmUrl) + path;
    }

}
