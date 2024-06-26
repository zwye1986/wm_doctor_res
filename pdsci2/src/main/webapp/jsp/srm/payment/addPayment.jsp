<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="true"/>
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
        var yetAllocate = 0;
        var yetMatching= 0;
        var allocateMoney = 0;
        var matchingMoney = 0;
        function saveReimburse() {
            var tip = "";
            if (!$("#paymentForm").validationEngine("validate")) {
                return false;
            }
            var surplusMoney = 0;
            var realityTypeId = $("#realityTypeId").val();
            var itemName = $("#itemName").val();
            var currentAmount = parseFloat($('#money').val());
            if(realityTypeId == 'Allocate'){
                surplusMoney = allocateMoney-yetAllocate;
                if (currentAmount > surplusMoney*10000) {
                    tip = "<span style='color:red;'>本次报销中"+itemName+"超过预算，下拨预算"+allocateMoney*10000+"，下拨已报销"+yetAllocate*10000+"，本次报销"+currentAmount+"</span> ,";
                }
            }
            if(realityTypeId == 'Matching'){
                surplusMoney = matchingMoney-yetMatching;
                if (currentAmount > surplusMoney*10000) {
                    tip = "<span style='color:red;'>本次报销中"+itemName+"超过预算，配套预算"+matchingMoney*10000+"，配套已报销"+yetMatching*10000+"，本次报销"+currentAmount+"</span> ,";
                }
            }

            jboxConfirm(tip + "是否提交？", function () {
                var url = "<s:url value='/srm/payment/saveReimburse'/>";
                jboxPost(url, $("#paymentForm").serialize() , function(resp){
                    if(resp == '${GlobalConstant.OPRE_SUCCESSED}'){
                       // alert(resp);
                        window.parent.frames['mainIframe'].window.search("${fundInfo.fundFlow}");
                    }
                } , null , true);
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
                var yetReimburse = "<p id='allocateMoney'>下拨已报销金额："+resp.allocateMoney*10000+"</p>";
                yetReimburse += "<p id='matchingMoney'>配套已报销金额："+resp.matchingMoney*10000+"</p>";
                $("#yetReimburse").html(yetReimburse);
                yetAllocate = resp.allocateMoney;
                yetMatching = resp.matchingMoney;
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
            allocateMoney = (!treeNode.allocateMoney || treeNode.allocateMoney == 'null')?'':(treeNode.allocateMoney);
            matchingMoney = (!treeNode.matchingMoney || treeNode.matchingMoney == 'null')?'':(treeNode.matchingMoney);
            var budgetInfo = "<p>下拨金额："+allocateMoney*10000+"</p>";
            budgetInfo += "<p>配套金额："+matchingMoney*10000+"</p>";
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
    <style type="text/css">
        .xllist td{
            text-align: left;
        }
    </style>
</head>

<body>
<div style="height:450px;width:100%;overflow: auto;">
        <form id="paymentForm" action="<s:url value='/srm/payment/saveReimburse'/>" method="post" enctype="multipart/form-data">
            <input type="hidden" name="fundFlow" value="${fundInfo.fundFlow}"  />
            <%--<input type="hidden" id="fundDetailFlow" name="fundDetailFlow" value=""/>--%>
            <div>
                <table class="xllist nofix">
                    <tr>
                        <th colspan="3" style="font-size: 14px;">项目信息</th>
                    </tr>
                    <tr>
                        <td colspan="2"><span>项目名称：</span>${pubProj.projName}</td>
                        <td><span>年&#12288;份：</span>${pubProj.projYear}</td>

                        <%--<td colspan="2"><span>项目类型：</span>${detailExt.proj.projTypeName}</td>--%>
                    </tr>
                    <tr>
                        <td><span>课题编号：</span>${pubProj.projNo}</td>
                        <td><span>承担单位：</span>${pubProj.applyOrgName}</td>
                        <td><span>负责人：</span>${pubProj.applyUserName}</td>
                    </tr>
                    <tr>
                        <td width="30%"><span>一级来源：</span>${pubProj.projDeclarer}</td>
                        <td width="35%"><span>二级来源：</span>${pubProj.projSecondSourceName}</td>
                        <td width="35%"><span>起止日期：</span>${pubProj.projStartTime}~${pubProj.projEndTime}</td>
                    </tr>

                    <tr>
                        <td><span>预算总额：</span>${pdfn:transMultiply(fundInfo.budgetAmount,10000)}元</td>
                        <td><span>到账总额：</span>${pdfn:transMultiply(fundInfo.realityAmount,10000)}元</td>
                        <td><span>到账余额：</span><span style="font-weight: normal"
                                                        id="realityBalance">${pdfn:transMultiply(fundInfo.realityBalance,10000)}元</span></td>
                    </tr>
                </table>
            </div>
            <div>
                <table class="basic nofix" width="100%">
                    <tr>
                        <th colspan="4" style="font-size: 14px;text-align: center">报销明细</th>
                    </tr>
                    <tr>
                        <th><span>报销项目：</span></th>
                        <td >
                            <input type="text" id="itemName" name="itemName" class="validate[required] " style="width: 90%;" readonly="readonly"
                                   onclick="showMenu(); return false;"/>
                            <input type="hidden" id="itemId" name="itemId" />
                            <input type="hidden" id="itemPid" name="itemPid" />
                            <input type="hidden" id="itemFlow" name="itemFlow" />
                        </td>
                        <th >到账类型：</th>
                        <td >
                            <select name="fundSourceId" class="validate[required]" style="width: 90%;">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumProjFundAccountsTypeList}" var="dict" varStatus="status">
                                    <option value="${dict.dictId}" >${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">预算金额(元)：</th>
                        <td width="30%" id="budgetInfo"></td>
                        <th width="20%">已报销金额(元)：</th>
                        <td width="30%" id="yetReimburse" style="color: red; "></td>
                    </tr>
                    <tr>

                        <th >预算类型：</th>
                        <td >
                            <select class="validate[required]" id="realityTypeId" name="realityTypeId" style="width: 90%;">
                                <option value="">请选择</option>
                                <c:forEach var="fundAccountsType" items="${projFundAccountsTypeEnumList}">
                                    <option value="${fundAccountsType.id}">${fundAccountsType.name}</option>
                                </c:forEach>
                            </select></td>
                        <th>报销方式：</th>
                        <td>
                            <select class="validate[required]" style="width: 90%;" name="fundRetype">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumFundRetypeList}">
                                    <option value="${dict.dictId}">${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <%--<tr>


                        <th></th>
                        <td>
                        </td>
                    </tr>--%>
                    <tr>
                        <th >报销金额(元)：</th>
                        <td >
                            <input id="money" name="realmoney" type="text" style="width: 90%;"
                                   class="validate[required,custom[number],min[0]]"/>
                        </td>
                        <th >经办人：</th>
                        <td>
                        <input name="fundOperator" id="fundOperator" type="text" style="width: 90%;"
                               class="validate[required]"/>
                        </td>
                    </tr>
                    <tr>
                        <th>报销内容：</th>
                        <td colspan="3">
                            <input name="content" type="text" style="width: 96%" class="validate[required]"/>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
</div>
<p align="center" style="width:100%;padding-top: 10px;">
    <input type="button" onclick="saveReimburse();" class="search" value="确&#12288;认">
    <input type="button" onclick="jboxClose();" class="search" value="关&#12288;闭">
</p>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index:1000;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:210px;height: 300px"></ul>
</div>
</body>
</html>