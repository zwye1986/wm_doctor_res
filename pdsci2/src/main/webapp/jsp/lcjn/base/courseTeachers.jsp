<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            var userFlowList = [];
            var userNameList = [];
            var content = "";
            var number = $(":checkbox[name='userFlow']:checked").length;
            if(number == 0){
                jboxTip("任课老师不能为空！");
                return;
            }
            $(":checkbox[name='userFlow']:checked").each(function(i){
                userFlowList.push(this.value);
                userNameList.push($(this).attr("userName"));
                content += $(this).attr("userName")+"&nbsp;<input type='text' name='courseFee' placeholder='填写课程费用' value='"+$(this).attr("teachingCost")+"' style='width:80px;border-top:0px;border-left:0px;border-right:0px;border-bottom:1px solid gray;'>" +
                        "<img style='cursor:pointer;' src=\"<s:url value='/jsp/inx/lcjn/images/reduce.png'/>\" onclick=\"delTeacher(this,'"+this.value+"');\" title='删除' />";
                if(i != number-1){
                    content += "</td>";
                    if((i+1) % 2 == 0){
                        content +="</tr><tr>"
                    }
                    content += "<td style='border:0px;padding:0px;'>";
                }
            });
            var html = "<table width=70%><tr><td style='border:0px;padding:0px;'>"+content+"</td></tr></table><img style='cursor:pointer;float:right;margin:-26px 147px 0px 0px;' src=\"<s:url value='/jsp/inx/lcjn/images/add.png'/>\" onclick='addTeacher()' title='添加'>";
                html += "<input type='hidden' id='userFlowList' name='userFlowList' value='"+userFlowList+"'/><input type='hidden' id='userNameList' name='userNameList' value='"+userNameList+"'/>";
            window.parent.frames['jbox-iframe'].$('#userTD').html(html);
            jboxCloseMessager();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <table class="xllist" style="width:100%;margin:10px 0px;">
            <tr>
                <th>工号</th>
                <th>姓名</th>
            </tr>
            <c:forEach items="${teachersList}" var="tea">
                <tr>
                    <td><input type="checkbox" name="userFlow" userName="${tea.userName}" teachingCost="${teaMap[tea.userFlow]}" value="${tea.userFlow}" ${fn:contains(exitTeachersLst,tea.userFlow)?'checked':''}>&nbsp;${tea.userCode}</td>
                    <td>${tea.userName}</td>
                </tr>
            </c:forEach>
            <c:if test="${fn:length(teachersList) eq 0}"><tr><th colspan="2" style="text-align:center;">无记录！</th></tr></c:if>
        </table>
        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" onclick="save();" value="确&#12288;认"/>
            <input type="button" class="search" onclick="jboxCloseMessager();" value="取&#12288;消"/>
        </div>
    </div>
</div>
</body>
</html>