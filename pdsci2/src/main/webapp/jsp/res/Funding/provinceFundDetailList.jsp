<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}

    </style>
    <script type="text/javascript">
        $(function () {
            $("#form1").width($("#form2").width())
            $('.begin').each(function () {
                countSum(this);
            })
        })
        function countSum(obj) {
            var sum = 0;
            var inputs = $(obj).closest('tr').find('input');
            var len = inputs.length;
            for(var i=0;i<len-1;i++){
                var val = inputs.eq(i).val();
                    sum += Number(val) * 10000;
            }
            if(sum >= 0){
                inputs.last().val(sum/10000);

                var y = String(sum/10000).indexOf(".") + 1;
                var count = String(sum/10000).length - y;

                     if(y >0 &&count>5){
                     inputs.last().val((sum/10000).toFixed(4));
                 }
            }
        }
        function save() {
            if(!$("#addForm").validationEngine("validate")){
                return false;
            }
            var sumMoney = 0;
            $('.InputCheck').each(function () {
                var value = $(this).val();
                sumMoney += Number(value) * 10000;
            });
            sumMoney = sumMoney.toFixed(0);

            var money = (Number(${provinceFund.amountOfMoney}) * 10000).toFixed(0);
            if(Number(money) != Number(sumMoney)){
                jboxTip("资金来源合计与上报金额 ${provinceFund.amountOfMoney}(万元)不一致！");
                return false;
            }
            var data = [];
            $('.dataInput').each(function () {
                var recordFlow = $(this).attr('recordFlow');
                var sourcesOfFundsId = $(this).attr('sourcesOfFundsId');
                var projectOfFundsId = $(this).attr('projectOfFundsId');
                var value = $(this).val();
                var single = {'recordFlow':recordFlow,
                    'sourcesOfFundsId':sourcesOfFundsId,
                    'projectOfFundsId':projectOfFundsId,
                    'value':value
                }
                data.push(single);
            });
            var jsonData = {"data":data,"mainFlow":"${param.mainFlow}"};
            jboxPost("<s:url value='/res/resFunds/saveProvinceFundDetail'/>","jsonData="+JSON.stringify(jsonData),function (resp) {
                if(resp==1) {
                    setTimeout(function(){
                        window.parent.search();
                        jboxClose();
                        jboxTip("保存成功！");
                    },500);
                }
            },null,false);
        }
        function formatNumbers(obj){
            var re = (/^(?!0\d|[.]+$)\d{0,20}(\.\d{0,4})?$/).test(obj.value) ;
            if(!re)
            {
                $(obj).val("").focus();
                jboxTip("金额格式不正确，请输入正整数或者小数(小数最多保留4位)！");
            }
        }
    </script>
</head>
<body >
<div class="mainright"  style="max-height: 800px; overflow: scroll; width: 900px; height: 700px;">
    <div class="basic" >
        <form id="addForm" style="position: relative;">
            <table class="grid" id="form1" style="width:100%; margin-bottom: 10px; margin-top: 30px;">
                <tr>
                    <td colspan='20' >资金来源</td>
                </tr>
                <c:forEach items="${dictTypeEnumSourcesOfFundsList}" var="dict">
                    <tr>
                        <th style="width: 300px" >${dict.dictName}(万元)</th>
                        <td  ><input type="text" class="input dataInput InputCheck" name="amountOfMoney" value="${detailMap[dict.dictId].amountOfMoney}"
                                   recordFlow="${detailMap[dict.dictId].recordFlow}" onblur="formatNumbers(this);"
                                   sourcesOfFundsId="${dict.dictId}" projectOfFundsId=""></td>
                    </tr>
                </c:forEach>
            </table>
            <table class="grid" id="form2" style="width:100%; margin-bottom: 10px; margin-top: 30px;">
                <tr>
                     <td colspan="20">经费拨付用途</td>
                </tr>
                <tr>
                    <th ><p>统计项目</p></th>
                    <c:forEach items="${dictTypeEnumProjectOfFundsList}" var="dict">
                        <th>${dict.dictName}(万元)</th>
                    </c:forEach>
                    <th>总额(万元)</th>
                </tr>
                <c:forEach items="${dictTypeEnumSourcesOfFundsList}" var="dict">
                    <tr>
                    <td style="text-align: center"><p style="white-space: nowrap">${dict.dictName}</p></td>
                    <c:forEach items="${dictTypeEnumProjectOfFundsList}" var="dict2" varStatus="v">
                        <c:set var="key" value="${dict.dictId}${dict2.dictId}"></c:set>
                        <td style="text-align: center">
                            <input type="text" class="input dataInput ${v.first?'begin':''}" style="width: 100px;" name="amountOfMoney"
                                   sourcesOfFundsId="${dict.dictId}" projectOfFundsId="${dict2.dictId}" onblur="formatNumbers(this),countSum(this);"
                                   recordFlow="${detailMap[key].recordFlow}" value="${detailMap[key].amountOfMoney}">
                        </td>
                    </c:forEach>
                    <td style="text-align: center"><input type="text" class="input" style="width: 100px;" value="" readonly="readonly"></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <p style="text-align: center;">
            <input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>&#12288;
            <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
