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
    <style>
        a{color:#4195c5}
    </style>
    <script>
        function search(){
            $("#searchForm").submit();
        }
        function toPage(page) {
            if(page){
                $("#currentPage").val(page);
            }
            search();
        }
        function del(flow,type){
            var tip;
            if(type=='delete') tip="确认删除？";
            if(type=='disable') tip="确认停用？";
            if(type=='undisable') tip="确认启用？";
            jboxConfirm(tip,function(){
                var url = '<s:url value="/osca/formCfg/edit/nothing/?fromFlow="/>'+flow+"&type="+type;
                jboxPost(url,null,function(resp){
                    if(resp==1) jboxTip("操作成功！")
                    else jboxTip("操作失败！")
                    search();
                },null,false)
            })
        }
        function add(){
            var url = "<s:url value='/osca/formCfg/newForm/${roleFlag}'/>";
            jboxOpen(url,"添加表单",400,220);
        }
        function edit(flow,name,flag){
            jboxOpen('<s:url value="/osca/formCfg/editFrom?flag="/>'+flag+"&fromFlow="+flow,name+"详情",1200,600)
        }
        function editFromName(flow) {
            jboxOpen('<s:url value="/osca/formCfg/newForm/${roleFlag}?fromFlow="/>'+flow,"修改表单名称",400,220)
        }

        function copyFrom(flow,fromName,type) {
            jboxOpen('<s:url value="/osca/formCfg/copyForm?fromFlow="/>'+flow+"&fromName="+fromName+"&type="+type,"复制表单",400,220)
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value='/osca/formCfg/list/${roleFlag}'/>" method="post">

            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <div class="queryDiv">

            <%--<table class="basic" style="margin-top: 10px;width: 100%">--%>
                <%--<tr style="height: 54px;">--%>
                    <%--<td>--%>
                        <c:if test="${roleFlag eq 'hospital'}">
                        <div class="inputDiv">
                            <label class="qlable">表单类型：</label>
                            <select name="fromTypeId" class="qselect">
                                <option value="">全部</option>
                                <option value="1" ${(param.fromTypeId eq '1')?'selected':""}>固定表单</option>
                                <option value="0" ${(param.fromTypeId eq '0')?'selected':""}>自定义表单</option>
                            </select>
                        </div>
                        </c:if>
                        <div class="inputDiv">
                            <label class="qlable">表单名称：</label>
                            <input class="qtext" type="text" value="${param.fromName}" name="fromName"/>
                        </div>

                        <div class="lastDiv">
                            <input class="searchInput" type="button" value="查&#12288;询" onclick="search();"/>
                        </div>
                        <div class="lastDiv">
                            <input class="searchInput" type="button" value="新&#12288;增" onclick="add();"/>
                        </div>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>
                </div>
        </form>
        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <th>序号</th>
                <th>表单名称</th>
                <c:if test="${roleFlag eq 'hospital'}">
                    <th>表单类型</th>
                </c:if>
                <th>操作</th>
            </tr>
            <c:forEach items="${froms}" var="item" varStatus="s">
                <tr>
                    <td>${s.index+1}</td>
                    <td>${item.fromName}</td>
                    <c:if test="${roleFlag eq 'hospital'}">
                        <td>${item.fromTypeName}</td>
                    </c:if>
                    <td style="text-align: center;width: 40%">
                        <c:if test="${roleFlag eq 'province'}">
                            <c:if test="${item.isReleased eq 'Y'}">
                                <a style="cursor: pointer" onclick="del('${item.fromFlow}','disable')">停用</a>
                            </c:if>
                            <c:if test="${item.isReleased eq 'N'}">
                                <a style="cursor: pointer" onclick="del('${item.fromFlow}','undisable')">启用</a>
                            </c:if>
                               |
                            <c:if test="${item.fromTypeId eq '1'}">
                                <a style="cursor: pointer" onclick="jboxOpen('<s:url value="/${item.fromUrl}"/>','${item.fromName}',700,500)">
                                    查看</a>
                                |
                                <a style="cursor: pointer" onclick="copyFrom('${item.fromFlow}copy','${item.fromName}','province')">复制</a>
                            </c:if>
                            <c:if test="${item.fromTypeId eq '0'}">
                                <a style="cursor: pointer" onclick="edit('${item.fromFlow}','${item.fromName}','read')">查看</a>
                                |
                                <a style="cursor: pointer" onclick="edit('${item.fromFlow}','${item.fromName}','edit')">编辑</a>
                              <%--  |
                                <a style="cursor: pointer" onclick="editFromName('${item.fromFlow}')">修改</a>--%>
                                |
                                <a style="cursor: pointer" onclick="del('${item.fromFlow}','delete')">删除</a>
                                |
                                <a style="cursor: pointer" onclick="copyFrom('${item.fromFlow}','${item.fromName}','')">复制</a>
                            </c:if>
                       </c:if>
                       <c:if test="${roleFlag eq 'hospital'}">
                           <c:if test="${(item.fromTypeId eq '1')&&(item.orgFlow eq 'jsst')}">
                               <a style="cursor: pointer" onclick="jboxOpen('<s:url value="/${item.fromUrl}"/>','${item.fromName}',700,500)">
                                   查看</a>
                               |
                               <a style="cursor: pointer" onclick="copyFrom('${item.fromFlow}copy','${item.fromName}','hospital')">复制</a>
                           </c:if>
                           <c:if test="${(item.fromTypeId eq '0')&&(item.orgFlow eq 'jsst')}">
                               <a style="cursor: pointer" onclick="edit('${item.fromFlow}','','read')">查看</a>
                               |
                               <a style="cursor: pointer" onclick="copyFrom('${item.fromFlow}','${item.fromName}','hospital')">复制</a>
                           </c:if>
                           <c:if test="${!(item.orgFlow eq 'jsst')}">
                               <a style="cursor: pointer" onclick="edit('${item.fromFlow}','${item.fromName}','read')">查看</a>
                               |
                               <a style="cursor: pointer" onclick="edit('${item.fromFlow}','${item.fromName}','edit')">编辑</a>
                               <%--|
                               <a style="cursor: pointer" onclick="editFromName('${item.fromFlow}')">修改</a>--%>
                               |
                               <a style="cursor: pointer" onclick="del('${item.fromFlow}','delete')">删除</a>
                               |
                               <a style="cursor: pointer" onclick="copyFrom('${item.fromFlow}','${item.fromName}','')">复制</a>
                           </c:if>
                       </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty froms}">
                <tr>
                    <td colspan="${(roleFlag eq 'hospital')?4:3}">无记录</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(froms)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
