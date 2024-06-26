
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
            toPage(1);
        }

        function toPage(page)
        {
            jboxStartLoading();
            page=page||1;
            $("#currentPage").val(page);
            $("#auditStatusForm").submit();
        }

        function auditRecruitInfo(recruitFlow) {
            jboxOpen("<s:url value='/recruit/auditStatus/auditRecruitInfo'/>?recruitFlow="+recruitFlow, "审核填报信息", 1200, 600 ,true);
        }

        function examineRecruitInfo(recruitFlow) {
            jboxOpen("<s:url value='/recruit/auditStatus/examineRecruitInfo'/>?recruitFlow="+recruitFlow, "查看填报信息", 1200, 600 ,true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 ">
            <form id="auditStatusForm" action="<s:url value='/recruit/auditStatus/main'/>" method="post">
                <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
                <div class="div_search" style="padding: 10px">
                    <div style="display:inline;margin-left: 50px">
                        <label class="qlable">姓名：</label>
                        <input id="userName" value="${userName}" name="userName" type="text" class="qtext"/>
                    </div>
                    <div style="display:inline;margin-left: 50px">
                        <label class="qlable">招录年份：</label>
                        <input id="recruitYear" value="${recruitYear}" onClick="WdatePicker({dateFmt:'yyyy'})" name="recruitYear" type="text" class="qtext"/>
                    </div>
                    <div style="display:inline;margin-left: 50px">
                        <label class="qlable">审核状态：</label>
                        <select class="xlselect" name="auditStatusId" id="auditStatusId">
                            <option value="">--全部--</option>
                            <option value="Passing" <c:if test="${auditStatusId eq 'Passing'}">selected</c:if>>待审核</option>
                            <option value="Passed" <c:if test="${auditStatusId eq 'Passed'}">selected</c:if>>审核通过</option>
                            <option value="NoPassed" <c:if test="${auditStatusId eq 'NoPassed'}">selected</c:if>>审核不通过</option>
                        </select>
                    </div>
                </div>
                <div class="div_search" style="padding: 10px">
                    <div style="display:inline;margin-left: 50px">
                        <label class="qlable">提交时间：</label>
                        <input type="text" value="${beginDate}" class="xltext" id="beginDate" name="beginDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '#F{$dp.$D(\'endDate\')}'})" readonly="readonly">
                        &#12288;~&#12288;
                        <input type="text" value="${endDate}" class="xltext" id="endDate"   name="endDate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate: '#F{$dp.$D(\'beginDate\')}'})" readonly="readonly">
                        <input style="margin-left: 40px" type="button" class="search" value="查&#12288;询" class="searchInput" onclick="search();"/>
                    </div>
                </div>
            </form>
        </div>
        <table class="xllist" style="width: 100%;">
            <tr>
                <th width="8%" >年份</th>
                <th width="10%">姓名</th>
                <th width="5%" style="text-align: center">性别</th>
                <th width="8%" style="text-align: center">证件类型</th>
                <th width="15%" style="text-align: center">证件号码</th>
                <th width="10%" style="text-align: center">准考证号</th>
                <th width="10%" style="text-align: center">资料提交时间</th>
                <th width="8%" style="text-align: center">状态</th>
                <th width="10%" style="text-align: center">操作</th>
            </tr>
            <tbody>
            <c:forEach items="${recruitInfoExts}" var="recruitInfoExt" varStatus="seq">
                <tr>
                    <td>${recruitInfoExt.recruitYear}</td>
                    <td>${recruitInfoExt.sysUser.userName}</td>
                    <td>${recruitInfoExt.sysUser.sexName}</td>
                    <td>身份证</td>
                    <td>${recruitInfoExt.sysUser.idNo}</td>
                    <td>${recruitInfoExt.ticketNumber}</td>
                    <td>${recruitInfoExt.submitTime}</td>
                    <td>${recruitInfoExt.auditStatusName}</td>
                    <td>
                        <c:if test="${recruitInfoExt.auditStatusId eq 'Passing'}">
                            <a href="#" onclick="auditRecruitInfo('${recruitInfoExt.recruitFlow}')" style="color: blue">审核</a>
                        </c:if>
                        <c:if test="${recruitInfoExt.auditStatusId ne 'Passing'}">
                            <a href="javascript:examineRecruitInfo('${recruitInfoExt.recruitFlow}')" style="color: blue">查看</a>
                        </c:if>
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