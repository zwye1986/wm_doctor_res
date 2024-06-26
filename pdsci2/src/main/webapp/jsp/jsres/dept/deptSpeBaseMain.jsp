<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>江苏省住院医师规范化培训管理平台</title>
    <style>
    </style>
    <script type="text/javascript">
        var curSpeBaseData = [];
        $(function () {
            // 查出树形列表数据，并展示
            jboxPostJson("<s:url value='/jsres/dept/searchSpeBaseAll' />", null, function(data) {
                curSpeBaseData = data;
                buildSpeBaseRow(data);
            }, function(msg) {
            }, false)
        });

        // 查一次，然后在本地搜
        function searchSpeBaseFuzzy(speBaseFuzzy) {
            if(!speBaseFuzzy) {
                buildSpeBaseRow(curSpeBaseData);
                return;
            }

            var searchData = curSpeBaseData.filter(function(item) {
               var speBaseName = item.dictName;
               return speBaseName.indexOf(speBaseFuzzy) > -1;
            });
            buildSpeBaseRow(searchData);
        }

        function buildSpeBaseRow(rowData) {
            var html = '';
            if(rowData && rowData.length) {
                rowData.forEach(function(item) {
                    var tempHtml = $("#speBaseRowTemplate tbody").html();
                    tempHtml = tempHtml.replaceAll("\{speBaseCode}", item.dictId);
                    tempHtml = tempHtml.replaceAll("\{speBaseName}", item.dictName);
                    html += tempHtml;
                });
            }

            $("#speBaseList").html(html);
        }

        function configDept(speBaseId, speBaseName) {
            var width = window.innerWidth * 0.85;
            var height = window.innerHeight * 0.85;
            var url = "<s:url value='/jsres/dept/configSpeBase' />" + "?speBaseId=" + speBaseId + "&speBaseName=" + speBaseName + "&height=" + (height - 100);
            jboxOpen(url, "配置", width, height, true);
        }
    </script>
</head>
<body>
<div class="div_search">
    <form id="spetBaseForm">
        <label class="label">专业基地：</label>
        <input type="text" name="speBaseFuzzy" id="speBaseFuzzy" class="input_text input" style="width: 150px" />
        <input type="button" onclick='searchSpeBaseFuzzy($("#speBaseFuzzy").val())' value="查询" class='btn btn_green' />
    </form>
</div>
<div class="div_table" style="overflow: auto">
    <table border="0" cellpadding="0" cellspacing="0" class="base_info" style="table-layout: fixed">
        <thead>
        <tr>
            <th style="text-align: center;width: 35%">专业基地编码</th>
            <th style="text-align: center;width: 35%">专业基地名称</th>
            <th style="text-align: center;width: 30%">操作</th>
        </tr>
        </thead>
        <tbody id="speBaseList">
        </tbody>
    </table>
</div>
<table id="speBaseRowTemplate" style="display: none">
    <tbody>
    <tr>
        <td>{speBaseCode}</td>
        <td>{speBaseName}</td>
        <td style="text-align: center"><a id="" class="" onclick="configDept('{speBaseCode}', '{speBaseName}')">配置</a> </td>
    </tr>
    </tbody>
</table>
</body>
</html>