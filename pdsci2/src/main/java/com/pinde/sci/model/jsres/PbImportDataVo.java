package com.pinde.sci.model.jsres;

import lombok.Data;

import java.util.List;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/09/23/8:33
 * @Description: 排班导入到db的接受类
 */
@Data
public class PbImportDataVo implements java.io.Serializable {

    private String doctorFlow;

    private String doctorName;

    private String rotationFlow;
    private String rotationName;

    private String orgFlow;

    private String userFlow;

    private String userName;

    private String sessionNumber;

    private String schYear;


    private List<PbImportDataItem> schDeptList;



}
