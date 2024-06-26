<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(statusId){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            $("#statusId").val(statusId);
            if(statusId == 'Submit'){
                $("#statusName").val("提交");
            }
            jboxPost("<s:url value='/xjgl/paper/saveDefence'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <fieldset>
                <legend>基本信息</legend>
                <table class="basic" style="width: 100%;">
                    <input type="hidden" name="recordFlow" value="${defence.recordFlow}"/>
                    <input type="hidden" name="stuNo" value="${baseInfo.sid}"/>
                    <input type="hidden" name="userFlow" value="${baseInfo.userFlow}"/>
                    <input type="hidden" name="userName" value="${baseInfo.userName}"/>
                    <input type="hidden" name="sexId" value="${baseInfo.sexId}"/>
                    <input type="hidden" name="sexName" value="${baseInfo.sexName}"/>
                    <input type="hidden" name="userPhone" value="${baseInfo.userPhone}"/>
                    <input type="hidden" name="majorId" value="${baseInfo.majorId}"/>
                    <input type="hidden" name="majorName" value="${baseInfo.majorName}"/>
                    <input type="hidden" name="trainGradationId" value="${baseInfo.trainTypeId}"/>
                    <input type="hidden" name="trainGradationName" value="${baseInfo.trainTypeName}"/>
                    <input type="hidden" name="trainCategoryId" value="${baseInfo.trainCategoryId}"/>
                    <input type="hidden" name="trainCategoryName" value="${baseInfo.trainCategoryName}"/>
                    <input type="hidden" name="tutorFlow" value="${baseInfo.firstTeacherFlow}"/>
                    <input type="hidden" name="tutorName" value="${baseInfo.firstTeacher}"/>
                    <input type="hidden" name="tutorPhone" value="${baseInfo.firstTeacherPhone}"/>
                    <input type="hidden" name="twoTutorFlow" value="${baseInfo.secondTeacherFlow}"/>
                    <input type="hidden" name="twoTutorName" value="${baseInfo.secondTeacher}"/>
                    <input type="hidden" name="twoTutorPhone" value="${baseInfo.secondTeacherPhone}"/>
                    <input type="hidden" name="pydwOrgFlow" value="${baseInfo.orgFlow}"/>
                    <input type="hidden" name="pydwOrgName" value="${baseInfo.orgName}"/>
                    <input type="hidden" name="fwhOrgFlow" value="${baseInfo.deptFlow}"/>
                    <input type="hidden" name="fwhOrgName" value="${baseInfo.deptName}"/>
                    <input type="hidden" name="studyFormId" value="${baseInfo.studyFormId}"/>
                    <input type="hidden" name="studyFormName" value="${baseInfo.studyFormName}"/>
                    <input type="hidden" name="isReplenish" value="${isReplenish}"/>
                    <input type="hidden" name="statusId" id="statusId"/>
                    <input type="hidden" name="statusName" id="statusName" value="保存"/>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">省市代码：</th>
                        <td style="text-align:left;padding:0px;border:0px;">44</td>
                        <th style="text-align:right;padding:0px;border:0px;">省市名称：</th>
                        <td style="text-align:left;padding:0px;border:0px;">广东省</td>
                        <th style="text-align:right;padding:0px;border:0px;">学校代码：</th>
                        <td style="text-align:left;padding:0px;border:0px;">12121</td>
                        <th style="text-align:right;padding:0px;border:0px;">学校名称：</th>
                        <td style="text-align:left;padding:0px;border:0px;">南方医科大学</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">学号：</th>
                        <td style="text-align:left;padding:0px;border:0px;">${baseInfo.sid}</td>
                        <th style="text-align:right;padding:0px;border:0px;">姓名：</th>
                        <td style="text-align:left;padding:0px;border:0px;">${baseInfo.userName}</td>
                        <th style="text-align:right;padding:0px;border:0px;">专业代码：</th>
                        <td style="text-align:left;padding:0px;border:0px;">${baseInfo.majorId}</td>
                        <th style="text-align:right;padding:0px;border:0px;">专业名称：</th>
                        <td style="text-align:left;padding:0px;border:0px;">${baseInfo.majorName}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">培养层次：</th>
                        <td style="text-align:left;padding:0px;border:0px;">${baseInfo.trainTypeName}</td>
                        <th style="text-align:right;padding:0px;border:0px;">培养类型：</th>
                        <td style="text-align:left;padding:0px;border:0px;">${baseInfo.trainCategoryName}</td>
                        <th style="text-align:right;padding:0px;border:0px;">导师一：</th>
                        <td style="text-align:left;padding:0px;border:0px;">${baseInfo.firstTeacher}</td>
                        <th style="text-align:right;padding:0px;border:0px;">导师二：</th>
                        <td style="text-align:left;padding:0px;border:0px;">${baseInfo.secondTeacher}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">培养单位：</th>
                        <td colspan="3" style="text-align:left;padding:0px;border:0px;">${baseInfo.orgName}</td>
                        <th style="text-align:right;padding:0px;border:0px;">学位分委员会：</th>
                        <td colspan="3" style="text-align:left;padding:0px;border:0px;">${baseInfo.deptName}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">学习形式：</th>
                        <td colspan="7" style="text-align:left;padding:0px;border:0px;">${baseInfo.studyFormName}</td>
                    </tr>
                </table>
            </fieldset>
            <fieldset>
                <legend><c:if test="${isReplenish ne 'Y'}">答辩申请信息</c:if><c:if test="${isReplenish eq 'Y'}">学位补授申请信息</c:if></legend>
                <table class="basic" style="width: 100%;">
                    <c:if test="${isReplenish eq 'Y'}">
                        <tr>
                            <th style="text-align:right;padding:0px;border:0px;min-width: 120px;">申请学位补授时间：</th>
                            <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">
                                <c:if test="${param.viewFlag ne 'view'}">
                                    <select class="validate[required] select" name="replenishTime" style="width:156px;">
                                        <option/>
                                        <c:forEach items="${dictTypeEnumReplenishTimeList}" var="dt">
                                            <option value="${dt.dictName}" ${dt.dictName eq defence.replenishTime?'selected':''}>${dt.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </c:if>
                                <c:if test="${param.viewFlag eq 'view'}">${defence.replenishTime}</c:if>
                            </td>
                            <th style="text-align:right;padding:0px;border:0px;min-width: 92px;"></th>
                            <td style="text-align:left;padding:0px;border:0px;line-height: 130%;"></td>
                        </tr>
                    </c:if>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;min-width: 120px;"><c:if test="${isReplenish ne 'Y'}">答辩时间：</c:if><c:if test="${isReplenish eq 'Y'}">学位论文答辩时间：</c:if></th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">
                            <c:if test="${param.viewFlag ne 'view'}">
                                <select class="validate[required] select" name="defenceTime" style="width:156px;">
                                    <option/>
                                    <c:forEach items="${dictTypeEnumDefenceTimeList}" var="dt">
                                        <option value="${dt.dictName}" ${dt.dictName eq defence.defenceTime?'selected':''}>${dt.dictName}</option>
                                    </c:forEach>
                                </select>
                            </c:if>
                            <c:if test="${param.viewFlag eq 'view'}">${defence.defenceTime}</c:if>
                        </td>
                        <th style="text-align:right;padding:0px;border:0px;min-width: 92px;">评审论文命名：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">
                            <c:if test="${param.viewFlag ne 'view'}">
                                <input type="text" class="validate[required]" readonly="readonly" name="paperAuditName" value="12121_${baseInfo.majorId}_${baseInfo.sid}_LW"/>
                            </c:if>
                            <c:if test="${param.viewFlag eq 'view'}">12121_${baseInfo.majorId}_${baseInfo.sid}_LW</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;min-width: 92px;">中文论文题目：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;min-width: 240px;">
                            <c:if test="${param.viewFlag ne 'view'}">
                                <input type="text" class="validate[required]" name="paperChiTitle" value="${defence.paperChiTitle}"/>
                            </c:if>
                            <c:if test="${param.viewFlag eq 'view'}">${defence.paperChiTitle}</c:if>
                        </td>
                        <th style="text-align:right;padding:0px;border:0px;min-width: 92px;">英文论文题目：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;min-width: 240px;">
                            <c:if test="${param.viewFlag ne 'view'}">
                                <input type="text" class="validate[required]" name="paperEngTitle" value="${defence.paperEngTitle}"/>
                            </c:if>
                            <c:if test="${param.viewFlag eq 'view'}">${defence.paperEngTitle}</c:if>
                        </td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">关键词：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">
                            <c:if test="${param.viewFlag ne 'view'}">
                                <input type="text" class="validate[required]" name="keyWord" value="${defence.keyWord}"/>
                            </c:if>
                            <c:if test="${param.viewFlag eq 'view'}">${defence.keyWord}</c:if>
                        </td>
                        <th style="text-align:right;padding:0px;border:0px;">研究方向：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">
                            <c:if test="${param.viewFlag ne 'view'}">
                                <input type="text" class="validate[required]" name="researchDirection" value="${defence.researchDirection}"/>
                            </c:if>
                            <c:if test="${param.viewFlag eq 'view'}">${defence.researchDirection}</c:if>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <c:if test="${empty param.role && param.viewFlag ne 'view'}">
                <font style="color: red;padding-left: 8px;">注意：点击“保存”后还可以编辑、修改；点击“提交”后无法修改，请确认无误后再提交！</font>
            </c:if>
            <%--排除学生新增/编辑情况 审核意见展示--%>
            <c:if test="${!(empty param.role && param.viewFlag ne 'view')}">
                <%--导师审核流程开启--%>
                <c:if test="${applicationScope.sysCfgMap['xjgl_audit_tutor'] eq GlobalConstant.FLAG_Y}">
                    <c:if test="${!empty defence.tutorAuditId && !empty defence.twoTutorAuditId}">
                        <fieldset>
                            <legend>导师一意见</legend>
                            <textarea style="width:100%;" name="tutorAuditAdvice" ${param.viewFlag eq 'view'?'readonly':''}>${defence.tutorAuditAdvice}</textarea>
                        </fieldset>
                        <fieldset>
                            <legend>导师二意见</legend>
                            <textarea style="width:100%;" name="twoTutorAuditAdvice" ${param.viewFlag eq 'view'?'readonly':''}>${defence.twoTutorAuditAdvice}</textarea>
                        </fieldset>
                    </c:if>
                    <c:if test="${empty defence.tutorAuditId or empty defence.twoTutorAuditId}">
                        <fieldset>
                            <legend>导师意见</legend>
                            <textarea style="width:100%;" name="${!empty defence.tutorAuditId?'tutorAuditAdvice':'twoTutorAuditAdvice'}" ${param.viewFlag eq 'view'?'readonly':''}>${defence.tutorAuditAdvice}${defence.twoTutorAuditAdvice}</textarea>
                        </fieldset>
                    </c:if>
                </c:if>
                <%--导师审核流程关闭--%>
                <c:if test="${applicationScope.sysCfgMap['xjgl_audit_tutor'] ne GlobalConstant.FLAG_Y}">
                    <fieldset>
                        <legend>导师意见</legend>
                        <textarea style="width:100%;" ${param.viewFlag eq 'view'?'readonly':''}>审核通过</textarea>
                    </fieldset>
                </c:if>
                <%--学生/培养单位/分委会/学校角色--%>
                <c:if test="${empty param.role or param.role eq 'pydw' or param.role eq 'fwh' or param.role eq 'xx'}">
                    <fieldset>
                        <legend>培养单位意见</legend>
                        <textarea style="width:100%;" name="pydwAuditAdvice" ${param.viewFlag eq 'view'?'readonly':''}>${defence.pydwAuditAdvice}</textarea>
                    </fieldset>
                    <input type="hidden" name="pydwAuditStatusId" id="pydwAuditStatusId">
                    <input type="hidden" name="pydwAuditStatusName" id="pydwAuditStatusName" value="审核通过">
                </c:if>
            </c:if>
            <div style="text-align: center;margin-top:20px;">
                <c:if test="${param.viewFlag ne 'view'}">
                    <input type="button" class="search" onclick="save('Save');" value="保&#12288;存"/>
                    <input type="button" class="search" onclick="save('Submit');" value="提&#12288;交"/>
                </c:if>
                <c:if test="${param.viewFlag eq 'view'}">
                    <input type="button" class="search" onclick="jboxClose()" value="关&#12288;闭"/>
                </c:if>
            </div>
        </form>
    </div>
</div>
</body>
</html>