<c:if test="${param.view != GlobalConstant.FLAG_Y}">
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
        function itemFunds(start,end){
            var fundsTotal=0;
            var financeTotal=0;
            var unitFundTotal=0;
            for(var i=start+1;i < end;i++){
                var finance = parseFloat($("input[name='finance_"+i+"']").val().trim());
                var unitFund = parseFloat($("input[name='unitFund_"+i+"']").val().trim());
                if(!finance){
                    finance=0;
                }
                if(!unitFund){
                    unitFund=0;
                }
                financeTotal+=finance;
                unitFundTotal+=unitFund;
            }
            fundsTotal=financeTotal+unitFundTotal;
            $("input[name='fundsTotal_"+start+"']").val(parseFloat(fundsTotal.toFixed(4)));
            $("input[name='finance_"+start+"']").val(parseFloat(financeTotal.toFixed(4)));
            $("input[name='unitFund_"+start+"']").val(parseFloat(unitFundTotal.toFixed(4)));
        }
        function totalFunds(index){
            var fundsTotal;
            var finance;
            var unitFund;
            finance = parseFloat($("input[name='finance_"+index+"']").val().trim());
            unitFund = parseFloat($("input[name='unitFund_"+index+"']").val().trim());
            if(!finance){
                finance=0;
            }
            if(!unitFund){
                unitFund=0;
            }
            fundsTotal = finance + unitFund;
            $("input[name='fundsTotal_"+index+"']").val(parseFloat(fundsTotal.toFixed(4)));
        }
        function updateFunds(obj){
            var name = $(obj).prop("name");
            var strs= new Array(); //定义一数组
            strs=name.split("_"); //字符分割
            if(parseInt(strs[1])){
                var index = strs[1];
                totalFunds(index);
                if(1<index && index<8){//设备费下子项
                    itemFunds(1,8);
                }else if(8<index && index<14){//科研费
                    itemFunds(8,14);
                }else if(14<index && index<17){//临床技术开发经费
                    itemFunds(14,17);
                }else if(18<index && index<24){//差旅费
                    itemFunds(18,24);
                }else if(24<index && index<28){//会议费
                    itemFunds(24,28);
                }else if(28<index && index<31){//国际合作与交流费
                    itemFunds(28,31);
                }else if(31<index && index<38){//出版、文献、信息传播、知识产权事务费
                    itemFunds(31,38);
                }else if(38<index && index<41){//人员费
                    itemFunds(38,41);
                }else if(41<index && index<44){//劳务费
                    itemFunds(41,44);
                }else if(44<index && index<47){//专家咨询费
                    itemFunds(44,47);
                }
            }
            var fundsTotal = 0;
            var financeTotal = 0;
            var unitTotal = 0;
            financeTotal += parseFloat($("input[name='finance_1']").val().trim())?parseFloat($("input[name='finance_1']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_1']").val().trim())?parseFloat($("input[name='unitFund_1']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_8']").val().trim())?parseFloat($("input[name='finance_8']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_8']").val().trim())?parseFloat($("input[name='unitFund_8']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_14']").val().trim())?parseFloat($("input[name='finance_14']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_14']").val().trim())?parseFloat($("input[name='unitFund_14']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_17']").val().trim())?parseFloat($("input[name='finance_17']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_17']").val().trim())?parseFloat($("input[name='unitFund_17']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_18']").val().trim())?parseFloat($("input[name='finance_18']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_18']").val().trim())?parseFloat($("input[name='unitFund_18']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_24']").val().trim())?parseFloat($("input[name='finance_24']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_24']").val().trim())?parseFloat($("input[name='unitFund_24']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_28']").val().trim())?parseFloat($("input[name='finance_28']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_28']").val().trim())?parseFloat($("input[name='unitFund_28']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_31']").val().trim())?parseFloat($("input[name='finance_31']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_31']").val().trim())?parseFloat($("input[name='unitFund_31']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_38']").val().trim())?parseFloat($("input[name='finance_38']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_38']").val().trim())?parseFloat($("input[name='unitFund_38']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_41']").val().trim())?parseFloat($("input[name='finance_41']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_41']").val().trim())?parseFloat($("input[name='unitFund_41']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_44']").val().trim())?parseFloat($("input[name='finance_44']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_44']").val().trim())?parseFloat($("input[name='unitFund_44']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_47']").val().trim())?parseFloat($("input[name='finance_47']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_47']").val().trim())?parseFloat($("input[name='unitFund_47']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_48']").val().trim())?parseFloat($("input[name='finance_48']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_48']").val().trim())?parseFloat($("input[name='unitFund_48']").val().trim()):0;
            financeTotal += parseFloat($("input[name='finance_49']").val().trim())?parseFloat($("input[name='finance_49']").val().trim()):0;
            unitTotal += parseFloat($("input[name='unitFund_49']").val().trim())?parseFloat($("input[name='unitFund_49']").val().trim()):0;

            fundsTotal = financeTotal+unitTotal;
            $("input[name='fundsTotal']").val(parseFloat(fundsTotal.toFixed(4)));
            $("input[name='financeTotal']").val(parseFloat(financeTotal.toFixed(4)));
            $("input[name='unitTotal']").val(parseFloat(unitTotal.toFixed(4)));

        }
    </script>
</c:if>
<style type="text/css">
    .basic tbody th {
        text-align: center;
    }
    .readonlycss{
        background-color: #EEEEEE;
    }
</style>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step9"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
    <input type="hidden" id="itemGroupName" value="certificate"/>
    <font style="font-size: 14px; font-weight: bold; color: #333;">六、项目经费预算</font>
    <table class="basic" style="width: 100%;margin-top: 20px;">
        <colgroup>
            <col width="10%"/>
            <col width="25%"/>
            <col width="22%"/>
            <col width="22%"/>
            <col width="21%"/>
        </colgroup>
        <tr>
            <th style="text-align: left" colspan="5">
                <p style="line-height: 20px">申请苏州市临床医学专家团队专项经费总额：</p>
                <p style="font-weight: normal;font-size: 16px;line-height: 25px">
                    <span>（大写）：
                    <input type="text" class="inputText" style="width: 30%" name="capitalFunds" value="${resultMap.capitalFunds}"/>万元；
                    </span>
                    <span>（小写）：
                        <input type="text" class="inputText" style="width: 20%" name="amountFunds" value="${resultMap.amountFunds}"/>万元。
                    </span>
                </p>
            </th>
        </tr>
        <tr>
            <th style="text-align: left" colspan="5">申请苏州市临床医学专家团队专项经费项目预算表</th>
        </tr>
        <tr>
            <th rowspan="2">序号</th>
            <th rowspan="2">经费支出内容</th>
            <th rowspan="2">合计金额（万元）</th>
            <th colspan="2">其中（单位：万元）</th>
        </tr>
        <tr>
            <th>财政专项经费</th>
            <th>用人单位</th>
        </tr>
        <tr>
            <th colspan="2">总计</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal" value="${resultMap.fundsTotal}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="financeTotal" value="${resultMap.financeTotal}" readonly="readonly" title="自动计算，不允许编辑"/>
            </td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitTotal" value="${resultMap.unitTotal}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">1</td>
            <th style="text-align: left">一、设备费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_1"
                       value="${resultMap.fundsTotal_1}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="finance_1"
                       value="${resultMap.finance_1}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitFund_1"
                       value="${resultMap.unitFund_1}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">2</td>
            <td>1. 购置设备费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_2"
                       value="${resultMap.fundsTotal_2}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_2"
                       value="${resultMap.finance_2}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_2"
                       value="${resultMap.unitFund_2}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">3</td>
            <td>2. 安装、试制费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_3"
                       value="${resultMap.fundsTotal_3}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_3"
                       value="${resultMap.finance_3}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_3"
                       value="${resultMap.unitFund_3}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">4</td>
            <td>3. 设备运输费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_4"
                       value="${resultMap.fundsTotal_4}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_4"
                       value="${resultMap.finance_4}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_4"
                       value="${resultMap.unitFund_4}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">5</td>
            <td>4. 维护运转费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_5"
                       value="${resultMap.fundsTotal_5}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_5"
                       value="${resultMap.finance_5}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_5"
                       value="${resultMap.unitFund_5}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">6</td>
            <td>5. 改造升级费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_6"
                       value="${resultMap.fundsTotal_6}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_6"
                       value="${resultMap.finance_6}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_6"
                       value="${resultMap.unitFund_6}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">7</td>
            <td>6. 设备租赁费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_7"
                       value="${resultMap.fundsTotal_7}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_7"
                       value="${resultMap.finance_7}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_7"
                       value="${resultMap.unitFund_7}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">8</td>
            <th style="text-align: left">二、科研费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_8"
                       value="${resultMap.fundsTotal_8}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="finance_8"
                       value="${resultMap.finance_8}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitFund_8"
                       value="${resultMap.unitFund_8}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">9</td>
            <td>1.材料费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_9"
                       value="${resultMap.fundsTotal_9}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_9"
                       value="${resultMap.finance_9}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_9"
                       value="${resultMap.unitFund_9}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">10</td>
            <td>2.测试费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_10"
                       value="${resultMap.fundsTotal_10}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_10"
                       value="${resultMap.finance_10}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_10"
                       value="${resultMap.unitFund_10}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">11</td>
            <td>3.化验费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_11"
                       value="${resultMap.fundsTotal_11}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_11"
                       value="${resultMap.finance_11}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_11"
                       value="${resultMap.unitFund_11}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">12</td>
            <td>4.加工费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_12"
                       value="${resultMap.fundsTotal_12}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_12"
                       value="${resultMap.finance_12}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_12"
                       value="${resultMap.unitFund_12}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">13</td>
            <td>5.燃料动力费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_13"
                       value="${resultMap.fundsTotal_13}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_13"
                       value="${resultMap.finance_13}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_13"
                       value="${resultMap.unitFund_13}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">14</td>
            <th style="text-align: left">三、临床技术开发经费
            </td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_14"
                       value="${resultMap.fundsTotal_14}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="finance_14"
                       value="${resultMap.finance_14}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitFund_14"
                       value="${resultMap.unitFund_14}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">15</td>
            <td>1.材料费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_15"
                       value="${resultMap.fundsTotal_15}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_15"
                       value="${resultMap.finance_15}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_15"
                       value="${resultMap.unitFund_15}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">16</td>
            <td>2.测试费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_16"
                       value="${resultMap.fundsTotal_16}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_16"
                       value="${resultMap.finance_16}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_16"
                       value="${resultMap.unitFund_16}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">17</td>
            <th style="text-align: left">四、培训费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_17"
                       value="${resultMap.fundsTotal_17}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_17"
                       value="${resultMap.finance_17}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_17"
                       value="${resultMap.unitFund_17}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">18</td>
            <th style="text-align: left">五、差旅费
            </td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_18"
                       value="${resultMap.fundsTotal_18}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="finance_18"
                       value="${resultMap.finance_18}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitFund_18"
                       value="${resultMap.unitFund_18}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">19</td>
            <td>1. 出境交通费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_19"
                       value="${resultMap.fundsTotal_19}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_19"
                       value="${resultMap.finance_19}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_19"
                       value="${resultMap.unitFund_19}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">20</td>
            <td>2. 国内交通费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_20"
                       value="${resultMap.fundsTotal_20}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_20"
                       value="${resultMap.finance_20}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_20"
                       value="${resultMap.unitFund_20}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">21</td>
            <td>3. 出境住宿、餐饮费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_21"
                       value="${resultMap.fundsTotal_21}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_21"
                       value="${resultMap.finance_21}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_21"
                       value="${resultMap.unitFund_21}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">22</td>
            <td>4. 国内住宿、餐饮费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_22"
                       value="${resultMap.fundsTotal_22}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_22"
                       value="${resultMap.finance_22}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_22"
                       value="${resultMap.unitFund_22}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">23</td>
            <td>5. 差旅补贴</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_23"
                       value="${resultMap.fundsTotal_23}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_23"
                       value="${resultMap.finance_23}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_23"
                       value="${resultMap.unitFund_23}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">24</td>
            <th style="text-align: left">六、会议费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_24"
                       value="${resultMap.fundsTotal_24}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="finance_24"
                       value="${resultMap.finance_24}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitFund_24"
                       value="${resultMap.unitFund_24}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">25</td>
            <td>1. 学术研讨会议费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_25"
                       value="${resultMap.fundsTotal_25}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_25"
                       value="${resultMap.finance_25}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_25"
                       value="${resultMap.unitFund_25}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">26</td>
            <td>2. 咨询会议费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_26"
                       value="${resultMap.fundsTotal_26}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_26"
                       value="${resultMap.finance_26}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_26"
                       value="${resultMap.unitFund_26}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">27</td>
            <td>3. 项目协调会议费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_27"
                       value="${resultMap.fundsTotal_27}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_27"
                       value="${resultMap.finance_27}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_27"
                       value="${resultMap.unitFund_27}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">28</td>
            <th style="text-align: left">七、国际合作与交流费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_28"
                       value="${resultMap.fundsTotal_28}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="finance_28"
                       value="${resultMap.finance_28}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitFund_28"
                       value="${resultMap.unitFund_28}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">29</td>
            <td>1.外国专家来苏工作费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_29"
                       value="${resultMap.fundsTotal_29}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_29"
                       value="${resultMap.finance_29}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_29"
                       value="${resultMap.unitFund_29}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">30</td>
            <td>2. 开展国际合作交流费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_30"
                       value="${resultMap.fundsTotal_30}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_30"
                       value="${resultMap.finance_30}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_30"
                       value="${resultMap.unitFund_30}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">31</td>
            <th style="text-align: left">八、出版、文献、信息传播、知识产权事务费
            </td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_31"
                       value="${resultMap.fundsTotal_31}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="finance_31"
                       value="${resultMap.finance_31}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitFund_31"
                       value="${resultMap.unitFund_31}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">32</td>
            <td>1. 出版费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_32"
                       value="${resultMap.fundsTotal_32}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_32"
                       value="${resultMap.finance_32}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_32"
                       value="${resultMap.unitFund_32}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">33</td>
            <td>2. 资料费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_33"
                       value="${resultMap.fundsTotal_33}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_33"
                       value="${resultMap.finance_33}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_33"
                       value="${resultMap.unitFund_33}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">34</td>
            <td>3. 专利申请费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_34"
                       value="${resultMap.fundsTotal_34}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_34"
                       value="${resultMap.finance_34}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_34"
                       value="${resultMap.unitFund_34}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">35</td>
            <td>4. 文献检索费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_35"
                       value="${resultMap.fundsTotal_35}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_35"
                       value="${resultMap.finance_35}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_35"
                       value="${resultMap.unitFund_35}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">36</td>
            <td>5. 入网、通信费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_36"
                       value="${resultMap.fundsTotal_36}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_36"
                       value="${resultMap.finance_36}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_36"
                       value="${resultMap.unitFund_36}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">37</td>
            <td>6. 其他知识产权事务费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_37"
                       value="${resultMap.fundsTotal_37}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_37"
                       value="${resultMap.finance_37}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_37"
                       value="${resultMap.unitFund_37}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">38</td>
            <th style="text-align: left">九、人员费
            </td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_38"
                       value="${resultMap.fundsTotal_38}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="finance_38"
                       value="${resultMap.finance_38}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitFund_38"
                       value="${resultMap.unitFund_38}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">39</td>
            <td>1. 引进成员工资</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_39"
                       value="${resultMap.fundsTotal_39}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_39"
                       value="${resultMap.finance_39}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_39"
                       value="${resultMap.unitFund_39}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">40</td>
            <td>2.项目其他成员按事业费削减比例列支工资
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_40"
                       value="${resultMap.fundsTotal_40}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_40"
                       value="${resultMap.finance_40}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_40"
                       value="${resultMap.unitFund_40}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">41</td>
            <th style="text-align: left">十、劳务费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_41"
                       value="${resultMap.fundsTotal_41}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="finance_41"
                       value="${resultMap.finance_41}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitFund_41"
                       value="${resultMap.unitFund_41}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">42</td>
            <td>1. 无工资性收入的项目成员劳务性费用</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_42"
                       value="${resultMap.fundsTotal_42}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_42"
                       value="${resultMap.finance_42}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_42"
                       value="${resultMap.unitFund_42}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">43</td>
            <td>2. 项目组临时聘用人员劳务性费用</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_43"
                       value="${resultMap.fundsTotal_43}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_43"
                       value="${resultMap.finance_43}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_43"
                       value="${resultMap.unitFund_43}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">44</td>
            <th style="text-align: left">十一、专家咨询费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_44"
                       value="${resultMap.fundsTotal_44}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="finance_44"
                       value="${resultMap.finance_44}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="unitFund_44"
                       value="${resultMap.unitFund_44}" readonly="readonly" title="自动计算，不允许编辑"/></td>
        </tr>
        <tr>
            <td style="text-align: center">45</td>
            <td>1. 以会议形式组织的咨询费
            </th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_45"
                       value="${resultMap.fundsTotal_45}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_45"
                       value="${resultMap.finance_45}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_45"
                       value="${resultMap.unitFund_45}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">46</td>
            <td>2. 以通讯形式组织的咨询费</td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_46"
                       value="${resultMap.fundsTotal_46}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_46"
                       value="${resultMap.finance_46}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_46"
                       value="${resultMap.unitFund_46}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">47</td>
            <th style="text-align: left">十二、项目管理费
            </td>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_47"
                       value="${resultMap.fundsTotal_47}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_47"
                       value="${resultMap.finance_47}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_47"
                       value="${resultMap.unitFund_47}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">48</td>
            <th style="text-align: left">十三、外部协作费</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_48"
                       value="${resultMap.fundsTotal_48}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_48"
                       value="${resultMap.finance_48}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_48"
                       value="${resultMap.unitFund_48}" onchange="updateFunds(this)"/></td>
        </tr>
        <tr>
            <td style="text-align: center">49</td>
            <th style="text-align: left">十四、其他</th>
            <td><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" name="fundsTotal_49"
                       value="${resultMap.fundsTotal_49}" readonly="readonly" title="自动计算，不允许编辑"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="finance_49"
                       value="${resultMap.finance_49}" onchange="updateFunds(this)"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" style="width: 90%" name="unitFund_49"
                       value="${resultMap.unitFund_49}" onchange="updateFunds(this)"/></td>
        </tr>
    </table>
    <p style="font-weight: bold">注：经费支出内容中：第九：人员费和第十：劳务费累计不超过项目总经费70%。</p>
</form>

<div class="button" style="width:100%;
<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none;</c:if>">
    <input id="prev" type="button" onclick="nextOpt('step8')" class="search" value="上一步"/>
    <input id="nxt" type="button" onclick="nextOpt('step10')" class="search" value="下一步"/>
</div>
