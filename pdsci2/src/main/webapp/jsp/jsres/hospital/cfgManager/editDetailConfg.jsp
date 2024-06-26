<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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

        function save() {
            var url = "<s:url value='/jsres/cfgManager/saveDeptConfig'/>";
            var data = $('#addDeptCfg').serialize();
            jboxPost(url, data, function(resp) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
                    window.parent.refreshCkkConfig();
                    setTimeout(function(){
                        jboxClose();
                    }, 1000);
                }
            }, null, true);
        }

        function spanShow(flag) {
            if(flag == "Y"){
                $("#testOutTimeSpan").show();
            }else{
                $("#testOutTimeSpan").hide();
            }
        }
    </script>
</head>
<body>
<div class="infoAudit">
    <form id="addDeptCfg" method="post" style="position:relative;" >
        <input type="hidden" name="itemId" value="${param.itemId}"/>
        <input type="hidden" name="itemName" value="${param.itemName}"/>
        <input type="hidden" name="orgFlow" value="${orgFlow}"/>
        <input type="hidden" name="cfgFlow" value="${deptConfig.cfgFlow}"/>
        <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 10px;">
            <tr>
                <th style="width: 50%;">轮转科室：</th>
                <td>
                    <input type="text" class="input" id="deptName" name="deptName" value="${deptConfig.deptName}" readonly="readonly">
                </td>
            </tr>
            <tr>
                <th>出科考核次数：</th>
                <td>
                    <input type="text" class="input validate[custom[number],min[0]]" id="testNum" name="testNum" value="${deptConfig.testNum}"/>
                </td>
            </tr>
            <tr>
                <th>默认合格线：</th>
                <td>
                    <input type="text" class="input validate[custom[number],maxSize[100],min[0]]" id="scorePass" name="scorePass" value="${deptConfig.scorePass}"/>
                </td>
            </tr>
            <tr>
                <%--<th>出科考核时间：</th>--%>
                <%--<td>--%>
                    <%--超出轮转结束时间--%>
                    <%--<input type="text" style="width: 30px" class="input validate[custom[number],min[0]]" id="testTime" name="testTime" />--%>
                    <%--日内可进行出科考试--%>
                <%--</td>--%>
                <th>是否允许学员在轮转时间结束后考试：</th>
                <td>
                    <input type="radio" id="isTestOutY" name="isTestOut" value="Y" onclick="spanShow('Y')"
                            <c:if test="${deptConfig.isTestOut eq 'Y'}">checked</c:if>/>
                        <label for="isTestOutY">是</label>&#12288;
                    <input type="radio" id="isTestOutN" name="isTestOut" value="N" onclick="spanShow('N')"
                           <c:if test="${deptConfig.isTestOut ne 'Y'}">checked</c:if>/>
                        <label for="isTestOutN">否</label>
                    <span <c:if test="${deptConfig.isTestOut ne 'Y'}">hidden</c:if> id="testOutTimeSpan">
                        <br/>
                        超出轮转结束时间
                        <input type="text" style="width: 30px" class="input validate[custom[number],min[0]]" id="testOutTime" name="testOutTime" value="${deptConfig.testOutTime}"/>
                        日内可进行出科考试
                    </span>
                </td>
            </tr>
            <tr>
                <th>是否允许带教老师填写分数：</th>
                <td>
                    <input type="radio" id="teacherWriteY" name="teacherWrite" value="Y"
                            <c:if test="${deptConfig.teacherWrite eq 'Y'}">checked</c:if>/>
                        <label for="teacherWriteY">是</label>&#12288;
                    <input type="radio" id="teacherWriteN" name="teacherWrite" value="N"
                           <c:if test="${deptConfig.teacherWrite ne 'Y'}">checked</c:if>/>
                        <label for="teacherWriteN">否</label>
                </td>
            </tr>
            <tr>
                <th>出科退回重考分数上限：</th>
                <td>
                    <input type="text" class="input validate[custom[number],max[100],min[0]]" name="scoreToplimit" value="${deptConfig.scoreToplimit}"/>
                </td>
            </tr>
        </table>
        <div class="button">
            <input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>
            <input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
        </div>
    </form>
</div>
</body>
</html>