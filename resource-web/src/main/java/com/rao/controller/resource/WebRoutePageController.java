package com.rao.controller.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面路由器
 * @author raojing
 * @date 2019/11/13 23:29
 */
@Controller
@RequestMapping("/web/page")
public class WebRoutePageController {

    /**
     * 路由
     * @param first
     * @param second
     * @return
     */
    @GetMapping("/{first}/{second}/{page}")
    public String route(@PathVariable("first") String first,
                        @PathVariable("second") String second,
                        @PathVariable("page") String page) {
        return "/resource/" + first + "/" + second + "/" + page;
    }

}
