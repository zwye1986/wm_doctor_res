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
            jboxPostJson("<s:url value='/jsres/dept/searchStandardDeptLevelAllByNameFuzzy' />", JSON.stringify({standardDeptNameFuzzy: '', withSpeBaseFlag: 'Y', speBaseId: $("#speBaseId").val()}), function(data) {
                curDeptData = data;
                buildStdDeptTree(data);
            }, function(msg) {
            }, false)
        });

        // 如果deptNameFuzzy有内容，那么搜索结果直接展示（此时结果较少），如果deptNameFuzzy为空，那么搜索结果树形展示（此时结果较多）
        function searchStdDeptFuzzy(deptNameFuzzy) {
            deptNameFuzzy = $.trim(deptNameFuzzy);
            var data = {standardDeptNameFuzzy: deptNameFuzzy || '', withSpeBaseFlag: 'Y', speBaseId: $("#speBaseId").val()};
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
            var html = '';
            for(var i = 0; i < data.length; i++) {
                html += buildStdDeptOne(data[i], treeFlowMap);
            }

            $("#stdDeptList").html(html);
            buildRotationTable(treeFlowMap);
        }

        function buildStdDeptOne(dataOne, treeFlowMap) {
            treeFlowMap[dataOne.standardDeptCode] = dataOne;
            var content = '';
            content = $("#stdDeptTreeNode .innerRow").prop("outerHTML");
            content = content.replace("\{id}", dataOne.standardDeptCode);
            content = content.replace("\{levelDeptCode}", dataOne.standardDeptCode);
            content = content.replace("\{levelDeptName}", dataOne.standardDeptName);
            content = content.replace("\{deptLevel}", dataOne.deptLevel);
            content = content.replace("\{deptCodeCascade}", '');
            // content = content.repeat("\{deptFlow}", dataOne.standardDeptFlow);
            content = content.replace("\{stdDeptBlank}", '');
            // 不隐藏子节点
            content = content.replace("\{stdDeptHidden}", '');
            if(dataOne.deptStatus == 'N') {
                content = content.replace("\{textColorForce}", 'text-grey')
                content = content.replace("\{textColorOption}", 'text-grey');
            }
            if(dataOne.rotateRequireStatus == '1') {
                content = content.replace("\{textColorForce}", 'text-red')
            }else if(dataOne.rotateRequireStatus == '2') {
                content = content.replace("\{textColorOption}", 'text-red');
            }

            return content;
        }

        function buildRotationTable(treeFlowMap) {
            var rotationForceData = []
            var rotationOptionData= [];
            for(var key in treeFlowMap) {
                var oneData = treeFlowMap[key];
                if(oneData.rotateRequireStatus == '1') {
                    rotationForceData.push(oneData);
                }else if(oneData.rotateRequireStatus == '2') {
                    rotationOptionData.push(oneData);
                }
            }

            var html = '';
            for(var i = 0; i < rotationForceData.length; i++) {
                var content = $("#rotationDeptNode tbody").html();
                content = content.replace("\{speBaseStdDeptFlowForce}", rotationForceData[i].speBaseStdDeptFlow);
                content = content.replace("\{rotationForce}", rotationForceData[i].standardDeptName);
                if(i < rotationOptionData.length) {
                    content = content.replace("\{speBaseStdDeptFlowOption}", rotationOptionData[i].speBaseStdDeptFlow);
                    content = content.replace("\{rotationOption}", rotationOptionData[i].standardDeptName);
                }
                html += content;
            }

            for(var i =  rotationForceData.length; i < rotationOptionData.length; i++) {
                content = $("#rotationDeptNode tbody").html();
                content = content.replace("\{speBaseStdDeptFlowOption}", rotationOptionData[i].speBaseStdDeptFlow);
                content = content.replace("\{rotationOption}", rotationOptionData[i].standardDeptName);
                html += content;
            }

            $("#rotationDeptList").html(html).css("display", "none");

            var deletingTd = [];
            $("#rotationDeptList td").each(function(idx, item) {
                if($(item).text() == '{rotationOption}') {
                    deletingTd.push(item);
                    deletingTd.push($(item).next()[0]);
                }
                if($(item).text() == '{rotationForce}') {
                    deletingTd.push(item);
                    deletingTd.push($(item).next()[0]);
                }
            });
            deletingTd.forEach(function(item) {
                $(item).empty();
            });
            $("#rotationDeptList").css("display", "");
        }

        // 构建树形列表，最多四级
        function buildStdDeptTree(treeData) {
            html = '';
            for(var i = 0; i < treeData.length; i++) {
                html += buildStdDeptTreeOne(treeData[i], treeData[i].standardDeptCode, treeData[i].standardDeptName, treeFlowMap);
            }

            $("#stdDeptList").html(html);
            buildRotationTable(treeFlowMap);
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
            // content = content.repeat("\{deptFlow}", dataOne.standardDeptFlow);
            var stdDeptLevel = Number.parseInt(dataOne.deptLevel);
            for(var i = 1; i < stdDeptLevel; i++) {
                blanks += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            content = content.replace("\{stdDeptBlank}", blanks);
            // 不隐藏子节点
            content = content.replace("\{stdDeptHidden}", '');
            if(dataOne.deptStatus == 'N') {
                content = content.replace("\{textColorForce}", 'text-grey')
                content = content.replace("\{textColorOption}", 'text-grey');
            }
            if(dataOne.rotateRequireStatus == '1') {
                content = content.replace("\{textColorForce}", 'text-red')
            }else if(dataOne.rotateRequireStatus == '2') {
                content = content.replace("\{textColorOption}", 'text-red');
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

        function configToForce(e) {
            var target = (e || window.event).target;
            var curTr = $(target).parents("tr");
            var stdDeptId = curTr.attr("id");
            var deptData = treeFlowMap[stdDeptId];
            if(deptData['deptStatus'] == 'N') {
                return;
            }
            if(deptData['rotateRequireStatus'] == '1') {
                return;
            }

            var url = '<s:url value="/jsres/dept/configSpeBaseStatus" />'
            // 后台校验保存
            jboxPostJson(url, JSON.stringify({speBaseId: $("#speBaseId").val(), rotateRequireStatus: 1, standardDeptFlow: deptData.standardDeptFlow}), function(resp) {
                jboxTip(resp);
                if(resp == "保存成功！") {
                    searchStdDeptFuzzy($("#standardDeptNameFuzzy").val());
                }
            }, function (msg) {

            }, false);
        }

        function configToOption(e) {
            var target = (e || window.event).target;
            var curTr = $(target).parents("tr");
            var stdDeptId = curTr.attr("id");
            var deptData = treeFlowMap[stdDeptId];
            if(deptData['deptStatus'] == 'N') {
                return;
            }
            if(deptData['rotateRequireStatus'] == '2') {
                return;
            }

            var url = '<s:url value="/jsres/dept/configSpeBaseStatus" />'
            // 后台校验保存
            jboxPostJson(url, JSON.stringify({speBaseId: $("#speBaseId").val(), rotateRequireStatus: 2, standardDeptFlow: deptData.standardDeptFlow}), function(resp) {
                jboxTip(resp);
                if(resp == "保存成功！") {
                    searchStdDeptFuzzy($("#standardDeptNameFuzzy").val());
                }
            }, function (msg) {

            }, false);
        }

        function deleteRotation(e) {
            var target = (e || window.event).target;
            var speBaseStdDeptFlow = $(target).parents("td").attr("id").substring(5);
            var url = '<s:url value="/jsres/dept/configSpeBaseDelete" />'
            // 后台校验保存
            jboxPost(url, {speBaseStdDeptFlow: speBaseStdDeptFlow}, function(resp) {
                jboxTip(resp);
                if(resp == "保存成功！") {
                    searchStdDeptFuzzy($("#standardDeptNameFuzzy").val());
                }
            }, function (msg) {

            }, false);
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
        <label>当前配置专业基地：</label>
        <label style="color: red;">${speBaseName}</label>
        <input type="hidden" value="${speBaseId}" id="speBaseId">
        <input type="hidden" value="${speBaseName}" id="speBaseName">
    </form>
</div>
<div class="" style="height: ${dialogHeight}px;overflow: auto; width: 58%; display: inline-block; float:left">
    <div style="padding: 15px">
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
</div>
<div class="" style="height: ${dialogHeight}px;overflow: auto; width: 38%;display: inline-block; float: right">
    <div style="padding: 15px">
    <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="table-layout: fixed">
        <thead>
        <tr>
            <th style="text-align: center;width: 35%">必选轮转科室</th>
            <th style="text-align: center;width: 15%">操作</th>
            <th style="text-align: center;width: 35%">可选轮转科室</th>
            <th style="text-align: center;width: 15%">操作</th>
        </tr>
        </thead>
        <tbody id="rotationDeptList">
        </tbody>
    </table>
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
            <a class="oper {textColorForce}" onclick="configToForce(event)">设为必轮</a>
            &#12288;&#12288;&#12288;&#12288;
            <a class="oper {textColorOption}" onclick="configToOption(event)">设为选轮</a>
        </td>
    </tr>
    <tr id="{id}" class="innerRow {stdDeptHidden}" deptcodemark="{deptCodeCascade}" deptflow="{deptFlow}">
        <td><div onclick="toggleShow()"><span style="margin-left: 25px">{stdDeptBlank}</span><span>{levelDeptName}</span></div></td>
        <td>{levelDeptCode}</td>
        <td>{deptLevel}</td>
        <td style="text-align: center">
            <a class="oper {textColorForce}" onclick="configToForce(event)">设为必轮</a>
            &#12288;&#12288;&#12288;&#12288;
            <a class="oper {textColorOption}" onclick="configToOption(event)">设为选轮</a>
        </td>
    </tr>
</table>

<table id="rotationDeptNode" style="display: none">
    <tbody>
    <tr>
        <td>{rotationForce}</td>
        <td style="text-align: center" id="edit_{speBaseStdDeptFlowForce}">
            <a class="" onclick="deleteRotation(event)">删除</a>
        </td>
        <td>{rotationOption}</td>
        <td style="text-align: center" id="edit_{speBaseStdDeptFlowOption}">
            <a class="" onclick="deleteRotation(event)">删除</a>
        </td>
    </tr>
    </tbody>
</table>
</body>
</html>