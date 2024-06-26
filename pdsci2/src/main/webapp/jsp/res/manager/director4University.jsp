<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        function search(){
            $("#searchForm").submit();
        }
        function edit(recordFlow,roleFlag){
            jboxOpen("<s:url value='/res/liveTraining/openDirector'/>?recordFlow="+recordFlow+"&roleFlag="+roleFlag,"详细信息",1000,500);
        }
        function del(recordFlow){
            jboxConfirm("确认删除?",function(){
            jboxPost("<s:url value='/res/liveTraining/delDirector'/>?recordFlow="+recordFlow,null,function(resp){
                if('${GlobalConstant.DELETE_SUCCESSED}'==resp){
                    jboxTip("删除成功!");
                    search();
                }
            },null,false);
            });
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value='/res/liveTraining/director4University'/>" method="post">
                <input id="currentPage" type="hidden" name="currentPage" value=""/>
                <%--<table class="basic" style="width:100%; margin-bottom: 10px; margin-top: 10px;border: none;">--%>
                <%--<tr>--%>
                <%--<td style="border: none;">--%>
                <%----%>
                <%--</td>--%>
                <%--</tr>--%>
                <%--</table>--%>
                <div class="queryDiv">
                    <div class="inputDiv">
                        培训基地：
                        <select name="orgFlow" class="qselect">
                            <option value="">全部</option>
                            <c:forEach items="${orgList}" var="org">
                                <option value="${org.orgFlow}" ${org.orgFlow eq param.orgFlow?'selected="selected"':''}>${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="inputDiv">
                        <label class="qlable"><%--住培--%>指南标题：</label>
                        <input name="resNoticeTitle" value="${param.resNoticeTitle}" type="text" class="qtext"/>
                    </div>

                    <div class="lastDiv">
                        <input type="button" class="searchInput" value="查&#12288;询" onclick="search();">
                    </div>
                    <%--<div class="lastDiv">--%>
                        <%--<c:if test="${'manager'eq roleFlag}">--%>
                            <%--<input type="button" class="searchInput" value="新&#12288;增" onclick="edit('','manager')">--%>
                        <%--</c:if>--%>
                    <%--</div>--%>
                </div>
            </form>
        </div>
        <div class="resultDiv">
            <table class="xllist" style="margin-top: 10px;">
                <%--<tr>
                    <th colspan="3" style="text-align: center;">
                        住培指南
                    </th>
                </tr>--%>
                <tr>
                    <th width="5%">序号</th>
                    <th width="15%">基地</th>
                    <th width="72%">指南标题</th>
                    <th width="8%">操作</th>
                </tr>
                <c:forEach items="${tarinNotices}" var="tarinNotice" varStatus="status">
                    <tr>
                        <td >${status.count}</td>
                        <td >${tarinNotice.orgName}</td>
                        <td >${tarinNotice.resNoticeTitle}</td>
                        <td >
                            <%--<c:if test="${'manager'eq roleFlag}">--%>
                                <%--<a onclick="edit('${tarinNotice.recordFlow}','manager')"style="cursor: pointer;color: #0a8cd2">编辑</a>--%>
                                <%--<a onclick="del('${tarinNotice.recordFlow}')"style="cursor: pointer;color: #0a8cd2">删除</a>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${'doctor'eq roleFlag}">--%>
                                <a onclick="edit('${tarinNotice.recordFlow}','doctor')"style="cursor: pointer;color: #0a8cd2">查看</a>
                            <%--</c:if>--%>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty tarinNotices}">
                    <tr>
                        <td colspan="2">无记录!</td>
                    </tr>
                </c:if>
            </table>
        </div>

        <div class="resultDiv">
            <c:set var="pageView" value="${pdfn:getPageView(tarinNotices)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
