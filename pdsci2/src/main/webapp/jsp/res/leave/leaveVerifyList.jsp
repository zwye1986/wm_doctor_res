<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>

<html>
<head>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        function toPage(page) {
            $("#currentPage").val(page);
            search();
        }
        function search() {
            var url = "<s:url value='/jsres/attendanceNew/leaveVerifyList/${roleFlag}'/>";
            var data = $("#searchForm").serialize();
            jboxStartLoading();
            jboxPost(url,data,function(resp){
                $("#content").html(resp);
                jboxEndLoading();
            },null,false);
        }
        // 请假审核
        function verify(recordFlow,type) {
            var title = "请假审核";
            if("show" == type){
                title = "请假信息";
            }
            var url = "<s:url value='/jsres/attendanceNew/queryleaveInfoById'/>?recordFlow=" + recordFlow + "&roleFlag=${roleFlag}"+ "&type="+type;
            jboxOpen(url, title, 850, 600);
        }

        // 销假审核
        function verify2(recordFlow,type) {
            var title = "销假审核";
            if("show" == type){
                title = "销假信息";
            }
            var url = "<s:url value='/jsres/attendanceNew/querycancelInfoById'/>?recordFlow=" + recordFlow + "&roleFlag=${roleFlag}"+ "&type="+type;
            jboxOpen(url, title, 850, 600);
        }

        function exportExcel(){
            var url = "<s:url value='/jsres/attendanceNew/exportLeaveList/${roleFlag}'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>

<body>
<div class="main_hd">
    <h2 class="underline">学员请假管理</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input id="currentPage" type="hidden" name="currentPage"/>
            <table class="searchTable" style="float: inside">
                <tr>
                    <td class="td_left">姓&#12288;&#12288;名：</td>
                    <td >
                        <input type="text" name="doctorName" value="${param.doctorName}" class="input" style="width: 120px"/>
                    </td>
                    <td class="td_left">&#12288;请假类型：</td>
                    <td >
                        <select name="typeId" id="typeId" class="select" style="width: 120px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumLeaveTypeList}" var="leaveType">
                                <option value="${leaveType.dictId}" <c:if test="${param.typeId==leaveType.dictId}">selected="selected"</c:if> >${leaveType.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">&#12288;请假状态：</td>
                    <td >
                        <select name="auditStatusId" id="auditStatusId" class="select" style="width: 150px" >
                            <option value="" >请选择</option>
                            <option value="Auditing" <c:if test="${'Auditing' eq param.auditStatusId}">selected="selected"</c:if>>请假待审核</option>
                            <option value="ManagerPass" <c:if test="${'ManagerPass' eq param.auditStatusId}">selected="selected"</c:if>>请假审核通过</option>
                            <option value="ManagerUnPass" <c:if test="${'ManagerUnPass' eq param.auditStatusId}">selected="selected"</c:if>>请假审核不通过</option>
                            <option value="Revokeing" <c:if test="${'Revokeing' eq param.auditStatusId}">selected="selected"</c:if>>销假待审核</option>
                            <option value="RevokeManagerPass" <c:if test="${'RevokeManagerPass' eq param.auditStatusId}">selected="selected"</c:if>>销假审核通过</option>
<%--                            <option value="RevokeManagerUnPass" <c:if test="${'RevokeManagerUnPass' eq param.auditStatusId}">selected="selected"</c:if>>销假审核不通过</option>--%>
<%--                            <option value="Auditing" <c:if test="${'Auditing' eq param.auditStatusId}">selected="selected"</c:if>>待审核</option>--%>
<%--                            <option value="Y" <c:if test="${'Y' eq param.auditStatusId}">selected="selected"</c:if>>审核通过</option>--%>
<%--                            <option value="N" <c:if test="${'N' eq param.auditStatusId}">selected="selected"</c:if>>审核不通过</option>--%>
                        </select>
                    </td>
                    <td colspan="2">
                        &#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage('1');"/>
                        &#12288;<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
                    </td>
                </tr>
            </table>
        </form>
        <%--<form id="searchForm" action="" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
            &lt;%&ndash;基地管理员角色&ndash;%&gt;
            <c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope || GlobalConstant.USER_LIST_BASE eq sessionScope.userListScope}">
            </c:if>

            &lt;%&ndash;带教老师角色&ndash;%&gt;
            <c:if test="${'teacher' eq roleId}">
            </c:if>
            <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
                <tr>
                    <td class="td_left">学生姓名：</td>
                    <td>
                        <input type="text" name="studentName" class="input" value="${param.studentName}"  style="width: 100px;margin-left: 0px"/>
                    </td>
                    <td class="td_left">请假类型：</td>
                    <td>
                        <select class="select" id="typeId" name="typeId" style="width: 100px;margin-left: 0px">
                            <option value="">请选择</option>
                            <c:forEach var="type" items="${dictTypeEnumLeaveTypeList}">
                                <option value="${type.dictId}" <c:if test="${param.typeId eq type.dictId}">selected="selected"</c:if> >${type.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">出勤状态：</td>
                    <td>
                        <select class="select" id="statueId" name="statueId" style="width: 100px;margin-left: 0px">
                            <option value="2" <c:if test="${param.statueId=='2'}">selected="selected"</c:if>>全部
                            </option>
                            <option value="0" <c:if test="${param.statueId==0}">selected="selected"</c:if>>缺勤
                            </option>
                            <option value="1" <c:if test="${param.statueId==1}">selected="selected"</c:if>>出勤
                            </option>
                            <option value="-1" <c:if test="${param.statueId==-1}">selected="selected"</c:if>>轮休
                            </option>
                        </select>
                    </td>
                    &lt;%&ndash;<td class="td_left">考勤时间：</td>
                    <td colspan="3">
                        <input type="text" id="startDate" name="schStartDate" value="${schStartDate}"
                               class="input datepicker" readonly="readonly" style="width: 90px;"/>
                        ~
                        <input type="text" id="endDate" name="schEndDate" value="${schEndDate}"
                               class="input datepicker" readonly="readonly" style="width: 90px;"/>
                    </td>&ndash;%&gt;
                </tr>
                <tr>
                    <td colspan="8">
                        <input type="button" class="btn_green" onclick="searchAtendanceByitems();"  value="查&#12288;询"/>
                    </td>
                </tr>
            </table>
        </form>--%>
    </div>
    <div class="search_table">
        <table class="grid">
            <tr>
                <th width="5%">序号</th>
                <th width="10%">姓名</th>
                <th width="10%">轮转科室</th>
                <th width="10%">请假类型</th>
                <th width="20%">请假时间</th>
                <th width="10%">请假天数</th>
                <th width="10%">请假状态</th>
                <th width="10%">操作</th>
            </tr>
            <c:forEach items="${kqList}" var="resDoctorKq" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${resDoctorKq.doctorName}</td>
                <td>${resDoctorKq.schDeptName}</td>
                <td>${resDoctorKq.typeName}</td>
                <td>${resDoctorKq.startDate}<br/>${resDoctorKq.endDate}</td>
                <td>${resDoctorKq.intervalDays}</td>
                <td>${resDoctorKq.auditStatusName}</td>
                <c:set var="cancelFlag" value="${resDoctorKq.auditStatusId}"/>
                <c:if test="${!cancelFlag.startsWith('Revoke')}">
                    <%--请假审核--%>
                    <td>
                        <c:set var="verifyFlag" value="false"/>
                        <c:if test="${'teacher' eq roleFlag and empty resDoctorKq.teacherAgreeFlag}">
                            <c:set var="verifyFlag" value="true"/>
                        </c:if>
                        <c:if test="${'head' eq roleFlag and  empty resDoctorKq.headAgreeFlag and resDoctorKq.auditStatusId eq 'HeadAuditing'}">
                            <c:set var="verifyFlag" value="true"/>
                        </c:if>
                        <c:if test="${'manager' eq roleFlag and empty resDoctorKq.managerAgreeFlag and resDoctorKq.auditStatusId eq 'ManagerAuditing'}">
                            <c:set var="verifyFlag" value="true"/>
                        </c:if>
                        <c:if test="${verifyFlag}">
                            <a href="javascript:void(0);" onclick="verify('${resDoctorKq.recordFlow}','');">审核</a>
                        </c:if>
                        <c:if test="${!verifyFlag}">
                            <a href="javascript:void(0);" onclick="verify('${resDoctorKq.recordFlow}','show');">查看</a>
                        </c:if>
                    </td>
                </c:if>
                <c:if test="${cancelFlag.startsWith('Revoke')}">
                    <%--销假审核--%>
                    <td>
                        <c:set var="verifyFlag" value="false"/>
                        <c:if test="${'teacher' eq roleFlag and empty resDoctorKq.teacherAgreeFlag}">
                            <c:set var="verifyFlag" value="true"/>
                        </c:if>
                        <c:if test="${'head' eq roleFlag and  empty resDoctorKq.headAgreeFlag and resDoctorKq.auditStatusId eq 'RevokeHeadAuditing'}">
                            <c:set var="verifyFlag" value="true"/>
                        </c:if>
                        <c:if test="${'manager' eq roleFlag and empty resDoctorKq.managerAgreeFlag and resDoctorKq.auditStatusId eq 'RevokeManagerAuditing'}">
                            <c:set var="verifyFlag" value="true"/>
                        </c:if>
                        <c:if test="${verifyFlag}">
                            <a href="javascript:void(0);" onclick="verify2('${resDoctorKq.recordFlow}','');">审核</a>
                        </c:if>
                        <c:if test="${!verifyFlag}">
                            <a href="javascript:void(0);" onclick="verify2('${resDoctorKq.recordFlow}','show');">查看</a>
                        </c:if>
                    </td>
                </c:if>
            </tr>

            </c:forEach>
            <%--<c:forEach items="${kqList}" var="resDoctorKq">
                <c:set var="disagreeFlag" value="false"/>
                <tr>
                    <td>${resDoctorKq.doctorName}</td>
                    <td>${resDoctorKq.typeName}</td>
                    <td>${resDoctorKq.doctorRemarks}</td>
                    <td>
                            ${resDoctorKq.startDate}~${resDoctorKq.endDate}<br/>
                        <font style="font-weight: bold;">
                            (${resDoctorKq.intervalDays}天)
                        </font>
                    </td>
                    <td>${resDoctorKq.schDeptName}</td>
                    <td>
                        <c:forEach items="${fileMap[resDoctorKq.recordFlow]}" var="f">
                            <a href="${sysCfgMap['upload_base_url']}/${f.filePath}" target="_blank">${f.fileName}</a>
                            <br/>
                        </c:forEach>
                    </td>
                    <td>
                        <c:if test="${resDoctorKq.teacherName ne '-'}">
                            <c:if test="${not empty resDoctorKq.teacherAgreeFlag}">
                                <c:if test="${GlobalConstant.FLAG_N eq resDoctorKq.teacherAgreeFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" alt="审核不通过"/>
                                </c:if>
                                <c:if test="${GlobalConstant.FLAG_Y eq resDoctorKq.teacherAgreeFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" alt="审核通过"/>
                                </c:if>
                                <a title="审核信息：${resDoctorKq.teacherAuditInfo}">${resDoctorKq.teacherName}</a>
                            </c:if>
                            <c:if test="${empty resDoctorKq.teacherAgreeFlag and 'teacher' ne roleFlag}">
                                <img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${resDoctorKq.teacherName}
                            </c:if>
                            <c:if test="${empty resDoctorKq.teacherAgreeFlag and 'teacher' eq roleFlag}">
                                <a href="javascript:save('${resDoctorKq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
                                <a href="javascript:save('${resDoctorKq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
                            </c:if>
                        </c:if>
                        <c:if test="${resDoctorKq.teacherName eq '-'}">
                            ${resDoctorKq.teacherName}
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${resDoctorKq.headName ne '-'}">
                            <c:if test="${not empty resDoctorKq.headAgreeFlag}">
                                <c:if test="${GlobalConstant.FLAG_N eq resDoctorKq.headAgreeFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
                                </c:if>
                                <c:if test="${GlobalConstant.FLAG_Y eq resDoctorKq.headAgreeFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
                                </c:if>
                                <a title="审核信息：${resDoctorKq.headAuditInfo}">${resDoctorKq.headName}</a>
                            </c:if>
                            <c:if test="${empty resDoctorKq.headAgreeFlag}">
                                <c:set var="audit" value="false"></c:set>
                                <c:if test="${resDoctorKq.teacherName eq '-'}">
                                    <c:set var="audit" value="true"></c:set>
                                </c:if>
                                <c:if test="${resDoctorKq.teacherName ne '-'}">
                                    <c:if test="${not empty resDoctorKq.teacherAgreeFlag and resDoctorKq.teacherAgreeFlag eq 'Y'}">
                                        <c:set var="audit" value="true"></c:set>
                                    </c:if>
                                    <c:if test="${empty resDoctorKq.teacherAgreeFlag}">
                                        请等待带教审核
                                    </c:if>
                                </c:if>
                                <c:if test="${audit and 'head' eq roleFlag}">
                                    <a href="javascript:save('${resDoctorKq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
                                    <a href="javascript:save('${resDoctorKq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
                                </c:if>
                                <c:if test="${audit and 'head' ne roleFlag}">
                                    <img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${resDoctorKq.headName}
                                </c:if>
                            </c:if>
                        </c:if>
                        <c:if test="${resDoctorKq.headName eq '-'}">
                            ${resDoctorKq.headName}
                        </c:if>
                    </td>
                        <td>
                            <c:if test="${resDoctorKq.tutorName ne '-'}">
                                <c:if test="${not empty resDoctorKq.tutorAgreeFlag}">
                                    <c:if test="${GlobalConstant.FLAG_N eq resDoctorKq.tutorAgreeFlag}">
                                        <img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
                                    </c:if>
                                    <c:if test="${GlobalConstant.FLAG_Y eq resDoctorKq.tutorAgreeFlag}">
                                        <img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
                                    </c:if>
                                    <a title="审核信息：${resDoctorKq.tutorAuditInfo}">${resDoctorKq.tutorName}</a>
                                </c:if>
                                <c:if test="${empty resDoctorKq.tutorAgreeFlag}">
                                    <c:set var="audit" value="false"></c:set>
                                    <c:if test="${resDoctorKq.teacherName eq '-' and resDoctorKq.headName eq '-'  }">
                                        <c:set var="audit" value="true"></c:set>
                                    </c:if>
                                    <c:if test="${resDoctorKq.teacherName ne '-' and resDoctorKq.headName eq '-' }">
                                        <c:if test="${not empty resDoctorKq.teacherAgreeFlag and resDoctorKq.teacherAgreeFlag eq 'Y'}">
                                            <c:set var="audit" value="true"></c:set>
                                        </c:if>
                                        <c:if test="${empty resDoctorKq.teacherAgreeFlag}">
                                            请等待带教审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${resDoctorKq.teacherName eq '-' and resDoctorKq.headName ne '-' }">
                                        <c:if test="${not empty resDoctorKq.headAgreeFlag and resDoctorKq.headAgreeFlag eq 'Y'}">
                                            <c:set var="audit" value="true"></c:set>
                                        </c:if>
                                        <c:if test="${empty resDoctorKq.headAgreeFlag}">
                                            请等待主任审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${resDoctorKq.teacherName ne '-' and resDoctorKq.headName ne '-'  }">
                                        <c:if test="${not empty resDoctorKq.teacherAgreeFlag and resDoctorKq.teacherAgreeFlag eq 'Y'}">
                                            <c:if test="${not empty resDoctorKq.headAgreeFlag  and resDoctorKq.headAgreeFlag eq 'Y'}">
                                                <c:set var="audit" value="true"></c:set>
                                            </c:if>
                                            <c:if test="${empty resDoctorKq.headAgreeFlag}">
                                                请等待主任审核
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty resDoctorKq.teacherAgreeFlag}">
                                            请等待带教审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${audit and 'tutor' eq roleFlag}">
                                        <a href="javascript:save('${resDoctorKq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
                                        <a href="javascript:save('${resDoctorKq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
                                    </c:if>
                                    <c:if test="${audit and 'tutor' ne roleFlag}">
                                        <img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${resDoctorKq.tutorName}
                                    </c:if>
                                </c:if>
                            </c:if>
                            <c:if test="${resDoctorKq.tutorName eq '-'}">
                                ${resDoctorKq.tutorName}
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${resDoctorKq.managerName ne '-'}">
                                <c:if test="${not empty resDoctorKq.managerAgreeFlag}">
                                    <c:if test="${GlobalConstant.FLAG_N eq resDoctorKq.managerAgreeFlag}">
                                        <img src="<s:url value='/css/skin/${skinPath}/images/del3.png'/>" />
                                    </c:if>
                                    <c:if test="${GlobalConstant.FLAG_Y eq resDoctorKq.managerAgreeFlag}">
                                        <img src="<s:url value='/css/skin/${skinPath}/images/gou.png'/>" />
                                    </c:if>
                                    <a title="审核信息：${resDoctorKq.managerAuditInfo}">${resDoctorKq.managerName}</a>
                                </c:if>
                                <c:if test="${empty resDoctorKq.managerAgreeFlag}">
                                    <c:set var="audit" value="false"></c:set>
                                    <c:if test="${resDoctorKq.teacherName eq '-' and resDoctorKq.headName eq '-' and resDoctorKq.tutorName eq '-'  }">
                                        <c:set var="audit" value="true"></c:set>
                                    </c:if>
                                    <c:if test="${resDoctorKq.teacherName ne '-' and resDoctorKq.headName eq '-' and resDoctorKq.tutorName eq '-'}">
                                        <c:if test="${not empty resDoctorKq.teacherAgreeFlag and resDoctorKq.teacherAgreeFlag eq 'Y'}">
                                            <c:set var="audit" value="true"></c:set>
                                        </c:if>
                                        <c:if test="${empty resDoctorKq.teacherAgreeFlag}">
                                            请等待带教审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${resDoctorKq.teacherName eq '-' and resDoctorKq.headName ne '-'  and resDoctorKq.tutorName eq '-'}">
                                        <c:if test="${not empty resDoctorKq.headAgreeFlag and resDoctorKq.headAgreeFlag eq 'Y'}">
                                            <c:set var="audit" value="true"></c:set>
                                        </c:if>
                                        <c:if test="${empty resDoctorKq.headAgreeFlag}">
                                            请等待主任审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${resDoctorKq.teacherName eq '-' and resDoctorKq.headName eq '-'  and resDoctorKq.tutorName ne '-'}">
                                        <c:if test="${not empty resDoctorKq.tutorAgreeFlag and resDoctorKq.tutorAgreeFlag eq 'Y'}">
                                            <c:set var="audit" value="true"></c:set>
                                        </c:if>
                                        <c:if test="${empty resDoctorKq.tutorAgreeFlag}">
                                            请等待导师审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${resDoctorKq.teacherName ne '-' and resDoctorKq.headName ne '-' and resDoctorKq.tutorName ne '-'  }">
                                        <c:if test="${not empty resDoctorKq.teacherAgreeFlag and resDoctorKq.teacherAgreeFlag eq 'Y'}">
                                            <c:if test="${not empty resDoctorKq.headAgreeFlag  and resDoctorKq.headAgreeFlag eq 'Y'}">
                                                <c:if test="${not empty resDoctorKq.tutorAgreeFlag  and resDoctorKq.tutorAgreeFlag eq 'Y'}">
                                                    <c:set var="audit" value="true"></c:set>
                                                </c:if>
                                                <c:if test="${empty resDoctorKq.tutorAgreeFlag}">
                                                    请等待导师审核
                                                </c:if>
                                            </c:if>
                                            <c:if test="${empty resDoctorKq.headAgreeFlag}">
                                                请等待主任审核
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty resDoctorKq.teacherAgreeFlag}">
                                            请等待带教审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${resDoctorKq.teacherName ne '-' and resDoctorKq.headName ne '-' and resDoctorKq.tutorName eq '-'  }">
                                        <c:if test="${not empty resDoctorKq.teacherAgreeFlag and resDoctorKq.teacherAgreeFlag eq 'Y'}">
                                            <c:if test="${not empty resDoctorKq.headAgreeFlag  and resDoctorKq.headAgreeFlag eq 'Y'}">
                                                <c:set var="audit" value="true"></c:set>
                                            </c:if>
                                            <c:if test="${empty resDoctorKq.headAgreeFlag}">
                                                请等待主任审核
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty resDoctorKq.teacherAgreeFlag}">
                                            请等待带教审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${resDoctorKq.teacherName ne '-' and resDoctorKq.headName eq '-' and resDoctorKq.tutorName ne '-'  }">
                                        <c:if test="${not empty resDoctorKq.teacherAgreeFlag and resDoctorKq.teacherAgreeFlag eq 'Y'}">
                                            <c:if test="${not empty resDoctorKq.tutorAgreeFlag  and resDoctorKq.tutorAgreeFlag eq 'Y'}">
                                                <c:set var="audit" value="true"></c:set>
                                            </c:if>
                                            <c:if test="${empty resDoctorKq.tutorAgreeFlag}">
                                                请等待导师审核
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty resDoctorKq.teacherAgreeFlag}">
                                            请等待带教审核
                                        </c:if>
                                    </c:if>
                                    <c:if test="${resDoctorKq.teacherName eq '-' and resDoctorKq.headName ne '-' and resDoctorKq.tutorName ne '-'  }">
                                        <c:if test="${not empty resDoctorKq.headAgreeFlag  and resDoctorKq.headAgreeFlag eq 'Y'}">
                                            <c:if test="${not empty resDoctorKq.tutorAgreeFlag  and resDoctorKq.tutorAgreeFlag eq 'Y'}">
                                                <c:set var="audit" value="true"></c:set>
                                            </c:if>
                                            <c:if test="${empty resDoctorKq.tutorAgreeFlag}">
                                                请等待导师审核
                                            </c:if>
                                        </c:if>
                                        <c:if test="${empty resDoctorKq.headAgreeFlag}">
                                            请等待主任审核
                                        </c:if>
                                    </c:if>

                                    <c:if test="${audit  and 'manager' eq roleFlag}">
                                        <a href="javascript:save('${resDoctorKq.recordFlow}','${GlobalConstant.FLAG_Y}');">同意</a> |
                                        <a href="javascript:save('${resDoctorKq.recordFlow}','${GlobalConstant.FLAG_N}');">不同意</a>
                                    </c:if>
                                    <c:if test="${audit and 'manager' ne roleFlag}">
                                        <img src="<s:url value='/css/skin/${skinPath}/images/query.gif'/>"/>&nbsp;${resDoctorKq.managerName}
                                    </c:if>
                                </c:if>
                            </c:if>
                            <c:if test="${resDoctorKq.managerName eq '-'}">
                                ${resDoctorKq.managerName}
                            </c:if>
                        </td>
                </tr>
            </c:forEach>--%>

            <c:if test="${empty kqList}">
                <tr>
                    <td style="text-align: center" colspan="20">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="resultDiv">
        <c:set var="pageView" value="${pdfn:getPageView(kqList)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>

</body>
</html>
