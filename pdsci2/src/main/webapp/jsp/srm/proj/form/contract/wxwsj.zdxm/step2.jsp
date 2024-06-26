<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_ztree" value="true"/>
</jsp:include>

<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
        //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
        function banBackSpace(e) {
            var ev = e || window.event;//获取event对象
            var obj = ev.target || ev.srcElement;//获取事件源
            var t = obj.type || obj.getAttribute('type');//获取事件源类型
            //获取作为判断条件的事件类型
            var vReadOnly = obj.getAttribute('readonly');
            //处理null值情况
            vReadOnly = (vReadOnly == "") ? false : vReadOnly;
            //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
            //并且readonly属性为true或enabled属性为false的，则退格键失效
            var flag1 = (ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea")
            && vReadOnly == "readonly") ? true : false;
            //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
            var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea")
                    ? true : false;

            //判断
            if (flag2) {
                return false;
            }
            if (flag1) {
                return false;
            }
        }

        window.onload = function () {
//禁止后退键 作用于Firefox、Opera
            document.onkeypress = banBackSpace;
//禁止后退键  作用于IE、Chrome
            document.onkeydown = banBackSpace;
        }
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

        /* 学科代码 */
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
            var cityObj = $("#belongSubject");
            $("#belongSubjectCode").val(id);
            cityObj.attr("value", v);
        }

        function showMenu() {
            var cityObj = $("#belongSubject");
            var cityOffset = $("#belongSubject").offset();
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
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);//所属学科
                    $.fn.zTree.init($("#treeDemo1"), setting1, zNodes);//所属学科1
                    $.fn.zTree.init($("#treeDemo2"), setting2, zNodes);//相关学科2
                }
            }, null, false);
        });

        /* 相关学科 */
        var setting2 = {
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
                onClick: onClick2
            }
        };

        function onClick2(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo2"),
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
            var cityObj = $("#relatedSubject2");
            $("#relatedSubjectCode2").val(id);
            cityObj.attr("value", v);
        }
        function showMenu2() {
            var cityObj = $("#relatedSubject2");
            var cityOffset = $("#relatedSubject2").offset();
            $("#menuContent2").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");
            $("body").bind("mousedown", onBodyDown2);
        }
        function hideMenu2() {
            $("#menuContent2").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }

        function onBodyDown2(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent2" || $(event.target).parents("#menuContent2").length > 0)) {
                hideMenu2();
            }
        }


        /* 相关学科 */
        var setting1 = {
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
                onClick: onClick1
            }
        };

        function onClick1(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo1"),
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
            var cityObj = $("#relatedSubject1");
            $("#relatedSubjectCode1").val(id);
            cityObj.attr("value", v);
        }
        function showMenu1() {
            var cityObj = $("#relatedSubject1");
            var cityOffset = $("#relatedSubject1").offset();
            $("#menuContent1").css({
                left: cityOffset.left + "px",
                top: cityOffset.top + cityObj.outerHeight() + "px"
            }).slideDown("fast");
            $("body").bind("mousedown", onBodyDown1);
        }
        function hideMenu1() {
            $("#menuContent1").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }

        function onBodyDown1(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent1" || $(event.target).parents("#menuContent1").length > 0)) {
                hideMenu1();
            }
        }
    </script>
</c:if>
<style>
    #infoTable .inputText {
        text-align: left;
    }
</style>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
      id="projForm">
    <input type="hidden" id="pageName" name="pageName" value="step2"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">一、基本信息表</font>
    <table id="infoTable" class="basic" style="width: 100%; margin-top: 10px;">
        <tr>
            <th rowspan="4">项目负责人</th>
            <td style="text-align: right;">姓&#12288;&#12288;名：</td>
            <td>
                    <input type="text" name="userName" value="${resultMap.userName}"
                           class="validate[required] inputText" style="width: 80%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
            <td style="text-align: right;">性&#12288;&#12288;别：</td>
            <td>
                <select name="gender" class="validate[required] inputText" style="width: 80%;">
                    <option value="">请选择</option>
                    <c:forEach var="sex" items="${userSexEnumList}">
                        <c:if test="${sex.id != userSexEnumUnknown.id}">
                            <option value="${sex.name}"
                                    <c:if test="${resultMap.gender eq sex.name}">selected="selected"</c:if>>${sex.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
            <td style="text-align: right;">出生年月：</td>
            <td colspan="3"><input class="validate[required] inputText ctime" type="text" name="birthday"
                       value="${resultMap.birthday}" onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                       style="width: 80%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;">学历：</td>
            <td>
                <select name="education" class="validate[required] inputText" style="width: 80%;">
                    <option value="">请选择</option>
                    <c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
                        <option value="${dict.dictName}"
                                <c:if test="${resultMap.education eq dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
                    </c:forEach>
                </select>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
            <td style="text-align: right;">学位：</td>
            <td><input type="text" name="degree" value="${resultMap.degree}"
                                   class="validate[required] inputText" style="width: 80%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
            <td style="text-align: right;">职&#12288;&#12288;称：</td>
            <td>
                <select class="validate[required] inputText" name="title" style="width: 80%">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                        <option value="${title.dictName}"
                                <c:if test='${resultMap.title==title.dictName}'>selected="selected"</c:if>>${title.dictName}</option>
                    </c:forEach>
                </select>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
            <td style="text-align: right;">职&#12288;&#12288;务：</td>
            <td>
                <select class="validate[required] inputText" name="duty" style="width: 80%">
                    <option value="">请选择</option>
                    <c:forEach items="${dictTypeEnumUserPostList}" var="post">
                        <option value="${post.dictName}"
                                <c:if test='${resultMap.duty==post.dictName}'>selected="selected"</c:if>>${post.dictName}</option>
                    </c:forEach>
                </select>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;">工作单位：</td>
            <td colspan="4">
                    <input type="text" name="orgName" value="${resultMap.orgName}" class="validate[required] inputText"
                           style="width: 80%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
            <td style="text-align: right;">所在部门：</td>
            <td colspan="2"><input type="text" name="deptName" value="${resultMap.deptName}"
                                   class="validate[required] inputText" style="width: 80%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>

        <tr>
            <td style="text-align: right;">从事专业：</td>
            <td colspan="7">
                    <input type="text" name="major" value="${resultMap.major}"
                           class="validate[required]] inputText" style="width: 80%"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>

        <tr>
            <th rowspan="8">项目摘要</th>
        </tr>
        <tr>
            <td style="text-align: right;">项目名称：</td>
            <td colspan="7"><input type="text" name="projectName" value="<c:if test='${empty resultMap.projectName and param.view != GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.projectName}'>${resultMap.projectName}</c:if>"
                                   class="validate[required] inputText" style="width: 80%;text-align: left;"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;">所属学科：</td>
            <td colspan="3">
                <input type="text" id="belongSubject" name="belongSubject" value="${resultMap.belongSubject}"
                       class="validate[required] inputText" style="width: 80%;" readonly="readonly"
                       onclick="showMenu(); return false;"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
            <td style="text-align: right;">代&#12288;&#12288;码：</td>
            <td colspan="3"><input type="text" id="belongSubjectCode" name="belongSubjectCode"
                                   value="${resultMap.belongSubjectCode}" class="inputText" style="width: 80%"
                                   readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">相关学科1：</td>
            <td colspan="3">
                <input type="text" id="relatedSubject1" name="relatedSubject1" value="${resultMap.relatedSubject1}"
                       class="validate inputText" style="width: 80%;" readonly="readonly"
                       onclick="showMenu1(); return false;"/>
            </td>
            <td style="text-align: right;">代&#12288;&#12288;码：</td>
            <td colspan="3"><input type="text" id="relatedSubjectCode1" name="relatedSubjectCode1"
                                   value="${resultMap.relatedSubjectCode1}" class="inputText" style="width: 80%"
                                   readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">相关学科2：</td>
            <td colspan="3">
                <input type="text" id="relatedSubject2" name="relatedSubject2" value="${resultMap.relatedSubject2}"
                       class="validate inputText" style="width: 80%;" readonly="readonly"
                       onclick="showMenu2(); return false;"/>
            </td>
            <td style="text-align: right;">代&#12288;&#12288;码：</td>
            <td colspan="3"><input type="text" id="relatedSubjectCode2" name="relatedSubjectCode2"
                                   value="${resultMap.relatedSubjectCode2}" class="inputText" style="width: 80%"
                                   readonly="readonly"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">市卫计委资助经费：</td>
            <td colspan="3"><input type="text" name="applyFunds" value="${resultMap.applyFunds}"
                                   class="validate[custom[number]] inputText" style="width: 80%"/>万元
            </td>
            <td style="text-align: right;">总经费：</td>
            <td colspan="3"><input type="text" name="amountFunds" value="${resultMap.amountFunds}"
                                   class="validate[custom[number]] inputText" style="width: 80%"/>万元
            </td>

        </tr>
        <tr>
            <td style="text-align: right;">起止年月：</td>
            <td colspan="7">
                <input type="text" name="projStartYearMonth" value="${resultMap.projStartYearMonth}"
                       onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                       class="validate[required] inputText ctime" style="width: 20%;margin-right: 0px;"/>
                ~ <input type="text" name="projEndYearMonth" value="${resultMap.projEndYearMonth}"
                         onClick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"
                         class="validate[required] inputText ctime" style="width: 20%;"/>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td colspan="8" style="text-align:left;">
                <textarea placeholder="项目摘要（限400字）" class="xltxtarea"
                          style="height: 300px;"
                          name="projectSignificanceSummary">${resultMap.projectSignificanceSummary}</textarea>
            </td>
        </tr>
    </table>
</form>

<div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index:1000;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:210px;"></ul>
</div>
<div id="menuContent1" class="menuContent" style="display:none; position:absolute;z-index:1000;">
    <ul id="treeDemo1" class="ztree" style="margin-top:0; width:210px;"></ul>
</div>
<div id="menuContent2" class="menuContent" style="display:none; position:absolute;z-index:1000;">
    <ul id="treeDemo2" class="ztree" style="margin-top:0; width:210px;"></ul>
</div>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
    </div>
</c:if>

		