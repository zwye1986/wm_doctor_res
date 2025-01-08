package com.pinde.core.model;

import java.util.List;

/**
 * Created by Administrator on 2017/1/9.
 */
public class FromTitle {
    private String id;
    private String name;
    private Integer score;
    private String typeName;
    private String orderNum;
    private List<FromItem> items;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<FromItem> getItems() {
        return items;
    }

    public void setItems(List<FromItem> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
