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
        //学科 BEGIN
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
        //学科 END
        function add(userFlow){
            var title = userFlow==''?'新增':'编辑';
            var url = "<s:url value='/fstu/score/editScore?userFlow='/>"+userFlow;
            jboxOpen(url,title,800,530);
        }
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/fstu/score/register'/>" method="post">
                <div class="queryDiv">
                    <input type="hidden" name="currentPage" id="currentPage">
                    <input type="hidden" name="flag" value="${flag}">
                    <c:if test="${flag eq 'local'}">
                        <div class="inputDiv">
                            <label class="qlable">学分类型：</label>
                            <select name="scoreType" class="qselect">
                                <option/>
                                <option value="1" <c:if test="${param.scoreType eq '1'}">selected</c:if>>I类学分</option>
                                <option value="2" <c:if test="${param.scoreType eq '2'}">selected</c:if>>II类学分</option>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">年&#12288;&#12288;度：</label>
                            <select name="sessionNumber" class="qselect">
                                <option/>
                                <option value="2020" <c:if test="${param.sessionNumber eq '2020'}">selected</c:if>>2020</option>
                                <option value="2019" <c:if test="${param.sessionNumber eq '2019'}">selected</c:if>>2019</option>
                                <option value="2018" <c:if test="${param.sessionNumber eq '2018'}">selected</c:if>>2018</option>
                                <option value="2017" <c:if test="${param.sessionNumber eq '2017'}">selected</c:if>>2017</option>
                                <option value="2016" <c:if test="${param.sessionNumber eq '2016'}">selected</c:if>>2016</option>
                                <option value="2015" <c:if test="${param.sessionNumber eq '2015'}">selected</c:if>>2015</option>
                                <option value="2014" <c:if test="${param.sessionNumber eq '2014'}">selected</c:if>>2014</option>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">学&#12288;&#12288;科：</label>
                            <input type="hidden" id="selectProjId" name='subjectId'>
                            <input id="proSelect" name="subjectName" class="qtext" value="${param.subjectName}" onclick="showMenu();"/>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">姓&#12288;&#12288;名：</label>
                            <input type="text" name="userName" class="qtext" value="${param.userName}"/>
                        </div>
                        <c:if test="${flag eq 'local'}">
                            <div class="lastDiv"></div>
                        </c:if>
                        <div class="inputDiv">
                            <label class="qlable">学分是否完成：</label>
                            <select name="scoreTarget" class="qselect">
                                <option/>
                                <option value="Y" <c:if test="${param.scoreTarget eq 'Y'}">selected</c:if>>是</option>
                                <option value="N" <c:if test="${param.scoreTarget eq 'N'}">selected</c:if>>否</option>
                            </select>
                        </div>
                    </c:if>
                    <c:if test="${flag eq 'global'}">
                        <div class="inputDiv">
                            <label class="qlable">单&#12288;&#12288;位：</label>
                            <select name="orgFlow" class="qselect">
                                <option/>
                                <c:forEach var="sysOrg" items="${applicationScope.sysOrgList}">
                                    <option value="${sysOrg.orgFlow}" ${param.orgFlow eq sysOrg.orgFlow?'selected':''}>${sysOrg.orgName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">年&#12288;&#12288;度：</label>
                            <select name="sessionNumber" class="qselect">
                                <option/>
                                <option value="2020" <c:if test="${param.sessionNumber eq '2020'}">selected</c:if>>2020</option>
                                <option value="2019" <c:if test="${param.sessionNumber eq '2019'}">selected</c:if>>2019</option>
                                <option value="2018" <c:if test="${param.sessionNumber eq '2018'}">selected</c:if>>2018</option>
                                <option value="2017" <c:if test="${param.sessionNumber eq '2017'}">selected</c:if>>2017</option>
                                <option value="2016" <c:if test="${param.sessionNumber eq '2016'}">selected</c:if>>2016</option>
                                <option value="2015" <c:if test="${param.sessionNumber eq '2015'}">selected</c:if>>2015</option>
                                <option value="2014" <c:if test="${param.sessionNumber eq '2014'}">selected</c:if>>2014</option>
                            </select>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">姓&#12288;&#12288;名：</label>
                            <input type="text" name="userName" class="qtext" value="${param.userName}"/>
                        </div>
                        <div class="inputDiv">
                            <label class="qlable">职&#12288;&#12288;称：</label>
                            <select name="titleId" class="qselect">
                                <option/>
                                <c:forEach items="${dictTypeEnumUserTitleList}" var="title">
                                    <option value="${title.dictId}" ${param.titleId eq title.dictId?'selected':''}>${title.dictName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </c:if>
                    <div class="lastDiv">
                        <input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
                    </div>
                    <c:if test="${flag eq 'local'}">
                        <div>
                            <input type="button" class="searchInput" value="新&#12288;增" onclick="add('')"/>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
        <div id="menuContent" class="menuContent" style="display:none; position:absolute;">
            <ul id="treeDemo" class="ztree" style="margin-top:0; width:190px;"></ul>
        </div>
        <table class="xllist" style="width:100%;">
            <tr>
                <th>年度</th>
                <th>单位</th>
                <th>姓名</th>
                <th>性别</th>
                <th>身份证号</th>
                <th>职称</th>
                <th>最后晋升时间</th>
                <th>学科</th>
                <th>学分类型</th>
                <th>学分是否完成</th>
                <c:if test="${flag eq 'local'}"><th>操作</th></c:if>
            </tr>
            <c:forEach items="${scoreList}" var="score">
                <tr>
                    <td>${score.sessionNumber}</td>
                    <td>${score.orgName}</td>
                    <td>${score.userName}</td>
                    <td>${score.sexName}</td>
                    <td>${score.idNo}</td>
                    <td>${score.titleName}</td>
                    <td>${score.promoteTime}</td>
                    <td>${score.subjectName}</td>
                    <td>${score.scoreTypeName}</td>
                    <td>${score.scoreTarget eq 'N'?'否':''}${score.scoreTarget eq 'Y'?'是':''}</td>
                    <c:if test="${flag eq 'local'}">
                        <td><a style="cursor:pointer; color: blue;" onclick="add('${score.userFlow}')">编辑</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(scoreList)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>