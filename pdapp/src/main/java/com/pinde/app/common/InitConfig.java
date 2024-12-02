package com.pinde.app.common;

import com.pinde.core.common.BaseEnum;
import com.pinde.core.common.enums.DictTypeEnum;
import com.pinde.core.common.GeneralEnum;
import com.pinde.core.model.SysCfg;
import com.pinde.core.model.SysDict;
import com.pinde.core.util.ClassUtil;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IDictBiz;
import com.pinde.res.biz.stdp.ICfgBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by jiayf on 2016/4/1.
 */
public class InitConfig implements ServletContextListener {
    private final static Logger logger = LoggerFactory.getLogger(InitConfig.class);

    private static ServletContextEvent servletContext;

    private static Map<String,Map<String,String>> sysDictNameMap;
    private static Map<String, String> sysCfgMap = new HashMap<String, String>();
    private static Map<String, String> sysCfgDescMap;

    public static Map<String,String> weekPasswordMap;

    //住院医师
    public static IrbFormRequestUtil resFormRequestUtil;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent;
        toLoad(servletContextEvent);
    }

    private static void toLoad(ServletContextEvent servletContextEvent){
        //加载表单配置
        //logger.debug("开始加载表单配置...");
        //GlobalUtil.setCfg();
        GlobalUtil.loadLocalCfg();
        //加载填写数据表单配置
        GlobalUtil._loadResCfg();
        //logger.debug("表单配置加载完成...");
        if("oracle".equals(GlobalUtil.getLocalCfgMap().get("dbType")) ){
            //加载字典及枚举
            _loadDict(servletContextEvent.getServletContext());
            try {
                _loadEnum(servletContextEvent.getServletContext());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            _loadSysCfg(servletContextEvent.getServletContext());
        }
        //读取弱密码
        __weekPassword(servletContextEvent.getServletContext());
    }

    public static void reload(){
        toLoad(servletContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}

    private static void __weekPassword(ServletContext context) {
        logger.debug("开始读取弱密码...");
        try {
            Map<String,String> weekPasswords = new HashMap<String,String>();
            BufferedReader br = new BufferedReader(new FileReader(SpringUtil.getResource("classpath:weak_password/weekPassword1.txt").getFile()));
            for (String s = null; (s = br.readLine()) != null;) {
                weekPasswords.put(s,s);
            }
            br.close();

            BufferedReader br2 = new BufferedReader(new FileReader(SpringUtil.getResource("classpath:weak_password/weekPassword2.txt").getFile()));
            for (String s = null; (s = br2.readLine()) != null;) {
                weekPasswords.put(s,s);
            }
            br2.close();

            BufferedReader br3 = new BufferedReader(new FileReader(SpringUtil.getResource("classpath:weak_password/weekPassword3.txt").getFile()));
            for (String s = null; (s = br3.readLine()) != null;) {
                weekPasswords.put(s,s);
            }
            br3.close();
            weekPasswordMap = weekPasswords;

        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("读取弱密码结束...");
    }

    private static void _loadDict(ServletContext context) {
        try {
            Map<String, List<SysDict>> sysListDictMap = new HashMap<String, List<SysDict>>();
            Map<String, String> sysDictIdMap = new HashMap<String, String>();
            Map<String, Map<String, String>> sysDictNameMap = new HashMap<String, Map<String, String>>();
            List<DictTypeEnum> dictTypeEnumList = (List<DictTypeEnum>) EnumUtil.toList(com.pinde.core.common.enums.DictTypeEnum.class);
            for (DictTypeEnum dictTypeEnum : dictTypeEnumList) {
                String dictTypeId = dictTypeEnum.getId();
                Map<String, String> dictNameMap = new HashMap<String, String>();
                sysDictNameMap.put(dictTypeId, dictNameMap);
                IDictBiz dictBiz = SpringUtil.getBean(IDictBiz.class);
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeId(dictTypeId);
                sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                List<SysDict> sysDictList = dictBiz.searchDictList(sysDict);
                for (SysDict dict : sysDictList) {
                    String typeId = dict.getDictTypeId() + "." + dict.getDictId();
                    sysDictIdMap.put(typeId, dict.getDictName());
                    dictNameMap.put(dict.getDictName(), dict.getDictId());
                    if (dictTypeEnum.getLevel() > 1) {
                        sysDict.setDictTypeId(typeId);
                        List<SysDict> sysDictSecondList = dictBiz.searchDictList(sysDict);
                        if (sysDictSecondList != null && sysDictSecondList.size() > 0) {
                            for (SysDict sDict : sysDictSecondList) {
                                String tTypeId = typeId + "." + sDict.getDictId();
                                String tTypeName = dict.getDictName() + "." + sDict.getDictName();
                                sysDictIdMap.put(tTypeId, tTypeName);
                                if (dictTypeEnum.getLevel() > 2) {
                                    sysDict.setDictTypeId(tTypeId);
                                    List<SysDict> sysDictThirdList = dictBiz.searchDictList(sysDict);
                                    if (sysDictThirdList != null && sysDictThirdList.size() > 0) {
                                        for (SysDict tDict : sysDictThirdList) {
                                            sysDictIdMap.put(tTypeId + "." + tDict.getDictId(), tTypeName + "." + tDict.getDictName());
                                        }
                                    }
                                    sysListDictMap.put(tTypeId, sysDictThirdList);
                                    context.setAttribute("dictTypeEnum" + tTypeId + "List", sysDictThirdList);
                                }
                            }
                        }
                        sysListDictMap.put(typeId, sysDictSecondList);
                        context.setAttribute("dictTypeEnum" + typeId + "List", sysDictSecondList);
                    }
                }

                sysListDictMap.put(dictTypeId, sysDictList);
                context.setAttribute("dictTypeEnum" + dictTypeEnum.name() + "List", sysDictList);
            }
            context.setAttribute("sysDictIdMap", sysDictIdMap);
            InitConfig.sysDictNameMap = sysDictNameMap;
            com.pinde.core.common.enums.DictTypeEnum.sysDictIdMap = sysDictIdMap;
            com.pinde.core.common.enums.DictTypeEnum.sysListDictMap = sysListDictMap;
        }catch (Exception e) {
            logger.error("", e);
        }
    }

    private static void _loadEnum(ServletContext context) {
        Set<Class<?>> set = ClassUtil.getClasses("com.pinde.core.common.enums");
        for (Class<?> cls : set) {
            if (BaseEnum.class.isAssignableFrom(cls)) {
                Class[] innerEnums = cls.getDeclaredClasses();
                if (innerEnums != null) {
                    for (int i = 0; i < innerEnums.length; i++) {
                        if (Enum.class.isAssignableFrom(innerEnums[i])) {
                            String mixName = innerEnums[i].getSimpleName();
                            context.setAttribute(StringUtil.uncapitalize(mixName.substring(mixName.lastIndexOf("$") + 1)) + "List", EnumUtil.toList((Class<? extends GeneralEnum>) innerEnums[i]));
                        }
                    }
                }
            } else if (GeneralEnum.class.isAssignableFrom(cls)) {
                List<GeneralEnum> enumList = (List<GeneralEnum>) EnumUtil.toList((Class<? extends GeneralEnum>) cls);
                context.setAttribute(StringUtil.uncapitalize(cls.getSimpleName()) + "List", enumList);
                for (GeneralEnum genum : enumList) {
                    context.setAttribute(StringUtil.uncapitalize(cls.getSimpleName()) + genum.name(), genum);
                }
            }
        }
    }

    private static void _loadSysCfg(ServletContext context) {
        sysCfgMap = new HashMap<String, String>();
        sysCfgDescMap = new HashMap<String, String>();
        ICfgBiz cfgBiz = SpringUtil.getBean(ICfgBiz.class);
        List<SysCfg> sysCfgList = cfgBiz.search(new SysCfg());
        for(SysCfg sysCfg : sysCfgList){
            if(StringUtil.isNotBlank(sysCfg.getCfgDesc())){
                sysCfgDescMap.put(sysCfg.getCfgCode(), sysCfg.getCfgDesc());
            }
            sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
            if(StringUtil.isNotBlank(sysCfg.getCfgBigValue())){
                sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgBigValue());
            }
        }

        Properties prop=null;
        try {
            InputStream inputStream = InitConfig.class.getClassLoader().getResourceAsStream("appCfg.properties");
            prop=new Properties();
            prop.load(inputStream);
        } catch (IOException e) {
            logger.error(e.getMessage());
            prop=null;
        }
        if(prop!=null)
        {
            for (Object key:prop.keySet())
            {
                sysCfgMap.put((String)key,(String)prop.get(key));
            }
        }
        context.setAttribute("sysCfgMap",sysCfgMap);
        context.setAttribute("sysCfgDescMap", sysCfgDescMap);
    }
    public static String getSysCfg(String key){
        return StringUtil.defaultString(sysCfgMap.get(key));
    }

    public static Map<String, String> getDictNameMap(String dictTypeId) {
        if (sysDictNameMap != null) {
            return sysDictNameMap.get(dictTypeId);
        }
        return null;
    }
}
