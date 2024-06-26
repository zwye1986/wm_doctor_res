package com.pinde.sci.ctrl.res;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorOrgHistoryBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.res.ResSxsScoreExportForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/sxs")
public class ResSxsScoreController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResSxsScoreController.class);

	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorOrgHistoryBiz docOrgHisBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	/**
	 * 培训查询
	 */
	@RequestMapping(value = "/schScore/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
	public String registryNote(@PathVariable String scope,
							   String sessionNumber,
							   String isShow,
							   String orgFlow,
							   String schDeptFlow,
							   String doctorName,
							   Integer currentPage,
							   Model model,HttpServletRequest request) {

		SysUser currUser = GlobalContext.getCurrentUser();

		ResDoctorExt doctorExt = new ResDoctorExt();
		SysUser user = new SysUser();
		doctorExt.setSysUser(user);
		doctorExt.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isBlank(sessionNumber)&&StringUtil.isNotBlank(isShow)&&isShow.equals(GlobalConstant.FLAG_Y))
		{
			List<SysDict> list=DictTypeEnum.DoctorSessionNumber.getSysDictList();
			if(list!=null&&list.size()>0)
			{
				sessionNumber=list.get(0).getDictId();
			}
		}
		model.addAttribute("sessionNumber",sessionNumber);
		doctorExt.setSessionNumber(sessionNumber);
		user.setUserName(doctorName);

		if (currentPage == null) {
			currentPage = 1;
		}
        String medicineTypeId="";
		if (GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(scope)) {
			doctorExt.setOrgFlow(currUser.getOrgFlow());
			orgFlow=currUser.getOrgFlow();
			List<SchDept> deptList = schDeptBiz.searchSchDeptList(currUser.getOrgFlow());
			model.addAttribute("deptList", deptList);
		} else if (GlobalConstant.USER_LIST_GLOBAL.equals(scope)) {
			doctorExt.setOrgFlow(orgFlow);
			if(StringUtil.isNotBlank(orgFlow))
			{
				List<SchDept> deptList = schDeptBiz.searchSchDeptList(orgFlow);
				model.addAttribute("deptList", deptList);
			}
            medicineTypeId=currUser.getMedicineTypeId();
		}
		model.addAttribute("orgFlow",orgFlow);
		PageHelper.startPage(currentPage,  getPageSize(request));
		List<ResDoctorExt> doctorExtList = doctorBiz.searchDocUser(doctorExt, medicineTypeId);
		model.addAttribute("doctorList", doctorExtList);
		if (doctorExtList != null && doctorExtList.size() > 0) {
			Map<String,ResDoctorSchProcess> resultMap=new HashMap<>();
			for(ResDoctorExt doctor:doctorExtList)
			{	ResDoctorSchProcess temp=new ResDoctorSchProcess();
				temp.setUserFlow(doctor.getDoctorFlow());
				temp.setRecordStatus(GlobalConstant.FLAG_Y);
				temp.setSchDeptFlow(schDeptFlow);
				temp.setSchFlag(GlobalConstant.FLAG_Y);
				List<ResDoctorSchProcess> processes=processBiz.searchDoctorProcess(null,null,temp);
				if(processes!=null&& processes.size()>0){
					for(ResDoctorSchProcess p:processes)
					{
						resultMap.put(p.getUserFlow()+p.getSchDeptFlow(),p);
					}
				}
			}
			model.addAttribute("resultMap",resultMap);
		}
		model.addAttribute("scope", scope);
		if (GlobalConstant.USER_LIST_GLOBAL.equals(scope)) {
			List<SysOrg> orgList = orgBiz.searchSysOrg();
			model.addAttribute("orgList", orgList);
		}
		return "res/sxs/sxsList";
	}

	@RequestMapping("/searchSysOrg")
	@ResponseBody
	public List<SysOrg> searchSysOrg() {
		List<SysOrg> orgList = orgBiz.searchSysOrg();
		return orgList;
	}


    @RequestMapping(value = "/expoertResSxsScore/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String expoertResSxsScore(@PathVariable String scope,
                                     String sessionNumber,
                                     String orgFlow,
                                     String schDeptFlow,
                                     String doctorName, HttpServletResponse response) throws Exception {

        SysUser currUser = GlobalContext.getCurrentUser();

        ResDoctorExt doctorExt = new ResDoctorExt();
        SysUser user = new SysUser();
        doctorExt.setSysUser(user);
        doctorExt.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        doctorExt.setSessionNumber(sessionNumber);
        user.setUserName(doctorName);
        List<SchDept> deptList = new ArrayList<>();
        String medicineTypeId="";
        if (GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(scope)) {
            doctorExt.setOrgFlow(currUser.getOrgFlow());
            deptList = schDeptBiz.searchSchDeptList(currUser.getOrgFlow());
        } else if (GlobalConstant.USER_LIST_GLOBAL.equals(scope)) {
            doctorExt.setOrgFlow(orgFlow);
            if(StringUtil.isNotBlank(orgFlow))
            {
                deptList = schDeptBiz.searchSchDeptList(orgFlow);
            }
            medicineTypeId=currUser.getMedicineTypeId();
        }
        SchDept dept = null;
        if(StringUtil.isNotBlank(schDeptFlow)){
            dept = schDeptBiz.readSchDept(schDeptFlow);
        }
        String[] titles=null;
        if(null != dept){
            titles=new String[2];
            titles[0] = "userName:姓名\\科室";
            titles[1] = dept.getSchDeptFlow()+":"+dept.getSchDeptName();
        }else{
            titles=new String[deptList.size()+1];
            titles[0] = "userName:姓名\\科室";
            for(int i=0;i<deptList.size();i++){
                titles[i+1]= deptList.get(i).getSchDeptFlow()+":"+deptList.get(i).getSchDeptName();
            }
        }
        List<ResSxsScoreExportForm> sxsScoreExportForms = new ArrayList<>();
        List<ResDoctorExt> doctorExtList = doctorBiz.searchDocUser(doctorExt, medicineTypeId);
        if (doctorExtList != null && doctorExtList.size() > 0) {

            for(ResDoctorExt doctor:doctorExtList){
                ResSxsScoreExportForm scoreExportForm = new ResSxsScoreExportForm();
                scoreExportForm.setUserName(doctor.getSysUser().getUserName());
                scoreExportForm.setUserFlow(doctor.getSysUser().getUserFlow());
                ResDoctorSchProcess temp=new ResDoctorSchProcess();
                temp.setUserFlow(doctor.getDoctorFlow());
                temp.setRecordStatus(GlobalConstant.FLAG_Y);
                temp.setSchDeptFlow(schDeptFlow);
                temp.setSchFlag(GlobalConstant.FLAG_Y);
                List<ResDoctorSchProcess> processes=processBiz.searchDoctorProcess(null,null,temp);
                Map<String,ResDoctorSchProcess> resultMap=new HashMap<>();
                if(processes!=null&& processes.size()>0){
                    for(ResDoctorSchProcess p:processes)
                    {
                        resultMap.put(p.getUserFlow()+p.getSchDeptFlow(),p);
                    }
                }
                scoreExportForm.setMap(resultMap);
                sxsScoreExportForms.add(scoreExportForm);
            }
        }

        exportExcleExpertResScore(titles, sxsScoreExportForms, response.getOutputStream());
        String fileName = "实习生出科成绩统计.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");

        return "res/sxs/sxsList";
    }


    /**
     * 实习生出科成绩导出
     * @param titles
     * @param titles2
     * @param dataList
     * @param os
     * @throws Exception
     */
    private void exportExcleExpertResScore(String[] titles,List<ResSxsScoreExportForm> dataList,OutputStream os) throws Exception{
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        List<String> paramIds = new ArrayList<String>();

        HSSFCell cell = null;
        for(int i = 0 ; i<titles.length ; i++){
            String[] title = titles[i].split(":");
            cell = row.createCell(i);
            cell.setCellValue(title[1]);
            cell.setCellStyle(style);
            paramIds.add(title[0]);
            int length = title[1].length();
            sheet.setColumnWidth(i, length*700);
        }
        if(dataList!=null){
            for(int i=0; i<dataList.size() ; i++){
                ResSxsScoreExportForm item = dataList.get(i);
                row = sheet.createRow(i + 1);
                String result = null;
                for(int j = 0 ; j <paramIds.size();j++){
                    String paramId = paramIds.get(j);
                    if(paramId.equals("userName")){
                        result = item.getUserName();
                    }else{
                        ResDoctorSchProcess process = item.getMap().get(item.getUserFlow()+paramId);
                        if(null != process) {
                            BigDecimal score = process.getSchScore();
                            if (null == score) {
                                result = "";
                            } else {
                                result = score.toString();
                            }
                        }else{
                            result = "";
                        }
                    }
                    row.createCell(j).setCellValue(result);
                }

            }
        }
        wb.write(os);
    }

}


