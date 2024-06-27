<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>

<style type="text/css">
	.sideTwo {
		height: 75px;
		width: 33%;
		float: left;
	}
	.side1Two {
		height: 75px;
		width: 33%;
		float: right;
	}
	.mainTwo {
		height: 75px;
		margin: 0 33%;
	}
	.oneleft {
		width: 70%;
		height: 75px;
	}
	.oneright {
		width: 25%;
		float: right;
		margin-top: -95px;
	}
	.divonetop {
		text-align: center;
		width: 80%;
		height: 80%;
		margin: 8px auto;
		line-height: 30px;
	}
	.icls{
		margin-left: 15px;
	}

	.infoAudit table th {
		color: #000000;
		font: 14px 'Microsoft Yahei';
		font-weight: 500;
	}
	.infoAudit table td {
		color: #000000;
		font: 14px 'Microsoft Yahei';
		font-weight: 400;
	}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		$('#subjectYear').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode: 2,
			format: 'yyyy'
		});
		jboxEndLoading();

		// 初始化总计信息
		updateSummaryInfo();
	});

	function updateSummaryInfo() {
		var zyysCount = 0;
		if($(".zyys") && $(".zyys").length) {
			$(".zyys").each(function(i, item) {
				var zyysRow = Number.parseInt($(item).text());
				if(zyysRow) {
					zyysCount += zyysRow;
				}
			});
		}
		$(".zyysCount").text(zyysCount);

		var zxzsCount = 0;
		if($(".zxzs") && $(".zxzs").length) {
			$(".zxzs").each(function(i, item) {
				var zxzsRow = Number.parseInt($(item).text());
				if(zxzsRow) {
					zxzsCount += zxzsRow;
				}
			});
		}
		$(".zxzsCount").text(zxzsCount);

		var baseCapacityCount = 0;
		if($(".baseCapacity") && $(".baseCapacity").length) {
			$(".baseCapacity").each(function(i, item) {
				<c:if test="${ishos eq 'Y'}">
				// 省厅不可编辑，用span
				var baseCapacityRow = Number.parseInt($(item).text());
				</c:if>
				<c:if test="${ishos ne 'Y'}">
				// 基地可编辑，用input
				var baseCapacityRow = Number.parseInt($(item).val());
				</c:if>
				if (baseCapacityRow) {
					baseCapacityCount += baseCapacityRow;
				}
			});
		}
		$(".baseCapacityCount").text(baseCapacityCount);

		var minRecruitCapacityCount = 0;
		if ($(".minRecruitCapacity") && $(".minRecruitCapacity").length) {
			$(".minRecruitCapacity").each(function(i, item) {
				var minRecruitCapacityRow = Number.parseInt($(item).text());
				if(minRecruitCapacityRow) {
					minRecruitCapacityCount +=minRecruitCapacityRow;
				}
			});
		}
		$(".minRecruitCapacityCount").text(minRecruitCapacityCount);

		var capacityUsePercentCount = 0;
		var capacityUsePercentTotal = 0;
		if($(".capacityUsePercent") && $(".capacityUsePercent").length) {
			$(".capacityUsePercent").each(function(i, item) {
				var capacityUsePercentRow = Number.parseInt($(item).text());
				if(capacityUsePercentRow) {
					capacityUsePercentCount +=capacityUsePercentRow;
					capacityUsePercentTotal++;
				}
			});
		}
		if(capacityUsePercentTotal > 0) {
			$(".capacityUsePercentCount").text(Math.round(capacityUsePercentCount / capacityUsePercentTotal).toFixed(1));
		}

		var zyysTotalCount = 0;
		if($(".zyysTotal") && $(".zyysTotal").length) {
			$(".zyysTotal").each(function(i, item) {
				var zyysTotalRow = Number.parseInt($(item).text());
				if(zyysTotalRow) {
					zyysTotalCount += zyysTotalRow;
				}
			});
		}
		$(".zyysTotalCount").text(zyysTotalCount);

		var trainingSumTotalCount = 0;
		if($(".trainingSumTotal") && $(".trainingSumTotal").length) {
			$(".trainingSumTotal").each(function(i, item) {
				var trainingSumTotalRow = Number.parseInt($(item).text());
				if(trainingSumTotalRow) {
					trainingSumTotalCount += trainingSumTotalRow;
				}
			});
		}
		$(".trainingSumTotalCount").text(trainingSumTotalCount);

		var zxzsTotalCount = 0;
		if($(".zxzsTotal") && $(".zxzsTotal").length) {
			$(".zxzsTotal").each(function(i, item) {
				var zxzsTotalRow = Number.parseInt($(item).text());
				if(zxzsTotalRow) {
					zxzsTotalCount += zxzsTotalRow;
				}
			});
		}
		$(".zxzsTotalCount").text(zxzsTotalCount);
	}

	function showSpe(speFlow, isJoin, ishos) {
		var sessionNumber = $("#sessionNumber").val();
		var url = "<s:url value ='/jsres/base/orgShowSpeInfo'/>?onlyRead=Y&ishos=${ishos}&speFlow=" + speFlow + "&orgFlow=${orgFlow}&isJoin=" + isJoin+"&sessionNumber="+sessionNumber;
	/*	if (isJoin == 'Y' || ishos == 'Y') {
			jboxOpen(url, "专业基地", 1200, 700);
		} else {*/
			var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='1200' height='700' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
			jboxMessager(iframe, '专业基地', 1200, 700);
	/*	}*/
	}

	function showDept(deptFlow, schDeptName, isJoin, speFlow, ishos) {
		var url = "<s:url value ='/jsres/base/showDeptInfo'/>?onlyRead=Y&deptFlow=" + deptFlow + "&orgFlow=${orgFlow}&isJoin=" + isJoin + "&speFlow=" + speFlow;
		if (isJoin == 'Y' || ishos == 'Y') {
			jboxOpen(url, '科室信息（' + schDeptName + '）', 1200, 700);
		} else {
			url = "<s:url value ='/jsres/base/showDeptInfo'/>?onlyRead=Y&deptFlow=" + deptFlow + "&speFlow=" + speFlow;
			var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='1200' height='700' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
			jboxMessager(iframe, '科室信息（' + schDeptName + '）', 1200, 700);
		}
	}

	function editSpeInfo(speFlow, isJoin, ishos) {
		var sessionNumber = $("#sessionNumber").val();
		if (null==sessionNumber || sessionNumber=='' || sessionNumber==undefined){
			jboxTip("请选择年份！");
			return
		}
		var url = "<s:url value ='/jsres/base/editSpeInfo'/>?speFlow=" + speFlow + "&orgFlow=${orgFlow}&isJoin=" + isJoin+"&sessionNumber="+sessionNumber;
		if (isJoin == 'Y' || ishos == 'Y') {
			jboxOpen(url, "专业基地", 1200, 700);
		} else {
			var iframe = "<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='1200' height='700' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='" + url + "'></iframe>";
			jboxMessager(iframe, '专业基地', 1200, 700);
		}
	}

	function dialogBaseCapacity(recordFlow, speId) {
		var id = "baseCapacityDialog" + speId;
		$.prompt({
			state0: {
				title: "请输入基地容量",
				html: "<input type='text' id='" + id + "' maxlength='10' class='input' style='width=105px;' />",
				buttons: {确定: 1, 取消: 0},
				submit:function(e,v,m,f){
					if(v == 1) {
						var capacity = $("#" + id).val();
						var reg = /^[0-9]{1,10}$/;
						if (!reg.test(capacity) || Number.parseInt(capacity) < 0) {
							$.prompt('请输入不小于0的整数', {
								title: "提示",
								buttons: {确定: 1},
								classes: {
								}
							});
						}else {
							var data = {
								recordFlow: recordFlow,
								baseCapacity: capacity
							}
							var url = "<s:url value='/jsres/base/trainSpeBaseCapacitySave'/>";
							jboxPost(url, data,
									function(){
										getInfo();
									}, null, false);
						}
					}
				}
			}
		});
	}
</script>
<body>
<div id="allInfo" style="overflow-y: auto;">
<%--	<div style=" display: flex;flex-wrap: wrap;">--%>
<%--		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict" varStatus="status">--%>
<%--			<c:if test="${not empty speId and speId eq dict.dictId}">--%>
<%--					<div style="height: 278px;background-color: #e8e9ec;width: 31%;margin: 5px;padding: 5px">--%>
<%--						<div style="background-color: white;border-radius: 15px;">--%>
<%--							<div style="background-color: #409eff;border-radius: 15px;color: white">--%>
<%--								<div class="oneleft">--%>
<%--									<div style="padding-left: 20px;padding-top: 15px;font-size: 16px">--%>
<%--										<div>--%>
<%--											<span style="cursor: pointer" onclick="showSpe('${dict.dictId}','${isJoin}','${ishos}');">--%>
<%--												<c:if test="${ishos eq 'Y'}">--%>
<%--													${dict.dictName}&nbsp;&nbsp;(${dict.dictId})--%>
<%--												</c:if>--%>
<%--												<c:if test="${ishos ne 'Y'}">--%>
<%--													${pdfn:cutString(dict.dictName,6,true,3)  }&nbsp;&nbsp;(${dict.dictId})--%>
<%--												</c:if>--%>
<%--											</span>--%>
<%--										</div>--%>
<%--										&lt;%&ndash;<div style="<c:if test="${ishos eq 'Y'}">width: 300px;</c:if> padding-left: 25px;font-size: 14px;height: 40px;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp:2">--%>
<%--											<c:forEach items="${deptMap}" var="d" varStatus="de">--%>
<%--												<c:if test="${dict.dictId eq d.speId}">--%>
<%--													<span style="cursor: pointer" onclick="showDept('${d.schDeptFlow}','${d.schDeptName}','${isJoin}','${dict.dictId}','${ishos}')">${d.schDeptName}</span>--%>
<%--												</c:if>--%>
<%--											</c:forEach>--%>
<%--										</div>&ndash;%&gt;--%>
<%--									</div>--%>

<%--								</div>--%>
<%--								<div class="oneright" onclick="editSpeInfo('${dict.dictId}','${isJoin}','${ishos}');">--%>
<%--									<c:if test="${ishos ne 'Y'}">--%>
<%--										<div style="padding-left: 25px;padding-top: 35px">--%>
<%--											<img src="<s:url value='/jsp/res/images/test.png'/>" style="cursor: pointer;height: 20px;width: 20px;"/>--%>
<%--										</div>--%>
<%--									</c:if>--%>
<%--								</div>--%>

<%--								<hr style="width: 90%">--%>
<%--								<div>--%>
<%--									<div class="sideTwo" style="border-right:1px solid white">--%>
<%--										<div class="divonetop">--%>
<%--											<c:set var="infoMsg" value="zyys${dict.dictId}"></c:set>--%>
<%--											<div>${empty resultMap[infoMsg]? 0 :resultMap[infoMsg]}人</div>--%>
<%--											<div>住院医师</div>--%>
<%--										</div>--%>
<%--									</div>--%>
<%--									<div class="side1Two" style="border-left:1px solid white ">--%>
<%--										<div class="divonetop">--%>
<%--											<c:set var="infoMsg" value="sz${dict.dictId}"></c:set>--%>
<%--											<div>${empty resultMap[infoMsg]? 0 :resultMap[infoMsg]}人</div>--%>
<%--											<div>导师师资</div>--%>
<%--										</div>--%>

<%--									</div>--%>
<%--									<div class="mainTwo" style="padding-top: 8px;height: 75px;width: 33%;text-align: center;line-height: 30px">--%>
<%--										<c:set var="infoMsg" value="zxzs${dict.dictId}"></c:set>--%>
<%--										<div>${empty resultMap[infoMsg]? 0 :resultMap[infoMsg]}人</div>--%>
<%--										<div>在校专硕</div>--%>
<%--									</div>--%>
<%--								</div>--%>

<%--							</div>--%>
<%--							<c:set var="infoMsg" value="speRespName${dict.dictId}"></c:set>--%>
<%--							<div style="height: 20px;margin-top: 10px"><i class="icon_menu menu_360check icls"></i>专业基地负责人： ${resultMap[infoMsg]}</div>--%>
<%--							<hr style="width: 90%;color:#f8f8f8;">--%>
<%--							<c:set var="infoMsg" value="speDirName${dict.dictId}"></c:set>--%>
<%--							<div style="height: 20px"><i class="icon_menu menu_360check icls"></i>教学主任：${resultMap[infoMsg]}</div>--%>
<%--							<hr style="width: 90%;color:#f8f8f8;">--%>
<%--							<c:set var="infoMsg" value="speSceName${dict.dictId}"></c:set>--%>
<%--							<div style="height: 25px"><i class="icon_menu menu_360check icls"></i>教学秘书：${resultMap[infoMsg]}</div>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--			</c:if>--%>
<%--			<c:if test="${empty speId}">--%>
<%--				<div style="height: 278px;background-color: #e8e9ec;width: 31%;margin: 5px;padding: 5px">--%>
<%--					<div style="background-color: white;border-radius: 15px;">--%>
<%--						<div style="background-color: #409eff;border-radius: 15px;color: white">--%>
<%--							<div class="oneleft">--%>
<%--								<div style="padding-left: 20px;padding-top: 15px;font-size: 16px">--%>
<%--									<div>--%>
<%--										<span onclick="showSpe('${dict.dictId}','${isJoin}','${ishos}');" style="cursor: pointer">--%>
<%--											<c:if test="${ishos eq 'Y'}">--%>
<%--												${dict.dictName}&nbsp;&nbsp;(${dict.dictId})--%>
<%--											</c:if>--%>
<%--											<c:if test="${ishos ne 'Y'}">--%>
<%--												${pdfn:cutString(dict.dictName,6,true,3)  }&nbsp;&nbsp;(${dict.dictId})--%>
<%--											</c:if>--%>
<%--										</span>--%>
<%--									</div>--%>


<%--									&lt;%&ndash;<div style="<c:if test="${ishos eq 'Y'}">width: 300px;</c:if>padding-left: 25px;font-size: 14px;height: 40px;overflow: hidden;display: -webkit-box;-webkit-box-orient: vertical;-webkit-line-clamp:2">--%>
<%--										<c:forEach items="${deptMap}" var="d" varStatus="de">--%>
<%--											<c:if test="${dict.dictId eq d.speId}">--%>
<%--												<span style="cursor: pointer" onclick="showDept('${d.schDeptFlow}','${d.schDeptName}','${isJoin}','${dict.dictId}','${ishos}')">${d.schDeptName}</span>--%>
<%--											</c:if>--%>
<%--										</c:forEach>--%>
<%--									</div>&ndash;%&gt;--%>
<%--								</div>--%>

<%--							</div>--%>
<%--							<div class="oneright" onclick="editSpeInfo('${dict.dictId}','${isJoin}','${ishos}');">--%>
<%--								<c:if test="${ishos ne 'Y'}">--%>
<%--									<div style="padding-left: 25px;padding-top: 35px">--%>
<%--										<img src="<s:url value='/jsp/res/images/test.png'/>" style="cursor: pointer;height: 20px;width: 20px;"/>--%>
<%--									</div>--%>
<%--								</c:if>--%>
<%--							</div>--%>
<%--							<hr style="width: 90%">--%>
<%--							<div>--%>
<%--								<div class="sideTwo" style="border-right:1px solid white">--%>
<%--									<div class="divonetop">--%>
<%--										<c:set var="infoMsg" value="zyys${dict.dictId}"></c:set>--%>
<%--										<div>${empty resultMap[infoMsg]? 0 :resultMap[infoMsg]}人</div>--%>
<%--										<div>住院医师</div>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--								<div class="side1Two" style="border-left:1px solid white ">--%>
<%--									<div class="divonetop">--%>
<%--										<c:set var="infoMsg" value="sz${dict.dictId}"></c:set>--%>
<%--										<div>${empty resultMap[infoMsg]? 0 :resultMap[infoMsg]}人</div>--%>
<%--										<div>导师师资</div>--%>
<%--									</div>--%>

<%--								</div>--%>
<%--								<div class="mainTwo" style="padding-top: 8px;height: 75px;width: 33%;text-align: center;line-height: 30px">--%>
<%--									<c:set var="infoMsg" value="zxzs${dict.dictId}"></c:set>--%>
<%--									<div>${empty resultMap[infoMsg]? 0 :resultMap[infoMsg]}人</div>--%>
<%--									<div>在校专硕</div>--%>
<%--								</div>--%>
<%--							</div>--%>

<%--						</div>--%>
<%--						<c:set var="infoMsg" value="speRespName${dict.dictId}"></c:set>--%>
<%--						<div style="height: 20px;margin-top: 10px"><i class="icon_menu menu_360check icls"></i>专业基地负责人： ${resultMap[infoMsg]}</div>--%>
<%--						<hr style="width: 90%;color:#f8f8f8;">--%>
<%--						<c:set var="infoMsg" value="speDirName${dict.dictId}"></c:set>--%>
<%--						<div style="height: 20px"><i class="icon_menu menu_360check icls"></i>教学主任：${resultMap[infoMsg]}</div>--%>
<%--						<hr style="width: 90%;color:#f8f8f8;">--%>
<%--						<c:set var="infoMsg" value="speSceName${dict.dictId}"></c:set>--%>
<%--						<div style="height: 25px"><i class="icon_menu menu_360check icls"></i>教学秘书：${resultMap[infoMsg]}</div>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</c:if>--%>
<%--		</c:forEach>--%>
<%--	</div>--%>
	<table border="0" cellpadding="0" cellspacing="0" class="grid">
		<tr>
			<th>专业基地编码</th>
			<th>专业基地名称</th>
			<th>住院医师(${sessionNumber}在培)</th>
			<th>在校专硕(${sessionNumber}在培)</th>
			<c:if test="${sessionNumber eq pdfn:getCurrYear()}">
				<th>住院医师(在培)</th>
				<th>在校专硕(在培)</th>
				<th>在培人员总数</th>
				<th>基地培训容量</th>
				<th>最小培训容量</th>
				<th>容量使用率</th>
			</c:if>
		</tr>
		<c:forEach items="${orgSpeList}" var="orgSpe">
			<c:set var="zyys" value="zyys${orgSpe.SPE_ID}"></c:set>
			<c:set var="zxzs" value="zxzs${orgSpe.SPE_ID}"></c:set>
			<tr <c:if test="${empty orgSpe.STATUS or orgSpe.STATUS eq 'close' or orgSpe.STATUS eq 'stop'}">style="background: lightgrey" </c:if>>
				<td>${orgSpe.SPE_ID}</td>
				<td>
					<c:if test="${orgSpe.STATUS eq 'open'}">
						<a style="color: #459ae9;cursor: pointer" onclick="showSpe('${orgSpe.SPE_ID}','${isJoin}','${ishos}');">
					</c:if>
						${orgSpe.SPE_NAME}<c:if test="${orgSpe.STATUS eq 'stop'}"><font color="red">（停招）</font></c:if>
					<c:if test="${orgSpe.STATUS eq 'open'}">
						</a>
					</c:if>
				</td>
				<td><span class="zyys">${empty resultMap[zyys]? 0 :resultMap[zyys]}</span>人</td>
				<td><span class="zxzs">${empty resultMap[zxzs]? 0 :resultMap[zxzs]}</span>人</td>
				<c:if test="${sessionNumber eq pdfn:getCurrYear()}">
					<td><span class="zyysTotal">${empty orgSpe.zyysTotal? '0' :orgSpe.zyysTotal}</span>人</td>
					<td><span class="zxzsTotal">${empty orgSpe.zxzsTotal? '0' :orgSpe.zxzsTotal}</span>人</td>
					<td><span class="trainingSumTotal">${empty orgSpe.trainingSumTotal? '0' :orgSpe.trainingSumTotal}</span>人</td>
					<td>
						<c:if test="${ishos eq 'Y'}">
							<span class="baseCapacity" id="baseCapacity${orgSpe.SPE_ID}">${empty orgSpe.BASE_CAPACITY? '0' :orgSpe.BASE_CAPACITY}</span>人
						</c:if>
						<c:if test="${ishos ne 'Y'}">
							<span><input style="width: 60%; text-align: center" type="text" id='baseCapacity${orgSpe.SPE_ID}' class="input baseCapacity" value="${empty orgSpe.BASE_CAPACITY? '' :orgSpe.BASE_CAPACITY}" <c:if test="${orgSpe.STATUS ne 'open'}">disabled</c:if> readonly onclick="dialogBaseCapacity('${orgSpe.ORG_SPE_FLOW}', '${orgSpe.SPE_ID}')" /><span>人</span></span>
						</c:if>
					</td>
					<td><span class="minRecruitCapacity">${empty orgSpe.minRecruitCapacity? '' :orgSpe.minRecruitCapacity}</span>人</td>
					<td><span class="capacityUsePercent">${empty orgSpe.capacityUsePercent? '0' :orgSpe.capacityUsePercent}</span>%</td>
				</c:if>
			</tr>
		</c:forEach>
		<tr>
			<td>总计</td>
			<td></td>
			<td><span class="zyysCount"></span>人</td>
			<td><span class="zxzsCount"></span>人</td>
			<c:if test="${sessionNumber eq pdfn:getCurrYear()}">
				<td><span class="zyysTotalCount"></span>人</td>
				<td><span class="zxzsTotalCount"></span>人</td>
				<td><span class="trainingSumTotalCount"></span>人</td>
				<td><span class="baseCapacityCount"></span>人</td>
				<td><span class="minRecruitCapacityCount"></span>人</td>
				<td><span class="capacityUsePercentCount"></span>%</td>
			</c:if>
		</tr>
	</table>
</div>
</body>