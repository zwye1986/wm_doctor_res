<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
    </jsp:include>
    <script>
        $(function(){
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
        })
        function search(){
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function doctorList(param){
            var url='<s:url value="/osca/provincial/doctorList?clinicalFlow="/>'+param;
            jboxOpen(url,"预约列表",1050,550);
        }
        function linkageSubject(dictId){
            $('#trainingSpeId').val("");//清空上次展现数据
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="queryDiv">
            <form id="searchForm" action="<s:url value='/osca/city/skillsAssessmentList'/>" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <%--<table class="basic" style="width:100%; margin-bottom: 10px; margin-top: 10px;">--%>
                <%--<tr style="height: 54px;">--%>
                    <%--<td>--%>
                <div class="inputDiv">
                    培训类型：
                    <select name="trainingTypeId" class="xlselect" onchange="linkageSubject(this.value)">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                            <option value="${dict.dictId}" ${param.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="inputDiv">
                    培训专业：
                    <c:set value="OscaTrainingType.${param.trainingTypeId}" var="trainingTypeClass"></c:set>
                    <select id="trainingSpeId" name="speId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                            <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
                            <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                <option class="${scope.dictTypeId}" value="${scope.dictId}" ${(param.speId eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
                            </c:forEach>
                        </c:forEach>
                    </select>
                </div>
                        <div class="inputDiv">
                            <label class="qlable">年&#12288;&#12288;份：</label>
                            <input type="text" class="qtext" value="${param.clinicalYear}" name="clinicalYear" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">考&#12288;&#12288;点：</label>
                            <select name="orgFlow" class="qselect">
                                <option value="">全部</option>
                                <c:forEach items="${examOrgList}" var="item">
                                    <option value="${item.orgFlow}"
                                            <c:if test="${item.orgFlow eq param.orgFlow}">selected</c:if>>${item.orgName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="lastDiv">
                            <input type="button" value="查&#12288;询" onclick="search()" class="searchInput">
                        </div>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
        </form>
        </div>
        <table class="xllist">
            <tr>
                <th>序号</th>
                <th>年份</th>
                <th>考核名称</th>
                <th>考核专业</th>
                <th>预约开放时间</th>
                <th>预约人员容量</th>
                <th>考核时间</th>
                <th>考点</th>
                <th>预约成功人数</th>
            </tr>
            <c:forEach items="${skillsAssessments}" var="item" varStatus="s">
                <tr>
                    <td>${s.index+1}</td>
                    <td>${item.clinicalYear}</td>
                    <td>${item.clinicalName}</td>
                    <td>${item.speName}</td>
                    <td >${item.appointStartTime}~<br/>${item.appointEndTime}</td>
                    <td>${item.appointNum}</td>
                    <td
                            <c:if test="${!(empty timeMap[item.clinicalFlow])}">
                                title="<c:if test="${!(empty timeMap[item.clinicalFlow])}">
                                        <c:forEach items="${timeMap[item.clinicalFlow]}" var="time">
                                            ${time.examStartTime}~${time.examEndTime}<br/>
                                        </c:forEach>
                                    </c:if>"
                            </c:if>
                    >
                        <c:if test="${empty timeMap[item.clinicalFlow]}">
                            暂无时间信息
                        </c:if>
                        <c:if test="${!(empty timeMap[item.clinicalFlow])}">
                            <c:forEach items="${timeMap[item.clinicalFlow]}" var="time" end="0">
                                ${time.examStartTime}~<br/>${time.examEndTime}<br/>
                                <c:if test="${timeMapSize>1}">......</c:if>
                            </c:forEach>
                        </c:if>
                    </td>
                    <td>${item.orgName}</td>
                    <td onclick="doctorList('${item.clinicalFlow}')" style="color: #00a1e5;cursor: pointer">
                        ${doctorNumMap[item.clinicalFlow]}
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty skillsAssessments}">
                <tr>
                    <td colspan="9">无记录</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(skillsAssessments)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
