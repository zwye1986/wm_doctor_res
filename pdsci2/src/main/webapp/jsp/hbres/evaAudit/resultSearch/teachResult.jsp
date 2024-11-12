<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
        <jsp:param name="queryFont" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        function search(){
            toPage(1);
            jboxPostLoad("content","<s:url value='/res/evaDoctorResult/teachMain'/>", $("#searchForm").serialize(),false)
        }

        function toPage(page)
        {
            page=page||1;
            $("#currentPage").val(page);
            jboxPostLoad("content","<s:url value='/res/evaDoctorResult/teachMain'/>", $("#searchForm").serialize(),false)
        }

        function operDetail(deptFlow,keyCode,date,processFlow){
            var url = "<s:url value='/res/evaluateHospitalResult/gradeSearchDoc'/>?gradeRole=doctor&keyCode="+keyCode+"&date="+date+"&tdFlag=doctor"+"&deptFlow="+deptFlow+"&processFlow="+processFlow;
            jboxOpen(url,"评分详情",800,500);
        }
        function grade(recTypeId, recFlow, doctorFlow, processFlow) {
            jboxOpen("<s:url value='/res/evaDoctorResult/grade'/>?recTypeId=" + recTypeId + "&recFlow=" + recFlow+ "&doctorFlow=" + doctorFlow+ "&processFlow=" + processFlow,
                '学员评估带教', 1200, 800);
        }

        function newGrade(recTypeId, recFlow, doctorFlow, processFlow) {
            jboxOpen("<s:url value='/res/evaDoctorResult/fiveGrade'/>?recTypeId=" + recTypeId + "&recFlow=" + recFlow+ "&doctorFlow=" + doctorFlow+ "&processFlow=" + processFlow,
                '学员评估带教', 1200, 800);
        }

        function selGrade(recTypeId, recFlow, doctorFlow, processFlow) {
            var url = "<s:url value='/res/evaDoctorResult/selGrade'/>?recFlow=" + recFlow+ "&doctorFlow=" + doctorFlow+ "&processFlow=" + processFlow;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,'选择评估表单',500,200);
        }
    </script>
    <style>
        .search_table>tbody>tr:first-child>td{
            padding-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="main_hd">
    <h2 class="underline">带教评价查询</h2>
</div>
<div class="main_bd" id="div_table_0" style="width:1000px;">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <table>
                <tr>
                    <td class="td_left">
                        <label>姓&#12288;&#12288;名：</label>
                        <input type="text" name="teachName" value="${teachName}" class="input"/>
                    </td>
                    <td class="td_left">
                        <label style="margin-left: 20px">培训科室：</label>
                        <select name="deptFlow" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${deptList}" var="dept">
                                <option value="${dept.deptFlow}"
                                        <c:if test="${deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">
                        <label style="margin-left: 20px">状&#12288;&#12288;态：</label>
                        <select name="assessStatusId" class="select" style="width: 164px">
                            <option value="">全部</option>
                            <c:forEach items="${aidAssessStatusEnumList}" var="status">
                                <option value="${status.id}" <c:if test="${status.id eq assessStatusId}">selected="selected"</c:if> >${status.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="button"  class="btn_green" style="margin-left: 30px"
                               onclick="search();" value="查&#12288;询"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="overflow-x:auto;max-width:95%;overflow-y:auto;min-height:500px; margin-left: 30px;">
        <table style="margin-top: 30px" border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>序号</th>
                <th>姓名</th>
                <th>培训专业</th>
                <th>轮转时间</th>
                <th>状态</th>
                <th>分数</th>
            </tr>
            <tbody>
            <c:forEach items="${datas}" var="data" varStatus="seq">
                <tr>
                    <td>${seq.index + 1}</td>
                    <td>${data.teacherUserName}</td>
                    <td>${pdfn:cutString(data.schDeptName,8,true,3)}</td>
                    <td>${data.schStartDate}-${data.schEndDate}</td>
                    <td>
                        <c:if test="${empty data.recFlow}">
                            待评价
                        </c:if>
                        <c:if test="${not empty data.recFlow}">
                            已评价
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${empty data.recFlow}">
                            <a href="javascript:selGrade('${data.recTypeId}','${data.recFlow}','${data.doctorFlow}','${data.processFlow}')">评价</a>
                        </c:if>
                        <c:if test="${not empty data.recFlow}">
                            <a href="javascript:grade('${data.recTypeId}',
                            '${data.recFlow}','${data.doctorFlow}')">
                                    ${data.recContent}
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty datas}">
                <tr><td align="center" colspan="6">无记录</td></tr>
            </c:if>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(datas)}" scope="request"/>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>

</div>
</body>
</html>