<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        $(document).ready(function () {
            if ($("#mainResearcherTb tr").length <= 0) {
                add('mainResearcher');
            }
            if ($("#joinUserTb tr").length <= 0) {
                add('joinUser');
            }
        });

        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            $('#nxt').attr({"disabled":"disabled"});
            $('#prev').attr({"disabled":"disabled"});
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }

        function add(tb) {
            if ("mainResearcher" == tb) {
                var length = $("#" + tb + "Tb").children().length;
//                if (length > 1) {
//                    jboxTip("项目负责人不超过2人!");
//                    return false;
//                }
            } else if ("joinUser" == tb) {
                var length = $("#" + tb + "Tb").children().length;
                /* if (length > 14) {
                 jboxTip("项目成员不超过15人!");
                 return false;
                 }*/
            }
            $("#" + tb + "Tb").append($("#" + tb + "Template tr:eq(0)").clone());

            var length = $("#" + tb + "Tb").children().length;
            //序号
            $("#" + tb + "Tb").children("tr").last().children("td").eq(1).text(length);
        }

        function delTr(tb) {
            //alert("input[name="+tb+"Ids]:checked");
            var checkboxs = $("input[name='" + tb + "Ids']:checked");
            if (checkboxs.length == 0) {
                jboxTip("请勾选要删除的！");
                return false;
            }
            jboxConfirm("确认删除?", function () {
                var trs = $('#' + tb + 'Tb').find(':checkbox:checked');
                $.each(trs, function (i, n) {
                    $(n).parent('td').parent("tr").remove();
                });
                //删除后序号
                var serial = 0;
                $("." + tb + "Serial").each(function () {
                    serial += 1;
                    $(this).text(serial);
                });
            });
        }
    </script>
</c:if>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
      id="projForm">
    <input type="hidden" id="pageName" name="pageName" value="step6"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>


    <font style="font-size: 14px; font-weight:bold;color: #333; ">项目承担单位、参加单位及主要研究开发人员</font>

    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th style="width: 15%">项目承担单位：</th>
            <td>
                <input type="text" class="inputText" name="mainOrgName" style="width: 50%" value="${resultMap.mainOrgName}"/>
            </td>
        </tr>
        <tr>
            <th style="width: 15%">项目参加单位：</th>
            <td>
                <input type="text" class="inputText" name="partakeOrgName" style="width: 50%" value="${resultMap.partakeOrgName}"/>
            </td>
        </tr>
    </table>
    <!-- 主要研究人员 -->
    <table class="basic" style="width: 100%;margin-top: 10px; border-bottom-style: none;">
        <tr>
            <th style="text-align: left" colspan="11" class="theader">项目负责人<%--（不超过2人）--%>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
					<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                         style="cursor: pointer;" onclick="add('mainResearcher')"></img>&#12288;
					<img title="删除" style="cursor: pointer;"
                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                         onclick="delTr('mainResearcher');"></img></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                <td width="4%"></td>
            </c:if>
            <th style="text-align: center" width="5%">序号</th>
            <th style="text-align: center" width="15%">姓名</th>
            <th style="text-align: center" width="8%">性别</th>
            <th style="text-align: center" width="8%">年龄</th>
            <th style="text-align: center" width="15%">职称/职务</th>
            <th style="text-align: center" width="15%">从事专业</th>
            <th style="text-align: center" width="20%">为本项目工作时间</th>
            <th style="text-align: center" width="15%">所在单位</th>
        </tr>
        <tbody id="mainResearcherTb">
        <c:if test="${not empty resultMap.mainResearcher}">
            <c:forEach var="mr" items="${resultMap.mainResearcher}" varStatus="status">
                <tr>
                    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                        <td width="4%" style="text-align: center;"><input name="mainResearcherIds" type="checkbox"/>
                        </td>
                    </c:if>
                    <td width="5%" style="text-align: center;" class="mainResearcherSerial">${status.count}</td>

                    <td>
                        <input type="text" name="mainResearcher_name"
                               value="<c:if test="${empty mr.objMap.mainResearcher_name}">${proj.applyUserName}</c:if>${mr.objMap.mainResearcher_name}" class="inputText" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="mainResearcher_sex" class="inputText" style="width: 80%;">
                            <option value="">请选择</option>
                            <c:forEach var="sex" items="${userSexEnumList}">
                                <c:if test="${sex.id != userSexEnumUnknown.id}">
                                    <option value="${sex.name}"
                                            <c:if test="${mr.objMap.mainResearcher_sex eq sex.name}">selected="selected"</c:if>>${sex.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input class="inputText" type="text" name="mainResearcher_age"
                               value="${mr.objMap.mainResearcher_age}" style="width: 80%;"/>
                    </td>
                    <td>
                       <%-- <input type="text" name="mainResearcher_title" value="${mr.objMap.mainResearcher_title}"
                               class="inputText" style="width: 80%"/>--%>
                        <select class="inputText" name="mainResearcher_title" style="width: 80%">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserTitleList}" var="post">
                                <option value="${post.dictName}" <c:if test='${mr.objMap.mainResearcher_title==post.dictName}'>selected="selected"</c:if>>${post.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input class="inputText" type="text" name="mainResearcher_major"
                               value="${mr.objMap.mainResearcher_major}" style="width: 80%;"/>
                    </td>

                    <td>
                        <input type="text" name="mainResearcher_time" value="${mr.objMap.mainResearcher_time}"
                               class="inputText" style="width: 80%"/>
                    </td>
                    <td>
                        <input class="inputText" type="text" name="mainResearcher_org"
                               value="${mr.objMap.mainResearcher_org}" style="width: 80%;"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>


    <!-- 参加人员 -->
    <table class="basic" style="width: 100%;margin-top: 10px; border-bottom-style: none;">
        <tr>
            <th colspan="9" style="text-align: left" class="theader">项目成员
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
					<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                         style="cursor: pointer;" onclick="add('joinUser')"></img>&#12288;
					<img title="删除" style="cursor: pointer;"
                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                         onclick="delTr('joinUser');"></img></span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                <th style="text-align: center" width="4%"></th>
            </c:if>
            <th style="text-align: center" width="5%">序号</th>
            <th style="text-align: center" width="15%">姓名</th>
            <th style="text-align: center" width="8%">性别</th>
            <th style="text-align: center" width="8%">年龄</th>
            <th style="text-align: center" width="15%">职称/职务</th>
            <th style="text-align: center" width="15%">从事专业</th>
            <th style="text-align: center" width="20%">为本项目工作时间</th>
            <th style="text-align: center" width="15%">所在单位</th>
        </tr>
        <tbody id="joinUserTb">
        <c:if test="${not empty resultMap.joinUser}">
            <c:forEach var="ju" items="${resultMap.joinUser}" varStatus="status">
                <tr>
                    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                        <td width="4%" style="text-align: center;"><input name="joinUserIds" type="checkbox"/>
                        </td>
                    </c:if>
                    <td width="5%" style="text-align: center;" class="joinUserSerial">${status.count}</td>

                    <td>
                        <input type="text" name="joinUser_name"
                               value="${ju.objMap.joinUser_name}" class="inputText" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="joinUser_sex" class="inputText" style="width: 80%;">
                            <option value="">请选择</option>
                            <c:forEach var="sex" items="${userSexEnumList}">
                                <c:if test="${sex.id != userSexEnumUnknown.id}">
                                    <option value="${sex.name}"
                                            <c:if test="${ju.objMap.joinUser_sex eq sex.name}">selected="selected"</c:if>>${sex.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input class="inputText" type="text" name="joinUser_age"
                               value="${ju.objMap.joinUser_age}" style="width: 80%;"/>
                    </td>
                    <td>
                       <%-- <input type="text" name="joinUser_title" value="${ju.objMap.joinUser_title}"
                               class="inputText" style="width: 80%"/>--%>
                        <select class="inputText" name="joinUser_title" style="width: 80%">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserTitleList}" var="post">
                                <option value="${post.dictName}" <c:if test='${ju.objMap.joinUser_title==post.dictName}'>selected="selected"</c:if>>${post.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input class="inputText" type="text" name="joinUser_major"
                               value="${ju.objMap.joinUser_major}" style="width: 80%;"/>
                    </td>
                    <td>
                        <input type="text" name="joinUser_time" value="${ju.objMap.joinUser_time}"
                               class="inputText" style="width: 80%"/>
                    </td>
                    <td>
                        <input class="inputText" type="text" name="joinUser_org"
                               value="${ju.objMap.joinUser_org}" style="width: 80%;"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>

<!-- -------------------------------------------------------------------------------------------------------->

<div style="display: none">
    <!-- 主要研究人员 -->
    <table class="basic" id="mainResearcherTemplate" style="width: 100%">
        <tr>
           <td width="4%" style="text-align: center;"><input name="mainResearcherIds" type="checkbox"/>
            </td>
            <td width="5%" style="text-align: center;" class="mainResearcherSerial"></td>
            <td>
                <input type="text" name="mainResearcher_name" value="${proj.applyUserName}" class="inputText" style="width: 80%"/>
            </td>
            <td>
                <select name="mainResearcher_sex" class="inputText" style="width: 80%;">
                    <option value="">请选择</option>
                    <c:forEach var="sex" items="${userSexEnumList}">
                        <c:if test="${sex.id != userSexEnumUnknown.id}">
                            <option value="${sex.name}">${sex.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input class="inputText" type="text" name="mainResearcher_age" style="width: 80%;"/>
            </td>
            <td>
                <select class="inputText" name="mainResearcher_title" style="width: 80%">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserTitleList}" var="post">
                        <option value="${post.dictName}" <c:if test='${mr.objMap.mainResearcher_title==post.dictName}'>selected="selected"</c:if>>${post.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input class="inputText" type="text" name="mainResearcher_major"  style="width: 80%;"/>
            </td>
            <td>
                <input type="text" name="mainResearcher_time" class="inputText" style="width: 80%"/>
            </td>
            <td>
                <input class="inputText" type="text" name="mainResearcher_org" style="width: 80%;"/>
            </td>


        </tr>
    </table>


    <!-- 参加人员 -->
    <table class="basic" id="joinUserTemplate" style="width: 100%">
        <tr>
            <td width="4%" style="text-align: center;"><input name="joinUserIds" type="checkbox"/></td>
            <td width="5%" style="text-align: center;" class="joinUserSerial"></td>
            <td>
                <input type="text" name="joinUser_name"
                       value="${ju.objMap.joinUser_name}" class="inputText" style="width: 80%"/>
            </td>
            <td>
                <select name="joinUser_sex" class="inputText" style="width: 80%;">
                    <option value="">请选择</option>
                    <c:forEach var="sex" items="${userSexEnumList}">
                        <c:if test="${sex.id != userSexEnumUnknown.id}">
                            <option value="${sex.name}" >${sex.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input class="inputText" type="text" name="joinUser_age" style="width: 80%;"/>
            </td>
            <td>
                <%--<input type="text" name="joinUser_title" class="inputText" style="width: 80%"/>--%>
                    <select class="inputText" name="joinUser_title" style="width: 80%">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumUserTitleList}" var="post">
                            <option value="${post.dictName}" <c:if test='${ju.objMap.joinUser_title==post.dictName}'>selected="selected"</c:if>>${post.dictName}</option>
                        </c:forEach>
                    </select>
            </td>
            <td>
                <input class="inputText" type="text" name="joinUser_major" style="width: 80%;"/>
            </td>
            <td>
                <input type="text" name="joinUser_time" class="inputText" style="width: 80%"/>
            </td>
            <td>
                <input class="inputText" type="text" name="joinUser_org" style="width: 80%;"/>
            </td>
        </tr>
    </table>
</div>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step7')" class="search" value="下一步"/>
    </div>
</c:if>

