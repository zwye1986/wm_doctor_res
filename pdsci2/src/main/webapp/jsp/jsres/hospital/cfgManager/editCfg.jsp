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
        var url = "<s:url value='/jsres/cfgManager/save'/>";
        var data = $('#saveCfgForm').serialize();
        jboxPost(url, data, function() {
            $(".tab_select").click();
        });
    }
    function delTarget(recordFlow) {
        jboxConfirm("确认删除？", function () {
            var url = "<s:url value='/jsres/cfgManager/delActivityCfg'/>?recordFlow="+recordFlow;
            jboxPost(url, null, function (resp) {
                jboxTip(resp);
                jboxLoad("div_table",'<s:url value='/jsres/cfgManager/edit'/>?tagId=activityCfg',true);
            },null,false);
        });
    }
    function add(recordFlow){
        if(recordFlow==null){
          var url = "<s:url value ='/jsres/cfgManager/add'/>";
          jboxOpen(url, "新增教学活动流程", 700, 200);
        }else{
          var url = "<s:url value ='/jsres/cfgManager/add'/>?recordFlow="+recordFlow;
          jboxOpen(url, "修改教学活动流程", 700, 200);
        }
    }

    $(document).ready(function(){
        if(${param.tagId == 'ckxzCfg'}){
            <c:set var="key" value="out_test_check_${sessionScope.currUser.orgFlow}"></c:set>
            var flag = '${pdfn:jsresPowerCfgMap(key)}';
            tableShow(flag);
            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_ckxz" var="key"/>
            var flag = '${pdfn:jsresPowerCfgMap(key)}';
            showSessionNumber(flag);
        }
        if(${param.tagId == 'jxhdTimeCfg'}){
            <c:set value="jsres_${sessionScope.currUser.orgFlow}_org_ctrl_approve_activity" var="key"/>
            var flag = '${pdfn:jsresPowerCfgMap(key)}';
            showFlag(flag);
        }

      /*  if(${param.tagId == 'ckshCfg'}){
            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_cksh" var="key"/>
            var flag = '${pdfn:jsresPowerCfgMap(key)}';
            showCkshDetail(flag);
        }*/
    });

    function tableShow(flag) {
        if(flag == "Y"){
            $("#detailList").show();
            $("#addDetail").show();
        }else{
            $("#detailList").hide();
            $("#addDetail").hide();
        }
    }

    function showSessionNumber(flag,show) {
        if(flag === "Y"){
            $("#sessionNumberList").show();
            $("#sessionDetail").show();
            if(show){
                addCkxzConfg();
            }
        }else {
            $("#sessionNumberList").hide();
            $("#sessionDetail").hide();
        }
    }

  /*  function showCkshDetail(flag) {
        if(flag == "Y"){
            $("#ckshDetail").show();
            $("#ckshList").show();
        }else {
            $("#ckshDetail").hide();
            $("#ckshList").hide();
        }
    }*/

    function addDetailConfg() {
        var url = "<s:url value ='/jsres/cfgManager/addDetailConfg'/>";
        jboxOpen(url, "新增轮转科室出科限制配置", 500, 400);
    }

    function addCkxzConfg() {
        var url = "<s:url value ='/jsres/cfgManager/addCkxzConfg'/>";
        jboxOpen(url, "新增学员轮转年级、年份配置", 500, 340);
    }

    function editDeptConfig(cfgFlow) {
        var url = "<s:url value ='/jsres/cfgManager/editDetailConfg'/>?cfgFlow="+cfgFlow;
        jboxOpen(url, "编辑轮转科室出科限制配置", 500, 340);
    }

    function editCkxzConfig(recordFlow) {
        var url = "<s:url value ='/jsres/cfgManager/editCkxzConfig'/>?recordFlow="+recordFlow;
        jboxOpen(url, "编辑学员轮转年级、年份配置", 500, 340);
    }

    function delCkxzConfig(recordFlow) {
        jboxConfirm("删除后配置为通用配置，确认删除?",function () {
            var url = "<s:url value='/jsres/cfgManager/delCkxzConfig'/>?recordFlow="+recordFlow;
            jboxPost(url, null, function (resp) {
                jboxTip(resp);
                jboxLoad("div_table",'<s:url value='/jsres/cfgManager/edit'/>?tagId=ckxzCfg',true);
            },null,false);
        });
    }

    function delDeptConfig(cfgFlow) {
        jboxConfirm("删除后配置为通用配置，确认删除?",function () {
            var url = "<s:url value='/jsres/cfgManager/delDeptConfig'/>?cfgFlow="+cfgFlow;
            jboxPost(url, null, function (resp) {
                jboxTip(resp);
                jboxLoad("div_table",'<s:url value='/jsres/cfgManager/edit'/>?tagId=ckxzCfg',true);
            },null,false);
        });
    }

    function saveCksh() {
        var url = "<s:url value='/jsres/cfgManager/saveCkshConfig'/>";
        jboxSubmit($('#ckshList'),url, function (resp) {
            if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
                jboxTip(resp);
                jboxLoad("div_table",'<s:url value='/jsres/cfgManager/edit'/>?tagId=ckshCfg',true);
            }else {
                jboxTip("保存失败！");
            }
        }, null, true);
    }

    function showFlag(flag) {
        if (flag=='Y'){
            $("#org_approve").show();
        }else {
            $("#org_approve").hide();
        }
    }

    function chooseSch(type) {
        if ($("#"+type+"_checkbox").prop("checked")){
            $("#"+type).val("Y")
        }else{
            $("#"+type).val("N")
        }
        if (type=='scheduling_N'){
            $("#scheduling_org_checkbox").attr("checked",false);
            $("#scheduling_user_checkbox").attr("checked",false);
            $("#scheduling_org").val("N")
            $("#scheduling_user").val("N");

        }
        if (type=='scheduling_org' || type=='scheduling_user'){
            $("#scheduling_N_checkbox").removeAttr("checked");
            $("#scheduling_N").val("N");
        }
    }

    function chooseActivityDelete(type) {
        if ($("#"+type+"_checkbox").prop("checked")){
            $("#"+type).val("Y")
        }else{
            $("#"+type).val("N")
        }
    }

    function formatNumbers(obj){
        var re = (/^[1-9]\d*$/).test(obj.value) ;
        if(!re)
        {
            $('#'+obj.id).val("");
            jboxTip("限制数据请输入非0正整数！");
            return false;
        }
    }

    // 刷新出科考tab页
    function refreshCkkConfig(){
        jboxLoad("div_table",'<s:url value='/jsres/cfgManager/searchDeptConfig'/>?tagId=ckxzCfg',true);
    }
</script>
<body>
<div class="main_bd">
    <c:if test="${'dataCfg'==param.tagId }">
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
                        <td style="text-align: center;">是否允许学员导入培训数据：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">

                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_daoru" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/><label
                                for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/><label
                                for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="是否允许学员导入配置数据">
                        </td>
                    </tr>
                </table>
                <div class="button" >
                    <input type="button" class="btn_green" onclick="save();" value="保&#12288;存">
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${'szsqshCfg'==param.tagId }">
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
                        <td style="text-align: center;">是否科主任审核：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">

                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_head_audit" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/><label
                                for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/><label
                                for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="是否科主任审核">
                        </td>
                    </tr>
                </table>
                <div class="button" >
                    <input type="button" class="btn_green" onclick="save();" value="保&#12288;存">
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${'kqcxdcCfg'==param.tagId }">
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
                        <td style="text-align: center;">允许查询导出考勤数据的角色配置：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">

                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_export_attendance_teacher" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <label><input type="checkbox" onclick="chooseActivityDelete('teacherDc');" id="teacherDc_checkbox" value="Y" class="autoValue"
                                          <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                带教老师</label>&#12288;&#12288;
                            <input type="hidden" name="${key}"  id="teacherDc" value="${pdfn:jsresPowerCfgMap(key)}">
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="允许带教老师导出考勤数据">


                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_export_attendance_secretary" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <label><input type="checkbox" onclick="chooseActivityDelete('secretaryDc');" id="secretaryDc_checkbox" value="Y" class="autoValue"
                                          <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                科秘</label>&#12288;&#12288;
                            <input type="hidden" name="${key}"  id="secretaryDc" value="${pdfn:jsresPowerCfgMap(key)}">
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="允许科秘导出考勤数据">


                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_export_attendance_head" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <label><input type="checkbox" onclick="chooseActivityDelete('headDc');" id="headDc_checkbox" value="Y" class="autoValue"
                                          <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                科主任</label>&#12288;&#12288;
                            <input type="hidden" name="${key}"  id="headDc" value="${pdfn:jsresPowerCfgMap(key)}">
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="允许科主任导出考勤数据">

                        </td>
                    </tr>
                </table>
                <div class="button" >
                    <input type="button" class="btn_green" onclick="save();" value="保&#12288;存">
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${'ckshCfg'==param.tagId }">
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
                        <td style="text-align: center;">是否由科主任/教学主任/教学秘书审核学员技能出科成绩：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_cksh" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/><label
                                for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/><label
                                for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="是否由科主任审核学员技能出科成绩">
                        </td>
                    </tr>
                </table>
                <div class="button" >
                    <input type="button" class="btn_green" onclick="save();" value="保&#12288;存">
                </div>
            </div>
        </form>

       <%-- <form id="ckshList">
            <div class="div_table">
                <c:forEach items="${sysDeptList}" var="dept">
                    <div style="width:24.9%;float: left;margin-top: 2px;margin-bottom: 2px;">
                        <label id="${dept.deptFlow }" class="otherDept">
                            <input type="checkbox" name="mulDeptFlow" value="${dept.deptFlow }"
                                   <c:if test="${!empty ckshMap[dept.deptFlow] }">checked</c:if>
                            />${dept.deptName}&#12288;&#12288;</label>
                    </div>
                </c:forEach>
            </div>
            <div class="button" >
                <input style="margin-top: 25px;" type="button" class="btn_green" onclick="saveCksh();" value="保&#12288;存">
            </div>
        </form>--%>
    </c:if>
    <c:if test="${'ckxzCfg'==param.tagId }">
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
                        <td style="text-align: right;">是否开启学员轮转出科考核表通过带教老师或教学秘书（科主任）审核后，且限制学员出科时上传出科表,才可进入下个轮转科室：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_ckxz" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${empty orgCkxzList}">onclick="showSessionNumber('Y',true)"</c:if>
                                   <c:if test="${not empty orgCkxzList}">onclick="showSessionNumber('Y',false)"</c:if>
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                <label for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N" onclick="showSessionNumber('N')"
                                   <c:if test="${empty pdfn:jsresPowerCfgMap(key) or pdfn:jsresPowerCfgMap(key) eq 'N' or empty orgCkxzList}">checked</c:if>/>
                                <label for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="是否限制学员出科后，才可添加轮转科室">

                            <img id="sessionDetail" style="cursor:pointer;float:right;margin:8px 2% 0px 0px;"  src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addCkxzConfg()" title="添加" />
                        </td>
                    </tr>
<%--                    <tr>--%>
<%--                        <td style="text-align: right;">是否限制学员出科时上传出科表：</td>--%>
<%--                        <td style="text-align: left;padding-left: 5px" width="200px">--%>
<%--                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_ckxz_upload_cktable" var="key"/>--%>
<%--                            <input type="hidden" name="cfgCode" value="${key}">--%>
<%--                            &nbsp;--%>
<%--                            <input type="radio" id="${key}_y" name="${key}" value="Y"--%>
<%--                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>--%>
<%--                            <label for="${key}_y">是</label>&#12288;--%>
<%--                            <input type="radio" id="${key}_n" name="${key}" value="N"--%>
<%--                                   <c:if test="${empty pdfn:jsresPowerCfgMap(key) or pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>--%>
<%--                            <label for="${key}_n">否</label>--%>
<%--                            <input type="hidden" name="${key}_ws_id" value="res">--%>
<%--                            <input type="hidden" name="${key}_desc" value="是否限制学员出科时上传出科表">--%>
<%--                        </td>--%>
<%--                    </tr>--%>
                    <tr>
                        <td style="text-align: right;">是否强制带教对学员进行月度考评：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_eval" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                <label for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N"
                                   <c:if test="${empty pdfn:jsresPowerCfgMap(key) or pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                                <label for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="是否强制带教对学员进行月度考评">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">是否开启出科考核校验：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set var="key" value="out_test_check_${sessionScope.currUser.orgFlow}"></c:set>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y" onclick="tableShow('Y')"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                <label for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N" onclick="tableShow('N')"
                                   <c:if test="${empty pdfn:jsresPowerCfgMap(key) or pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                                <label for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="是否开启出科考核校验">
                            <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">
                            <img id="addDetail" style="cursor:pointer;float:right;margin:8px 2% 0px 0px;" hidden="hidden" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addDetailConfg()" title="添加" />
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">是否开启轮转计划排班校验：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set var="key" value="process_scheduling_check_${sessionScope.currUser.orgFlow}"></c:set>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                            <label for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N"
                                   <c:if test="${empty pdfn:jsresPowerCfgMap(key) or pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                            <label for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="是否开启轮转计划排班校验">
                        </td>
                    </tr>

                    <tr>
                        <td style="text-align: right;">360评价是否为必评项：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_360evaluate" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                            <label for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N"
                                   <c:if test="${empty pdfn:jsresPowerCfgMap(key) or pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                            <label for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="360评价是否为必评项">
                        </td>
                    </tr>

                    <tr>
                        <td style="text-align: right">学员出科是否需要带教老师填写出科评价</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set var="key" value="jsres_departure_evaluation_${sessionScope.currUser.orgFlow}"></c:set>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y" onclick="tableShow('Y')"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                            <label for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N" onclick="tableShow('N')"
                                   <c:if test="${empty pdfn:jsresPowerCfgMap(key) or pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                            <label for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="学员出科是否需要带教老师填写出科评价">
                        </td>
                    </tr>

                    <tr>
                        <td style="text-align: right;">轮转计划排班设置：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">

                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_scheduling_N" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">&nbsp;
                            <label><input type="checkbox"   onclick="chooseSch('scheduling_N');" value="Y" id="scheduling_N_checkbox"  class="autoValue"
                                          <c:if test="${empty pdfn:jsresPowerCfgMap(key) or pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                不开启</label>&#12288;&#12288;
                            <input type="hidden" name="${key}"  id="scheduling_N" value="${pdfn:jsresPowerCfgMap(key)}">
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="轮转计划排班设置-不开启">



                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_scheduling_org" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <label><input type="checkbox"  onclick="chooseSch('scheduling_org');" value="Y" id="scheduling_org_checkbox"  class="autoValue"
                                          <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                基地配置</label>&#12288;&#12288;
                            <input type="hidden" name="${key}"  id="scheduling_org" value="${pdfn:jsresPowerCfgMap(key)}">
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="轮转计划排班设置-基地配置">


                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_scheduling_user" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <label><input type="checkbox" onclick="chooseSch('scheduling_user');" id="scheduling_user_checkbox" value="Y"  class="autoValue"
                                          <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                学员自行配置</label>
                            <input type="hidden" name="${key}"  id="scheduling_user" value="${pdfn:jsresPowerCfgMap(key)}">
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="轮转计划排班设置-学员自行配置">
                            &#12288;&#12288;&#12288;&#12288;(默认不开启)

                          <%--  <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_scheduling_N" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <input type="radio" id="${key}_N" name="${key}" value="Y" onclick="chooseSch('scheduling_N');"
                                          <c:if test="${empty pdfn:jsresPowerCfgMap(key) || pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                            <label for="${key}_N">不开启</label>&#12288;
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="轮转计划排班设置-不开启">


                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_scheduling_org" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <input type="radio" id="${key}_org" name="${key}" value="Y" onclick="chooseSch('scheduling_org');"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'org'}">checked</c:if>/>
                            <label for="${key}_org">基地配置</label>&#12288;
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="轮转计划排班设置-基地配置">


                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_scheduling_user" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <input type="radio" id="${key}_user" name="${key}" value="Y" onclick="chooseSch('scheduling_user');"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'user'}">checked</c:if>/>
                            <label for="${key}_user">学员自行配置</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="轮转计划排班设置-学员自行配置">
                            --%>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;">轮转计划排班最短时间：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_process_scheduling_time" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">&nbsp;
                            <input type="radio" id="${key}_1" name="${key}" value="1"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq '1'}">checked</c:if>/>
                            <label for="${key}_1">1年</label>&#12288;
                            <input type="radio" id="${key}_2" name="${key}" value="2"
                                    <c:if test="${pdfn:jsresPowerCfgMap(key) eq '2'}">checked</c:if>/>
                            <label for="${key}_2">2年</label>&#12288;
                            <input type="radio" id="${key}_3" name="${key}" value="3"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq '3'}">checked</c:if>/>
                            <label for="${key}_3">3年</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="轮转计划排班最短时间">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">出科后填写数据时间限制：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set var="key" value="out_day_check_${sessionScope.currUser.orgFlow}"></c:set>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;超过<input type="text" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}"
                                           class="input validate[custom[number],maxSize[100]]"
                                           style="width: 5%;"/>天未填写，将不可进行数据填写操作
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="出科后填写数据时间限制">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">数据填写比例限制：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set var="key" value="out_filling_check_${sessionScope.currUser.orgFlow}"></c:set>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;大于等于<input type="text" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}" class="input validate[custom[number],maxSize[100],min[0]]" style="width: 5%;"/>

                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="数据填写比例限制">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">登录限制：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set var="key" value="jsres_${sessionScope.currUser.orgFlow}_user_lock"></c:set>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;超过<input type="text" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}"
                                           class="input validate[custom[number],maxSize[100]]"
                                           style="width: 5%;"/>天未填写数据，账号锁定
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="登录限制">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">一个带教同时可带学员人数设置：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set var="key" value="jsres_${sessionScope.currUser.orgFlow}_student_number_limit"></c:set>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <input type="text" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}"
                                   class="input validate[custom[number],maxSize[100]]"
                                   onkeyup="formatNumbers(this);"
                                   style="width: 5%;"/> 人
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="一个带教同时可带学员人数设置">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right">未填写数据提醒时间限制：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">

                            <c:set var="key" value="jsres_${sessionScope.currUser.orgFlow}_message_push"></c:set>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/><label
                                for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N" style="margin-left: 14px"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/><label
                                for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="未填写数据提醒时间开关限制">

                            <c:set var="key" value="jsres_${sessionScope.currUser.orgFlow}_message_push_time"></c:set>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &#12288;超过<input type="text" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}"
                                           class="input validate[custom[number],maxSize[100]]"
                                           style="width: 5%;"/>天未填写，提醒科秘学员名单
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="未填写数据提醒时间限制">
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td style="text-align: center">学员出科考试次数：</td>--%>
                        <%--<td style="text-align: left;padding-left: 5px" width="200px">--%>
                            <%--<c:set var="key" value="out_test_limit_${sessionScope.currUser.orgFlow}"></c:set>--%>
                            <%--<input type="hidden" name="cfgCode" value="${key}">--%>
                            <%--<input type="text" class="input validate[custom[integer],min[1]]" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}" style="width: 200px;"/>--%>
                            <%--<input type="hidden" name="${key}_ws_id"  value="res">--%>
                            <%--<input type="hidden" name="${key}_desc"  value="出科考次数限制">--%>
                            <%--<span>说明：未设置表示不限制考试次数</span>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <%--<tr>--%>
                        <%--<td style="text-align: center;">出科考核表审核时校验学员理论成绩是否合格：</td>--%>
                        <%--<td style="text-align: left;padding-left: 5px" width="200px">--%>
                            <%--<c:set value="theoretical_qualified_flag_${sessionScope.currUser.orgFlow}" var="key"/>--%>
                            <%--<input type="hidden" name="cfgCode" value="${key}">--%>
                            <%--&nbsp;--%>
                            <%--<input type="radio" id="${key}_y" name="${key}" value="Y"--%>
                                   <%--<c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/><label--%>
                                <%--for="${key}_y">是</label>&#12288;--%>
                            <%--<input type="radio" id="${key}_n" name="${key}" value="N"--%>
                                   <%--<c:if test="${empty pdfn:jsresPowerCfgMap(key) or pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/><label--%>
                                <%--for="${key}_n">否</label>--%>
                            <%--<input type="hidden" name="${key}_ws_id" value="res">--%>
                            <%--<input type="hidden" name="${key}_desc" value="出科考核表审核时校验学员理论成绩是否合格">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                </table>

                <div class="button" >
                    <input type="button" class="btn_green" onclick="save();" value="保&#12288;存">
                </div>
            </div>
        </form>

        <form id="sessionNumberList">
            <div class="div_table">
                <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 10px">
                    <tr>
                        <th style="text-align: center">年级</th>
                        <th style="text-align: center">节点</th>
                        <th style="text-align: center">编辑</th>
                    </tr>
                    <c:if test="${empty orgCkxzList}">
                        <tr>
                            <td colspan="3" style="text-align: center">无记录！</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty orgCkxzList}">
                        <c:forEach var="c" items="${orgCkxzList}">
                            <tr>
                                <td style="text-align: center">${c.sessionGrade}</td>
                                <td style="text-align: center">${c.sessionYear}</td>
                                <td style="text-align: center">
                                    <a href="javascript:editCkxzConfig('${c.recordFlow}');">编辑</a>
                                    <c:if test="${not empty c.recordFlow}">
                                        <a href="javascript:delCkxzConfig('${c.recordFlow}');">删除</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
        </form>

        <form id="detailList">
            <div class="div_table">
                <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 10px">
                    <tr>
                        <th style="text-align: center">轮转科室</th>
                        <th style="text-align: center">出科考核次数</th>
                        <th style="text-align: center">默认合格线</th>
                        <th style="text-align: center">出科考核时间</th>
                        <th style="text-align: center">是否允许带教老师填写分数</th>
                        <th style="text-align: center">出科退回重考分数上限</th>
                        <th style="text-align: center">编辑</th>
                    </tr>
                    <c:if test="${empty deptConfigList}">
                        <tr>
                            <td colspan="7" style="text-align: center">无记录！</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty deptConfigList}">
                        <c:forEach var="deptConfig" items="${deptConfigList}">
                            <tr>
                                <td style="text-align: center">${deptConfig.deptName}</td>
                                <td style="text-align: center">${deptConfig.testNum}</td>
                                <td style="text-align: center">${deptConfig.scorePass}</td>
                                <td style="text-align: center">${deptConfig.testOutTime}</td>
                                <td style="text-align: center">
                                    <c:if test="${deptConfig.teacherWrite eq 'Y'}">是</c:if>
                                    <c:if test="${deptConfig.teacherWrite ne 'Y'}">否</c:if>
                                </td>
                                <td style="text-align: center">${deptConfig.scoreToplimit}</td>
                                <td style="text-align: center">
                                    <a href="javascript:editDeptConfig('${deptConfig.cfgFlow}');">编辑</a>
                                    <c:if test="${not empty deptConfig.deptFlow}">
                                        <a href="javascript:delDeptConfig('${deptConfig.cfgFlow}');">删除</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </div>
            <%--<div style="text-align: center;color: red">注意：请先设置默认值</div>--%>
        </form>
    </c:if>
    <c:if test="${'activityCfg'==param.tagId }">
        <form id="addCfg">
            <div class="div_table">
                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                    <tr class="button" >
                        <input type="button" class="btn_green" onclick="add();" value="新&#12288;增">
                    </tr>
                    <br/>
                    <br/>
                    <thead>
                        <tr>
                            <th width="20%" style="text-align: center;">发起人角色</th>
                            <th width="50%" style="text-align: center;">审批人角色</th>
                            <th width="30%" style="text-align: center;">操作</th>
                        </tr>
                    </thead>
                    <c:forEach items="${targets}" var="target">
                    <tr>
                        <td>${target.subRoleName}</td>
                        <td>${target.auditRoleName}</td>
                        <td>
                            <a style="cursor: pointer;" href="javascript:void(0);" onclick="add('${target.recordFlow}')">修改</a>
                            <a style="cursor: pointer;" href="javascript:void(0);" onclick="delTarget('${target.recordFlow}')">删除</a>
                        </td>
                    </tr>
                    </c:forEach>
                    <c:if test="${empty targets}">
                        <tr>
                            <td colspan="3" style="text-align: center">无记录</td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </form>
    </c:if>
    <c:if test="${'jxhdTimeCfg'==param.tagId }">
        <form id="saveCfgForm">
            <div class="div_table">
                <table class="base_info">
                    <thead>
                    <tr>
                        <th width="25%" style="text-align: center;">配置项</th>
                        <th width="25%" style="text-align: center;">配置内容</th>
                        <th width="50%" style="text-align: center;">配置参数值</th>
                    </tr>
                    </thead>
                    <tr>
                        <td style="text-align: right;width: 100px;"  rowspan="2">讲座活动扫码配置：</td>
                        <td style="text-align: left"  rowspan="2">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_jiangzuo_code_type" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="N"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) ne 'Y'}">checked</c:if>/><label
                                for="${key}_y">静态二维码</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/><label
                                for="${key}_n">动态二维码</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="讲座活动活动二维码类型">
                        </td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_jiangzuo_start_time" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            讲座开始时间前后<input type="text" style="width:50px;" class="input validate[custom[number],min[1]]" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}">分钟内扫码签到有效。(默认值为：10分钟)
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="讲座签到时间">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_jiangzuo_end_time" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            讲座结束时间前后<input type="text"  style="width:50px;" class="input validate[custom[number],min[1]]" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}">分钟内扫码签到有效。(默认值为：10分钟)
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="讲座签退时间">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right"  rowspan="2">教学活动扫码配置：</td>
                        <td style="text-align: left"  rowspan="2">

                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_code_type" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="N"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key)  ne 'Y'}">checked</c:if>/><label
                                for="${key}_y">静态二维码</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/><label
                                for="${key}_n">动态二维码</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="教学活动活动二维码类型">
                        </td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_start_time" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            活动开始时间前后<input type="text"  style="width:50px;" class="input validate[custom[number],min[1]]" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}">分钟内扫码签到有效。(默认值为：10分钟)
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="教学活动签到时间">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_end_time" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            活动结束时间前后<input type="text" style="width:50px;"  class="input validate[custom[number],min[1]]" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}">分钟内扫码签到有效。(默认值为：10分钟)
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="教学活动签退时间">
                        </td>
                    </tr>
                    <%--<tr>--%>
                        <%--<td>--%>
                            <%--<c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_sign_in" var="key"/>--%>
                            <%--<input type="hidden" name="cfgCode" value="jsres_${sessionScope.currUser.orgFlow }_org_activity_sign_in">--%>
                            <%--&nbsp;--%>
                            <%--<input  type="checkbox" name="jsres_${sessionScope.currUser.orgFlow }_org_activity_sign_in" value="${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_N ?'N':'Y'}" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_N ?'':'checked'} />${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_N ?'N':'Y'}开启签到扫码&#12288;&#12288;--%>
                            <%--<input type="hidden" name="jsres_${sessionScope.currUser.orgFlow }_org_activity_sign_in_ws_id" value="res">--%>
                            <%--<input type="hidden" name="jsres_${sessionScope.currUser.orgFlow }_org_activity_sign_in_desc" value="开启签到扫码">--%>
                        <%--</td>--%>
                        <%--<td>--%>
                            <%--<c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_sign_out" var="key"/>--%>
                            <%--<input type="hidden" name="cfgCode" value="jsres_${sessionScope.currUser.orgFlow }_org_activity_sign_out">--%>
                            <%--&nbsp;--%>
                            <%--<input  type="checkbox" name="jsres_${sessionScope.currUser.orgFlow }_org_activity_sign_out" value="${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_N ?'N':'Y'}" <c:if test="${pdfn:jsresPowerCfgMap(key) ne 'Y'}">${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_N ?'':'checked'}</c:if>--%>
                                    <%--<c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if> />开启签退扫码&#12288;&#12288;--%>
                            <%--<input type="hidden" name="jsres_${sessionScope.currUser.orgFlow }_org_activity_sign_out_ws_id" value="res">--%>
                            <%--<input type="hidden" name="jsres_${sessionScope.currUser.orgFlow }_org_activity_sign_out_desc" value="开启签退扫码">--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                    <tr>
                        <td style="text-align: right;width: 100px;" >是否允许学员填写教学活动数据：</td>
                        <td colspan="2">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_stu_add_activity" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                            <label for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N" style="margin-left: 14px"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                            <label for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="是否允许学员填写教学活动数据">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;width: 100px;" >教学活动评价配置：</td>
                        <td colspan="2">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow}_org_ctrl_approve_activity" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y" onclick="showFlag('Y');"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                            <label for="${key}_y">启用</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N" onclick="showFlag('N');"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                            <label for="${key}_n">停用</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="教学活动评价配置">

                            <div id="org_approve">
                                <c:set value="jsres_${sessionScope.currUser.orgFlow}_org_approve_activity" var="key1"/>
                                <input type="hidden" name="cfgCode" value="${key1}">
                                &nbsp;
                                <input type="radio" id="${key1}_y" name="${key1}" value="Y"
                                       <c:if test="${pdfn:jsresPowerCfgMap(key1) eq 'Y'}">checked</c:if>/>
                                <label for="${key1}_y">必评</label>&#12288;
                                <input type="radio" id="${key1}_n" name="${key1}" value="N"
                                       <c:if test="${pdfn:jsresPowerCfgMap(key1) eq 'N'}">checked</c:if>/>
                                <label for="${key1}_n">非必评</label>
                                &#12288;&#12288;（若设置必评后，学员参与教学活动未评价，则不计入出科考核表）
                                <input type="hidden" name="${key1}_ws_id" value="res">
                                <input type="hidden" name="${key1}_desc" value="教学活动评价配置评审类型">
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td style="text-align: right;">允许删除教学活动的角色设置：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px" colspan="2">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_delete_activity_teacher" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">&nbsp;
                            <label><input type="checkbox" onclick="chooseActivityDelete('teacher');" id="teacher_checkbox" value="Y" class="autoValue"
                                          <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                带教老师</label>&#12288;&#12288;
                            <input type="hidden" name="${key}"  id="teacher" value="${pdfn:jsresPowerCfgMap(key)}">
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="允许带教老师删除教学活动">


                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_delete_activity_secretary" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <label><input type="checkbox" onclick="chooseActivityDelete('secretary');" id="secretary_checkbox" value="Y" class="autoValue"
                                          <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                科秘</label>&#12288;&#12288;
                            <input type="hidden" name="${key}"  id="secretary" value="${pdfn:jsresPowerCfgMap(key)}">
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="允许科秘删除教学活动">


                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_delete_activity_head" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            <label><input type="checkbox" onclick="chooseActivityDelete('head');" id="head_checkbox" value="Y" class="autoValue"
                                          <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                                科主任</label>&#12288;&#12288;
                            <input type="hidden" name="${key}"  id="head" value="${pdfn:jsresPowerCfgMap(key)}">
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="允许科主任删除教学活动">


<%--                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_delete_activity_manager" var="key"/>--%>
<%--                            <input type="hidden" name="cfgCode" value="${key}">--%>
<%--                            <label><input type="checkbox" onclick="chooseActivityDelete('manager');" id="manager_checkbox" value="Y" class="autoValue"--%>
<%--                                          <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>--%>
<%--                                基地管理员</label>&#12288;&#12288;--%>
<%--                            <input type="hidden" name="${key}"  id="manager" value="${pdfn:jsresPowerCfgMap(key)}">--%>
<%--                            <input type="hidden" name="${key}_ws_id" value="res">--%>
<%--                            <input type="hidden" name="${key}_desc" value="允许基地管理员删除教学活动">--%>
                        </td>
                    </tr>

                    <tr>
                        <td style="text-align: right">新增教学活动时间配置：</td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_add_day" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            活动开始时间前<input type="text"  style="width:50px;" class="input validate[custom[number],min[0]]" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}">天
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="新增教学活动日期配置">
                        </td>
                        <td style="text-align: left;padding-left: 5px" width="200px">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_org_activity_add_time" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="text" onclick="WdatePicker({dateFmt:'HH:mm'})" readonly="readonly" style="width:70px;" class="input" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}">点前设置完毕。
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="新增教学活动时间配置">
                        </td>
                    </tr>

                    <tr>
                        <td style="text-align: right" rowspan="2">是否支持学员签到、签退后修改主讲人和实际主讲人：</td>
                        <td style="text-align: left;padding-left: 5px">
                            &nbsp;&nbsp;主讲人
                        </td>
                        <td>
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_activity_teach" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                            <label for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N" style="margin-left: 14px"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                            <label for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="修改主讲人">

                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_activity_teach_day" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="text"  style="width:50px;text-align: center;" class="input validate[custom[number],min[0]]" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}">天内可以修改
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="修改主讲人日期配置">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: left;padding-left: 5px">
                            &nbsp;&nbsp;实际主讲人
                        </td>
                        <td>
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_activity_kzr" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                            <label for="${key}_y">是</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N" style="margin-left: 14px"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                            <label for="${key}_n">否</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="修改实际主讲人">

                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_activity_kzr_day" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="text"  style="width:50px;text-align: center;" class="input validate[custom[number],min[0]]" name="${key}" value="${pdfn:jsresPowerCfgMap(key)}">天内可以修改
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="修改实际主讲人日期配置">
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align: right;width: 100px;" >是否隐藏实际主讲人：</td>
                        <td colspan="2">
                            <c:set value="jsres_${sessionScope.currUser.orgFlow }_activity_teach_show" var="key"/>
                            <input type="hidden" name="cfgCode" value="${key}">
                            &nbsp;
                            <input type="radio" id="${key}_y" name="${key}" value="Y"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'Y'}">checked</c:if>/>
                            <label for="${key}_y">显示</label>&#12288;
                            <input type="radio" id="${key}_n" name="${key}" value="N" style="margin-left: 14px"
                                   <c:if test="${pdfn:jsresPowerCfgMap(key) eq 'N'}">checked</c:if>/>
                            <label for="${key}_n">隐藏</label>
                            <input type="hidden" name="${key}_ws_id" value="res">
                            <input type="hidden" name="${key}_desc" value="是否隐藏实际主讲人">
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