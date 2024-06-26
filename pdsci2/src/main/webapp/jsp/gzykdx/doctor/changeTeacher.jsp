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
                jboxConfirm("确认选择该导师吗？",function() {
                    jboxPost(url,null,function(resp){
                        jboxTip(resp);
                        setTimeout("window.parent.frames['mainIframe'].location.reload(true);jboxClose();", 1000);
                    });
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
    <div class="content">
        <div class="title1 clearfix">
            <form id="searchForm1" action="<s:url value="/gzykdx/teaAndDoc/showNotFullTeachers"/>"  method="post">
                <%--<div class="choseDivNewStyle">--%>
                    <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                        <tr>
                            <td style="border:0px;">
                                <span style="margin-left: -10px;"></span>姓&#12288;&#12288;名：
                                <input type="text" name="userName" value="${param.userName}" style="width:137px;" onchange="search1()">
                                <input type="text" name="orgFlow" value="${orgFlow}" hidden="hidden">
                                <input type="text" name="stuSpeId" value="${stuSpeId}" hidden="hidden">
                                <input type="text" name="degreeTypeId" value="${degreeTypeId}" hidden="hidden">
                                <span style="padding-left:10px;"></span>专业名称：
                                <select name="speId" style="width:137px;" onchange="search1()" class="select">
                                    <option value="">全部</option>
                                    <c:forEach items="${dictTypeEnumGzykdxSpeList}" var="status">
                                        <option value="${status.dictId}" ${param.speId eq status.dictId ?'selected':''}>${status.dictName}[${status.dictId}]</option>
                                    </c:forEach>
                                </select>
                                研究方向：
                                <select name="researchDirectionId" onchange="search1()" class="xlselect" style="width:137px;">
                                    <option value="">请选择</option>
                                    <c:forEach items="${dictTypeEnumResearchAreaList }" var="dict">
                                        <option
                                                <c:if test="${param.researchDirectionId eq dict.dictId }">selected="selected"</c:if>
                                                value="${dict.dictId }">${dict.dictName }</option>
                                    </c:forEach>
                                </select>&#12288;
                                <input type="button" class="search" onclick="choseTeacher();" value="确&#12288;定"><br/>
                            </td>
                        </tr>
                    </table>
                <%--</div>--%>
            </form>
        </div>

        <table class="xllist">
            <tr>
                <th>序号</th>
                <th>导师</th>
                <th>专业名称</th>
                <th>研究方向</th>
                <th>学术学位招生缺额人数</th>
                <th>专业学位招生缺额人数</th>
                <th>学位类型</th>
            </tr>
            <c:forEach items="${teacherApplyList}" var="apply">
                <tr>
                    <td><input type="checkbox" id="subcheck" class="check" value="${apply.APPLY_FLOW}"></td>
                    <td>${apply.USER_NAME}</td>
                    <td>${apply.SPE_NAME}</td>
                    <td>${apply.RESEARCH_DIRECTION}</td>
                    <td>${apply.ACADEMICSURPLUS}</td>
                    <td>${apply.SPECIALIZEDSURPLUS}</td>
                    <td>
                        <c:if test="${apply.IS_ACADEMIC eq 'Y'}">学术型</c:if>
                        <c:if test="${apply.IS_ACADEMIC eq 'Y' and apply.IS_SPECIALIZED eq 'Y'}">&#12288;</c:if>
                        <c:if test="${apply.IS_SPECIALIZED eq 'Y'}">专业型</c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>