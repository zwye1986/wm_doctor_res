<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function saveHeadAccount(){
            if(false == $("#editForm").validationEngine("validate")){
                return false;
            }
            jboxStartLoading();
            jboxPost("<s:url value='/zseyjxres/hospital/saveHeadAccount'/>",$("#editForm").serialize(),function(resp){
                jboxEndLoading();
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    jboxTip("保存成功");
                    window.parent.headMaintenance();
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
            <input type="hidden" name="userFlow" value="${user.userFlow}">
            <table class="grid" style="margin-top: 20px;">
                <tr>
                    <th style="text-align: right;">姓名：</th>
                    <td style="text-align: left;">
                        <input type="text" style="width:160px;" class="input validate[required]" name="userName" value="${user.userName}"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">登录名：</th>
                    <td style="text-align: left;">
                        <input type="text" style="width:160px;" class="input validate[required]" name="userCode"  value="${user.userCode}"/>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: right;">所属科室：</th>
                    <td style="text-align: left;">
                        <select name="deptFlow" style="width:166px;margin-left: 5px" class="select validate[required]">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dept">
                                <option value="${dept.dictId}" <c:if test="${dept.dictId eq user.deptFlow}">selected</c:if> >${dept.dictName}</option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>

            </table>
        </form>
    </div>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input type="button" class="btn_green" style="width:100px;" onclick="saveHeadAccount();" value="保&#12288;存" />
        <input type="button" class="btn_green" style="width:100px;" onclick="jboxClose();" value="关&#12288;闭"/>
    </div>
</body>
</html>