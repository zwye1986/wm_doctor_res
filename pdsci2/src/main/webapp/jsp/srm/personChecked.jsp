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

        function saveOrg() {
            var addResult = [];
            var delResult = [];
            $(":checkbox:checked:not([isResult])").each(function () {
                addResult.push(this.value);
            });
            $(":checkbox[isResult]:not(:checked)").each(function () {
                delResult.push($(this).attr("isResult"));
            });

            var data = "";
            if (addResult.length > 0) {
                data += "&";
                data += serializeList("jointOrgFlows", addResult);
            }
            if (delResult.length > 0) {
                data += ("&" + serializeList("delJointFlows", delResult));
            }
            if ((addResult.length + delResult.length) <= 0) {
                top.jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
                window.parent.document.mainIframe.search();
                return jboxClose();
            }
            var url = "<s:url value='/res/platform/save'/>?orgFlow=${org.orgFlow}";
            jboxPost(url, data, function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.document.mainIframe.search();
                    jboxClose();
                }
            });
        }
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

        function addPerson() {
            $("#selectDiv").empty();
            var addResult = [];
            var users = $(':checkbox:checked');
            $.each(users, function (i, n) {
                var userFlow = $(n).val();
                addResult.push($("#" + userFlow).clone());
            });
            $("#selectDiv").append(addResult);
        }
        function savePerson() {
            var datas = [];
            var users = $("#selectDiv").children();
            $.each(users, function (i, n) {
                var userFlow = $(n).find("input[name='userFlow']").val();
                var userName = $(n).find("input[name='userName']").val();
                var workCode = $(n).find("input[name='workCode']").val();
                var sexId = $(n).find("input[name='sexId']").val();
                var sexName = $(n).find("input[name='sexName']").val();
                var titleId = $(n).find("input[name='titleId']").val();
                var titleName = $(n).find("input[name='titleName']").val();
                var degreeId = $(n).find("input[name='degreeId']").val();
                var degreeName = $(n).find("input[name=degreeName]").val();
                var deptFlow = $(n).find("input[name='deptFlow']").val();
                var deptName = $(n).find("input[name=deptName]").val();
                var educationId = $(n).find("input[name='educationId']").val();
                var educationName = $(n).find("input[name='educationName']").val();
                //alert(userFlow);
                var data = {//json对象   {key:value}
                    "userFlow": userFlow,
                    "userName": userName,
                    "workCode": workCode,
                    "sexId": sexId,
                    "sexName": sexName,
                    "titleId": titleId,
                    "titleName": titleName,
                    "degreeId": degreeId,
                    "degreeName": degreeName,
                    "deptFlow": deptFlow,
                    "deptName": deptName,
                    "educationId": educationId,
                    "educationName": educationName
                };
                datas.push(data);
            });
            if(datas && datas != ''){
                //alert(datas);
               window.parent.closeEditPage(datas);
            }
        }

        function removePerson() {
            $("#selectDiv").empty();
            var users = $(':checkbox:checked');
            $.each(users, function (i, n) {
                var userFlow = $(n).val();
                $(n).removeAttr("checked");
                $(n).parent().parent().removeClass();
            });
        }

        function checkedDiv(obj) {
            if ($(obj).find("input[type='checkbox']").is(':checked')) {
                $(obj).find("input[type='checkbox']").add("checked");
                $(obj).addClass("checkedClass");
            } else {
                $(obj).find("input[type='checkbox']").remove("checked");
                $(obj).removeClass("checkedClass");
            }
        }


    </script>
</head>
<body>
<div class="">
    <div class="">
        <div style="width: 100%;height: 50px">
            <span>查找：</span>
            <input placeholder="输入姓名搜索" type="text" value="" style="width: 50%" onkeyup="likeSearch(this.value);"/>
        </div>
        <div style="float: left; width: 44%;">
            <div style="width: 80%; margin-left: 10%;border: 1px solid #336544; overflow:auto;height: 300px">
                <c:forEach items="${sysUserList}" var="user">
                    <div onclick="checkedDiv(this);" style="width: 100%;margin-top: 5px"
                         userName="${user.userName}${user.deptName}">
                        <label>
                            <input type="checkbox" readonly="readonly" name="jointUserFlows" value="${user.userFlow}"/>
                                ${user.userName}&#12288;&#12288;${user.deptName}
                        </label>
                        <div style="display: none">
                            <div id="${user.userFlow}" style="width: 100%;margin-top: 5px">
                                <input type="hidden" name="userFlow" value="${user.userFlow}"/>
                                <input type="hidden" name="userName" value="${user.userName}"/>
                                <input type="hidden" name="workCode" value="${user.workCode}"/>
                                <input type="hidden" name="sexName" value="${user.sexName}"/>
                                <input type="hidden" name="sexId" value="${user.sexId}"/>
                                <input type="hidden" name="titleId" value="${user.titleId}"/>
                                <input type="hidden" name="titleName" value="${user.titleName}"/>
                                <input type="hidden" name="degreeId" value="${user.degreeId}"/>
                                <input type="hidden" name="degreeName" value="${user.degreeName}"/>
                                <input type="hidden" name="educationId" value="${user.educationId}"/>
                                <input type="hidden" name="educationName" value="${user.educationName}"/>
                                <input type="hidden" name="deptFlow" value="${user.deptFlow}"/>
                                <input type="hidden" name="deptName" value="${user.deptName}"/>
                                <span style="color: #00a1e5;">${user.userName}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div style="float: left; width: 11%;height: 100%">
            <p style="margin-top: 50px"><input type="button" class="search" onclick="addPerson()" value="选择"/>
                <br/><br/>
                <input type="button" class="search" onclick="removePerson()" value="取消"/></p>
            <p style="margin-top: 50px"><input type="button" class="search" onclick="savePerson()" value="确定"/></p>
        </div>
        <div style="float: left; width: 44%;">
            <form method="post" id="postForm">
                <div id="selectDiv"
                     style="width: 80%; margin-left: 10%;border: 1px solid #336544; overflow:auto;height: 300px">

                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>