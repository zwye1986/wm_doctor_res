<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });
        if ("${param.liId}" != "") {
            $("#${param.liId}").addClass("tab_select");
        } else {
            $('li').first().addClass("tab_select");
        }
        $(".tab_select").click();
    });
    function reductionManage(operType){
        var url = "<s:url value='/hbzy/reduction/reductionManage?statusFlag=all&roleId=${roleId}'/>" + "&operType=" + operType;
        jboxLoad("div_table", url, true);
    }
</script>

<div class="main_hd">
    <h2>学员减免管理</h2>
    <div class="title_tab">
        <ul>
            <li class="tab_select" onclick="reductionManage('isCheck');"><a>学员减免审核</a></li>
            <li class="tab" onclick="reductionManage('isQuery');"><a >学员减免查询</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table" >
</div>