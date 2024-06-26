<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            var materialFlowList = [];
            var materialNameList = [];
            var content = "";
            var number = $(":checkbox[name='materialFlow']:checked").length;
//            if(number == 0){
//                jboxTip("设备耗材不能为空！");
//                return;
//            }
            $(":checkbox[name='materialFlow']:checked").each(function(i){
                materialFlowList.push(this.value);
                materialNameList.push($(this).attr("materialName"));
                content += $(this).attr("materialName")+"&nbsp;<input type='text' name='materialNum' placeholder='填写所需数量' value='"+$(this).attr("materialNum")+"' style='width:80px;border-top:0px;border-left:0px;border-right:0px;border-bottom:1px solid gray;'>" +
                        "<img style='cursor:pointer;' src=\"<s:url value='/jsp/inx/lcjn/images/reduce.png'/>\" onclick=\"delMaterial(this,'"+this.value+"');\" title='删除' />";
                if(i != number-1){
                    content += "</div></td>";
                    if((i+1) % 2 == 0){
                        content +="</tr><tr>"
                    }
                    content += "<td style='border:0px;padding:0px;width:50%;'><div style='white-space:nowrap;width:96%;text-align:right'>";
                }
            });
            var html = "<table width=85%><tr><td style='border:0px;padding:0px;width:50%;'><div style='white-space:nowrap;width:96%;text-align:right'>"+content+"</div></td></tr></table><img style='cursor:pointer;float:right;margin:-26px 20px 0px 0px;' src=\"<s:url value='/jsp/inx/lcjn/images/add.png'/>\" onclick='addMaterial()' title='添加'>";
                html += "<span id='tip' style='color:grey;padding-top:2px;'></span><input type='hidden' id='recordFlowList' name='recordFlowList'/><input type='hidden' id='materialFlowList' name='materialFlowList' value='"+materialFlowList+"'/><input type='hidden' id='materialNameList' name='materialNameList' value='"+materialNameList+"'/>";
            window.parent.frames['jbox-iframe'].$('#materialTD').html(html);
            if(number == 1){
                window.parent.frames['jbox-iframe'].$("#materialTD div").css("text-align","left");
            }else{
                window.parent.frames['jbox-iframe'].$("#materialTD div").css("text-align","right");
            }
            jboxCloseMessager();
        }
        function toPage(){
            $("#searchForm").submit();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/zsey/dept/selectMaterial"/>" method="post">
            <input type="hidden" name="appointFlow" value="${param.appointFlow}"/>
            <div style="width:100%;">
                <span style=""></span>
                <input type="text" name="materialName" style="width:133px;" placeholder="输入名称检索" value="${param.materialName}">
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage()"/>
            </div>
        </form>
        <div style="border:1px solid lightgrey;width:100%;height:auto;margin-top:10px;">
            <table class="xllist" style="width:99%;margin:0px 0px 0px 4px;border:0;">
                <tr>
                    <c:forEach items="${materialList}" var="mat" varStatus="i">
                        <td style="text-align:left;border:0;width:20%;white-space:nowrap;"><input type="checkbox" name="materialFlow" materialName="${mat.materialName}" materialNum="${materialMap[mat.materialFlow]}" value="${mat.materialFlow}" ${fn:contains(exitMaterialLst,mat.materialFlow)?'checked':''}>&nbsp;${mat.materialName}</td>
                        <c:if test="${i.count % 5 eq 0}"></tr><tr></c:if>
                    </c:forEach>
                    <c:if test="${fn:length(materialList) eq 0}"><th style="border:0;text-align:center;">无记录！</th></c:if>
                </tr>
            </table>
        </div>
        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" onclick="save();" value="确&#12288;认"/>
            <input type="button" class="search" onclick="jboxCloseMessager();" value="取&#12288;消"/>
        </div>
    </div>
</div>
</body>
</html>