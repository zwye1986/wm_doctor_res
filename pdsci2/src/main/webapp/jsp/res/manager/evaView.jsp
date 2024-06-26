<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
<script type="text/javascript">
    $(function(){
        $("li").click(function(){
            $(".selectTag").removeClass("selectTag");
            $(this).addClass("selectTag");
        });
        <c:if test="${flag eq 'N'}">
        $("#Y").click();
        </c:if>
        <c:if test="${flag eq 'Y'}">
        $("#N").click();
        </c:if>
        <c:if test="${flag eq 'N2'}">
        $("#Y2").click();
        </c:if>
        <c:if test="${flag eq 'Y2'}">
        $("#N2").click();
        </c:if>
    });
    function selTag(tag,inx,data,flag){
        $('.tab_select').removeClass('tab_select');
        $(tag).removeClass('tab');
        $(tag).addClass('tab_select');
        var url = '<s:url value="/res/manager/"/>'+inx+"?lectureFlow="+data+"&flag="+flag;
        jboxLoad("mainDiv",url,false);
    }

    function exportInfo(inx,flag){
        var url = '<s:url value="/res/manager/exportInfo2"/>'+"?lectureFlow=${lectureFlow}&flag="+flag;
        jboxExp(null,url);
    }
</script>
</head>
<body>
<div class="title_tab">
    <ul>
        <li id="Y" class="tab_select" onclick="selTag(this,'evaList','${lectureFlow}','${lectureInfo.randomSignIn}');" style="cursor: pointer;"><a id="evaList">已扫码学员（${fn:length(scans)+0}）</a></li>
        <li id="N" class="tab" onclick="selTag(this,'noRegist','${lectureFlow}',null);"style="cursor: pointer;"><a id="noRegist">已报名学员（${fn:length(regists)+0}）</a></li>
        <li id="Y2" class="tab" onclick="selTag(this,'evaList2','${lectureFlow}','${lectureInfo.randomSignIn}');" style="cursor: pointer;"><a id="evaList2">已扫码师资人员（${fn:length(scans2)+0}）</a></li>
        <li id="N2" class="tab" onclick="selTag(this,'noRegist2','${lectureFlow}',null);"style="cursor: pointer;"><a id="noRegist2">已报名师资人员（${fn:length(regists2)+0}）</a></li>

    </ul>
</div>
<div id="mainDiv" style="">
</div>
</body>
</html>