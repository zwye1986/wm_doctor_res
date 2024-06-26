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
        // selTag("Audit");
        selTag("${param.tabTag}");
    });
    function selTag(tabTag){
        if("FristList"==tabTag) {
            $("#FristList").removeClass("tab");
            $("#FristList").addClass("tab_select");
            $("#FristList2").addClass("tab");
            $("#FristList2").removeClass("tab_select");
            $("#SecondList").addClass("tab");
            $("#SecondList").removeClass("tab_select");
            $("#SecondList2").addClass("tab");
            $("#SecondList2").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/asseGlobal/AuditListMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        }else if("FristList2"==tabTag){
            $("#FristList2").removeClass("tab");
            $("#FristList2").addClass("tab_select");
            $("#FristList").addClass("tab");
            $("#FristList").removeClass("tab_select");
            $("#SecondList").addClass("tab");
            $("#SecondList").removeClass("tab_select");
            $("#SecondList2").addClass("tab");
            $("#SecondList2").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/asseGlobal/AuditListMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        } else if("SecondList"==tabTag){
            $("#SecondList").removeClass("tab");
            $("#SecondList").addClass("tab_select");
            $("#FristList").addClass("tab");
            $("#FristList").removeClass("tab_select");
            $("#FristList2").addClass("tab");
            $("#FristList2").removeClass("tab_select");
            $("#SecondList2").addClass("tab");
            $("#SecondList2").removeClass("tab_select");
            <%--jboxLoad("mainDiv","<s:url value='/jsres/asseGlobal/AuditListMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);--%>
            jboxLoad("mainDiv","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        }else if("SecondList2"==tabTag){
            $("#SecondList2").removeClass("tab");
            $("#SecondList2").addClass("tab_select");
            $("#FristList").addClass("tab");
            $("#FristList").removeClass("tab_select");
            $("#FristList2").addClass("tab");
            $("#FristList2").removeClass("tab_select");
            $("#SecondList").addClass("tab");
            $("#SecondList").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        }
    }
</script>

<div class="main_hd">
    <h2 class="underline">结业考核资格查询</h2>
</div>
<div class="title_tab" style="margin-top: 0px;">
    <ul id="reducationTab">
        <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
            <li id="FristList" class="tab" onclick="selTag('FristList');"><a>住院医师首考查询</a></li>
            <li id="SecondList" class="tab" onclick="selTag('SecondList');"><a>住院医师补考查询</a></li>
        </c:if>
        <c:if test="${param.catSpeId eq 'AssiGeneral'}">
            <li id="FristList2" class="tab" onclick="selTag('FristList2');"><a>助理全科首考查询</a></li>
            <li id="SecondList2" class="tab" onclick="selTag('SecondList2');"><a>助理全科补考查询</a></li>
        </c:if>
    </ul>
</div>
</div>
<div id="mainDiv">
</div>
</html>