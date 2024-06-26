<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="true"/>
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
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }
        function defaultIfEmpty(obj) {
            if (isNaN(obj)) {
                return 0;
            } else {
                return obj;
            }
        }

        function calculate() {
            var amountFund = 0.0;
            var constructOrgFund = parseFloat($("input[name='constructOrgFund']").val());
            var assumeOrgFund = parseFloat($("input[name='assumeOrgFund']").val());
            var thirdFund = parseFloat($("input[name='thirdFund']").val());
            var otherFund = parseFloat($("input[name='otherFund']").val());
            amountFund = defaultIfEmpty(constructOrgFund) + defaultIfEmpty(assumeOrgFund) + defaultIfEmpty(thirdFund) + defaultIfEmpty(otherFund);

            $("input[name='amountFund']").val(parseFloat(amountFund.toFixed(3)));
        }

        function budgetCalculate(index, obj) {
            var sumVal = 0;
            var cell = $(obj).parent()[0].cellIndex + 1;
            $("#fund2 tr:not(:last) td:nth-child(" + cell + ")").each(function () {
                var val = $(this).children("input").val();

                if (val == null || val == undefined || val == '' || isNaN(val)) {
                    val = 0;
                }
                if (parseFloat(val) < 0) {
                    return;
                }
                sumVal = parseFloat(sumVal) + parseFloat(val);
            });
            if (index == 1) {
                sumInput = $("input[name=amountFirstYear]");
            } else if (index == 2) {
                sumInput = $("input[name=amountSecondYear]");
            } else if (index == 3) {
                sumInput = $("input[name=amountThirdYear]");
            } else if (index == 4) {
                sumInput = $("input[name=amountFourthYear]");
            } else if (index == 5) {
                sumInput = $("input[name=amountFifthYear]");
            }
            sumInput.val(parseFloat(sumVal.toFixed(3)));
        }

    </script>
    <style type="text/css">
        .bs_tb tbody th {
            background: #fff;
        }

        .bs_tb tbody td {
            text-align: left;
        }

        .bs_tb tbody td input {
            text-align: left;
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step6"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
                    <table id="fund1" style="width: 100%;margin-top: 10px;" cellspacing="0" cellpadding="0"
                           class="basic">
                        <tr>
                            <th colspan="4" style="text-align: left;padding-left: 15px;font-size: 14px;">六、经费预算</th>
                        </tr>
                        <tr>
                            <td width="200px;" style="text-align:center;">甲方资助金额：</td>
                            <td>
                                <input name="constructOrgFund" value="${resultMap.constructOrgFund}"
                                       onchange="calculate()" type="text" class="inputText validate[custom[number]]"
                                       style="margin-right: 0px; width:26%; margin-left: 22px;"/>（万元）
                            </td>
                            <td style="text-align:center;">乙方匹配金额：</td>
                            <td>
                                <input name="assumeOrgFund" value="${resultMap.assumeOrgFund}" onchange="calculate()"
                                       type="text" class="inputText validate[custom[number]]"
                                       style="margin-right: 0px; width:26%; margin-left: 22px;"/>（万元）
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:center;">丙方匹配金额：</td>
                            <td>
                                <input name="thirdFund" value="${resultMap.thirdFund}" onchange="calculate()"
                                       type="text" class="inputText validate[custom[number]]"
                                       style="margin-right: 0px; width:26%; margin-left: 22px;"/>（万元）
                            </td>
                            <td style="text-align:center;">其他经费来源：</td>
                            <td>
                                <input name="otherFund" value="${resultMap.otherFund}" onchange="calculate()"
                                       type="text" class="inputText validate[custom[number]]"
                                       style="margin-right: 0px; width:26%; margin-left: 22px;"/>（万元）
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:center;">合&#12288;&#12288;计：</td>
                            <td>
                                <input name="amountFund" value="${resultMap.amountFund}" type="text" readonly="readonly"
                                       class="inputText validate[custom[number]] amount"
                                       style="margin-right: 0px; width:26%; margin-left: 22px;"/>（万元）
                            </td>
                        </tr>
                    </table>

                    <table id="fund2" style="width: 100%;border-top: none;" cellspacing="0" cellpadding="0"
                           class="basic">
                        <tr>
                            <th style="text-align:center;width:200px;">申请资助的预算支出科目</th>
                            <th style="text-align: center;">2016年</th>
                            <th style="text-align: center;">2017年</th>
                            <th style="text-align: center;">2018年</th>
                            <th style="text-align: center;">2019年</th>
                            <th style="text-align: center;">2020年</th>
                        </tr>
                        <tr>
                            <td >1、国内外进修费用</td>
                            <td><input type="text" name="inlandFirstYear" value="${resultMap.inlandFirstYear}"
                                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="inlandSecondYear" value="${resultMap.inlandSecondYear}"
                                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="inlandThirdYear" value="${resultMap.inlandThirdYear}"
                                       onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="inlandFourthYear" value="${resultMap.inlandFourthYear}"
                                       onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="inlandFifthYear" value="${resultMap.inlandFifthYear}"
                                       onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                        </tr>
                        <tr>
                            <td >2、学术交流费用</td>
                            <td><input type="text" name="communicationFirstYear"
                                       value="${resultMap.communicationFirstYear}" onchange="budgetCalculate(1,this)"
                                       class="inputText validate[custom[number]]" style="width:80%;"/></td>
                            <td><input type="text" name="communicationSecondYear"
                                       value="${resultMap.communicationSecondYear}" onchange="budgetCalculate(2,this)"
                                       class="inputText validate[custom[number]]" style="width:80%;"/></td>
                            <td><input type="text" name="communicationThirdYear"
                                       value="${resultMap.communicationThirdYear}" onchange="budgetCalculate(3,this)"
                                       class="inputText validate[custom[number]]" style="width:80%;"/></td>
                            <td><input type="text" name="communicationFourthYear"
                                       value="${resultMap.communicationFourthYear}" onchange="budgetCalculate(4,this)"
                                       class="inputText validate[custom[number]]" style="width:80%;"/></td>
                            <td><input type="text" name="communicationFifthYear"
                                       value="${resultMap.communicationFifthYear}" onchange="budgetCalculate(5,this)"
                                       class="inputText validate[custom[number]]" style="width:80%;"/></td>
                        </tr>
                        <tr>
                            <td>3、仪器设备费</td>
                            <td><input type="text" name="instrumentFirstYear" value="${resultMap.instrumentFirstYear}"
                                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="instrumentSecondYear" value="${resultMap.instrumentSecondYear}"
                                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="instrumentThirdYear" value="${resultMap.instrumentThirdYear}"
                                       onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="instrumentFourthYear" value="${resultMap.instrumentFourthYear}"
                                       onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="instrumentFifthYear" value="${resultMap.instrumentFifthYear}"
                                       onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                        </tr>

                        <tr>
                            <td>4、实验材料费</td>
                            <td><input type="text" name="experimentMaterialFirstYear"
                                       value="${resultMap.experimentMaterialFirstYear}"
                                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="experimentMaterialSecondYear"
                                       value="${resultMap.experimentMaterialSecondYear}"
                                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="experimentMaterialThirdYear"
                                       value="${resultMap.experimentMaterialThirdYear}"
                                       onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="experimentMaterialFourthYear"
                                       value="${resultMap.experimentMaterialFourthYear}"
                                       onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="experimentMaterialFifthYear"
                                       value="${resultMap.experimentMaterialFifthYear}"
                                       onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                        </tr>
                        <tr>
                            <td>5、实验动物费</td>
                            <td><input type="text" name="experimentAnimalTrFirstYear"
                                       value="${resultMap.experimentAnimalTrFirstYear}"
                                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="experimentAnimalTrSecondYear"
                                       value="${resultMap.experimentAnimalTrSecondYear}"
                                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="experimentAnimalTrThirdYear"
                                       value="${resultMap.experimentAnimalTrThirdYear}"
                                       onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="experimentAnimalTrFourthYear"
                                       value="${resultMap.experimentAnimalTrFourthYear}"
                                       onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="experimentAnimalTrFifthYear"
                                       value="${resultMap.experimentAnimalTrFifthYear}"
                                       onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                        </tr>
                        <tr>
                            <td>6、人员培养费用</td>
                            <td><input type="text" name="cultivateFirstYear" value="${resultMap.cultivateFirstYear}"
                                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="cultivateSecondYear" value="${resultMap.cultivateSecondYear}"
                                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="cultivateThirdYear" value="${resultMap.cultivateThirdYear}"
                                       onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="cultivateFourthYear" value="${resultMap.cultivateFourthYear}"
                                       onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="cultivateFifthYear" value="${resultMap.cultivateFifthYear}"
                                       onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                        </tr>
                        <tr>
                            <td>7、购买图书、资料费</td>
                            <td><input type="text" name="bookFirstYear" value="${resultMap.bookFirstYear}"
                                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="bookSecondYear" value="${resultMap.bookSecondYear}"
                                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="bookThirdYear" value="${resultMap.bookThirdYear}"
                                       onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="bookFourthYear" value="${resultMap.bookFourthYear}"
                                       onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="bookFifthYear" value="${resultMap.bookFifthYear}"
                                       onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                        </tr>
                        <tr>
                            <td>8、研究项目费用</td>
                            <td><input type="text" name="researchProjFirstYear"
                                       value="${resultMap.researchProjFirstYear}" onchange="budgetCalculate(1,this)"
                                       class="inputText validate[custom[number]]" style="width:80%;"/></td>
                            <td><input type="text" name="researchProjSecondYear"
                                       value="${resultMap.researchProjSecondYear}" onchange="budgetCalculate(2,this)"
                                       class="inputText validate[custom[number]]" style="width:80%;"/></td>
                            <td><input type="text" name="researchProjThirdYear"
                                       value="${resultMap.researchProjThirdYear}" onchange="budgetCalculate(3,this)"
                                       class="inputText validate[custom[number]]" style="width:80%;"/></td>
                            <td><input type="text" name="researchProjFourthYear"
                                       value="${resultMap.researchProjFourthYear}" onchange="budgetCalculate(4,this)"
                                       class="inputText validate[custom[number]]" style="width:80%;"/></td>
                            <td><input type="text" name="researchProjFifthYear"
                                       value="${resultMap.researchProjFifthYear}" onchange="budgetCalculate(5,this)"
                                       class="inputText validate[custom[number]]" style="width:80%;"/></td>
                        </tr>
                        <tr>
                            <td>9、其他费用</td>
                            <td><input type="text" name="otherFirstYear" value="${resultMap.otherFirstYear}"
                                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="otherSecondYear" value="${resultMap.otherSecondYear}"
                                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="otherThirdYear" value="${resultMap.otherThirdYear}"
                                       onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="otherFourthYear" value="${resultMap.otherFourthYear}"
                                       onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                            <td><input type="text" name="otherFifthYear" value="${resultMap.otherFifthYear}"
                                       onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]"
                                       style="width:80%;"/></td>
                        </tr>
                        <tr>
                            <td>合&#12288;&#12288;&#12288;计</td>
                            <td><input type="text" name="amountFirstYear" value="${resultMap.amountFirstYear}" readonly="readonly"
                                       class="inputText validate[custom[number]] amount" style="width:80%;"/></td>
                            <td><input type="text" name="amountSecondYear" value="${resultMap.amountSecondYear}" readonly="readonly"
                                       class="inputText validate[custom[number]] amount" style="width:80%;"/></td>
                            <td><input type="text" name="amountThirdYear" value="${resultMap.amountThirdYear}" readonly="readonly"
                                       class="inputText validate[custom[number]] amount" style="width:80%;"/></td>
                            <td><input type="text" name="amountFourthYear" value="${resultMap.amountFourthYear}" readonly="readonly"
                                       class="inputText validate[custom[number]] amount" style="width:80%;"/></td>
                            <td><input type="text" name="amountFifthYear" value="${resultMap.amountFifthYear}" readonly="readonly"
                                       class="inputText validate[custom[number]] amount" style="width:80%;"/></td>
                        </tr>
                    </table>
                    <table style="width: 100%;margin-top: 10px;" cellspacing="0" cellpadding="0"
                           class="basic" >
                        <tr>
                            <td style="text-align:center;width: 200px">补充说明</td>
                            <td>
                                <textarea placeholder="" class="xltxtarea" name="supplementary_notes">${resultMap.supplementary_notes}</textarea>
                            </td>
                        </tr>
                    </table>
                    <table class="basic tbdashed" style="width: 100%; margin-top: 10px;border:1px dashed #f2d4b2;">
                        <tr><td style="border:1px dashed #f2d4b2;font-weight:bold;">填表说明：</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;1、经费预算请遵照《省政府关于深化省级财政科研项目和资金管理改革的意见》（苏政发〔2015〕15号）编制。</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;2、仪器设备费：指项目专用仪器的购置费和运杂、包装、安装费、自制仪器设备的材料、配件和外协加工费。</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;3、大型仪器设备应充分利用本单位、本地区现有条件。</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;4、交通运输设备一般不得列入，如特殊需要应说明理由，经批准后按程序采购。</td></tr>
                        <tr><td style="border:1px dashed #f2d4b2;">&#12288;5、单台件在5千元以上的仪器设备须逐项填写名称、规格、型号、单价、数量。</td></tr>
                    </table>

                </form>
                <div class="button"
                     style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                    <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
                    <input id="prev" type="button" onclick="nextOpt('finish')" class="search" value="完&#12288;成"/>
                    <%--<a href="javascript:void(0)" target="_self" class="search" onclick="nextOpt('step5')">上一步</a>--%>
                    <%--<a href="javascript:void(0)" target="_self" class="search" onclick="nextOpt('finish')">完成</a>--%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		