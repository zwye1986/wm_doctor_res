package com.pinde.core.common.enums;
/**
 * 用于枚举需要实现的接口
 * 
 * @author badqiu
 */
public interface GeneralEnum<K> {

	/** 得到枚举对应的id,一般保存这个id至数据库 */
    public K getId();
    /** 得到枚举描述 */
    public String getName();
    /** 枚举名称 */
    public String name();
    
}

