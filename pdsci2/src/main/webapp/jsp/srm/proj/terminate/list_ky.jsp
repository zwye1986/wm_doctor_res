<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>
</head>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>"></script>
<script type="text/javascript">
    function searchProj() {
        jboxStartLoading();
        $("#searchForm").submit();
    }
    function auditList(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/mine/auditList?projFlow='/>" + projFlow, "审核信息", 900, 600);
    }
    function viewScheduleRec(projFlow) {
        jboxStartLoading();
        jboxOpen("<s:url value='/srm/proj/terminate/audit'/>?projFlow=" + projFlow, "审核", 900, 600);
    }

    //加载申请单位
    function loadApplyOrg() {
        //清空
        $("#trainOrg").val("");
        $("#orgFlow").val("");
        initApplyOrg();
    }

    function initApplyOrg() {
        var chargeOrgFlow = $('#chargeOrg').val();
        if (!chargeOrgFlow) {
            return;
        }
        var url = "<s:url value='/sys/org/loadApplyOrg'/>?orgFlow=" + chargeOrgFlow;
        jboxStartLoading();
        jboxGet(url, null, function (data) {
            var datas = [];
            $.each(data, function (i, n) {
                var d = {};
                d.id = n.orgFlow;
                d.text = n.orgName;
                datas.push(d);
            });
            var itemSelectFuntion = function () {
                $("#orgFlow").val(this.id);
//            searchInfo();
            };
            $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
        }, null, false);
    }
    <c:if test='${sessionScope.projListScope=="charge"}'>
    $(function(){
        initOrg();
    });

    function initOrg() {
        var datas = [];
        <c:forEach var="org" items="${orgList}">
        var d = {};

        d.id = "${org.orgFlow}";
        d.text = "${org.orgName}";
        datas.push(d);
        </c:forEach>
        var itemSelectFuntion = function () {
            $("#orgFlow").val(this.id);
            searchInfo();
        };
        $.selectSuggest('trainOrg', datas, itemSelectFuntion, "orgFlow", true);
    }
    </c:if>
    $(document).ready(function () {
        initApplyOrg();
    });
    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchProj();
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
</script>

<body>
<div class="content">
    <div class="title1 clearfix">
        <form id="searchForm"
              action="<s:url value="/srm/proj/terminate/list/${sessionScope.projListScope}/${sessionScope.projCateScope}?recTypeId=${param.recTypeId}" />"
              method="post">
            <p>
            <div class="searchDiv">
                年&#12288;&#12288;份：
                <input type="text" class="xltext ctime" name="projYear" readonly="readonly"
                       onClick="WdatePicker({dateFmt:'yyyy'})" value="${param.projYear }"/>
            </div>
            <div class="searchDiv">
                项目类型：
                <select class="xlselect" name="projTypeId">
                    <option value="">请选择</option>
                    <c:forEach var="dictEnuProjType" items="${dictTypeEnumProjTypeList}">
                        <option value="${dictEnuProjType.dictId}"
                                <c:if test='${param.projTypeId==dictEnuProjType.dictId}'>selected="selected"</c:if>>${dictEnuProjType.dictName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="searchDiv">
                项目编号：<input name="projNo" value="${param.projNo}" class="xltext"/>
            </div>
            <c:if test='${sessionScope.projListScope=="charge"}'>
                <div class="searchDiv">
                    申报单位：
                    <input id="trainOrg" class="xltext" name="applyOrgName" type="text"
                           value="${param.applyOrgName}" autocomplete="off"/>
                    <input id="orgFlow" name="applyOrgFlow" class="input" value="${param.applyOrgFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
            </c:if>

            <c:if test='${sessionScope.projListScope=="global" }'>
                <div class="searchDiv">
                    主管部门：
                    <select id="chargeOrg" name="chargeOrgFlow" onchange="loadApplyOrg();" class="xlselect">
                        <option value="">请选择</option>
                        <c:forEach var="chargeOrg" items="${chargeOrgList}">
                            <option value="${chargeOrg.orgFlow}"
                                    <c:if test="${param.chargeOrgFlow==chargeOrg.orgFlow}">selected</c:if>>${chargeOrg.orgName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="searchDiv">
                    申报单位：
                    <input id="trainOrg" class="xltext" name="applyOrgName" type="text"
                           value="${param.applyOrgName}" autocomplete="off"/>
                    <input id="orgFlow" name="applyOrgFlow" class="input" value="${param.applyOrgFlow}" type="text"
                           hidden style="margin-left: 0px;"/>
                </div>
            </c:if>
            <div class="searchDiv">
                项目名称：&nbsp;<input type="text" name="projName" value="${param.projName}" class="xltext"/>
            </div>
            <div class="searchDiv">
                负 责 人 ：&nbsp;<input type="text" name="applyUserName" value="${param.applyUserName}" class="xltext"/>
            </div>
            <div class="searchDiv">
                学&#12288;&#12288;科：
                <%--<input type="hidden" id="selectProjId" name='subjId' value='${param.subjId}'>--%>
                <input id="proSelect" name="subjName" class="xltext" value="${param.subjName}"
                       style="text-align: left" onclick="showMenu(); return false;"/>
            </div>
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <div class="searchDiv">
                <input type="button" class="search" onclick="searchProj();" value="查&#12288;询">
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
            <th width="10%">年份</th>
            <th width="10%">项目类别</th>
            <th width="10%">项目名称</th>
            <th width="20%">起止时间</th>
            <th width="10%">项目负责人</th>
            <th width="10%">当前阶段</th>
            <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                <th width="10%">申报单位</th>
            </c:if>
            <th width="10%">审核意见</th>
            <th width="10%">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty projList}">
            <tr>
                <td colspan="11">无记录</td>
            </tr>
        </c:if>
        <c:forEach items="${projList}" var="proj">
            <tr>
                <td><span>${proj.projYear }</span></td>
                <td>${proj.projTypeName }</td>
                <td><a href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>"
                       target="_blank">${proj.projName}</a></td>
                <td>${proj.projStartTime }~${proj.projEndTime }</td>
                <td>${proj.applyUserName}</td>
                <td>${proj.projStageName }</td>
                <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                    <td>${proj.applyOrgName }</td>
                </c:if>
                <td><a href="javascript:void(0)" onclick="auditList('${proj.projFlow}');">查看</a></td>
                <td><a href="javascript:viewScheduleRec('${proj.projFlow}');">审核</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:set var="pageView" value="${pdfn:getPageView(projList)}" scope="request"></c:set>
    <pd:pagination toPage="toPage"/>
</div>
</body>
</html>