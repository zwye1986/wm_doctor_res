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
        var url = "<s:url value='/jszy/sysCfg/save'/>";
        var data = $('#saveCfgForm').serialize();
        jboxPost(url, data, function() {
            $(".tab_select").click();
        });
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
        <%--var url ="<s:url value='/jszy/doctor/accountList'/>";--%>
        var url =" <s:url value='/jszy/base/loadScoreConf'/>";
        jboxPostLoad("contentDiv", url, $("#searchForm").serialize(), true);
    }
    var width  = 400;
    var height =200;
    //添加
    function setScoreConf(){
        var url="<s:url value='/jszy/base/setScoreConf'/>";
        jboxOpen(url,"添加配置信息",width,height);
    }
    //编辑
    function editCfgInfo(cfgYear){
        var url="<s:url value='/jszy/base/setScoreConf?cfgYear='/>"+cfgYear;
        jboxOpen(url,"编辑配置信息",width,height);
    }
    //删除
    function delCfgInfo(cfgYear){
        jboxConfirm("确认删除此条记录?", function(){
            var url="<s:url value='/jszy/base/delScoreConf?cfgYear='/>"+cfgYear;
            jboxPost(url, null, function(resp){
                if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                    searchCfgInfo();
                }
            }, null, false);
        });
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
                        <th width="40%" style="text-align: center;">配置项</th>
                        <th width="60%" style="text-align: center;">配置内容</th>
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
                        <td style="text-align: center;">是否允许医院审核学生报名：</td>
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
                        <td style="text-align: center;">学员规培起始日期的选择范围：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <input type="hidden" name="cfgCode" value="jsres_is_apply_start">
                            <input type="text" name="jsres_is_apply_start" value="${sysCfgMap['jsres_is_apply_start']}"
                                   style="width: 100px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input"/>~~&#12288;
                            <input type="hidden" name="jsres_is_apply_start_ws_id" value="res">
                            <input type="hidden" name="jsres_is_apply_start_desc" value="报名开始日期">
                            <input type="hidden" name="cfgCode" value="jsres_is_apply_end">
                            <input type="text" name="jsres_is_apply_end" value="${sysCfgMap['jsres_is_apply_end']}"
                                   style="width: 100px;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input"/>
                            <input type="hidden" name="jsres_is_apply_end_ws_id" value="res">
                            <input type="hidden" name="jsres_is_apply_end_desc" value="报名终止日期">
                        </td>
                    </tr>
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
                </table>
                <div class="button" >
                    <input type="button" class="btn_brown" onclick="save();" value="保&#12288;存">
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
                    <th width="40%" style="text-align: center;">配置项</th>
                    <th width="60%" style="text-align: center;" colspan="2">配置内容</th>
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
                <input type="button" class="btn_brown" onclick="save();" value="保&#12288;存">
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
                <input class="btn_brown" type="button" onclick="toPage(1)" value="查&#12288;询"/>&#12288;
                <input class="btn_brown" type="button" value="添&#12288;加" onclick="setScoreConf()"/>
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
                        <th width="40%" style="text-align: center;">配置项</th>
                        <th width="60%" style="text-align: center;">配置内容</th>
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
                    <input type="button" class="btn_brown" onclick="save();" value="保&#12288;存">
                </div>
            </div>
        </form>
    </c:if>

</div>
</body>