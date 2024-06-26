<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
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
        function audit(operstatusid, funddetailflow,money,rejectreason) {
            var fundFlow = $("input[name='fundFlow']").val();
            jboxConfirm("确认" + (('${achStatusEnumSecondAudit.id}' == operstatusid) ? "通过" : "驳回") + "?", function () {
                var data = {
                    "fundFlow": fundFlow,
                    "fundDetailFlow": funddetailflow,
                    "operStatusId": operstatusid,
                    "content": rejectreason,
                    "money":money
                };
                var url = "<s:url value='/srm/fund/auditFundDetail'/>";
                jboxPost(url, data, function (resp) {
                    if (resp == '${GlobalConstant.OPERATE_SUCCESSED}') {
                        jboxContentClose();
                        window.parent.frames['mainIframe'].window.search();
                        jboxCloseMessager();
                    }
                }, null, false);
            });


        }

        function saveDetails() {
            if (!$("#detailsForm").validationEngine("validate")) {
                return false;
            }
            jboxConfirm("确认提交?", function () {
                var authTab = $('#test');//query选择器:id为test的元素
                var trs = authTab.children();//.children()方法获取authTab的子元素
                var datas = [];//js数组对象   datas[0]
                var fundFlow = $("input[name='fundFlow']").val();//jquery选择器:选择标签名为input,属性name值为fundFlow的所有元素,.val()取得元素值
                var isDataExist = false;//是否有新数据提交
                $.each(trs, function (i, n) {//jquery迭代器,用于循环数组和json  i表示索引   n表示当前循环的元素
                    var realityTypeId = $(n).find("input[name='realityTypeId']").val();//$(n)  $(js对象)   将js对象转换成jquery对象
                    var money = $(n).find("input[name='money']").val();
                    var provideDateTime = $(n).find("input[name='provideDateTime']").val();
                    var operStatusId = "";
                    <c:if test="${sessionScope.projListScope=='finance'}">
                    operStatusId = "${achStatusEnumSecondAudit.id}";
                    </c:if>
                    var data = {//json对象   {key:value}
                        "fundFlow": fundFlow,
                        "realityTypeId": realityTypeId,
                        "money": -money/10000,
                        "provideDateTime": provideDateTime,
                        "fundTypeId": '${projFundTypeEnumManageFee.id}',
                        "operStatusId": operStatusId
                    };
                    datas.push(data);//数组的push方法
                    if(money != null && realityTypeId != null && provideDateTime != null){
                        isDataExist = true;
                    }
                });
                var requestData = JSON.stringify(datas);//将数组对象转换成json字符串
                var url = "<s:url value='/srm/fund/saveDetail'/>";
                if(isDataExist){
                jboxStartLoading();
                jboxPostJson(url, requestData, function (resp) {
                    if (resp == '${GlobalConstant.SAVE_SUCCESSED}') {
                        window.parent.frames['mainIframe'].window.search();
                        jboxCloseMessager();
                    }
                }, null, true);
                }else{
                    jboxTip("没有修改数据");
                }
            });
        }

        function doClose() {
            jboxClose();
        }
        function check(obj) {
            if (obj.checked == true) {
                obj.value = 1;
            }
            else {
                obj.value = 0;
            }
        }
        function addItem() {
            $('#test').append($("#moban tr:eq(0)").clone());
        }
        function delItemTr(obj) {
            var tr = obj.parentNode.parentNode;
            tr.remove();
        }
        function delFundDetail(detailFlow) {
            jboxConfirm("确认删除该记录?", function () {
                jboxStartLoading();
                var url = "<s:url value='/srm/fund/delDetail?fundDetailFlow='/>" + detailFlow;
                jboxGet(url, null, function () {
                    $("#searchForm", window.parent.frames['mainIframe'].document).submit();
                    jboxCloseMessager();
                }, null, true);
            });

        }


        function rejectReason(operStatusId, detailFlow) {
            jboxOpenContent(
                    '<table class="bs_tb" width="100%" cellspacing="0" cellpadding="0">' +
                    '<tr><th style="text-align:left">驳回理由</th></tr>' +
                    '<tr><td><textarea id="rejectReason" rows="6" cols="70"></textarea></td></tr>' +
                    ' <tr></table><div class="button"><input type="button" class="search" value="确定" onclick="top.frames[\'jbox-message-iframe\'].audit(\'' + operStatusId + '\',\'' + detailFlow + '\',null,$(\'#rejectReason\').val())" /> ' +
                    '<input type="button" class="search" value="取消" onclick="jboxContentClose();" /></div></tr>' +
                    '', '驳回理由', 500, 200, true);
        }
        function isOvertop(obj, maxLimit, budgetAmount) {
            if (maxLimit != null && maxLimit != "") {
                var deductFee = -parseFloat($("#deductFee").val());//已扣除管理费
                if(!deductFee){
                    deductFee = 0;
                }
                var money = $(obj).val().trim();
                var maxMoney = parseFloat(budgetAmount) * parseFloat(maxLimit);
                if ((parseFloat(money)+deductFee) * 100 > maxMoney) {
                    jboxTip("管理费超出预算");
                    $(obj).val(0);
                }
            } else {
                jboxTip("管理费未设置");
                $(obj).val(0);
            }
        }
    </script>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <c:if test="${view ne 'Y'}">
            <p style="font-weight: bold;font-size: 14px;"> 项目名称：${proj.projName}
                &nbsp;&nbsp;&nbsp;&nbsp;项目编号：${proj.projNo }&nbsp;&nbsp;&nbsp;&nbsp;项目类型：${proj.projTypeName}</p>
                </c:if>
            <br/>
            <div class="title1 clearfix">
                <form id="detailsForm">
                    <input id="deductFee" type="hidden" value="${pdfn:transMultiply(deductFee,10000)}"/>
                    <table class="bs_tb" width="100%" cellspacing="0" cellpadding="0">
                        <c:if test="${view ne 'Y'}">
                        <tr>
                            <th colspan="8" class="bs_name">管理费编辑<a href="javascript:void(0)">
                                <img
                                        src="<s:url value='/'/>css/skin/${skinPath}/images/add.png" onclick="addItem();"
                                        title="添加"/></a></th>
                        </tr>
                        </c:if>
                        <tr>
                            <th style="text-align: center; width:10%">管理费类型</th>
                            <th style="text-align: center; width:10%;">管理费金额(元)</th>
                            <th style="text-align: center; width:20%;">管理费扣除时间</th>
                            <th style="text-align: center; width:20%;" >审核状态</th>
                            <c:if test="${view ne 'Y'}">
                            <th style="text-align: center;width: 20%;">操作</th>
                            </c:if>
                        </tr>
                        <c:forEach items="${details}" var="detail">

                            <tr>
                                <td>
                                        ${detail.realityTypeName}
                                </td>
                                <td>
                                        ${pdfn:transMultiply(-detail.money,10000)}
                                </td>
                                <td>
                                        ${detail.provideDateTime}
                                </td>
                                <c:if test="${(sessionScope.projListScope=='finance') and (view ne 'Y') }">
                                    <td>
                                        <c:if test="${!(detail.operStatusId eq achStatusEnumSecondAudit.id) && !(detail.operStatusId eq achStatusEnumSecondBack.id)}">
                                            <a href="javascript:void(0);"
                                               onclick="audit('${achStatusEnumSecondAudit.id}','${detail.fundDetailFlow}','${detail.money}')">同意</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="javascript:void(0);"
                                               onclick="rejectReason('${achStatusEnumSecondBack.id}','${detail.fundDetailFlow}')">驳回</a>
                                        </c:if>
                                        <c:if test="${!(!(detail.operStatusId eq achStatusEnumSecondAudit.id) && !(detail.operStatusId eq achStatusEnumSecondBack.id))}">
                                            ${detail.operStatusName}
                                        </c:if>
                                    </td>
                                </c:if>
                                <c:if test="${(sessionScope.projListScope=='local') or (view eq 'Y')}">
                                    <td>
                                            ${detail.operStatusName}
                                    </td>
                                </c:if>
                                <c:if test="${view ne 'Y'}">
                                <td>
                                    <c:if test="${!(detail.operStatusId eq achStatusEnumSecondAudit.id) && !(detail.operStatusId eq achStatusEnumApply.id)}">
                                        <a href="javascript:void(0);"
                                           onclick="delFundDetail('${detail.fundDetailFlow}')">删除</a>
                                    </c:if>
                                </td>
                                </c:if>
                            </tr>

                        </c:forEach>
                        <tbody id="test">
                        </tbody>
                    </table>
                   <c:if test="${view ne 'Y'}">
                    <div class="button">
                        <input type="hidden" name="fundFlow" value="${param.fundFlow}"/>
                        <input id="jsondata" type="hidden" name="jsondata" value=""/>
                        <input type="button" value="提&#12288;交" onclick="saveDetails();" class="search"/>&#12288;<input
                            type="button" value="关&#12288;闭" onclick="jboxCloseMessager();" class="search"/>
                    </div>
                   </c:if>
                </form>

                <table id="moban" style="display: none">
                    <tr>
                        <td>
                            <%-- <select name="realityTypeId" class="validate[required]" style="width: 80px;">
                                 <option value="">请选择</option>
                                 <c:forEach var="fundAccountsType" items="${projFundAccountsTypeEnumList}">
                                     <option value="${fundAccountsType.id}" >${fundAccountsType.name}</option>
                                 </c:forEach>
                             </select>--%>
                            <input type="hidden" name="realityTypeId" value="Allocate"/>
                            <span>下拨</span>
                        </td>
                        <td>
                            <input style="width: 90%; text-align: center;" type="text"
                                   onblur="isOvertop(this,'${manageFee.maxLimit}',${fund.amountFund})" name="money"
                                   class="validate[required,custom[number],min[0]]"/>
                        </td>

                        <td>
                            <input type="text" name="provideDateTime" class="validate[required]" style="width:90%"
                                   readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        </td>

                        <td>

                        </td>
                        <td>
                            <a href="javascript:void(0);" onclick="delItemTr(this)">删除</a>
                        </td>
                    </tr>
                </table>

            </div>
        </div>
    </div>
</div>
</body>
</html>