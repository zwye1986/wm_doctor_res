package com.pinde.sci.job;

import java.text.ParseException;

/**
 * @创建人 zsq
 * @创建时间 2020/10/12
 * 描述
 */
public interface Job {
    public void localAppInitData() throws ParseException;

    public void localDoctorLunZhuanDataInsertDataNew() throws ParseException;

    public void localActivityInitData() throws ParseException;

    public void localOutDeptData() throws ParseException;

    public void localApp_InsertData() throws ParseException;

    public void localDoctorLunZhuanDataInsertData();

    public void monthStaticData() throws ParseException;

}
