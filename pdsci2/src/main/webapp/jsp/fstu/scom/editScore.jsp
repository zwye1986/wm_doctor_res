<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
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
                var idNo = $(n).find("input[name='idNo']").val();
                var sexId = $(n).find("input[name='sexId']").val();
                var sexName = $(n).find("input[name='sexName']").val();
                var titleId = $(n).find("input[name='titleId']").val();
                var titleName = $(n).find("input[name='titleName']").val();
                var promoteTime = $(n).find("input[name='promoteTime']").val();
                htmlStr += "<tr><td><input type='hidden' name='userFlow' value='"+userFlow+"'/><input type='hidden' name='userName' value='"+userName+"'/>"+userName+"</td><td><input type='hidden' name='sexId' value='"+sexId+"'/><input type='hidden' name='sexName' value='"+sexName+"'/>"+sexName+"</td>" +
                        "<td><input type='hidden' name='idNo' value='"+idNo+"'/>"+idNo+"</td><td><input type='hidden' name='titleId' value='"+titleId+"'/><input type='hidden' name='titleName' value='"+titleName+"'/>"+titleName+"</td><td><input type='hidden' name='promoteTime' value='"+promoteTime+"'/>"+promoteTime+"</td>" +
                        "<td><select style='width:70px;' name='sessionNumber' class='validate[required]'><option/><option value='2020'>2020</option><option value='2019'>2019</option><option value='2018'>2018</option><option value='2017'>2017</option><option value='2016'>2016</option><option value='2015'>2015</option><option value='2014'>2014</option></td>" +
                        "<td><input style='width:70px;' id='proSelect"+i+"' name='subjectName' class='validate[required]' onclick='showMenu("+i+")'/><input type='hidden' id='selectProjId"+i+"' name='subjectId'></td>" +
                        "<td><select style='width:70px;' name='scoreType' class='validate[required]'><option/><option value='1'>I类学分</option><option value='2'>II类学分</option></select></td>" +
                        "<td><select style='width:70px;' name='scoreTarget' class='validate[required]'><option/><option value='Y'>是</option><option value='N'>否</option></select></td></tr>";
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

        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var datas = [];
            var trs = $("#userTbody tr");
            $.each(trs, function (i, n) {
                var userFlow = $(n).find("input[name='userFlow']").val();
                var userName = $(n).find("input[name='userName']").val();
                var idNo = $(n).find("input[name='idNo']").val();
                var sexId = $(n).find("input[name='sexId']").val();
                var sexName = $(n).find("input[name='sexName']").val();
                var titleId = $(n).find("input[name='titleId']").val();
                var titleName = $(n).find("input[name='titleName']").val();
                var promoteTime = $(n).find("input[name='promoteTime']").val();
                var sessionNumber = $(n).find("select[name='sessionNumber'] option:selected").val();
                var subjectId = $(n).find("input[name='subjectId']").val();
                var subjectName = $(n).find("input[name='subjectName']").val();
                var scoreType = $(n).find("select[name='scoreType'] option:selected").val();
                var scoreTypeName = $(n).find("select[name='scoreType'] option:selected").text();
                var scoreTarget = $(n).find("select[name='scoreTarget'] option:selected").val();
                var data = {
                    "userFlow": userFlow,
                    "userName": userName,
                    "idNo": idNo,
                    "sexId": sexId,
                    "sexName": sexName,
                    "titleId": titleId,
                    "titleName": titleName,
                    "promoteTime": promoteTime,
                    "sessionNumber": sessionNumber,
                    "subjectId": subjectId,
                    "subjectName": subjectName,
                    "scoreType": scoreType,
                    "scoreTypeName": scoreTypeName,
                    "scoreTarget": scoreTarget
                };
                datas.push(data);
            });
            var requestData = JSON.stringify(datas);
            var url = "<s:url value='/fstu/score/saveUserScore'/>";
            jboxStartLoading();
            jboxPostJson(url, requestData, function (resp) {
                if (resp == '${GlobalConstant.OPRE_SUCCESSED}') {
                    window.parent.frames['mainIframe'].$("#searchForm").submit();
                    window.location.reload();
                }
            }, null, true);
        }

        //学科 BEGIN
        $(function(){
            var url = "<s:url value='/sys/subject/getAllDataJson'/>";
            jboxPostJson(url, null, function (data) {
                if (data) {
                    zNodes = $.parseJSON(data);
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            }, null, false);
        });
        var setting = {
            view: {
                dblClickExpand: false,
                showIcon: false,
                showTitle: false,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick
            }
        };
        function beforeClick(treeId, treeNode) {
            var check = (treeNode.id != 0);
            if (!check) jboxTip('不能选择根节点');
            return check;
        }
        function onClick(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                nodes = zTree.getSelectedNodes();
            nodes.sort(function compare(a, b) {
                return a.id - b.id;
            });
            var v = "",id = "";
            for (var i = 0, l = nodes.length; i < l; i++) {
                v += nodes[i].name + ",";
                id += nodes[i].id + ",";
            }
            if (v.length > 0) v = v.substring(0, v.length - 1);
            if (id.length > 0) id = id.substring(0, id.length - 1);
            <c:if test="${empty param.userFlow}">
                //通过重叠位置，判定ztree学科代码赋值哪个文本
                var overlay = $("#menuContent").offset().top+$("#menuContent").outerHeight();
                $("input[id^='proSelect']").each(function(){
                    if($(this).offset().top == overlay){
                        $(this).val(v);
                        $(this).next("input").val(id);
                        return;
                    }
                });
            </c:if>
            <c:if test="${!empty param.userFlow}">
                var cityObj = $("#proSelect");
                $("#selectProjId").val(id);
                cityObj.attr("value", v);
            </c:if>
        }
        function showMenu(inx) {
            var cityObj = $("#proSelect"+inx);
            var cityOffset = $("#proSelect"+inx).offset();
            $("#menuContent").css({
                left: cityOffset.left + "px",
                top: cityOffset.top - $("#menuContent").outerHeight() + "px"
            }).slideDown("fast");
            $("body").bind("mousedown", onBodyDown);
        }
        function showMenu2() {
            var cityObj = $("#proSelect");
            var cityOffset = $("#proSelect").offset();
            $("#menuContent").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");
            $("body").bind("mousedown", onBodyDown);
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
                hideMenu();
            }
        }
        //学科 END
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <c:if test="${empty param.userFlow}">
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
                                    <input type="hidden" name="idNo" value="${user.idNo}"/>
                                    <input type="hidden" name="sexId" value="${user.sexId}"/>
                                    <input type="hidden" name="sexName" value="${user.sexName}"/>
                                    <input type="hidden" name="titleId" value="${user.titleId}"/>
                                    <input type="hidden" name="titleName" value="${user.titleName}"/>
                                    <input type="hidden" name="promoteTime" value="${user.promoteTime}"/>
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
        </c:if>
        <div id="menuContent" class="menuContent" style="display:none; position:absolute;">
            <ul id="treeDemo" class="ztree" style="margin-top:0; width:190px;"></ul>
        </div>
        <form id="myForm">
            <table class="xllist" style="width:100%;margin-top:${!empty param.userFlow?'0':'330'}px;">
                <c:if test="${empty param.userFlow}">
                    <tr>
                        <th colspan="10" class="bs_name">新增学员列表</th>
                    </tr>
                </c:if>
                <tr>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>身份证号</th>
                    <th>职称</th>
                    <th>最后晋升时间</th>
                    <th>年度</th>
                    <th>学科</th>
                    <th>学分类型</th>
                    <th>学分是否完成</th>
                </tr>
                <tbody id="userTbody">
                    <c:if test="${!empty param.userFlow}">
                        <td>${userScore.userName}</td>
                        <td>${userScore.sexName}</td>
                        <td>${userScore.idNo}</td>
                        <td>${userScore.titleName}</td>
                        <td>${userScore.promoteTime}</td>
                        <td>
                            <select name="sessionNumber" style='width:70px;'>
                                <option value="2020" <c:if test="${userScore.sessionNumber eq '2020'}">selected</c:if>>2020</option>
                                <option value="2019" <c:if test="${userScore.sessionNumber eq '2019'}">selected</c:if>>2019</option>
                                <option value="2018" <c:if test="${userScore.sessionNumber eq '2018'}">selected</c:if>>2018</option>
                                <option value="2017" <c:if test="${userScore.sessionNumber eq '2017'}">selected</c:if>>2017</option>
                                <option value="2016" <c:if test="${userScore.sessionNumber eq '2016'}">selected</c:if>>2016</option>
                                <option value="2015" <c:if test="${userScore.sessionNumber eq '2015'}">selected</c:if>>2015</option>
                                <option value="2014" <c:if test="${userScore.sessionNumber eq '2014'}">selected</c:if>>2014</option>
                            </select>
                        </td>
                        <td>
                            <input type="hidden" id="selectProjId" name='subjectId' value="${userScore.subjectId}">
                            <input style="width:70px;" id="proSelect" name="subjectName" value="${userScore.subjectName}" onclick="showMenu2();"/>
                        </td>
                        <td>
                            <select name="scoreType" style="width:70px;">
                                <option value="1" <c:if test="${userScore.scoreType eq '1'}">selected</c:if>>I类学分</option>
                                <option value="2" <c:if test="${userScore.scoreType eq '2'}">selected</c:if>>II类学分</option>
                            </select>
                        </td>
                        <td>
                            <select name="scoreTarget" style="width:70px;">
                                <option value="Y" <c:if test="${userScore.scoreTarget eq 'Y'}">selected</c:if>>是</option>
                                <option value="N" <c:if test="${userScore.scoreTarget eq 'N'}">selected</c:if>>否</option>
                            </select>
                            <input type="hidden" name="userFlow" value="${userScore.userFlow}">
                            <input type="hidden" name="userName" value="${userScore.userName}">
                            <input type="hidden" name="idNo" value="${userScore.idNo}">
                            <input type="hidden" name="sexId" value="${userScore.sexId}">
                            <input type="hidden" name="sexName" value="${userScore.sexName}">
                            <input type="hidden" name="titleId" value="${userScore.titleId}">
                            <input type="hidden" name="titleName" value="${userScore.titleName}">
                            <input type="hidden" name="promoteTime" value="${userScore.promoteTime}">
                        </td>
                    </c:if>
                </tbody>
            </table>
        </form>
        <div class="button" style="width: 100%;">
            <input class="search" type="button" value="保&#12288;存" onclick="save();"/>
            <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
        </div>
    </div>
</div>
</body>
</html>