package com.pinde.sci.ctrl.zsey;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.zsey.IZseyDeptBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.ZseyAppointMain;
import com.pinde.sci.model.mo.ZseyAppointMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/zsey/dept")
public class ZseyDeptController extends GeneralController {

    @Autowired
    private IZseyDeptBiz deptBiz;

    /**
     * 培训预约信息记录
     * @param trainBeginDate 开始日期
     * @param trainEndDate 结束日期
     * @param teacherName 任课老师
     * @param auditStatusId 审核状态
     * @param currentPage 当前页码
     * @param model 视图
     * @return
     */
    @RequestMapping("/appointList")
    public String appointList(String trainBeginDate, String trainEndDate, String teacherName, String auditStatusId, Integer currentPage,Model model){
        Map<String,String> param = new HashMap<>();
        param.put("trainBeginDate",trainBeginDate);
        param.put("trainEndDate",trainEndDate);
        param.put("teacherName",teacherName);
        param.put("auditStatusId",auditStatusId);
        PageHelper.startPage(currentPage,10);
        List<ZseyAppointMain> dataList = deptBiz.queryAppointList(param);
        model.addAttribute("dataList",dataList);
        return "zsey/dept/appointList";
    }

    //模糊检索-任课老师
    @RequestMapping("/searchTeacher")
    @ResponseBody
    public List<SysUser> searchTeacher(){
        return deptBiz.searchTeacher();
    }

    /**
     * 新增/编辑预约信息页面
     * @param appointFlow 预约flow
     * @param model
     * @return
     */
    @RequestMapping(value="/editAppoint")
    public String editAppoint(String appointFlow, Model model){
        if(StringUtil.isNotBlank(appointFlow)){
            ZseyAppointMain appoint = deptBiz.queryAppointByFlow(appointFlow);
            List<ZseyAppointMaterial> materialList = deptBiz.queryMaterialList(appointFlow);
            model.addAttribute("appoint",appoint);
            model.addAttribute("materialList",materialList);
        }
        return "zsey/dept/editAppoint";
    }

    @RequestMapping(value="/qryMaterial")
    public String qryMaterial(String appointFlow, Model model){
        if(StringUtil.isNotBlank(appointFlow)){
            List<ZseyAppointMaterial> materialList = deptBiz.queryMaterialList(appointFlow);
            model.addAttribute("materialList",materialList);
        }
        return "zsey/dept/qryMaterial";
    }

    /**
     * 新增/编辑培训预约信息保存操作
     */
    @RequestMapping("/saveAppoint")
    @ResponseBody
    public String saveAppoint(ZseyAppointMain main,String [] recordFlowList, String [] materialFlowList, String [] materialNameList, String [] materialNum){
        Map<String,Object> param = new HashMap<>();
        param.put("appointMain",main);
        param.put("recordFlowList", recordFlowList==null?null:Arrays.asList(recordFlowList));//新增时为null
        param.put("materialFlowList", materialFlowList==null?null:Arrays.asList(materialFlowList));
        param.put("materialNameList", materialNameList==null?null:Arrays.asList(materialNameList));
        param.put("materialNumList", Arrays.asList(materialNum==null || materialNum.length == 0?new String[]{""}:materialNum));
        int num = deptBiz.saveAppoint(param);
        if (num > 0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 所需设备耗材查询
     */
    @RequestMapping(value="/selectMaterial")
    public String selectMaterial(String appointFlow, String materialName, Model model) {
        List<Map<String, Object>> materialList = deptBiz.queryAppointMaterialList(materialName);
        if(StringUtil.isNotBlank(appointFlow)){
            List<String> exitMaterialLst = new ArrayList<>();
            Map<String,String> materialMap = new HashMap<>();
            List<ZseyAppointMaterial> matList = deptBiz.queryMaterialList(appointFlow);
            if(null != matList && matList.size() > 0){
                for(int i=0; i< matList.size(); i++){
                    exitMaterialLst.add(matList.get(i).getMaterialFlow());
                    materialMap.put(matList.get(i).getMaterialFlow(),matList.get(i).getMaterialNumber());
                }
            }
            model.addAttribute("exitMaterialLst",exitMaterialLst);
            model.addAttribute("materialMap",materialMap);
        }
        model.addAttribute("materialList",materialList);
        return "zsey/dept/appointMaterial";
    }

    /**
     * 预约信息删除操作
     */
    @RequestMapping("/delAppoint")
    @ResponseBody
    public String delAppoint(String appointFlow){
        int num = deptBiz.delAppoint(appointFlow);
        if (num == GlobalConstant.ONE_LINE) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 预约学员导出操作
     */
    @RequestMapping(value="/expAppoint", method={RequestMethod.POST,RequestMethod.GET})
    public void expAppoint(String trainBeginDate, String trainEndDate, String teacherName, String auditStatusId,HttpServletResponse response) throws Exception {
        Map<String,String> param = new HashMap<>();
        param.put("trainBeginDate",trainBeginDate);
        param.put("trainEndDate",trainEndDate);
        param.put("teacherName",teacherName);
        param.put("auditStatusId",auditStatusId);
        List<ZseyAppointMain> appointList = deptBiz.queryAppointList(param);
        if(null != appointList && !appointList.isEmpty()){
            for(ZseyAppointMain main : appointList) {
                main.setEndTime(main.getBeginTime()+"~"+main.getEndTime());//存储时间区域
                main.setBeginTime(DateUtil.getWeekFromDate(main.getTrainDate(),"3"));//存储星期值
            }
        }
        String[] titles = new String[]{
                "trainDate:培训日期",
                "beginTime:星期",
                "endTime:培训时间",
                "traineesName:培训对象",
                "traineesSpeName:专业",
                "traineesNumber:学员人数/分组数",
                "projectName:培训项目",
                "teacherName:任课老师",
                "teacherPhone:联系方式",
                "trainRoomName:培训教室",
                "auditStatusName:审核状态"
        };
        String fileName = "培训预约信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, appointList, response.getOutputStream());
    }
}