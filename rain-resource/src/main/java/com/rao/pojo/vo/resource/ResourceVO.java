package com.rao.pojo.vo.resource;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Lenovo on 2018/9/13.
 */
@Data
public class ResourceVO implements Serializable {

    private String path;

    private String fileSize;

    private Date lastModifyTime;

}
