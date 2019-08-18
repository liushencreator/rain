package pojo.vo.resource;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Lenovo on 2019/1/29.
 */
@Data
public class LocalFilePathNameVO implements Serializable {

    /*文件地址*/
    private String filePath;

    /*文件名字*/
    private String fileName;

}
