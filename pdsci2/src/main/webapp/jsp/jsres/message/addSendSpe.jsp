    <tr>
        <th style="text-align: center;width: 180px;">招收专业</th>
        <th style="text-align: center;width: 120px;">专业简介</th>
        <th style="text-align: center;width: 80px;">报送人数</th>
        <th style="text-align: center;width: 80px;">报送通过人数</th>
        <th style="text-align: center;width: 100px;">毕业专业要求</th>
        <th style="text-align: center;width: 100px;">学历学位要求</th>
    </tr>
    <c:forEach items="${orgSpeList}" var="orgSpe">
        <tr>
            <td style="text-align: center;width: 180px;">${orgSpe.SPE_NAME}<c:if test="${orgSpe.STATUS eq 'stop'}"><font color="red">（停招）</font></c:if></td>
            <td style="text-align: center;width: 120px;">
                <input type="hidden" name="recordFlow" value="${orgSpe.RECORD_FLOW}"/>
                <c:if test="${flag eq 'edit'}">
                    <a style="color: #459ae9;cursor: pointer" speDescData="" <c:if test="${flag eq 'edit'}"> onclick="showSpeDescView('${orgSpe.RECORD_FLOW}')"</c:if>>[编辑]</a>
                </c:if>
                <c:if test="${flag ne 'edit'}">无</c:if>
            </td>
            <td style="text-align: center;width: 80px;">
                <input type="text" class="input validate[max[999],custom[numbernotzero]]" name="sendPlan"
                       value="${empty orgSpe.SEND_PLAN ? 0 : orgSpe.SEND_PLAN}" style="width: 40px;"
                        <c:if test="${flag eq 'audit' or orgSpe.STATUS eq 'stop'}">disabled</c:if>/>
            </td>
            <td style="text-align: center;width: 80px;">
                ${empty orgSpe.SEND_REAL ? 0 : orgSpe.SEND_REAL}
            </td>
            <td style="text-align: center;width: 100px;">
                <input type="text" class="input validate[maxSize[50]]" value="${orgSpe.GRADUATE_SPE}" name="graduateSpe"
                       <c:if test="${flag eq 'audit'}">disabled</c:if>/>
            </td>
            <td style="text-align: center;width: 100px;">
                <input type="text" class="input validate[maxSize[20]]" value="${orgSpe.EDUCATION}" name="education"
                       <c:if test="${flag eq 'audit'}">disabled</c:if>/>
            </td>
        </tr>
    </c:forEach>
    <c:if test="${empty orgSpeList}">
        <tr>
            <c:if test="${empty assignYear}">
                <td colspan="9">请先选择招收年份！</td>
            </c:if>
            <c:if test="${not empty assignYear}">
                <td colspan="9">还未配置该年份的专业基地，请联系上级部门！</td>
            </c:if>
        </tr>
    </c:if>