package com.pinde.sci.ctrl.res;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.res.IDgdhyyActivityBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.DgdhyyActivity;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/res/dgdhyyActivity")
public class ResDgdhyyActivityController extends GeneralController{
    @Autowired
    private IDgdhyyActivityBiz dgdhyyActivityBiz;

    @RequestMapping("/dgdhyyActivityList")
    public String dgdhyyActivityList(DgdhyyActivity dgdhyyActivity, Model model, Integer currentPage, HttpServletRequest request){
        SysUser currentUser = GlobalContext.getCurrentUser();
        dgdhyyActivity.setOrgFlow(currentUser.getOrgFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List< DgdhyyActivity> dgdhyyActivityList = dgdhyyActivityBiz.searchDgdhyyActivity(dgdhyyActivity);
        model.addAttribute("dgdhyyActivityList",dgdhyyActivityList);
        return "res/dgdhyyActivity/dgdhyyActivityList";
    }

    @RequestMapping("/delDgdhyyActivity")
    @ResponseBody
    public int delDgdhyyActivity(DgdhyyActivity dgdhyyActivity){
        dgdhyyActivity.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        return dgdhyyActivityBiz.editDgdhyyActivity(dgdhyyActivity);
    }

    /**
     * 导入分数页面
     */
    @RequestMapping("/importDgdhyyActivityPage")
    public String importDgdhyyActivityPage(){
        return "res/dgdhyyActivity/importDgdhyyActivityPage";
    }

    @RequestMapping(value="/importDgdhyyActivity")
    @ResponseBody
    public String importDgdhyyActivity(MultipartFile file){
        if(file.getSize() > 0){
            try{
                int result = dgdhyyActivityBiz.importDgdhyyActivity(file);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    //带教、科主任、科秘查看---------------------------------------------
    @RequestMapping("/dgdhyyActivityList4teacher")
    public String dgdhyyActivityList4teacher(DgdhyyActivity dgdhyyActivity, Model model, Integer currentPage, HttpServletRequest request){
        SysUser currentUser = GlobalContext.getCurrentUser();
        dgdhyyActivity.setUserFlow(currentUser.getUserFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<DgdhyyActivity> dgdhyyActivityList = dgdhyyActivityBiz.searchDgdhyyActivity(dgdhyyActivity);
        model.addAttribute("dgdhyyActivityList",dgdhyyActivityList);
        return "res/dgdhyyActivity/dgdhyyActivityList4teacher";
    }

}
