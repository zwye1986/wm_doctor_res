<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <style type="text/css">
        .checkedClass {
            color: #00a1e5;
        }
    </style>
    <script type="text/javascript">


        function serializeList(name, list) {
            var result = "";
            for (var index in list) {
                if (result) {
                    result += ("&" + name + "=" + list[index]);
                } else {
                    result += (name + "=" + list[index]);
                }
            }
            return result;
        }
        //模糊查询
        function likeSearch(name) {
            if (name) {
                $("[userName]").hide();
                $("[userName*='" + name + "']").show();
            } else {
                $("[userName]").show();
            }
        }

        function addSelectedDoctors() {
            var datas = [];
            var users = $("#selectDiv").children();
            var courseFlow='${courseFlow}';
            var courseName='${courseName}';
            var id = document.getElementsByName('jointUserFlows');
            var checkedNum=0;
            for(var i = 0; i < id.length; i++){
                if(id[i].checked) {
                    var userFlow = id[i].value;
                    var userName = id[i].getAttribute("userName");
                    var userCode = id[i].getAttribute("userCode");
                    var data = {//json对象   {key:value}
                        "userFlow": userFlow,
                        "userName": encodeURI(encodeURI(userName)),
                        "userCode": userCode
                    };
                    datas.push(data);
                    checkedNum+=1;
                }
            }
            if(checkedNum==0){
                jboxTip("请勾选学员！");
                return;
            }
            if(datas && datas != ''){
                var json = {"datas":datas,"courseFlow":courseFlow,"courseName":encodeURI(encodeURI(courseName))};
                var jsonData=JSON.stringify(json);
                var url = "<s:url value="/lcjn/lcjnDoctorTrainInfo/checkOrderNum"/>";
                jboxPost(url, "jsonData="+jsonData, function (resp) {
                    if("N"== resp){
                        jboxTip("所选学员数量超过本课程预约剩余人数，请重新选择!");
                        return;
                    }
                    if("Y"== resp){
//                        doAddDoctor(jsonData);
                        doAddDoctor(checkedNum);
                    }
                }, null, false);
            }
        }

        function doAddDoctor(checkedNum){
            $("#docInfoDiv1").hide();
            $("#resoultInfoDiv1").show();
            var courseFlow='${courseFlow}';
            var courseName='${courseName}';
            courseName=encodeURI(encodeURI(courseName));
            var id = document.getElementsByName('jointUserFlows');
            var countResoult=0;
            for(var i = 0; i < id.length; i++){
                if(id[i].checked) {
                    var userFlow = id[i].value;
                    var userName = id[i].getAttribute("userName1");
                    userName=encodeURI(encodeURI(userName));
                    var userCode = id[i].getAttribute("userCode1");
                    var url="<s:url value='/lcjn/lcjnDoctorTrainInfo/addLocalDoctors'/>?userFlow="+userFlow+"&userName="+userName+"&userCode="+userCode+"&courseFlow="+courseFlow+"&courseName="+courseName;
                    jboxPost(url, null, function (resp) {
                        $("#resoultMsgDiv").append(resp);
                        countResoult+=1;
                        addResoultMsg(countResoult,checkedNum);
                    }, null, false);
                }
            }
        }

        function addResoultMsg(countResoult,checkedNum){
            if(countResoult==checkedNum){
                $("#resoultMsgDiv").append("<label style='font-size:16px; '>导入完成！<br/></label>");
            }
        }
        <%--function doAddDoctor(jsonData){--%>
            <%--var url="<s:url value='/lcjn/lcjnDoctorTrainInfo/addLocalDoctors'/>?jsonData="+jsonData;--%>
<%--//            jboxPostLoad("docInfoDiv1",url1,null,true);--%>
<%--//            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url1+"'></iframe>";--%>
<%--//            jboxMessager(iframe,null,400,410,false);--%>
            <%--var d = top.dialog({--%>
                <%--id:"artTip",--%>
                <%--padding:5,--%>
                <%--content:"导入中···",--%>
                <%--backdropOpacity: 0.1--%>
            <%--});--%>
            <%--d.show();--%>
            <%--$("input").attr("disabled","disabled");--%>
            <%--jboxPost(url, null, function (resp) {--%>
                <%--$("input").removeAttr("disabled");--%>
                <%--d.close().remove();--%>
                <%--jboxConfirm(resp,function(){--%>
                    <%--window.parent.frames['mainIframe'].window.toPage1(1);--%>
                    <%--top.jboxClose();--%>
                <%--},function(){--%>
                    <%--window.parent.frames['mainIframe'].window.toPage1(1);--%>
                    <%--top.jboxClose();--%>
                <%--});--%>
            <%--}, null, false);--%>
        <%--}--%>

        function checkedDiv(obj) {
            if ($(obj).find("input[type='checkbox']").is(':checked')) {
                $(obj).find("input[type='checkbox']").add("checked");
                $(obj).addClass("checkedClass");
            } else {
                $(obj).find("input[type='checkbox']").remove("checked");
                $(obj).removeClass("checkedClass");
            }
        }

        function closeThis(){
            window.parent.frames['mainIframe'].window.toPage1(1);
            jboxClose();
        }
    </script>
</head>
<body>
<div class="">
    <div class="" id="docInfoDiv1">
        <div style="width: 100%;height: 40px">
            <span>查找：</span>
            <input placeholder="按专业、用户名、姓名查找" type="text" value="" style="width: 60%" onkeyup="likeSearch(this.value);"/><br/>
            <font style="color: red">*建议单次导入学员不超过10位</font>
        </div>
        <div>
            <div style="width: 98%;border: 0px solid #336544; overflow:auto;height: 280px" id="selectDiv">
                    <%--<div onclick="checkedDiv(this);"--%>
                         <%--userName="${user.LCJN_SPE_NAME}${user.USER_NAME}${user.USER_CODE}">--%>
                        <table class="xllist" style="width:100%;">
                            <tr>
                                <th style="width: 15%;">序号</th>
                                <th style="width: 45%;">用户名</th>
                                <th style="width: 40%;">姓名</th>
                            </tr>
                <c:forEach items="${localDoctors}" var="user" varStatus="i">
                            <tr onclick="checkedDiv(this);"
                                userName="${user.LCJN_SPE_NAME}${user.USER_NAME}${user.USER_CODE}">
                                <td style="width: 10%;">
                                    <input type="checkbox" readonly="readonly" name="jointUserFlows" value="${user.USER_FLOW}" userName1="${user.USER_NAME}" userCode1="${user.USER_CODE}"/>
                                        ${i.index + 1}
                                </td>
                                <td style="width: 45%;">
                                    ${user.USER_CODE}
                                </td>
                                <td style="width: 45%;">
                                    ${user.USER_NAME}
                                </td>
                            </tr>
                </c:forEach>
                        </table>
                        <%--</label>--%>
                        <div style="display: none">
                            <div id="${user.USER_FLOW}" style="width: 100%;">
                                <input type="hidden" name="userFlow" value="${user.USER_FLOW}"/>
                                <input type="hidden" name="userName" value="${user.USER_NAME}"/>
                                <input type="hidden" name="userCode" value="${user.USER_CODE}"/>
                                <span style="color: #00a1e5;">${user.USER_NAME}</span>
                            </div>
                        </div>
                    </div>
        </div>
        <div style="text-align: center;margin-top:20px;">
            <input type="button" class="search" onclick="addSelectedDoctors()" value="确&#12288;定"/>&#12288;&#12288;
            <input type="button" class="search" onclick="jboxClose()" value="取&#12288;消"/>
        </div>
    </div>
    <div class="content" id="resoultInfoDiv1" style="display:none;">
        <div id="resoultMsgDiv" style="margin-top:25px;margin-left:10px;height: 315px;overflow: auto">
            <label style="font-size:16px; ">导入中....<br/></label>
        </div>
        <div style="text-align: center;margin-bottom:10px;">
            <input class="search" onclick="closeThis()" value="确&#12288;定" type="button">
        </div>
    </div>
</div>
</body>
</html>