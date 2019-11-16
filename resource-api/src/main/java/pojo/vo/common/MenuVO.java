package pojo.vo.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pojo.entity.system.RainSystemMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单 视图模型
 * @author raojing
 * @date 2019/9/19 21:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuVO {

    /**
     * 菜单id
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单链接
     */
    private String linkPath;

    /**
     * 菜单logo
     */
    private String menuIcon;

    /**
     * 子菜单
     */
    private List<MenuVO> menuVOList;

    public static MenuVO build(RainSystemMenu systemMenu){
        return MenuVO.builder()
                .id(systemMenu.getId())
                .menuName(systemMenu.getMenuName())
                .linkPath(systemMenu.getMenuUrl())
                .menuIcon(systemMenu.getMenuIcon())
                .build();
    }

//    public static List<MenuVO> buildTree(){
//        List<MenuVO> categoryVOList = new ArrayList<>(10);
//        MenuVO firstParent = MenuVO.builder()
//                .id("1")
//                .menuName("资源管理")
//                .linkPath("#")
//                .build();
//
//        List<MenuVO> firstSubList = new ArrayList<>();
//        MenuVO firstSubOne = MenuVO.builder()
//                .id("11")
//                .menuName("首页")
//                .linkPath("/page/resource/admin/index.html")
//                .menulogo("layui-icon layui-icon-home")
//                .build();
//        MenuVO firstSubTwo = MenuVO.builder()
//                .id("12")
//                .menuName("本地映射地址配置")
//                .linkPath("/page/resource/admin/config/list.html")
//                .menulogo("layui-icon layui-icon-set")
//                .build();
//        MenuVO firstSubThree = MenuVO.builder()
//                .id("13")
//                .menuName("服务列表")
//                .linkPath("/page/resource/admin/server/list.html")
//                .menulogo("layui-icon layui-icon-senior")
//                .build();
//
//        firstSubList.add(firstSubOne);
//        firstSubList.add(firstSubTwo);
//        firstSubList.add(firstSubThree);
//        firstParent.setMenuVOList(firstSubList);
//
//
//        MenuVO secondParent = MenuVO.builder()
//                .id("2")
//                .menuName("菜单管理")
//                .linkPath("#")
//                .build();
//
//        List<MenuVO> secondSubList = new ArrayList<>();
//        MenuVO secondSubOne = MenuVO.builder()
//                .id("21")
//                .menuName("菜单列表")
//                .linkPath("/page/resource/admin/index.html")
//                .menulogo("layui-icon layui-icon-home")
//                .build();
//        MenuVO secondSubTwo = MenuVO.builder()
//                .id("22")
//                .menuName("角色配置")
//                .linkPath("/page/resource/admin/config/list.html")
//                .menulogo("layui-icon layui-icon-set")
//                .build();
//        secondSubList.add(secondSubOne);
//        secondSubList.add(secondSubTwo);
//        secondParent.setMenuVOList(secondSubList);
//
//        categoryVOList.add(firstParent);
//        categoryVOList.add(secondParent);
//        return categoryVOList;
//    }

}
