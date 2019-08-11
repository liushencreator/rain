package pojo.vo.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 资源列表视图模型
 * @author raojing
 * @date 2019/8/8 21:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceVideoVO {

    /** id */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /** 视频名字 */
    private String videoName;

    /** 视频描述信息 */
    private String videoDescribe;

    /** 视频大小 */
    private String videoSize;

    /** 创建时间 */
    private String createTime;

}
