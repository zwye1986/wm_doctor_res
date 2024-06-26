<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
	</jsp:include>
	<style type="text/css">
		.wDept{color:#3d91d5;}
		fieldset{
			margin-top: 10px;
		}
		.chooseStandard2{
			margin-top: 10px;
		}
		.rotationChoose2{
			width: 48%;
		}
		.cannotSelect{
			cursor:default;
		}
	</style>
	<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>
	<script type="text/javascript">

		function compare(property){
			return function(a,b){
				var value1 = a[property];
				var value2 = b[property];
				if(value1>value2)
				{
					return 1;
				}else
				if(value1<value2)
				{
					return -1;
				}else 	return 0;
			}
		}
		//科室选择或取消
		function save(){
			var selCount=$(".selChoose").length;
			if(selCount<=0)
			{
				jboxTip("请选择需要轮转的科室！");
				return false;
			}
			if(allSchMonth!=2)
			{
				jboxTip("选择的轮转科室时间不足2个月！");
				return false;
			}
			var datas=[];

			$(".selChoose").each(function(){
				var recordFlow=$(this).attr("recordFlow");
				var standardDeptName=$(this).attr("standardDeptName");
				var schDeptFlow=$(this).attr("schDeptFlow");
				var schMonth=$(this).attr("schMonth");
				var schDeptName=$(this).attr("schDeptName");
				var groupFlow=$(this).attr("groupFlow");
				var groupName=$(this).attr("groupName");
				var schDate=$.trim($("#"+recordFlow+"result").html());
				var data = {};
				data.schDeptFlow = schDeptFlow;
				data.schDeptName = schDeptName;
				data.recordFlow = recordFlow;
				data.standardDeptName = standardDeptName;
				data.schMonth = schMonth;
				data.groupFlow = groupFlow;
				data.groupName = groupName;
				data.schDate = schDate;
				datas.push(data);
			});
			if(datas.length<=0)
			{
				jboxTip("请选择需要轮转的科室！");
				return false;
			}
			datas.sort(compare('schDate'));
			var selectData={
				depts:datas
			};
			$("#jsondata").val(JSON.stringify(selectData));
			jboxConfirm("确认提交选科,提交后无法修改?", function () {
				jboxPost("<s:url value='/res/doc/operSelDept'/>",$("#selectForm").serialize(),function(resp){
					if(resp=="选科保存成功！"){
						jboxStartLoading();
						window.location.reload(true);
						jboxEndLoading();
					}
				},null,true);
			});

		}
		var schMonthMap={};
		schMonthMap["${nextMonth}"]=0;
		schMonthMap["${nextNextMonth}"]=0;

		var deptNum=0;
		var allSchMonth=0;
		//选中一个科室
		function selItem(div){
			var schMonth=$(div).attr("schMonth");
			var recordFlow=$(div).attr("recordFlow");
			var groupFlow=$(div).attr("groupFlow");
			if(schMonth=="")
					schMonth=0;
			else schMonth=parseInt(schMonth);
			if($(div).hasClass("selChoose"))
			{
				allSchMonth-=schMonth;
				deptNum-=1;
				$(div).removeClass("selChoose");
				schMonthMap["${nextMonth}"]=0;
				schMonthMap["${nextNextMonth}"]=0;
				$("#"+recordFlow+"result").css("color","");
				$("#"+recordFlow+"result").html("无");
				var selNum=parseInt($("#"+groupFlow+"SelNum").html());
				$("#"+groupFlow+"SelNum").html(selNum-1);
			}else{
				allSchMonth+=schMonth;
				if(allSchMonth>2)
				{
					allSchMonth-=schMonth;
					jboxTip("每次选择的轮转时间不得超过2个月");
					return;
				}
				deptNum+=1;
				$(div).addClass("selChoose");
				schMonthMap["${nextMonth}"]=1;
				schMonthMap["${nextNextMonth}"]=1;

				$("#"+recordFlow+"result").css("color","red");
				$("#"+recordFlow+"result").html("${nextMonth}"+","+"${nextNextMonth}");
				var selNum=parseInt($("#"+groupFlow+"SelNum").html());
				$("#"+groupFlow+"SelNum").html(selNum+1);
			}
		}
		function selShowItem(recordFlow){
			$("#recordFlow").val(recordFlow);
			var div=$("#"+recordFlow);
			var schMonth=$(div).attr("schMonth");
			var groupFlow=$(div).attr("groupFlow");
			if(schMonth=="")
					schMonth=0;
			else schMonth=parseInt(schMonth);
			if(!$(div).hasClass("selChoose"))
			{
				if((allSchMonth+schMonth)>2)
				{
					jboxTip("每次选择的轮转时间不得超过2个月");
					return;
				}
				var minDeptNum=$(div).attr("minDeptNum");
				if(minDeptNum=="")
					minDeptNum=0;
				else minDeptNum=parseInt(minDeptNum);
				var selNum=parseInt($("#"+groupFlow+"SelNum").html());
				if(selNum+1>minDeptNum)
				{

					jboxTip("本组科室最多只可选择"+minDeptNum+"个科室！");
					return;
				}
			}

			var selectMonth= $.trim($("#"+recordFlow+"result").html());
			$(".selectMonth").removeClass("dateSelect");
			$(".selectMonth").css("color","");
			if(selectMonth!="无")
			{
				$("#"+selectMonth).addClass("dateSelect");
				$("#"+selectMonth).css("color","#3d91d5");
			}
			var html = $("#companyType").html();
			jboxOpenContent(html,"轮转时间选择" , 300,200,true);
		}
		function selecDate(month,obj)
		{
			var recordFlow=$("#recordFlow").val();
			var div=$("#"+recordFlow);
			var schMonth=$(div).attr("schMonth");
			var groupFlow=$(div).attr("groupFlow");
			if(schMonth=="")
				schMonth=0;
			else schMonth=parseInt(schMonth);
			if($(obj).hasClass("dateSelect"))
			{
				$(div).removeClass("selChoose");
				schMonthMap[month] = 0;
				$("#" + recordFlow + "result").css("color", "");
				$("#" + recordFlow + "result").html("无");
				allSchMonth -= schMonth;
				deptNum -= 1;
				var selNum = parseInt($("#" + groupFlow + "SelNum").html());
				$("#" + groupFlow + "SelNum").html(selNum - 1);
				$(obj).removeClass("dateSelect");
				$(obj).css("color","");
			}else{
				var f = schMonthMap[month];
				if (f == 1) {
					jboxTip("轮转时间：" + month + " 已选择其他科室！");
					return false;
				}

				var haveLast=$(div).attr("haveLast");
				var haveLast2=$(div).attr("haveLast2");
				if(haveLast<=0 && month=="${nextMonth}")
				{
					jboxTip("该科室轮转时间：${nextMonth} 内轮转人数已经满！");
					return false;
				}
				if(haveLast2<=0 && month=="${nextNextMonth}")
				{
					jboxTip("该科室轮转时间：${nextNextMonth} 内轮转人数已经满！");
					return false;
				}
				console.log($(".selectMonth").length);
				window.parent.$(".selectMonth").each(function(){
					if($(this).hasClass("dateSelect"))
					{
						var lastMonth= $.trim($(this).html());
						$(this).removeClass("dateSelect");
						$(this).css("color","");
						schMonthMap[lastMonth]=0;
						allSchMonth-=schMonth;
						deptNum-=1;
						var selNum=parseInt($("#"+groupFlow+"SelNum").html());
						$("#"+groupFlow+"SelNum").html(selNum-1);
					}
				});
				$(obj).addClass("dateSelect");
				$(obj).css("color","#3d91d5");
				allSchMonth+=schMonth;
				deptNum+=1;
				$(div).addClass("selChoose");
				schMonthMap[month]=1;
				$("#"+recordFlow+"result").css("color","red");
				$("#"+recordFlow+"result").html(month);
				var selNum=parseInt($("#"+groupFlow+"SelNum").html());
				$("#"+groupFlow+"SelNum").html(selNum+1);

			}
		}

	</script>
</head>
<body>
<div class="mainright">
	<form id="selectForm">
		<input type="hidden" name="selectData" id="jsondata">
	</form>
	<input type="hidden" value="" id="recordFlow">

	<div id="companyType" style="display: none;">
		<div  class="content" >
			<div class="title1 clearfix">
				<div id="tagContent">
					<div class="tagContent selectTag" id="tagContent0">
						<form id="cfgForm" style="position: relative;">
							<table style="width: 100%;" cellpadding="0" cellspacing="0" class="basic">
								<tr>
									<%--<td width="50%" style="width: 50%;text-align: right;border-right: 0px;">--%>
										<%--<input type="radio" id="${nextMonth}" name="selectMonth"--%>
											   <%--value="${nextMonth}"--%>
											   <%--onchange="window.frames['mainIframe'].window.selecDate('${nextMonth}');"/></td>--%>
									<td width="50%" style="width: 50%;text-align: center;border-left: 0px;cursor: pointer;" class="selectMonth"  id="${nextMonth}"
										onclick="window.frames['mainIframe'].window.selecDate('${nextMonth}',this);">${nextMonth}</td>
								</tr>
								<tr>
									<%--<td width="50%" style="width: 50%;text-align: right;border-right: 0px;">--%>
										<%--<input type="radio" id="${nextNextMonth}" name="selectMonth" value="${nextNextMonth}"--%>
											   <%--onchange="window.frames['mainIframe'].window.selecDate('${nextNextMonth}');"/>--%>
									<%--</td>--%>
									<td width="50%" style="width: 50%;text-align: center;border-left: 0px;cursor: pointer;" class="selectMonth"  id="${nextNextMonth}"
										onclick="window.frames['mainIframe'].window.selecDate('${nextNextMonth}',this);">${nextNextMonth}</td>
								</tr>
								<%--<tr>--%>
									<%--<td width="50%" style="width: 50%;text-align: right;border-right: 0px;">--%>
										<%--<input type="radio" id="N" name="selectMonth" value="N" onchange="window.frames['mainIframe'].window.selecDate('N');"/>--%>
									<%--</td>--%>
									<%--<td width="50%" style="width: 50%;text-align: left;border-left: 0px;">--%>
										<%--<label style="cursor: pointer;" style="margin-top: 10px;" for="N"--%>
											   <%-->暂不选择</label><br/>--%>
									<%--</td>--%>
								<%--</tr>--%>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="content">
		<div>
			<c:forEach items="${groups}" var="group">
				<c:set var="rotationDepts" value="${groupDeptMap[group.groupFlow]}"></c:set>
				<c:set var="selDeptCount" value="${empty groupMap[group.groupFlow]?0:groupMap[group.groupFlow]}"></c:set>
				<fieldset  class="${group.schStageId} groupHome ${group.groupFlow} " deptNum="${group.deptNum}" selTypeId="${group.selTypeId}"  schMonth="${group.schMonth+0}">
					<legend>
						<span class="groupName">${group.groupName}</span>
					</legend>
					<c:forEach items="${rotationDepts}" var="rotationDept">
						<c:set var="schMonth" value="${rotationDept.schMonth}"></c:set>

						<c:set var="key" value="${rotationDept.recordFlow}nextMonth"></c:set>
						<c:set var="key2" value="${rotationDept.recordFlow}nextNextMonth"></c:set>

						<c:set var="count" value="${empty countMap[key] ? 0:countMap[key]}"></c:set>
						<c:set var="count2" value="${empty countMap[key2] ? 0:countMap[key2]}"></c:set>

						<c:set var="schDept" value="${deptMap[rotationDept.recordFlow]}"></c:set>
						<c:set var="deptNum" value="${schDept.deptNum}"></c:set>

						<c:set var="haveLast" value="${schDept.deptNum-count}"></c:set>
						<c:set var="haveLast2" value="${schDept.deptNum-count2}"></c:set>

						<c:set var="click" value="true"></c:set>
						<c:set var="showTitle" value="false"></c:set>

						<c:if test="${selDeptCount>=group.deptNum}">
							<c:set var="click" value="false"></c:set>
						</c:if>
						<c:if test="${(empty deptNum or deptNum==0) and click}">
							<c:set var="click" value="false"></c:set>
						</c:if>
						<c:if test="${rotationDept.schMonth eq 1 and click}">
							<c:set var="rotationDeptSchMonth1" value="${rotationDept.standardDeptId}2nextMonth"></c:set>
							<c:set var="rotationDeptSchMonth2" value="${rotationDept.standardDeptId}2nextNextMonth"></c:set>
							<c:set var="Month1" value="${empty countMap[rotationDeptSchMonth1]?-1:countMap[rotationDeptSchMonth1]}"></c:set>
							<c:set var="Month2" value="${empty countMap[rotationDeptSchMonth2]?-1:countMap[rotationDeptSchMonth2]}"></c:set>
							<c:if test="${((schDept.deptNum-Month1)>0 and (schDept.deptNum-Month2)>0) and Month1!=-1 and Month2!=-1}">
								<c:set var="click" value="false"></c:set>
								<c:set var="showTitle" value="true"></c:set>
							</c:if>
							<c:if test="${ (haveLast<=0 and haveLast2<=0) and click}">
								<c:set var="click" value="false"></c:set>
							</c:if>
						</c:if>
						<c:if test="${rotationDept.schMonth eq 2 and click}">
							<c:if test="${ haveLast<=0 or haveLast2<=0	}">
								<c:set var="click" value="false"></c:set>
							</c:if>
						</c:if>
						<div class="wDept rotationChoose2
							<c:if test="${!click}">
								cannotSelect
							</c:if>	"
							<c:if test="${showTitle}">
								title="请优先选择轮转时间为2个月的【${rotationDept.standardDeptName}】科室！"
							</c:if>
								<c:if test="${rotationDept.schMonth eq 2 and click}">
									onclick="selItem(this);"
								</c:if>
								<c:if test="${rotationDept.schMonth eq 1 and click}">
									onclick="selShowItem('${rotationDept.recordFlow}');"
								</c:if>
							 schMonth="${rotationDept.schMonth}"
							 recordFlow="${rotationDept.recordFlow}"
							 groupFlow="${rotationDept.groupFlow}"
							 standardDeptName="${rotationDept.standardDeptName}"
							 schDeptFlow="${schDept.schDeptFlow}"
							 schDeptName="${schDept.schDeptName}"
							 minDeptNum="${group.deptNum}"
							 groupName="${group.groupName}"
							 deptNum="${schDept.deptNum}"
							 haveLast="${haveLast}"
							 haveLast2="${haveLast2}"
							 id="${rotationDept.recordFlow}"

						>
							<div class="chooseStandard2">
								科室名称：${rotationDept.standardDeptName}
								<c:if test="${not empty schDept}">
									(${schDept.schDeptName})
								</c:if>
							</div>
							<div class="chooseStandard2">
								轮转时间：${schMonth} ${applicationScope[unitKey].name}
							</div>
							<div class="chooseStandard2">
								开放报名人数：
								<c:if test="${not empty deptNum and deptNum !=0}">
									${deptNum}
								</c:if>
								<c:if test="${empty deptNum or deptNum==0}">
									<span style="color:red">未维护开放人数！</span>
								</c:if>
								<c:if test="${haveLast<=0 and haveLast2<=0 and (not empty deptNum and deptNum !=0)}">
									<span style="color:red">开放人数已满！</span>
								</c:if>
							</div>
							<div class="chooseStandard2">
								${nextMonth}已报名人数：${count}<br/>
								${nextNextMonth}已报名人数：${count2}
							</div>
							<div class="chooseStandard2"  style="margin-top: 0px;">
								已选择月份：<span id="${rotationDept.recordFlow}result">无</span>
							</div>
						</div>
					</c:forEach>
					<div style="width: 97%;float: left;border: 1px solid #ccc;margin-top: 10px;">
						<p style="padding-left: 10px;height: 35px;line-height: 35px;">
							<span style="font-weight: bold;">选科条件：</span>
							总选科数：${group.deptNum}
							<span style="font-weight: bold;">&#12288;&#12288;选择状态：</span>
							<font>
								已选
								<font color="blue" id="${group.groupFlow}SelNum">${selDeptCount}</font>
								个科室
							</font>
						</p>
					</div>
					<div style="width: 97%;float: left;border: 1px solid #ccc;margin-top: 10px;">
						<p style="padding-left: 10px;height: 35px;line-height: 35px;">
							<span style="font-weight: bold;">备注：</span>
								${group.groupNote}
						</p>
					</div>
				</fieldset>
			</c:forEach>
			<fieldset style="border: none;text-align: center;">
				<input class="search" type="button" id="save" value="提交选科" onclick="save()" />
			</fieldset>
		</div>
	</div>
</div>
</body>
</html>