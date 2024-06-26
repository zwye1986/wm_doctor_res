<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_ui_sortable" value="true"/>
	</jsp:include>

	<style type="text/css">
		caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#f60;margin-bottom: 10px;}
		.processFlag{width: 50px;float: left;height: 100%;}
		.processDept{width: 120px;padding-left: 10px;}
		.processDept .dept{font-size: 15px;color:#3d91d5;margin: 5px 0px;}
		.processDept .schScore{color:#5A5A5A;margin: 10px 0px 5px 0px;;}
		.headNmae{width: 170px;color: #A3A3A3;margin-top: 5px;line-height: 25px;}
	</style>
	<script>
		//弹出增加轮转科室页面
		function editResult(resultFlow) {
			var url = "<s:url value='/sch/arrangeImport/showEditResult'/>?doctorFlow=${doctor.doctorFlow}&resultFlow="+resultFlow;
			jboxOpen(url, "选择要轮转的科室", 700, 400, true);
		}
		function refreshResult()
		{
			viewUser("${doctor.doctorFlow}");
		}
		function editSchResult(resultFlow,recordStatus){
			var url = "<s:url value='/sch/arrangeImport/updateResultStatus'/>";
			var data = {};
			data.resultFlow = resultFlow;
			data.recordStatus = recordStatus;
			if('${GlobalConstant.RECORD_STATUS_N}' == recordStatus){
				jboxConfirm("删除此轮转科室后，无法恢复!", function(){
					jboxPost(url, data, function (resp) {
						if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
							jboxTip("删除成功!");
							refreshResult();
						}else{
							jboxTip("删除失败!");
						}
					}, null, false);
				});
			}else	if('${GlobalConstant.RECORD_STATUS_D}' == recordStatus){
				jboxConfirm("学员将无法查看该轮转科室信息!", function(){
					jboxPost(url, data, function (resp) {
						if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
							jboxTip("停用成功!");
							refreshResult();
						}else{
							jboxTip("停用失败!");
						}
					}, null, false);
				});
			}else {
				jboxPost(url, data, function (resp) {
					if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
						jboxTip("启用成功!");
						refreshResult();
					}else{
						jboxTip("启用失败!");
					}
				}, null, false);
			}
		}

		var oldFirst;//排序前第一个元素
		//初始化操作
		$(function () {
			saveFirstResult();
			var oldData = "";
			var lastOperResultFlow = "";
			$("#sortResult").sortable({
				helper: function (e, ui) {
					ui.children().each(function () {
						$(this).width($(this).width());
					});
					return ui;
				},
				create: function (e, ui) {
					var oldSortedIds = $("#sortResult").sortable("toArray");
					$.each(oldSortedIds, function (i, sortedId) {
						oldData = oldData + "&recordFlow=" + sortedId;
					});
					console.log(oldData);
				},
				start: function (e, ui) {
					$(".lastOper").removeClass("lastOper");
					ui.helper.addClass("moveColor");
					saveFirstResult($(".resultHome:first"));
					return ui;
				},
				stop: function (event, ui) {
					$(".moveColor").removeClass("moveColor");
					ui.item.addClass("lastOper");

					var operEndIndex = $(".resultHome").index(ui.item);
					var startId = ui.item[0].id;
					lastOperResultFlow = startId;
					if (operEndIndex > 0) {
						startId = $(".resultHome:eq(" + (operEndIndex - 1) + ")")[0].id;
					}
					getCurrEleIndex(startId);
					var sortedIds = $("#sortResult").sortable("toArray");
					var postdata = "";
					$.each(sortedIds, function (i, sortedId) {
						postdata = postdata + "&resultFlow=" + sortedId;
					});
					if (oldData == postdata) {
						return;
					}
					jboxPost("<s:url value='/sch/arrangeImport/sortAndCalculateTwo'/>", postdata + "&resultNum=${results.size()}", function () {
						jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
					}, null, false);
					oldData = postdata;
				}
			});
			$(".isAfter").each(function () {
				$(this).removeClass("resultHome");
				$(".start,.end", this).each(function () {
					var value = this.value;
					$(this).hide().after('<label>' + value + '</label>');
				});
			});
			$("#afterResult").append($(".isAfter"));

			$(".isRemove").each(function () {
				$(this).removeClass("resultHome");
				$(".start,.end", this).each(function () {
					var value = this.value;
					$(this).hide().after('<label>' + value + '</label>');
				});
			});
			$("#removeResult").append($(".isRemove"));

			$(".isExternal").each(function () {
				$(".start,.end", this).each(function () {
					var value = this.value;
					$(this).hide().after('<label>' + value + '</label>');
				});
			});
			if (lastOperResultFlow) {
				$("#" + lastOperResultFlow).addClass("lastOper");
			}
			var afterResultTrs = $("#afterResult").find("tr");
			if (afterResultTrs.length > 0) {
				$("#autoCount").hide();
			}
		});
		//记录下第一条
		function saveFirstResult(first) {
			if (first) {
				oldFirst = $(".resultHome:first");
			}
			return oldFirst;
		}
		//获取当前这个元素的位置
		function getCurrEleIndex(id) {
			var startResultIndex = $(".resultHome").index($("#" + id));
			if ($(".resultHome").length - 1 != startResultIndex) {
				var startDate = $("#" + id + " .end").val();
				var currStart = $("#" + id + " .start").val();

				var fStart = $(".start", saveFirstResult()).val();
				if (startResultIndex == 0 && currStart != fStart) {
					return autoCount(fStart);
				}
				if (startDate) {
					var postdata = createResults(startResultIndex);
					postdata += ("&startDate=" + startDate);
					var mustdate = "";
					if ($("#mustDate").length) {
						mustdate = "&mustDate=" + (($("#mustDate")[0].checked) ? "${GlobalConstant.FLAG_Y}" : "${GlobalConstant.FLAG_N}");
					}
					var url = "<s:url value='/sch/arrangeImport/sortAndCalculateTwo'/>";
					jboxPost(url, postdata + "&resultNum=${results.size()}&addOneDay=true" + mustdate, function (resp) {
						refreshResult();
					}, null, false);
				}
			}
		}
		//包装排班列表参数
		function createResults(index) {
			var selector = "";
			if (index || index == 0) {
				selector = ":gt(" + index + ")";
			}
			var postdata = "";
			$(".resultHome:not(.isAfter):not(.isRemove):not(.isExternal)" + selector).each(function () {
				postdata += ("&resultFlow=" + (this.id));
			});
			return postdata;
		}
		//包装排班列表参数（用于自动排班）
		function createResultsByAuto(index) {
			var selector = "";
			if (index || index == 0) {
				selector = ":gt(" + index + ")";
			}
			var postdata = "";
			$(".resultHome:not(.isAfter):not(.isRemove)" + selector).each(function () {
				postdata += ("&resultFlow=" + (this.id));
			});
			return postdata;
		}
		//保存轮转时长
		function saveSchMonth(resultFlow, schMonth, groupFlow, groupMonth, groupName) {
			if (isNaN(schMonth) || schMonth == "") {
				return jboxTip("请输入数字！");
			}


			var data = {};
			data.resultFlow = resultFlow;
			schMonth = parseFloat(schMonth).toFixed(1);

			if (schMonth > 99 || schMonth < 0.5) {
				return jboxTip("请输入合法数字(0.5~99)！");
			}

			data.schMonth = schMonth;
			var url = "<s:url value='/sch/arrangeImport/saveDiyDate'/>";
			jboxPost(url, data, function (resp) {
				if (resp == "${GlobalConstant.OPRE_SUCCESSED_FLAG}") {
					$("#" + resultFlow).attr("schMonth", schMonth);
					refreshResult();
				}
			}, null, false);
		}
		//保存自定义时间
		function diyDate(data) {
			var resultFlow = data.resultFlow;
			var start = $("#" + resultFlow).find(".start").val();
			var end = $("#" + resultFlow).find(".end").val();
			if (start && end && (start >= end)) {
				return jboxTip("结束时间必须大于开始时间！");
			}

			var result = true;
			$(".resultHome").each(function () {
				var currStart = $(this).find(".start").val();
				var currEnd = $(this).find(".end").val();
				if ((start || end) && currStart && currEnd && resultFlow != this.id) {
					if (start)
						result = (start < currStart) || (start > currEnd);
					else if (end)
						result = (end < currStart) || (end > currEnd);
					else if (start && end)
						result = (start < currStart && end < currStart) || (start > currEnd && end > currEnd);
					if (!result)
						return result;
				}
			});
			if (!result) {
				if (data.schStartDate) {
					$("#" + resultFlow + " .start").val("");
				} else if (data.schEndDate) {
					$("#" + resultFlow + " .end").val("");
				}
				return jboxTip("该日期与其他日期产生重叠！");
			}
			if (data.schStartDate) {
				data.schMonth = $("#" + resultFlow).attr("schMonth") || "";
			}
			var url = "<s:url value='/sch/arrangeImport/saveDiyDate'/>";
			jboxPost(url, data, function (resp) {
				if (data.schEndDate) {
					getCurrEleIndex(resultFlow);
				}
			}, null, false);
		}

		//自动排班
		function autoCount(startDate) {
			var startDate = $("#startDate").val() || startDate;
			if (!startDate)
				return jboxTip("请选择开始日期！");


			if(!checkExternal()){
				jboxTip("有外院轮转的科室不满足条件，请修改排班数据或调整开始时间！");
				return false;
			}
			var startDate = $("#startDate").val() || startDate;

			var postdata = createResultsByAuto();

			postdata += ("&startDate=" + startDate);
			var url = "<s:url value='/sch/arrangeImport/sortAndCalculateTwo'/>";
			jboxPost(url, postdata + "&resultNum=${results.size()}", function () {
				refreshResult();
			}, null, false);
		}
		function checkExternal() {
			var b = true;
			$(".isExternal").each(function () {
				var startDate = getDate($("#startDate").val());
				var externalResultFlow = $(this).attr("id");
				var externalStartDate = getDate($(this).attr("startDate"));
				var sumSchMonth = 0;
				$("input[name=resultFlows]").each(function () {
					var resultFlow = $(this).val();
					if(externalResultFlow == resultFlow){
						return false;
					}
					sumSchMonth = parseInt(sumSchMonth) + parseInt($("#" + resultFlow).attr("schMonth"));
				});
				startDate.setMonth(startDate.getMonth() + sumSchMonth);
				if(startDate.getTime() != externalStartDate.getTime()){
					b = false;
					return false;
				}
			});
			return b;
		}
		//清空排班
		function clearCount() {
			jboxConfirm("确认清空排班数据？", function () {
				var postdata = createResults();

				postdata += ("&clear=true");
				var url = "<s:url value='/sch/arrangeImport/sortAndCalculateTwo'/>";
				jboxPost(url, postdata + "&resultNum=${results.size()}", function () {
					refreshResult();
				}, null, false);
			}, null);
		}
	</script>
</head>

<body>
<div class="mainright">
	<div class="content" style="padding: 0 5px;">
		<div style="width: 100%;height: 80px;">
			<table class="basic" style="width: 100%;margin-top: 10px;background-color: white;position: relative;">
				<tr>
					<td id="titleTd" style="cursor: pointer;">
						姓名：<font style="font-weight: bold;">${doctor.doctorName}</font>
						&#12288;&#12288;&#12288;&#12288;
						轮转方案：<font style="font-weight: bold;" >${doctor.rotationName}</font>
						&#12288;&#12288;&#12288;&#12288;
						培训年级：<font style="font-weight: bold;"><c:out value="${doctor.sessionNumber}" default="无"/></font>
						&#12288;&#12288;&#12288;&#12288;
						培养年限：<font style="font-weight: bold;"><c:out value="${doctor.trainingYears}" default="无"/></font>
						&#12288;&#12288;&#12288;&#12288;
					</td>
				</tr>
				<tr>
					<td>
						<div>
							<input type="button" value="增加轮转科室" class="search"
								   style="float: right;margin-right: 10px;margin-top: 4px;"onclick="editResult('');"/>
							<input type="button" value="清空排班" class="search"
								   style="float: right;margin-right: 10px;margin-top: 4px;" onclick="clearCount();">
							<input type="button" value="自动排班" class="search" id="autoCount"
								   style="float: right;margin-right: 10px;margin-top: 4px;"  onclick="autoCount();">
							<input
									class="mustpicker"
									type="text"
									style="float: right;margin-right: 10px;margin-top: 7px;cursor: pointer;width: 100px;"
									id="startDate"
									name="startDate"
									readonly="readonly"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
									value="${param.startDate}"
							/>
							<span style="float: right;margin-right: 10px;margin-top: 0px;">开始日期：</span>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<div id="selDept${doctor.doctorFlow}">
			<table class="xllist nofix" style="width:100%;margin-bottom: 10px;text-align: center;">
				<tr>
					<th style="text-align: center;">标准科室</th>
					<th style="text-align: center;">转轮科室</th>
					<th style="text-align: center;">轮转时长</th>
					<th style="text-align: center;">转轮时间</th>
					<c:if test="${!empty processMap}">
						<th style="text-align: center;">轮转状态</th>
					</c:if>
					<th style="text-align: center;">操作</th>
				</tr>

				<tbody id="afterResult"></tbody>

				<c:set var="sumMonth" value="0"/>
				<tbody id="sortResult">
					<c:forEach items="${results}" var="result">
						<c:if test="${result.recordStatus ne GlobalConstant.RECORD_STATUS_D}">
							<c:set var="sumMonth" value="${sumMonth+result.schMonth }"/>
						</c:if>
						<tr id="${result.resultFlow}" schMonth="${result.schMonth}" startDate="${result.schStartDate}"
							class="resultHome <c:if test="${processMap[result.resultFlow].schFlag eq GlobalConstant.FLAG_Y}"> isAfter</c:if>
             sort${groupMap[result.groupFlow].schStageId} <c:if test="${result.recordStatus eq GlobalConstant.RECORD_STATUS_D}"> isRemove</c:if>
             <c:if test="${processMap[result.resultFlow].isExternal eq GlobalConstant.FLAG_Y}"> isExternal</c:if>"
							style="cursor: move;
							<c:if test="${result.recordStatus eq GlobalConstant.RECORD_STATUS_D}">color:grey;</c:if>">
							<td >${result.standardDeptName}
								<c:if test="${empty result.standardDeptName}">
									<a style="cursor: pointer;color: blue;" href="javascript:void(0);" onclick="editResult('${result.resultFlow}');">维护科室</a>
								</c:if>
							</td>
							<td >${result.schDeptName}</td>
							<td >
								<c:if test="${result.recordStatus eq GlobalConstant.RECORD_STATUS_D}">
									${result.schMonth}
								</c:if>
								<c:if test="${result.recordStatus ne GlobalConstant.RECORD_STATUS_D}">
									<input class="schMonthInput" type="text" value="${result.schMonth}"
										   style="width: 55px;text-align: center;"
										   onchange="saveSchMonth('${result.resultFlow}',this.value,'${result.groupFlow}',${groupMap[result.groupFlow].schMonth+0},'${groupMap[result.groupFlow].groupName}');"/>
								</c:if>
							</td>
							<td >
								<input class="start" type="text" value="${result.schStartDate}" style="width: 80px;cursor: pointer;"
									   readonly="readonly"
										<c:if test="${result.recordStatus ne GlobalConstant.RECORD_STATUS_D}">
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
										</c:if>
										<c:if test="${result.recordStatus eq GlobalConstant.RECORD_STATUS_D}">
											disabled
										</c:if>
									   onchange="diyDate({resultFlow:'${result.resultFlow}',schStartDate:this.value});"
								/>
								~
								<input class="end" type="text" value="${result.schEndDate}" style="width: 80px;cursor: pointer;"
									   readonly="readonly"
										<c:if test="${result.recordStatus ne GlobalConstant.RECORD_STATUS_D}">
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"
										</c:if>
										<c:if test="${result.recordStatus eq GlobalConstant.RECORD_STATUS_D}">
											disabled
										</c:if>
									   onchange="diyDate({resultFlow:'${result.resultFlow}',schEndDate:this.value});"/>
							</td>
							<c:if test="${!empty processMap}">
								<td>
									<c:if test="${processMap[result.resultFlow].schFlag eq GlobalConstant.FLAG_Y}">
										已出科
									</c:if>
									<c:if test="${processMap[result.resultFlow].isCurrentFlag eq GlobalConstant.FLAG_Y}">
										轮转中
									</c:if>
								</td>
							</c:if>
							<td>
								<c:if test="${processMap[result.resultFlow].schFlag ne GlobalConstant.FLAG_Y  and result.recordStatus eq GlobalConstant.RECORD_STATUS_Y}">
									<a style="cursor: pointer;color: blue;" href="javascript:void(0);" onclick="editSchResult('${result.resultFlow}','${GlobalConstant.RECORD_STATUS_D}');">停用</a>
									<a style="cursor: pointer;color: blue;" href="javascript:void(0);" onclick="editSchResult('${result.resultFlow}','${GlobalConstant.RECORD_STATUS_N}');">删除</a>
								</c:if>
								<c:if test="${processMap[result.resultFlow].schFlag ne GlobalConstant.FLAG_Y  and result.recordStatus eq GlobalConstant.RECORD_STATUS_D}">
									<a style="cursor: pointer;color: blue;" href="javascript:void(0);" onclick="editSchResult('${result.resultFlow}','${GlobalConstant.RECORD_STATUS_Y}');">启用</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${empty results}">
						<tr>
							<td colspan="99" style="text-align: center;">暂无轮转安排！</td>
						</tr>
					</c:if>
				</tbody>
				<tbody id="removeResult"></tbody>
				<c:if test="${!empty results}">
					<tr>
						<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
							<th  colspan="3" style="text-align: right">合计：&#12288;</th>
						</c:if>
						<c:if test="${!(rotation.isStage eq GlobalConstant.FLAG_Y)}">
							<th colspan="2" style="text-align: right">合计：&#12288;</th>
						</c:if>
						<th>
							<fmt:formatNumber type="number" maxFractionDigits="1" value="${sumMonth}"/>
						</th>
						<c:if test="${!empty processMap}">
							<th colspan="3"></th>
						</c:if>
						<c:if test="${empty processMap}">
							<th colspan="2"></th>
						</c:if>
					</tr>
				</c:if>
			</table>
		</div>

	</div>
</div>
</body>
</html>