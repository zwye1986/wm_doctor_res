
<%@include file="/jsp/common/doctype.jsp" %>
<!-- 合计 -->
<c:set var="courseCount" value="0"/>
<c:set var="coursePeriodSum" value="0"/>
<c:set var="courseCreditSum" value="0"/>
<!-- 学位课程合计 -->
<c:set var="degreeCourseCount" value="0"/>
<c:set var="degreeCoursePeriodSum" value="0"/>
<c:set var="degreeCourseCreditSum" value="0"/>
<c:set var="gzFlag" value="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'}"/>
<table class="xllist table" width="100%">
    <tr style="height: 60px;">
        <c:if test="${gzFlag}">
            <th style="width:20%;">课程类别</th>
            <th style="width:35%;">课程列表</th>
            <th style="width:15%;">学时</th>
            <th style="width:15%;">学分</th>
            <th style="width:15%;">学位课程</th>
        </c:if>
    </tr>

    <c:if test="${not empty studentCourseList}">
        <tr>
            <td>合计</td>
            <td>已选&nbsp;<span id="courseCount">0</span>&nbsp;门课程</td>
            <td><span id="coursePeriodSum"></span></td>
            <td><span id="courseCreditSum"></span></td>
            <td>--</td>
        </tr>
        <tr>
            <td>学位课程合计</td>
            <td>已选&nbsp;<span id="degreeCourseCount">0</span>&nbsp;门学位课程</td>
            <td><span id="degreeCoursePeriodSum"></span></td>
            <td><span id="degreeCourseCreditSum"></span></td>
            <td>--</td>
        </tr>
    </c:if>
    <!-- 循环课程类别 -->
    <c:forEach items="${xjglCourseTypeEnumList}" var="courseType">
        <c:set var="isFirst" value="true"/> <!-- 首列 （false无Td）-->
        <!-- 循环该课程类别下   已选所有课程 -->
        <c:if test="${courseType.id == 'OptionalNeed' || courseType.id == 'Public' }">
            <c:forEach items="${studentCourseMap[courseType.id]}" var="studentCourse" varStatus="status">
                <!-- 课程 -->
                <c:if test="${not empty studentCourseMap[courseType.id]}">
                    <tr>
                        <c:set var="rowSapn" value="${studentCourseMap[courseType.id].size()}"/>
                        <c:if test="${isFirst}">
                            <td style="width:20%;" rowspan="${rowSapn}">
                                <c:if test="${courseType.name=='专业必选课'}">必选课</c:if>
                                <c:if test="${courseType.name=='公共选修课'}">选修课</c:if>
                            </td>
                        </c:if>
                        <td>
                            <font color="${studentCourse.replenishFlag eq GlobalConstant.FLAG_Y?'red':''}">[${studentCourse.courseCode}]${studentCourse.courseName}</font>
                        </td>
                        <td>${studentCourse.coursePeriod}</td>
                        <td>${studentCourse.courseCredit}</td>
                        <td>${studentCourse.degreeCourseFlag eq 'Y'?'是':'否'}</td>
                        <c:set var="courseCount" value="${courseCount + 1}"/>
                        <c:set var="coursePeriodSum" value="${coursePeriodSum + studentCourse.coursePeriod}"/>
                        <c:set var="courseCreditSum" value="${courseCreditSum + studentCourse.courseCredit}"/>
                        <c:if test="${studentCourse.degreeCourseFlag eq 'Y'}">
                            <c:set var="degreeCourseCount" value="${degreeCourseCount + 1}"/>
                            <c:set var="degreeCoursePeriodSum" value="${degreeCoursePeriodSum + studentCourse.coursePeriod}"/>
                            <c:set var="degreeCourseCreditSum" value="${degreeCourseCreditSum + studentCourse.courseCredit}"/>
                        </c:if>
                    </tr>
                    <script>
                        $("#courseCount").html('${courseCount}');
                        $("#coursePeriodSum").html('${coursePeriodSum}');
                        $("#degreeCourseCount").html('${degreeCourseCount}');
                        $("#degreeCoursePeriodSum").html('${degreeCoursePeriodSum}');
                        $("#courseCreditSum").html(Number('${courseCreditSum}').toFixed(1));
                        $("#degreeCourseCreditSum").html(Number('${degreeCourseCreditSum}').toFixed(1));
                    </script>
                    <c:set var="isFirst" value="false"/>
                </c:if>
            </c:forEach>
            <c:if test="${empty studentCourseMap[courseType.id]}">
                <tr>
                    <td style="width:20%;">
                        <c:if test="${courseType.name=='专业必选课'}">必选课</c:if>
                        <c:if test="${courseType.name=='公共选修课'}">选修课</c:if>
                    </td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                </tr>
            </c:if>
        </c:if>
    </c:forEach>

</table>
