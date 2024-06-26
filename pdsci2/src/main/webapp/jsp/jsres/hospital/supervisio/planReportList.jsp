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
    function setFeedback(subjectActivitiFlows,type, devTime, closedTime) {
        var url ="";
        //获取当前时间
        var currentTime = new Date();
        devTime = devTime.replace("-", "/");
        devTime = new Date(Date.parse(devTime));
        closedTime = closedTime.replace("-", "/");
        closedTime = new Date(Date.parse(closedTime));
        if (currentTime < devTime || closedTime < currentTime) {
            //只能查看
            if(type == 'spe'){
                url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectFlow="+subjectActivitiFlows+"&type="+type+"&isRead=Y";
            }
            if(type == 'major'){
                url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectActivitiFlows="+subjectActivitiFlows+"&type="+type+"&isRead=Y";
            }
            jboxOpen(url,"报告", 1000, 800);;;
        } else {
            if(type == 'spe'){
                url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectFlow="+subjectActivitiFlows+"&type="+type;
            }
            if(type == 'major'){
                url ="<s:url value='/jsres/supervisio/expertMajor'/>?roleFlag=${roleFlag}&suAoth=${suAoth}"+"&subjectActivitiFlows="+subjectActivitiFlows+"&type="+type;
            }
            jboxOpen(url,"报告", 1000, 800);
        }


    }
    function downFeedback(fileUrl,type) {
        jboxTip("下载中…………");
        var url = "<s:url value='/jsres/supervisio/downFeedbackFile'/>?fileUrl=" + fileUrl + "&type=" + type;
        window.location.href = url;
    }
</script>

<c:if test="${empty list}">
    <div class="search_table" style="width: 100%;padding: 0px 20px">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th >基地代码</th>
                <th>检查基地</th>
                <th >检查年份</th>
                <th>基地自评反馈</th>
            </tr>
            <tr>
                <td colspan="4">无记录！</td>
            </tr>
        </table>
    </div>
</c:if>
<c:if test="${not empty list}">
    <div class="main_bd clearfix" style="width: 100%;padding: 0px 20px">
        <table id="dataTable" border="0" cellpadding="0" cellspacing="0" class="grid">
            <thead>
            <tr>
                <th width="150px">项目名称</th>
                <th width="150px">基地代码</th>
                <th>检查基地</th>
                <th>检查年份</th>
                <th>基地自评反馈</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="s">
                <tr class="fixTrTd">
                    <td>${s.subjectName}</td>
                    <td>${s.baseCode}</td>
                    <td>${s.orgName}</td>
                    <td>${s.subjectYear}</td>
                    <td>
                        <a class="btn_blue" style="width: 56px;color: white;margin-top: -1px" href="javascript:void(0);"
                           onclick="setFeedback('${s.subjectActivitiFlows}','major','${s.devTime}','${s.devTimeClose}');">自评反馈</a>
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
      
