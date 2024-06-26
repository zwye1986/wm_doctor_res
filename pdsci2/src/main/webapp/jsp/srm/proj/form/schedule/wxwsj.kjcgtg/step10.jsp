<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
            $('#nxt').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }
        function add(templateId) {
            if (templateId) {
                $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                reSeq(templateId);
            }
        }

        function del(templateId) {
            if (templateId) {
                if (!$('.' + templateId + ' .toDel:checked').length) {
                    return jboxTip('请选择需要删除的项目！');
                }
                jboxConfirm('确认删除？', function () {
                    $('.' + templateId + ' .toDel:checked').closest('tr').remove();
                    reSeq(templateId);
                    payAmount();
                }, null);
            }
        }

        function reSeq(templateId) {
            $('.' + templateId + ' .seq').each(function (i, n) {
                $(n).text(i + 1);
            });
        }

        $(function () {
            $('#template tr').each(function () {
                var id = this.id;
                if (id) {
                    if (!$('.' + id + ' .toDel').length) {
                        add(id);
                    }
                }
            });
        });
        function fundCount() {
            var moneys = $("#payFund").find("input");
            $("input[name='fundAmount']").val(0);
            var amountMoneys = 0;
            $.each(moneys, function (i, n) {
                var re = /^[0-9]+.?[0-9]*$/;//正数正则
                var money = $(n).val();
                if (re.test(money)) {
                    amountMoneys += parseFloat(money);
                }
            });
            $("input[name='fundAmount']").val(amountMoneys.toFixed(4));
        }
        function getMoney(td) {

            var nums = $(td).parent().parent().find("td input[name='payBudgets_num']");
            var prices = $(td).parent().parent().find("td input[name='payBudgets_price']");
            var num = parseFloat($(nums).val());
            var price = parseFloat($(prices).val());
            if (isNaN(num)) {
                num = 0;
            }
            if (isNaN(price)) {
                 price = 0;
            }
            var countPrice = num * price;
            $(td).parent().parent().find("input[name='payBudgets_fund']").val("");
            $(td).parent().parent().find("input[name='payBudgets_fund']").val(countPrice);
            payAmount();
        }
        function payAmount() {
            var amountMoney = 0;
            var moneys = $(".payBudget").find("input[name='payBudgets_fund']");
            $.each(moneys, function (i, n) {
                var money = $(n).val();
                amountMoney += parseFloat(money);
            });
            $("#pay_amount").val(amountMoney);
        }
    </script>
</c:if>
<style type="text/css">
    .title_sp {
        padding-left: 10px;
        font-size: 14px;
        margin-bottom: 10px;
        margin-top: 10px;
        font-weight: bold;
        color: #333;
    }
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" name="pageName" value="step10"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
    <font style="font-size: 14px; font-weight:bold;color: #333;">九、项目经费使用预算（经费单位：万元）</font>
    <div style="margin-top: 10px;">
        <table width="100%" cellspacing="0" cellpadding="0" class="bs_tb" id="payFund">
            <tr>
                <td class="xlming">经费合计:</td>
                <td colspan="3" class="xlming"><input type="text" class="inputText validate[custom[number]]"
                                                      name="fundAmount" value="${resultMap.fundAmount }"
                                                      readonly="readonly" width="200px;"/></td>
            </tr>
            <tr>
                <td class="xlming">1、无锡市卫生计生委资助</td>
                <td class="xlming"><input type="text" class="inputText validate[custom[number]]" name="healthBureau"
                                          value="${resultMap.healthBureau }" onblur="fundCount()"/></td>
                <td class="xlming">3、上级主管部门资助</td>
                <td class="xlming"><input type="text" class="inputText validate[custom[number]]" name="chargeFund"
                                          value="${resultMap.chargeFund }" onblur="fundCount()"/></td>
            </tr>
            <tr>
                <td class="xlming">2、项目单位资助</td>
                <td class="xlming"><input type="text" class="inputText validate[custom[number]]" name="companyFund"
                                          value="${resultMap.companyFund }" onblur="fundCount()"/></td>
                <td class="xlming">4、其它渠道资助</td>
                <td class="xlming"><input type="text" class="inputText validate[custom[number]]" name="otherFund"
                                          value="${resultMap.otherFund }" onblur="fundCount()"/></td>
            </tr>
        </table>
    </div>

    <div style="margin-top: 10px;">
        <table width="100%" cellspacing="0" cellpadding="0" class="basic">
            <tr>
                <th style="text-align: left;padding-left: 15px;" colspan="7">
                    支出预算:
                    <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('payBudget');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('payBudget');"/>
						</span>
                    </c:if>
                </th>
            </tr>
            <tr>
                <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                    <td style="text-align: center;" width="5%">选择</td>
                </c:if>
                <td style="text-align: center;" width="20%">支出科目<font color="red">*</font></td>
                <td style="text-align: center;" width="10%">数量<font color="red">*</font></td>
                <td style="text-align: center;" width="10%">单价<font color="red">*</font></td>
                <td style="text-align: center;" width="10%">金额<font color="red">*</font></td>
                <td style="text-align: center;" width="15%">其中：市卫生计生委资助<font color="red">*</font></td>
                <td style="text-align: center;" width="20%">备注/计算依据</td>
            </tr>
            <tbody class="payBudget">
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <c:forEach var="payBudget" items="${resultMap.payBudgets}" varStatus="payBudgetsStatus">
                    <tr>
                        <td><input type="checkbox" class="toDel"></td>
                            <%--<td class="seq">${memberListStatus.count}</td>--%>
                        <td>
                            <input type="text" class="inputText validate[required]" name="payBudgets_subject"
                                   value="${payBudget.objMap.payBudgets_subject}" style="width: 80%"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required,custom[number]]"
                                   name="payBudgets_num"
                                   value="${payBudget.objMap.payBudgets_num}" style="width: 80%"
                                   onchange="getMoney(this)"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required,custom[number]]" name="payBudgets_price"
                                   value="${payBudget.objMap.payBudgets_price}" style="width: 80%"
                                   onchange="getMoney(this)"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="payBudgets_fund"
                                   value="${payBudget.objMap.payBudgets_fund}" style="width: 80%" readonly="readonly"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="payBudgets_health"
                                   value="${payBudget.objMap.payBudgets_health}" style="width: 80%"/>
                        </td>
                        <td>
                            <input type="text" class="inputText" name="payBudgets_remarks"
                                   value="${payBudget.objMap.payBudgets_remarks}" style="width: 80%"/>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                <c:forEach var="payBudget" items="${resultMap.payBudgets}" varStatus="payBudgetsStatus">
                    <tr>
                            <%-- <td>${memberListStatus.count}</td>--%>
                        <td>
                                ${payBudget.objMap.payBudgets_subject}
                        </td>
                        <td>
                                ${payBudget.objMap.payBudgets_num}
                        </td>
                        <td>
                                ${payBudget.objMap.payBudgets_price}
                        </td>
                        <td>
                                ${payBudget.objMap.payBudgets_fund}
                        </td>
                        <td>
                                ${payBudget.objMap.payBudgets_health}
                        </td>
                        <td>
                                ${payBudget.objMap.payBudgets_remarks}
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
            <tr>
                <td style="text-align: right;padding-right: 15px;" colspan="7">
                    费用总计：<input type="text" class="inputText validate[required]" name="pay_amount" id="pay_amount"
                                value="${resultMap.pay_amount}" style="width:200px;" readonly="readonly"/>
                </td>
            </tr>
        </table>
    </div>
</form>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step9')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step11')" class="search" value="下一步"/>
    </div>
</c:if>
<table id="template" style="display: none">
    <tr id="payBudget">
        <td><input type="checkbox" class="toDel"></td>
        <td>
            <input type="text" class="inputText validate[required]" name="payBudgets_subject" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required,custom[number]]" name="payBudgets_num" style="width: 80%"
                   onchange="getMoney(this)"/>
        </td>

        <td>
            <input type="text" class="inputText validate[required,custom[number]]" name="payBudgets_price" style="width: 80%"
                   onchange="getMoney(this)"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="payBudgets_fund" style="width: 80%"
                   readonly="readonly"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="payBudgets_health" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="payBudgets_remarks" style="width: 80%"/>
        </td>
    </tr>
</table>
	