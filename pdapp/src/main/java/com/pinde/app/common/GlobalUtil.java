package com.pinde.app.common;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.IrbSingleForm;
import com.pinde.core.common.enums.GeneralEnum;
import com.pinde.core.common.enums.ResRecTypeEnum;
import com.pinde.core.model.DeptTeacherGradeInfo;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.XmlParse;
import com.pinde.res.biz.stdp.ICfgBiz;
import com.pinde.res.biz.stdp.IResGradeBiz;
import com.pinde.res.dao.stdp.ext.CustomExtMapper;
import org.dom4j.Element;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by jiayf on 2016/3/30.
 */
public class GlobalUtil {
    /**
     * 根据方法名及flow获取对象
     * @param sqlId
     * @param premaryKey
     * @return
     */
    public static Object getObjByFlow(String sqlId,String premaryKey){
        Object o = null;
        if(StringUtil.isNotBlank(sqlId) && StringUtil.isNotBlank(premaryKey)){
            CustomExtMapper cem = SpringUtil.getBean(CustomExtMapper.class);
            try{
                Method m = CustomExtMapper.class.getMethod(sqlId,String.class);
                if(m!=null){
                    o = m.invoke(cem,premaryKey);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return o;
    }

    /**
     * 根据表单类型id获取表单类型name
     * @param id
     * @return
     */
    public static String getResRecTypeEnumNameById(String id) {
        String result = ResRecTypeEnum.getNameById(id);
        return result;
    }

    //为出科小结控制权限
    //如果已填写对老师和科室评价则可以填写出科小结
    public static Boolean isWriteAfterSummary(Boolean curr,String processFlow){
        if(curr){
            return curr;
        }

        curr = StringUtil.isBlank(processFlow);
        if(curr){
            return curr;
        }

        IResGradeBiz gradeBiz = SpringUtil.getBean(IResGradeBiz.class);
        DeptTeacherGradeInfo trr = gradeBiz.getRecByRecType(processFlow, ResRecTypeEnum.TeacherGrade.getId());
        curr = trr==null;
        if(curr){
            return curr;
        }

        DeptTeacherGradeInfo drr = gradeBiz.getRecByRecType(processFlow, ResRecTypeEnum.DeptGrade.getId());
        curr = drr==null;
        if(curr){
            return curr;
        }

        return false;
    }

    //配置id对应的value
    private static Map<String,String> idValCfg;
    public static Map<String,String> getIdValCfg(){
        return idValCfg;
    }
    //老师展示内容配置
    private static Map<String,Map<String,String>> teacherDataListContentCfg;
    public static Map<String,Map<String,String>> getTeacherDataListContentCfg(){
        return teacherDataListContentCfg;
    }
    //保存子项名称的节点需要同时保存子项id
    private static List<String> itemIdAttrCfg;
    public static List<String> getItemIdAttrCfg(){
        return itemIdAttrCfg;
    }
    //需要计算和的表单
    private static Map<String,List<String>> sumItemMap;
    public static Map<String,List<String>> getSumItemMap(){
        return sumItemMap;
    }
    public static void setCfg(){
        idValCfg = new HashMap<String, String>();
        //参加活动-活动形式
        idValCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId()+"activity_way1","教学查房");
        idValCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId()+"activity_way2","疑难病例讨论");
        idValCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId()+"activity_way3","危重病例讨论");
        idValCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId()+"activity_way4","学术讲座");
        idValCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId()+"activity_way5","死亡病例讨论");
        //参加活动-学时
        idValCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId()+"activity_period1","1");
        idValCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId()+"activity_period2","2");
        idValCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId()+"activity_period3","3");
        idValCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId()+"activity_period4","4");
        idValCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId()+"activity_period5","5");
        //参加活动-学时
        idValCfg.put(ResRecTypeEnum.CampaignRegistry.getId()+"activity_period1","1");
        idValCfg.put(ResRecTypeEnum.CampaignRegistry.getId()+"activity_period2","2");
        idValCfg.put(ResRecTypeEnum.CampaignRegistry.getId()+"activity_period3","3");
        idValCfg.put(ResRecTypeEnum.CampaignRegistry.getId()+"activity_period4","4");
        idValCfg.put(ResRecTypeEnum.CampaignRegistry.getId()+"activity_period5","5");
        //大病例-诊断类型
        idValCfg.put(ResRecTypeEnum.CaseRegistry.getId()+"mr_diagType1","主要诊断");
        idValCfg.put(ResRecTypeEnum.CaseRegistry.getId()+"mr_diagType2","次要诊断");
        idValCfg.put(ResRecTypeEnum.CaseRegistry.getId()+"mr_diagType3","并行诊断");
        //病种-诊断类型
        idValCfg.put(ResRecTypeEnum.DiseaseRegistry.getId()+"disease_diagType1","主要诊断");
        idValCfg.put(ResRecTypeEnum.DiseaseRegistry.getId()+"disease_diagType2","次要诊断");
        idValCfg.put(ResRecTypeEnum.DiseaseRegistry.getId()+"disease_diagType3","并行诊断");
        //病种-是否主管
        idValCfg.put(ResRecTypeEnum.DiseaseRegistry.getId()+"disease_isCharge0","否");
        idValCfg.put(ResRecTypeEnum.DiseaseRegistry.getId()+"disease_isCharge1","是");
        //病种-是否抢救
        idValCfg.put(ResRecTypeEnum.DiseaseRegistry.getId()+"disease_isRescue0","否");
        idValCfg.put(ResRecTypeEnum.DiseaseRegistry.getId()+"disease_isRescue1","是");
        //手术-术中职务
        idValCfg.put(ResRecTypeEnum.OperationRegistry.getId()+"operation_operRole0","术者");
        idValCfg.put(ResRecTypeEnum.OperationRegistry.getId()+"operation_operRole1","一助");
        idValCfg.put(ResRecTypeEnum.OperationRegistry.getId()+"operation_operRole2","二助");
        //操作技能-成功
        idValCfg.put(ResRecTypeEnum.SkillRegistry.getId()+"skill_result0","否");
        idValCfg.put(ResRecTypeEnum.SkillRegistry.getId()+"skill_result1","是");


        teacherDataListContentCfg = new HashMap<String, Map<String,String>>();
        //参加活动
        Map<String,String> vierCfgMap = new LinkedHashMap<String, String>();
        vierCfgMap.put("activity_way","活动形式");
        vierCfgMap.put("activity_speaker","主讲人");
        teacherDataListContentCfg.put(ResRecTypeEnum.CampaignNoItemRegistry.getId(),vierCfgMap);
        //大病例
        vierCfgMap = new LinkedHashMap<String, String>();
        vierCfgMap.put("mr_pName","病人姓名");
        vierCfgMap.put("disease_pName","疾病名称");
        teacherDataListContentCfg.put(ResRecTypeEnum.CaseRegistry.getId(),vierCfgMap);
        //病种
        vierCfgMap = new LinkedHashMap<String, String>();
        vierCfgMap.put("disease_pName","病人姓名");
        vierCfgMap.put("disease_diagName","病种名称");
        teacherDataListContentCfg.put(ResRecTypeEnum.DiseaseRegistry.getId(),vierCfgMap);
        //手术
        vierCfgMap = new LinkedHashMap<String, String>();
        vierCfgMap.put("operation_pName","病人姓名");
        vierCfgMap.put("operation_operName","手术名称");
        teacherDataListContentCfg.put(ResRecTypeEnum.OperationRegistry.getId(),vierCfgMap);
        //操作技能
        vierCfgMap = new LinkedHashMap<String, String>();
        vierCfgMap.put("skill_pName","病人姓名");
        vierCfgMap.put("skill_operName","操作名称");
        teacherDataListContentCfg.put(ResRecTypeEnum.SkillRegistry.getId(),vierCfgMap);

        itemIdAttrCfg = new ArrayList<String>();
        itemIdAttrCfg.add(ResRecTypeEnum.DiseaseRegistry.getId()+"disease_diagName");
        itemIdAttrCfg.add(ResRecTypeEnum.OperationRegistry.getId()+"operation_operName");
        itemIdAttrCfg.add(ResRecTypeEnum.SkillRegistry.getId()+"skill_operName");

        sumItemMap = new HashMap();
    }

    public static Map<String,String> localCfgMap;

    public static Map<String,String> getLocalCfgMap(){
        return localCfgMap;
    }
    public static void loadLocalCfg() {
        localCfgMap=new HashMap<>();
        Properties prop=null;
        try {
            InputStream inputStream = InitConfig.class.getClassLoader().getResourceAsStream("appCfg.properties");
            prop=new Properties();
            prop.load(inputStream);
        } catch (IOException e) {
            prop=null;
        }
        if(prop!=null)
        {
            for (Object key:prop.keySet())
            {
                localCfgMap.put((String)key,(String)prop.get(key));
            }
        }

    }

    public static boolean findChineseOrWestern(String rotationTypeId,String recTypeId) {
        if(StringUtil.isBlank(rotationTypeId)||StringUtil.isBlank(recTypeId)){
            return false;
        }
        String value= getSysCfg(recTypeId+"_medicine_type");
        if(StringUtil.isBlank(value))
        {
            return false;
        }
        if(!value.contains(rotationTypeId))
        {
            return false;
        }
        return  true;
    }

    public static String getSysCfg(String cfgCode) {

        ICfgBiz cfgBiz = SpringUtil.getBean(ICfgBiz.class);
        return cfgBiz.getCfgCode(cfgCode);
    }


    public static void _loadResCfg() {
        loadResFormDict();
        InitConfig.resFormRequestUtil = new IrbFormRequestUtil();

        try {
            ResRecTypeEnum resRecTypeEnum = null;
            String formFileName = null;
            XmlParse irbFormXp = null;
            List<Element> productTypeElements = null;
            String jspPath = null;
            List<IrbSingleForm> formList = null;

            String productType = null;
            List<Element> pageElements = null;

            IrbSingleForm singleForm = null;
            List<Element> itemElements = null;
            Map<String, IrbSingleForm> singleFormMap = null;

            //循环表单类型
            for (GeneralEnum<String> temp : EnumUtil.toList(ResRecTypeEnum.class)) {
                resRecTypeEnum = (ResRecTypeEnum) temp;
                if (GlobalConstant.FLAG_N.equals(resRecTypeEnum.getIsForm())) {
                    continue;
                }
                formFileName = resRecTypeEnum.getId(); //登记类型 ID  (如：CaseRegistry...)
                //根据登记类型ID  获了XML文件
                irbFormXp = new XmlParse(SpringUtil.getResource("classpath:" + GlobalConstant.RES_FORM_CONFIG_PATH + "/" + formFileName + ".xml").getFile());

                productTypeElements = irbFormXp.getRootElement().elements();//XML文件根节点下的子节点列表
                jspPath = "";
                formList = null;
                //循环子节点列表
                for (Element productEle : productTypeElements) {
                    productType = productEle.getName();//子节点名
                    //子节点名为jsp的是表单路径
                    if (productType.equals("jsp")) {
                        jspPath = productEle.attributeValue("path");
                        continue;
                    }
                    //当前登记类型表单列表
                    formList = InitConfig.resFormRequestUtil.getFormTypeMap().get(formFileName);
                    if (formList == null) {
                        formList = new ArrayList<IrbSingleForm>();
                    }
                    pageElements = productEle.elements();
                    //赋版本号(key=子节点名即项目名_登记类型，value=版本号)
                    InitConfig.resFormRequestUtil.getVersionMap().put(productType + "_" + formFileName, productEle.attributeValue("ver"));
                    //循环子节点下的page列表
                    for (Element pageEle : pageElements) {
                        singleForm = new IrbSingleForm();
                        singleForm.setProductType(productType);
                        singleForm.setVersion(pageEle.attributeValue("ver"));
                        singleForm.setJspPath(jspPath);
                        itemElements = pageEle.elements();
                        singleForm.setItemList(itemElements);
                        formList.add(singleForm);
                        singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(formFileName);
                        if (singleFormMap == null) {
                            singleFormMap = new HashMap<String, IrbSingleForm>();
                        }
                        //项目的表单列表 (key=子节点名即项目名_版本号, value=表单)
                        singleFormMap.put(singleForm.getProductType() + "_" + singleForm.getVersion(), singleForm);
                        //登记类型的表单Map列表 (key=登记类型, value=项目的表单列表Map)
                        InitConfig.resFormRequestUtil.getFormMap().put(formFileName, singleFormMap);
                    }
                    //登记类型的表单List列表 (key=登记类型, value=项目的表单列表List)
                    InitConfig.resFormRequestUtil.getFormTypeMap().put(formFileName, formList);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("RES CONFIG FILE LOAD FAIL，不能启动系统...");
        }
    }

    public static void loadResFormDict() {
        try {
            XmlParse formXp = new XmlParse(SpringUtil.getResource("classpath:" + GlobalConstant.RES_FORM_CONFIG_PATH + "/formDict.xml").getFile());
            List<Element> formDictElements = formXp.getRootElement().elements();
            List<Map<String, Object>> resFormDictList = new ArrayList<Map<String, Object>>();

            Map<String, Object> dictForm = null;
            String dictId = null;
            String dictDesc = null;
            for (Element dict : formDictElements) {
                dictForm = new HashMap<String, Object>();
                dictId = dict.getName();
                dictDesc = dict.attributeValue("desc");
                dictForm.put("dictId", dictId);
                dictForm.put("dictDesc", dictDesc);
                resFormDictList.add(dictForm);
                List<Element> depts = dict.elements();
                boolean notEmpty = depts!=null && !depts.isEmpty();
                dictForm.put("hasDeptForm",notEmpty);
                if(notEmpty){
                    List<Map<String,Object>> deptList = new ArrayList<Map<String, Object>>();
                    for(Element dept : depts){
                        Map<String,Object> deptMap = new HashMap<String,Object>();
                        String pageFlag = dept.attributeValue("pageFlag").trim();
                        deptMap.put("subPage",pageFlag);
                        String deptCfgName = dept.getText().trim();
                        deptMap.put("subPageName",deptCfgName);
                        deptList.add(deptMap);
                    }
                    dictForm.put("deptForm",deptList);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("RES FORM Directory LOAD FAIL，不能启动系统...");
        }
    }

    /**
     * 获取表单路径
     * @param rotationFlow 方案流水
     * @param recTypeId 登记类型
     * @param currVer 版本号
     * @param recForm 使用的表单
     * @param type
     * @return
     */
    public static String getFormPath(String rotationFlow, String recTypeId, String currVer, String recForm, String type, String medicineTypeId, String recordFlow){
        // 1 如果传入使用的表单为空，则根据 "res_form_category_方案流水号" 获取表单，如没有，则根据 "res_form_category" 获取，还没有，则获取全局配置GlobalConstant.RES_FORM_PRODUCT
        if(!StringUtil.isNotBlank(recForm)){
            recForm = getRecFormByCfg(rotationFlow,recordFlow,recTypeId);
        }
        return getFormPath(recTypeId,currVer,recForm,type,medicineTypeId);
    }

    /**
     * 获取该表单jsp地址
     * @param recTypeId 登记类型
     * @param currVer 版本号
     * @param recForm 使用的表单
     * @param type
     * @return
     */
    public static String getFormPath(String recTypeId, String currVer, String recForm, String type,String medicineTypeId){
        if(StringUtil.isBlank(medicineTypeId))
            medicineTypeId="";
        if(StringUtil.isNotBlank(recTypeId)){
            IrbSingleForm singleForm = findTheRecForm(recForm,recTypeId,currVer);
            if(singleForm!=null){
                String jspPath = singleForm.getJspPath();
                if(StringUtil.isNotBlank(jspPath)){
                    jspPath = MessageFormat.format(jspPath,singleForm.getProductType(),singleForm.getVersion(),type,medicineTypeId);
                }
                return jspPath;
            }
        }
        return "";
    }

    /**
     * 获取表单信息
     * @param recForm 使用的表单 (如:product、sczyfy...)
     * @param recTypeId 登记类型ID
     * @param ver 使用的版本号
     * @return
     */
    public static IrbSingleForm findTheRecForm(String recForm, String recTypeId, String ver){
        if(!StringUtil.isNotBlank(recTypeId)){
            return null;
        }

        if(!StringUtil.isNotBlank(recForm)){
            recForm = GlobalConstant.RES_FORM_PRODUCT;
        }

        IrbFormRequestUtil resFormRequestUtil = InitConfig.resFormRequestUtil;
        if(resFormRequestUtil!=null){
            if(!StringUtil.isNotBlank(ver)){
                Map<String,String> verMap = resFormRequestUtil.getVersionMap();
                if(verMap!=null){
                    ver = verMap.get(recForm+"_"+recTypeId);
                }
            }
            if(!StringUtil.isNotBlank(ver)){
                ver = GlobalConstant.RES_FORM_PRODUCT_VER;
            }

            Map<String,Map<String,IrbSingleForm>> singleFormMapMap = resFormRequestUtil.getFormMap();
            if(singleFormMapMap!=null){
                Map<String,IrbSingleForm> singleFormMap = singleFormMapMap.get(recTypeId);
                if(singleFormMap!=null){
                    IrbSingleForm singleForm = singleFormMap.get(recForm+"_"+ver);

                    if(singleForm == null){
                        singleForm = singleFormMap.get(GlobalConstant.RES_FORM_PRODUCT+"_"+GlobalConstant.RES_FORM_PRODUCT_VER);
                    }

                    if(singleForm == null){
                        throw new RuntimeException("未发现表单 模版类型:"+recForm+",表单类型:"+ResRecTypeEnum.getNameById(recTypeId)+",版本号:"+ver);
                    }else{
                        return singleForm;
                    }
                }
            }
        }
        return null;
    }
    /**
     * 根据方案查找对应的表单
     * @param rotationFlow
     * @param recordFlow
     *@param recTypeId @return
     */
    public static String getRecFormByCfg(String rotationFlow, String recordFlow, String recTypeId){
        String recForm = getSysCfg("res_form_category_"+rotationFlow+"_"+recordFlow+"_"+recTypeId);

        if(!StringUtil.isNotBlank(recForm)){
            recForm = getSysCfg("res_form_category_"+rotationFlow);
        }
        if(!StringUtil.isNotBlank(recForm)){
            recForm = getSysCfg("res_form_category");
        }

        if(!StringUtil.isNotBlank(recForm)){
            recForm = GlobalConstant.RES_FORM_PRODUCT;
        }

        return recForm;
    }

    /**
     * 获取表单配置文件需要显示的字段
     * @param rotationFlow
     * @param recTypeId
     * @param currVer
     * @param recForm
     * @param medicineTypeId
     * @param recordFlow
     * @return
     */
    public static List<Map<String,String>> getFormViews(String rotationFlow, String recTypeId, String currVer, String recForm, String medicineTypeId, String recordFlow){
        // 1 如果传入使用的表单为空，则根据 "res_form_category_方案流水号" 获取表单，如没有，则根据 "res_form_category" 获取，还没有，则获取全局配置GlobalConstant.RES_FORM_PRODUCT
        if(!StringUtil.isNotBlank(recForm)){
            recForm = getRecFormByCfg(rotationFlow,recordFlow,recTypeId);
        }
        return getFormViews(recTypeId,currVer,recForm,medicineTypeId);
    }

    public static List<Map<String,String>> getFormViews(String recTypeId, String currVer, String recForm, String medicineTypeId){

        List<Map<String,String>> viewList = null;
        if(StringUtil.isBlank(medicineTypeId))
            medicineTypeId="";
        if(StringUtil.isNotBlank(recTypeId)){
            IrbSingleForm singleForm = findTheRecForm(recForm,recTypeId,currVer);
            if(singleForm!=null){

                List<Element> eles = singleForm.getItemList();
                if(eles!=null && !eles.isEmpty()){

                    viewList = new ArrayList<Map<String,String>>();
                    for(Element e : eles){
                        String isView = e.attributeValue("isView");
                        if(StringUtil.isNotBlank(isView)) {
                            String eName = e.attributeValue("name");
                            Map<String, String> propInfo = new HashMap<String, String>();
                            propInfo.put("isView", isView);

                            String title = e.attributeValue("remark");
                            propInfo.put("title", title);

                            propInfo.put("itemName", eName);

                            viewList.add(propInfo);
                        }
                    }
                    if(viewList!=null && viewList.size()>0){
                        Collections.sort(viewList,new Comparator<Map<String,String>>() {
                            @Override
                            public int compare(Map<String, String> map0,Map<String, String> map1){
                                if(map0!=null && map1!=null){
                                    Integer a = 0;
                                    Integer b = 0;
                                    try {
                                        a = Integer.parseInt(map0.get("isView"));
                                        b = Integer.parseInt(map1.get("isView"));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    return a-b;
                                }
                                return 0;
                            }
                        });
                    }
                }
            }
        }
        return viewList;
    }

}
