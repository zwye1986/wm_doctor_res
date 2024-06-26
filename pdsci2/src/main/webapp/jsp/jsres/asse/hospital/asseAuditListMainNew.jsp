<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">

    $(document).ready(function(){
        <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
            selTag("SecondWait");
        </c:if>
        <c:if test="${param.catSpeId eq 'AssiGeneral'}">
            selTag("SecondWait2");
        </c:if>

        <%--selTag("${param.tabTag}");--%>
    });
    function selTag(tabTag){
        if("SecondWait"==tabTag){
            $("#SecondWait").removeClass("tab");
            $("#SecondWait").addClass("tab_select");
            $("#SecondWait2").removeClass("tab_select");
            $("#SecondList").removeClass("tab_select");
            $("#SecondList2").removeClass("tab_select");
            //补考待审核
            jboxLoad("mainDiv","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,false);
        }else if("SecondWait2"==tabTag){
            $("#SecondWait2").removeClass("tab");
            $("#SecondWait2").addClass("tab_select");
            $("#SecondWait").removeClass("tab_select");
            $("#SecondList").removeClass("tab_select");
            $("#SecondList2").removeClass("tab_select");
            //补考待审核
            jboxLoad("mainDiv","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,false);
        } else if("SecondList"==tabTag){
            $("#SecondList").removeClass("tab");
            $("#SecondList").addClass("tab_select");
            $("#SecondWait").removeClass("tab_select");
            $("#SecondWait2").removeClass("tab_select");
            $("#SecondList2").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        }else if("SecondList2"==tabTag){
            $("#SecondList2").removeClass("tab");
            $("#SecondList2").addClass("tab_select");
            $("#SecondList").removeClass("tab_select");
            $("#SecondWait2").removeClass("tab_select");
            $("#SecondWait").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        }
    }
</script>

<div class="main_hd">
    <h2 class="underline">补考管理</h2>
</div>
<div class="title_tab" style="margin-top: 0px;">
    <ul id="reducationTab">
        <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
            <li id="SecondWait" class="tab" onclick="selTag('SecondWait');"><a>住院医师补考审核</a></li>
        </c:if>
        <c:if test="${param.catSpeId eq 'AssiGeneral'}">
            <li id="SecondWait2" class="tab" onclick="selTag('SecondWait2');"><a>助理全科补考审核</a></li>
        </c:if>
        <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
            <li id="SecondList" class="tab" onclick="selTag('SecondList');"><a>住院医师补考查询</a></li>
        </c:if>
        <c:if test="${param.catSpeId eq 'AssiGeneral'}">
            <li id="SecondList2" class="tab" onclick="selTag('SecondList2');"><a>助理全科补考查询</a></li>
        </c:if>
    </ul>
</div>
</div>
<div id="mainDiv">
</div>
</html>