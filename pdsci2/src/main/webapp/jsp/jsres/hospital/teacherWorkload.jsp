<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
    function toPage(page) {
        var operStartDate = $("#operStartDate").val();
        var operEndDate = $("#operEndDate").val();
        if (operStartDate != "" || operEndDate != "") {
            if (operStartDate != "" && operEndDate == "") {
                jboxTip("请选择结束时间！");
                return false;
            }
            else if (operStartDate == "" && operEndDate != "") {
                jboxTip("请选择开始时间！");
                return false;
            }
            else if (operEndDate < operStartDate) {
                jboxTip("开始时间不能大于结束时间！");
                return false;
            }
        }
        if($("input[name='doctorTypeIdList']:checked").length<=0)
        {
            jboxTip("请选择人员类型！");
            return false;
        }
        page = page || "${param.currentPage}";
        $("#currentPage").val(page);
        var form = $("#teacherWorkloadForm").serialize();
        teacherWorkload(form);
    }
    $(function () {
        $('#operStartDate,#operEndDate').datepicker();
    });
    function details(teacherUserFlow, leiXin, biaoJi, startDate, endDate) {
        var doctorTypeIdList = "";
        <c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
        if("${doctorTypeSelectMap[type.dictId]}"!="")
        {
            doctorTypeIdList = doctorTypeIdList + "&doctorTypeIdList=${type.dictId}";
        }
        </c:forEach>
        var url = "<s:url value='/jsres/manage/details'/>?teacherUserFlow=" + teacherUserFlow + "&biaoJi=" + biaoJi + "&startDate=" + startDate + "&endDate=" + endDate+doctorTypeIdList;
        jboxOpen(url, leiXin, 850, 550, false);
    }
    function exportInfo() {
        var operStartDate = $("#operStartDate").val();
        var operEndDate = $("#operEndDate").val();
        if (operStartDate != "" || operEndDate != "") {
            if (operStartDate != "" && operEndDate == "") {
                jboxTip("请选择结束时间！");
                return false;
            }
            else if (operStartDate == "" && operEndDate != "") {
                jboxTip("请选择开始时间！");
                return false;
            }
            else if (operEndDate < operStartDate) {
                jboxTip("开始时间不能大于结束时间！");
                return false;
            }
        }
        if($("input[name='doctorTypeIdList']:checked").length<=0)
        {
            jboxTip("请选择人员类型！");
            return false;
        }
        jboxTip("导出中…………");
        var url = "<s:url value='/jsres/manage/exportExcel'/>";
        jboxSubmit($("#teacherWorkloadForm"), url, null, null, false);
        jboxEndLoading();
    }
    function activityDetails(peakerFlow, deptFlow,leiXin,startTime, endTime){
        var url = "<s:url value='/jsres/manage/activityDetails'/>?speakerFlow=" + peakerFlow + "&startTime=" + startTime + "&endTime=" + endTime+"&deptFlow="+deptFlow;
        jboxOpen(url, leiXin, 650, 500, true);
    }
</script>

<body>
<div class="main_hd">
    <h2 class="underline">带教工作量查询</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="teacherWorkloadForm" style="position: relative;margin: 10px;" method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">科&#12288;&#12288;室：</td>
                    <td>
                        <select name="deptFlow" class="select">
                            <option></option>
                            <c:forEach items="${deptList}" var="dept">
                                <option value="${dept.deptFlow}"
                                        <c:if test="${param.deptFlow==dept.deptFlow}">selected="selected"</c:if>>${dept.deptName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">姓&#12288;&#12288;名：</td>
                    <td>
                        <input type="text" name="userName" value="${param.userName}" class="input" />
                    </td>
                    <td class="td_left">时间区间：</td>
                    <td colspan="3">
                        <input type="text" id="operStartDate" name="operStartDate" value="${operStartDate}" class="input"
                               readonly="readonly" style="width: 100px;"/>
                        ~
                        <input type="text" id="operEndDate" name="operEndDate" value="${operEndDate}" class="input"
                               readonly="readonly" style="width: 100px;"/>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">排&ensp;序&ensp;项：</td>
                    <td>
                        <select name="orderItem" class="select">
                            <option value="0"
                                    <c:if test="${(param.orderItem=='0') or empty param.orderItem}">selected="selected"</c:if>>带教名单</option>
                            <%--<option value="1"--%>
                                    <%--<c:if test="${param.orderItem=='1'}">selected="selected"</c:if>>未审核数</option>--%>
                            <%--<option value="2"--%>
                                    <%--<c:if test="${param.orderItem=='2'}">selected="selected"</c:if>>已审核数</option>--%>
                        </select>
                    </td>
                    <td colspan="2">
                        <label>
                            <input type="radio" name="sortName" value="DESC" <c:if test="${(param.sortName eq 'DESC') or empty param.sortName}">checked="checked"</c:if>/>降序
                        </label>
                        &#12288;
                        <label>
                            <input type="radio" name="sortName" value="ASC" <c:if test="${param.sortName eq 'ASC'}">checked="checked"</c:if>/>升序
                        </label>
                    </td>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" name="doctorTypeIdList" value="${type.id}"
                                ${doctorTypeSelectMap[type.id]}>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td colspan="8">
                        <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                        &#12288;<input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="10%"/>
                <col width="15%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="10%"/>
            </colgroup>
            <tr>
                <th>科室</th>
                <th>带教老师</th>
                <th>用户名</th>
                <th>老师均分</th>
                <th>已审核数</th>
                <th>未审核数</th>
                <th>教学活动数</th>
                <th>带教名单</th>
                <th>带教人次</th>
            </tr>
            <c:forEach items="${dataList}" var="data">
                <%--<c:set value="curr${user.userFlow}" var="currKey"/>--%>
                <%--<c:set value="average${user.userFlow}" var="averageKey"/>--%>
                <%--<c:set value="notAudited${user.userFlow}" var="notAuditedKey"/>--%>
                <%--<c:set value="isNotAudited${user.userFlow}" var="isNotAuditedKey"/>--%>
                <%--<c:set value="studentNum${user.userFlow}" var="studentNumKey"/>--%>
                <%--<c:set value="after${user.userFlow}" var="afterKey"/>--%>
                <tr>
                    <td title="${data.DEPT_NAME}">
                            ${pdfn:cutString(data.DEPT_NAME,5,true,3)}
                    </td>
                    <td>${data.USER_NAME}</td>
                    <td>${data.USER_CODE}</td>
                    <td>${empty data.AVERAGE ? 0 : data.AVERAGE}</td>
                    <td>${empty data.isNotAudited ? 0 : data.isNotAudited}</td>
                    <td>${empty data.notAudited ? 0 : data.notAudited}</td>
                    <td>
                        <a onclick="activityDetails('${data.USER_FLOW}','${data.DEPT_FLOW}','教学活动','${param.operStartDate}','${param.operEndDate}');">${data.ACTIVITYNUM}</a>
                    </td>
                    <td>
                        <a onclick="details('${data.USER_FLOW}','带教学员','1','${operStartDate}','${operEndDate}');">${data.CURRSTUDENTHE}</a>
                    </td>
                    <td>
                        <a onclick="details('${data.USER_FLOW}','带教学员','1','${operStartDate}','${operEndDate}');">${data.CURRSTUDENTCOUNT}</a>
                    </td>
                        <%--<td><a onclick="details('${user.userFlow}','已出科学员','0');">${studentNumMap[afterKey]}</a></td>--%>
                        <%--<td>${studentNumMap[studentNumKey]}</td>--%>
                </tr>
            </c:forEach>
            <c:if test="${empty dataList}">
                <tr>
                    <td colspan="9" style="text-align: center;">无记录!</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
</body>
