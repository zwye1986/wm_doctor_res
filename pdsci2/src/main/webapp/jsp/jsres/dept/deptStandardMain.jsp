<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏省住院医师规范化培训管理平台</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="impromptu" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style>
        .hidden-row{
            display: none;
        }
        .outerRow {
            background-color: #fafafa;
        }
    </style>
    <script type="text/javascript">
        treeFlowMap = {};
        levelMap = {
            "1": "一级",
            "2": "二级",
            "3": "三级",
            "4": "四级"
        };
        curDeptData = [];


        $(function() {
            // 查出树形列表数据，并展示
            jboxPostJson("<s:url value='/jsres/dept/searchStandardDeptLevelAllByNameFuzzy' />", '{}', function(data) {
                curDeptData = data;
                buildStdDeptTree(data);
            }, function(msg) {
            }, false)
        });

        function searchStdDeptFuzzy(deptNameFuzzy) {
            deptNameFuzzy = $.trim(deptNameFuzzy);
            var data = {standardDeptNameFuzzy: deptNameFuzzy || ''};
            jboxPostJson("<s:url value='/jsres/dept/searchStandardDeptLevelAllByNameFuzzy' />", JSON.stringify(data), function(data) {
                treeFlowMap = {};
                curDeptData = data;
                if(!deptNameFuzzy) {
                    buildStdDeptTree(data);
                }else {
                    buildStdDeptPlain(data);
                }
            }, function(msg) {
            }, false);
        }

        function buildStdDeptPlain(data) {
            html = '';
            for(var i = 0; i < data.length; i++) {
                html += buildStdDeptOne(data[i], treeFlowMap);
            }

            $("#stdDeptList").html(html);
        }

        function buildStdDeptOne(dataOne) {
            treeFlowMap[dataOne.standardDeptCode] = dataOne;
            var content = '';
            content = $("#stdDeptTreeNode .innerRow").prop("outerHTML");
            content = content.replace("\{id}", dataOne.standardDeptCode);
            content = content.replace("\{levelDeptCode}", dataOne.standardDeptCode);
            content = content.replace("\{levelDeptName}",dataOne.standardDeptName);
            content = content.replace("\{deptLevel}", dataOne.deptLevel);
            content = content.replace("\{deptCodeCascade}", '');
            // content = content.repeat("\{deptFlow}", dataOne.standardDeptFlow);
            content = content.replace("\{stdDeptBlank}", '');
            var stdDeptLevel = Number.parseInt(dataOne.deptLevel);
            if(stdDeptLevel == 4) {
                content = content.replace("\{addSub}", "");
            }else {
                content = content.replace("\{addSub}", '<a class="oper" onclick="createSubStdDept(event)">新增子级</a>&nbsp;&nbsp;&nbsp;');
            }
            content = content.replace("\{stdDeptHidden}", '');
            if(dataOne.deptStatus == 'Y') {
                content = content.replace("\{deptStatusName}", '禁用');
            }else {
                content = content.replace("\{deptStatusName}", '启用');
            }

            return content;
        }

        // 构建树形列表，最多四级
        function buildStdDeptTree(treeData) {
            html = '';
            for(var i = 0; i < treeData.length; i++) {
                html += buildStdDeptTreeOne(treeData[i], treeData[i].standardDeptCode, treeData[i].standardDeptName, treeFlowMap);
            }

            $("#stdDeptList").html(html);
        }

        function buildStdDeptTreeOne(dataOne, levelCode, levelName) {
            dataOne.levelName = levelName;
            dataOne.levelCode = levelCode;
            treeFlowMap[dataOne.standardDeptCode] = dataOne;
            var content = '';
            var blanks = '';
            if(dataOne.subStandardDeptList && dataOne.subStandardDeptList.length) { // 有子节点
                content = $("#stdDeptTreeNode .outerRow").prop("outerHTML");
            }else {
                content = $("#stdDeptTreeNode .innerRow").prop("outerHTML");
            }
            content = content.replace("\{id}", dataOne.standardDeptCode);
            content = content.replace("\{levelDeptCode}", dataOne.standardDeptCode);
            content = content.replace("\{levelDeptName}", dataOne.standardDeptName);
            content = content.replace("\{deptLevel}", dataOne.deptLevel);
            content = content.replace("\{deptCodeCascade}", levelCode);
            // content = content.repeat("\{deptFlow}", dataOne.standardDeptFlow);
            var stdDeptLevel = Number.parseInt(dataOne.deptLevel);
            for(var i = 1; i < stdDeptLevel; i++) {
                blanks += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            content = content.replace("\{stdDeptBlank}", blanks);
            var hiddenClass = 'hidden-row';
            if(stdDeptLevel == 1) {
                hiddenClass = '';
            }
            if(stdDeptLevel == 4) {
                content = content.replace("\{addSub}", "");
            }else {
                content = content.replace("\{addSub}", '<a class="oper" onclick="createSubStdDept(event)">新增子级</a>&nbsp;&nbsp;&nbsp;');
            }
            content = content.replace("\{stdDeptHidden}", hiddenClass);
            if(dataOne.deptStatus == 'Y') {
                content = content.replace("\{deptStatusName}", '禁用');
            }else {
                content = content.replace("\{deptStatusName}", '启用');
            }

            if(dataOne.subStandardDeptList && dataOne.subStandardDeptList.length) { // 处理子节点
                for(var i = 0; i < dataOne.subStandardDeptList.length; i++) {
                    content += buildStdDeptTreeOne(dataOne.subStandardDeptList[i], levelCode + "-" + dataOne.subStandardDeptList[i].standardDeptCode,
                        levelName + "-" + dataOne.subStandardDeptList[i].standardDeptName, treeFlowMap);
                }
            }

            return content;
        }

        function createLevel1StdDept() {
            var html = $("#stdDeptEditTemplate").html();
            html = html.replace("{0}", "");
            html = html.replace("{1}", "");
            html = html.replace("{2}", "");
            html = html.replace("{3}", "一级");

            var settings = {
                operType: 'addLevel1',
                title: '新增科室',
                html: html
            }

            promptShow(settings);
        }

        function promptShow(settings) {
            settings.html = settings.html.replace("\{operType}", settings.operType);
            settings.html = settings.html.replace("\{deptFlow}", settings.deptFlow || '');
            settings.html = settings.html.replace("\{deptCode}", settings.deptCode || '');
            $.prompt({
                state0: {
                    title: settings.title,
                    html: settings.html,
                    buttons: {},
                    submit:function(e,v,m,f){

                    },
                    close: function (t, e, i, n) {

                    },
                    position: {width: 600}
                }
            });
        }

        function editStdDept(e) {
            var target = (e || window.event).target;
            var stdDeptCode = $(target).parents("tr").attr("id");
            var stdDeptData = treeFlowMap[stdDeptCode];

            var html = $("#stdDeptEditTemplate").html();
            if(stdDeptData.parentDeptCode == stdDeptCode) { // 父级是自己，最上层
                html = html.replace("{0}", "");
            }else {
                html = html.replace("{0}", treeFlowMap[stdDeptData.parentDeptCode].standardDeptName);
            }

            html = html.replace("{1}", stdDeptData.standardDeptName);
            html = html.replace("{2}", stdDeptData.standardDeptCode);
            html = html.replace("{3}", levelMap[stdDeptData.deptLevel]);

            var settings = {
                operType: 'edit',
                title: '编辑',
                html: html,
                deptFlow: stdDeptData.standardDeptFlow
            }

            promptShow(settings);

            // 设置一下前缀科室
            var nameLen = stdDeptData.standardDeptName.length;
            var codeLen = stdDeptData.standardDeptCode.length;

            $(".stdDeptNamePre").last().text(stdDeptData.levelName.substring(0, stdDeptData.levelName.length - nameLen));
            $(".stdDeptCodePre").last().text(stdDeptData.levelCode.substring(0, stdDeptData.levelCode.length - codeLen));
        }

        function createSiblingStdDept(e) {
            var target = (e || window.event).target;
            var stdDeptCode = $(target).parents("tr").attr("id");
            var stdDeptData = treeFlowMap[stdDeptCode];

            var html = $("#stdDeptEditTemplate").html();
            if(stdDeptData.parentDeptCode == stdDeptCode) { // 父级是自己，最上层
                html = html.replace("{0}", "");
            }else {
                html = html.replace("{0}", treeFlowMap[stdDeptData.parentDeptCode].standardDeptName);
            }

            html = html.replace("{1}", '');
            html = html.replace("{2}", '');
            html = html.replace("{3}", levelMap[stdDeptData.deptLevel]);
            var parentDeptCode = stdDeptData.parentDeptCode;
            if(parentDeptCode == stdDeptData.standardDeptCode) { // 自己是第一级
                parentDeptCode = '';
            }

            var settings = {
                operType: 'addSibling',
                title: '新增同级',
                html: html,
                deptCode: parentDeptCode
            }

            promptShow(settings);
        }

        function createSubStdDept(e) {
            var target = (e || window.event).target;
            var stdDeptCode = $(target).parents("tr").attr("id");
            var stdDeptData = treeFlowMap[stdDeptCode];

            var html = $("#stdDeptEditTemplate").html();
            html = html.replace("{0}", treeFlowMap[stdDeptCode].standardDeptName);
            html = html.replace("{1}", '');
            html = html.replace("{2}", '');
            html = html.replace("{3}", levelMap["" + (Number.parseInt(stdDeptData.deptLevel) + 1)]);

            var settings = {
                operType: "addSub",
                title: '新增子级',
                html: html,
                deptCode: stdDeptData.standardDeptCode,
            }

            promptShow(settings);
        }

        function deleteStdDept(e) {
            var target = (e || window.event).target;
            var curTr = $(target).parents("tr");
            var mark = curTr.attr("deptcodemark");
            if(curTr.siblings("tr[deptcodemark^='" + mark + "-']").length) { // 存在子级，不允许删除
                jboxTip("存在子级科室无法删除");
                return;
            }

            jboxConfirm("是否确认删除？", function() {
                var stdDeptCode = curTr.attr("id");
                var stdDeptData = treeFlowMap[stdDeptCode];
                // 后台校验并删除
                var url = '<s:url value="/jsres/dept/deleteStandardDept" />';
                jboxPostJson(url, JSON.stringify({standardDeptFlow: stdDeptData.standardDeptFlow, standardDeptCode: stdDeptCode}), function(resp) {
                    jboxTip(resp);
                    if(resp == "保存成功！") {
                        searchStdDeptFuzzy($("#standardDeptNameFuzzy").val());
                        /*curTr.remove();
                        delete treeFlowMap[stdDeptCode];
                        for(var i = 0; i < curDeptData.length; i++) {
                            if(curDeptData[i].standardDeptCode == stdDeptCode) {
                                curDeptData.splice(i, 1);
                                break;
                            }
                        }*/
                    }
                }, function (msg) {

                }, false);
            });
        }

        function toggleStdDeptStatus(e) {
            var target = (e || window.event).target;
            var stdDeptCode = $(target).parents("tr").attr("id");
            var stdDeptData = treeFlowMap[stdDeptCode];

            if(stdDeptData.deptStatus == 'N') {
                var tips = "确认启用标准科室：";
                var toggleStatus = 'Y';
            }else {
                var tips = "确认禁用标准科室：";
                var toggleStatus = 'N';
            }

            jboxConfirm(tips + stdDeptData.standardDeptName, function() {
                var url = '<s:url value="/jsres/dept/saveStandardDept/editDeptStatus" />';
                jboxPostJson(url, JSON.stringify({standardDeptFlow: stdDeptData.standardDeptFlow, deptStatus: toggleStatus, standardDeptCode: stdDeptCode}), function(resp) {
                    jboxTip(resp);
                    if(resp == "保存成功！") {
                        searchStdDeptFuzzy($("#standardDeptNameFuzzy").val());
                        // var label =
                    }
                }, function (msg) {

                }, false);
            });
        }

        /**
         * @param e
         */
        function toggleShow(e) {
            var target = (e || window.event).target;
            var curTr = $(target).parents("tr");
            var mark = curTr.attr("deptcodemark");
            if(curTr.siblings("tr[deptcodemark^='" + mark + "-']").first().css("display") == 'table-row') {
                curTr.find(".toggleExpand").css("display", "inline-block");
                curTr.find(".toggleShrink").css("display", "none");
                $("tr[deptcodemark^='" + mark + "-']").each(function(i, item) {
                   if($(item).hasClass("outerRow")) {
                       $(item).find(".toggleExpand").css("display", "inline-block");
                       $(item).find(".toggleShrink").css("display", "none");
                   }
                });
                $("tr[deptcodemark^='" + mark + "-']").addClass("hidden-row");
            }else {
                curTr.find(".toggleExpand").css("display", "none");
                curTr.find(".toggleShrink").css("display", "inline-block");
                $("tr[deptcodemark^='" + mark + "-']").each(function(i, item) {
                    if($(item).hasClass("outerRow")) {
                        $(item).find(".toggleExpand").css("display", "none");
                        $(item).find(".toggleShrink").css("display", "inline-block");
                    }
                });
                $("tr[deptcodemark^='" + mark + "-']").removeClass("hidden-row");
            }
        }

        function closeEditDialog() { // 找不到插件关闭弹窗入口，只能模拟点击一下
            $(".jqiclose").trigger('click');
        }

        function editSave(operType) {
            var data = {
                standardDeptCode: $("input[name='standardDeptCodeEdit']")[1].value,
                standardDeptName: $("input[name='standardDeptNameEdit']")[1].value
            }
            var url = '';
            if(operType == 'addLevel1') {
                url = '<s:url value="/jsres/dept/saveStandardDept/addLevel1" />';
            }else if(operType == 'edit') {
                url = '<s:url value="/jsres/dept/saveStandardDept/edit" />';
                data['standardDeptFlow'] = $(".deptFlowEdit")[1].value;
            }else if(operType == 'addSibling') {
                url = '<s:url value="/jsres/dept/saveStandardDept/addSibling" />';
                data['parentDeptCode'] = $(".deptCodeEdit")[1].value;
                if(!data['parentDeptCode']) { // 没有上一级，自己是第一级
                    data['deptLevel'] = '1';
                }else {
                    var parentDeptData = treeFlowMap[data['parentDeptCode']];
                    data['deptLevel'] = (Number(parentDeptData.deptLevel) + 1) + '';
                }
            }else if(operType == 'addSub') {
                url = '<s:url value="/jsres/dept/saveStandardDept/addSub" />';
                data['parentDeptCode'] = $(".deptCodeEdit")[1].value;
            }

            // 后台校验保存
            jboxPostJson(url, JSON.stringify(data), function(resp) {
                jboxTip(resp);
                if(resp == "保存成功！") {
                    searchStdDeptFuzzy($("#standardDeptNameFuzzy").val());
                    closeEditDialog();
                }
            }, function (msg) {

            }, false);
        }
    </script>
</head>
<body>
<div class="div_search">
    <form id="stdDpetBaseForm">
        <label class="label">科室名称：</label>
        <input type="text" name="standardDeptNameFuzzy" id="standardDeptNameFuzzy" class="input_text input" style="width: 150px" />
        <input type="button" onclick='searchStdDeptFuzzy($("#standardDeptNameFuzzy").val())' value="查询" class='btn btn_green' />
        <input type="button" onclick='createLevel1StdDept()' value="新增科室" class='btn btn_green' />
    </form>
</div>
<div class="div_table" style="overflow: auto">
    <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="table-layout: fixed">
        <thead>
        <tr>
            <th style="text-align: center;width: 35%">科室名称</th>
            <th style="text-align: center;width: 15%">科室代码</th>
            <th style="text-align: center;width: 15%">级别</th>
            <th style="text-align: center;width: 35%">操作</th>
        </tr>
        </thead>
        <tbody id="stdDeptList">
        </tbody>
    </table>
</div>
<div id="stdDeptEditTemplate" style="display: none">
        <div class="div_table" style="padding:0 10px 10px">
            <!-- <h4>编辑科室信息</h4> -->
            <form id="editForm" style="position: relative;" method="post">
                <input type="hidden" value="{deptFlow}" class="deptFlowEdit">
                <input type="hidden" value="{deptCode}" class="deptCodeEdit">
                <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top: 20px;">
                    <colgroup>
                        <col style="width: 20%" />
                        <col style="width: 80%" />
                    </colgroup>
                    <tbody>
                    <%-- <tr>
                        <th>机构名称：</th>
                        <td>${sessionScope.currUser.orgName}</td>
                    </tr> --%>
                    <tr>
                        <th>上级科室：</th>
                        <td style="text-align: center"><input class="input" name="parentDeptCodeEdit" type="text" value="{0}" disabled/></td>
                    </tr>
                    <tr>
                        <th>科室名称：</th>
                        <td style="text-align: center"><span class="stdDeptNamePre"></span><input class="validate[required,minSize[1],maxSize[25]] input" name="standardDeptNameEdit" type="text" value="{1}" /></td>
                    </tr>
                    <tr>
                        <th>科室代码：</th>
                        <td style="text-align: center"><span class="stdDeptCodePre"></span><input class="validate[required,custom[integer],maxSize[50]] input" name="standardDeptCodeEdit" type="text" value="{2}" /></td>
                    </tr>
                    <tr>
                        <th>级别：</th>
                        <td style="text-align: center"><input class="input" name="deptLevelEdit" type="text" value="{3}" disabled /></td>
                    </tr>
                    </tbody>
                </table>
            </form>

            <div class="button">
                <input type="button" class="btn_green" onclick="editSave('{operType}')" value="保&#12288;存"/>
                <input type="button" class="btn_green" onclick="closeEditDialog()" value="关&#12288;闭"/>
            </div>
        </div>
</div>
<table id="stdDeptTreeNode" style="display: none">
    <tr id="{id}" class="outerRow {stdDeptHidden}" deptcodemark="{deptCodeCascade}">
        <td>
            <div onclick="toggleShow()" style="cursor: pointer;">
                <img id="" class='toggleExpand' src="<s:url value='/jsp/jsres/images/up3.png'/>" style="display: inline-block; vertical-align: text-bottom" />
                <img id="" class='toggleShrink' src="<s:url value='/jsp/jsres/images/down3.png' />"
                     style="display: none; vertical-align: text-bottom"/>
                <span>{stdDeptBlank}</span><span>{levelDeptName}</span>
            </div>
        </td>
        <td>{levelDeptCode}</td>
        <td>{deptLevel}</td>
        <td style="text-align: center">
            <a class="oper" onclick="editStdDept(event)">编辑</a>&nbsp;&nbsp;&nbsp;
            <a class="oper" onclick="createSiblingStdDept(event)">新增同级</a>&nbsp;&nbsp;&nbsp;
            <a class="oper" onclick="createSubStdDept(event)">新增子级</a>&nbsp;&nbsp;&nbsp;
            <a class="oper" onclick="deleteStdDept(event)">删除</a>&nbsp;&nbsp;&nbsp;
            <a class="oper" onclick="toggleStdDeptStatus(event)">{deptStatusName}</a>
        </td>
    </tr>
    <tr id="{id}" class="innerRow {stdDeptHidden}" deptcodemark="{deptCodeCascade}" deptflow="{deptFlow}">
        <td><div onclick="toggleShow()"><span style="margin-left: 25px">{stdDeptBlank}</span><span>{levelDeptName}</span></div></td>
        <td>{levelDeptCode}</td>
        <td>{deptLevel}</td>
        <td style="text-align: center">
            <a class="oper" onclick="editStdDept(event)">编辑</a>&nbsp;&nbsp;&nbsp;
            <a class="oper" onclick="createSiblingStdDept(event)">新增同级</a>&nbsp;&nbsp;&nbsp;
            {addSub}
            <a class="oper" onclick="deleteStdDept(event)">删除</a>&nbsp;&nbsp;&nbsp;
            <a class="oper" onclick="toggleStdDeptStatus(event)">{deptStatusName}</a>
        </td>
    </tr>
</table>
</body>
</html>