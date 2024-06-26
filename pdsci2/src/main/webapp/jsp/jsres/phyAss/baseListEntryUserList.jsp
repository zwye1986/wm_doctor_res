<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            searchSpeNum();
        });

        function searchSpeNum() {
            $('#peopleNum').html("");
            var planFlow = $('#planFlow').val()
            $.ajax({
                url: "<s:url value='/jsres/phyAss/searchSpeNum'/>",
                type: "get",
                data: {"planFlow": planFlow},
                dataType: "json",
                success: function (res) {
                 var parse = JSON.parse(res);
                    for(var i in parse){
                        var speName = parse[i].speName;
                        var speNum = parse[i].speNum;
                        var html=speName+"："+speNum+"&#12288;人 &#12288; &#12288;";
                        $("#peopleNum").append(html);
                    }
                }
            });
        }

        function saveOrgSpeManage(obj,msg,type) {
            if (type=='Y'){
                jboxTip("该人员已上报，无法修改！");
                return;
            }
            var userFlow2 = $(obj).val();
            var userName = $("#" + userFlow2 + "_userName").val();
            var checkedVal = $(obj).attr("checked");
            if ("checked" == checkedVal) {
                var html = '<input hidden id="add' + userFlow2 + '" name="addUserFlow" value="' + msg + '"></input>';
                $("#addDiv").append(html);
                $("#speTable").append("<tr id=" + userFlow2 + " >"
                    + '<td>'
                    + '<input hidden id="' + userFlow2 + '" name="userFlow" value="' + userFlow2 + '">'
                    + '<input hidden id="' + userFlow2 + '_userName" name="userName" value="' + userName + '">'
                    + userName + '</td>'
                    + "<td><a href=\'#\' onclick=\"deltr('" + userFlow2 + "')\">删除</a></td>"
                    + "</tr>");

            } else {
                deltr(userFlow2);
            }

        }

        function deltr(value) {
            //删除当前行
            var valueName = $("#" + value + "_userName").val();
            var inputAddSpeList = $("input[name='addUserFlow']");
            if (inputAddSpeList.length > 0) {
                for (var i = 0; i < inputAddSpeList.length; i++) {
                    if (inputAddSpeList[i].id == value) {
                        inputAddSpeList[i].remove();
                    }
                }
            }
            $("tr[id='" + value + "']").remove();

            //已选择,勾选取消
            $("#" + value).removeAttr("checked");

        }

        function saveAdd() {
            var userMsg= [];
            var inputAddSpeList = $("input[name='addUserFlow']");
            for (var i = 0; i < inputAddSpeList.length; i++) {
                var msg = inputAddSpeList[i].value;
                userMsg.push(msg);
            }
            var speId = $("#speId").val();
            var speName = $("#speId").find("option:selected").text();
            var planFlow = $("#planFlow").val();
            $("#dataMsg").val(JSON.stringify(userMsg))
            $("#sendSpeId").val(speId);
            $("#sendSpeName").val(speName);
            $("#sendPlanFlow").val(planFlow);
            jboxPost( "<s:url value='/jsres/phyAss/saveBaseListEntryUser'/>",$('#send').serialize(),function(resp){
                if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
                    searchSpeNum();
                    jboxTip(resp);
                }
            },null,true);
        }

        function closeFlash() {
            window.parent.toPage(1);
            top.jboxClose();
        }

        //模糊查询
        function likeSearch(name) {
            if (name) {
                $("[speName]").hide();
                $("[speName*='" + name + "']").show();
            } else {
                $("[speName]").show();
            }
        }
    </script>

    <div>
        <div id="checkedForm" style="width: 48%;float: left;overflow: auto;max-height: 560px;">
            <form id="send">
                <input type="hidden" id="dataMsg" name="dataMsg">
                <input type="hidden" id="sendSpeId" name="speId">
                <input type="hidden" id="sendSpeName" name="speName">
                <input type="hidden" id="sendPlanFlow" name="planFlow">
            </form>
            <form>
                <table border="0" cellpadding="0" cellspacing="0" class="grid">
                    <tr>
                        <td class="td_left" colspan="3">
                            用户名：<input type="text" name="speName" placeholder="可通过关键字检索"
                                      onkeyup="likeSearch(this.value);" class="input"/>
                        </td>
                    </tr>
                    <tr>
                        <th >
                            <span style="width: 50%"> 登录名</span>
                        </th>
                        <th >
                            <span style="width: 30%"> 用户名</span>
                        </th>
                        <th >
                            <span style="width: 20%"> 角色名</span>
                        </th>
                    </tr>
                    <c:if test="${empty userList}">
                        <tr>
                            <td>暂无人员可选择</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${doctorList}" var="u" varStatus="status">
                        <tr speName="${u.doctorName}">
                            <td style="text-align: left" width="40%">
                                <c:set var="key" value="${u.doctorFlow}"/>
                                &#12288;&#12288;<input type="hidden" id="${u.doctorFlow}_userName" name="userName"
                                                       value="${u.doctorName}"/>
                                <input type="checkbox" id="${key}" checked="checked" name="userFlow" value="${u.doctorFlow}"
                                       onclick="saveOrgSpeManage(this,'${u.doctorFlow}_${u.doctorCode}_${u.doctorName}_${u.doctorRoleFlow}_${u.doctorRoleName}','${u.appearFlag}')"/>&nbsp;${u.doctorCode}
                            </td>
                            <td width="30%">
                                    ${u.doctorName }
                            </td>
                            <td width="30%">
                                带教
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${not empty userList}">
                        <c:forEach items="${userList}" var="u" varStatus="status">
                            <tr speName="${u.userName}">
                                <td style="text-align: left" width="40%">
                                    <c:set var="key" value="${u.userFlow}"/>
                                    &#12288;&#12288;<input type="hidden" id="${u.userFlow}_userName" name="userName"
                                                           value="${u.userName}"/>
                                    <input type="checkbox" id="${key}" name="userFlow" value="${u.userFlow}"
                                           onclick="saveOrgSpeManage(this,'${u.userFlow}_${u.userCode}_${u.userName}_${u.roleFlow}_${u.roleName}','N')"/>&nbsp;${u.userCode }
                                </td>
                                <td width="30%">
                                        ${u.userName }
                                </td>
                                <td width="30%">
                                    ${u.roleName}
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
            </form>
        </div>
        <div id="checkedForm2" style="width: 48%;float: right;overflow: auto;max-height: 530px;margin: 0 0 10px 0px;">
            <table border="0" cellpadding="0" cellspacing="0" class="grid" id="speTable">
                <tr>
                    <th colspan="2">已选择人员：</th>
                </tr>
                <c:forEach items="${doctorList}" var="u" varStatus="status">
                    <tr id="${u.doctorFlow}">
                        <td>
                            <input type="hidden" id="${u.doctorFlow}" name="userFlow" value="${u.doctorFlow}">
                            <input type="hidden" id="${u.doctorFlow}_userName" name="userName" value="${u.doctorName}">${u.doctorName}
                        </td>
                        <td>
                            <a href="#" onclick="deltr('${u.doctorFlow}')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="button">
            <input type="button" class="btn_green" onclick="saveAdd();" value="保&#12288;存"/>
            <input type="button" class="btn_green" onclick="closeFlash();" value="取&#12288;消"/>
        </div>
        <div style="display: none" id="addDiv">
            <c:forEach items="${doctorList}" var="u" varStatus="status">
                <input hidden id="${u.doctorFlow}" name="addUserFlow" value="${u.doctorFlow}_${u.doctorCode}_${u.doctorName}_${u.doctorRoleFlow}_${u.doctorRoleName}">
            </c:forEach>
        </div>
        <div style="display: none" id="delDiv"></div>
    </div>


