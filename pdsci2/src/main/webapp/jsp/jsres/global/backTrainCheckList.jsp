<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script>

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
    function searchRecInfo() {
        if ($("#orgFlow").val() == "") {
            $("#trainOrg").val("");
        }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/doctor/showBackCheckList'/>";
        jboxPostLoad("div_table2", url, $("#searchForm").serialize(), true);
    }
    function exportExcel() {
        <%--var url = "<s:url value='/jsres/doctor/exportForBack?auditStatusId=2'/>";--%>
        var url = "<s:url value='/jsres/doctor/exportForBackNew?auditStatusId=2'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    function showCheck(recordFlow) {
        jboxOpen("<s:url value='/jsres/doctor/showCheck?recordFlow='/>" + recordFlow, "退培医师审核", 500, 300);
    }
    function doctorPassedList(doctorFlow,recruitFlow){
        var hideApprove="hideApprove";
        var url = "<s:url value='/jsres/manage/province/doctor/doctorPassedList'/>?isRetrunShow=Y&info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove;
        jboxOpen(url,"学员信息",1050,550);
    }
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
</script>
<div class="main_bd">
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>培训基地</th>
                <th>培训届别</th>
                <th>学员姓名</th>
                <th>培训专业</th>
                <th>退培主要原因</th>
                <th>退培类型</th>
                <th>学员去向</th>
                <th>备注(培训基地意见)</th>
                <th>学员提交时间</th>
                <th style="width: 80px;">附件</th>
<c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
                <th style="width: 50px;">操作</th>
</c:if>
            </tr>
            <c:forEach items="${resRecList}" var="rec">
                <tr>
                    <td title="${rec.orgName}">${pdfn:cutString(rec.orgName,6,true,3) }</td>
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
                    <c:set var="policyTime" value="${pdfn:transDateTime(rec.policyTime)}"></c:set>
                    <c:set var="policyTime" value="${ '  审核时间：'.concat(policyTime)}"></c:set>
                    <c:if test="${empty rec.remark}">
                        <td><label title="${policyTime}">${pdfn:cutString(policyTime,10,true,3) }</label></td>
                    </c:if>
                    <c:if test="${not empty rec.remark}">
                        <td><label
                                title="${rec.remark.concat(policyTime)}">${pdfn:cutString(rec.remark.concat(policyTime),5,true,3) }</label>
                        </td>
                    </c:if>
                        <%--<td><label title="${rec.remark}">${pdfn:cutString(rec.remark,5,true,3) }</label></td>--%>
                    <td>${pdfn: transDateTime(rec.createTime)}</td>
                    <td>
                        <c:forEach items="${fileMaps[rec.recordFlow]}" var="file">
                            <%--[<a target="_blank" href="<s:url value='/res/doc/fileDown'/>?fileFlow=${file.fileFlow}">查看附件</a>]--%>
                            [<a target="_blank" href="${sysCfgMap['upload_base_url']}${file.filePath}">查看</a>]</br>
                        </c:forEach>
                        <c:if test="${empty fileMaps[rec.recordFlow]}">
                            暂未上传附件！
                        </c:if>
                    </td>
                    <c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
                    <td>

                        <a class="btn" href="javascript:void(0)" onclick="showCheck('${rec.recordFlow}')">审&nbsp;核</a>
                            <%--<input class="btn_green" type="button" onclick="showCheck('${rec.recordFlow}')" value="审&nbsp;核"/>--%>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty resRecList}">
                <tr>
                    <td colspan="10">无记录</td>
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

