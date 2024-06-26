<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            $('#nxt').attr({"disabled":"disabled"});
            $('#prev').attr({"disabled":"disabled"});
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }

        function calculationFunds() {
            var amountBudget = 0;
            var fundSupport = 0;
            var fundOneself = 0;

            var amountBudget_1 = 0;
            var fundSupport_1 = 0;
            var fundOneself_1 = 0;

            var amountBudget_2 = 0;
            var fundSupport_2 = 0;
            var fundOneself_2 = 0;

            var amountBudget_14 = 0;
            var fundSupport_14 = 0;
            var fundOneself_14 = 0;

            for (var i = 3; i < 19; i++) {
                var budget = parseFloat($("input[name='amountBudget_" + i + "']").val());
                var support = parseFloat($("input[name='fundSupport_" + i + "']").val());
                var oneself = parseFloat($("input[name='fundOneself_" + i + "']").val());
                if (!budget) {
                    budget = 0;
                }
                if (!support) {
                    support = 0;
                }
                if (!oneself) {
                    oneself = 0;
                }
                if (i < 6) {
                    amountBudget_2 += budget;
                    fundSupport_2 += support;
                    fundOneself_2 += oneself;
                }
                if (i < 14) {
                    amountBudget_1 += budget;
                    fundSupport_1 += support;
                    fundOneself_1 += oneself;
                }
                if(i>14 && i < 19){
                    amountBudget_14 += budget;
                    fundSupport_14 += support;
                    fundOneself_14 += oneself;
                }
                if(i != 14){
                    amountBudget += budget;
                    fundSupport += support;
                    fundOneself += oneself;
                }
            }

            $("input[name='amountBudget']").val(parseFloat(amountBudget.toFixed(4)));
            $("input[name='fundSupport']").val(parseFloat(fundSupport.toFixed(4)));
            $("input[name='fundOneself']").val(parseFloat(fundOneself.toFixed(4)));

            $("input[name='amountBudget_1']").val(parseFloat(amountBudget_1.toFixed(4)));
            $("input[name='fundSupport_1']").val(parseFloat(fundSupport_1.toFixed(4)));
            $("input[name='fundOneself_1']").val(parseFloat(fundOneself_1.toFixed(4)));

            $("input[name='amountBudget_2']").val(parseFloat(amountBudget_2.toFixed(4)));
            $("input[name='fundSupport_2']").val(parseFloat(fundSupport_2.toFixed(4)));
            $("input[name='fundOneself_2']").val(parseFloat(fundOneself_2.toFixed(4)));

            $("input[name='amountBudget_14']").val(parseFloat(amountBudget_14.toFixed(4)));
            $("input[name='fundSupport_14']").val(parseFloat(fundSupport_14.toFixed(4)));
            $("input[name='fundOneself_14']").val(parseFloat(fundOneself_14.toFixed(4)));
        }

        $(function () {
            /* $("input[name^='amountBudget_']").bind("change", function () {//name属性以amountBudget_开始的所有input标签失去焦点
             var budget = parseFloat($(this).val());
             var index = $(this).attr("name").split("_")[1];
             var autonomy = parseFloat($("input[name='fundAutonomy_" + index + "']").val());
             if (!budget) {
             budget = 0;
             }
             if (!autonomy) {
             autonomy = 0;
             }
             if (autonomy > budget) {
             jboxTip("预算数不能小于拨款自主");
             $(this).val(autonomy);
             }
             calculationFunds();
             });*/
            $("input[name^='fundSupport_']").bind("change", function () {//name属性以amountBudget_开始的所有input标签失去焦点
                var support = parseFloat($(this).val());
                var index = $(this).attr("name").split("_")[1];
                var oneself = parseFloat($("input[name='fundOneself_" + index + "']").val());
                if (!oneself) {
                    oneself = 0;
                }
                if (!support) {
                    support = 0;
                }
                var amount = oneself+support;
                $("input[name='amountBudget_" + index + "']").val(parseFloat(amount.toFixed(4)));
                calculationFunds();
            });

            $("input[name^='fundOneself_']").bind("change", function () {//name属性以amountBudget_开始的所有input标签失去焦点
                var oneself = parseFloat($(this).val());
                var index = $(this).attr("name").split("_")[1];
                var support = parseFloat($("input[name='fundSupport_" + index + "']").val());
                if (!oneself) {
                    oneself = 0;
                }
                if (!support) {
                    support = 0;
                }
                var amount = oneself+support;
                $("input[name='amountBudget_" + index + "']").val(parseFloat(amount.toFixed(4)));
                calculationFunds();
            });
        });
    </script>
</c:if>
<style type="text/css">
    /*.basic tbody th {
        text-align: center;
    }*/
    .readonlycss {
        background-color: #EEEEEE;
    }
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
    <input type="hidden" id="pageName" name="pageName" value="step7"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">项目经费预算</font>
    <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>

            <th style="width: 20%;text-align: center">科&#12288;目</th>
            <th style="width: 20%;text-align: center">总预算</th>
            <th style="width: 20%;text-align: center">资助经费</th>
            <th style="width: 20%;text-align: center">自筹经费</th>
            <th style="text-align: center">备&#12288;注</th>
        </tr>

        <tr>
            <th style="text-align: left">（一）直接费用</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_1" value="<c:if test="${empty resultMap.amountBudget_1}">${applyMap.amountBudget_1}</c:if>${resultMap.amountBudget_1}"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="fundSupport_1" value="<c:if test="${empty resultMap.fundSupport_1}">${applyMap.fundSupport_1}</c:if>${resultMap.fundSupport_1}"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="fundOneself_1" value="<c:if test="${empty resultMap.fundOneself_1}">${applyMap.fundOneself_1}</c:if>${resultMap.fundOneself_1}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_1"
                       value="<c:if test="${empty resultMap.fundRemark_1}">${applyMap.fundRemark_1}</c:if>${resultMap.fundRemark_1}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">1、设备费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_2" value="<c:if test="${empty resultMap.amountBudget_2}">${applyMap.amountBudget_2}</c:if>${resultMap.amountBudget_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="fundSupport_2" value="<c:if test="${empty resultMap.fundSupport_2}">${applyMap.fundSupport_2}</c:if>${resultMap.fundSupport_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="fundOneself_2" value="<c:if test="${empty resultMap.fundOneself_2}">${applyMap.fundOneself_2}</c:if>${resultMap.fundOneself_2}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_2"
                       value="<c:if test="${empty resultMap.fundRemark_2}">${applyMap.fundRemark_2}</c:if>${resultMap.fundRemark_2}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">（1）设备购置费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_3" value="<c:if test="${empty resultMap.amountBudget_3}">${applyMap.amountBudget_3}</c:if>${resultMap.amountBudget_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_3" value="<c:if test="${empty resultMap.fundSupport_3}">${applyMap.fundSupport_3}</c:if>${resultMap.fundSupport_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_3" value="<c:if test="${empty resultMap.fundOneself_3}">${applyMap.fundOneself_3}</c:if>${resultMap.fundOneself_3}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_3"
                       value="<c:if test="${empty resultMap.fundRemark_3}">${applyMap.fundRemark_3}</c:if>${resultMap.fundRemark_3}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">（2）设备试制费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_4" value="<c:if test="${empty resultMap.amountBudget_4}">${applyMap.amountBudget_4}</c:if>${resultMap.amountBudget_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_4" value="<c:if test="${empty resultMap.fundSupport_4}">${applyMap.fundSupport_4}</c:if>${resultMap.fundSupport_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_4" value="<c:if test="${empty resultMap.fundOneself_4}">${applyMap.fundOneself_4}</c:if>${resultMap.fundOneself_4}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_4"
                       value="<c:if test="${empty resultMap.fundRemark_4}">${applyMap.fundRemark_4}</c:if>${resultMap.fundRemark_4}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">（3）设备改造与租赁费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_5" value="<c:if test="${empty resultMap.amountBudget_5}">${applyMap.amountBudget_5}</c:if>${resultMap.amountBudget_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_5" value="<c:if test="${empty resultMap.fundSupport_5}">${applyMap.fundSupport_5}</c:if>${resultMap.fundSupport_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_5" value="<c:if test="${empty resultMap.fundOneself_5}">${applyMap.fundOneself_5}</c:if>${resultMap.fundOneself_5}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_5"
                       value="<c:if test="${empty resultMap.fundRemark_5}">${applyMap.fundRemark_5}</c:if>${resultMap.fundRemark_5}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">2、材料费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_6" value="<c:if test="${empty resultMap.amountBudget_6}">${applyMap.amountBudget_6}</c:if>${resultMap.amountBudget_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_6" value="<c:if test="${empty resultMap.fundSupport_6}">${applyMap.fundSupport_6}</c:if>${resultMap.fundSupport_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_6" value="<c:if test="${empty resultMap.fundOneself_6}">${applyMap.fundOneself_6}</c:if>${resultMap.fundOneself_6}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_6"
                       value="<c:if test="${empty resultMap.fundRemark_6}">${applyMap.fundRemark_6}</c:if>${resultMap.fundRemark_6}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">3、测试化验加工费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_7" value="<c:if test="${empty resultMap.amountBudget_7}">${applyMap.amountBudget_7}</c:if>${resultMap.amountBudget_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_7" value="<c:if test="${empty resultMap.fundSupport_7}">${applyMap.fundSupport_7}</c:if>${resultMap.fundSupport_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_7" value="<c:if test="${empty resultMap.fundOneself_7}">${applyMap.fundOneself_7}</c:if>${resultMap.fundOneself_7}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_7"
                       value="<c:if test="${empty resultMap.fundRemark_7}">${applyMap.fundRemark_7}</c:if>${resultMap.fundRemark_7}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">4、燃料动力费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_8" value="<c:if test="${empty resultMap.amountBudget_8}">${applyMap.amountBudget_8}</c:if>${resultMap.amountBudget_8}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_8" value="<c:if test="${empty resultMap.fundSupport_8}">${applyMap.fundSupport_8}</c:if>${resultMap.fundSupport_8}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_8" value="<c:if test="${empty resultMap.fundOneself_8}">${applyMap.fundOneself_8}</c:if>${resultMap.fundOneself_8}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_8"
                       value="<c:if test="${empty resultMap.fundRemark_8}">${applyMap.fundRemark_8}</c:if>${resultMap.fundRemark_8}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">5、出版/文献/信息传播/知识产权事务费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_9" value="<c:if test="${empty resultMap.amountBudget_9}">${applyMap.amountBudget_9}</c:if>${resultMap.amountBudget_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_9" value="<c:if test="${empty resultMap.fundSupport_9}">${applyMap.fundSupport_9}</c:if>${resultMap.fundSupport_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_9" value="<c:if test="${empty resultMap.fundOneself_9}">${applyMap.fundOneself_9}</c:if>${resultMap.fundOneself_9}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_9"
                       value="<c:if test="${empty resultMap.fundRemark_9}">${applyMap.fundRemark_9}</c:if>${resultMap.fundRemark_9}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">6、会议/差旅/国际合作交流费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_10" value="<c:if test="${empty resultMap.amountBudget_10}">${applyMap.amountBudget_10}</c:if>${resultMap.amountBudget_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_10" value="<c:if test="${empty resultMap.fundSupport_10}">${applyMap.fundSupport_10}</c:if>${resultMap.fundSupport_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_10" value="<c:if test="${empty resultMap.fundOneself_10}">${applyMap.fundOneself_10}</c:if>${resultMap.fundOneself_10}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_10"
                       value="<c:if test="${empty resultMap.fundRemark_10}">${applyMap.fundRemark_10}</c:if>${resultMap.fundRemark_10}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">7、劳务费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_11" value="<c:if test="${empty resultMap.amountBudget_11}">${applyMap.amountBudget_11}</c:if>${resultMap.amountBudget_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_11" value="<c:if test="${empty resultMap.fundSupport_11}">${applyMap.fundSupport_11}</c:if>${resultMap.fundSupport_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_11" value="<c:if test="${empty resultMap.fundOneself_11}">${applyMap.fundOneself_11}</c:if>${resultMap.fundOneself_11}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_11"
                       value="<c:if test="${empty resultMap.fundRemark_11}">${applyMap.fundRemark_11}</c:if>${resultMap.fundRemark_11}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">8、专家咨询费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_12" value="<c:if test="${empty resultMap.amountBudget_12}">${applyMap.amountBudget_12}</c:if>${resultMap.amountBudget_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_12" value="<c:if test="${empty resultMap.fundSupport_12}">${applyMap.fundSupport_12}</c:if>${resultMap.fundSupport_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_12" value="<c:if test="${empty resultMap.fundOneself_12}">${applyMap.fundOneself_12}</c:if>${resultMap.fundOneself_12}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_12"
                       value="<c:if test="${empty resultMap.fundRemark_12}">${applyMap.fundRemark_12}</c:if>${resultMap.fundRemark_12}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">9、其他支出</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_13" value="<c:if test="${empty resultMap.amountBudget_13}">${applyMap.amountBudget_13}</c:if>${resultMap.amountBudget_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_13" value="<c:if test="${empty resultMap.fundSupport_13}">${applyMap.fundSupport_13}</c:if>${resultMap.fundSupport_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_13" value="<c:if test="${empty resultMap.fundOneself_13}">${applyMap.fundOneself_13}</c:if>${resultMap.fundOneself_13}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_13"
                       value="<c:if test="${empty resultMap.fundRemark_13}">${applyMap.fundRemark_13}</c:if>${resultMap.fundRemark_13}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">（二）间接费用</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_14" value="<c:if test="${empty resultMap.amountBudget_14}">${applyMap.amountBudget_14}</c:if>${resultMap.amountBudget_14}"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="fundSupport_14" value="<c:if test="${empty resultMap.fundSupport_14}">${applyMap.fundSupport_14}</c:if>${resultMap.fundSupport_14}"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="fundOneself_14" value="<c:if test="${empty resultMap.fundOneself_14}">${applyMap.fundOneself_14}</c:if>${resultMap.fundOneself_14}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_14"
                       value="<c:if test="${empty resultMap.fundRemark_14}">${applyMap.fundRemark_14}</c:if>${resultMap.fundRemark_14}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">10、绩效支出</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_15" value="<c:if test="${empty resultMap.amountBudget_15}">${applyMap.amountBudget_15}</c:if>${resultMap.amountBudget_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_15" value="<c:if test="${empty resultMap.fundSupport_15}">${applyMap.fundSupport_15}</c:if>${resultMap.fundSupport_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_15" value="<c:if test="${empty resultMap.fundOneself_15}">${applyMap.fundOneself_15}</c:if>${resultMap.fundOneself_15}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_15"
                       value="<c:if test="${empty resultMap.fundRemark_15}">${applyMap.fundRemark_15}</c:if>${resultMap.fundRemark_15}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">11、管理费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_16" value="<c:if test="${empty resultMap.amountBudget_16}">${applyMap.amountBudget_16}</c:if>${resultMap.amountBudget_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_16" value="<c:if test="${empty resultMap.fundSupport_16}">${applyMap.fundSupport_16}</c:if>${resultMap.fundSupport_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_16" value="<c:if test="${empty resultMap.fundOneself_16}">${applyMap.fundOneself_16}</c:if>${resultMap.fundOneself_16}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_16"
                       value="<c:if test="${empty resultMap.fundRemark_16}">${applyMap.fundRemark_16}</c:if>${resultMap.fundRemark_16}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">12、能源费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_17" value="<c:if test="${empty resultMap.amountBudget_17}">${applyMap.amountBudget_17}</c:if>${resultMap.amountBudget_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_17" value="<c:if test="${empty resultMap.fundSupport_17}">${applyMap.fundSupport_17}</c:if>${resultMap.fundSupport_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_17" value="<c:if test="${empty resultMap.fundOneself_17}">${applyMap.fundOneself_17}</c:if>${resultMap.fundOneself_17}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_17"
                       value="<c:if test="${empty resultMap.fundRemark_17}">${applyMap.fundRemark_17}</c:if>${resultMap.fundRemark_17}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">13、其他</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="amountBudget_18" value="<c:if test="${empty resultMap.amountBudget_18}">${applyMap.amountBudget_18}</c:if>${resultMap.amountBudget_18}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundSupport_18" value="<c:if test="${empty resultMap.fundSupport_18}">${applyMap.fundSupport_18}</c:if>${resultMap.fundSupport_18}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundOneself_18" value="<c:if test="${empty resultMap.fundOneself_18}">${applyMap.fundOneself_18}</c:if>${resultMap.fundOneself_18}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_18"
                       value="<c:if test="${empty resultMap.fundRemark_18}">${applyMap.fundRemark_18}</c:if>${resultMap.fundRemark_18}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">合计</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="amountBudget" readonly="readonly" title="自动计算，不可修改"
                       value="<c:if test="${empty resultMap.amountBudget}">${applyMap.amountBudget}</c:if>${resultMap.amountBudget}"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundSupport" readonly="readonly" title="自动计算，不可修改"
                       value="<c:if test="${empty resultMap.fundSupport}">${applyMap.fundSupport}</c:if>${resultMap.fundSupport}"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundOneself" readonly="readonly" title="自动计算，不可修改"
                       value="<c:if test="${empty resultMap.fundOneself}">${applyMap.fundOneself}</c:if>${resultMap.fundOneself}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark"
                       value="<c:if test="${empty resultMap.fundRemark}">${applyMap.fundRemark}</c:if>${resultMap.fundRemark}"/></td>
        </tr>

    </table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="保&#12288;存"/>
    </div>
</c:if>	