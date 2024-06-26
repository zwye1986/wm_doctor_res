<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
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
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>

    <script type="text/javascript">

        function doClose() {
            jboxClose();
        }

        function modify(fundDetailFLow, reimburseId, money, content, remark, createTime) {
            $('#fundDetailFlow').val(fundDetailFLow);
            $('#money').val((parseFloat(money) * 10000).toFixed(2));//万元转化为元
            $('#reimburseId').val(reimburseId);
            $('#contents').val(content);
            $('#remark').val(remark);
            $("#regisTime").val(createTime);
            $("#searchBtn").show();
            getReimburseName();
        }

        function getReimburseName() {
            $("#reimburseName").val($("#reimburseId :selected").text());
            detailItemChanged($("#reimburseId :selected").val());
            $("#fundMessage").show();
        }

        function deleteDetail(fundDetailFlow) {
            jboxConfirm("确认删除该条报销?", function () {
                var requestData = {"fundDetailFlow": fundDetailFlow};
                var url = "<s:url value='/srm/payment/deleteDetail' />";
                jboxStartLoading();
                jboxPost(url, requestData, function (resp) {
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        //window.parent.frames['mainIframe'].window.search();
                        window.location.reload();
                    }
                }, null, true);
            });
        }

        function saveReimburse() {
            if (!$("#paymentForm").validationEngine("validate")) {
                return false;
            }
            jboxConfirm("确认保存?", function () {
                jboxStartLoading();
                $('#paymentForm').submit();

            });
        }
        function submitReimburse(fundDetailFlow) {
            var url = "<s:url value='/srm/regis/proj/auditDetail/${scope}'/>";
            var fundFlow = '${fundInfo.fundFlow}';
            var date = {
                "fundDetailFlow": fundDetailFlow,
                "fundFlow": fundFlow
            };
            jboxConfirm("确认提交该条报销?", function () {
                jboxStartLoading();
                jboxPost(url, date, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        // window.parent.frames['mainIframe'].window.search();
                        window.location.reload();
                    }
                }, null, true);
            });
        }

        function auditReimburse(fundDetailFlow, agreeFlag, reimburseId, money) {
            var url = "<s:url value='/srm/regis/proj/auditDetail/${scope}'/>";
            var fundFlow = '${fundInfo.fundFlow}';
            var date = {
                "fundDetailFlow": fundDetailFlow,
                "agreeFlag": agreeFlag,
                "fundFlow": fundFlow
            };
            var tip = "";
            if (reimburseId) {
                detailItemChanged(reimburseId);
                var budgetAmount = parseFloat($("#budgetMoney").val());
                money = money == null ? 0 : money;
                var reimburseMoney = parseFloat($("#reimburseMoney").val()) + parseFloat(money);
                var reallyReimburseMoney = parseFloat($("#reimburseMoney").val());
                if (reimburseMoney > budgetAmount) {
                    tip += "报销金额累加超过报销比例，最高为：<span style='color:red;'>" + budgetAmount + "(万元),</span>已报销：<span style='color:red;'>" + reallyReimburseMoney + "（万元）!</span>";
                }
            }
            tip += agreeFlag == "${GlobalConstant.FLAG_Y}" ? "确认审核通过" : "确认退回?";
            jboxConfirm(tip + "?", function () {
                jboxStartLoading();
                jboxPost(url, date, function (resp) {
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        // window.parent.frames['mainIframe'].window.search();
                        window.location.reload();
                    }
                }, null, true);
            });
        }

        function findAuditDetail(fundDetailFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/payment/showPaymentAudit?fundDetailFlow='/>" + fundDetailFlow, "操作信息", 800, 600);
        }

        function detailItemChanged(detailFlow) {
            $("#fundMessage").html("");
            var requestData = {
                "detailFlow": detailFlow
            };
            var url = "<s:url value='/srm/regis/proj/reimburseIdChange'/>";
            jboxStartLoading();
            jboxPostAsync(url, requestData, function (resp) {
                var budgetMoney = resp["budgetMoney"];
                var reimburseMoney = resp["reimburseMoney"];
                if (!budgetMoney) {
                    budgetMoney = 0;
                }
                if (!reimburseMoney) {
                    reimburseMoney = 0;
                }
                $("#fundMessage").html("预算金额：" + budgetMoney + " 万元，已报销金额:" + reimburseMoney + " 万元");
                $("#reimburseMoney").val(reimburseMoney);
                $("#budgetMoney").val(budgetMoney);

            }, null, false);
        }
        $(function () {
            var serial = 0;
            $(".reimburseSerial").each(function () {
                serial++;
                $(this).text(serial);
            });
        });


        $(function () {
            $(":radio[name='projStatusId']").click(function () {
                var status = $(":radio[name='projStatusId']:checked").val();
                if (status == 'all') {
                    $("#submitBody").show();
                    $("#auditBody").show();
                }
                if (status == 'Submit') {
                    $("#submitBody").show();
                    $("#auditBody").hide();
                }
                if (status == 'Pass') {
                    $("#submitBody").hide();
                    $("#auditBody").show();
                }
            });
            var scope = '${scope}';
            if (scope == 'apply' || scope == 'director') {
                $("#submitBody").hide();
                $("#auditBody").hide();
            }
        });
        function refreshParentPage() {
            // jboxClose();
            window.parent.frames['mainIframe'].window.search();
        }

    </script>

</head>
<body>
<div class="mainright">
    <div class="content">

        <form id="paymentForm" action="<s:url value='/srm/regis/proj/reimburse/${scope}'/>" method="post">
            <input type="hidden" id="agreeFlag" name="agreeFlag"/>
            <input type="hidden" id="fundFlow" name="fundFlow" value="${fundInfo.fundFlow}"/>
            <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
            <input type="hidden" id="fundDetailFlow" name="fundDetailFlow"/>
            <input type="hidden" id="budgetMoney"/>
            <input type="hidden" id="reimburseMoney"/>
            <table class="bs_tb" width="100%">
                <tr>
                    <td colspan="4"><span style="font-size: 16px; float: left">报销登记:</span></td>
                </tr>
                <tr>
                    <th>&#12288;项目名称:</th>
                    <td>${proj.projName}</td>
                    <th>&#12288;项目编号：</th>
                    <td>${proj.projNo }</td>
                </tr>
                <tr>
                    <th>&#12288;到账经费:</th>
                    <td>${fundInfo.realityAmount}（万元）</td>
                    <th>&#12288;到账时间：</th>
                    <td>${fundInfo.fundIncomeTime}</td>
                </tr>
                <tr>
                    <th>&#12288;登记时间:</th>
                    <td><input type="text" class="xltext" id="regisTime" readonly="readonly"
                               value="${pdfn:getCurrDate()}"/></td>
                    <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>项目金额：</th>
                    <td style="text-align: center;">
                        <input id="money" name="money" type="text" style="width: 70%;"
                               class="xltext validate[required,custom[number],min[0],max[1000000]]"/>（元）
                    </td>
                </tr>
                <tr>
                    <th><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>报销项目:</th>
                    <td colspan="3">
                        <select class="validate[required] xltext" id="reimburseId" name="reimburseId"
                                onchange="getReimburseName();"
                                style="width: 50%">
                            <option value="">请选择</option>
                            <c:forEach items="${details}" var="reimburse">
                                <c:if test="${reimburse.fundTypeId eq projFundTypeEnumBudget.id}">
                                    <option value="${reimburse.fundDetailFlow}"
                                            <c:if test="${reimburse.fundDetailFlow eq detail.reimburseId}">selected="selected"</c:if>>${reimburse.reimburseName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="reimburseName" name="reimburseName"/>
                    </td>
                </tr>
                <tr>
                    <th>&#12288;项目明细:</th>
                    <td colspan="3">
                        <textarea id="contents" class="validate[maxSize[200]] xltxtarea" placeholder="200个字符以内"
                                  name="content"></textarea>
                    </td>
                </tr>
                <tr>
                    <th>&#12288;备&#12288;&#12288;注:</th>
                    <td colspan="3">
                        <textarea id="remark" class="validate[maxSize[200]] xltxtarea" placeholder="200个字符以内"
                                  name="remark"></textarea>
                    </td>
                </tr>
            </table>
            <div id="fundMessage" style="color: red;height: 25px;margin-left: 20px; ">
            </div>
            <div align="center" style="width: 100%">
                <div style="float: left;width: 49%">
                    <p id="searchBtn" style="<c:if test="${scope eq 'audit'}">display: none;</c:if>">
                        <input type="button" style="float: right" class="search" value="保&#12288;&#12288;存"
                               onclick="saveReimburse();"/>
                    </p>
                </div>
                <div style="float: right;width: 49%">
                    <input type="button" style="float: left" class="search" value="关&#12288;&#12288;闭"
                           onclick="refreshParentPage();"/>
                </div>
            </div>

        </form>
        <br/>
        <br/>
        <c:if test="${scope eq 'audit'}">
            <div class="searchDiv" style="margin-right: 30px">
                审核状态：
                <input type="radio" name="projStatusId" id="achStatusEnumAll" value="all"/>
                <label for="achStatusEnumAll">全部</label>
                <input type="radio" name="projStatusId" id="achStatusEnumSubmit" value="${achStatusEnumSubmit.id}"
                       checked="checked"/>
                <label for="achStatusEnumSubmit">${achStatusEnumSubmit.name}</label>
                <input type="radio" name="projStatusId" id="achStatusEnumEnumPass" value="${achStatusEnumPass.id}"/>
                <label for="achStatusEnumEnumPass">${achStatusEnumPass.name}</label>
            </div>
        </c:if>
        <table style="width: 100%;" class="bs_tb">
            <tr>
                <th style="text-align: center; width:5%;">序号</th>
                <th style="text-align: center; width:11%;">项目类别</th>
                <th style="text-align: center; width:25%;">项目明细</th>
                <th style="text-align: center; width:10%;">项目金额</th>
                <th style="text-align: center; width:10%;">登记时间</th>
                <th style="text-align: center; width:14%;">备注</th>
                <th style="text-align: center; width:7%;">状态</th>
                <th style="text-align: center; width: 18%;">操作</th>
            </tr>
            <c:if test="${(scope eq 'apply') or (scope eq 'director')}">
                <tbody id="applyBody">
                <c:forEach items="${details}" var="detail" varStatus="status">
                    <c:if test="${(detail.fundTypeId eq projFundTypeEnumReimburse.id)}">
                        <tr>
                            <td class="reimburseSerial"></td>
                            <td>${detail.reimburseName}</td>
                            <td>
                                    ${detail.content}
                            </td>
                            <td>
                                <fmt:formatNumber type="number" value="${detail.money*10000}" maxFractionDigits="2"/>（元）
                            </td>
                            <td>
                                    ${pdfn:transDate(detail.createTime)}
                            </td>
                            <td>
                                    ${detail.remark}
                            </td>
                            <td>
                                    ${detail.operStatusName}
                            </td>
                            <td>
                                <c:if test="${(scope eq 'apply') and ((detail.operStatusId eq achStatusEnumApply.id) or (detail.operStatusId eq achStatusEnumRollBack.id))}">
                                    <a href="javascript:void(0)"
                                       onclick="modify('${detail.fundDetailFlow}' , '${detail.reimburseId}' , '${detail.money}' , '${detail.content}','${detail.remark}','${detail.createTime}')"
                                       id="modifyLink">[修改]</a>
                                    &nbsp;
                                    <a href="javascript:void(0)"
                                       onclick="deleteDetail('${detail.fundDetailFlow}')">[删除]</a>&nbsp;
                                    <a href="javascript:void(0)"
                                       onclick="submitReimburse('${detail.fundDetailFlow}')">[送审]</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </c:if>
            <tbody id="submitBody">
            <c:forEach items="${details}" var="detail" varStatus="status">
                <c:if test="${(detail.fundTypeId eq projFundTypeEnumReimburse.id) && (detail.operStatusId eq achStatusEnumSubmit.id)}">
                    <tr>
                        <td class="reimburseSerial"></td>
                        <td>${detail.reimburseName}</td>
                        <td>
                                ${detail.content}
                        </td>
                        <td>
                            <fmt:formatNumber type="number" value="${detail.money*10000}" maxFractionDigits="2"/>（元）
                        </td>
                        <td>
                                ${pdfn:transDate(detail.createTime)}
                        </td>
                        <td>
                                ${detail.remark}
                        </td>
                        <td>
                                ${detail.operStatusName}
                        </td>
                        <td>
                            <c:if test="${scope eq 'audit'}">
                                <c:if test="${detail.operStatusId eq achStatusEnumSubmit.id}">
                                    <a href="javascript:void(0)"
                                       onclick="modify('${detail.fundDetailFlow}' , '${detail.reimburseId}' , '${detail.money}' , '${detail.content}','${detail.remark}','${detail.createTime}')"
                                       id="modifyLink">[修改]</a>&nbsp;
                                    <a href="javascript:void(0)"
                                       onclick="auditReimburse('${detail.fundDetailFlow}','Y','${detail.reimburseId}','${detail.money}')">[通过]</a>&nbsp;
                                    <a href="javascript:void(0)"
                                       onclick="auditReimburse('${detail.fundDetailFlow}','N')">[退回]</a>
                                </c:if>
                            </c:if>

                                <%--<a href="javascript:void(0)" onclick="findAuditDetail('${detail.fundDetailFlow}')">[查看]</a>--%>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
            <tbody id="auditBody" style="display: none">
            <c:forEach items="${details}" var="detail" varStatus="status">
                <c:if test="${(detail.fundTypeId eq projFundTypeEnumReimburse.id) && (detail.operStatusId eq achStatusEnumPass.id)}">
                    <tr>
                        <td class="reimburseSerial"></td>
                        <td>${detail.reimburseName}</td>
                        <td>
                                ${detail.content}
                        </td>
                        <td>
                            <fmt:formatNumber type="number" value="${detail.money*10000}" maxFractionDigits="2"/>（元）
                        </td>
                        <td>
                                ${pdfn:transDate(detail.createTime)}
                        </td>
                        <td>
                                ${detail.remark}
                        </td>
                        <td>
                                ${detail.operStatusName}
                        </td>
                        <td>

                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>

        </table>
    </div>
</div>
<c:forEach var="budgetItem" items="${budgetFundDtlMap}">
    <input type="hidden" id="budget_${budgetItem.key}" value="${budgetItem.value.money}"/>
</c:forEach>
</body>
</html>