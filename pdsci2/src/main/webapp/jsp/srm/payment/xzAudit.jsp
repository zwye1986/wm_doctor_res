<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="false"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <script type="text/javascript">
        function saveAudit(agreeFlag, fundFormFlow) {
            if (!$("#auditForm").validationEngine("validate")) {
                return false;
            }
            var realityBalance = parseFloat($('#realityBalance').text());
            var applyFundSum = parseFloat($('#applyFundSum').val());
            var msg = "";
            if (applyFundSum > applyFundSum) {
                msg = "<span style='color:red;'>报销金额大于余额</span> ,";
            }
            var agreemsg = "";
            if ("${detailExt.operStatusId}" == "${achStatusEnumFourthAudit.id}") {
                agreemsg = "<span style='color:red;'>审核通过后将无法修改</span>,"
            }
            var tip = agreeFlag == "${GlobalConstant.FLAG_Y}" ? msg + agreemsg + "确认审核通过" : "确认退回";
            jboxConfirm(tip + "?", function () {
                var trs = $("#appendTbody").children();
                var projFundForm = $("#book").serializeJson();
                var datas = [];
                var fundFlow = $("#fundFlow").val();
                var content = $("#auditContent").val();
                $.each(trs, function (i, n) {//jquery迭代器,用于循环数组和json  i表示索引   n表示当前循环的元素
                    var fundDetailFlow = $(n).find("input[name='fundDetailFlow']").val();
                    var realmoney = $(n).find("input[name='realmoney']").val();
                    var realityTypeId = $(n).find("select[name='realityTypeId']").val();
                    var data = {//json对象   {key:value}
                        "fundDetailFlow": fundDetailFlow,
                        "realityTypeId": realityTypeId,
                        "fundFlow": fundFlow,
                        "fundFormFlow":fundFormFlow,
                        "realmoney":realmoney/10000
                    };
                    datas.push(data);//数组的push方法
                });
                var requestData = JSON.stringify(datas);
                $("#jsondata").val(requestData);
               // var t = {'fundDetailList': datas, 'content': content};
                var url = "<s:url value='/srm/paymentXZ/saveAudit'/>?agreeFlag=" + agreeFlag;
               /* jboxPostJson(url, requestData, function (resp) {
                    if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                        window.parent.frames['mainIframe'].window.search();
                        window.location.reload();
                    }
                }, null, true);*/
                jboxSubmit($('#auditForm'), url, function (resp) {
                            window.parent.frames['mainIframe'].location.reload(true);
                            jboxClose();
                        },
                        function (resp) {
                            alert("error");
                        });
            });
        }
        function doClose() {
            jboxClose();
        }
        $(function(){
            fundSum();
        })
        function fundSum() {
            var trs = $("#appendTbody").children();
            var sum = 0;
            $.each(trs, function (i, n) {
                var realmoney = $(n).find("input[name='realmoney']").val();
                sum += parseFloat(realmoney);
            });
            //alert(sum.toString().split(".")[1]);
            if((sum.toString().split(".")[1]) && parseInt(sum.toString().split(".")[1].length) > 4){
                sum = sum.toFixed(4);
            }
            if(!sum){
                sum = 0;
            }
            $("#realmoneySum").val(sum);
        }
    </script>
    <style type="text/css">
        .xllist td, .xllist th {
            text-align: left;
            padding-left: 15px;
        }

        .xllist td span {
            font-weight: bold;
            color: #333;
            font-size: 13px;
        }

        table {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div class="title1 clearfix">
                <form method="post" id="auditForm">
                    <input type="hidden" name="jsondata" id="jsondata"/>
                    <div>
                        <table class="xllist nofix">
                            <tr>
                                <th colspan="4" style="font-size: 14px;">项目信息</th>
                            </tr>
                            <tr>
                                <td><span>项目名称：</span>${fundFormExt.proj.projName}</td>
                                <td><span>年&#12288;份：</span>${fundFormExt.proj.projYear}</td>
                                <td colspan="2"><span>项目类型：</span>${fundFormExt.proj.projTypeName}</td>
                            </tr>
                            <tr>
                                <td><span>项目编号：</span>${fundFormExt.proj.projNo}</td>
                                <td><span>负责人：</span>${fundFormExt.proj.applyUserName}</td>
                                <td colspan="2">
                                    <span>起止日期：</span>${fundFormExt.proj.projStartTime}~${fundFormExt.proj.projEndTime}
                                </td>
                            </tr>

                            <tr>
                                <td><span>预算总额(元)：</span>${pdfn:transMultiply(fundFormExt.projFundInfo.budgetAmount,10000)}</td>
                                <td><span>到账总额(元)：</span>${pdfn:transMultiply(fundFormExt.projFundInfo.realityAmount,10000)}</td>
                                <td><span>到账余额(元)：</span><span style="font-weight: normal"
                                                                id="realityBalance">${pdfn:transMultiply(fundFormExt.projFundInfo.realityBalance,10000)}</span>
                                </td>
                            </tr>
                        </table>
                    </div>

                    <div>
                        <c:set var="payFlag" value="false"/>
                        <%--如果是财务角色--%>
                        <c:if test="${scope eq GlobalConstant.USER_LIST_FINANCE}">
                            <%--如果版本是徐州中心医院只有分管院长可以看到界面--%>
                                <c:if test="${pdfn:contain(applicationScope.sysCfgMap['srm_local_xz_dean'] ,sessionScope.currRoleList)}">
                                    <c:set var="payFlag" value="true"/>
                                </c:if>
                        </c:if>
                        <table style="width: 100%;" class="bs_tb">
                            <tr>
                                <th colspan="9">报销详情</th>
                            </tr>
                            <tr>
                                <th width="10%">报销项目</th>
                                <th width="5%">预算金额(元)</th>
                                <th width="7%">申请报销金额(元)</th>
                                <th width="8%">实际报销金额(元)</th>
                                <th width="10%">使用经费</th>
                                <th width="20%">报销内容</th>
                                <th width="10%">单据张数</th>
                                <th width="15%">附件</th>
                                <th width="10%">审核状态</th>
                            </tr>
                            <tbody id="appendTbody">
                            <c:forEach items="${fundDetailMap}" var="detailEntry">
                                <c:forEach items="${detailEntry.value}" var="detail" varStatus="status">
                                    <tr>
                                        <c:if test="${status.count==1}">
                                            <td rowspan="${detailEntry.value.size()}">
                                                <c:forEach items="${schemeDetailList}" var="schemeItem">
                                                    <c:if test='${schemeItem.itemFlow eq detailEntry.key}'>${schemeItem.itemName}</c:if>
                                                </c:forEach>
                                            </td>
                                            <td rowspan="${detailEntry.value.size()}">${pdfn:transMultiply(budgetFundDtlMap[detailEntry.key].money,10000)}</td>
                                        </c:if>
                                            <%--  <td rowspan="${detailEntry.value.size()}">
                                                      ${pdfn:getPercentByDouble(yetPaymentMap[detailEntry.key], budgetFundDtlMap[detailEntry.key].money)}
                                              </td>--%>
                                        <td>${pdfn:transMultiply(detail.money,10000)}</td>
                                        <td>
                                            <c:if test="${payFlag}">
                                                <input type="text" name="realmoney" class="inputText validate[required,custom[number],min[0]]" <c:if test="${empty detail.realmoney}">value="${pdfn:transMultiply(detail.money,10000)}"</c:if> <c:if test="${not empty detail.realmoney}">value="${pdfn:transMultiply(detail.realmoney,10000)}"</c:if> onchange="fundSum()" />
                                            </c:if>
                                            <c:if test="${!payFlag}">
                                                ${pdfn:transMultiply(detail.realmoney,10000)}
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${payFlag}">
                                                <select class="validate[required]" name="realityTypeId">
                                                    <option value="">请选择</option>
                                                    <c:forEach var="fundAccountsType"
                                                               items="${projFundAccountsTypeEnumList}">
                                                        <option value="${fundAccountsType.id}"
                                                                <c:if test="${detail.realityTypeId eq fundAccountsType.id}">selected="selected"</c:if>>${fundAccountsType.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </c:if>
                                            <c:if test="${!payFlag}">
                                                ${detail.realityTypeName}
                                            </c:if>
                                        </td>
                                        <td>
                                                ${detail.content}
                                        </td>
                                        <td>
                                                ${detail.fundFormNum}
                                        </td>
                                        <td>
                                            <a href='<s:url value="/pub/file/down?fileFlow=${fileMap[detail.fundDetailFlow].fileFlow}"/>'>${fileMap[detail.fundDetailFlow].fileName}</a>
                                            <input type="hidden" value="${fileMap[detail.fundDetailFlow].fileFlow}"/>
                                        </td>
                                        <td>
                                                ${detail.operStatusName}
                                            <input type="hidden" name="fundDetailFlow"
                                                   value="${detail.fundDetailFlow}"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <td>合计：</td>
                                <td style="text-align: left;" colspan="9">
                                    申请报销：<input type="text" style="width: 150px;text-align: center;"
                                           value="${pdfn:transMultiply(fundFormExt.money,10000)} (元)" id="applyFundSum"
                                           class="inputText" readonly="readonly"/>
                                    &#12288;&#12288;&#12288;实际报销：
                                    <input type="text" style="width: 150px;text-align: center;" id="realmoneySum"
                                                                       value="${pdfn:transMultiply(fundFormExt.realmoney,10000)} (元)"
                                                                       class="inputText" readonly="readonly"/>
                                </td>
                            </tr>
                            <tr>
                                <td>报销人：</td>
                                <td style="text-align: left;" colspan="9">
                                    <input type="text" style="width: 150px;text-align: center;"
                                           class="inputText" value="${fundFormExt.fundOperator}" readonly="readonly"/>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                    <h2 style="margin-top:5px;">审核意见：</h2>
                    <hr/>
                    <div style="text-align: center;">

                    <textarea id="auditContent" class="validate[maxSize[100]]" name="content" cols="25" rows="3"
                              style="margin-top: 10px; width:100%; height:150px; border:1px solid #D0E3F2"></textarea>
                        <p style="text-align: center;padding-top: 10px;">
                            <input class='search'
                                   onclick="saveAudit('${GlobalConstant.FLAG_Y}','${fundFormExt.fundFormFlow}')"
                                   type='button' value='通&nbsp;&nbsp;过'/>&#12288;
                            <input class='search'
                                   onclick="saveAudit('${GlobalConstant.FLAG_N}','${fundFormExt.fundFormFlow}')"
                                   type='button' value='退&nbsp;&nbsp;回'/>&#12288;
                        </p>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>