package com.pinde.core.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import lombok.Data;

@Data
@TableName(value = "RES_REC_CHECK_CONFIG")
public class ResRecCheckConfig {
    /**
     * 主键
     */
    @TableId(value = "ID", type = IdType.INPUT)
    private BigDecimal id;

    /**
     * 轮转手册填写类型
     */
    @TableField(value = "REGISTRY_TYPE_ID")
    private String registryTypeId;

    /**
     * 轮转手册填写名称
     */
    @TableField(value = "REGISTRY_TYPE_NAME")
    private String registryTypeName;

    /**
     * 需要校验的字段
     */
    @TableField(value = "CHECK_ITEM")
    private String checkItem;

    /**
     * 需要校验的字段名称
     */
    @TableField(value = "CHECK_ITEM_NAME")
    private String checkItemName;

    /**
     * 匹配规则（正则表达式）
     */
    @TableField(value = "CHECK_RULES")
    private String checkRules;

    /**
     * 特殊制定的规则（多个字符串，用,隔开）
     */
    @TableField(value = "CHECK_RULES_SINGLE")
    private String checkRulesSingle;

    /**
     * 例外的情况，可以配置多个用","分隔；只要匹配上就算合规
     */
    @TableField(value = "CHECK_RULES_EXA")
    private String checkRulesExa;

    /**
     * 启用状态
     */
    @TableField(value = "RECORD_STATUS")
    private String recordStatus;
}