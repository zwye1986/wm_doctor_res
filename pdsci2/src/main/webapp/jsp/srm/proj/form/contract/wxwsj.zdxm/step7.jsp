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
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }

        function add(tb) {
            if ("mainResearcher" == tb) {
                var length = $("#" + tb + "Tb").children().length;
                if (length > 1) {
                    jboxTip("项目负责人不超过2人!");
                    return false;
                }
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
    <input type="hidden" id="pageName" name="pageName" value="step7"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>


    <font style="font-size: 14px; font-weight:bold;color: #333; ">七、项目工作组成员</font>

    <!-- 主要研究人员 -->
    <table class="bs_tb" style="width: 100%;margin-top: 10px; border-bottom-style: none;">
        <tr>
            <th colspan="11" class="theader">项目负责人（不超过2人）
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
            <td width="5%">序号</td>
            <td width="15%">姓名</td>
            <td width="8%">性别</td>
            <td width="8%">年龄</td>
            <td width="15%">职称(职务)</td>
            <td width="15%">单位（部门）</td>
            <td width="20%">项目分工</td>
            <td width="15%">联系电话</td>
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
                                   value="${mr.objMap.mainResearcher_name}" class="inputText" style="width: 80%"/>
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
                        <input class="inputText" type="text" name="mainResearcher_major"
                               value="${mr.objMap.mainResearcher_major}" style="width: 80%;"/>
                    </td>
                    <td>
                        <input class="inputText" type="text" name="mainResearcher_dept"
                               value="${mr.objMap.mainResearcher_dept}" style="width: 80%;"/>
                    </td>
                    <td>
                        <input type="text" name="mainResearcher_work" value="${mr.objMap.mainResearcher_work}"
                               class="inputText" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" name="mainResearcher_phone" value="${mr.objMap.mainResearcher_phone}"
                               class="inputText" style="width: 80%"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>


    <!-- 参加人员 -->
    <table class="bs_tb" style="width: 100%;margin-top: 10px; border-bottom-style: none;">
        <tr>
            <th colspan="11" class="theader">项目成员
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
                <td width="4%"></td>
            </c:if>
            <td width="5%">序号</td>
            <td width="15%">姓名</td>
            <td width="8%">性别</td>
            <td width="8%">年龄</td>
            <td width="15%">职称(职务)</td>
            <td width="15%">单位（部门）</td>
            <td width="20%">项目分工</td>
            <td width="15%">联系电话</td>
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
                        <input class="inputText" type="text" name="joinUser_major"
                               value="${ju.objMap.joinUser_major}" style="width: 80%;"/>
                    </td>
                    <td>
                        <input class="inputText" type="text" name="joinUser_dept"
                               value="${ju.objMap.joinUser_dept}" style="width: 80%;"/>
                    </td>
                    <td>
                        <input type="text" name="joinUser_work" value="${ju.objMap.joinUser_work}"
                               class="inputText" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" name="joinUser_phone" value="${ju.objMap.joinUser_phone}"
                               class="inputText" style="width: 80%"/>
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
                    <input type="text" name="mainResearcher_name" class="inputText" style="width: 80%"/>
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
                <input class="inputText" type="text" name="mainResearcher_major"  style="width: 80%;"/>
            </td>
            <td>
                <input class="inputText" type="text" name="mainResearcher_dept" style="width: 80%;"/>
            </td>
            <td>
                <input type="text" name="mainResearcher_work" class="inputText" style="width: 80%"/>
            </td>
            <td>
                <input type="text" name="mainResearcher_phone"  class="inputText" style="width: 80%"/>
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
                <input class="inputText" type="text" name="joinUser_major" style="width: 80%;"/>
            </td>
            <td>
                <input class="inputText" type="text" name="joinUser_dept" style="width: 80%;"/>
            </td>
            <td>
                <input type="text" name="joinUser_work" class="inputText" style="width: 80%"/>
            </td>
            <td>
                <input type="text" name="joinUser_phone" class="inputText" style="width: 80%"/>
            </td>
        </tr>
    </table>
</div>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="下一步"/>
    </div>
</c:if>

		