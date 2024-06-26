<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">

	$(document).ready(function () {
		selTag("ScoreQuery");
	});

	function selTag(tabTag) {
		if ("ScorePassed" == tabTag) {
			$("#ScorePassed").removeClass("tab");
			$("#ScorePassed").addClass("tab_select");
			$("#ScoreQuery").addClass("tab");
			$("#ScoreQuery").removeClass("tab_select");
			jboxLoad("mainDiv", "<s:url value='/jsres/supervisio/expertLeaderMain'/>?roleFlag=${roleFlag}", true);
		} else if ("ScoreQuery" == tabTag) {
			$("#ScoreQuery").removeClass("tab");
			$("#ScoreQuery").addClass("tab_select");
			$("#ScorePassed").addClass("tab");
			$("#ScorePassed").removeClass("tab_select");
			jboxLoad("mainDiv", "<s:url value='/jsres/supervisio/managementMain'/>?roleFlag=${roleFlag}", true);
		}
	}
</script>

<div class="main_hd">
	<h2>督导管理 — 评估指标</h2>
	<div class="title_tab" style="margin-top: 0px;">
		<ul id="reducationTab">
			<li id="ScoreQuery" class="tab" onclick="selTag('ScoreQuery');"><a>管理表格</a></li>
			<li id="ScorePassed" class="tab" onclick="selTag('ScorePassed');"><a>专业表格</a></li>
		</ul>
	</div>
</div>
<div id="mainDiv">
</div>
</html>