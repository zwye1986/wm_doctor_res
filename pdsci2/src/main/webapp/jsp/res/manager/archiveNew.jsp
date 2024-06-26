<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
<script type="text/javascript">
    function save(){
        var archiveTime = $("#archiveTime").val();
        var sessionNumber = $("#sessionNumber option:selected").val();
            var url = "<s:url value="/hbres/archive/saveArchiveInfo"/>?archiveTime="+archiveTime+"&sessionNumber="+sessionNumber;
        jboxStartLoading();
        jboxPost(url,null,function(resp){
            if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
                jboxClose();
            }else{
                jboxEndLoading();
            }
        },null,true);
    }
</script>
<style>
</style>
</head>
<body>
<div style="text-align: center;height: 75%;line-height: 75px;font-weight: bold;font-size: medium;">是否确认保存当前全部学员信息？<br>
    年&#12288;&#12288;级：
    <select name="sessionNumber" id="sessionNumber" class="qselect">
        <c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict" varStatus="num">
            <option value="${dict.dictName}">${dict.dictName}</option>
        </c:forEach>
    </select>
</div>
<div style="text-align: center">
    <input type="hidden" value="${pdfn:getCurrDateTime('yyyy-MM-dd HH:mm:ss')}" id="archiveTime"/>
    <input type="button" class="search" value="确认存档" onclick="save();">&#12288;
    <input type="button" class="search" value="关&#12288;&#12288;闭" onclick="jboxClose();">
</div>
</body>
</html>