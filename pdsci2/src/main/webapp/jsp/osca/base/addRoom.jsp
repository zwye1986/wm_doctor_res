<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(function(){
            $(":checkbox[name='staCheck']:checked").each(function(){
                var stationFlow = $(this).val();
                var stationName = $(this).attr("stationName");
                $(this).next(".station").css("color","blue");
                $(this).next(".station").bind("click", function () {
                    selectTea(stationFlow,stationName)
                });
                $(this).next(".station").bind("hover", function () {
                    $(this).attr("title","请点击进入");
                });
            })
        });
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var checkLen = $(":checkbox[name='staCheck']:checked").length;
            if(checkLen == 0){
                jboxTip("请勾选考核站点！");
                return;
            }
            var roomText = $("#roomFlow option:selected").text();
            $("#roomName").val(roomText);
            $("#backRoomFlow").val($("#roomFlow option:selected").val());
            var stationFlowLst = [];
            var stationNameLst = [];
            var jsonArry = [];
            $(":checkbox[name='staCheck']:checked").each(function(){
                stationFlowLst.push(this.value);
                stationNameLst.push($(this).attr("stationName"));
            });
            $("input[name='jsonData2']").each(function(){
                jsonArry.push($(this).val());
            });
            var json = {"stationFlowLst": stationFlowLst, "stationNameLst": stationNameLst};
            var url = "<s:url value='/osca/base/saveRoom?jsonData='/>"+encodeURI(encodeURI(JSON.stringify(json)))+"&jsonDataDetail="+encodeURI(encodeURI(JSON.stringify(jsonArry)));
            jboxPost(url, $("#myForm").serialize(), function (resp) {//form提交，jsonData2已存在，切记重复传值
                setTimeout(function(){
                    window.parent.frames['mainIframe'].searchRoom();
                    jboxClose();
                },2000);
            }, null,true);
        }
        function selectTea(stationFlow,stationName){//房间流水号recordFlow，用来判定编辑则清空$("#teaTD")内容
            var exitTeaLst = [];
            <c:forEach items="${teaList}" var="tea">
                exitTeaLst.push('${tea.partnerFlow}');
            </c:forEach>
            var teaFlows=$("div[stationFlow='"+stationFlow+"']").find("input[name='teaFlowInput']").val();
            if(teaFlows)
            {
                var s=teaFlows.split(",");
                for(var i=0;i<s.length;i++)
                {
                    if(s[i])
                        exitTeaLst.push(s[i]);
                }
            }
            var url ="<s:url value='/osca/base/roomTeacher?recordFlow=${param.recordFlow}&stationFlow='/>"+stationFlow+"&stationName="+encodeURI(encodeURI(stationName))+"&exitTeaLst="+exitTeaLst;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		    jboxMessager(iframe,null,300,400,false);
        }
        function bindSelectTea(obj){
            if($(obj).attr("checked")){
                var stationFlow = $(obj).val();
                var stationName = $(obj).attr("stationName");
                $(obj).next(".station").bind("click", function () {
                    selectTea(stationFlow,stationName);
                });
                $(obj).next(".station").bind("hover", function () {
                    $(this).attr("title","请点击进入");
                });
                $(obj).next(".station").css("color","blue");
            }else{
                $(obj).next(".station").unbind("click");
                $(obj).next(".station").css("color","black");
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="recordFlow" value="${osr.recordFlow}">
            <input type="hidden" name="clinicalFlow" value="${clinicalFlow}">
            <input type="hidden" name="clinicalName" value="${clinicalName}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;width:30%;">房间名称：</td>
                    <td><select id="roomFlow" name="roomFlow" style="width:137px;" class="validate[required] select" ${empty param.recordFlow?"":"disabled='disabled'"}>
                            <option/>
                            <c:forEach items="${examRoomList}" var="dict">
                                <option value="${dict.dictId}" ${osr.roomFlow eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="roomName" name="roomName" value="${osr.roomName}">
                        <input type="hidden" id="backRoomFlow" name="backRoomFlow" value="${osr.roomFlow}">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;line-height:18px;">房间考核站点及考官选择：</td>
                    <td>
                        <c:forEach items="${stationList}" var="sta">
                            <input type="checkbox" name="staCheck" value="${sta.stationFlow}" stationName="${sta.stationName}" onchange="bindSelectTea(this);" ${empty param.recordFlow?"":"disabled='disabled'"} ${osr.stationFlow eq sta.stationFlow?'checked':''}>&nbsp;<span style="cursor:pointer;" class="station">${sta.stationName}</span><span style="padding-left:20px;"></span>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">考官：</td>
                    <td id="teaTD">
                        <c:if test="${fn:length(teaList) gt 0}">
                            <div  style='line-height:20px;margin:2px 0px;' stationFlow='${teaList[0].stationFlow}'>
                                ${teaList[0].stationName}&#12288;
                                <c:set var="teaFlows" value=""></c:set>
                                <c:forEach items="${teaList}" var="tea">
                                     ${tea.partnerName}&nbsp;
                                    <c:set var="teaFlows" value="${teaFlows},${tea.partnerFlow}"></c:set>
                                </c:forEach>
                                <input type='hidden' name='teaFlowInput' value='${teaFlows}'/>
                            </div>

                        </c:if>
                    </td>
                </tr>
            </table>
            <label style="color: blue">可点击站点名称，进行考官添加。</label>
            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="search" onclick="jboxClose();" value="取&#12288;消"/>
            </div>
        </form>
    </div>
</div>
</body>
</html>