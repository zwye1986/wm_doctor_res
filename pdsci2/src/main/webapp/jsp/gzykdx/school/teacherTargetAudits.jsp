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
            $("#searchForm").submit();
        }

        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }

        function auditTeacherApply(applyFlow,detailFlag) {
            var page=$("#currentPage").val();
            jboxStartLoading();
            jboxOpen("<s:url value='/gzykdx/teaAndDoc/edit?applyFlow='/>" + applyFlow+"&scope=school&detailFlag="+detailFlag+"&currentPage="+page, "审核", width, height);
        }
        function auditAllapply(){
            var url = "<s:url value='/gzykdx/schoolAudit/auditAllapply'/>";
            jboxConfirm("确认审核通过全部招生申请吗？",function() {
                jboxPost(url, null, function (resp) {
                    jboxTip(resp);
                    setTimeout("window.parent.frames['mainIframe'].location.reload(true);jboxClose();", 1000);
                }, null, false);
            });
        }

        function editApplyInfo(applyFlow,type,valtext,orgFlow,obj){
            var reg=/^(0|([1-9]\d*))(\.\d+)?$/;
            if(type == 'academicNum'){
                var val=$("input[name='academicNum']").val();
                var totle1='${recruitNum.ACADEMIC_NUM}';
                if(!reg.test(valtext)){
                    jboxTip("请输入正整数！");
                    $(obj).val("");
                    return;
                }
                //验证剩余指标
                var url = "<s:url value='/gzykdx/orgAudit/countApplyNum?applyFlow='/>" + applyFlow+"&type=academicNum&orgFlow="+orgFlow;
                jboxPost(url, null, function (resp) {
                    if(valtext > resp){
                        jboxTip("编辑人数大于剩余指标人数！");
                        var page = '${currentPage}';
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
                if(!reg.test(valtext)){
                    jboxTip("请输入正整数！");
                    $(obj).val("");
                    return;
                }
                //验证剩余指标
                var url1 = "<s:url value='/gzykdx/orgAudit/countApplyNum?applyFlow='/>" + applyFlow+"&type=specializedNum&orgFlow="+orgFlow;
                jboxPost(url1, null, function (resp) {
                    if(valtext > resp){
                        jboxTip("编辑人数大于剩余指标人数！");
                        var page = '${currentPage}';
                        setTimeout(function(){
                            window.parent.frames['mainIframe'].window.toPage(page);
                            jboxClose();
                        },1000)
                    }else{
                        saveIndexNum(applyFlow,type,valtext);
                    }
                }, null, false);
            }
        }
        function saveIndexNum(applyFlow,type,val){
            var url = "<s:url value='/gzykdx/orgAudit/saveIndexNum?applyFlow='/>" + applyFlow+"&type="+type+"&val="+val;
            jboxPost(url, null, function (resp) {
                jboxTip(resp);
                var page = '${currentPage}';
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
            var url = "<s:url value='/gzykdx/schoolAudit/exportTeacherApply'/>";
            jboxTip("导出中……");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>

<div class="mainright">
    <div class="content">
        <div id="gradeDiv" class="labelDiv">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/gzykdx/schoolAudit/teacherApplyList"/>" method="post">
                <div class="choseDivNewStyle">
                    <input id="currentPage" type="hidden" name="currentPage" value="${currentPage}"/>
                    <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                        <tr>
                            <td style="border:0px;">
                                <span style="margin-left:-10px;"></span>年&#12288;&#12288;份：
                                <input type="text" name="recruitYear" value="${(empty param.recruitYear)?recruitYear:param.recruitYear}"
                                       style="width: 140px;" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
                                <span style="padding-left:10px;"></span>机构名称：
                                <select name="orgFlow" class="select" style="width:140px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${orgList}" var="org">
                                        <option <c:if test="${param.orgFlow eq org.orgFlow }">selected="selected"</c:if>
                                                value="${org.orgFlow}" orgName="${org.orgName}">
                                                ${org.orgName}</option>
                                    </c:forEach>
                                </select>
                                <span style="padding-left:10px;"></span>状态：
                                <input type="radio" name="auditStatusId" id="achStatusEnumAll"
                                       <c:if test="${empty param.auditStatusId}">checked="checked"</c:if>
                                       value=""/><label for="achStatusEnumAll">全部</label>
                                <input type="radio" name="auditStatusId" id="achStatusEnum_SchoolAudit"
                                       <c:if test="${param.auditStatusId eq gzykdxAuditStatusEnumWaitingForSchool.id }">checked="checked"</c:if>
                                       value="${gzykdxAuditStatusEnumWaitingForSchool.id }"/><label
                                    for="achStatusEnum_SchoolAudit">待审核</label>
                                <input type="radio" name="auditStatusId" id="achStatusEnum_Passed"
                                       <c:if test="${param.auditStatusId eq gzykdxAuditStatusEnumSchoolPassed.id }">checked="checked"</c:if>
                                       value="${gzykdxAuditStatusEnumSchoolPassed.id }"/><label
                                    for="achStatusEnum_Passed">${gzykdxAuditStatusEnumSchoolPassed.name}</label>
                                <input type="radio" name="auditStatusId" id="achStatusEnum_UnPassed"
                                       <c:if test="${param.auditStatusId eq gzykdxAuditStatusEnumUnPassed.id }">checked="checked"</c:if>
                                       value="${gzykdxAuditStatusEnumUnPassed.id }"/><label
                                    for="achStatusEnum_UnPassed">${gzykdxAuditStatusEnumUnPassed.name}</label><br/>
                                <span style="margin-left:-10px;"></span>姓&#12288;&#12288;名：
                                <input type="text" name="userName" value="${param.userName}" style="width:140px;"/>
                                <span style="padding-left:10px;"></span>专业名称：
                                <select name="speId" style="width:140px;" class="select">
                                    <option value="">全部</option>
                                    <c:forEach items="${dictTypeEnumGzykdxSpeList}" var="status">
                                        <option value="${status.dictId}" ${param.speId eq status.dictId ?'selected':''}>${status.dictName}[${status.dictId}]</option>
                                    </c:forEach>
                                </select>
                                <span style="padding-left:10px;"></span>研究方向：
                                <input type="text" name="researchDirection" value="${param.researchDirection}" style="width:137px;"/>
                            <%--<select name="researchDirectionId" class="select" style="width:140px;">--%>
                                    <%--<option value="">请选择</option>--%>
                                    <%--<c:forEach items="${dictTypeEnumResearchAreaList }" var="dict">--%>
                                        <%--<option--%>
                                                <%--<c:if test="${param.researchDirectionId eq dict.dictId }">selected="selected"</c:if>--%>
                                                <%--value="${dict.dictId }">${dict.dictName }</option>--%>
                                    <%--</c:forEach>--%>
                                <%--</select>--%>
                                <input type="button" class="search" onclick="toPage(1);" value="查&#12288;询">
                                <input class="search" type="button" value="导&#12288;出" onclick="exportExcel()"/>
                                <input class="search" type="button" value="一键审核" onclick="auditAllapply()"/>
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
                <th>经费(万元)</th>
                <th>工作机构</th>
                <th>手机</th>
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
                        <input value="${apply.ACADEMIC_NUM}" onchange="editApplyInfo('${apply.APPLY_FLOW}','academicNum',this.value,'${apply.ORG_FLOW}',this)" name="academicNum"
                               <c:if test="${apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumUnPassed or apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumSchoolPassed or
                                    apply.IS_ACADEMIC ne 'Y'}">disabled="disabled"</c:if> style="width: 60px;" class="validate[custom[positiveNum]]"/>
                    </td>
                    <td>
                        <input value="${apply.SPECIALIZED_NUM}" onchange="editApplyInfo('${apply.APPLY_FLOW}','specializedNum',this.value,'${apply.ORG_FLOW}',this)" name="specializedNum"
                               <c:if test="${apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumUnPassed or apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumSchoolPassed or
                                    apply.IS_SPECIALIZED ne 'Y'}">disabled="disabled"</c:if> style="width: 60px;" class="validate[custom[positiveNum]]"/>
                    </td>
                    <td>${apply.FUNDS}</td>
                    <td>${apply.ORG_NAME}</td>
                    <td>${apply.USER_PHONE}</td>
                    <td>${apply.AUDIT_STATUS_NAME}</td>
                    <td>
                        <c:choose>
                            <c:when test="${apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumWaitingForSchool}">
                                <a onclick="auditTeacherApply('${apply.APPLY_FLOW}')" style="cursor:pointer;color:#4195c5;">审核</a>
                            </c:when>
                            <c:when test="${apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumUnPassed or apply.AUDIT_STATUS_ID eq gzykdxAuditStatusEnumSchoolPassed}">
                                <a onclick="auditTeacherApply('${apply.APPLY_FLOW}','Y')" style="cursor:pointer;color:#4195c5;">查看</a>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty teacherApplyList}">
                <tr>
                    <td colspan="99">暂无信息</td>
                </tr>
            </c:if>
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