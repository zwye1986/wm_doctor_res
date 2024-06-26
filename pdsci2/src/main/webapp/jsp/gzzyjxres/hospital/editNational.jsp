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

        function saveNational(){
            if(false == $("#editForm").validationEngine("validate")){
                return false;
            }
            jboxStartLoading();
            jboxPost("<s:url value='/gzzyjxres/hospital/saveNational'/>",$("#editForm").serialize(),function(resp){
                jboxEndLoading();
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    jboxTip("保存成功");
                    window.parent.nationalityMaintenance();
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
                    <th style="text-align: right;">国籍编码：</th>
                    <td style="text-align: left;"><input type="text" style="width:160px;" class="input validate[required]" name="dictId" <c:if test="${not empty dictNational.dictFlow }">readonly</c:if> value="${dictNational.dictId}" /></td>
                    <input type="hidden" name="dictFlow" value="${dictNational.dictFlow}" />
                </tr>
                <tr>
                    <th style="text-align: right;">国籍名称：</th>
                    <td style="text-align: left;">
                        <input type="text" class="input validate[required]" name="dictName" value="${dictNational.dictName}" />
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">国籍英文名称：</th>
                    <td style="text-align: left;">
                        <input type="text" class="input validate[required]" name="dictDesc" value="${dictNational.dictDesc}" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input type="button" class="btn_green" style="width:100px;" onclick="saveNational();" value="保&#12288;存" />
        <input type="button" class="btn_green" style="width:100px;" onclick="jboxClose();" value="关&#12288;闭"/>
    </div>
</body>
</html>