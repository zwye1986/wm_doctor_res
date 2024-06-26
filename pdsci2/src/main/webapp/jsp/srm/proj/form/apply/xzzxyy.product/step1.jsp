<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_ztree" value="true"/>
</jsp:include>

<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <script type="text/javascript">
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
            var cityObj = $("#subjectName");
            $("#subjectCode").val(id);
            cityObj.attr("value", v);
        }

        function doClose() {
            jboxClose();
        }
        function showMenu() {
            var cityObj = $("#subjectName");
            var cityOffset = $("#subjectName").offset();
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
                }
            }, null, false);
        });

        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            if(!checkBDDate()) return;
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }

        function checkBDDate(){
            if($('#projStartTime').val() && $('#projEndTime').val() && $('#projStartTime').val() > $('#projEndTime').val()){
                jboxTip("计划开始时间不能大于计划结束时间！");
                return false;
            }
            return true;
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
    <input type="hidden" id="pageName" name="pageName" value="step1"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;"></font><br/>

    <table class="basic" id="infoTable" style="width: 100%; margin-top: 10px;">
        <tr>
            <td style="text-align: right;">项目名称：</td>
            <td><input type="text" name="projName" value="${empty resultMap.projName?proj.projName:resultMap.projName}"
                       class="validate[required] inputText" style="width: 90%"/><span class="redspan"
                                                                                      style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
            <td style="text-align: right;">项目负责人：</td>
            <td><input type="text" name="applyUserName"
                       value="${empty resultMap.applyUserName?proj.applyUserName:resultMap.applyUserName}"
                       class="validate[required] inputText" style="width: 90%"/><span class="redspan"
                                                                                      style="color: red;padding: 0px;margin-left: 10px;">*</span>
            </td>
        </tr>
        <tr>
            <td style="text-align: right;">联系电话：</td>
            <td><input type="text" name="phoneNum" value="${resultMap.phoneNum}" class="inputText" style="width: 90%"/>
            </td>
            <td style="text-align: right;">承担科室：</td>
            <td>
                <select name="applyDeptName" class="inputText validate[required]" style="width: 90%;">
                    <option value="">请选择</option>
                    <c:forEach var="dict" items="${dictTypeEnumXzApplyDeptList}">
                        <option value="${dict.dictName }"
                                <c:if test="${resultMap.applyDeptName eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                    </c:forEach>
                </select>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></td>
        </tr>
        <tr>
            <td style="text-align: right;">项目经费：</td>
            <td><input type="text" name="projMoney" value="${resultMap.projMoney}" class="validate[required] inputText"
                       style="width: 90%"/><span class="redspan"
                                                 style="color: red;padding: 0px;margin-left: 10px;">*</span></td>
            <td style="text-align: right;">项目类型：</td>
            <td>
                <select name="projType" class="validate[required] inputText" style="width: 90%">
                    <option value="">请选择</option>

                    <c:choose>
                        <c:when test="${empty resultMap.projType}">
                            <c:forEach var="dict" items="${dictTypeEnumProjTypeList}">
                                <option value="${dict.dictId}"
                                        <c:if test='${proj.projTypeId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="dict" items="${dictTypeEnumProjTypeList}">
                                <option value="${dict.dictId}"
                                        <c:if test='${resultMap.projType==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </select>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></td>
        </tr>
        <tr>
            <td style="text-align: right;">项目级别：</td>
            <td>
                <select name="projLevel" class="inputText validate[required]" style="width: 90%;">
                    <option value="">请选择</option>
                    <option value="国家级" <c:if test="${resultMap.projLevel eq '国家级'}">selected="selected"</c:if>>
                        国家级
                    </option>
                    <option value="省部级" <c:if test="${resultMap.projLevel eq '省部级'}">selected="selected"</c:if>>
                        省部级
                    </option>
                    <option value="厅市级" <c:if test="${resultMap.projLevel eq '厅市级'}">selected="selected"</c:if>>
                        厅市级
                    </option>
                    <option value="院级" <c:if test="${resultMap  .projLevel eq '院级'}">selected="selected"</c:if>>
                        院级
                    </option>
                </select>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></td>
            <td style="text-align: right;">项目来源：</td>
            <td>
                <select name="projSource" class="inputText validate[required]" style="width: 90%;">
                    <option value="">请选择</option>
                    <c:forEach var="dict" items="${dictTypeEnumXzProjSourceList}">
                        <option value="${dict.dictName }"
                                <c:if test="${resultMap.projSource eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                    </c:forEach>
                </select>
                <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></td>
        </tr>
        <tr>
            <td style="text-align: right;">项目开始时间：</td>
            <td><input type="text" name="projStartTime" id="projStartTime"
                       value="${empty resultMap.projStartTime?proj.projStartTime:resultMap.projStartTime}"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="inputText ctime"
                       style="width: 90%; margin-right: 0px;" readonly="readonly" onchange="checkBDDate()"/>
            </td>
            <td style="text-align: right;">项目结束时间：</td>
            <td><input type="text" name="projEndTime" id="projEndTime"
                       value="${empty resultMap.projEndTime?proj.projEndTime:resultMap.projEndTime}"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="inputText ctime"
                       style="width: 90%; margin-right: 0px;" readonly="readonly" onchange="checkBDDate()"/></td>
        </tr>
        <tr>
            <td style="text-align: right;">技术依托单位：</td>
            <td><input type="text" name="technologyApply" value="${resultMap.technologyApply}" class="inputText"
                       style="width: 90%"/></td>
            <td width="20%" style="text-align: right;">学科代码：</td>
            <td width="30%">
                    <input type="hidden" id="subjectCode" name='subjectCode' value='${empty resultMap.subjectCode?projInfoMap.subjectCode:resultMap.subjectCode}'>
                    <input type="text" id="subjectName" name="subjectName" class="inputText" value="${empty resultMap.subjectName?projInfoMap.subjectName:resultMap.subjectName}"
                           readonly="readonly" class="" style="width: 180px;text-align: left"
                           onclick="showMenu(); return false;"/>
                <!-- <a id="menuBtn" href="javascript:void(0)" onclick="showMenu(); return false;">选择</a> -->
            </td>
        </tr>
        <tr>
            <td style="text-align: left; margin-left: 20px;" colspan="4">项目简介：</td>
        </tr>
        <tr>
            <td colspan="4"><textarea name="projContent" style="width:98%;height: 150px;"
                                      class="validate[maxSize[1000]]">${resultMap.projContent}</textarea></td>
        </tr>
    </table>


</form>

<div id="menuContent" class="menuContent" style="display:none; position:absolute;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:159px;"></ul>
</div>
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="next" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
    </div>
</c:if>

		