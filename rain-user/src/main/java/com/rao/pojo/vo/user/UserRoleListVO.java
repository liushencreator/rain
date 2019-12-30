package com.rao.pojo.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : hudelin
 * @className :UserRoleListVO
 * @description : 用户角色列表
 * @date: 2019-12-30 15:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleListVO {

    /**
     * 用户角色List
     */
    private List<UserRoleVO> roleList;
}
