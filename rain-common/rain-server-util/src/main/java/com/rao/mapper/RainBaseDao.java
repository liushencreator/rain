package com.rao.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 通用 mapper
 * @author raojing
 * @date 2019/12/13 12:56
 */
public interface RainBaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}
