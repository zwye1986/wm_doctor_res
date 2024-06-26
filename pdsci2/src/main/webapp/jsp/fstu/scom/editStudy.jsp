<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <style type="text/css">
        .checkedClass{color: #00a1e5;}
    </style>
    <script type="text/javascript">
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

        function removePerson() {
            $("#selectDiv").empty();
            var users = $(':checkbox:checked');
            $.each(users, function (i, n) {
                var userFlow = $(n).val();
                $(n).removeAttr("checked");
                $(n).parent().parent().removeClass();
            });
        }

        function savePerson() {
            var datas = [];
            var users = $("#selectDiv").children();
            var htmlStr = "";
            $.each(users, function (i, n) {
                var userFlow = $(n).find("input[name='userFlow']").val();
                var userName = $(n).find("input[name='userName']").val();
                var sexId = $(n).find("input[name='sexId']").val();
                var sexName = $(n).find("input[name='sexName']").val();
                var deptFlow = $(n).find("input[name='deptFlow']").val();
                var deptName = $(n).find("input[name='deptName']").val();
                var postId = $(n).find("input[name='postId']").val();
                var postName = $(n).find("input[name='postName']").val();
                var titleId = $(n).find("input[name='titleId']").val();
                var titleName = $(n).find("input[name='titleName']").val();
                var userBirthday = $(n).find("input[name='userBirthday']").val();
                htmlStr += "<tr><td><input type='hidden' name='userFlow' value='"+userFlow+"'/><input type='hidden' name='userName' value='"+userName+"'/>"+userName+"</td><td><input type='hidden' name='sexId' value='"+sexId+"'/><input type='hidden' name='sexName' value='"+sexName+"'/>"+sexName+"</td>" +
                        "<td><input type='hidden' name='deptFlow' value='"+deptFlow+"'/><input type='hidden' name='deptName' value='"+deptName+"'/>"+deptName+"</td><td><input type='hidden' name='postId' value='"+postId+"'/><input type='hidden' name='postName' value='"+postName+"'/>"+postName+"</td>" +
                        "<td><input type='hidden' name='titleId' value='"+titleId+"'/><input type='hidden' name='titleName' value='"+titleName+"'/>"+titleName+"</td><td><input type='hidden' name='userBirthday' value='"+userBirthday+"'/>"+userBirthday+"</td>" +
                        "<td><input type='text' style='width:80px;' name='studyOrgName' class='validate[required]'/></td><td><input type='text' style='width:80px;' name='studyCourse' class='validate[required]'/></td><td><input type='text' style='width:40px;' name='studyMonth' class='validate[required,custom[integer],min[1]max[12]]'></td>" +
                        "<td><input type='text' style='width:80px;' readonly='readonly' name='studyBeginDate' class='validate[required]' onchange='checkYear(this)' onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"/>~<input type='text' style='width:80px;' readonly='readonly' name='studyEndDate' class='validate[required]' onchange='checkYear(this)' onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd'})\"/></td>" +
                        "<td><input type='text' style='width:140px;' name='memo'/></td></tr>";
            });

            $("#userTbody").html(htmlStr);
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

        function save(submitFlag){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var datas = [];
            var trs = $("#userTbody tr");
            $.each(trs, function (i, n) {
                var userFlow = $(n).find("input[name='userFlow']").val();
                var userName = $(n).find("input[name='userName']").val();
                var sexId = $(n).find("input[name='sexId']").val();
                var sexName = $(n).find("input[name='sexName']").val();
                var deptFlow = $(n).find("input[name='deptFlow']").val();
                var deptName = $(n).find("input[name='deptName']").val();
                var postId = $(n).find("input[name='postId']").val();
                var postName = $(n).find("input[name='postName']").val();
                var titleId = $(n).find("input[name='titleId']").val();
                var titleName = $(n).find("input[name='titleName']").val();
                var userBirthday = $(n).find("input[name='userBirthday']").val();
                var studyOrgName = $(n).find("input[name='studyOrgName']").val();
                var studyCourse = $(n).find("input[name='studyCourse']").val();
                var studyMonth = $(n).find("input[name='studyMonth']").val();
                var studyBeginDate = $(n).find("input[name='studyBeginDate']").val();
                var studyEndDate = $(n).find("input[name='studyEndDate']").val();
                var memo = $(n).find("input[name='memo']").val();
                var data = {
                    "userFlow": userFlow,
                    "userName": userName,
                    "sexId": sexId,
                    "sexName": sexName,
                    "deptFlow": deptFlow,
                    "deptName": deptName,
                    "postId": postId,
                    "postName": postName,
                    "titleId": titleId,
                    "titleName": titleName,
                    "userBirthday": userBirthday,
                    "studyOrgName": studyOrgName,
                    "studyCourse": studyCourse,
                    "studyMonth": studyMonth,
                    "studyBeginDate": studyBeginDate,
                    "studyEndDate": studyEndDate,
                    "memo": memo
                };
                datas.push(data);
            });
            var requestData = JSON.stringify(datas);
            var url = "<s:url value='/fstu/score/saveUserStudy?submitFlag='/>"+submitFlag;
            jboxStartLoading();
            jboxPostJson(url, requestData, function (resp) {
                if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                    window.parent.frames['mainIframe'].$("#searchForm").submit();
                    window.location.reload();
                }
            }, null, true);
        }
        function checkYear(obj) {
            var dates = $(':text', $(obj).closest("td"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div style="width: 100%;height: 50px">
            <span>查找：</span>
            <input placeholder="输入姓名搜索" type="text" style="width: 50%" onkeyup="likeSearch(this.value);"/>
        </div>
        <div style="float: left; width: 44%;">
            <div style="width: 80%; margin-left: 10%;border: 1px solid #336544; overflow:auto;height: 300px">
                <c:forEach items="${sysUserList}" var="user">
                    <div onclick="checkedDiv(this);" style="width: 100%;margin-top: 5px" userName="${user.userName}">
                        <label>&nbsp;
                            <input type="checkbox" readonly="readonly" name="jointUserFlows" value="${user.userFlow}"/>${user.userCode}&#12288;${user.userName}
                        </label>
                        <div style="display: none">
                            <div id="${user.userFlow}" style="width: 100%;margin-top: 5px">
                                <input type="hidden" name="userFlow" value="${user.userFlow}"/>
                                <input type="hidden" name="userName" value="${user.userName}"/>
                                <input type="hidden" name="sexId" value="${user.sexId}"/>
                                <input type="hidden" name="sexName" value="${user.sexName}"/>
                                <input type="hidden" name="deptFlow" value="${user.deptFlow}"/>
                                <input type="hidden" name="deptName" value="${user.deptName}"/>
                                <input type="hidden" name="postId" value="${user.postId}"/>
                                <input type="hidden" name="postName" value="${user.postName}"/>
                                <input type="hidden" name="titleId" value="${user.titleId}"/>
                                <input type="hidden" name="titleName" value="${user.titleName}"/>
                                <input type="hidden" name="userBirthday" value="${user.userBirthday}"/>
                                <span style="color: #00a1e5;">${user.userName}</span>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div style="float: left;width:12%;text-align:center;height: 100%">
            <p style="margin-top: 50px"><input type="button" class="search" onclick="addPerson()" value="选&#12288;择"/>
                <br/><br/>
                <input type="button" class="search" onclick="removePerson()" value="取&#12288;消"/></p>
            <p style="margin-top: 50px"><input type="button" class="search" onclick="savePerson()" value="确&#12288;定"/></p>
        </div>
        <div style="float: right; width: 44%;">
            <form method="post" id="postForm">
                <div id="selectDiv" style="width: 80%; margin-left: 10%;border: 1px solid #336544; overflow:auto;height: 300px">

                </div>
            </form>
        </div>
        <div style="width:100%;margin-top:330px;overflow-x:auto;">
            <form id="myForm">
                <table class="xllist">
                    <tr>
                        <th colspan="11" class="bs_name">新增学员列表</th>
                    </tr>
                    <tr>
                        <td style="font-weight:bold;min-width:60px;">姓名</td>
                        <td style="font-weight:bold;min-width:60px;">性别</td>
                        <td style="font-weight:bold;min-width:60px;">科室</td>
                        <td style="font-weight:bold;min-width:60px;">职务</td>
                        <td style="font-weight:bold;min-width:60px;">职称</td>
                        <td style="font-weight:bold;min-width:80px;">出生年月</td>
                        <td style="font-weight:bold;min-width:100px;">进修单位</td>
                        <td style="font-weight:bold;min-width:100px;">进修科目</td>
                        <td style="font-weight:bold;min-width:60px;">进修月份</td>
                        <td style="font-weight:bold;min-width:200px;">进修起止时间</td>
                        <td style="font-weight:bold;min-width:160px;">备注</td>
                    </tr>
                    <tbody id="userTbody">

                    </tbody>
                </table>
            </form>
        </div>
        <div class="button" style="width: 100%;">
            <input class="search" type="button" value="保&#12288;存" onclick="save();"/>
            <input class="search" type="button" value="提&#12288;交" onclick="save('submit');"/>
            <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
        </div>
    </div>
</div>
</body>
</html>