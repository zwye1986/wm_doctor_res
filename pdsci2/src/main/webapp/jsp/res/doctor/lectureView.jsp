<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true" />
    <jsp:param name="jquery_cxselect" value="true" />
    <jsp:param name="jquery_validation" value="true" />
    <jsp:param name="jquery_datePicker" value="true" />
    <jsp:param name="jquery_fullcalendar" value="true" />
    <jsp:param name="jquery_fixedtableheader" value="true" />
    <jsp:param name="jquery_placeholder" value="true" />
</jsp:include>
<script type="text/javascript">
    $(function(){
        search("${param.tabType}", 1);
    });

    function search(tabType,currentPage){
        if (!currentPage) {
            currentPage = 1;
        }
        var url = "<s:url value='/res/manager/getNewLectures'/>?roleId=${param.roleId}&currentPage=" + currentPage + "&tabType=" + tabType + "&" + $("#searchForm").serialize();
        if("history" == tabType){
            url = "<s:url value='/res/manager/getHistoryLectures'/>?roleId=${param.roleId}&currentPage=" + currentPage + "&tabType=" + tabType + "&" + $("#searchForm").serialize();
        }
        jboxLoad("tagContent", url, true);
    }

    // tab页切换
    function tabClick(tag,tabType){
        $('.tab_select').removeClass('tab_select');
        $(tag).removeClass('tab');
        $(tag).addClass('tab_select');
        search(tabType, 1);
    }
</script>
<div class="main_hd">
    <c:choose>
        <c:when test="${param.isNew eq 'Y'}">
            <h2 class="underline">讲座活动预约</h2>
        </c:when>
        <c:when test="${param.isEval eq 'Y'}">
            <h2 class="underline">讲座活动评价</h2>
        </c:when>
        <c:otherwise>
            <h2>讲座活动查询</h2>
            <div class="title_tab">
                <ul>
                    <li class=" ${param.tabType eq 'new'?'tab_select':'tab'}" onclick="tabClick(this,'new');"><a>最新讲座</a></li>
                    <li class=" ${param.tabType eq 'history'?'tab_select':'tab'}" onclick="tabClick(this,'history');"><a>历史讲座</a></li>
                </ul>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<div class="main_bd">
    <div id="tagContent" style="border: 0px;margin: 0px 20px 20px;">
    </div>
</div>