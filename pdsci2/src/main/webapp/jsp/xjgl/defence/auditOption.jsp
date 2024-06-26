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
        function auditOpt(auditStatusId){
            if(${param.role eq 'ds'}){
                if(${param.tutorFlag eq 'one'}){
                    $("#tutorAuditId").val(auditStatusId);
                    if(auditStatusId=='UnPassed'){
                        $("#tutorAuditName").val("审核不通过");
                    }else if(${empty defence.twoTutorFlow or defence.twoTutorAuditId eq 'Passed'}){
                        //导师(或两个都)审核通过 初始化培养单位审核状态
                        $("#myForm").append("<input type='hidden' name='pydwAuditId' value='Passing'/><input type='hidden' name='pydwAuditName' value='待审核'/>");
                    }
                }else if(${param.tutorFlag eq 'two'}){
                    $("#twoTutorAuditId").val(auditStatusId);
                    if(auditStatusId=='UnPassed'){
                        $("#twoTutorAuditName").val("审核不通过");
                    }else if(${empty defence.tutorFlow or defence.tutorAuditId eq 'Passed'}){
                        //导师(或两个都)审核通过 初始化培养单位审核状态
                        $("#myForm").append("<input type='hidden' name='pydwAuditId' value='Passing'/><input type='hidden' name='pydwAuditName' value='待审核'/>");
                    }
                }
            }else if(${param.role eq 'pydw'}){
                $("#pydwAuditId").val(auditStatusId);
                if(auditStatusId=='UnPassed'){
                    $("#pydwAuditName").val("审核不通过");
                }
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
                    <tr>
                        <input type="hidden" name="recordFlow" value="${defence.recordFlow}"/>
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
                </table>
            </fieldset>
            <fieldset>
                <legend><c:if test="${isReplenish ne 'Y'}">答辩申请信息</c:if><c:if test="${isReplenish eq 'Y'}">学位补授申请信息</c:if></legend>
                <table class="basic" style="width: 100%;">
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;min-width: 92px;"><c:if test="${isReplenish ne 'Y'}">答辩时间：</c:if><c:if test="${isReplenish eq 'Y'}">学位补授时间：</c:if></th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">${defence.defenceTime}</td>
                        <th style="text-align:right;padding:0px;border:0px;min-width: 92px;">评审论文命名：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">12121_${baseInfo.majorId}_${baseInfo.sid}_LW</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;min-width: 92px;">中文论文题目：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">${defence.paperChiTitle}</td>
                        <th style="text-align:right;padding:0px;border:0px;min-width: 92px;">英文论文题目：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">${defence.paperEngTitle}</td>
                    </tr>
                    <tr>
                        <th style="text-align:right;padding:0px;border:0px;">关键词：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">${defence.keyWord}</td>
                        <th style="text-align:right;padding:0px;border:0px;">研究方向：</th>
                        <td style="text-align:left;padding:0px;border:0px;line-height: 130%;">${defence.researchDirection}</td>
                    </tr>
                </table>
            </fieldset>
            <%--导师审核流程开启--%>
            <c:if test="${applicationScope.sysCfgMap['xjgl_audit_tutor'] eq GlobalConstant.FLAG_Y}">
                <c:if test="${param.tutorFlag eq 'one'}">
                    <fieldset>
                        <legend>导师意见</legend>
                        <textarea style="width:100%;" name="tutorAuditAdvice">${defence.tutorAuditAdvice}</textarea>
                    </fieldset>
                    <input type="hidden" name="tutorAuditId" id="tutorAuditId">
                    <input type="hidden" name="tutorAuditName" id="tutorAuditName" value="审核通过">
                </c:if>
                <c:if test="${param.tutorFlag eq 'two'}">
                    <fieldset>
                        <legend>导师意见</legend>
                        <textarea style="width:100%;" name="twoTutorAuditAdvice">${defence.twoTutorAuditAdvice}</textarea>
                    </fieldset>
                    <input type="hidden" name="twoTutorAuditId" id="twoTutorAuditId">
                    <input type="hidden" name="twoTutorAuditName" id="twoTutorAuditName" value="审核通过">
                </c:if>
                <c:if test="${empty param.tutorFlag}">
                    <fieldset>
                        <legend>导师一意见</legend>
                        <textarea style="width:100%;" readonly="readonly">${defence.tutorAuditAdvice}</textarea>
                    </fieldset>
                    <fieldset>
                        <legend>导师二意见</legend>
                        <textarea style="width:100%;" readonly="readonly">${defence.twoTutorAuditAdvice}</textarea>
                    </fieldset>
                </c:if>
            </c:if>
            <%--导师审核流程关闭--%>
            <c:if test="${applicationScope.sysCfgMap['xjgl_audit_tutor'] ne GlobalConstant.FLAG_Y}">
                <fieldset>
                    <legend>导师意见</legend>
                    <textarea style="width:100%;" readonly="readonly"}>审核通过</textarea>
                </fieldset>
            </c:if>
            <c:if test="${param.role eq 'pydw'}">
                <fieldset>
                    <legend>培养单位意见</legend>
                    <textarea style="width:100%;" name="pydwAuditAdvice">${defence.pydwAuditAdvice}</textarea>
                </fieldset>
                <input type="hidden" name="pydwAuditId" id="pydwAuditId">
                <input type="hidden" name="pydwAuditName" id="pydwAuditName" value="审核通过">
            </c:if>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="auditOpt('Passed');" value="通&#12288;过"/>
                <input type="button" class="search" onclick="auditOpt('UnPassed');" value="不通过"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>