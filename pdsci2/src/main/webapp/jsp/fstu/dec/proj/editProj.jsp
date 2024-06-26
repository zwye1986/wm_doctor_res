<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(function(){
            if('${proj.declareResult}' == "Passed"){
                $(".projNoTd").show();
            }else{
                $(".projNoTd").hide();
            }
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
        });
        function reCheck(obj) {
            $(obj).hide();
            $("#down").hide();
            $("#file").show();
        }

        function saveOrg() {
            if (!$("#addForm").validationEngine("validate")) {
                return;
            }
            $("input[name='projTypeName']").val($("#projTypeId option:selected").text());
            $("input[name='teachingObject']").val($("#teachingObjectId option:selected").text());
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
                    window.parent.frames['mainIframe'].$("#searchForm").submit();
                    window.location.reload();
                }
            });
        }
        function cancel() {
            $("#file").hide().attr("disabled", true);
        }
        function addFile(tb) {
            $("input[name='projName']").css("width","561px");
            $("input[name='applyOrgName']").css("width","561px");
            $("textarea[name='remark']").css("width","566px");
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
                if($("#projFileTb tr").length == 0){
                    $("input[name='projName']").css("width","569px");
                    $("input[name='applyOrgName']").css("width","569px");
                    $("textarea[name='remark']").css("width","575px");
                }
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
        function bindProjNo(value){
            if(value == "Passed"){
                $(".projNoTd").show();
            }else{
                $(".projNoTd").hide();
                $(".projNoTd input[name='projNo']").val('');
            }
        }
        //学科代码 BEGIN
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
                    nodes = zTree.getSelectedNodes(),
                    v = "";
            id = "";
            nodes.sort(function compare(a, b) {
                return a.id - b.id;
            });
            for (var i = 0, l = nodes.length; i < l; i++) {
                v += nodes[i].name + ",";
                id += nodes[i].id + ",";
            }
            if (v.length > 0) v = v.substring(0, v.length - 1);
            if (id.length > 0) id = id.substring(0, id.length - 1);
            var cityObj = $("#proSelect");
            $("#selectProjId").val(id);
            cityObj.attr("value", v);
        }
        function showMenu() {
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
        //学科代码 END
    </script>
</head>
<body>
<div style="width:100%;height:100%;overflow: auto">
<form id="addForm" style="height: 100px;">
    <input id="jsondata" type="hidden" name="jsondata"/>
    <input name="projFlow" value="${proj.projFlow}" type="hidden"/>
    <input name="orgName" type="hidden" value="<c:out value="${proj.orgName}" default="${sessionScope.currUser.orgName}"/>"/>
    <input name="orgFlow" type="hidden" value="<c:out value="${proj.orgFlow}" default="${sessionScope.currUser.orgFlow}"/>"/>
    <div class="content">
        <div class="title1 clearfix">
            <table width="100%" cellpadding="0" cellspacing="0" class="basic" id="look">
                <tr>
                    <th width="20%"><span class="red">*</span>项目名称：</th>
                    <td colspan="3"><input class="validate[required] xltext" name="projName" type="text" value="${proj.projName}" style="width: 569px"/></td>
                </tr>
                <tr>
                    <th width="20%"><span class="red">*</span>学科代码：</th>
                    <td width="30%">
                        <input id="proSelect" name="projSubject" class="xltext" value="${proj.projSubject}" onclick="showMenu();"/>
                        <input type="hidden" id="selectProjId" name='projSubjectId' value="${proj.projSubjectId}">
                    </td>
                    <th width="20%"><span class="red">*</span>项目类型：</th>
                    <td width="30%">
                        <select id="projTypeId" name="projTypeId" class="validate[required] xlselect">
                            <option/>
                            <option value="1" <c:if test="${proj.projTypeId eq '1'}">selected</c:if>>国家级继续医学教育项目</option>
                            <option value="2" <c:if test="${proj.projTypeId eq '2'}">selected</c:if>>国家级继续医学教育项目（备案项目）</option>
                            <option value="3" <c:if test="${proj.projTypeId eq '3'}">selected</c:if>>国家级中医药继续教育项目</option>
                            <option value="4" <c:if test="${proj.projTypeId eq '4'}">selected</c:if>>省级继续医学教育项目</option>
                            <option value="5" <c:if test="${proj.projTypeId eq '5'}">selected</c:if>>省级继续医学教育备案项目</option>
                            <option value="6" <c:if test="${proj.projTypeId eq '6'}">selected</c:if>>浙江省中医药继续教育项目</option>
                            <option value="7" <c:if test="${proj.projTypeId eq '7'}">selected</c:if>>杭州市继续医学教育项目</option>
                            <option value="8" <c:if test="${proj.projTypeId eq '8'}">selected</c:if>>其他继续医学教育项目</option>
                        </select>
                        <input type="hidden" name="projTypeName" value="${proj.projTypeName}">
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>主办单位：</th>
                    <td colspan="3"><input class="validate[required] xltext" name="applyOrgName" type="text" value="${proj.applyOrgName}" style="width: 569px"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>项目负责人：</th>
                    <td><input class="validate[required] xltext" type="text" name="applyUserName" value="${proj.applyUserName}"/></td>
                    <th><span class="red">*</span>负责人电话：</th>
                    <td><input type="text" name="projPhone" value="${proj.projPhone}" class="validate[required] xltext"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>举办起止时间：</th>
                    <td colspan="3">
                        <input type="text" style="width: 160px" class="validate[required] xltext ctime" name="projStartTime" value="${proj.projStartTime}" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>&nbsp;&nbsp;&#12288;——
                        &#12288;<input type="text" style="width: 160px" class="validate[required] xltext ctime" name="projEndTime" value="${proj.projEndTime}" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>举办地点：</th>
                    <td><input type="text" name="projHoldAddress" value="${proj.projHoldAddress}" class="validate[required] xltext"/></td>
                    <th><span class="red">*</span>教学对象：</th>
                    <td>
                        <select class="validate[required] xlselect" id="teachingObjectId" name="teachingObjectId">
                            <option/>
                            <c:forEach items="${dictTypeEnumFstuProjTeachingObjectList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${dict.dictId eq proj.teachingObjectId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="teachingObject" value="${proj.teachingObject}">
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>申请学分：</th>
                    <td><input type="text" name="applyScore" value="${proj.applyScore}" class="validate[required] xltext"/></td>
                    <th><span class="red">*</span>拟招生人数：</th>
                    <td><input type="text" name="recruitNum" value="${proj.recruitNum}" class="validate[required,custom[integer]] xltext"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>申报结果：</th>
                    <td>
                        <select class="validate[required] xlselect" name="declareResult" onchange="bindProjNo(this.value)">
                            <option>请选择</option>
                            <option value="Passed" ${proj.declareResult eq 'Passed'?'selected':''}>通过</option>
                            <option value="UnPassed" ${proj.declareResult eq 'UnPassed'?'selected':''}>不通过</option>
                        </select>
                    </td>
                    <th class="projNoTd"><span class="red">*</span>项目编号：</th>
                    <td class="projNoTd"><input type="text" name="projNo" value="${proj.projNo}" class="validate[required] xltext"/></td>
                </tr>
                <tr>
                    <th>备注：</th>
                    <td colspan="3"><textarea name="remark" class="xltxtarea" style="margin-left: 0px;width: 575px">${proj.remark}</textarea></td>
                </tr>
            </table>
            <div id="menuContent" class="menuContent" style="display:none; position:absolute;">
                <ul id="treeDemo" class="ztree" style="margin-top:0; width:190px;"></ul>
            </div>
            <table class="basic" style="width:100%;">
                <tr>
                    <th colspan="4" class="bs_name">申请表附件上传
                        <c:if test="${viewFlag ne 'view'}">
                            <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png" style="cursor: pointer;" onclick="addFile('projFileTb')"/></a></span>
                        </c:if>
                    </th>
                </tr>
                <tr>
                    <td width="30%" style="font-weight:bold;">附件名称</td>
                    <td width="40%" style="font-weight:bold;">附件内容</td>
                    <td width="20%" style="font-weight:bold;">附件操作</td>
                </tr>
                <tbody id="projFileTb">
                <c:forEach var="file" items="${fileList}" varStatus="status">
                    <tr>
                        <td>
                            <c:choose>
                                <c:when test="${not empty file.fileFlow}">
                                    <a id="down" href='<s:url value="/fstu/dec/fileDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                    <input type="file" id="file" name="files" style="display: none;"/>
                                </c:when>
                                <c:otherwise>
                                    <input type="file" id="file" name="files"/>
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                        </td>
                        <td>
                            <input class="validate[required,maxSize[100]] xltext" style="width: 97%;" name="fileRemark" type="text" value="${file.fileRemark}"/>
                        </td>
                        <td>
                            <a class="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>&#12288;
                            <a class="reCheck" href="javascript:void(0);" onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div class="button" style="width: 100%;">
                <c:if test="${viewFlag ne 'view'}">
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