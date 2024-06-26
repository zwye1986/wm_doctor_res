<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true" />
    <jsp:param name="jbox" value="true" />
    <jsp:param name="jquery_validation" value="true" />
    <jsp:param name="jquery_datePicker" value="true" />
</jsp:include>
<table class="xllist">
    <colgroup>
        <col width="10%"/>
        <col width="10%"/>
        <col width="10%"/>
        <col width="10%"/>
        <col width="10%"/>
        <col width="10%"/>
        <col width="10%"/>
        <col width="10%"/>
        <col width="10%"/>
        <col width="10%"/>
    </colgroup>
    <tr>
        <th>培训专业</th>
        <th>年级</th>
        <th>跟师记录</th>
        <th>跟师学习<br/>笔记</th>
        <th>跟师心得</th>
        <th>中医经典书籍<br/>学习记录</th>
        <th>经典医籍<br/>学习体会</th>
        <th>跟师医案</th>
        <th>跟师学习<br/>年度考核<br/>情况记录表</th>
        <th>跟师学习<br/>结业考核<br/>情况记录表</th>
    </tr>
    <c:if test="${not empty resDiscipleReq.trainingTypeId}">
        <tr>
            <td>${resDiscipleReq.trainingTypeName}</td>
            <td>${resDiscipleReq.sessionNumber}</td>
            <td><input
                    onchange="saveReq('${resDiscipleReq.trainingTypeId}','${resDiscipleReq.sessionNumber}','${noteTypeEnumFollowTeacherRecord}',this);"
                    class="input" type="text"
                    value="${resDiscipleReqMap[resDiscipleReq.trainingTypeId.concat(noteTypeEnumFollowTeacherRecord)]}"
                    style="height:20px;width: 50px; text-align: center"/></td>
            <td><input
                    onchange="saveReq('${resDiscipleReq.trainingTypeId}','${resDiscipleReq.sessionNumber}','${noteTypeEnumNote}',this);"
                    class="input" type="text"
                    value="${resDiscipleReqMap[resDiscipleReq.trainingTypeId.concat(noteTypeEnumNote)]}"
                    style="height:20px;width: 50px; text-align: center"/></td>
            <td><input
                    onchange="saveReq('${resDiscipleReq.trainingTypeId}','${resDiscipleReq.sessionNumber}','${noteTypeEnumExperience}',this);"
                    class="input" type="text"
                    value="${resDiscipleReqMap[resDiscipleReq.trainingTypeId.concat(noteTypeEnumExperience)]}"
                    style="height:20px;width: 50px; text-align: center"/></td>
            <td><input
                    onchange="saveReq('${resDiscipleReq.trainingTypeId}','${resDiscipleReq.sessionNumber}','${noteTypeEnumBookRecord}',this);"
                    class="input" type="text"
                    value="${resDiscipleReqMap[resDiscipleReq.trainingTypeId.concat(noteTypeEnumBookRecord)]}"
                    style="height:20px;width: 50px; text-align: center"/></td>
            <td><input
                    onchange="saveReq('${resDiscipleReq.trainingTypeId}','${resDiscipleReq.sessionNumber}','${noteTypeEnumBookExperience}',this);"
                    class="input" type="text"
                    value="${resDiscipleReqMap[resDiscipleReq.trainingTypeId.concat(noteTypeEnumBookExperience)]}"
                    style="height:20px;width: 50px; text-align: center"/></td>
            <td><input
                    onchange="saveReq('${resDiscipleReq.trainingTypeId}','${resDiscipleReq.sessionNumber}','${noteTypeEnumTypicalCases}',this);"
                    class="input" type="text"
                    value="${resDiscipleReqMap[resDiscipleReq.trainingTypeId.concat(noteTypeEnumTypicalCases)]}"
                    style="height:20px;width: 50px; text-align: center"/></td>
            <td><input
                    onchange="saveReq('${resDiscipleReq.trainingTypeId}','${resDiscipleReq.sessionNumber}','${noteTypeEnumAnnualAssessment}',this);"
                    class="input" type="text"
                    value="${resDiscipleReqMap[resDiscipleReq.trainingTypeId.concat(noteTypeEnumAnnualAssessment)]}"
                    style="height:20px;width: 50px; text-align: center"/></td>
            <td><input
                    onchange="saveReq('${resDiscipleReq.trainingTypeId}','${resDiscipleReq.sessionNumber}','${noteTypeEnumGraduationAssessment}',this);"
                    class="input" type="text"
                    value="${resDiscipleReqMap[resDiscipleReq.trainingTypeId.concat(noteTypeEnumGraduationAssessment)]}"
                    style="height:20px;width: 50px; text-align: center"/></td>
        </tr>
    </c:if>
    <c:if test="${empty resDiscipleReq.trainingTypeId}">
        <c:forEach items="${recDocCategoryEnumList}" var="category">
            <tr>
                <c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
                <c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
                    <td>${category.name}</td>
                    <td>${resDiscipleReq.sessionNumber}</td>
                    <td><input
                            onchange="saveReq('${category.id}','${resDiscipleReq.sessionNumber}','${noteTypeEnumFollowTeacherRecord}',this);"
                            class="input" type="text"
                            value="${resDiscipleReqMap[category.id.concat(noteTypeEnumFollowTeacherRecord)]}"
                            style="height:20px;width: 50px; text-align: center"/></td>
                    <td><input
                            onchange="saveReq('${category.id}','${resDiscipleReq.sessionNumber}','${noteTypeEnumNote}',this);"
                            class="input" type="text"
                            value="${resDiscipleReqMap[category.id.concat(noteTypeEnumNote)]}"
                            style="height:20px;width: 50px; text-align: center"/></td>
                    <td><input
                            onchange="saveReq('${category.id}','${resDiscipleReq.sessionNumber}','${noteTypeEnumExperience}',this);"
                            class="input" type="text"
                            value="${resDiscipleReqMap[category.id.concat(noteTypeEnumExperience)]}"
                            style="height:20px;width: 50px; text-align: center"/></td>
                    <td><input
                            onchange="saveReq('${category.id}','${resDiscipleReq.sessionNumber}','${noteTypeEnumBookRecord}',this);"
                            class="input" type="text"
                            value="${resDiscipleReqMap[category.id.concat(noteTypeEnumBookRecord)]}"
                            style="height:20px;width: 50px; text-align: center"/></td>
                    <td><input
                            onchange="saveReq('${category.id}','${resDiscipleReq.sessionNumber}','${noteTypeEnumBookExperience}',this);"
                            class="input" type="text"
                            value="${resDiscipleReqMap[category.id.concat(noteTypeEnumBookExperience)]}"
                            style="height:20px;width: 50px; text-align: center"/></td>
                    <td><input
                            onchange="saveReq('${category.id}','${resDiscipleReq.sessionNumber}','${noteTypeEnumTypicalCases}',this);"
                            class="input" type="text"
                            value="${resDiscipleReqMap[category.id.concat(noteTypeEnumTypicalCases)]}"
                            style="height:20px;width: 50px; text-align: center"/></td>
                    <td><input
                            onchange="saveReq('${category.id}','${resDiscipleReq.sessionNumber}','${noteTypeEnumAnnualAssessment}',this);"
                            class="input" type="text"
                            value="${resDiscipleReqMap[category.id.concat(noteTypeEnumAnnualAssessment)]}"
                            style="height:20px;width: 50px; text-align: center"/></td>
                    <td><input
                            onchange="saveReq('${category.id}','${resDiscipleReq.sessionNumber}','${noteTypeEnumGraduationAssessment}',this);"
                            class="input" type="text"
                            value="${resDiscipleReqMap[category.id.concat(noteTypeEnumGraduationAssessment)]}"
                            style="height:20px;width: 50px; text-align: center"/></td>
                </c:if>
            </tr>
        </c:forEach>
    </c:if>
</table>
<div style="padding-top: 20px;" class="red">
tip：要求数为年要求数！
</div>


