package com.rao.util.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author raojing
 * @date 2019/12/3 12:37
 */
@Getter
@AllArgsConstructor
public class PageResult<T> {

    /**
     * 总数
     */
    private Integer total;

    /**
     * 数据
     */
    private List<T> data;
    
    public static<T> PageResult<T> build(Integer total, List<T> data){
        return new PageResult<>(total, data);
    }
    
}
