<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style type="text/css">
    .itemDiv {
        line-height: 20px;
    }
</style>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $.checkYear("sessionNumber","",null);
//        $('#sessionNumber').datepicker({
//            startView: 2,
//            maxViewMode: 2,
//            minViewMode: 2,
//            format: 'yyyy'
//        });
        if ("${baseFlag}" == "0") {
            $("#baseFlag").hide();
        }
        if("${baseFlag}"!="0"){
            $("#baseFlag").show();
        }
        <c:forEach items="${jsResDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if("${data}"=="${type.id}"){
            $("#"+"${data}").attr("checked","checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#"+"${type.id}").attr("checked","checked");
        </c:if>
        </c:forEach>
    });

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
    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }

    function auditOrgChange(recordFlow, auditFlag, doctorFlow) {
        var title = "不通过";
        var changeStatusId = "${jsResChangeApplyStatusEnumGlobalApplyUnPass.id}";//省厅审核不通过
        if ("${GlobalConstant.FLAG_Y}" == auditFlag) {
            title = "通过";
            changeStatusId = "${jsResChangeApplyStatusEnumGlobalApplyPass.id}";
        }
        if (title == "不通过") {
            var url = "<s:url value='/jsres/manage/auditInfoGlobal'/>?recordFlow=" + recordFlow + "&changeStatusId=" + changeStatusId + "&doctorFlow=" + doctorFlow;
            jboxOpen(url, "审核意见", 680, 400);
        } else {
            <%--var url = "<s:url value='/jsres/manage/info'/>?recordFlow=" + recordFlow + "&changeStatusId=" + changeStatusId + "&doctorFlow=" + doctorFlow;--%>
            <%--jboxOpen(url, "相关信息", 600, 280);--%>
            var url = "<s:url value='/jsres/manage/turnOutOrgGlobal'/>?recordFlow="+recordFlow +"&changeStatusId="+changeStatusId+"&doctorFlow="+doctorFlow;
            jboxConfirm("确认通过该条记录?",  function(){
                jboxPost(url, null, function(resp){
                    if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
                        window.parent.search();
                        top.jboxTip("操作成功！");
                        jboxClose();
                    }else{
                        top.jboxTip("操作失败！");
                        jboxClose();
                    }
                }, null, false);
            });
        }
    }

    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        search();
    }
    function search() {
        var url = "<s:url value='/jsres/manage/auditBaseChange'/>";
        jboxPostLoad("doctorContent", url, $("#inForm").serialize(), false);
    }
</script>
<%--<div class="main_hd">--%>
    <%--<h2 class="underline">基地变更审核</h2>--%>
<%--</div>--%>
<div class="main_bd">
    <div class="div_search">
        <form id="inForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">姓&#12288;&#12288;名：</td>
                    <td>
                        <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                    </td>
                    <td class="td_left">届&#12288;&#12288;别：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                    </td>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="7%"/>
                <col width="5%"/>
                <col width="7%"/>
                <col width="15%"/>
                <col width="13%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="30%"/>
            </colgroup>
            <thead>
            <tr>
                <th>姓名</th>
                <th>届别</th>
                <th>人员类型</th>
                <th>原培训基地</th>
                <th>审核状态</th>
                <th>审核时间</th>
                <th>附件</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${docOrgHistoryExtList }" var="docOrgHistoryExt">
                <tr>
                    <td>${docOrgHistoryExt.resDoctor.doctorName}</td>
                    <td>${docOrgHistoryExt.resDoctor.sessionNumber}</td>
                    <td>${docOrgHistoryExt.resDoctor.doctorTypeName}</td>
                    <td>${docOrgHistoryExt.historyOrgName}</td>
                        <%-- <td>${docOrgHistoryExt.orgName}</td> --%>
                    <td>${docOrgHistoryExt.changeStatusName}</td>
                    <td>${pdfn:transDate(docOrgHistoryExt.inDate)}</td>
                    <td>
                        <c:if test="${not empty docOrgHistoryExt.speChangeApplyFile }">
                            [<a href="${sysCfgMap['upload_base_url']}/${docOrgHistoryExt.speChangeApplyFile }" target="_blank">查看附件</a>]
                        </c:if>
                    </td>
                    <td>
                        <a class="btn"
                           onclick="getChangeOrgDetail('${docOrgHistoryExt.doctorFlow}','${docOrgHistoryExt.recordFlow}');">详情</a>
                        <c:if test="${jsResChangeApplyStatusEnumGlobalApplyWaiting.id eq docOrgHistoryExt.changeStatusId}">
                            <a class="btn audit"
                               onclick="auditOrgChange('${docOrgHistoryExt.recordFlow}','${GlobalConstant.FLAG_Y}','${docOrgHistoryExt.doctorFlow}')">通过</a>
                            <a class="btn audit"
                               onclick="auditOrgChange('${docOrgHistoryExt.recordFlow}','${GlobalConstant.FLAG_N}','${docOrgHistoryExt.doctorFlow}')">不通过</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty docOrgHistoryExtList }">
                <tr>
                    <td colspan="20">无记录</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(docOrgHistoryExtList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
