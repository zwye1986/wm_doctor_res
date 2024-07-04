package com.pinde.res.ctrl.gzdh;

import com.transfer.ws.InfoTransfer;
import com.transfer.ws.InfoTransferService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import javax.xml.namespace.QName;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class GzdhResUtil {

    private static final QName SERVICE_NAME = new QName("http://transfer.pd.com/", "InfoTransferService");

    public static String getBingLiHao(String blh) {
        InfoTransferService ss = new InfoTransferService();
        InfoTransfer port = ss.getInfoTransferPort();
        String _getResrecData__return = port.getResrecData(blh, "9F9A5BC7111A4688DEA79F25C7C98EFD");
        //System.out.println("getResrecData.result=" + _getResrecData__return);
        return _getResrecData__return;
    }

    public static void main(String[] args) {
        InfoTransferService ss = new InfoTransferService();
        InfoTransfer port = ss.getInfoTransferPort();
        String _getResrecData__return = port.getResrecData("222", "9F9A5BC7111A4688DEA79F25C7C98EFD");
        //System.out.println("getResrecData.result=" + _getResrecData__return);

    }
    private static String _post(String url, String body) {
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