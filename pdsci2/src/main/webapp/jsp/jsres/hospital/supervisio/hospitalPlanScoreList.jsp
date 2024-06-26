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

    function scoreSchedule(subjectFlow,speId,edit) {
        var url = "<s:url value ='/jsres/supervisio/hospitalSchedule'/>?subjectFlow=" +subjectFlow+"&speId="+speId+"&edit="+edit;
        top.jboxOpen(url, "评分表", 1100, 670);
    }

    function hospitalSubjectInfo(subjectFlow) {
        var url = "<s:url value ='/jsres/supervisio/hospitalSubjectInfo'/>?subjectFlow=" + subjectFlow;
        var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
        jboxMessager(iframe, "项目详情", 1000, 200, false);
    }
    function showActivity(activityFlow) {
        jboxOpen("<s:url value='/jsres/activityQuery/showActivity'/>?activityFlow=" + activityFlow,'活动详情',800,500);
    }
</script>

<c:if test="${empty list}">
    <div class="search_table" style="width: 100%;padding: 0px 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>活动名称</th>
                <th>专家名称</th>
                <th>检查类型</th>
                <th width="80px">检查专业</th>
                <th width="100px">检查年份</th>
                <th>得分</th>
                <th>操作</th>
            </tr>
            <tr>
                <td colspan="7">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix" style="width: 100%;padding: 0px 20px">
        <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
            <thead>
            <tr>
                <th>活动名称</th>
                <th>专家名称</th>
                <th>检查类型</th>
                <th width="80px">检查专业</th>
                <th width="100px">检查年份</th>
                <th>得分</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="s">
                <tr class="fixTrTd">
                    <td><a onclick="showActivity('${s.activityFlow}')"> ${s.activityName}</a></td>
                    <td>
                            ${s.leaderOneName}<c:if test="${not empty s.leaderTwoName}">、${s.leaderTwoName}</c:if>
                        <c:if test="${not empty s.leaderThreeName}">、${s.leaderThreeName}</c:if>
                        <c:if test="${not empty s.leaderFourName}">、${s.leaderFourName}</c:if>
                    </td>
                    <td>
                        <c:forEach items="${activityTypeEnumList}" var="dict">
                            <c:if test="${s.inspectionType eq dict.id}">${dict.name}</c:if>
                        </c:forEach>
                    </td>
                    <td>${s.speName}</td>
                    <td>${s.subjectYear}</td>
                    <td>${s.avgScore == null ? "暂无":s.avgScore }</td>
                    <td>
                        <c:if test="${userName eq s.leaderOneName}">
                            <c:if test="${nowTime > s.activityEndTime and  not empty s.leaderOneEndTime}">
                                <a class="btn_green" href="javascript:void(0);"style="color: white" onclick="scoreSchedule('${s.subjectFlow}','${s.speId}','N');">查看</a>
                            </c:if>
                            <c:if test="${nowTime < s.activityEndTime || empty s.leaderOneEndTime}">
                                <a class="btn_green" href="javascript:void(0);"style="color: white" onclick="scoreSchedule('${s.subjectFlow}','${s.speId}','Y');">评分</a>
                            </c:if>
                        </c:if>

                        <c:if test="${userName eq s.leaderTwoName}">
                            <c:if test="${nowTime > s.activityEndTime and  not empty s.leaderTwoEndTime}">
                                <a class="btn_green" href="javascript:void(0);"style="color: white" onclick="scoreSchedule('${s.subjectFlow}','${s.speId}','N');">查看</a>
                            </c:if>

                            <c:if test="${nowTime < s.activityEndTime || empty s.leaderTwoEndTime}">
                                <a class="btn_green" href="javascript:void(0);"style="color: white" onclick="scoreSchedule('${s.subjectFlow}','${s.speId}','Y');">评分</a>
                            </c:if>
                        </c:if>

                        <c:if test="${userName eq s.leaderThreeName}">
                            <c:if test="${nowTime > s.activityEndTime and  not empty s.leaderThreeEndTime}">
                                <a class="btn_green" href="javascript:void(0);"style="color: white" onclick="scoreSchedule('${s.subjectFlow}','${s.speId}','N');">查看</a>
                            </c:if>

                            <c:if test="${nowTime < s.activityEndTime || empty s.leaderThreeEndTime}">
                                <a class="btn_green" href="javascript:void(0);"style="color: white" onclick="scoreSchedule('${s.subjectFlow}','${s.speId}','Y');">评分</a>
                            </c:if>
                        </c:if>

                        <c:if test="${userName eq s.leaderFourName}">
                            <c:if test="${nowTime > s.activityEndTime and  not empty s.leaderFourEndTime}">
                                <a class="btn_green" href="javascript:void(0);"style="color: white" onclick="scoreSchedule('${s.subjectFlow}','${s.speId}','N');">查看</a>
                            </c:if>

                            <c:if test="${nowTime < s.activityEndTime || empty s.leaderFourEndTime}">
                                <a class="btn_green" href="javascript:void(0);"style="color: white" onclick="scoreSchedule('${s.subjectFlow}','${s.speId}','Y');">评分</a>
                            </c:if>
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
      
