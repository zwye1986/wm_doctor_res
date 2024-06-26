<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        function save(){
            if(!$("#addForm").validationEngine("validate")){
                return;
            }
            jboxConfirm("确认保存？" , function(){
                var url = "<s:url value='/res/liveTraining/saveOpinionReply'/>";
                var data = $('#addForm').serialize();
                jboxPost(url,data,function(resp){
                    if(resp=='1'){
                        top.jboxTip("操作成功");
                        top.document.mainIframe.search();
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
<div class="mainright">
    <div class="content">
        <form id="addForm">
            <input type="hidden" name="trainingOpinionFlow" value="${trainingOpinion.trainingOpinionFlow}"/>
            <tr>
                <td>
                    <textarea class="validate[required]"name="opinionReplyContent" style="width: 100%;height: 230px">${trainingOpinion.opinionReplyContent}</textarea>
                </td>
            </tr>
        </form>
        <p style="text-align: center;margin: 5px">
           <input type="button" onclick="save();" class="search" value="保&#12288;存"/>
            <input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
