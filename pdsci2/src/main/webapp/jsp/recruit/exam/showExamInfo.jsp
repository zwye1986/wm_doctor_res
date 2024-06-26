<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
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


 
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix" style="overflow: auto;min-height:300px">
                 <input type="hidden" id="mainFlow" name="mainFlow" value="${main.mainFlow}"/>
                <input type="hidden" id="examFlow" name="examFlow" value="${examInfo.examFlow}"/>
                <table width="100%" class="basic" >
                    <tr>
                        <th style="width: 160px"> 考试年份：</th>
                        <td  colspan="3">
                            ${main.recruitYear}
                        </td>
                    </tr>
                    <tr>
                        <th>笔试时间：</th>
                        <td>
                            ${examInfo.examStartTime}~${examInfo.examEndTime}
                        </td>
                    </tr>
                    <tr>
                        <th>考场信息：</th>
                        <td  colspan="3">
                            <div style="max-height: 200px;overflow: auto;width:100%;padding-top:5px;">
                                <table class="basic" width="100%">
                                    <c:forEach items="${recruitExamRooms}" var="recruitExamRoom"  >
                                        <c:set var="roomInfo" value="${roomInfoMap[recruitExamRoom.roomFlow]}"></c:set>
                                        <c:if test="${not empty roomInfo}">
                                            <tr>
                                                <td width="60%">
                                                    ${recruitExamRoom.roomName}
                                                </td>
                                                <td>
                                                    考场人数：${roomInfo.examNum}
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </table>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>面试时间：</th>
                        <td>
                            ${examInfo.interviewStartTime}~${examInfo.interviewEndTime}
                        </td>
                    </tr>
                    <tr>

                        <th>面试地点：</th>
                        <td>
                            ${examInfo.interviewAddress}
                        </td>
                    </tr>
                    <tr>
                        <th>面试备注：</th>
                        <td  colspan="3">
                            ${examInfo.interviewDemo}
                        </td>
                    </tr>
                </table>
                <div style="text-align: center;margin-top: 15px;">
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
                </div>
        </div>
    </div>
</div>
</body>
</html>