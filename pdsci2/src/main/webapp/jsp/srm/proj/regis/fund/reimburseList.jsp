<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>

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
        function font() {
            $("td").css("font-size", "12px");
        }
        var height = (window.screen.height) * 0.7;
        var width = (window.screen.width) * 0.7;
        function search() {
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            search();
        }
        function editItem(fundFlow, projFlow) {
            var url = "<s:url value="/srm/regis/proj/details/apply?fundFlow="/>"+fundFlow + "&projFlow=" + projFlow;
            jboxOpen(url,"经费报销编辑",800,600,false);
        }


    </script>
</head>
<body onload="font()">

<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/regis/proj/remburseList"/>"
                  method="post">
                <p>
                <div class="searchDiv">
                    年&#12288;&#12288;度：
                    <input type="text" class="xltext ctime" name="projYear" readonly="readonly"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.projYear }"/>
                </div>
                <div class="searchDiv">
                    &#12288; 项目编号：
                    <input type="text" name="projNo" value="${param.projNo}" class="xltext"/>
                    </div>
                <div class="searchDiv">
                    &#12288;项目名称：
                    <input type="text" name="projName" value="${param.projName}" class="xltext" />
                    </div>
                <div class="searchDiv">
                    <input id="currentPage" type="hidden" name="currentPage" value=""/>
                    &#12288;<input type="button" class="search" onclick="search();" value="查&#12288;询">
                    </div>
                </p>
            </form>
        </div>

        <table class="xllist">
            <thead>
            <tr>
                <th width="10%">年 度</th>
                <th width="15%">项目编号</th>
                <th width="25%">项目名称</th>
                <th width="15%">项目类别</th>
                <th width="8%">项目奖金</th>
                <th width="7%">报销记录</th>
                <th width="20%">操 作</th>
            </tr>
            </thead>
            <c:forEach items="${regisProjFundExtList}" var="fundExt">
                <tr>
                    <td>${fundExt.projYear}</td>
                    <td>${fundExt.projNo}</td>
                    <td>${fundExt.projName}</td>
                    <td>${fundExt.projSubTypeName}</td>
                    <td>${fundExt.projFundInfo.amountFund}</td>
                    <td>${fundExt.fundDetailNum}</td>
                    <td>
                        <a href="javascript:void(0)"
                           onclick="editItem('${fundExt.projFundInfo.fundFlow}' , '${fundExt.projFlow}')">报销</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(regisProjFundExtList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</div>
</body>
</html>