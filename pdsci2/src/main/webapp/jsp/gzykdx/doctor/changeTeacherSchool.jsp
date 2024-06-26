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
    </jsp:include>
    <script type="text/javascript">
        function search(){
            $("#serchForm").submit();
        }
        function detail(orgFlow,param) {
            $("#docInfoDiv1").hide();
            $("#resoultInfoDiv1").show();
            var url = "<s:url value='/gzykdx/teaAndDoc/showNotFullTeachers?orgFlow='/>" + orgFlow+"&degreeTypeId="+param;
            jboxLoad("resoultInfoDiv1",url,true);
        }
        function choseTeacher() {
            var checkLen = $(":checkbox[class='check']:checked").length;
            if (checkLen == 0) {
                jboxTip("请勾选导师信息！");
                return;
            } else if (checkLen > 1) {
                jboxTip("只能勾选一条导师信息！");
                return;
            }else{
                var applyFlow=$(":checkbox[class='check']:checked").val();
                var url = "<s:url value='/gzykdx/teaAndDoc/saveChose?applyFlow='/>" + applyFlow;
                jboxPost(url,null,function(resp){
                    jboxTip(resp);
                    setTimeout("window.parent.frames['mainIframe'].location.reload(true);jboxClose();", 1000);
                });
            }
        }
        function search1(){
            $("#searchForm1").submit();
        }
    </script>
</head>
<body>

<div class="mainright">
    <div class="" id="docInfoDiv1">
    <div class="content">
        <div class="title1 clearfix">
            <form id="serchForm" action="<s:url value="/gzykdx/teaAndDoc/showNotFullOrgs"/>"  method="post">
                <%--<div class="choseDivNewStyle">--%>
                    <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                        <tr>
                            <td style="border:0px;">
                                <input type="text" name="year" value="${pdfn:getCurrDateTime('yyyy')}" hidden="hidden"/>
                                <input type="text" name="flage" value="${flage}" hidden="hidden"/>
                                <span style="margin-left: -10px;"></span>机构名称：
                                <select name="orgFlow" onchange="search()" class="select" style="width:180px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${orgList}" var="org">
                                        <option <c:if test="${param.orgFlow eq org.orgFlow }">selected="selected"</c:if>
                                                value="${org.orgFlow}" orgName="${org.orgName}">
                                                ${org.orgName}</option>
                                    </c:forEach>
                                </select>
                                学位类型：
                                <select name="degreeTypeId" onchange="search()" style="width:137px;" class="select">
                                    <option value="">全部</option>
                                    <c:forEach items="${gzykdxDegreeTypeEnumList}" var="status">
                                        <option value="${status.id}" ${param.degreeTypeId eq status.id ?'selected':''}>${status.name}</option>
                                    </c:forEach>
                                </select>
                                <%--<input type="button" class="search" onclick="searchOrg();" value="确&#12288;定"><br/>--%>
                            </td>
                        </tr>
                    </table>
                <%--</div>--%>
            </form>
        </div>

        <table class="xllist" >
            <tr>
                <th>机构</th>
                <th>学术学位拟招生缺额人数</th>
                <th>专业学位拟招生缺额人数</th>
            </tr>
            <c:forEach items="${recruitInfoList}" var="info">
                <tr>
                    <td>${info['ORG_NAME']}</td>
                    <td>
                        <c:choose>
                            <c:when test="${flage eq gzykdxDegreeTypeEnumAcademicType.id or empty flage}">
                                <a onclick="detail('${info['ORG_FLOW']}','${gzykdxDegreeTypeEnumAcademicType.id}')" style="cursor:pointer;color:#4195c5;">
                                        ${info['ACADEMICNUM']}
                                </a>
                            </c:when>
                            <c:otherwise>
                                ${info['ACADEMICNUM']}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${flage eq gzykdxDegreeTypeEnumProfessionalType.id or empty flage}">
                                <a onclick="detail('${info['ORG_FLOW']}','${gzykdxDegreeTypeEnumProfessionalType.id}')" style="cursor:pointer;color:#4195c5;">
                                        ${info['SPECIALIZEDNUM']}
                                </a>
                            </c:when>
                            <c:otherwise>
                                ${info['SPECIALIZEDNUM']}
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty recruitInfoList}">
                <tr>
                    <td colspan="8">暂无信息</td>
                </tr>
            </c:if>
        </table>
    </div>
    </div>
    <div class="content" id="resoultInfoDiv1" style="display:none;">

    </div>
</div>
</body>
</html>