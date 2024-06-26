<%@ taglib prefix="cP" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt " %>--%>
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

    function toPage(page) {
        var currentPage = "";
        if (!page || page != undefined) {
            currentPage = page;
        }
        if (page == undefined || page == "") {
            currentPage = 1;
        }
        $("#currentPage").val(currentPage);
        search();
    }

    //黑名单信息查询
    function search() {
        // if ($("#orgFlow").val() == "") {
        //     $("#trainOrg").val("");
        // }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/blackList/blackListInfoListAcc'/>";
        jboxPostLoad("div_table2", url, $("#searchForm").serialize(), true);
    }
    //黑名单移除
    function removeBlack(userFlow, recordStatus, recordflow) {
        jboxConfirm("确认移除该记录吗？", function () {
            var url = "<s:url value='/jsres/blackList/removeBlack?userFlow='/>" + userFlow + "&recordStatus=" + recordStatus + "&recordFlow=" + recordflow;
            jboxGet(url, null, function () {
                search();
            });
        });
    }
    //黑名单审核
    function auditBlack(recordFlow,userName,type) {
        var title = "是否将学员"+userName+"添加到黑名单？";
        if(type == 'Remove'){
            title = "是否将学员"+userName+"移除黑名单？";
        }
        jboxButtonConfirm(title,"是","否",function(){
            jboxPost("<s:url value='/jsres/blackList/auditBlack'/>",{"recordFlow":recordFlow,"auditStatusId":"Passed","type":type}, function(resp){
                setTimeout(function(){
                    search();
                },300);
            } , null , true);

        },function(){
            jboxStartLoading();
            jboxPost("<s:url value='/jsres/blackList/auditBlack'/>",{"recordFlow":recordFlow,"auditStatusId":"NotPassed","type":type}, function(resp){
                setTimeout(function(){
                    search();
                },300);
            } , null , true);

        },300);
    }
    //展示原因详情
    function showDetail(resp1, resp2) {
//	jboxConfirm(resp1);
        jboxOpenContent(resp1, "原因", 300, 100, true);
    }
    //添加黑名单
    function addBlackList(roleFlag) {
        jboxOpen("<s:url value='/jsres/blackList/addBlackList'/>?roleFlag="+roleFlag, "黑名单添加", 500, 400);
    }
    function downloadFile(recordFlow) {
        top.jboxTip("下载中…………");
        var url = "<s:url value='/jsres/blackList/downloadFile?recordFlow='/>" + recordFlow;
        window.location.href = url;
    }
    function change() {
        $("#trainOrg").val("");
    }
</script>
<body>
<div class="main_bd">
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="5%">姓名</th>
                <th width="10%">证件类型</th>
                <th width="5%">证件号</th>
                <th width="10%">原培训基地</th>
                <th width="7%">原培训届别</th>
                <th width="10%">是否为系<br/>统人员</th>
                <th>创建时间</th>
                <th width="10%">原因</th>
                <%--<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">--%>
                <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
                    <th width="10%">操作</th>
                </c:if>
            </tr>
            <c:forEach items="${blackLists}" var="black">
                <tr>
                    <td>${black.userName}</td>
                    <td>
                        <c:choose>
                            <c:when test="${black.cretTypeId=='01'}">
                                身份证
                            </c:when>
                            <c:when test="${black.cretTypeId=='02'}">
                                军官证
                            </c:when>
                            <c:when test="${black.cretTypeId=='05'}">
                                护照
                            </c:when>
                            <c:when test="${black.cretTypeId=='06'}">
                                港澳居民来往内地通行证
                            </c:when>
                            <c:when test="${black.cretTypeId=='07'}">
                                台湾居民来往内地通行证
                            </c:when>
                        </c:choose>
                    </td>
                    <td>${black.idNo}</td>
                        <%--<td>${black.orgName}</td>--%>
                    <td class="titleName" title="${black.orgName}">${pdfn:cutString(black.orgName,10,true,3) }</td>
                        <%--<td>${black.trainingSpeName}</td>--%>

                    <td>${black.sessionNumber}</td>
                    <td>
                        <c:if test="${black.isSystem ne 'N'}">是</c:if>
                        <c:if test="${black.isSystem eq 'N'}">否</c:if>
                    </td>
                    <td>${pdfn: transDateTime(black.createTime.substring(0,8))}</td>
                        <%--<td><fmt:formatDate value="${black.createTime}"  type="date" dateStyle="default"/></td>--%>
                        <%--<td>${black.createTime}</td>--%>
                        <%--<td class="titleName" title="${black.reason}">${pdfn:cutString(black.reason,1,true,3) }</td>--%>
                    <td>
                        <a href="javascript:void(0);" class="btn" onclick="showDetail('${black.reason}','${black.reasonYj}');">详情</a>
                        <c:if test="${not empty black.attachmentPath and  black.attachmentPath ne 'undefined'}">
                            <a href="javascript:void(0);" class="btn" onclick="downloadFile('${black.recordFlow}');">附件</a>
                        </c:if>
                    </td>
                    <%--<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">--%>
                    <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
                        <td>
                            <c:if test="${black.auditStatusId eq 'Passed'}">
                                已审核
                            </c:if>
                            <c:if test="${black.auditStatusId eq 'Auditing' or black.auditStatusId eq 'Remove'}">
                                <a href="javascript:void(0);" class="btn"
                                   onclick="auditBlack('${black.recordFlow}','${black.userName}','${black.auditStatusId}');">审核</a>
                            </c:if>
                        </td>
                            <%--<td>--%>
                                <%--<a href="javascript:void(0);" class="btn"--%>
                                       <%--onclick="removeBlack('${black.userFlow}','${GlobalConstant.RECORD_STATUS_N}','${black.recordFlow}');">--%>
                                <%--移除--%>
                                <%--</a>--%>
                            <%--</td>--%>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty blackLists}">
                <tr>
                    <td colspan="9">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(blackLists)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>

</body>
