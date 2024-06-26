<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>

    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function applyInfo(tutorFlow){
            jboxConfirm("确认提交导师申请？", function(){
                var url = "<s:url value='/xjgl/tutor/applyOption?tutorFlow='/>"+tutorFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function detailInfo(tutorFlow){
            var url = "<s:url value='/xjgl/tutor/detailInfo?tutorFlow='/>"+tutorFlow;
            jboxOpen(url, "查看",800,600);
        }
        function printInfo(tutorFlow){
            var url = '<s:url value="/xjgl/tutor/printDetail?tutorFlow="/>'+tutorFlow;
            jboxOpen(url,"打印",800,600,true);
        }
        function backInfo(tutorFlow){
            jboxConfirm("确认重新申请？", function(){
                var url = "<s:url value='/xjgl/tutor/backAuditOpt?tutorFlow='/>"+tutorFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="line-height:40px;"><span style="color:red;">&#12288;注意：导师申请后进入审核流程，无法更正导师信息！</span></div>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>导师姓名</th>
                <th>导师类型</th>
                <th>培养单位</th>
                <th>分委员会</th>
                <th>申请时间</th>
                <th>培养单位审核</th>
                <th>分委会审核</th>
                <th>研究生院审核</th>
                <th style="min-width:140px;">操作</th>
            </tr>
            <tr>
                <td>${tutor.doctorName}</td>
                <td>${tutor.doctorTypeName}</td>
                <td>${tutor.pydwOrgName}</td>
                <td>${tutor.fwhOrgName}</td>
                <td>${tutor.applyTime}</td>
                <td style="cursor:pointer;" <c:if test="${!empty tutor.pydwAuditAdvice}">title="<div style='max-width:400px;white-space:nowrap;white-space:pre-wrap;'>${tutor.pydwAuditAdvice}</div>"</c:if>>${empty tutor.pydwAuditStatusName?'':tutor.pydwAuditStatusName}</td>
                <td style="cursor:pointer;" <c:if test="${!empty tutor.fwhAuditAdvice}">title="<div style='max-width:400px;white-space:nowrap;white-space:pre-wrap;'>${tutor.fwhAuditAdvice}</div>"</c:if>>${empty tutor.fwhAuditStatusName?'':tutor.fwhAuditStatusName}</td>
                <td style="cursor:pointer;" <c:if test="${!empty tutor.xwkAuditAdvice}">title="<div style='max-width:400px;white-space:nowrap;white-space:pre-wrap;'>${tutor.xwkAuditAdvice}</div>"</c:if>>${empty tutor.xwkAuditStatusName?'':tutor.xwkAuditStatusName}</td>
                <td>
                    <c:if test="${!empty tutor.doctorFlow && tutor.applyFlag ne GlobalConstant.FLAG_Y}">
                        <a onclick="applyInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">导师申请</a>
                    </c:if>
                    <!--较上次导师类型，平级或降级申请-->
                    <c:set var="boolFlag" value="${tutor.lastTypeId eq tutor.doctorTypeId || (tutor.lastTypeId eq 'xsxbd' && tutor.doctorTypeId eq 'zyxbd') || (tutor.lastTypeId eq 'xsxsd' && tutor.doctorTypeId eq 'zyxsd')}" />
                    <!--上次审核不通过情况-->
                    <c:set var="statusFlag" value="${tutor.pydwAuditStatusId eq 'UnPassed' || tutor.fwhAuditStatusId eq 'UnPassed' || tutor.xwkAuditStatusId eq 'UnPassed'}" />
                    <!--firstPassFlag：首次通过标识，lastAuditYear：上次审核年份 -->
                    <c:if test="${statusFlag && (empty tutor.firstPassFlag || (boolFlag && pdfn:getCurrYear()-tutor.lastAuditYear ge 2))}">
                        <a onclick="backInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">重新申请</a>
                    </c:if>
                    <a onclick="detailInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">查看</a>
                    <%--<c:if test="${tutor.xwkAuditStatusId eq 'Passed'}">--%>
                        <%--<a onclick="printInfo('${tutor.doctorFlow}');" style="cursor:pointer;color:blue;">打印</a>--%>
                    <%--</c:if>--%>
                </td>
            </tr>
        </table>
    </div>
</div>
<div id="printDiv" style="display: none;height:10000px;"></div>
</body>
</html>