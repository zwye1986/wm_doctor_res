package com.pinde.sci.ctrl.lcjn;


import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnSuppliesAndAssetsBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.LcjnFixedAssets;
import com.pinde.sci.model.mo.LcjnSupplies;
import com.pinde.sci.model.mo.LcjnSuppliesBatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lcjn/lcjnSuppliesAssets")
public class LcjnSuppliesAssetsController extends GeneralController {
    @Autowired
    private ILcjnSuppliesAndAssetsBiz suppliesAndAssetsBiz;
    @Autowired
    private IDictBiz dictBiz;
    /**
     * 固定资产列表
     * @param dictName
     * @param statusId
     * @param currentPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/fixedAssetsList", method = {RequestMethod.GET, RequestMethod.POST})
    public String fixedAssetsList(String dictName, String statusId, Integer currentPage, HttpServletRequest request, Model model){
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> fixedAssetsList=suppliesAndAssetsBiz.selectFixedAssetsList(dictName,statusId);
        if(fixedAssetsList!=null&&fixedAssetsList.size()>0){
            for(int i=0;i<fixedAssetsList.size();i++){
                SysDict sysDict=dictBiz.readDict(DictTypeEnum.SkileFixedAssets.getId(),(String)fixedAssetsList.get(i).get("DICT_ID"),GlobalContext.getCurrentUser().getOrgFlow());
                if(sysDict!=null){
                    fixedAssetsList.get(i).put("record",sysDict.getRecordStatus());
                }
            }
        }
        model.addAttribute("fixedAssetsList",fixedAssetsList);
        model.addAttribute("currentPage",currentPage);
        return "/lcjn/suppliesAndAssets/fixedAssets";
    }

    /**
     * 修改资产信息
     * @param fixedFlow
     * @param dictId
     * @param fixedPrice
     * @param fixedCode
     * @param statusId
     * @return
     */
    @RequestMapping(value = "/editAssets")
    @ResponseBody
    public String editAssets(String fixedFlow,String dictId,String fixedPrice,String fixedCode,String statusId){
        if (StringUtil.isBlank(GlobalContext.getCurrentUser().getOrgFlow())) {
            return "保存失败！当前登录账号未关联机构!";
        }
        LcjnFixedAssets fixedAssets=new LcjnFixedAssets();
        fixedAssets.setFixedFlow(fixedFlow);
        fixedAssets.setFixedCode(fixedCode);
        fixedAssets.setFixedPrice(fixedPrice);
        fixedAssets.setDictId(dictId);
        fixedAssets.setStatusId(statusId);
        fixedAssets.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        fixedAssets.setOrgName(GlobalContext.getCurrentUser().getOrgName());
        List<SysDict> distList= DictTypeEnum.SkileFixedAssets.getSysDictList();
        if(distList!=null&&distList.size()>0){
            for(int k=0;k<distList.size();k++){
                if(distList.get(k)!=null&&GlobalContext.getCurrentUser().getOrgFlow()!=null
                        &&dictId.equals(distList.get(k).getDictId())&&GlobalContext.getCurrentUser().getOrgFlow()
                        .equals(distList.get(k).getOrgFlow())){
                    fixedAssets.setDictName(distList.get(k).getDictName());
                }
            }
        }
        int num=0;
        num=suppliesAndAssetsBiz.editFixedAsset(fixedAssets);
        if (num > 0) {
            return "保存成功！";
        }
        return "保存失败！";
    }

    @RequestMapping(value = "/showEditAssets")
    public String showEditAssets(String fixedFlow,String currentPage,Model model){
        if(StringUtil.isNotBlank(fixedFlow)){
            LcjnFixedAssets fixedAssets=suppliesAndAssetsBiz.selectByFixedFlow(fixedFlow);
            model.addAttribute("asset",fixedAssets);
            model.addAttribute("currentPage",currentPage);
        }
        model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        return "/lcjn/suppliesAndAssets/editAssets";
    }

    @RequestMapping(value = "/deleteAssets")
    @ResponseBody
    public String deleteAssets(String fixedFlow){
        int num=0;
        num=suppliesAndAssetsBiz.deleteByFixedFlow(fixedFlow);
        if (num > 0) {
            return "删除成功！";
        }
        return "删除失败！";
    }

    /**
     * 展示导入资产页面
     * @return
     */
    @RequestMapping(value="/showImportAssets")
    public String showImportAssets(){return "/lcjn/suppliesAndAssets/importAssets";}

    /**
     * 导入资产
     * @param file
     * @return
     */
    @RequestMapping(value="importAssets")
    public @ResponseBody String importAssets(MultipartFile file){
        if(file.getSize() > 0){
            try{
                suppliesAndAssetsBiz.importAssetsFromExcel(file);
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_SUCCESSED;
    }

    @RequestMapping(value="importSupplies")
    public @ResponseBody String importSupplies(MultipartFile file){
        if(file.getSize() > 0){
            try{
                suppliesAndAssetsBiz.importSuppliesFromExcel(file);
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_SUCCESSED;
    }

    /**
     * 耗材列表
     * @param dictName
     * @param currentPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/suppliesList", method = {RequestMethod.GET, RequestMethod.POST})
    public String suppliesList(String dictName, Integer currentPage, HttpServletRequest request, Model model){
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> suppliesList=suppliesAndAssetsBiz.selectSuppliesList(dictName,null);
        if(suppliesList!=null&&suppliesList.size()>0){
            for (int i=0;i<suppliesList.size();i++){
                List<LcjnSuppliesBatch> list=suppliesAndAssetsBiz.selectSuppliesBatch((String)suppliesList.get(i).get("SUPPLIES_FLOW"));
                suppliesList.get(i).put("batchList",list);
                SysDict sysDict=dictBiz.readDict(DictTypeEnum.SkillMaterial.getId(),(String)suppliesList.get(i).get("DICT_ID"),GlobalContext.getCurrentUser().getOrgFlow());
                if(sysDict!=null){
                    suppliesList.get(i).put("record",sysDict.getRecordStatus());
                }
            }
        }
        model.addAttribute("suppliesList",suppliesList);
        model.addAttribute("currentPage",currentPage);
        return "/lcjn/suppliesAndAssets/suppliesManager";
    }

    @RequestMapping(value="/selectByDictId")
    @ResponseBody
    public String selectByDictId(String dictId){
        String suppliesPrice="";
        List<LcjnSupplies> suppliesList=suppliesAndAssetsBiz.selectByDictId(dictId);
        if(suppliesList!=null&&suppliesList.size()>0){
            suppliesPrice=suppliesList.get(0).getSuppliesPrice();
        }
        return suppliesPrice;
    }

    /**
     * 添加耗材
     * @param suppliesFlow
     * @param dictId
     * @param suppliesPrice
     * @param stockNum
     * @param addTime
     * @return
     */
    @RequestMapping(value="/editSupplies")
    @ResponseBody
    public String editSupplies(String suppliesFlow,String dictId,String suppliesPrice,String stockNum,String addTime){
        int num=0;
        if (StringUtil.isNotBlank(suppliesFlow)){
            LcjnSuppliesBatch suppliesBatch=new LcjnSuppliesBatch();
            suppliesBatch.setSuppliesFlow(suppliesFlow);
            suppliesBatch.setStockNum(stockNum);
            suppliesBatch.setAddTime(addTime);
            num=suppliesAndAssetsBiz.addSupliesBatch(suppliesBatch);
        }else{
            List<LcjnSupplies> suppliesList=suppliesAndAssetsBiz.selectByDictId(dictId);
            if(suppliesList!=null&&suppliesList.size()>0){
                LcjnSuppliesBatch suppliesBatch=new LcjnSuppliesBatch();
                suppliesBatch.setSuppliesFlow(suppliesList.get(0).getSuppliesFlow());
                suppliesBatch.setStockNum(stockNum);
                suppliesBatch.setAddTime(addTime);
                num=suppliesAndAssetsBiz.addSupliesBatch(suppliesBatch);
            }else {
                LcjnSupplies supplies1=new LcjnSupplies();
                supplies1.setSuppliesFlow(PkUtil.getUUID());
                supplies1.setDictId(dictId);
                supplies1.setSuppliesPrice(suppliesPrice);
                supplies1.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                supplies1.setOrgName(GlobalContext.getCurrentUser().getOrgName());
                num=suppliesAndAssetsBiz.addSupplies(supplies1);
                LcjnSuppliesBatch suppliesBatch1=new LcjnSuppliesBatch();
                suppliesBatch1.setSuppliesFlow(supplies1.getSuppliesFlow());
                suppliesBatch1.setAddTime(addTime);
                suppliesBatch1.setStockNum(stockNum);
                num=suppliesAndAssetsBiz.addSupliesBatch(suppliesBatch1);
            }
        }
        if (num > 0) {
            return "保存成功！";
        }
        return "保存失败！";
    }
    @RequestMapping(value = "/showEditSupplies")
    public String showEditSupplies(String suppliesFlow,String currentPage,String exmp,Model model){
        if(StringUtil.isNotBlank(suppliesFlow)){
            List<Map<String,Object>> suppliesList=suppliesAndAssetsBiz.selectSuppliesList(null,suppliesFlow);
            if(suppliesList!=null&&suppliesList.size()>0){
                model.addAttribute("supplies",suppliesList.get(0));
            }
        }
        model.addAttribute("exmp",exmp);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        return "/lcjn/suppliesAndAssets/editSupplies";
    }
    @RequestMapping(value = "/deleteSupplies")
    @ResponseBody
    public String deleteSupplies(String suppliesFlow){
        int num=0;
        num=suppliesAndAssetsBiz.deleteSuppliesInfo(suppliesFlow);
        if (num > 0) {
            return "删除成功！";
        }
        return "删除失败！";
    }
}
