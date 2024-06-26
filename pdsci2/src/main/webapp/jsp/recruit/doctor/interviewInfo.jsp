
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
            $("#auditStatusForm").submit();
        }

        function toPage(page)
        {
            page=page||1;
            $("#currentPage").val(page);
        }


        function examineInterviewInfo(recruitFlow) {
            jboxOpen("<s:url value='/recruit/interviewInfo/examineInterviewInfo'/>?recruitFlow="+recruitFlow, "面试通知", 500, 460 ,true);
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="xllist" style="width: 100%;margin-top: 35px">
            <tr>
                <th width="8%" >年份</th>
                <th width="10%">姓名</th>
                <th width="5%" style="text-align: center">性别</th>
                <th width="8%" style="text-align: center">证件类型</th>
                <th width="15%" style="text-align: center">证件号码</th>
                <th width="10%" style="text-align: center">说明</th>
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
                    <td>面试通知</td>
                    <td>
                        <a href="javascript:examineInterviewInfo('${recruitInfoExt.recruitFlow}')" style="color: blue">查看</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
            <c:if test="${empty recruitInfoExts}">
                <tr><td align="center" colspan="10">无记录</td></tr>
            </c:if>
        </table>
    </div>
</div>
</body>
</html>