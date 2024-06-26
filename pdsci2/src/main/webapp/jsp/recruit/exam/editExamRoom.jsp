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
        function update(){
            if(false==$("#updateExamRoomForm").validationEngine("validate")){
                return false;
            }
            var url = "<s:url value='/recruit/examRoom/updateExamRoom'/>";
            requestData = $('#updateExamRoomForm').serialize();
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
            <form id="updateExamRoomForm"name="updateExamRoomForm" action="<s:url value='/recruit/examRoom/updateExamRoom'/>" method="post" style="position: relative;">
                <table width="100%" class="basic" >
                    <input type="hidden" name="roomFlow" id="roomFlow" value="${editInfo.roomFlow}">
                    <tr>
                        <th><font color="red" >*</font>考场名称：</th>
                        <td>
                            <input type="text" class="validate[required] xltext" id="roomName" name="roomName"
                                   value="${editInfo.roomName}" style="margin-right: 0px" placeholder="名称"/>

                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>考场地址：</th>
                        <td>
                            <input type="text"  class="validate[required] xltext" id="roomAddress" name="roomAddress"
                                   value="${editInfo.roomAddress}" style="margin-right: 0px" placeholder="地址"/>

                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>考场规模：</th>
                        <td>
                            <input type="text"class="validate[required,custom[number]] xltext" id="examNum" name="examNum"
                                   value="${editInfo.examNum}" style="margin-right: 0px" placeholder="人数(请填入整数)"/>

                        </td>
                    </tr>
                    <tr>
                        <th>备注说明：</th>
                        <td>
                            <input type="text" id="roomDemo" name="roomDemo" class="xltext"
                                   value="${editInfo.roomDemo}" style="margin-right: 0px" placeholder="备注"/>

                        </td>
                    </tr>
                </table>
                <div style="text-align: center;margin-top: 15px;">
                    <input type="button" onclick="update()" class="search" value="保&#12288;存"/>
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>


