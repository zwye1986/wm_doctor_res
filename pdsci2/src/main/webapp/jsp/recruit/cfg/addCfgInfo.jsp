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
            if(false==$("#addCfgInfoForm").validationEngine("validate")){
                return false;
            }
            var startDate = $("#recruitStartDate").val();
            var endDate = $("#recruitEndDate").val();
            if(startDate>endDate){
                jboxTip("开放时间填写有误");
                return false;
            }
            var url = "<s:url value='/recruit/cfgInfo/addCfgInfo'/>";
            requestData = $('#addCfgInfoForm').serialize();
            jboxPost(url, requestData, function(resp) {
                if(resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                    window.parent.frames['mainIframe'].window.location.reload();
                    jboxClose();
                }
            }, null, true);
        }
    </script>

</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <%--<form id="addExamRoomForm"name="addExamRoomForm" action="<s:url value='/recruit/exam/addExamRoom'/>" method="post" style="position: relative;">--%>
            <form id="addCfgInfoForm" name="addCfgInfoForm" action="<s:url value='/recruit/cfgInfo/addCfgInfo'/>" method="post" style="position: relative;">
                <table width="100%" class="basic" >
                    <tr>
                        <th><font color="red" >*</font>招录年份：</th>
                        <td>
                            <input type="text"class="validate[required,custom[number]] xltext" id="recruitYear" name="recruitYear"
                                   style="margin-right: 0px"  onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>

                        </td>
                    </tr>
                    <tr>
                        <th><font color="red" >*</font>报名时间：</th>
                        <td>
                            <input type="text" class="validate[required] xltext" id="recruitStartDate" name="recruitStartDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '#F{$dp.$D(\'recruitEndDate\')}'})" readonly="readonly">
                            ~&#12288;
                            <input type="text" class="validate[required] xltext" id="recruitEndDate"   name="recruitEndDate"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate: '#F{$dp.$D(\'recruitStartDate\')}'})" readonly="readonly">
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


