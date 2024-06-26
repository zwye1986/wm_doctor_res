<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">

</script>
    <div class="search_table" style="height: 440px;overflow: auto;padding: 0;margin: 0px 10px;margin-top: 10px;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <c:if test="${param.gradeRole eq 'teacher'}">
                    <c:if test="${empty param.sessionNumber}">
                        <c:set value="6" var="col"/>
                        <col width="30%"/>
                        <col width="12%"/>
                        <col width="12%"/>
                        <col width="12%"/>
                        <col width="12%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </c:if>

                    <c:if test="${not empty param.sessionNumber}">
                        <c:set value="4" var="col"/>
                        <col width="55%"/>
                        <col width="25%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </c:if>
                </c:if>
                <c:if test="${param.gradeRole eq 'head'}">
                    <c:if test="${empty param.sessionNumber}">
                        <c:set value="6" var="col"/>
                        <col width="30%"/>
                        <col width="12%"/>
                        <col width="12%"/>
                        <col width="12%"/>
                        <col width="12%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </c:if>
                    <c:if test="${not empty param.sessionNumber}">
                        <c:set value="4" var="col"/>
                        <col width="55%"/>
                        <col width="25%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                    </c:if>
                </c:if>
            </colgroup>

            <c:if test="${param.gradeRole eq 'teacher'}">
                <c:set var="key" value="${data.userFlow}"/>
                <c:set var="name" value="${data.userName}"/>
            </c:if>

            <c:if test="${param.gradeRole eq 'head'}">
                <c:set var="key" value="${data.deptFlow}"/>
                <c:set var="name" value="${data.deptName}"/>
            </c:if>
            <tr>
                <th style="text-align: left;padding-left: 10px;">
                    <c:if test="${param.gradeRole eq 'teacher'}">
                        ${name}老师
                    </c:if>
                    <c:if test="${param.gradeRole eq 'head'}">
                        ${name}科室名称
                    </c:if>
                </th>
                <c:if test="${empty param.sessionNumber}">
                    <th>${ZeroTwoYearsDate}届总均分</th>
                    <th>${FirstTwoYearsDate}届总均分</th>
                    <th>${PreviouYearDate}届总均分</th>
                    <th>${currDate}届总均分</th>
                </c:if>
                <c:if test="${not empty param.sessionNumber}">
                    <th>${param.sessionNumber}届总均分</th>
                </c:if>
                <th>标准分</th>
                <th>总均分</th>
            </tr>
                <c:forEach items="${assessCfgList}" var="title">
                    <tr>
                        <c:if test="${param.gradeRole eq 'head'}">
                            <c:if test="${empty param.sessionNumber}">
                                <td class="${key}Show" colspan="6"
                                    style="text-align: left;padding-left: 10px;font-weight: bold;">
                                        ${title.name}
                                </td>
                            </c:if>
                            <c:if test="${not empty param.sessionNumber}">
                                <td class="${key}Show" colspan="3"
                                    style="text-align: left;padding-left: 10px;font-weight: bold;">
                                        ${title.name}
                                </td>
                            </c:if>
                        </c:if>
                        <c:if test="${param.gradeRole eq 'teacher'}">
                            <c:if test="${empty param.sessionNumber}">
                                <td class="${key}Show" colspan="6"
                                    style="text-align: left;padding-left: 10px;font-weight: bold;">
                                        ${title.name}
                                </td>
                            </c:if>
                            <c:if test="${not empty param.sessionNumber}">
                                <td class="${key}Show" colspan="3"
                                    style="text-align: left;padding-left: 10px;font-weight: bold;">
                                        ${title.name}
                                </td>
                            </c:if>
                        </c:if>
                        <td class="${key}Show" style="font-weight: bold;padding-right: 20px;">
                        </td>
                    </tr>
                    <c:forEach items="${title.itemList}" var="item">
                        <c:set var="scoreKey" value="${key}_${item.id}"/>
                        <tr>
                            <c:if test="${param.gradeRole eq 'head'}">
                                <td class="${key}Show"
                                    style="text-align: left;padding-left: 10px;">${item.name}</td>
                            </c:if>
                            <c:if test="${param.gradeRole eq 'teacher'}">
                                <td class="${key}Show"
                                    style="text-align: left;padding-left: 10px;">${item.name}</td>
                            </c:if>
                            <c:if test="${empty param.sessionNumber}">
                                <td class="${key}Show"
                                    style="padding-right: 20px;">${recZeroTwoYearsAvgMap[scoreKey]}</td>
                                <td class="${key}Show"
                                    style="padding-right: 20px;">${recFirstTwoYearsAvgMap[scoreKey]}</td>
                                <td class="${key}Show"
                                    style="padding-right: 20px;">${recpreviouYearAvgMap[scoreKey]}</td>
                                <td class="${key}Show"
                                    style="padding-right: 20px;">${recCurrDateAvgMap[scoreKey]}</td>
                            </c:if>
                            <c:if test="${not empty param.sessionNumber}">
                                <td class="${key}Show"
                                    style="padding-right: 20px;">${recDateAvgMap[scoreKey]}</td>
                            </c:if>
                            <td class="${key}Show"
                                style="padding-right: 20px;color: #C5C5C5;">${item.score}</td>
                            <td class="${key}Show" style="">${avgMap[scoreKey]}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
        </table>
    </div>
    <div align="center" style="margin-top: 10px;">
        <input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
    </div>
</html>