<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="treetable" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        function toggleTr(obj){
            $('#treeTable').treetable('collapseOrexpand',$(obj).parents('tr').attr("data-tt-id"));
        }
        $(function () {

            //$("#treeTable1").treetable({onNodeExpand:function(){alert(1);}});
            $("#treeTable").treetable({expandable: true,indenterTemplate: "<span class='indenter'></span>"});
            $("#treeTable").treetable("collapseAll");
            countBudget();
        });

        function countBudget(){
            var trs = $('#tbody tr');
            var tdLimit = 0;
            $.each(trs, function (i, n) {
                if(!$(n).attr("data-tt-parent-id")){
                    tdLimit += parseFloat($(n).find("td").eq(1).text()) ? parseFloat($(n).find("td").eq(1).text()) : 0;
                }
            });
            $("#countLimit").text(parseFloat(tdLimit.toFixed(4)));
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <p style="font-weight: bold;font-size: 14px;"> 项目名称：${proj.projName}
            &nbsp;&nbsp;&nbsp;&nbsp;项目编号：${proj.projNo }&nbsp;&nbsp;&nbsp;&nbsp;项目类型：${proj.projTypeName}</p>
        <br/>
        <form id="schemeDtlList" method="post">
            <input type="hidden" id="fundFlow" name="fundFlow" value="${fundInfo.fundFlow }"/>
            <div style="margin-bottom:10px;text-align: left;">
                <p>
                    &#12288;总经费：${pdfn:transMultiply(fundInfo.amountFund,10000)}（元）
                    &#12288;预算方案：${scheme.schemeName}
                    <br />下拨金额：${pdfn:transMultiply(fundInfo.goveFund,10000) }（元）
                    &#12288;配套金额：${pdfn:transMultiply(fundInfo.orgFund,10000) }（元）

                </p>
            </div>
            <table id="treeTable" class="xllist linetable">
                <tr>
                    <th style="width:300px;">预算项名称</th>
                    <th style="width:110px;">预算比例(单位：%)</th>
                    <th style="width:100px;">预算金额(元)</th>
                    <th style="width:120px;">下拨金额预算(元)</th>
                    <th style="width:120px;">配套金额预算(元)</th>
                    <th>预算备注</th>
                </tr>
                <tr>
                    <td>&#12288;合&#12288;计</td>
                    <td id="countLimit">

                    </td>
                    <td>
                        ${pdfn:transMultiply(fundInfo.budgetAmount,10000)}
                    </td>
                    <td>
                        ${pdfn:transMultiply(fundInfo.budgetGove,10000)}
                    </td>
                    <td>
                        ${pdfn:transMultiply(fundInfo.budgetOrg,10000)}
                    </td>
                    <td>

                    </td>
                </tr>
                <tbody id="tbody">
                <%--<c:if test="${empty fundDtlList}">--%>
                <c:forEach var="n" items="${fundDtlList}">
                    <c:if test="${(empty n.itemPid) or (n.itemPid eq '0')}">
                        <tr data-tt-id="${n.itemId}">
                            <td ondblclick="toggleTr(this)" style="text-align: left;cursor:pointer">${n.itemName}
                            </td>
                            <td>
                               ${n.maxLimit}
                            </td>
                            <td>
                                ${pdfn:transMultiply(n.money,10000)}
                            </td>
                            <td>
                                ${pdfn:transMultiply(n.allocateMoney,10000)}
                            </td>
                            <td>
                               ${pdfn:transMultiply(n.matchingMoney,10000)}
                            </td>
                            <td>
                                ${n.remark}
                            </td>
                        </tr>
                        <c:forEach var="childDtl" items="${fundDtlList}">
                            <c:if test="${(not empty n.itemId) and (n.itemId eq childDtl.itemPid)}">
                                <tr data-tt-id="${childDtl.itemId}" data-tt-parent-id="${n.itemId}">
                                    <td ondblclick="toggleTr(this)" style="text-align: left;cursor:pointer">${childDtl.itemName}
                                    </td>
                                    <td>
                                            ${childDtl.maxLimit}
                                    </td>
                                    <td>
                                            ${pdfn:transMultiply(childDtl.money,10000)}
                                    </td>
                                    <td>
                                            ${pdfn:transMultiply(childDtl.allocateMoney,10000)}
                                    </td>
                                    <td>
                                            ${pdfn:transMultiply(childDtl.matchingMoney,10000)}
                                    </td>
                                    <td>
                                            ${pdfn:transMultiply(childDtl.remark,10000)}
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>

                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </form>
    </div>
</div>
</body>