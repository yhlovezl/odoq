package com.xiaoniucr.test.string;

import lombok.Data;

/**
 * 审核体系
 * @author yanghl
 * @date 2020/4/17 11:22
 */
@Data
public class SccAuditClausesModelDto {

    //体系ID
    private Long systemTypeId;

    //体系名称
    private String systemTypeName;

    /**
     * 条款ID
     */
    private Long id;

    /**
     * 条款名称
     */
    private String name;
}
