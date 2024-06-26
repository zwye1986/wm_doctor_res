package com.pinde.sci.ctrl.eval;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.eval.IEvalCfgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.eval.ActionTypeEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.ExpertEvalCfg;
import com.pinde.sci.model.mo.SysDict;
import com.pinde.sci.model.mo.SysUser;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/eval/evalCfg")
public class EvalCfgController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IEvalCfgBiz evalCfgBiz;

    @RequestMapping(value = "/main")
    public String main(Model model) {
        return "eval/evalCfg/main";
    }

    @RequestMapping(value = "/list")
    public String list(Integer currentPage, HttpServletRequest request, Model model,  String evalYear,  String cfgName,  String isPublish) {
        //组织用户查询map
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("evalYear",evalYear);
        paramMap.put("cfgName",cfgName);
        paramMap.put("isPublish",isPublish);
        List<ExpertEvalCfg>  allList= new ArrayList<>();
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ExpertEvalCfg>  cfgList= evalCfgBiz.searchParentList(paramMap);
        Map<String,List<ExpertEvalCfg>> map=new HashMap<>();
        if(cfgList!=null&&cfgList.size()>0) {
            for (ExpertEvalCfg cfg : cfgList) {
                map.put(cfg.getCfgFlow(),evalCfgBiz.searchChildrenList(cfg.getCfgFlow()));
                allList.add(cfg);
                getChildrenList(cfg, allList);
            }
        }
        model.addAttribute("cfgMap",map);
        model.addAttribute("allList",allList);
        model.addAttribute("cfgList",cfgList);
        return "eval/evalCfg/list";
    }
    @RequestMapping(value = "/edit")
    public String edit(String cfgFlow, String parentCfgFlow, HttpServletRequest request, Model model) {
        ExpertEvalCfg cfg =evalCfgBiz.readByFlow(cfgFlow);
        model.addAttribute("cfg",cfg);
        ExpertEvalCfg pCfg =evalCfgBiz.readByFlow(parentCfgFlow);
        model.addAttribute("pCfg",pCfg);
        if(StringUtil.isBlank(parentCfgFlow))//新增总评估配置
        {

            return "eval/evalCfg/editAll";
        }else{//新增子评估配置

            Integer maxOrdinal=evalCfgBiz.getMaxOrdinal(parentCfgFlow);
            int count=1;
            if(maxOrdinal!=null)
            {
                count=maxOrdinal+1;
            }
            model.addAttribute("ordinal",count);
            return "eval/evalCfg/editChildren";
        }
    }
    @RequestMapping(value = "/showCfg")
    public String showCfg(String cfgFlow, String parentCfgFlow, HttpServletRequest request, Model model) {
        ExpertEvalCfg cfg =evalCfgBiz.readByFlow(cfgFlow);
        model.addAttribute("cfg",cfg);
        ExpertEvalCfg pCfg =evalCfgBiz.readByFlow(parentCfgFlow);
        model.addAttribute("pCfg",pCfg);
        if(StringUtil.isBlank(parentCfgFlow))//新增总评估配置
        {
            return "eval/evalCfg/showAll";
        }else{//新增子评估配置
            return "eval/evalCfg/showChildren";
        }
    }
    @RequestMapping(value = "/addSpe")
    public String addSpe(String cfgFlow,HttpServletRequest request, Model model) {
        ExpertEvalCfg cfg =evalCfgBiz.readByFlow(cfgFlow);
        model.addAttribute("cfg",cfg);
        List<SysDict> dicts=evalCfgBiz.getSpeListNotSelf(cfgFlow);
        model.addAttribute("dicts",dicts);
        return "eval/evalCfg/addSpe";
    }
    @RequestMapping(value = "/saveAddSpe")
    @ResponseBody
    public String saveAddSpe(ExpertEvalCfg cfg,HttpServletRequest request, Model model) {
        if(StringUtil.isBlank(cfg.getCfgFlow()))
        {
            return "请选择需要关联的配置";
        }
        if(StringUtil.isBlank(cfg.getSpeId()))
        {
            return "请选择专业基地";
        }else {
            cfg.setSpeName(DictTypeEnum.getDictName(DictTypeEnum.DoctorTrainingSpe,cfg.getSpeId()));
        }
//        ExpertEvalCfg cfg2 =evalCfgBiz.checkSpeCfgNotSelf(cfg.getCfgFlow(),cfg.getSpeId());
//        if(cfg2!=null)
//        {
//            return "此专业基地已关联基地配置项！";
//        }
        int result=evalCfgBiz.save(cfg);
        if(result==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    private void getChildrenList(ExpertEvalCfg cfg, List<ExpertEvalCfg> cfgList) {
        if(cfgList!=null&&cfg!=null)
        {
            List<ExpertEvalCfg> childrens=evalCfgBiz.searchChildrenList(cfg.getCfgFlow());
            if(childrens!=null&&childrens.size()>0)
            {
                for (ExpertEvalCfg c : childrens) {
                    cfgList.add(c);
                    getChildrenList(c, cfgList);
                }
            }
        }
    }

    @RequestMapping(value={"/save"},method=RequestMethod.POST)
    public @ResponseBody String save(ExpertEvalCfg cfg,String roleFlow){
        //新增
        if(StringUtil.isBlank(cfg.getCfgFlow())){
            //判断用户id是否重复
            ExpertEvalCfg old = evalCfgBiz.findByEvalYear(cfg.getEvalYear());
            if(old!=null){
                return cfg.getEvalYear()+"年评估指标已存在，无法保存！";
            }
        }else{
            String cfgFlow = cfg.getCfgFlow();
            //判断用户id是否重复
            ExpertEvalCfg old = evalCfgBiz.findByEvalYearNotSelf(cfgFlow,cfg.getEvalYear());
            if(old!=null){
                return cfg.getEvalYear()+"年评估指标已存在，无法保存！";
            }
        }
        if(cfg.getLevelId()==null)
        {
            cfg.setLevelId(0);
        }
        if(StringUtil.isNotBlank(cfg.getActionTypeId()))
        {
            cfg.setActionTypeName(ActionTypeEnum.getNameById(cfg.getActionTypeId()));
        }
      //  ss
        cfg.setParentCfgFlow("");
        cfg.setOrdinal(1);
        int result=evalCfgBiz.save(cfg);
        if(result==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value={"/saveItem"},method=RequestMethod.POST)
    public @ResponseBody String saveItem(ExpertEvalCfg cfg){
        ExpertEvalCfg pcfg=evalCfgBiz.readByFlow(cfg.getParentCfgFlow());
        if(pcfg==null)
        {
            return "请选择父级指标！";
        }
        //新增
        if(StringUtil.isBlank(cfg.getCfgFlow())){
            //判断用户id是否重复
            ExpertEvalCfg old = evalCfgBiz.findByCfgNameByParent(cfg.getCfgName(),pcfg.getEvalYear(),cfg.getParentCfgFlow());
            if(old!=null){
                return pcfg.getEvalYear()+"年评估名称已存在，无法保存！";
            }
        }else{
            String cfgFlow = cfg.getCfgFlow();
            //判断用户id是否重复
            ExpertEvalCfg old = evalCfgBiz.findByCfgNameNotSelfByParent(cfgFlow,cfg.getCfgName(),pcfg.getEvalYear(),cfg.getParentCfgFlow());
            if(old!=null){
                return pcfg.getEvalYear()+"年评估名称已存在，无法保存！";
            }
        }
        if(StringUtil.isNotBlank(cfg.getActionTypeId()))
        {
            cfg.setActionTypeName(ActionTypeEnum.getNameById(cfg.getActionTypeId()));
        }
        int result=evalCfgBiz.save(cfg);
        if(result==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        int ordrinal=cfg.getOrdinal();

        List<ExpertEvalCfg> childrens=evalCfgBiz.searchChildrenList(cfg.getParentCfgFlow());
        if(childrens!=null&&childrens.size()>0) {
            for (ExpertEvalCfg c : childrens) {
                if(!c.getCfgFlow().equals(cfg.getCfgFlow())&&cfg.getOrdinal()<=c.getOrdinal())
                {
                    c.setOrdinal(++ordrinal);
                    evalCfgBiz.save(c);
                }
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value={"/publishCfg"},method=RequestMethod.POST)
    public @ResponseBody String publishCfg(ExpertEvalCfg cfg){
       int result=evalCfgBiz.save(cfg);
        if(result==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        publishChildrenCfg(cfg.getCfgFlow());
        return GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value={"/delCfg"},method=RequestMethod.POST)
    public @ResponseBody String delCfg(ExpertEvalCfg cfg){
       int result=evalCfgBiz.save(cfg);
        if(result==0)
        {
            return GlobalConstant.SAVE_FAIL;
        }
        delChildrenCfg(cfg.getCfgFlow());
        return GlobalConstant.SAVE_SUCCESSED;
    }

    private void delChildrenCfg(String cfgFlow) {
        List<ExpertEvalCfg> childrens=evalCfgBiz.searchChildrenList(cfgFlow);
        evalCfgBiz.delChildrenCfg(cfgFlow);
        if(childrens!=null&&childrens.size()>0)
        {
            for (ExpertEvalCfg c : childrens) {
                delChildrenCfg(c.getCfgFlow());
            }
        }
    }
    private void publishChildrenCfg(String cfgFlow) {
        List<ExpertEvalCfg> childrens=evalCfgBiz.searchChildrenList(cfgFlow);
        evalCfgBiz.publishChildrenCfg(cfgFlow);
        if(childrens!=null&&childrens.size()>0)
        {
            for (ExpertEvalCfg c : childrens) {
                publishChildrenCfg(c.getCfgFlow());
            }
        }
    }

    @RequestMapping(value={"/fileDirs"},method=RequestMethod.POST)
    public String fileDirs(String year,Model model){
        List<Map<String,Object>> list=new ArrayList<>();
        if(StringUtil.isNotBlank(year)) {
            String baseDirs = InitConfig.getSysCfg("upload_base_dir");
            String filePath=baseDirs + File.separator + "evalFiles"+ File.separator +year;
            File file=new File(filePath);
            getFileName2(file,baseDirs+ File.separator +"evalFiles",list);

            Collections.sort(list, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> f1, Map<String, Object> f2) {
                    String order1 = (String) f1.get("id");
                    String order2 = (String) f2.get("id");
                    if (order1 == null) {
                        return -1;
                    } else if (order2 == null) {
                        return 1;
                    } else if (order1 != null && order2 != null) {
                        return order1.compareTo(order2);
                    }
                    return 0;
                }
            });
        }
        model.addAttribute("list",list);
        return "eval/evalCfg/fileDirs";
    }

    public static void getFileName2(File file,String basePath,List<Map<String,Object>> list)
    {
        if(file!=null&&file.exists())
        {
            String absolutePath=file.getAbsolutePath();
            String parent=file.getParent();
            Map<String,Object> map=new HashMap<>();
            list.add(map);
            if(file.isDirectory())
            {
                File[] files = file.listFiles();
                if(files!=null)
                {
                    for(File f:files)
                    {
                        getFileName2(f,basePath,list);
                    }
                }
                map.put("isFile","N");
            }else {
                if(file.getName().indexOf(".jsp")>0)
                {
                    map.put("isFile","U");
                }else {
                    map.put("isFile", "Y");
                }
            }
            map.put("speOrgFilePath",absolutePath.substring(basePath.length()));
            map.put("parentPath", parent.substring(basePath.length()));
            String name=file.getName();
            if(!"N".equals(map.get("isFile")))
            {
                if(name.lastIndexOf(".")>=0)
                {
                    name=name.substring(0,name.lastIndexOf("."));
                }
            }
            map.put("name",name);
            map.put("id",absolutePath.substring(basePath.length()).replaceAll("\\\\",""));
            map.put("pId",parent.substring(basePath.length()).replaceAll("\\\\",""));
        }
    }
}