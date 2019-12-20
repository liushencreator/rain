package com.rao.util.common;

import com.rao.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author raojing
 * @date 2019/12/20 16:20
 */
@Slf4j
public class PermissionClazzUtil {
    
    private static List<Class> clazzList = new ArrayList<>();
    
    static {
        try {
            clazzList.add(Class.forName("com.rao.constant.permission.user.SystemCodeConstant"));
            clazzList.add(Class.forName("com.rao.constant.permission.user.UserCodeConstant"));
            clazzList.add(Class.forName("com.rao.constant.permission.quartz.QuartzCodeConstant"));
        } catch (Exception e) {
            log.error("加载类异常--->{}", e.getMessage());
            throw BusinessException.operate("加载类异常");
        }        
    }
    
    public static List<Class> allPermissionClazz(){
        return clazzList;
    }
    
}
