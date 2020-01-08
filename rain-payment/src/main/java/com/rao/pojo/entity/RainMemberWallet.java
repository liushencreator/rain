package com.rao.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entity - 用户钱包表
 *
 * @author raojing
 * @version 2.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RAIN_MEMBER_WALLET")
public class RainMemberWallet {

    private static final long serialVersionUID = 5081846432919091193L;

    /**
     * 用户id
     */
    @Id
    private Long memberId;

    /**
     * 支付密码
     */
    @Column(name = "pay_password")
    private String payPassword;

    /**
     * 可用余额(分)
     */
    @Column(name = "balance")
    private Long balance;

    /**
     * 历史余额(分)
     */
    @Column(name = "history_balance")
    private Long historyBalance;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}
