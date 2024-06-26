package com.pinde.sci.webservice;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class ZseyResUtil {
    public static final String IP = "172.18.41.133";
    public static final String SYSTEMCODE = "TECHPLANT_SYSTEM";
    public static final String SYSTEMPASSWORD = "0";

    public static void main(String[] args) {
        // 1、获取Token
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><GETTOKENREQUEST>" +
                "<REQUESTID>1000000</REQUESTID>" +
                "<REQUESTIP>"+IP+"</REQUESTIP>" +
                "<SYSTEMCODE>"+SYSTEMCODE+"</SYSTEMCODE>" +
                "<SYSTEMPASSWORD>"+SYSTEMPASSWORD+"</SYSTEMPASSWORD>" +
                "</GETTOKENREQUEST>";

        String accessToken = _post("http://172.18.41.96:9090/hip/hipService/getToken/",  xml); //3fde93fd79654df388f2f3f9d61a77f5c87df551d3993a2da0cfb742cae9150c7080125cffe412ee050dfa4ce58c39c8e8a7e10256b0bccb
        System.out.println("accessToken="+accessToken);
//        accessToken=<?xml version="1.0" encoding="UTF-8" standalone="yes"?><GETTOKENRESPONSE><REQUESTID>1000000</REQUESTID><MSGCODE>000000</MSGCODE><MSGDESC>响应成功</MSGDESC><ACCESSTOKEN>3fde93fd79654df388f2f3f9d61a77f5c87df551d3993a2da0cfb742cae9150c7080125cffe412ee050dfa4ce58c39c8e8a7e10256b0bccb</ACCESSTOKEN></GETTOKENRESPONSE>
//        <?xml version="1.0" encoding="utf-8"?>
//        <GETTOKENRESPONSE>
//        <REQUESTID>1000000</REQUESTID>
//        <MSGCODE>000000</MSGCODE>
//        <MSGDESC>响应成功</MSGDESC>
//        <ACCESSTOKEN>3fde93fd79654df388f2f3f9d61a77f5c87df551d3993a2da0cfb742cae9150c7080125cffe412ee050dfa4ce58c39c8e8a7e10256b0bccb</ACCESSTOKEN>
//        </GETTOKENRESPONSE>

        // 2、根据病历号获取信息
        xml =
        "<COMMONREQUEST>" +
                "<REQUESTID>1000001</REQUESTID>" +
                "<REQUESTIP>172.18.41.133</REQUESTIP>" +
                "<ACCESSTOKEN>3fde93fd79654df388f2f3f9d61a77f5c87df551d3993a2da0cfb742cae9150c7080125cffe412ee050dfa4ce58c39c8e8a7e10256b0bccb</ACCESSTOKEN>" +
                "<SERVICEVERSION>1.0</SERVICEVERSION>" +
                "<SERVICECODE>W003001</SERVICECODE>" +
                "<REQUESTDATA>" +
                    "<REQUEST>" +
                        "<IPSEQNOTEXT>575898</IPSEQNOTEXT>" +
                        "<IPTIMES>5</IPTIMES>" +
                    "</REQUEST>" +
                "</REQUESTDATA>" +
                "</COMMONREQUEST>";

        String result = _post("http://172.18.41.96:9090/hip/hipService/CommonPort/",  xml);
        System.out.println("result="+result);

//        result=<?xml version="1.0" encoding="UTF-8" standalone="yes"?><COMMONRESPONSE><REQUESTID>1000001</REQUESTID><MSGCODE>000000</MSGCODE><MSGDESC>响应成功</MSGDESC><RESPONSEDATA><RESPONSE><RECORD><PATIENTID>1006839840</PATIENTID><PATIENTNAME>林国平</PATIENTNAME><BIRTHDAY>1968-11-26 00:00:00.0</BIRTHDAY><DEPARTMENTID>2362</DEPARTMENTID><DEPARTMENTNO>05106</DEPARTMENTNO><DEPARTMENTNAME>骨外科三区</DEPARTMENTNAME><INDEPARTMENTID>2362</INDEPARTMENTID><INDEPARTMENTNO>05106</INDEPARTMENTNO><INDEPARTMENTNAME>骨外科三区</INDEPARTMENTNAME><INDATETIME>2017-02-13 17:02:00.0</INDATETIME><INPATIENTID>1002255400</INPATIENTID><IPFLAG>5</IPFLAG><IPTIMES>5</IPTIMES><SEXFLAG>1</SEXFLAG><SURGERYLIST><SURGERY><NAME></NAME><DATE>2017-02-27 08:48:00.0</DATE></SURGERY><SURGERY><NAME></NAME><DATE>2017-03-01 09:16:00.0</DATE></SURGERY></SURGERYLIST></RECORD></RESPONSE></RESPONSEDATA></COMMONRESPONSE>

    }

    private static String _post(String url, String body){
        HttpClient httpClient = new HttpClient();
        PostMethod request = new PostMethod(url);
        HttpMethodParams param = request.getParams();
        param.setContentCharset("UTF-8");
        request.setRequestBody(body);
        request.addRequestHeader("Content-Type", "text/plain;charset=UTF-8");

        StringBuffer buf = new StringBuffer();
        BufferedReader in = null;
        try {
            httpClient.executeMethod(request);
            InputStream stream = request.getResponseBodyAsStream();
            in = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line;
            while (null != (line = in.readLine())) {
                buf.append(line).append("\n");
            }
            request.releaseConnection();
        } catch (Exception e) {
            e.printStackTrace();
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e1) {
					e1.printStackTrace();
                }
            }
        }

        String result = buf.toString();
        return result;
    }
}