<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<%@include file="/jsp/common/common.jsp"%>
<script type="text/javascript"
        src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
    });

    function showAllSignup(signupFlow, flag, roleFlag) {
        var url = "<s:url value ='/jsres/examSignUp/auditSignup'/>?signupFlow=" + signupFlow + "&flag=" + flag + "&roleFlag=" + roleFlag;
        jboxOpen(url, "补考审核", 1000, 600);
    }

    function tableFixed(div) {
        $("#dateFixed,#topTitle").css("top", $(div).scrollTop() + "px");
        $("#deptFixed,#topTitle").css("left", $(div).scrollLeft() + "px");
    }

    function heightFiexed() {
        //修正高度
        var toFixedTd = $(".toFiexdDept");
        $(".fixedBy").each(function (i) {
            $(toFixedTd[i]).height($(this).height());
        });
        var fixTrTd = $(".fixTrTd");
        var fixTrTh = $(".fixTrTh");
        var fixTd = $(".fix");
        $(fixTrTd).each(function (i) {
            var maxheight = -1;
            $(fixTrTd[i]).find(".by").each(function () {
                if ($(this).height() > maxheight) maxheight = $(this).height();
            });
            if (maxheight != -1) {
                $(fixTrTh[i]).find(".fix").each(function () {
                    $(this).height(maxheight);
                });
            }
        });
    }

    onresize = function () {
        heightFiexed();
    };
</script>
<c:if test="${empty list}">
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>审核</th>
                <c:if test="${param.tabTag ne 'SecondWait' and param.tabTag ne 'SecondWait2'}">
                    <th>审核状态</th>
                </c:if>
                <th>姓名</th>
                <th>地市</th>
                <th>培训基地</th>
                <th>考试编号</th>
                <th>报考科目</th>
                <th>培训起止时间</th>
                <th>异常报考</th>
            </tr>
            <tr>
                <td colspan="9">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix" style="padding: 0px 20px">
        <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
            <thead>
            <tr>
                <th class="toFiexdDept">审核</th>
                <c:if test="${param.tabTag ne 'SecondWait' and param.tabTag ne 'SecondWait2'}">
                    <th class="fixedBy">审核状态</th>
                </c:if>
                <th class="fixedBy">轮转数据是否合规</th>
                <th class="fixedBy">姓名</th>
                <th class="fixedBy">地市</th>
                <th class="fixedBy">培训基地</th>
                <th class="fixedBy">考试编号</th>
                <th class="fixedBy">报考科目</th>
                <th class="fixedBy">培训起止时间</th>
                <th class="fixedBy">异常报考</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="s">
                <c:set value="${s.signupFlow}startDate" var="startDate"></c:set>
                <c:set value="${s.signupFlow}endTime" var="endTime"></c:set>
                <tr class="fixTrTd">
                    <td class="by">
                        <c:if test="${f eq 'Y'}">
                            <c:if test="${s.auditStatusId eq 'Auditing'}">
                                <a class="btn" onclick="showAllSignup('${s.signupFlow}','Y','${roleFlag}');"
                                   style="margin-top: 5px;margin-bottom: 5px;padding:0 20px">审核</a>
                            </c:if>
                            <c:if test="${s.auditStatusId ne 'Auditing'}">
                                <a class="btn" onclick="showAllSignup('${s.signupFlow}','N','${roleFlag}');"
                                   style="margin-top: 5px;margin-bottom: 5px;padding:0 20px">查看</a>
                            </c:if>

                        </c:if>
                        <c:if test="${f eq 'N'}">
                            <a class="btn" onclick="showAllSignup('${s.signupFlow}','N','${roleFlag}');"
                               style="margin-top: 5px;margin-bottom: 5px;">查看</a>
                        </c:if>
                    </td>
                    <c:if test="${param.tabTag ne 'SecondWait' and param.tabTag ne 'SecondWait2'}">
                        <c:set var="k" value="${s.testId}_make_up"/>
                        <c:choose>
                            <c:when test="${ sysCfgMap[k] eq 'Y'}">
                                <td class="by">${s.auditStatusName}</td>
                            </c:when>
                            <c:when test="${ sysCfgMap[k] eq 'N' or empty sysCfgMap[k] }">
                                <c:choose>
                                    <c:when test="${not empty s.globalAuditStatusId}">
                                        <td style="min-width: 80px; max-width: 80px; " class="by">省厅审核中</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td style="min-width: 80px; max-width: 80px; " class="by"> ${s.auditStatusName}</td>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </c:if>
                    <td style="min-width: 80px; max-width: 80px; " class="by">
						<c:choose>
                            <c:when test="${not empty nonComplianceRecordsMap[s.doctorFlow]}">
                                <a onclick="showNonComplianceRecords('${s.doctorFlow}')">否</a>
                            </c:when>
                            <c:otherwise>
                                是
                            </c:otherwise>
                        </c:choose>
					</td>
                    <td class="by">${s.userName}</td>
                    <td class="by">${s.orgCityName}</td>
                    <td class="by">${s.orgName}</td>
                    <td class="by">${s.testId}</td>
                    <td class="by">
                        <c:if test="${s.signupTypeId eq 'Theory'}">理论</c:if>
                        <c:if test="${s.signupTypeId eq 'Skill'}">技能</c:if>
                    </td>
                    <td class="by">${trainMap[startDate]}&nbsp;~&nbsp;${trainMap[endTime]}</td>
                    <td class="by">${s.tempDoctorFlag}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
      
