package pojo.dto.system;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;


/**
 * 菜单配置 数据传输模型
 * @author raojing
 * @date 2019/11/18 12:57
 */
@Data
public class MenuConfigDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    /**
     * 菜单链接
     */
    @NotBlank(message = "菜单链接不能为空")
    private String menuUrl;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 排序
     */
    @NotNull(message = "菜单权重值不能为空")
    private Integer sort;

    /**
     * 父菜单id(一级菜单为0)
     */
    @NotNull(message = "父菜单不能为空")
    private Long parentId;

    /**
     * 状态 1-启用 2-禁用
     */
    @Range(min = 1, max = 2, message = "状态值只能为1 或者 2")
    @NotNull(message = "状态不能为空")
    private Integer status;
    
}
