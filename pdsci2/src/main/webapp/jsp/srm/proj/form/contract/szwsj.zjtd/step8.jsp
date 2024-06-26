<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
        /*计算下标在indexItem里数据的合计*/
        function itemFunds(index,indexItem){
            var fundsTotal;
            var finance2017Total = 0;
            var finance2018Total = 0;
            var finance2019Total = 0;
            var finance2020Total = 0;
            var finance2021Total = 0;
            var matched2017Total = 0;
            var matched2018Total = 0;
            var matched2019Total = 0;
            var matched2020Total = 0;
            var matched2021Total = 0;
            for(var i=0;i < indexItem.length;i++){
                var financeFund2017 = parseFloat($("input[name='financeFund2017_"+indexItem[i]+"']").val().trim());
                var matchedFund2017 = parseFloat($("input[name='matchedFund2017_"+indexItem[i]+"']").val().trim());
                var financeFund2018 = parseFloat($("input[name='financeFund2018_"+indexItem[i]+"']").val().trim());
                var matchedFund2018 = parseFloat($("input[name='matchedFund2018_"+indexItem[i]+"']").val().trim());
                var financeFund2019 = parseFloat($("input[name='financeFund2019_"+indexItem[i]+"']").val().trim());
                var matchedFund2019 = parseFloat($("input[name='matchedFund2019_"+indexItem[i]+"']").val().trim());
                var financeFund2020 = parseFloat($("input[name='financeFund2020_"+indexItem[i]+"']").val().trim());
                var matchedFund2020 = parseFloat($("input[name='matchedFund2020_"+indexItem[i]+"']").val().trim());
                var financeFund2021 = parseFloat($("input[name='financeFund2021_"+indexItem[i]+"']").val().trim());
                var matchedFund2021 = parseFloat($("input[name='matchedFund2021_"+indexItem[i]+"']").val().trim());
                if(!matchedFund2017){
                    matchedFund2017=0;
                }
                if(!financeFund2017){
                    financeFund2017=0;
                }
                if(!matchedFund2018){
                    matchedFund2018=0;
                }
                if(!financeFund2018){
                    financeFund2018=0;
                }
                if(!matchedFund2019){
                    matchedFund2019=0;
                }
                if(!financeFund2019){
                    financeFund2019=0;
                }
                if(!matchedFund2020){
                    matchedFund2020=0;
                }
                if(!financeFund2020){
                    financeFund2020=0;
                }
                if(!matchedFund2021){
                    matchedFund2021=0;
                }
                if(!financeFund2021){
                    financeFund2021=0;
                }
                finance2017Total+=financeFund2017;
                finance2018Total+=financeFund2018;
                finance2019Total+=financeFund2019;
                finance2020Total+=financeFund2020;
                finance2021Total+=financeFund2021;

                matched2017Total+=matchedFund2017;
                matched2018Total+=matchedFund2018;
                matched2019Total+=matchedFund2019;
                matched2020Total+=matchedFund2020;
                matched2021Total+=matchedFund2021;

            }
            fundsTotal=finance2017Total+finance2018Total+finance2019Total+finance2020Total+finance2021Total+matched2017Total+matched2018Total+matched2019Total+matched2020Total+matched2021Total;
            $("input[name='amountFund_"+index+"']").val(parseFloat(fundsTotal.toFixed(4)));
            $("input[name='financeFund2017_"+index+"']").val(parseFloat(finance2017Total.toFixed(4)));
            $("input[name='financeFund2018_"+index+"']").val(parseFloat(finance2018Total.toFixed(4)));
            $("input[name='financeFund2019_"+index+"']").val(parseFloat(finance2019Total.toFixed(4)));
            $("input[name='financeFund2020_"+index+"']").val(parseFloat(finance2020Total.toFixed(4)));
            $("input[name='financeFund2021_"+index+"']").val(parseFloat(finance2021Total.toFixed(4)));
            $("input[name='matchedFund2017_"+index+"']").val(parseFloat(matched2017Total.toFixed(4)));
            $("input[name='matchedFund2018_"+index+"']").val(parseFloat(matched2018Total.toFixed(4)));
            $("input[name='matchedFund2019_"+index+"']").val(parseFloat(matched2019Total.toFixed(4)));
            $("input[name='matchedFund2020_"+index+"']").val(parseFloat(matched2020Total.toFixed(4)));
            $("input[name='matchedFund2021_"+index+"']").val(parseFloat(matched2021Total.toFixed(4)));
        }
        /*计算当前修改行的合计*/
        function totalFunds(index){
            var fundsTotal;
            var financeFund2017 = parseFloat($("input[name='financeFund2017_"+index+"']").val().trim());
            var matchedFund2017 = parseFloat($("input[name='matchedFund2017_"+index+"']").val().trim());
            var financeFund2018 = parseFloat($("input[name='financeFund2018_"+index+"']").val().trim());
            var matchedFund2018 = parseFloat($("input[name='matchedFund2018_"+index+"']").val().trim());
            var financeFund2019 = parseFloat($("input[name='financeFund2019_"+index+"']").val().trim());
            var matchedFund2019 = parseFloat($("input[name='matchedFund2019_"+index+"']").val().trim());
            var financeFund2020 = parseFloat($("input[name='financeFund2020_"+index+"']").val().trim());
            var matchedFund2020 = parseFloat($("input[name='matchedFund2020_"+index+"']").val().trim());
            var financeFund2021 = parseFloat($("input[name='financeFund2021_"+index+"']").val().trim());
            var matchedFund2021 = parseFloat($("input[name='matchedFund2021_"+index+"']").val().trim());
            if(!matchedFund2017){
                matchedFund2017=0;
            }
            if(!financeFund2017){
                financeFund2017=0;
            }
            if(!matchedFund2018){
                matchedFund2018=0;
            }
            if(!financeFund2018){
                financeFund2018=0;
            }
            if(!matchedFund2019){
                matchedFund2019=0;
            }
            if(!financeFund2019){
                financeFund2019=0;
            }
            if(!matchedFund2020){
                matchedFund2020=0;
            }
            if(!financeFund2020){
                financeFund2020=0;
            }
            if(!matchedFund2021){
                matchedFund2021=0;
            }
            if(!financeFund2021){
                financeFund2021=0;
            }
            fundsTotal = financeFund2017 + financeFund2018 + financeFund2019 + financeFund2020 + financeFund2021 + matchedFund2017 + matchedFund2018 + matchedFund2019 + matchedFund2020 + matchedFund2021;
            $("input[name='amountFund_"+index+"']").val(parseFloat(fundsTotal.toFixed(4)));
        }
        function updateFunds(obj){
            var indexItem=[];
            var name = $(obj).prop("name");
            var strs= new Array(); //定义一数组
            strs=name.split("_"); //字符分割
            if(parseInt(strs[1])){
                var index = strs[1];
                totalFunds(index);
                if(1<index && index<8){//设备费下子项
                    indexItem=[2,3,4,5,6,7];
                    itemFunds(1,indexItem);
                }else if(8<index && index<14){//科研费
                    indexItem=new Array(9,10,11,12,13);
                    itemFunds(8,indexItem);
                }else if(14<index && index<17){//临床技术开发经费
                    indexItem= new Array(15,16);
                    itemFunds(14,indexItem);
                }else if(18<index && index<24){//差旅费
                    indexItem=new Array(19,20,21,22,23);
                    itemFunds(18,indexItem);
                }else if(24<index && index<28){//会议费
                    indexItem= new Array(25,26,27);
                    itemFunds(24,indexItem);
                }else if(28<index && index<31){//国际合作与交流费
                    indexItem=new Array(29,30);
                    itemFunds(28,indexItem);
                }else if(31<index && index<38){//出版、文献、信息传播、知识产权事务费
                    indexItem=new Array(32,33,34,35,36,37);
                    itemFunds(31,indexItem);
                }else if(38<index && index<41){//人员费
                    indexItem=new Array(39,40);
                    itemFunds(38,indexItem);
                }else if(41<index && index<44){//劳务费
                    indexItem=new Array(42,43);
                    itemFunds(41,indexItem);
                }else if(44<index && index<47){//专家咨询费
                    indexItem=new Array(45,46);
                    itemFunds(44,indexItem);
                }
            }
            var fundIndexs =[1,8,14,17,18,24,28,31,38,41,44,47,48,49];
            itemFunds("total",fundIndexs);//计算合计
        }


    </script>
</c:if>
<style type="text/css">
    .readonlycss{
        background-color: rgb(255, 255, 245);
    }
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
      id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step8"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
    <font style="font-size: 14px; font-weight:bold;color: #333;">八、经费预算表(单位：万元)</font>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <th style="text-align: center">财政资助</th>
            <td colspan="12"><input type="text" class="inputText validate[custom[number]]" style="width: 150px"
                                    name="financeFund" value="${resultMap.financeFund}"/>（万元）</td>
        </tr>
        <tr>
            <th style="text-align: center">单位配套</th>
            <td colspan="12"><input type="text" class="inputText validate[custom[number]]" style="width: 150px"
                                    name="matchedFund" value="${resultMap.matchedFund}"/>（万元）</td>
        </tr>
        <tr>
            <th style="text-align: center">其他来源</th>
            <td colspan="12"><input type="text" class="inputText validate[custom[number]]" style="width: 150px"
                                    name="otherFund" value="${resultMap.otherFund}"/>（万元）</td>
        </tr>
        <tr>
            <th style="text-align: center">合计</th>
            <td colspan="12"><input type="text" class="inputText validate[custom[number]]" style="width: 150px"
                                    name="amountFund" value="${resultMap.amountFund}"/>（万元）</td>
        </tr>
        <tr>
            <th style="text-align: center" colspan="3">年度</th>
            <th colspan="2" style="text-align: center">2017年</th>
            <th colspan="2" style="text-align: center">2018年</th>
            <th colspan="2" style="text-align: center">2019年</th>
            <th colspan="2" style="text-align: center">2020年</th>
            <th colspan="2" style="text-align: center">2021年</th>
        </tr>
        <tr>
            <th style="width: 7%;text-align: center">序号</th>
            <th style="width:16%;text-align: center">经费支出内容</th>
            <th style="width: 7%;text-align: center">合计</th>
            <th style="width: 7%;text-align: center">财政拨款</th>
            <th style="width: 7%;text-align: center">单位配套</th>
            <th style="width: 7%;text-align: center">财政拨款</th>
            <th style="width: 7%;text-align: center">单位配套</th>
            <th style="width: 7%;text-align: center">财政拨款</th>
            <th style="width: 7%;text-align: center">单位配套</th>
            <th style="width: 7%;text-align: center">财政拨款</th>
            <th style="width: 7%;text-align: center">单位配套</th>
            <th style="width: 7%;text-align: center">财政拨款</th>
            <th style="width: 7%;text-align: center">单位配套</th>
        </tr>
        <tr>
            <td style="text-align: center">1</td>
            <th style="text-align: left">一、设备费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_1" value="${resultMap.amountFund_1}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_1" value="${resultMap.financeFund2017_1}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_1" value="${resultMap.matchedFund2017_1}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_1" value="${resultMap.financeFund2018_1}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_1" value="${resultMap.matchedFund2018_1}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_1" value="${resultMap.financeFund2019_1}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_1" value="${resultMap.matchedFund2019_1}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_1" value="${resultMap.financeFund2020_1}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_1" value="${resultMap.matchedFund2020_1}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_1" value="${resultMap.financeFund2021_1}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_1" value="${resultMap.matchedFund2021_1}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">2</td>
            <td>1. 购置设备费</td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_2" value="${resultMap.amountFund_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_2" value="${resultMap.financeFund2017_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_2" value="${resultMap.matchedFund2017_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_2" value="${resultMap.financeFund2018_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_2" value="${resultMap.matchedFund2018_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_2" value="${resultMap.financeFund2019_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_2" value="${resultMap.matchedFund2019_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_2" value="${resultMap.financeFund2020_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_2" value="${resultMap.matchedFund2020_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_2" value="${resultMap.financeFund2021_2}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_2" value="${resultMap.matchedFund2021_2}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">3</td>
            <td>2. 安装、试制费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_3" value="${resultMap.amountFund_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_3" value="${resultMap.financeFund2017_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_3" value="${resultMap.matchedFund2017_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_3" value="${resultMap.financeFund2018_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_3" value="${resultMap.matchedFund2018_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_3" value="${resultMap.financeFund2019_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_3" value="${resultMap.matchedFund2019_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_3" value="${resultMap.financeFund2020_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_3" value="${resultMap.matchedFund2020_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_3" value="${resultMap.financeFund2021_3}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_3" value="${resultMap.matchedFund2021_3}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">4</td>
            <td>3. 设备运输费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_4" value="${resultMap.amountFund_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_4" value="${resultMap.financeFund2017_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_4" value="${resultMap.matchedFund2017_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_4" value="${resultMap.financeFund2018_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_4" value="${resultMap.matchedFund2018_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_4" value="${resultMap.financeFund2019_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_4" value="${resultMap.matchedFund2019_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_4" value="${resultMap.financeFund2020_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_4" value="${resultMap.matchedFund2020_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_4" value="${resultMap.financeFund2021_4}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_4" value="${resultMap.matchedFund2021_4}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">5</td>
            <td>4. 维护运转费</td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_5" value="${resultMap.amountFund_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_5" value="${resultMap.financeFund2017_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_5" value="${resultMap.matchedFund2017_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_5" value="${resultMap.financeFund2018_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_5" value="${resultMap.matchedFund2018_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_5" value="${resultMap.financeFund2019_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_5" value="${resultMap.matchedFund2019_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_5" value="${resultMap.financeFund2020_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_5" value="${resultMap.matchedFund2020_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_5" value="${resultMap.financeFund2021_5}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_5" value="${resultMap.matchedFund2021_5}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">6</td>
            <td>5. 改造升级费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_6" value="${resultMap.amountFund_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_6" value="${resultMap.financeFund2017_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_6" value="${resultMap.matchedFund2017_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_6" value="${resultMap.financeFund2018_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_6" value="${resultMap.matchedFund2018_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_6" value="${resultMap.financeFund2019_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_6" value="${resultMap.matchedFund2019_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_6" value="${resultMap.financeFund2020_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_6" value="${resultMap.matchedFund2020_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_6" value="${resultMap.financeFund2021_6}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_6" value="${resultMap.matchedFund2021_6}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">7</td>
            <td>6. 设备租赁费</td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_7" value="${resultMap.amountFund_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_7" value="${resultMap.financeFund2017_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_7" value="${resultMap.matchedFund2017_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_7" value="${resultMap.financeFund2018_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_7" value="${resultMap.matchedFund2018_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_7" value="${resultMap.financeFund2019_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_7" value="${resultMap.matchedFund2019_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_7" value="${resultMap.financeFund2020_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_7" value="${resultMap.matchedFund2020_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_7" value="${resultMap.financeFund2021_7}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_7" value="${resultMap.matchedFund2021_7}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">8</td>
            <th style="text-align: left">二、科研费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_8" value="${resultMap.amountFund_8}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_8" value="${resultMap.financeFund2017_8}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_8" value="${resultMap.matchedFund2017_8}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_8" value="${resultMap.financeFund2018_8}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_8" value="${resultMap.matchedFund2018_8}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_8" value="${resultMap.financeFund2019_8}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_8" value="${resultMap.matchedFund2019_8}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_8" value="${resultMap.financeFund2020_8}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_8" value="${resultMap.matchedFund2020_8}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_8" value="${resultMap.financeFund2021_8}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_8" value="${resultMap.matchedFund2021_8}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">9</td>
            <td>1.材料费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_9" value="${resultMap.amountFund_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_9" value="${resultMap.financeFund2017_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_9" value="${resultMap.matchedFund2017_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_9" value="${resultMap.financeFund2018_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_9" value="${resultMap.matchedFund2018_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_9" value="${resultMap.financeFund2019_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_9" value="${resultMap.matchedFund2019_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_9" value="${resultMap.financeFund2020_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_9" value="${resultMap.matchedFund2020_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_9" value="${resultMap.financeFund2021_9}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_9" value="${resultMap.matchedFund2021_9}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">10</td>
            <td>2.测试费</td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_10" value="${resultMap.amountFund_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_10" value="${resultMap.financeFund2017_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_10" value="${resultMap.matchedFund2017_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_10" value="${resultMap.financeFund2018_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_10" value="${resultMap.matchedFund2018_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_10" value="${resultMap.financeFund2019_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_10" value="${resultMap.matchedFund2019_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_10" value="${resultMap.financeFund2020_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_10" value="${resultMap.matchedFund2020_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_10" value="${resultMap.financeFund2021_10}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_10" value="${resultMap.matchedFund2021_10}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">11</td>
            <td>3.化验费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_11" value="${resultMap.amountFund_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_11" value="${resultMap.financeFund2017_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_11" value="${resultMap.matchedFund2017_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_11" value="${resultMap.financeFund2018_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_11" value="${resultMap.matchedFund2018_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_11" value="${resultMap.financeFund2019_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_11" value="${resultMap.matchedFund2019_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_11" value="${resultMap.financeFund2020_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_11" value="${resultMap.matchedFund2020_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_11" value="${resultMap.financeFund2021_11}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_11" value="${resultMap.matchedFund2021_11}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">12</td>
            <td>4.加工费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_12" value="${resultMap.amountFund_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_12" value="${resultMap.financeFund2017_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_12" value="${resultMap.matchedFund2017_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_12" value="${resultMap.financeFund2018_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_12" value="${resultMap.matchedFund2018_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_12" value="${resultMap.financeFund2019_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_12" value="${resultMap.matchedFund2019_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_12" value="${resultMap.financeFund2020_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_12" value="${resultMap.matchedFund2020_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_12" value="${resultMap.financeFund2021_12}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_12" value="${resultMap.matchedFund2021_12}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">13</td>
            <td>5.燃料动力费</td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_13" value="${resultMap.amountFund_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_13" value="${resultMap.financeFund2017_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_13" value="${resultMap.matchedFund2017_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_13" value="${resultMap.financeFund2018_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_13" value="${resultMap.matchedFund2018_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_13" value="${resultMap.financeFund2019_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_13" value="${resultMap.matchedFund2019_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_13" value="${resultMap.financeFund2020_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_13" value="${resultMap.matchedFund2020_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_13" value="${resultMap.financeFund2021_13}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_13" value="${resultMap.matchedFund2021_13}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">14</td>
            <th style="text-align: left">三、临床技术开发经费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_14" value="${resultMap.amountFund_14}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_14" value="${resultMap.financeFund2017_14}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_14" value="${resultMap.matchedFund2017_14}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_14" value="${resultMap.financeFund2018_14}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_14" value="${resultMap.matchedFund2018_14}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_14" value="${resultMap.financeFund2019_14}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_14" value="${resultMap.matchedFund2019_14}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_14" value="${resultMap.financeFund2020_14}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_14" value="${resultMap.matchedFund2020_14}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_14" value="${resultMap.financeFund2021_14}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_14" value="${resultMap.matchedFund2021_14}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">15</td>
            <td>1.材料费</td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_15" value="${resultMap.amountFund_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_15" value="${resultMap.financeFund2017_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_15" value="${resultMap.matchedFund2017_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_15" value="${resultMap.financeFund2018_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_15" value="${resultMap.matchedFund2018_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_15" value="${resultMap.financeFund2019_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_15" value="${resultMap.matchedFund2019_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_15" value="${resultMap.financeFund2020_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_15" value="${resultMap.matchedFund2020_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_15" value="${resultMap.financeFund2021_15}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_15" value="${resultMap.matchedFund2021_15}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">16</td>
            <td>2.测试费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_16" value="${resultMap.amountFund_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_16" value="${resultMap.financeFund2017_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_16" value="${resultMap.matchedFund2017_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_16" value="${resultMap.financeFund2018_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_16" value="${resultMap.matchedFund2018_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_16" value="${resultMap.financeFund2019_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_16" value="${resultMap.matchedFund2019_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_16" value="${resultMap.financeFund2020_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_16" value="${resultMap.matchedFund2020_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_16" value="${resultMap.financeFund2021_16}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_16" value="${resultMap.matchedFund2021_16}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">17</td>
            <th style="text-align: left">四、培训费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_17" value="${resultMap.amountFund_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_17" value="${resultMap.financeFund2017_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_17" value="${resultMap.matchedFund2017_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_17" value="${resultMap.financeFund2018_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_17" value="${resultMap.matchedFund2018_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_17" value="${resultMap.financeFund2019_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_17" value="${resultMap.matchedFund2019_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_17" value="${resultMap.financeFund2020_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_17" value="${resultMap.matchedFund2020_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_17" value="${resultMap.financeFund2021_17}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_17" value="${resultMap.matchedFund2021_17}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">18</td>
            <th style="text-align: left">五、差旅费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_18" value="${resultMap.amountFund_18}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_18" value="${resultMap.financeFund2017_18}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_18" value="${resultMap.matchedFund2017_18}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_18" value="${resultMap.financeFund2018_18}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_18" value="${resultMap.matchedFund2018_18}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_18" value="${resultMap.financeFund2019_18}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_18" value="${resultMap.matchedFund2019_18}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_18" value="${resultMap.financeFund2020_18}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_18" value="${resultMap.matchedFund2020_18}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_18" value="${resultMap.financeFund2021_18}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_18" value="${resultMap.matchedFund2021_18}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">19</td>
            <td>1. 出境交通费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_19" value="${resultMap.amountFund_19}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_19" value="${resultMap.financeFund2017_19}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_19" value="${resultMap.matchedFund2017_19}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_19" value="${resultMap.financeFund2018_19}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_19" value="${resultMap.matchedFund2018_19}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_19" value="${resultMap.financeFund2019_19}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_19" value="${resultMap.matchedFund2019_19}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_19" value="${resultMap.financeFund2020_19}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_19" value="${resultMap.matchedFund2020_19}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_19" value="${resultMap.financeFund2021_19}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_19" value="${resultMap.matchedFund2021_19}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">20</td>
            <td>2. 国内交通费</td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_20" value="${resultMap.amountFund_20}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_20" value="${resultMap.financeFund2017_20}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_20" value="${resultMap.matchedFund2017_20}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_20" value="${resultMap.financeFund2018_20}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_20" value="${resultMap.matchedFund2018_20}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_20" value="${resultMap.financeFund2019_20}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_20" value="${resultMap.matchedFund2019_20}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_20" value="${resultMap.financeFund2020_20}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_20" value="${resultMap.matchedFund2020_20}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_20" value="${resultMap.financeFund2021_20}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_20" value="${resultMap.matchedFund2021_20}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">21</td>
            <td>3. 出境住宿、餐饮费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_21" value="${resultMap.amountFund_21}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_21" value="${resultMap.financeFund2017_21}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_21" value="${resultMap.matchedFund2017_21}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_21" value="${resultMap.financeFund2018_21}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_21" value="${resultMap.matchedFund2018_21}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_21" value="${resultMap.financeFund2019_21}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_21" value="${resultMap.matchedFund2019_21}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_21" value="${resultMap.financeFund2020_21}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_21" value="${resultMap.matchedFund2020_21}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_21" value="${resultMap.financeFund2021_21}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_21" value="${resultMap.matchedFund2021_21}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">22</td>
            <td>4. 国内住宿、餐饮费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_22" value="${resultMap.amountFund_22}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_22" value="${resultMap.financeFund2017_22}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_22" value="${resultMap.matchedFund2017_22}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_22" value="${resultMap.financeFund2018_22}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_22" value="${resultMap.matchedFund2018_22}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_22" value="${resultMap.financeFund2019_22}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_22" value="${resultMap.matchedFund2019_22}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_22" value="${resultMap.financeFund2020_22}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_22" value="${resultMap.matchedFund2020_22}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_22" value="${resultMap.financeFund2021_22}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_22" value="${resultMap.matchedFund2021_22}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">23</td>
            <td>5. 差旅补贴
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_23" value="${resultMap.amountFund_23}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_23" value="${resultMap.financeFund2017_23}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_23" value="${resultMap.matchedFund2017_23}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_23" value="${resultMap.financeFund2018_23}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_23" value="${resultMap.matchedFund2018_23}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_23" value="${resultMap.financeFund2019_23}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_23" value="${resultMap.matchedFund2019_23}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_23" value="${resultMap.financeFund2020_23}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_23" value="${resultMap.matchedFund2020_23}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_23" value="${resultMap.financeFund2021_23}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_23" value="${resultMap.matchedFund2021_23}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">24</td>
            <th style="text-align: left">六、会议费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_24" value="${resultMap.amountFund_24}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_24" value="${resultMap.financeFund2017_24}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_24" value="${resultMap.matchedFund2017_24}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_24" value="${resultMap.financeFund2018_24}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_24" value="${resultMap.matchedFund2018_24}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_24" value="${resultMap.financeFund2019_24}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_24" value="${resultMap.matchedFund2019_24}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_24" value="${resultMap.financeFund2020_24}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_24" value="${resultMap.matchedFund2020_24}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_24" value="${resultMap.financeFund2021_24}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_24" value="${resultMap.matchedFund2021_24}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">25</td>
            <td>1. 学术研讨会议费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_25" value="${resultMap.amountFund_25}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_25" value="${resultMap.financeFund2017_25}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_25" value="${resultMap.matchedFund2017_25}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_25" value="${resultMap.financeFund2018_25}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_25" value="${resultMap.matchedFund2018_25}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_25" value="${resultMap.financeFund2019_25}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_25" value="${resultMap.matchedFund2019_25}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_25" value="${resultMap.financeFund2020_25}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_25" value="${resultMap.matchedFund2020_25}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_25" value="${resultMap.financeFund2021_25}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_25" value="${resultMap.matchedFund2021_25}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">26</td>
            <td>2. 咨询会议费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_26" value="${resultMap.amountFund_26}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_26" value="${resultMap.financeFund2017_26}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_26" value="${resultMap.matchedFund2017_26}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_26" value="${resultMap.financeFund2018_26}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_26" value="${resultMap.matchedFund2018_26}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_26" value="${resultMap.financeFund2019_26}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_26" value="${resultMap.matchedFund2019_26}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_26" value="${resultMap.financeFund2020_26}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_26" value="${resultMap.matchedFund2020_26}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_26" value="${resultMap.financeFund2021_26}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_26" value="${resultMap.matchedFund2021_26}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">27</td>
            <td>3. 项目协调会议费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_27" value="${resultMap.amountFund_27}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_27" value="${resultMap.financeFund2017_27}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_27" value="${resultMap.matchedFund2017_27}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_27" value="${resultMap.financeFund2018_27}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_27" value="${resultMap.matchedFund2018_27}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_27" value="${resultMap.financeFund2019_27}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_27" value="${resultMap.matchedFund2019_27}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_27" value="${resultMap.financeFund2020_27}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_27" value="${resultMap.matchedFund2020_27}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_27" value="${resultMap.financeFund2021_27}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_27" value="${resultMap.matchedFund2021_27}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">28</td>
            <th style="text-align: left">七、国际合作与交流费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_28" value="${resultMap.amountFund_28}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_28" value="${resultMap.financeFund2017_28}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_28" value="${resultMap.matchedFund2017_28}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_28" value="${resultMap.financeFund2018_28}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_28" value="${resultMap.matchedFund2018_28}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_28" value="${resultMap.financeFund2019_28}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_28" value="${resultMap.matchedFund2019_28}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_28" value="${resultMap.financeFund2020_28}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_28" value="${resultMap.matchedFund2020_28}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_28" value="${resultMap.financeFund2021_28}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_28" value="${resultMap.matchedFund2021_28}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">29</td>
            <td>1.外国专家来苏工作费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_29" value="${resultMap.amountFund_29}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_29" value="${resultMap.financeFund2017_29}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_29" value="${resultMap.matchedFund2017_29}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_29" value="${resultMap.financeFund2018_29}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_29" value="${resultMap.matchedFund2018_29}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_29" value="${resultMap.financeFund2019_29}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_29" value="${resultMap.matchedFund2019_29}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_29" value="${resultMap.financeFund2020_29}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_29" value="${resultMap.matchedFund2020_29}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_29" value="${resultMap.financeFund2021_29}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_29" value="${resultMap.matchedFund2021_29}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">30</td>
            <td>2. 开展国际合作交流费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_30" value="${resultMap.amountFund_30}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_30" value="${resultMap.financeFund2017_30}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_30" value="${resultMap.matchedFund2017_30}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_30" value="${resultMap.financeFund2018_30}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_30" value="${resultMap.matchedFund2018_30}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_30" value="${resultMap.financeFund2019_30}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_30" value="${resultMap.matchedFund2019_30}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_30" value="${resultMap.financeFund2020_30}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_30" value="${resultMap.matchedFund2020_30}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_30" value="${resultMap.financeFund2021_30}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_30" value="${resultMap.matchedFund2021_30}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">31</td>
            <th style="text-align: left">八、出版、文献、信息传播、知识产权事务费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_31" value="${resultMap.amountFund_31}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_31" value="${resultMap.financeFund2017_31}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_31" value="${resultMap.matchedFund2017_31}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_31" value="${resultMap.financeFund2018_31}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_31" value="${resultMap.matchedFund2018_31}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_31" value="${resultMap.financeFund2019_31}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_31" value="${resultMap.matchedFund2019_31}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_31" value="${resultMap.financeFund2020_31}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_31" value="${resultMap.matchedFund2020_31}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_31" value="${resultMap.financeFund2021_31}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_31" value="${resultMap.matchedFund2021_31}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">32</td>
            <td>1. 出版费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_32" value="${resultMap.amountFund_32}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_32" value="${resultMap.financeFund2017_32}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_32" value="${resultMap.matchedFund2017_32}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_32" value="${resultMap.financeFund2018_32}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_32" value="${resultMap.matchedFund2018_32}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_32" value="${resultMap.financeFund2019_32}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_32" value="${resultMap.matchedFund2019_32}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_32" value="${resultMap.financeFund2020_32}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_32" value="${resultMap.matchedFund2020_32}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_32" value="${resultMap.financeFund2021_32}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_32" value="${resultMap.matchedFund2021_32}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">33</td>
            <td>2. 资料费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_33" value="${resultMap.amountFund_33}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_33" value="${resultMap.financeFund2017_33}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_33" value="${resultMap.matchedFund2017_33}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_33" value="${resultMap.financeFund2018_33}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_33" value="${resultMap.matchedFund2018_33}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_33" value="${resultMap.financeFund2019_33}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_33" value="${resultMap.matchedFund2019_33}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_33" value="${resultMap.financeFund2020_33}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_33" value="${resultMap.matchedFund2020_33}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_33" value="${resultMap.financeFund2021_33}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_33" value="${resultMap.matchedFund2021_33}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">34</td>
            <td>3. 专利申请费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_34" value="${resultMap.amountFund_34}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_34" value="${resultMap.financeFund2017_34}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_34" value="${resultMap.matchedFund2017_34}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_34" value="${resultMap.financeFund2018_34}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_34" value="${resultMap.matchedFund2018_34}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_34" value="${resultMap.financeFund2019_34}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_34" value="${resultMap.matchedFund2019_34}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_34" value="${resultMap.financeFund2020_34}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_34" value="${resultMap.matchedFund2020_34}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_34" value="${resultMap.financeFund2021_34}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_34" value="${resultMap.matchedFund2021_34}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">35</td>
            <td>4. 文献检索费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_35" value="${resultMap.amountFund_35}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_35" value="${resultMap.financeFund2017_35}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_35" value="${resultMap.matchedFund2017_35}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_35" value="${resultMap.financeFund2018_35}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_35" value="${resultMap.matchedFund2018_35}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_35" value="${resultMap.financeFund2019_35}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_35" value="${resultMap.matchedFund2019_35}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_35" value="${resultMap.financeFund2020_35}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_35" value="${resultMap.matchedFund2020_35}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_35" value="${resultMap.financeFund2021_35}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_35" value="${resultMap.matchedFund2021_35}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">36</td>
            <td>5. 入网、通信费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_36" value="${resultMap.amountFund_36}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_36" value="${resultMap.financeFund2017_36}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_36" value="${resultMap.matchedFund2017_36}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_36" value="${resultMap.financeFund2018_36}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_36" value="${resultMap.matchedFund2018_36}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_36" value="${resultMap.financeFund2019_36}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_36" value="${resultMap.matchedFund2019_36}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_36" value="${resultMap.financeFund2020_36}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_36" value="${resultMap.matchedFund2020_36}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_36" value="${resultMap.financeFund2021_36}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_36" value="${resultMap.matchedFund2021_36}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">37</td>
            <td>6. 其他知识产权事务费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_37" value="${resultMap.amountFund_37}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_37" value="${resultMap.financeFund2017_37}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_37" value="${resultMap.matchedFund2017_37}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_37" value="${resultMap.financeFund2018_37}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_37" value="${resultMap.matchedFund2018_37}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_37" value="${resultMap.financeFund2019_37}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_37" value="${resultMap.matchedFund2019_37}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_37" value="${resultMap.financeFund2020_37}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_37" value="${resultMap.matchedFund2020_37}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_37" value="${resultMap.financeFund2021_37}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_37" value="${resultMap.matchedFund2021_37}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">38</td>
            <th style="text-align: left">九、人员费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_38" value="${resultMap.amountFund_38}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_38" value="${resultMap.financeFund2017_38}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_38" value="${resultMap.matchedFund2017_38}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_38" value="${resultMap.financeFund2018_38}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_38" value="${resultMap.matchedFund2018_38}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_38" value="${resultMap.financeFund2019_38}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_38" value="${resultMap.matchedFund2019_38}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_38" value="${resultMap.financeFund2020_38}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_38" value="${resultMap.matchedFund2020_38}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_38" value="${resultMap.financeFund2021_38}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_38" value="${resultMap.matchedFund2021_38}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">39</td>
            <td>1. 引进成员工资
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_39" value="${resultMap.amountFund_39}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_39" value="${resultMap.financeFund2017_39}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_39" value="${resultMap.matchedFund2017_39}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_39" value="${resultMap.financeFund2018_39}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_39" value="${resultMap.matchedFund2018_39}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_39" value="${resultMap.financeFund2019_39}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_39" value="${resultMap.matchedFund2019_39}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_39" value="${resultMap.financeFund2020_39}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_39" value="${resultMap.matchedFund2020_39}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_39" value="${resultMap.financeFund2021_39}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_39" value="${resultMap.matchedFund2021_39}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">40</td>
            <td>2.项目其他成员按事业费削减比例列支工资
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_40" value="${resultMap.amountFund_40}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_40" value="${resultMap.financeFund2017_40}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_40" value="${resultMap.matchedFund2017_40}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_40" value="${resultMap.financeFund2018_40}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_40" value="${resultMap.matchedFund2018_40}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_40" value="${resultMap.financeFund2019_40}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_40" value="${resultMap.matchedFund2019_40}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_40" value="${resultMap.financeFund2020_40}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_40" value="${resultMap.matchedFund2020_40}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_40" value="${resultMap.financeFund2021_40}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_40" value="${resultMap.matchedFund2021_40}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">41</td>
            <th style="text-align: left">十、劳务费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_41" value="${resultMap.amountFund_41}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_41" value="${resultMap.financeFund2017_41}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_41" value="${resultMap.matchedFund2017_41}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_41" value="${resultMap.financeFund2018_41}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_41" value="${resultMap.matchedFund2018_41}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_41" value="${resultMap.financeFund2019_41}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_41" value="${resultMap.matchedFund2019_41}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_41" value="${resultMap.financeFund2020_41}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_41" value="${resultMap.matchedFund2020_41}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_41" value="${resultMap.financeFund2021_41}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_41" value="${resultMap.matchedFund2021_41}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">42</td>
            <td>1. 无工资性收入的项目成员劳务性费用
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_42" value="${resultMap.amountFund_42}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_42" value="${resultMap.financeFund2017_42}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_42" value="${resultMap.matchedFund2017_42}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_42" value="${resultMap.financeFund2018_42}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_42" value="${resultMap.matchedFund2018_42}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_42" value="${resultMap.financeFund2019_42}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_42" value="${resultMap.matchedFund2019_42}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_42" value="${resultMap.financeFund2020_42}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_42" value="${resultMap.matchedFund2020_42}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_42" value="${resultMap.financeFund2021_42}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_42" value="${resultMap.matchedFund2021_42}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">43</td>
            <td>2. 项目组临时聘用人员劳务性费用
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_43" value="${resultMap.amountFund_43}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_43" value="${resultMap.financeFund2017_43}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_43" value="${resultMap.matchedFund2017_43}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_43" value="${resultMap.financeFund2018_43}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_43" value="${resultMap.matchedFund2018_43}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_43" value="${resultMap.financeFund2019_43}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_43" value="${resultMap.matchedFund2019_43}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_43" value="${resultMap.financeFund2020_43}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_43" value="${resultMap.matchedFund2020_43}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_43" value="${resultMap.financeFund2021_43}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_43" value="${resultMap.matchedFund2021_43}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">44</td>
            <th style="text-align: left">十一、专家咨询费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_44" value="${resultMap.amountFund_44}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_44" value="${resultMap.financeFund2017_44}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_44" value="${resultMap.matchedFund2017_44}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_44" value="${resultMap.financeFund2018_44}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_44" value="${resultMap.matchedFund2018_44}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_44" value="${resultMap.financeFund2019_44}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_44" value="${resultMap.matchedFund2019_44}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_44" value="${resultMap.financeFund2020_44}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_44" value="${resultMap.matchedFund2020_44}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_44" value="${resultMap.financeFund2021_44}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_44" value="${resultMap.matchedFund2021_44}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">45</td>
            <td>1. 以会议形式组织的咨询费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_45" value="${resultMap.amountFund_45}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_45" value="${resultMap.financeFund2017_45}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_45" value="${resultMap.matchedFund2017_45}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_45" value="${resultMap.financeFund2018_45}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_45" value="${resultMap.matchedFund2018_45}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_45" value="${resultMap.financeFund2019_45}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_45" value="${resultMap.matchedFund2019_45}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_45" value="${resultMap.financeFund2020_45}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_45" value="${resultMap.matchedFund2020_45}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_45" value="${resultMap.financeFund2021_45}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_45" value="${resultMap.matchedFund2021_45}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">46</td>
            <td>2. 以通讯形式组织的咨询费
            </th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_46" value="${resultMap.amountFund_46}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_46" value="${resultMap.financeFund2017_46}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_46" value="${resultMap.matchedFund2017_46}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_46" value="${resultMap.financeFund2018_46}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_46" value="${resultMap.matchedFund2018_46}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_46" value="${resultMap.financeFund2019_46}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_46" value="${resultMap.matchedFund2019_46}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_46" value="${resultMap.financeFund2020_46}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_46" value="${resultMap.matchedFund2020_46}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_46" value="${resultMap.financeFund2021_46}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_46" value="${resultMap.matchedFund2021_46}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">47</td>
            <th style="text-align: left">十二、项目管理费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_47" value="${resultMap.amountFund_47}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_47" value="${resultMap.financeFund2017_47}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_47" value="${resultMap.matchedFund2017_47}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_47" value="${resultMap.financeFund2018_47}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_47" value="${resultMap.matchedFund2018_47}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_47" value="${resultMap.financeFund2019_47}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_47" value="${resultMap.matchedFund2019_47}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_47" value="${resultMap.financeFund2020_47}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_47" value="${resultMap.matchedFund2020_47}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_47" value="${resultMap.financeFund2021_47}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_47" value="${resultMap.matchedFund2021_47}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">48</td>
            <th style="text-align: left">十三、外部协作费</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_48" value="${resultMap.amountFund_48}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_48" value="${resultMap.financeFund2017_48}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_48" value="${resultMap.matchedFund2017_48}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_48" value="${resultMap.financeFund2018_48}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_48" value="${resultMap.matchedFund2018_48}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_48" value="${resultMap.financeFund2019_48}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_48" value="${resultMap.matchedFund2019_48}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_48" value="${resultMap.financeFund2020_48}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_48" value="${resultMap.matchedFund2020_48}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_48" value="${resultMap.financeFund2021_48}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_48" value="${resultMap.matchedFund2021_48}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">49</td>
            <th style="text-align: left">十四、其他</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_49" value="${resultMap.amountFund_49}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2017_49" value="${resultMap.financeFund2017_49}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2017_49" value="${resultMap.matchedFund2017_49}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2018_49" value="${resultMap.financeFund2018_49}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2018_49" value="${resultMap.matchedFund2018_49}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2019_49" value="${resultMap.financeFund2019_49}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2019_49" value="${resultMap.matchedFund2019_49}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2020_49" value="${resultMap.financeFund2020_49}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2020_49" value="${resultMap.matchedFund2020_49}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="financeFund2021_49" value="${resultMap.financeFund2021_49}"/></td>
            <td><input type="text" class="inputText validate[custom[number]]" onchange="updateFunds(this)" style="width: 90%"
                       name="matchedFund2021_49" value="${resultMap.matchedFund2021_49}"/></td>
        </tr>
        <tr>
            <td style="text-align: center">50</td>
            <th style="text-align: left">合&#12288;计</th>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="amountFund_total" value="${resultMap.amountFund_total}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2017_total" value="${resultMap.financeFund2017_total}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2017_total" value="${resultMap.matchedFund2017_total}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2018_total" value="${resultMap.financeFund2018_total}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2018_total" value="${resultMap.matchedFund2018_total}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2019_total" value="${resultMap.financeFund2019_total}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2019_total" value="${resultMap.matchedFund2019_total}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2020_total" value="${resultMap.financeFund2020_total}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2020_total" value="${resultMap.matchedFund2020_total}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="financeFund2021_total" value="${resultMap.financeFund2021_total}"/></td>
            <td class="readonlycss"><input type="text" class="inputText validate[custom[number]] readonlycss" style="width: 90%" readonly="readonly" title="自动计算，不允许编辑"
                       name="matchedFund2021_total" value="${resultMap.matchedFund2021_total}"/></td>
        </tr>
    </table>
<p style="font-weight: bold">注：经费支出内容中：第九：人员费和第十：劳务费累计不超过项目总经费70%。</p>
</form>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step7')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step9')" class="search" value="下一步"/>
    </div>
</c:if>



