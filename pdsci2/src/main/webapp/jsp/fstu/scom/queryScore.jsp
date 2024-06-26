<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <style type="text/css">
        .content div {
            margin-top: 5px;
        }
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <table class="basic" style="width: 100%;">
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学分年份：</th>
                    <td>
                        <select name="sessionNumber" style="width:140px;" disabled="disabled">
                            <option/>
                            <c:forEach items="${dictTypeEnumScoreYearList}" var="dict">
                                <option value="${dict.dictName}"
                                        <c:if test="${dict.dictName eq score.sessionNumber}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th style="width: 20%;">学员姓名：</th>
                    <td>
                        <input type="text" name="userName" value="${score.userName}" readonly="readonly"
                               style="width:137px;" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">学员工号：</th>
                    <td>
                        <input type="text" name="userCode" value="${score.userCode}" readonly="readonly"
                               style="width:137px;" readonly="readonly">
                    </td>
                    <th style="width: 20%;">用户所属部门：</th>
                    <td>
                        <input type="text" name="userDeptName" value="${score.userDeptName}" readonly="readonly"
                               style="width:137px;" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学分类别：</th>
                    <td>
                        <select id="firstScoreTypeId" name="firstScoreTypeId" style="width:140px;" disabled="disabled">
                            <option/>
                            <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                <option value="${dict.dictId}"
                                        <c:if test="${dict.dictId eq score.firstScoreTypeId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th style="width: 20%;"><span style="color:red;">*</span>项目大类：</th>
                    <td>
                        <select name="firstProjTypeId" style="width:140px;" disabled="disabled">
                            <option/>
                            <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                <c:set var="dictKey" value="dictTypeEnumAcaScoreType.${dict.dictId}List"/>
                                <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                    <option class="${scope.dictTypeId}" value="${scope.dictId}"
                                            <c:if test="${scope.dictId eq score.firstProjTypeId and dict.dictId eq score.firstScoreTypeId}">selected</c:if>>${scope.dictName}</option>
                                </c:forEach>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>评分项：</th>
                    <td>
                        <select id="firstScoreItemId" name="firstScoreItemId" style="width:140px;" disabled="disabled">
                            <option/>
                            <c:forEach items="${dictTypeEnumAssessItemList}" var="dict">
                                <option value="${dict.dictId}"
                                        <c:if test="${dict.dictId eq score.firstScoreItemId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th style="width: 20%;"><span style="color:red;">*</span>完成情况：</th>
                    <td>
                        <select id="firstExecutionId" name="firstExecutionId" style="width:140px;" disabled="disabled">
                            <option/>
                            <c:forEach items="${dictTypeEnumAccomplishResultList}" var="dict">
                                <option value="${dict.dictId}"
                                        <c:if test="${dict.dictId eq score.firstExecutionId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">名称：</th>
                    <td>
                        <input type="text" name="scoreName" value="${score.scoreName}" style="width:137px;"
                               readonly="readonly">
                    </td>
                    <th style="width: 20%;">编号：</th>
                    <td>
                        <input type="text" name="scoreNumber" value="${score.scoreNumber}" style="width:137px;"
                               readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">开始时间：</th>
                    <td>
                        <input type="text" readonly="readonly" style="width:137px;" name="beginTime"
                               value="${score.beginTime}">
                    </td>
                    <th style="width: 20%;">结束时间：</th>
                    <td>
                        <input type="text" readonly="readonly" style="width:137px;" name="endTime"
                               value="${score.endTime}">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">说明：</th>
                    <td colspan="3">
                        <textarea type="text" name="instruction" style="width: 550px;height: 80px" class="xltextarea" placeholder="支持250字符"
                                  readonly="readonly">${score.instruction}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>分值：</th>
                    <td>
                        <input type="text" name="myScore" value="${score.myScore}"
                               class="validate[required,custom[number]]" style="width:137px;" readonly="readonly">
                    </td>
                    <th style="width: 20%;">最大分值：</th>
                    <td>
                        <input type="text" name="firstMaxScore" value="${score.firstMaxScore}" readonly="readonly"
                               style="width:137px;">
                    </td>
                </tr>
            </table>
            <c:if test="${not empty pubFileList}">
            <table class="basic" style="width: 100%;">
                <tr>
                    <th class="bs_name" colspan="2">附件信息</th>
                </tr>
                <tr>
                    <td width="38%" style="text-align: center">附件名称</td>
                    <td style="text-align: center">附件内容</td>
                </tr>
                <c:forEach var="file" items="${pubFileList}" varStatus="status">
                    <tr>
                        <td>
                            <c:set value="${fn:toLowerCase(pdfn:getFileSuffix(file.filePath))}" var="picFlag"/>
                            <a href='<s:url value="/fstu/dec/fileDown?fileFlow=${file.fileFlow}"/>'
                               <c:if test="${picFlag eq 'jpg'|| picFlag eq 'jpg'||picFlag eq 'jpeg'||picFlag eq 'gif'||picFlag eq 'png'||picFlag eq 'bmp'}">target="_blank"</c:if>
                            >${file.fileName}</a>
                        </td>
                        <td >
                            <input class="validate[required,maxSize[100]] xltext" style="width: 90%;" name="fileRemark" readonly="readonly" type="text" value="${file.fileRemark}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            </c:if>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </div>
    </div>
</div>
</body>
</html>