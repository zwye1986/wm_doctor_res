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

        function inputOnBlur() {
            var sum = 0;
            $('.resBaseinputValue').each(function (){
                var value = $(this).val();
                sum +=Number(value);
            })
            // 收支总额
            $("#amountOfIncome").val(sum);
        }
        function save() {
            if(false==$("#addForm").validationEngine("validate")){
                return false;
            }
            var sumMoney = ${amountMoney};
            var amountOfMoneys = $("#amountOfMoney").val();
            if(Number(sumMoney) > 0 && Number(amountOfMoneys) != Number(sumMoney)){
                jboxTip("资金上报金额与资金来源合计不一致！");
                return false;
            }
            var url = "<s:url value='/res/resFunds/saveProvinceFund'/>";
            jboxPost(url,$('#addForm').serialize(),function(resp) {
                if(resp==1) {
                    jboxTip("操作成功")
                    window.parent.search();
                    jboxClose();
                }
            }, null, false);
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
<body>
<div class="mainright">
    <div class="basic" style="max-height: 392px;">
        <form id="addForm" style="position: relative;">
            <input name="recordFlow" type="hidden" value="${provinceFund.recordFlow}"/>
            <input name="reportDate" type="hidden" value="${provinceFund.reportDate}"/>
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 30px;">
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>资金到位日期：</th>
                    <td >
                        <input type="text" name="inPlaceDate" value="${provinceFund.inPlaceDate}"  style="width: 267px;" class="input validate[required]"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>资金开始使用时间：</th>
                    <td>
                        <input type="text" name="startUsingDate" value="${provinceFund.startUsingDate}" style="width: 267px;" class="input validate[required]"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>资金使用截止时间：</th>
                    <td>
                        <input type="text" name="stopUsingDate" value="${provinceFund.stopUsingDate}" style="width: 267px;" class="input validate[required]"  readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>金额：</th>
                    <td>
                        <input type="text" id="amountOfMoney" name="amountOfMoney" value="${provinceFund.amountOfMoney}" onblur="formatNumbers(this);"
                               class="input validate[required]">（万元）
                    </td>
                </tr>
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
