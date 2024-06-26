<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
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
        function saveAudit(agreeFlag, fundDetailFlow) {

            if (false == $("#auditForm").validationEngine("validate")) {
                return false;
            }
            var reimburseAllocate = parseFloat($('#reimburseAllocate').html());//下拨已报
            var reimburseMatching = parseFloat($('#reimburseMatching').html());//配套已报
            var allocateMoney = parseFloat($('#allocateMoney').html());//下拨预算
            var matchingMoney = parseFloat($('#matchingMoney').html());//配套预算
            var money = parseFloat($('#realmoney').val());
//            var budgetMoney = parseFloat($('#budgetMoney').text());
            var realityTypeId = $("#realityTypeId").val();
            var msg = "";
            var itemName = $("#itemName").text();
            if(realityTypeId && realityTypeId == 'Allocate'){
                if ((money+reimburseAllocate) > allocateMoney) {
                    msg = "<span style='color:red;'>本次报销中"+itemName+"超过预算，下拨预算"+allocateMoney+"，下拨已报销"+reimburseAllocate+"，本次报销"+money+"</span> ,";
                }
            }
            if(realityTypeId && realityTypeId == 'Matching'){
                if ((money+reimburseMatching) > matchingMoney) {
                    msg = "<span style='color:red;'>本次报销中"+itemName+"超过预算，配套预算"+matchingMoney+"，配套已报销"+reimburseMatching+"，本次报销"+money+"</span> ,";
                }
            }

            var agreemsg = "";
            if("${detailExt.operStatusId}" == "${achStatusEnumFourthAudit.id}"){
                agreemsg = "<span style='color:red;'>审核通过后将无法修改</span>,"
            }
            var tip = agreeFlag == "${GlobalConstant.FLAG_Y}" ? msg + agreemsg + "确认审核通过" : "确认退回";
            var url = "<s:url value='/srm/payment/saveAudit/${scope}'/>?agreeFlag=" + agreeFlag + "&fundDetailFlow=" + fundDetailFlow;
            jboxConfirm(tip + "?", function () {
                jboxStartLoading();
                jboxPost(url, $('#auditForm').serialize(), function (resp) {
//                    window.parent.frames['mainIframe'].location.reload(true);
                    window.parent.frames['mainIframe'].window.search("${detailExt.projFundInfo.fundFlow}");

                }, null, true);

            });
        }
        function doClose() {
            jboxClose();
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
            <form method="post" id="auditForm">
            <div class="title1 clearfix">
                <div>
                    <table class="xllist nofix">
                        <tr>
                            <th colspan="3" style="font-size: 14px;">项目信息</th>
                        </tr>
                        <tr>
                            <td><span>项目名称：</span>${detailExt.proj.projName}</td>
                            <td><span>年&#12288;份：</span>${detailExt.proj.projYear}</td>
                            <td><span>起止日期：</span>${detailExt.proj.projStartTime}~${detailExt.proj.projEndTime}</td>
                            <%--<td colspan="2"><span>项目类型：</span>${detailExt.proj.projTypeName}</td>--%>
                        </tr>
                        <tr>
                            <td><span>项目编号：</span>${detailExt.proj.projNo}</td>
                            <td><span>承担单位：</span>${detailExt.proj.applyOrgName}</td>
                            <td><span>负责人：</span>${detailExt.proj.applyUserName}</td>
                        </tr>
                        <tr>
                            <td><span>一级来源：</span>${detailExt.proj.projDeclarer}</td>
                            <td><span>二级来源：</span>${detailExt.proj.projSecondSourceName}</td>
                            <td><span>经费方案：</span>${detailExt.schemeDetail.schemeName}</td>
                        </tr>

                        <tr>
                            <td><span>预算总额：</span>${pdfn:transMultiply(detailExt.projFundInfo.budgetAmount,10000)}元</td>
                            <td><span>到账总额：</span>${pdfn:transMultiply(detailExt.projFundInfo.realityAmount,10000)}元</td>
                            <td><span>到账余额：</span><span style="font-weight: normal"
                                                            id="realityBalance">${pdfn:transMultiply(detailExt.projFundInfo.realityBalance,10000)}元</span></td>
                        </tr>
                    </table>
                </div>

                <div>
                    <table class="xllist nofix">
                        <tr>
                            <th colspan="4" style="font-size: 14px;">报销明细</th>
                        </tr>
                        <tr>
                            <td style="width: 25%"><span>报销项目：</span><span style="font-weight: normal" id="itemName">${detailExt.schemeDetail.itemName}</span></td>
                            <td style="width: 25%"><span>预算金额：</span><span style="font-weight: normal">${pdfn:transMultiply(budget.money,10000)}元</span></td>
                            <td style="width: 25%"><span>预算下拨金额：</span><span style="font-weight: normal" id="allocateMoney">${pdfn:transMultiply(empty budget.allocateMoney?"0":budget.allocateMoney,10000)}元</span></td>
                            <td style="width: 25%"><span>预算配套金额：</span><span style="font-weight: normal" id="matchingMoney">${empty budget.matchingMoney?"0":pdfn:transMultiply(budget.matchingMoney,10000)}元</span></td>
                        </tr>
                        <tr>
                            <td><span>申请报销金额：</span><span style="font-weight: normal" id="money">${pdfn:transMultiply(detailExt.money,10000)}元</span></td>
                            <td><span>该项已报金额：</span><span style="font-weight: normal" id="reimburseMoney">${pdfn:transMultiply(reimburseMoney,10000)}元</span></td>
                            <td><span>下拨已报金额：</span><span style="font-weight: normal" id="reimburseAllocate">${pdfn:transMultiply(allocateMoney,10000)}元</span></td>
                            <td><span>配套已报金额：</span><span style="font-weight: normal" id="reimburseMatching">${pdfn:transMultiply(matchingMoney,10000)}元</span></td>
                        </tr>
                        <tr>
                            <td colspan="4"><span>报销内容：</span >${detailExt.content}</td>
                        </tr>
                    </table>
                </div>
                <c:set var="payFlag" value="false"/>
                <%--如果是财务角色--%>
                <c:if test="${scope eq GlobalConstant.USER_LIST_FINANCE}">
                    <c:set var="payFlag" value="true"/>
                    <%--如果版本是徐州中心医院只有分管院长可以看到界面--%>
                    <c:if test="${applicationScope.sysCfgMap['srm_local_type'] eq 'Y'}">
                        <c:set var="payFlag" value="false"/>
                        <c:if test="${pdfn:contain(applicationScope.sysCfgMap['srm_local_xz_dean'] ,sessionScope.currRoleList)}">
                            <c:set var="payFlag" value="true"/>
                        </c:if>
                    </c:if>
                </c:if>
                <c:if test="${payFlag}">
                    <div>
                        <table class="xllist nofix">
                            <tr>
                                <td style="width: 50%"><span>预算类型：</span><%--</td>--%>
                                <%--<td style="width: 25%">--%>
                                    <select class="xlselect validate[required]" id="realityTypeId" name="realityTypeId" >
                                        <option value="">请选择</option>
                                        <c:forEach var="fundAccountsType" items="${projFundAccountsTypeEnumList}">
                                            <option value="${fundAccountsType.id}" <c:if test="${detailExt.realityTypeId eq fundAccountsType.id}">selected="selected"</c:if>>${fundAccountsType.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td style="width: 50%"><span>到账类型：</span>
                                <%--<td style="width: 25%">--%>
                                    <select name="fundSourceId" class="xlselect validate[required]" >
                                        <option value="">请选择</option>
                                        <c:forEach items="${dictTypeEnumProjFundAccountsTypeList}" var="dict" varStatus="status">
                                            <option value="${dict.dictId}" >${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                  </td>
                            </tr>
                            <tr>
                                <td><span>报销方式：</span><%--</td>--%>
                                <%--<td>--%>
                                    <select class="xlselect validate[required]" id="fundRetype" name="fundRetype" >
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumFundRetypeList}">
                                            <option value="${dict.dictId}" <c:if test="${detailExt.fundRetype eq dict.dictId}" >selected="selected"</c:if>>${dict.dictName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td><span>实报金额：</span>&nbsp;<input type="text" class="xltext validate[required,custom[number],min[0]]" id="realmoney" name="realmoney" value="${pdfn:transMultiply(detailExt.money,10000)}"/>(元)</td>
                            </tr>
                        </table>
                    </div>
                </c:if>
            </div>
            <h2 style="margin-top:5px;">审核意见：</h2>
            <hr/>
            <div style="text-align: center;">

                    <textarea id="auditContent" name="content" cols="25" rows="3"
                              style="margin-top: 10px; width:100%; height:150px; border:1px solid #D0E3F2"></textarea>
                    <p style="text-align: center;padding-top: 10px;">
                        <input class='search' onclick="saveAudit('${GlobalConstant.FLAG_Y}','${detailExt.fundDetailFlow}')"
                               type='button' value='通&nbsp;&nbsp;过'/>&#12288;
                        <input class='search' onclick="saveAudit('${GlobalConstant.FLAG_N}','${detailExt.fundDetailFlow}')"
                               type='button' value='退&nbsp;&nbsp;回'/>&#12288;
                    </p>
            </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>