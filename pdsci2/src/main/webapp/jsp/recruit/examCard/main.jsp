
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
        <jsp:param name="jquery_validation" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <style type="text/css">
        caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;}
    </style>

    <script type="text/javascript">
        function search(){
            $("#recruitInfoForm").submit();
        }

        function toPage(page)
        {
            page=page||1;
            $("#currentPage").val(page);
            jboxStartLoading();
            search();
        }

        function showExamCard(recruitFlow) {
            jboxOpen("<s:url value='/recruit/examCard/showExamCard'/>?recruitFlow="+recruitFlow, "准考证", 500,460);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 ">
            <form id="recruitInfoForm" action="<s:url value='/recruit/examCard/main'/>" method="post">
                <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
                    <div class="div_search" style="padding: 10px">
                        <div style="display:inline;margin-left: 50px">
                            <p1 class="qlable">姓&#12288;&#12288;名：</p1>
                            <input id="userName" value="${param.userName}" name="userName" type="text" class="qtext"/>
                        </div>
                        <div style="display:inline;margin-left: 50px">
                            <p1 class="qlable">证件号码：</p1>
                            <input id="idNo" value="${param.idNo}" name="idNo" type="text" class="qtext"/>
                        </div>
                        <div style="display:inline;margin-left: 50px">
                            <p1 class="qlable">年&#12288;&#12288;份：</p1>
                            <input id="recruitYear" value="${param.recruitYear}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" name="recruitYear" type="text" class="qtext"/>
                        </div>
                        <br>
                        <br>
                        <div style="display:inline;margin-left: 50px">
                            <p1 class="qlable">考试时间：</p1>
                            <input type="text" value="${param.startDate}" class="xltext" id="startDate" name="startDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '#F{$dp.$D(\'endDate\')}'})" readonly="readonly">
                            ~
                            <input type="text" value="${param.endDate}" class="xltext" id="endDate"   name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate: '#F{$dp.$D(\'startDate\')}'})" readonly="readonly">
                            <input style="margin-left: 40px" type="button" class="search" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
                        </div>
                </div>
            </form>
        </div>
        <table class="xllist" style="width: 100%;">
            <tr>
                <th width="10%" >年份</th>
                <th width="10%">姓名</th>
                <th width="10%" style="text-align: center">性别</th>
                <th width="10%" style="text-align: center">证件类型</th>
                <th width="15%" style="text-align: center">证件号码</th>
                <th width="15%" style="text-align: center">准考证号</th>
                <th width="15%" style="text-align: center">考试时间</th>
                <th width="15%" style="text-align: center">操作</th>
            </tr>
            <tbody>
            <c:forEach items="${recruitInfoExts}" var="recruitInfoExt" varStatus="seq">
                <tr>
                    <td>${recruitInfoExt.recruitYear}</td>
                    <td>${recruitInfoExt.sysUser.userName}</td>
                    <td>${recruitInfoExt.sysUser.sexName}</td>
                    <td>${recruitInfoExt.sysUser.cretTypeName}</td>
                    <td>${recruitInfoExt.sysUser.idNo}</td>
                    <td>${recruitInfoExt.ticketNumber}</td>
                    <td>${recruitInfoExt.recruitExamInfo.examStartTime}~${recruitInfoExt.recruitExamInfo.examEndTime}</td>
                    <td>
                        <a href="javascript:showExamCard('${recruitInfoExt.recruitFlow}')" style="color: blue">准考证</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty recruitInfoExts}">
                <tr><td align="center" colspan="10">无记录</td></tr>
            </c:if>
        </table>
    </div>
    <div class="resultDiv">
        <c:set var="pageView" value="${pdfn:getPageView(recruitInfoExts)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>