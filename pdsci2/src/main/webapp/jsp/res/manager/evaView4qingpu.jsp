<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
<script type="text/javascript">
    $(function(){
        <c:if test="${flag eq 'N'}">
        $("#Y").click();
        </c:if>
        <c:if test="${flag eq 'Y'}">
        $("#N").click();
        </c:if>
    });
    function selTag(tag,inx,data){
        $('.selectTag').removeClass('selectTag');
        $(tag).addClass('selectTag');
        var url = '<s:url value="/res/lecture4qingpu/manager/"/>'+inx+"?lectureFlow="+data;
        jboxLoad("content2",url,false);
    }
    function expExcel(flag){
        var url = "<s:url value='/res/lecture4qingpu/manager/expDoctorExcel?lectureFlow=${lectureFlow}&flag='/>"+flag;
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
<style>

</style>
</head>
<body>
<div class="title1 clearfix">
    <form id="searchForm"></form>
    <ul id="tags">
        <li id="Y" onclick="selTag(this,'evaList','${lectureFlow}');"><a>已扫码学员</a></li>
        <li id="N" onclick="selTag(this,'noRegist','${lectureFlow}');"><a>已报名学员</a></li>
    </ul>
    <div style="float:right;margin:-30px 0px 0px 0px;">
        <input type="button" class="search" value="导出扫码学员" onclick="expExcel('scan')"/>
        <input type="button" class="search" value="导出报名学员" onclick="expExcel('regist')"/>
    </div>
    <div id="content2" style="margin-top:10px">
    </div>
</div>

</body>
</html>