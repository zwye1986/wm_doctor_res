<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        th{width: 14%}
        textarea{width: 98%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <script type="text/javascript">
//        $(document).ready(function(){
//            $('#discipleDate').datepicker();
//        });
        function save(){
            if(!$("#addForm").validationEngine("validate")){
                return ;
            }
            var currentDate="${pdfn:getCurrDate()}";
            var discipleDate=$("#discipleDate").val();
            var startDate = $("#startTime").val();
            var endDate = $("#endTime").val();
            if(discipleDate>currentDate){
                jboxTip("记录日期不能晚于当前日期！");
                return;
            }
            if(startDate>endDate){
                var startDate = $("#itemsDate").attr("schStartDate");
                var endDate = $("#itemsDate").attr("schEndDate");
                jboxTip("开始时间不能小于结束时间");
                return;
            }
            jboxConfirm("确认保存？" , function(){
                var url = "<s:url value='/res/folowTeacherRecord/saveFollowTeacherRecord'/>";
                var data = $('#addForm').serialize();
                jboxPost(url,data,function(resp){
                    if(resp=='1'){
                        top.jboxTip("操作成功");
                        top.document.mainIframe.location.reload();
                        jboxClose();
                    }else{
                        jboxTip("操作失败");
                    }
                },null,false);
            });
        }
        function doClose(){
            jboxClose();
        }
    </script>
</head>
<body>
<div class="">
    <div class="">
        <form id="addForm">
            <input name="recordFlow" type="hidden" value="${resDiscipleRecordInfo.recordFlow}">
            <table class="basic" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <th style="width:30%;" align="right">出诊日期：</th>
                    <td>
                        <input id="discipleDate" class="validate[required]" name="discipleDate" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"value="${resDiscipleRecordInfo.discipleDate}"><font color="red">*</font>
                    </td>
                </tr>
                <tr>
                    <th style="width:30%;" align="right">出诊开始时间：</th>
                    <td>
                        <input id="startTime" class="validate[required]" name="startTime" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm'})"value="${resDiscipleRecordInfo.startTime}"><font color="red">*</font>
                    </td>
                </tr>
                <tr>
                    <th style="width:30%;" align="right">出诊结束时间：</th>
                    <td>
                        <input id="endTime" class="validate[required]" name="endTime" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'HH:mm'})"value="${resDiscipleRecordInfo.endTime}"><font color="red">*</font>
                    </td>
                </tr>
                <tr>
                    <th style="width:30%;" align="right">地点：</th>
                    <td>
                        <input name="address" type="text" value="${resDiscipleRecordInfo.address}">
                    </td>
                </tr>
            </table>
        </form>
        <p style="text-align: center;">
            <input type="button" onclick="save();" class="search" value="保&#12288;存"/>
            <input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
