<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <jsp:param name="jquery_cxselect" value="false" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="false" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="false" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="false" />
        <jsp:param name="jquery_placeholder" value="false" />
        <jsp:param name="jquery_iealert" value="false" />
    </jsp:include>
    <title>轮转异常查询</title>
    <link rel="stylesheet" href="<s:url value='/jsp/res/css/squeezebox.css'/>?t=9&v=${applicationScope.sysCfgMap['sys_version']}" type="text/css" media="screen"/>
    <script>

        function search(){
            var startDate=$("#startDate").val();
            var endDate=$("#endDate").val();
            if(startDate==""||startDate==undefined)
            {
                jboxTip("开始时间不得为空！！");
                return false;
            }
            if(endDate==""||endDate==undefined)
            {
                jboxTip("结束时间不得为空！！");
                return false;
            }
            if(startDate!=""&&endDate!=""&&endDate<startDate)
            {
                jboxTip("开始时间不得大于结束时间！！");
                return false;
            }
            jboxStartLoading();
            $("#searchForm").submit();
        }
        function showDetail(deptFlow,deptName,type)
        {
            var msg="【"+deptName+"】";
            if(type=="WarningBefore")
            {
                msg+="入科异常学员列表";
            }
            if(type=="WarningIng")
            {
                msg+="出科异常学员列表";
            }
            if(type=="WarningAfter")
            {
                msg+="出科考核异常学员列表";
            }
            var url="<s:url value='/res/prewarning/detail'/>?schStartDate=${schStartDate}&schEndDate=${schEndDate}&sessionNumber=${sessionNumber}&deptFlow="+deptFlow+"&type="+type;
            jboxOpen(url,msg,1050,500,true);
        }

        function warningExport(type)
        {

            var url="<s:url value='/res/prewarning/warningExport'/>?type="+type;
            jboxTip("导出中…………");
            jboxExp($("#searchForm"),url);
        }
    </script>
</head>
<body>
<div class="queryDiv" style="width:100%;">
    <div style="margin-left: 23px;margin-bottom: 8px;">
        <jsp:include page="/res/doc/newNoticeList">
            <jsp:param name="fromSch" value="Y"></jsp:param>
        </jsp:include>
    </div>
    <form id="searchForm" action="<s:url value='/res/prewarning/list/local'/>"  method="post">
        <div class="inputDiv" style="min-width: 300px;max-width: 300px;">
            <label class="qlable">时间：</label>
            <input type="text" id="startDate" name="schStartDate" value="${schStartDate}"
                   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'${pdfn:getCurrDate()}'})" style="width: 85px;" class="qtime" />
            ~
            <input type="text" id="endDate" name="schEndDate" value="${schEndDate}"
                   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'${pdfn:getCurrDate()}'})" style="width: 85px;" class="qtime" />
        </div>
        <div class="inputDiv"  style="min-width: 150px;max-width: 150px;">
            <label class="qlable">年级：</label>
            <input type="text"  name="sessionNumber" value="${sessionNumber}"
                   readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'})" style="width: 85px;" class="qtime" />
          </div>
        <div class="lastDiv" style="max-width: none;">
            <input type="button" class="searchInput" value="查&#12288;询" onclick="search();">
            <input type="button" class="searchInput" value="导&#12288;出" onclick="warningExport('WarningAll');">
        </div>
    </form>
</div>
<div id="wrapper-250">
    <ul class="accordion" id="acc01">
        <c:set var="befQty" value="0"></c:set>
        <c:forEach items="${listBefore}" var="lb">
            <c:set var="befQty" value="${empty lb ? befQty : lb.qty + befQty}"></c:set>
        </c:forEach>
        <li id="one"><a href="javascript:warningExport('WarningBefore','${schStartDate}','${schEndDate}','${sessionNumber}')"   style="color: black;font-weight: bold;">入科异常&nbsp;<img title="该入科但未入科的学员" style="margin-top: -3px" src="<s:url value='/jsp/res/images/wenhao.png'/>"><span style="margin-right:110px;">共${befQty}人</span><span >导出</span></a>
            <ul class="sub-menu" id="accu01">
                <c:if test="${empty listBefore}">
                    <li><a href="#" style="text-align:center;padding:0px;">无即将入科学员</a></li>
                </c:if>
                <c:forEach items="${listBefore}" varStatus="i" var="bef">
                    <c:set var="inx" scope="page" value="${i.index < 10 ? ('0'+(i.index+1)) : (i.index+1)}"></c:set>
                    <li><a href="javascript:void(0)" onclick="showDetail('${bef.id}','${bef.name}','WarningBefore')"><em>${inx}</em>${bef.name}<span>${bef.qty}人</span></a></li>
                </c:forEach>
            </ul>
        </li>
    </ul>
    <ul class="accordion" id="acc02">
        <c:set var="ingQty" value="0"></c:set>
        <c:forEach items="${listIng}" var="lb">
            <c:set var="ingQty" value="${empty lb ? ingQty : lb.qty + ingQty}"></c:set>
        </c:forEach>
        <li id="two"><a href="javascript:warningExport('WarningIng')" style="color: black;font-weight: bold;">出科异常&nbsp;<img title="该出科但未出科的学员" style="margin-top: -3px" src="<s:url value='/jsp/res/images/wenhao.png'/>"><span style="margin-right:110px;">共${ingQty}人</span><span >导出</span></a>
            <ul class="sub-menu" id="accu02">
                <c:if test="${empty listIng}">
                    <li><a href="#" style="text-align:center;padding:0px;">无出科异常学员</a></li>
                </c:if>
                <c:forEach items="${listIng}" varStatus="i" var="bef">
                    <c:set var="inx" scope="page" value="${i.index < 10 ? ('0'+(i.index+1)) : (i.index+1)}"></c:set>
                    <li><a href="javascript:void(0)" onclick="showDetail('${bef.id}','${bef.name}','WarningIng')"><em>${inx}</em>${bef.name}<span>${bef.qty}人</span></a></li>
                </c:forEach>
            </ul>
        </li>
    </ul>
    <ul class="accordion" id="acc03">
        <c:set var="afterQty" value="0"></c:set>
        <c:forEach items="${listAfter}" var="lb">
            <c:set var="afterQty" value="${empty lb ? afterQty : lb.qty + afterQty}"></c:set>
        </c:forEach>
        <li id="three"><a href="javascript:warningExport('WarningAfter')" style="color: black;font-weight: bold;">出科考核异常&nbsp;<img title="出科考核表未审核的学员" style="margin-top: -3px" src="<s:url value='/jsp/res/images/wenhao.png'/>"><span style="margin-right:110px;">共${afterQty}人</span><span >导出</span></a>
            <ul class="sub-menu" id="accu03">
                <c:if test="${empty listAfter}">
                    <li><a href="#" style="text-align:center;padding:0px;">无出科考核异常学员</a></li>
                </c:if>
                <c:forEach items="${listAfter}" varStatus="i" var="bef">
                    <c:set var="inx" scope="page" value="${i.index < 10 ? ('0'+(i.index+1)) : (i.index+1)}"></c:set>
                    <li><a href="javascript:void(0)" onclick="showDetail('${bef.id}','${bef.name}','WarningAfter')"><em>${inx}</em>${bef.name}<span>${bef.qty}人</span></a></li>
                </c:forEach>
            </ul>
        </li>
    </ul>
</div>
</body>
<script type="text/javascript">
    function setWH(){
        var width = document.body.clientWidth / 3 - 40;
        var height = document.body.clientHeight - 90;
        if(width < 250){
            width = 250;
        }
//        $('#acc01').height(height);
        $('#acc01').width(width);
//        $('#acc02').height(height);
        $('#acc02').width(width);
//        $('#acc03').height(height);
        $('#acc03').width(width);

        $('#accu01').height((height-40));
        $('#accu02').height((height-40));
        $('#accu03').height((height-40));
    }
    $(document).ready(function () {
        setWH();
        $('#acc01 > li > a').first().addClass('active').next().slideDown('normal');
        $('#acc02 > li > a').first().addClass('active').next().slideDown('normal');
        $('#acc03 > li > a').first().addClass('active').next().slideDown('normal');
    });
    window.onresize = function(){
        setWH();
    }
</script>
</html>