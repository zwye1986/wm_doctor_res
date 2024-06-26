<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        #myForm div{margin-top:5px;}
        input[type='text']{width:133px;}
    </style>
    <script type="application/javascript">
        $(function(){
            $(".fee").bind("change",function(){
                var fee = 0;
                $(".fee").each(function(){
                    fee += Number($(this).val());
                });
                $('#sumFee').val(fee)
            });
        });
        function option(auditStatusId){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var url = "<s:url value='/fstu/academic/auditOption?auditStatusId='/>"+auditStatusId;
            jboxPost(url, $('#myForm').serialize(), function(resp){
                if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            }, null, false);
        }
        function selectFood(obj){
            var feeInp = $(obj).closest("div").find(".food").eq(0);
            if($(obj).val() == "Y"){
                $(feeInp).attr("disabled",false);
            }else{
                var fee = Number($('#sumFee').val())-Number($(feeInp).val())
                $('#sumFee').val(fee);
                $(feeInp).val("");
                $(feeInp).attr("disabled",true);
            }
        }
        function prt(academicFlow){
            jboxTip("打印中，请稍等...");
            setTimeout(function(){
                var url = "<s:url value='/fstu/academic/printExpense?academicFlow='/>"+academicFlow;
                jboxPost(url, null, function(resp){
                    if (resp) {
                        var headstr = "<html><head><title></title></head><body>";
                        var footstr = "</body></html>";
                        var newstr = resp;
                        var oldstr = document.body.innerHTML;
                        document.body.innerHTML = headstr+newstr+footstr;
                        window.print();
                        document.body.innerHTML = oldstr;
                        return false;
                    }
                }, null, false);
            },2000);
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
                            <input type="text" value="${acActivity.applyUserName}" disabled="disabled">
                        </td>
                        <th style="width: 20%;">申请时间：</th>
                        <td>
                            <input type="text" value="${acActivity.applyTime}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">申请人部门：</th>
                        <td>
                            <input type="text"value="${acActivity.applyDeptName}" disabled="disabled">
                        </td>
                        <th style="width: 20%;"><span style="color:red;">*</span>学术名称：</th>
                        <td>
                            <input type="text" value="${acActivity.academicName}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>学术类型：</th>
                        <td>
                            <input type="text" value="${acActivity.academicTypeName}" disabled="disabled">
                        </td>
                        <th style="width: 20%;"><span style="color:red;">*</span>学术天数：</th>
                        <td>
                            <input type="text" value="${acActivity.takeDay}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>学术地点：</th>
                        <td colspan="3">
                            <input type="text" value="${acActivity.placeProvince}" disabled="disabled">
                            <input type="text" value="${acActivity.placeCity}" disabled="disabled">
                            <input type="text" value="${acActivity.placeArea}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>学术开始时间：</th>
                        <td>
                            <input type="text" disabled="disabled" value="${acActivity.beginTime}">
                        </td>
                        <th style="width: 20%;"><span style="color:red;">*</span>学术结束时间：</th>
                        <td>
                            <input type="text" disabled="disabled" value="${acActivity.endTime}">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>学术内容：</th>
                        <td colspan="3">
                            <textarea type="text" style="width:98%;height:80px;" placeholder="30-300字符" disabled="disabled">${acActivity.academicContent}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">主办单位：</th>
                        <td colspan="3">
                            <input type="text" maxlength="50" value="${acActivity.holdUnit}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">备注：</th>
                        <td colspan="3">
                            <textarea type="text" style="width:98%;height:80px;" placeholder="支持250字符" disabled="disabled">${acActivity.remark}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;" rowspan="${fn:length(fileList)+1}">附件：</th>
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
                                <input class="validate[required,maxSize[100]]" style="width: 96%;"
                                       name="fileRemark" type="text" value="${file.fileRemark}" disabled="disabled"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>学术总结：</th>
                        <td colspan="3">
                            <textarea type="text" style="width:98%;height:80px;" name="academicSummary" placeholder="至少300字符" disabled="disabled">${acActivity.academicSummary}</textarea>
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
                            <input type="text" value="${acActivity.meetingBudget}" disabled="disabled">
                        </td>
                        <th style="width: 20%;">资料费：</th>
                        <td>
                            <input type="text" value="${acActivity.materialBudget}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">交通费：</th>
                        <td>
                            <input type="text" value="${acActivity.trafficBudget}" disabled="disabled">
                        </td>
                        <th style="width: 20%;">服装费：</th>
                        <td>
                            <input type="text" value="${acActivity.costumeBudget}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">是否含食宿：</th>
                        <td>
                            <input type="radio" value="Y" <c:if test="${acActivity.foodBudgetWhether eq 'Y'}">checked="checked"</c:if> disabled="disabled">含&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
                            <input type="radio" value="N" <c:if test="${acActivity.foodBudgetWhether eq 'N'}">checked="checked"</c:if> disabled="disabled">不含
                        </td>
                        <th style="width: 20%;">食宿费：</th>
                        <td>
                            <input type="text" value="${acActivity.foodBudget}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">补贴费：</th>
                        <td>
                            <input type="text" disabled="disabled" value="${acActivity.subsidyBudget}">
                        </td>
                        <th style="width: 20%;">其他费：</th>
                        <td>
                            <input type="text" disabled="disabled" value="${acActivity.otherBudget}">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"> 预计费用合计：</th>
                        <td>
                            <input type="text" value="${acActivity.sumBudget}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"> 科室主任审核意见：</th>
                        <td colspan="3">
                            <input type="text" style="width:98%;" value="${acActivity.headerAdvice}" size="100" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"> 继教部审核意见：</th>
                        <td colspan="3">
                            <input type="text" style="width:98%;" value="${acActivity.adminAdvice}" size="100" disabled="disabled">
                        </td>
                    </tr>
                </table>

                <table class="basic" style="width: 100%;">
                    <tr class="bs_name" style="height: 26px">
                        <td style="text-align:left" colspan="4">实际费用：</td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">会议费：</th>
                        <td>
                            <input type="text" name="meetingFee" value="${acActivity.meetingFee}" class="fee validate[custom[number]]">
                        </td>
                        <th style="width: 20%;">资料费：</th>
                        <td>
                            <input type="text" name="materialFee" value="${acActivity.materialFee}" class="fee validate[custom[number]]">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">交通费：</th>
                        <td>
                            <input type="text" name="trafficFee" value="${acActivity.trafficFee}" class="fee validate[custom[number]]">
                        </td>
                        <th style="width: 20%;">服装费：</th>
                        <td>
                            <input type="text" name="costumeFee" value="${acActivity.costumeFee}" class="fee validate[custom[number]]">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">是否含食宿：</th>
                        <td>
                            <input type="radio" name="foodFeeWhether" value="Y" <c:if test="${acActivity.foodFeeWhether eq 'Y'}">checked="checked"</c:if> onclick="selectFood(this)">含&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
                            <input type="radio" name="foodFeeWhether" value="N" <c:if test="${acActivity.foodFeeWhether eq 'N'}">checked="checked"</c:if> onclick="selectFood(this)">不含
                        </td>
                        <th style="width: 20%;">食宿费：</th>
                        <td>
                            <input type="text" name="foodFee" value="${acActivity.foodFee}" class="food fee validate[custom[number]]" <c:if test="${acActivity.foodFeeWhether eq 'N'}">disabled='disabled'</c:if>>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">补贴费：</th>
                        <td>
                            <input type="text" name="subsidyFee" value="${acActivity.subsidyFee}" class="fee validate[custom[number]]">
                        </td>
                        <th style="width: 20%;">其他费：</th>
                        <td>
                            <input type="text" name="otherFee" value="${acActivity.otherFee}" class="fee validate[custom[number]]">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">实际费用合计：</th>
                        <td colspan="3">
                            <input type="text" id="sumFee" name="sumFee" value="${acActivity.sumFee}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">继教部审核意见：</th>
                        <td colspan="3">
                            <input type="text" style="width:98%;" name="expenseAdminAdvice" value="${acActivity.expenseAdminAdvice}" size="100">
                            <input type="hidden" name="academicFlow" value="${acActivity.academicFlow}">
                        </td>
                    </tr>
                </table>
            <div style="text-align: center;margin-top:20px;">
                <c:if test="${acActivity.expenseStatusId eq 'Passing'}">
                    <input type="button" class="search" value="完&#12288;结" onclick="option('Passed');"/>
                    <input type="button" class="search" value="退&#12288;回" onclick="option('Backed');"/>
                </c:if>
                <input type="button" class="search" value="保&#12288;存" onclick="option('');"/>
                <input type="button" class="search" value="打&#12288;印" onclick="prt('${acActivity.academicFlow}');"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
        </div>
    </div>
</div>
</body>
</html>