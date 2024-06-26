<head>
    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <script type="text/javascript">
            function nextOpt(step){
                if(false==$("#projForm").validationEngine("validate")){
                    return false;
                }
                var form = $('#projForm');
                form.append("<input name='nextPageName' value='"+step+"' type='hidden'/>");
                $('#nxt').attr({"disabled":"disabled"});
                $('#prev').attr({"disabled":"disabled"});
                jboxStartLoading();
                form.submit();
            }

            function add(templateId,total) {
                if (templateId) {
                    if ($('.' + templateId + ' .toDel').length < total) {
                        $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                        reSeq(templateId);
                    } else {
                        jboxTip('该项最多新增'+total+'条！');
                    }
                }
            }

            function del(templateId) {
                if (templateId) {
                    if (!$('.' + templateId + ' .toDel:checked').length) {
                        return jboxTip('请选择需要删除的项目！');
                    }
                    jboxConfirm('确认删除？', function () {
                        $('.' + templateId + ' .toDel:checked').closest('tr').remove();
                        reSeq(templateId);
                    }, null);
                }
            }

            function reSeq(templateId) {
                $('.' + templateId + ' .seq').each(function (i, n) {
                    $(n).text(i + 1);
                });
            }
            $(function () {
                $('#template tr').each(function(){
                    var id = this.id;
                    if(id){
                        if(!$('.'+id+' .toDel').length){
                            add(id,1);
                        }
                    }
                });
            });
        </script>
    </c:if>
</head>
<body>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step5" />
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333; ">四、项目组主要参加人员名单</font>

    <table class="bs_tb" style="width: 100%;margin-top: 10px;">
        <tr>
            <th style="text-align: center" width="25px"></td>
            <th style="text-align: center" width="25px">序号</th>
            <th style="width: 10%;text-align: center">姓名</th>
            <th style="width: 7%;text-align: center">性别</th>
            <th style="width: 8%;text-align: center">年龄</th>
            <th style="width: 15%;text-align: center">职称</th>
            <th style="width: 15%;text-align: center">从事专业</th>
            <th style="width: 20%;text-align: center">项目分工</th>
            <th style="width: 15%;text-align: center">所在科室</th>

        </tr>
        <tr>
            <th colspan="10" class="theader">
                项目负责人<font color="red">*</font>
            </th>
        </tr>
        <tr>
            <td style="text-align: center;"></td>
            <td class="seq">1</td>
            <td><input type="text" name="leader_userName" value="<c:if test="${empty resultMap.leader_userName}">${proj.applyUserName}</c:if>${resultMap.leader_userName}" class="inputText validate[required]" style="width: 90%"/></td>
            <td>
                <select name="leader_gender" class="validate[required] inputText" style="width: 80%;">
                    <option value="">请选择</option>
                    <c:forEach var="sex" items="${userSexEnumList}">
                        <c:if test="${sex.id != userSexEnumUnknown.id}">
                            <option value="${sex.name}"
                                    <c:if test="${resultMap.leader_gender eq sex.name}">selected="selected"</c:if>
                                    <c:if test="${(empty resultMap.leader_gender) and (applyMap.leader_gender eq sex.name)}">selected="selected"</c:if>
                            >${sex.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input class="inputText validate[required,custom[number]]" type="text" name="leader_age"
                       value="<c:if test="${empty resultMap.leader_age}">${applyMap.leader_age}</c:if>${resultMap.leader_age}" style="width: 80%"/>
            </td>
            <td>

                <select class="validate[required] inputText" name="leader_title" style="width: 80%">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                        <option value="${title.dictName}"
                                <c:if test='${resultMap.leader_title==title.dictName}'>selected="selected"</c:if>
                                <c:if test="${(empty resultMap.leader_title) and (applyMap.leader_title eq title.dictName)}">selected="selected"</c:if>
                        >${title.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type="text" name="leader_major" value="<c:if test="${empty resultMap.leader_major}">${applyMap.leader_major}</c:if>${resultMap.leader_major}" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="leader_labor" value="<c:if test="${empty resultMap.leader_labor}">${applyMap.leader_labor}</c:if>${resultMap.leader_labor}" class="inputText validate[required]" style="width: 90%"/></td>
            <td><input type="text" name="leader_dept" value="${resultMap.leader_dept}<c:if test="${(empty resultMap.leader_dept) and (param.view!=GlobalConstant.FLAG_Y)}">${applyMap.leader_dept}</c:if>" class="inputText validate[required]" style="width: 90%"/></td>
        </tr>
        <tr>
            <th colspan="13" class="theader">
                主要研究开发人员<font color="red">*</font>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('mainResearcher',99)"/>&#12288;
									<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('mainResearcher');"/></span>
                </c:if>
            </th>
        </tr>
        <tbody class="mainResearcher">
        <c:if test="${not empty resultMap.mainResearcher}">
            <c:forEach var="researcher" items="${resultMap.mainResearcher}" varStatus="status">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${status.count}</td>
                    <td><input type="text" name="mainResearcher_userName" value="${researcher.objMap.mainResearcher_userName}" class="inputText validate[required]" style="width: 90%"/></td>
                    <td>
                        <select name="mainResearcher_gender" class="validate[required] inputText" style="width: 80%;">
                            <option value="">请选择</option>
                            <c:forEach var="sex" items="${userSexEnumList}">
                                <c:if test="${sex.id != userSexEnumUnknown.id}">
                                    <option value="${sex.name}"
                                            <c:if test="${researcher.objMap.mainResearcher_gender eq sex.name}">selected="selected"</c:if>>${sex.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input class="inputText validate[required,custom[number]]" type="text" name="mainResearcher_age"
                               value="${researcher.objMap.mainResearcher_age}" style="width: 80%"/>
                    </td>
                    <td>
                        <select class="validate[required] inputText" name="mainResearcher_title" style="width: 80%">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                <option value="${title.dictName}"
                                        <c:if test='${researcher.objMap.mainResearcher_title==title.dictName}'>selected="selected"</c:if>>${title.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" name="mainResearcher_major" value="${researcher.objMap.mainResearcher_major}" class="inputText validate[required]" style="width: 90%"/></td>
                    <td><input type="text" name="mainResearcher_labor" value="${researcher.objMap.mainResearcher_labor}" class="inputText validate[required]" style="width: 90%"/></td>
                    <td><input type="text" name="mainResearcher_dept" value="${researcher.objMap.mainResearcher_dept}" class="inputText validate[required]" style="width: 90%"/></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty resultMap.mainResearcher}">
            <c:forEach var="researcher" items="${applyMap.mainResearcher}" varStatus="status">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${status.count}</td>
                    <td><input type="text" name="mainResearcher_userName" value="${researcher.objMap.mainResearcher_userName}" class="inputText validate[required]" style="width: 90%"/></td>
                    <td>
                        <select name="mainResearcher_gender" class="validate[required] inputText" style="width: 80%;">
                            <option value="">请选择</option>
                            <c:forEach var="sex" items="${userSexEnumList}">
                                <c:if test="${sex.id != userSexEnumUnknown.id}">
                                    <option value="${sex.name}"
                                            <c:if test="${researcher.objMap.mainResearcher_gender eq sex.name}">selected="selected"</c:if>>${sex.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input class="inputText validate[required,custom[number]]" type="text" name="mainResearcher_age"
                               value="${researcher.objMap.mainResearcher_age}" style="width: 80%"/>
                    </td>
                    <td>
                        <select class="validate[required] inputText" name="mainResearcher_title" style="width: 80%">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                <option value="${title.dictName}"
                                        <c:if test='${researcher.objMap.mainResearcher_title==title.dictName}'>selected="selected"</c:if>>${title.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td><input type="text" name="mainResearcher_major" value="${researcher.objMap.mainResearcher_major}" class="inputText validate[required]" style="width: 90%"/></td>
                    <td><input type="text" name="mainResearcher_labor" value="${researcher.objMap.mainResearcher_labor}" class="inputText validate[required]" style="width: 90%"/></td>
                    <td><input type="text" name="mainResearcher_dept" value="${researcher.objMap.mainResearcher_dept}" class="inputText validate[required]" style="width: 90%"/></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>

    </table>
    <div class="button" style="width: 100%;
    <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
        <input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="保&#12288;存"/>
    </div>

</form>
<table id="template" style="display: none">
    <tr id="mainResearcher">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td><input type="text" name="mainResearcher_userName" class="inputText validate[required]" style="width: 90%"/></td>
        <td>
            <select name="mainResearcher_gender" class="validate[required] inputText" style="width: 80%;">
                <option value="">请选择</option>
                <c:forEach var="sex" items="${userSexEnumList}">
                    <c:if test="${sex.id != userSexEnumUnknown.id}">
                        <option value="${sex.name}">${sex.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </td>
        <td>
            <input class="inputText validate[required,custom[number]]" type="text" name="mainResearcher_age"
                   value="${researcher.objMap.mainResearcher_age}" style="width: 80%"/>
        </td>
        <td>

            <select class="validate[required] inputText" name="mainResearcher_title" style="width: 90%">
                <option value="">请选择</option>
                <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                    <option value="${title.dictName}">${title.dictName}</option>
                </c:forEach>
            </select>
        </td>
        <td><input type="text" name="mainResearcher_major"  class="inputText validate[required]" style="width: 90%"/></td>
        <td><input type="text" name="mainResearcher_labor"  class="inputText validate[required]" style="width: 90%"/></td>
        <td><input type="text" name="mainResearcher_dept"  class="inputText validate[required]" style="width: 90%"/></td>
    </tr>
</table>
