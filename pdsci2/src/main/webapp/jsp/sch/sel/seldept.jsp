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
	<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>
	<script type="text/javascript">
		//选科数据
		var selDeptFlows = {};
		var selMonths = {};
		//页面初始化
		$(function(){
			//阻止事件冒泡
			$("[onclick]").click(function(e){
				e.stopPropagation();
			});
		});

		//轮转方案说明
		function openDetail(rotationName, rotationFlow){
			var url = "<s:url value='/sch/template/editRotation'/>?roleFlag=${param.roleFlag}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow="+rotationFlow;
			jboxOpen(url, rotationName, 800, 500);
		}

		//选中科室
		function selDept(dept,recordFlow,groupFlow,selMax,selTypeId,maxDeptNum,schMonth){
			selDeptFlows[groupFlow] = selDeptFlows[groupFlow] || [];
			selMonths[groupFlow] = selMonths[groupFlow] || 0;

			var index = selDeptFlows[groupFlow].indexOf(recordFlow);
			if(index!=-1){
				selDeptFlows[groupFlow].splice(index,1);
				selMonths[groupFlow] =selMonths[groupFlow] - parseFloat(schMonth);
				$(dept).removeClass("selChoose");
			}else{
				selDeptFlows[groupFlow].push(recordFlow);
				selMonths[groupFlow] =selMonths[groupFlow] + parseFloat(schMonth);
				$(dept).addClass("selChoose");
			};
			var selCount = selDeptFlows[groupFlow].length;
			$("#"+groupFlow+"SelNum").text(selCount);
			$("#"+groupFlow+"SelMonthNum").text(selMonths[groupFlow]);
			var isOver =( selMonths[groupFlow]== parseFloat($("."+groupFlow).attr("schMonth")));
			$("#"+groupFlow+"selError").toggle(!isOver);
		}

		function selDeptSumCheck(){
			//判断轮转时间和
			var haveEmpty = true;
			$(".group").each(function(){
				var monthCount = 0;
				var noEmpty = true;
				var schMonth = parseFloat($(this).attr("schMonth")) || 0;
				var selectYear = $(this).attr("selectYear") || "";
				var title = $("legend font",this).text();
				$(".selChoose .selDiyMonth",this).each(function(){
					var sm = parseFloat(this.value) || 0;
					if(sm){
						monthCount+=(sm);
					}else{
						return noEmpty = false;
					}
				});

				if(!noEmpty){
					jboxInfo("请为"+selectYear+"<font color='blue'>"+title+"</font>组合内选择的科室完整填写轮转时间！");
					return haveEmpty = false;
				}
				if(schMonth!=monthCount){
					jboxInfo(selectYear+"<font color='blue'>"+title+"</font>要求轮转时间为：<font color='red'>"+schMonth+"</font>,已选总时间为：<font color='red'>"+monthCount+"</font>");
					return haveEmpty = false;
				}
			});
			return haveEmpty;
		}

		//确认选科
		function confirmDept(){
			if(!selDeptSumCheck()){return false;};
			var isOk = true;
			$(".selError").each(function(){
				if($(this).is(":visible"))
				{
					isOk=false;
					return isOk;
				}
			});
			var selDepts=[];
			$(".selChoose").each(function(){
				var selDept={};
				selDept.recordFlow=$(this).attr("recordFlow")||"";
				selDept.selectYear=$(this).attr("selectYear")||"";
				selDept.schMonth=$(this).attr("schMonth")||"";
				selDepts.push(selDept);
			});
			if(isOk){
				jboxConfirm("确认完成选科？",function(){
					jboxPostJson("<s:url value='/sch/sel/saveSelDept'/>?doctorFlow=${doctor.doctorFlow}&rotationFlow=${doctor.rotationFlow}&orgFlow=${doctor.orgFlow}&sessionNumber=${doctor.sessionNumber}",
							JSON.stringify(selDepts),
							function(resp){
								if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
									top.jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
									if("${param.role}"=="admin")
									{
										window.parent.frames['mainIframe'].location.reload(true);
									}
 									location.reload(true);
								}
							},
							null,false);
				},null);
			}else{
				jboxTip("请完成所有选科！");
			}
		}


	</script>
</head>

<body>
<div class="mainright">
	<div class="tips">
		<h1 class="tips_title">选科操作：</h1>
		<div class="tips_body" style="padding: 10px 10px 0;">
			<c:if test="${doctor.selAllFlag ne 'Y'}">
				<p style="color:red;">
					<font style="font-weight: bolder;">· </font>
					选择轮转科室并
					<c:if test="${empty resultList}">
						<a style="color:blue;cursor: pointer;" onclick="confirmDept();">确认完成</a>
					</c:if>
					<c:if test="${!(empty resultList)}">
						确认完成
					</c:if>。
				</p>
			</c:if>
			<c:if test="${doctor.selAllFlag eq 'Y'}">
				<p style="color:red;">
					<font style="font-weight: bolder;">· </font>
					你<a style="color:blue;cursor: pointer;" onclick="javascript:void(0);">已完成</a>轮转科室选择。
				</p>
				<c:if test="${ cfg.cycleTypeId eq schCycleTypeEnumOneYear.id}">

					<c:if test="${selectYear eq 'One'||selectYear eq 'Two'||selectYear eq 'Three'}">
						<p>
							<font style="font-weight: bolder;">· </font>
							<c:if test="${doctor.schOneFlag eq 'Y'}">
								【第一年选科】管理员已为你安排好轮转时间，请去【轮转查询功能】查看。
							</c:if>
							<c:if test="${doctor.schOneFlag eq 'N'}">
								【第一年选科】请等待管理员为你安排轮转时间。
							</c:if>
						</p>
					</c:if>
					<c:if test="${selectYear eq 'Two'||selectYear eq 'Three'}">
						<p>
							<font style="font-weight: bolder;">· </font>
							<c:if test="${doctor.schTwoFlag eq 'Y'}">
								【第二年选科】管理员已为你安排好轮转时间，请去【轮转查询功能】查看。
							</c:if>
							<c:if test="${doctor.schTwoFlag eq 'N'}">
								【第二年选科】请等待管理员为你安排轮转时间。
							</c:if>
						</p>
					</c:if>
					<c:if test="${selectYear eq 'Three'}">
						<p>
							<font style="font-weight: bolder;">· </font>
							<c:if test="${doctor.schThreeFlag eq 'Y'}">
								【第三年选科】管理员已为你安排好轮转时间，请去【轮转查询功能】查看。
							</c:if>
							<c:if test="${doctor.schThreeFlag eq 'N'}">
								【第三年选科】请等待管理员为你安排轮转时间。
							</c:if>
						</p>
					</c:if>
				</c:if>
				<c:if test="${ cfg.cycleTypeId eq schCycleTypeEnumAllYear.id}">
					<p>
					<font style="font-weight: bolder;">· </font>
					<c:if test="${param.role ne 'admin'}">
						<c:if test="${doctor.schAllFlag eq 'Y'}">
							管理员已为你安排好轮转时间，请去【轮转查询功能】查看。
						</c:if>
						<c:if test="${doctor.schAllFlag eq 'N'}">
							请等待管理员为你安排轮转时间。
						</c:if>
					</c:if>
					<c:if test="${param.role eq 'admin'}">
						<c:if test="${doctor.schAllFlag eq 'Y'}">
							已安排轮转时间，请在列表页点击【查看排班】。
						</c:if>
						<c:if test="${doctor.schAllFlag eq 'N'}">
							请等待安排轮转时间。
						</c:if>
					</c:if>
					</p>
				</c:if>
			</c:if>
		</div>
		<div class="tips_bottom_bg"></div>
	</div>
	<div class="content" style="padding-right:300px;">
		<div style="width: 100%;height: 40px;">
			<table class="basic" style="width: 100%;margin-top: 10px;margin-bottom: 10px;background-color: white;position: relative;">
				<tr>
					<td id="titleTd" style="cursor: pointer;">
						姓名：<font style="font-weight: bold;">${doctor.doctorName}</font>
						&#12288;&#12288;&#12288;&#12288;
						轮转方案：
						<c:if test="${param.role ne 'admin'}">
							<font title="点击查看方案说明" style="font-weight: bold;color:#3d91d5;"  onclick="openDetail('${doctor.rotationName}','${doctor.rotationFlow}');">${doctor.rotationName}</font>
						</c:if>
						<c:if test="${param.role eq 'admin'}">
							<font  style="font-weight: bold;" >${doctor.rotationName}</font>
						</c:if>
						&#12288;&#12288;&#12288;&#12288;
						培训年级：<font style="font-weight: bold;"><c:out value="${doctor.sessionNumber}" default="无"/></font>
						&#12288;&#12288;&#12288;&#12288;
						轮转年限：<font style="font-weight: bold;"><c:out value="${doctor.trainingYears}" default="无"/></font>
					</td>
				</tr>
			</table>
		</div>
		<div id="selDept">
			<table width="100%">
				<caption>
					轮转科室选择
				</caption>
			</table>
			<c:if test="${ cfg.cycleTypeId eq schCycleTypeEnumOneYear.id}">
				<c:if test="${selectYear eq 'One'||selectYear eq 'Two'||selectYear eq 'Three'}">
					<table width="100%">
						<caption>
							第一年轮转科室选择
						</caption>
					</table>
					<div class="groupHome">
						<c:set var="groupNotCount" value="0"></c:set>
						<c:forEach items="${groups}" var="group">
							<c:set var="selDeptCount" value="0"/>
							<c:set var="selDeptMonthCount" value="0"/>
							<c:if test="${not empty standardGroupDeptMap[group.groupFlow] and group.oneSchMonth !=0.0}">
								<fieldset class="sort${group.schStageId} group ${group.groupFlow}OneSchMonth"
										  deptNum="${group.deptNum}"
										  selTypeId="${group.selTypeId}"
										  maxDeptNum="${group.maxDeptNum}"
										  schMonth="${group.oneSchMonth}"
										  selectYear="【第一年科室选择】">
									<legend class="groupName">
										<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
											${group.schStageName}：
										</c:if>
										<font>${group.groupName}</font>
									</legend>
									<c:forEach items="${standardGroupDeptMap[group.groupFlow]}" var="dept">
									<c:if test="${dept.recordStatus eq 'Y'}">
									<c:set var="key" value="${group.groupFlow}${dept.standardDeptId}One"></c:set>
									<c:forEach items="${localRotationDeptListMap[key]}" var="schDept">

									<c:set var="doctorDeptKey" value="${group.groupFlow}${dept.standardDeptId}${schDept.schDeptFlow}One"></c:set>
									<c:if test="${group.isRequired eq 'Y'}">
									<c:set var="selDeptCount" value="${1+selDeptCount}"/>
									<c:if test="${empty selectDeptMap[doctorDeptKey] }">
										<c:set var="selDeptMonthCount" value="${schDept.oneSchMonth+selDeptMonthCount}"/>
									</c:if>
									<c:if test="${not empty selectDeptMap[doctorDeptKey] }">
										<c:set var="selDeptMonthCount" value="${selectDeptMap[doctorDeptKey].schMonth+selDeptMonthCount}"/>
									</c:if>
									<div class="rotationChoose selChoose" schMonth="${schDept.oneSchMonth+0}" recordFlow="${schDept.recordFlow}" selectYear="One">
										</c:if>
										<c:if test="${group.isRequired ne 'Y'}">
										<c:if test="${not empty selectDeptMap[doctorDeptKey] }">
											<c:set var="selDeptCount" value="${1+selDeptCount}"/>
											<c:set var="selDeptMonthCount" value="${selectDeptMap[doctorDeptKey].schMonth+selDeptMonthCount}"/>
										</c:if>
										<div   class="rotationChoose ${!empty selectDeptMap[doctorDeptKey]?'selChoose':''}"
											<c:if test="${doctor.selAllFlag ne 'Y'}">title="选科/取消"
												onclick="selDept(this,'${schDept.recordFlow}','${group.groupFlow}OneSchMonth','${group.deptNum}','${group.selTypeId}','${group.maxDeptNum}','${schDept.oneSchMonth+0}');"
											</c:if>
												schMonth="${schDept.oneSchMonth+0}"recordFlow="${schDept.recordFlow}" selectYear="One"
										>
											</c:if>
											<div class="chooseStandard">
												标准科室：${schDept.standardDeptName}
											</div>
											<div class="chooseName">${schDept.schDeptName}（${schDept.oneSchMonth}${applicationScope[unitKey].name}）</div>
											<div class="chooseMonth">
												<input id="schMonth${schDept.recordFlow}" readonly type="text" value="${schDept.oneSchMonth}" class="inputText selDiyMonth" schMonth="${schDept.oneSchMonth}" style="width: 20px;"/>
													${applicationScope[unitKey].name}
											</div>
										</div>
										</c:forEach>
										</c:if>
										</c:forEach>

										<div style="width: 97.5%;float: left;border: 1px solid #ccc;margin-top: 10px;height: 30px;">
											<p style="padding-left: 10px;margin-top: 5px;">
												<span style="font-weight: bold;">选科条件：</span>
												轮转时间：${group.oneSchMonth}${applicationScope[unitKey].name}
												<span style="font-weight: bold;">&#12288;&#12288;选择状态：</span>
												<font>
													已选
													<font color="blue" id="${group.groupFlow}OneSchMonthSelNum">${selDeptCount}</font>
													个科室，合计
													<font color="blue" id="${group.groupFlow}OneSchMonthSelMonthNum">${selDeptMonthCount}</font>
														${applicationScope[unitKey].name}。
													<c:set var="selError" value="${selDeptMonthCount ne group.oneSchMonth}"/>
													<font color="orange" class="selError" id="${group.groupFlow}OneSchMonthselError" style="<c:if test="${!selError}">display: none;</c:if>">
														注意，轮转时长不在要求范围！
													</font>
												</font>
											</p>
										</div>
										<div style="width: 97.5%;float: left;border: 1px solid #ccc;margin-top: 10px;height: 30px;">
											<p style="padding-left: 10px;margin-top: 5px;">
												<span style="font-weight: bold;">备注：</span>
													${group.groupNote}
											</p>
										</div>
								</fieldset>
							</c:if>
							<c:if test="${empty standardGroupDeptMap[group.groupFlow]}">
								<c:set var="groupNotCount" value="${groupNotCount+1}"></c:set>
							</c:if>
						</c:forEach>

						<c:if test="${groupNotCount eq fn:length(groups)}">
							<table class="basic" style="width:100%;margin-top: 10px;margin-bottom: 10px;">
								<tr>
									<td style="text-align: center;">没有科室可选！</td>
								</tr>
							</table>
						</c:if>
					</div>
				</c:if>
				<c:if test="${selectYear eq 'Two'||selectYear eq 'Three'}">
					<table width="100%">
						<caption>
							第二年轮转科室选择
						</caption>
					</table>
					<div class="groupHome">
						<c:set var="groupNotCount" value="0"></c:set>
						<c:forEach items="${groups}" var="group">
							<c:set var="selDeptCount" value="0"/>
							<c:set var="selDeptMonthCount" value="0"/>
							<c:if test="${not empty standardGroupDeptMap[group.groupFlow]  and group.twoSchMonth !=0.0}">
								<fieldset class="sort${group.schStageId} group ${group.groupFlow}TwoSchMonth"
										  deptNum="${group.deptNum}"
										  selTypeId="${group.selTypeId}"
										  maxDeptNum="${group.maxDeptNum}"
										  schMonth="${group.twoSchMonth}"
										  selectYear="【第二年科室选择】">
									<legend class="groupName">
										<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
											${group.schStageName}：
										</c:if>
										<font>${group.groupName}</font>
									</legend>
									<c:forEach items="${standardGroupDeptMap[group.groupFlow]}" var="dept">
									<c:if test="${dept.recordStatus eq 'Y'}">
									<c:set var="key" value="${group.groupFlow}${dept.standardDeptId}Two"></c:set>
									<c:forEach items="${localRotationDeptListMap[key]}" var="schDept">

									<c:set var="doctorDeptKey" value="${group.groupFlow}${dept.standardDeptId}${schDept.schDeptFlow}Two"></c:set>
										<c:if test="${group.isRequired eq 'Y'}">
											<c:set var="selDeptCount" value="${1+selDeptCount}"/>
											<c:if test="${empty selectDeptMap[doctorDeptKey] }">
												<c:set var="selDeptMonthCount" value="${schDept.twoSchMonth+selDeptMonthCount}"/>
											</c:if>
											<c:if test="${not empty selectDeptMap[doctorDeptKey] }">
												<c:set var="selDeptMonthCount" value="${selectDeptMap[doctorDeptKey].schMonth+selDeptMonthCount}"/>
											</c:if>
											<div class="rotationChoose selChoose" schMonth="${schDept.twoSchMonth+0}" recordFlow="${schDept.recordFlow}" selectYear="Two" >
										</c:if>
										<c:if test="${group.isRequired ne 'Y'}">
										<c:if test="${not empty selectDeptMap[doctorDeptKey] }">
											<c:set var="selDeptCount" value="${1+selDeptCount}"/>
											<c:set var="selDeptMonthCount" value="${selectDeptMap[doctorDeptKey].schMonth+selDeptMonthCount}"/>
										</c:if>
										<div   class="rotationChoose ${!empty selectDeptMap[doctorDeptKey]?'selChoose':''}"
												<c:if test="${doctor.selAllFlag ne 'Y'}">title="选科/取消"
													onclick="selDept(this,'${schDept.recordFlow}','${group.groupFlow}TwoSchMonth','${group.deptNum}','${group.selTypeId}','${group.maxDeptNum}','${schDept.twoSchMonth+0}');"
												</c:if>
											 schMonth="${schDept.twoSchMonth+0}" recordFlow="${schDept.recordFlow}" selectYear="Two"
										>
											</c:if>
											<div class="chooseStandard">
												标准科室：${schDept.standardDeptName}
											</div>
											<div class="chooseName">${schDept.schDeptName}（${schDept.twoSchMonth}${applicationScope[unitKey].name}）</div>
											<div class="chooseMonth">
												<input id="schMonth${schDept.recordFlow}" readonly type="text" value="${schDept.twoSchMonth}" class="inputText selDiyMonth" schMonth="${schDept.twoSchMonth}" style="width: 20px;"/>
													${applicationScope[unitKey].name}
											</div>
										</div>
										</c:forEach>
										</c:if>
										</c:forEach>

										<div style="width: 97.5%;float: left;border: 1px solid #ccc;margin-top: 10px;height: 30px;">
											<p style="padding-left: 10px;margin-top: 5px;">
												<span style="font-weight: bold;">选科条件：</span>
												轮转时间：${group.twoSchMonth}${applicationScope[unitKey].name}
												<span style="font-weight: bold;">&#12288;&#12288;选择状态：</span>
												<font>
													已选
													<font color="blue" id="${group.groupFlow}TwoSchMonthSelNum">${selDeptCount}</font>
													个科室，合计
													<font color="blue" id="${group.groupFlow}TwoSchMonthSelMonthNum">${selDeptMonthCount}</font>
														${applicationScope[unitKey].name}。
													<c:set var="selError" value="${selDeptMonthCount ne group.twoSchMonth}"/>
													<font color="orange" class="selError" id="${group.groupFlow}TwoSchMonthselError" style="<c:if test="${!selError}">display: none;</c:if>">
														注意，轮转时长不在要求范围！
													</font>
												</font>
											</p>
										</div>
										<div style="width: 97.5%;float: left;border: 1px solid #ccc;margin-top: 10px;height: 30px;">
											<p style="padding-left: 10px;margin-top: 5px;">
												<span style="font-weight: bold;">备注：</span>
													${group.groupNote}
											</p>
										</div>
								</fieldset>
							</c:if>
							<c:if test="${empty standardGroupDeptMap[group.groupFlow]}">
								<c:set var="groupNotCount" value="${groupNotCount+1}"></c:set>
							</c:if>
						</c:forEach>

						<c:if test="${groupNotCount eq fn:length(groups)}">
							<table class="basic" style="width:100%;margin-top: 10px;margin-bottom: 10px;">
								<tr>
									<td style="text-align: center;">没有科室可选！</td>
								</tr>
							</table>
						</c:if>
					</div>

				</c:if>
				<c:if test="${selectYear eq 'Three'}">
					<table width="100%">
						<caption>
							第三年轮转科室选择
						</caption>
					</table>
					<div class="groupHome">
						<c:set var="groupNotCount" value="0"></c:set>
						<c:forEach items="${groups}" var="group">
							<c:set var="selDeptCount" value="0"/>
							<c:set var="selDeptMonthCount" value="0"/>
							<c:if test="${not empty standardGroupDeptMap[group.groupFlow] and group.threeSchMonth !=0.0}">
								<fieldset class="sort${group.schStageId} group ${group.groupFlow}ThreeSchMonth"
										  deptNum="${group.deptNum}"
										  selTypeId="${group.selTypeId}"
										  maxDeptNum="${group.maxDeptNum}"
										  schMonth="${group.threeSchMonth}"
										  selectYear="【第三年科室选择】">
									<legend class="groupName">
										<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
											${group.schStageName}：
										</c:if>
										<font>${group.groupName}</font>
									</legend>
									<c:forEach items="${standardGroupDeptMap[group.groupFlow]}" var="dept">
									<c:if test="${dept.recordStatus eq 'Y'}">
									<c:set var="key" value="${group.groupFlow}${dept.standardDeptId}Three"></c:set>
									<c:forEach items="${localRotationDeptListMap[key]}" var="schDept">

									<c:set var="doctorDeptKey" value="${group.groupFlow}${dept.standardDeptId}${schDept.schDeptFlow}Three"></c:set>
									<c:if test="${group.isRequired eq 'Y'}">
									<c:set var="selDeptCount" value="${1+selDeptCount}"/>
									<c:if test="${empty selectDeptMap[doctorDeptKey] }">
										<c:set var="selDeptMonthCount" value="${schDept.threeSchMonth+selDeptMonthCount}"/>
									</c:if>
									<c:if test="${not empty selectDeptMap[doctorDeptKey] }">
										<c:set var="selDeptMonthCount" value="${selectDeptMap[doctorDeptKey].schMonth+selDeptMonthCount}"/>
									</c:if>
									<div class="rotationChoose selChoose" schMonth="${schDept.threeSchMonth+0}" recordFlow="${schDept.recordFlow}" selectYear="Three">
										</c:if>
										<c:if test="${group.isRequired ne 'Y'}">
										<c:if test="${not empty selectDeptMap[doctorDeptKey] }">
											<c:set var="selDeptCount" value="${1+selDeptCount}"/>
											<c:set var="selDeptMonthCount" value="${selectDeptMap[doctorDeptKey].schMonth+selDeptMonthCount}"/>
										</c:if>
										<div  class="rotationChoose ${!empty selectDeptMap[doctorDeptKey]?'selChoose':''}"
												<c:if test="${doctor.selAllFlag ne 'Y'}">title="选科/取消"
													onclick="selDept(this,'${schDept.recordFlow}','${group.groupFlow}ThreeSchMonth','${group.deptNum}','${group.selTypeId}','${group.maxDeptNum}','${schDept.threeSchMonth+0}');"
												</c:if>
											 schMonth="${schDept.threeSchMonth+0}" recordFlow="${schDept.recordFlow}" selectYear="Three"
										>
											</c:if>
											<div class="chooseStandard">
												标准科室：${schDept.standardDeptName}
											</div>
											<div class="chooseName">${schDept.schDeptName}（${schDept.threeSchMonth}${applicationScope[unitKey].name}）</div>
											<div class="chooseMonth">
												<input id="schMonth${schDept.recordFlow}" readonly type="text" value="${schDept.threeSchMonth}" class="inputText selDiyMonth" schMonth="${schDept.threeSchMonth}" style="width: 20px;"/>
													${applicationScope[unitKey].name}
											</div>
										</div>
										</c:forEach>
										</c:if>
										</c:forEach>

										<div style="width: 97.5%;float: left;border: 1px solid #ccc;margin-top: 10px;height: 30px;">
											<p style="padding-left: 10px;margin-top: 5px;">
												<span style="font-weight: bold;">选科条件：</span>
												轮转时间：${group.threeSchMonth}${applicationScope[unitKey].name}
												<span style="font-weight: bold;">&#12288;&#12288;选择状态：</span>
												<font>
													已选
													<font color="blue" id="${group.groupFlow}ThreeSchMonthSelNum">${selDeptCount}</font>
													个科室，合计
													<font color="blue" id="${group.groupFlow}ThreeSchMonthSelMonthNum">${selDeptMonthCount}</font>
														${applicationScope[unitKey].name}。
													<c:set var="selError" value="${selDeptMonthCount ne group.threeSchMonth}"/>
													<font color="orange" class="selError" id="${group.groupFlow}ThreeSchMonthselError" style="<c:if test="${!selError}">display: none;</c:if>">
														注意，轮转时长不在要求范围！
													</font>
												</font>
											</p>
										</div>
										<div style="width: 97.5%;float: left;border: 1px solid #ccc;margin-top: 10px;height: 30px;">
											<p style="padding-left: 10px;margin-top: 5px;">
												<span style="font-weight: bold;">备注：</span>
													${group.groupNote}
											</p>
										</div>
								</fieldset>
							</c:if>
							<c:if test="${empty standardGroupDeptMap[group.groupFlow]}">
								<c:set var="groupNotCount" value="${groupNotCount+1}"></c:set>
							</c:if>
						</c:forEach>

						<c:if test="${groupNotCount eq fn:length(groups)}">
							<table class="basic" style="width:100%;margin-top: 10px;margin-bottom: 10px;">
								<tr>
									<td style="text-align: center;">没有科室可选！</td>
								</tr>
							</table>
						</c:if>
					</div>
				</c:if>
			</c:if>
			<c:if test="${ cfg.cycleTypeId eq schCycleTypeEnumAllYear.id}">
				<div class="groupHome">
					<c:set var="groupNotCount" value="0"></c:set>
					<c:forEach items="${groups}" var="group">
						<c:set var="selDeptCount" value="0"/>
						<c:set var="selDeptMonthCount" value="0"/>
						<c:if test="${not empty standardGroupDeptMap[group.groupFlow] and group.schMonth !=0.0}">
							<fieldset class="sort${group.schStageId} group ${group.groupFlow}SchMonth"
									  deptNum="${group.deptNum}"
									  selTypeId="${group.selTypeId}"
									  maxDeptNum="${group.maxDeptNum}"
									  schMonth="${group.schMonth}"
									  selectYear="All">
								<legend class="groupName">
									<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
										${group.schStageName}：
									</c:if>
									<font>${group.groupName}</font>
								</legend>
								<c:forEach items="${standardGroupDeptMap[group.groupFlow]}" var="dept">
									<c:if test="${dept.recordStatus eq 'Y'}">
										<c:set var="key" value="${group.groupFlow}${dept.standardDeptId}"></c:set>
										<c:forEach items="${localRotationDeptListMap[key]}" var="schDept">

											<c:set var="doctorDeptKey" value="${group.groupFlow}${dept.standardDeptId}${schDept.schDeptFlow}All"></c:set>
											<c:if test="${group.isRequired eq 'Y'}">
												<c:set var="selDeptCount" value="${1+selDeptCount}"/>
												<c:if test="${empty selectDeptMap[doctorDeptKey] }">
													<c:set var="selDeptMonthCount" value="${schDept.schMonth+selDeptMonthCount}"/>
												</c:if>
												<c:if test="${not empty selectDeptMap[doctorDeptKey] }">
													<c:set var="selDeptMonthCount" value="${selectDeptMap[doctorDeptKey].schMonth+selDeptMonthCount}"/>
												</c:if>
												<div class="rotationChoose selChoose" schMonth="${schDept.schMonth+0}" recordFlow="${schDept.recordFlow}" selectYear="All">
											</c:if>
											<c:if test="${group.isRequired ne 'Y'}">
												<c:if test="${not empty selectDeptMap[doctorDeptKey] }">
													<c:set var="selDeptCount" value="${1+selDeptCount}"/>
													<c:set var="selDeptMonthCount" value="${selectDeptMap[doctorDeptKey].schMonth+selDeptMonthCount}"/>
												</c:if>
												<div  class="rotationChoose ${!empty selectDeptMap[doctorDeptKey]?'selChoose':''}"
														<c:if test="${doctor.selAllFlag ne 'Y'}">title="选科/取消"
															onclick="selDept(this,'${schDept.recordFlow}','${group.groupFlow}SchMonth','${group.deptNum}','${group.selTypeId}','${group.maxDeptNum}','${schDept.schMonth+0}');"
														</c:if>
													 schMonth="${schDept.schMonth+0}"recordFlow="${schDept.recordFlow}" selectYear="All"
												>
											</c:if>
												<div class="chooseStandard">
													标准科室：${schDept.standardDeptName}
												</div>
												<div class="chooseName">${schDept.schDeptName}（${schDept.schMonth}${applicationScope[unitKey].name}）</div>
												<div class="chooseMonth">
													<input id="schMonth${schDept.recordFlow}" readonly type="text" value="${schDept.schMonth}" class="inputText selDiyMonth" schMonth="${schDept.schMonth}" style="width: 20px;"/>
														${applicationScope[unitKey].name}
												</div>
											</div>
										</c:forEach>
									</c:if>
								</c:forEach>

								<div style="width: 97.5%;float: left;border: 1px solid #ccc;margin-top: 10px;height: 30px;">
									<p style="padding-left: 10px;margin-top: 5px;">
										<span style="font-weight: bold;">选科条件：</span>
										轮转时间：${group.schMonth}${applicationScope[unitKey].name}
										<span style="font-weight: bold;">&#12288;&#12288;选择状态：</span>
										<font>
											已选
											<font color="blue" id="${group.groupFlow}SchMonthSelNum">${selDeptCount}</font>
											个科室，合计
											<font color="blue" id="${group.groupFlow}SchMonthSelMonthNum">${selDeptMonthCount}</font>
												${applicationScope[unitKey].name}。
											<c:set var="selError" value="${selDeptMonthCount ne group.schMonth}"/>
											<font color="orange" class="selError" id="${group.groupFlow}SchMonthselError" style="<c:if test="${!selError}">display: none;</c:if>">
												注意，轮转时长不在要求范围！
											</font>
										</font>
									</p>
								</div>
								<div style="width: 97.5%;float: left;border: 1px solid #ccc;margin-top: 10px;height: 30px;">
									<p style="padding-left: 10px;margin-top: 5px;">
										<span style="font-weight: bold;">备注：</span>
											${group.groupNote}
									</p>
								</div>
							</fieldset>
						</c:if>
						<c:if test="${empty standardGroupDeptMap[group.groupFlow]}">
							<c:set var="groupNotCount" value="${groupNotCount+1}"></c:set>
						</c:if>
					</c:forEach>

					<c:if test="${groupNotCount eq fn:length(groups)}">
						<table class="basic" style="width:100%;margin-top: 10px;margin-bottom: 10px;">
							<tr>
								<td style="text-align: center;">没有科室可选！</td>
							</tr>
						</table>
					</c:if>
				</div>
			</c:if>
		</div>

	</div>
</div>
</body>
</html>