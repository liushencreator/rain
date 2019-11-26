package com.rao.pojo.dto.resource;

import lombok.Data;

/**
 * 修改资源信息 数据传输模型
 *
 * @author raojing
 * @date 2019/8/18 14:18
 */
@Data
public class UpdateResourceDTO {

    /**
     * 资源id
     */
    private Long id;

    /**
     * 资源名称
     */
    private String videoName;

    /**
     * 资源展示图片
     */
    private String videoImage;

    /**
     * 资源描述信息
     */
    private String videoDescribe;

}
