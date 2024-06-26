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
</head>
<body>
<div class="printDiv">


    <table  style="width:100%;border:0px; border-collapse:separate; border-spacing:0px 10px;">
        <input type="hidden" name="gradationId" value="${empty eduCourseMaterial.gradationId?eduCourse.gradationId:eduCourseMaterial.gradationId}"/>
        <input type="hidden" name="assumeOrgFlow" value="${empty eduCourseMaterial.assumeOrgFlow?eduCourse.assumeOrgFlow:eduCourseMaterial.assumeOrgFlow}"/>
        <input type="hidden" name="recordFlow" value="${eduCourseMaterial.recordFlow}"/>
        <input type="hidden" name="formType" value="${xjEduMaterialTypeEnumKcdg.id}"/>
        <div style="text-align:center;line-height:30px; margin:20px 0">
            <p style="font-size:28px;font-weight:bold;">研究生课程教学大纲</p>
        </div>
        <tr>
            <td colspan="2" style="width:100%;border:0px;padding:0px;margin-top:10px; text-align: center;">中文课程名称：${empty eduCourseMaterial.courseName?eduCourse.courseName:eduCourseMaterial.courseName} </td>
        </tr>
        <tr>
            <td colspan="2" style="width:100%;border:0px;padding:0px;text-align: center; ">英文课程名称：${empty eduCourseMaterial.courseNameEn?eduCourse.courseNameEn:eduCourseMaterial.courseNameEn}</td>
        </tr>
        <tr>
            <td style="border:0px;padding:0px;">&emsp;总学时：${eduCourseMaterial.coursePeriod}</td>
            <td style="border:0px;padding:0px;">&emsp;总学分：${eduCourseMaterial.courseCredit}</td>

        </tr>
        <tr>
            <td style="width:40%;border:0px;padding:0px;">授课层次： ${eduCourseMaterial.gradationName} </td>
            <td style="width:60%;border:0px;padding:0px;">承担单位： ${eduCourseMaterial.assumeOrgName}</td>
        </tr>
        <tr>
            <td colspan="2" >一、课程简介：<br/>
                &nbsp; &nbsp;${eduCourseMaterial.courseCredit}
            </td>

        </tr>

        <tr>
            <td colspan="2" >二、课程目标：<br/>
                &nbsp; &nbsp;${outlineForm.courseTarget}
            </td>
        </tr>
        <tr>
            <td colspan="2" >三、课程进度：<br/>
                &nbsp; &nbsp;${outlineForm.courseTerm}
            </td>
        </tr>
        <tr>
            <td colspan="2" >四、教学方式及要求：<br/>
                &nbsp; &nbsp;${outlineForm.teachingMethod}
            </td>
        </tr>
        <tr>
            <td colspan="2" >五、考核方式：<br/>
                &nbsp; &nbsp;${outlineForm.assessType}
            </td>
        </tr>
        <tr>
            <td colspan="2" >六、教材及主要参考书目：<br/>
                &nbsp; &nbsp;${outlineForm.teachingMaterial}
            </td>
        </tr>

    </table>
</div>
</body>
</html>