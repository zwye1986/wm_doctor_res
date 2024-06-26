<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="true"/>
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
    <style type="text/css">
    .label td{
        width:120px;height:35px;text-align:center;border:1px solid gray;cursor:pointer;
    }
    .label td.on{background-color:#FF9933;}
    </style>
    <script type="text/javascript">
        function searchScoreForm(doctorFlow,stationFlow){
            <%--jboxOpen("<s:url value='/osca/base/searchScoreForm?clinicalFlow=${clinicalFlow}&doctorFlow='/>"+doctorFlow+"&stationFlow="+stationFlow, "评分表",600,600);--%>
            var url="<s:url value='/osca/base/searchScoreForm?clinicalFlow=${clinicalFlow}&doctorFlow='/>"+doctorFlow+"&stationFlow="+stationFlow;
            jboxLoad("hosContent",url,false);
        }
        $(function(){
            <c:forEach items="${gradeList}" var="info" varStatus="i">
            var sum = 0;
            for(var i = 0;i<$(".${info.TICKET_NUMBER}").size();i++){
                sum+=Number($(".${info.TICKET_NUMBER}").eq(i).text());
                $(".sum${info.TICKET_NUMBER}").text(sum==0?"":sum);
            }
            </c:forEach>
        })
    </script>
</head>
<body>
<div class="mainright">
    <table class="xllist" style="width:100%;">
        <tr style="background-color:#F5F5F5;">
            <td colspan="${fn:length(stationList)+1}">成绩</td>
            <td rowspan="2">考核结果</td>
        </tr>
        <tr style="background-color:#F5F5F5;">
            <c:set var="count" value="0"></c:set>
            <c:if test="${not empty stationList}">
                <c:forEach items="${stationList}" var="station">
                    <td>${station.stationName}${station.stationScore}</td>
                    <c:set var="count" value='${count + station.stationScore}'></c:set>
                </c:forEach>
            </c:if>
            <td>总分<fmt:formatNumber type="number" value="${count}" /></td>
        </tr>
        <c:if test="${not empty gradeList}">
            <c:forEach items="${gradeList}" var="info" varStatus="i">
                <tr style="${info.IS_PASS eq 'Miss'?'background-color:#D7D7D7':''}">
                    <c:set var="stuCount" value='0'></c:set>
                    <c:if test="${not empty stationList}">
                        <c:forEach begin="0" end="${fn:length(stationList)-1}" varStatus="s">
                            <c:forEach items="${fn:split(info.STATION_FLOW,',')}" var="sta" varStatus="si">
                                <c:if test="${sta eq stationList[s.index].stationFlow}">
                                    <td>
                                        <a class="${info.TICKET_NUMBER}" onclick="searchScoreForm('${info.DOCTOR_FLOW}','${sta}')" style="cursor:pointer;color:#4195C5;">

                                            <fmt:formatNumber type="number" pattern="0" value="${fn:split(info.EXAM_SCORE,',')[si.index] eq '0'?'':fn:split(info.EXAM_SCORE,',')[si.index]+0.001}"/>
                                        </a>
                                        <%--<c:set var="stuCount" value="${stuCount + fn:split(info.EXAM_SCORE,',')[si.index]}"></c:set>--%>
                                    </td>
                                    <c:set var="exitFlag" value="${s.index}"></c:set>
                                </c:if>
                            </c:forEach>
                            <c:if test="${s.index ne exitFlag || empty exitFlag}">
                                <td></td>
                                <c:set var="exitFlag"></c:set>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <td class="sum${info.TICKET_NUMBER}"></td>
                    <td>${info.IS_PASS_NAME}</td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <div class="main_bd">
        <div id="hosContent">
        </div>
    </div>
</div>

</body>
</html>
