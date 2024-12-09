package com.pinde.sci.excelListens;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.PkUtil;
import com.pinde.sci.excelListens.model.*;
import com.pinde.sci.model.mo.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ~~~~~~~~~溺水的鱼~~~~~~~~
 *
 * @Author: 吴强
 * @Date: 2024/10/26/14:21
 * @Description: 导入排班excel数据读取监听器
 */
@Slf4j
@Data
public class SchedulingAuditRead extends AnalysisEventListener<Map<Integer, String>> {

    //根据姓名或者身份证号查询res_doctor
    private List<SysUser> userList = new ArrayList<>();

    private Map<String,ResDoctor> doctorMap = new HashMap<>();
    //人员姓名_身份证号：userId
    private Map<String,String> userMap = new HashMap<>();
    //导入的学员姓名集合
    private Set<String> excelUserName = new HashSet<>();
    //导入的学员身份证集合
    private Set<String> excelIdNo = new HashSet<>();
    //第一行表头
    private List<String> oneHeaders = new ArrayList<>();
    //第二行表头
    private List<String> twoHeaders = new ArrayList<>();
    //专业-标准科室
    private Map<String, List<SchRotationDept>> bzMap = new HashMap<>();
    //实际可轮转科室：机构下的科室+协同单位开放的科室
    private List<SysDept> lzDept = new ArrayList<>();
    //学员-已排班的排班记录
    private List<SchArrangeResult> stuResultList = new ArrayList<>();

    //数据整合比较过渡集合
    private List<PbInfoItem> compareList = new ArrayList<>();

    //本次导入的所有的resultFlow集合对应的学员数据提交情况
    private Map<String, Integer> resRecList = new HashMap<>();

    //处理后合理的排班安排,可以入库的数据
    private List<SchArrangeResult> arrangeResults = new ArrayList<>();



    //解析出来的数据
    private List<SchedulingDataModel> data = new ArrayList<>();

    public SchedulingAuditRead(){

    }

    public SchedulingAuditRead(Map<String, List<SchRotationDept>> bzMap,
                               List<SysDept> lzDept) {
        this.bzMap = bzMap;
        this.lzDept = lzDept;
    }



    @Override
    public void invoke(Map<Integer, String> integerStringMap, AnalysisContext analysisContext) {
        log.info("excel数据：：：：{}",integerStringMap.size());
        if (CollectionUtil.isNotEmpty(integerStringMap)) {
            SchedulingDataModel row = new SchedulingDataModel();
            List<SchedulingDataInfo> cellList = new ArrayList<>();
            SchedulingDataInfo cellItem = new SchedulingDataInfo();
            String speName = integerStringMap.get(2);
            if (StringUtils.isNotEmpty(integerStringMap.get(0))) {
                excelUserName.add(integerStringMap.get(0));
            }
            if (StringUtils.isNotEmpty(integerStringMap.get(1))) {
                excelIdNo.add(integerStringMap.get(1));
            }
            for (Integer index : integerStringMap.keySet()) {
                cellItem = new SchedulingDataInfo();
                if (index<5){
                    cellItem.setName(StringUtils.isEmpty(integerStringMap.get(index))? "":integerStringMap.get(index));
                    cellItem.setIndex(index);
                    cellList.add(cellItem);
//                    data.add(item);
                    continue;
                }
                cellItem.setName(StringUtils.isEmpty(integerStringMap.get(index))? "":integerStringMap.get(index));
                cellItem.setType("select");
                String indexName = oneHeaders.get(index);
                if (index>4) {
                    int num = index - 4;
                    int yu = num % 2;
                    int yu2 = num % 4;
                    if (yu2 == 1 || yu2 == 2) {
                        cellItem.setSchStartDate(indexName+"-01");
                        cellItem.setSchEndDate(indexName+"-15");
                        cellItem.setConcatMon(indexName+"上");
                    }
                    if (yu2 == 3 || yu2 == 0) {
                        cellItem.setSchStartDate(indexName+"-16");
                        cellItem.setSchEndDate(DateUtil.formatDate(DateUtil.endOfMonth(DateUtil.parseDate(indexName+"-01"))));
                        cellItem.setConcatMon(indexName+"下");
                    }
                    if (yu==1){
                        //标准科室下拉列表
                        indexName = indexName + "_标准科室";
                        SchRotationDept bzDeptId = getBzDeptId(speName, integerStringMap.get(index));
                        if (null == bzDeptId) {
                            cellItem.setId("--");
                        }else {
                            cellItem.setId(bzDeptId.getStandardDeptId());
                            cellItem.setIsRequired(bzDeptId.getIsRequired());
                        }
                        cellItem.setSelectData(getBzSelect(speName));
                        cellItem.setIndexName(indexName);
                        cellItem.setDeptType("bz");
                    }else{
                        //实际科室的下拉列表
                        indexName = indexName + "_轮转科室";
                        cellItem.setId(getLzDeptId(integerStringMap.get(index)));
                        cellItem.setSelectData(getLzSelect());
                        cellItem.setIndexName(indexName);
                        cellItem.setDeptType("lz");
                    }
                }
                cellList.add(cellItem);
            }
            row.setCellData(cellList);
            data.add(row);
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (StringUtils.isEmpty(headMap.get(0))) {
            log.info("第一行表头：：{}",headMap.size());
            this.twoHeaders = headMap.values().stream().collect(Collectors.toList());
            return;
        }
        log.info("第二行表头：：{}",headMap.size());
        this.oneHeaders = headMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public void setUserList(List<SysUser> userList) {
        this.userList = userList;
        if (CollectionUtil.isEmpty(userList)) {
            return;
        }
        this.userMap =  userList.stream().collect(Collectors.toMap(e -> e.getUserName() + "_" + e.getIdNo(), SysUser::getUserFlow, (k1, k2) -> k2));
    }

    private List<SelectItem> getBzSelect(String speName){
        List<SelectItem> result = new ArrayList<>();
        if (StringUtils.isEmpty(speName)) {
            return result;
        }
        List<SchRotationDept> schRotationDepts = this.bzMap.get(speName);
        if (CollectionUtil.isEmpty(schRotationDepts)) {
            return result;
        }
        SelectItem val = new SelectItem();
        for (SchRotationDept item : schRotationDepts) {
            val = new SelectItem();
            val.setValue(item.getStandardDeptId());
            val.setLabel(item.getStandardDeptName());
            result.add(val);
        }
        return result;
    }

    private List<SelectItem> getLzSelect(){
        List<SelectItem> result = new ArrayList<>();
        if (CollectionUtil.isEmpty(this.lzDept)) {
            return result;
        }
        SelectItem val = new SelectItem();
        for (SysDept item : this.lzDept) {
            val = new SelectItem();
            val.setValue(item.getDeptFlow());
            val.setLabel(item.getDeptName());
            result.add(val);
        }
        return result;
    }

    private SchRotationDept getBzDeptId(String speName,String bzDeptName){
        if (StringUtils.isEmpty(speName)) {
            return null;
        }
        List<SchRotationDept> schRotationDepts = this.bzMap.get(speName);
        if (CollectionUtil.isEmpty(schRotationDepts)) {
            return null;
        }
        if (StringUtils.isEmpty(bzDeptName)) {
            return null;
        }
        for (SchRotationDept item : schRotationDepts) {
            if (bzDeptName.equalsIgnoreCase(item.getStandardDeptName())) {
                return item;
            }
        }
        return null;
    }


    private String getLzDeptId(String deptName){
        if (CollectionUtil.isEmpty(this.lzDept)) {
            return "--";
        }
        if (StringUtils.isEmpty(deptName)) {
            return "--";
        }
        for (SysDept item : this.lzDept) {
            if (deptName.equalsIgnoreCase(item.getDeptName())) {
                return item.getDeptFlow();
            }
        }
        return "--";
    }

    /**
     * ~~~~~~~~~溺水的鱼~~~~~~~~
     * @Author: 吴强
     * @Date: 2024/10/28 15:25
     * @Description: getData的时候对标准科室的轮转时长进行校验
     */
    public List<SchedulingDataModel> getData(boolean checkSchMon,
                                             String minMonthCheck,
                                             String minMonth) {
        if (CollectionUtil.isEmpty(data)) {
            return data;
        }
        this.arrangeResults = new ArrayList<>();
        this.compareList = new ArrayList<>();
        for (SchedulingDataModel item : data) {
            item.setTip("");
            List<SchedulingDataInfo> cellData = item.getCellData();
            cellData.get(2).setDisable(true);
            cellData.get(3).setDisable(true);
            cellData.get(4).setDisable(true);
            if (CollectionUtil.isEmpty(cellData)) {
                continue;
            }
            String name = cellData.get(0).getName();
            cellData.get(0).setDisable(true);
            if (StringUtils.isEmpty(name)) {
                item.setTip(item.getTip()+"学员姓名不能为空！<br/>");
                cellData.get(0).setDisable(false);
                continue;
            }
            String idNO = cellData.get(1).getName();
            cellData.get(1).setDisable(true);
            if (StringUtils.isEmpty(idNO)) {
                item.setTip(item.getTip()+"身份证号不能为空！<br/>");
                cellData.get(1).setDisable(false);
                continue;
            }
            String userId = userMap.get(name + "_" + idNO);
            if (StringUtils.isEmpty(userId)) {
                item.setTip(item.getTip()+"学员不存在，请检查【学员姓名】、【身份证号】是否正确！<br/>");
                cellData.get(0).setDisable(false);
                cellData.get(1).setDisable(false);
                continue;
            }
            item.setId(userId);
            String speName = "";
            String sessionNumber = "";
            String trainYear = "";
            for (SchedulingDataInfo cellItem : cellData) {
                String indexName = cellItem.getIndexName();
                if ("专业".equals(indexName)) {
                    speName = cellItem.getName();
                }
                if ("年级".equals(indexName)) {
                    sessionNumber = cellItem.getName();
                }
                if ("年限".equals(indexName)) {
                    trainYear = cellItem.getName();
                }
            }
            if (StringUtils.isEmpty(speName)) {
                item.setTip(item.getTip()+"专业不能为空！请重新导入！<br/>");
                continue;
            }
            if (StringUtils.isEmpty(sessionNumber)) {
                item.setTip(item.getTip()+"年级不能为空！请重新导入！<br/>");
            }
            if (StringUtils.isEmpty(trainYear)) {
                item.setTip(item.getTip()+"年限不能为空！请重新导入！<br/>");
            }
            //判断学员专业是否有正确
            if (CollectionUtil.isNotEmpty(doctorMap)) {
                ResDoctor doctor = doctorMap.get(userId);
                String trainingSpeName = doctor.getTrainingSpeName();
                if (StringUtils.isNotEmpty(trainingSpeName)) {
                    trainingSpeName = StringUtils.replace(trainingSpeName,"（","");
                    trainingSpeName = StringUtils.replace(trainingSpeName,"）","");
                    trainingSpeName = StringUtils.replace(trainingSpeName,"(","");
                    trainingSpeName = StringUtils.replace(trainingSpeName,")","");
                }
                String sessionNumber1 = doctor.getSessionNumber();
                String trainingYears = doctor.getTrainingYears();
                if (!speName.equalsIgnoreCase(trainingSpeName)) {
                    //专业不对
                    item.setTip(item.getTip()+"学员专业有误，请修改后重新导入，系统识别到的专业为【"+trainingSpeName+"】！<br/>");
                }
                if (StringUtils.isNotEmpty(sessionNumber1)) {
                    if (!sessionNumber1.equalsIgnoreCase(sessionNumber)) {
                        item.setTip(item.getTip()+"学员【年级】有误，请修改后重新导入，系统识别到的年级为【"+sessionNumber1+"】！<br/>");
                    }
                }
                if (StringUtils.isNotEmpty(trainingYears)) {
                    trainingYears = trainingYears.equalsIgnoreCase("OneYear")? "一年":trainingYears;
                    trainingYears = trainingYears.equalsIgnoreCase("TwoYear")? "两年":trainingYears;
                    trainingYears = trainingYears.equalsIgnoreCase("ThreeYear")? "三年":trainingYears;
                    if (!trainingYears.equalsIgnoreCase(trainYear)) {
                        item.setTip(item.getTip()+"学员【年限】有误，请修改后重新导入，系统识别到的年限为【"+trainingYears+"】！<br/>");
                    }
                }

            }

            //获取方案下的各个标准科室的轮转时长 标准科室名-配置的轮转时长
            Map<String, Double> bzSchMon = schMonStandDeptMap(item, speName);
            if (CollectionUtil.isEmpty(bzSchMon)) {
                item.setTip(item.getTip()+"【"+speName+"】专业下的最新方案暂未配置轮转方案！<br/>");
            }
            //先把导入的数据转型
            double schMinmon = 0;
            for (int i = 5; i < cellData.size(); i++) {
                SchedulingDataInfo cellItem = cellData.get(i);
                if ("lz".equalsIgnoreCase(cellItem.getDeptType())) {
                    int bzindex = i - 1;
                    SchedulingDataInfo schedulingDataInfo = cellData.get(bzindex);
                    PbInfoItem byCellItem = getByCellItem(cellItem, schedulingDataInfo, item.getId(), cellData.get(0).getName(),cellData.get(3).getName());
                    if (null != byCellItem) {
                        this.compareList.add(byCellItem);
                        String schMonth = byCellItem.getSchMonth();
                        schMinmon += Double.valueOf(schMonth);
                    }

                }
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(minMonthCheck)) {
                if (StringUtils.isEmpty(minMonth)) {
                    minMonthCheck = "1";
                }
                if (schMinmon < ((Integer.valueOf(minMonth))*12)){
                    item.setTip(item.getTip()+"本次排班总时长不能低于12个月！<br/>");
                }
            }

            //根据学员的历史排班和当前排班，比较处理是否覆盖
            //1.不存在历史排班的情况下
//            if (CollectionUtil.isEmpty(this.stuResultList)) {
//                Map<String, Double> stuSchMon = initSchArrangeResultNoHistory(item);
//                if (CollectionUtil.isEmpty(stuSchMon)) {
//                    item.setTip(item.getTip()+"暂无排班信息！<br/>");
//                }
//                for (String bzName : stuSchMon.keySet()) {
//                    //获取方案配置的标准科室bzName的轮转时长
//                    Double v = bzSchMon.get(bzName);
//                    //本次排班的时长
//                    Double v1 = stuSchMon.get(bzName);
//                    if (v1>v) {
//                        //本次排班的时长超出了方案配置的时长
//                        item.setTip(item.getTip()+"标准科室【"+bzName+"】的排班时长超出方案配置的轮转时长！<br/>");
//                    }
//                }
//                continue;
//            }
            //存在历史排班信息，但是该学员的轮转数据没有
            Map<String, List<SchArrangeResult>> collect = this.stuResultList.stream().collect(Collectors.groupingBy(SchArrangeResult::getDoctorFlow));
            List<SchArrangeResult> stuHistoryBb = collect.get(item.getId());
//            if (CollectionUtil.isEmpty(stuHistoryBb)) {
//                Map<String, Double> stuSchMon = initSchArrangeResultNoHistory(item);
//                if (CollectionUtil.isEmpty(stuSchMon)) {
//                    item.setTip(item.getTip()+"暂无排班信息！<br/>");
//                }
//                for (String bzName : stuSchMon.keySet()) {
//                    //获取方案配置的标准科室bzName的轮转时长
//                    Double v = bzSchMon.get(bzName);
//                    //本次排班的时长
//                    Double v1 = stuSchMon.get(bzName);
//                    if (v1>v) {
//                        //本次排班的时长超出了方案配置的时长
//                        item.setTip(item.getTip()+"标准科室【"+bzName+"】的排班时长超出方案配置的轮转时长！<br/>");
//                    }
//                }
//                continue;
//            }

            //stuHistoryBb不为空，这个学员存在历史的排班数据
            //再把历史数据转型
            getByCellItemHistory(stuHistoryBb);
//            if (!checkSchMon) {
//                continue;
//            }
//            //对比校验数据，回填异常数据信息（轮转时长是否超出限制）
//            Map<String, Double> stringDoubleMap = checkSchMon(item);
//            for (String bzName : stringDoubleMap.keySet()) {
//                if (StringUtils.isEmpty(bzName)) {
//                    continue;
//                }
//                Double v = bzSchMon.get(bzName);
//                if (null == v || v <=0) {
//                    item.setTip(item.getTip()+"方案中的标准科室【"+bzName+"】暂未设置轮转时长！<br/>");
//                    continue;
//                }
//                Double v1 = stringDoubleMap.get(bzName);
//                if (null == v1 || v1 <=0) {
//                    continue;
//                }
//                if (v1>v) {
//                    item.setTip(item.getTip()+"标准科室【"+bzName+"】的排班时长超出方案配置的轮转时长！<br/>");
//                }
//            }
//            data.add(item);
        }
        return data;
    }


    /**
     * ~~~~~~~~~溺水的鱼~~~~~~~~
     * @Author: 吴强
     * @Date: 2024/10/28 16:22
     * @Description: 统计方案中的各个标准科室的轮转时长
     */
    private Map<String, Double> schMonStandDeptMap(SchedulingDataModel row,String speName){
        Map<String, Double> result = new HashMap<>();
        //获取专业下的轮转方案的标准科室以及其轮转时长
        List<SchRotationDept> schRotationDepts = bzMap.get(speName);
        if (CollectionUtil.isEmpty(schRotationDepts)) {
            row.setTip(row.getTip()+"暂未查询到专业【"+speName+"】的轮转方案下的标准科室！<br/>");
            return result;
        }
        //统计该专业下的标准科室的轮转时长（汇总的时长）
        Map<String, List<SchRotationDept>> collect = schRotationDepts.stream().collect(Collectors.groupingBy(SchRotationDept::getStandardDeptName));
        for (String standDeptName : collect.keySet()) {
            List<SchRotationDept> schRotationDepts1 = collect.get(standDeptName);
            if (CollectionUtil.isEmpty(schRotationDepts1)) {
                result.put(standDeptName,Double.valueOf("0"));
                continue;
            }
            Double schMon = Double.valueOf("0");
            for (SchRotationDept val : schRotationDepts1) {
                String schMonth = val.getSchMonth();
                String schMaxMonth = val.getSchMaxMonth();
                if (StringUtils.isEmpty(schMonth) || "0".equalsIgnoreCase(schMonth)){
                    if (StringUtils.isEmpty(schMaxMonth)) {
                        schMonth = "0";
                    }else {
                        schMonth = schMaxMonth;
                    }
                }else {
                    if (StringUtils.isNotEmpty(schMaxMonth)) {
                        Double v = Double.valueOf(schMaxMonth);
                        Double v1 = Double.valueOf(schMonth);
                        if (v>=v1) {
                            schMonth = schMaxMonth;
                        }
                    }
                }
                Double v = StringUtils.isEmpty(schMonth) ? Double.valueOf("0") : Double.valueOf(schMonth);
                schMon += v;
            }
            result.put(standDeptName,schMon);
        }
        return result;
    }


    /**
     * ~~~~~~~~~溺水的鱼~~~~~~~~
     * @Author: 吴强
     * @Date: 2024/10/28 16:23
     * @Description: 统计各个学员的已排班+本次导入的排班（标准科室名-轮转时长）
     */
    private Map<String, Double> schMonStudentDeptMap(SchedulingDataModel row,String speName){
        Map<String, Double> result = new HashMap<>();
        //该行的用户id
        String userFlow = row.getId();
        //该学员导入的排班安排
        List<SchedulingDataInfo> cellData1 = row.getCellData();
        if (CollectionUtil.isEmpty(cellData1) || cellData1.size()<=5) {
            //本次导入，该行没有安排排班数据
            return result;
        }
        if (CollectionUtil.isEmpty(stuResultList)) {
            //如果没有历史排班信息
            List<SchedulingDataInfo> cellData = row.getCellData();
            for (SchedulingDataInfo item : cellData) {
                if ("bz".equalsIgnoreCase(item.getDeptType())) {
                    result.put(item.getName(),null == result.get(item.getName())? 0.5:result.get(item.getName())+0.5);
                }
            }
            return result;
        }
        //存在历史排班的情况下，需要对每个标准科室进行校验
        String s = this.oneHeaders.get(5);
        DateTime startDate = DateUtil.beginOfMonth(DateUtil.parseDate(s + "-01"));
        for (SchedulingDataInfo item : cellData1) {
//            initSchArrangeResult(item,startDate);
        }

        return result;
    }




    //没有历史排班的情况下
    private Map<String,Double> initSchArrangeResultNoHistory(SchedulingDataModel row){
        Map<String, Double> result = new HashMap<>();
        List<SchedulingDataInfo> cellData = row.getCellData();
        if (CollectionUtil.isEmpty(cellData)) {
            return result;
        }
        for (SchedulingDataInfo item : cellData) {
            if ("bz".equalsIgnoreCase(item.getDeptType())) {
                if (StringUtils.isEmpty(item.getId()) || "--".equalsIgnoreCase(item.getId())) {
                    continue;
                }
                result.put(item.getName(),null == result.get(item.getName())? 0.5:result.get(item.getName())+0.5);
            }
        }
        return result;
    }

    //没有历史排班的情况下
    private void initSchArrangeResultWithHistory(SchedulingDataModel row,
                                                               List<SchArrangeResult> stuHistoryBb){
        List<SchedulingDataInfo> cellData = row.getCellData();
        if (CollectionUtil.isEmpty(cellData)) {
            row.setTip(row.getTip()+"暂无排班信息！<br/>");
            return;
        }
        Map<String, List<SchedulingDataInfo>> collect = cellData.stream().collect(Collectors.groupingBy(SchedulingDataInfo::getDeptType));
        List<SchedulingDataInfo> bz = collect.get("bz");
        if (CollectionUtil.isEmpty(bz)) {
            row.setTip(row.getTip()+"暂无排班信息！<br/>");
            return;
        }
        String startDate = "";
        if (cellData.size()>5) {
            startDate = cellData.get(5).getSchStartDate();
        }
        List<SchArrangeResult> resOld = new ArrayList<>();
        List<SchArrangeResult> resAfter = new ArrayList<>();
        startDate=startDate+"-01";
        try{
            DateTime start = DateUtil.beginOfMonth(DateUtil.parseDate(startDate));
            //将历史排班按照开始时间升序
            List<SchArrangeResult> list = stuHistoryBb.stream().sorted(Comparator.comparing(SchArrangeResult::getSchStartDate)).collect(Collectors.toList());
            //对历史排班进行排序
            for (SchArrangeResult vo : list) {
                String schStartDate = vo.getSchStartDate();
                String schEndDate = vo.getSchEndDate();
                if (StringUtils.isEmpty(schStartDate) || StringUtils.isEmpty(schEndDate)) {
                    continue;
                }
                DateTime s = DateUtil.parseDate(schStartDate);
                DateTime e = DateUtil.parseDate(schEndDate);
                if (e.compareTo(start)<0) {
                    //结束时间小于本次排班最开始的时间
                    resOld.add(vo);
                    continue;
                }
                if (s.compareTo(start)<=0) {
                    //历史排班和本次排班最开始的部分存在重合的部分
                    vo.setSchEndDate(DateUtil.formatDate(DateUtil.offsetDay(start,-1)));
                    long dayNum = DateUtil.betweenDay(s, DateUtil.parseDate(vo.getSchEndDate()), false);
                    BigDecimal divide = new BigDecimal(String.valueOf(dayNum)).divide(new BigDecimal("30"), 1, BigDecimal.ROUND_HALF_DOWN);
                    vo.setSchMonth(String.valueOf(divide));
                    resOld.add(vo);
                    continue;
                }
                resAfter.add(vo);
            }
        }catch (Exception e) {
            return;
        }
        //存在历史排班记录的i情况下
//        compareAfterAndImport(resAfter,bz,row);

    }



//    private List<SchArrangeResult> compareAfterAndImport(List<SchArrangeResult> afterList,
//                                                         List<SchedulingDataInfo> bz,
//                                                         SchedulingDataModel row){
//        List<SchArrangeResult> result = new ArrayList<>();
//        boolean pass = true;
//        for (SchArrangeResult schArrangeResult : afterList) {
//            String schStartDate = schArrangeResult.getSchStartDate();
//            String schEndDate = schArrangeResult.getSchEndDate();
//            String substring = StringUtils.substring(schStartDate, -2);
//            if (!"01".equalsIgnoreCase(substring) && !"15".equalsIgnoreCase(substring)) {
//                DateTime dateTime = DateUtil.endOfMonth(DateUtil.parseDate(schStartDate));
//                String s = DateUtil.formatDate(dateTime);
//                if(!StringUtils.substring(s,-2).equalsIgnoreCase(substring)) {
//                    pass = false;
//                    continue;
//                }
//            }
//            String endSub = StringUtils.substring(schEndDate, -2);
//            if (!"01".equalsIgnoreCase(endSub) && !"15".equalsIgnoreCase(endSub)) {
//                DateTime dateTime = DateUtil.endOfMonth(DateUtil.parseDate(schEndDate));
//                String s = DateUtil.formatDate(dateTime);
//                if(!StringUtils.substring(s,-2).equalsIgnoreCase(endSub)) {
//                    pass = false;
//                    continue;
//                }
//            }
//        }
//        if (!pass) {
//            //排班不需要校验了，直接覆盖吧
//            SchArrangeResult res = new SchArrangeResult();
//            for (SchedulingDataInfo schedulingDataInfo : bz) {
//                res = new SchArrangeResult();
//                res.setArrangeFlow(PkUtil.getUUID());
//                res.setSchStartDate(schedulingDataInfo.getSchStartDate());
//                res.setSchEndDate(schedulingDataInfo.getSchEndDate());
//                res.setDoctorFlow(row.getId());
//                res.setSc
//            }
//        }
//        SchArrangeResult vo = new SchArrangeResult();
//        for (SchedulingDataInfo item : bz) {
//            vo = new SchArrangeResult();
//            String schStartDate = item.getSchStartDate();
//            String schEndDate = item.getSchEndDate();
//
//        }
//    }

    private PbInfoItem getByCellItem(SchedulingDataInfo item,
                                     SchedulingDataInfo bzItem,
                                     String doctorFlow,
                                     String doctorName,
                                     String sessionNumber){
        if (!"lz".equalsIgnoreCase(item.getDeptType())) {
            return null;
        }
        if (StringUtils.isEmpty(item.getId()) || "--".equalsIgnoreCase(item.getId())) {
            return null;
        }
        if (CollectionUtil.isEmpty(doctorMap)) {
            return null;
        }
        if (StringUtils.isEmpty(doctorFlow)) {
            return null;
        }

        ResDoctor doctor = doctorMap.get(doctorFlow);
        if (ObjectUtil.isEmpty(doctor)) {
            return null;
        }
        PbInfoItem result = new PbInfoItem();
        String schDeptFlow = item.getId();
        String schDeptName = item.getName();
        String schStartDate = item.getSchStartDate();
        String schEndDate = item.getSchEndDate();
        String concatMon = item.getConcatMon();
        result.setResultFlow(PkUtil.getUUID());
        result.setType("import");
        result.setConcatMon(concatMon);
        result.setSchDeptFlow(schDeptFlow);
        result.setSchDeptName(schDeptName);
        result.setStandardDeptId(bzItem.getId());
        result.setStandardDeptName(bzItem.getName());
        result.setSchStartDate(schStartDate);
        result.setSchEndDate(schEndDate);
        result.setDoctorFlow(doctorFlow);
        result.setDoctorName(doctorName);
        result.setSessionNumber(doctor.getSessionNumber());
        result.setRotationFlow(doctor.getRotationFlow());
        result.setRotationName(doctor.getRotationName());
        return result;
    }

    private void getByCellItemHistory(List<SchArrangeResult> historyList){
        if (CollectionUtil.isEmpty(historyList)) {
            return;
        }
        PbInfoItem item = new PbInfoItem();
        for (SchArrangeResult vo : historyList) {
            item = new PbInfoItem();
            BeanUtil.copyProperties(vo,item);
            item.setType("db");
            item.getConcatMon();
            this.compareList.add(item);
        }
    }

    private Map<String, Double> checkSchMon(SchedulingDataModel row){
        Map<String, Double> result = new HashMap<>();
        String userFlow = row.getId();
        List<SchedulingDataInfo> cellData = row.getCellData();
        if (StringUtils.isEmpty(userFlow) || CollectionUtil.isEmpty(cellData)) {
            return result;
        }
        if (CollectionUtil.isEmpty(compareList)) {
            return result;
        }
        Map<String, List<PbInfoItem>> doctorCompare = compareList.stream().collect(Collectors.groupingBy(PbInfoItem::getDoctorFlow));
        List<PbInfoItem> pbInfoItems = doctorCompare.get(userFlow);
        if (CollectionUtil.isEmpty(pbInfoItems)) {
            return result;
        }
        for (int i = 0; i < cellData.size(); i++) {
            if (i<5) {
                continue;
            }
            int yu = i % 2;
            if (yu == 1) {
                continue;
            }
            SchedulingDataInfo lunzhuan = cellData.get(i);
            SchedulingDataInfo biaozhun = cellData.get(i - 1);
            String concatMon = lunzhuan.getConcatMon();
            if (StringUtils.isEmpty(lunzhuan.getName()) || "--".equalsIgnoreCase(lunzhuan.getName())) {
                continue;
            }
            if (StringUtils.isEmpty(lunzhuan.getId()) || "--".equalsIgnoreCase(lunzhuan.getId())) {
                continue;
            }
            List<PbInfoItem> db = compareList.stream().filter(e -> e.getConcatMon().contains(concatMon) && e.getType().equalsIgnoreCase("db")).collect(Collectors.toList());
            if (CollectionUtil.isEmpty(db)) {
                //该月份没有历史排班
                result.put(biaozhun.getName(),null == result.get(biaozhun.getName())? 0.5:result.get(biaozhun.getName())+0.5);
            }else {
                PbInfoItem pbInfoItem = db.get(0);
                //校验这个历史排班下有没有学员数据
                String resultFlow = pbInfoItem.getResultFlow();
                Integer i1 = resRecList.get(resultFlow);
                if (null == i1 || i1==0) {
                    //没有学员提交历史记录，可以替换
                    result.put(biaozhun.getName(),null == result.get(biaozhun.getName())? 0.5:result.get(biaozhun.getName())+0.5);
                }
//                else {
//                    //存在学员提交记录，不允许替换
//                    result.put(pbInfoItem.getStandardDeptName(),null == result.get(pbInfoItem.getStandardDeptName())? 0.5:result.get(pbInfoItem.getStandardDeptName())+0.5);
//                }
            }
        }

        return result;
    }

    public List<PbInfoItem> getCompareList() {
        if (CollectionUtil.isEmpty(compareList)) {
            return compareList;
        }
        List<PbInfoItem> importList = compareList.stream().filter(e -> "import".equals(e.getType())).collect(Collectors.toList());
        List<PbInfoItem> dbList = compareList.stream().filter(e -> "db".equals(e.getType())).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(importList)) {
            return compareList;
        }
        List<PbInfoItem> result = new ArrayList<>();
        for (PbInfoItem item : importList) {
            String type = item.getType();
            String doctorFlow = item.getDoctorFlow();
            if ("db".equals(type) || item.getRecordStatus().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
                continue;
            }
            DateTime itemEnd = DateUtil.parseDate(item.getSchEndDate());
            DateTime itemStart = DateUtil.parseDate(item.getSchStartDate());
            String itemDeptFlow = item.getSchDeptFlow();
            for (PbInfoItem vo : importList) {
                if ("db".equals(vo.getType()) || vo.getRecordStatus().equals(com.pinde.core.common.GlobalConstant.FLAG_N) || !doctorFlow.equals(vo.getDoctorFlow())) {
                    continue;
                }
                DateTime voEnd = DateUtil.parseDate(vo.getSchEndDate());
                DateTime voStart = DateUtil.parseDate(vo.getSchStartDate());
                String voDeptFlow = vo.getSchDeptFlow();
                if (itemDeptFlow.equals(voDeptFlow)) {
                    if (itemStart.compareTo(voEnd)==0 || itemStart.compareTo(DateUtil.offsetDay(voEnd,1)) == 0) {
                        item.setSchStartDate(vo.getSchStartDate());
                        vo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
                    }
                }
            }
        }

        if (CollectionUtil.isNotEmpty(importList)) {
            List<PbInfoItem> collect = importList.stream().filter(e -> com.pinde.core.common.GlobalConstant.FLAG_Y.equals(e.getRecordStatus())).collect(Collectors.toList());
            result.addAll(collect);
        }
        if (CollectionUtil.isNotEmpty(dbList)) {
            result.addAll(dbList);
        }
        return result;
    }
}
