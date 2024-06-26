<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/common-art.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script type="text/javascript">
    function jboxButtonConfirm(msg,button1,button2,funcOk,funcCancel,width){
        dialog({
            fixed: true,
            width:width,
            title: '提示',
            cancelValue:'关闭',
            content: msg,
            backdropOpacity:0.1,
            button:[
                {
                    value: button1,
                    callback:funcOk,
                    autofocus: true
                },
                {
                    value: button2,
                    callback:funcCancel
                }
            ]
        }).showModal();
    }

    $(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        <c:forEach items="${jsResDocTypeEnumList}" var="type">
            <c:forEach items="${datas}" var="data">
                if("${data}"=="${type.id}"){
                    $("#"+"${data}").attr("checked","checked");
                }
            </c:forEach>
            <c:if test="${empty datas}">
                $("#"+"${type.id}").attr("checked","checked");
            </c:if>
        </c:forEach>
    });
    function search() {
        if(!$("#orgFlow").val()){
            $("#orgSel").val("");
        }
        if($("#ifOpen").find("option:selected ").val()){
            if($("input[name='powerTypeId']:checked").size()<1){
                jboxTip("请选择权限类型！");
                return false;
            }
        }
        if($("input[name='powerTypeId']:checked").size()>=1){
            if(!$("#ifOpen").find("option:selected ").val()){
                jboxTip("请选择权限筛选！");
                return false;
            }
        }
        var orgName = $("#orgSel").val();
        if(!orgName){
            $("#orgFlow").val("");
        }
        var url = "<s:url value='/jsres/business/userList' />?checkStatusId=${param.checkStatusId}";
        // jboxStartLoading();
        jboxPostLoad("doctorContent", url, $("#searchForm").serialize(), true);
        // jboxEndLoading();
    }

    function toPage(page) {
        if (page) {
            $("#currentPage").val(page);
        }
        search();
    }

    function clearOrgFlow(){
        $("#orgFlow").val("");
    }

    /**
     *模糊查询加载
     */
    (function($){
        $.fn.likeSearchInit = function(option){
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            searchInput.on("keyup focus",function(){
                $("#boxHome").show();
                if($.trim(this.value)){
                    $("#boxHome .item").hide();
                    var items = $("#boxHome .item[value*='"+this.value+"']").show();
                    if(!items){
                        $("#boxHome").hide();
                    }
                }else{
                    $("#boxHome .item").show();
                }
            });
            searchInput.on("blur",function(){
                if(!$("#boxHome.on").length){
                    $("#boxHome").hide();
                }
            });
            $("#boxHome").on("mouseenter mouseleave",function(){
                $(this).toggleClass("on");
            });
            $("#boxHome .item").click(function(){
                $("#boxHome").hide();
                var value = $(this).attr("value");
                $("#itemName").val(value);
                searchInput.val(value);
                if(option.clickActive){
                    option['clickActive']($(this).attr("flow"));
                }
            });
        };
    })(jQuery);
    //======================================
    //页面加载完成时调用
    $(function(){
        $("#orgSel").likeSearchInit({
            clickActive:function(flow){
                $("#orgFlow").val(flow);
            }
        });
    });

    function changeOpen(vari){
        if(!$(vari).val()){
            $("input[name='powerTypeId']").attr({"disabled":"disabled"}).removeAttr("checked");
            $("#powerTypes").hide();
        }else {
            $("input[name='powerTypeId']").removeAttr("disabled");
            $("#powerTypes").show();
        }
    }

    function showSendSchool(obj) {
        var v="";
        $(".docType:checked").each(function(){
            v+=$(this).val();
        });
        // if(v=='Graduate') {
        //     $(".Graduate").show();
        //     $("#workOrgId").find("option[value='']").attr("selected",true);
        //     $(".GraduateNext").hide();
        // }else{
        //     $(".Graduate").hide();
        //     $(".GraduateNext").show();
        // }
    }

    function updateCheck() {
        $("input[name='checkStatusId']").prop("checked",true);
        var userFlows="";
        var count = 1;
        $("input[name='checkStatusId']").each(function(i){
            if(i == 0){
                userFlows += $(this).val();
            }else {
                userFlows += "&userFlows=" + $(this).val();
                count += 1;
            }
        });
        var url2 = "<s:url value='/jsres/business/checkReason'/>?userFlows="+userFlows;
        var typeName = "学员权限审核";
        jboxOpen(url2, typeName, 350, 210);

        <%--jboxButtonConfirm("共"+count+"条数据，审核是否通过？","通过","不通过",function(){//通过--%>
        <%--var url = "<s:url value='/jsres/business/updateCheck'/>?userFlows="+userFlows+"&flag=Y";--%>
        <%--jboxPost(url, null, function(resp){--%>
        <%--if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){--%>
        <%--search();--%>
        <%--jboxTip("操作成功！");--%>
        <%--}else{--%>
        <%--jboxTip("操作失败！");--%>
        <%--}--%>
        <%--}, null, false);--%>
        <%--},function(){//不通过--%>
        <%--var url = "<s:url value='/jsres/business/updateCheck'/>?userFlows="+userFlows+"&flag=N";--%>
        <%--jboxPost(url, null, function(resp){--%>
        <%--if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){--%>
        <%--search();--%>
        <%--jboxTip("操作成功！");--%>
        <%--}else{--%>
        <%--jboxTip("操作失败！");--%>
        <%--}--%>
        <%--}, null, false);--%>
        <%--},300);--%>
    }

    function updateCheckOne(userFlow) {
        var userFlows = userFlow;
        var url2 = "<s:url value='/jsres/business/checkReason'/>?userFlows="+userFlows;
        var typeName = "学员权限审核";
        jboxOpen(url2, typeName, 350, 210);
        <%--jboxButtonConfirm("审核是否通过？","通过","不通过",function(){//通过--%>
        <%--var url = "<s:url value='/jsres/business/updateCheck'/>?userFlows="+userFlows+"&flag=Y";--%>
        <%--jboxPost(url, null, function(resp){--%>
        <%--if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){--%>
        <%--search();--%>
        <%--jboxTip("操作成功！");--%>
        <%--}else{--%>
        <%--jboxTip("操作失败！");--%>
        <%--}--%>
        <%--}, null, false);--%>
        <%--},function(){//不通过--%>
        <%--var url = "<s:url value='/jsres/business/updateCheck'/>?userFlows="+userFlows+"&flag=N";--%>
        <%--jboxPost(url, null, function(resp){--%>
        <%--if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){--%>
        <%--search();--%>
        <%--jboxTip("操作成功！");--%>
        <%--}else{--%>
        <%--jboxTip("操作失败！");--%>
        <%--}--%>
        <%--}, null, false);--%>
        <%--},300);--%>
    }

    function sendBack() {
        $("input[name='sendBackId']").prop("checked",true);
        var userFlows="";
        var count = 1;
        $("input[name='sendBackId']").each(function(i){
            if(i == 0){
                userFlows += $(this).val();
            }else {
                userFlows += "&userFlows=" + $(this).val();
                count += 1;
            }
        });
        var url = "<s:url value='/jsres/business/sendBack'/>?userFlows="+userFlows;
        jboxConfirm("共"+count+"条，确认退回重新编辑？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    search();
                    jboxTip("操作成功");
                }
            }, null, true);
        });
    }

    function sendBackOne(userFlow) {
        var userFlows = userFlow;
        var url = "<s:url value='/jsres/business/sendBack'/>?userFlows="+userFlows;
        jboxConfirm("确认退回重新编辑？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    search();
                    jboxTip("操作成功");
                }
            }, null, true);
        });
    }

    function exportDoc1(){
        var checkStatusId = '${param.checkStatusId}';
        var url = '<s:url value="/jsres/business/exportDoc"/>?checkStatusId='+checkStatusId;
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>

<div class="main_bd" id="div_table_0" >
    <div class="div_search">
        <form id="searchForm" method="post">
            <input id="currentPage" type="hidden" name="currentPage"/>
            <table class="searchTable">
                <tr>
                    <td>
                        培训基地：
                        <input id="orgSel" onchange="clearOrgFlow();" class="input"  type="text" name="orgName"
                               value="${param.orgName}" autocomplete="off" style="width: 120px;"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top: 36px; left:74px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;
							  vertical-align:middle; background-color: white;min-width: 126px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${applicationScope.sysOrgList}" var="org">
                                    <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="line-height: 20px; padding:10px 0;cursor: default; text-align: left;">${org.orgName}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
                    </td>
                    <td>
                        姓&#12288;&#12288;名：
                        <input type="text" name="userName" value="${param.userName}" class="input"
                               style="width: 120px;"/>
                    </td>
                    <td>
                        证件号码：
                        <input type="text" name="idNo" class="input" value="${param.idNo}"
                               style="width: 120px;"/>
                    </td>
                    <td class="Graduate">
                        派送学校：
                        <select name="workOrgId" id="workOrgId"  class="select" style="width: 126px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${param.workOrgId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        审核区间：
                        <input  id="startTime" name="startTime" class="input" type="text" value="${param.startTime}" readonly="readonly"
                                onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" style="width: 120px;"/>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input  id="endTime" name="endTime" class="input" type="text" value="${param.endTime}" readonly="readonly"
                                onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" style="width: 120px;"/>
                    </td>
                    <td colspan="2">
                        人员类型：
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}" onclick="showSendSchool(this);" class="docType" name="datas" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td colspan="1">
                        权限筛选：
                        <%--<select name="ifOpen" id="ifOpen" class="select"  onchange="changeOpen(this);" style="width: 126px;">--%>
                        <select name="ifOpen" id="ifOpen" class="select" style="width: 126px;">
                            <option value="">全部</option>
                            <option value="Y" ${param.ifOpen eq 'Y'?'selected':''}>已开通</option>
                            <option value="N" ${param.ifOpen eq 'N'?'selected':''}>未开通</option>
                        </select>
                    </td>
                    <td style="" id="powerTypes" colspan="2">
                        权限类型：
                        <input name="powerTypeId"  type="checkbox" value="APP" ${APP eq 'Y'?'checked':''}/>APP&nbsp;
                        <input name="powerTypeId"  type="checkbox" value="menu" ${menu eq 'Y'?'checked':''}/>付费功能 &nbsp;
                        <input name="powerTypeId"  type="checkbox" value="ckks" ${ckks eq 'Y'?'checked':''}/>出科考试&nbsp;
                        <input name="powerTypeId"  type="checkbox" value="pxsc" ${pxsc eq 'Y'?'checked':''}/>培训手册&nbsp;
                    </td>
                    <%--<td colspan="1">--%>
                        <%--<input type="button" class="btn_green" value="查&#12288;询" id="searchDoctor" onclick="search()">--%>
                        <%--<c:if test="${param.checkStatusId eq 'Passing'}">--%>
                            <%--&nbsp;<input type="button" class="btn_green" value="一键审核" id="checkDoctor" onclick="updateCheck()">--%>
                        <%--</c:if>--%>
                        <%--<c:if test="${param.checkStatusId ne 'Passing'}">--%>
                            <%--&nbsp;<input type="button" class="btn_green" value="退&#12288;回" id="sendBackDoctor" onclick="sendBack()">--%>
                        <%--</c:if>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--审核状态：--%>
                        <%--<select name="checkStatusId" class="select" style="width: 126px;">--%>
                            <%--<c:if test="${param.checkStatusId eq 'Passing'}">--%>
                                <%--<option value="Passing" selected>待审核</option>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${param.checkStatusId ne 'Passing'}">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--&lt;%&ndash;<option value="Passing" ${param.checkStatusId eq 'Passing'?'selected':''}>待审核</option>&ndash;%&gt;--%>
                                <%--<option value="Passed" ${param.checkStatusId eq 'Passed'?'selected':''}>审核通过</option>--%>
                                <%--<option value="UnPassed" ${param.checkStatusId eq 'UnPassed'?'selected':''}>审核不通过</option>--%>
                            <%--</c:if>--%>
                        <%--</select>--%>
                    <%--</td>--%>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" class="btn_green" value="查&#12288;询" id="searchDoctor" onclick="search()">
                        <c:if test="${param.checkStatusId eq 'Passing'}">
                            &nbsp;<input type="button" class="btn_green" value="一键审核" id="checkDoctor" onclick="updateCheck()">
                        </c:if>
                        <c:if test="${param.checkStatusId ne 'Passing'}">
                            &nbsp;<input type="button" class="btn_green" value="退&#12288;回" id="sendBackDoctor" onclick="sendBack()">
                            &nbsp;<input type="button" class="btn_green" value="导&#12288;出" id="exportDoc" onclick="exportDoc1()">
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="contentDiv">
        <div class="search_table" style="width: 900px;height: auto;margin-top: 5px;margin-bottom: 10px;overflow-x: auto;margin-left: 0px;">
            <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%;">
                <tr>
                    <th rowspan="2" style="min-width: 70px; max-width: 70px; ">培训基地</th>
                    <th rowspan="2" style="min-width: 50px; max-width: 50px; ">年级</th>
                    <th rowspan="2" style="min-width: 80px; max-width: 80px; ">姓名</th>
                    <th rowspan="2" style="min-width: 80px; max-width: 80px; ">证件号码</th>
                    <th rowspan="2" style="min-width: 80px; max-width: 80px; ">培养年限</th>
                    <th rowspan="2" style="min-width: 80px; max-width: 80px; ">APP登录<br/>权限</th>
                    <th colspan="3" style="min-width: 240px; max-width: 240px; ">付费功能</th>
                    <th rowspan="2" style="min-width: 80px; max-width: 80px; ">数据审核区间</th>
                    <th rowspan="2" style="min-width: 80px; max-width: 80px; ">出科考试权限</th>
                    <th rowspan="2" style="min-width: 80px; max-width: 80px; ">结业理论模拟考核</th>
                    <th rowspan="2" style="min-width: 80px; max-width: 80px; ">培训手册</th>
                    <th rowspan="2" style="min-width: 80px; max-width: 80px; ">审核状态
                        <c:if test="${param.checkStatusId eq 'Passing'}">
                            <br>
                            <input type="checkbox" name="checkStatusName"/>
                        </c:if>
                    </th>
                    <c:if test="${param.checkStatusId ne 'Passing'}">
                        <th rowspan="2" style="min-width: 80px; max-width: 80px; ">审核意见</th>
                        <th rowspan="2" style="min-width: 80px; max-width: 80px; ">开通时间</th>
                        <th rowspan="2" style="min-width: 80px; max-width: 80px; ">审核时间</th>
                        <th rowspan="2" style="min-width: 80px; max-width: 80px; ">操作
                            <br>
                            <input type="checkbox" name=""/>
                        </th>
                    </c:if>
                </tr>
                <tr>
                    <th style="min-width: 80px; max-width: 80px; ">教学活动</th>
                    <th style="min-width: 80px; max-width: 80px; ">考勤功能</th>
                    <th style="min-width: 80px; max-width: 80px; ">其他付费功能</th>
                </tr>
                <c:forEach items="${list }" var="userExt">
                    <tr>
                        <td>${userExt.orgName }</td>
                        <td>${userExt.sessionNumber }</td>
                        <td>${userExt.userName }</td>
                        <td>${userExt.idNo }</td>
                        <td>
                            <c:choose>
                                <c:when test="${userExt.trainingYears eq 'OneYear'}">
                                    一年
                                </c:when>
                                <c:when test="${userExt.trainingYears eq 'TwoYear'}">
                                    两年
                                </c:when>
                                <c:when test="${userExt.trainingYears eq 'ThreeYear'}">
                                    三年
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:set var="key" value="jsres_doctor_app_login_${userExt.userFlow}"/>
                            <input type="checkbox" disabled id="jsres_doctor_app_login_${userExt.userFlow}" name="appLogins" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','jsres_doctor_app_login_');"/>
                        </td>
                        <%-- 活动--%>
                        <td>
                            <c:set var="activity_key" value="jsres_doctor_activity_${userExt.userFlow}"/>
                            <input type="checkbox" disabled id="jsres_doctor_activity_${userExt.userFlow}" name="appLogins" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(activity_key) == GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow}','jsres_doctor_activity_');"/>
                        </td>
                        <td>
                            <c:set var="attendance_key" value="jsres_doctor_attendance_${userExt.userFlow}"/>
                            <input type="checkbox" disabled id="jsres_doctor_attendance_${userExt.userFlow}" name="appLogins" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(attendance_key) == GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','jsres_doctor_attendance_');"/>
                        </td>
                        <%--其他--%>
                        <td>
                            <c:set var="menu_key" value="jsres_doctor_app_menu_${userExt.userFlow}"/>
                            <input type="checkbox" disabled id="jsres_doctor_app_menu_${userExt.userFlow}" name="appMenus" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(menu_key) != GlobalConstant.FLAG_Y?'disabled':''}  ${pdfn:jsresPowerCfgMap(menu_key)==GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','jsres_doctor_app_menu_');"/>
                        </td>
                        <td   id="jsres_doctor_data_time_${userExt.userFlow}">
                            <c:set var="cfg" value="${cfgMap[userExt.userFlow]}"/>
                                ${cfg.powerStartTime}<c:if test="${!((empty cfg.powerStartTime) and (empty cfg.powerEndTime))}">至</c:if>${cfg.powerEndTime}
                        </td>
                        <td>
                            <c:set var="key2" value="jsres_doctor_exam_${userExt.userFlow}"/>
                            <input type="checkbox" disabled id="jsres_doctor_exam_${userExt.userFlow}" name="outProcessExams" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','jsres_doctor_exam_');"/>
                        </td>
                        <td>
                            <c:set var="key2" value="jsres_doctor_graduation_exam_${userExt.userFlow}"/>
                            <input type="checkbox" disabled id="jsres_doctor_graduation_exam_${userExt.userFlow}" name="outGraduationExams" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','jsres_doctor_graduation_exam_');"/>
                        </td>
                        <td>
                            <c:set var="key3" value="jsres_doctor_manual_${userExt.userFlow}"/>
                            <input type="checkbox" disabled id="jsres_doctor_manual_${userExt.userFlow}" name="schManuals" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key3)==GlobalConstant.FLAG_Y?'checked':''} onchange="oper(this,'${userExt.userFlow }','jsres_doctor_manual_');"/>
                        </td>
                        <td>
                            <c:if test="${userExt.checkStatusId eq 'Passing'}">
                                <input type="button" value="审核" style="background: beige;border: 1px solid #ccc;border-radius: 3px;padding: 1px 5px; cursor:pointer"
                                       onclick="updateCheckOne('${userExt.userFlow}')"/>
                                <input type="checkbox" id="checkStatusId" name="checkStatusId" value="${userExt.userFlow}" style="display: none"/>
                            </c:if>
                            <c:if test="${userExt.checkStatusId ne 'Passing'}">
                                ${userExt.checkStatusName }
                            </c:if>
                        </td>
                        <c:if test="${param.checkStatusId ne 'Passing'}">
                            <td title="${userExt.checkReason}">${pdfn:cutString(userExt.checkReason,5,true,3) }</td>
                            <td>${userExt.submitTime}</td>
                            <td>${userExt.checkTime}</td>
                            <td><input type="button" value="退回" style="background: beige;border: 1px solid #ccc;border-radius: 3px;padding: 1px 5px; cursor:pointer"
                                       onclick="sendBackOne('${userExt.userFlow}')"/>
                                <input type="checkbox" id="sendBackId" name="sendBackId" value="${userExt.userFlow}" style="display: none"/>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${empty list}">
                    <tr>
                        <td colspan="15">暂无记录！</td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>
