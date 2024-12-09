package com.pinde.sci.model.jsres;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.core.model.SysUser;

import java.io.Serializable;
import java.util.*;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/09/19/16:24
 * @Description:
 */
public class ArrangTdVo implements java.io.Serializable {

    /**
     * context:内容，统一字符类型
     * */
    private String context = "";

    /**
     * context:内容，统一字符类型
     * */
    private String deptFlow = "";
    /**
     * hide:true/false 是否隐藏
     * */
    private boolean hide;
    /**
     * inputType: 'input'/'select' 只允许调整姓名和轮转科室，input是姓名，select下拉框是科室 span 显示
     * */
    private String inputType = "span";

    /**
     * color: 色值 正确-'#00B83F' 异常-'#ee0101'
     * */
    private String color = "#00B83F";

    /**
     * disable: 是否禁用编辑 true-禁用编辑 false-不禁用编辑
     * 默认 false
     * */
    private boolean disable;

    /**
     * tip: 异常原因提示
     * */
    private String tip = "";

    /**
     * 是否是冗余的信息  默认false
     * */
    private boolean otherInfoFlag;


    private SysUser user = new SysUser();


    private List<SysDept> schDeptList = new ArrayList<>();

    private String orgFlow = "";


    private String bzDeptCodeId;

    private Double bzDeptPbMon = Double.valueOf("0");

    private Integer pbMonthCount = 0;

    public ArrangTdVo(){

    }
    public ArrangTdVo(String orgFlow){
        this.orgFlow = orgFlow;
    }
    public ArrangTdVo(boolean otherInfoFlag,
                      SysUser user){
        this.otherInfoFlag = otherInfoFlag;
        this.user = user;
        this.hide = true;
    }
    public ArrangTdVo(List<SysDept> schDeptList){
        this.otherInfoFlag = true;
        this.schDeptList = schDeptList;
        this.hide = true;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public void setTip(String tip) {
        this.tip = tip;
        if (StringUtil.isNotEmpty(tip)) {
            this.color = "#ee0101";
        }else {
            this.color = "#00B83F";
        }
    }

    public void setOtherInfoFlag(boolean otherInfoFlag) {
        this.otherInfoFlag = otherInfoFlag;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public void setSchDeptList(List<SysDept> schDeptList) {
        this.schDeptList = schDeptList;
    }

    public void setDeptFlow(String deptFlow) {
        this.deptFlow = deptFlow;
    }

    public void setOrgFlow(String orgFlow) {
        this.orgFlow = orgFlow;
    }

    public String getContext() {
        return context;
    }


    public String getInputType() {
        return inputType;
    }

    public String getColor() {
        return color;
    }

    public String getTip() {
        return tip;
    }

    public String getOrgFlow() {
        return orgFlow;
    }

    public boolean isOtherInfoFlag() {
        return otherInfoFlag;
    }

    public SysUser getUser() {
        return user;
    }

    public boolean isHide() {
        return hide;
    }

    public boolean isDisable() {
        return disable;
    }

    public List<SysDept> getSchDeptList() {
        return schDeptList;
    }

    public String getDeptFlow() {
        return deptFlow;
    }

    public String getBzDeptCodeId() {
        return bzDeptCodeId;
    }

    public void setBzDeptCodeId(String bzDeptCodeId) {
        this.bzDeptCodeId = bzDeptCodeId;
    }

    public Double getBzDeptPbMon() {
        return bzDeptPbMon;
    }

    public void setBzDeptPbMon(Double bzDeptPbMon) {
        this.bzDeptPbMon = bzDeptPbMon;
    }

    public Integer getPbMonthCount() {
        return pbMonthCount;
    }

    public void setPbMonthCount(Integer pbMonthCount) {
        this.pbMonthCount = pbMonthCount;
    }
}
