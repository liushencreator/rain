package com.rao.util.page;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 分页参数
 * @author : hudelin
 * @className :PageParam
 * @description :
 * @date: 2019-12-16 15:31
 */
@Data
public class PageParam {

    /**
     * 页码
     */
    private Integer pageNumber = 1;

    /**
     * 页大小
     */
    private Integer pageSize = 10;

}
