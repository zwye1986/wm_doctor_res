<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>

<script type="text/javascript">
    $(document).ready(function(){
        init();
        calculate();
        <c:if test="${eduUser.chooseCourseStatusId!=xjChooseCourseStatusEnumChoose.id}">
            initClassChecked();
        </c:if>
    });
    /**
     * 页面绑定课程选中情况
     */
    function initClassChecked(){
        $('input[name="courseFlow"]').each(function(){
            var courseFlow=$(this).val();
            var courseUserCount = parseFloat($("#courseUserCount_"+courseFlow).text());
            var chooseCount =parseFloat($("#chooseCount_"+courseFlow).text());
            if($(this).attr("checked") =="checked"){
                $("#chooseCount_"+courseFlow).text(chooseCount+1);
            }
        });
    }
    /**
     * 学位课程禁用
     */
    function init(){
        //学位课程中的课程层次与学生的培养层次一致时，默认全部选中
        $(".${xjglCourseTypeEnumDegree.id}_checkBox").each(function(){
            var className=$(this).attr("class");
            if(className.indexOf("${eduUser.trainTypeId}_checkBox")>-1) {
                if(${strideFlag eq 'checked'}) {
                    $(this).attr("checked", false);
                }else{
                    $(this).attr("checked", true);
                }
                $(this).attr("disabled", true);
                $(this).parent().parent().removeAttr("onclick");
                $(this).parent().parent().removeAttr("style");
            }
            if(className.indexOf("3_checkBox")>-1){
                $(this).attr("disabled", false);
            }
        });

        $("input[type='checkbox']").click(function(event){
            event.stopPropagation();
        });

    }
</script>
<table class="xllist table" width="100%">
    <tr style="height: 60px;">
        <th style="width:15%;">课程类别</th>
        <th style="width:30%;">课程列表</th>
        <th style="width:9%;">学时</th>
        <th style="width:9%;">学分</th>
        <th style="width:20%;">课程容纳人数(已选人数)</th>
        <th style="width:9%;">学位课程</th>
        <th style="width:9%;">操作</th>
    </tr>
    <c:if test="${not empty courseMajorExtList}">
        <tr>
            <td>合计</td>
            <td>已选&nbsp;<span id="courseCount" style="color:red;">0</span>&nbsp;门课程</td>
            <td><span id="coursePeriodSum" style="color:red;"></span></td>
            <td><span id="courseCreditSum" style="color:red;"></span></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td>学位课程合计</td>
            <td>已选&nbsp;<span id="degreeCourseCount" style="color:red;">0</span>&nbsp;门学位课程</td>
            <td><span id="degreeCoursePeriodSum" style="color:red;"></span></td>
            <td><span id="degreeCourseCreditSum" style="color:red;"></span></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </c:if>
    <!-- 循环课程类别 -->
    <c:forEach items="${xjglCourseTypeEnumList}" var="courseType">
        <c:set var="isFirst" value="true"/> <!-- 首列 （false无Td）-->
        <!-- 循环该课程类别下设所有课程 -->
        <c:if test="${courseType.id == 'OptionalNeed' || courseType.id == 'Public' }">
            <c:forEach items="${resultMap['courseTypeMajorMap'][courseType.id]}" var="courseMajorExt" varStatus="status">
                <!-- 课程 -->
                <c:set var="course" value="${courseMajorExt.course}"/>
                <!-- 已选人数 -->
                <c:set var="chooseCount" value="${empty resultMap['chooseCountMap'][course.courseFlow]?'0':resultMap['chooseCountMap'][course.courseFlow]}"/>
                <!-- 课程容纳人数 -->
                <c:set var="courseUserCount" value="${empty course.courseUserCount?0:course.courseUserCount}"/>
                <!-- 该用户选课 -->
                <c:set var="chooseStudentCourse" value="${chooseCourseMap['chooseCourseMap'][courseType.id][course.courseFlow]}"/>

                <tr onclick="maintain('${course.courseFlow }','tr');" style="cursor: pointer;">
                    <c:set var="rowSapn" value="${resultMap['courseTypeMajorMap'][courseType.id].size()}"/>
                    <c:if test="${isFirst}">
                        <td style="width:15%;" rowspan="${rowSapn}">
                            <c:if test="${courseType.name=='专业必选课'}">必选课</c:if>
                            <c:if test="${courseType.name=='公共选修课'}">选修课</c:if>
                        </td>
                    </c:if>
                    <td style="text-align: left;padding-left: 10px;">
                        <font <c:if test="${chooseStudentCourse.replenishFlag eq GlobalConstant.FLAG_Y}">color="red"</c:if>
                              title="<table class='basic' style='width:300px;'>
					 <tr><th  style='text-align: left'>教学组长：${course.courseSpeaker }&nbsp;联系电话：${course.courseSpeakerPhone }</th></tr>
					 <tr><th style='text-align: left'>${!empty course.courseIntro? course.courseIntro:'无'}</th></tr></table>">
                            [${course.courseCode}]${course.courseName}</font>
                    </td>
                    <td>${course.coursePeriod}</td>
                    <td>${course.courseCredit}</td>
                    <td>
                        <font id="courseUserCount_${course.courseFlow }">${courseUserCount}</font>（<font color="red" id="chooseCount_${course.courseFlow }">${chooseCount}</font>）
                    </td>
                    <td>
                        <input type="checkbox" name="degreeCourseFlag" ${chooseStudentCourse.degreeCourseFlag eq 'Y'?'checked':''} onclick="saveDegreeCourse('${chooseStudentCourse.recordFlow}','${course.courseFlow }',this);"/>
                    </td>
                    <td>
                        <input type="hidden" id="coursePeriod_${course.courseFlow }" value="${course.coursePeriod}"/>
                        <input type="hidden" id="courseCredit_${course.courseFlow }" value="${course.courseCredit}"/>
                        <input type="hidden" id="courseTypeId_${course.courseFlow }" value="${courseType.id}"/>
                        <input type="hidden" id="courseTypeName_${course.courseFlow }" value="${courseType.name}"/>
                        <input type="hidden" id="courseCode_${course.courseFlow }" value="${course.courseCode}"/>
                        <input type="hidden" id="courseName_${course.courseFlow }" value="${course.courseName}"/>
                        <input type="hidden" id="courseNameEn_${course.courseFlow }" value="${course.courseNameEn}"/>
                        <input type="hidden" id="courseSession_${course.courseFlow }" value="${course.courseSession}"/>
                        <input type="checkbox" class="${courseType.id}_checkBox ${course.gradationId}_checkBox ${course.courseFlow}" name="courseFlow" value="${course.courseFlow}"
                               onclick="maintain('${course.courseFlow }','',this);"
                               <c:if test="${not empty chooseStudentCourse}">checked="checked"</c:if>>
                    </td>
                </tr>
                <c:set var="isFirst" value="false"/>
            </c:forEach>
            <c:if test="${empty resultMap['courseTypeMajorMap'][courseType.id]}">
                <tr>
                    <td style="width:15%;">
                        <c:if test="${courseType.name=='专业必选课'}">必选课</c:if>
                        <c:if test="${courseType.name=='公共选修课'}">选修课</c:if>
                    </td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                    <td>--</td>
                </tr>
            </c:if>
        </c:if>
    </c:forEach>

</table>
