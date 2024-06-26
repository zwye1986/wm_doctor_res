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
            form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
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
    <style type="text/css">
        /*.basic tbody th {
            text-align: center;
        }*/
        .readonlycss{
            background-color: #EEEEEE;
        }
    </style>
</head>
<body>
<div style="margin-top: 10px;">
    <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;" id="projForm">
        <input type="hidden" id="pageName" name="pageName" value="step9"/>
        <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
        <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
        <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
        <font style="font-size: 14px; font-weight:bold;color: #333;">八、经费预算</font>
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
                           name="amountBudget_1" value="${resultMap.amountBudget_1}"/></td>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="fundSupport_1" value="${resultMap.fundSupport_1}"/></td>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="fundOneself_1" value="${resultMap.fundOneself_1}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_1"
                           value="${resultMap.fundRemark_1}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">1、设备费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_2" value="${resultMap.amountBudget_2}"/></td>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="fundSupport_2" value="${resultMap.fundSupport_2}"/></td>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="fundOneself_2" value="${resultMap.fundOneself_2}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_2"
                           value="${resultMap.fundRemark_2}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">（1）设备购置费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_3" value="${resultMap.amountBudget_3}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_3" value="${resultMap.fundSupport_3}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_3" value="${resultMap.fundOneself_3}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_3"
                           value="${resultMap.fundRemark_3}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">（2）设备试制费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_4" value="${resultMap.amountBudget_4}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_4" value="${resultMap.fundSupport_4}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_4" value="${resultMap.fundOneself_4}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_4"
                           value="${resultMap.fundRemark_4}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">（3）设备改造与租赁费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_5" value="${resultMap.amountBudget_5}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_5" value="${resultMap.fundSupport_5}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_5" value="${resultMap.fundOneself_5}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_5"
                           value="${resultMap.fundRemark_5}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">2、材料费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_6" value="${resultMap.amountBudget_6}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_6" value="${resultMap.fundSupport_6}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_6" value="${resultMap.fundOneself_6}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_6"
                           value="${resultMap.fundRemark_6}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">3、测试化验加工费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_7" value="${resultMap.amountBudget_7}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_7" value="${resultMap.fundSupport_7}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_7" value="${resultMap.fundOneself_7}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_7"
                           value="${resultMap.fundRemark_7}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">4、燃料动力费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_8" value="${resultMap.amountBudget_8}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_8" value="${resultMap.fundSupport_8}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_8" value="${resultMap.fundOneself_8}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_8"
                           value="${resultMap.fundRemark_8}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">5、出版/文献/信息传播/知识产权事务费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_9" value="${resultMap.amountBudget_9}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_9" value="${resultMap.fundSupport_9}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_9" value="${resultMap.fundOneself_9}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_9"
                           value="${resultMap.fundRemark_9}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">6、会议/差旅/国际合作交流费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_10" value="${resultMap.amountBudget_10}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_10" value="${resultMap.fundSupport_10}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_10" value="${resultMap.fundOneself_10}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_10"
                           value="${resultMap.fundRemark_10}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">7、劳务费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_11" value="${resultMap.amountBudget_11}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_11" value="${resultMap.fundSupport_11}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_11" value="${resultMap.fundOneself_11}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_11"
                           value="${resultMap.fundRemark_11}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">8、专家咨询费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_12" value="${resultMap.amountBudget_12}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_12" value="${resultMap.fundSupport_12}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_12" value="${resultMap.fundOneself_12}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_12"
                           value="${resultMap.fundRemark_12}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">9、其他支出</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_13" value="${resultMap.amountBudget_13}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_13" value="${resultMap.fundSupport_13}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_13" value="${resultMap.fundOneself_13}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_13"
                           value="${resultMap.fundRemark_13}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">（二）间接费用</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_14" value="${resultMap.amountBudget_14}"/></td>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="fundSupport_14" value="${resultMap.fundSupport_14}"/></td>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="fundOneself_14" value="${resultMap.fundOneself_14}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_14"
                           value="${resultMap.fundRemark_14}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">10、绩效支出</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_15" value="${resultMap.amountBudget_15}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_15" value="${resultMap.fundSupport_15}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_15" value="${resultMap.fundOneself_15}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_15"
                           value="${resultMap.fundRemark_15}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">11、管理费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_16" value="${resultMap.amountBudget_16}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_16" value="${resultMap.fundSupport_16}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_16" value="${resultMap.fundOneself_16}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_16"
                           value="${resultMap.fundRemark_16}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">12、能源费</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_17" value="${resultMap.amountBudget_17}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_17" value="${resultMap.fundSupport_17}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_17" value="${resultMap.fundOneself_17}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_17"
                           value="${resultMap.fundRemark_17}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">13、其他</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                           name="amountBudget_18" value="${resultMap.amountBudget_18}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundSupport_18" value="${resultMap.fundSupport_18}"/></td>
                <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                           name="fundOneself_18" value="${resultMap.fundOneself_18}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_18"
                           value="${resultMap.fundRemark_18}"/></td>
            </tr>
            <tr>
                <th style="text-align: left">合计</th>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="amountBudget" readonly="readonly" title="自动计算，不可修改"
                           value="${resultMap.amountBudget}"/></td>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundSupport" readonly="readonly" title="自动计算，不可修改"
                           value="${resultMap.fundSupport}"/></td>
                <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundOneself" readonly="readonly" title="自动计算，不可修改"
                           value="${resultMap.fundOneself}"/></td>
                <td><input type="text" class="inputText" style="width: 90%" name="fundRemark"
                           value="${resultMap.fundRemark}"/></td>
            </tr>

        </table>
        <div class="button"
             style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
            <input id="prev" type="button" onclick="nextOpt('step8')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step10')" class="search" value="保&#12288;存"/>
        </div>
    </form>
</div>
</body>
</html>
