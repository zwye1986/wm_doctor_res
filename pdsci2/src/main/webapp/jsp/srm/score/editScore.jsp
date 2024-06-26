<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
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

        function saveScoreSet(){
            if(false==$("#saveScore").validationEngine("validate")){
                return false;
            }
            var url = "<s:url value='/srm/ach/score/saveScore'/>";
            jboxStartLoading();
            jboxSubmit($('#saveScore'),url,function(resp){
                        window.parent.frames['mainIframe'].window.scoreList("${parentScoreFlow}");
                        jboxClose();
                    },
                    function(resp){
                        alert("error");
                    });
        }
        function checkNum(expl){
            var num=expl.value;
            var reg = /^\d+(\.\d{1,2})?$/;
            if(reg.test(num)){
                $("input[name='saveBtn']").attr("disabled",false);
            }else{
                jboxTip("分值只能是正整数或最多含有两位小数");
                $("input[name='saveBtn']").attr("disabled",true);
            }
        }
    </script>
</head>
<body>
<div class="content">
    <div class="title1 clearfix">
    <form id="saveScore" action="<s:url value="/srm/ach/score/saveScore"/>" method="post">
        <input  type="hidden" name="parentScoreFlow" value="${parentScoreFlow}"/><br/>
        <div align="center" >
            <input class="xltext" name="scoreFlow" type="text" hidden="hidden" value="${achScore.scoreFlow}"/>
            &#12288;&#12288;积分项名称：
            <input class="validate[required] xltext" name="scoreName" type="text" value="${achScore.scoreName}"/><br/><br/>
            &#12288;分值（科室）：
            <input class="validate[required] xltext" name="scoreDeptValue" type="text" value="${achScore.scoreDeptValue}" placeholder="允许输入小数点后两位" onchange="checkNum(this)"/><br/><br/>
            &#12288;分值（个人）：
            <input class="validate[required] xltext" name="scorePersonalValue" type="text" value="${achScore.scorePersonalValue}" placeholder="允许输入小数点后两位" onchange="checkNum(this)"/><br/><br/>
            <span style="padding-left:65px;"></span>
            备注：
            <input class="xltext" maxlength="30" name="scoreRemark" type="text" value="${achScore.scoreRemark}" placeholder="最多输入30个汉字"/><br/>
        </div><br/><br/>
        <div align="center">
            <input class="search" type="button" name="saveBtn" value="保&#12288;存" onclick="saveScoreSet()"/>
            <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose()"/>
        </div>
    </form>
    </div>
</div>
</body>
</html>
