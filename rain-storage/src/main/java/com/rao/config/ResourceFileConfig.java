package com.rao.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件路径配置
 *
 * @author raojing
 * @date 2019/8/17 21:09
 */
@Data
@Component
@ConfigurationProperties(prefix = "file-path")
public class ResourceFileConfig {

    /**
     * windows 文件基础路径
     */
    private String wdBasePath;

    /**
     * linux 或 Mac 文件基础路径
     */
    private String lmBasePath;

}
