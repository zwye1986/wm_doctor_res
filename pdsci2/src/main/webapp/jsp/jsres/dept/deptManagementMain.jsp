<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<script type="text/javascript">
    $(document).ready(function(){
        dialogClose('loadingDialog');
        deptManage("standardDept");
    });

    function deptManage(tabTag){
        if("standardDept"==tabTag)
        {
            $("#standardDept").removeClass("tab");
            $("#standardDept").addClass("tab_select");
            $("#speBase").addClass("tab");
            $("#speBase").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/dept/deptStandardMain'/>",false);
        }else{
            $("#speBase").removeClass("tab");
            $("#speBase").addClass("tab_select");
            $("#standardDept").addClass("tab");
            $("#standardDept").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/dept/deptSpeBaseMain'/>",false);
        }
    }

</script>

<div class="main_hd">
    <h2>标准科室管理</h2>
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="standardDept" class="tab" onclick="deptManage('standardDept');"><a>标准科室管理</a></li>
            <li id="speBase" class="tab" onclick="deptManage('speBase');"><a>专业基地科室管理</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>