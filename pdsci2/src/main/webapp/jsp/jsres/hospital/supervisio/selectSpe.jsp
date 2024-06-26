<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript"
            src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            if ("orgSubject" == "${subjectEdit}") {
                var inputSpeList = window.parent.frames["jbox-message-iframe"].$("input[name='speId']");
                if (inputSpeList.length > 0) {
                    for (var i = 0; i < inputSpeList.length; i++) {
                        <c:forEach items="${speList}" var="spe">
                        if ("${spe.speId}" == inputSpeList[i].value) {
                            $("#${spe.speId}").attr("checked", "checked");
                            $("#${spe.speId}").attr("disabled", true);
                            var speId2 = '${spe.speId}';
                            var speName = '${spe.speName}';
                            $("#speTable").append("<tr id=" + speId2 + " >"
                                + '<td>'
                                + '<input hidden id="' + speId2 + '_speName" name="speName" value="' + speName + '">'
                                + speName + '</td>'
                                + "<td><a href=\'#\' onclick=\"deltr('" + speId2 + "')\">删除</a></td>"
                                + "</tr>");
                        }
                        </c:forEach>
                    }
                }
            }
            if ("speSubject" == "${subjectEdit}") {
                var inputOrgList = window.parent.frames["jbox-message-iframe"].$("input[name='orgFlow']");
                if (inputOrgList.length > 0) {
                    for (var i = 0; i < inputOrgList.length; i++) {
                        <c:forEach items="${orgList}" var="org">
                        if ("${org.orgFlow}" == inputOrgList[i].value) {
                            $("#${org.orgFlow}").attr("checked", "checked");
                            $("#${org.orgFlow}").attr("disabled", true);
                            var orgFlow2 = '${org.orgFlow}';
                            var orgName = '${org.orgName}';
                            $("#orgTable").append("<tr id=" + orgFlow2 + " >"
                                + '<td>'
                                + '<input hidden id="' + orgFlow2 + '_orgName" name="orgName" value="' + orgName + '">'
                                + orgName + '</td>'
                                + "<td><a href=\'#\' onclick=\"deltr('" + orgFlow2 + "')\">删除</a></td>"
                                + "</tr>");
                        }
                        </c:forEach>
                    }
                }
            }
        });

        function saveOrgSpeManage(obj) {
            if ("orgSubject" == "${subjectEdit}") {
                var speId2 = $(obj).val();
                var speName = $("#" + speId2 + "_speName").val();
                var checkedVal = $(obj).attr("checked");
                if ("checked" == checkedVal) {
                    var html = '<input hidden id="add' + speId2 + '" name="addSpeId" value="' + speId2 + '"></input><input hidden id="add' + speId2 + 'name"  value="' + speName + '"></input>';
                    $("#addDiv").append(html);
                    $("#speTable").append("<tr id=" + speId2 + " >"
                        + '<td>'
                        + '<input hidden id="' + speId2 + '" name="speId" value="' + speId2 + '">'
                        + '<input hidden id="' + speId2 + '_speName" name="speName" value="' + speName + '">'
                        + speName + '</td>'
                        + "<td><a href=\'#\' onclick=\"deltr('" + speId2 + "')\">删除</a></td>"
                        + "</tr>");

                } else {
                    deltr(speId2);
                }
            }

            if ("speSubject" == "${subjectEdit}") {
                var orgFlow2 = $(obj).val();
                var orgName = $("#" + orgFlow2 + "_orgName").val();
                var checkedVal = $(obj).attr("checked");
                if ("checked" == checkedVal) {
                    var html = '<input hidden id="add' + orgFlow2 + '" name="addOrgFlow" value="' + orgFlow2 + '"></input><input hidden id="add' + orgFlow2 + 'name"  value="' + orgName + '"></input>';
                    $("#addDiv").append(html);
                    $("#orgTable").append("<tr id=" + orgFlow2 + " >"
                        + '<td>'
                        + '<input hidden id="' + orgFlow2 + '_orgName" name="orgName" value="' + orgName + '">'
                        + orgName + '</td>'
                        + "<td><a href=\'#\' onclick=\"deltr('" + orgFlow2 + "')\">删除</a></td>"
                        + "</tr>");
                } else {
                    deltr(orgFlow2);
                }
            }
        }

        function deltr(value) {
            //删除当前行
            <c:if test="${subjectEdit eq 'orgSubject'}">
            var valueName = $("#" + value + "_speName").val();
            var inputAddSpeList = $("input[name='addSpeId']");
            if (inputAddSpeList.length > 0) {
                for (var i = 0; i < inputAddSpeList.length; i++) {
                    if (inputAddSpeList[i].value == value) {
                        $("#add" + value).remove();
                        $("#add" + value + "name").remove();
                    }
                }
            } else {
                var html = '<input hidden id="' + value + '" name="delSpeId" value="' + value + '"></input><input hidden id="del' + value + 'name"  value="' + valueName + '"></input>';
                $("#delDiv").append(html);
            }
            $("tr[id='" + value + "']").remove();
            </c:if>
            <c:if test="${subjectEdit eq 'speSubject'}">
            var valueName = $("#" + value + "_orgName").val();
            var inputAddOrgList = $("input[name='addOrgFlow']");
            if (inputAddOrgList.length > 0) {
                for (var i = 0; i < inputAddOrgList.length; i++) {
                    if (inputAddOrgList[i].value == value) {
                        $("#add" + value).remove();
                        $("#add" + value + "name").remove();
                    }
                }
            } else {
                var html = '<input hidden id="' + value + '" name="delOrgFlow" value="' + value + '"></input><input hidden id="del' + value + 'name"  value="' + valueName + '"></input>';
                $("#delDiv").append(html);
            }
            window.parent.frames["jbox-message-iframe"].$("#" + value).remove();
            window.parent.frames["jbox-message-iframe"].$("#" + value + "name").remove();
            window.parent.frames["jbox-message-iframe"].$("#del" + value).remove();
            $("tr[id='" + value + "']").remove();
            </c:if>
            //已选择专业,基地勾选取消
            $("#" + value).removeAttr("checked");

        }

        function saveAdd() {
            <c:if test="${subjectEdit eq 'orgSubject'}">
            var inputAddSpeList = $("input[name='addSpeId']");
            if (inputAddSpeList.length > 0) {
                for (var i = 0; i < inputAddSpeList.length; i++) {
                    var speId2 = inputAddSpeList[i].value;
                    var speName = $("#add" + speId2 + "name").val();
                    var html = '<input readonly hidden id="' + speId2 + '" name="speId" value="' + speId2 + '"></input>' +
                        '<input readonly id="' + speId2 + 'name" name="speIdName" style="width: 120px;height: 30px" value="' + speName + '"></input>' +
                        '<a id="del' + speId2 + '" name="delSpeId" style="margin-right: 40px;" href=\'#\' onclick=\'delParam("' + speId2 + '")\'><img alt="删除" src="<s:url value="/css/skin/LightBlue/images/del3.png"/>"></a>';
                    window.parent.frames["jbox-message-iframe"].$("#orgSubjectDiv").append(html);
                }
            }
            var delSpeList = $("input[name='delSpeId']");
            if (delSpeList.length > 0) {
                for (var i = 0; i < delSpeList.length; i++) {
                    var delSpeId = delSpeList[i].value;
                    window.parent.frames["jbox-message-iframe"].$("#" + delSpeId).remove();
                    window.parent.frames["jbox-message-iframe"].$("#" + delSpeId + "name").remove();
                    window.parent.frames["jbox-message-iframe"].$("#del" + delSpeId).remove();
                }
            }
            jboxClose();
            </c:if>
            <c:if test="${subjectEdit eq 'speSubject'}">
            var inputAddOrgList = $("input[name='addOrgFlow']");
            if (inputAddOrgList.length > 0) {
                for (var i = 0; i < inputAddOrgList.length; i++) {
                    var orgFlow2 = inputAddOrgList[i].value;
                    var orgName = $("#add" + orgFlow2 + "name").val();
                    var html = '<input readonly hidden id="' + orgFlow2 + '" name="orgFlow" value="' + orgFlow2 + '"></input>' +
                        '<input readonly id="' + orgFlow2 + 'name" name="orgFlowName" style="width: 250px;height: 30px" value="' + orgName + '"></input>' +
                        '<a id="del' + orgFlow2 + '" name="delOrgFlow" style="margin-right: 20px;" href=\'#\' onclick=\'delParam("' + orgFlow2 + '")\'><img alt="删除" src="\<s:url value="/css/skin/LightBlue/images/del3.png"/>\"></a>';
                    window.parent.frames["jbox-message-iframe"].$("#speSubjectDiv").append(html);
                }
            }
            var delOrgList = $("input[name='delOrgFlow']");
            if (delOrgList.length > 0) {
                for (var i = 0; i < delOrgList.length; i++) {
                    var delOrgFlow = delOrgList[i].value;
                    window.parent.frames["jbox-message-iframe"].$("#" + delOrgFlow).remove();
                    window.parent.frames["jbox-message-iframe"].$("#" + delOrgFlow + "name").remove();
                    window.parent.frames["jbox-message-iframe"].$("#del" + delOrgFlow).remove();
                }
            }
            jboxClose();
            </c:if>
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

        function likeSearch2(name) {
            if (name) {
                $("[orgName]").hide();
                $("[orgName*='" + name + "']").show();
            } else {
                $("[orgName]").show();
            }
        }
        function likeSearch3(name) {
            if (name) {
                $("[baseCode]").hide();
                $("[baseCode*='" + name + "']").show();
            } else {
                $("[baseCode]").show();
            }
        }
        function allSelect(obj) {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "speId") {
                    itemIdList[i].checked=obj.checked;
                    itemIdList[i].onclick();
                }
            }
        }
        function allSelectOrg(obj) {
            var itemIdList = $("input");
            for (var i = 0; i < itemIdList.length; i++) {
                if (itemIdList[i].getAttribute("name") == "orgFlow") {
                    itemIdList[i].checked=obj.checked;
                    itemIdList[i].onclick();
                }
            }
        }
    </script>

    <div>
        <div id="checkedForm" style="width: 48%;float: left;overflow: auto;max-height: 560px;">
            <c:if test="${subjectEdit eq 'orgSubject'}">
                <form>
                    <table border="0" cellpadding="0" cellspacing="0" class="grid">
                        <tr>
                            <td class="td_left">
                                专业：<input type="text" name="speName" placeholder="可通过关键字检索"
                                          onkeyup="likeSearch(this.value);" class="input"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <span style="margin-right: 60px"><input type="checkbox" value="" onclick="allSelect(this);"/>&nbsp;全部</span>
                                <span style="margin-right: 90px"> 可选择专业：</span>
                            </th>
                        </tr>
                        <c:if test="${empty speList}">
                            <tr>
                                <td>暂无专业可选择</td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty speList}">
                            <c:forEach items="${speList}" var="spe" varStatus="status">
                                <tr speName="${spe.speName}">
                                    <c:if test="${spe.speId ne '3500'}">
                                        <td>
                                            <c:set var="key" value="${spe.speId}"/>
                                            <input type="hidden" id="${spe.speId}_speName" name="speName"
                                                   value="${spe.speName }"/>
                                            <input type="checkbox" id="${key}" name="speId" value="${spe.speId}"
                                                   onclick="saveOrgSpeManage(this)"/>&nbsp;${spe.speName }
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </form>
            </c:if>
            <c:if test="${subjectEdit eq 'speSubject'}">
                <form>
                    <table border="0" cellpadding="0" cellspacing="0" class="grid">
                        <tr>
                            <td class="td_left">
                                基地：<input type="text" name="orgName" placeholder="可通过关键字检索"
                                          onkeyup="likeSearch2(this.value);" class="input"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_left">
                                基地代码：<input type="text" name="baseCode" placeholder="可通过基地代码检索"
                                            onkeyup="likeSearch3(this.value);" class="input"/>
                            </td>
                        </tr>
                        <tr>
                            <th>
                                <span style="margin-right: 60px"><input type="checkbox" value="" onclick="allSelectOrg(this);"/>&nbsp;全部</span>
                                <span style="margin-right: 90px"> 可选择基地：</span>
                            </th>
                        </tr>
                        <c:if test="${empty orgList}">
                            <tr>
                                <td>暂无基地可选择</td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty orgList}">
                            <c:forEach items="${orgList}" var="org" varStatus="status">

                                <tr orgName="${org.orgName}"  baseCode="${org.baseCode}">
                                    <td>
                                        <c:set var="key" value="${org.orgFlow}"/>
                                        <input type="hidden" id="${org.orgFlow}_orgName" name="orgName" value="${org.orgName}"/>
                                        <input type="hidden" id="${org.orgFlow}_baseCode" name="baseCode" value="${org.baseCode}"/>
                                        <input type="checkbox" id="${key}" name="orgFlow" value="${org.orgFlow}"
                                               onclick="saveOrgSpeManage(this)"/>&nbsp;${org.orgName}
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </table>
                </form>
            </c:if>
        </div>
        <div id="checkedForm2" style="width: 48%;float: right;overflow: auto;max-height: 530px;margin: 0 0 10px 0px;">
            <c:if test="${subjectEdit eq 'orgSubject'}">
                <table border="0" cellpadding="0" cellspacing="0" class="grid" id="speTable">
                    <tr>
                        <th colspan="2">已选择专业：</th>
                    </tr>
                </table>
            </c:if>
            <c:if test="${subjectEdit eq 'speSubject'}">
                <table border="0" cellpadding="0" cellspacing="0" class="grid" id="orgTable">
                    <tr>
                        <th colspan="2">已选择基地：</th>
                    </tr>
                </table>
            </c:if>
        </div>
        <div class="button">
            <input type="button" class="btn_green" onclick="saveAdd();" value="保&#12288;存"/>
            <input type="button" class="btn_green" onclick="jboxClose();" value="取&#12288;消"/>
        </div>
        <div style="display: none" id="addDiv"></div>
        <div style="display: none" id="delDiv"></div>
    </div>


