<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        /*$(document).ready(function(){
         init();
         });*/

        function init() {
            var trLength = $("#expendBudgetTb tr").length;
            if (trLength < 10) {
                for (var i = 0; i < 10; i++) {
                    add('expendBudget');
                    $("#expendBudgetTb tr:eq(" + i + ") td:eq(0)").children().attr("disabled", "disabled");
                    var $input = $("#expendBudgetTb tr:eq(" + i + ") td:eq(2)").children();
                    $input.css("border-bottom-style", "none");
                    $input.attr("readonly", "readonly");
                    if (i == 0) {
                        $input.val("材料费");
                    } else if (i == 1) {
                        $input.val("测试化验加工费");
                    } else if (i == 2) {
                        $input.val("试剂耗材费");
                    } else if (i == 3) {
                        $input.val("燃料动力费");
                    } else if (i == 4) {
                        $input.val("差旅费");
                    } else if (i == 5) {
                        $input.val("会议费");
                    } else if (i == 6) {
                        $input.val("出版/文献/信息传播/知识产权事务费");
                    } else if (i == 7) {
                        $input.val("劳务费");
                    } else if (i == 8) {
                        $input.val("专家咨询费");
                    } else if (i == 9) {
                        $input.val("其他支出");
                    }
                }
            }
        }

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


        function budgetCalculate(index, obj) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var amountVal = 0;
            var expensesAmountVal = 0;
            //var cell = $(obj).parent()[0].cellIndex+1;
            //金额
            var num = 0;
            var unitPrice = 0;
            var $amount;
            if (index == 1) {
                num = $(obj).val();
                unitPrice = $(obj).parent().next().children().val();
                $amount = $(obj).parent().next().next().children();
            }
            if (index == 2) {
                num = $(obj).parent().prev().children().val();
                unitPrice = $(obj).val();
                $amount = $(obj).parent().next().children();
            }
            if (num == null || num == undefined || num == '' || isNaN(num)) {
                num = 0;
            }
            if (unitPrice == null || unitPrice == undefined || unitPrice == '' || isNaN(unitPrice)) {
                unitPrice = 0;
            }
            amountVal = num * unitPrice;
            $amount.val(parseFloat(amountVal.toFixed(3)));//三位有效小数

            //费用总计
            $("#expendBudgetTb tr td:nth-child(4)").each(function () {
                var val = $(this).children("input").val();
                if (val == null || val == undefined || val == '' || isNaN(val)) {
                    val = 0;
                }
                expensesAmountVal += parseFloat(val);
            });
            $("#expensesAmount").val(parseFloat(expensesAmountVal.toFixed(3)));
        }

        function fundCount() {
            var moneys = $("#payFund").find("input");
            $("input[name='amountAmount']").val(0);
            var amountMoneys = 0;
            $.each(moneys, function (i, n) {
                var re = /^[0-9]+.?[0-9]*$/;//正数正则
                var money = $(n).val();
                if (re.test(money)) {
                    amountMoneys += parseFloat(money);
                }
            });
            $("input[name='amountAmount']").val(parseFloat(amountMoneys.toFixed(4)));
        }
    </script>
</c:if>
<style>
    .disabled {
        border-bottom-style: none;
    }
</style>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
      id="projForm">
    <input type="hidden" id="pageName" name="pageName" value="step8"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">八、项目经费使用预算（经费单位：万元）</font>
    <table class="basic" style="width: 100%; margin-top: 10px;" id="payFund">
        <tr>
            <td>经费合计：</td>
            <td colspan="3">
                <input type="text" name="amountAmount" value="${resultMap.amountAmount}"
                       class="inputText" readonly="readonly" style="width: 40%"/>
            </td>
        </tr>
        <tbody>
        <tr>
            <td>
                1．无锡市卫计委资助：
            </td>
            <td>
                <input type="text" name="swsjbkAmount" value="${resultMap.swsjbkAmount}"
                       class="inputText validate[custom[number]]" style="width: 40%" onchange="fundCount()"/>
            </td>
            <td>
                3．上级主管部门资助：
            </td>
            <td>
                <input type="text" name="sjzgbmptAmount" value="${resultMap.sjzgbmptAmount}"
                       class="inputText validate[custom[number]]" style="width: 40%" onchange="fundCount()"/>
            </td>
        </tr>
        <tr>
            <td>
                2．依托单位资助：
            </td>
            <td><input type="text" name="zddwzcAmount" value="${resultMap.zddwzcAmount}"
                       class="inputText validate[custom[number]]" style="width: 40%" onchange="fundCount()"/>
            </td>
            <td>
                4．其它渠道资助：
            </td>
            <td><input type="text" name="qtlyAmount" value="${resultMap.qtlyAmount}"
                       class="inputText validate[custom[number]]" style="width: 40%" onchange="fundCount()"/>
            </td>
        </tr>
    </table>

    <table class="bs_tb" id="expendBudgetTb" style="width: 100%;margin-top: 10px; border-bottom-style: none;">
        <tr>
            <th colspan="10" class="theader">支出预算:</th>
        </tr>
        <tr>
            <td width="24%">支出科目</td>
            <td width="8%">数量</td>
            <td width="10%">单价</td>
            <td width="15%">金额</td>
            <td width="12%">其中：市卫计委资助额</td>
            <td width="14%">备注/计算依据</td>
        </tr>
        <tr>
            <td>专业仪器设备费</td>
            <td><input type="text" name="expend1Cell1" value="${resultMap.expend1Cell1}"
                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[integer,maxSize[6]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend1Cell2" value="${resultMap.expend1Cell2}"
                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number,max[100000]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend1Cell3" value="${resultMap.expend1Cell3}" class="disabled inputText"
                       style="width: 80%" readonly="readonly"/></td>
            <td><input type="text" name="expend1Cell4" value="${resultMap.expend1Cell4}"
                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
            <td><input type="text" name="expend1Cell5" value="${resultMap.expend1Cell5}" class="inputText"
                       style="width: 80%"/></td>
        </tr>
        <tr>
            <td>试验材料费</td>
            <td><input type="text" name="expend2Cell1" value="${resultMap.expend2Cell1}"
                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[integer,maxSize[6]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend2Cell2" value="${resultMap.expend2Cell2}"
                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number,max[100000]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend2Cell3" value="${resultMap.expend2Cell3}" class="disabled inputText"
                       style="width: 80%" readonly="readonly"/></td>
            <td><input type="text" name="expend2Cell4" value="${resultMap.expend2Cell4}"
                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
            <td><input type="text" name="expend2Cell5" value="${resultMap.expend2Cell5}" class="inputText"
                       style="width: 80%"/></td>
        </tr>
        <tr>
            <td>科研业务费（测试、化验、加工、燃料动力等）</td>
            <td><input type="text" name="expend3Cell1" value="${resultMap.expend3Cell1}"
                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[integer,maxSize[6]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend3Cell2" value="${resultMap.expend3Cell2}"
                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number,max[100000]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend3Cell3" value="${resultMap.expend3Cell3}" class="disabled inputText"
                       style="width: 80%" readonly="readonly"/></td>
            <td><input type="text" name="expend3Cell4" value="${resultMap.expend3Cell4}"
                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
            <td><input type="text" name="expend3Cell5" value="${resultMap.expend3Cell5}" class="inputText"
                       style="width: 80%"/></td>
        </tr>
        <tr>
            <td>会议费</td>
            <td><input type="text" name="expend4Cell1" value="${resultMap.expend4Cell1}"
                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[integer,maxSize[6]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend4Cell2" value="${resultMap.expend4Cell2}"
                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number,max[100000]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend4Cell3" value="${resultMap.expend4Cell3}" class="disabled inputText"
                       style="width: 80%" readonly="readonly"/></td>
            <td><input type="text" name="expend4Cell4" value="${resultMap.expend4Cell4}"
                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
            <td><input type="text" name="expend4Cell5" value="${resultMap.expend4Cell5}" class="inputText"
                       style="width: 80%"/></td>
        </tr>
        <tr>
            <td>合作交流费</td>
            <td><input type="text" name="expend5Cell1" value="${resultMap.expend5Cell1}"
                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[integer,maxSize[6]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend5Cell2" value="${resultMap.expend5Cell2}"
                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number,max[100000]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend5Cell3" value="${resultMap.expend5Cell3}" class="disabled inputText"
                       style="width: 80%" readonly="readonly"/></td>
            <td><input type="text" name="expend5Cell4" value="${resultMap.expend5Cell4}"
                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
            <td><input type="text" name="expend5Cell5" value="${resultMap.expend5Cell5}" class="inputText"
                       style="width: 80%"/></td>
        </tr>
        <tr>
            <td>出版／文献／信息传播／知识产权事务费</td>
            <td><input type="text" name="expend6Cell1" value="${resultMap.expend6Cell1}"
                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[integer,maxSize[6]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend6Cell2" value="${resultMap.expend6Cell2}"
                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number,max[100000]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend6Cell3" value="${resultMap.expend6Cell3}" class="disabled inputText"
                       style="width: 80%" readonly="readonly"/></td>
            <td><input type="text" name="expend6Cell4" value="${resultMap.expend6Cell4}"
                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
            <td><input type="text" name="expend6Cell5" value="${resultMap.expend6Cell5}" class="inputText"
                       style="width: 80%"/></td>
        </tr>
        <tr>
            <td>专家咨询费</td>
            <td><input type="text" name="expend7Cell1" value="${resultMap.expend7Cell1}"
                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[integer,maxSize[6]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend7Cell2" value="${resultMap.expend7Cell2}"
                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number,max[100000]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend7Cell3" value="${resultMap.expend7Cell3}" class="disabled inputText"
                       style="width: 80%" readonly="readonly"/></td>
            <td><input type="text" name="expend7Cell4" value="${resultMap.expend7Cell4}"
                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
            <td><input type="text" name="expend7Cell5" value="${resultMap.expend7Cell5}" class="inputText"
                       style="width: 80%"/></td>
        </tr>
        <tr>
            <td>劳务费</td>
            <td><input type="text" name="expend8Cell1" value="${resultMap.expend8Cell1}"
                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[integer,maxSize[6]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend8Cell2" value="${resultMap.expend8Cell2}"
                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number,max[100000]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend8Cell3" value="${resultMap.expend8Cell3}" class="disabled inputText"
                       style="width: 80%" readonly="readonly"/></td>
            <td><input type="text" name="expend8Cell4" value="${resultMap.expend8Cell4}"
                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
            <td><input type="text" name="expend8Cell5" value="${resultMap.expend8Cell5}" class="inputText"
                       style="width: 80%"/></td>
        </tr>
        <tr>
            <td>项目组织实施费</td>
            <td><input type="text" name="expend9Cell1" value="${resultMap.expend9Cell1}"
                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[integer,maxSize[6]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend9Cell2" value="${resultMap.expend9Cell2}"
                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number,max[100000]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend9Cell3" value="${resultMap.expend9Cell3}" class="disabled inputText"
                       style="width: 80%" readonly="readonly"/></td>
            <td><input type="text" name="expend9Cell4" value="${resultMap.expend9Cell4}"
                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
            <td><input type="text" name="expend9Cell5" value="${resultMap.expend9Cell5}" class="inputText"
                       style="width: 80%"/></td>
        </tr>
        <tr>
            <td>其他</td>
            <td><input type="text" name="expend10Cell1" value="${resultMap.expend10Cell1}"
                       onchange="budgetCalculate(1,this)" class="inputText validate[custom[integer,maxSize[6]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend10Cell2" value="${resultMap.expend10Cell2}"
                       onchange="budgetCalculate(2,this)" class="inputText validate[custom[number,max[100000]]]"
                       style="width: 80%"/></td>
            <td><input type="text" name="expend10Cell3" value="${resultMap.expend10Cell3}" class="disabled inputText"
                       style="width: 80%" readonly="readonly"/></td>
            <td><input type="text" name="expend10Cell4" value="${resultMap.expend10Cell4}"
                       class="inputText validate[custom[number]]" style="width: 80%"/></td>
            <td><input type="text" name="expend10Cell5" value="${resultMap.expend10Cell5}" class="inputText"
                       style="width: 80%"/></td>
        </tr>
        <tr>
            <td colspan="8" style="text-align:left;">
                &#12288;<span style="color: red;">费用总计：</span><input type="text" id="expensesAmount"
                                                                     name="expensesAmount"
                                                                     value="${resultMap.expensesAmount}"
                                                                     class="disabled inputText"
                                                                     style="width: 100px; color: red;"
                                                                     readonly="readonly"/>
            </td>
        </tr>
    </table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step7')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step9')" class="search" value="下一步"/>
    </div>
</c:if>
		