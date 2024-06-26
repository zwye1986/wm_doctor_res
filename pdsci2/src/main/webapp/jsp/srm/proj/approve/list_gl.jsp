<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="jquery_ui_combobox" value="true"/>
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
    <script type="text/javascript">
        function initDept() {
            var datas = [];
            var url = "<s:url value='/srm/ach/topic/searchDept'/>";
            jboxPost(url, null, function (resp) {
                $.each(resp, function (i, n) {
                    var d = {};
                    d.id = n.deptFlow;
                    d.text = n.deptName;
                    datas.push(d);
                });
            }, null, false);
            var itemSelectFuntion = function () {
                $("#deptFlow").val(this.id);
            };
            $.selectSuggest('trainDept', datas, itemSelectFuntion, "deptFlow", true);
        }

        $(document).ready(function () {
            initDept();
        });
        function setUp(projFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/proj/approve/setUp?projFlow='/>" + projFlow, "立项信息", 900, 600);
        }

        function searchProj() {
            jboxStartLoading();
            $("#searchForm").submit();
        }


        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            searchProj();
        }

        function exportExcel() {
            var url = "<s:url value='/srm/proj/approve/exportExcel/${sessionScope.projListScope}/${sessionScope.projCateScope}?recTypeId=${param.recTypeId}'/>"
            jboxSubmit($('#searchForm'), url, null, null);
            jboxEndLoading();
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
            searchProjCategory();
        });

        function searchProjCategory(){
            var dictFlow = $("select[name='projDeclarerFlow'] option:selected").attr("dictFlow");
            var dictTypeId = $("#projDeclarerFlow").val();
            var data = {
                dictFlow : dictFlow,
                dictTypeId : dictTypeId
            };
            var url = "<s:url value='/sys/dict/getCategoryDictByDeclarer'/>";
            jboxPost(url , data , function(data){
                //清空原类别！
                $("select[name=projSecondSourceId] option[value != '']").remove();
                var dataObj = data;
                for(var i =0;i<dataObj.length;i++){
                    var cId =dataObj[i].dictId;
                    var cName =dataObj[i].dictName;
                    $option =$("<option></option>");
                    $option.attr("value",cId);
                    $option.text(cName);
                    if("${param.projSecondSourceId}" == cId){
                        $option.attr("selected",true);
                    }
                    $("select[name='projSecondSourceId']").append($option);
                }
            } , null , false);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm"
                  action="<s:url value="/srm/proj/approve/list/${sessionScope.projListScope}/${sessionScope.projCateScope}" />"
                  method="post">
                <p>
                <div class="searchDiv">
                    年份区间：
                    <input type="text" class="xltext ctime" name="startYear" readonly="readonly" style="width: 69px"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.startYear }<c:if test="${empty param.startYear and defaultTerm eq 'Y'}">${pdfn:getCurrYear()}</c:if>"/> -
                    <input type="text" class="xltext ctime" name="endYear" readonly="readonly" style="width: 69px"
                           onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.endYear }<c:if test="${empty param.endYear and defaultTerm eq 'Y'}">${pdfn:getCurrYear()}</c:if>"/>
                </div>
                <div class="searchDiv">
                    科&#12288;&#12288;室：
                    <input id="trainDept" class="xltext" name="applyDeptName" type="text"
                           value="${param.applyDeptName}" autocomplete="off"/>
                    <input id="deptFlow" name="applyDeptFlow" class="input" value="${param.applyDeptFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
                <%--<div class="searchDiv">
                    项目类型：
                    <select name="projTypeId" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumProjTypeList}">
                            <option value="${dict.dictId}"
                                    <c:if test='${param.projTypeId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>--%>
                <div class="searchDiv">
                    一级来源：
                    <select id="projDeclarerFlow" name="projDeclarerFlow" class="xlselect"  onchange="searchProjCategory();">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumProjTypeSourceList}" var="dict" varStatus="status">
                            <option dictFlow="${dict.dictFlow}" value="${dict.dictId}" <c:if test="${param.projDeclarerFlow eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    二级来源：
                    <select name="projSecondSourceId" class="xlselect">
                        <option value="">请选择</option>
                    </select>
                </div>

                <div class="searchDiv">
                    项目名称：
                    <input type="text" name="projName" value="${param.projName}" class="xltext" style="width: 160px"/>
                </div>
                <div class="searchDiv">
                    负 责 人 ：&nbsp;<input type="text" name="applyUserName" value="${param.applyUserName}" class="xltext"/>
                </div>
                <%--<div class="searchDiv">
                    学&#12288;&#12288;科：
                    &lt;%&ndash;<input type="hidden" id="selectProjId" name='subjId' value='${param.subjId}'>&ndash;%&gt;
                    <input id="proSelect" name="subjName" class="xltext" value="${param.subjName}"
                           style="text-align: left" onclick="showMenu(); return false;"/>
                </div>--%>

                <input id="currentPage" type="hidden" name="currentPage" value=""/>
                <div class="searchDiv">
                    <input type="button" class="search" onclick="searchProj();" value="查&#12288;询">
                    <c:if test="${pdfn:contains(param.projTypeId, 'yhwsj') and sessionScope.projListScope eq 'global'}">
                        <input type="button" class="search" onclick="exportExcel();" value="导出Excel">
                    </c:if>
                </div>
                </p>
            </form>
        </div>
        <div id="menuContent" class="menuContent" style="display:none; position:absolute;">
            <ul id="treeDemo" class="ztree" style="margin-top:0; width:190px;"></ul>
        </div>
        <table class="xllist">
            <thead>
            <tr>
                <th width="5%">年份</th>
                <%--<th width="10%">项目类别</th>--%>
                <th width="10%">一级来源</th>
                <th width="10%">二级来源</th>
                <th>项目名称</th>
                <th width="20%">申报单位</th>
                <th width="10%">项目负责人</th>
                <th width="15%">起止时间</th>
                <th width="15%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty projList}">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
            <c:forEach var="proj" items="${projList}">
                <tr>
                    <td>${proj.projYear}</td>
                    <%--<td><span>${proj.projTypeName}</span></td>--%>
                    <td>${proj.projDeclarer }</td>
                    <td>${proj.projSecondSourceName }</td>
                    <td><a style="color:blue;"
                           href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>"
                           target="_blank">${proj.projName}</a></td>
                    <td>${proj.applyOrgName}</td>
                    <td>${proj.applyUserName}</td>
                    <td width="200px">${proj.projStartTime}~${proj.projEndTime}</td>
                    <td>
                        [<a href="javascript:void(0)" onclick="setUp('${proj.projFlow}')">审批</a>]
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(projList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>