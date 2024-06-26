<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <style type="text/css">
        #myForm div{margin-top:5px;}
        input[type='text']{width:133px;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
        <form id="myForm">
            <table class="basic" style="width: 100%;">
                <tr class="bs_name" style="height: 26px">
                    <td style="text-align:center" colspan="8">学术申请表</td>
                </tr>
                <tr>
                    <th style="width: 20%;">申请人：</th>
                    <td >
                        <input type="text" value="${acActivity.applyUserName}" readonly="readonly">
                    </td>
                    <th style="width: 20%;">申请时间：</th>
                    <td >
                        <input type="text" value="${acActivity.applyTime}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">申请人部门：</th>
                    <td >
                        <input type="text"value="${acActivity.applyDeptName}" readonly="readonly">
                    </td>

                    <th style="width: 20%;"><span style="color:red;">*</span>学术名称：</th>
                    <td >
                        <input type="text" value="${acActivity.academicName}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术类型：</th>
                    <td >
                        <input type="text" value="${acActivity.academicTypeName}" readonly="readonly">
                    </td>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术天数：</th>
                    <td >
                        <input type="text" value="${acActivity.takeDay}" readonly="readonly">
                    </td>

                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术地点：</th>
                    <td colspan="8">
                        <input type="text" value="${acActivity.placeProvince}" readonly="readonly">
                        <input type="text" value="${acActivity.placeCity}" readonly="readonly">
                        <input type="text" value="${acActivity.placeArea}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术开始时间：</th>
                    <td >
                        <input type="text" readonly="readonly" value="${acActivity.beginTime}">
                    </td>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术结束时间：</th>
                    <td >
                        <input type="text" readonly="readonly" value="${acActivity.endTime}">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"><span style="color:red;">*</span>学术内容：</th>
                    <td colspan="6">
                        <textarea type="text" style="width:98%;height:80px;" placeholder="30-300字符" readonly="readonly">${acActivity.academicContent}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">主办单位：</th>
                    <td colspan="6">
                        <input type="text" maxlength="50" value="${acActivity.holdUnit}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">备注：</th>
                    <td colspan="6">
                        <textarea type="text" style="width:98%;height:80px;" placeholder="支持250字符" readonly="readonly">${acActivity.remark}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;" rowspan="${fn:length(fileList)+1}">附件：</th>
                    <c:if test="${empty fileList}">
                        <td colspan="7">暂无附件信息</td>
                    </c:if>
                    <c:if test="${not empty fileList}">
                        <td >附件名称</td>
                        <td colspan="4">附件内容</td>
                    </c:if>
                </tr>
                <c:forEach var="file" items="${fileList}">
                    <tr>
                        <td >
                            <a id="down" href='<s:url value="/fstu/book/fileDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                        </td>
                        <td colspan="4">
                            <input class="validate[required,maxSize[100]] xltext" style="width: 90%;"
                                   name="fileRemark" type="text" value="${file.fileRemark}" readonly="readonly"/>
                        </td>
                    </tr>
                </c:forEach>
                <tr style="${acActivity.auditStatusId eq 'Passed'?'':'display:none;'}">
                    <th style="width: 20%;"><span style="color:red;">*</span>学术总结：</th>
                    <td colspan="6">
                        <textarea type="text" style="width:98%;height:80px;" placeholder="至少300字符" readonly="readonly">${acActivity.academicSummary}</textarea>
                    </td>
                </tr>

                </table>
            <table class="basic" style="width: 100%;">
                    <tr class="bs_name" style="height: 26px">
                    <td style="text-align:left" colspan="9">预计费用：</td>
                </tr>
                <tr>
                    <th style="width: 20%;">会议费：</th>
                    <td >
                        <input type="text" value="${acActivity.meetingBudget}" readonly="readonly">
                    </td>
                    <th style="width: 20%;">资料费：</th>
                    <td >
                        <input type="text" value="${acActivity.materialBudget}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">交通费：</th>
                    <td >
                        <input type="text" value="${acActivity.trafficBudget}" readonly="readonly">
                    </td>
                    <th style="width: 20%;">服装费：</th>
                    <td >
                        <input type="text" value="${acActivity.costumeBudget}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">是否含食宿：</th>
                    <td >
                        <input type="radio" name="foodBudgetWhether" value="Y" <c:if test="${acActivity.foodBudgetWhether eq 'Y'}">checked="checked"</c:if> disabled="disabled">含&#12288;&#12288;&#12288;&#12288;&#12288;
                        <input type="radio" name="foodBudgetWhether" value="N" <c:if test="${acActivity.foodBudgetWhether eq 'N'}">checked="checked"</c:if> disabled="disabled">不含
                    </td>
                    <th style="width: 20%;">食宿费：</th>
                    <td >
                        <input type="text" value="${acActivity.foodBudget}" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">补贴费：</th>
                    <td >
                        <input type="text" readonly="readonly" value="${acActivity.subsidyBudget}">
                    </td>
                    <th style="width: 20%;">其他费：</th>
                    <td >
                        <input type="text" readonly="readonly" value="${acActivity.otherBudget}">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"> 预计费用合计：</th>
                    <td >
                        <input type="text" value="${acActivity.sumBudget}" readonly="readonly">
                    </td>
                </tr>

                    <c:if test="${acActivity.auditStatusId ne 'Add'}"><%-- 学术申请送审后 可见 --%>
                <tr>

                    <th style="width: 20%;"> 科室主任审核意见：</th>
                    <td colspan="3">
                        <input type="text" style="width:98%;" value="${acActivity.headerAdvice}" size="100" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;"> 继教部审核意见：</th>
                    <td colspan="3">
                        <input type="text" style="width:98%;" value="${acActivity.adminAdvice}" size="100" readonly="readonly">
                    </td>
                </tr>
                    </c:if>
                </table>
            <table class="basic" style="width: 100%;">
                <tr class="bs_name" style="height: 26px">
                    <td style="text-align:left" colspan="4">实际费用：</td>
                </tr>
                <tr>
                <th style="width: 20%;">会议费：</th>
                <td >
                    <input type="text" readonly="readonly" value="${acActivity.meetingFee}">
                </td>
                <th style="width: 20%;">资料费：</th>
                <td >
                    <input type="text" readonly="readonly" value="${acActivity.materialFee}">
                </td>
                </tr>
                <tr>
                    <th style="width: 20%;">交通费：</th>
                    <td >
                        <input type="text" readonly="readonly" value="${acActivity.trafficFee}">
                    </td>
                    <th style="width: 20%;">服装费：</th>
                    <td >
                        <input type="text" readonly="readonly" value="${acActivity.costumeFee}">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">是否含食宿：</th>
                    <td >
                        <input type="radio" name="foodFeeWhether" value="Y" <c:if test="${acActivity.foodFeeWhether eq 'Y'}">checked="checked"</c:if> disabled="disabled">含&#12288;&#12288;&#12288;&#12288;&#12288;
                        <input type="radio" name="foodFeeWhether" value="N" <c:if test="${acActivity.foodFeeWhether eq 'N'}">checked="checked"</c:if> disabled="disabled">不含
                    </td>
                    <th style="width: 20%;">食宿费：</th>
                    <td >
                        <input type="text" readonly="readonly" value="${acActivity.foodFee}">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">补贴费：</th>
                    <td >
                        <input type="text" readonly="readonly" value="${acActivity.subsidyFee}">
                    </td>
                    <th style="width: 20%;">其他费：</th>
                    <td >
                        <input type="text" readonly="readonly" value="${acActivity.otherFee}">
                    </td>
                </tr>
                <tr>
                    <th style="width: 20%;">实际费用合计：</th>
                    <td colspan="3">
                        <input type="text" value="${acActivity.sumFee}" readonly="readonly">
                    </td>
                </tr>
            </table>
            <table class="basic" style="width: 100%;">
                <c:if test="${acActivity.auditStatusId eq 'Passed' && acActivity.expenseStatusId ne 'Add'}"><%-- 学术申请审核通过 可见 --%>
                <tr>
                    <th style="width: 20%;">继教部审核意见：</th>
                    <td>
                        <input type="text" style="width:98%;" value="${acActivity.expenseAdminAdvice}" size="100" readonly="readonly">
                    </td>
                </tr>
                </c:if>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
        </div>
    </div>
</div>
</body>
</html>