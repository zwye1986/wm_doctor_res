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
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="treetable" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function addScore(parentScoreFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/score/addScore'/>?parentScoreFlow=" + parentScoreFlow, "新增积分项", 400, 300);
        }

        function addScoreType(typeFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/score/addScoreType'/>?typeFlow=" + typeFlow, "新增积分类别", 400, 200);
        }
        function editScoreType(typeFlow) {
            jboxStartLoading();
            jboxOpen("<s:url value='/srm/ach/score/editScoreType'/>?typeFlow=" + typeFlow, "修改积分类别", 400, 200);
        }
        function setScoreStatus(msg, scoreFlow, scoreStatus) {
            jboxConfirm("确认" + msg + "该积分项吗？", function () {
                var url = "<s:url value='/srm/ach/score/modifyScoreStatus?scoreFlow='/>" + scoreFlow + "&scoreStatus=" + scoreStatus;
                jboxPost(url, null, function (resp) {
                    jboxTip(resp);
                    window.parent.frames['mainIframe'].location.reload(true);
                });
            });
        }
        function scoreList(parentScoreFlow) {
         //   alert(parentScoreFlow);
            var url = "<s:url value='/srm/ach/score/scoreList'/>?expandNode=" + parentScoreFlow;
            window.location.href = url;
        }

        function delScore(scoreFlow){
            jboxConfirm("确认删除该积分项吗？", function () {
                var url = "<s:url value='/srm/ach/score/delScore?scoreFlow='/>" + scoreFlow;
                jboxPost(url, null, function (resp) {
                    jboxTip(resp);
                    if(resp == '${GlobalConstant.DELETE_SUCCESSED}') {
                        window.location.reload(true);
                    }
                });
            });
        }
        function delScoreType(typeFlow){
            jboxConfirm("确认删除该类积分项吗？", function () {
                var url = "<s:url value='/srm/ach/score/delScoreType?typeFlow='/>" + typeFlow;
                jboxPost(url, null, function (resp) {
                    jboxTip(resp);
                    if(resp == '${GlobalConstant.DELETE_SUCCESSED}') {
                        window.location.reload(true);
                    }
                });
            });
        }

        function updateScore(scoreFlow){
            jboxConfirm("确认修改该积分项吗？", function () {
                var url = "<s:url value='/srm/ach/score/editScore?scoreFlow='/>" + scoreFlow;
                jboxOpen(url, "修改积分项", 400, 300);
                /*jboxPost(url, null, function (resp) {
                    jboxTip(resp);
                    if(resp == '${GlobalConstant.DELETE_SUCCESSED}') {
                        window.location.reload(true);
                    }
                });*/
            });
        }

        $(document).ready(function () {
            var heads = ["积分类别/积分项", "分值（科室）", "分值（个人）", "备注", "操作"];
            var tNodes = ${jsonData};
            $.TreeTable("treeMenu", heads, tNodes);
            <c:if test="${not empty parentFlowList}">
            <c:forEach var="expandNode" items="${parentFlowList}">
            var expandNode = "${expandNode}";
            if (expandNode) {
                $('#treeMenu').treetable('expandNode', expandNode);
            }
            </c:forEach>
            </c:if>
            // $('#treeMenu').treetable('expandAll');
            $("#treeMenu tr").bind("dblclick",function (){
                $('#treeMenu').treetable('collapseOrexpand',$(this).attr("data-tt-id"));
            });
        });
    </script>
    <style type="text/css">
        a {
            color: #00a0ff;
        }

        .selected a {
            color: #ffffff;
        }

        a:hover {
            text-decoration: underline;
            color: #f60;
        }
    </style>
</head>
<body>

<%--<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">--%>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action=""/>
        <div style="height: 30px;border-bottom-color: black;float: right;margin-top: 10px;">
            <input class="search" type="button" value="新增积分类别" onclick="addScoreType('')"/>&#12288;
            <input class="search" type="button" value="新增积分项" onclick="addScore('')"/>
            <span style="padding-left:30px;"></span>
        </div>

        <%--<a href="#" onclick="jQuery('#treeMenu').treetable('expandAll'); return false;">展开 all</a>--%>
        <%--<a href="#" onclick="jQuery('#treeMenu').treetable('collapseAll'); return false;">折叠 all</a>--%>
        <table id="treeMenu" class="linetable xllist"></table>
        </form>
    </div>
</div>

</body>
</html>
