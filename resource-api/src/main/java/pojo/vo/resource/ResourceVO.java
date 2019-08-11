package pojo.vo.resource;

import lombok.Data;

import java.util.Date;

/**
 * Created by Lenovo on 2018/9/13.
 */
@Data
public class ResourceVO {

    private String path;

    private String fileSize;

    private Date lastModifyTime;

}
