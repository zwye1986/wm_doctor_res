<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
    </script>
</head>
<body>

<div class="search_table">
    <div class="div_table" style="margin:10px;padding:0px;height:500px;overflow: auto;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="20%"/>
                <col width="15%"/>
                <col width="10%"/>
                <col width="20%"/>
                <col width="17%"/>
                <col width="18%"/>
            </colgroup>
            <tr>
                <th>轮转科室</th>
                <th>姓名</th>
                <th>年级</th>
                <th>培训专业</th>
                <th>轮转开始时间</th>
                <th>轮转结束时间</th>
            </tr>
            <c:forEach items="${doctorListMap}" var="doctor">
                <tr>
                    <td>${doctor.schDeptName}</td>
                    <td>${doctor.doctorName}</td>
                    <td>${doctor.sessionNumber}</td>
                    <td>${doctor.trainingSpeName}</td>
                    <td>${doctor.schStartDate}</td>
                    <td>${doctor.schEndDate}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty doctorListMap}">
                <tr>
                    <td colspan="6" style="text-align: center;">暂无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
        <div align="center" class="button" >
            <input type="button" class="btn_green" onclick="javascript:jboxClose();"
                   value="关&#12288;闭"/>
        </div>
</div>
</body>
</html>
