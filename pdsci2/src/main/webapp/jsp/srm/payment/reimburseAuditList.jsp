<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>

    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="treetable" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript">
        function doAudit(fundDetailFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/payment/audit/finance?fundDetailFlow="/>" + fundDetailFlow, "审核", 750, 500);
        }
        function search() {
            jboxStartLoading();
            $("#expandFundFlow").val();
            $('#searchForm').submit();
        }
        function toPage(page){
            var form = $("#searchForm");
            $("#currentPage").val(page);
            search();
        }
        function initDept() {
            var datas = [];
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    var d = {};
                    d.id = n.deptFlow;
                    d.text = n.deptName;
                    datas.push(d);
                });
            }, null, false);
            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }

        $(document).ready(function () {
            initDept();
        });
    </script>
    <style type="text/css">
        .basic tbody th{
            text-align: center;
        }
        .basic tbody td{
            text-align: center;
        }
        .basic a{
            color: #00a0ff;
        }
        a:hover{
            color: #ff821a;
        }
    </style>
</head>
<body>
<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/payment/reimburseAuditList"/>" method="post">
                <input type="hidden" id="expandFundFlow" name="expandFundFlow">
                <input type="hidden" id="currentPage" name="currentPage">
                <div class="searchDiv">
                    &#12288;科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="proj.applyDeptName" type="text"
                           value="${fundDetailExt.proj.applyDeptName}" autocomplete="off"/>
                    <input id="deptFlow" name="proj.applyDeptFlow" class="input" value="${fundDetailExt.proj.applyDeptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <div class="searchDiv">
                    项目来源：
                    <select id="projDeclarerFlow" name="proj.projDeclarerFlow" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                            <option dictFlow="${dict.dictFlow}" value="${dict.dictId}" <c:if test="${fundDetailExt.proj.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    &#12288;课题编号：
                    <input class="xltext" name="proj.projNo" type="text"
                           value="${fundDetailExt.proj.projNo}"/>
                </div>
                <div class="searchDiv">
                    &#12288;课题账号：
                    <input class="xltext" name="proj.accountNo" type="text"
                           value="${fundDetailExt.proj.accountNo}"/>
                </div>

                <div class="searchDiv">
                    &#12288; 负 责 人：
                    <input class="xltext"  name="proj.applyUserName" type="text"
                           value="${fundDetailExt.proj.applyUserName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;
                    <input class="search" type="button" value="查&#12288;询" onclick="search()"/>
                </div>
            </form>
        </div>

        <table class="basic" style="width: 100%">

                    <tr>
                        <th width="5%">序号</th>
                        <th width="7%">课题账号</th>
                        <th width="8%">课题编号</th>
                        <th width="14%">项目名称</th>
                        <th width="9%">报销用户</th>
                        <th width="15%">报销项目</th>
                        <th width="7%">经办人</th>
                        <th width="12%">申请报销金额（元）</th>
                        <%--<th width="5%">报销金额</th>--%>
                        <th width="15%">报销内容</th>
                        <th width="8%">操&#12288;作</th>
                    </tr>

                <c:forEach var="fundDetailExt" items="${fundDetailExtList}" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${fundDetailExt.proj.accountNo}</td>
                        <td>${fundDetailExt.proj.projNo}</td>
                        <td>${fundDetailExt.proj.projName}</td>
                        <td>${fundDetailExt.proj.applyUserName}</td>
                        <td><span class="children">${fundDetailExt.itemName}</span></td>
                        <td>${fundDetailExt.fundOperator}</td>
                        <td>${pdfn:transMultiply(fundDetailExt.money,10000)}</td>
                        <%--<td>${fundDetailExt.realmoney}</td>--%>
                        <td>${fundDetailExt.content}</td>
                        <td>
                            <a href="javascript:void(0)" onclick="doAudit('${fundDetailExt.fundDetailFlow}');" >审核</a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
        <%--<p align="right" style="margin-top: 20px;">--%>
        <%--<b style="color: red;padding-right: 50px;">已报销总计：<span id="total">${total}</span>万元</b>--%>
        <%--</p>--%>
        <c:set var="pageView" value="${pdfn:getPageView(fundDetailExtList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>