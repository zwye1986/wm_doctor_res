<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript"
            src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#subjectYear').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode: 2,
                format: 'yyyy'
            });
        });

        function saveSubject() {
            jboxStartLoading();
            if (false == $("#editForm").validationEngine("validate")) {
                jboxEndLoading();
                return false;
            }
            var subjectEdit = $("input[name='subjectEdit']:checked").val();
            if ("allSubject" != subjectEdit) {
                var speIds = "";
                var speId = "";
                $("input[name='speId']").each(function (i) {
                    speIds += "&speIds=" + $(this).val();
                });
                if (speIds == "") {
                    speId = $("#speId").find("option:selected").val();
                }
                if (speIds == '' && speId == '') {
                    jboxTip("请选择检查专业");
                    jboxEndLoading();
                    return false;
                }
                var orgFlows = "";
                var orgFlow = "";
                $("input[name='orgFlow']").each(function (i) {
                    orgFlows += "&orgFlows=" + $(this).val();
                });
                if (orgFlows == "") {
                    orgFlow = $("#orgFlow").find("option:selected").val();
                }
                if (orgFlows == '' && orgFlow == '') {
                    jboxTip("请选择检查基地");
                    jboxEndLoading();
                    return false;
                }
            }

            var userFlows = "";
            $("input[name='userFlow']").each(function (i) {
                userFlows += "&userFlows=" + $(this).val();
            });

            var subjectYear = $("#subjectYear").val();
            if (subjectYear == null || subjectYear == "") {
                jboxTip("检查年份不能为空！");
                jboxEndLoading();
                return false;
            }

            var devTime = $("#devTime").val();
            var devTimeClose = $("#devTimeClose").val();
            var openTime = $("#openTime").val();
            var closedTime = $("#closedTime").val();
            if (devTime > devTimeClose) {
                jboxTip("基地自评开始时间不能大于结束时间！");
                jboxEndLoading();
                return false;
            }
            if (openTime!=null && openTime!='' && openTime!=undefined && closedTime!=null && closedTime!='' && closedTime!=undefined){
                if (openTime > closedTime) {
                    jboxTip("督导组开始时间不能大于结束时间！");
                    jboxEndLoading();
                    return false;
                }
                if (openTime < devTimeClose) {
                    jboxTip("督导组评审开始时间不能小于基地自评结束时间！");
                    jboxEndLoading();
                    return false;
                }
            }
            /*  if (openTime > closedTime) {
                  jboxTip("督导组开始时间不能大于结束时间！");
                  jboxEndLoading();
                  return false;
              }
              if (openTime < devTimeClose) {
                  jboxTip("督导组评审开始时间不能小于基地自评结束时间！");
                  jboxEndLoading();
                  return false;
              }*/
            subjectEdit = $("input[name='subjectEdit']:checked").val();
            var orgName = null;
            var speName = null;
            if ("orgSubject" == subjectEdit) {
                orgName = $("#orgFlow").find("option:selected").text();
            } else if ("speSubject" == subjectEdit) {
                speName = $("#speId").find("option:selected").text();
            }
            var url = "";
            if ("${type}" == "edit") {
                url = "<s:url value='/jsres/supervisio/updateSubject'/>?" + userFlows + "&orgName=" + orgName + "&speName=" + speName + "&orgFlow=" + orgFlow + "&speId=" + speId + "&subjectEditFlag=" + subjectEdit;
                if ("allSubject" == subjectEdit) {
                    url = "<s:url value='/jsres/supervisio/updateSubject'/>?" + userFlows + "&orgName=" + orgName + "&speName=" + speName + "&orgFlow=" + orgFlow + "&speId=" + speId + "&subjectEditFlag=" + subjectEdit;
                }
            } else {
                url = "<s:url value='/jsres/supervisio/saveSubjectNew'/>?" + speIds + userFlows + orgFlows + "&orgName=" + orgName + "&speName=" + speName + "&orgFlow=" + orgFlow + "&speId=" + speId;
                if ("allSubject" == subjectEdit) {
                    url = "<s:url value='/jsres/supervisio/saveSubjectNew'/>?"  + userFlows;
                }
            }
            var data = $('#editForm').serialize();

            jboxPost(url, data, function (resp) {
                if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    window.parent.toPage(1);
                    setTimeout(function () {
                        top.jboxCloseMessager();
                    }, 3000);
                    jboxEndLoading();
                    jboxTip(resp);
                } else if (resp == '${GlobalConstant.SAVE_FAIL}') {
                    jboxTip(resp);
                }else if (resp=='${GlobalConstant.CRM_CUSTOMER_NAME_EXIST}'){
                    jboxEndLoading();
                    jboxTip("该项目名称已存在！");
                }else if (resp=='${GlobalConstant.NOT_BASE_EXPERT}'){
                    jboxEndLoading();
                    jboxTip(resp);
                }
            }, null, false);
        }


        function delOrgFlow() {
            var aList = $("a");
            for (var i = 0; i < aList.length; i++) {
                if (aList[i].getAttribute("name") == "delOrgFlow"){
                    aList[i].onclick();
                }
            }
        }
        //查询基地代码或者基地
        function searchBaseCode(identification) {
            //估计基地代码查询基地
            if (identification=="baseCode"){
                var baseCode = $('#baseCode').val();
                if (baseCode==null || baseCode=='' || baseCode==undefined){
                    top.jboxTip("请填写基地代码！");
                }
                var url = "<s:url value='/jsres/supervisio/searchBaseInfo'/>";
                var data = $('#editForm').serialize();
                jboxPost(url, data, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_FAIL}") {
                        top.jboxTip("查无此基地！");
                    } else {
                        $("#orgFlow").val(resp);
                        var aList = $("a");
                        for (var i = 0; i < aList.length; i++) {
                            if (aList[i].getAttribute("name") == "delSpeId"){
                                aList[i].onclick();
                            }
                        }
                    }
                }, null, false);
                //根据基地程序基地代码
            } else if (identification=="orgFlow"){
                var aList = $("a");
                for (var i = 0; i < aList.length; i++) {
                    if (aList[i].getAttribute("name") == "delSpeId"){
                        aList[i].onclick();
                    }
                }

                var orgFlow=$("#orgFlow").val();
                var url = "<s:url value='/jsres/supervisio/searchBaseInfo'/>?orgFlow="+orgFlow;
                jboxPost(url, null, function (resp) {
                    if (resp != "${GlobalConstant.OPERATE_FAIL}") {
                        $("#baseCode").val(resp);
                    }
                }, null, false);
            }
        }

        function showOrgSpe(subjectAddFalg) {
            if ("orgSubject" == subjectAddFalg) {
                $("#orgSubjectTr1").show();
                $("#orgSubjectTr2").show();
                $("#speSubjectTr1").hide();
                $("#speSubjectTr2").hide();
                $("#orgSubjectTr2").show();
                $("#orgSubjectTr3").show();
                var selectOrgFlow = window.parent.frames["jbox-message-iframe"].$("#orgFlow").find("option:selected").val();
                if (selectOrgFlow != '' && selectOrgFlow != null) {
                    window.parent.frames["jbox-message-iframe"].$("#orgFlow").find("option:selected").removeAttr("selected");
                }
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=speId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=speIdName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv [name=delSpeId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=orgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=orgFlowName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv [name=delOrgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=speId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=speIdName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv [name=delSpeId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=orgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=orgFlowName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv [name=delOrgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#subjectUserDiv input[name=userFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#subjectUserDiv input[name=userFlowName]").remove();
            } else if ("speSubject" == subjectAddFalg) {
                $("#speSubjectTr1").show();
                $("#speSubjectTr2").show();
                $("#orgSubjectTr1").hide();
                $("#orgSubjectTr2").hide();
                $("#orgSubjectTr3").hide();

                var selectSpeId = window.parent.frames["jbox-message-iframe"].$("#speId").find("option:selected").val();
                if (selectSpeId != '' && selectSpeId != null) {
                    window.parent.frames["jbox-message-iframe"].$("#speId").find("option:selected").removeAttr("selected");
                }
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=speId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=speIdName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=orgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=orgFlowName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=speId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=speIdName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=orgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=orgFlowName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#subjectUserDiv input[name=userFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#subjectUserDiv input[name=userFlowName]").remove();
            }else if ("allSubject"==subjectAddFalg){
                $("#orgSubjectTr1").hide();
                $("#orgSubjectTr2").hide();
                $("#speSubjectTr1").hide();
                $("#speSubjectTr2").hide();
                $("#orgSubjectTr2").hide();
                $("#orgSubjectTr3").hide();
                var selectOrgFlow = window.parent.frames["jbox-message-iframe"].$("#orgFlow").find("option:selected").val();
                if (selectOrgFlow != '' && selectOrgFlow != null) {
                    window.parent.frames["jbox-message-iframe"].$("#orgFlow").find("option:selected").removeAttr("selected");
                }
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=speId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=speIdName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv [name=delSpeId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=orgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=orgFlowName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv [name=delOrgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=speId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=speIdName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv [name=delSpeId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=orgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=orgFlowName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv [name=delOrgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#subjectUserDiv input[name=userFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#subjectUserDiv input[name=userFlowName]").remove();
                var selectSpeId = window.parent.frames["jbox-message-iframe"].$("#speId").find("option:selected").val();
                if (selectSpeId != '' && selectSpeId != null) {
                    window.parent.frames["jbox-message-iframe"].$("#speId").find("option:selected").removeAttr("selected");
                }
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=speId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=speIdName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=orgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv input[name=orgFlowName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=speId]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=speIdName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=orgFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv input[name=orgFlowName]").remove();
                window.parent.frames["jbox-message-iframe"].$("#subjectUserDiv input[name=userFlow]").remove();
                window.parent.frames["jbox-message-iframe"].$("#subjectUserDiv input[name=userFlowName]").remove();
            }
        }
        $(document).ready(function () {
            if ("orgSubject" == "${subject.subjectEdit}") {
                $("#orgSubjectTr1").show();
                $("#orgSubjectTr2").show();
                $("#speSubjectTr1").hide();
                $("#speSubjectTr2").hide();
            } else if ("speSubject" == "${subject.subjectEdit}") {
                $("#speSubjectTr1").show();
                $("#speSubjectTr2").show();
                $("#orgSubjectTr1").hide();
                $("#orgSubjectTr2").hide();
            }
            if ("edit" == "${type}") {
                if ("orgSubject" == "${subject.subjectEdit}") {
                    var speId2 = "${subject.speId}";
                    var speName = "${subject.speName}";
                    var html = '<input readonly hidden id="' + speId2 + '" name="speId" value="' + speId2 + '"></input><input readonly name="speIdName" id="' + speId2 + 'name" style="width: 140px;height: 30px" value="' + speName + '"></input>';
                    window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv").append(html);
                }
                if ("speSubject" == "${subject.subjectEdit}") {
                    var orgFlow2 = "${subject.orgFlow}";
                    var orgName = "${subject.orgName}";
                    var html = '<input readonly hidden id="' + orgFlow2 + '" name="orgFlow" value="' + orgFlow2 + '"></input><input name="orgFlowName" readonly id="' + orgFlow2 + 'name" style="width: 250px;height: 30px" value="' + orgName + '"></input>';
                    window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv").append(html);
                }

                if (${not empty userList}) {
                    <c:forEach items="${userList}" var="user">
                    <c:if test="${user.userLevelID=='management'}">
                    var userFlow2 = "${user.userFlow}";
                    var userName = "${user.userName}";
                    var html = '<input readonly hidden id="' + userFlow2 + '" name="userFlow"  itemId="userFlow2" value="' + userFlow2 + '"></input>' +
                        '<input readonly name="userFlowName" id="' + userFlow2 + 'name" style="width: 93px;height: 30px" value="' + userName + '"></input>' +
                        '<a id="del' + userFlow2 + '" name="delSpeId" style="margin-right: 40px;" href=\'#\' onclick=\'delParam("' + userFlow2 + '")\'><img alt="删除" src="<s:url value="/css/skin/LightBlue/images/del3.png"/>"></a>';
                    window.parent.frames["jbox-message-iframe"].$("#subjectUserDivment").append(html);
                    </c:if>
                    <c:if test="${user.userLevelID=='expertLeader'}">
                    var userFlow2 = "${user.userFlow}";
                    var userName = "${user.userName}";
                    var html = '<input readonly hidden id="' + userFlow2 + '" name="userFlow" itemId="userFlow1"  value="' + userFlow2 + '"></input>' +
                        '<input readonly name="userFlowName" id="' + userFlow2 + 'name" style="width: 93px;height: 30px" value="' + userName + '"></input>' +
                        '<a id="del' + userFlow2 + '" name="delSpeId" style="margin-right: 40px;" href=\'#\' onclick=\'delParam("' + userFlow2 + '")\'><img alt="删除" src="<s:url value="/css/skin/LightBlue/images/del3.png"/>"></a>';
                    window.parent.frames["jbox-message-iframe"].$("#subjectUserDiv").append(html);
                    </c:if>
                    </c:forEach>
                }
            }
        });

        function selectSpeByOrgFlow(subjectEdit) {
            var title = "专业";
            if ("orgSubject" == subjectEdit) {
                var orgFlow = $("#orgFlow").val();
                if (orgFlow == null || orgFlow == '') {
                    jboxTip("请选择基地");
                    return false;
                }
            }
            if ("speSubject" == subjectEdit) {
                var speId = $("#speId").val();
                if (speId == null || speId == '') {
                    jboxTip("请选择专业");
                    return false;
                }
                title = "基地";
            }
            var url = '<s:url value="/jsres/supervisio/selectSpeByOrgFlow"/>?orgFlow=' + orgFlow + "&type=${type}&subjectEdit=" + subjectEdit + "&speId=" + speId;
            jboxOpen(url, title, 800, 600);

        }


        function selectUser(identification) {
            //identification是标识，区分管理专家、专业专家
            var url = "<s:url value='/jsres/supervisio/selectUser'/>?identification="+identification;
            if (identification=="management"){
                jboxOpen(url, "管理专家", 800, 650);
            }else if (identification=="expert") {
                jboxOpen(url, "专业专家", 800, 650);
            }

        }

        function delParam(value) {
            window.parent.frames["jbox-message-iframe"].$("#" + value).remove();
            window.parent.frames["jbox-message-iframe"].$("#" + value + "name").remove();
            window.parent.frames["jbox-message-iframe"].$("#del" + value).remove();
        }

    </script>
</head>

<body>
<div class="infoAudit">
    <div class="div_table">
        <form id="editForm" style="position: relative;" method="post">
            <input type="hidden" name="subjectFlow" value="${subject.subjectFlow }"/>
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
                <colgroup>
                    <col width="35%"/>
                    <col width="65%"/>
                </colgroup>
                <tbody>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>项目名称：</th>
                    <td>
                        <input class="validate[required] input" style="width: 270px;" name="subjectName" type="text"
                               value="${subject.subjectName }"
                               <c:if test="${type eq 'edit'}">readonly</c:if>/>
                    </td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>新建项目：</th>
                    <td>
                        <input class="validate[required]" id="orgSubject" name="subjectEdit" type="radio"
                               value="orgSubject"
                               <c:if test="${subject.subjectEdit eq 'orgSubject'}">checked="checked"</c:if>
                               <c:if test="${type eq 'edit'}">disabled</c:if>
                               onchange="showOrgSpe(this.value);">&nbsp;<label for="orgSubject">按基地</label>&#12288;
                        <input class="validate[required]" id="speSubject" name="subjectEdit" type="radio"
                               value="speSubject"
                               <c:if test="${subject.subjectEdit eq 'speSubject'}">checked="checked"</c:if>
                               <c:if test="${type eq 'edit'}">disabled</c:if>
                               onchange="showOrgSpe(this.value);">&nbsp;<label for="speSubject">按专业</label>
                        &#12288;&#12288;&#12288;&#12288;
                        <input class="validate[required]" id="allSubject" name="subjectEdit" type="radio"
                               value="allSubject"
                               <c:if test="${subject.subjectEdit eq 'allSubject'}">checked="checked"</c:if>
                               <c:if test="${type eq 'edit'}">disabled</c:if>
                               onchange="showOrgSpe(this.value);">&nbsp;<label for="allSubject">全省年度督导</label>
                    </td>
                </tr>
                <tr style="display: none" id="orgSubjectTr1">
                    <th><span style="color:red;">*&nbsp;</span>督导基地：</th>
                    <td>
                        <select id="orgFlow" class="select" style="width: 275px;margin-left: 4px" onchange="searchBaseCode('orgFlow');">
                            <c:if test="${type ne 'edit'}">
                                <option value="">请选择</option>
                                <c:forEach items="${orgs}" var="org">
                                    <option value="${org.orgFlow}" ${subject.orgFlow eq org.orgFlow ? 'selected':''}
                                    >${org.orgName}</option>
                                </c:forEach>
                            </c:if>
                            <c:if test="${type eq 'edit'}">
                                <option value="${subject.orgFlow}" selected>${subject.orgName}</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr style="display: none" id="orgSubjectTr2">
                    <th><span style="color:red;">*&nbsp;</span>基地代码：</th>
                    <td>
                        <input class="input" style="width: 270px;" name="baseCode" id="baseCode" type="text" value="${subject.baseCode}"/>
                        <a style="width: 42px;background-color: #44b549;color: white" class="btn" href="javascript:void(0);"
                           onclick="searchBaseCode('baseCode');">查&#12288;询</a><br>
                    </td>
                </tr>
                <c:if test="${type eq 'edit' and subject.subjectEdit eq 'orgSubject'}">
                    <tr>
                        <th>督导专业：</th>
                        <td>
                            <input class="input" style="width: 270px;"  type="text"
                                   value="${subject.speName }" readonly/>
                        </td>
                    </tr>
                </c:if>
                <tr style="display: none" id="orgSubjectTr3">
                    <th><span style="color:red;">*&nbsp;</span>督导专业：</th>
                    <td>
                        <div id="orgSubjectDiv">
                            <c:if test="${type eq 'add'}">
                                <a style="width: 112px" class="btn" href="javascript:void(0);"
                                   onclick="selectSpeByOrgFlow('orgSubject');">点击选择专业</a><br>
                            </c:if>
                        </div>
                    </td>
                </tr>
                <tr style="display: none" id="speSubjectTr2">
                    <th><span style="color:red;">*&nbsp;</span>督导基地：</th>
                    <td>
                        <div id="speSubjectDiv">
                            <c:if test="${type eq 'add'}">
                                <a style="width: 112px;" class="btn" href="javascript:void(0);"
                                   onclick="selectSpeByOrgFlow('speSubject');">点击选择基地</a><br>
                            </c:if>
                        </div>
                    </td>
                </tr>
                <tr style="display: none" id="speSubjectTr1">
                    <th><span style="color:red;">*&nbsp;</span>督导专业：</th>
                    <td>
                        <select id="speId" class="select" style="width: 275px;margin-left: 4px" onchange="delOrgFlow();">
                            <c:if test="${type ne 'edit'}">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" ${subject.speId eq dict.dictId?'selected':''}
                                            <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>
                                            <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>
                                    >${dict.dictName}</option>
                                </c:forEach>
                            </c:if>
                            <c:if test="${type eq 'edit'}">
                                <option value="${subject.speId}" selected>${subject.speName}</option>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>管理专家：</th>
                    <td>
                        <div id="subjectUserDivment">
                            <a class="btn" href="javascript:void(0);" onclick="selectUser('management');">选择专家</a><br>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>专业专家：</th>
                    <td>
                        <div id="subjectUserDiv">
                            <a class="btn" href="javascript:void(0);" onclick="selectUser('expert');">选择专家</a><br>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>督导年份：</th>
                    <td><input class="input" name="subjectYear" id="subjectYear" type="text"
                               value="${subject.subjectYear==null?'2022':subject.subjectYear}"/>
                    </td>
                </tr>
                <tr>
                    <th><span style="color:red;">*&nbsp;</span>基地自评开放时间：</th>
                    <td>
                        <input name="devTime" id="devTime"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                               onchange="checkJointLocalStart()" value="${subject.devTime }"
                               class="input validate[required]">
                        至
                        <input name="devTimeClose" id="devTimeClose"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                               onchange="checkJointLocalStart()" value="${subject.devTimeClose }"
                               class="input validate[required]">
                    </td>
                </tr>
                <tr>
                    <th>督导组评审开放时间：</th>
                    <td>
                        <input name="openTime" id="openTime"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                               onchange="checkJointLocalStart()" value="${subject.openTime }"
                               class="input">
                        至
                        <input name="closedTime" id="closedTime"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                               onchange="checkJointLocalStart()" value="${subject.closedTime }"
                               class="input">
                    </td>
                </tr>
                <%--<tr>
                    <th>督导总结：</th>
                    <td>
                        <textarea name="supervisioSummary"  style="width: 96%" class="text-input validate[required,maxSize[1000]]">${subject.supervisioSummary}</textarea>
                    </td>
                </tr>--%>
                </tbody>
            </table>
        </form>

        <div class="button">
            <input type="button" class="btn_green" onclick="saveSubject();" value="保&#12288;存"/>
            <input type="button" class="btn_green" onclick="top.jboxCloseMessager();" value="关&#12288;闭"/>
        </div>
    </div>
</div>
</body>
</html>