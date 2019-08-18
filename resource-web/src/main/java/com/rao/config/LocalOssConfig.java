package com.rao.config;

import lombok.Data;
import org.jsoup.helper.StringUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本地 OSS 服务地址
 *
 * @author raojing
 * @date 2019/8/17 23:38
 */
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

    public String getFullPath(String path){
        if(StringUtil.isBlank(path)){
            return "";
        }
        String os = System.getProperty("os.name");
        return (os.toLowerCase().startsWith("win") ? wdUrl : lmUrl) + path;
    }

}
