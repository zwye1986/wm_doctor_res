package com.pinde.sci.ctrl.jsres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IDeptBasicInfoBiz;
import com.pinde.sci.biz.jsres.IJsResDeptManagementBiz;
import com.pinde.sci.biz.jsres.IResBaseSpeDeptBiz;
import com.pinde.sci.biz.sch.ISchAndStandardDeptCfgBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.vo.ResDeptRelStdDeptVO;
import com.pinde.sci.model.vo.ResSpeBaseStdDeptVO;
import com.pinde.sci.model.vo.ResStandardDeptVO;
import com.pinde.sci.model.vo.SysDeptVO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jsres/dept")
@Transactional(rollbackFor = Exception.class)
public class JsResDeptManagementController {
    private Logger logger = LoggerFactory.getLogger(JsResDeptManagementController.class);

    @Autowired
    private IJsResDeptManagementBiz deptManagementBiz;

    @Autowired
    private IDictBiz dictBiz;

    @Autowired
    private ICfgBiz cfgBiz;

    @Autowired
    private IResBaseSpeDeptBiz baseSpeDeptBiz;

    @Autowired
    private IDeptBasicInfoBiz deptBasicInfoBiz;

    @Autowired
    private IDeptBiz deptBiz;

    @Autowired
    private ISchAndStandardDeptCfgBiz schAndStandardDeptCfgBiz;

    @Autowired
    private IUserBiz userBiz;

    @RequestMapping("deptManagementMain")
    public String deptManagementMain(String roleFlag, Model model, HttpServletRequest request) {
        model.addAttribute("roleFlag", roleFlag);


        return "jsres/dept/deptManagementMain";
    }

    @RequestMapping("deptStandardMain")
    public String deptStandardMain(Model model, HttpServletRequest request) {

        return "jsres/dept/deptStandardMain";
    }

    /**
     * 层次树形结构， 返回第一级
     * @param resStandardDeptVOList
     * @return
     */
    private List<ResStandardDeptVO> buildAllLevelTreeDept(List<ResStandardDeptVO> resStandardDeptVOList) {
        Map<String, ResStandardDeptVO> flowToEntityMap = resStandardDeptVOList.stream().collect(Collectors.toMap(vo -> vo.getStandardDeptCode(), Function.identity()));
        List<ResStandardDeptVO> res = new ArrayList<>();
        for (ResStandardDeptVO resStandardDeptVO : resStandardDeptVOList) {
            if(resStandardDeptVO.getTopLevelDeptCode().equals(resStandardDeptVO.getStandardDeptCode())) {
                res.add(resStandardDeptVO);
            }else {
                String parentDeptCode = resStandardDeptVO.getParentDeptCode();
                ResStandardDeptVO parentDeptVO = flowToEntityMap.get(parentDeptCode);
                parentDeptVO.getSubStandardDeptList().add(resStandardDeptVO);
            }
        }

        // 按code排个序
        sortAllLevel(res);

        return res;
    }

    private void sortAllLevel(List<ResStandardDeptVO> res) {
        Collections.sort(res, Comparator.comparing(ResStandardDept::getStandardDeptCode));
        for (ResStandardDeptVO vo : res) {
            if(CollectionUtils.isNotEmpty(vo.getSubStandardDeptList())) {
                sortAllLevel(vo.getSubStandardDeptList());
            }
        }
    }

    @RequestMapping("deptSpeBaseMain")
    public String deptSpeBaseMain(Model model, HttpServletRequest request) {


        return "jsres/dept/deptSpeBaseMain";
    }

    @RequestMapping("searchStandardDeptLevelAllByNameFuzzy")
    @ResponseBody
    public List<ResStandardDeptVO> searchStandardDeptLevelAllByNameFuzzy(@RequestBody ResStandardDeptVO standardDeptVO, HttpServletRequest request) {
        List<ResStandardDeptVO> resStandardDeptVOList = new ArrayList<>();
        if(StringUtil.isEmpty(standardDeptVO.getStandardDeptNameFuzzy())) {
            resStandardDeptVOList = deptManagementBiz.searchStandardDept(standardDeptVO);
        }else {
            // 不查层级了，只简单查名称中有standardDeptNameFuzzy的标准科室
            resStandardDeptVOList = deptManagementBiz.searchStandardDeptLevelAllByNameFuzzy(standardDeptVO);
        }

        // 专业基地关联标准科室，增加专业基地信息
        if("Y".equals(standardDeptVO.getWithSpeBaseFlag()) && StringUtils.isNotEmpty(standardDeptVO.getSpeBaseId())) {
            ResSpeBaseStdDeptVO speBaseStdDeptVO = new ResSpeBaseStdDeptVO();
            speBaseStdDeptVO.setSpeBaseId(standardDeptVO.getSpeBaseId());
            // 1:必选，2：可选
            speBaseStdDeptVO.setRequireStatusIn(Arrays.asList("1", "2"));
            List<String> stdDeptFlowList = resStandardDeptVOList.stream().map(vo -> vo.getStandardDeptFlow()).collect(Collectors.toList());
            speBaseStdDeptVO.setStandardDeptFlowIn(stdDeptFlowList);
            List<ResSpeBaseStdDeptVO> resSpeBaseStdDeptVOList = deptManagementBiz.searchSpeBaseStdDept(speBaseStdDeptVO);

            if(StringUtil.isEmpty(standardDeptVO.getStandardDeptNameFuzzy())) {
                return buildAllLevelTreeDeptWithRequireStatus(resStandardDeptVOList, resSpeBaseStdDeptVOList);
            }else {
                deptAddRequireStatus(resStandardDeptVOList, resSpeBaseStdDeptVOList);
                return resStandardDeptVOList;
            }
        }
        // 基地科室关联标准科室，增加基地科室信息
        if("Y".equals(standardDeptVO.getWithBaseDeptFlag()) && StringUtils.isNotEmpty(standardDeptVO.getDeptFlow())) {
            SysDeptVO sysDeptVO = new SysDeptVO();
            sysDeptVO.setDeptFlow(standardDeptVO.getDeptFlow());
            List<SysDeptVO> sysDeptVOList = deptManagementBiz.selectSysDeptRelStdDept(sysDeptVO);
            if(CollectionUtils.isNotEmpty(sysDeptVOList)) {
                SysDeptVO tempVO = sysDeptVOList.get(0);
                if(StringUtils.isNotEmpty(tempVO.getStandardDeptFlow())) {
                    for (ResStandardDeptVO resStandardDeptVO : resStandardDeptVOList) {
                        if(resStandardDeptVO.getStandardDeptFlow().equals(tempVO.getStandardDeptFlow())) {
                            resStandardDeptVO.setCurRelStandardDeptFlag("Y");
                            break;
                        }
                    }
                }
            }
        }

        if(StringUtil.isEmpty(standardDeptVO.getStandardDeptNameFuzzy())) {
            return buildAllLevelTreeDept(resStandardDeptVOList);
        }
        return resStandardDeptVOList;
    }

    private void deptAddRequireStatus(List<ResStandardDeptVO> resStandardDeptVOList, List<ResSpeBaseStdDeptVO> resSpeBaseStdDeptVOList) {
        Map<String, ResSpeBaseStdDeptVO> stdDeptFlowToSpeBaseStdDeptMap = resSpeBaseStdDeptVOList.stream().collect(Collectors.toMap(vo -> vo.getStandardDeptFlow(), Function.identity()));
        for (ResStandardDeptVO resStandardDeptVO : resStandardDeptVOList) {
            // 设置一下轮转状态（必轮，选轮）
            ResSpeBaseStdDeptVO speBaseStdDeptVO = stdDeptFlowToSpeBaseStdDeptMap.get(resStandardDeptVO.getStandardDeptFlow());
            if(null != speBaseStdDeptVO) {
                resStandardDeptVO.setRotateRequireStatus(speBaseStdDeptVO.getRotationRequireStatus());
                resStandardDeptVO.setSpeBaseStdDeptFlow(speBaseStdDeptVO.getSpeBaseStdDeptFlow());
            }
        }
    }

    private List<ResStandardDeptVO> buildAllLevelTreeDeptWithRequireStatus(List<ResStandardDeptVO> resStandardDeptVOList, List<ResSpeBaseStdDeptVO> resSpeBaseStdDeptVOList) {
        Map<String, ResStandardDeptVO> flowToEntityMap = resStandardDeptVOList.stream().collect(Collectors.toMap(vo -> vo.getStandardDeptCode(), Function.identity()));
        Map<String, ResSpeBaseStdDeptVO> stdDeptFlowToSpeBaseStdDeptMap = resSpeBaseStdDeptVOList.stream().collect(Collectors.toMap(vo -> vo.getStandardDeptFlow(), Function.identity()));
        List<ResStandardDeptVO> res = new ArrayList<>();
        for (ResStandardDeptVO resStandardDeptVO : resStandardDeptVOList) {
            if(resStandardDeptVO.getTopLevelDeptCode().equals(resStandardDeptVO.getStandardDeptCode())) { // 最外层
                res.add(resStandardDeptVO);
            }else { // 内层
                String parentDeptCode = resStandardDeptVO.getParentDeptCode();
                ResStandardDeptVO parentDeptVO = flowToEntityMap.get(parentDeptCode);
                parentDeptVO.getSubStandardDeptList().add(resStandardDeptVO);
            }
            // 设置一下轮转状态（必轮，选轮）
            ResSpeBaseStdDeptVO speBaseStdDeptVO = stdDeptFlowToSpeBaseStdDeptMap.get(resStandardDeptVO.getStandardDeptFlow());
            if(null != speBaseStdDeptVO) {
                resStandardDeptVO.setRotateRequireStatus(speBaseStdDeptVO.getRotationRequireStatus());
                resStandardDeptVO.setSpeBaseStdDeptFlow(speBaseStdDeptVO.getSpeBaseStdDeptFlow());
            }
        }

        return res;
    }

    @RequestMapping("deleteStandardDept")
    @ResponseBody
    public String deleteStandardDept(@RequestBody ResStandardDeptVO standardDeptVO, HttpServletRequest request) {
        // 入参校验
        if(StringUtils.isAnyEmpty(standardDeptVO.getStandardDeptCode(), standardDeptVO.getStandardDeptFlow())) {
            return "入参不完整，科室编码和科室id应有值";
        }

        // 当前标准科室不应关联有基地科室
        ResDeptRelStdDeptVO deptRelStdDeptVO = new ResDeptRelStdDeptVO();
        deptRelStdDeptVO.setStandardDeptFlow(standardDeptVO.getStandardDeptFlow());
        PageHelper.startPage(1, 1, false);
        List<ResDeptRelStdDeptVO> resDeptRelStdDeptVOList = deptManagementBiz.searchResDeptRelStdDept(deptRelStdDeptVO);
        // 查出有数据，报错
        if(CollectionUtils.isNotEmpty(resDeptRelStdDeptVOList)) {
            return "当前已有专业基地关联，无法删除";
        }
        // 当前标准科室不应关联专业基地
        ResSpeBaseStdDeptVO speBaseStdDeptVO = new ResSpeBaseStdDeptVO();
        speBaseStdDeptVO.setStandardDeptFlow(standardDeptVO.getStandardDeptFlow());
        speBaseStdDeptVO.setRequireStatusIn(Arrays.asList("1", "2"));
        PageHelper.startPage(1, 1, false);
        List<ResSpeBaseStdDeptVO> resSpeBaseStdDeptVOList = deptManagementBiz.searchSpeBaseStdDept(speBaseStdDeptVO);
        // 查出有数据，报错
        if(CollectionUtils.isNotEmpty(resSpeBaseStdDeptVOList)) {
            return "当前已有专业基地关联，无法删除";
        }

        // 删除当前科室
        deptManagementBiz.deleteStandardDeptByDeptFlow(standardDeptVO.getStandardDeptFlow());

        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping("saveStandardDept/{operType}")
    @ResponseBody
    public String saveStandardDept(@PathVariable String operType, @RequestBody ResStandardDeptVO standardDeptVO, HttpServletRequest request) {
        if("addLevel1".equals(operType)) { // 新增一级标准科室
            return insertTopDept(standardDeptVO);
        }
        if("edit".equals(operType)) { // 编辑科室名称和code
            // 入参校验
            if(StringUtils.isAnyEmpty(standardDeptVO.getStandardDeptCode(), standardDeptVO.getStandardDeptName(), standardDeptVO.getStandardDeptFlow())) {
                return "入参不完整，科室名称和科室编码和科室id应有值";
            }

            // 校验科室名称和code不能与已有其它科室重复
            ResStandardDeptVO queryVO = new ResStandardDeptVO();
            queryVO.setDeptNameCodeOrPair(Arrays.asList(standardDeptVO.getStandardDeptName(), standardDeptVO.getStandardDeptCode()));
            List<ResStandardDeptVO> resStandardDeptVOList = deptManagementBiz.searchStandardDept(queryVO);
            if(CollectionUtils.isNotEmpty(resStandardDeptVOList)) {
                for (ResStandardDeptVO resStandardDeptVO : resStandardDeptVOList) {
                    if(!resStandardDeptVO.getStandardDeptFlow().equals(standardDeptVO.getStandardDeptFlow())) {
                        return "存在相同的科室名称或科室编码，请检查";
                    }
                }
            }

            queryVO = new ResStandardDeptVO();
            queryVO.setStandardDeptFlow(standardDeptVO.getStandardDeptFlow());
            List<ResStandardDeptVO> curDpetList = deptManagementBiz.searchStandardDept(queryVO);
            ResStandardDeptVO curDept = curDpetList.get(0);


            // 保存数据
            ResStandardDeptVO updateStandardDeptVO = new ResStandardDeptVO();
            updateStandardDeptVO.setStandardDeptFlow(standardDeptVO.getStandardDeptFlow());
            updateStandardDeptVO.setStandardDeptName(standardDeptVO.getStandardDeptName());
            updateStandardDeptVO.setStandardDeptCode(standardDeptVO.getStandardDeptCode());
            GeneralMethod.setRecordInfo(updateStandardDeptVO, false);
            deptManagementBiz.updateStandardDept(updateStandardDeptVO);

            if(curDept.getStandardDeptCode().equals(standardDeptVO.getStandardDeptCode())) {
                return GlobalConstant.SAVE_SUCCESSED;
            }

            // 修改了deptCode，那么所有下层的dept要更新parentCode，如果当前是最外层，topDept也要更新
            List<ResStandardDeptVO> updatingDeptList = new ArrayList<>();
            if(StringUtils.equals(curDept.getDeptLevel(), "1")) { // 如果是第一层，那么所有下层都要更新
                // 找所有子层
                queryVO = new ResStandardDeptVO();
                queryVO.setTopLevelDeptCode(curDept.getStandardDeptCode());
                List<ResStandardDeptVO> subDeptList = deptManagementBiz.searchStandardDept(queryVO);
                if(CollectionUtils.isNotEmpty(subDeptList)) {
                    subDeptList.forEach(subDept -> {
                        ResStandardDeptVO updatingDeptVO = new ResStandardDeptVO();
                        updatingDeptVO.setTopLevelDeptCode(standardDeptVO.getStandardDeptCode()); // 更新topDept
                        if(subDept.getParentDeptCode().equals(curDept.getStandardDeptCode())) {
                            updatingDeptVO.setParentDeptCode(standardDeptVO.getStandardDeptCode()); // 更新下一层的parent
                        }
                        updatingDeptVO.setStandardDeptFlow(subDept.getStandardDeptFlow());
                        updatingDeptList.add(updatingDeptVO);
                    });
                }
            }else { // 否则只更新下一层
                // 找下一层
                queryVO = new ResStandardDeptVO();
                queryVO.setParentDeptCode(curDept.getStandardDeptCode());
                List<ResStandardDeptVO> subDeptList = deptManagementBiz.searchStandardDept(queryVO);
                if(CollectionUtils.isNotEmpty(subDeptList)) {
                    subDeptList.forEach(subDept -> {
                        ResStandardDeptVO updatingDeptVO = new ResStandardDeptVO();
                        updatingDeptVO.setParentDeptCode(standardDeptVO.getStandardDeptCode());
                        updatingDeptVO.setStandardDeptFlow(subDept.getStandardDeptFlow());
                        updatingDeptList.add(updatingDeptVO);
                    });
                }
            }

            if(CollectionUtils.isNotEmpty(updatingDeptList)) { // 只更新要更新的字段
                deptManagementBiz.batchUpdateStandardDeptSelective(updatingDeptList);
            }
        }
        if("addSibling".equals(operType)) { // 增加同级
            // 参数校验
            if (StringUtils.isAnyEmpty(standardDeptVO.getStandardDeptCode(), standardDeptVO.getStandardDeptName(), standardDeptVO.getDeptLevel())) {
                return "入参不完整，科室名称和科室编码和科室层级应有值";
            }
            if (!StringUtils.equals("1", standardDeptVO.getDeptLevel()) && StringUtils.isEmpty(standardDeptVO.getParentDeptCode())) {
                return "上级科室编码不存在，请检查";
            }

            if (StringUtils.equals("1", standardDeptVO.getDeptLevel())) {
                return insertTopDept(standardDeptVO);
            }

            // 不是第一级
            return insertSubDept(standardDeptVO);
        }
        if("addSub".equals(operType)) {
            // 参数校验
            if(StringUtils.isAnyEmpty(standardDeptVO.getStandardDeptCode(), standardDeptVO.getStandardDeptName(), standardDeptVO.getParentDeptCode())) {
                return "入参不完整，科室名称和科室编码和父级编码应有值";
            }
            return insertSubDept(standardDeptVO);
        }
        if("editDeptStatus".equals(operType)) {
            // 入参校验
            if(StringUtils.isAnyEmpty(standardDeptVO.getStandardDeptFlow(), standardDeptVO.getDeptStatus())) {
                return "入参不完整，科室id和科室状态应有值";
            }

            // 当禁用时，与删除一样的校验
            if("N".equals(standardDeptVO.getDeptStatus())) {
                // 当前标准科室不应关联有基地科室
                ResDeptRelStdDeptVO deptRelStdDeptVO = new ResDeptRelStdDeptVO();
                deptRelStdDeptVO.setStandardDeptFlow(standardDeptVO.getStandardDeptFlow());
                PageHelper.startPage(1, 1, false);
                List<ResDeptRelStdDeptVO> resDeptRelStdDeptVOList = deptManagementBiz.searchResDeptRelStdDept(deptRelStdDeptVO);
                // 查出有数据，报错
                if(CollectionUtils.isNotEmpty(resDeptRelStdDeptVOList)) {
                    return "当前已有专业基地关联，无法禁用";
                }
                // 当前标准科室不应关联专业基地
                ResSpeBaseStdDeptVO speBaseStdDeptVO = new ResSpeBaseStdDeptVO();
                speBaseStdDeptVO.setStandardDeptFlow(standardDeptVO.getStandardDeptFlow());
                speBaseStdDeptVO.setRequireStatusIn(Arrays.asList("1", "2"));
                PageHelper.startPage(1, 1, false);
                List<ResSpeBaseStdDeptVO> resSpeBaseStdDeptVOList = deptManagementBiz.searchSpeBaseStdDept(speBaseStdDeptVO);
                // 查出有数据，报错
                if (CollectionUtils.isNotEmpty(resSpeBaseStdDeptVOList)) {
                    return "当前已有专业基地关联，无法禁用";
                }
            }

            ResStandardDeptVO updateStandardDeptVO = new ResStandardDeptVO();
            updateStandardDeptVO.setStandardDeptFlow(standardDeptVO.getStandardDeptFlow());
            updateStandardDeptVO.setDeptStatus(standardDeptVO.getDeptStatus());
            GeneralMethod.setRecordInfo(updateStandardDeptVO, false);
            deptManagementBiz.updateStandardDept(updateStandardDeptVO);
        }

        return GlobalConstant.SAVE_SUCCESSED;
    }

    private String insertSubDept(ResStandardDeptVO standardDeptVO) {
        // 名称和编码不能重复
        ResStandardDeptVO queryVO = new ResStandardDeptVO();
        queryVO.setDeptNameCodeOrPair(Arrays.asList(standardDeptVO.getStandardDeptName(), standardDeptVO.getStandardDeptCode()));
        List<ResStandardDeptVO> resStandardDeptVOList = deptManagementBiz.searchStandardDept(queryVO);
        if (CollectionUtils.isNotEmpty(resStandardDeptVOList)) {
            return "存在相同的科室名称或科室编码，请检查";
        }

        // 获取上一级
        queryVO = new ResStandardDeptVO();
        queryVO.setStandardDeptCode(standardDeptVO.getParentDeptCode());
        resStandardDeptVOList = deptManagementBiz.searchStandardDept(queryVO);
        ResStandardDeptVO parentDeptVO = resStandardDeptVOList.get(0);
        // 准备插入数据
        ResStandardDeptVO insertingDeptVO = new ResStandardDeptVO();
        insertingDeptVO.setStandardDeptFlow(PkUtil.getUUID());
        insertingDeptVO.setStandardDeptCode(standardDeptVO.getStandardDeptCode());
        insertingDeptVO.setStandardDeptName(standardDeptVO.getStandardDeptName());
        String deptLevel = standardDeptVO.getDeptLevel();
        if(StringUtils.isEmpty(deptLevel)) {
            deptLevel = String.valueOf(Integer.parseInt(parentDeptVO.getDeptLevel()) + 1);
        }
        insertingDeptVO.setDeptLevel(deptLevel);
        insertingDeptVO.setParentDeptCode(parentDeptVO.getStandardDeptCode());
        insertingDeptVO.setTopLevelDeptCode(parentDeptVO.getTopLevelDeptCode());
        insertingDeptVO.setRecordStatus("Y");
        insertingDeptVO.setDeptStatus("Y");
        GeneralMethod.setRecordInfo(insertingDeptVO, true);
        deptManagementBiz.saveStandardDept(insertingDeptVO);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    private String insertTopDept(ResStandardDeptVO standardDeptVO) {
        // 入参校验
        if(StringUtils.isAnyEmpty(standardDeptVO.getStandardDeptCode(), standardDeptVO.getStandardDeptName())) {
            return "入参不完整，科室名称和科室编码应必填";
        }
        // 校验科室名称和code不能与已有科室重复
        ResStandardDeptVO queryVO = new ResStandardDeptVO();
        queryVO.setDeptNameCodeOrPair(Arrays.asList(standardDeptVO.getStandardDeptName(), standardDeptVO.getStandardDeptCode()));
        List<ResStandardDeptVO> resStandardDeptVOList = deptManagementBiz.searchStandardDept(queryVO);
        if(CollectionUtils.isNotEmpty(resStandardDeptVOList)) {
            return "存在相同的科室名称或科室编码，请检查";
        }

        // 保存数据
        standardDeptVO.setDeptLevel("1");
        standardDeptVO.setDeptLevelName("一级");
        standardDeptVO.setParentDeptCode(standardDeptVO.getStandardDeptCode());
        standardDeptVO.setTopLevelDeptCode(standardDeptVO.getStandardDeptCode());
        standardDeptVO.setRecordStatus("Y");
        standardDeptVO.setDeptStatus("Y");
        standardDeptVO.setStandardDeptFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(standardDeptVO, true);
        deptManagementBiz.saveStandardDept(standardDeptVO);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 专业基地以数据字典的形式存在，暂时不改
     * @param request
     * @return
     */
    @RequestMapping("searchSpeBaseAll")
    @ResponseBody
    public List<SysDict> searchSpeBaseByNameFuzzy(String speBaseNameFuzzy, HttpServletRequest request) {
        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(DictTypeEnum.DoctorTrainingSpe.getId());
        // id不能为50(助理全科）
        sysDictList = sysDictList.stream().filter(vo -> !"50".equals(vo.getDictId())).collect(Collectors.toList());
        Collections.sort(sysDictList, Comparator.comparing(SysDict::getDictId));
        return sysDictList;
    }

    /**
     * 专业基地配置关联标准科室，多对多
     * @return
     */
    @RequestMapping("configSpeBase")
    public String configSpeBase(String speBaseId, String speBaseName, String height, Model model, HttpServletRequest request) {
        model.addAttribute("speBaseId", speBaseId);
        model.addAttribute("speBaseName", speBaseName);
        model.addAttribute("dialogHeight", height);
        return "/jsres/dept/deptSpeBaseConfig";
    }

    @RequestMapping("configSpeBaseStatus")
    @ResponseBody
    public String configSpeBaseStatus(@RequestBody ResStandardDeptVO standardDeptVO, HttpServletRequest request) {
        // 入参校验
        if(StringUtils.isAnyEmpty(standardDeptVO.getSpeBaseId(), standardDeptVO.getStandardDeptFlow(), standardDeptVO.getRotateRequireStatus())) {
            return "入参不完整，必填字段没有值";
        }
        // todo 逻辑校验，暂不考虑

        // 先查有没有speBaseId和standardDeptFlow相应记录，有就更新，没有就新增
        ResSpeBaseStdDeptVO queryVO = new ResSpeBaseStdDeptVO();
        queryVO.setSpeBaseId(standardDeptVO.getSpeBaseId());
        queryVO.setStandardDeptFlow(standardDeptVO.getStandardDeptFlow());
        List<ResSpeBaseStdDeptVO> resSpeBaseStdDeptVOList = deptManagementBiz.searchSpeBaseStdDept(queryVO);
        if(CollectionUtils.isEmpty(resSpeBaseStdDeptVOList)) { // 无则新增
            ResSpeBaseStdDeptVO insertVO = new ResSpeBaseStdDeptVO();
            insertVO.setSpeBaseStdDeptFlow(PkUtil.getUUID());
            insertVO.setStandardDeptFlow(standardDeptVO.getStandardDeptFlow());
            insertVO.setSpeBaseId(standardDeptVO.getSpeBaseId());
            insertVO.setRotationRequireStatus(standardDeptVO.getRotateRequireStatus());
            GeneralMethod.setRecordInfo(insertVO, true);
            deptManagementBiz.saveSpeBaseStdDept(insertVO);
        }else if(resSpeBaseStdDeptVOList.size() == 1) { // 有则修改
            ResSpeBaseStdDeptVO speBaseStdDeptVO = resSpeBaseStdDeptVOList.get(0);
            ResSpeBaseStdDeptVO updateVO = new ResSpeBaseStdDeptVO();
            updateVO.setSpeBaseStdDeptFlow(speBaseStdDeptVO.getSpeBaseStdDeptFlow());
            updateVO.setRotationRequireStatus(standardDeptVO.getRotateRequireStatus());
            GeneralMethod.setRecordInfo(updateVO, false);
            deptManagementBiz.updateSpeBaseStdDeptSelective(updateVO);
        }else { // 多条则报错
            return "系统内部错误，请联系运维人员";
        }

        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping("configSpeBaseDelete")
    @ResponseBody
    public String configSpeBaseStatus(String speBaseStdDeptFlow, HttpServletRequest request) {
        // todo 逻辑校验（校验存在性等），暂不做

        deptManagementBiz.deleteSpeBaseStdDeptByKey(speBaseStdDeptFlow);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping("baseDeptMain")
    public String baseDeptMain(String orgFlow, HttpServletRequest request) {
        return "/jsres/dept/deptBaseDeptMain";
    }

    @RequestMapping("queryBaseDept")
    public String queryBaseDept(String orgFlow, String deptCode, String deptNameFuzzy, String relStdDeptFlag, Integer currentPage, Model model, HttpServletRequest request) {
        /*ResDeptRelStdDeptVO deptRelStdDeptVO = new ResDeptRelStdDeptVO();
        deptRelStdDeptVO.setOrgFlow(orgFlow);
        deptRelStdDeptVO.setDeptCode(deptCode);
        deptRelStdDeptVO.setDeptNameFuzzy(deptNameFuzzy);
        deptRelStdDeptVO.setRelStdDeptFlag(relStdDeptFlag);
        PageHelper.startPage(currentPage, Integer.parseInt(cfgBiz.getPageSize(request)));
        List<ResDeptRelStdDeptVO> resDeptRelStdDeptVOList = deptManagementBiz.searchResDeptRelStdDept(deptRelStdDeptVO);

        List<String> standardDeptFlowList = resDeptRelStdDeptVOList.stream().filter(vo -> StringUtils.isNotEmpty(vo.getStandardDeptFlow())).map(vo -> vo.getStandardDeptFlow()).collect(Collectors.toList());

        ResStandardDeptVO standardDeptVO = new ResStandardDeptVO();
        standardDeptVO.setStandardDeptFlowList(standardDeptFlowList);
        List<ResStandardDeptVO> resStandardDeptVOList = deptManagementBiz.searchStandardDept(standardDeptVO);
        Map<String, ResStandardDeptVO> stdDeptFlowToEntityMap = resStandardDeptVOList.stream().collect(Collectors.toMap(vo -> vo.getStandardDeptFlow(), Function.identity()));
        resDeptRelStdDeptVOList.forEach(vo -> {
            if(StringUtils.isNotEmpty(vo.getStandardDeptFlow())) {
                ResStandardDeptVO stdDeptVO = stdDeptFlowToEntityMap.get(vo.getStandardDeptFlow());
                if(null != stdDeptVO) {
                    vo.setStandardDeptName(stdDeptVO.getStandardDeptName());
                }
            }
        });*/

        SysDeptVO sysDeptVO = new SysDeptVO();
        sysDeptVO.setOrgFlow(orgFlow);
        sysDeptVO.setDeptCode(deptCode);
        sysDeptVO.setDeptNameFuzzy(deptNameFuzzy);
        sysDeptVO.setRelStdDeptFlag(relStdDeptFlag);
        PageHelper.startPage(currentPage, Integer.parseInt(cfgBiz.getPageSize(request)));
        List<SysDeptVO> sysDeptVOList = deptManagementBiz.selectSysDeptRelStdDept(sysDeptVO);

        model.addAttribute("baseDeptList", sysDeptVOList);
        return "/jsres/dept/deptBaseDeptList";
    }

    @RequestMapping("saveBaseDept")
    @ResponseBody
    public String saveBaseDept(@RequestBody ResDeptRelStdDeptVO deptRelStdDeptVO, HttpServletRequest request) {
        if("add".equals(deptRelStdDeptVO.getOperType())) {
            if(StringUtils.isAnyEmpty(deptRelStdDeptVO.getDeptCode(), deptRelStdDeptVO.getDeptName(), deptRelStdDeptVO.getOrgFlow()) || null == deptRelStdDeptVO.getOrdinal()) {
                return "请求入参不完整，请检查！";
            }

            // 校验编码和名称不能与其它的科室重复
            SysDept sysDept = new SysDept();
            sysDept.setDeptName(deptRelStdDeptVO.getDeptName());
            PageHelper.startPage(1, 1, false);
            List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
            if(CollectionUtils.isNotEmpty(sysDeptList)) {
                return "存在相同的基地科室名称或基地科室编码，请检查";
            }
            sysDept = new SysDept();
            sysDept.setDeptCode(deptRelStdDeptVO.getDeptCode());
            PageHelper.startPage(1, 1, false);
            sysDeptList = deptBiz.searchDept(sysDept);
            if(CollectionUtils.isNotEmpty(sysDeptList)) {
                return "存在相同的基地科室名称或基地科室编码，请检查";
            }

            // 新增科室
            sysDept = new SysDept();
            sysDept.setDeptFlow(PkUtil.getUUID());
            sysDept.setOrgFlow(deptRelStdDeptVO.getOrgFlow());
            sysDept.setDeptFlow(deptRelStdDeptVO.getDeptFlow());
            sysDept.setDeptCode(deptRelStdDeptVO.getDeptCode());
            sysDept.setDeptName(deptRelStdDeptVO.getDeptName());
            sysDept.setOrdinal(deptRelStdDeptVO.getOrdinal());
            GeneralMethod.setRecordInfo(sysDept, true);
            deptBiz.saveDept(sysDept);
        }
        if("edit".equals(deptRelStdDeptVO.getOperType())) {
            // 入参校验
            if(StringUtils.isAnyEmpty(deptRelStdDeptVO.getDeptFlow(), deptRelStdDeptVO.getDeptCode(), deptRelStdDeptVO.getDeptName()) || null == deptRelStdDeptVO.getOrdinal()) {
                return "请求入参不完整，请检查！";
            }
            // 校验编码和名称不能与其它的科室重复
            SysDept sysDept = new SysDept();
            sysDept.setDeptName(deptRelStdDeptVO.getDeptName());
            PageHelper.startPage(1, 2, false);
            List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
            for (SysDept dept : sysDeptList) {
                if(!dept.getDeptFlow().equals(deptRelStdDeptVO.getDeptFlow())) {
                    return "存在相同的基地科室名称或基地科室编码，请检查";
                }
            }
            sysDept = new SysDept();
            sysDept.setDeptCode(deptRelStdDeptVO.getDeptCode());
            PageHelper.startPage(1, 2, false);
            sysDeptList = deptBiz.searchDept(sysDept);
            for (SysDept dept : sysDeptList) {
                if(!dept.getDeptFlow().equals(deptRelStdDeptVO.getDeptFlow())) {
                    return "存在相同的基地科室名称或基地科室编码，请检查";
                }
            }

            // 更新科室
            sysDept = new SysDept();
            sysDept.setDeptFlow(deptRelStdDeptVO.getDeptFlow());
            sysDept.setDeptCode(deptRelStdDeptVO.getDeptCode());
            sysDept.setDeptName(deptRelStdDeptVO.getDeptName());
            sysDept.setOrdinal(deptRelStdDeptVO.getOrdinal());
            GeneralMethod.setRecordInfo(sysDept, false);
            deptBiz.saveDept(sysDept);
        }
        if("toggleStatus".equals(deptRelStdDeptVO.getOperType())) {
            if(StringUtils.isAnyEmpty(deptRelStdDeptVO.getDeptFlow(), deptRelStdDeptVO.getRecordStatus())) {
                return "请求入参不完整，请检查！";
            }

            // 更新状态
            SysDept sysDept = new SysDept();
            sysDept.setDeptFlow(deptRelStdDeptVO.getDeptFlow());
            sysDept.setRecordStatus(deptRelStdDeptVO.getRecordStatus());
            GeneralMethod.setRecordInfo(sysDept, false);
            deptBiz.saveDept(sysDept);
        }
        if("relDept".equals(deptRelStdDeptVO.getOperType())) {
            if(StringUtils.isAnyEmpty(deptRelStdDeptVO.getDeptFlow(), deptRelStdDeptVO.getStandardDeptFlow())) {
                return "请求入参不完整，请检查！";
            }

            SysDept sysDept = deptBiz.readSysDept(deptRelStdDeptVO.getDeptFlow());
            ResDeptRelStdDeptVO resDeptRelStdDeptVO = new ResDeptRelStdDeptVO();
            resDeptRelStdDeptVO.setOrgFlow(sysDept.getOrgFlow());
            resDeptRelStdDeptVO.setDeptCode(sysDept.getDeptCode());
//            resDeptRelStdDeptVO.setStandardDeptFlow(deptRelStdDeptVO.getStandardDeptFlow());
            List<ResDeptRelStdDeptVO> resDeptRelStdDeptVOList = deptManagementBiz.searchResDeptRelStdDept(resDeptRelStdDeptVO);
            if(CollectionUtils.isNotEmpty(resDeptRelStdDeptVOList)) { // 有则更新
                ResDeptRelStdDeptVO relStdDeptVO = resDeptRelStdDeptVOList.get(0);
                ResDeptRelStdDeptVO updateRelDeptVO = new ResDeptRelStdDeptVO();
                updateRelDeptVO.setDeptFlow(relStdDeptVO.getDeptFlow());
                updateRelDeptVO.setStandardDeptFlow(deptRelStdDeptVO.getStandardDeptFlow());
                GeneralMethod.setRecordInfo(updateRelDeptVO, false);
                deptManagementBiz.updateDeptRelStdDept(updateRelDeptVO);
            }else { // 无则新增
                ResDeptRelStdDeptVO insertRelDeptVO = new ResDeptRelStdDeptVO();
                insertRelDeptVO.setDeptFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(insertRelDeptVO, true);
                insertRelDeptVO.setDeptCode(sysDept.getDeptCode());
                insertRelDeptVO.setDeptName(sysDept.getDeptName());
                insertRelDeptVO.setOrgFlow(sysDept.getOrgFlow());
                insertRelDeptVO.setOrgName(GlobalContext.getCurrentUser().getOrgName());
                insertRelDeptVO.setStandardDeptFlow(deptRelStdDeptVO.getStandardDeptFlow());
                GeneralMethod.setRecordInfo(insertRelDeptVO, false);
                deptManagementBiz.saveDeptRelStdDept(insertRelDeptVO);
            }
        }

        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping("configBaseDept")
    public String configSpeBase(String deptFlow, String height, Model model, HttpServletRequest request) {
        model.addAttribute("curDeptFlow", deptFlow);
        model.addAttribute("dialogHeight", height);
        return "/jsres/dept/deptBaseDeptConfig";
    }

    @RequestMapping("baseDeptDelete")
    @ResponseBody
    public String baseDeptDelete(@RequestBody ResDeptRelStdDeptVO deptRelStdDeptVO, HttpServletRequest request) {
        if(StringUtils.isEmpty(deptRelStdDeptVO.getDeptFlow())) {
            return "请求入参不完整，请检查！";
        }

        // 删除前校验，该基地科室下没有填写过信息（不好处理，处理成没有记录，而不是一个字段一个字段看，即使什么都没填，但只要保存过，那就认为是有信息）
        // RES_BASE_SPE_DEPT表每一年都没有相关记录，RES_BASE_SPE_DEPT_DATA表每一年都没有相关记录
        ResBaseSpeDept resBaseSpeDept = baseSpeDeptBiz.readBaseByDeptFlow(deptRelStdDeptVO.getDeptFlow());
        if(resBaseSpeDept != null) {
            return "当前科室有信息，不可删除";
        }
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("deptFlow", deptRelStdDeptVO.getDeptFlow());
        int dataCount = deptBasicInfoBiz.countResBaseSpeDeptInfoData(paramMap);
        if(dataCount > 0) {
            return "当前科室有信息，不可删除";
        }
        // 当前科室没有关联标准科室
        SysDept sysDept = deptBiz.readSysDept(deptRelStdDeptVO.getDeptFlow());

        ResDeptRelStdDeptVO resDeptRelStdDeptVO = new ResDeptRelStdDeptVO();
        resDeptRelStdDeptVO.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        resDeptRelStdDeptVO.setDeptCode(sysDept.getDeptCode());
        List<ResDeptRelStdDeptVO> resDeptRelStdDeptVOList = deptManagementBiz.searchResDeptRelStdDept(resDeptRelStdDeptVO);
        if(CollectionUtils.isNotEmpty(resDeptRelStdDeptVOList)) {
            return "当前科室关联了标准科室，不可删除";
        }

        // 当前科室没有关联考试科室
        SchAndStandardDeptCfg standardDeptCfg = schAndStandardDeptCfgBiz.readBySchDeptFlowAndOrgFlow(deptRelStdDeptVO.getDeptFlow(), GlobalContext.getCurrentUser().getOrgFlow());
        if(null != standardDeptCfg) {
            return "当前科室关联了考试科室，不可删除";
        }

        // 当前科室下没有用户
        PageHelper.startPage(1, 1, false);
        SysUserExample userExample = new SysUserExample();
        userExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow()).andDeptFlowEqualTo(deptRelStdDeptVO.getDeptFlow());
        List<SysUser> sysUserList = userBiz.readSysUserByExample(userExample);
        if(CollectionUtils.isNotEmpty(sysUserList)) {
            return "当前科室下有用户，不可删除";
        }

        // 硬删，慎用
        deptBiz.deleteDeptByKey(deptRelStdDeptVO.getDeptFlow());

        return GlobalConstant.SAVE_SUCCESSED;
    }
}
