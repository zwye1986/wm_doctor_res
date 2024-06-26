<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript">

    function checkAll(obj){
        var f=false;
        if($(obj).attr("checked")=="checked")
        {
            f=true;
        }
        $(".docType").each(function(){
            if(f)
            {
                $(this).attr("checked","checked");
            }else {
                $(this).removeAttr("checked");
            }

        });
    }
    function changeAllBox(){
        if($(".docType:checked").length==$(".docType").length)
        {
            $("#all").attr("checked","checked");
        }else{
            $("#all").removeAttr("checked");
        }
    }

    function toPage(page) {
        var currentPage = "";
        if (!page || page != undefined) {
            currentPage = page;
        }
        if (page == undefined || page == "") {
            currentPage = 1;
        }
        $("#currentPage").val(currentPage);
        searchRecInfo();
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }
    function searchRecInfo() {
        if ($("#orgFlow").val() == "") {
            $("#trainOrg").val("");
        }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/doctor/backTrainInfoListAcc?roleFlag=global'/>";
        jboxPostLoad("div_table2", url, $("#searchForm").serialize(), true);
    }
    function exportExcel() {
        var url = "<s:url value='/jsres/doctor/exportForBackNewAcc'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    function change() {
        $("#trainOrg").val("");
    }
    //上传退培附件
    function uploadFile(recordFlow) {
        if (${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}) {
            jboxTip("退培附件由基地上传！");
            return;
        }
        var url = "<s:url value='/jsres/doctor/showUpload?recordFlow='/>" + recordFlow;
        typeName = "上传退培附件";
        jboxOpen(url, typeName, 500, 260);
    }
    function doctorPassedList(doctorFlow,recruitFlow){
        var hideApprove="hideApprove";
        var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?isRetrunShow=Y&info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
        jboxOpen(url,"学员信息",1050,550);
    }
</script>
<body>
<div class="main_bd">
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="10%"/>
                <col width="9%"/>
                <col width="9%"/>
                <col width="9%"/>
            </colgroup>
            <tr>
                <th>培训基地</th>
                <th>培训届别</th>
                <th>学员姓名</th>
                <th>培训专业</th>
                <th>退培主要<br/>原因</th>
                <th>退培类型</th>
                <th>学员去向</th>
                <th>备注(培训基地意见)</th>
                <th>学员提交时间</th>
                <th>附件</th>
                <th>审核状态</th>
                <th>省厅意见</th>
            </tr>
            <c:forEach items="${resRecList}" var="rec">
                <tr>
                    <td title="${rec.orgName}">${pdfn:cutString(rec.orgName,4,true,3) }</td>
                    <td>${rec.sessionNumber}</td>
                    <td><a style="color:#1e7db3;" href="javascript:void(0);" onclick="doctorPassedList('${rec.doctorFlow}','${rec.recruitFlow}');">${rec.doctorName}</a></td>
                    <td title="${rec.trainingSpeName}">${pdfn:cutString(rec.trainingSpeName,7,true,3) }</td>
                    <td>${rec.reasonName}
                        <label title="${rec.reason}">
                            <c:if test="${not empty rec.reason }">
                                (${pdfn:cutString(rec.reason,5,true,3) })
                            </c:if>
                        </label>
                    </td>
                    <td>${rec.policyName}
                        <label title="${rec.policy }">
                            <c:if test="${not empty rec.policy }">
                                (${pdfn:cutString(rec.policy,5,true,3) })
                            </c:if>
                        </label>
                    </td>
                    <td><label title="${rec.dispositon}">${pdfn:cutString(rec.dispositon,5,true,3) }</label></td>
                    <td><label title="${rec.remark}">${pdfn:cutString(rec.remark,5,true,3) }</label></td>
                    <td><label title="${pdfn: transDateTime(rec.createTime)}">${pdfn:cutString(pdfn: transDateTime(rec.createTime),5,true,5)}</label></td>
                    <td>
                        <c:forEach items="${fileMaps[rec.recordFlow]}" var="file">
                            [<a target="_blank" href="${sysCfgMap['upload_base_url']}${file.filePath}">查看</a>]</br>
                            <%--[<a target="_blank" href="<s:url value='/res/doc/fileDown'/>?fileFlow=${file.fileFlow}">下载</a>]--%>
                            <%--</br>--%>
                        </c:forEach>
                        <c:if test="${empty fileMaps[rec.recordFlow]}">
                            暂未上传附件！
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${'2' ne rec.auditStatusId and '1' ne rec.auditStatusId and '0' ne rec.auditStatusId }">
                            <label title="${rec.auditStatusName}">
                                    ${pdfn:cutString(rec.auditStatusName,7,true,3) }
                            </label>
                        </c:if>
                        <c:if test="${'2' eq rec.auditStatusId}">
                            <label title="待省厅审核">
                                    ${pdfn:cutString('待省厅审核',7,true,3) }
                            </label>
                        </c:if>
                        <c:if test="${'0' eq rec.auditStatusId}">
                            <label title="省厅审核不通过">
                                    ${pdfn:cutString('省厅审核不通过',7,true,3) }
                            </label>
                        </c:if>
                        <c:if test="${'1' eq rec.auditStatusId}">
                            <label title="省厅审核通过">
                                    ${pdfn:cutString('省厅审核通过',7,true,3) }
                            </label>
                        </c:if>
                    </td>
                    <%--<c:set var="modifyTime" value="${pdfn:transDateTime(rec.modifyTime)}"></c:set>--%>
                    <c:set var="modifyTime" value="${pdfn:transDateTime(rec.auditTime)}"></c:set>
                    <c:set var="modifyTime" value="${ '  审核时间：'.concat(modifyTime)}"></c:set>
                    <c:if test="${empty rec.auditOpinion}">
                        <td><label title="${modifyTime}">${pdfn:cutString(modifyTime,10,true,3) }</label></td>
                    </c:if>
                    <c:if test="${not empty rec.auditOpinion}">
                        <td><label
                                title="${rec.auditOpinion.concat(modifyTime)}">${pdfn:cutString(rec.auditOpinion.concat(modifyTime),10,true,3) }</label>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${(not empty resRecList and GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope and (not empty param.orgFlow and not empty param.sessionNumber))or(not empty resRecList and GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope and (not empty param.orgFlow and not empty param.sessionNumber))or(GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and not empty param.sessionNumber and not empty resRecList)}">
                <tr>
                    <td colspan="11" style="text-align: center;">合计退培：${resRecListSize}&#12288;退培比例：${percent}</td>
                </tr>
            </c:if>
            <c:if test="${empty resRecList}">
                <tr>
                    <td colspan="11">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope}">
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </c:if>
</div>
</body>
