<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script type="text/javascript">
        var width=1005;
        var height=650;
        function search() {
            $("#gradeForm").submit();
        }
        function toPage(page){
            $("#currentPage").val(page);
            jboxPostLoad("gradeDiv","<s:url value="/gzykdx/orgAudit/teacherApplyList"/>",$("#gradeForm").serialize(),true);
        }
        function auditTeacherApply(applyFlow,detailFlag) {
            var page=$("#currentPage").val();
            jboxStartLoading();
            jboxOpen("<s:url value='/gzykdx/teaAndDoc/edit?applyFlow='/>" + applyFlow+"&scope=org&detailFlag="+detailFlag+"&currentPage="+page, "审核", width, height);
        }
        function commitAll(year){
            if(${pdfn:getCurrDateTime('yyyy-MM-dd') ge sysCfgMap.teacher_target_start_date
                        and pdfn:getCurrDateTime('yyyy-MM-dd') le sysCfgMap.teacher_target_end_date}) {
                jboxConfirm("确认提交年度（" + year + "）所有指标信息至学校审核吗？", function () {
                    var url = "<s:url value='/gzykdx/orgAudit/commitAll'/>";
                    jboxPost(url, null, function (resp) {
                        jboxTip(resp);
                        setTimeout("window.parent.frames['mainIframe'].location.reload(true);jboxClose();", 1000)
                    }, null, false);
                });
            }else {
                jboxTip("当前时间不在导师指标上报时间内！");
            }
        }

        function commitOneApply(applyFlow){
            var page = '${currentPage}';
            if(${pdfn:getCurrDateTime('yyyy-MM-dd') ge sysCfgMap.teacher_target_start_date
                        and pdfn:getCurrDateTime('yyyy-MM-dd') le sysCfgMap.teacher_target_end_date}) {
                jboxConfirm("确认提交该指标信息至学校审核吗？", function () {
                    var url = "<s:url value='/gzykdx/orgAudit/commitOneApply?applyFlow='/>" + applyFlow;
                    jboxPost(url, null, function (resp) {
                        jboxTip(resp);
                        setTimeout(function(){
                            window.parent.frames['mainIframe'].window.toPage(page);
                            jboxClose();
                        },2000);
                    }, null, false);
                });
            }else {
                jboxTip("当前时间不在导师指标上报时间内！");
            }
        }

        function editApplyInfo(applyFlow,type,valtext,obj){
            var reg=/^(0|([1-9]\d*))(\.\d+)?$/;
            if(type == 'revoke'){
                jboxConfirm("确定撤销吗？",function() {
                    var page = '${currentPage}';
                    var url = "<s:url value='/gzykdx/orgAudit/editApplyInfo?applyFlow='/>" + applyFlow+"&auditStatusId=WaitingForOrg";
                    jboxPost(url, null, function (resp) {
                        jboxTip(resp);
                        setTimeout(function(){
                            window.parent.frames['mainIframe'].window.toPage(page);
                            jboxClose();
                        },1000);
                    }, null, false);
                });
            }
            if(type == 'academicNum'){
                var val=$("input[name='academicNum']").val();
                var totle1='${recruitNum.ACADEMIC_NUM}';
                if(!reg.test(valtext)||valtext=='0'){
                    jboxTip("请输入正整数！");
                    $(obj).val("");
                    return;
                }
                //验证剩余指标
                var url = "<s:url value='/gzykdx/orgAudit/countApplyNum?applyFlow='/>" + applyFlow+"&type=academicNum";
                jboxPost(url, null, function (resp) {
                    var page = '${currentPage}';
                    if(valtext > resp){
                        jboxTip("编辑总人数大于剩余指标人数！");
                        setTimeout(function(){
                            window.parent.frames['mainIframe'].window.toPage(page);
                            jboxClose();
                        },1000);
                    }else{
                        saveIndexNum(applyFlow,type,valtext);
                    }
                }, null, false);
            }
            if(type == 'specializedNum'){
                var val1=$("input[name='specializedNum']").val();
                var totle2='${recruitNum.SPECIALIZED_NUM}';
                if(!reg.test(valtext)||valtext=='0'){
                    jboxTip("请输入正整数！");
                    $(obj).val("");
                    return;
                }
                //验证剩余指标
                var url1 = "<s:url value='/gzykdx/orgAudit/countApplyNum?applyFlow='/>" + applyFlow+"&type=specializedNum";
                jboxPost(url1, null, function (resp) {
                    var page = '${currentPage}';
                    if(valtext > resp){
                        jboxTip("编辑总人数大于剩余指标人数！");
                        setTimeout(function(){
                            window.parent.frames['mainIframe'].window.toPage(page);
                            jboxClose();
                        },1000);
                    }else{
                        saveIndexNum(applyFlow,type,valtext);
                    }
                }, null, false);
            }
        }
        function saveIndexNum(applyFlow,type,val){
            var url = "<s:url value='/gzykdx/orgAudit/saveIndexNum?applyFlow='/>" + applyFlow+"&type="+type+"&val="+val;
            jboxPost(url, null, function (resp) {
                var page = '${currentPage}';
                jboxTip(resp);
                setTimeout(function(){
                    window.parent.frames['mainIframe'].window.toPage(page);
                    jboxClose();
                },1000);
            }, null, false);
        }

//        function toPage(page) {
//            var form = $("#searchForm");
//            $("#currentPage").val(page);
//            jboxStartLoading();
//            form.submit();
//        }

        function exportExcel(){
            var url = "<s:url value='/gzykdx/orgAudit/exportTeacherApply'/>";
            jboxTip("导出中……");
            jboxSubmit($("#gradeForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>

<div class="mainright">
    <div class="content">
        <div id="gradeDiv" class="labelDiv">
            <div class="title1 clearfix">
            <form id="gradeForm" action="<s:url value="/gzykdx/orgAudit/teacherApplyList"/>" method="post">
                <div class="choseDivNewStyle">
                    <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
                    <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                        <tr>
                            <td style="border:0px;">
                                <span style="margin-left: -10px;"></span>年份：
                                <input type="text" name="recruitYear" value="${(empty param.recruitYear)?recruitYear:param.recruitYear}" style="width:137px;" class="xltext" onClick="WdatePicker({dateFmt:'yyyy'})">
                                专业名称：
                                <select name="speId" style="width:137px;" class="xlselect">
                                    <option value="">全部</option>
                                    <c:forEach items="${dictTypeEnumGzykdxSpeList}" var="status">
                                        <option value="${status.dictId}" ${param.speId eq status.dictId ?'selected':''}>${status.dictName}[${status.dictId}]</option>
                                    </c:forEach>
                                </select>
                                研究方向：
                                <input type="text" name="researchDirectionName" value="${param.researchDirectionName}" style="width:137px;" class="xltext"/>
                            <%--<select name="researchDirectionId" class="xlselect" style="width:137px;">--%>
                                    <%--<option value="">请选择</option>--%>
                                    <%--<c:forEach items="${dictTypeEnumResearchAreaList }" var="dict">--%>
                                        <%--<option--%>
                                                <%--<c:if test="${param.researchDirectionId eq dict.dictId }">selected="selected"</c:if>--%>
                                                <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                                <input type="button" class="search" onclick="toPage(1);" value="查&#12288;询"><br/>
                                <span style="margin-left: -10px;"></span>姓名：
                                <input type="text" name="userName" value="${param.userName}" style="width:137px;" class="xltext"/>
                                审核结果：
                                <input type="radio" name="auditStatusId" id="achStatusEnumAll"
                                       <c:if test="${empty param.auditStatusId }">checked="checked"</c:if>
                                       value=""/><label for="achStatusEnumAll">全部</label>
                                <input type="radio" name="auditStatusId" id="achStatusEnum_OrgAudit"
                                       <c:if test="${param.auditStatusId eq gzykdxAuditStatusEnumWaitingForOrg.id}">checked="checked"</c:if>
                                       value="${gzykdxAuditStatusEnumWaitingForOrg.id }"/><label
                                    for="achStatusEnum_OrgAudit">待审核</label>
                                <input type="radio" name="auditStatusId" id="achStatusEnum_WaitingForCommitted"
                                       <c:if test="${param.auditStatusId eq gzykdxAuditStatusEnumWaitingForCommitted.id }">checked="checked"</c:if>
                                       value="${gzykdxAuditStatusEnumWaitingForCommitted.id }"/><label
                                    for="achStatusEnum_WaitingForCommitted">${gzykdxAuditStatusEnumWaitingForCommitted.name}</label>
                                <input type="radio" name="auditStatusId" id="achStatusEnum_SchoolAudit"
                                       <c:if test="${param.auditStatusId eq gzykdxAuditStatusEnumWaitingForSchool.id }">checked="checked"</c:if>
                                       value="${gzykdxAuditStatusEnumWaitingForSchool.id }"/><label
                                    for="achStatusEnum_SchoolAudit">${gzykdxAuditStatusEnumWaitingForSchool.name}</label>
                                <input type="radio" name="auditStatusId" id="achStatusEnum_Passed"
                                       <c:if test="${param.auditStatusId eq gzykdxAuditStatusEnumSchoolPassed.id }">checked="checked"</c:if>
                                       value="${gzykdxAuditStatusEnumSchoolPassed.id }"/><label
                                    for="achStatusEnum_Passed">${gzykdxAuditStatusEnumSchoolPassed.name}</label>
                                <input type="radio" name="auditStatusId" id="achStatusEnum_UnPassed"
                                       <c:if test="${param.auditStatusId eq gzykdxAuditStatusEnumUnPassed.id }">checked="checked"</c:if>
                                       value="${gzykdxAuditStatusEnumUnPassed.id }"/><label
                                    for="achStatusEnum_UnPassed">${gzykdxAuditStatusEnumUnPassed.name}</label>
                                <input type="radio" name="auditStatusId" id="achStatusEnum_OrgSendBack"
                                       <c:if test="${param.auditStatusId eq gzykdxAuditStatusEnumOrgSendBack.id }">checked="checked"</c:if>
                                       value="${gzykdxAuditStatusEnumOrgSendBack.id }"/><label
                                    for="achStatusEnum_OrgSendBack">${gzykdxAuditStatusEnumOrgSendBack.name}</label>
                                <input type="radio" name="auditStatusId" id="achStatusEnum_SchoolSendBack"
                                       <c:if test="${param.auditStatusId eq gzykdxAuditStatusEnumSchoolSendBack.id }">checked="checked"</c:if>
                                       value="${gzykdxAuditStatusEnumSchoolSendBack.id }"/><label
                                    for="achStatusEnum_SchoolSendBack">${gzykdxAuditStatusEnumSchoolSendBack.name}</label><br/>
                                <input class="search" type="button" value="一键提交" onclick="commitAll('${pdfn:getCurrDateTime('yyyy')}')"/>
                                <input class="search" type="button" value="导&#12288;出" onclick="exportExcel()"/><br/>
                                <span style="margin-left: -10px;"></span>
                                学术学位：总指标/已使用指标/剩余指标（${recruitNum.ACADEMIC_NUM}/${academicSum}/${recruitNum.ACADEMIC_NUM - academicSum}）&#12288;&#12288;
                                专业学位：总指标/已使用指标/剩余指标（${recruitNum.SPECIALIZED_NUM}/${specializedSum}/${recruitNum.SPECIALIZED_NUM - specializedSum}）
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
        </div>

        <table class="xllist">
            <tr>
                <th>年份</th>
                <th>导师姓名</th>
                <th>申请招生专业</th>
                <th>招生专业代码</th>
                <th style="max-width: 200px;">研究方向</th>
                <th>年龄</th>
                <th>职称</th>
                <th>最高学位</th>
                <th>学术学位拟招生人数</th>
                <th>专业学位拟招生人数</th>
                <th>手机</th>
                <th>经费(万元)</th>
                <%--<th>备注</th>--%>
                <th>状态</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${teacherApplyList}" var="apply">
                <tr>
                    <td>${apply.RECRUIT_YEAR}</td>
                    <td>${apply.USER_NAME}</td>
                    <td>${apply.SPE_NAME}</td>
                    <td>${apply.SPE_ID}</td>
                    <td style="max-width: 200px;">${apply.RESEARCH_DIRECTION}</td>
                    <td>${apply.infoForm.age}</td>
                    <td>${apply.TITLE_NAME}</td>
                    <td>${apply.DEGREE_NAME}</td>
                    <td>
                        <input value="${apply.ACADEMIC_NUM}" onchange="editApplyInfo('${apply.APPLY_FLOW}','academicNum',this.value,this)" name="academicNum"
                            <c:if test="${apply.IS_SUBMIT eq 'Y' or apply.IS_ACADEMIC ne 'Y'
                                or apply.AUDIT_STATUS_ID ne gzykdxAuditStatusEnumWaitingForCommitted}">disabled="disabled"</c:if> style="width: 60px;" class="custom[positiveNum]]"/>
                    </td>
                    <td>
                        <input value="${apply.SPECIALIZED_NUM}" onchange="editApplyInfo('${apply.APPLY_FLOW}','specializedNum',this.value,this)" name="specializedNum"
                               <c:if test="${apply.IS_SUBMIT eq 'Y' or apply.IS_SPECIALIZED ne 'Y'
                                or apply.AUDIT_STATUS_ID ne gzykdxAuditStatusEnumWaitingForCommitted}">disabled="disabled"</c:if> style="width: 60px;" class="custom[positiveNum]]"/>
                    </td>
                    <td>${apply.USER_PHONE}</td>
                    <td>${apply.FUNDS}</td>
                    <%--<td>${apply.projSourceName}</td>--%>
                    <td>${apply.AUDIT_STATUS_NAME}</td>
                    <td>
                        <c:choose>
                            <c:when test="${apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumWaitingForOrg or apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumSchoolSendBack}">
                                <a onclick="auditTeacherApply('${apply.APPLY_FLOW}')" style="cursor:pointer;color:#4195c5;">审核</a>
                            </c:when>
                            <c:when test="${apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumUnPassed or apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumWaitingForSchool}">
                                <a onclick="auditTeacherApply('${apply.APPLY_FLOW}','Y')" style="cursor:pointer;color:#4195c5;">查看</a>
                                <a onclick="editApplyInfo('${apply.APPLY_FLOW}','revoke')" style="cursor:pointer;color:#4195c5;">撤销</a>
                            </c:when>
                            <c:when test="${apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumWaitingForCommitted}">
                                <a onclick="auditTeacherApply('${apply.APPLY_FLOW}','Y')" style="cursor:pointer;color:#4195c5;">查看</a>
                                <a onclick="commitOneApply('${apply.APPLY_FLOW}')" style="cursor:pointer;color:#4195c5;">提交</a>
                                <a onclick="editApplyInfo('${apply.APPLY_FLOW}','revoke')" style="cursor:pointer;color:#4195c5;">撤销</a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="auditTeacherApply('${apply.APPLY_FLOW}','Y')" style="cursor:pointer;color:#4195c5;">查看</a>
                                <a onclick="editApplyInfo('${apply.APPLY_FLOW}','revoke')" style="cursor:pointer;color:#4195c5;">撤销</a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <p>
            <c:if test="${not empty teacherApplyList}">
                <c:set var="pageView" value="${pdfn:getPageView2(teacherApplyList , 10)}" scope="request"></c:set>
                <pd:pagination toPage="toPage"/>
            </c:if>
        </p>
    </div>
</div>
</div>
</body>
</html>