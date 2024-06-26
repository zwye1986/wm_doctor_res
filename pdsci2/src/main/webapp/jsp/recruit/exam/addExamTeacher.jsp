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
        function save(){
            if(false==$("#addExamTeacherForm").validationEngine("validate")){
                return false;
            }
            var url = "<s:url value='/recruit/examTeacher/addExamTeacher'/>";
            requestData = $('#addExamTeacherForm').serialize();
            jboxPost(url,requestData,function(resp){
                if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
                    window.parent.frames['mainIframe'].location.reload(true);
                    jboxClose();
                }
            },null,true);
        }
    </script>

</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <%--<form id="addExamRoomForm"name="addExamRoomForm" action="<s:url value='/recruit/exam/addExamRoom'/>" method="post" style="position: relative;">--%>
            <form id="addExamTeacherForm"name="addExamTeacherForm" action="<s:url value='/recruit/examTeacher/addExamTeacher'/>" method="post" style="position: relative;">
                <table width="100%" class="basic" >
                    <tr>
                        <th><font color="red" >*</font>教师姓名：</th>
                        <td>
                            <input type="text" class="validate[required] xltext" id="teaName" name="teaName"
                                   value="${examTeacher.teaName}" style="margin-right: 0px" placeholder="姓名"/>

                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>考场名称：</th>
                        <td>
                            <select class="validate[required] xlselect" name="roomName" id="roomName">
                                <option value="">--请选择--</option>
                                <c:forEach items="${examRoomNameList}" var="examRoomName">
                                    <option value="${examRoomName}">${examRoomName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>角色：</th>
                        <td>
                            <select class="validate[required] xlselect" name="teaRole" id="teaRole">
                                <option value="">--请选择--</option>
                                <option value="teacher" >带教老师</option>
                                <option value="secretary" >规培秘书</option>
                                <option value="head" >科主任</option>
                            </select>

                        </td>
                    </tr>
                    <tr>
                        <th>备注说明：</th>
                        <td>
                            <input type="text" id="teaDemo" name="teaDemo"
                                   value="${examTeacher.teaDemo}" style="margin-right: 0px" placeholder="备注"/>

                        </td>
                    </tr>
                </table>
                <div style="text-align: center;margin-top: 15px;">
                    <input type="button" onclick="save()" class="search" value="保&#12288;存"/>
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">

                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>


