<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript">

    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
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
    $(document).ready(function () {
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
    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }
    function search() {
        // if ($("#orgFlow").val() == "") {
        //     $("#trainOrg").val("");
        // }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/doctor/delayList'/>";
        jboxPostLoad("div_table2", url, $("#submitForm").serialize(), true);
    }
    function toPage(page) {
        var currentPage;
        if (page != undefined) {
            currentPage = page;
        }
        $("#currentPage").val(currentPage);
        search();
    }
</script>

<div>
    <div class="search_table" >
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>培训基地</th>
                <th>学员姓名</th>
                <th>培训类型</th>
                <th>培训专业</th>
                <th>届别</th>
                <th>结业考核年份</th>
                <th>延期原因</th>
                <th>附件信息</th>
                <c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
                    <th>详细</th>
                </c:if>
            </tr>
            <c:forEach items="${resRecList }" var="resRec">
                <tr>
                    <td title="${resRec.orgName}">${pdfn:cutString(resRec.orgName,6,true,3) }</td>
                    <td>${resRec.doctorName }</td>
                    <td>${resRec.trainingTypeName }</td>
                    <td>${resRec.trainingSpeName }</td>
                    <td>${resRec.sessionNumber }</td>
                    <td>${resRec.graduationYear }</td>
                    <c:set var="modifyTime" value="${pdfn:transDateTime(resRec.modifyTime)}"></c:set>
                    <c:set var="modifyTime" value="${ '  审核时间：'.concat(modifyTime)}"></c:set>
                    <c:if test="${empty resRec.delayreason}">
                        <td><label title="${modifyTime}">${pdfn:cutString(modifyTime,6,true,3) }</label></td>
                    </c:if>
                    <c:if test="${not empty resRec.delayreason}">
                        <td><label
                                title="${resRec.delayreason.concat(modifyTime)}">${pdfn:cutString(resRec.delayreason.concat(modifyTime),6,true,3) }</label>
                        </td>
                    </c:if>
                        <%--<td title="${resRec.delayreason }">${pdfn:cutString(resRec.delayreason,6,true,3)}</td>--%>
                    <td>
                        <c:if test="${not empty resRec.delayFilePath}">
                            [<a href="${sysCfgMap['upload_base_url']}/${resRec.delayFilePath}" target="_blank">查看附件</a>]
                        </c:if>
                    </td>
                    <c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
                        <td>
                            <a class="btn"
                               onclick="getChangeOrgDetail('${resRec.doctorFlow}','${resRec.recruitFlow}');">详情</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty resRecList}">
                <tr>
                    <td colspan="9">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
