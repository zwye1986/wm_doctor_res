<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_qrcode" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <style>
        .xlselect{margin-right: 0px;}
        .xltext{margin-right: 0px;}
    </style>
    <script type="text/javascript">
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="subjectFlow" value="${subject.subjectFlow}">
            <div id="dataTable">
                <table class="basic" style="width: 100%;margin: 10px 0px;">
                    <tr>
                        <td style="text-align:right;">课程名称：</td>
                        <td>
                               ${subject.subjectName}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">课程年份：</td>
                        <td>
                        ${subject.subjectYear}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">课程类型：</td>
                        <td>
                            <c:forEach items="${dictTypeEnumCourseTypeList}" var="dict">
                                <c:if test="${dict.dictId eq subject.subjectType}">${dict.dictName}</c:if>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">预约开放时间：</td>
                        <td>
                            ${subject.subjectStartTime} — ${subject.subjectEndTime}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">预约人员容量：</td>
                        <td>
                             ${subject.reservationsNum}
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">审核通过人数：</td>
                        <td>
                              ${passNum}
                        </td>
                    </tr>
                    <tr>
                            <td style="text-align:right;">课程说明：</td>
                            <td>${subject.subjectExplain}
                            </td>
                        </tr>
                </table>
            </div>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();" id="closeBtn"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>