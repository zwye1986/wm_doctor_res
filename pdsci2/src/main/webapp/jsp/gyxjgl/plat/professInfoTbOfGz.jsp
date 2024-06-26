<table class="xllist" style="width:100%;">
    <div hidden="hidden" id="delFlowsDiv"></div>
    <tr>
        <th style="width: 28%;">(专业代码)专业名称</th>
        <th style="width: 28%;">(课程代码)课程名称</th>
        <td rowspan="4" style="width: 8%;">
            <div style="padding:3px 0;"><a style="cursor: pointer;" onclick="toMove('','left');"><img style="width: 30px;" src="<s:url value='/css/skin/${skinPath}/images/select_edit.png'/>"/></a></div>
        </td>
        <td rowspan="2" style="width: 8%;">
            <div style="padding:3px 0;"><a style="cursor: pointer;" onclick="toMove('OptionalNeed','right');"><img
                    style="width: 30px;"
                    src="<s:url value='/'/>css/skin/LightBlue/images/select_add.png"></a></div>
        </td>
        <th style="width: 28%;">必选课</th>
    </tr>
    <tr>
        <td rowspan="3" id="majorTd"><c:if test="${not empty param.majorId}">(${param.majorId })${majorName }</c:if></td>
        <td rowspan="3" id="notIncludeTd">
            <c:forEach items="${courseMap['notIncludeList'] }" var="course">
                <%--<c:set var="sumPeriod" value="${sumPeriod+course.coursePeriod }"/>--%>
                <%--<c:set var="sumCredit" value="${sumCredit+course.courseCredit }"/> --%>
                <div>
                    <a style="cursor: pointer;" onclick="clickOn(this);" id="${course.courseFlow }" period="${periodMap[course.courseFlow]}" credit="${creditMap[course.courseFlow]}">(${course.courseCode })${course.courseName}</a>
                    <a onclick="del(this,'${course.courseFlow}','${course.courseCode}','${course.courseName}','${periodMap[course.courseFlow]}','${creditMap[course.courseFlow]}');" style="cursor: pointer;"><img style="width: 10px;padding-bottom: 3px;" src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>"/></a>
                </div>
            </c:forEach>
        </td>
        <td id="OptionalNeedTd" class="sumTd">
            <c:forEach items="${courseMap['optionalNeedList'] }" var="course">
                <c:set var="sumPeriod" value="${sumPeriod+course.coursePeriod }"/>
                <c:set var="sumCredit" value="${sumCredit+course.courseCredit }"/>
                <div><a style="cursor: pointer;" onclick="clickOn(this);" id="${course.courseFlow }" courseCode="${course.courseCode }" courseName="${course.courseName }"  period="${periodMap[course.courseFlow]}" credit="${creditMap[course.courseFlow]}">(${course.courseCode })${course.courseName }</a></div>
            </c:forEach>
        </td>
    </tr>
    <tr>
        <td rowspan="2">
            <div style="padding:3px 0;"><a style="cursor: pointer;" onclick="toMove('Public','right');"><img style="width: 30px;" src="<s:url value='/css/skin/${skinPath}/images/select_add.png'/>"/></a></div>
        </td>
        <th>选修课</th>
    </tr>
    <tr>
        <td id="PublicTd" class="sumTd">
            <c:forEach items="${courseMap['publicList'] }" var="course">
                <c:set var="sumPeriod" value="${sumPeriod+course.coursePeriod }"/>
                <c:set var="sumCredit" value="${sumCredit+course.courseCredit }"/>
                <div><a style="cursor: pointer;" onclick="clickOn(this);" id="${course.courseFlow }" courseCode="${course.courseCode }" courseName="${course.courseName }"  period="${periodMap[course.courseFlow]}" credit="${creditMap[course.courseFlow]}">(${course.courseCode })${course.courseName }</a></div>
            </c:forEach>
        </td>
    </tr>
    <%--<tr>--%>
        <%--<td rowspan="2">--%>
            <%--<div style="padding:3px 0;"><a style="cursor: pointer;" onclick="toMove('PublicNeed','right');"><img style="width: 30px;" src="<s:url value='/css/skin/${skinPath}/images/select_add.png'/>"/></a></div>--%>
        <%--</td>--%>
        <%--<th>公共必选课</th>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td id="PublicNeedTd" class="sumTd">--%>
            <%--<c:forEach items="${courseMap['publicNeedList'] }" var="course">--%>
                <%--<c:set var="sumPeriod" value="${sumPeriod+course.coursePeriod }"/>--%>
                <%--<c:set var="sumCredit" value="${sumCredit+course.courseCredit }"/>--%>
                <%--<div><a style="cursor: pointer;" onclick="clickOn(this);" id="${course.courseFlow }" courseCode="${course.courseCode }" courseName="${course.courseName }"  period="${periodMap[course.courseFlow]}" credit="${creditMap[course.courseFlow]}">(${course.courseCode })${course.courseName }</a></div>--%>
            <%--</c:forEach>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td rowspan="2">--%>
            <%--<div style="padding:3px 0;"><a style="cursor: pointer;" onclick="toMove('Optional','right');"><img style="width: 30px;" src="<s:url value='/css/skin/${skinPath}/images/select_add.png'/>"/></a></div>--%>
        <%--</td>--%>
        <%--<th>专业选修课</th>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td id="OptionalTd" class="sumTd">--%>
            <%--<c:forEach items="${courseMap['optionalList'] }" var="course">--%>
                <%--<c:set var="sumPeriod" value="${sumPeriod+course.coursePeriod }"/>--%>
                <%--<c:set var="sumCredit" value="${sumCredit+course.courseCredit }"/>--%>
                <%--<div><a style="cursor: pointer;" onclick="clickOn(this);" id="${course.courseFlow }" courseCode="${course.courseCode }" courseName="${course.courseName }"  period="${periodMap[course.courseFlow]}" credit="${creditMap[course.courseFlow]}">(${course.courseCode })${course.courseName }</a></div>--%>
            <%--</c:forEach>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td rowspan="2">--%>
            <%--<div style="padding:3px 0;"><a style="cursor: pointer;" onclick="toMove('OptionalNeed','right');"><img style="width: 30px;" src="<s:url value='/css/skin/${skinPath}/images/select_add.png'/>"/></a></div>--%>
        <%--</td>--%>
        <%--<th>专业必选课</th>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td id="OptionalNeedTd" class="sumTd">--%>
            <%--<c:forEach items="${courseMap['optionalNeedList'] }" var="course">--%>
                <%--<c:set var="sumPeriod" value="${sumPeriod+course.coursePeriod }"/>--%>
                <%--<c:set var="sumCredit" value="${sumCredit+course.courseCredit }"/>--%>
                <%--<div><a style="cursor: pointer;" onclick="clickOn(this);" id="${course.courseFlow }" courseCode="${course.courseCode }" courseName="${course.courseName }"  period="${periodMap[course.courseFlow]}" credit="${creditMap[course.courseFlow]}">(${course.courseCode })${course.courseName }</a></div>--%>
            <%--</c:forEach>--%>
        <%--</td>--%>
    <%--</tr>--%>
    <tr>
        <td colspan="4">合计：<c:set var="sumCourse" value="${fn:length(courseMap['publicList'])+fn:length(courseMap['optionalNeedList'])}"/></td>
        <td>课程数：<span class="sum1"><c:out value="${sumCourse }" default="0"/></span>门
            <br>
            总学时数：<span class="sum2"><c:out value="${sumPeriod }" default="0"/></span>学时
            <br>
            总学分数：<span class="sum3">
							<fmt:formatNumber pattern="0.0" value="${sumCredit }" var="s"/><c:out value="${s}" default="0"/>
						</span>学分
        </td>
    </tr>
</table>