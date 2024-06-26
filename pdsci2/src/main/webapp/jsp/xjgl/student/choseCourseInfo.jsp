<c:if test="${termCourseFlag}">
	<c:set var="weekList" value="${weekNuMap['keyList']}"></c:set><!-- 有几周 -->
	<tr>
		<td style="width: 115px;min-width: 115px;">周序</td>
		<c:forEach items="${weekList}" var="week" varStatus="index">
			<c:if test="${index.first}"><c:set var="firstWeek" value="${week}"></c:set></c:if>
			<c:set var="weekDayListMap" value="${weekNuMap['weekDayListMap']}"/>
			<c:set var="weekDayList" value="${weekDayListMap[week]}"/>
			<td style="width: 100px;max-width: 100px;" colspan="${(fn:length(weekDayList))eq '0'?'7':(fn:length(weekDayList))}">第${week}周</td>
			<c:if test="${index.last}"><c:set var="lastWeek" value="${week}"></c:set></c:if>
		</c:forEach>
	</tr>
	<tr>
		<td style="width: 115px;min-width: 115px;">日期</td>
		<c:set var="days" value="${weekNuMap['days']}"/>
		<c:forEach items="${days}" var="day">
			<td style="width: 100px;max-width: 100px;">${day}</td>
		</c:forEach>
	</tr>
	<tr>
		<td style="width: 115px;min-width: 115px;">星期</td>
		<c:set var="days" value="${weekNuMap['days']}"/>
		<c:set var="weekMaps" value="${weekNuMap['weekMaps']}"/>
		<c:forEach items="${days}" var="day">
			<td style="width: 100px;max-width: 100px;">${weekMaps[day]}</td>
		</c:forEach>
	</tr>
	<c:forEach items="${applicationScope.sysCfgMap['xjgl_customer'] eq 'gzykdx'?classOrderEnumOfGzList:classOrderEnumList}" var="classOrder">
		<tr>
			<td style="width:120px;max-width:120px;">${classOrder.name}<br/>${classOrder.time}</td>
			<c:set var="days" value="${weekNuMap['days']}"/>
			<c:forEach items="${days}" var="day">
				<c:set var="key" value="${day}_${classOrder.id}"></c:set>
				<c:set var="extList" value="${info[key]}"></c:set>
				<td style="width:100px;max-width:100px;" id="${key}_td">
					<table style="width:100%;height:100%;text-align:center;border:0px solid #e3e3e3;">
						<tr>
							<c:set var="isChosed" value=""></c:set>
							<c:set var="isChosedCourseName" value=""></c:set>
							<c:forEach items="${extList}" var="result">
								<c:set var="classKey" value="${result.courseCode}_${day}_${classOrder.id}"></c:set>
								<c:if test="${result.recordFlow eq chosedClassMap[classKey]}">
									<c:set var="isChosed" value="Y"></c:set>
									<c:set var="isChosedCourseName" value="${result.classCourseName}"></c:set>
								</c:if>
							</c:forEach>
							<c:forEach items="${extList}" var="result">
								<c:set var="classKey" value="${result.courseCode}_${day}_${classOrder.id}"></c:set>
								<td class="nameTitle" recordFlow="${result.recordFlow}" flow="${key}" style="cursor: pointer;
										<c:choose><c:when test="${result.recordFlow eq chosedClassMap[classKey]}">background-color:lightgreen;</c:when>
										<c:otherwise><c:if test="${not empty firstClassMap[classKey]}">background-color:#c0c0c0;</c:if></c:otherwise></c:choose>">
									<div>${result.classCourseName}</div>
									<div class="pxxx" id="${result.recordFlow}_table" style="display:none;position: relative">
										<div id="${result.recordFlow}_table_div" style="position:absolute;background-color:#fff;padding:10px;border:1px solid #dcdcdc;width:400px;height:auto;">
											<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width:100%;text-align: center;height: 100%;">
												<tr>
													<th style="text-align: center;">授课老师</th>
													<td style="text-align: center;">
														<c:forEach items="${result.scheduleTeacherList}" var="tea" varStatus="teaStatus">
															<p>${tea.classTeacherName}</p>
														</c:forEach>
													</td>
												</tr>
												<tr>
													<th>上课地点</th>
													<td>${result.classroomName}</td>
												</tr>
												<tr>
													<th>人数上限</th>
													<td>${result.studentMaxmun}</td>
												</tr>
												<tr>
													<th>学时</th>
													<td>${result.classPeriod}</td>
												</tr>
												<tr>
													<th style="text-align: center;">学分</th>
													<td style="text-align:center;">${result.course.courseCredit}</td>
												</tr>
												<tr>
													<th>详细情况</th>
													<td><a class="showCourseInfo" href="javascript:void(0);" onclick="showCourseInfo('${result.courseFlow}','${result.recordFlow}','${result.recordFlow eq chosedClassMap[classKey]}','${result.studentMaxmun}','${isChosed}','${isChosedCourseName}');" style="color:blue">查看</a></td>
												</tr>
											</table>
										</div>
									</div>
								</td>
							</c:forEach>
						</tr>
					</table>
				</td>
			</c:forEach>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="15" style="text-align: right;width: 100%;">
			<span><a style="cursor: pointer;" href="javascript:searchScheduleClass('${firstWeek-1}')">[上一周]</a></span>
			<span style="margin-left:10px;margin-right:20px;"><a style="cursor: pointer;" href="javascript:searchScheduleClass('${lastWeek+1}')">[下一周]</a></span>
		</td>
	</tr>
</c:if>
<script type="text/javascript">
	$(document).ready(function(){
		var tds = $('.nameTitle');
		tds.each(function(){
			$(this).on('mouseover',function(e){
				$(".pxxx").hide();
				var flow =$(this).attr("recordFlow");
				if($("#"+flow+"_table").is(":hidden")) {

					$("#"+flow+"_table").show();
					var td_id = $(this).attr("flow");
					var left = $(this).width() / 2;
					var top = $(this).height() / 2;

					var thisOutWidth = $(this).outerWidth();//当前td宽度
					var parentTd = $("#" + td_id + "_td");//当前td的父类中的td
					var thisParentWidth = parentTd.outerWidth();//当前td的父类中的td宽度
					//当前td的父类中的td 右边所有兄弟的宽度
					var nextWidth = 0;
					$(parentTd).nextAll().each(function () {
						nextWidth += $(this).outerWidth();
					});
					var winWidth = $("#contentDiv").outerWidth();//整个table宽度
					var right = nextWidth + thisOutWidth / 2;
					if (right < 400) {
						$("#" + flow + "_table").css("left", (thisOutWidth / 2 - 400) + "px");
					} else {
						$("#" + flow + "_table").css("left", left + "px");
					}
					var winHeight = $("#contentDiv").outerHeight();//整个table高度
					var parentTdTr = $(parentTd).parents("tr");

					var preHeight = 0;
					$(parentTdTr).prevAll().each(function () {
						preHeight += $(this).outerHeight();
					});
					var heightLast=winHeight - preHeight - $(this).outerHeight() / 2;
					var height=$("#" + flow + "_table_div").outerHeight(true);
					if (heightLast < height) {
						$("#" + flow + "_table").css("top", top-height + "px");
					} else {
						$("#" + flow + "_table").css("top", -top + "px");
					}
				}
			});
			$(this).on('mouseout',function(e){
				$(".pxxx").hide();
			});
		});
	});
	function showCourseInfo(courseFlow,recordFlow,flag,stuMaxNum,isChosed,isChosedCourseName){
		jboxOpen("<s:url value='/xjgl/term/manage/currCourse'/>?year=${param.year}&termSeason=${param.termSeason}&classId=${param.classId}&trainTypeId=${param.trainTypeId}&userFlow=${param.userFlow}&courseFlow="+courseFlow+"&recordFlow="+recordFlow+"&flag="+flag+"&studMaxNum="+stuMaxNum+"&isChosed="+isChosed+"&isChosedCourseName="+isChosedCourseName+"&lastWeek=${lastWeek}","课程信息",850,420);
	}
</script>