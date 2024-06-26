<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>

<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<link rel="stylesheet" type="text/css" href="<s:url value='/css/jquery.step.css'/>" />
<script type="text/javascript" src="<s:url value='/js/jquery.step.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script type="text/javascript">
// 审核进度条
<%--    <c:forEach items="${kqList}" var="kq" varStatus="status">--%>
<%--        var titles = [];--%>
<%--        titles.push("待带教审核");--%>
<%--        if("-" != "${kq.headName}"){--%>
<%--            titles.push("待科主任审核");--%>
<%--        }--%>
<%--        if("-" != "${kq.managerName}"){--%>
<%--            titles.push("待管理员审核");--%>
<%--        }--%>
<%--        titles.push("审核完成");--%>

<%--        var id = "step${status.index+1}";--%>
<%--        var step1 = $("#"+id);--%>
<%--        var indexStep = 0;--%>
<%--        if("ManagerPass" == "${kq.auditStatusId}" || "ManagerUnPass" == "${kq.auditStatusId}"){--%>
<%--            indexStep = titles.length - 1;--%>
<%--        }--%>

<%--        step1.step({--%>
<%--            index1: indexStep,--%>
<%--            title: titles--%>
<%--        });--%>
<%--    </c:forEach>--%>

    // $(document).ready(function(){
    //     $(".showInfo").on("mouseenter mouseleave",
    //         function(){
    //             $(".theInfo",this).toggle(100);
    //         }
    //     );
    // });

    function downImg(recordFlow) {
        var url='<s:url value="/jsres/statistic/downImg?recordFlow="/>'+recordFlow;
        window.location.href=url;
    }

    function showFiles(recordFlow) {
        var title = "附件信息";
        var url = "<s:url value='/jsres/attendanceNew/showFilws'/>?recordFlow=" + recordFlow;
        jboxOpen(url, title, 500, 460);
    }

    //撤回
    function backLeave(recordFlow,auditStatusId) {
        var title = "请假申请";
        if(auditStatusId == 'Revokeing'){
            title = "销假申请";
        }
        jboxConfirm("确认撤回"+title+"？",function(){
            var url = "<s:url value='/jsres/attendanceNew/backLeave'/>?recordFlow="+recordFlow+"&auditStatusId="+auditStatusId;
            jboxPost(url,null,function(resp){
                if('${GlobalConstant.OPRE_SUCCESSED_FLAG}'==resp){
                    jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
                    toPage(1);
                }
                if('${GlobalConstant.OPRE_FAIL_FLAG}'==resp){
                    jboxTip("${GlobalConstant.OPRE_FAIL}");
                }
                if('${GlobalConstant.OPRE_FAIL_FLAG}'!=resp&&'${GlobalConstant.OPRE_SUCCESSED_FLAG}'!=resp) {
                    jboxTip(resp);
                }
            },null,false);
        },null);
    }

    //销假
    function cancelLeave(recordFlow) {
        jboxConfirm("确认销假？",function(){
            var url = "<s:url value='/jsres/attendanceNew/cancelLeave'/>?recordFlow="+recordFlow;
            jboxPost(url,null,function(resp){
                if('${GlobalConstant.OPRE_SUCCESSED_FLAG}'==resp){
                    jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
                    toPage(1);
                }
                if('${GlobalConstant.OPRE_FAIL_FLAG}'==resp){
                    jboxTip("${GlobalConstant.OPRE_FAIL}");
                }
                if('${GlobalConstant.OPRE_FAIL_FLAG}'!=resp&&'${GlobalConstant.OPRE_SUCCESSED_FLAG}'!=resp) {
                    jboxTip(resp);
                }
            },null,false);
        },null);
    }

    // 查看审核进度
    function searchAudit(recordFlow) {
        var url = "<s:url value='/jsres/attendanceNew/searchAudit'/>?recordFlow=" + recordFlow;
        jboxOpen(url, "审核进度", 850, 600);
    }

</script>

<div class="main_bd">
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="5%">序号</th>
                <th width="8%">请假类型</th>
                <th width="8%">轮转科室</th>
                <th width="15%">请假事由</th>
                <th width="15%">请假时间</th>
                <th width="8%">请假天数</th>
                <th width="9%">附件</th>
                <th width="16%">请假状态</th>
                <th width="8%">审核详情</th>
                <th width="8%">操作</th>
            </tr>
            <c:forEach items="${kqList}" var="kq" varStatus="status">
                <tr >
                    <td style="max-width: 5%">${status.index+1}</td>
                    <td style="max-width: 8%">${kq.typeName }</td>
                    <td style="max-width: 8%">${kq.schDeptName }</td>
                    <td style="max-width: 15%" title="${kq.doctorRemarks}">${pdfn:cutString(kq.doctorRemarks,10,true,3)}</td>
                    <td style="max-width: 15%">${kq.startDate}<br/>${kq.endDate}</td>
                    <td style="max-width: 8%">${kq.intervalDays}</td>
                    <td style="max-width: 9%">
                        <c:if test="${kq.fileFlows eq 'N'}">无</c:if>
                        <c:if test="${kq.fileFlows eq 'Y'}">
                            <a href="javascript:void(0);" onclick="showFiles('${kq.recordFlow}');">查看附件</a>
                        </c:if>
                    </td>
                    <td class="showInfo" style="max-width: 16%">
                        ${kq.auditStatusName}
<%--                        <div style="height:auto;display: none;position: relative;width: 350px;" class="theInfo">--%>
<%--                            <table class="grid" style="background: white;width: 350px;">--%>
<%--                                <tr>--%>
<%--                                    <td>--%>
<%--                                        <div id="step${status.index+1}" style="width: 340px"></div>--%>
<%--                                    </td>--%>
<%--                                </tr>--%>
<%--                            </table>--%>
<%--                        </div>--%>
                    </td>
                    <td style="max-width: 8%">
                        <a href="javascript:void(0);" onclick="searchAudit('${kq.recordFlow}');">查看</a>
                    </td>
                    <td style="max-width: 8%">
                        <c:if test="${kq.auditStatusId ne 'ManagerPass' and kq.auditStatusId ne 'ManagerUnPass'
                                and kq.auditStatusId ne 'RevokeManagerPass' and kq.auditStatusId ne 'BackLeave'}">
                            <a href="javascript:void(0);" onclick="backLeave('${kq.recordFlow}','${kq.auditStatusId}');">撤回</a>
                        </c:if>
                        <c:if test="${kq.auditStatusId eq 'ManagerPass'}">
                            <a href="javascript:void(0);" onclick="cancelLeave('${kq.recordFlow}');">销假</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty kqList}">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(kqList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>