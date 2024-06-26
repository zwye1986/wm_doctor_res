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
    <script type="text/javascript">
        function search(){
            $("#searchForm").submit();
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
        <form id="searchForm" action="<s:url value='/gzykdx/school/recruitLogList'/>" method="post">
            <input name="currentPage" id="currentPage" type="hidden" value="">
            <table class="basic" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <td>
                        年份：<input type="text" name="recruitYear" value="${param.recruitYear}" onchange="search();" style="width: 60px;">
                        &#12288;
                        导师：<input type="text" name="userName" value="${param.userName}" onchange="search();" style="width: 60px;">
                        &#12288;
                        修改日期：<input type="text" name="startChangeTime" value="${param.startChangeTime}" onchange="search();" style="width: 100px;"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" >
                        至
                        <input type="text" name="endChangeTime" value="${param.endChangeTime}" onchange="search();" style="width: 100px;"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" >
                        &#12288;
                    </td>
                </tr>
            </table>
        </form>
        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <th style="width: 5%;">年份</th>
                <th style="width: 10%;">专业名称</th>
                <th style="width: 21%;">研究方向</th>
                <th style="width: 10%;">导师</th>
                <th style="width: 12%;">修改项</th>
                <th style="width: 7%;">原数据</th>
                <th style="width: 7%;">新数据</th>
                <th style="width: 12%;">修改时间</th>
                <th style="width: 10%;">修改账户</th>
                <th style="width: 10%;">账户单位</th>
                <th style="width: 10%;">账户姓名</th>
            </tr>
            <c:forEach items="${logs}" var="log" varStatus="s">
                <tr>
                    <td>${log.RECRUIT_YEAR}</td>
                    <td>${log.SPE_NAME}[${log.SPE_ID}]</td>
                    <td style="line-height: 130%;">${log.RESEARCH_DIRECTION}</td>
                    <td>${log.USER_NAME}</td>
                    <td>
                        <c:if test="${log.CHANGE_ITEM eq 'academicNum'}">学术学位拟招生人数</c:if>
                        <c:if test="${log.CHANGE_ITEM eq 'specializedNum'}">专业学位拟招生人数</c:if>
                    </td>
                    <td class="oldData">${log.OLD_DATA}</td>
                    <td class="newData">${log.NEW_DATA}</td>
                    <script>
                        var oldData = $(".oldData").eq(${s.index}).text();
                        var newData = $(".newData").eq(${s.index}).text();
                        var oldDatas = oldData.split("");
                        var newDatas = newData.split("");
                        var result ="";
                        var result2 ="";
                        for(var i=0;i<oldDatas.length;i++){
                            if(oldDatas[i]==newDatas[i]){
                                result = result+oldDatas[i];
                            }else{
                                result = result +'<font color="red" size="3">'+oldDatas[i]+'</font>';
                            }
                            $(".oldData").eq(${s.index}).html(result);
                        }
                        for(var i=0;i<newDatas.length;i++){
                            if(oldDatas[i]==newDatas[i]){
                                result2 = result2+newDatas[i];
                            }else{
                                result2 = result2 +'<font color="red" size="3">'+newDatas[i]+'</font>';
                            }
                            $(".newData").eq(${s.index}).html(result2);
                        }
                    </script>
                    <td>${pdfn:transDateTime(log.CHANGE_TIME)}</td>
                    <td>${log.CHANGE_USER_CODE}</td>
                    <td>${log.ORG_NAME}</td>
                    <td>${log.SYSUSERNAME}</td>
                </tr>
            </c:forEach>
            <c:if test="${empty logs}">
                <tr>
                    <td colspan="8">无记录</td>
                </tr>
            </c:if>
        </table>
        <div>
            <c:set var="pageView" value="${pdfn:getPageView(logs)}" scope="request"/>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>
