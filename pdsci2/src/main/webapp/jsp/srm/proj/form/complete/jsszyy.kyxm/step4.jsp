<head>
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <script type="text/javascript">
            function nextOpt(step){
                if(false==$("#projForm").validationEngine("validate")){
                    return false;
                }
                var form = $('#projForm');
                form.append("<input name='nextPageName' value='"+step+"' type='hidden'/>");
                $('#nxt').attr({"disabled":"disabled"});
                $('#prev').attr({"disabled":"disabled"});
                jboxStartLoading();
                form.submit();
            }

            function calculationFunds() {
                var fundExpend = 0;

                var fundExpend_1 = 0;

                var fundExpend_2 = 0;

                var fundExpend_14 = 0;

                for (var i = 3; i < 19; i++) {
                    var Expend = parseFloat($("input[name='fundExpend_" + i + "']").val());

                    if (!Expend) {
                        Expend = 0;
                    }
                    if (i < 6) {
                        fundExpend_2 += Expend;
                    }
                    if (i < 14) {
                        fundExpend_1 += Expend;
                    }
                    if(i>14 && i < 19){
                        fundExpend_14 += Expend;
                    }
                    if(i != 14){
                        fundExpend += Expend;
                    }
                }
                var projfee = $("#projfee").val();
                if (!projfee) {
                    projfee = 0;
                }
                $("input[name='fundExpend']").val(parseFloat(fundExpend.toFixed(4)));
                $("input[name='fundSurplus']").val(parseFloat((projfee-fundExpend).toFixed(4)));

                $("input[name='fundExpend_1']").val(parseFloat(fundExpend_1.toFixed(4)));

                $("input[name='fundExpend_2']").val(parseFloat(fundExpend_2.toFixed(4)));

                $("input[name='fundExpend_14']").val(parseFloat(fundExpend_14.toFixed(4)));
            }

            $(function () {
                $("input[name^='fundExpend_']").bind("change", function () {//name属性以amountBudget_开始的所有input标签失去焦点
                    calculationFunds();
                });
            });

        </script>
    </c:if>
    <style type="text/css">
        .borderNone{border-bottom-style: none;}
    </style>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step4" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">三、项目经费决算表</font>
<p>
    资助经费：<input type="text" class="inputText validate[custom[number]]" onchange="calculationFunds();" id="projfee" name="projfee" value="${resultMap.projfee}" />&#12288;&#12288;&#12288;
    经费账号：<input type="text" class="inputText" name="feeNo" value="${resultMap.feeNo}<c:if test="${empty resultMap.feeNo}" >${proj.accountNo}</c:if>" />&#12288;<span style="float: right">单位：万元</span>
</p>
    <table class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <th style="width: 20%;text-align: center">科&#12288;目</th>
            <th style="width: 20%;text-align: center">支出数</th>
            <th style="text-align: center">备&#12288;注</th>
        </tr>

        <tr>
            <th style="text-align: left">（一）直接费用</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="fundExpend_1" value="${resultMap.fundExpend_1}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_1"
                       value="${resultMap.fundRemark_1}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">1、设备费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="fundExpend_2" value="${resultMap.fundExpend_2}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_2"
                       value="${resultMap.fundRemark_2}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">（1）设备购置费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_3" value="${resultMap.fundExpend_3}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_3"
                       value="${resultMap.fundRemark_3}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">（2）设备试制费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_4" value="${resultMap.fundExpend_4}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_4"
                       value="${resultMap.fundRemark_4}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">（3）设备改造与租赁费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_5" value="${resultMap.fundExpend_5}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_5"
                       value="${resultMap.fundRemark_5}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">2、材料费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_6" value="${resultMap.fundExpend_6}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_6"
                       value="${resultMap.fundRemark_6}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">3、测试化验加工费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_7" value="${resultMap.fundExpend_7}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_7"
                       value="${resultMap.fundRemark_7}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">4、燃料动力费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_8" value="${resultMap.fundExpend_8}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_8"
                       value="${resultMap.fundRemark_8}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">5、出版/文献/信息传播/知识产权事务费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_9" value="${resultMap.fundExpend_9}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_9"
                       value="${resultMap.fundRemark_9}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">6、会议/差旅/国际合作交流费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_10" value="${resultMap.fundExpend_10}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_10"
                       value="${resultMap.fundRemark_10}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">7、劳务费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_11" value="${resultMap.fundExpend_11}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_11"
                       value="${resultMap.fundRemark_11}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">8、专家咨询费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_12" value="${resultMap.fundExpend_12}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_12"
                       value="${resultMap.fundRemark_12}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">9、其他支出</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_13" value="${resultMap.fundExpend_13}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_13"
                       value="${resultMap.fundRemark_13}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">（二）间接费用</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不可修改"
                       name="fundExpend_14" value="${resultMap.fundExpend_14}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_14"
                       value="${resultMap.fundRemark_14}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">10、绩效支出</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_15" value="${resultMap.fundExpend_15}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_15"
                       value="${resultMap.fundRemark_15}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">11、管理费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_16" value="${resultMap.fundExpend_16}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_16"
                       value="${resultMap.fundRemark_16}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">12、能源费</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_17" value="${resultMap.fundExpend_17}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_17"
                       value="${resultMap.fundRemark_17}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">13、其他</th>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%"
                       name="fundExpend_18" value="${resultMap.fundExpend_18}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark_18"
                       value="${resultMap.fundRemark_18}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">合计</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundExpend" readonly="readonly" title="自动计算，不可修改"
                       value="${resultMap.fundExpend}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="fundRemark"
                       value="${resultMap.fundRemark}"/></td>
        </tr>
        <tr>
            <th style="text-align: left">剩余经费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundSurplus" readonly="readonly" title="自动计算，不可修改"
                       value="${resultMap.fundExpend}"/></td>
            <td><input type="text" class="inputText" style="width: 90%" name="surplusRemark"
                       value="${resultMap.surplusRemark}"/></td>
        </tr>
    </table>
    </table>
    <div class="button" style="width: 100%;
    <c:if test="${param.view eq GlobalConstant.FLAG_Y}">display:none</c:if>">
        <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="保&#12288;存"/>
    </div>

</form>
<table style="display:none">
    <tr id="feePayList">
        <td style="text-align: center"><input type="checkbox" class="toDel"></td>
        <td colspan="3"><input type="text" id="feePayList_content" name="feePayList_content" value="" class="inputText " style="width: 100%;"/></td>
        <td><input type="text"  name="feePayList_money" value="" class="inputText " style="width: 100%;" onchange="countFee()"/></td>
    </tr>
</table>
