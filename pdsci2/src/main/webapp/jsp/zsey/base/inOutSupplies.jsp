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
            $("input[name='deptName']").val($("select[name='deptFlow'] option:selected").text());
            jboxPost("<s:url value='/zsey/base/saveBatchSupplies'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;">耗材名称：</td>
                    <td>${suppliesName}
                        <input type="hidden" name="suppliesFlow" value="${param.suppliesFlow}">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>一次性量：</td>
                    <td>
                        <c:if test="${param.flag eq 'in'}"><input type="hidden" name="batchType" value="IN"/></c:if>
                        <c:if test="${param.flag eq 'out'}"><input type="hidden" name="batchType" value="OUT"/></c:if>
                        <input type="text" class="validate[required,custom[integer]]" name="oneNumber"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>可重复量：</td>
                    <td>
                        <input type="text" class="validate[required,custom[integer]]" name="repeatNumber"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">维护日期：</td>
                    <td>${pdfn:getCurrDate()}
                        <input type="hidden" name="batchDate" value="${pdfn:getCurrDate()}"/>
                    </td>
                </tr>
                <c:if test="${param.flag eq 'out'}">
                    <tr>
                        <td style="text-align:right;"><span style="color:red;">*&nbsp;</span>使用部门：</td>
                        <td>
                            <select name="deptFlow" style="width:156px;" class="validate[required] select">
                                <option/>
                                <c:forEach items="${deptList}" var="dept">
                                    <option value="${dept.deptFlow}">${dept.deptName}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" name="deptName">
                        </td>
                    </tr>
                </c:if>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>