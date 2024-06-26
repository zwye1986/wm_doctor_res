<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".showInfo").on("mouseover mouseout",
                    function(){
                        $(".theInfo",this).toggle();
                    }
            );
        });
        function applyLeave(recordFlow,flag){
            var url="<s:url value='/xjgl/leave/showApplyInfo'/>?recordFlow="+recordFlow+"&flag="+flag;
            jboxOpen(url,"请假申请",800,550,true);
        }
        function applyCancel(recordFlow){
            jboxConfirm("确定取消申请？",function(){
                var url="<s:url value='/xjgl/leave/applyCancel'/>?recordFlow="+recordFlow;
                jboxPost(url, null, function(resp){
                    if(resp=='${GlobalConstant.OPERATE_SUCCESSED}'){
                        location.reload();
                        jboxClose();
                    }
                } , null , true);
            },null);
        }
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        function exportLeave(recordFlow){
            var url="<s:url value='/xjgl/leave/exportLeaveInfo'/>?recordFlow="+recordFlow;
            jboxTip("导出中…………");
            window.location.href = url;
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="basic" style="width: 100%;margin: 15px 0px;border: none;">
            <tr >
                <td style="border: none;">
                    <form id="searchForm" method="post" action="<s:url value='/xjgl/leave/leaveInfosByStu'/>">
                        <input id="currentPage" type="hidden" name="currentPage"/>
                        请假类型：
                        <select style="width: 137px;" name="applyTypeId">
                            <option value="">全部</option>
                            <c:forEach items="${xjNyqjApplyTypeEnumList}" var="type">
                                <option value="${type.id}" <c:if test="${param.applyTypeId eq type.id}">selected="selected"</c:if>>${type.name}</option>
                            </c:forEach>
                        </select>
                        <input type="button" class="search" onclick="toPage(1);" value="查&#12288;询" />
                        <input type="button" class="search" onclick="applyLeave();" value="申&#12288;请" />

                    </form>
                </td>
            </tr>
        </table>
        <div class="resultDiv">
            <table class="xllist" width="100%">
            <tr >
                <th>学号</th>
                <th>姓名</th>
                <th>联系方式</th>
                <th>申请时间</th>
                <th>申请详情</th>
                <th>申请类型</th>
                <th>离校期限</th>
                <th>销假时间</th>
                <th>导师</th>
                <th>培养单位</th>
                <th>研究生工作部（思政科）</th>
                <th>研究生工作部（领导）</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${leaveList}" var="leave">
                <tr>
                    <td>${leave.studentId}</td>
                    <td>${leave.userName}</td>
                    <td>${leave.linkPhone}</td>
                    <td>${leave.applyTime}</td>
                    <td>
                        <a style="cursor: pointer;color: blue;" onclick="applyLeave('${leave.recordFlow}','Y');">查看</a>
                    </td>
                    <td>${leave.applyTypeName}</td>
                    <td>${leave.leaveTime}<br/>(${leave.daysOfLeave}天)</td>
                    <td>
                        <c:if test="${empty leave.sickLeaveFlag or leave.sickLeaveFlag eq 'N'}">等待销假</c:if>
                        <c:if test="${leave.sickLeaveFlag eq 'Y'}">${pdfn:transDate(leave.modifyTime)}</c:if>
                    </td>
                    <td <c:if test="${!(empty leave.doctorFlow && empty leave.secondDoctorFlow)}">class="showInfo"</c:if>>
                        <c:choose>
                            <c:when test="${empty leave.doctorFlow && empty leave.secondDoctorFlow}">无导师审核</c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${not empty leave.doctorFlow && not empty leave.secondDoctorFlow}">
                                        导师一：${leave.doctorAuditStatusName}<br/>导师二：${leave.secondAuditStatusName}
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${!empty leave.doctorFlow}">${leave.doctorAuditStatusName}</c:if>
                                        <c:if test="${!empty leave.secondDoctorFlow}">${leave.secondAuditStatusName}</c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        <div style="width: 0px;height: 0px;overflow: visible;">
                            <div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-50px;width: 300px;" class="theInfo">
                                <table class="xllist" style="background: white;width: 120px;">
                                    <tr style="line-height: 150%;">
                                        <th align="center" style="width:100px;font-weight: 900;">审核内容：<br/>
                                            <c:choose>
                                                <c:when test="${not empty leave.doctorFlow && not empty leave.secondDoctorFlow}">
                                                    导师一：${leave.doctorAuditAdvice}<br/>导师二：${leave.secondAuditAdvice}
                                                </c:when>
                                                <c:otherwise>
                                                    <c:if test="${!empty leave.doctorFlow}">${leave.doctorAuditAdvice}</c:if>
                                                    <c:if test="${!empty leave.secondDoctorFlow}">${leave.secondAuditAdvice}</c:if>
                                                </c:otherwise>
                                            </c:choose>
                                        </th>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </td>
                    <td <c:if test="${not empty leave.pydwAuditStatusName}">class="showInfo"</c:if> >${empty leave.pydwAuditStatusName?'--':leave.pydwAuditStatusName}
                        <div style="width: 0px;height: 0px;overflow: visible;">
                            <div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-50px;width: 300px;" class="theInfo">
                                <table class="xllist" style="background: white;width: 120px;">
                                    <tr style="line-height: 150%;">
                                        <th align="center" style="width:100px;font-weight: 900;">审核内容：<br/>${leave.pydwAuditAdvice}</th>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </td>
                    <td <c:if test="${not empty leave.szkAuditStatusName}">class="showInfo"</c:if> >${empty leave.szkAuditStatusName?'--':leave.szkAuditStatusName}
                        <div style="width: 0px;height: 0px;overflow: visible;">
                            <div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-50px;width: 300px;" class="theInfo">
                                <table class="xllist" style="background: white;width: 120px;">
                                    <tr style="line-height: 150%;">
                                        <th align="center" style="width:100px;font-weight: 900;">审核内容：<br/>${leave.szkAuditAdvice}</th>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </td>
                    <td <c:if test="${not empty leave.leaderAuditStatusName}">class="showInfo"</c:if> >${empty leave.leaderAuditStatusName?'--':leave.leaderAuditStatusName}
                        <div style="width: 0px;height: 0px;overflow: visible;">
                            <div style="max-height: 300px;overflow: auto;display: none;position:relative;margin-left:-50px;width: 300px;" class="theInfo">
                                <table class="xllist" style="background: white;width: 120px;">
                                    <tr style="line-height: 150%;">
                                        <th align="center" style="width:100px;font-weight: 900;">审核内容：<br/>${leave.leaderAuditAdvice}</th>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${!(leave.doctorAuditStatusId eq 'UnPassed' || leave.doctorAuditStatusId eq 'Passed' || leave.secondAuditStatusId eq 'UnPassed' || leave.secondAuditStatusId eq 'Passed')}">
                                <a style="cursor: pointer;color: blue;" onclick="applyCancel('${leave.recordFlow}');">取消申请</a>
                            </c:when>
                            <c:when test="${leave.doctorAuditStatusId eq 'UnPassed' || leave.secondAuditStatusId eq 'UnPassed' || leave.pydwAuditStatusId eq 'UnPassed' || leave.szkAuditStatusId eq 'UnPassed' || leave.leaderAuditStatusId eq 'UnPassed'}"><%--管理层有任何一方不通过--%>
                                <a onclick="applyLeave('${leave.recordFlow}');" style="cursor:pointer;color:blue;">重新申请</a><br/>
                                <a onclick="applyCancel('${leave.recordFlow}');" style="cursor:pointer;color:blue;">取消申请</a>
                            </c:when>
                            <c:when test="${(leave.yjsbmAuditFlag ne 'Y' and leave.pydwAuditStatusId eq 'Passed')||(leave.yjsbmAuditFlag eq 'Y' and leave.leaderAuditStatusId eq 'Passed')}"><%--最终审核通过--%>
                                <a onclick="exportLeave('${leave.recordFlow}');" style="cursor:pointer;color:blue;">导出</a>
                            </c:when>
                            <c:otherwise>审核中</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty leaveList }">
                <tr>
                    <td colspan="99">无记录!</td>
                </tr>
            </c:if>
            </table>
            <c:set var="pageView" value="${pdfn:getPageView(leaveList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>