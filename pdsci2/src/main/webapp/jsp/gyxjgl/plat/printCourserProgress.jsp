<%@include file="/jsp/common/doctype.jsp" %>
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
    <jsp:param name="ueditor" value="true"/>
</jsp:include>
<html>
<head>
    <style>
    </style>
</head>
<body>
 <div class="printDiv">
  <div class="content">
  <input type="hidden" name="printFlag" value="Kcjdb">
  <input id="jsondata" type="hidden" name="json" value=""/>
  <input type="hidden" name="courseFlow" value="${param.courseFlow}">
  <input type="hidden" name="recordFlow" value="${eduCourseMaterial.recordFlow}"/>
  <div style="text-align:center;line-height:30px;">
      <p style="font-size:18px;font-weight:bold;">广州医科大学硕士研究生基础课教学进度表</p>
     ${eduCourseMaterial.schoolYearDesc} 学年度  （${eduCourseMaterial.gradeDesc} 年级）  第 ${eduCourseMaterial.termDesc} 学期  </div>
</div>
          <table class="basic" style="width:100%;border:0px;">
              <tr>
                  <td style="width:40%;border:0px;padding:0px;">课程名称：${empty eduCourseMaterial?eduCourse.courseName:eduCourseMaterial.courseName} </td>
                  <td style="width:60%;border:0px;padding:0px;">授课教研室：${empty eduCourseMaterial?eduCourse.assumeOrgName:eduCourseMaterial.assumeOrgName} </td>
              </tr>
              <tr>
                  <td colspan="2" style="width:100%;border:0px;padding:0px;">授课地点：${eduCourseMaterial.teachingPlace}</td>
              </tr>
          </table>
          <div style="width:100%;text-align:center;margin-top: 3px;margin-bottom: 3px;">

          </div>
    <table class="basic" style="width: 100%;">
          <tr>
              <th style="width:10%;text-align:center;padding:0px;">周次</th>
              <th style="width:10%;text-align:center;padding:0px;">月/日</th>
              <th style="width:10%;text-align:center;padding:0px;">星期</th>
              <th style="width:10%;text-align:center;padding:0px;">时数</th>
              <th style="width:10%;text-align:center;padding:0px;">节次</th>
              <th style="width:35%;text-align:center;padding:0px;">讲授内容（理论课或实验课）</th>
              <th style="width:10%;text-align:center;padding:0px;">授课人</th>
          </tr>
          <tbody id="test">
          <c:forEach items="${courserProgressList}" var="su">
              <tr>
                  <td style="text-align:center;padding:0px;">${su.weekTimes}</td>
                  <td style="text-align:center;padding:0px;">${su.monthOrDay}</td>
                  <td style="text-align:center;padding:0px;">${su.week}</td>
                  <td style="text-align:center;padding:0px;">${su.hours}</td>
                  <td style="text-align:center;padding:0px;">${su.festivals} </td>
                  <td style="text-align:center;padding:0px;">${su.teachingContent} </td>
                  <td>  ${su.teachingman}</td>
              </tr>
          </c:forEach>

          </tbody>
      </table>

    </div>
</div>
</body>
</html>