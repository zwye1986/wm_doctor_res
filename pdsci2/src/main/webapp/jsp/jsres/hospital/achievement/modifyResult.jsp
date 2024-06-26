<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        $('#time').datepicker({
            startView: 2,
            format:'yyyy-mm-dd'
        });
    });
    function save(){
        if(false==$("#submitForm").validationEngine("validate")){
            return false;
        }
        var url = "<s:url value='/jsres/hospital/modifyResult'/>";
        jboxConfirm("确认修改学员成绩?",  function(){
            jboxPost(url, $("#submitForm").serialize(), function(resp){
                if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
                    window.parent.jboxTip("操作成功！");
                    setTimeout("window.parent.toPage(1);window.parent.jboxClose();", 1000);
                }else{
                    window.parent.jboxTip("操作失败！");
                    window.parent.jboxClose();
                }
            }, null, false);
        });
    }
</script>
<div class="infoAudit">
    <form id="submitForm">
        <div class="div_table">
            <input type="hidden" name="recruitFlow" value="${doctorRecruit.recruitFlow}" />
            <table border="0" cellpadding="0" cellspacing="0" class="base_info">
                <tr>
                    <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>笔试成绩：</th>
                    <td>
                        <input type="text" name="examResult" value="${doctorRecruit.examResult}" class="validate[required] input"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>面试成绩：</th>
                    <td>
                        <input type="text" name="auditionResult" value="${doctorRecruit.auditionResult}" class="validate[required] input"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>操作成绩：</th>
                    <td>
                        <input type="text" name="operResult" value="${doctorRecruit.operResult}" class="validate[required] input"/>
                    </td>
                </tr>
            </table>
        </div>
        <div class="btn_info">
            <input type="button" class="btn_green" onclick="save()" value="保&#12288;存">
            <input type="button" class="btn_green" onclick="window.parent.jboxClose();" value="关&#12288;闭"/>
        </div>
    </form>
</div>
