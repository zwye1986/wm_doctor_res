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
        selTag("${param.tabTag}");
    });
    function selTag(tabTag){
        if("FristWait"==tabTag) {
            $("#FristWait").removeClass("tab");
            $("#FristWait").addClass("tab_select");
            $("#FristList").removeClass("tab_select");
            $("#FristWait2").removeClass("tab_select");
            $("#FristList2").removeClass("tab_select");
            //首考待审核
            jboxLoad("mainDiv","<s:url value='/jsres/doctorTheoryScore/auditMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        }else if("FristWait2"==tabTag) {
            $("#FristWait2").removeClass("tab");
            $("#FristWait2").addClass("tab_select");
            $("#FristList").removeClass("tab_select");
            $("#FristWait").removeClass("tab_select");
            $("#FristList2").removeClass("tab_select");
            //首考待审核
            jboxLoad("mainDiv","<s:url value='/jsres/doctorTheoryScore/auditMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        }
        if("FristList"==tabTag) {
            $("#FristList").removeClass("tab");
            $("#FristList").addClass("tab_select");
            $("#FristList2").removeClass("tab_select");
            $("#FristWait").removeClass("tab_select");
            $("#FristWait2").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/doctorTheoryScore/auditMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        }else if("FristList2"==tabTag) {
            $("#FristList2").removeClass("tab");
            $("#FristList2").addClass("tab_select");
            $("#FristList").removeClass("tab_select");
            $("#FristWait").removeClass("tab_select");
            $("#FristWait2").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/doctorTheoryScore/auditMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        }
    }
</script>

<div class="main_hd">
    <h2 class="underline">结业资格管理</h2>
</div>
<div class="title_tab" style=" margin-top: 0px;">
    <ul id="reducationTab">
        <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
            <li id="FristWait" class="tab" onclick="selTag('FristWait');"><a>住院医师首考审核</a></li>
        </c:if>
        <c:if test="${param.catSpeId eq 'AssiGeneral'}">
            <li id="FristWait2" class="tab" onclick="selTag('FristWait2');"><a>助理全科首考审核</a></li>
        </c:if>
        <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
            <li id="FristList" class="tab" onclick="selTag('FristList');"><a>住院医师首考查询</a></li>
        </c:if>
        <c:if test="${param.catSpeId eq 'AssiGeneral'}">
            <li id="FristList2" class="tab" onclick="selTag('FristList2');"><a>助理全科首考查询</a></li>
        </c:if>
    </ul>
</div>
</div>
<div id="mainDiv">
</div>
</html>