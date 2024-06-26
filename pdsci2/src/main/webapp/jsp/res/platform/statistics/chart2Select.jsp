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
<script type="text/javascript">
    $(function(){
        $("#masterSchool").click();
    });
    function selTag(tag,inx){
        jboxStartLoading();
        $('.selectTag').removeClass('selectTag');
        $(tag).addClass('selectTag');
        var url = '<s:url value="/res/statistics/"/>'+inx;
        jboxLoad("content2",url,false);
        $("#sessionNumber option[value=${sessionNumber}]").attr('selected','selected');
        jboxEndLoading();
    }
    function chartsInfo(){
        jboxStartLoading();
        var sessionNumber = $("#sessionNumber").val();
        var inx = $(".selectTag").attr("id");
        var url = '<s:url value="/res/statistics/"/>'+inx+'?sessionNumber='+sessionNumber;
        jboxLoad("content2",url,false);
        jboxEndLoading();
    }
</script>
<style>

</style>
</head>
<body>
<div class="title1 clearfix">
    <ul id="tags">
        <li id="masterSchool" onclick="selTag(this,'');"><a>并轨专硕所属高校分布</a></li>
        <li id="masterHospital" onclick="selTag(this,'');"><a>并轨专硕基地人数统计</a></li>
        <li id="masterSpe" onclick="selTag(this,'');"><a>并轨专硕专业人数统计</a></li>
    </ul>
    <div style="margin:20px;">
        年&#12288;&#12288;级：
        <select id="sessionNumber" name="sessionNumber" class="qselect">
            <option value="all">全部</option>
            <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
                <option value="${dict.dictName}" ${sessionNumber eq dict.dictName?'selected':''}>${dict.dictName}</option>
            </c:forEach>
        </select>&#12288;
        机构名称：
        <select class="qselect" name="orgFlow">
            <option value="">全部</option>
            <c:forEach var="org" items="${applicationScope.sysOrgList}">
                <option value="${org.orgFlow}"
                        <c:if test="${param.orgFlow==org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
            </c:forEach>
        </select>&#12288;
        <input class="search" type="button" value="查&#12288;询" onclick="chartsInfo();"/>
    </div>
    <div id="content2" style="margin:20px">

    </div>
</div>

</body>
</html>