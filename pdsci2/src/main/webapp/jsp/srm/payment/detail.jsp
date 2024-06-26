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
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>

    <script type="text/javascript">

        function doClose() {
            jboxClose();
        }

        function modify(fundDetailFLow, money, itemId,fundOperator, fundRetype,realityTypeId,content) {
            $('#fundDetailFlow').val(fundDetailFLow);
            $('#money').val(money);
            $('input[name="content"]').val(content);
            $('#fundOperator').val(fundOperator);
            $('#fundRetype').val(fundRetype);
            $('#realityTypeId').val(realityTypeId);
            expandNode(itemId);
        }

        function expandNode(treeId) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            var node = zTree.getNodeByParam("id",treeId,null);
            zTree.selectNode(node);
            onClick(null, treeId, node);
        }


        function deleteDetail(fundDetailFlow) {
            jboxConfirm("确认删除该条报销?", function () {
                var requestData = {"fundDetailFlow": fundDetailFlow};
                var url = "<s:url value='/srm/payment/deleteDetail' />";
                jboxStartLoading();
                jboxPost(url, requestData, function (resp) {
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].window.search();
                        window.location.reload();
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
           // alert(currentAmount+"-----"+budgetAmount);
            if (currentAmount > budgetAmount) {
                tip = "<span style='color:red;'>对于该报销项目中,报销总额已超过预算</span>，";
            }
            jboxConfirm(tip + "确认报销?", function () {
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
                /*$("#yetReimburse").html("预算金额："+resp["budgetMoney"]+" 万元，已报销金额:"+resp.realMoney+" 万元");*/
                var yetReimburse = "<p>下拨已报销金额："+resp.allocateMoney*10000+"</p>";
                yetReimburse += "<p>配套已报销金额："+resp.matchingMoney*10000+"</p>";
                $("#yetReimburse").html(yetReimburse);
            }, null, false);
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
                    title:"t"
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

            var cityObj = $("#itemName");
            $("#itemId").val(id);
            $("#itemFlow").val(treeNode.itemFlow);
            $("#itemPid").val(treeNode.pId);
            cityObj.attr("value", v);
            var budgetInfo = "<p>下拨金额："+((!treeNode.allocateMoney || treeNode.allocateMoney == 'null')?'':(treeNode.allocateMoney*10000))+"</p>";
            budgetInfo += "<p>配套金额："+((!treeNode.matchingMoney || treeNode.matchingMoney == 'null')?'':(treeNode.matchingMoney*10000))+"</p>";
            $("#budgetInfo").html(budgetInfo);
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
            var url = "<s:url value='/srm/fund/scheme/detailBudgetJson'/>?fundFlow=${fundFlow}";
            jboxPostJson(url, null, function (data) {
                //console.log(data);
                if (data) {
                    zNodes = $.parseJSON(data);
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            }, null, false);
        });
    </script>
<style type="text/css">
    a{
        color:#2222ff;
    }
    a:hover{
        color: #ff821a;
    }
</style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <p style="font-weight: bold;font-size: 14px;"> 项目名称：${proj.projName}
            &nbsp;&nbsp;&nbsp;&nbsp;项目编号：${proj.projNo }&nbsp;&nbsp;&nbsp;&nbsp;
            <c:choose>
            <c:when test="${(sysCfgMap['srm_for_use'] eq 'local') and (sysCfgMap['srm_local_type'] eq 'Y')}">
                项目类型：${proj.projTypeName}
            </c:when>
            <c:otherwise>
                一级来源：${proj.projDeclarer}&nbsp;&nbsp;&nbsp;&nbsp;
                二级来源：${proj.projSecondSourceName}
            </c:otherwise>
            </c:choose>
            </p>
        <br/>
        <form id="paymentForm" action="<s:url value='/srm/payment/reimburse'/>" method="post" enctype="multipart/form-data">
            <input type="hidden" id="fundFlow" name="fundFlow" value="${fundFlow}"/>
            <input type="hidden" id="fundDetailFlow" name="fundDetailFlow" value=""/>
            <table class="xllist">
                <tr>
                    <th width="15%">报销项目</th>
                    <th width="15%">预算金额(元)</th>
                    <th width="15%">已报销金额(元)</th>
                    <th width="7%">报销金额<br/>(元)</th>
                    <th width="5%">到账方式</th>
                    <th width="5%">报销方式</th>
                    <th width="10%">经办人</th>
                    <th width="15%">报销内容</th>
                    <th width="6%">操作</th>
                </tr>
                <tr>
                    <td style="text-align: center;">
                        <input type="text" id="itemName" name="itemName" class="validate[required] " style="width: 230px;" readonly="readonly"
                               onclick="showMenu(); return false;"/>
                        <input type="hidden" id="itemId" name="itemId" />
                        <input type="hidden" id="itemPid" name="itemPid" />
                        <input type="hidden" id="itemFlow" name="itemFlow" />
                    </td>
                    <td id="budgetInfo" style="text-align: center;"></td>
                    <td id="yetReimburse" style="color: red; text-align: center;"></td>
                    <td style="text-align: center;">
                        <input id="money" name="money" type="text" style="width: 90%;text-align: center;"
                               class="validate[required,custom[number],min[0]]"/>
                    </td>
                    <td style="text-align: center;">
                        <select class="validate[required]" id="realityTypeId" name="realityTypeId">
                            <option value="">请选择</option>
                            <c:forEach var="fundAccountsType" items="${projFundAccountsTypeEnumList}">
                                <option value="${fundAccountsType.id}">${fundAccountsType.name}</option>
                            </c:forEach>
                        </select></td>
                    <td style="text-align: center;">
                        <select class="validate[required]" id="fundRetype" name="fundRetype">
                            <option value="">请选择</option>
                            <c:forEach var="dict" items="${dictTypeEnumFundRetypeList}">
                                <option value="${dict.dictId}">${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td style="text-align: center;">
                        <input name="fundOperator" id="fundOperator" type="text" style="width: 90%;text-align: center;"
                               class="validate[required]"/>
                    </td>
                    <td style="text-align: center;">
                        <input name="content" type="text" style="width: 96%" class="validate[required]"/>
                    </td>
                    <td>
                        <a href="javascript:void(0);" onclick="reimburse();">报销</a>
                    </td>
                </tr>
            </table>
        </form>
        <br/>
        <table style="width: 100%;" class="bs_tb">
            <tr>
                <th width="100px;">报销项目</th>
                <th width="80px;">预算金额(元)</th>
                <th width="80px;">申请报销金额(元)</th>
                <th width="80px;">实际报销金额(元)</th>
                <th width="60px;">报销方式</th>
                <th width="100px;">报销时间</th>
                <th width="100px;">经办人</th>
                <th>报销内容</th>
                <th width="100px;">进度</th>
                <th width="160px;">操作</th>
            </tr>
            <tbody id="appendTbody">
            <c:forEach items="${fundDetailMap}" var="detailEntry">
                <%--<c:set var="budgetFundDtl" value="${budgetFundDtlMap[detailEntry.key]}" ></c:set>--%>
                <c:forEach items="${detailEntry.value}" var="detail" varStatus="status">
                    <tr>
                        <c:if test="${status.count==1}">
                            <td rowspan="${detailEntry.value.size()}">
                                ${budgetFundDtlMap[detailEntry.key].itemName}
                            </td>
                            <td rowspan="${detailEntry.value.size()}">
                                ${pdfn:transMultiply(budgetFundDtlMap[detailEntry.key].money,10000)}
                            </td>
                            <%--<td rowspan="${detailEntry.value.size()}">
                                    ${pdfn:getPercentByDouble(yetPaymentMap[detailEntry.key], budgetFundDtlMap[detailEntry.key].money)}
                            </td>--%>
                        </c:if>
                        <td class="${detail.itemFlow}">${pdfn:transMultiply(detail.money,10000)}</td>
                        <td>${pdfn:transMultiply(detail.realmoney,10000)}</td>
                        <td>${detail.realityTypeName}</td>
                        <td>
                                ${pdfn:transDateTime(detail.provideDateTime)}
                        </td>
                        <td>
                                ${detail.fundOperator}
                        </td>
                        <td>
                                ${detail.content}
                        </td>
                        <td>
                                ${detail.operStatusName}
                        </td>
                        <td>
                            <c:if test="${detail.operStatusId==achStatusEnumApply.id || detail.operStatusId==achStatusEnumFirstBack.id || detail.operStatusId==achStatusEnumSecondBack.id || detail.operStatusId==achStatusEnumThirdBack.id || detail.operStatusId==achStatusEnumFourthBack.id || detail.operStatusId==achStatusEnumFifthBack.id}">
                                <a href="javascript:void(0)"
                                   onclick="modify('${detail.fundDetailFlow}', '${detail.money}'  ,'${detail.itemId}', '${detail.fundOperator}','${detail.fundRetype}' ,'${detail.realityTypeId}', '${detail.content}')"
                                   id="modifyLink">[修改]</a>&nbsp;
                                <a href="javascript:void(0)" onclick="deleteDetail('${detail.fundDetailFlow}')">[删除]</a>
                            </c:if>
                            &nbsp;<a href="javascript:void(0)" onclick="findAuditDetail('${detail.fundDetailFlow}')">[审核意见]</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index:1000;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:210px;height: 300px"></ul>
</div>
<c:forEach var="budgetItem" items="${budgetFundDtlMap}">
    <input type="hidden" id="budget_${budgetItem.key}" value="${pdfn:transMultiply(budgetItem.value.money,10000)}"/>
</c:forEach>
</body>
</html>