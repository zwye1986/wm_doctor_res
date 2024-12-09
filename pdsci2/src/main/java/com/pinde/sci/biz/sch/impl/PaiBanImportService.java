package com.pinde.sci.biz.sch.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.dao.sys.SysDeptExtMapper;
import com.pinde.sci.model.jsres.LzDeptItem;
import com.pinde.sci.model.mo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/09/28/13:45
 * @Description:
 */
@Service
public class PaiBanImportService {

    @Resource
    private SysDeptExtMapper sysDeptExtMapper;
    @Autowired
    private SchRotationDeptMapper rotationDeptMapper;

    /**
     * ~~~~~~~~~溺水的鱼~~~~~~~~
     * @Author: 吴强
     * @Date: 2024/9/28 13:45
     * @Description: 获取可排班的轮转科室列表，包含协同单位
     * @Param: orgFlow:主机构id，默认当前登陆人的机构
     */
    public List<LzDeptItem> deptSelectData(String orgFlow){
        List<LzDeptItem> result = new ArrayList<>();
        if (StringUtils.isEmpty(orgFlow)) {
            orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        }
        if (StringUtils.isEmpty(orgFlow)) {
            return result;
        }
        //获取当前机构可轮转的所有的科室
        result = sysDeptExtMapper.deptSelectData(orgFlow);
        //获取轮转科室和标准科室之间的对应关系
        if (CollectionUtil.isNotEmpty(result)) {
            List<String> deptIdList = result.stream().map(LzDeptItem::getDeptFlow).collect(Collectors.toList());
            List<SchAndStandardDeptCfg> bzList = sysDeptExtMapper.getBzDeptListByLzDeptFlows(deptIdList);
            if (CollectionUtil.isNotEmpty(bzList)) {
                Map<String, SchAndStandardDeptCfg> map = bzList.stream().collect(Collectors.toMap(SchAndStandardDeptCfg::getSchDeptFlow, Function.identity(), (k1, k2) -> k2));
                for (LzDeptItem item : result) {
                    String deptFlow = item.getDeptFlow();
                    if (ObjectUtil.isEmpty(map.get(deptFlow))) {
                        //没有相关的标准科室的配置
                        continue;
                    }
                    SchAndStandardDeptCfg schAndStandardDeptCfg = map.get(deptFlow);
                    if (StringUtils.isEmpty(schAndStandardDeptCfg.getSchDeptFlow())) {
                        //没有相关的标准科室的配置
                        continue;
                    }
                    item.setBzDeptCode(schAndStandardDeptCfg.getStandardDeptId());
                    item.setBzDeptName(schAndStandardDeptCfg.getStandardDeptName());
                }
            }
        }
        return result;
    }


    /**
     * ~~~~~~~~~溺水的鱼~~~~~~~~
     * @Author: 吴强
     * @Date: 2024/9/28 14:37
     * @Description: 根据轮转方案id查询出方案下对应的轮转科室
     * @Param: rotationFlow:轮转方案id
     *         orgFlow: 指定机构id，空值表示当前登录人对应的机构
     */
    public List<LzDeptItem> getDeptListByRotationId(String rotationFlow,
                                                    String orgFlow){
        List<LzDeptItem> result = new ArrayList<>();
        if (StringUtils.isEmpty(rotationFlow)) {
            return result;
        }
        result = deptSelectData(orgFlow);
        if (CollectionUtil.isEmpty(result)) {
            return result;
        }
        //先排除掉没有标准科室的，因为识别不了
        result = result.stream().filter(e->StringUtils.isNotEmpty(e.getBzDeptCode())).collect(Collectors.toList());
        //再根据标准科室分组
        if (CollectionUtil.isEmpty(result)) {
            return result;
        }
        //key是标准科室的code
        Map<String, List<LzDeptItem>> bzDeptMap = result.stream().collect(Collectors.groupingBy(LzDeptItem::getBzDeptCode));
        //查新方案下的标准科室
        List<SchRotationDept> schRotationDepts = bzDeptList(rotationFlow);
        List<LzDeptItem> list = new ArrayList<>();
        for (SchRotationDept item : schRotationDepts) {
            if (StringUtils.isEmpty(item.getStandardDeptId())) {
                continue;
            }
            if (CollectionUtil.isEmpty(bzDeptMap.get(item.getStandardDeptId()))) {
                continue;
            }
            List<LzDeptItem> lzDeptItems = bzDeptMap.get(item.getStandardDeptId());
            list.addAll(lzDeptItems);
        }
        return list;
    }

    /**
     * ~~~~~~~~~溺水的鱼~~~~~~~~
     * @Author: 吴强
     * @Date: 2024/9/28 16:26
     * @Description: 根据rotationFlow获取方案下的标准科室
     * @Param: rotationFlow 方案id
     */
    public List<SchRotationDept> bzDeptList(String rotationFlow){
        SchRotationDeptExample example = new SchRotationDeptExample();
        SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowIsNull();
        if(StringUtil.isNotEmpty(rotationFlow)){
            criteria.andRotationFlowEqualTo(rotationFlow);
        }
        example.setOrderByClause("ORDINAL");
        return rotationDeptMapper.selectByExample(example);
    }


    /**
     * ~~~~~~~~~溺水的鱼~~~~~~~~
     * @Author: 吴强
     * @Date: 2024/9/29 11:20
     * @Description: 根据科室flow查询标准科室的信息
     */
    public SchAndStandardDeptCfg getBzDeptByDeptFlow(String deptFlow){
        if (StringUtils.isEmpty(deptFlow)){
            return new SchAndStandardDeptCfg();
        }
        return sysDeptExtMapper.getBzDeptByDeptFlow(deptFlow);
    }


    public SchRotationDept getRotationInfo(String rotationFlow,
                                           String standardId){
        if (StringUtils.isEmpty(rotationFlow)) {
            return new SchRotationDept();
        }
        if (StringUtils.isEmpty(standardId)) {
            return new SchRotationDept();
        }
        SchRotationDeptExample example = new SchRotationDeptExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                .andRotationFlowEqualTo(rotationFlow)
                .andStandardDeptIdEqualTo(standardId);
        List<SchRotationDept> list = rotationDeptMapper.selectByExample(example);
        if (CollectionUtil.isEmpty(list)) {
            return new SchRotationDept();
        }
        return list.get(0);
    }
}
