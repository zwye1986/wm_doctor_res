<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
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
    <script type="text/javascript">
        function reupload() {
            $("#fileName,#reupload").hide();
            $("#file").show().attr("disabled", false);
            $("#declarationFormFlow").attr("disabled", true);
            $("#cancel").show();
        }

        function reCheck(obj) {
            $(obj).hide();
            $("#down").hide();
            $("#file").show();
        }

        function saveOrg() {
            if (!$("#addForm").validationEngine("validate")) {
                return;
            }
            var fstuProj = $("#addForm").serializeJson();
            var fileTr = $("#projFileTb").children();
            var fileDatas = [];
            $.each(fileTr, function (i, n) {
                var fileFlow = $(n).find("input[name='fileFlow']").val();
                var fileRemark = $(n).find("input[name='fileRemark']").val();
                var pubFlie = {
                    "fileFlow": fileFlow,
                    "fileRemark": fileRemark
                };
                fileDatas.push(pubFlie);
            });
            var requestData = {'fileList': fileDatas,'fstuProj': fstuProj};
            $("#jsondata").val(JSON.stringify(requestData));
            var url = "<s:url value='/fstu/dec/save'/>";
            jboxSubmit($("#addForm"), url, function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.document.mainIframe.search();
                    jboxClose();
                }
            });
        }

        function cancel() {
            $("#fileName,#reupload").show();
            $("#file").hide().attr("disabled", true);
            $("#declarationFormFlow").attr("disabled", false);
            $("#cancel").hide();
        }

        $(function () {
            if ('${viewFlag}' == 'view') {
                $("#look input").attr("disabled", "disabled");
                $("#look select").attr("disabled", "disabled");
                $("#look textarea").attr("disabled", "disabled");
                $("input[name='fileRemark']").attr("disabled", "disabled");
                $(".reCheck").css("display", "none");
                if ('${canDown}' != 'canDown') {
                    $("a[id=down]").css({color: "gray", cursor: "default"}).removeAttr("href");
                }
            }
        })


        function addFile(tb) {
            if ($("#projFileTb tr").length > 9) {
                jboxTip("附件总数不能超过10个！");
                return false;
            }
            var html = '<tr>' +
                    '<td><input type="file" id="file" name="files" class="validate[required]"/></td>' +
                    '<td><input class="validate[required,maxSize[100]] xltext" style="width: 97%;" name="fileRemark" type="text"/></td>' +
                    '<td><a class="reCheck" href="javascript:void(0);" onclick="delTr(this);">[删除]</a></td>' +
                    '</tr>';
            $('#' + tb).append(html);
        }

        function delTr(tb) {
            jboxConfirm("确认删除？", function () {
                $(tb).parent('td').parent("tr").remove();
            });
        }

        function delFile(obj,fileFlow){
            var url = '<s:url value="/pub/file/delFileByFlow?fileFlow="/>' + fileFlow;
            jboxConfirm("确认删除？", function () {
                jboxGet(url, null, function () {
                    var tr = $(obj).parent("td").parent("tr");
                    tr.remove();
                }, null, true);
            });
        }
    </script>
</head>
<body>
<div style="width:100%;height:100%;overflow: auto">
<form id="addForm" style="height: 100px;">
    <input id="jsondata" type="hidden" name="jsondata" value=""/>
    <input name="projFlow" value="${proj.projFlow}" type="hidden"/>
    <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
    <input name="orgName" type="hidden"
           value="<c:out value="${proj.orgName}" default="${sessionScope.currUser.orgName}"/>"/>
    <input name="orgFlow" type="hidden"
           value="<c:out value="${proj.orgFlow}" default="${sessionScope.currUser.orgFlow}"/>"/>
    <div class="content">
        <div class="title1 clearfix">
            <table width="100%" cellpadding="0" cellspacing="0" class="basic" id="look">
                <tr>
                    <th width="20%"><span class="red">*</span>年份：</th>
                    <td width="30%"><input type="text" class="validate[required] xltext ctime" name="projYear"
                                           value="${proj.projYear}" readonly="readonly"
                                           onClick="WdatePicker({dateFmt:'yyyy'})"/></td>
                    <th width="20%"><span class="red">*</span>项目级别：</th>
                    <td width="30%">
                        <select name="projLevelName" class="xlselect validate[required]">
                            <option/>
                            <option <c:if test="${proj.projLevelName eq '国家'}">selected</c:if>>国家</option>
                            <option <c:if test="${proj.projLevelName eq '省级'}">selected</c:if>>省级</option>
                            <option <c:if test="${proj.projLevelName eq '其他'}">selected</c:if>>其他</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>项目名称：</th>
                    <td colspan="3">
                        <input class="validate[required] xltext" style="width: 89%" name="projName" type="text" value="${proj.projName}"/>
                    </td>
                </tr>
                <tr>
                    <th>项目编号：</th>
                    <td>
                        <input type="text" name="projNo" value="${proj.projNo}" class="xltext"/>
                    </td>
                    <th><span class="red">*</span>项目类型：</th>
                    <td>
                        <input class="validate[required] xltext" name="projTypeId" type="text" value="${proj.projTypeId}"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>所属学科：</th>
                    <td>
                        <select class="xlselect validate[required]" name="projSubjectId">
                            <option/>
                            <c:forEach items="${dictTypeEnumFstuProjSubjectList}" var="dict">
                                <option value="${dict.dictId}"
                                        <c:if test="${dict.dictId eq proj.projSubjectId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>实施方式：</th>
                    <td>
                        <input class="xltext validate[maxSize[50]]" name="practiceMode" type="text" value="${proj.practiceMode}"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>主办单位：</th>
                    <td>
                        <input class="xltext validate[required]" name="applyOrgName" type="text" value="${proj.applyOrgName}"/>
                    </td>
                    <th>承办单位：</th>
                    <td>
                        <input class="xltext" name="acceptOrgName" type="text" value="${proj.acceptOrgName}"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>项目负责人：</th>
                    <td width="30%"><input class="xltext validate[required]" type="text" name="applyUserName"
                                           value="${proj.applyUserName}" id="applyUserName"
                                           onchange="check(this.value)"/></td>
                    <th>负责人工号：</th>
                    <td>
                        <input type="text" class="xltext" name="applyUserFlow" id="applyUserFlow" value="${proj.applyUserFlow}">
                    </td>
                    <script>
                        function check(name) {
                            var userMap =  function(data) {return data}(${userMap})||{};
                            $("#applyUserFlow").val(userMap[name]);
                        }
                    </script>
                </tr>
                <tr>
                    <th>负责人电话：</th>
                    <td>
                        <input type="text" name="projPhone" value="<c:if test="${roleFlag eq 'admin'}">${proj.projPhone}</c:if>" class="xltext"/>
                    </td>
                    <th>负责人邮箱：</th>
                    <td>
                        <input type="text" name="projEmail" value="${proj.projEmail}"
                               class="xltext validate[custom[email]]"/>
                    </td>
                </tr>
                <tr>
                    <th>联络人姓名：</th>
                    <td>
                        <input type="text" name="attenName" value="${proj.attenName}" class="xltext"/>
                    </td>
                    <th>联络人电话：</th>
                    <td>
                        <input type="text" name="attenPhone" value="${proj.attenPhone}" class="xltext"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>起止时间：</th>
                    <td colspan="3">
                        <input type="text" style="width: 35%" class="validate[required] xltext ctime" name="projStartTime"
                               value="${proj.projStartTime}" readonly="readonly"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/> --
                        <input type="text" style="width: 35%" class="validate[required] xltext ctime" name="projEndTime"
                               value="${proj.projEndTime}" readonly="readonly"
                               onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>举办地点：</th>
                    <td>
                        <input type="text" name="projHoldAddress" value="${proj.projHoldAddress}"
                               class="validate[required] xltext"/>
                    </td>
                    <th>学分类别：</th>
                    <td>
                        <select class="xlselect" name="acascoreTypeId">
                            <option/>
                            <c:forEach items="${dictTypeEnumScoreTypeList}" var="dict">
                                <option value="${dict.dictId}"
                                        <c:if test="${dict.dictId eq proj.acascoreTypeId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>申请学分：</th>
                    <td>
                        <input type="text" name="applyScore" value="${proj.applyScore}" class="validate[required] xltext"/>
                    </td>
                    <th><span class="red">*</span>教学对象：</th>
                    <td>
                        <select class="validate[required] xlselect" name="teachingObjectId">
                            <option/>
                            <c:forEach items="${dictTypeEnumFstuProjTeachingObjectList}" var="dict">
                                <option value="${dict.dictId}"
                                        <c:if test="${dict.dictId eq proj.teachingObjectId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>拟招生人数：</th>
                    <td>
                        <input type="text" name="recruitNum" value="${proj.recruitNum}"
                               class="validate[custom[integer],required] xltext"/>
                    </td>
                    <th></th>
                    <td></td>
                </tr>

                <tr>
                    <th>备注：</th>
                    <td colspan="3">
                        <textarea name="remark" class="xltxtarea" style="margin-left: 0px;width: 90%">${proj.remark}</textarea>
                    </td>
                </tr>
            </table>
            <table class="basic" style="width:100%;">
                <thead>
                <tr>
                    <th colspan="4" class="bs_name">附件上传（上传的附件为证书扫描件）
                        <c:if test="${viewFlag != 'view'}">
                                    <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img
                                            title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                            style="cursor: pointer;" onclick="addFile('projFileTb')"/></a></span>
                        </c:if>
                    </th>
                </tr>
                <tr>
                    <td width="30%" style="font-weight:bold;">附件名称</td>
                    <td width="40%" style="font-weight:bold;">附件内容</td>
                    <%--<c:if test="${viewFlag != 'view' }">--%>
                    <td width="20%" style="font-weight:bold;">附件操作</td>
                    <%--</c:if>--%>
                </tr>
                </thead>
                <tbody id="projFileTb">
                <c:forEach var="file" items="${fileList}" varStatus="status">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${not empty file.fileFlow}">
                                    <a id="down"
                                       href='<s:url value="/fstu/dec/fileDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                    <input type="file" id="file" name="files" style="display: none;"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="file" id="file" name="files"/>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                        </td>
                        <td>
                            <input class="validate[required,maxSize[100]] xltext" style="width: 97%;"
                                   name="fileRemark" type="text" value="${file.fileRemark}"/>
                        </td>
                        <%--<c:if test="${viewFlag != 'view' }">--%>
                        <td>
                            <a class="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>&#12288;
                            <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                        </td>
                        <%--</c:if>--%>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="button" style="width: 100%;">
                <c:if test="${!(viewFlag eq 'view')}">
                    <input class="search" type="button" value="保&#12288;存" onclick="saveOrg();"/>
                </c:if>
                <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </div>
    </div>
</form>
    </div>
</body>
</html>