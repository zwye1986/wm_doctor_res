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
            jboxPost("<s:url value='/nyzl/retestStudent/saveRetestStudent'/>", $("#StuDataAddForm").serialize(), function (resp) {
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
            <input type="hidden" name="recordFlow" value="${retestStudent.recordFlow}"/>
            <input type="hidden" name="stuSign" value="${stuSignFlag}"/>
            <input type="hidden" name="adminFlag" value="${adminFlag}"/>
            <tbody><tr>
                <th>考生编号：
                </th>
                <td>
                    <input type="text" id="stuNo" name="stuNo" class="validate[required,maxSize[25]] xltext" value="${retestStudent.stuNo}">
                </td>
                <th>姓&#12288;&#12288;名：
                </th>
                <td><input class="validate[required,maxSize[25]] xltext" id="stuName" name="stuName" type="text" value="${retestStudent.stuName}">
                </td>
            </tr>
            <tr>
                <th>身份证号：
                </th>
                <td>
                    <input type="text" class="validate[required,custom[chinaIdLoose]] xltext" id="cardNo" name="cardNo" value="${retestStudent.cardNo}">
                </td>
                <th>毕业单位(学校)：
                </th>
                <td>
                    <input class="validate[required,maxSize[25]] xltext" id="graduationUnit" name="graduationUnit" type="text" value="${retestStudent.graduationUnit}">
                </td>
            </tr>
            <tr>
                <th>博士类型：</th>
                <td>
                    <select name="educationTypeId" class="validate[required] xlselect">
                        <option value="" <c:if test="${empty retestStudent.educationTypeId}">selected="selected"</c:if>>请选择</option>
                        <option value="1" <c:if test="${'1' eq retestStudent.educationTypeId}">selected="selected"</c:if>>全日制</option>
                        <option value="0" <c:if test="${'0' eq retestStudent.educationTypeId}">selected="selected"</c:if>>在职</option>
                    </select>
                </td>
                <th>年&#12288;&#12288;份：
                </th>
                <td>
                    <input class="validate[required,maxSize[25]] xltext" id="recruitYear" name="recruitYear" type="text" value="${retestStudent.recruitYear}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
                </td>
            </tr>
            <tr>
                <th>合格证编号：
                </th>
                <td>
                    <input type="text" class="xltext" id="certificateNo" name="certificateNo" value="${retestStudent.certificateNo}">
                </td>
                <th>意向报考导师：
                </th>
                <td>
                    <input class="xltext" id="intentionTeacherName" name="intentionTeacherName" type="text" value="${retestStudent.intentionTeacherName}">
                </td>
            </tr>
            <tr>
                <th>报考类型：
                </th>
                <td>
                    <select name="applicationCategoryId" class="validate[required] xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumNyzlApplicationTypeList}" var="applicationType">
                            <option value="${applicationType.dictId}" <c:if test="${retestStudent.applicationCategoryId==applicationType.dictId}">selected="selected"</c:if>>${applicationType.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
                <th>毕业专业名称：
                </th>
                <td>
                    <input class="validate[required,maxSize[25]] xltext"  id="graduationSpeName" name="graduationSpeName" type="text" value="${retestStudent.graduationSpeName}">
                </td>
            </tr>
            <tr>
                <th>学位类型：
                </th>
                <td>
                    <select name="degreeTypeId" class="validate[required] xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumNyzlDegreeTypeList}" var="degreeType">
                            <option value="${degreeType.dictId}" <c:if test="${retestStudent.degreeTypeId==degreeType.dictId}">selected="selected"</c:if>>${degreeType.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
                <th>报考专业名称：
                </th>
                <td>
                    <input class="validate[required] xltext" name="speName" type="text" value="${retestStudent.speName}"/>
                    <%--<select name="speId" class="validate[required] xlselect">--%>
                        <%--<option value="">请选择</option>--%>
                        <%--<c:forEach items="${dictTypeEnumNyzlSpeList}" var="spe">--%>
                            <%--<option value="${spe.dictId}" <c:if test="${retestStudent.speId==spe.dictId}">selected="selected"</c:if>>${spe.dictName}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                </td>
            </tr>
            <tr>
                <th>方&#12288;&#12288;向：
                </th>
                <td>
                    <input class="validate[required] xltext" name="directionName" type="text" value="${retestStudent.directionName}"/>
                    <%--<select name="directionId" class="validate[required] xlselect">--%>
                        <%--<option value="">请选择</option>--%>
                        <%--<c:forEach items="${dictTypeEnumNyzlDirectionList}" var="direction">--%>
                            <%--<option value="${direction.dictId}" <c:if test="${retestStudent.directionId==direction.dictId}">selected="selected"</c:if>>${direction.dictName}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                </td>
                <th>性&#12288;&#12288;别：
                </th>
                <td>
                    <select name="sexId" class="validate[required] xlselect">
                        <option value="">请选择</option>
                        <option value="Man" <c:if test="${retestStudent.sexId eq 'Man'}">selected="selected"</c:if>>男</option>
                        <option value="Woman" <c:if test="${retestStudent.sexId eq 'Woman'}">selected="selected"</c:if>>女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>移动电话：
                </th>
                <td>
                    <input class="validate[required,custom[phone]] xltext " id="contactPhone" name="contactPhone" type="text" value="${retestStudent.contactPhone}">
                </td>
                <th>电子信箱：
                </th>
                <td>
                    <input class="validate[required,custom[email]] xltext" id="contactEmail" name="contactEmail" type="text" value="${retestStudent.contactEmail}">
                </td>
            </tr>
            <tr>
                <th>政治理论名称：
                </th>
                <td>
                    <input class="validate[required,maxSize[25]] xltext" id="politicalTheory" name="politicalTheory" type="text" value="${retestStudent.politicalTheory}">
                </td>
                <th>政治理论分数：
                </th>
                <td>
                    <input class="validate[required,custom[number]] xltext" id="politicalTheoryGrade" name="politicalTheoryGrade" type="text" value="${retestStudent.politicalTheoryGrade}">
                </td>
            </tr>
            <tr>
                <th>外国语名称：
                </th>
                <td>
                    <input class="validate[required,maxSize[25]] xltext" id="foreignLanguage" name="foreignLanguage" type="text" value="${retestStudent.foreignLanguage}">
                </td>
                <th>外国语分数：
                </th>
                <td>
                    <input class="validate[required,custom[number]] xltext" id="foreignLanguageGrade" name="foreignLanguageGrade" type="text" value="${retestStudent.foreignLanguageGrade}">
                </td>
            </tr>
            <tr>
                <th>业务课1名称：
                </th>
                <td>
                    <input class="validate[required,maxSize[25]] xltext" id="businessOne" name="businessOne" type="text" value="${retestStudent.businessOne}">
                </td>
                <th>业务课1分数：
                </th>
                <td>
                    <input class="validate[required,custom[number]] xltext" id="businessOneGrade" name="businessOneGrade" type="text" value="${retestStudent.businessOneGrade}">
                </td>
            </tr>
            <tr>
                <th>业务课2名称：
                </th>
                <td>
                    <input class="validate[required,maxSize[25]] xltext" id="businessTwo" name="businessTwo" type="text" value="${retestStudent.businessTwo}">
                </td>
                <th>业务课2分数：
                </th>
                <td>
                    <input class="validate[custom[number]] xltext" id="businessTwoGrade" name="businessTwoGrade" type="text" value="${retestStudent.businessTwoGrade}">
                </td>
            </tr>
            <tr>
                <th>总&#12288;&#12288;分：
                </th>
                <td>
                    <input class="validate[required,custom[number]] xltext" id="stuGrade" name="stuGrade" type="text" value="${retestStudent.stuGrade}">
                </td>
                <th>一级学科名称：
                </th>
                <td>
                    <input class="validate[required,maxSize[25]] xltext" id="subjectOne" name="subjectOne" type="text" value="${retestStudent.subjectOne}">
                </td>
            </tr>
            <tr>
                <th>全校排名：
                </th>
                <td>
                    <input class="validate[required,custom[integer]] xltext" id="schoolRanking" name="schoolRanking" type="text" value="${retestStudent.schoolRanking}">
                </td>
                <th>全院排名：
                </th>
                <td>
                    <input class="validate[required,custom[integer]] xltext" id="collegeRankging" name="collegeRankging" type="text" value="${retestStudent.collegeRankging}">
                </td>
            </tr>
            <tr>
                <%--<th>报到状态：--%>
                <%--</th>--%>
                <%--<td>--%>
                    <%--<select name="registerStatusId" class="validate[required] xlselect">--%>
                        <%--<option value="">请选择</option>--%>
                        <%--<c:forEach items="${nyzlRegisterStatusEnumList}" var="rs">--%>
                            <%--<option value="${rs.id}" <c:if test="${retestStudent.registerStatusId == rs.id}">selected="selected"</c:if>>${rs.name}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                <%--</td>--%>
                <th>复试批次：
                </th>
                <td>
                    <select name="swapBatchId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumNyzlBatchList}" var="batch">
                            <option value="${batch.dictId}" <c:if test="${retestStudent.swapBatchId eq batch.dictId}">selected="selected"</c:if>>${batch.dictName}</option>
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
