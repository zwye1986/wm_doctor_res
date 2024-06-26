package com.pinde.sci.ctrl.eval;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IEvalCfgBiz;
import com.pinde.sci.biz.eval.IExpertOrgBiz;
import com.pinde.sci.biz.eval.IExpertOrgSpeBiz;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.model.mo.ExpertEvalCfg;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/eval/evalOrgPm")
public class EvalOrgPMController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IEvalCfgBiz evalCfgBiz;
    @Autowired
    private IExpertOrgBiz evalOrgBiz;
    @Autowired
    private IExpertOrgSpeBiz expertOrgSpeBiz;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private IResOrgSpeBiz resOrgSpeBiz;

    @RequestMapping(value = "/main")
    public String main(Model model) {
        return "eval/evalOrgPm/main";
    }

    @RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
    public String list(String orgName,String orderType, Integer currentPage, HttpServletRequest request, Model model,String evalYear){
        if(StringUtil.isNotBlank(evalYear)) {
            ExpertEvalCfg evalCfg=evalCfgBiz.findByEvalYearFirst(evalYear);
            model.addAttribute("evalCfg",evalCfg);
            if(evalCfg!=null) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("orgName", orgName);
                paramMap.put("orderType", orderType);
                paramMap.put("evalYear", evalYear);
                PageHelper.startPage(currentPage, getPageSize(request));
                List<Map<String,Object>> sysList = evalOrgBiz.evalOrgQueryOrgPm(paramMap);
                model.addAttribute("sysList", sysList);
            }
        }
        return "eval/evalOrgPm/list";
    }

    @RequestMapping(value = {"/exportOrgResult"})
    public void exportOrgResult(  String orgName,String orderType, HttpServletResponse response,
                                  HttpServletRequest request, Model model,String evalYear) throws Exception {


        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orgName", orgName);
        paramMap.put("orderType", orderType);
        paramMap.put("evalYear", evalYear);
        List<Map<String,Object>> sysList = evalOrgBiz.evalOrgQueryOrgPm(paramMap);

        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleCenter.setWrapText(true);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleLeft.setWrapText(true);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        styleLeft.setWrapText(true);

        HSSFRow rowDep = sheet.createRow(0);//第一行
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(0, 0, 0, 3));//合并单元格
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue(evalYear+"年度基地排名一览表");

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        String[] titles = new String[]{
                "基地名称",
                "年度",
                "专家评分",
                "排名"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowTwo.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        //行数
        int rowNum = 2;
        //存放在excel中的行数据
        String[] resultList = null;
        //对当前师资信息List循环
        if (sysList != null && !sysList.isEmpty()) {
            for (int i = 0; i < sysList.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                Map<String,Object> bean = sysList.get(i);
                String order=(String) bean.get("orderNum");
                if("-".equals((String) bean.get("score")))
                    order="-";
                resultList = new String[]{
                        (String) bean.get("orgName"),
                        (String) bean.get("evalYear"),
                        (String) bean.get("score"),
                        order
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                }
            }
        }
        String fileName = evalYear+"年度基地排名一览表.xls";
        fileName = new String(fileName.getBytes("gbk"),"ISO8859-1" );
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }
}