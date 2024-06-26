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
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });
        if ("${param.tabTag}" != "") {
            $("#${param.tabTag}").addClass("tab_select");
        } else {
            $('li').first().addClass("tab_select");
        }
        $(".tab_select").click();
	});
	function selTag(tabTag){
        jboxLoad("mainDiv","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
	}
</script>

<div class="main_hd">
   <div class="title_tab" style=" margin-top: 0px;">
        <ul id="reducationTab">
            <li class="tab_select" onclick="selTag('WaitAudit');"><a>补考待审核</a></li>
            <li class="tab" onclick="selTag('AuditList');"><a>补考查询</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>