<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <script type="text/javascript">
            function nextOpt(step) {
                if (false == $("#projForm").validationEngine("validate")) {
                    return false;
                }
                var form = $('#projForm');
                var action = form.attr('action');
                action += "?nextPageName=" + step;
                form.attr("action", action);
                $('#nxt').attr({"disabled": "disabled"});
                $('#prev').attr({"disabled": "disabled"});
                form.submit();
            }

            $(function () {
                $('[calc]').on('keyup', function () {
                    var v = $(this).attr('calc');
                    var totle = 0;
                    $('[calc="' + v + '"]:not(.totle)').each(function () {
                        var val = this.value;
                        if (val && !isNaN(val)) {
                            totle += parseFloat(val);
                        }
                    });
                    if (totle % 1 == 0) {
                        totle = totle.toFixed(0);
                    } else {
                        totle = parseFloat(totle.toFixed(4));
                    }
                    $('[calc="' + v + '"].totle').val(totle);
                });
            });
        </script>
    </c:if>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step8"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
                    <font style="font-size: 14px; font-weight:bold;color: #333;">七、经费预算分类细目</font>
                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th style="text-align: center; width: 15%" rowspan="2">科目</th>
                            <th style="text-align: center;width: 20%" rowspan="2">细目</th>
                            <th style="text-align: center;width: 10%" rowspan="2">规格</th>
                            <th style="text-align: center;width: 10%" rowspan="2">数量</th>
                            <th style="text-align: center;width: 10%" rowspan="2">单价</th>
                            <th style="text-align: center;width: 27%" colspan="3">经费预算(万元)</th>
                            <th style="text-align: center;width: 8%" rowspan="2">备注</th>
                        </tr>
                            <th style="text-align: center;">总计</th>
                            <th style="text-align: center;">资助</th>
                            <th style="text-align: center;">自筹</th>
                        <tr>
                            <th style="text-align: center;">总经费</th>
                            <td colspan="4">
                                <input type="text" name="amountFunds1" value="${resultMap.amountFunds1}"
                                       class="inputText validate[custom[number]]" style="width:150px"/>
                            </td>
                            <td>
                                <input type="text" name="budgetAmountFund" value="${resultMap.budgetAmountFund}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="imburseBudgetFunds" value="${resultMap.imburseBudgetFunds}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="raiseBudgetFunds" value="${resultMap.raiseBudgetFunds}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="budgetRemark" value="${resultMap.budgetRemark}"
                                       class="inputText" style="width:90%"/>
                            </td>
                        </tr>

                        <tr style="border-bottom: hidden">
                            <th style="text-align: center;" rowspan="7">科研业务费</th>
                            <td>
                                1．调研
                            </td>
                            <td>
                                <input type="text" name="businessType" value="${resultMap.businessType}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessNumber" value="${resultMap.businessNumber}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessPrice" value="${resultMap.businessPrice}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessAmount" value="${resultMap.businessAmount}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessImburse"
                                       value="${resultMap.businessImburse}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessRaise" value="${resultMap.businessRaise}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td rowspan="7">
                                <input type="text" name="businessRemark" value="${resultMap.businessRemark}"
                                       class="inputText" style="width:90%"/>
                            </td>
                        </tr>
                        <tr style="border-bottom: hidden">
                            <td>
                                2．学术交流
                            </td>
                            <td>
                                <input type="text" name="businessType2" value="${resultMap.businessType2}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessNumber2" value="${resultMap.businessNumber2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessPrice2" value="${resultMap.businessPrice2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessAmount2" value="${resultMap.businessAmount2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessImburse2"
                                       value="${resultMap.businessImburse2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessRaise2" value="${resultMap.businessRaise2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr style="border-bottom: hidden">
                            <td>
                               3．发表论文、著作
                            </td>
                            <td>
                                <input type="text" name="businessType3" value="${resultMap.businessType3}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessNumber3" value="${resultMap.businessNumber3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessPrice3" value="${resultMap.businessPrice3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessAmount3" value="${resultMap.businessAmount3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessImburse3"
                                       value="${resultMap.businessImburse3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessRaise3" value="${resultMap.businessRaise3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr style="border-bottom: hidden">
                            <td>
                                4．查新检索
                            </td>
                            <td>
                                <input type="text" name="businessType4" value="${resultMap.businessType4}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessNumber4" value="${resultMap.businessNumber4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessPrice4" value="${resultMap.businessPrice4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessAmount4" value="${resultMap.businessAmount4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessImburse4"
                                       value="${resultMap.businessImburse4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessRaise4" value="${resultMap.businessRaise4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr style="border-bottom: hidden">
                            <td>
                                5．业务资料印刷
                            </td>
                            <td>
                                <input type="text" name="businessType5" value="${resultMap.businessType5}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessNumber5" value="${resultMap.businessNumber5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessPrice5" value="${resultMap.businessPrice5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessAmount5" value="${resultMap.businessAmount5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessImburse5"
                                       value="${resultMap.businessImburse5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessRaise5" value="${resultMap.businessRaise5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr style="border-bottom: hidden">
                            <td>
                                6．专业软件
                            </td>
                            <td>
                                <input type="text" name="businessType6" value="${resultMap.businessType6}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessNumber6" value="${resultMap.businessNumber6}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessPrice6" value="${resultMap.businessPrice6}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessAmount6" value="${resultMap.businessAmount6}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessImburse6"
                                       value="${resultMap.businessImburse6}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessRaise6" value="${resultMap.businessRaise6}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                7．其他
                            </td>
                            <td>
                                <input type="text" name="businessType7" value="${resultMap.businessType7}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessNumber7" value="${resultMap.businessNumber7}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessPrice7" value="${resultMap.businessPrice7}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessAmount7" value="${resultMap.businessAmount7}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessImburse7"
                                       value="${resultMap.businessImburse7}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="businessRaise7" value="${resultMap.businessRaise7}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>


                        <tr style="border-bottom: hidden">
                            <th style="text-align: center;" rowspan="5">消耗性实验材料费</th>
                            <td>
                                1．实验动物
                            </td>
                            <td>
                                <input type="text" name="testType" value="${resultMap.testType}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testNumber" value="${resultMap.testNumber}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testPrice" value="${resultMap.testPrice}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testAmount" value="${resultMap.testAmount}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testImburse" value="${resultMap.testImburse}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testRaise" value="${resultMap.testRaise}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td rowspan="5">
                                <input type="text" name="testRemark" value="${resultMap.testRemark}"
                                       class="inputText" style="width:90%"/>
                            </td>
                        </tr>
                        <tr style="border-bottom: hidden">
                            <td>
                                2．实验动物饲料
                            </td>
                            <td>
                                <input type="text" name="testType2" value="${resultMap.testType2}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testNumber2" value="${resultMap.testNumber2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testPrice2" value="${resultMap.testPrice2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testAmount2" value="${resultMap.testAmount2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testImburse2" value="${resultMap.testImburse2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testRaise2" value="${resultMap.testRaise2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr style="border-bottom: hidden">
                            <td>
                                3．试剂
                            </td>
                            <td>
                                <input type="text" name="testType3" value="${resultMap.testType3}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testNumber3" value="${resultMap.testNumber3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testPrice3" value="${resultMap.testPrice3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testAmount3" value="${resultMap.testAmount3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testImburse3" value="${resultMap.testImburse3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testRaise3" value="${resultMap.testRaise3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr style="border-bottom: hidden">
                            <td>
                                4．实验用品(如玻璃器皿等)
                            </td>
                            <td>
                                <input type="text" name="testType4" value="${resultMap.testType4}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testNumber4" value="${resultMap.testNumber4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testPrice4" value="${resultMap.testPrice4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testAmount4" value="${resultMap.testAmount4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testImburse4" value="${resultMap.testImburse4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testRaise4" value="${resultMap.testRaise4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                5．其他
                            </td>
                            <td>
                                <input type="text" name="testType5" value="${resultMap.testType5}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testNumber5" value="${resultMap.testNumber5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testPrice5" value="${resultMap.testPrice5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testAmount5" value="${resultMap.testAmount5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testImburse5" value="${resultMap.testImburse5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="testRaise5" value="${resultMap.testRaise5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>

                        <tr style="border-bottom: hidden">
                            <th style="text-align: center;" rowspan="5">消耗性临床材料费</th>
                            <td>
                                1．化验
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialType"
                                       value="${resultMap.clinicalMaterialType}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialNumber"
                                       value="${resultMap.clinicalMaterialNumber}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialPrice"
                                       value="${resultMap.clinicalMaterialPrice}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialAmount"
                                       value="${resultMap.clinicalMaterialAmount}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialImburse"
                                       value="${resultMap.clinicalMaterialImburse}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialRaise"
                                       value="${resultMap.clinicalMaterialRaise}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td rowspan="5">
                                <input type="text" name="clinicalMaterialRemark"
                                       value="${resultMap.clinicalMaterialRemark}"
                                       class="inputText" style="width:90%"/>
                            </td>
                        </tr>

                        <tr style="border-bottom: hidden">
                            <td>
                                2．检查
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialType2"
                                       value="${resultMap.clinicalMaterialType2}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialNumber2"
                                       value="${resultMap.clinicalMaterialNumber2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialPrice2"
                                       value="${resultMap.clinicalMaterialPrice2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialAmount2"
                                       value="${resultMap.clinicalMaterialAmount2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialImburse2"
                                       value="${resultMap.clinicalMaterialImburse2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialRaise2"
                                       value="${resultMap.clinicalMaterialRaise2}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr style="border-bottom: hidden">
                            <td>
                                3．医院制剂
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialType3"
                                       value="${resultMap.clinicalMaterialType3}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialNumber3"
                                       value="${resultMap.clinicalMaterialNumber3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialPrice3"
                                       value="${resultMap.clinicalMaterialPrice3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialAmount3"
                                       value="${resultMap.clinicalMaterialAmount3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialImburse3"
                                       value="${resultMap.clinicalMaterialImburse3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialRaise3"
                                       value="${resultMap.clinicalMaterialRaise3}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr style="border-bottom: hidden">
                            <td>
                                4．临床观察
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialType4"
                                       value="${resultMap.clinicalMaterialType4}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialNumber4"
                                       value="${resultMap.clinicalMaterialNumber4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialPrice4"
                                       value="${resultMap.clinicalMaterialPrice4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialAmount4"
                                       value="${resultMap.clinicalMaterialAmount4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialImburse4"
                                       value="${resultMap.clinicalMaterialImburse4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialRaise4"
                                       value="${resultMap.clinicalMaterialRaise4}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                5．其他
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialType5"
                                       value="${resultMap.clinicalMaterialType5}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialNumber5"
                                       value="${resultMap.clinicalMaterialNumber5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialPrice5"
                                       value="${resultMap.clinicalMaterialPrice5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialAmount5"
                                       value="${resultMap.clinicalMaterialAmount5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialImburse5"
                                       value="${resultMap.clinicalMaterialImburse5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="clinicalMaterialRaise5"
                                       value="${resultMap.clinicalMaterialRaise5}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                        </tr>

                        <tr>
                            <th style="text-align: center;">仪器设备使用费</th>
                            <td>
                                <textarea class="xltxtarea" style="width:90%;height: 50px;"
                                          name="deviceUseItem">${resultMap.deviceUseItem}</textarea>
                            </td>
                            <td>
                                <input type="text" name="deviceUseType" value="${resultMap.deviceUseType}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="deviceUseNumber"
                                       value="${resultMap.deviceUseNumber}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="deviceUsePrice" value="${resultMap.deviceUsePrice}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="deviceUseAmount"
                                       value="${resultMap.deviceUseAmount}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="deviceUseImburse"
                                       value="${resultMap.deviceUseImburse}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="deviceUseRaise" value="${resultMap.deviceUseRaise}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="deviceUseRemark"
                                       value="${resultMap.deviceUseRemark}"
                                       class="inputText" style="width:90%"/>
                            </td>
                        </tr>

                        <tr>
                            <th style="text-align: center;">科研协作费</th>
                            <td>
                                <textarea class="xltxtarea" style="width:90%;height: 50px;"
                                          name="jointItem">${resultMap.jointItem}</textarea>
                            </td>
                            <td>
                                <input type="text" name="jointType" value="${resultMap.jointType}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="jointNumber" value="${resultMap.jointNumber}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="jointPrice" value="${resultMap.jointPrice}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="jointAmount" value="${resultMap.jointAmount}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="jointImburse" value="${resultMap.jointImburse}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="jointRaise" value="${resultMap.jointRaise}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="jointRemark" value="${resultMap.jointRemark}"
                                       class="inputText" style="width:90%"/>
                            </td>
                        </tr>

                        <tr>
                            <th style="text-align: center;">其他</th>
                            <td>
                                <textarea class="xltxtarea" style="width:90%;height: 50px;"
                                          name="otherItem">${resultMap.otherItem}</textarea>
                            </td>
                            <td>
                                <input type="text" name="otherType" value="${resultMap.otherType}"
                                       class="inputText" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="otherNumber" value="${resultMap.otherNumber}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="otherPrice" value="${resultMap.otherPrice}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="otherAmount" value="${resultMap.otherAmount}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="otherImburse" value="${resultMap.otherImburse}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="otherRaise" value="${resultMap.otherRaise}"
                                       class="inputText validate[custom[number]]" style="width:90%"/>
                            </td>
                            <td>
                                <input type="text" name="otherRemark" value="${resultMap.otherRemark}"
                                       class="inputText" style="width:90%"/>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;" rowspan="2">经费使用年度计划(百分比)</td>
                            <td style="text-align: center;" colspan="2">
                                <input class="inputText ctime" type="text" name="firstYear"
                                       value="${resultMap.firstYear}"
                                       onClick="WdatePicker({dateFmt:'yyyy'})"
                                       readonly="readonly" style="width: 80px"/>年(第一年)
                            </td>
                            <td style="text-align: center;" colspan="3">
                                <input class="inputText ctime" type="text" name="secondYear"
                                       value="${resultMap.secondYear}"
                                       onClick="WdatePicker({dateFmt:'yyyy'})"
                                       readonly="readonly" style="width: 80px"/>年(第二年)
                            </td>
                            <td style="text-align: center;" colspan="3">
                                <input class="inputText ctime" type="text" name="thirdYear"
                                       value="${resultMap.thirdYear}"
                                       onClick="WdatePicker({dateFmt:'yyyy'})"
                                       readonly="readonly" style="width: 80px"/>年(第三年)
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: center;" colspan="2">
                                <input type="text" name="fundPlanPercent1"
                                       value="${resultMap.fundPlanPercent1}"
                                       class="inputText validate[custom[number]]" style="width:80%"/>%
                            </td>
                            <td style="text-align: center;" colspan="3">
                                <input type="text" name="fundPlanPercent2"
                                       value="${resultMap.fundPlanPercent2}"
                                       class="inputText validate[custom[number]]" style="width:80%"/>%
                            </td>
                            <td style="text-align: center;" colspan="3">
                                <input type="text" name="fundPlanPercent3"
                                       value="${resultMap.fundPlanPercent3}"
                                       class="inputText validate[custom[number]]" style="width:80%"/>%
                            </td>

                        </tr>
                    </table>

                    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
                        <div class="button" style="width: 100%;">
                            <input id="prev" type="button" onclick="nextOpt('step7')" class="search"
                                   value="上一步"/>
                            <input id="nxt" type="button" onclick="nextOpt('step9')" class="search"
                                   value="下一步"/>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
		