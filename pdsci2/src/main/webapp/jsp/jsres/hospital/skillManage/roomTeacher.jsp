<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            var checkLen = $(":checkbox[name='teaFlow']:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选考官确认！");
                return;
            }
            var teaFlowLst = [];
            var teaNameLst = [];
            var teaNameHtml = "&#12288;";
            var teaflows="";
            $(":checkbox[name='teaFlow']:checked").each(function(i){
                teaFlowLst.push(this.value);
                teaNameLst.push($(this).attr("teaName"));
                teaNameHtml += $(this).attr("teaName")+"&nbsp;";
                if(i!=0) {
                    teaflows+=",";
                }
                teaflows+=this.value;
            });
            var json = {"stationFlow":'${param.stationFlow}',"stationName":'${stationName}',"teaFlowLst": teaFlowLst, "teaNameLst": teaNameLst};
            var html = "<div style='line-height:20px;margin:2px 0px;' stationFlow='${param.stationFlow}'></div>";
            var stationDivBody="${stationName}"+teaNameHtml+"<input type='hidden' name='jsonData2' value='"+JSON.stringify(json)+"'/>" +
                    "<input type='hidden' name='teaFlowInput' value='"+teaflows+"'/>";
            debugger
            var stationDiv= window.parent.frames['jbox-message-iframe'].$('#teaTD').find("div[stationFlow='${param.stationFlow}']");
            if(stationDiv == null || stationDiv == undefined || stationDiv == ''||stationDiv.length==0) {
                stationDiv=$(html);
                $(stationDiv).html(stationDivBody);
                $(window.parent.frames['jbox-message-iframe'].$('#teaTD')).append($(stationDiv));
            }else{
                window.parent.frames['jbox-message-iframe'].$('#teaTD').find("div[stationFlow='${param.stationFlow}']").html(stationDivBody);
            }
            window.parent.frames['jbox-message-iframe'].$(":checkbox[name='staCheck']:checked").each(function(){
                if($(this).val() == '${param.stationFlow}'){
                    $(this).attr("disabled",true);
                }
            });
            jboxClose();
        }
        //模糊查询
        function likeSearch(name){
            if(name){
                $("[teaNameTr]").hide();
                $("[teaNameTr*='"+name+"']").show();
            }else{
                $("[teaNameTr]").show();
            }
        }

    </script>
</head>
<body>
<div class="mainright" style="max-height: 300px;overflow: auto">
        <form id="myForm" action="<s:url value="/jsres/skillTimeConfig/roomTeacher"/>" method="post">
            <input type="hidden" name="stationFlow" value="${param.stationFlow}">
            <input type="hidden" name="stationName" value="${param.stationName}">
            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 30px;">
                <tr>
                    <td colspan="2"><input type="text" class="input" name="userName" value="${param.userName}" placeholder="可通过关键字检索" onkeyup="likeSearch(this.value);">&#12288;
                        <%--<input type="submit" class="search" value="查询">--%>
                    </td>
                </tr>
                <c:forEach items="${teaList}" var="tea">
                    <tr teaNameTr="${tea.userName}">
                        <td><input type="checkbox" name="teaFlow" value="${tea.userFlow}" teaName="${tea.userName}" ${fn:contains(exitTeaLst,tea.userFlow)?'checked':''}>&nbsp;${tea.userName}</td>
                        <td>${tea.userCode}</td>
                    </tr>
                </c:forEach>
            </table>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="btn_green" onclick="save();" value="确&#12288;认"/>
                <%--<input type="button" class="btn_green" onclick="jboxClose();" value="取&#12288;消"/>--%>
            </div>
        </form>
</div>
</body>
</html>