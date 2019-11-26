package com.rao.pojo.vo.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 网链 视图模型
 * @author raojing
 * @date 2019/11/14 21:50
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NetworkLinkVO implements Serializable {

    /**
     * id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 网链名称
     */
    private String linkName;

    /**
     * 链接地址
     */
    private String linkPath;

}
