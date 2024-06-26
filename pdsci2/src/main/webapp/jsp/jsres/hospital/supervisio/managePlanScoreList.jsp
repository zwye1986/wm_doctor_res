<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $("#currentPage").val("${param.currentPage}");
    });

    function zeroFill(i) {
        if (i >= 0 && i <= 9) {
            return "0" + i;
        } else {
            return i;
        }
    }

    function goScore(speId, orgFlow, userFlow, subjectFlow, devTime, closedTime, subjectYear,subjectActivitiFlows) {
        //获取当前时间
        var currentTime = new Date();
        devTime = devTime.replace("-", "/");
        devTime = new Date(Date.parse(devTime));
        closedTime = closedTime.replace("-", "/");
        closedTime = new Date(Date.parse(closedTime));
        if (currentTime < devTime) {
            top.jboxTip("评分时间未到！");
        } else if (closedTime < currentTime) {
            top.jboxTip("评分时间已结束！");
        } else {
            var url = "<s:url value='/jsres/supervisio/searchPlanScore?speId='/>" + speId + "&roleFlag=${roleFlag}&orgFlow=" + orgFlow +
                "&userFlow=" + userFlow + "&subjectFlow=" + subjectFlow + "&subjectYear=" + subjectYear+"&subjectActivitiFlows="+subjectActivitiFlows;
            var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, "评分", 1870, 815, false);
        }
    }

    function baseScore(speId, orgFlow, userFlow, subjectFlow, devTime, closedTime, subjectYear,subjectActivitiFlows) {
        //获取当前时间
        var currentTime = new Date();
        devTime = devTime.replace("-", "/");
        devTime = new Date(Date.parse(devTime));
        closedTime = closedTime.replace("-", "/");
        closedTime = new Date(Date.parse(closedTime));
        if (currentTime < devTime) {
            top.jboxTip("评分时间未到！");
        } else if (closedTime < currentTime) {
            top.jboxTip("评分时间已结束！");
        } else {
            var url = "<s:url value='/jsres/supervisio/baseScore?speId='/>" + speId + "&roleFlag=${roleFlag}&orgFlow=" + orgFlow +
                "&userFlow=" + userFlow + "&subjectFlow=" + subjectFlow + "&subjectYear=" + subjectYear+"&subjectActivitiFlows="+subjectActivitiFlows;
            var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, "评分", 1100, 800, false);
        }
    }

    function showPlanInfo(speId, subjectFlow,roleFlag,subjectActivitiFlows,manageUserFlow) {
        var url = "<s:url value ='/jsres/supervisio/showPlanInfoMian'/>?speId=" + speId + "&roleFlag=${roleFlag}&subjectFlow=" + subjectFlow+"&subjectActivitiFlows="+subjectActivitiFlows+"&manageUserFlow="+manageUserFlow;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, "查看表单评分详情", 1875, 1000, false);
    }
    function subjectInfo(subjectFlow) {
        var url = "<s:url value ='/jsres/supervisio/planScoreInfo'/>?subjectFlow=" + subjectFlow + "&roleFlag=${roleFlag}&baseInfo=${baseInfo}";
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, "专业详情", 1000, 200, false);
    }
    function setFeedback(subjectActivitiFlows,type) {
        var title = "上传督导反馈";
        var url = "<s:url value='/jsres/supervisio/addFeedback'/>?subjectActivitiFlows="+subjectActivitiFlows+"&type="+type;
        if(type == 'spe'){
            title = "上传专业反馈";
            <%--url = "<s:url value='/hbres/supervisio/addFeedback'/>?subjectFlow="+subjectActivitiFlows+"&type="+type;--%>
        }
        if(type == 'major'){
            title = "上传专业基地反馈";
            url = "<s:url value='/jsres/supervisio/addFeedback'/>?subjectFlow="+subjectActivitiFlows+"&type="+type;
        }
        jboxOpen(url,title, 600, 280);
    }
    function downFeedback(fileUrl,type) {
        jboxTip("下载中…………");
        var url = "<s:url value='/jsres/supervisio/downFeedbackFile'/>?fileUrl=" + fileUrl + "&type=" + type;
        window.location.href = url;
    }

    function lookFeedback(subjectActivitiFlows,type,isRead,speName) {
        var url ="";
        var  title = "报告";
        if(type == 'spe'){
            //按照项目  专业基地专家的报告
            url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectFlow="+subjectActivitiFlows+"&type="+type+"&isRead="+isRead+"&speName="+speName;
            jboxOpen(url,title, 1000, 666);
        }
        if(type == 'major'){
            //按照批次  基地自评报告
            url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectActivitiFlows="+subjectActivitiFlows+"&type="+type+"&isRead="+isRead+"&speName="+speName;
            jboxOpen(url,title, 1000, 800);
        }
        if (type == 'management'){
            //按照批次，督导报告
            url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectActivitiFlows="+subjectActivitiFlows+"&type="+type+"&isRead="+isRead+"&speName="+speName;
            jboxOpen(url,title, 1000, 800);
        }
    }
</script>

<c:if test="${empty list}">
    <div class="search_table" style="width: 100%;padding: 0px 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>基地代码</th>
                <th>检查基地</th>
                <th>检查专业</th>
                <th>检查年份</th>
                <th>督导结果</th>
                <th>操作</th>
            </tr>
            <tr>
                <td colspan="6">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix" style="width: 100%;padding: 0px 20px">
        <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
            <thead>
            <tr>
                <th>基地代码</th>
                <th>检查基地</th>
                <th width="100px">检查专业</th>
                <th>检查年份</th>
                <th>督导结果</th>
                <th>督导反馈</th>
                <th>专业基地反馈</th>
                <th>基地自评反馈</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="s">
                <tr class="fixTrTd">
                    <td>${s.baseCode}</td>
                    <td><a onclick="subjectInfo('${s.subjectFlow}')"> ${s.orgName}</a></td>
                    <td>${s.speName}</td>
                    <td>${s.subjectYear}</td>
                    <td>
                        <c:choose>
                            <c:when test="${s.supervisioResults eq 'qualified'}">
                                合格
                            </c:when>
                            <c:when test="${s.supervisioResults eq 'basically'}">
                                基本合格
                            </c:when>
                            <c:when test="${s.supervisioResults eq 'yellowCard'}">
                                限时整改
                            </c:when>
                            <c:when test="${s.supervisioResults eq 'redCard'}">
                                撤销资格
                            </c:when>
                            <c:otherwise>
                                暂无结果
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a class="btn_blue" style="width: 56px;color:white;margin-top: 4px" href="javascript:void(0);" onclick="lookFeedback('${s.subjectActivitiFlows}','management','','${s.speName}');">评价</a>
                    </td>
                    <td>
                        <a class="btn_blue" style="width: 56px;color:white;margin-top: 4px" href="javascript:void(0);" onclick="lookFeedback('${s.subjectFlow}','spe','Y','${s.speName}');">查看</a>
                    </td>
                    <td>
                        <a class="btn_blue" style="width: 56px;color:white;margin-top: 4px" href="javascript:void(0);" onclick="lookFeedback('${s.subjectActivitiFlows}','major','Y','${s.speName}');">查看</a>
                    </td>

                    <td>
                       <%-- 基地可以评分和查看--%>
                        <c:if test="${roleFlag eq 'local'}">
                            <a class="btn_blue" href="javascript:void(0);" style="width: 56px;margin-right: -101px;margin-top: -32px;color: white"
                               onclick="baseScore('${s.speId}','${s.orgFlow}','${userFlow}','${s.subjectFlow}','${s.devTime}','${s.devTimeClose}','${s.subjectYear}','${s.subjectActivitiFlows}');">评分</a>
                            <a class="btn_blue" style="width: 56px;margin-top: 35px;color: white" href="javascript:void(0);" onclick="showPlanInfo('${s.speId}','${s.subjectFlow}','${roleFlag}');">查看评分</a>
                        </c:if>
                        <%--专业专家：根据当前时间和项目评分的关闭时间判断是评分还是查看评分--%>
                        <c:if test="${(roleFlag eq 'expertLeader' || roleFlag eq 'management' || roleFlag eq 'baseExpert' )&& s.closedTime>dateNow}">
                            <a class="btn_blue" style="color: white;width: 56px" href="javascript:void(0);"
                               onclick="goScore('${s.speId}','${s.orgFlow}','${userFlow}','${s.subjectFlow}','${s.openTime}','${s.closedTime}','${s.subjectYear}','${s.subjectActivitiFlows}');">评分</a>
                        </c:if>
                        <c:if test="${(roleFlag eq 'expertLeader' || roleFlag eq 'management' ||  roleFlag eq 'baseExpert')&& (s.closedTime<dateNow)}">
                            <a class="btn_blue" style="width: 56px;color:white;" href="javascript:void(0);" onclick="showPlanInfo('${s.speId}','${s.subjectFlow}','${roleFlag}','${s.subjectActivitiFlows}');">查看评分</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<div class="page" style="text-align: center">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
      
