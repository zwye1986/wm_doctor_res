<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
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
        function checkBDDate(dt) {
            var dates = $(':text', $(dt).closest("td"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                dt.value = "";
            }
        }

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
        $(document).ready(function () {
            var url = "<s:url value='/sys/subject/getAllDataJson'/>";
            jboxPostJson(url, null, function (data) {
                if (data) {
                    zNodes = $.parseJSON(data);
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            }, null, false);
        });
        function doBack() {
            window.location.href = "<s:url value='/srm/proj/mine/process?projFlow='/>" + $("#projFlow").val();
        }
        </c:if>
    </script>
    <style type="text/css">
        .basic .inputText {
            text-align: left;
        }
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step1"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">一、项目基本情况</font>
                    <table class="basic" style="width: 100%; margin-top: 10px;">

                        <tr>
                            <th><font color="red">*</font>申报项目名称：</th>
                            <td colspan="3">
                                <input type="text" class="validate[required] inputText" name="projName"
                                       value="<c:if test='${empty resultMap.projName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.projName}'>${resultMap.projName}</c:if>"
                                       style="width: 30%"/>
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>申报单位：</th>
                            <td colspan="3"><input type="text" name="applyOrgName" value="<c:if test='${empty resultMap.applyOrgName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${!empty resultMap.applyOrgName}'>${resultMap.applyOrgName}</c:if>"
                                       class="inputText validate[required]" style="width: 30%"/>
                            </td>
                        </tr>
                        <tr>
                            <th style="width: 20%"><font color="red">*</font>申报科室：</th>
                            <td style="width: 30%"><input type="text" name="applyDeptName" value="<c:if test='${empty resultMap.applyDeptName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyDeptName}</c:if><c:if test='${!empty resultMap.applyDeptName}'>${resultMap.applyDeptName}</c:if>"
                                                   class="inputText validate[required]" style="width: 80%"/></td>
                            <th style="width: 20%">学科专业代码：</th>
                            <td style="width: 30%"><input id="proSelect" name="subjTypeCode" class="inputText"
                                                   value="<c:if test='${empty resultMap.subjTypeCode and param.view!=GlobalConstant.FLAG_Y}'>${proj.subjName}</c:if>${resultMap.subjTypeCode}"
                                                   readonly="readonly" style="width: 180px;text-align: left"
                                                   onclick="showMenu(); return false;"/></td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>主要完成人：</th>
                            <td colspan="3">1、<input type="text" name="mainCompleteOne" value="${resultMap.mainCompleteOne}"
                                                   class="inputText validate[required]" style="width: 25%"/>&#12288;
                                2、<input type="text" name="mainCompleteTwo" value="${resultMap.mainCompleteTwo}"
                                       class="inputText" style="width: 25%"/>&#12288;
                                3、<input type="text" name="mainCompleteThree" value="${resultMap.mainCompleteThree}"
                                       class="inputText" style="width: 25%"/>
                            </td>

                        </tr>
                        <tr>
                            <th><font color="red">*</font>申报奖励等级：</th>
                            <td colspan="3">
                                <select class="inputText validate[required]" name="applyLevel" style="width: 30%">
                                    <option value="">--请选择--</option>
                                    <option <c:if test="${resultMap.applyLevel eq '特等奖'}">selected="selected"</c:if> value="特等奖">特等奖</option>
                                    <option <c:if test="${resultMap.applyLevel eq '一等奖'}">selected="selected"</c:if> value="一等奖">一等奖</option>
                                    <option <c:if test="${resultMap.applyLevel eq '二等奖'}">selected="selected"</c:if> value="二等奖">二等奖</option>
                                </select>
                            </td>

                        </tr>
                        <tr>
                            <th>项目首创单位：</th>
                            <td>
                                <input type="text" name="createOrgName" value="${resultMap.createOrgName}"
                                       class="inputText" style="width: 80%"/>
                            </td>
                            <th>时&#12288;间：</th>
                            <td>
                                <input type="text" class="inputText ctime" name="createOrgTime" value="${resultMap.createOrgTime}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                       style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>项目首用单位：</th>
                            <td>
                                <input type="text" name="firstUseOrg" value="${resultMap.firstUseOrg}"
                                       class="inputText" style="width: 80%"/>
                            </td>
                            <th>时&#12288;间：</th>
                            <td>
                                <input type="text" class="inputText ctime" name="firstUseTime" value="${resultMap.firstUseTime}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                       style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>国内首用单位：</th>
                            <td colspan="3"><input type="text" name="firstUseDomestic" value="${resultMap.firstUseDomestic}"
                                                   class="inputText" style="width: 30%"/>
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>本单位引进时间：</th>
                            <td colspan="3">
                                <input type="text" class="inputText ctime validate[required]" name="importOwnUnitTime"
                                       onchange="checkBDDate(this)" value="${resultMap.importOwnUnitTime}"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                                       style="width: 30%"/>
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>联系人：</th>
                            <td>
                                <input type="text" name="contacts" value="${resultMap.contacts}"
                                       class="inputText validate[required]" style="width: 80%"/>
                            </td>
                            <th>电&#12288;话：</th>
                            <td>
                                <input type="text" name="telephone" value="${resultMap.telephone}"
                                       class="inputText validate[custom[phone]]" style="width: 80%"/>
                            </td>
                        </tr>
                        <tr>
                            <th>项目申报单位</th>
                            <td>
                               <textarea class="validate[maxSize[300]]" name="applyOrgOption"
                                         style="width:98%;height: 150px;">${resultMap.applyOrgOption}</textarea>
                            </td>
                            <th>申报单位伦理委员会审查意见</th>
                            <td>
                                <textarea class="validate[maxSize[300]]" name="councilOrgOption"
                                          style="width:98%;height: 150px;">${resultMap.councilOrgOption}</textarea>
                            </td>
                        </tr>
                    </table>

                </form>
                <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                    <div align="center" style="margin-top: 10px">
                        <input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
                        <input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index:1000;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:210px;"></ul>
</div>
</body>
</html>

