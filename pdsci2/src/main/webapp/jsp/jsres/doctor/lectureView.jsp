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
    function selTag(tag,inx){
        $('.selTag').removeClass('selTag');
        $(tag).addClass('selTag');
        var url = '<s:url value="/jsres/lecture/"/>'+inx+"?roleId=${param.roleId}";
        jboxLoad("content2",url,true);
    }
</script>
<div class="main_hd">
    <h2 class="underline">讲座信息查询</h2>
</div>
<div class="title_tab" style="margin-top: 0;">
    <ul>
        <li class="tab_select"  id="init" onclick="selTag(this,'getNewLectures');" style="cursor: pointer;"><a>最新讲座</a></li>
        <li class="tab_select"  id="history" onclick="selTag(this,'getHistoryLectures');"style="cursor: pointer;"><a>历史讲座</a></li>
    </ul>
</div>
<div style="margin: 10px;">
        &nbsp;
</div>
<div id="content2">
</div>
