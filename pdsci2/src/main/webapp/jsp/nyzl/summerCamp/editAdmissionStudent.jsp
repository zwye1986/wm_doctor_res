<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            if (!$("#StuDataAddForm").validationEngine("validate")) {
                return;
            }
            var orgFlowText = $("select[name='orgFlow'] option:selected").text();
            $("input[name='orgName']").val(orgFlowText);
            jboxPost("<s:url value='/nyzl/admissionStudent/saveAdmissionStudent'/>", $("#StuDataAddForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <form id="StuDataAddForm">
        <div class="content">
            <table class="basic" width="100%">
                <input type="hidden" name="recordFlow" value="${admissionStudent.recordFlow}"/>
                <%--<input type="hidden" name="stuSign" value="${stuSignFlag}"/>--%>
                <%--<input type="hidden" name="adminFlag" value="${adminFlag}"/>--%>
                <tbody><tr>
                    <th>姓&#12288;&#12288;名：
                    </th>
                    <td><input class="validate[required,maxSize[25]] xltext" id="stuName" name="stuName" type="text" value="${admissionStudent.stuName}">
                    </td>
                    <th>身份证号：
                    </th>
                    <td>
                        <input type="text" class="validate[required,custom[chinaIdLoose]] xltext" id="cardNo" name="cardNo" value="${admissionStudent.cardNo}">
                    </td>
                </tr>
                <tr>
                    <th>培养单位：
                    </th>
                    <td>
                        <select name="orgFlow" class="validate[required] xlselect">
                            <option value="">请选择</option>
                            <c:forEach items="${orgs}" var="org">
                                <option value="${org.orgFlow}" <c:if test="${admissionStudent.orgFlow==org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="orgName" name="orgName" value="${admissionStudent.orgName}"/>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
                <tr>
                    <th>录取专业代码：</th>
                    <td>
                        <input class="validate[required,maxSize[25]] xltext" id="speId" name="speId" type="text" value="${admissionStudent.speId}">
                    </td>
                    <th>录取专业名称：
                    </th>
                    <td>
                        <input class="validate[required,maxSize[25]] xltext" id="speName" name="speName" type="text" value="${admissionStudent.speName}">
                    </td>
                </tr>
                <tr>
                    <th>录取专业研究方向代码：
                    </th>
                    <td>
                        <input type="text" class="xltext" name="stuSpeDirectionId" value="${admissionStudent.stuSpeDirectionId}">
                    </td>
                    <th>录取专业研究方向：
                    </th>
                    <td>
                        <input class="xltext" name="stuSpeDirectionName" type="text" value="${admissionStudent.stuSpeDirectionName}">
                    </td>
                </tr>
                <tr>
                    <th>复试成绩：
                    </th>
                    <td>
                        <input class="validate[required,custom[number]] xltext" name="retestGrade" type="text" value="${admissionStudent.retestGrade}">
                    </td>
                    <th>总成绩：
                    </th>
                    <td>
                        <input class="validate[required,custom[number]] xltext" name="totalGrade" type="text" value="${admissionStudent.totalGrade}">
                    </td>
                </tr>
                <tr>
                    <th>导师所在单位：
                    </th>
                    <td>
                        <input class="validate[required] xltext" name="teaWorkUnit" type="text" value="${admissionStudent.teaWorkUnit}"/>
                    </td>
                    <th>导师姓名：
                    </th>
                    <td>
                        <input class="validate[required] xltext" name="teaName" type="text" value="${admissionStudent.teaName}"/>
                    </td>
                </tr>
                <tr>
                    <th>导师方向：
                    </th>
                    <td>
                        <input class="validate[required] xltext" name="teaDirectionName" type="text" value="${admissionStudent.teaDirectionName}"/>
                    </td>
                    <th>报考方向：
                    </th>
                    <td>
                        <input class="validate[required] xltext" name="stuDirectionName" type="text" value="${admissionStudent.stuDirectionName}"/>
                    </td>
                </tr>

                <tr>
                    <th>复试小组入学总成绩排名：
                    </th>
                    <td>
                        <input class="validate[required,custom[number]] xltext" name="groupRanking" type="text" value="${admissionStudent.groupRanking}">
                    </td>
                    <th>是否录取：
                    </th>
                    <td>
                        <select name="admissionFlag" class="validate[required] xlselect">
                            <option value="">请选择</option>
                            <option value="Y" <c:if test="${admissionStudent.admissionFlag == 'Y'}">selected="selected"</c:if>>是</option>
                            <option value="N" <c:if test="${admissionStudent.admissionFlag == 'N'}">selected="selected"</c:if>>否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>学位类型：
                    </th>
                    <td>
                        <select name="degreeTypeId" class="validate[required] xlselect">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumNyzlDegreeTypeList}" var="degreeType">
                                <option value="${degreeType.dictId}" <c:if test="${admissionStudent.degreeTypeId==degreeType.dictId}">selected="selected"</c:if>>${degreeType.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th></th>
                    <td></td>
                </tr>
                </tbody></table>
            <br>
            <div style="text-align: center;">
                <input type="button" value="保&#12288;存" class="search" onclick="save()">
                <input type="button" value="关&#12288;闭" class="search" onclick="jboxClose()">
            </div>
        </div>
    </form></div>
</body>
</html>
