<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            jboxPost("<s:url value='/xjgl/scholarship/batchAuditOpt'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.OPRE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            },true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="role" value="${role}">
            <input type="hidden" name="remarkFlag" value="${remarkFlag}">
            <input type="hidden" name="auditFlag" value="${auditFlag}">
            <input type="hidden" name="recordLst" value="${recordLst}">
            <c:if test="${remarkFlag eq 'Y'}">
                <table class="basic" style="width: 100%;">
                    <tr>
                        <th style="width:35%">申请奖助类型：</th>
                        <td style="width:65%">学业奖学金</td>
                    </tr>
                    <tr>
                        <th>申报等级：</th>
                        <td><select name="applyLevel" style="width:120px;" class="validate[required] select">
                            <option value="一等奖" <c:if test="${main.applyLevel eq '一等奖'}">selected="selected"</c:if>>一等奖</option>
                            <option value="二等奖" <c:if test="${main.applyLevel eq '二等奖'}">selected="selected"</c:if>>二等奖</option>
                            <option value="新生奖学金" <c:if test="${main.applyLevel eq '新生奖学金'}">selected="selected"</c:if>>新生奖学金</option>
                        </select></td>
                    </tr>
                    <tr>
                        <th>公示工作日：</th>
                        <td><input type="text" name="workDay" class="validate[required,custom[integer]]" style="width:60px" value="${main.workDay}">个工作日</td>
                    </tr>
                    <tr>
                        <td colspan="2"><textarea name="advice" style="width:97%;height:80px;" placeholder="审核理由" class="validate[required]"></textarea></td>
                    </tr>
                </table>
                <div style="text-align: center;margin-top:10px;">
                    <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                    <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
                </div>
            </c:if>
            <c:if test="${remarkFlag eq 'N'}">
                <c:if test="${role eq 'ds'}">
                    <input type="hidden" name="docFlagLst" value="${docFlagLst}">
                </c:if>
                <div><textarea name="advice" style="width:100%;height:140px;" class="validate[required]"></textarea></div>
                <div style="text-align: center;margin-top:20px;">
                    <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                    <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
                </div>
            </c:if>
        </form>
    </div>
</div>
</body>
</html>