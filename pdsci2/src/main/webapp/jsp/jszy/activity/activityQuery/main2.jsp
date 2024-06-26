<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });

        $("#init").click();
    });
    function selTag(tag,isNew,isEval){
        $('.selTag').removeClass('selTag');
        $(tag).addClass('selTag');
        var url = "<s:url value='/jszy/base/activityQuery/doctorMain'/>?roleFlag=${param.roleFlag}&isNew="+isNew+"&isEval="+isEval;
        jboxLoad("content2",url,true);
    }
</script>
<div class="main_hd">
    <h2 class="underline">教学活动</h2>
</div>
<div class="title_tab" style="margin-top: 0;">
    <ul>
        <li class="tab_select"  id="init" onclick="selTag(this,'Y','');" style="cursor: pointer;"><a>最新活动</a></li>
        <li class="tab_select"  id="history" onclick="selTag(this,'','Y');"style="cursor: pointer;"><a>活动评价</a></li>
    </ul>
</div>
<div id="content2">
</div>
