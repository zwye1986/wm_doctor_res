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
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>

    <script type="text/javascript">

        function doClose() {
            window.parent.frames['mainIframe'].window.search();
            jboxCloseMessager()
        }

        function modify(fundDetailFLow, itemFlow, money, content) {
            $('#fundDetailFlow').val(fundDetailFLow);
            $('#money').val(money);
            $('input[name="content"]').val(content);
            $('#itemFlow').val(itemFlow);
        }

        function deleteDetail(fundDetailFlow) {
            jboxConfirm("确认删除该条报销?", function () {
                var requestData = {"fundDetailFlow": fundDetailFlow};
                var url = "<s:url value='/srm/payment/deleteDetail' />";
                jboxStartLoading();
                jboxPost(url, requestData, function (resp) {
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        /* window.parent.frames['mainIframe'].window.search();*/
                        window.location.reload();
                        jboxTip(resp);
                    }
                }, null, true);
            });
        }

        function reimburse() {
            var tip = "";
            if (!$("#paymentForm").validationEngine("validate")) {
                return false;
            }
            var itemFlow = $('#itemFlow').val();
            var currentAmount = parseFloat($('#money').val());
            var itemFLowTds = $('#appendTbody .' + itemFlow);
            $.each(itemFLowTds, function (i, n) {
                currentAmount = parseFloat(currentAmount) + parseFloat($(n).html());
            });
            var budgetAmount = parseFloat($('#budget_' + itemFlow).val());
            if (currentAmount > budgetAmount*10000) {
                tip = "<span style='color:red;'>对于该报销项目中,报销总额已超过预算</span>，";
            }
            jboxConfirm(tip + "确认添加报销?", function () {
                jboxStartLoading();
                $('#paymentForm').submit();
            });

        }

        function findAuditDetail(fundDetailFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/payment/showPaymentAudit?fundDetailFlow='/>" + fundDetailFlow, "操作信息", 800, 600);
        }

        function onItemFlowChanged(fundDetailFlow) {
            $("#fundMessage").html("");
            var itemFlow = $('#itemFlow option:selected').val();
            var requestData = {
                "fundDetailFlow": fundDetailFlow
            };
            var url = "<s:url value='/srm/payment/schemeFundMessage'/>";
            jboxStartLoading();
            /*jboxPost(url, requestData, function (resp) {
             var res = JSON.parse(resp);
             $("#fundMessage").html("预算金额："+res["budgetMoney"]+" 万元，已报销金额:"+res["realMoney"]+" 万元");
             }, null, true);*/
            jboxPost(url, requestData, function (resp) {
                $("#fundMessage").html("预算金额：" + resp["budgetMoney"] * 10000 + " 元，已报销金额:" + resp.realMoney * 10000 + " 元");
            }, null, false);
        }

        function saveReimburse() {
            if (!$("#postForm").validationEngine("validate")) {
                return false;
            }
            var trs = $("#appendTbody").children();
            var datas = [];
            var fundOperator = $("#fundOperator").val();
            var fundFlow = $("#fundFlow").val();
            var fundFormFlow = $("#fundFormFlow").val();
            $.each(trs, function (i, n) {//jquery迭代器,用于循环数组和json  i表示索引   n表示当前循环的元素
                var fundDetailFlow = $(n).find("input[name='fundDetailFlow']").val();
                var money = $(n).find("input[name='money']").val();
                var data = {//json对象   {key:value}
                    "fundDetailFlow": fundDetailFlow,
                    "fundOperator": fundOperator,
                    "fundFlow": fundFlow,
                    "fundFormFlow": fundFormFlow,
                    "money": money/10000
                };
                datas.push(data);//数组的push方法
            });
            var requestData = JSON.stringify(datas);
            var url = "<s:url value='/srm/paymentXZ/saveFundDetailGroup'/>";
            jboxStartLoading();
            jboxPostJson(url, requestData, function (resp) {
                if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                    window.parent.frames['mainIframe'].window.search();
                    window.location.reload();
                }
            }, null, true);
        }
        $(function () {
            fundSum();
        });
        function fundSum() {
            var trs = $("#appendTbody").children();
            var sum = 0;
            $.each(trs, function (i, n) {
                var money = $(n).find("input[name='money']").val();
                sum += parseFloat(money);
            });
            sum = parseFloat(sum.toFixed(4));
            $("#applyFundSum").val(sum + " (元)");
        }

        var setting = {
            view: {
                dblClickExpand: true,
                showIcon: true,
                showTitle: false,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                },
                key: {
                    title: "t"
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick
            }
        };

        function beforeClick(treeId, treeNode) {
            var check = (treeNode.id != 0);
            if (!check) {
                jboxTip('不能选择根节点');
                return check;
            }
            if (treeNode.isParent) {
                jboxTip("该项不允许选择，请选择子项...");
                return false;
            }

        }

        function onClick(e, treeId, treeNode) {
            /*if (treeNode.isParent) {
             alert("这个 是父节点 ， 去点击子节点吧... ");
             return false;
             }*/

            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getSelectedNodes(),
                    v = "";
            id = "";
            nodes.sort(function compare(a, b) {
                return a.id - b.id;
            });
            for (var i = 0, l = nodes.length; i < l; i++) {
                v += nodes[i].name + ",";
                id += nodes[i].id + ",";
            }

            if (v.length > 0) v = v.substring(0, v.length - 1);
            if (id.length > 0) id = id.substring(0, id.length - 1);

            $("#itemFlow").val(treeNode.itemFlow);
            $("#itemPid").val(treeNode.pId);
            var cityObj = $("#itemName");
            $("#itemId").val(id);
            cityObj.attr("value", v);
//            var budgetInfo = "<p>下拨金额："+treeNode.allocateMoney+"</p>";
//            budgetInfo += "<p>配套金额："+treeNode.matchingMoney+"</p>";
//            $("#budgetInfo").html(budgetInfo);
            onItemFlowChanged(treeNode.detailFlow);

        }

        function showMenu() {
            var cityObj = $("#itemName");
            var cityOffset = $("#itemName").offset();
            $("#menuContent").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");
            $("body").bind("mousedown", onBodyDown);
        }

        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
                hideMenu();
            }
        }

        $(document).ready(function () {
            var url = "<s:url value='/srm/fund/scheme/detailBudgetJson'/>?fundFlow=${fundInfo.fundFlow}";
            jboxPostJson(url, null, function (data) {
                //console.log(data);
                if (data) {
                    zNodes = $.parseJSON(data);
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            }, null, false);
        });
    </script>

</head>
<body>
<div class="mainright">
    <div class="content" style="height: 330px;overflow:auto">
        <p style="font-weight: bold;font-size: 14px;"> 项目名称：${proj.projName}
            &nbsp;&nbsp;&nbsp;&nbsp;项目编号：${proj.projNo }&nbsp;&nbsp;&nbsp;&nbsp;项目类型：${proj.projTypeName}</p>
        <p style="font-weight: bold;font-size: 14px;">
            下拨经费（剩余）：${pdfn:transMultiply(fundInfo.realityGoveAmount - allocateMoney,10000)}(元)&nbsp;&nbsp;&nbsp;&nbsp;
            配套经费（剩余）：${pdfn:transMultiply(fundInfo.realityOrgAmount - matchingMoney,10000)}(元)
        </p>
        <br/>
        <c:if test="${empty fundForm.fundFormFlow || fundForm.operStatusId==achStatusEnumApply.id || fundForm.operStatusId==achStatusEnumThirdBack.id || fundForm.operStatusId==achStatusEnumFourthBack.id || fundForm.operStatusId==achStatusEnumFifthBack.id}">
            <form id="paymentForm" action="<s:url value='/srm/payment/reimburse'/>" method="post"
                  enctype="multipart/form-data">
                <input type="hidden" name="fundFlow" value="${fundInfo.fundFlow}"/>
                <input type="hidden" name="fundFormFlow" value="${fundForm.fundFormFlow}"/>
                <input name="fundOperator" type="hidden" value="${sessionScope.currUser.userName}"/>
                <table class="xllist">
                    <tr>
                        <th width="10%">报销项目</th>
                        <th width="10%">报销金额(元)</th>
                        <th width="10%">使用经费</th>
                        <th width="35%">报销内容</th>
                        <th width="10%">单据张数</th>
                        <th width="15%">附件</th>
                        <th width="5%">操作</th>
                    </tr>
                    <tr>
                        <td style="text-align: center;">
                                <%--<select class="validate[required]" name="itemFlow" id="itemFlow"--%>
                                <%--onchange="onItemFlowChanged('${fundInfo.fundFlow}')">--%>
                                <%--<option value="">请选择</option>--%>
                                <%--<c:forEach items="${schemeDetailList}" var="schemeItem">--%>
                                <%--<option value="${schemeItem.itemFlow}">${schemeItem.itemName}</option>--%>
                                <%--</c:forEach>--%>
                                <%--</select>--%>
                            <input type="text" id="itemName" name="itemName" class="validate[required] "
                                   style="width: 230px;" readonly="readonly"
                                   onclick="showMenu(); return false;"/>
                            <input type="hidden" id="itemId" name="itemId"/>
                            <input type="hidden" id="itemPid" name="itemPid"/>
                            <input type="hidden" id="itemFlow" name="itemFlow"/>
                        </td>
                        <td style="text-align: center;">
                            <input id="money" name="money" type="text" style="width: 90%;text-align: center;"
                                   class="validate[required,custom[number],min[0]]"/>
                        </td>
                        <td style="text-align: center;">
                            <select class="validate[required]" name="realityTypeId">
                                <option value="">请选择</option>
                                <c:forEach var="fundAccountsType" items="${projFundAccountsTypeEnumList}">
                                    <option value="${fundAccountsType.id}">${fundAccountsType.name}</option>
                                </c:forEach>
                            </select></td>
                        <td style="text-align: center;">
                            <input name="content" type="text" style="width: 96%" class="validate[required]"/>
                        </td>
                        <td style="text-align: center;">
                            <input name="fundFormNum" type="text" style="width: 96%"
                                   class="validate[required,,custom[integer],min[0],maxSize[4]]"/>
                        </td>
                        <td style="text-align: center;">
                            <input name="file" type="file" style="width: 96%"/>
                        </td>
                        <td>
                            <a href="javascript:void(0);" onclick="reimburse();">[保存]</a>
                        </td>
                    </tr>
                </table>
                <div id="fundMessage" style="color: red;height: 25px;margin-left: 20px"></div>
            </form>
        </c:if>
        <br/>
        <form id="postForm">
            <input type="hidden" id="fundFlow" name="fundFlow" value="${fundInfo.fundFlow}"/>
            <input type="hidden" id="fundFormFlow" name="fundFormFlow" value="${fundForm.fundFormFlow}"/>
            <table style="width: 100%;" class="bs_tb">
                <tr>
                    <th width="10%">报销项目</th>
                    <th width="5%">预算金额(元)</th>
                    <th width="7%">申请报销金额(元)</th>
                    <th width="8%">实际报销金额(元)</th>
                    <th width="10%">使用经费</th>
                    <th width="20%">报销内容</th>
                    <th width="10%">单据张数</th>
                    <th width="15%">附件</th>
                    <th width="10%">审核状态</th>
                    <th width="5%">操作</th>
                </tr>
                <tbody id="appendTbody">
                <c:forEach items="${fundDetailMap}" var="detailEntry">
                    <c:forEach items="${detailEntry.value}" var="detail" varStatus="status">
                        <tr>
                            <c:if test="${status.count==1}">
                                <td rowspan="${detailEntry.value.size()}">
                                    <c:forEach items="${schemeDetailList}" var="schemeItem">
                                        <c:if test='${schemeItem.itemFlow eq detailEntry.key}'>${schemeItem.itemName}</c:if>
                                    </c:forEach>
                                </td>
                                <td rowspan="${detailEntry.value.size()}">${pdfn:transMultiply(budgetFundDtlMap[detailEntry.key].money,10000)}</td>
                            </c:if>
                                <%--  <td rowspan="${detailEntry.value.size()}">
                                          ${pdfn:getPercentByDouble(yetPaymentMap[detailEntry.key], budgetFundDtlMap[detailEntry.key].money)}
                                  </td>--%>
                            <td><input type="hidden" class="inputText" name="money"
                                       value="${pdfn:transMultiply(detail.money,10000)}"/>${pdfn:transMultiply(detail.money,10000)}
                            </td>
                            <td>${pdfn:transMultiply(detail.realmoney,10000)}

                            </td>
                            <td>
                                    ${detail.realityTypeName}
                            </td>
                            <td>
                                    ${detail.content}
                            </td>
                            <td>
                                    ${detail.fundFormNum}
                            </td>
                            <td>
                                <a href='<s:url value="/pub/file/down?fileFlow=${fileMap[detail.fundDetailFlow].fileFlow}"/>'>${fileMap[detail.fundDetailFlow].fileName}</a>
                                <input type="hidden" name="fileFlow"
                                       value="${fileMap[detail.fundDetailFlow].fileFlow}"/>
                            </td>
                            <td>
                                    ${detail.operStatusName}
                                <input type="hidden" name="fundDetailFlow" value="${detail.fundDetailFlow}"/>
                            </td>
                            <td>
                                <c:if test="${detail.operStatusId==achStatusEnumApply.id || detail.operStatusId==achStatusEnumThirdBack.id || detail.operStatusId==achStatusEnumFourthBack.id || detail.operStatusId==achStatusEnumFifthBack.id}">
                                    <a href="javascript:void(0)"
                                       onclick="deleteDetail('${detail.fundDetailFlow}')">[删除]</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td>合计：</td>
                    <td style="text-align: left;" colspan="9">
                        &#12288;&#12288;申请报销：<input type="text" style="width: 150px;text-align: center;"
                                                    id="applyFundSum"
                                                    class="inputText" readonly="readonly"/>
                        &#12288;&#12288;&#12288;实际报销：
                        <input type="text" style="width: 150px;text-align: center;" id="realmoneySum"
                               value="${pdfn:transMultiply(fundForm.realmoney,10000)} (元)"
                               class="inputText" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <td>报销人：</td>
                    <td style="text-align: left;" colspan="9">
                        <c:choose>
                            <c:when test="${detail.operStatusId==achStatusEnumApply.id || detail.operStatusId==achStatusEnumThirdBack.id || detail.operStatusId==achStatusEnumFourthBack.id || detail.operStatusId==achStatusEnumFifthBack.id}">
                                &#12288;&#12288;<input name="fundOperator" id="fundOperator" type="text"
                                style="width: 150px;text-align: center;"
                                class="inputText validate[required]" value="<c:if
                                    test="${empty fundForm.fundOperator}">${sessionScope.currUser.userName}</c:if>${fundForm.fundOperator}"/>
                            </c:when>
                            <c:otherwise>
                                &#12288;&#12288;<input class="inputText" type="text" value="${fundForm.fundOperator}" readonly="readonly" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                </tfoot>
            </table>
        </form>
    </div>
    <c:if test="${empty fundForm.fundFormFlow || fundForm.operStatusId==achStatusEnumApply.id || fundForm.operStatusId==achStatusEnumThirdBack.id || fundForm.operStatusId==achStatusEnumFourthBack.id || fundForm.operStatusId==achStatusEnumFifthBack.id}">
        <div style="text-align: center;">
            <p style="text-align: center;padding-top: 10px;">
                <input class='search' onclick="saveReimburse()"
                       type='button' value='提&nbsp;&nbsp;交'/>&#12288;
                <input class='search' onclick="doClose()"
                       type='button' value='关&nbsp;&nbsp;闭'/>&#12288;
            </p>
        </div>
    </c:if>
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index:1000;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:210px;height: 300px"></ul>
</div>
<c:forEach var="budgetItem" items="${budgetFundDtlMap}">
    <input type="hidden" id="budget_${budgetItem.key}" value="${budgetItem.value.money}"/>
</c:forEach>
</body>
</html>