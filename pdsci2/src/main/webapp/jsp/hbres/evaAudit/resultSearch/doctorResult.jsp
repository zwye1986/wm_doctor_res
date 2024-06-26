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
    </jsp:include>

    <%--<style type="text/css">--%>
        <%--caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}--%>
    <%--</style>--%>

    <script type="text/javascript">

        function search(){
            var endScore = $("#endScore").val();
            var startScore = $("#startScore").val();
            if(endScore){
                var ex = /^[0-9]+\d*$/;
                if (!ex.test(endScore)) {
                    jboxInfo("得分区间内应填入为整数！");
                    return;
                }
                if (endScore < 0 || endScore > 100){
                    jboxInfo("得分区间应在0~100范围内！");
                    return;
                }
            }
            if(startScore){
                var ex = /^[0-9]+\d*$/;
                if (!ex.test(startScore)) {
                    jboxInfo("得分区间内应填入为整数！");
                    return;
                }
                if (startScore < 0 || startScore > 100){
                    jboxInfo("得分区间应在0~100范围内！");
                    return;
                }
                if (endScore){
                    if (parseInt(endScore) < parseInt(startScore)){
                        jboxInfo("得分区间填写有误！");
                        return;
                    }
                }
            }

            toPage(1);
            // $("#teacherResultForm").submit();
            jboxPostLoad("content","<s:url value='/res/evaDoctorResult/main'/>", $("#teacherResultForm").serialize(),false)
        }

        function toPage(page)
        {
            page=page||1;
            $("#currentPage").val(page);

        }

        function operDetail(deptFlow,keyCode,date,processFlow,recFlow,recTypeId){
            var url = "<s:url value='/res/evaluateHospitalResult/gradeSearchDoc'/>?gradeRole=doctor&keyCode="+keyCode+"&date="+date+"&tdFlag=doctor"+"&deptFlow="+deptFlow+"&processFlow="+processFlow+"&recFlow="+recFlow;
            if (recTypeId=='ManageDoctorAssess360'){
                url = "<s:url value='/res/evaluateHospitalResult/gradeSearchDoc'/>?gradeRole=ManageDoctorAssess360&keyCode="+keyCode+"&date="+date+"&tdFlag=doctor"+"&deptFlow="+deptFlow+"&processFlow="+processFlow+"&recFlow="+recFlow;
            }
            jboxOpen(url,"评分详情",800,500);
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
    <h2 class="underline">个人评价查询</h2>
</div>
<div class="main_bd" id="div_table_0" style="width:1000px;">
    <div class="div_search">
        <form id="teacherResultForm" action="<s:url value='/res/evaDoctorResult/main'/>" method="post">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <td class="queryDiv" style="min-width: 960px;max-width: 960px;">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">
                            <span style="font-weight: normal;">科&#12288;&#12288;室：</span>
                        </td>
                        <td>
                            <select name="deptFlow" class="select" style="width: 184px;">
                                <option value=""></option>
                                <c:forEach items="${depts}" var="dept">
                                    <option value="${dept.deptFlow}" <c:if test="${param.deptFlow eq dept.deptFlow}">selected</c:if>>${dept.deptName}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td class="td_left">
                            <span style="font-weight: normal;margin-left: 70px;">类&#12288;&#12288;型：</span>
                        </td>
                        <td>
                            <select name="recTypeId" class="select" style="width: 182px;">
                                <option value="">全部</option>
                                <option value="${resRecTypeEnumTeacherDoctorGrade.id}" <c:if test="${param.deptFlow eq resRecTypeEnumTeacherDoctorGrade.id}">selected</c:if>>${resRecTypeEnumTeacherDoctorGrade.name}</option>
                                <option value="${resRecTypeEnumTeacherDoctorGradeTwo.id}" <c:if test="${param.deptFlow eq resRecTypeEnumTeacherDoctorGradeTwo.id}">selected</c:if>>${resRecTypeEnumTeacherDoctorGradeTwo.name}</option>
                                <option value="${resRecTypeEnumNurseDoctorGrade.id}" <c:if test="${param.deptFlow eq resRecTypeEnumNurseDoctorGrade.id}">selected</c:if>>${resRecTypeEnumNurseDoctorGrade.name}</option>
                                <option value="${resRecTypeEnumManageDoctorAssess360.id}" <c:if test="${param.deptFlow eq resRecTypeEnumManageDoctorAssess360.id}">selected</c:if>>${resRecTypeEnumManageDoctorAssess360.name}</option>
                                <option value="${resRecTypeEnumPatientDoctorAssess360.id}" <c:if test="${param.deptFlow eq resRecTypeEnumPatientDoctorAssess360.id}">selected</c:if>>${resRecTypeEnumPatientDoctorAssess360.name}</option>
                            </select>
                        </td>

                        <td class="td_left">
                            <span style="font-weight: normal;margin-left: 70px;">入科时间：</span>
                        </td>
                        <td>
                            <input type="text" id="startTime" name="startTime" value="${param.startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"readonly="readonly" class="input" style="width: 75px;"/>
                            ~
                            <input type="text" id="endDate" name="endDate" value="${param.endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" class="input" style="width: 75px;"/></td>
                        <%--<td class="td_left">轮转状态：</td>
                        <td>
                            <select name="status" class="select" style="width: 182px;margin-right: 20px;    margin-left: 5px;">
                                <option value="">全部</option>
                                <option value="Y" <c:if test="${status eq 'Y'}">selected</c:if>>轮转中</option>
                                <option value="N" <c:if test="${status eq 'N'}">selected</c:if>>已出科</option>

                            </select>
                        </td>--%>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <span style="font-weight: normal;">得分区间：</span>
                        </td>
                        <td>
                            <input type="text" id="startScore" name="startScore" value="${param.startScore}" class="input" style="width: 75px;margin: 0px 0px"/>
                            ~
                            <input type="text" id="endScore" name="endScore" value="${param.endScore}"class="input" style="width: 75px;"/></td>
                        <td  class="td_left" id="func" colspan="4">
                            <input style="margin-left: 70px;" type="button" value="查&#12288;询" class="btn_green" onclick="search();"/>
                        </td>
                    </tr>
                </table>
            </td>
        </form>
    </div>
    <div style="overflow-x:auto;max-width:95%;overflow-y:auto;min-height:500px; margin-left: 30px;">
        <table style="margin-top: 30px" border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="10%" >序号</th>
                <th width="25%" >类型</th>
                <th width="10%">科室</th>
                <th width="25%">轮转时间</th>
<%--                <th width="20%" style="text-align: center">轮转状态</th>--%>
                <th width="20%" style="text-align: center">平均分</th>
            </tr>
            <tbody>
            <c:forEach items="${datas}" var="data" varStatus="seq">
                <tr>
                    <td>${seq.index + 1}</td>
                    <td>
                        <c:forEach items="${resRecTypeEnumList}" var="d" >
                            <c:if test="${d.id eq data.recTypeId}">
                                ${d.name}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>${data.deptName}</td>
                    <td>${data.startTime}~${data.endTime}</td>
                   <%-- <td>
                        <c:if test="${data.schFlag eq 'N'}">轮转中</c:if>
                        <c:if test="${data.schFlag eq 'Y'}">已出科</c:if>
                    </td>--%>
                    <td>
                        <a style="cursor: pointer;color: blue;font-weight: normal;" onclick="operDetail('${data.deptFlow}','${userFlow}','${data.sessionNumber}','${data.processFlow}','${data.recFlow}','${data.recTypeId}');">${data.avg}</a>
                    </td>                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty datas}">
                <tr><td align="center" colspan="5">无记录</td></tr>
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