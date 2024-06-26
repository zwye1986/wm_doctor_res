<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
</head>
<body>
<script type="text/javascript">

    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("selectTag");
            $(this).removeClass("tab");
            $(this).addClass("selectTag");
        });
        $("li a:first").click();
    });
    function selTag(tag,isNew,isEval){
        var selLi = $(tag).parent();
        selLi.siblings("li").removeClass("selectTag");
        selLi.addClass("selectTag");
        var url = "<s:url value='/res/activityQuery/doctorMain'/>?roleFlag=${param.roleFlag}&isNew="+isNew+"&isEval="+isEval;
        jboxLoad("tagContent",url,true);
    }
</script>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <ul id="tags">
                <li id="init">
                    <a  onclick="selTag(this,'Y','');" style="cursor: pointer;" href="javascript:void(0)">最新活动</a>
                </li>
                <li id="history">
                    <a  onclick="selTag(this,'','Y');" style="cursor: pointer;" href="javascript:void(0)">活动评价</a>
                </li>
            </ul>
            <div id="tagContent">
            </div>
        </div>
    </div>
</div>
</body>
</html>