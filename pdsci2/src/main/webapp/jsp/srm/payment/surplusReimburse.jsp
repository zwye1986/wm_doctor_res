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
        <jsp:param name="jquery_cxselect" value="true"/>
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

        function savePerson() {
            if(false==$("#reimburseForm").validationEngine("validate")){
                return;
            }

            var url = "<s:url value='/srm/surplus/saveReimburse'/>";
            jboxPost(url, $("#reimburseForm").serialize() , function(resp){
                if(resp == '${GlobalConstant.OPRE_SUCCESSED}'){
                    //alert(resp);
                    window.parent.closeEditPage();
                }
            } , null , false);

        }

        function validateMoney(obj){
            var money = parseFloat($(obj).val());
            if(money){
                var realityBalance = parseFloat($("#realityBalance").text());
                if(!realityBalance){
                    realityBalance=0;
                }
                if(money>realityBalance){
                    jboxTip("报销费用超过余额！");
                    $(obj).val("0")
                    return false;
                }
            }
        }

    </script>
    <style type="text/css">
        .xllist td{
            text-align: left;
        }
    </style>
</head>

<body>
<div style="height:260px;width:100%;overflow: auto;">
    <form id="reimburseForm" method="post">
        <input type="hidden" name="fundFlow" value="${fundInfo.fundFlow}"  />
        <table class="xllist" style="width: 98%">
            <tr>
                <th width="25%">姓&#12288;名</th>
                <td width="25%">${fundInfo.projName}</td>
                <th width="25%">总金额（元）</th>
                <td width="25%">${pdfn:transMultiply(fundInfo.realityAmount,10000)}</td>
            </tr>
            <tr>
                <th>已报销金额（元）</th>
                <td>${pdfn:transMultiply(fundInfo.yetPaymentAmount,10000)}</td>
                <th>剩余金额（元）</th>
                <td id="realityBalance">${pdfn:transMultiply(fundInfo.realityBalance,10000)}</td>
            </tr>
            <tr>
                <th>报销项目</th>
                <td colspan="3"><input name="itemName" class="validate[required]" style="width: 80%"  /></td>
            </tr>
            <tr>
                <th>报销方式</th>
                <td>
                    <select class="validate[required]" name="fundRetype" style="width: 80%">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumFundRetypeList}">
                            <option value="${dict.dictId}">${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
                <th>报销金额（元）</th>
                <td><input name="realmoney" class="validate[required,custom[number],min[0]]" onchange="validateMoney(this)" style="width: 80%" /></td>
            </tr>
            <tr>
                <th>报销时间</th>
                <td><input type="text" class="inputText validate[required] ctime" name="provideDateTime"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width:80%"/></td>
                <th>经办人</th>
                <td><input name="fundOperator" value="${sessionScope.currUser.userName}" class="validate[required]" style="width: 80%" /></td>
            </tr>
            <tr>
                <th>报销内容</th>
                <td colspan="3">
                    <textarea name="content" class="xltxtarea"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>
<p align="center" style="width:100%;padding-top: 10px;">
    <input type="button" onclick="savePerson();" class="search" value="确认">
    <input type="button" onclick="window.parent.doClose();" class="search" value="关闭">
</p>
</body>
</html>