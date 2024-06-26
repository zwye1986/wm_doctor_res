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
        <div class="title1 clearfix">
            <form id="sendInterviewForm"name="sendInterviewForm"  style="position: relative;">
                <input hidden value="${recruitInfo.recruitFlow}" id="recruitFlow" name="recruitFlow"/>
                <input hidden value="${recruitInfo.doctorFlow}" id="doctorFlow" name="doctorFlow"/>
                <input hidden value="${recruitInfo.orgFlow}" id="orgFlow" name="orgFlow"/>
                <table width="100%" class="basic" >
                    <tr>
                        <th>年份：</th>
                        <td>
                            ${recruitInfo.recruitYear}
                        </td>
                    </tr>
                    <tr>
                        <th>考生姓名：</th>
                        <td>
                            ${recruitInfo.sysUser.userName}
                        </td>
                    </tr>
                    <tr>
                        <th>身份证号：</th>
                        <td>
                            ${recruitInfo.sysUser.idNo}
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>面试开始时间：</th>
                        <td>
                            ${interviewInfo.interviewStartTime}
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>面试结束时间：</th>
                        <td>
                            ${interviewInfo.interviewEndTime}
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>面试地点：</th>
                        <td>
                            ${interviewInfo.interviewAddress}
                        </td>
                    </tr>
                    <tr>
                        <th>备注说明：</th>
                        <td>
                            ${interviewInfo.interviewDemo}
                        </td>
                    </tr>
                </table>
                <div style="text-align: center;margin-top: 15px;">
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>


