<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        #myForm div{margin-top:5px;}
        input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            jboxPost("<s:url value='/fstu/academic/saveAcademic'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
        function sub(academicFlow){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            jboxConfirm("确认提交?", function(){
                var url = "<s:url value='/fstu/academic/submitExpense'/>";
                jboxPost(url, $('#myForm').serialize(), function(resp){
                    if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                }, null, false);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
        <form id="myForm">
            <table class="basic" style="width: 100%;">
                <tr class="bs_name" style="height: 26px">
                    <td style="text-align:center" colspan="4">学术活动报销单</td>
                </tr>
                <tr>
                    <th style="width: 20%;">申请人：</th>
                    <td>
                        <input type="text" name="applyUserName" value="${acActivity.applyUserName}" readonly="readonly">
                    </td>
                    <th style="width: 20%;">申请时间：</th>
                    <td>
                        <input type="text" name="applyTime" value="${acActivity.applyTime}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">申请人部门：</th>
                    <td>
                        <input type="text" name="applyDeptName" value="${acActivity.applyDeptName}" readonly="readonly">
                    </td>
                    <th style="width: 20%;"><span style="color: red;">*</span>学术名称：</th>
                    <td>
                        <input type="text" value="${acActivity.academicName}" name="academicName" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color: red;">*</span>学术类型：</th>
                    <td>
                        <input type="text" value="${acActivity.academicTypeId}" name="academicTypeId" readonly="readonly">
                        <input type="hidden" name="applyYear" value="${acActivity.applyYear}">
                        <input type="hidden" name="applyDeptFlow" value="${acActivity.applyDeptFlow}">
                        <input type="hidden" name="applyOrgFlow" value="${acActivity.applyOrgFlow}">
                        <input type="hidden" name="applyOrgName" value="${acActivity.applyOrgName}">
                        <input type="hidden" name="academicFlow" value="${acActivity.academicFlow}">
                        <input type="hidden" name="applyUserFlow" value="${acActivity.applyUserFlow}">
                        <input type="hidden" name="academicTypeName" value="${acActivity.academicTypeName}">
                    </td>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术天数：</th>
                    <td>
                        <input type="text" name="takeDay" value="${acActivity.takeDay}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color: red;">*</span>学术地点：</th>
                    <td colspan="3">
                        <input type="text" value="${acActivity.placeProvinceId}" name="placeProvinceId" readonly="readonly">
                        <input type="text" value="${acActivity.placeCityId}" name="placeCityId" readonly="readonly">
                        <input type="text" name="placeArea" value="${acActivity.placeArea}" readonly="readonly">
                        <input name="placeProvince" type="hidden" value="${acActivity.placeProvince}">
                        <input name="placeCity" type="hidden" value="${acActivity.placeCity}">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术开始时间：</th>
                    <td>
                        <input type="text" value="${acActivity.beginTime}" name="beginTime" readonly="readonly">
                    </td>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术结束时间：</th>
                    <td>
                        <input type="text" value="${acActivity.endTime}" name="endTime" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术内容：</th>
                    <td colspan="3">
                        <textarea type="text" name="academicContent" style="width:98%;height:80px;" placeholder="30-300字符" readonly="readonly">${acActivity.academicContent}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">主办单位：</th>
                    <td colspan="3">
                        <input type="text" name="holdUnit" maxlength="50" value="${acActivity.holdUnit}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">备&#12288;&#12288;注：</th>
                    <td colspan="3">
                        <textarea type="text" name="remark" style="width:98%;height:80px;" placeholder="支持250字符" readonly="readonly">${acActivity.remark}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;" rowspan="${fn:length(fileList)+1}">附&#12288;&#12288;件：</th>
                    <c:if test="${empty fileList}">
                        <td colspan="3">暂无附件信息</td>
                    </c:if>
                    <c:if test="${not empty fileList}">
                        <td>附件名称</td>
                        <td colspan="2">附件内容</td>
                    </c:if>
                </tr>
                <c:forEach var="file" items="${fileList}">
                    <tr>
                        <td>
                            <a id="down" href='<s:url value="/fstu/book/fileDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                        </td>
                        <td colspan="2">
                            <input class="validate[required,maxSize[100]] xltext" style="width: 90%;"
                                   name="fileRemark" type="text" value="${file.fileRemark}" readonly="readonly"/>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术总结：</th>
                    <td colspan="3">
                        <textarea type="text" style="width:98%;height:80px;" name="academicSummary" placeholder="至少300字符" class="validate[required,minSize[300]]">${acActivity.academicSummary}</textarea>
                    </td>
                </tr>
            </table>

            <table class="basic" style="width: 100%;">
                <tr class="bs_name" style="height: 26px">
                    <td style="text-align:left" colspan="4">预计费用：</td>
                </tr>
                <tr>
                    <th style="width: 20%;">会议费：</th>
                    <td>
                        <input type="text" name="meetingBudget" value="${acActivity.meetingBudget}" readonly="readonly">
                    </td>
                    <th style="width: 20%;">资料费：</th>
                    <td>
                        <input type="text" name="materialBudget" value="${acActivity.materialBudget}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">交通费：</th>
                    <td>
                        <input type="text" name="trafficBudget" value="${acActivity.trafficBudget}" readonly="readonly">
                    </td>
                    <th style="width: 20%;">服装费：</th>
                    <td>
                        <input type="text" name="costumeBudget" value="${acActivity.costumeBudget}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">是否含食宿：</th>
                    <td>
                        <input type="radio" name="foodBudgetWhether" value="Y" <c:if test="${acActivity.foodBudgetWhether eq 'Y'}">checked="checked"</c:if> disabled="disabled">含&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
                        <input type="radio" name="foodBudgetWhether" value="N" <c:if test="${acActivity.foodBudgetWhether eq 'N'}">checked="checked"</c:if> disabled="disabled">不含
                    </td>
                    <th style="width: 20%;">食宿费：</th>
                    <td>
                        <input type="text" name="foodBudget" value="${acActivity.foodBudget}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">补贴费：</th>
                    <td>
                        <input type="text" name="subsidyBudget" value="${acActivity.subsidyBudget}" readonly="readonly">
                    </td>
                    <th style="width: 20%;">其他费：</th>
                    <td>
                        <input type="text" name="otherBudget" value="${acActivity.otherBudget}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"> 预计费用合计：</th>
                    <td>
                        <input type="text" name="sumBudget" value="${acActivity.sumBudget}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"> 科室主任审核意见：</th>
                    <td colspan="3">
                        <input type="text" style="width:98%;" name="headerAdvice" value="${acActivity.headerAdvice}" size="100" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"> 继教部审核意见：</th>
                    <td colspan="3">
                        <input type="text" style="width:98%;" name="adminAdvice" value="${acActivity.adminAdvice}" size="100" readonly="readonly">
                    </td>
                </tr>
            </table>

            <table class="basic" style="width: 100%;">
                <c:if test="${acActivity.expenseStatusId ne 'Add'}"><%-- 学术报销申请送审后 可见 --%>
                <tr class="bs_name" style="height: 26px">
                    <td style="text-align:left" colspan="4">实际费用：</td>
                </tr>
                <tr>
                    <th style="width: 20%;">会议费：</th>
                    <td>
                        <input type="text" name="meetingFee" value="${acActivity.meetingFee}" readonly="readonly">
                    </td>
                    <th style="width: 20%;">资料费：</th>
                    <td>
                        <input type="text" name="materialFee" value="${acActivity.materialFee}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">交通费：</th>
                    <td>
                        <input type="text" name="trafficFee" value="${acActivity.trafficFee}" readonly="readonly">
                    </td>
                    <th style="width: 20%;">服装费：</th>
                    <td>
                        <input type="text" name="costumeFee" value="${acActivity.costumeFee}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">是否含食宿：</th>
                    <td>
                        <input type="radio" name="foodFeeWhether" value="Y" <c:if test="${acActivity.foodFeeWhether eq 'Y'}">checked="checked"</c:if> disabled="disabled">含&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
                        <input type="radio" name="foodFeeWhether" value="N" <c:if test="${acActivity.foodFeeWhether eq 'N'}">checked="checked"</c:if> disabled="disabled">不含
                    </td>
                    <th style="width: 20%;">食宿费：</th>
                    <td>
                        <input type="text" name="foodFee" value="${acActivity.foodFee}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">补贴费：</th>
                    <td>
                        <input type="text" name="subsidyFee" value="${acActivity.subsidyFee}" readonly="readonly">
                    </td>
                    <th style="width: 20%;">其他费：</th>
                    <td>
                        <input type="text" name="otherFee" value="${acActivity.otherFee}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">实际费用合计：</th>
                    <td>
                        <input type="text" name="sumFee" value="${acActivity.sumFee}" readonly="readonly">
                    </td>
                </tr>
             </table>
            <table class="basic" style="width: 100%;">
                <tr>
                    <th style="width: 20%;">继教部审核意见：</th>
                    <td colspan="3">
                        <input type="text" style="width:98%;" name="expenseAdminAdvice" value="${acActivity.expenseAdminAdvice}" size="100" readonly="readonly">
                    </td>
                </tr>
            </table>
            </c:if>

            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <c:if test="${acActivity.expenseStatusId ne 'Passing'}">
                    <input type="button" class="search" onclick="sub('${acActivity.academicFlow}');" value="提&#12288;交"/>
                </c:if>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
        </div>
    </div>
</div>
</body>
</html>