<%@ taglib prefix="cP" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt " %>--%>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript"
        src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
    });
    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus", function () {
                var boxHome = $("#" + spaceId + "Sel");
                boxHome.show();
                var pDiv = $(boxHome).parent();
                $(pDiv).css("left", $("#train").outerWidth());
                var w = $(this).css("marginTop").replace("px", "");
                w = w - 0 + $(this).outerHeight() + 6 + "px";
                $(pDiv).css("top", w);
                if ($.trim(this.value)) {
                    $("p." + spaceId + ".item", boxHome).hide();
                    var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
                    if (!items) {
                        boxHome.hide();
                    }
                    changeOrgFlow(this);
                } else {
                    $("p." + spaceId + ".item", boxHome).show();
                }
            });
            searchInput.on("blur", function () {
                var boxHome = $("#" + spaceId + "Sel");
                if (!$("." + spaceId + ".boxHome.on").length) {
                    boxHome.hide();
                }
            });
            $("." + spaceId + ".boxHome").on("mouseenter mouseleave", function () {
                $(this).toggleClass("on");
            });

            $("." + spaceId + ".boxHome .item").click(function () {
                var boxHome = $("." + spaceId + ".boxHome");
                boxHome.hide();
                var value = $(this).attr("value");
                var input = $("#" + spaceId);
                input.val(value);
                if (option.clickActive) {
                    option.clickActive($(this).attr("flow"));
                    $("#orgFlow").val($(this).attr("flow"));
                }
            });
        };
    })(jQuery);
    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
    }
    $(function () {
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
    });
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
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }
    //黑名单信息查询
    function search() {
        if ($("#orgFlow").val() == "") {
            $("#trainOrg").val("");
        }
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        var url = "<s:url value='/jsres/blackList/blackListInfo'/>";
        jboxPostLoad("content", url, $("#searchForm").serialize(), false);
    }
    //黑名单移除
    function removeBlack(userFlow, auditStatusId, recordflow) {
        jboxConfirm("确认移除该记录吗？", function () {
            var url = "<s:url value='/jsres/blackList/removeBlackNew?userFlow='/>" + userFlow + "&auditStatusId=" + auditStatusId + "&recordFlow=" + recordflow;
            jboxGet(url, null, function () {
                search();
            });
        });
    }
    //展示原因详情
    function showDetail(resp1, resp2) {
//	jboxConfirm(resp1);
        jboxOpenContent(resp1, "原因", 300, 100, true);
    }
    //添加黑名单
    function addBlackList(roleFlag) {
        debugger;
        var url = "<s:url value='/jsres/blackList/addBlackList'/>?roleFlag="+roleFlag;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='500px' height='450px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'黑名单添加',500,450,null,false);
        <%--jboxOpen("<s:url value='/jsres/blackList/addBlackList'/>?roleFlag="+roleFlag, "黑名单添加", 500, 400);--%>
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
<c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y}">
    <div class="main_hd">
        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">
              <h2 class="underline">黑名单管理</h2>
        </c:if>
        <c:if test="${!(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope)}">
            <h2 class="underline">黑名单查询</h2>
        </c:if>
    </div>
</c:if>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value=""/>
            <c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y }">
                <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
                    <tr>
                        <td class="td_left">
                            <nobr>原培训年级：</nobr>
                        </td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value=""
                                   class="input" readonly="readonly" style="width: 135px;margin-left: -30px"/>
                        </td>
                        <td class="td_left">
                            <nobr style="margin-left: 25px">&#12288;培训专业：</nobr>
                        </td>
                        <td>
                            <input type="text" value="${param.trainingSpeName}" class="input" name="trainingSpeName" style="width: 160px;margin-left: -41px"/>
                        </td>
                        <c:if test="${JointOrgCount ne '0'}">
                            <td class="td_left">
                                <nobr style="margin-right: -39px;margin-left: 34px">培训基地：</nobr>
                            </td>
                            <td>
                                <select class="select" name="orgFlow0" style="width: 170px;margin-left: 17px;margin-right: -32px;" onchange="searchDeptList(this.value)">
                                    <option value="all" <c:if test="${orgFlow eq 'all'}">selected="selected"</c:if>>全部</option>
                                    <c:forEach items="${orgList}" var="org">
                                        <option value="${org.orgFlow}" <c:if test="${orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <nobr>姓&#12288;&#12288;&#12288;名：</nobr>
                        </td>
                        <td>
                            <input type="text" value="${param.userName}" class="input" name="userName" style="width: 135px; margin-left: -30px" />
                        </td>
                        <td class="td_left">
                            <nobr style="margin-left: 26px;">证件号：</nobr>
                        </td>
                        <td>
                            <input type="text" value="${param.idNo}" class="input" name="idNo" style="width: 160px;margin-left: -41px" />
                        </td>

                        <td colspan="6">
                            <input class="btn_green" type="button" onclick="search()" style="margin-left: 35px;margin-right: -8px" value="查&#12288;询"/>&#12288;
                            <c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope || GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">
                                <input class="btn_green" type="button" style="margin-right: -41px;margin-left: 21px;" onclick="addBlackList('local')" value="新&#12288;增"/>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </c:if>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th width="7%">姓名</th>
                <th width="7%">证件类型</th>
                <th width="10%">证件号</th>
                <th width="10%">原培训<br/>基地</th>
                <th width="7%">原培训<br/>专业</th>
                <th width="10%">原培训<br/>届别</th>
                <th width="10%">是否为系<br/>统人员</th>
                <th width="8%">创建时间</th>
                <th width="10%">原因</th>
                <c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope}">
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
                    <td class="titleName"
                        title="${black.trainingSpeName}">${pdfn:cutString(black.trainingSpeName,6,true,3) }</td>
                    <td>${black.sessionNumber}</td>
                    <td>
                        <c:if test="${black.isSystem ne 'N'}">是</c:if>
                        <c:if test="${black.isSystem eq 'N'}">否</c:if>
                    </td>
                    <td>${pdfn: transDateTime(black.createTime.substring(0,8))}</td>
                        <%--<td><fmt:formatDate value="${black.createTime}"  type="date" dateStyle="default"/></td>--%>
                        <%--<td>${black.createTime}</td>--%>
                        <%--<td class="titleName" title="${black.reason}">${pdfn:cutString(black.reason,1,true,3) }</td>--%>
                    <%--<td>${black.auditStatusName}</td>--%>
                    <td>
                        <a href="javascript:void(0);" class="btn" onclick="showDetail('${black.reason}','${black.reasonYj}','${black.attachmentPath}');">详情</a>
                        <c:if test="${not empty black.attachmentPath and  black.attachmentPath ne 'undefined'}">
                            <a href="javascript:void(0);" class="btn" onclick="downloadFile('${black.recordFlow}');">附件</a>
                        </c:if>
                    </td>
                    <%--<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">--%>
                    <c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope}">
                        <td>
                            <c:if test="${black.auditStatusId eq 'Auditing' or black.auditStatusId eq 'Remove'}">
                                    省厅审核中
                            </c:if>
                            <c:if test="${black.auditStatusId eq 'Passed'}">
                                <a href="javascript:void(0);" class="btn"
                                   onclick="removeBlack('${black.userFlow}','Remove','${black.recordFlow}');">
                                    移除
                                </a>
                            </c:if>
                        </td>
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
