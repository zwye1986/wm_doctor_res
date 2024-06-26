<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">

    $(document).ready(function(){
        selTag("Regiest");
    });
    function selTag(tabTag){
        if("Regiest"==tabTag)
        {
            $("#Regiest").removeClass("tab");
            $("#Regiest").addClass("tab_select");
            $("#Singn").addClass("tab");
            $("#Singn").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/activityQuery/showRegistEval'/>?activityFlow=${param.activityFlow}&roleFlag=${param.roleFlag}",true);
        }else{
            $("#Singn").removeClass("tab");
            $("#Singn").addClass("tab_select");
            $("#Regiest").addClass("tab");
            $("#Regiest").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/activityQuery/showEval'/>?activityFlow=${param.activityFlow}&roleFlag=${param.roleFlag}",true);
        }
    }
</script>

<div class="main_hd">
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="Regiest" class="tab"><a id="regiestA" onclick="selTag('Regiest');">报名学员（${registNum}）</a></li>
            <li id="Singn" class="tab"><a id="scanA" onclick="selTag('Singn');">签到学员（${scanNum}）</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv" style="min-height: 530px;max-height: 530px;overflow: auto;">
</div>
<p style="text-align: center;">
    <input type="button" onclick="jboxClose();" class="btn_green" value="关&#12288;闭"/>
</p>
</html>