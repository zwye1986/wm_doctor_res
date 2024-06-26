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
            jboxOpen("<s:url value="/srm/payment/audit/finance?fundDetailFlow="/>" + fundDetailFlow, "审核", 750, 600);
        }
        function addReimburse(fundFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/payment/addReimburse?fundFlow="/>" + fundFlow, "添加报销", 750, 500);
        }
        function doAuditLocal(fundDetailFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/payment/audit/local?fundDetailFlow="/>" + fundDetailFlow, "审核", 750, 600);
        }
        function doView(fundDetailFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/payment/showPaymentAudit?fundDetailFlow="/>" + fundDetailFlow, "操作信息", 800, 600);
        }
        function doPrint(fundDetailFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value="/srm/payment/print?fundDetailFlow="/>" + fundDetailFlow, "打印", 750, 550);
        }
        function deleteDetail(fundDetailFlow,fundFlow){
            jboxConfirm("确认删除该项报销？",function (){
                jboxPost("<s:url value='/srm/payment/deleteReimburse?fundDetailFlow='/>"+fundDetailFlow,null,function(resp){
                    search(fundFlow);
                },null,true);
            },null);
        }
        function search(flow) {
            jboxStartLoading();
            $("#expandFundFlow").val(flow);
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
        $(function () {
            //$("#treeTable1").treetable({onNodeExpand:function(){alert(1);}});
            $("#treeTable1").treetable({expandable: true,indenterTemplate: "<span class='indenter'></span>"});
//            $("#treeTable1").treetable("collapseAll");
            <c:if test="${not empty expandFundFlow}">
            var expandNode = "${expandFundFlow}";
            if (expandNode) {
                $('#treeTable1').treetable('expandNode', expandNode);
            }
            </c:if>
            $("#treeTable1 tr").bind("dblclick",function (){
                $('#treeTable1').treetable('collapseOrexpand',$(this).attr("data-tt-id"));
            });
        });
    </script>
<style type="text/css">
    .basic tbody th{
        text-align: center;
    }
    .basic tbody td{
        text-align: center;
        line-height: 22px;
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
            <form id="searchForm" action="<s:url value="/srm/payment/auditList/${scope}"/>" method="post">
                <input type="hidden" id="expandFundFlow" name="expandFundFlow">
                <input type="hidden" id="currentPage" name="currentPage">
                <div class="searchDiv">
                    &#12288;科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                           value="${param.applyDeptName}" autocomplete="off"/>
                    <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <div class="searchDiv">
                    项目来源：
                    <select id="projDeclarerFlow" name="projDeclarerFlow" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                            <option dictFlow="${dict.dictFlow}" value="${dict.dictId}" <c:if test="${param.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
               <%-- <div class="searchDiv">
                    &#12288;课题编号：
                    <input class="xltext" name="projNo" type="text"
                           value="${param.projNo}"/>
                </div>--%>
                <div class="searchDiv">
                    &#12288;课题账号：
                    <input class="xltext" name="accountNo" type="text"
                           value="${param.accountNo}"/>
                </div>

                <div class="searchDiv">
                    &#12288; 负 责 人：
                    <input class="xltext"  name="applyUserName" type="text"
                           value="${param.applyUserName}"/>
                </div>
                <div class="searchDiv">
                    &#12288;
                    <input class="search" type="button" value="查&#12288;询" onclick="search()"/>
                </div>
            </form>
        </div>

            <table id="treeTable1" class="linetable basic" style="width: 100%">
                <colgroup>
                    <col width="15%">
                    <col width="10%">
                    <col width="9%">
                    <col width="9%">
                    <col width="12%">
                    <col width="10%">
                    <col width="7%">
                    <col width="8%">
                    <col width="8%">
                    <col width="12%">
                </colgroup>
            <tr>
                <th>课题账号</th>
                <th colspan="4">项目名称</th>
                <%--<th width="100px;">项目类型</th>--%>
                <th colspan="3">项目来源</th>
                <th>负责人</th>
                <th>操&#12288;&#12288;作</th>
            </tr>
            <%--<c:forEach items="${fundDetailKeys}" var="fundDetailKey">--%>
                <%--<c:forEach items="${fundDetailMap[fundDetailKey]}" var="detail">--%>
                <c:forEach items="${pubProjExtList}" var="pubProjExt">
                    <tr data-tt-id="${pubProjExt.projFundInfo.fundFlow}">
                        <td style="text-align: left"><span class="parent">${pubProjExt.accountNo}</span></td>
                        <td colspan="4">${pubProjExt.projName}</td>
                        <td colspan="3">${pubProjExt.projDeclarer}</td>
                        <%--<td>${pubProjExt.projSecondSourceName}</td>--%>
                        <td>${pubProjExt.applyUserName}</td>
                        <%--<td>${pubProjExt.projFundInfo.budgetAmount}</td>
                        <td class="money">${pubProjExt.projFundInfo.yetPaymentAmount}</td>
                        <td>${pubProjExt.projFundInfo.realityAmount}</td>
                        <td>${pubProjExt.projFundInfo.realityBalance}</td>--%>
                        <td><c:if test="${scope eq 'finance'}"><a href="javascript:void(0)" onclick="addReimburse('${pubProjExt.projFundInfo.fundFlow}');">添加报销</a></c:if></td>
                    </tr>
                    <c:if test="${not empty fundDetailMap[pubProjExt.projFundInfo.fundFlow]}">
                        <tr data-tt-id="1" data-tt-parent-id="${pubProjExt.projFundInfo.fundFlow}">
                            <%--<th width="80px;">报销用户</th>--%>
                            <td style="text-align: left;background-color: #fff8f8;">报销项目</td>
                            <td style="background-color: #fff8f8;">报销时间</td>
                            <td style="background-color: #fff8f8;">申请报销金额（元）</td>
                            <td style="background-color: #fff8f8;">实际报销金额（元）</td>
                            <td style="background-color: #fff8f8;">报销内容</td>
                            <td style="background-color: #fff8f8;">审核状态</td>
                            <td style="background-color: #fff8f8;">预算类型</td>
                            <td style="background-color: #fff8f8;">到账类型</td>
                            <td style="background-color: #fff8f8;">经&nbsp;办&nbsp;人</td>
                            <td style="background-color: #fff8f8;">操&#12288;&#12288;作</td>
                        </tr>
                    </c:if>
                    <c:forEach var="detail" items="${fundDetailMap[pubProjExt.projFundInfo.fundFlow]}">
                        <tr data-tt-id="${detail.fundDetailFlow}" data-tt-parent-id="${detail.fundFlow}">
                            <td style="text-align: left"><span class="children">${detail.itemName}</span></td>
                            <td>${pdfn:transDateTime(detail.provideDateTime)}</td>
                            <td>${pdfn:transMultiply(detail.money,10000)}</td>
                            <td>${pdfn:transMultiply(detail.realmoney,10000)}</td>
                            <td>${detail.content}</td>
                            <td>${detail.operStatusName}</td>
                            <td>${detail.realityTypeName}</td>
                            <td>${detail.fundSourceName}</td>
                            <td>${detail.fundOperator}</td>
                            <td>
                                <c:set var="approveFlag" value="false"/>
                                <c:if test="${applicationScope.sysCfgMap['srm_local_type'] eq 'Y'}">
                                    <c:if test="${detail.operStatusId==achStatusEnumThirdAudit.id && isChief eq 'Y'}">
                                        <c:set var="approveFlag" value="true"/>
                                    </c:if>
                                    <c:if test="${detail.operStatusId==achStatusEnumFourthAudit.id && isDean eq 'Y'}">
                                        <c:set var="approveFlag" value="true"/>
                                    </c:if>
                                </c:if>
                                <c:if test="${detail.operStatusId==achStatusEnumFirstAudit.id}">
                                    <c:set var="approveFlag" value="true"/>
                                </c:if>
                                <c:choose>
                                    <c:when test="${scope == GlobalConstant.USER_LIST_FINANCE}">
                                        <c:choose>
                                            <c:when test="${approveFlag}"><a
                                                    href="javascript:void(0)" onclick="doView('${detail.fundDetailFlow}');">查看</a>|<a href="javascript:void(0)" onclick="doAudit('${detail.fundDetailFlow}');" >审核</a></c:when>
                                            <c:otherwise><a href="javascript:void(0)"
                                                            onclick="doView('${detail.fundDetailFlow}');">查看</a>|<a href="javascript:void(0)" onclick="deleteDetail('${detail.fundDetailFlow}','${detail.fundFlow}');" >删除</a></c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${detail.operStatusId==achStatusEnumSubmit.id}"><a
                                                    href="javascript:void(0)"
                                                    onclick="doAuditLocal('${detail.fundDetailFlow}');">审核</a>|<a href="javascript:void(0)" onclick="doView('${detail.fundDetailFlow}');">查看</a></c:when>
                                            <c:otherwise><a href="javascript:void(0)"
                                                            onclick="doView('${detail.fundDetailFlow}');">查看</a><c:if
                                                    test="${detail.operStatusId==achStatusEnumSecondAudit.id}"><%--|<a href="javascript:void(0)" onclick="doPrint('${detail.fundDetailFlow}');" >打印</a>--%></c:if></c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr data-tt-id="2" data-tt-parent-id="${pubProjExt.projFundInfo.fundFlow}">
                        <td colspan="10">
                            预算金额总计：${pdfn:transMultiply(pubProjExt.projFundInfo.budgetAmount,10000)}（元）&#12288;&#12288;&#12288;&#12288;
                            已报销总计：${pdfn:transMultiply(pubProjExt.projFundInfo.yetPaymentAmount,10000)}（元）
                        </td>
                    </tr>
                </c:forEach>
            <%--</c:forEach>--%>
        </table>
        <%--<p align="right" style="margin-top: 20px;">--%>
            <%--<b style="color: red;padding-right: 50px;">已报销总计：<span id="total">${total}</span>万元</b>--%>
        <%--</p>--%>
        <c:set var="pageView" value="${pdfn:getPageView(pubProjExtList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>