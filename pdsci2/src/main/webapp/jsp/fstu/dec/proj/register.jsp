<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ztree" value="true"/>
    </jsp:include>

    <script type="text/javascript">
        function toPage(page) {
            if (page != undefined) {
                $("#currentPage").val(page);
            }
            $("#searchForm").submit();
        }
        function edit(flow, viewFlag) {
            jboxOpen("<s:url value='/fstu/dec/editProject'/>?flow=" + flow + "&viewFlag=" + viewFlag, "详情", 850, 580);
        }
        function delAidProj(flow) {
            jboxConfirm("确认删除？", function () {
                url = "<s:url value='/fstu/dec/delPro'/>?projFlow=" + flow + "&recordStatus=N";
                jboxPost(url, null, function (obj) {
                    if (obj == "${GlobalConstant.DELETE_SUCCESSED}") {
                        search();
                    }
                });
            });
        }
        //学科代码 BEGIN
        $(function(){
            var url = "<s:url value='/sys/subject/getAllDataJson'/>";
            jboxPostJson(url, null, function (data) {
                if (data) {
                    zNodes = $.parseJSON(data);
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            }, null, false);
        });
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
        //学科代码 END
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/fstu/dec/project/register'/>" method="post">
                <input type="hidden" name="flag" value="${flag}"/>
                <input id="currentPage" type="hidden" name="currentPage"/>
                <div class="queryDiv">
                    <div class="inputDiv">
                        <label class="qlable">项目类型：</label>
                        <select id="projTypeId" name="projTypeId" class="qselect">
                            <option/>
                            <option value="1" <c:if test="${param.projTypeId eq '1'}">selected</c:if>>国家级继续医学教育项目</option>
                            <option value="2" <c:if test="${param.projTypeId eq '2'}">selected</c:if>>国家级继续医学教育项目（备案项目）</option>
                            <option value="3" <c:if test="${param.projTypeId eq '3'}">selected</c:if>>国家级中医药继续教育项目</option>
                            <option value="4" <c:if test="${param.projTypeId eq '4'}">selected</c:if>>省级继续医学教育项目</option>
                            <option value="5" <c:if test="${param.projTypeId eq '5'}">selected</c:if>>省级继续医学教育备案项目</option>
                            <option value="6" <c:if test="${param.projTypeId eq '6'}">selected</c:if>>浙江省中医药继续教育项目</option>
                            <option value="7" <c:if test="${param.projTypeId eq '7'}">selected</c:if>>杭州市继续医学教育项目</option>
                            <option value="8" <c:if test="${param.projTypeId eq '8'}">selected</c:if>>其他继续医学教育项目</option>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">项目名称：</label>
                        <input type="text" name="projName" class="qtext" value="${param.projName}"/>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable">项目负责人：</label>
                        <input type="text" name="applyUserName" class="qtext" value="${param.applyUserName}"/>
                    </div>
                    <div class="inputDiv">
                        <c:if test="${flag eq 'local'}">
                            <label class="qlable">学科代码：</label>
                            <input id="proSelect" name="projSubject" class="qtext" value="${param.projSubject}" onclick="showMenu();"/>
                            <input type="hidden" id="selectProjId" name='projSubjectId'>
                        </c:if>
                    </div>
                    <div class="lastDiv">
                        <c:if test="${flag eq 'local'}">
                            <input type="button" value="查&#12288;询" class="searchInput" onclick="toPage();"/>
                        </c:if>
                    </div>
                    <c:if test="${flag eq 'global'}">
                        <div class="inputDiv">
                            <label class="qlable">学科代码：</label>
                            <input id="proSelect" name="projSubject" class="qtext" value="${param.projSubject}" onclick="showMenu();"/>
                            <input type="hidden" id="selectProjId" name='projSubjectId'>
                        </div>
                        <div class="lastDiv">
                            <input type="button" value="查&#12288;询" class="searchInput" onclick="toPage();"/>
                        </div>
                        <div>
                            <input type="button" class="searchInput" onclick="edit('','');" value="新&#12288;增"/>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
        <div id="menuContent" class="menuContent" style="display:none; position:absolute;">
            <ul id="treeDemo" class="ztree" style="margin-top:0; width:190px;"></ul>
        </div>
        <table class="xllist">
            <thead>
            <tr>
                <th>项目负责人</th>
                <th>项目名称</th>
                <th>项目类型</th>
                <th>主办单位</th>
                <th>申请学分</th>
                <th>拟招生人数</th>
                <th>举办地点</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="proj" items="${projList}">
                <tr>
                    <td>${proj.applyUserName}</td>
                    <td>${proj.projName}</td>
                    <td>${proj.projTypeName}</td>
                    <td>${proj.applyOrgName}</td>
                    <td>${proj.applyScore}</td>
                    <td>${proj.recruitNum}</td>
                    <td>${proj.projHoldAddress}</td>
                    <td>
                        <a style="cursor:pointer; color: blue;" onclick="edit('${proj.projFlow}','view')">查看</a>
                        <c:if test="${flag eq 'global'}">
                            | <a style="cursor:pointer; color: blue;" onclick="edit('${proj.projFlow}')">编辑</a> |
                            <a style="cursor:pointer; color: blue;" onclick="delAidProj('${proj.projFlow}')">删除</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty projList}">
                <tr>
                    <td colspan="8">无记录</td>
                </tr>
            </c:if>
            </tbody>
        </table>
        <c:set var="pageView" value="${pdfn:getPageView(projList)}" scope="request"></c:set>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
</body>
</html>