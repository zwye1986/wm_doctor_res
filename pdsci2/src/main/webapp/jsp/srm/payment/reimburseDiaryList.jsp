<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            if (false == $("#searchForm").validationEngine("validate")) {
                return false;
            }
            $('#searchForm').submit();
        }
        function toPage(page) {
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
        .basic tbody th {
            text-align: center;
        }

        .basic tbody td {
            text-align: center;
        }

        .basic a {
            color: #00a0ff;
        }

        a:hover {
            color: #ff821a;
        }
    </style>
</head>
<body>
<div class="mainright" id="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/srm/payment/reimburseDiary"/>" method="post">
                <input type="hidden" id="expandFundFlow" name="expandFundFlow">
                <input type="hidden" id="currentPage" name="currentPage">

                    <div class="searchDiv">
                        &#12288;&#12288;&#12288;项目来源：
                        <select id="projDeclarerFlow" name="projDeclarerFlow" class="xlselect">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                                <option dictFlow="${dict.dictFlow}" value="${dict.dictId}"
                                        <c:if test="${param.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="searchDiv">
                        &#12288;&#12288;&#12288;课题账号：
                        <input class="xltext" name="accountNo" type="text"
                               value="${param.accountNo}"/>
                    </div>
                    <div class="searchDiv">
                        &#12288;&#12288;&#12288;课题编号：
                        <input class="xltext" name="projNo" type="text"
                               value="${param.projNo}"/>
                    </div>

                    <div class="searchDiv">
                        &#12288;&#12288;&#12288;科&#12288;&#12288;室：
                        <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                               value="${param.applyDeptName}" autocomplete="off"/>
                        <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}"
                               type="text"
                               hidden style="margin-left: 0px;"/>
                    </div>
                    <div class="searchDiv">
                        报销金额（元）：
                        <input class="xltext validate[custom[number]]" name="startMoney" type="text" style="width: 65px"
                               value="${param.startMoney}"/> —
                        <input class="xltext validate[custom[number]]" name="endMoney" type="text" style="width: 65px"
                               value="${param.endMoney}"/>
                    </div>

                    <div class="searchDiv">
                        &#12288;&#12288;&#12288;&#12288;负责人：
                        <input class="xltext" name="applyUserName" type="text"
                               value="${param.applyUserName}"/>
                    </div>
                    <div class="searchDiv" >
                        &#12288;&#12288;&#12288;报销（到账）时间：
                        <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                               class="ctime"
                               name="startTime" type="text" value="${param.startTime}"/>-
                        <input onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                               class="ctime"
                               name="endTime" type="text" value="${param.endTime}"/>
                    </div>

                    <div class="searchDiv">
                        <input class="search" type="button" value="查&#12288;询" onclick="search()"/>
                    </div>

            </form>
        </div>

        <table class="basic" style="width: 100%">

            <tr>
                <th width="6%">课题账号</th>
                <th width="6%">课题编号</th>
                <th width="10%">报销（到账）时间</th>
                <th width="13%">项&#12288;目</th>
                <th width="7%">报销经手人</th>
                <th width="10%">报销内容</th>
                <th width="7%">预算类型</th>
                <th width="7%">到账方式</th>
                <th width="7%">拨款单位</th>
                <th width="6%">到账金额（元）</th>
                <th width="6%">报销金额（元）</th>
                <%--<th width="5%">剩余金额</th>--%>
                <th width="7%">报销总金额（元）</th>
                <th width="7%">到账总金额（元）</th>
                <th width="7%">剩余总金额（元）</th>
            </tr>

            <c:forEach var="key" items="${list}">
                <c:set var="fundDetail1"/>
                <c:set var="projExt"/>
                <c:if test="${fn:length(fundDetailMap[key])>0}">
                    <c:set var="fundDetail1" value="${(fundDetailMap[key])[0]}"/>
                    <c:set var="projExt" value="${pubProjExtMap[key]}"/>
                </c:if>
            <%--<c:if test="${not empty projExt}">--%>
                <tr>
                    <td rowspan="${fn:length(fundDetailMap[key])}">${projExt.accountNo}</td>
                    <td rowspan="${fn:length(fundDetailMap[key])}">${projExt.projNo}</td>

                    <td>${pdfn:transDateTime(fundDetail1.provideDateTime)}</td>
                    <td>
                        <c:choose>
                            <c:when test="${fundDetail1.fundTypeId eq 'Income'}">
                                经费到账
                            </c:when>
                            <c:when test="${fundDetail1.fundTypeId eq 'Surplus'}">
                                经费结余
                            </c:when>
                            <c:when test="${fundDetail1.fundTypeId eq 'Reimburse'}">
                                ${empty fundDetail1.itemName?"-":fundDetail1.itemName}
                            </c:when>
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
                    </td>
                    <td>${fundDetail1.fundOperator}</td>
                    <td>
                            ${empty fundDetail1.content?fundDetail1.itemName:fundDetail1.content}
                    </td>
                    <td>${fundDetail1.realityTypeName}</td>
                    <td>${fundDetail1.fundSourceName}</td>
                    <td>${fundDetail1.provideOrg}</td>
                    <td>${pdfn:transMultiply(fundDetail1.money,10000)}</td>
                    <td>${pdfn:transMultiply(fundDetail1.realmoney,10000)}</td>

                    <td rowspan="${fn:length(fundDetailMap[key])}">${pdfn:transMultiply(projExt.projFundInfo.yetPaymentAmount,10000)}</td>
                    <td rowspan="${fn:length(fundDetailMap[key])}">${pdfn:transMultiply(projExt.projFundInfo.realityAmount,10000)}</td>
                    <td rowspan="${fn:length(fundDetailMap[key])}">${pdfn:transMultiply(projExt.projFundInfo.realityBalance,10000)}</td>

                </tr>
                <c:if test="${fundDetailMap[key] != null and fn:length(fundDetailMap[key]) > 1}">
                    <c:forEach var="fundDetail" items="${fundDetailMap[key]}" begin="1">
                        <tr>
                            <td>${pdfn:transDateTime(fundDetail.provideDateTime)}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${fundDetail.fundTypeId eq 'Income'}">
                                        经费到账
                                    </c:when>
                                    <c:when test="${fundDetail.fundTypeId eq 'Surplus'}">
                                        经费结余
                                    </c:when>
                                    <c:when test="${fundDetail.fundTypeId eq 'Reimburse'}">
                                        ${empty fundDetail.itemName?"-":fundDetail.itemName}
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                            <td>${fundDetail.fundOperator}</td>
                            <td>
                                    ${empty fundDetail.content?fundDetail.itemName:fundDetail.content}
                            </td>
                            <td>${fundDetail.realityTypeName}</td>
                            <td>${fundDetail.fundSourceName}</td>
                            <td>${fundDetail.provideOrg}</td>
                            <td><c:if test="${fundDetail.fundTypeId eq 'Income'}">${pdfn:transMultiply(fundDetail.money,10000)}</c:if></td>
                            <td>${pdfn:transMultiply(fundDetail.realmoney,10000)}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            <%--</c:if>--%>
            </c:forEach>
        </table>
        <%--<p align="right" style="margin-top: 20px;">--%>
        <%--<b style="color: red;padding-right: 50px;">已报销总计：<span id="total">${total}</span>万元</b>--%>
        <%--</p>--%>
        <c:set var="pageView" value="${pdfn:getPageView(srmProjFundDetails)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>