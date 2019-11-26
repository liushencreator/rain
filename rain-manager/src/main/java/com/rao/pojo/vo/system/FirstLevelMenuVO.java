package com.rao.pojo.vo.system;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 一级菜单 视图模型
 * @author raojing
 * @date 2019/11/16 23:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FirstLevelMenuVO {

    /**
     * 菜单id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 菜单名称
     */
    private String menuName;

}
