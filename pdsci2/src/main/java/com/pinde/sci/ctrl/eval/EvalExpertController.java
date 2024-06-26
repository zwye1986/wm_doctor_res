package com.pinde.sci.ctrl.eval;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.ResPowerCfgMapper;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.erp.ContractForm;
import com.pinde.sci.form.erp.ContractTimeForm;
import com.pinde.sci.form.erp.CustomerUserForm;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/eval/expert")
public class EvalExpertController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @RequestMapping(value = "/main")
    public String main(Model model) {
        return "eval/expert/main";
    }
    @RequestMapping(value = "/edit")
    public String edit(Model model,String userFlow) {
        SysUser user=userBiz.readSysUser(userFlow);
        model.addAttribute("user",user);
        String roleFlow=InitConfig.getSysCfg("eval_expert_role_flow");
        model.addAttribute("roleFlow",roleFlow);
        return "eval/expert/edit";
    }

    @RequestMapping(value = "/list")
    public String list(Integer currentPage, HttpServletRequest request, Model model,  String userName,  String statusId) {
        String roleFlow=InitConfig.getSysCfg("eval_expert_role_flow");
        String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
        //组织用户查询map
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("roleFlow",roleFlow);
        paramMap.put("wsId",wsId);
        paramMap.put("userName",userName);
        paramMap.put("statusId",statusId);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysUser>  userList= userBiz.searchUserWithRole(paramMap);
        model.addAttribute("userList",userList);
        return "eval/expert/list";
    }

    @RequestMapping(value={"/save"},method=RequestMethod.POST)
    public @ResponseBody String save(SysUser user,String roleFlow){
        //新增用户是判断
        if(StringUtil.isBlank(user.getUserFlow())){
            //判断用户id是否重复
            SysUser old = userBiz.findByUserCode(user.getUserCode());
            if(old!=null){
                return GlobalConstant.USER_CODE_REPETE;
            }
        }else{
            String userFlow = user.getUserFlow();
            //判断用户id是否重复
            SysUser old = userBiz.findByUserCodeNotSelf(userFlow,user.getUserCode());
            if(old!=null){
                return GlobalConstant.USER_CODE_REPETE;
            }
        }
        if(StringUtil.isNotBlank(user.getSexId())){
            user.setSexName(UserSexEnum.getNameById(user.getSexId()));
        }
        if(StringUtil.isNotBlank(user.getPostId())){
            user.setPostName(DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
        }else {
            user.setPostName("");
        }
        if(StringUtil.isNotBlank(roleFlow)){
            userBiz.saveUser(user,roleFlow);
        }else{
            userBiz.saveUser(user);
        }

        //如果当前用户修改自己的信息，同步到session
        SysUser currUser = GlobalContext.getCurrentUser();
        if(currUser.getUserFlow().equals(user.getUserFlow())){
            currUser = userBiz.readSysUser(user.getUserFlow());
            setSessionAttribute(GlobalConstant.CURRENT_USER, currUser);
            setSessionAttribute(GlobalConstant.CURRENT_USER_NAME, user.getUserName());
            setSessionAttribute(GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(currUser));
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = {"/exportExpert"})
    public void exportExpert(  String userName,  String statusId, HttpServletResponse response,
                                  HttpServletRequest request) throws Exception {

        String roleFlow=InitConfig.getSysCfg("eval_expert_role_flow");
        String wsId = (String)getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
        //组织用户查询map
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("roleFlow",roleFlow);
        paramMap.put("wsId",wsId);
        paramMap.put("userName",userName);
        paramMap.put("statusId",statusId);
        List<SysUser>  userList= userBiz.searchUserWithRole(paramMap);

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
        sheet.addMergedRegion(new org.apache.poi.hssf.util.CellRangeAddress(0, 0, 0, 12));//合并单元格
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("评估专家信息一览表");

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        String[] titles = new String[]{
                "姓名",
                "登录名",
                "性别",
                "专业",
                "职务",
                "出生年月",
                "工作单位",
                "行政职务",
                "技术职称",
                "是否为组长",
                "手机号码",
                "电子邮箱",
                "状态"
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
        if (userList != null && !userList.isEmpty()) {
            for (int i = 0; i < userList.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                SysUser user = userList.get(i);
                String isExpert=user.getSrmExpertFlag();
                if(GlobalConstant.FLAG_Y.equals(isExpert))
                {
                    isExpert="是";
                }else{
                    isExpert="否";
                }
                resultList = new String[]{
                        user.getUserName(),
                        user.getUserCode(),
                        user.getSexName(),
                        user.getResTrainingSpeName(),
                        user.getPostName(),
                        user.getUserBirthday(),
                        user.getWorkOrgName(),
                        user.getTitleName(),
                        user.getEducationName(),
                        isExpert ,
                        user.getUserPhone(),
                        user.getUserEmail(),
                        user.getStatusDesc()
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                }
            }
        }
        String fileName = "评估专家信息一览表.xls";
        fileName = new String(fileName.getBytes("gbk"),"ISO8859-1" );
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }
}