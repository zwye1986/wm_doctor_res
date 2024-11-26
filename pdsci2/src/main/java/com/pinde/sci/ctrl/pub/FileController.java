package com.pinde.sci.ctrl.pub;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.PubFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pub/file")
public class FileController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IFileBiz pubFileBiz;

    @RequestMapping(value = {"/down"}, method = RequestMethod.GET)
    public void down(String fileFlow, final HttpServletResponse response) throws Exception {
        PubFile file = pubFileBiz.readFile(fileFlow);
        pubFileBiz.down(file, response);
    }

    @RequestMapping("/downFileByUrl")
    public void downFileByUrl(String fileCode, HttpServletResponse response, HttpServletRequest request) throws Exception {
        //文件是否存在
        Boolean fileExists = false;
        if (StringUtil.isNotBlank(fileCode)) {
            // fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
            // System.out.println("----------------------------------"+fileName);
            String fileName = InitConfig.getSysCfg(fileCode) + ".docx";
            //String fileName = "";
            /*if("srm_expert_proj_notes_file_1".equals(fileCode)){
                fileName = "项目评审操作手册.docx";
            }
            if("srm_expert_proj_notes_file_2".equals(fileCode)){
                fileName = "网评专家注意.docx";
            }*/

            byte[] data = null;
            long dataLength = 0;
            //       获取文件物理路径
            String filePath = InitConfig.getSysCfg("srm_apply_file") + File.separator + fileName;
            //System.out.println("----------------------------------"+filePath);
            File downLoadFile = new File(filePath);
            //      文件是否存在
            if (downLoadFile.exists()) {
                InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
                data = new byte[fis.available()];
                dataLength = downLoadFile.length();
                fis.read(data);
                fis.close();
                fileExists = true;
            }
            if (fileExists) {
                try {
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                    response.reset();
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
                    response.addHeader("Content-Length", "" + dataLength);
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                    if (data != null) {
                        outputStream.write(data);
                    }
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    fileExists = false;
                }
            }
        } else {
            fileExists = false;
        }
        if (!fileExists) {
            //设置页面编码为UTF-8
            response.setHeader("Content-Type", "text/html;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,请联系管理员</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
            outputStream.flush();
            outputStream.close();
        }
    }

    @RequestMapping("/delFileByFlow")
    public
    @ResponseBody
    String delFileByFlow(String fileFlow) {
        int i = 0;
        if(StringUtil.isNotBlank(fileFlow)) {
            List<String> fileFlows = new ArrayList<>();
            fileFlows.add(fileFlow);
            i = pubFileBiz.deleteFile(fileFlows);
        }
        if(i > 0){
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }
}
