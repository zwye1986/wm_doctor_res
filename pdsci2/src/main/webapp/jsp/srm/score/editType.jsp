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

        function saveScoreType(){
            if(false==$("#saveScoreType").validationEngine("validate")){
                return false;
            }
            var url = "<s:url value='/srm/ach/score/saveScoreType'/>";
            jboxStartLoading();
            jboxSubmit($('#saveScoreType'),url,function(resp){
                        window.parent.frames['mainIframe'].window.scoreList("${parentTypeFlow}");
                        jboxClose();
                    },
                    function(resp){
                        alert("error");
                    });
        }

    </script>
</head>
<body>
<div class="content">
    <div class="title1 clearfix">
        <form id="saveScoreType" method="post">
            <br/>
            <div align="center" >
                <input class="xltext" name="typeFlow" type="text" hidden="hidden" value="${typeFlow}"/>
                <input class="xltext" name="parentTypeFlow" type="text" hidden="hidden" value="${parentTypeFlow}"/>
                &#12288;积分类型名称：
                <input class="validate[required] xltext" name="scoreTypeName" type="text" value="${scoreTypeName}"/><br/><br/>
                <span style="padding-left:65px;"></span>
            </div><br/><br/>
            <div align="center">
                <input class="search" type="button" name="saveBtn" value="保&#12288;存" onclick="saveScoreType()"/>
                <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose()"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>
