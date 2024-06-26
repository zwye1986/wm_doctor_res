<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
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
    </jsp:include>

    <script type="text/javascript">

    </script>

    <style>

    </style>
</head>
<body>
<div class="content">
    <div class="title1 clearfix">
        <div id="tagContent">
            <fieldset>
                <legend>填报信息表</legend>
                <div style="text-align: center">
                    <form id="recruitInfoForm" name="recruitInfoForm" method="post">
                        <table class="xllist" style="margin-top: 30px">
                            <tr>
                                <th>
                                    <label>姓&#12288;&#12288;名：</label>
                                </th>
                                <td>
                                    <input value="${currentUser.userName}" class="xltext" readonly/>
                                </td>
                                <th>
                                    <label>性&#12288;&#12288;別：</label>
                                </th>
                                <td>
                                    <input value="${currentUser.sexName}" class="xltext" readonly>
                                </td>
                                <th>
                                    <label>证件类型：</label>
                                </th>
                                <td>
                                    <input value="${currentUser.cretTypeName}" class="xltext" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>证件号码：</label>
                                </th>
                                <td>
                                    <input value="${currentUser.idNo}" class="xltext" readonly/>
                                </td>
                                <th>
                                    <label>本人手机：</label>
                                </th>
                                <td>
                                    <input value="${currentUser.userPhone}" class="xltext" readonly/>
                                </td>
                                <th>
                                    <label>学员类型：</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.doctorTypeName}" class="xltext" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>联系人姓名:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.linkMan}" class="xltext" readonly/>
                                </td>
                                <th>
                                    <label>联系人电话:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.linkManPhone}" class="xltext" readonly/>
                                </td>
                                <th>
                                    <label>单位名称:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.workOrgName}" class="xltext" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>单位级别：</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.workOrgLevelName}" class="xltext" readonly/>

                                </td>
                                <th>
                                    <label>报考专业第一志愿：</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.trainingSpeName}" class="xltext" readonly/>
                                </td>
                                <th>
                                    <label>是否调剂：</label>
                                </th>
                                <td>
                                    <input <c:if test="${recruitInfo.isSwap eq 'Y'}">value="是"</c:if> <c:if test="${recruitInfo.isSwap eq 'N'}">value="否"</c:if> class="xltext" readonly>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>参培年份:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.sessionNumber}" class="xltext" readonly/>
                                </td>
                                <th>
                                    <label>本科学历毕业时间:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.graduationTime}" class="xltext" readonly>
                                </td>
                                <th>
                                    <label>本科学历毕业院校:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.graduatedName}" class="xltext" readonly>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>本科学历所学专业:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.graduatedMajor}" class="xltext" readonly>
                                </td>
                                <th>
                                    <label>硕士学历毕业时间:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.masterGratime}" class="xltext" readonly>
                                </td>
                                <th>
                                    <label>硕士学历毕业院校:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.masterGraschoolName}" class="xltext" readonly>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>硕士学历所学专业:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.masterMajor}" class="xltext" readonly>
                                </td>
                                <th>
                                    <label>硕士学历类型:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.masterdegreeTypeName}" class="xltext" readonly>
                                </td>
                                <th>
                                    <label>博士学历毕业时间:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.doctorGratime}" class="xltext" readonly>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>博士学历毕业院校:</label>
                                </th>
                                <td>
                                    <input id="doctorGraschoolName" name="doctorGraschoolName" value="${recruitInfo.doctorGraschoolName}" class="xltext" readonly>
                                </td>
                                <th>
                                    <label>博士学历所学专业:</label>
                                </th>
                                <td>
                                    <input id="doctorMajor" name="doctorMajor" value="${recruitInfo.doctorMajor}" class="xltext" readonly>
                                </td>
                                <th>
                                    <label>博士学历类型:</label>
                                </th>
                                <td>
                                    <input value="${recruitInfo.doctorDegreeTypeName}" class="xltext" readonly>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <label>往届/应届:</label>
                                </th>
                                <td>
                                    <input <c:if test="${recruitInfo.isYearGraduate eq '01'}">value="应届"</c:if> <c:if test="${recruitInfo.isYearGraduate eq '02'}">value="往届"</c:if> class="xltext" readonly>
                                </td>
                                <th>
                                    <label>是否为执业医师:</label>
                                </th>
                                <td>
                                    <input <c:if test="${recruitInfo.isHaveQualification eq 'Y'}">value="是"</c:if> <c:if test="${recruitInfo.isHaveQualification eq 'N'}">value="否"</c:if> class="xltext" readonly>
                                </td>
                                <c:if test="${recruitInfo.isHaveQualification eq 'Y'}">
                                    <th>
                                        <label>执业医师证号:</label>
                                    </th>
                                    <td>
                                        <input id="qualificationCode" name="qualificationCode" value="${recruitInfo.qualificationCode}" class="xltext" readonly>
                                    </td>
                                </c:if>
                            </tr>
                        </table>
                    </form>
                </div>
            </fieldset>
        </div>
    </div>
</div>
</body>
</html>