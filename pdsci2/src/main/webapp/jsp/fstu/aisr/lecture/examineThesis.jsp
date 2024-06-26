<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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

        function saveAudit(agreeFlag,thesisFlow){
            var tip =  agreeFlag=="${GlobalConstant.FLAG_Y}"?"审核通过":"退回";
            var url = "<s:url value='/fstu/thesis/saveAudit'/>?agreeFlag="+agreeFlag;
            jboxConfirm("确认"+tip+"?" ,  function(){
                jboxStartLoading();
                jboxPost(url , $('#srmThesis').serialize()+"&auditContent="+$("#auditContent").val() , function(resp){
                    $("#searchForm",window.parent.frames['mainIframe'].document).submit();
                    window.parent.frames['mainIframe'].window.search();
                    jboxClose();
                } , null , true);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div id="view" style="width: 900px;height: 100%">
            <jsp:include page="editThesis.jsp">
                <jsp:param  name="editFlag" value="${GlobalConstant.FLAG_N}"/>
            </jsp:include>
        </div>
        <h2>审核意见：</h2>
        <hr/>
        <form method="post" id="auditForm">
            <div style="text-align: center;">
                <textarea id="auditContent" name="auditContent" style="width: 100%" rows="7"></textarea><br/><br/><br/>
                <input class='search' onclick="saveAudit('${GlobalConstant.FLAG_Y}')" type='button'  value='同&#12288;意' />&#12288;
                <input class='search' onclick="saveAudit('${GlobalConstant.FLAG_N}')" type='button' value='退&#12288;回'/>&#12288;
                <input class='search' onclick="jboxClose();" type='button' value='关&#12288;闭'/>
            </div>
        </form>
    </div>
</div>
</body>
</html>