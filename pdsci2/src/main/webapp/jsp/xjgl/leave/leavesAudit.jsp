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

        function auditLeave(recordFlow,auditFlag,doctorFlag){
            var url = "<s:url value='/xjgl/leave/auditLeave?role=${role}&recordFlow='/>"+recordFlow+"&auditFlag="+auditFlag+"&doctorFlag="+doctorFlag+"&leaderFlag=${leaderFlag}";
            jboxOpen(url, '审核意见',400,260);
        }
        function sickLeave(recordFlow){
            jboxConfirm("确定销假吗？",function(){
                var url="<s:url value='/xjgl/leave/sickLeaveSzk'/>?recordFlow="+recordFlow;
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
        function applyLeave(recordFlow,flag){
            var url="<s:url value='/xjgl/leave/showApplyInfo'/>?recordFlow="+recordFlow+"&flag="+flag;
            jboxOpen(url,"请假申请",800,550,true);
        }
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function exportLeaves(){
            var url = "<s:url value='/xjgl/leave/exportLeaves'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
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
                    <form id="searchForm" method="post" action="<s:url value='/xjgl/leave/leavesAudit'/>">
                        <input id="currentPage" type="hidden" name="currentPage"/>
                        <input type="hidden" name="role" value="${role}"/>
                        <c:if test="${role eq 'yjsb'}">
                            &#12288;姓&#12288;&#12288;名：
                            <input type="text" name="userName" value="${param.userName}" style="width: 137px;">
                            &#12288;学&#12288;&#12288;号：
                            <input type="text" name="studentId" value="${param.studentId}" style="width: 137px;"/>
                            &#12288;&#12288;&#12288;申请时间：
                        <span>
                        <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime" value="${param.createTime}" onchange="checkTime(this)" style="width: 137px;"/>
                        -- <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="modifyTime" value="${param.modifyTime}" onchange="checkTime(this)" style="width: 137px;"/>
                        </span>
                            <br/>&#12288;请假类型：
                            <select style="width: 141px;" name="applyTypeId">
                                <option value="">全部</option>
                                <c:forEach items="${xjNyqjApplyTypeEnumList}" var="type">
                                    <option value="${type.id}" <c:if test="${param.applyTypeId eq type.id}">selected="selected"</c:if>>${type.name}</option>
                                </c:forEach>
                            </select>
                            &#12288;导师审核：
                            <select style="width: 141px;" name="doctorAuditStatusId">
                                <option value="">全部</option>
                                <c:forEach items="${xjNyqjStatusEnumList}" var="status">
                                    <option value="${status.id}" <c:if test="${param.doctorAuditStatusId eq status.id}">selected="selected"</c:if>>${status.name}</option>
                                </c:forEach>
                            </select>
                            &#12288;培养单位审核：
                            <select style="width: 141px;" name="pydwAuditStatusId">
                                <option value="">全部</option>
                                <c:forEach items="${xjNyqjStatusEnumList}" var="status">
                                    <option value="${status.id}" <c:if test="${param.pydwAuditStatusId eq status.id}">selected="selected"</c:if>>${status.name}</option>
                                </c:forEach>
                            </select>
                            <br/>思政科审核：
                            <select style="width: 141px;" name="szkAuditStatusId">
                                <option value="">全部</option>
                                <c:forEach items="${xjNyqjStatusEnumList}" var="status">
                                    <option value="${status.id}" <c:if test="${param.szkAuditStatusId eq status.id}">selected="selected"</c:if>>${status.name}</option>
                                </c:forEach>
                            </select>
                            &#12288;领导审核：
                            <select style="width: 141px;" name="leaderAuditStatusId">
                                <option value="">全部</option>
                                <c:forEach items="${xjNyqjStatusEnumList}" var="status">
                                    <option value="${status.id}" <c:if test="${param.leaderAuditStatusId eq status.id}">selected="selected"</c:if>>${status.name}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <c:if test="${role eq 'doctor'}">
                            姓&#12288;&#12288;名：
                            <input type="text" name="userName" value="${param.userName}" style="width: 137px;">
                            &#12288;学&#12288;&#12288;号：
                            <input type="text" name="studentId" value="${param.studentId}" style="width: 137px;"/>
                            &#12288;申请时间：
                        <span>
                        <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime" value="${param.createTime}" onchange="checkTime(this)" style="width: 137px;"/>
                        -- <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="modifyTime" value="${param.modifyTime}" onchange="checkTime(this)" style="width: 137px;"/>
                        </span>
                            <br/>请假类型：
                            <select style="width: 141px;" name="applyTypeId">
                                <option value="">全部</option>
                                <c:forEach items="${xjNyqjApplyTypeEnumList}" var="type">
                                    <option value="${type.id}" <c:if test="${param.applyTypeId eq type.id}">selected="selected"</c:if>>${type.name}</option>
                                </c:forEach>
                            </select>
                            &#12288;导师审核：
                            <select style="width: 141px;" name="doctorAuditStatusId">
                                <option value="">全部</option>
                                <c:forEach items="${xjNyqjStatusEnumList}" var="status">
                                    <option value="${status.id}" <c:if test="${param.doctorAuditStatusId eq status.id}">selected="selected"</c:if>>${status.name}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <c:if test="${role eq 'pydw'}">
                            姓&#12288;&#12288;名：
                            <input type="text" name="userName" value="${param.userName}" style="width: 137px;">
                            &#12288;&#12288;&#12288;学&#12288;&#12288;号：
                            <input type="text" name="studentId" value="${param.studentId}" style="width: 137px;"/>
                            &#12288;申请时间：
                        <span>
                        <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="createTime" value="${param.createTime}" onchange="checkTime(this)" style="width: 137px;"/>
                        -- <input type="text" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="modifyTime" value="${param.modifyTime}" onchange="checkTime(this)" style="width: 137px;"/>
                        </span>
                            <br/>请假类型：
                            <select style="width: 141px;" name="applyTypeId">
                                <option value="">全部</option>
                                <c:forEach items="${xjNyqjApplyTypeEnumList}" var="type">
                                    <option value="${type.id}" <c:if test="${param.applyTypeId eq type.id}">selected="selected"</c:if>>${type.name}</option>
                                </c:forEach>
                            </select>
                            &#12288;培养单位审核：
                            <select style="width: 141px;" name="pydwAuditStatusId">
                                <option value="">全部</option>
                                <c:forEach items="${xjNyqjStatusEnumList}" var="status">
                                    <option value="${status.id}" <c:if test="${param.pydwAuditStatusId eq status.id}">selected="selected"</c:if>>${status.name}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                        <input type="button" class="search" onclick="toPage(1);" value="查&#12288;询" />
                        <input type="button" class="search" onclick="exportLeaves();" value="导&#12288;出" />
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
                            <c:if test="${leaderFlag eq 'Y'}">
                                <c:if test="${leave.sickLeaveFlag ne 'Y'}">等待销假</c:if>
                                <c:if test="${leave.sickLeaveFlag eq 'Y'}">${pdfn:transDate(leave.modifyTime)}</c:if>
                            </c:if>
                            <c:if test="${leaderFlag ne 'Y'}">
                                <c:if test="${leave.sickLeaveFlag eq 'Y'}">${pdfn:transDate(leave.modifyTime)}</c:if>
                                <c:if test="${leave.sickLeaveFlag eq 'N'}">
                                    <c:if test="${role eq 'yjsb'}">
                                        <a style="cursor: pointer;color: blue;" onclick="sickLeave('${leave.recordFlow}');">销假</a>
                                    </c:if>
                                    <c:if test="${role ne 'yjsb'}">等待销假</c:if>
                                </c:if>
                                <c:if test="${empty leave.sickLeaveFlag}">等待销假</c:if>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${role eq 'doctor' && leave.doctorFlow eq doctorFlow}">
                                <c:if test="${leave.doctorAuditStatusId eq 'Passing'}">
                                    <a onclick="auditLeave('${leave.recordFlow}','Passed','first');" style="cursor:pointer;color:blue;">同意</a>
                                    <a onclick="auditLeave('${leave.recordFlow}','UnPassed','first');" style="cursor:pointer;color:blue;">不同意</a>
                                </c:if>
                                <c:if test="${leave.doctorAuditStatusId ne 'Passing'}">${leave.doctorAuditStatusName}</c:if>
                            </c:if>
                            <c:if test="${role eq 'doctor' && leave.secondDoctorFlow eq doctorFlow}">
                                <c:if test="${leave.secondAuditStatusId eq 'Passing'}">
                                    <a onclick="auditLeave('${leave.recordFlow}','Passed','second');" style="cursor:pointer;color:blue;">同意</a>
                                    <a onclick="auditLeave('${leave.recordFlow}','UnPassed','second');" style="cursor:pointer;color:blue;">不同意</a>
                                </c:if>
                                <c:if test="${leave.secondAuditStatusId ne 'Passing'}">${leave.secondAuditStatusName}</c:if>
                            </c:if>
                            <c:if test="${role ne 'doctor'}">
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
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${role eq 'pydw' && leave.pydwAuditStatusId eq 'Passing'}">
                                <a onclick="auditLeave('${leave.recordFlow}','Passed');" style="cursor:pointer;color:blue;">同意</a>
                                <a onclick="auditLeave('${leave.recordFlow}','UnPassed');" style="cursor:pointer;color:blue;">不同意</a>
                            </c:if>
                            <c:if test="${role ne 'pydw' || leave.pydwAuditStatusId ne 'Passing'}">${empty leave.pydwAuditStatusId?'--':leave.pydwAuditStatusName}</c:if>
                        </td>
                        <td>
                            <c:if test="${role eq 'yjsb' && leave.szkAuditStatusId eq 'Passing'}">
                                <a onclick="auditLeave('${leave.recordFlow}','Passed');" style="cursor:pointer;color:blue;">同意</a>
                                <a onclick="auditLeave('${leave.recordFlow}','UnPassed');" style="cursor:pointer;color:blue;">不同意</a>
                            </c:if>
                            <c:if test="${role ne 'yjsb' || leave.szkAuditStatusId ne 'Passing'}">${empty leave.szkAuditStatusId?'--':leave.szkAuditStatusName}</c:if>
                        </td>
                        <td>
                            <c:if test="${leaderFlag eq 'Y' && leave.leaderAuditStatusId eq 'Passing'}">
                                <a onclick="auditLeave('${leave.recordFlow}','Passed');" style="cursor:pointer;color:blue;">同意</a>
                                <a onclick="auditLeave('${leave.recordFlow}','UnPassed');" style="cursor:pointer;color:blue;">不同意</a>
                            </c:if>
                            <c:if test="${leaderFlag ne 'Y' || leave.leaderAuditStatusId ne 'Passing'}">${empty leave.leaderAuditStatusId?'--':leave.leaderAuditStatusName}</c:if>
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