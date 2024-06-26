package com.pinde.core.util;

import java.lang.reflect.Field;

/**
 * @ClassName
 * @Description 将两个对象中的相同字段进行复制赋值
 */
public class PojoUtils {
    /**
     * 赋值给同类对象
     * 将origin属性注入到destination中
     * @param origin
     * @param destination
     * @param <T>
     */
    public static <T> void mergeObject(T origin, T destination) {
        if (origin == null || destination == null)
            return;
        if (!origin.getClass().equals(destination.getClass()))
            return;

        Field[] fields = origin.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                fields[i].setAccessible(true);
                Object value = fields[i].get(origin);
                if (null != value && !"".equals(value)) {
                    fields[i].set(destination, value);
                }
                fields[i].setAccessible(false);
            } catch (Exception e) {
            }
        }
    }
}
