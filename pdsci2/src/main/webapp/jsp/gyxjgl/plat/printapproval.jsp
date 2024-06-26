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
         <form  id="myForm" method="post">
             <input type="hidden" name="courseFlow" value="${param.courseFlow}">
             <input type="hidden" name="jsonArry" id="jsonArry">
             <input type="hidden" name="printFlag" value="jxspb">
             <div style="text-align:center;line-height:30px;">
                 <p style="font-size:18px;font-weight:bold;">广州医科大学硕士研究生基础课程教学审批表</p>
                 ${form.schoolYearDesc}    学年度   （${form.gradeDesc}年级） 第   ${form.termDesc}    学期
             </div>
             <table class="basic" style="width:100%;border:0px;">
                 <tr>
                     <td style="width:40%;border:0px;padding:0px;">课程名称： ${empty form?course.courseName:form.courseName}</td>
                     <td style="width:60%;border:0px;padding:0px;">授课教研室：${empty form?course.assumeOrgName:form.teachingPlace}</td>
                 </tr>
                 <tr>
                     <td style="border:0px;padding:0px;">英文名称：${empty form?course.courseNameEn:form.courseNameEn}</td>
                     <td style="border:0px;padding:0px;"><div style="float:left;line-height:35px;height:100%;">理论课教师：</div><c:forEach items="${subs}" var="su" varStatus="i"><c:if test="${su.isTestTeacher eq 'N'}">${su.teacherName}<c:if test="${!i.last}">&ensp;</c:if></c:if></c:forEach></td>
                 </tr>
                 <tr>
                     <td style="border:0px;padding:0px;">教材名称：${form.teachingMaterial}</td>
                     <td style="border:0px;padding:0px;">
                         作者：${form.authorName}
                         版次：${form.layoutNum}
                         出版社：${form.pressName}
                     </td>
                 </tr>
             </table>
             <table class="basic" style="width: 100%;">
                 <tr>
                     <th style="width:15%;text-align:center;padding:0px;">教师姓名</th>
                     <th style="width:15%;text-align:center;padding:0px;">职称</th>
                     <th style="width:60%;text-align:center;padding:0px;">授课内容</th>
                     <th style="width:10%;text-align:center;padding:0px;">学时</th>
                 </tr>
                 <c:forEach items="${subs}" var="su">
                     <c:if test="${su.isTestTeacher  eq 'N'}">
                         <tr>
                             <td style="text-align:center;padding:0px;">${su.teacherName}<input type="hidden" class="recordFlow" value="${su.recordFlow}"></td>
                             <td style="text-align:center;padding:0px;">${su.teacherPost}</td>
                             <td style="text-align:center;padding:0px;">${su.contentDesc}</td>
                             <td style="text-align:center;padding:0px;">${su.schoolHours}</td>
                         </tr>
                     </c:if>
                 </c:forEach>
             </table>
             <div style="width:100%;text-align:center;margin-top: 3px;margin-bottom: 3px;">
                 <span style="font-size: 14px;">实验课指导教师</span>
             </div>
             <table class="basic" style="width: 100%;">
                 <tr>
                     <th style="width:15%;text-align:center;padding:0px;">教师姓名</th>
                     <th style="width:15%;text-align:center;padding:0px;">职称</th>
                     <th style="width:60%;text-align:center;padding:0px;">实验名称</th>
                     <th style="width:10%;text-align:center;padding:0px;">学时</th>
                 </tr>
                 <tbody id="test">
                 <c:forEach items="${subs}" var="su">
                     <c:if test="${su.isTestTeacher  eq 'Y'}">
                         <tr>
                             <td style="text-align:center;padding:0px;">${su.teacherName}<input type="hidden" class="recordFlow" value="${su.recordFlow}"></td>
                             <td style="text-align:center;padding:0px;">${su.teacherPost}</td>
                             <td style="text-align:center;padding:0px;">${su.contentDesc}</td>
                             <td style="text-align:center;padding:0px;">${su.schoolHours}</td>
                         </tr>
                     </c:if>
                 </c:forEach>
                 </tbody>
             </table>
         </form>


         <div id="printDiv2" hidden="hidden">
             <div style="text-align:center;line-height:30px;">
                 <p style="font-size:18px;font-weight:bold;">广州医科大学硕士研究生基础课程教学审批表</p>
                 ${form.schoolYearDesc}学年度（${form.gradeDesc}年级）第${form.termDesc}学期
             </div>
             <table class="basic" style="width:100%;border:0px;">
                 <tr>
                     <td style="width:40%;border:0px;padding:0px;">课程名称：${empty form?course.courseName:form.courseName}</td>
                     <td style="width:60%;border:0px;padding:0px;">授课教研室：${empty form?course.assumeOrgName:form.teachingPlace}</td>
                 </tr>
                 <tr>
                     <td style="border:0px;padding:0px;">英文名称：${empty form?course.courseNameEn:form.courseNameEn}</td>
                     <td style="border:0px;padding:0px;"><div style="float:left;line-height:35px;height:100%;">理论课教师：</div><c:forEach items="${subs}" var="su" varStatus="i"><c:if test="${su.isTestTeacher eq 'N'}">${su.teacherName}<c:if test="${!i.last}">&ensp;</c:if></c:if></c:forEach></td>
                 </tr>
                 <tr>
                     <td style="border:0px;padding:0px;">教材名称：${form.teachingMaterial}</td>
                     <td style="border:0px;padding:0px;">
                         作者：${form.authorName}&emsp;
                         版次：${form.layoutNum}&emsp;
                         出版社：${form.pressName}
                     </td>
                 </tr>
             </table>
             <table class="basic" style="width: 100%;">
                 <tr>
                     <th style="width:15%;text-align:center;padding:0px;">教师姓名</th>
                     <th style="width:15%;text-align:center;padding:0px;">职称</th>
                     <th style="width:60%;text-align:center;padding:0px;">授课内容</th>
                     <th style="width:10%;text-align:center;padding:0px;">学时</th>
                 </tr>
                 <c:forEach items="${subs}" var="su">
                     <c:if test="${su.isTestTeacher  eq 'N'}">
                         <tr>
                             <td style="text-align:center;padding:0px;">${su.teacherName}</td>
                             <td style="text-align:center;padding:0px;">${su.teacherPost}</td>
                             <td style="text-align:center;padding:0px;">${su.contentDesc}</td>
                             <td style="text-align:center;padding:0px;">${su.schoolHours}</td>
                         </tr>
                     </c:if>
                 </c:forEach>
             </table>
             <div style="width:100%;text-align:center;margin-top: 3px;margin-bottom: 3px;">
                 <span style="font-size: 14px;">实验课指导教师</span>
             </div>
             <table class="basic" style="width: 100%;">
                 <tr>
                     <th style="width:15%;text-align:center;padding:0px;">教师姓名</th>
                     <th style="width:15%;text-align:center;padding:0px;">职称</th>
                     <th style="width:60%;text-align:center;padding:0px;">实验名称</th>
                     <th style="width:10%;text-align:center;padding:0px;">学时</th>
                 </tr>
                 <tbody id="test">
                 <c:forEach items="${subs}" var="su">
                     <c:if test="${su.isTestTeacher  eq 'Y'}">
                         <tr>
                             <td style="text-align:center;padding:0px;">${su.teacherName}</td>
                             <td style="text-align:center;padding:0px;">${su.teacherPost}</td>
                             <td style="text-align:center;padding:0px;">${su.contentDesc}</td>
                             <td style="text-align:center;padding:0px;">${su.schoolHours}</td>
                         </tr>
                     </c:if>
                 </c:forEach>
                 </tbody>
             </table>
         </div>
     </div>



</div>
</body>
</html>