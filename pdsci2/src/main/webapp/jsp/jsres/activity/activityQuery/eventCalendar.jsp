<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value="/jsp/res/log/assest/calendar.css"/>">
<style>
	.triangleNum{
		position: absolute;
		top: 1px;
		text-align: center;
		right: 2px;
		left: auto;
		transform: rotate(0);
	}
	.hasBac{
		background: linear-gradient(#aaadf8,#5a7dfb);
	}
	.hasBac .triangleDiv{
		opacity: 1;
	 }
	.hasBac .triangle2{
		border-color: transparent #fff transparent transparent;
		opacity: 1;
	}
	.lc-widget-header, .lc-widget-content{
		border: 1px solid #91cafa;
	}
	.lc-week td:last-child{
		border-right: 1px solid #91cafa;
	}
	.triangle2{
		opacity: 1;
		border-color: transparent rgba(135,200,254,0.4) transparent transparent;
	}
	.lc-week > td{
		width:14%;
	}
	.lc-grid .lc-day-content{
		box-sizing: border-box;
	}
</style>
<script type="text/javascript">
	function searchActivity(roleFlag,month,flag) {
		//jboxStartLoading();
		var url = "<s:url value='/jsres/activityQuery/activityMain'/>?roleFlag=${roleFlag}"+"&month="+month+"&flag="+flag;
        location.replace(url);
    }
    function searchByAcitivityFlows(roleFlag,activityFlows) {
        var url = "<s:url value='/jsres/activityQuery/getActivity'/>?roleFlag=${roleFlag}&activityFlows="+activityFlows;
        jboxOpen(url,"教学活动信息",900,600);
        //window.open(url,"教学活动信息");
    }
    function printCalendar() {
		window.print();
    }
    $(function(){
		test();
	})
	function test(){
	    $(".test").each(function(i){
            if($(this).text().length>25){
                //给td设置title属性,并且设置td的完整值.给title属性.
                $(this).attr("title",$(this).text());
                //获取td的值,进行截取。赋值给text变量保存.
                var text=$(this).text().substring(0,25)+"...";
                //重新为td赋值;
                $(this).text(text);
            }
		})

	}
</script>
<div id="calendar" style="width:900px;    margin: 0 auto;" class="lc lc-rtl">
	<table class="lc-header" style="width:100%">
		<tbody>
		<tr>
			<td class="lc-header-left">
												<span class="lc-header-title">
													<h2>${month}</h2>
												</span>
			</td>
			<td class="lc-header-center">
												<span class="lc-header-title">
													<h2>教学活动一览表</h2>
												</span>
			</td>
			<td class="lc-header-right">
				<img class="img_print" title="打印" style="cursor: pointer; width: 30px;margin-top: 1px;margin-right: 20px;" src="<s:url value='/css/skin/dayin.png'/>" onclick="printCalendar();"/>
				<span class="lc-button lc-button-prev lc-state-default lc-corner-left" unselectable="on" onclick="searchActivity('${roleFlag}','${month}','N')">
										<span class="lc-icon lc-icon-left-single-arrow"></span>
									</span>
				<span class="lc-button lc-button-next lc-state-default" unselectable="on" onclick="searchActivity('${roleFlag}','${month}','Y')">
										<span class="lc-icon lc-icon-right-single-arrow"></span>
									</span>
			</td>
		</tr>
		</tbody>
	</table>
	<div class="lc-content" style="">
		<div class="lc-view lc-view-month lc-grid" unselectable="on">
			<div class="lc-event-container" style="position:absolute;z-index:8;top:0;left:0"></div>
			<table class="lc-border-separate" style="width:100%" cellspacing="0">
				<thead>
				<tr class="lc-first lc-last">
					<th class="lc-day-header lc-mon lc-widget-header lc-first" style="width: 265px;">周一</th>
					<th class="lc-day-header lc-tue lc-widget-header" style="width: 265px;">周二</th>
					<th class="lc-day-header lc-wed lc-widget-header" style="width: 265px;">周三</th>
					<th class="lc-day-header lc-thu lc-widget-header" style="width: 265px;">周四</th>
					<th class="lc-day-header lc-fri lc-widget-header" style="width: 265px;">周五</th>
					<th class="lc-day-header lc-sat lc-widget-header" style="width: 265px;">周六</th>
					<th class="lc-day-header lc-sun lc-widget-header lc-last" style="width: 265px;">周日</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach begin="0" end="${trRows-1}" step="1" var="i">
					<tr  class="lc-week <c:if test="${i==0}">lc-first</c:if> <c:if test="${i==(trRows-1)}">lc-last</c:if>">
						<c:forEach begin="0" end="6" step="1" var="j">
							<c:set var="key" value="${i}-${j}"></c:set>
							<c:set var="data" value="${tableMap[key]}"></c:set>
							<td class="lc-day lc-mon lc-widget-content  lc-past
												<c:if test="${data.month != month}">lc-other-month</c:if>
												<c:if test="${i==0}">lc-first</c:if> <c:if test="${i==6}">lc-last</c:if>" data-date="${data.month}">
								<c:if test="${data.num > 0 && data.month == month}">
								<div style="min-height: 140px;position: relative;" class="hasBac">
									<c:if test="${data.month == month}">
										<div class="triangleDiv">
											<span class="triangle2"></span>
										</div>
										<span class="triangleNum" style="color: #0b31a0;font-size: 20px" >${data.num}</span>
										<div class="lc-day-number">${data.day}</div>
										<div class="lc-day-content" style="cursor: pointer;" onclick="searchByAcitivityFlows('${roleFlag}','${data.activityFlows}');">
											<div style="position: relative; height: 0px;">
												<div class="test" style="width: 123px;font-size: 13px; " title=${data.contentName}>${data.contentName}</div>
												<div style="font-size: 13px;">${data.contentTime}</div>
											</div>
										</div>
									</c:if>
								</div>
								</c:if>
								<c:if test="${empty data.num}">
									<div style="min-height: 140px;position: relative;">
										<c:if test="${data.month == month}">
											<div class="triangleDiv">
												<span class="triangle2"></span>
											</div>
											<span class="triangleNum">${data.num}</span>
											<div class="lc-day-number">${data.day}</div>
											<div class="lc-day-content">
												<div style="position: relative; height: 0px;">
													<div>${data.contentTime}</div>
													<div>${data.contentName}</div>
												</div>
											</div>
										</c:if>
									</div>
								</c:if>
							</td>

						</c:forEach>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
