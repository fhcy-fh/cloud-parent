package com.cloud.common.tenant.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.cloud.common.mybatis.core.domain.BaseEntity;

/**
 * 租户基类
 *
 * @author Michelle.Chung
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TenantEntity extends BaseEntity {

    /**
     * 租户编号
     */
    private String tenantId;

}