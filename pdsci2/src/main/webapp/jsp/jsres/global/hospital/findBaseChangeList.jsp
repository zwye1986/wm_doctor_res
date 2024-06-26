<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
    .indexNum + div{
        z-index: 99;
        position: relative!important;
        top:0!important;
        left:0!important;
    }
    .itemDiv {
        line-height: 20px;
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
    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }
    function search() {
        <%--var url = "<s:url value='/jsres/manage/changeBase'/>";--%>
        var url = "<s:url value='/jsres/manage/changeBaseListAcc'/>";
        jboxPostLoad("div_table2", url, $("#inForm").serialize(), true);

    }
    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }

        var data="";
        $("input[class='docType']:checked").each(function(){
            data+="&datas="+$(this).val();
        });
        if(data==""){
            jboxTip("请选择人员类型！");
            return false;
        }
        search();
    }
    function audit(recordFlow, doctorFlow) {
        var url = "<s:url value='/jsres/manage/audit'/>?doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow + "";
        jboxOpen(url, "基地变更记录审核", 650, 450);
    }
    function check(obj) {
        var url = "<s:url value='/jsres/manage/changeBase'/>";

        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
            jboxPostLoad("doctorContent", url, $("#inForm").serialize(), false);
        </c:if>
        <c:if test="${GlobalConstant.USER_LIST_GLOBAL != sessionScope.userListScope}">
            jboxPostLoad("content", url, $("#inForm").serialize(), false);
        </c:if>
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#historyOrgFlow").val("");
        }
    }

    function exportList(){
        var url = "<s:url value='/jsres/manage/exportBaseListNewAcc'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#inForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
<div class="main_bd">
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="10%"/>
                <col width="15%"/>
                <col width="10%"/>
                <col width="8%"/>
                <col width="9%"/>
                <col width="8%"/>
            </colgroup>
            <thead>
            <tr>
                <th>姓名</th>
                <th>培训专业</th>
                <th>人员类型</th>
                <th>届别</th>
                <th>转入审核时间</th>
                <th>原培训基地</th>
                <th>变更后基地</th>
                <th>附件</th>
                <th>审核时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${historyExts2}" var="docOrgHistoryExt">
                <tr>
                    <td>${docOrgHistoryExt.resDoctor.doctorName}</td>
                    <td>${docOrgHistoryExt.historyTrainingSpeName}</td>
                    <td>${docOrgHistoryExt.resDoctor.doctorTypeName}</td>
                    <td>${docOrgHistoryExt.resDoctor.sessionNumber}</td>
                    <td>${pdfn:transDate(docOrgHistoryExt.inDate)}</td>
                    <td>${docOrgHistoryExt.historyOrgName}</td>
                    <td>${docOrgHistoryExt.orgName}</td>
                    <td>
                        <c:if test="${not empty docOrgHistoryExt.speChangeApplyFile}">
                            [<a href="${sysCfgMap['upload_base_url']}/${docOrgHistoryExt.speChangeApplyFile }" target="_blank">查看附件</a>]&nbsp;
                        </c:if>
                    </td>
                    <td>${pdfn:transDateTime(docOrgHistoryExt.modifyTime)}</td>
                    <td>
                        <a class="btn" style="padding:0 15px;"
                           onclick="getChangeOrgDetail('${docOrgHistoryExt.doctorFlow}','${docOrgHistoryExt.recordFlow}');">详情</a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty historyExts2 }">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(historyExts2)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
