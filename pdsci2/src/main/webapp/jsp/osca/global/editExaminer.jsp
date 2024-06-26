
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
        <jsp:param name="jquery_cxselect" value="false"/>
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

    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

    <style type="text/css">
        .table {
            border: 1px solid #e3e3e3;
        }
        .table tr:nth-child(2n) {
            background-color: #fcfcfc;
            transition: all 0.125s ease-in-out 0s;
        }
        .table tr:hover {
            background: #fbf8e9 none repeat scroll 0 0;
        }
        .table th, .table td {
            border-bottom: 1px solid #e3e3e3;
            border-right: 1px solid #e3e3e3;
            text-align: center;
        }
        .table th {
            background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
            color: #585858;
            height: 30px;
        }
        .table td {
            height: 30px;
            line-height: 25px;
            text-align: center;
            word-break: break-all;
        }
    </style>
    <script type="text/javascript">

        String.prototype.htmlFormartById = function (data) {
            var html = this;
            for (var key in data) {
                var reg = new RegExp('\\{' + key + '\\}', 'g');
                html = html.replace(reg, data[key]);
            }
            return html;
        }

        function add(tb) {
            var cloneTr = $("#" + tb + "Template tr:eq(0)").clone();
            var index = $("#" + tb + "Tb tr").length;
            cloneTr.html(cloneTr.html().htmlFormartById({"index": index}));
            $("#" + tb + "Tb").append(cloneTr);

        }

        function delTr(obj) {
//            var checkboxs = $("input[name='" + tb + "Ids']:checked");
//
//            if (checkboxs.length == 0) {
//                jboxTip("请勾选要删除的！");
//                return false;
//            }
            $(obj).parent().parent().remove();
//            jboxConfirm("确认删除?", function () {
//                var trs = $('#' + tb + 'Tb').find(':checkbox:checked');
//                $.each(trs, function (i, n) {
//                    $(n).parent().parent().remove();
//                });

//                var reg = new RegExp('\\[\\d+\\]', 'g');
//                $("#" + tb + "Tb tr").each(function (i) {
//                    $("[name]", this).each(function () {
//                        this.name = this.name.replace(reg, "[" + i + "]");
//                    });
//                });
//            });


        }

        function linkageSubject(dictId){
//            $('#trainingSpeId').val("");//清空上次展现数据
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
        }

        $(document).ready(function(){
            <c:if test="${not empty typeSpeList}">
                linkageSubject('${typeSpeList[0].trainingTypeId}');
            </c:if>
            <c:if test="${empty typeSpeList}">
                linkageSubject('');
            </c:if>
        });

        function saveOscaExam(userFlow){
            if(false==$("#oscaExaminer").validationEngine("validate")){
                return false;
            }
            jboxConfirm("确认保存？",function(){
                var userCode=$("input[name='userCode']").val();
                var userName=$("input[name='userName']").val();
                var sexId=$("select[name='sexId']").val();
                var titleId=$("select[name='titleId'] option:checked").val();
                var titleName=$("select[name='titleName'] option:checked").text();
                var userPhone=$("input[name='userPhone']").val();
                var workOrgName=$("input[name='workOrgName']").val();
                var orgFlow=$("select[name='orgFlow']").val();
                var data = {
                    userFlow:userFlow,
                    userCode:userCode,
                    userName:userName,
                    sexId:sexId,
                    titleId:titleId,
                    titleName:titleName,
                    userPhone:userPhone,
                    orgFlow:orgFlow
                };
                <%--var url = "<s:url value='/osca/oscaExaminerManage/editExam'/>?workOrgName="+encodeURIComponent(encodeURIComponent(workOrgName))+"&manage=${manage}";--%>
                var url = "<s:url value='/osca/oscaExaminerManage/editExam'/>?manage=${manage}";
                jboxStartLoading();
                jboxPost(url, $("#oscaExaminer").serialize(),function(resp){
                    if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                        top.jboxTip(resp);
                        window.parent.frames['mainIframe'].location.reload(true);
                        top.jboxClose();
                    }
                    if(resp == "${GlobalConstant.USER_CODE_REPETE}"){
                        top.jboxTip("该用户名已存在，无法添加！");
                    }
                    if(resp == "${GlobalConstant.OPERATE_FAIL}"){
                        top.jboxTip("分配考点与该用户所在考点不符，无法修改！");
                    }
                }, null, false);
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <div <%--id="base"--%>>

                    <form id="oscaExaminer" style="position: relative;">
                    <input id="jsondata" type="hidden" name="jsondata" value=""/>
                    <input type="hidden" name="userFlow" value="${sysUser.userFlow}"/>

                    <table  class="xllist" <%--style="width:50%; position:relative;margin-left:200px"--%>>
                        <tr>
                            <th  style="text-align: right;"><span class="red">*</span>用&ensp;户&ensp;名：</th>
                            <td  style="text-align: left;"><input class="validate[required] qtext" style="border: 1;margin-left: 8px;" type="text" name="userCode" value="${sysUser.userCode}"/></td>
                            <th  style="text-align: right;"><span class="red">*</span>姓&#12288;&#12288;名：</th>
                            <td  style="text-align: left;"><input class="validate[required] qtext" style="border: 1;margin-left: 8px;" type="text" name="userName" value="${sysUser.userName}"/></td>
                        </tr>
                        <tbody id="trainingTb">
                        <c:if test="${empty typeSpeList}">
                        <tr>
                            <th style="text-align: right;"><span class="red">*</span>考核人员类型：</th>
                            <td  style="text-align: left;">
                                <select style="border:1;margin-left: 8px;" name="typeSpeList[0].trainingTypeId" class="validate[required] qselect" onchange="linkageSubject(this.value)">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                                        <option value="${dict.dictId}">${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th style="text-align: right;"><span class="red">*</span>考核专业：</th>
                            <td style="text-align: left;">
                                <c:set value="OscaTrainingType.${empty typeSpe.trainingTypeId?param.trainingTypeId:typeSpe.trainingTypeId}" var="trainingTypeClass"></c:set>
                                <select style="border:1;margin-left: 8px;" id="trainingSpeId" name="typeSpeList[0].trainingSpeId" class="validate[required] qselect">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                                        <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
                                        <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                            <option class="${scope.dictTypeId}" value="${scope.dictId}" >${scope.dictName}</option>
                                        </c:forEach>
                                    </c:forEach>
                                </select>
                                <img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('training')"/>&#12288;
                            </td>
                        </tr>
                        </c:if>

                        <c:forEach items="${typeSpeList}" var="typeSpe" varStatus="status">
                        <tr>
                            <th  style="text-align: right;"><span class="red">*</span>考核人员类型：</th>
                            <td  style="text-align: left;">
                                <select style="border:1;margin-left: 8px;" name="typeSpeList[${status.index}].trainingTypeId" class="validate[required] qselect" onchange="linkageSubject(this.value)">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                                        <option value="${dict.dictId}" ${typeSpe.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <th  style="text-align: right;"><span class="red">*</span>考核专业：</th>
                            <td  style="text-align: left;">
                                <c:set value="OscaTrainingType.${empty typeSpe.trainingTypeId?param.trainingTypeId:typeSpe.trainingTypeId}" var="trainingTypeClass"></c:set>
                                <select style="border:1;margin-left: 8px;" id="trainingSpeId" name="typeSpeList[${status.index}].trainingSpeId" class="validate[required] qselect">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                                        <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
                                        <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                            <option class="${scope.dictTypeId}" value="${scope.dictId}" ${(typeSpe.trainingSpeId eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
                                        </c:forEach>
                                    </c:forEach>
                                </select>
                                <c:choose>
                                    <c:when test="${status.index eq 0}">
                                        <img class="opBtn" title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('training')"/>&#12288;
                                    </c:when>
                                    <c:otherwise>
                                        <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr(this);"/>
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        </c:forEach>

                        </tbody>

                        <tr>
                            <th  style="text-align: right;">性&#12288;&#12288;别：</th>
                            <td  style="text-align: left;">
                                <select  name="sexId" style="border:1;margin-left: 8px;" class="qselect">
                                    <option value="Man" <c:if test="${'Man' == sysUser.sexId}">selected</c:if> >男
                                        <%--<input class="input" name="sexName" value="男" type="hidden"/>--%>
                                    </option>
                                    <option value="Woman" <c:if test="${'Woman' == sysUser.sexId}">selected</c:if> >女
                                        <%--<input class="input" name="sexName" value="女" type="hidden"/>--%>
                                    </option>
                                </select>
                            </td>
                            <th  style="text-align: right;">职&#12288;&#12288;称：</th>
                            <td  style="text-align: left;">
                                <select name="titleId" style="border:1;margin-left: 8px;" class="qselect">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumOscaUserTitleList}" var="dict">
                                        <option value="${dict.dictId}" ${dict.dictId eq sysUser.titleId?'selected':''}>${dict.dictName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th  style="text-align: right;">联系方式：</th>
                            <td  style="text-align: left;"><input class="qtext" style="border: 1;margin-left: 8px;" name="userPhone" type="text" value="${sysUser.userPhone}"/></td>
                            <th  style="text-align: right;">所在单位：</th>
                            <td  style="text-align: left;"><input class="qtext" style="border: 1;margin-left: 8px;" name="workOrgName" type="text" value="${sysUser.workOrgName}"/></td>
                        </tr>
                        <tr>
                            <th  style="text-align: right;">所在考点：</th>
                            <td  style="text-align: left;" colspan="3">
                            <c:choose>
                                 <c:when test="${manage eq 'manage'}">
                                 <select name="orgFlow" class="qselect" style="border: 1;margin-left: 8px;" disabled="disabled">
                                                <option value="">${orgName}</option>
                                 </select>
                                 </c:when>
                                 <c:otherwise>
                                     <select name="orgFlow"  class="qselect" style="border: 1;margin-left: 8px;">
                                       <option value="">请选择</option>
                                       <c:forEach var="org" items="${orgSpeList}">
                                       <option value="${org.orgFlow}"
                                            <c:if test="${org.orgFlow eq sysUser.orgFlow}">selected</c:if>>${org.orgName}
                                                            <%--<input class="input" name="orgName" value="${org.orgName}" type="hidden"/>--%>
                                       </option>
                                       </c:forEach>
                                     </select>
                                 </c:otherwise>
                            </c:choose>
                            </td>
                        </tr>
                    </table>
                    </form>
                    <br/>
                    <div  style="text-align: center;" colspan="2" style="height:100px;">
                        <input class="search" type="button" value='保&#12288;存' onclick="saveOscaExam('${userFlow}')" />
                    </div>
            </div>
        </div>
    </div>
</div>
<div style="display: none">
    <table id="trainingTemplate">
        <tr>
            <th style="text-align: right;"><span class="red">*</span>考核人员类型：</th>
            <td style="text-align: left;">
                <select style="border:1;margin-left: 8px;" name="typeSpeList[{index}].trainingTypeId" class="validate[required] qselect" onchange="linkageSubject(this.value)">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                        <option value="${dict.dictId}" ${param.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                    </c:forEach>
                </select>
            </td>
            <th style="text-align: right;"><span class="red">*</span>考核专业：</th>
            <td style="text-align: left;">
                <%--<c:set value="OscaTrainingType.${param.trainingTypeId}" var="trainingTypeClass"></c:set>--%>
                <select style="border:1;margin-left: 8px;" id="trainingSpeId" name="typeSpeList[{index}].trainingSpeId" class="validate[required] qselect">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                        <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
                        <c:forEach items="${applicationScope[dictKey]}" var="scope">
                            <option class="${scope.dictTypeId}" value="${scope.dictId}" ${(param.trainingSpeId eq scope.dictId && trainingTypeClass eq scope.dictTypeId)?'selected':''}>${scope.dictName}</option>
                        </c:forEach>
                    </c:forEach>
                </select>

                <img class="opBtn" title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr(this);"/>
                    <%--<input type="checkbox" name="trainingIds"/>--%>
            </td>
        </tr>
    </table>
</div>

</body>
</html>