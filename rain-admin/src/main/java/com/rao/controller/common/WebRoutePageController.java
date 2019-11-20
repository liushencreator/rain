package com.rao.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面路由器
 * @author raojing
 * @date 2019/11/13 23:29
 */
@Controller
@RequestMapping("/page")
public class WebRoutePageController {

    /**
     * 页面-路由
     * @return
     */
    @GetMapping("/{page}")
    public String routeOne(@PathVariable("page") String page,
                           HttpServletRequest request) {
        String menuId = request.getParameter("menuId");
        request.setAttribute("menuId", menuId);
        return "/" + page;
    }
    
    /**
     * 一级目录-路由
     * @param first
     * @return
     */
    @GetMapping("/{first}/{page}")
    public String routeOne(@PathVariable("first") String first,
                           @PathVariable("page") String page,
                           HttpServletRequest request) {
        String menuId = request.getParameter("menuId");
        request.setAttribute("menuId", menuId);
        return "/" + first + "/" + page;
    }
    
    /**
     * 二级目录-路由
     * @param first
     * @param second
     * @return
     */
    @GetMapping("/{first}/{second}/{page}")
    public String routeTwo(@PathVariable("first") String first,
                           @PathVariable("second") String second,
                           @PathVariable("page") String page,
                           HttpServletRequest request) {
        String menuId = request.getParameter("menuId");
        request.setAttribute("menuId", menuId);
        return "/" + first + "/" + second + "/" + page;
    }

    /**
     * 三级目录-路由
     * @param first
     * @param second
     * @param third
     * @param page
     * @return
     */
    @GetMapping("/{first}/{second}/{third}/{page}")
    public String routeThree(@PathVariable("first") String first,
                             @PathVariable("second") String second,
                             @PathVariable("third") String third,
                             @PathVariable("page") String page,
                             HttpServletRequest request) {
        String menuId = request.getParameter("menuId");
        request.setAttribute("menuId", menuId);
        return "/" + first + "/" + second + "/" + third + "/" + page;
    }

}
