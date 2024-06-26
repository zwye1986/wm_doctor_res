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
        (function ($) {
            $.fn.likeSearchInit = function (option) {
                option.clickActive = option.clickActive || null;

                var searchInput = this;
                var spaceId = this[0].id;
                searchInput.on("keyup focus", function () {
                    var boxHome = $("#" + spaceId + "Sel");
                    boxHome.show();
                    var pDiv = $(boxHome).parent();
                    $(pDiv).css("left", $("#train").outerWidth());
                    var w = $(this).css("marginTop").replace("px", "");
                    w = w - 0 + $(this).outerHeight() + 6 + "px";
                    $(pDiv).css("top", w);
                    if ($.trim(this.value)) {
                        $("p." + spaceId + ".item", boxHome).hide();
                        var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
                        if (!items) {
                            boxHome.hide();
                        }
                        changeOrgFlow(this);
                    } else {
                        $("p." + spaceId + ".item", boxHome).show();
                    }
                });
                searchInput.on("blur", function () {
                    var boxHome = $("#" + spaceId + "Sel");
                    if (!$("." + spaceId + ".boxHome.on").length) {
                        boxHome.hide();
                    }
                });
                $("." + spaceId + ".boxHome").on("mouseenter mouseleave", function () {
                    $(this).toggleClass("on");
                });

                $("." + spaceId + ".boxHome .item").click(function () {
                    var boxHome = $("." + spaceId + ".boxHome");
                    boxHome.hide();
                    var value = $(this).attr("value");
                    var input = $("#" + spaceId);
                    input.val(value);
                    if (option.clickActive) {
                        option.clickActive($(this).attr("flow"));
                        $("#orgFlow").val($(this).attr("flow"));
                    }
                });
            };
        })(jQuery);
        function changeOrgFlow(obj) {
            var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
            var flow = $(items).attr("flow") || '';
            $("#orgFlow").val(flow);
        }
        $(document).ready(function () {
            if ($("#trainOrg").length) {
                $("#trainOrg").likeSearchInit({
                    clickActive: function (flow) {
                        $("." + flow).show();
                    }
                });
            }
        });

        function formatNumbers(obj){
            var re = (/^(?!0\d|[.]+$)\d{0,20}(\.\d{0,4})?$/).test(obj.value) ;
            if(!re)
            {
                $('#'+obj.id).val("")
                jboxTip("金额格式不正确，请输入正整数或者小数(小数最多保留4位)！");
            }

        }
        function inputOnBlur() {
            var sum = 0;
            $('.resBaseinputValue').each(function (){
                var value = $(this).val();
                sum += Number(value) * 10000;
            })
            // 收支总额
            var sumNumber = sum / 10000;
            if(sumNumber > 0){
                var y = String(sumNumber).indexOf(".") + 1;
                var count = String(sumNumber).length - y;
                if(y > 0 && count > 5){
                    $("#amountOfIncome").val(sumNumber.toFixed(4));
                }else {
                    $("#amountOfIncome").val(sumNumber);
                }
            } else {
                $("#amountOfIncome").val("0");
            }
        }

        function save() {
            if(false==$("#addForm").validationEngine("validate")){
                return false;
            }
            var amountOfExpen =  $("#amountOfExpenses").val(); // 上报金额
            var amountOfIncome =  $("#amountOfIncome").val(); // 合计金额
            debugger;
            if(Number(amountOfExpen)  <  Number(amountOfIncome))
            {
                jboxTip("上报金额不能小于具体拨付用途合计总金额！");
                return false;
            }
            var data = [];
            $('.resBaseinputValue').each(function (){
                var value = $(this).val();
                var id = $(this).attr('id');
                var rojectOfBasefundsName = $(this).attr('rojectOfBasefundsName');
                var singleData = {value:value,id:id};
                if(!(value == '' || value == undefined)){
                    data.push(singleData);
                }

            })
            $("#resBaseinputs").val(JSON.stringify(data));

            var url = "<s:url value='/res/resFunds/saveSynCosTmanagemnt'/>?role=${role}";
            var msg = "确认无误点击保存？";
            jboxConfirm(msg,function(){
                jboxSubmit($('#addForm'),url,function(resp) {
                    if(resp=='1') {
                        jboxTip("操作成功");
                        window.parent.search();
                        jboxClose();
                    }
                }, null, false);
            });
        }
    </script>
</head>
<body >
<div class="mainright" style="max-height: 700px; overflow: scroll; width: 800px; height: 600px;"  >
    <div class="basic" style="max-height: 392px;">
        <form id="addForm" style="position: relative;">
            <input name="recordFlow" type="hidden" value="${resBaseFunds.recordFlow}"  />
            <input name="resBaseinputs" id="resBaseinputs" type="hidden" />
            <input name="auditStatusId" id="auditStatusId" type="hidden"  value=""/>
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 30px;">
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>培训基地：</th>
                    <td>
                        <input id="trainOrg" oncontextmenu="return false" name="orgName" value="${resBaseFunds.orgName}"  class="toggleView input validate[required]" type="text"
                               autocomplete="off" onkeydown="changeStatus();" onkeyup="changeStatus();"   <c:if test="${view eq '1'}"> readonly="true"</c:if> />
                    </td>
                    <c:if test="${view ne '1'}">
                    <div id="pDiv" style="width: 100px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                        <div class="boxHome trainOrg" id="trainOrgSel" style="left: 123px; max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                            <c:forEach items="${orgs}" var="org">
                                <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}" style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
                                   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;" </c:if>
                                >${org.orgName}</p>
                            </c:forEach>
                        </div>
                        <input type="text" name="orgFlow" id="orgFlow" value="${resBaseFunds.orgFlow}" style="display: none;"/>
                    </div>
                    </c:if>
                    <th style="text-align: right;"><font color="red" >*</font>金额：</th>
                    <td >
                        <input name="amountOfExpenses" id="amountOfExpenses" class="input validate[required,maxSize[200]]" value="${resBaseFunds.amountOfExpenses}" onblur="formatNumbers(this);"
                                <c:if test="${view eq '1'}"> readonly="true"</c:if> /><font >（万元）</font>
                    </td>

                </tr>
                <tr>
                    <th style="text-align: right;"><font color="red" >*</font>上报时间：</th>
                    <td>
                        <input type="text" id="reportDate" name="reportDate" value="${resBaseFunds.reportDate}"  class="input validate[required]"  readonly="readonly"
                                <c:if test="${view ne '1'}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if>>
                    </td>

                    <th style="text-align: right;"><font color="red" >*</font>资金下拨日期：</th>
                    <td>
                        <input type="text" id="inPlaceDate" name="inPlaceDate" value="${resBaseFunds.inPlaceDate}"  class="input validate[required]"  readonly="readonly"
                                <c:if test="${view ne '1'}"> onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"</c:if>>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: left;" colspan="4">省级经费具体用途 :</th>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center; width: 50%" > 统计项目 </td>
                    <td colspan="2" style="text-align: left; width: 50%"> 金额（万元）<font color="red">正整数或者小数(小数点后最多4位)</font> </td>
                </tr>
                <c:forEach items="${dictTypeEnumSynCostManagementList}" var="dict" >
                    <tr>
                        <td colspan="2" style="text-align: center"> ${dict.dictName} :</td>
                        <td colspan="2" style="text-align: left">
                            <c:set  var="key" value="${dict.dictId}${teacher.recordFlow}"/>
                            <input name="hospitalTypeId" class="input resBaseinputValue" value="${detailMap[key]}" id="${dict.dictId}"  <c:if test="${view eq '1'}"> readonly="true"</c:if> onblur="formatNumbers(this),inputOnBlur();">
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan="2" style="text-align: center" > 合计： </td>
                    <td colspan="2" style="text-align: left"> <input name="amountOfIncome" id="amountOfIncome" value="${resBaseFunds.amountOfIncome}" class="input" readonly="true"> </td>
                </tr>

            </table>
        </form>
        <p style="text-align: center;">
            <c:if test="${view ne '1'}">
                <input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>&#12288;
            </c:if>
            <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
        </p>
    </div>
</div>
</body>
</html>
