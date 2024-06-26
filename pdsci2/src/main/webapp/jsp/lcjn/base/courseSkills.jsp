<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            var skillFlowList = [];
            var skillNameList = [];
            var content = "";
            var number =  $(":checkbox[name='skillFlow']:checked").length;
            if(number == 0){
                jboxTip("所需技能不能为空！");
                return;
            }
            $(":checkbox[name='skillFlow']:checked").each(function(i){
                skillFlowList.push(this.value);
                skillNameList.push($(this).attr("skillName"));
                content += "<span>"+(i+1)+"."+$(this).attr("skillName")+"</span>&#12288;";
            });
            var html = content+"<img style='cursor:pointer;' src=\"<s:url value='/jsp/inx/lcjn/images/add.png'/>\" onclick='addSkill()' title='添加'>";
                html += "<input type='hidden' name='skillFlowList' value='"+skillFlowList+"'/><input type='hidden' name='skillNameList' value='"+skillNameList+"'/>";
            window.parent.frames['jbox-iframe'].$('#skillsTD').html(html);
            jboxCloseMessager();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="basic" style="width:100%;margin:10px 0px;">
            <c:forEach items="${skillsList}" var="skills">
                <tr>
                    <td><input type="checkbox" name="skillFlow" skillName=${skills.skillName} value="${skills.skillFlow}" ${fn:contains(exitSkillsLst,skills.skillFlow)?'checked':''}>&nbsp;${skills.skillName}</td>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(skillsList) eq 0}"><tr><th style="text-align:center;">无记录！</th></tr></c:if>
        </table>
        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" onclick="save();" value="确&#12288;认"/>
            <input type="button" class="search" onclick="jboxCloseMessager();" value="取&#12288;消"/>
        </div>
    </div>
</div>
</body>
</html>