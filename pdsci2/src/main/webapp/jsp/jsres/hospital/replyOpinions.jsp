<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        function save(){
            if($("#opinionReplyContent").val()==null||$("#opinionReplyContent").val()==""){
                jboxTip("请输入回复内容!");
                return;
            }
            jboxConfirm("确认保存？" , function(){
                var url = "<s:url value='/jsres/training/saveOpinionReply'/>";
                var data = $('#addForm').serialize();
                top.jboxPost(url,data,function(resp){
                    if(resp=='1'){
                        top.jboxTip("操作成功");
                        top.opinions();
                        top.jboxClose();
                    }else{
                        jboxTip("操作失败");
                    }
                },null,false);
            });
        }
    </script>
</head>
<body>

        <form id="addForm" style="text-align: center;padding-top: 2%">
            <input type="hidden" name="trainingOpinionFlow" value="${trainingOpinion.trainingOpinionFlow}"/>

                    <textarea name="opinionReplyContent" id="opinionReplyContent" style="width: 90%;height: 230px">${trainingOpinion.opinionReplyContent}</textarea>

        </form>

        <p style="text-align: center;margin: 5px;padding-top: 2%">
           <input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>&#12288;
            <input type="button" onclick="top.jboxClose();" class="btn_green" value="关&#12288;闭"/>
        </p>

</body>
</html>
