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
		if("FristWait"==tabTag) {
			$("#FristWait").removeClass("tab");
			$("#FristWait").addClass("tab_select");
            $("#FristWait2").addClass("tab");
            $("#FristWait2").removeClass("tab_select");
			$("#SecondWait").addClass("tab");
			$("#SecondWait").removeClass("tab_select");
            $("#SecondWait2").addClass("tab");
            $("#SecondWait2").removeClass("tab_select");
			//资格审核待审核
			<%--jboxLoad("mainDiv","<s:url value='/jsres/asseGlobal/waitAuditMain'/>?roleFlag=${param.roleFlag}&tabTag=${param.tabTag}",true);--%>
            jboxLoad("mainDiv","<s:url value='/jsres/asseGlobal/AuditListMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
		}else if("FristWait2"==tabTag){
            $("#FristWait2").removeClass("tab");
            $("#FristWait2").addClass("tab_select");
            $("#FristWait").addClass("tab");
            $("#FristWait").removeClass("tab_select");
            $("#SecondWait").addClass("tab");
            $("#SecondWait").removeClass("tab_select");
            $("#SecondWait2").addClass("tab");
            $("#SecondWait2").removeClass("tab_select");
            //资格审核待审核
            jboxLoad("mainDiv","<s:url value='/jsres/asseGlobal/AuditListMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        } else if("SecondWait"==tabTag){
			$("#SecondWait").removeClass("tab");
			$("#SecondWait").addClass("tab_select");
			$("#FristWait").addClass("tab");
			$("#FristWait").removeClass("tab_select");
            $("#FristWait2").addClass("tab");
            $("#FristWait2").removeClass("tab_select");
            $("#SecondWait2").addClass("tab");
            $("#SecondWait2").removeClass("tab_select");
			//审核列表
			<%--jboxLoad("mainDiv","<s:url value='/jsres/asseGlobal/AuditListMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);--%>
            jboxLoad("mainDiv","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
		}else if("SecondWait2"==tabTag){
            $("#SecondWait2").removeClass("tab");
            $("#SecondWait2").addClass("tab_select");
            $("#FristWait").addClass("tab");
            $("#FristWait").removeClass("tab_select");
            $("#FristWait2").addClass("tab");
            $("#FristWait2").removeClass("tab_select");
            $("#SecondWait").addClass("tab");
            $("#SecondWait").removeClass("tab_select");
            //审核列表
            jboxLoad("mainDiv","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag="+tabTag,true);
        }
	}
</script>

<div class="main_hd">
	<h2 class="underline">结业考核资格审核</h2>
</div>
<div class="title_tab" style="margin-top: 0px;">
        <ul id="reducationTab">
            <c:if test="${param.catSpeId eq 'DoctorTrainingSpe'}">
                <li id="FristWait" class="tab" onclick="selTag('FristWait');"><a>住院医师首考审核</a></li>
                <li id="SecondWait" class="tab" onclick="selTag('SecondWait');"><a>住院医师补考审核</a></li>
            </c:if>
            <c:if test="${param.catSpeId eq 'AssiGeneral'}">
                <li id="FristWait2" class="tab" onclick="selTag('FristWait2');"><a>助理全科首考审核</a></li>
                <li id="SecondWait2" class="tab" onclick="selTag('SecondWait2');"><a>助理全科补考审核</a></li>
            </c:if>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>