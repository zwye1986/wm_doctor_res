package com.pinde.sci.webservice.bean.shzs;

/**
 * Created by yex on 2018-11-12.
 */
public class ResultInfo {

    /**
     * 组织返回数据
     * @param resultCode 000000	成功	          平台服务异常编码
     * @param resultMSG
     * @param qty
     * @result
     */
     private String resultCode;
     private String resultMsg;
     private Object response;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
