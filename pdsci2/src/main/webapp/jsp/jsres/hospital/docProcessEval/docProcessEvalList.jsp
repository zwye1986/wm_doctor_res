<div class="search_table" >
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
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
            <th width="10%">姓名</th>
            <th width="10%">培训类别</th>
            <th width="10%">培训专业</th>
            <th width="10%">培训基地</th>
            <th width="10%">年级</th>
            <th width="10%">培训年限</th>
            <th width="10%">轮转科室</th>
            <th width="10%">轮转时间</th>
            <th width="10%">带教老师</th>
            <th width="10%">考评成绩</th>
        </tr>
        <c:forEach items="${list}" var="b">
            <c:set var="evals" value="${evalMap[b.processFlow]}"></c:set>
            <tr>
                <td>${b.userName}</td>
                <td>${b.catSpeName}</td>
                <td>${b.speName}</td>
                <td>${b.orgName}</td>
                <td>${b.sessionNumber}</td>
                <td>
                    <c:forEach items="${jsResTrainYearEnumList }" var="trainYear">
                        <c:if test="${b.trainYear eq trainYear.id}">${trainYear.name }</c:if>
                    </c:forEach>
                </td>
                <td>${b.deptName}</td>
                <td>${b.schStartDate}~${b.schEndDate}</td>
                <td>${b.teacherUserName}</td>
                <td>
                    <c:choose>
                        <c:when test="${evalSizeMap[b.processFlow] eq '1'}">
                            <c:set var="eval" value="${evals.get(0)}"></c:set>
                            <c:if test="${empty eval.recordFlow}">待考评</c:if>
                            <c:if test="${not empty eval.recordFlow}">
                                <c:if test="${eval.isForm eq 'Y'}">
                                    <a href="javascript:void(0);"
                                       onclick="showEvalDetail('${eval.recordFlow}')">${eval.evalScore}</a>
                                </c:if>
                                <c:if test="${eval.isForm != 'Y'}">
                                    <a href="javascript:void(0);" onclick="showEvalDetail('${eval.recordFlow}')">查看</a>
                                </c:if>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:void(0);" onclick="showEvalList('${b.processFlow}',this)">展开</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <c:if test="${evalSizeMap[b.processFlow] > '1'}">
                <tr id="${b.processFlow}" style="display: none;">
                    <td colspan="10">
                        <table style="width: 100%">
                            <tr>
                                <th>名称</th>
                                <th>时间</th>
                                <th>状态</th>
                                <th>成绩</th>
                            </tr>
                            <c:forEach items="${evals}" var="eval">
                                <tr>
                                    <td>月度考评表</td>
                                    <td>${eval.startTime}~${eval.endTime}</td>
                                    <td>
                                        <c:if test="${empty eval.recordFlow}">
                                            待考评
                                        </c:if>
                                        <c:if test="${not empty eval.recordFlow}">
                                            已考评
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${not empty eval.recordFlow}">
                                            <c:if test="${eval.isForm eq 'Y'}">
                                                <a href="javascript:void(0);"
                                                   onclick="showEvalDetail('${eval.recordFlow}')">${eval.evalScore}</a>
                                            </c:if>
                                            <c:if test="${eval.isForm != 'Y'}">
                                                <a href="javascript:void(0);"
                                                   onclick="showEvalDetail('${eval.recordFlow}')">查看</a>
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        <c:if test="${empty list}">
            <tr>
                <td colspan="11">无记录！</td>
            </tr>
        </c:if>
    </table>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>