<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="true"/>
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
    </jsp:include>
    <script type="text/javascript">
        function search(){
            jboxStartLoading();
            $("#searchForm").submit();
        }

        function toPage(page){
            var form = $("#searchForm");
            $("#currentPage").val(page);
            jboxStartLoading();
            form.submit();
        }
        function changeMenu(obj, orgFlow,orgName,menuId){
            var msg="";
            var isChecked="";
            if($(obj).attr('checked')){
                msg="确认开通此功能权限吗？";
                isChecked="Y";
            }else {
                msg="确认关闭此功能权限吗？";
                isChecked="N"
            }
            var url = "<s:url value='/osca/oscaOrgMenu/saveOrgMenu'/>?isChecked="+isChecked+"&orgFlow="+orgFlow+"&orgName="+encodeURIComponent(encodeURIComponent(orgName))+"&menuId="+menuId;
            jboxConfirm(msg,function () {
                jboxGet(url,null,function(resp){
                    jboxTip(resp);
                    toPage();
                });
            },function(){
                if($(obj).attr('checked')){
                    $(obj).attr('checked',false);
                }else {
                    $(obj).attr('checked',true);
                }
            });
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm" action="<s:url value="/osca/oscaOrgMenu/list" />" method="post" >
                <div class="queryDiv">
                <input type="hidden" name="currentPage" id="currentPage" value=""/>
                    <div class="inputDiv">
                        <label class="qlable">考&#12288;&#12288;点：</label>
                        <input class="qtext" name="orgName" type="text" value="${param.orgName}"/>
                    </div>
                    <div class="lastDiv">
                        <input type="button" class="searchInput" onclick="search();" value="查&#12288;询">
                    </div>
                </div>
            </form>
        </div>
        <table class="xllist">
            <tr>
                <th width="10%">序号</th>
                <th width="50%">考点</th>
                <th width="20%" >考核方案管理</th>
                <th width="20%" >评分表单配置</th>
                <%--<th width="20%" >考官信息管理</th>--%>
            </tr>
            <tbody id="sorttable">
            <c:if test="${not empty orgMenuList}">
                <c:forEach items="${orgMenuList}" var="orgMenu" varStatus="num">
                    <tr>
                        <td>${num.count}</td>
                        <td>${orgMenu.orgName}</td>
                        <td>
                            <input id="osca-gly-jcxxgl-khxm" <c:if test="${'osca-gly-jcxxgl-khxm' eq orgMenu.projectMenu}">checked="checked"</c:if> onchange="changeMenu(this,'${orgMenu.orgFlow }','${orgMenu.orgName }','osca-gly-jcxxgl-khxm');" type="checkbox" />
                        </td>
                        <td>
                            <input id="osca-gly-jcxxgl-pfbd" <c:if test="${'osca-gly-jcxxgl-pfbd' eq orgMenu.scoreMenu}">checked="checked"</c:if> onchange="changeMenu(this,'${orgMenu.orgFlow }','${orgMenu.orgName }','osca-gly-jcxxgl-pfbd');" type="checkbox" />
                        </td>
                        <%--<td>--%>
                            <%--<input id="osca-gly-jcxxgl-kgxx" <c:if test="${'osca-gly-jcxxgl-kgxx' eq orgMenu.examMenu}">checked="checked"</c:if> onchange="changeMenu(this,'${orgMenu.orgFlow }','${orgMenu.orgName }','osca-gly-jcxxgl-kgxx');" type="checkbox" />--%>
                        <%--</td>--%>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
            <c:if test="${orgMenuList == null || orgMenuList.size()==0 }">
                <tr>
                    <td align="center" colspan="4">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <c:set var="pageView" value="${pdfn:getPageView(orgMenuList)}" scope="request"></c:set>
    <pd:pagination toPage="toPage"/>
</div>
</body>
</html>