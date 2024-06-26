<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="font" value="true"/>
        <jsp:param name="queryFont" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
</head>
<body>
<div class="main_bd" id="div_table_0">
    <div class="search_table">
        <table class="grid">
            <tr>
                <td colspan="9" style="text-align: right;">
                    <img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" alt="审核通过"/> 审核通过&#12288;
                    "-" 无需审核
                </td>
            </tr>
            <tr>
                <th width="7%">姓名</th>
                <th width="8%">请假类型</th>
                <th width="20%">请假事由</th>
                <th width="15%">请假时间</th>
                <th width="10%">轮转科室</th>
                <th width="10%">状态</th>
                <th width="10%">带教老师</th>
                <th width="10%">科主任</th>
                <th width="10%">管理员</th>
            </tr>
            <c:forEach items="${kqList}" var="kq">
                <tr>
                    <td>${kq.doctorName}</td>
                    <td>${kq.typeName }</td>
                    <td><label title="${kq.doctorRemarks}">${pdfn:cutString(kq.doctorRemarks,12,true,3) }</label></td>
                    <td>
                        ${kq.startDate}<br/>${kq.endDate}<br/>
                        <font style="font-weight: bold;">
                            (${kq.intervalDays}小时)
                        </font>
                    </td>
                    <td style="text-align: center; ">${kq.schDeptName}</td>
                    <td>${kq.auditStatusName}</td>
                    <td>
                        <c:if test="${kq.teacherName ne '-'}">
                            <c:if test="${not empty kq.teacherAgreeFlag}">
                                <c:if test="${GlobalConstant.FLAG_N eq kq.teacherAgreeFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" alt="审核不通过"/>
                                </c:if>
                                <c:if test="${GlobalConstant.FLAG_Y eq kq.teacherAgreeFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" alt="审核通过"/>
                                </c:if>
                                <a title="审核信息：${kq.teacherAuditInfo}">${kq.teacherName}</a>
                            </c:if>
                            <c:if test="${empty kq.teacherAgreeFlag}">

                                <c:if test="${kq.auditStatusId eq 'Revoke'}">
                                    ${kq.teacherName}
                                </c:if>
                                <c:if test="${kq.auditStatusId ne 'Revoke'}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.teacherName}
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${kq.teacherName eq '-'}">
                            ${kq.teacherName}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${kq.headName ne '-'}">
                            <c:if test="${not empty kq.headAgreeFlag}">
                                <c:if test="${GlobalConstant.FLAG_N eq kq.headAgreeFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
                                </c:if>
                                <c:if test="${GlobalConstant.FLAG_Y eq kq.headAgreeFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
                                </c:if>
                                <a title="审核信息：${kq.headAuditInfo}">${kq.headName}</a>
                            </c:if>
                            <c:if test="${empty kq.headAgreeFlag}">
                                <c:set var="audit" value="false"></c:set>
                                <c:if test="${kq.teacherName eq '-'}">
                                    <c:set var="audit" value="true"></c:set>
                                </c:if>
                                <c:if test="${kq.teacherName ne '-'}">
                                    <c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
                                        <c:set var="audit" value="true"></c:set>
                                    </c:if>
                                    <c:if test="${empty kq.teacherAgreeFlag}">
                                        <c:set var="audit" value="true"></c:set>
                                    </c:if>
                                </c:if>
                                <c:if test="${audit}">

                                    <c:if test="${kq.auditStatusId eq 'Revoke'}">
                                        ${kq.headName}
                                    </c:if>
                                    <c:if test="${kq.auditStatusId ne 'Revoke'}">
                                        <img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.headName}
                                    </c:if>
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${kq.headName eq '-'}">
                            ${kq.headName}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${kq.managerName ne '-'}">
                            <c:if test="${not empty kq.managerAgreeFlag}">
                                <c:if test="${GlobalConstant.FLAG_N eq kq.managerAgreeFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
                                </c:if>
                                <c:if test="${GlobalConstant.FLAG_Y eq kq.managerAgreeFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
                                </c:if>
                                <a title="审核信息：${kq.managerAuditInfo}">${kq.managerName}</a>
                            </c:if>
                            <c:if test="${empty kq.managerAgreeFlag}">
                                <c:set var="audit" value="false"></c:set>
                                <c:if test="${kq.teacherName eq '-' and kq.headName eq '-' and kq.tutorName eq '-'  }">
                                    <c:set var="audit" value="true"></c:set>
                                </c:if>
                                <c:if test="${kq.teacherName ne '-' and kq.headName eq '-' and kq.tutorName eq '-'}">
                                    <c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
                                        <c:set var="audit" value="true"></c:set>
                                    </c:if>
                                    <c:if test="${empty kq.teacherAgreeFlag}">
                                        请等待带教审核
                                    </c:if>
                                </c:if>
                                <c:if test="${kq.teacherName eq '-' and kq.headName ne '-'  and kq.tutorName eq '-'}">
                                    <c:if test="${not empty kq.headAgreeFlag and kq.headAgreeFlag eq 'Y'}">
                                        <c:set var="audit" value="true"></c:set>
                                    </c:if>
                                    <c:if test="${empty kq.headAgreeFlag}">
                                        请等待主任审核
                                    </c:if>
                                </c:if>
                                <c:if test="${kq.teacherName eq '-' and kq.headName eq '-'  and kq.tutorName ne '-'}">
                                    <c:if test="${not empty kq.tutorAgreeFlag and kq.tutorAgreeFlag eq 'Y'}">
                                        <c:set var="audit" value="true"></c:set>
                                    </c:if>
                                    <c:if test="${empty kq.tutorAgreeFlag}">
                                        请等待导师审核
                                    </c:if>
                                </c:if>
                                <c:if test="${kq.teacherName ne '-' and kq.headName ne '-' and kq.tutorName ne '-'  }">
                                    <c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
                                        <c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
                                            <c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
                                                <c:set var="audit" value="true"></c:set>
                                            </c:if>
                                            <c:if test="${empty kq.tutorAgreeFlag}">
                                                请等待导师审核
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty kq.headAgreeFlag}">
                                            请等待主任审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${empty kq.teacherAgreeFlag}">
                                        请等待带教审核
                                    </c:if>
                                </c:if>
                                <c:if test="${kq.teacherName ne '-' and kq.headName ne '-' and kq.tutorName eq '-'  }">
                                    <c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
                                        <c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
                                            <c:set var="audit" value="true"></c:set>
                                        </c:if>
                                        <c:if test="${empty kq.headAgreeFlag}">
                                            请等待主任审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${empty kq.teacherAgreeFlag}">
                                        请等待带教审核
                                    </c:if>
                                </c:if>
                                <c:if test="${kq.teacherName ne '-' and kq.headName eq '-' and kq.tutorName ne '-'  }">
                                    <c:if test="${not empty kq.teacherAgreeFlag and kq.teacherAgreeFlag eq 'Y'}">
                                        <c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
                                            <c:set var="audit" value="true"></c:set>
                                        </c:if>
                                        <c:if test="${empty kq.tutorAgreeFlag}">
                                            请等待导师审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${empty kq.teacherAgreeFlag}">
                                        请等待带教审核
                                    </c:if>
                                </c:if>
                                <c:if test="${kq.teacherName eq '-' and kq.headName ne '-' and kq.tutorName ne '-'  }">
                                    <c:if test="${not empty kq.headAgreeFlag  and kq.headAgreeFlag eq 'Y'}">
                                        <c:if test="${not empty kq.tutorAgreeFlag  and kq.tutorAgreeFlag eq 'Y'}">
                                            <c:set var="audit" value="true"></c:set>
                                        </c:if>
                                        <c:if test="${empty kq.tutorAgreeFlag}">
                                            请等待导师审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${empty kq.headAgreeFlag}">
                                        请等待主任审核
                                    </c:if>
                                </c:if>

                                <c:if test="${audit  and 'manager' eq roleFlag}">
                                    <a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
                                    <a href="javascript:save('${kq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
                                </c:if>
                                <c:if test="${audit and 'manager' ne roleFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${kq.managerName}
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${kq.managerName eq '-'}">
                            ${kq.managerName}
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty kqList}">
                <tr>
                    <td colspan="20">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="resultDiv">
        <c:set var="pageView" value="${pdfn:getPageView(resultMapList)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>

</body>
</html>