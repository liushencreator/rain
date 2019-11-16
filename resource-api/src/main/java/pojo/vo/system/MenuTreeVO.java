package pojo.vo.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pojo.entity.system.RainSystemMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单树laui 视图对象
 * @author raojing
 * @date 2019/11/16 16:56
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuTreeVO {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 是否打开
     */
    private boolean spread;

    /**
     * 子分类
     */
    private List<MenuTreeVO> children;

    public static MenuTreeVO build(RainSystemMenu systemMenu){
        return MenuTreeVO.builder()
                .id(systemMenu.getId())
                .title("<i class='" + systemMenu.getMenuIcon() + "'></i> " + systemMenu.getMenuName())
                .spread(true)
                .build();
    }

    /**
     * 构建子菜单
     * @param systemMenuList
     * @param id
     * @return
     */
    public static List<MenuTreeVO> buildChildList(List<RainSystemMenu> systemMenuList, Long id) {
        List<MenuTreeVO> menuTreeVOList = new ArrayList<>();
        for (RainSystemMenu systemMenu : systemMenuList) {
            if(systemMenu.getParentId().equals(id)){
                MenuTreeVO menuTreeVO = MenuTreeVO.build(systemMenu);
                menuTreeVO.setChildren(buildChildList(systemMenuList, systemMenu.getId()));
                menuTreeVOList.add(menuTreeVO);
            }
        }
        return menuTreeVOList;
    }

}
