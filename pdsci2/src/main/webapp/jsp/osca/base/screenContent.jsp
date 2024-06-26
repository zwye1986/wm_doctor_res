<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
</jsp:include>
<style type="text/css">
	.xllistNew th{font-size:40px;line-height:60px;border: 0px;background: none;color: white;
		font-weight: normal;border: white 1px solid;padding-left: 5px}
	.xllistNew td{font-size:30px;line-height:48px;border: 0px;background: none;color: white;
		border: white 1px solid;padding-left: 5px}

	 /*.pad_right{ padding-right:2em;}*/
	.scroll_div {height:100%;overflow: hidden;white-space: nowrap;width:100%;}
	.scroll_begin,.scroll_end {display: inline;}
</style>
<script type="text/javascript">
	$(function(){
		setInterval(carousel,30000);
		ScrollLeft();
	});
	function carousel(){
		var page = $("#currentPage").val();
		if(page != "" && !isNaN(page) && Math.ceil(${total/10}) > page){
			page = parseInt(page) + 1;
		}else{
			page = 1;
		}
		$("#currentPage").val(page);
		$("#screenForm").submit();
	}
	//文字横向滚动
	function ScrollLeft(){
		var speed=12;
		var MyMar = null;
		var scroll_begin = $(".scroll_begin");
		var scroll_end = $(".scroll_end");
		var scroll_div = $(".scroll_div");
		for(var i = 0;i<scroll_div.length;i++){
			if($(scroll_begin[i]).width()>$(scroll_begin[i]).closest('td').width()) {
				scroll_end[i].innerHTML = scroll_begin[i].innerHTML;
			}
				function Marquee() {
					for (var j = 0; j < scroll_begin.length; j++) {
						if (scroll_end[j].offsetWidth - scroll_div[j].scrollLeft <= 0) {
							scroll_div[j].scrollLeft -= scroll_begin[j].offsetWidth;
						} else {
							scroll_div[j].scrollLeft++;
						}
					}
				}

		}
		Marquee();
		MyMar = setInterval(Marquee,speed);

	}

</script>
</head>
<body id="initCont" background="<s:url value='/jsp/inx/osce/images/${applicationScope.sysCfgMap["osce_screenBg"]}.png'/>">
<div class="mainright">
	<div class="content" style="background: none;">
		<div style="text-align: center;margin-top: 30px;margin-bottom: 20px;">
			<label style="font-size: 34px;color: white;">${orgName}临床技能考核房间动态</label>
		</div>
		<div style="text-align: center;width:100%;height:90%;">
			<form id="screenForm" method="get">
				<input type="hidden" id="currentPage" name="currentPage" value="${currentPage}">
				<c:forEach items="${clinicalFlows}" var="flow">
					<input type="hidden" name="clinicalFlow" value="${flow}">
				</c:forEach>
			</form>
			<table class="xllistNew" style="width:100%;border: none;table-layout: fixed">
				<tr>
					<th style="width:10%;" nowrap="">房间信息</th>
					<th style="width:20%;" nowrap="">正在考核</th>
					<th style="width:70%;">等待考核</th>
				</tr>
				<c:forEach items="${dataList}" var="info">
					<tr>
						<td>${info.ROOM_NAME}</td>
						<td>${info.DOCTOR_NAME}</td>
						<td class="rollTd">
							<div class="scroll_div">
								<div class="scroll_begin">
									${info.PD_DOCTOR_NAME_LIST}
								</div>
								<div class="scroll_end"></div>
							</div>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
<div style="position:absolute; bottom:10px;width: 100%;">
	<div style="text-align:center;"><label style="font-size: 18px;color: white;">技术支持：南京品德网络信息技术有限公司</label></div>
</div>
</div>
</body>
</html>