
<c:set var="weekList" value="${weekNuMap['keyList']}"></c:set><!-- 有几周 -->
<tr>
	<td style="width: 100px;min-width: 100px;">周序</td>
	<c:forEach items="${weekList}" var="week">
		<c:set var="weekDayListMap" value="${weekNuMap['weekDayListMap']}"/>
		<c:set var="weekDayList" value="${weekDayListMap[week]}"/>
		<td style="width: 100px;max-width: 100px;" colspan="${(fn:length(weekDayList))eq '0'?'7':(fn:length(weekDayList))}">第${week}周</td>
	</c:forEach>
</tr>
<tr>
	<td style="width: 100px;max-width: 100px;">日期</td>
	<c:set var="days" value="${weekNuMap['days']}"/>
	<c:forEach items="${days}" var="day">
		<td style="width: 100px;max-width: 100px;">${day}</td>
	</c:forEach>
</tr>
<tr>
	<td style="width: 100px;max-width: 100px;">星期</td>
	<c:set var="days" value="${weekNuMap['days']}"/>
	<c:set var="weekMaps" value="${weekNuMap['weekMaps']}"/>
	<c:forEach items="${days}" var="day">
		<td style="width: 100px;max-width: 100px;">${weekMaps[day]}</td>
	</c:forEach>
</tr>
<c:forEach items="${classOrderEnumList}" var="classOrder">
	<tr>
		<td style="width: 100px;max-width: 100px;">${classOrder.name}<br/>${classOrder.time}</td>
		<c:set var="days" value="${weekNuMap['days']}"/>
		<c:forEach items="${days}" var="day">
			<c:set var="key" value="${day}_${classOrder.id}"></c:set>
			<c:set var="extList" value="${info[key]}"></c:set>
			<c:if test="${not empty extList}">
				<td style="width: 100px;max-width: 100px;"  id="${key}_td" term_flow="${termFlow}" flow="${key}">
					<table style="width: 100%;border: 0px solid #e3e3e3;text-align: center;height:100%">
						<tr style="border: 0px solid #e3e3e3;text-align: center;height: 100%;">
							<c:forEach items="${extList}" var="result" varStatus="status">
							<c:set var="firstClassKey" value="${result.courseCode}_${day}_${classOrder.id}"></c:set>
							<c:set var="lastCount" value="${(status.count % 2) }"></c:set>
							<td class="nameTitle2" id="${key}" recordFlow="${result.recordFlow}" term_flow="${termFlow}" flow="${key}" style=" border:1px solid #e3e3e3;text-align: center;width:50%;${param.roleFlag eq 'skz'?'cursor:pointer':''}"
								class_time="${day}" class_order="${classOrder.name}" course_flow="${result.courseFlow}" <c:if test="${param.roleFlag eq 'skz' || param.roleFlag eq 'xx'}">onclick="arrangePlan(this<c:if test="${param.roleFlag eq 'skz'}">,'<c:forEach items="${limitList}" var="limit"><c:if test="${day ge limit.beginTime && day le limit.endTime}">${limit.memo}期间不可排课！</c:if></c:forEach>'</c:if>)"</c:if>>
								<div style="line-height:18px;<%--<c:if test="${not empty firstClassMap[firstClassKey]}"> background-color:#c0c0c0;</c:if>--%>
								<c:if test="${result.courseFlow eq param.courseFlow2}">background-color:lightgreen;</c:if>
								<c:if test="${empty result.scheduleTeacherList}">height:100%;</c:if>">
										${result.classCourseName}
								</div>
								<c:if test="${ not empty result.scheduleTeacherList}">
									<div style="font-style:oblique;font-weight:lighter;line-height:18px;<c:if test="${result.courseFlow eq param.courseFlow2}">background-color:lightgreen;</c:if>">
										<c:forEach items="${result.scheduleTeacherList}" var="tea" varStatus="teaStatus">
											${tea.classTeacherName}<c:if test="${!teaStatus.last}">/</c:if>
										</c:forEach>
									</div>
								</c:if>
								<div class="pxxx" id="${result.recordFlow}_table" style="display:none; position: relative">
									<div  id="${result.recordFlow}_table_div" style="position:absolute; background-color:#fff; padding:10px;border:1px solid  #dcdcdc; width:400px;">
										<table class="grid" style="width: 100%;border: 1px solid #e3e3e3;">
											<tr>
												<th style="text-align: center;">授课老师</th>
												<td style="text-align: center;">
													<c:forEach items="${result.scheduleTeacherList}" var="tea" varStatus="teaStatus">
														${tea.classTeacherName}<c:if test="${!teaStatus.last}">/</c:if>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<th style="text-align: center;">上课地点</th>
												<td style="text-align: center;">${result.classroomName}</td>
											</tr>
											<tr>
												<th style="text-align: center;">人数上限</th>
												<td style="text-align: center;">${result.studentMaxmun}</td>
											</tr>
											<tr>
												<th style="text-align: center;">学时</th>
												<td style="text-align: center;">${result.classPeriod}</td>
											</tr>
											<tr>
												<th style="text-align: center;">学分</th>
												<td style="text-align: center;">${result.course.courseCredit}</td>
											</tr>
											<tr>
												<th style="text-align: center;">备注</th>
												<td style="text-align: center;">${result.memo}</td>
											</tr>
											<tr>
												<th style="text-align: center;">详细情况</th>
												<td style="text-align: center;">
													<a onclick="showCourseInfo('${result.courseFlow}')" style="cursor:pointer;color:blue">查看</a>
													<c:if test="${result.courseFlow eq param.courseFlow2 || param.roleFlag eq 'xx'}"><a onclick="delInfo('${termFlow}','${result.recordFlow}','${result.courseFlow}')" style="cursor:pointer;color:blue">删除</a></c:if>
												</td>
											</tr>

										</table>
									</div>
								</div>
							</td>
							<c:if test="${ lastCount eq '0'}">
						</tr><tr style="border: 0px solid #e3e3e3;text-align: center;">
						</c:if>
						</c:forEach>
					</tr>
					</table>
				</td>
			</c:if>
			<c:if test="${empty extList}">
				<td style="width: 100px;max-width: 100px; ${(param.roleFlag eq 'skz' || param.roleFlag eq 'xx')&& term.termStartTime le day && day le term.termEndTime?'cursor:pointer':''}" recordFlow="" class_time="${day}" class_order="${classOrder.name}"
					term_flow="${termFlow}" <c:if test="${(param.roleFlag eq 'skz' || param.roleFlag eq 'xx')&& term.termStartTime le day && day le term.termEndTime}">onclick="arrangePlan(this<c:if test="${param.roleFlag eq 'skz'}">,'<c:forEach items="${limitList}" var="limit"><c:if test="${day ge limit.beginTime && day le limit.endTime}">${limit.memo}期间不可排课！</c:if></c:forEach>'</c:if>)"</c:if>>
					<c:forEach items="${limitList}" var="limit"><c:if test="${day ge limit.beginTime && day le limit.endTime}">${limit.memo}</c:if></c:forEach>
				</td>
			</c:if>

		</c:forEach>
	</tr>
</c:forEach>
<script type="text/javascript">
	$(function(){
		$(".pxxx").click(function(e){
			e.stopPropagation();
		});
		var tds = $('.nameTitle2');
		tds.each(function(){
			$(this).on('mouseover',function(e){
				var flow =$(this).attr("recordFlow");
				if($("#"+flow+"_table").is(":hidden")){
					$(".pxxx").hide();
					$("#"+flow+"_table").show();
					var term_flow =$(this).attr("term_flow");
					var td_id =$(this).attr("flow");

					var left =$(this).width()/2;
					var top=$(this).height()/2;

					var thisOutWidth=$(this).outerWidth();//当前td宽度
					var parentTd=$("#"+td_id+"_td");//当前td的父类中的td
					var thisParentWidth=parentTd.outerWidth();//当前td的父类中的td宽度
					//当前td的父类中的td 右边所有兄弟的宽度
					var nextWidth=0;
					$(parentTd).nextAll().each(function(){
						nextWidth+=$(this).outerWidth();
					});
					var winWidth=$("#"+term_flow+"_tbody").outerWidth();//整个table宽度
					var right=nextWidth+thisOutWidth/2;
					if(right<400)
					{
						$("#"+flow+"_table").css("left",(thisParentWidth-thisOutWidth/2-400)+"px");
					}else{
						$("#"+flow+"_table").css("left",left+"px");
					}
					$("#"+flow+"_table").css("top", -top+"px");
					var winHeight = $("#"+term_flow+"_tbody").outerHeight();//整个table高度
					var parentTdTr = $(parentTd).parents("tr");
					var preHeight = 0;
					$(parentTdTr).prevAll().each(function () {
						preHeight += $(this).outerHeight();
					});
					var heightLast=winHeight - preHeight - $(this).outerHeight() / 2;
					var height=$("#" + flow + "_table_div").outerHeight(true);
					if (heightLast < height) {
						$("#" + flow + "_table").css("top", -(top+height) + "px");
					} else {
						$("#" + flow + "_table").css("top", -top + "px");
					}

				}
			});
			$(this).on('mouseout',function(e){
				$(".pxxx").hide();
			});
		});
		$(document).on('click',function(){
			$(".pxxx").hide();
		});
	});
	function showCourseInfo(courseFlow){
		jboxOpen("<s:url value='/xjgl/term/manage/currCourse'/>?courseFlow="+courseFlow,"课程信息",850,450);
	}
</script>