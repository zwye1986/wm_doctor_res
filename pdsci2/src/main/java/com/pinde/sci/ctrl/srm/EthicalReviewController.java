package com.pinde.sci.ctrl.srm;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.IEthicalReviewBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.PubProj;
import com.pinde.sci.model.mo.SrmIrbApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/srm/ethical")
public class EthicalReviewController extends GeneralController{

    @Autowired
    private IEthicalReviewBiz ethicalReviewBiz;
    @Autowired
    private IFileBiz fileBiz;

    /**
     * 项目审查查询
     * @param projListScope
     * @param projCateScope
     * @param proj
     * @param flag
     * @param currentPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/list/{projListScope}/{projCateScope}")
    public String  list(@PathVariable String projListScope,
                        @PathVariable String projCateScope, PubProj proj,
                        String flag, Integer currentPage , HttpServletRequest request, Model model){
        setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
        setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);

        List<PubProj> projList = new ArrayList<PubProj>();
        proj.setProjCategoryId(projCateScope);
        PageHelper.startPage(currentPage, getPageSize(request));
        projList = ethicalReviewBiz.getPubProjInfo(proj);
        model.addAttribute("projList", projList);

      return "srm/ethical/list";
    }

    /**
     * 添加
     * @return
     */
    @RequestMapping(value = "/addInfo")
    public String addInfo(String projFlow,String flag,Model model){
        List<SrmIrbApply> srmIrbApplyList = ethicalReviewBiz.searchSrmIrbApply(projFlow);
        if(srmIrbApplyList.size()>0 && srmIrbApplyList!=null){
            Map<String,SrmIrbApply>  irbMap  = new HashMap<>();
            Map<String,PubFile>  fileMap  = new HashMap<>();
            for(SrmIrbApply srmIrbApply:srmIrbApplyList){
                irbMap.put(srmIrbApply.getIrbTypeId(),srmIrbApply);

                List<PubFile> files = fileBiz.searchByProductFlow(srmIrbApply.getIrbFlow());
                if(!files.isEmpty() && files.size()>0){
                    fileMap.put(srmIrbApply.getIrbTypeId(),files.get(0));
                }
            }
            model.addAttribute("fileMap",fileMap);
            model.addAttribute("irbMap",irbMap);
        }
        model.addAttribute("projFlow",projFlow);
        model.addAttribute("flag",flag);
        return "srm/ethical/edit";
    }

    /**
     * 保存
     * @return
     */
    @RequestMapping(value = "/save",method={RequestMethod.POST})
    @ResponseBody
    public String save(String jsondata,String projFlow,String[] fileFlow,HttpServletRequest request){

        List<SrmIrbApply> srmIrbApplyList  = JSON.parseArray(jsondata,SrmIrbApply.class);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fileList = multipartRequest.getFiles("file");
        int count=0;
        for (int i=0;i<srmIrbApplyList.size();i++) {
            srmIrbApplyList.get(i).setProjFlow(projFlow);
            if(fileFlow!=null){
                count =ethicalReviewBiz.updateFile(srmIrbApplyList.get(i),fileList.get(i),fileFlow[i]);
            }else{
                count =ethicalReviewBiz.addFile(srmIrbApplyList.get(i),fileList.get(i));
            }

        }
        if(count>GlobalConstant.ZERO_LINE){
            return  GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

}
