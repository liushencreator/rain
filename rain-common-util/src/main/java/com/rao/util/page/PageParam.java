package com.rao.util.page;

import lombok.Data;

/**
 * 分页参数
 * @author : hudelin
 * @className :PageParam
 * @description :
 * @date: 2019-12-16 15:31
 */
@Data
public class PageParam<T> {

    /**
     * 页码
     */
    private Integer pageNumber;

    /**
     * 页大小
     */
    private Integer pageSize;

    /**
     * 参数
     */
    private T dto;

}
