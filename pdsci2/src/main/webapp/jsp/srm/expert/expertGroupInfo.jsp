<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
</head>
<body>
<script type="text/javascript">

    function searchExpertGroup() {
        jboxStartLoading();
        $("#searchExpertGroupForm").submit();
    }

    function viewDetail(groupFlow) {
        var w = $('#mainright').width();
        var url = '<s:url value="/srm/expert/group/viewExpertList?groupFlow="/>' + groupFlow;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='330px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, '评审详细', w, 400);

    }

    function exportDetail(groupFlow) {
        $("#groupFlow").val(groupFlow);
        var url = "<s:url value='/srm/expert/group/exportExcel'/>";
        jboxSubmit($('#exportForm'), url, null, null);
        jboxEndLoading();
    }


    function toPage(page) {
        var form = $('#searchExpertGroupForm');
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        jboxStartLoading();
        form.submit();
    }

</script>
<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchExpertGroupForm" action="<s:url value="/srm/expert/group/expertGroupInfo" />" method="post">
                专家组名称：<input type="text" name="groupName" value="${param.groupName}" class="xltext"/>
                <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
                <input type="button" class="search" onclick="searchExpertGroup();" value="查&#12288;询">
            </form>
            <p></p>
        </div>
        <form id="exportForm" method="post" action="<s:url value="/srm/expert/group/exportExcel"/>">
            <input id="groupFlow" type="hidden" name="groupFlow"/>
        </form>
        <table class="xllist">
            <tr>
                <th width="20%">专家组名称</th>
                <th width="60%">专家组成员</th>
                <th width="20%">操作</th>
            </tr>
            <c:forEach items="${expertGroupInfoList}" var="expertGroupInfo">
                <tr style="height:20px ">
                    <td>${expertGroupInfo.expertGroup.groupName }</td>
                    <td>
                        <div>
                            <c:forEach items="${expertGroupInfo.expertInfoList}" var="expertInfo">
                                &#12288;${expertInfo.user.userName}(${expertInfo.user.orgName})
                            </c:forEach>
                        </div>
                    </td>
                    <td>
                        [<a href="javascript:viewDetail('${expertGroupInfo.expertGroup.groupFlow}');">查看评审详情</a>]
                        <%--[<a href="javascript:exportDetail('${expertGroupInfo.expertGroup.groupFlow}');">导出</a>]--%>
                        <!-- <s:url value="/srm/expert/groupUser/list?groupFlow="/>${expertGroup.groupFlow} -->
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(expertGroupList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>