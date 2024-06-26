<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        .basic td,tr{border: 0}
    </style>
    <script type="text/javascript">
        function search(){
            $("#searchForm").submit();
        }
        function toPage(page) {
            $("#currentPage").val(page);
            search();
        }
        function addMajorCredit(recordFlow){
            var url="<s:url value='/xjgl/majorCourse/editMajorCredit?recordFlow='/>"+recordFlow;
            jboxOpen(url,"编辑学分要求",600,250);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table  style="width: 100%;min-width:1080px;margin: 10px 0px;border: none;">
            <form id="searchForm" method="post" action="<s:url value='/xjgl/majorCourse/majorCreditList'/>">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <tr>
                    <td style="border: none;">
                        专业名称：<input type="text" name="majorName" value="${param.majorName}" style="width:141px;"/>&#12288;
                        培养层次：<select name="trainTypeId" style="width:137px;" class="select">
                        <option/>
                        <c:forEach items="${dictTypeEnumTrainTypeList}" var="trainType">
                            <c:if test="${trainType.dictId eq '1' || trainType.dictId eq '2'}">
                                <option value="${trainType.dictId}" ${param.trainTypeId eq trainType.dictId?'selected':''}>${trainType.dictName}</option>
                            </c:if>
                        </c:forEach>
                    </select>&#12288;
                        <input type="button" class="search" onclick="search();" value="查&#12288;询" />
                        <input type="button" class="search" onclick="addMajorCredit('');" value="新&#12288;增" />
                    </td>
                </tr>
            </form>
        </table>
        <table class="xllist" style="width: 100%;" class="table">
            <tr style="font-weight: bold;">
                <td>序号</td>
                <td>专业代码</td>
                <td>专业名称</td>
                <td>学位课程学分（不低于）</td>
                <td>总学分（不低于）</td>
                <td>培养层次</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${majorCreditList}" var="info" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>${info.majorId}</td>
                    <td>${info.majorName}</td>
                    <td>${info.degreeCredit}</td>
                    <td>${info.credit}</td>
                    <td>${info.trainTypeName}</td>
                    <td>
                        <a onclick="addMajorCredit('${info.recordFlow}');" style="cursor: pointer;color: blue;">编辑</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty majorCreditList}">
                <tr><td colspan="8" >无记录！</td></tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(majorCreditList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>