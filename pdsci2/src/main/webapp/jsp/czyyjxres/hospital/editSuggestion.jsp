<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/czyyjxres/htmlhead-czyyjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">

        function saveSuggestion(){
            if(false == $("#editForm").validationEngine("validate")){
                return false;
            }
            jboxStartLoading();
            jboxPost("<s:url value='/czyyjxres/hospital/saveSuggestion'/>",$("#editForm").serialize(),function(resp){
                jboxEndLoading();
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    jboxTip("保存成功");
                    window.parent.suggestionMaintenance();
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
                <tr>
                    <th style="text-align: right;width: 15%">审核意见编码：</th>
                    <td style="text-align: left;width: 85%"><input type="text" style="width:160px;" class="input validate[required]" name="dictId" <c:if test="${not empty dictSuggestion.dictFlow }">readonly</c:if> value="${dictSuggestion.dictId}" /></td>
                    <input type="hidden" name="dictFlow" value="${dictSuggestion.dictFlow}" />
                </tr>
                <tr>
                    <th style="text-align: right;">审核意见：</th>
                    <td style="text-align: left;">
                        <textarea class="validate[required]" name="dictName">${dictSuggestion.dictName}</textarea>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">审核意见英文：</th>
                    <td style="text-align: left;">
                        <textarea  class="validate[required]" name="dictDesc">${dictSuggestion.dictDesc}</textarea>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input type="button" class="btn_green" style="width:100px;" onclick="saveSuggestion();" value="保&#12288;存" />
        <input type="button" class="btn_green" style="width:100px;" onclick="jboxClose();" value="关&#12288;闭"/>
    </div>
</body>
</html>