<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏省住院医师规范化培训管理平台</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style>
        .hidden-row{
            display: none;
        }
        .outerRow {
            background-color: #fafafa;
        }
        .text-grey {
            color: grey !important;
        }
        .text-red {
            color: red !important;
        }
    </style>
    <script type="text/javascript">
        treeFlowMap = {};
        curDeptData = [];


        $(function() {
            // 查出树形列表数据，并展示
            jboxPostJson("<s:url value='/jsres/dept/searchStandardDeptLevelAllByNameFuzzy' />", JSON.stringify({standardDeptNameFuzzy: '', withBaseDeptFlag: 'Y', deptFlow: $("#curDeptFlow").val()}), function(data) {
                curDeptData = data;
                buildStdDeptTree(data);
            }, function(msg) {
            }, false);

            // 没有办法了，右上角x的click事件去不掉，只能先去掉这个元素，再创建一个，再绑自己的click事件
            var html = $(window.top.window.document).find("[i='close']").prop('outerHTML');
            $(window.top.window.document).find("[i='close']").remove();
            $(window.top.window.document).find("[i='header']").prepend(html);
            $(window.top.window.document).find("[i='close']").bind("click", function () {
                doClose();
            });
        });

        function searchStdDeptFuzzy(deptNameFuzzy) {
            deptNameFuzzy = $.trim(deptNameFuzzy);
            var data = {standardDeptNameFuzzy: deptNameFuzzy || '', withBaseDeptFlag: 'Y', deptFlow: $("#curDeptFlow").val()};
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
            content = content.replace("\{levelDeptName}", dataOne.standardDeptName);
            content = content.replace("\{deptLevel}", dataOne.deptLevel);
            content = content.replace("\{deptCodeCascade}", '');
            content = content.replace("\{deptFlow}", dataOne.standardDeptFlow);
            content = content.replace("\{stdDeptBlank}", '');
            // 不隐藏子节点
            content = content.replace("\{stdDeptHidden}", '');
            if(dataOne.curRelStandardDeptFlag == 'Y') {
                content = content.replaceAll("\{relTextColor}", 'text-red')
                content = content.replace("\{relStatus}", '已关联');
                $("#curRelStandardDeptFlow").val(dataOne.standardDeptFlow);
                $("#curRelStandardDeptName").text(dataOne.standardDeptName);
            }else {
                content = content.replaceAll("\{relTextColor}", '')
                content = content.replace("\{relStatus}", '未关联');
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
            content = content.replace("\{deptFlow}", dataOne.standardDeptFlow);
            var stdDeptLevel = Number.parseInt(dataOne.deptLevel);
            for(var i = 1; i < stdDeptLevel; i++) {
                blanks += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            content = content.replace("\{stdDeptBlank}", blanks);
            // 不隐藏子节点
            content = content.replace("\{stdDeptHidden}", '');
            if(dataOne.curRelStandardDeptFlag == 'Y') {
                content = content.replaceAll("\{relTextColor}", 'text-red')
                content = content.replace("\{relStatus}", '已关联');
                $("#curRelStandardDeptFlow").val(dataOne.standardDeptFlow);
                $("#curRelStandardDeptName").text(levelName);
            }else {
                content = content.replaceAll("\{relTextColor}", '')
                content = content.replace("\{relStatus}", '未关联');
            }

            if(dataOne.subStandardDeptList && dataOne.subStandardDeptList.length) { // 处理子节点
                for(var i = 0; i < dataOne.subStandardDeptList.length; i++) {
                    content += buildStdDeptTreeOne(dataOne.subStandardDeptList[i], levelCode + "-" + dataOne.subStandardDeptList[i].standardDeptCode,
                        levelName + "-" + dataOne.subStandardDeptList[i].standardDeptName, treeFlowMap);
                }
            }

            return content;
        }

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

        function toggleRel(e) {
            var target = (e || window.event).target;
            var curTr = $(target).parents("tr");
            var stdDeptId = curTr.attr("id");
            var deptData = treeFlowMap[stdDeptId];

            var data = {
                deptFlow: $("#curDeptFlow").val(),
                operType: 'relDept'
            };
            if(deptData.standardDeptFlow == $("#curRelStandardDeptFlow").val()) {
                return;
            }else {
                data.standardDeptFlow = deptData.standardDeptFlow;
            }

            var url = '<s:url value="/jsres/dept/saveBaseDept" />'
            // 后台校验保存
            jboxPostJson(url, JSON.stringify(data), function(resp) {
                jboxTip(resp);
                if(resp == "保存成功！") {
                    searchStdDeptFuzzy($("#standardDeptNameFuzzy").val());
                }
            }, function (msg) {

            }, false);
        }

        function doClose() {
            window.parent.search();
            jboxClose();
        }
    </script>
</head>
<body>
<div class="div_search">
    <form id="stdDpetBaseForm">
        <label class="label">标准科室名称：</label>
        <input type="text" name="standardDeptNameFuzzy" id="standardDeptNameFuzzy" class="input_text input" style="width: 150px" />
        <input type="button" onclick='searchStdDeptFuzzy($("#standardDeptNameFuzzy").val())' value="查询" class='btn btn_green' />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <label>已关联：</label>
        <label style="color: red;" id="curRelStandardDeptName"></label>
        <input type="hidden" value="" id="curRelStandardDeptFlow">
        <input type="hidden" value="${curDeptFlow}" id="curDeptFlow">
    </form>
</div>
<div class="" style="height: ${dialogHeight}px;overflow: auto; display: inline-block; ">
    <div style="padding: 15px">
        <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="table-layout: fixed">
            <thead>
            <tr>
                <th style="text-align: center;width: 35%">科室名称</th>
                <th style="text-align: center;width: 15%">科室代码</th>
                <th style="text-align: center;width: 15%">级别</th>
                <th style="text-align: center;width: 35%">状态</th>
            </tr>
            </thead>
            <tbody id="stdDeptList">
            </tbody>
        </table>
    </div>
</div>
<div class="main_bd" id="div_table_1" >
    <div align="center" style="margin-top: 20px; margin-bottom:20px;">
        <input type="button" id="submitBtn" class="btn btn_grey" onclick="doClose();" value="关闭"/>
    </div>
</div>
<table id="stdDeptTreeNode" style="display: none">
    <tr id="{id}" class="outerRow {stdDeptHidden}" deptcodemark="{deptCodeCascade}">
        <td>
            <div onclick="toggleShow()" style="cursor: pointer;">
                <img id="" class='toggleExpand' src="<s:url value='/jsp/jsres/images/up3.png'/>" style="display: none; vertical-align: text-bottom" />
                <img id="" class='toggleShrink' src="<s:url value='/jsp/jsres/images/down3.png' />"
                     style=" display: inline-block;vertical-align: text-bottom"/>
                <span>{stdDeptBlank}</span><span>{levelDeptName}</span>
            </div>
        </td>
        <td>{levelDeptCode}</td>
        <td>{deptLevel}</td>
        <td style="text-align: center">
            <a class="oper {relTextColor}" onclick="toggleRel(event)">{relStatus}</a>
        </td>
    </tr>
    <tr id="{id}" class="innerRow {stdDeptHidden}" deptcodemark="{deptCodeCascade}" deptflow="{deptFlow}">
        <td><div onclick="toggleShow()"><span style="margin-left: 25px">{stdDeptBlank}</span><span class="{relTextColor}">{levelDeptName}</span></div></td>
        <td>{levelDeptCode}</td>
        <td>{deptLevel}</td>
        <td style="text-align: center">
            <a class="oper {relTextColor}" onclick="toggleRel(event)">{relStatus}</a>
        </td>
    </tr>
</table>
</body>
</html>