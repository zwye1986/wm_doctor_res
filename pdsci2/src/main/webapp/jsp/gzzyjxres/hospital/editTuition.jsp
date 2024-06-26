<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/gzzyjxres/htmlhead-gzzyjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        function saveTuition(){
            if(false == $("#editForm").validationEngine("validate")){
                return false;
            }
            jboxStartLoading();
            jboxPost("<s:url value='/gzzyjxres/hospital/saveTuition'/>",$("#editForm").serialize(),function(resp){
                jboxEndLoading();
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    jboxTip("保存成功");
                    window.parent.tuitionMaintenance();
                    setTimeout(function(){
                        jboxClose();
                    },2000)
                }else{
                    jboxTip(resp);
                }
            },null,false);
        }
    </script>
</head>
<body>
    <div class="div_table">
        <form id="editForm">
            <table class="grid" style="margin-top: 20px;">
                <input type="hidden" name="recordFlow" value="${tuition.recordFlow}" />
                <tr>
                    <th style="text-align: right;">费用项：</th>
                    <td style="text-align: left;">
                        <%--<input type="text" class="input validate[required]" name="costCategory" value="${tuition.costCategory}"/>--%>
                        <select  class="select validate[required]" name="costCategory" value="${tuition.costCategory}">
                            <option value="">请选择</option>
                            <c:forEach items="${tuitionCategoryEnumList}" var="category">
                                <option value="${category.name}" ${category.name eq tuition.costCategory?'selected':''}>${category.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">费用：</th>
                    <td style="text-align: left;">
                        <input type="text" style="width: 172px;margin-left: 0px;" class="input validate[required]" name="costValue" value="${tuition.costValue}"/>元
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input type="button" class="btn_green" style="width:100px;" onclick="saveTuition();" value="保&#12288;存" />
        <input type="button" class="btn_green" style="width:100px;" onclick="jboxClose();" value="关&#12288;闭"/>
    </div>
</body>
</html>