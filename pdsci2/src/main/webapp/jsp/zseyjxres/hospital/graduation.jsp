<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function saveGraduation(){
            if(false == $("#graduationForm").validationEngine("validate")){
                return false;
            }
            var url="<s:url value='/zseyjxres/hospital/saveGraduation'/>";
            jboxSubmit($("#graduationForm"),url,function(resp){
                if("${GlobalConstant.SAVE_SUCCESSED}"== resp){
                    window.parent.searchDoctor();
                    setTimeout(function(){
                        jboxClose();
                    },2000)
                }
            }, null, true);
        }

    </script>
</head>
<body>
    <div class="div_table">
        <form id="graduationForm" enctype="multipart/form-data">
            <table class="grid" style="width: 100%;margin-top:20px;">
                <tr>
                    <th style="width:20%;">结业评语：</th>
                    <td style="text-align: left;">
                        <textarea style="width:80%;height:150px" name="comment" class="validate[required]">${comment}</textarea>
                        <input type="hidden" name="resumeFlow" value="${resumeFlow}" />
                    </td>
                </tr>
                <tr>
                    <th>是否延期结业</th>
                    <td style="text-align: left;">
                        <%--<c:if test="${stuUser.stuStatusId eq stuStatusEnumDelayGraduation.id}">--%>
                            <%--<input type="radio" value="Y" class="validate[required]"  name="agree" <c:if test="${agree eq 'Y'}">checked="checked"</c:if> />同意结业--%>
                        <%--</c:if>--%>
                        <c:if test="${stuUser.stuStatusId eq stuStatusEnumAdmited.id}">
                            <%--<input type="radio" value="Y" class="validate[required]"  name="agree" <c:if test="${agree eq 'Y'}">checked="checked"</c:if> />同意结业--%>
                            <input type="radio" value="N" class="validate[required]"  name="agree" <c:if test="${agree eq 'N'}">checked="checked"</c:if> />延期结业
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <th>附件</th>
                    <td style="text-align: left;">
                        <input type="file" id="file" name="file" class=""  contenteditable="false"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="text-align: center">
        <input type="button" value="保&#12288;存" class="btn_green" style="width:100px;" onclick="saveGraduation()" />
        <input type="button" value="关&#12288;闭" class="btn_green" style="width:100px;" onclick="jboxClose();" />
    </div>
</body>
</html>