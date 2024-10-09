package com.pinde.sci.model.jsres;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/09/19/16:24
 * @Description: 轮转科室对应的标准科室及其轮转信息
 */
public class DeptLzBzInfo implements Serializable {
    private String schDeptFlow;
    private String schDeptName;
    private String bzDeptFlow;
    private String bzDeptName;
    private String isRequest;
    private String schMonth;


}
