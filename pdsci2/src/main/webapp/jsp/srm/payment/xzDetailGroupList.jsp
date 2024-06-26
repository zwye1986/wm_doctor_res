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
    </jsp:include>

    <script type="text/javascript">

        function getDetail(fundFlow, schemeFlow, formFlow) {
            var url = "<s:url value='/srm/paymentXZ/getDetailByGroup?fundFlow='/>" + fundFlow + "&schemeFlow=" + schemeFlow + "&formFlow=" + formFlow;
            var w = $('#mainright').width();
            var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='400px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
            jboxMessager(iframe, '报销明细', w, 400);
        }

        function search() {
            jboxStartLoading();
            var fundFlow = "${fundInfo.fundFlow}";
            var projFlow = "${proj.projFlow}";
            $("#searchForm").attr("action", "<s:url value='/srm/paymentXZ/fundDetailGroup?fundFlow='/>" + fundFlow + '&projFlow=' + projFlow);
            $("#searchForm").submit();
        }

        function getDelete(formFlow,fundInfoFlow) {
            jboxConfirm("确认删除该组报销?", function () {
                var requestData = {"fundFormFlow": formFlow,"fundInfoFlow":fundInfoFlow};
                var url = "<s:url value='/srm/paymentXZ/deleteFundForm' />";
                jboxStartLoading();
                jboxPost(url, requestData, function (resp) {
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        search();
                        jboxTip(resp);
                    }
                }, null, true);
            });
        }
    </script>


</head>
<body>
<div class="mainright" id="mainright">
    <form id="searchForm" method="post"></form>
    <div class="content">
        <div class="title1 clearfix">
            <p>
                项目名称：<input class="inputText" type="text" value="${proj.projName}" readonly="readonly"/>
                &#12288;&#12288;预算经费：<input class="inputText" type="text" value="${pdfn:transMultiply(fundInfo.budgetAmount,10000)} (元)"
                                            readonly="readonly"/>
            </p>
        </div>

        <table class="xllist">
            <tr>
                <td colspan="6"><span style="float: right;padding-right: 30px"><a style="color: blue;"
                                                                                  href="javascript:void(0)"
                                                                                  onclick="getDetail('${fundInfo.fundFlow}','${fundInfo.schemeFlow}' , '${proj.projFlow}' ,'');">[添加报销]</a></span>
                </td>
            </tr>
            <tr>
                <th>申请人</th>
                <%--<th width="30%">项目名称</th>--%>
                <th>时间</th>
                <th>申请报销金额</th>
                <th>实际报销金额</th>
                <th>审核状态</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${fundFormList}" var="fundForm">
                <tr>
                    <td>${fundForm.fundOperator}</td>
                        <%--<td>${proj.projName}</td>--%>
                    <td>${pdfn:transDateTime(fundForm.createTime)}</td>
                    <td>
                            ${pdfn:transMultiply(fundForm.money,10000)}（元）
                            <%--<c:set value="0" var="sum"/>
                            <c:forEach items="${detailEntry.value}" var="detail">
                                <c:set value="${sum + detail.money}" var="sum"/>
                            </c:forEach>
                                ${sum}--%>
                    </td>
                    <td>
                            ${pdfn:transMultiply(fundForm.realmoney,10000)}（元）
                    </td>
                    <td>${fundForm.operStatusName}</td>
                    <td>
                            <%--点击报销，页面下方弹出报销明细界面 --%>
                        <a href="javascript:void(0)"
                           onclick="getDetail('${fundInfo.fundFlow}','${fundInfo.schemeFlow}','${fundForm.fundFormFlow}');">报销详细</a>
                        <c:if test="${empty fundForm.fundFormFlow || fundForm.operStatusId==achStatusEnumApply.id || fundForm.operStatusId==achStatusEnumThirdBack.id || fundForm.operStatusId==achStatusEnumFourthBack.id || fundForm.operStatusId==achStatusEnumFifthBack.id}">
                            | <a href="javascript:void(0)" onclick="getDelete('${fundForm.fundFormFlow}','${fundInfo.fundFlow}');">删除</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>
</body>
</html>