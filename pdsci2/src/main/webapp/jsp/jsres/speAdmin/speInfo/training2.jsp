<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script type="text/javascript">
    function showTraining(standardDeptFlow, standardDeptName, isJoin, speFlow) {
        var url = "<s:url value ='/jsres/base/showTrainingInfo'/>?onlyRead=${ishos}&standardDeptFlow=" + standardDeptFlow + "&orgFlow=${orgFlow}&isJoin=Y&speFlow=" + speFlow+"&isglobal=Y&sessionNumber=${sessionNumber}";
        jboxOpen(url, '科室信息（' + standardDeptName + '）', 1200, 800);
    }
</script>
<style type="text/css">
    .div_table h4 {
        color: #000000;
        font: 15px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }
    .base_info td {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>
<div class="div_table">
    <h4>培训情况</h4>
    <div
            <c:if test="${isJoin eq 'Y'}"> style="position: relative;overflow-y: auto;margin-top: 10px;" </c:if>
            <c:if test="${isglobal eq 'Y'}"> style="position: relative;overflow-y: auto;margin-top: 10px;" </c:if>
            <c:if test="${isJoin ne 'Y' and  isglobal ne 'Y'}"> style="margin-top: 10px;" </c:if>>
        <div>
            <div style="float: left;width: 40%;">
                <table border="0" cellspacing="0" cellpadding="0" style="width: 75%" class="base_info">
                    <colgroup>
                        <col width="70%"/>
                        <col width="30%"/>
                    </colgroup>
                    <tbody>
                    <tr>
                        <th style="text-align: center">轮转科室</th>
                        <th style="text-align: center">操作</th>
                    </tr>
                    <c:forEach items="${resultList}" var="r">
                        <tr>
                            <td style="text-align: center">${r.standardDeptName}</td>
                                <%--							<td style="text-align: center">${r.schDeptName}</td>--%>
                            <td style="text-align: center">
                                    <a href="javascript:void(0)" onclick="showTraining('${r.standardDeptFlow}','${r.standardDeptName}','${isJoin}','${speFlow}');" style="color:#59d5ff;">查看</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>
