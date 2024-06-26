<div class="search_table">
    <table style="width: 100%">
        <tr>
            <td style="width: 50%;text-align: left;border:0px solid #e7e7eb;"><B>姓名：</B>${sysUser.userName}&#12288;<B>培训专业：</B>${recruit.speName }
            </td>
        </tr>
    </table>
</div>
<div class="search_table" style="overflow:auto;margin-left: 30px;margin-right: 30px;padding: 0px;" id="indexBody">
    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%;padding:0px;margin: 0px;">
        <tr>
            <th style="width: 100px;min-width: 100px;max-width: 100px;">轮转类型</th>
            <th style="width: 100px;min-width: 100px;max-width: 100px;">标准科室</th>
            <th style="width: 100px;min-width: 100px;max-width: 100px;">要求时间/实<br>际时间(月)</th>
            <th style="width: 100px;min-width: 100px;max-width: 100px;">轮转科室</th>
            <th style="width: 200px;min-width: 200px;max-width: 200px;">轮转时间</th>
            <th style="width: 400px;min-width: 400px;max-width: 400px;">出科考核表</th>
            <th style="width: 60px;min-width: 60px;max-width: 60px;">总比例</th>
            <th style="width: 60px;min-width: 60px;max-width: 60px;">补填比例</th>
            <th style="width: 60px;min-width: 60px;max-width: 60px;">审核比例</th>
        </tr>

        <c:set value="" var="lastGroupFlow"></c:set>
        <c:set value="" var="lastDeptFlow"></c:set>
        <c:set value="0" var="allSchMonth"></c:set>
        <c:set value="0" var="allRealMonth"></c:set>
        <c:forEach items="${groupList}" var="group" varStatus="groupStatus">
            <c:forEach items="${rotationDeptMap[group.groupFlow]}" var="dept" varStatus="deptStatus">
                <c:set value="${allSchMonth+0+dept.schMonth}" var="allSchMonth"></c:set>
                <c:set var="resultKey" value="${group.groupFlow}${dept.standardDeptId}"/>
                <c:set value="${allRealMonth+0+(empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey])}"
                       var="allRealMonth"></c:set>
                <c:set var="bi" value="${biMap[dept.recordFlow]}"></c:set>
                <c:if test="${not empty resultMap[resultKey]}">
                    <c:forEach items="${resultMap[resultKey]}" var="result" varStatus="resultStatus">
                        <c:choose>
                            <c:when test="${!(lastGroupFlow eq group.groupFlow) and
								!(lastDeptFlow eq dept.recordFlow) and resultStatus.first
								}">
                                <tr>
                                    <td rowspan="${(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))<=0?0:(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))}">
                                        <c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
                                            ${group.schStageName}：
                                        </c:if>
                                            ${group.groupName}【${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}】
                                        <br>(轮转时间：${group.schMonth}月
                                        <c:if test="${group.isRequired eq GlobalConstant.FLAG_N}">
                                            &#12288;${group.selTypeName}：${group.deptNum}<c:if
                                                test="${group.selTypeId eq schSelTypeEnumFree.id}">~${group.maxDeptNum}</c:if>
                                        </c:if>)
                                    </td>
                                    <td rowspan="${deptRowSpan[resultKey]}">
                                            ${dept.standardDeptName}
                                    </td>
                                    <td rowspan="${deptRowSpan[resultKey]}">${dept.schMonth}/${empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey]}</td>
                                    <td>${result.schDeptName}</td>
                                    <td>${result.schStartDate}~${result.schEndDate}</td>
                                    <c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>
                                    <td rowspan="${deptRowSpan[resultKey]}">
                                        <div style="margin-top:10px; ">
                                            <c:forEach var="image" items="${imagelist}" varStatus="status">
                                                <a href="${image.imageUrl}" target="_blank"><img width="80px"
                                                                                                 height="80px"
                                                                                                 src="${image.thumbUrl}"></a>&nbsp;
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td rowspan="${deptRowSpan[resultKey]}">
                                        <a onclick="catalogue('${dept.recordFlow}','${recruit.doctorFlow}');">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</a>
                                    </td>
                                    <td rowspan="${deptRowSpan[resultKey]}">${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
                                    <td rowspan="${deptRowSpan[resultKey]}">${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>
                                </tr>
                            </c:when>
                            <c:when test="${(lastGroupFlow eq group.groupFlow) and
								!(lastDeptFlow eq dept.recordFlow) and resultStatus.first
								}">
                                <tr>
                                    <td rowspan="${deptRowSpan[resultKey]}">
                                            ${dept.standardDeptName}
                                    </td>
                                    <td rowspan="${deptRowSpan[resultKey]}">${dept.schMonth}/${empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey]}</td>
                                    <td>${result.schDeptName}</td>
                                    <td>${result.schStartDate}~${result.schEndDate}</td>
                                    <c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>
                                    <td rowspan="${deptRowSpan[resultKey]}">
                                        <div style="margin-top:10px; ">
                                            <c:forEach var="image" items="${imagelist}" varStatus="status">
                                                <a href="${image.imageUrl}" target="_blank"><img width="80px"
                                                                                                 height="80px"
                                                                                                 src="${image.thumbUrl}"></a>&nbsp;
                                            </c:forEach>
                                        </div>
                                    </td>
                                    <td rowspan="${deptRowSpan[resultKey]}">
                                        <a onclick="catalogue('${dept.recordFlow}','${recruit.doctorFlow}');">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</a>
                                    </td>
                                    <td rowspan="${deptRowSpan[resultKey]}">${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
                                    <td rowspan="${deptRowSpan[resultKey]}">${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>
                                </tr>
                            </c:when>
                            <c:when test="${(lastGroupFlow eq group.groupFlow) and
								(lastDeptFlow eq dept.recordFlow) and !(resultStatus.first)}">
                                <tr>
                                    <td>${result.schDeptName}</td>
                                    <td>${result.schStartDate}~${result.schEndDate}</td>
                                </tr>
                            </c:when>
                        </c:choose>
                        <c:set value="${group.groupFlow}" var="lastGroupFlow"></c:set>
                        <c:set value="${dept.recordFlow}" var="lastDeptFlow"></c:set>
                    </c:forEach>
                </c:if>
                <c:if test="${empty resultMap[resultKey]}">
                    <tr>
                        <c:if test="${deptStatus.first}">
                            <td rowspan="${(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))<=0?0:(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))}">
                                <c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
                                    ${group.schStageName}：
                                </c:if>
                                    ${group.groupName}【${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}】
                                <br>(轮转时间：${group.schMonth}月
                                <c:if test="${group.isRequired eq GlobalConstant.FLAG_N}">
                                    &#12288;${group.selTypeName}：${group.deptNum}<c:if
                                        test="${group.selTypeId eq schSelTypeEnumFree.id}">~${group.maxDeptNum}</c:if>
                                </c:if>)
                            </td>
                        </c:if>
                        <td>
                                ${dept.standardDeptName}
                        </td>
                        <td>${dept.schMonth}/0</td>
                        <td></td>
                        <td></td>
                        <c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>
                        <td rowspan="${deptRowSpan[resultKey]}">
                            <div style="margin-top:10px; ">
                                <c:forEach var="image" items="${imagelist}" varStatus="status">
                                    <a href="${image.imageUrl}" target="_blank"><img width="80px" height="80px"
                                                                                     src="${image.thumbUrl}"></a>&nbsp;
                                </c:forEach>
                            </div>
                        </td>
                        <td>
                            <a onclick="catalogue('${dept.recordFlow}','${recruit.doctorFlow}');">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</a>
                        </td>
                        <td>${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>
                        <td>${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>
                    </tr>
                    <c:set value="${group.groupFlow}" var="lastGroupFlow"></c:set>
                    <c:set value="${dept.recordFlow}" var="lastDeptFlow"></c:set>
                </c:if>
            </c:forEach>
        </c:forEach>
        <tr>
            <td colspan="2">合计时间</td>
            <td colspan="">${allSchMonth}/<fmt:formatNumber type="number" value="${allRealMonth}"
                                                            maxFractionDigits="2"/></td>
            <td colspan="3">平均比例</td>
            <td>${empty avgBiMap.avgComplete?0:avgBiMap.avgComplete}%</td>
            <td>${empty avgBiMap.avgOutComplete ? 0:avgBiMap.avgOutComplete}%</td>
            <td>${empty avgBiMap.avgAudit?0:avgBiMap.avgAudit}%</td>
        </tr>
    </table>

</div>