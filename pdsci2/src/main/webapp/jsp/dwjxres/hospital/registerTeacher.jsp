<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/dwjxres/htmlhead-dwjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function saveTeacher(){
            if(false == $("#reisterForm").validationEngine("validate")){
                return false;
            }
            jboxPost("<s:url value='/dwjxres/hospital/saveTeacher'/>",{"resumeFlow":"${resumeFlow}","teacherFlow":$("#teacherFlow").val()},function(resp){
                if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                    window.parent.searchDoctor();
                    setTimeout(function(){
                        jboxClose();
                    },2000);
                }
            },null,true);
        }
    </script>
</head>
<body>
    <div class="div_table">
        <form id="reisterForm">
            <table class="grid" style="margin-top: 20px;">
                <tr>
                    <td style="text-align: center;width: 50%;">进修专业：
                        ${speName}
                    </td>
                    <td style="text-align: center;width: 50%;">带教老师：
                        <select name="teacherFlow" id="teacherFlow" style="width: 150px;"class="select validate[required]">
                            <option value=""></option>
                            <c:forEach items="${teas}" var="tea">
                                <option value="${tea.userFlow}" <c:if test="${teacherFlow eq tea.userFlow or(tea.userName eq teacherName)}">selected</c:if>>${tea.userName}</option>
                            </c:forEach>
                        </select>
                        <c:if test="${empty teas}">
                            <label class="red">该科室无带教老师！</label>
                        </c:if>
                        <%--<input type="text" id="teacherName" class="input validate[required]" value="${teacherName}" />--%>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input class="btn_green" style="width:60px;" onclick="saveTeacher();" value="保&#12288;存" />
        <input class="btn_green" style="width:60px;" onclick="jboxClose();" value="取&#12288;消" />
    </div>
</body>
</html>