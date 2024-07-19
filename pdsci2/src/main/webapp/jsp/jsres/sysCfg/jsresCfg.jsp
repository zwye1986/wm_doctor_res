<script type="text/javascript">
    //异步
    function jboxPost(posturl,postdata,funcOk,funcErr,showResp){
        $.ajax({
            type : "post",
            url : posturl,
            data : postdata,
            cache : false,
            beforeSend : function(){
                jboxStartLoading();
            },
            success : function(resp) {
                jboxEndLoading();
                if(showResp==false){

                }else{
                    jboxTip(resp);
                }
                if(funcOk!=null){
                    funcOk(resp);
                }
            },
            error : function() {
                jboxEndLoading();
                jboxTip("操作失败,请刷新页面后重试");
                if(funcErr!=null){
                    funcErr();
                }
            },
            complete : function(){
                jboxEndLoading();
            }
        });
    }
    function save() {

        if(false==$("#saveCfgForm").validationEngine("validate")){
            return ;
        }
        if('recruitCfg'=='${param.tagId}' && !compareDate("jsres_is_apply")){
            jboxTip("规培开始日期不能大于规培结束日期");
            return;
        }
        <%--if('recruitCfg'=='${param.tagId}' && !compareDate("jsres_is_check")){--%>
        <%--    jboxTip("审核开始日期不能大于审核结束日期");--%>
        <%--    return;--%>
        <%--}--%>
        var url = "<s:url value='/jsres/sysCfg/save'/>";
        var data = $('#saveCfgForm').serialize();
        jboxPost(url, data, function() {
            $(".tab_select").click();
        });
    }
    function formatNumbers(obj){
        var re = (/^[1-9]\d*$/).test(obj.value) ;
        if(!re)
        {
            $('#'+obj.id).val("")
            jboxTip("限制数据请输入非0正整数！");
            return false;
        }
    }
    function formatNumbers2(obj){
        var objVal = $('#'+obj.id).val();
        if(objVal!=null&&objVal!=''){
            var re = (/^[1-9]\d*$/).test(obj.value) ;
            if(!re)
            {
                $('#'+obj.id).val("")
                jboxTip("限制日期请输入非0正整数！");
                return false;
            }
        }
    }
    function saveSetting() {
        if(false==$("#saveCfgForm").validationEngine("validate")){
            return ;
        }
        var checkedState = $('input[name=student_timeout_login_limit_checked]:checked').val();
        if(checkedState != "" && checkedState == "N"){
            $("#loginLimitValue").val("N");
        } else {
            var re = (/^[1-9]\d*$/).test($("#loginLimitValue").val()) ;
            if(!re)
            {
                jboxTip("月份请输入非0正整数！");
                $('#loginLimitValue').val("")
                return false;
            }
        }
        /*var checkeDataState = $('input[name=student_fill_in_data_checked]:checked').val();
        if(checkeDataState != "" && checkeDataState == "N"){
            $("#inDataValue").val("N");
        } else{
            var re = (/^[1-9]\d*$/).test($("#inDataValue").val()) ;
            if(!re)
            {
                jboxTip("日期请输入非0正整数！");
                $('#inDataValue').val("")
                return false;
            }
        }*/
        var url = "<s:url value='/jsres/sysCfg/save'/>";
        var data = $('#saveCfgForm').serialize();
        jboxPost(url, data, function() {
            $(".tab_select").click();
        });
    }

    function loginLimitCheck(type) {
        if(type == 'Y'){
            $('#loginLimitValue').val("");
            $("#loginLimit").show();
        } else {
            $("#loginLimit").hide();
        }
    }
    function inDataCheck(type) {
        if(type == 'Y'){
            $("#inData").show();
        } else {
            $("#inData").hide();
        }
    }
    $(document).ready(function () {

        <c:if test="${'graduationScoreCfg'==param.tagId }">
            $('#cfgYear').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode:2,
                format:'yyyy'
            });
            toPage();
        </c:if>
    });

    function toPage(page){
        if(page != undefined){
            $("#currentPage").val(page);
        }
        searchCfgInfo();
    }

    function searchCfgInfo(){
        <%--var url ="<s:url value='/jsres/doctor/accountList'/>";--%>
        var url =" <s:url value='/jsres/base/loadScoreConf'/>";
        jboxPostLoad("contentDiv", url, $("#searchForm").serialize(), true);
    }
    var width  = 400;
    var height =200;
    //添加
    function setScoreConf(){
        var url="<s:url value='/jsres/base/setScoreConf'/>";
        jboxOpen(url,"添加配置信息",width,height);
    }
    //编辑
    function editCfgInfo(cfgYear){
        var url="<s:url value='/jsres/base/setScoreConf?cfgYear='/>"+cfgYear;
        jboxOpen(url,"编辑配置信息",width,height);
    }
    //删除
    function delCfgInfo(cfgYear){
        jboxConfirm("确认删除此条记录?", function(){
            var url="<s:url value='/jsres/base/delScoreConf?cfgYear='/>"+cfgYear;
            jboxPost(url, null, function(resp){
                if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                    searchCfgInfo();
                }
            }, null, false);
        });
    }

    function compareDate(id) {
        var startTime = $("#" + id + "_start").val();
        var endTime = $("#" + id + "_end").val();
        if (startTime.length > 0 && endTime.length > 0) {
            var startTmp = startTime.split("-");
            var endTmp = endTime.split("-");
            var sd = new Date(startTmp[0], startTmp[1], startTmp[2]);
            var ed = new Date(endTmp[0], endTmp[1], endTmp[2]);
            if (sd.getTime() > ed.getTime()) {
                jboxTip("开始日期不能大于结束日期");
                return false;
            }
        }
        return true;
    }
</script>
<body>

<div class="main_bd">
    <c:if test="${'recruitCfg'==param.tagId }">
        <form id="saveCfgForm">
            <div class="div_table">
                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                    <thead>
                    <tr>
                        <th width="50%" style="text-align: center;">配置项</th>
                        <th width="50%" style="text-align: center;">配置内容</th>
                    </tr>
                    </thead>
                    <tr>
                        <td style="text-align: center;">是否开放注册按钮：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_is_register">
                            &nbsp;
                            <input type="radio" id="jsres_is_register_y" name="jsres_is_register" value="Y"
                                   <c:if test="${sysCfgMap['jsres_is_register'] eq 'Y'}">checked</c:if>/><label
                                for="jsres_is_register_y">是</label>&#12288;
                            <input type="radio" id="jsres_is_register_n" name="jsres_is_register" value="N"
                                   <c:if test="${sysCfgMap['jsres_is_register'] eq 'N'}">checked</c:if>/><label
                                for="jsres_is_register_n">否</label>
                            <input type="hidden" name="jsres_is_register_ws_id" value="res">
                            <input type="hidden" name="jsres_is_register_desc" value="是否允许注册">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">是否允许学员提交报名信息：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_is_train">
                            &nbsp;
                            <input type="radio" id="jsres_is_train_y" name="jsres_is_train" value="Y"
                                   <c:if test="${sysCfgMap['jsres_is_train'] eq 'Y'}">checked</c:if>/><label
                                for="jsres_is_train_y">是</label>&#12288;
                            <input type="radio" id="jsres_is_train_n" name="jsres_is_train" value="N"
                                   <c:if test="${sysCfgMap['jsres_is_train'] eq 'N'}">checked</c:if>/><label
                                for="jsres_is_train_n">否</label>
                            <input type="hidden" name="jsres_is_train_ws_id" value="res">
                            <input type="hidden" name="jsres_is_train_desc" value="是否允许报名">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">是否允许医院审核学员报名：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_is_hospital_audit">
                            &nbsp;
                            <input type="radio" id="jsres_is_hospital_audit_y" name="jsres_is_hospital_audit" value="Y"
                                   <c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'Y'}">checked</c:if>/><label
                                for="jsres_is_hospital_audit_y">是</label>&#12288;
                            <input type="radio" id="jsres_is_hospital_audit_n" name="jsres_is_hospital_audit" value="N"
                                   <c:if test="${sysCfgMap['jsres_is_hospital_audit'] eq 'N'}">checked</c:if>/><label
                                for="jsres_is_hospital_audit_n">否</label>
                            <input type="hidden" name="jsres_is_hospital_audit_ws_id" value="res">
                            <input type="hidden" name="jsres_is_hospital_audit_desc" value="是否允许医院审核学生报名">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">是否允许医院审核学员报到：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_is_hospital_audit_register">
                            &nbsp;
                            <input type="radio" id="jsres_is_hospital_audit_register_y" name="jsres_is_hospital_audit_register" value="Y"
                                   <c:if test="${sysCfgMap['jsres_is_hospital_audit_register'] eq 'Y'}">checked</c:if>/><label
                                for="jsres_is_hospital_audit_register_y">是</label>&#12288;
                            <input type="radio" id="jsres_is_hospital_audit_register_n" name="jsres_is_hospital_audit_register" value="N"
                                   <c:if test="${sysCfgMap['jsres_is_hospital_audit_register'] eq 'N'}">checked</c:if>/><label
                                for="jsres_is_hospital_audit_register_n">否</label>
                            <input type="hidden" name="jsres_is_hospital_audit_register_ws_id" value="res">
                            <input type="hidden" name="jsres_is_hospital_audit_register_desc" value="是否允许医院审核学生报到">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">学员规培起始日期的选择范围：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_is_apply_start">
                            <input type="text" id="jsres_is_apply_start" name="jsres_is_apply_start" value="${sysCfgMap['jsres_is_apply_start']}"
                                   style="width: 100px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="compareDate('jsres_is_apply')" class="input"/>~
                            <input type="hidden" name="jsres_is_apply_start_ws_id" value="res">
                            <input type="hidden" name="jsres_is_apply_start_desc" value="报名开始日期">
                            <input type="hidden" name="cfgCode" value="jsres_is_apply_end">
                            <input type="text" id="jsres_is_apply_end" name="jsres_is_apply_end" value="${sysCfgMap['jsres_is_apply_end']}"
                                   style="width: 100px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="compareDate('jsres_is_apply')" class="input"/>
                            <input type="hidden" name="jsres_is_apply_end_ws_id" value="res">
                            <input type="hidden" name="jsres_is_apply_end_desc" value="报名终止日期">
                        </td>
                    </tr>
<%--                    <tr>--%>
<%--                        <td style="text-align: center;">基地审核功能开放时间：</td>--%>
<%--                        <td style="text-align: left;padding-left: 5px" width="200px">--%>
<%--                            <input type="hidden" name="cfgCode" value="jsres_is_check_start">--%>
<%--                            <input type="text" id="jsres_is_check_start" name="jsres_is_check_start" value="${sysCfgMap['jsres_is_check_start']}"--%>
<%--                                   style="width: 100px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="compareDate('jsres_is_check')" class="input"/>~~&#12288;--%>
<%--                            <input type="hidden" name="jsres_is_check_start_ws_id" value="res">--%>
<%--                            <input type="hidden" name="jsres_is_check_start_desc" value="审核开始日期">--%>
<%--                            <input type="hidden" name="cfgCode" value="jsres_is_check_end">--%>
<%--                            <input type="text" id="jsres_is_check_end" name="jsres_is_check_end" value="${sysCfgMap['jsres_is_check_end']}"--%>
<%--                                   style="width: 100px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onchange="compareDate('jsres_is_check')" class="input"/>--%>
<%--                            <input type="hidden" name="jsres_is_check_end_ws_id" value="res">--%>
<%--                            <input type="hidden" name="jsres_is_check_end_desc" value="审核终止日期">--%>
<%--                        </td>--%>
<%--                    </tr>--%>
                    <tr>
                        <td style="text-align: center;">是否允许市局退回学员报名信息：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_charge_return">
                            &nbsp;
                            <input type="radio" id="jsres_charge_return_y" name="jsres_charge_return" value="Y"
                                   <c:if test="${sysCfgMap['jsres_charge_return'] eq 'Y'}">checked</c:if>/><label
                                for="jsres_charge_return_y">是</label>&#12288;
                            <input type="radio" id="jsres_charge_return_n" name="jsres_charge_return" value="N"
                                   <c:if test="${sysCfgMap['jsres_charge_return'] eq 'N'}">checked</c:if>/><label
                                for="jsres_charge_return_n">否</label>
                            <input type="hidden" name="jsres_charge_return_ws_id" value="res">
                            <input type="hidden" name="jsres_charge_return_desc" value="是否打开市局和省厅退回功能">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">是否打开省厅退回学员报名信息：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_global_return">
                            &nbsp;
                            <input type="radio" id="jsres_global_return_y" name="jsres_global_return" value="Y"
                                   <c:if test="${sysCfgMap['jsres_global_return'] eq 'Y'}">checked</c:if>/><label
                                for="jsres_global_return_y">是</label>&#12288;
                            <input type="radio" id="jsres_global_return_n" name="jsres_global_return" value="N"
                                   <c:if test="${sysCfgMap['jsres_global_return'] eq 'N'}">checked</c:if>/><label
                                for="jsres_global_return_n">否</label>
                            <input type="hidden" name="jsres_global_return_ws_id" value="res">
                            <input type="hidden" name="jsres_global_return_desc" value="是否打开省厅退回功能">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">培训基地审核届别控制功能：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_local_sessionNumber">
                            <input type="text" name="jsres_local_sessionNumber"
                                   value="${sysCfgMap['jsres_local_sessionNumber']}" readonly="readonly"
                                   style="width: 100px;" onclick="WdatePicker({dateFmt:'yyyy'})" class="input"/>
                            <input type="hidden" name="jsres_local_sessionNumber_ws_id" value="res">
                            <input type="hidden" name="jsres_local_sessionNumber_desc" value="培训基地审核界别控制功能">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">培训医师数届别控制：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_doctorCount_sessionNumber">
                            <input type="text" name="jsres_doctorCount_sessionNumber"
                                   value="${sysCfgMap['jsres_doctorCount_sessionNumber']}" readonly="readonly"
                                   style="width: 100px;" onclick="WdatePicker({dateFmt:'yyyy'})" class="input"/>
                            <input type="hidden" name="jsres_doctorCount_sessionNumber_ws_id" value="res">
                            <input type="hidden" name="jsres_doctorCount_sessionNumber_desc" value="培训医师数届别控制">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">是否开启固定招录数据按钮：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_photo_recruitData">
                            &nbsp;
                            <input type="radio" id="jsres_photo_recruitData_y" name="jsres_photo_recruitData" value="Y"
                                   <c:if test="${sysCfgMap['jsres_photo_recruitData'] eq 'Y'}">checked</c:if>/><label
                                for="jsres_photo_recruitData_y">是</label>&#12288;
                            <input type="radio" id="jsres_photo_recruitData_n" name="jsres_photo_recruitData" value="N"
                                   <c:if test="${sysCfgMap['jsres_photo_recruitData'] eq 'N'}">checked</c:if>/><label
                                for="jsres_photo_recruitData_n">否</label>
                            <input type="hidden" name="jsres_photo_recruitData_ws_id" value="res">
                            <input type="hidden" name="jsres_photo_recruitData_desc" value="是否开启固定招录数据按钮">
                        </td>
                    </tr>
                </table>
                <div class="button" >
                    <input type="button" class="btn_green" onclick="save();" value="保&#12288;存">
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${'graduationAuditCfg'==param.tagId }">
    <form id="saveCfgForm">
        <div class="div_table">
            <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                <thead>
                <tr>
                    <th width="50%" style="text-align: center;">配置项</th>
                    <th width="50%" style="text-align: center;" colspan="2">配置内容</th>
                </tr>
                </thead>
                <tr>
                    <td rowspan="2" style="text-align: center;">学员提交审核时间段</td>
                    <td style="text-align: right" width="100px">开始时间：</td>
                    <td style="text-align: left;padding-left: 5px" width="200px">
                        <input type="hidden" name="cfgCode" value="doctor_submit_start_time">
                        <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'doctor_submit_end_time\')}' })"readonly="readonly" class="input" id="doctor_submit_start_time" name="doctor_submit_start_time" value="${sysCfgMap['doctor_submit_start_time']}">
                        <input type="hidden" name="doctor_submit_start_time_desc"  value="学员提交审核开始时间">
                        <input type="hidden" name="doctor_submit_start_time_ws_id"  value="res">
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right" width="100px">结束时间：</td>
                    <td style="text-align: left;padding-left: 5px" width="200px">
                        <input type="hidden" name="cfgCode" value="doctor_submit_end_time">
                        <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'doctor_submit_start_time\')}'})"readonly="readonly" class="input" id="doctor_submit_end_time" name="doctor_submit_end_time" value="${sysCfgMap['doctor_submit_end_time']}">
                        <input type="hidden" name="doctor_submit_end_time_desc"  value="学员提交审核结束时间">
                        <input type="hidden" name="doctor_submit_end_time_ws_id"  value="res">
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center;" rowspan="2">基地审核时间段</td>
                    <td style="text-align: right" width="100px">开始时间：</td>
                    <td style="text-align: left;padding-left: 5px" width="200px">
                        <input type="hidden" name="cfgCode" value="local_submit_start_time">
                        <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'local_submit_end_time\')}' })"readonly="readonly" class="input" id="local_submit_start_time" name="local_submit_start_time" value="${sysCfgMap['local_submit_start_time']}">
                        <input type="hidden" name="local_submit_start_time_desc"  value="基地审核开始时间">
                        <input type="hidden" name="local_submit_start_time_ws_id"  value="res">
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right" width="100px">结束时间：</td>
                    <td style="text-align: left;padding-left: 5px" width="200px">
                        <input type="hidden" name="cfgCode" value="local_submit_end_time">
                        <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'local_submit_start_time\')}'})"readonly="readonly" class="input" id="local_submit_end_time" name="local_submit_end_time" value="${sysCfgMap['local_submit_end_time']}">
                        <input type="hidden" name="local_submit_end_time_desc"  value="基地审核结束时间">
                        <input type="hidden" name="local_submit_end_time_ws_id"  value="res">
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center;" rowspan="2">市局审核时间段</td>
                    <td style="text-align: right" width="100px">开始时间：</td>
                    <td style="text-align: left;padding-left: 5px" width="200px">
                        <input type="hidden" name="cfgCode" value="charge_submit_start_time">
                        <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'charge_submit_end_time\')}' })"readonly="readonly" class="input" id="charge_submit_start_time"name="charge_submit_start_time" value="${sysCfgMap['charge_submit_start_time']}">
                        <input type="hidden" name="charge_submit_start_time_desc"  value="市局审核开始时间">
                        <input type="hidden" name="charge_submit_start_time_ws_id"  value="res">
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right" width="100px">结束时间：</td>
                    <td style="text-align: left;padding-left: 5px" width="200px">
                        <input type="hidden" name="cfgCode" value="charge_submit_end_time">
                        <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'charge_submit_start_time\')}'})"readonly="readonly" class="input" id="charge_submit_end_time" name="charge_submit_end_time" value="${sysCfgMap['charge_submit_end_time']}">
                        <input type="hidden" name="charge_submit_end_time_desc"  value="市局审核结束时间">
                        <input type="hidden" name="charge_submit_end_time_ws_id"  value="res">
                    </td>
                </tr>
                <tr>
                    <td style="text-align: center;" rowspan="2">省厅审核时间段</td>
                    <td style="text-align: right" width="100px">开始时间：</td>
                    <td style="text-align: left;padding-left: 5px" width="200px">
                        <input type="hidden" name="cfgCode" value="global_submit_start_time">
                        <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: '#F{$dp.$D(\'global_submit_end_time\')}' })"readonly="readonly" class="input" id="global_submit_start_time" name="global_submit_start_time" value="${sysCfgMap['global_submit_start_time']}">
                        <input type="hidden" name="global_submit_start_time_desc"  value="省厅审核开始时间">
                        <input type="hidden" name="global_submit_start_time_ws_id"  value="res">
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right" width="100px">结束时间：</td>
                    <td style="text-align: left;padding-left: 5px" width="200px">
                        <input type="hidden" name="cfgCode" value="global_submit_end_time">
                        <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate: '#F{$dp.$D(\'global_submit_start_time\')}' })"readonly="readonly" class="input" id="global_submit_end_time" name="global_submit_end_time" value="${sysCfgMap['global_submit_end_time']}">
                        <input type="hidden" name="global_submit_end_time_desc"  value="省厅审核结束时间">
                        <input type="hidden" name="global_submit_end_time_ws_id"  value="res">
                    </td>
                </tr>
            </table>
            <div class="button" >
                <input type="button" class="btn_green" onclick="save();" value="保&#12288;存">
            </div>
        </div>
    </form>
    </c:if>
    <c:if test="${'graduationScoreCfg'==param.tagId }">
        <script type="text/javascript">

        </script>
        <div class="div_search">
            <form id="searchForm">
                <input type="hidden" name="currentPage" id="currentPage"/>
                年份：<input type="text" class="input" id="cfgYear" name="cfgYear" value="${param.cfgYear}" style="width:100px;"/>&#12288;
                <input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>&#12288;
                <input class="btn_green" type="button" value="添&#12288;加" onclick="setScoreConf()"/>
            </form>
        </div>

        <div class="search_table" id="contentDiv">

        </div>
    </c:if>
    <c:if test="${'graduationDateCfg'==param.tagId }">
        <form id="saveCfgForm">
            <div class="div_table">
                <table class="base_info">
                    <thead>
                    <tr>
                        <th width="50%" style="text-align: center;">配置项</th>
                        <th width="50%" style="text-align: center;">配置内容</th>
                    </tr>
                    </thead>
                    <tr>
                        <td style="text-align: center" width="100px">证书发放日期：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="res_certificateDate">
                            <input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"readonly="readonly" class="input" name="res_certificateDate" value="${sysCfgMap['res_certificateDate']}">
                            <input type="hidden" name="res_certificateDate_ws_id"  value="res">
                            <input type="hidden" name="res_certificateDate_desc"  value="证书发放日期">
                        </td>
                    </tr>
                </table>
                <div class="button" >
                    <input type="button" class="btn_green" onclick="save();" value="保&#12288;存">
                </div>
            </div>
        </form>
    </c:if>
    <%-- 学员过程管理配置 2019年6月10日 add--%>
    <c:if test="${'studentProcessCfg'==param.tagId }">
        <form id="saveCfgForm">
            <div class="div_table">
                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                    <thead>
                    <tr>
                        <th width="40%" style="text-align: center;">配置项</th>
                        <th width="15%" style="text-align: center;">配置内容</th>
                        <th width="45%" style="text-align: center;">配置参数</th>
                    </tr>
                    </thead>
                    <tr>
                        <td style="text-align: center;">是否开启学员自主入科时间校验：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px" colspan="2">
                            <input type="hidden" name="cfgCode" value="jsres_is_process">
                            <input type="radio" id="jsres_is_process_n" name="jsres_is_process" value="N"
                                   <c:if test="${sysCfgMap['jsres_is_process'] eq 'N'}">checked</c:if>/>
                                    <label for="jsres_is_process_y">是</label>&#12288;
                            <input type="radio" id="jsres_is_process_y" name="jsres_is_process" value="Y"
                                   <c:if test="${sysCfgMap['jsres_is_process'] eq 'Y'}">checked</c:if>/>
                                   <label for="jsres_is_process_n">否</label>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">是否允许学员补填数据：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="student_fill_in_data">
                            <input onclick="inDataCheck('Y')"type="radio" id="student_fill_in_data_y"  name="student_fill_in_data" value="Y"
                                   <c:if test="${sysCfgMap['student_fill_in_data'] ne 'N'}">checked</c:if>/>
                            <label for="student_fill_in_data_y">是</label>&#12288;
                            <input onclick="inDataCheck('N')" type="radio" id="student_fill_in_data_n" name="student_fill_in_data" value="N"
                                   <c:if test="${sysCfgMap['student_fill_in_data'] eq 'N'}">checked</c:if>/>
                            <label for="student_fill_in_data_n">否</label>
                        </td>
                        <td>

                            <span <c:if test="${sysCfgMap['student_fill_in_data'] eq 'N'}">style="display:none"</c:if> id="inData">
                                <input type="hidden" name="cfgCode" value="student_fill_in_data_value">
                                超过出科时间<input class="input "  onblur="formatNumbers2(this)" id="inDataValue" value="${sysCfgMap['student_fill_in_data_value']}"  name="student_fill_in_data_value" style="width: 40px;text-align: center;" ></input>
                                <input type="hidden" name="cfgCode" value="student_fill_in_data_type">
                                <select class="select"  name="student_fill_in_data_type">
                                    <option value="1" <c:if test="${sysCfgMap['student_fill_in_data_type'] eq '1'}"> selected = "selected" </c:if> > 日</option>
                                    <option value="2" <c:if test="${sysCfgMap['student_fill_in_data_type'] eq '2'}"> selected = "selected" </c:if> > 月</option>
                                </select>
                                内可补填轮转数据
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: center;">是否开启学员超时登录限制：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="student_timeout_login_limit">
                            <input onclick="loginLimitCheck('Y')"type="radio" id="student_timeout_login_limit_y"  name="student_timeout_login_limit_checked" value="Y"
                                   <c:if test="${sysCfgMap['student_timeout_login_limit'] ne 'N'}">checked</c:if>/>
                                    <label for="student_timeout_login_limit_y">是</label>&#12288;
                            <input onclick="loginLimitCheck('N')" type="radio" id="student_timeout_login_limit_n" name="student_timeout_login_limit_checked" value="N"
                                   <c:if test="${sysCfgMap['student_timeout_login_limit'] eq 'N'}">checked</c:if>/>
                                   <label for="student_timeout_login_limit_n">否</label>
                        </td>
                        <td>
                            <span <c:if test="${sysCfgMap['student_timeout_login_limit'] eq 'N'}">style="display:none"</c:if> id="loginLimit">
                                学员连续<input class="input validate[required]"  id="loginLimitValue" value="${sysCfgMap['student_timeout_login_limit']}" onblur="formatNumbers(this);" name="student_timeout_login_limit" style="width: 40px;text-align: center;" ></input> 月登录APP不填写数据，将无法正常登录APP
                            </span>
                        </td>
                    </tr>
<%--                    <tr>--%>
<%--                        <td style="text-align: center;">一个带教同时可带学员人数设置：</td>--%>
<%--                        <td style="text-align: left;padding-left: 5px" width="200px" colspan="2">--%>
<%--                            <input type="hidden" name="cfgCode" value="student_number_limit">--%>
<%--                            <span id="numberLimit">--%>
<%--                                <input class="input validate[required]"  id="numberLimitValue" value="${sysCfgMap['student_number_limit']}" onblur="formatNumbers(this);" name="student_number_limit" style="width: 40px;text-align: center;" ></input> 人--%>
<%--                            </span>--%>
<%--                        </td>--%>
<%--                    </tr>--%>
                </table>
                <div class="button" >
                    <input type="button" class="btn_green" onclick="saveSetting();" value="保&#12288;存">
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${'fundingReportAudit'==param.tagId }">
        <form id="saveCfgForm">
            <div class="div_table">
                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                    <thead>
                    <tr>
                        <th width="50%" style="text-align: center;">配置项</th>
                        <th width="50%" style="text-align: center;">配置内容</th>
                    </tr>
                    </thead>
                    <tr>
                        <td style="text-align: center;">基地上报的经费使用明细是否需要省厅审核：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="funding_Report_audit">
                            <input type="radio" id="funding_Report_audit_y" name="funding_Report_audit" value="Y"
                                   <c:if test="${sysCfgMap['funding_Report_audit'] eq 'Y'}">checked</c:if>/>
                            <label for="funding_Report_audit_y">是</label>&#12288;
                            <input type="radio" id="funding_Report_audit_n" name="funding_Report_audit" value="N"
                                   <c:if test="${sysCfgMap['funding_Report_audit'] eq 'N'}">checked</c:if>/>
                            <label for = "funding_Report_audit_n">否</label>
                        </td>
                    </tr>
                </table>
                <div class="button" >
                    <input type="button" class="btn_green" onclick="save();" value="保&#12288;存">
                </div>
            </div>
        </form>
    </c:if>

</div>
</body>