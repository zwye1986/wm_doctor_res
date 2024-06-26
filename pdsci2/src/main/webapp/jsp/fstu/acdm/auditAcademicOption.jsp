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
    <script type="application/javascript">
        function option(auditStatusId){
            var url = "<s:url value='/fstu/academic/updateAcademicActivity?auditStatusId='/>"+auditStatusId;
            jboxPost(url, $('#myForm').serialize(), function(resp){
                if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            }, null, false);
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
                        <td style="text-align:center" colspan="4">学术申请表</td>
                    </tr>
                    <tr class="bs_name" style="height: 26px">
                        <td style="text-align:left" colspan="4">基本信息</td>
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
                            <input type="text"value="${acActivity.applyOrgName}" disabled="disabled">
                        </td>
                        <th style="width: 20%;">学术名称：</th>
                        <td>
                            <input type="text" value="${acActivity.academicName}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">学术类型：</th>
                        <td>
                            <input type="text" value="${acActivity.academicTypeName}" disabled="disabled">
                        </td>
                        <th style="width: 20%;"><span style="color:red;">*</span>学术天数：</th>
                        <td>
                            <input type="text" value="${acActivity.takeDay}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color: red;">*</span>学术地点：</th>
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
                        <th style="width: 20%;"><span style="color: red;">*</span>学术内容：</th>
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
                </table>
                <table class="basic" style="width:100%;">

                    <tr class="bs_name" style="height: 26px">
                        <td style="text-align:left" colspan="4">备注信息：</td>
                    </tr>
                    <tr>
                        <th width="20%">备注信息：</th>
                        <td colspan="3">
                            <textarea type="text" name="remark" disabled="disabled" style="width:98%;height:80px;" placeholder="支持250字符" class="validate[maxSize[250]]">${acActivity.remark}</textarea>
                        </td>
                    </tr>

                </table>

                <table class="basic" style="width:100%;">
                    <tr class="bs_name" style="height: 26px">
                        <td style="text-align:left" colspan="3">附件信息：</td>
                    </tr>
                    <tr>
                    <c:if test="${empty fileList}">
                        <td colspan="2">暂无附件信息</td>
                    </c:if>
                    <c:if test="${not empty fileList}">
                        <td width="38%">附件名称</td>
                        <td width="62%">附件内容</td>
                    </c:if>
                    </tr>
                    </thead>
                    <c:forEach var="file" items="${fileList}" varStatus="status">
                        <tr>
                            <td>
                                <a id="down" href='<s:url value="/fstu/book/fileDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                            </td>
                            <td>
                                <input class="validate[required,maxSize[100]] xltext" style="width: 90%;"
                                       name="fileRemark" type="text" value="${file.fileRemark}" disabled="disabled"/>
                            </td>

                        </tr>
                    </c:forEach>
                </table>

                <table class="basic" style="width:100%;">
                    <tr class="bs_name" style="height: 26px">
                        <td style="text-align:left" colspan="8">预计费用：</td>
                    </tr>
                    <tr>
                        <th width="20%">会议费：</th>
                        <td>
                            <input type="text" value="${acActivity.meetingBudget}" disabled="disabled">
                        </td>
                        <th width="20%">资料费：</th>
                        <td>
                            <input type="text" value="${acActivity.materialBudget}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">交通费：</th>
                        <td>
                            <input type="text" value="${acActivity.trafficBudget}" disabled="disabled">
                        </td>
                        <th width="20%">服装费：</th>
                        <td>
                            <input type="text" value="${acActivity.costumeBudget}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">是否含食宿：</th>
                        <td>
                            <input type="radio" value="Y" <c:if test="${acActivity.foodBudgetWhether eq 'Y'}">checked="checked"</c:if> disabled="disabled">含&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
                            <input type="radio" value="N" <c:if test="${acActivity.foodBudgetWhether eq 'N'}">checked="checked"</c:if> disabled="disabled">不含
                        </td>
                        <th width="20%">食宿费：</th>
                        <td>
                            <input type="text" value="${acActivity.foodBudget}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">补贴费：</th>
                        <td>
                            <input type="text" disabled="disabled" value="${acActivity.subsidyBudget}">
                        </td>
                        <th width="20%">其他费：</th>
                        <td>
                            <input type="text" disabled="disabled" value="${acActivity.otherBudget}">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">预计费用合计：</th>
                        <td>
                            <input type="text" value="${acActivity.sumBudget}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">科室主任审核意见：</th>
                        <td colspan="8">
                            <input type="text"  style="width:98%;" name="headerAdvice" value="${acActivity.headerAdvice}" size="100"></input>
                        </td>
                    </tr>
                    <tr>
                        <th width="20%">继教部审核意见：</th>
                        <td colspan="8">
                            <input type="text"  style="width:98%;" name="adminAdvice" value="${acActivity.adminAdvice}" size="100"></input>
                            <input type="hidden" name="academicFlow" value="${acActivity.academicFlow}">
                        </td>
                    </tr>
                </table>

            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" value="同&#12288;意" onclick="option('Passed');"/>
                <input type="button" class="search" value="退&#12288;回" onclick="option('Backed');"/>
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