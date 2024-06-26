<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
    </jsp:include>
    <script src="<s:url value='/js/jquery.jqprint-0.3.js'/>" type="text/javascript"></script>
    <script>
        function toPage(page){
            $("#currentPage3").val(page);
            jboxPostLoad("gradeDiv","<s:url value="/gzykdx/teaAndDoc/selectTeacherApplyList"/>",$("#gradeForm").serialize(),true);
        }
        var width=1005;
        var height=650;
        function addApply(){
            var url = "<s:url value='/gzykdx/teaAndDoc/checkApplyOneYear'/>";
            if(${user.reportingAuthority eq 'Y'}){
//                jboxPost(url, null, function (resp) {
//                    if(resp == "NOTNULL"){
//                        jboxTip("本年度已经提交过指标申请！");
//                    }else {
                        jboxOpen("<s:url value='/gzykdx/teaAndDoc/edit?scope=teacher'/>", "编辑指标申请", width, height);
//                    }
//                }, null, false);
            }else {
                jboxTip("您没有申报权限！");
            }
        }

        function editTeacherApply(applyFlow,detailFlag){
            jboxStartLoading();
            var currentPage3=$("input[name='currentPage3']").val();
            jboxOpen("<s:url value='/gzykdx/teaAndDoc/edit'/>?scope=teacher&applyFlow="+applyFlow+"&currentPage3="+currentPage3+"&detailFlag="+detailFlag, "编辑指标申请", width, height);

        }

        function submitTeacherApply(applyFlow){
            if(${pdfn:getCurrDateTime('yyyy-MM-dd') ge sysCfgMap.teacher_apply_start_date
                        and pdfn:getCurrDateTime('yyyy-MM-dd') le sysCfgMap.teacher_apply_end_date}){
                jboxConfirm("送审后不能再进行修改",function() {
                    var url = "<s:url value='/gzykdx/teaAndDoc/commitApply?applyFlow='/>" + applyFlow;
                    jboxPost(url, null, function (resp) {
                        jboxTip(resp);
                        toPage(1);
                    }, null, false);
                });
            }else {
                jboxTip("当前时间不在指标上报时间内！");
            }
        }


        function printApply(applyFlow){
            jboxTip("打印中，请稍等...");
            setTimeout(function(){
                var url = "<s:url value='/gzykdx/teaAndDoc/printApply?applyFlow='/>"+applyFlow;
                jboxOpen(url,"day",800,1800,true);
                setTimeout(function(){jboxClose();},2000);
//                jboxPost(url, null, function(resp){
//                    if (resp) {
//                        var newstr = resp;
//                        var oldstr = document.body.innerHTML;
//                        document.body.innerHTML = newstr;
//                        window.print();
//                        document.body.innerHTML = oldstr;
//                        return false;
//                    }
//                }, null, false);
            },2000);
        }

    </script>
</head>
<div id="printApply" hidden="hidden" style="height: auto;"></div>
<body>
<div class="mainright">
    <div class="content">
        <div id="gradeDiv" class="labelDiv">
            <form id="gradeForm" action="<s:url value="/gzykdx/teaAndDoc/selectTeacherApplyList"/>" method="post">
                <div class="choseDivNewStyle">
                    <a class="btn" id="exportA3" type="hidden"><span id="outToExcelSpan3"> </span></a>
                    <input type="hidden" name="courseFlow" value="${courseFlow}"/>
                    <input id="currentPage3" type="hidden" name="currentPage3" value="${currentPage3}"/>
                    <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                        <tr>
                            <td style="border:0px;">
                                <span style="margin-left: -10px;"></span>年&#12288;&#12288;份：
                                <input type="text" name="recruitYear" value="${(empty param.recruitYear)?recruitYear:param.recruitYear}" style="width:137px;" onClick="WdatePicker({dateFmt:'yyyy'})">
                                <span style="padding-left:10px;"></span>学位类型：
                                <select name="applyType" style="width:137px;" class="select">
                                    <option value="">全部</option>
                                    <option value="isAcademic" ${param.applyType eq 'isAcademic' ?'selected':''}>学术型</option>
                                    <option value="isSpecialized" ${param.applyType eq 'isSpecialized' ?'selected':''}>专业型</option>
                                </select>
                                <span style="padding-left:10px;"></span>专业名称：
                                <select name="speId" style="width:137px;" class="select">
                                    <option value="">全部</option>
                                    <c:forEach items="${dictTypeEnumGzykdxSpeList}" var="status">
                                        <option value="${status.dictId}" ${param.speId eq status.dictId ?'selected':''}>${status.dictName}[${status.dictId}]</option>
                                    </c:forEach>
                                </select>
                                <span style="padding-left:20px;"></span>
                                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                                <input type="button" class="search" value="新&#12288;增" onclick="addApply()"/>
                            </td>
                        </tr>
                    </table>
                </div>
            </form>
            <table class="xllist" style="width:100%;">
                <tr>
                    <th>年份</th>
                    <th>拟招生学位类型</th>
                    <th>专业代码</th>
                    <th>专业名称</th>
                    <th style="max-width: 200px;">研究方向</th>
                    <th>学术学位招生指标</th>
                    <th>专业学位招生指标</th>
                    <th>审核状态</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${teacherTargetApplyList}" var="info" varStatus="i">
                    <tr>
                        <td>${info.recruitYear}</td>
                        <td>
                            <c:if test="${info.isAcademic eq 'Y'}">学术型</c:if>
                            <c:if test="${info.isAcademic eq 'Y' and info.isSpecialized eq 'Y'}">&#12288;</c:if>
                            <c:if test="${info.isSpecialized eq 'Y'}">专业型</c:if>
                        </td>
                        <td>${info.speId}</td>
                        <td>${info.speName}</td>
                        <td style="max-width: 200px;">${info.researchDirection}</td>
                        <td>${info.academicNum}</td>
                        <td>${info.specializedNum}</td>
                        <td>${info.auditStatusName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${info.auditStatusId eq gzykdxAuditStatusEnumUncommitted or info.auditStatusId eq gzykdxAuditStatusEnumOrgSendBack}">
                                    <a onclick="editTeacherApply('${info.applyFlow}')" style="cursor:pointer;color:#4195c5;">编辑</a>
                                    <a onclick="submitTeacherApply('${info.applyFlow}')" style="cursor:pointer;color:#4195c5;">送审</a>
                                </c:when>
                                <c:otherwise>
                                    <a onclick="editTeacherApply('${info.applyFlow}','Y')" style="cursor:pointer;color:#4195c5;">详情</a>
                                </c:otherwise>
                            </c:choose>
                            <a onclick="printApply('${info.applyFlow}')" style="cursor:pointer;color:#4195c5;">打印</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty teacherTargetApplyList}">
                    <tr><td colspan="99">暂无记录</td></tr>
                </c:if>
            </table>
            <div id="detail"></div>
            <div style="margin-top:100px;">
                <%--<c:if test="${not empty teacherTargetApplyList}">--%>
                    <%--<c:set var="pageView" value="${pdfn:getPageView(teacherTargetApplyList)}" scope="request"/>--%>
                    <%--<pd:pagination toPage="toPage"/>--%>
                <%--</c:if>--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
