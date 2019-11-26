package com.rao.pojo.vo.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 文件上传 视图模型
 *
 * @author raojing
 * @date 2019/8/18 1:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadVO implements Serializable {

    /**
     * 文件URL
     */
    private String fileUrl;

    /**
     * 文件路径
     */
    private String filePath;

}
