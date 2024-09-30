<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function opRec(recFlow,schDeptFlow, rotationFlow, userFlow, schResultFlow,typeId,typeName,processFlow){
            var url = "<s:url value='/res/rec/showRegistryFormNew'/>"+
                    "?schDeptFlow="+schDeptFlow+
                    "&rotationFlow="+rotationFlow+
                    "&recTypeId="+typeId+"&userFlow="+userFlow+
                    "&roleFlag=manage&openType=open"+
                    "&resultFlow="+schResultFlow+
                    "&recFlow="+recFlow+
                    "&processFlow="+processFlow+
                    "&operUserFlow="+userFlow;

            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='330px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,typeName,600,400);
        }

        function quantizationTableDOPS(doctorFlow, processFlow, deptFlow, recFlow) {
            var roleFlag = '${GlobalConstant.RES_ROLE_SCOPE_ADMIN}';
            var url = "<s:url value='/jsres/teacher/quantizationTableDOPS'/>?roleFlag=" + roleFlag + "&operUserFlow=" + doctorFlow + "&processFlow=" + processFlow + "&schDeptFlow=" + deptFlow + "&recFlow=" + recFlow;

            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='330px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxOpen(url,"临床操作技能评估量化表",900,450);
        }
        function quantizationTableMiNi(doctorFlow, processFlow, deptFlow, recFlow) {
            var roleFlag = '${GlobalConstant.RES_ROLE_SCOPE_ADMIN}';
            var url = "<s:url value='/jsres/teacher/quantizationTableMiNi'/>?roleFlag=" + roleFlag + "&operUserFlow=" + doctorFlow + "&processFlow=" + processFlow + "&schDeptFlow=" + deptFlow + "&recFlow=" + recFlow;
            jboxOpen(url,"迷你临床演练评估量化表",900,450);
        }
        function evaluation(doctorFlow, processFlow, deptFlow, recFlow, recordFlow) {
            var roleFlag = '${GlobalConstant.RES_ROLE_SCOPE_ADMIN}';
            var url = "<s:url value='/jsres/teacher/evaluationSun'/>?roleFlag=" + roleFlag + "&operUserFlow=" + doctorFlow + "&processFlow=" + processFlow + "&schDeptFlow=" + deptFlow + "&recFlow=" + recFlow + "&recordFlow=" + recordFlow;
            jboxOpen(url,"出科考核表",900,450);
        }
        function upload(recordFlow,userFlow,hideApprove){
            if(hideApprove==null){
                hideApprove=null;
            }
            if(userFlow==null){
                userFlow="";
            }
            var url = "<s:url value='/res/rec/upload'/>?recordFlow="+recordFlow+"&hideApprove="+hideApprove+"&userFlow="+userFlow;
            jboxOpen(url, "查看出科考核表",900,450);
        }

        function exportInfoBydoc() {
            var papersFlag = $("#exportPapers").attr("checked") == "checked";
            var url ="";
            if(papersFlag){
                url = "<s:url value='/res/exam/paper/downloadCkkPaperBatch?roleId=${param.roleId}&doctorFlow='/>" + "${doctor.doctorFlow}";
            }else{
                url = "<s:url value='/jsres/doctorRecruit/exportCycleResultsByDoc?roleId=${param.roleId}&doctorFlow='/>" + "${doctor.doctorFlow}";
            }
            jboxStartLoading();
            jboxExp(null,url);
            jboxEndLoading();
            // 是否导出试卷 Y 是 N 否
            <%--var papersFlag = $("#exportPapers").attr("checked") == "checked" ? 'Y' : 'N';--%>
            <%--var url = "<s:url value='/jsres/doctorRecruit/exportDoctorRecruitResult?roleId=${param.roleId}&doctorFlow=${doctor.doctorFlow}&papersFlag='/>" + papersFlag;--%>
            <%--jboxStartLoading();--%>
            <%--jboxExp(null,url);--%>
            <%--jboxEndLoading();--%>
        }

        function downloadCkkPaper(processFlow) {
            var url = "<s:url value='/res/exam/paper/downloadCkkPaper?processFlow='/>" + processFlow;
            jboxStartLoading();
            jboxGet(url,null,function(resp){
                var data=eval("("+resp+")");
                if(data.result=="1")
                {
                    $("#url").val(data.url);
                    jboxExp($("#exportFrom"),"<s:url value='/res/exam/paper/downloadCkkFile?processFlow='/>" + processFlow);
                }else{
                    jboxTip(data.msg);
                }
            },null,false);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<form id="exportFrom">
    <input type="hidden" id="url" name="url"/>
</form>
<div class="div_table" style="height: 500px;overflow:auto;">
    <table border="0" cellpadding="0" cellspacing="0" class="base_info">
        <tr>
            <td>
                学员：${doctor.doctorName}
                &#12288;
                培训基地：${doctor.orgName}
                &#12288;
                轮转方案：${doctor.rotationName}
                &#12288;
                <input type="button" class="btn_green" onclick="exportInfoBydoc();"
                       value="导&#12288;出"/>
                <span style="margin-left: 15px">

                <input type="checkbox" id="exportPapers" name="exportPapers"/> 是否导出试卷
                </span>
            </td>
        </tr>
    </table>
    <table border="0" cellpadding="0" cellspacing="0" class="base_info">
        <tr>
            <th style="text-align: center;padding: 0px">科室名称</th>
            <th style="text-align: center;padding: 0px">轮转时间</th>
            <th style="text-align: center;padding: 0px">带教老师</th>
            <th style="text-align: center;padding: 0px">理论成绩</th>
            <th style="text-align: center;padding: 0px">技能名称</th>
            <th style="text-align: center;padding: 0px">技能成绩</th>
            <th style="text-align: center;padding: 0px">DOPS</th>
            <th style="text-align: center;padding: 0px">Mini-cex</th>
            <th style="text-align: center;padding: 0px">出科考核表</th>
            <th style="text-align: center;padding: 0px">出科考核附件</th>
        </tr>
        <c:forEach items="${arrResultList}" var="result">
            <c:set var="key" value="${resultMap[result.resultFlow].processFlow}"></c:set>
            <c:set var="key2" value="${result.standardGroupFlow}${result.standardDeptId}"></c:set>
            <tr>
                <td style="text-align: center;padding: 0px">${result.schDeptName}</td>
                <td style="text-align: center;padding: 0px">${result.schStartDate} ~ ${result.schEndDate}</td>
                <td style="text-align: center;padding: 0px">${resultMap[result.resultFlow].teacherUserName}</td>
                <%--<td style="text-align: center;padding: 0px">${resultMap[result.resultFlow].schScore}</td>--%>
                <td style="text-align: center;padding: 0px">
                <c:if test="${ not empty skillMap[key].score}">
                    <a style="cursor: pointer" onclick="downloadCkkPaper('${key}');" class="theoreResult_id">${empty skillMap[key].theoreResult?resultMap[result.resultFlow].schScore:skillMap[key].theoreResult}</a>
                </c:if>
                </td>
                <td style="text-align: center;padding: 0px">${skillMap[key].skillName}</td>
                <td style="text-align: center;padding: 0px">${skillMap[key].score} </td>
                <td style="text-align: center;padding: 0px">
                    <c:if test="${not empty DOPSFlowMap[key]}">
                        <a style="cursor: pointer"	onclick="quantizationTableDOPS('${result.doctorFlow}','${key}','${resultMap[result.resultFlow].deptFlow}','${DOPSFlowMap[key]}');">[查看]</a>
                    </c:if>
                </td>
                <td style="text-align: center;padding: 0px">
                    <c:if test="${not empty MiniFlowMap[key]}">
                        <a style="cursor: pointer"	onclick="quantizationTableMiNi('${result.doctorFlow}','${key}','${resultMap[result.resultFlow].deptFlow}','${MiniFlowMap[key]}');">[查看]</a>
                    </c:if>
                </td>
                <td style="text-align: center;padding: 0px">
                    <c:if test="${not empty AfterFlowMap[key]}">
                        <a style="cursor: pointer"	onclick="evaluation('${result.doctorFlow}','${key}','${resultMap[result.resultFlow].deptFlow}','${AfterFlowMap[key]}');">[查看]</a>
                    </c:if>
                </td>
                <td style="text-align: center;padding: 0px">
                    <c:if test="${result.haveAfterPic eq 'Y'}">
                        <a   style="cursor:pointer;" onclick="upload('${deptMap[key2].recordFlow}','${result.doctorFlow}','hideApprove');">[查看]</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${!empty arrResultList}">
            <tr>
                <td style="text-align: center;padding: 0px">总计</td>
                <td style="text-align: center;padding: 0px;color: orangered">--</td>
                <td style="text-align: center;padding: 0px;color: orangered">--</td>
                <td style="text-align: center;padding: 0px">${thrAvaScore}</td>
                <td style="text-align: center;padding: 0px;color: orangered">--</td>
                <td style="text-align: center;padding: 0px">${killAvaScore}</td>
                <td style="text-align: center;padding: 0px;color: orangered">--</td>
                <td style="text-align: center;padding: 0px;color: orangered">--</td>
                <td style="text-align: center;padding: 0px;color: orangered">--</td>
                <td style="text-align: center;padding: 0px;color: orangered">--</td>
            </tr>
        </c:if>
        <c:if test="${empty arrResultList}">
            <tr><td colspan="20" style="text-align: center">暂无轮转计划!</td></tr>
        </c:if>
    </table>
</div>

</body>
</html>
