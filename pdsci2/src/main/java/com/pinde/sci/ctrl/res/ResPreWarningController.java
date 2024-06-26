package com.pinde.sci.ctrl.res;


import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.SysDeptMapper;
import com.pinde.sci.dao.sch.SchArrangeResultExtMapper;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.GeneralIdNameModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/prewarning")
public class ResPreWarningController extends GeneralController {

    @Autowired
    SchArrangeResultExtMapper schArrangeResultExtMapper;
    @Autowired
    SysDeptMapper sysDeptMapper;

    @RequestMapping(value="/list/{scope}")
    public String list(@PathVariable String scope, Model model,String isInit,String sessionNumber,String schEndDate,String schStartDate){
        SysUser currUser = GlobalContext.getCurrentUser();
        if(StringUtil.isNotBlank(isInit)) {
            schEndDate = DateUtil.getCurrDate();
            schStartDate = DateUtil.addDate(DateUtil.getCurrDate(), -30);
        }
        model.addAttribute("schStartDate", schStartDate);
        model.addAttribute("schEndDate", schEndDate);
        model.addAttribute("sessionNumber", sessionNumber);
        Map<String,String> param=new HashMap<>();
        param.put("schStartDate", schStartDate);
        param.put("schEndDate", schEndDate);
        param.put("sessionNumber", sessionNumber);
        param.put("orgFlow", currUser.getOrgFlow());

        //查询即将入科学员
        List<GeneralIdNameModel> listBefore =schArrangeResultExtMapper.preWarningBefore(param);
        model.addAttribute("listBefore", listBefore);
        //查询正在轮转学员
        List<GeneralIdNameModel> listIng =schArrangeResultExtMapper.preWarningIng(param);
        model.addAttribute("listIng", listIng);
        //查询即将出科学员
        List<GeneralIdNameModel> listAfter =schArrangeResultExtMapper.preWarningAfter(param);
        model.addAttribute("listAfter", listAfter);

        return "res/hospital/preWarning";
    }
    @RequestMapping(value="/detail")
    public String detail( Model model,String type,String deptFlow,String sessionNumber,String schEndDate,String schStartDate){
        Map<String,String>  param = assembleWarningParam(deptFlow,sessionNumber,schEndDate,schStartDate);
        List<Map<String,Object>> list=getDetailList(param,type);
        model.addAttribute("list",list);
        return "res/hospital/preDetail";
    }

    /**
     * 单个类型警告导出
     * 入科异常/出科异常/出科考核异常
     */
    @RequestMapping(value="/warningExport", method={RequestMethod.POST,RequestMethod.GET})
    public void warningExport( Model model,String type,String deptFlow,String sessionNumber,String schEndDate,String schStartDate,HttpServletResponse response)  throws Exception{
        Map<String,String>  param = assembleWarningParam(deptFlow,sessionNumber,schEndDate,schStartDate);
        String msg="";
        switch (type){
            case "WarningBefore":
                msg = "入科异常学员列表";
                break;
            case "WarningIng":
                msg = "出科异常学员列表";
                break;
            case "WarningAfter":
                msg = "出科考核异常学员列表";
                break;
            case "WarningAll":
                msg = "科室轮转异常学员列表";
                break;
        }

        String headLine1 = msg;
        String headLine2 = "时间：" +schStartDate + "~" + schEndDate;
        String[] headLines ={msg,headLine2};

        String[] titles = {
                ":序号",
                "deptName:轮转科室",
                "userName:学员",
                "trainingSpeName:培训专业",
                "trainingTypeName:对应专业",
                "sessionNumber:年级",
                "schStartDate:轮转开始时间",
                "schEndDate:轮转结束时间",
                "reason:异常原因"
        };
        HSSFWorkbook wb = new HSSFWorkbook();
        List dataList = null;
        if("WarningAll".equals(type)){
            dataList =  getDetailList(param,"WarningBefore");
            headLines[0] = msg +" - 入科";
            ExcleUtile.createSimpleSheetWithHeadline(headLines,titles,dataList,wb,"入科异常学员列表");
            headLines[0] = msg +" - 出科";
            dataList =  getDetailList(param,"WarningIng");
            ExcleUtile.createSimpleSheetWithHeadline(headLines,titles,dataList,wb,"出科异常学员列表");
            headLines[0] = msg +" - 出科考核";
            dataList =  getDetailList(param,"WarningAfter");
            ExcleUtile.createSimpleSheetWithHeadline(headLines,titles,dataList,wb,"出科考核异常学员列表");
        }else{
            dataList =  getDetailList(param,type);
            if(StringUtil.isNotEmpty(deptFlow)){
                SysDept sysDept = sysDeptMapper.selectByPrimaryKey(deptFlow);
                String deptName = sysDept.getDeptName();
                deptName = deptName.replace("/",",");
                msg=msg+"-"+deptName;
                headLines[0] = msg;
            }
            ExcleUtile.createSimpleSheetWithHeadline(headLines,titles,dataList,wb,msg);
        }

        String fileName = msg +".xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    /**
     * 获取警告列表
     */
    private List<Map<String,Object>> getDetailList(Map<String,String>  param,String type){
        List<Map<String,Object>> list=null;
        if("WarningBefore".equals(type))
        {
            list =schArrangeResultExtMapper.preWarningBeforeList(param);
        }
        if("WarningIng".equals(type))
        {
            list =schArrangeResultExtMapper.preWarningIngList(param);
        }
        if("WarningAfter".equals(type))
        {
            list =schArrangeResultExtMapper.preWarningAfterList(param);
        }
        return list;
    }

    /**
     * 组装入参
     */
    private Map<String,String> assembleWarningParam(String deptFlow,String sessionNumber,String schEndDate,String schStartDate){
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String,String> param=new HashMap<>();
        param.put("schStartDate", schStartDate);
        param.put("schEndDate", schEndDate);
        param.put("deptFlow", deptFlow);
        param.put("sessionNumber", sessionNumber);
        param.put("orgFlow", currUser.getOrgFlow());
        return param;
    }


}
