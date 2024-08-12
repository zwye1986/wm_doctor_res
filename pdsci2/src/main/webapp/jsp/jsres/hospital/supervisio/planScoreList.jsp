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
            jboxMessager(iframe, "评分", 1875, 1000, false);
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
            jboxMessager(iframe, "评分", 1875, 850, false);
        }
    }

    function showPlanInfo(speId, subjectFlow,roleFlag,subjectActivitiFlows,manageUserFlow) {
        var url = "<s:url value ='/jsres/supervisio/showPlanInfoMian'/>?speId=" + speId + "&roleFlag=${roleFlag}&userFlow=${userFlow}&subjectFlow=" + subjectFlow+"&subjectActivitiFlows="+subjectActivitiFlows+"&manageUserFlow="+manageUserFlow;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, "查看表单评分详情", 1875, 850, false);
    }
    function subjectInfo(subjectFlow) {
        var url = "<s:url value ='/jsres/supervisio/planScoreInfo'/>?subjectFlow=" + subjectFlow + "&roleFlag=${roleFlag}&baseInfo=${baseInfo}";
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, "专业详情", 1000, 200, false);
    }
    function exportReport(subjectActivitiFlows,type) {
        var url ="";
        if(type == 'spe'){
            url ="<s:url value='/jsres/supervisio/exportReport'/>?subjectActivitiFlows="+subjectActivitiFlows+"&type="+type;
        }
        if(type == 'major'){
            url ="<s:url value='/jsres/supervisio/exportReport'/>?&subjectFlow="+subjectActivitiFlows+"&type="+type;
        }
        jboxTip("导出中…………");
        top.jboxExp(null,url);
        jboxEndLoading();
    }


    function setFeedback(subjectActivitiFlows,type,isRead,speName, devTime, closedTime,read) {
        var url ="";
        var  title = "报告";
        //获取当前时间
        var currentTime = new Date();
        devTime = devTime.replace("-", "/");
        devTime = new Date(Date.parse(devTime));
        closedTime = closedTime.replace("-", "/");
        closedTime = new Date(Date.parse(closedTime));
        //查看
        if (read=='Y'){
            if(type == 'spe'){
                //按照项目
                url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectFlow="+subjectActivitiFlows+"&type="+type+"&isRead="+isRead+"&speName="+speName;
                jboxOpen(url,title, 1000, 666);
            }
            if(type == 'major'){
                //按照批次
                url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectActivitiFlows="+subjectActivitiFlows+"&type="+type+"&isRead="+isRead+"&speName="+speName;
                jboxOpen(url,title, 1000, 800);
            }
            return;
        }
        if (currentTime < devTime) {
            top.jboxTip("自评时间未到！");
        } else if (closedTime < currentTime) {
            top.jboxTip("自评时间已结束！");
        } else {
            if(type == 'spe'){
                //按照项目
                url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectFlow="+subjectActivitiFlows+"&type="+type+"&isRead="+isRead+"&speName="+speName;
                jboxOpen(url,title, 1000, 666);
            }
            if(type == 'major'){
                //按照批次
                url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectActivitiFlows="+subjectActivitiFlows+"&type="+type+"&isRead="+isRead+"&speName="+speName;
                jboxOpen(url,title, 1000, 800);
            }
        }
    }
    function downFeedback(fileUrl,type) {
        jboxTip("下载中…………");
        var url = "<s:url value='/jsres/supervisio/downFeedbackFile'/>?fileUrl=" + fileUrl + "&type=" + type;
        window.location.href = url;
    }

    function showRecords(subjectFlow,roleFlag) {
        var url ="<s:url value='/jsres/supervisio/showRecords'/>?subjectFlow="+subjectFlow+"&roleFlag="+roleFlag;
        jboxOpen(url,"提交记录", 1022, 666);
    }
</script>

<c:if test="${empty list}">
    <div class="search_table" style="width: 100%;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th >基地代码</th>
                <th>检查基地</th>
                <th>检查专业</th>
                <th width="80px">检查年份</th>
                <th width="100px">督导结果</th>
                <th>操作</th>
            </tr>
            <tr>
                <td colspan="6">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix" style="width: 100%;">
        <div class="div_search">
        <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
            <thead>
            <tr>
                <th width="100px">基地代码</th>
                <th>检查基地</th>
                <th width="100px">检查专业</th>
                <th width="80px">检查年份</th>
                <th width="100px">督导结果</th>
                <c:if test="${roleFlag ne 'local' && roleFlag ne 'expertLeader'}">
                    <th width="100px">督导反馈</th>
                </c:if>
                <th>专业基地反馈</th>
                <c:if test="${roleFlag ne 'baseExpert' && roleFlag ne 'local'&& roleFlag ne 'expertLeader'}">
                    <th>基地自评反馈</th>
                </c:if>
                <th width="100px">
                    提交记录
                </th>
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
                    <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL ||  roleFlag eq 'management' || (roleFlag eq 'local' and suAoth eq 'Y')}">
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
                    </c:if>
                    <c:if test="${(roleFlag ne GlobalConstant.USER_LIST_GLOBAL && roleFlag ne 'management' && roleFlag ne 'local') or (roleFlag eq 'local' and suAoth ne 'Y')}">
                        <td>暂无结果</td>
                    </c:if>

                    <c:if test="${roleFlag ne 'local' && roleFlag ne 'expertLeader'}">
                        <td>
                            <c:if test="${roleFlag ne 'baseExpert'}">
                                <a class="btn" style="width: 56px;margin-top: -32px;margin-right: -102px;" href="javascript:void(0);"
                                   onclick="setFeedback('${s.subjectActivitiFlows}','sup');">${empty s.supFeedBack?'':'重新'}上传</a>
                            </c:if>
                            <c:if test="${empty s.supFeedBack and roleFlag ne 'local' and roleFlag ne 'management'}" >
                                暂无
                            </c:if>
                        </td>
                    </c:if>

                    <td>
                        <c:if test="${roleFlag eq 'local' }">
                            <c:if test="${roleFlag eq 'local' }">
                                <a  style="width: 56px;margin-top: 4px" href="javascript:void(0);" onclick="setFeedback('${s.subjectFlow}','spe','Y','${s.speName}','${s.devTime}','${s.devTimeClose}','Y');">查看</a>
                            </c:if>
                        </c:if>
                        <c:if test="${roleFlag eq 'baseExpert' || roleFlag eq 'expertLeader'}">
                            <a  style="width: 56px;" href="javascript:void(0);" onclick="setFeedback('${s.subjectFlow}','spe','','${s.speName}','${s.devTime}','${s.devTimeClose}','N');">自评反馈</a>
                        </c:if>
                    </td>
                    <td>
                        <a  href="javascript:void(0);" onclick="showRecords('${s.subjectFlow}','${roleFlag}');">查看</a>
                    </td>

                    <td>
                            <%-- 基地可以评分和查看--%>
                        <c:if test="${roleFlag eq 'local' || roleFlag eq 'baseExpert' }">
                            <a href="javascript:void(0);" style="width: 56px;margin-right: -100px;margin-top: -30px;"
                               onclick="baseScore('${s.speId}','${s.orgFlow}','${userFlow}','${s.subjectFlow}','${s.devTime}','${s.devTimeClose}','${s.subjectYear}','${s.subjectActivitiFlows}');">自评</a>
                            <a style="width: 56px;margin-top: 33px;" href="javascript:void(0);" onclick="showPlanInfo('${s.speId}','${s.subjectFlow}','${roleFlag}');">查看评分</a>
                        </c:if>
                            <%--专业专家：根据当前时间和项目评分的关闭时间判断是评分还是查看评分--%>
                        <c:if test="${(roleFlag ne 'baseExpert' || roleFlag eq 'expertLeader' || roleFlag eq 'management')&& s.closedTime>dateNow && (roleFlag ne 'local') }">
                            <a href="javascript:void(0);"
                               onclick="goScore('${s.speId}','${s.orgFlow}','${userFlow}','${s.subjectFlow}','${s.openTime}','${s.closedTime}','${s.subjectYear}','${s.subjectActivitiFlows}');">评分</a>
                        </c:if>
                        <c:if test="${(roleFlag eq 'expertLeader' || roleFlag eq 'management')&& (s.closedTime<dateNow)}">
                            <a style="width: 56px;" href="javascript:void(0);" onclick="showPlanInfo('${s.speId}','${s.subjectFlow}','${roleFlag}','${s.subjectActivitiFlows}');">查看评分</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        </div>
    </div>
</c:if>

<div class="page" style="text-align: right">
    <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
      
