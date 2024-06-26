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
</head>
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
    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchProj();
    }

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
        jboxOpen("<s:url value='/srm/proj/archive/audit'/>?projFlow=" + projFlow, "审核", 900, 600);
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
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
        <form id="searchForm"
              action="<s:url value="/srm/proj/archive/list/${sessionScope.projListScope}/${sessionScope.projCateScope}" />"
              method="post">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
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
                <select class="xlselect" name="projTypeId">
                    <option value="">请选择</option>
                    <c:forEach var="dict" items="${dictTypeEnumProjTypeList}">
                        <option value="${dict.dictId}"
                                <c:if test='${param.projTypeId==dict.dictId}'>selected="selected"</c:if>>${dict.dictName}</option>
                    </c:forEach>
                </select>
            </div>--%>
            <div class="searchDiv">
                课题编号：
                <input class="xltext" name="projNo" type="text"
                       value="${param.projNo}"/>
            </div>
            <div class="searchDiv">
                课题账号：
                <input class="xltext" name="accountNo" type="text"
                       value="${param.accountNo}"/>
            </div>

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
                项目名称：&nbsp;<input type="text" name="projName" value="${param.projName}" class="xltext"/>
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
            <th width="5%">年份</th>
            <th width="8%">课题编号</th>
            <th width="8%">课题账号</th>
            <th width="10%">一级来源</th>
            <th>项目名称</th>
            <th width="10%">起止时间</th>
            <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                <th width="12%">申报单位</th>
            </c:if>
            <th width="8%">申报人</th>
            <th width="10%">当前阶段</th>
            <th width="8%">审核意见</th>
            <th width="8%">操作</th>
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
                <td>${proj.projNo }</td>
                <td>${proj.accountNo }</td>
                <td>${proj.projDeclarer }</td>
                <td><a style="color: blue"
                       href="<s:url value='/srm/proj/mine/auditProjView?projFlow=${proj.projFlow}'/>"
                       target="_blank">${proj.projName}</a></td>
                <td>${proj.projStartTime }~<br/>${proj.projEndTime }</td>
                <c:if test="${sessionScope.projListScope eq 'charge' or sessionScope.projListScope eq 'global'}">
                    <td>${proj.applyOrgName }</td>
                </c:if>
                <td>${proj.applyUserName}</td>
                <td>${proj.projStageName }</td>
                <td>[<a href="javascript:void(0)" onclick="auditList('${proj.projFlow}');">查看</a>]</td>
                <td>[<a href="javascript:viewScheduleRec('${proj.projFlow}');">审核</a>]</td>
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