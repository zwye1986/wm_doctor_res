
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">

	$(document).ready(function(){
		 hospMant("manage");
	});
	function hospMant(tabTag){
		if("manage"==tabTag)
		{
			$("#manage").removeClass("tab");
			$("#manage").addClass("tab_select");
			$("#signatureManage").addClass("tab");
			$("#signatureManage").removeClass("tab_select");

            jboxLoad("mainDiv","<s:url value='/jsres/supervisio/hospitalSpeSelfAssessment?roleFlag=speAdmin'/>",true);
		}else{
			$("#signatureManage").removeClass("tab");
			$("#signatureManage").addClass("tab_select");
			$("#manage").addClass("tab");
			$("#manage").removeClass("tab_select");
            search();
          /*  jboxPostLoad("mainDiv","<s:url value='/jsres/supervisio/userSignSelf'/>",$("#searchForm").serialize(),false);*/
		}
	}

    function search() {
        jboxStartLoading();
        jboxPostLoad("mainDiv","<s:url value='/jsres/supervisio/userSignSelf'/>",$("#searchForm").serialize(),false);
    }
</script>

<div class="main_hd">
	<h2>专业基地自评</h2>
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="manage" class="tab" onclick="hospMant('manage');"><a>专业基地自评</a></li>
            <li id="signatureManage" class="tab" onclick="hospMant('signatureManage');"><a>签名管理</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>