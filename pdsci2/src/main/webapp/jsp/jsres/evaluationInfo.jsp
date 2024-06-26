<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
		<jsp:param name="basic" value="true" />
		<jsp:param name="jbox" value="true" />
		<jsp:param name="font" value="true" />
		<jsp:param name="jquery_validation" value="true" />
	</jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
	table{
		border-collapse: collapse;
		border: 1px solid #D3D3D3;
	}
	table td {
		border-top: 0;
		border-right: 1px solid #D3D3D3;
		border-bottom: 1px solid #D3D3D3;
		border-left: 0;
		padding-left: 4px;
		padding-right: 2px;
	}
	table th {
		border-top: 0;
		border-right: 1px solid #D3D3D3;
		border-bottom: 1px solid #D3D3D3;
		border-left: 0;
	}
	table tr.lastrow td {
		border-bottom: 0;
	}
	table tr td.lastCol {
		border-right: 0;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$(".showInfo").on("mouseenter mouseleave",
				function(){
					$(".theInfo",this).toggle(100);
				}
		);
		$(".show").on("mouseenter mouseleave",
				function(){
					$(".info",this).toggle(100);
				}
		);

	});
	//保存自评分
	function saveScore(expl,num){
		var score = expl.value;
		var itemId = expl.getAttribute("itemId");
		var itemName = expl.getAttribute("itemName");
		var selfTotalled = $("#selfTotalled").text();
		var reg = /^\d+(\.\d)?$/;
		var reg1 = /^\d+(\.0)$/;
		if(score>=0 && score<=num && reg.test(score)){
			if(reg1.test(score)){
				var score1 = parseInt(score);
				expl.value = score1;
			}else {
				var score1 = score;
			}
			$(expl).attr("preValue",score1);
			var url = "<s:url value='/jsres/evaluation/saveEvaluation'/>";
			var data = {"itemId":itemId,"itemName":itemName,"score":score1,"selfTotalled":selfTotalled};
			jboxPost(url, data, function(resp) {
				if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
					top.jboxTip(resp);
				}else{
					top.jboxTip(resp);
				}
			}, null, false);
		}else{
			expl.value = expl.getAttribute("preValue");
			jboxTip("评分不能大于"+num+"且只能是正整数或含有一位小数");
		}
		loadDate();
	}
	function saveScore4Expert(expl,num){
		var score = expl.value;
		var itemId = expl.getAttribute("itemId");
		var itemName = expl.getAttribute("itemName");
		var recordFlow1 = "${recordFlow}";
		var orgFlow1 = "${orgFlow}";
		var reg = /^\d+(\.\d)?$/;
		var reg1 = /^\d+(\.0)$/;
		if(score>=0 && score<=num && reg.test(score)){
			if(reg1.test(score)){
				var score1 = parseInt(score);
				expl.value = score1;
			}else {
				var score1 = score;
			}
			$(expl).attr("preValue",score1);
			var url = "<s:url value='/jsres/evaluation/saveExpertScore'/>";
			var data = {"itemId":itemId,"itemName":itemName,"speScore":score1,"recordFlow":recordFlow1,"orgFlow":orgFlow1};
			jboxPost(url, data, function(resp) {
				if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
					top.jboxTip(resp);
				}else{
					top.jboxTip(resp);
				}
			}, null, false);
			loadDate();
		}else{
			expl.value = expl.getAttribute("preValue");
			jboxTip("评分不能大于"+num+"且只能是正整数或含有一位小数");
		}
	}
	function uploadFile(itemId,itemName) {
		var url = "<s:url value='/jsres/evaluation/uploadFile'/>?itemId="+itemId+"&itemName="+encodeURIComponent(encodeURIComponent(itemName));
		typeName="医院评估附件";
		jboxOpen(url, "上传"+typeName, 500, 185);
	}
	function delFile(recordFlow,exp){
		jboxConfirm("确认移除该附件吗？",function () {
			var url = "<s:url value='/jsres/evaluation/removeFile?recordFlow='/>" + recordFlow;
			jboxGet(url,null,function(){
				$("#"+exp).attr({style:"display:none"});
				$("#"+exp).attr({style:"display:inline"});
				$("#"+exp).next().remove();
				$("#"+exp).remove();
			});
		});
	}
	//窗口加载后
	$(function(){
		//获取所有input标签
		var itemIdList = $("input");

		//为每个input赋值
		<c:forEach items="${jsresBaseEvaluationScoreList}" var="item" varStatus="status" >

			//获得值
			for(var i=0;i<itemIdList.length;i++){
				if(itemIdList[i].getAttribute("itemId")=="${item.itemId}"&&itemIdList[i].getAttribute("name")=="self"){
					itemIdList[i].value="${item.ownerScore}";
					$(itemIdList[i]).attr("preValue","${item.ownerScore}");
				}
				if(itemIdList[i].getAttribute("itemId")=="${item.itemId}"&&itemIdList[i].getAttribute("name")=="expert"){
					itemIdList[i].value="${item.speScore}";
					$(itemIdList[i]).attr("preValue","${item.speScore}");
				}
			}

			<%--获得其下标--%>
			<%--alert("${status.count}");--%>
		</c:forEach>
		loadDate("${jsresBaseEvaluation.speAllScore}");
		//获取所有name为file的td
		var fileList = $("td[name='file']");
		for(var i=0;i<fileList.length;i++){
			var itemId = fileList[i].getAttribute("itemId");
			var itemName = fileList[i].getAttribute("itemName");
			var id=itemId;
			id = id.replace(".", "-");
			id = id.replace(".", "-");
			<c:forEach items="${jsresBaseEvaluationFileList}" var="file" varStatus="status" >
				if(itemId=="${file.itemId}"){
					var delId="delFile_${file.recordFlow}";
					fileList[i].innerHTML+="<span class='titleName' title='${file.fileName}' id='"+delId+"'><a style=\"color: #459ae9;\" href='javascript:void(0);' onclick=\"downloadFile('${file.recordFlow}');\">${pdfn:cutString(file.fileName,4,true,3) }</a><button name='removeFileBtn' title='移除' style='background: white'  onclick=\"delFile('${file.recordFlow}','"+delId+"')\"><font color='red' size='3px;'>×</font></button></span><br>";

				}
			</c:forEach>
			var fileId="divFile_"+id;
			var html = "<a style=\"color: #459ae9;\" id='"+fileId+"' name='upLoadBtn' href=\"javascript:uploadFile('"+itemId+"','"+itemName+"');\">上传</a>";
			fileList[i].innerHTML+="<span id='"+fileId+"'></span>"
			fileList[i].innerHTML+=html;
		}
		//console.log(fileList);
		for(var i=0;i<itemIdList.length;i++){
			//权限判断（各级角色以及是否付费）
			if(${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope} ) {
				if(itemIdList[i].getAttribute("name")=="self"){
					itemIdList[i].readOnly="true";
					itemIdList[i].onchange =function(){};
				}
				if(!${type eq 'CountryOrg'}){
					if(itemIdList[i].getAttribute("name")=="expert"){
						itemIdList[i].readOnly="true";
						itemIdList[i].onchange =function(){};
					}
					$("#speContentInput").attr({readOnly:"true"});
				}
				$("button[name='removeFileBtn']").attr({style:"display:none"});
				$("a[name='upLoadBtn']").attr({style:"display:none"});
			}else if (${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}) {
				if(itemIdList[i].getAttribute("name")=="self"){
					itemIdList[i].readOnly="true";
					itemIdList[i].onchange =function(){};
				}
				$("button[name='removeFileBtn']").attr({style:"display:none"});
				$("a[name='upLoadBtn']").attr({style:"display:none"});
				if (!${sysCfg.cfgValue eq 'Y'} || ${type eq 'CountryOrg'}) {
					if(itemIdList[i].getAttribute("name")=="expert"){
						itemIdList[i].readOnly="true";
						itemIdList[i].onchange =function(){};
					}
					$("#speContentInput").attr({readOnly:"true"});
				}
			}else{
				if(itemIdList[i].getAttribute("name")=="expert"){
					itemIdList[i].readOnly="true";
					itemIdList[i].onchange =function(){};
				}
				$("#speContentInput").attr({readOnly:"true"});
				if(!${isCountry  eq 'Y' }){
					if (!${sysCfg.cfgValue eq 'Y'}||${seeFlag eq 'Y'}){
						if(itemIdList[i].getAttribute("name")=="self"){
							itemIdList[i].readOnly="true";
							itemIdList[i].onchange =function(){};
						}
						$("button[name='removeFileBtn']").attr({style:"display:none"});
						$("a[name='upLoadBtn']").attr({style:"display:none"});
					}
				}
			}
		}
	});
	function downloadFile(recordFlow){
		jboxTip("下载中…………");
		var url ="<s:url value='/jsres/evaluation/downloadFile?recordFlow='/>" + recordFlow;
		window.location.href = url;
	}
	//计算合计
	function loadDate(speAllExpScore) {
		var itemIdList = $("input");
		//自评合计
		var selfToal=0;
		//专家合计
		var expertToal=0;
		//自评附加分合计
		var selfAdditionalToal=0;
		//专家附加分合计
		var expertAdditionalToal=0;
		//自评总计
		var selfTotalled=0;
		//专家总计
		var expertTotalled=0;
		for(var i=0;i<itemIdList.length;i++){
			if(itemIdList[i].getAttribute("name")=="self"){
				selfTotalled=Number(selfTotalled)+Number(itemIdList[i].value);
			}
			if(itemIdList[i].getAttribute("name")=="expert"){
				expertTotalled=Number(expertTotalled)+Number(itemIdList[i].value);
			}
			if(itemIdList[i].getAttribute("selfAdd")=="selfAdd"){
				selfAdditionalToal=Number(selfAdditionalToal)+Number(itemIdList[i].value);
			}
			if(itemIdList[i].getAttribute("expertAdd")=="expertAdd"){
				expertAdditionalToal=Number(expertAdditionalToal)+Number(itemIdList[i].value);
			}
		}
		selfToal=Number(selfTotalled)-Number(selfAdditionalToal);
		expertToal=Number(expertTotalled)-Number(expertAdditionalToal);
		if(speAllExpScore!=null&&speAllExpScore!=""){
			expertTotalled = Number(speAllExpScore);
		}
		var reg1 = /^\d+(\.0)$/;
		$("#selfToal").text(check(selfToal.toFixed(1)));
		$("#selfAdditionalToal").text(check(selfAdditionalToal.toFixed(1)));
		$("#selfTotalled").text(check(selfTotalled.toFixed(1)));
		$("#expertToal").text(check(expertToal.toFixed(1)));
		$("#expertAdditionalToal").text(check(expertAdditionalToal.toFixed(1)));
		$("#expertTotalled").text(check(expertTotalled.toFixed(1)));
	}
	function check(exp){
		var reg1 = /^\d+(\.0)$/;
		if(reg1.test(exp)){
			return parseInt(exp);
		}else{
			return exp;
		}
	}

	$(function(){
		if(${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope} || ${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}){
			$("button[name='removeFileBtn']").attr({style:"display:none"});
			$("a[name='upLoadBtn']").attr({style:"display:none"});
		}
	});
	function saveSpeContent(expe,orgFlow){
		var speContent = expe.value;
		var url = "<s:url value='/jsres/evaluation/saveSpeContent'/>?orgFlow="+orgFlow+"&speContent="+encodeURIComponent(encodeURIComponent(speContent));
		jboxPost(url, null, function(resp) {
			if(resp == "${GlobalConstant.OPERATE_SUCCESSED}"){
				top.jboxTip(resp);
			}else{
				top.jboxTip(resp);
			}
		}, null, false);
	}
	function returnToBaseList(){
//		$(".tab_select a").click();
		if(${not empty page}){
			toPage(${page});
		}else{
			toPage(1);
		}
	}
</script>
</head>
<body>
<div class="div_table">
	<c:if test="${sessionScope.currUser.orgFlow != orgFlow}">
		<input type="button" class="btn_green" onclick="returnToBaseList()" value="返&nbsp;回" style="margin: 0px 5px 10px 860px;"/>
	</c:if>
	<table cellpadding="4">
		<tbody>
		<tr height="34" class="firstRow">
			<th colspan="10">
				<h2 style="font-size:150%">江苏省住院医师规范化培训基地评估指标（2019年版）</h2>
			</th>
		</tr>
		<tr height="28" >
			<th style="text-align:left;padding-left: 4px;"  height="28"  colspan="10">培训基地（医院）名称：${orgName}</th>
		</tr>
		<tr style="height:16px;">
			<th style="width: 285px;height: 16px;" colspan="3">评估项目</th>
			<th style="width: 196px;" rowspan="2">评估内容</th>
			<th style="width: 97px;" rowspan="2">现场评估<br>方式</th>
			<th style="width: 449px;" rowspan="2">评分标准</th>
			<th style="width: 44px;" rowspan="2">分值</th>
			<th style="width: 148px;" rowspan="2">附件信息</th>
			<th style="width: 72px;" rowspan="2">自评得分</th>
			<th style="width: 72px;" rowspan="2">专家评分</th>
		</tr>
		<tr style="height:32px">
			<th style="width: 88px;height: 32px;">一级指标</th>
			<th style="width: 92px;">二级指标</th>
			<th style="width: 105px;">三级指标<br> ★为核心指标</th>
		</tr>
		<tr style="height:50px">
			<th style="height: 438px;" rowspan="9">1.基本条件<br>（15分）</th>
			<td>1.1医院情况</td>
			<td>1.1.1培训基地基本条件★</td>
			<td>
				专业基地基本条件及建设情况
			</td>
			<td>查看医院相关材料(重点核查医疗机构执业许可证，医院及专业基地相关数据指标)</td>
			<td>
				1.符合标准，得3分<br>
				2.各专业基地1项不符合标准，得2分<br>
				3.各专业基地2项不符合标准，得1分<br>
				4.各专业基地3项及以上不符合标准，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2019-1.1.1" itemName="培训基地基本条件"></td>
			<td><input onchange="saveScore(this,3);" itemId="2019-1.1.1" value="${ownerScoreMap['2019-1.1.1']}" itemName="培训基地基本条件"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2019-1.1.1" itemName="培训基地基本条件"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:53px;">
			<td style="height:53px;" rowspan="2">1.2培训设施及信息系统</td>
			<td>1.2.1培训设施</td>
			<td>
				示教室、图书馆（包括电子图书）情况
			</td>
			<td rowspan="2">现场考查，查看相关资料</td>
			<td>
				1.各专业基地有满足培训需求的示教室，得0.2分；没有或不满足培训需求，不得分<br>
				2.有图书馆或阅览室，得0.1分；没有，不得分<br>
				3.图书种类齐全、数量满足培训需求，得0.1分；不满足培训需求，不得分<br>
				4.对住院医师开放使用,有借阅记录（或后台使用记录），得0.1分；不符合要求，不得分
			</td>
			<td style="text-align: center;">0.5</td>
			<td name="file" itemId="2019-1.2.1" itemName="培训设施" ></td>
			<td><input onchange="saveScore(this,0.5);" itemId="2019-1.2.1" itemName="培训设施"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,0.5);" itemId="2019-1.2.1" itemName="培训设施"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:53px;">
			<td>1.2.2信息系统</td>
			<td>
				网络信息管理平台及信息检索系统（含手机端）
			</td>
			<td>
				1.有用于住培管理的网络信息管理平台，且平台能满足培训需求，得0.3分；有平台但不满足培训需求，得0.2分；无平台，不得分<br>
				2.有可供住院医师学习使用的文献检索系统，且住院医师利用率较高，得0.2分；有系统，利用率不高，得0.1分；没有系统，不得分
			</td>
			<td style="text-align: center;">0.5</td>
			<td name="file" itemId="2019-1.2.2" itemName="信息系统" ></td>
			<td><input onchange="saveScore(this,0.5);" itemId="2019-1.2.2" itemName="信息系统"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,0.5);" itemId="2019-1.2.2" itemName="信息系统"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:39px;">
			<td style="height: 103px;" rowspan="3" align="center">1.3临床能力训练中心</td>
			<td>1.3.1设施设备</td>
			<td>
				建筑面积和训练设备配备情况
			</td>
			<td rowspan="3">1.现场考查，查看原始记录<br>2.访谈3名以上住院医师</td>
			<td>
				1.面积不低于600平方米（专科医院符合培训基地认定标准相关要求），且设施设备满足培训需求，得1分<br>
				2.不符合标准或不满足培训需求，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-1.3.1" itemName="设施设备"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-1.3.1" itemName="设施设备"  name="self"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-1.3.1" itemName="设施设备"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 32px;">1.3.2人员配备</td>
			<td>
				专职人员及专（兼）职指导医师
			</td>
			<td>
				1.有专人管理且有对应专业专（兼）职指导医师，通过相关的专业培训，满足培训需求，得1分<br>
				2.仅有专人管理或仅有对应专业专（兼）职指导医师，通过相关的专业培训，满足部分培训需求，得0.5分<br>
				3.仅有兼职人员管理或无对应专业专（兼）职指导医师，且不满足培训需求，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-1.3.2" itemName="人员配备"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-1.3.2" itemName="人员配备"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-1.3.2" itemName="人员配备"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 32px;">1.3.3管理情况</td>
			<td>
				管理制度（规定）、培训计划及工作实施情况
			</td>
			<td>
				1.有培训管理制度（规定）、项目培训标准，培训计划体现分层分级、符合专业特点，且有效落实，得2分<br>
				2.有培训管理制度（规定）、项目培训标准、培训计划，但计划制定不科学或未按计划实施，得1分<br>
				3.培训管理制度（规定）不健全，无培训计划或未落实，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-1.3.3" itemName="管理情况"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-1.3.3" itemName="管理情况"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-1.3.3" itemName="管理情况"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:64px">
			<td style="height: 64px;" rowspan="2">
				1.4全科医学科
			</td>
			<td>1.4.1学科建设★</td>
			<td>
				综合医院全科医学科设置及工作开展情况
			</td>
			<td>现场考查，查看相关资料(重点核查医疗机构执业许可证，全科医学科成立与科主任任命文件、开展培训、有培训对象以及内网业务工作流程)</td>
			<td>
				1.全科医学科独立设置，且有符合教学要求的全科门诊、全科病房，规范带教，得3分<br>
				2.独立设置全科门诊，并规范开展带教，得2分<br>
				3.仅有挂靠运行的全科门诊，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2019-1.4.1" itemName="学科建设"></td>
			<td><input onchange="saveScore(this,3);" itemId="2019-1.4.1" itemName="学科建设"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2019-1.4.1" itemName="学科建设"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:64px;">
			<td>1.4.2基层实践基地</td>
			<td>
				基本条件与管理情况
			</td>
			<td>现场考查，查看相关资料</td>
			<td>
				1.基层实践基地基本条件符合《住院医师规范化培训基地认定标准（试行）》，得0.5分；有1项不符合，不得分<br>
				2.培训基地每年至少对基层实践基地住院医师培训工作进行6次以上规范的指导与考核，得1分；组织5次，得0.5分；组织4次及以下，不得分<br>
				3.基层实践基地按照《住院医师规范化培训内容与标准（试行）》开展临床教学实践活动，得0.5分；不符合标准，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-1.4.2" itemName="基层实践基地"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-1.4.2" itemName="基层实践基地"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-1.4.2" itemName="基层实践基地"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:50px;">
			<td style="height: 57px;">1.5协同单位</td>
			<td>1.5.1协同单位建设★</td>
			<td>
				培训基地对协同单位的管理与指导协同单位培训工作情况
			</td>
			<td>
				1.查看原始资料<br>
				2.核实培训基地、协同单位住培管理人员<br>
				3.访谈指导医师和住院医师
			</td>
			<td>
				1.签订协议，明确培训基地与协同单位职责任务，培训基地负总责，协同单位在约定的有限专业、有限内容和有限时间内开展培训活动，
				培训基地对协同单位每季度至少开展1次以上的定期指导且认真规范，得2分；每年组织3次，得1分；每年组织2次及以下，不得分<br>
				2.协同单位独立招收住院医师或培训超过有限专业、有限内容和有限时间的，培训基地此项不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-1.5.1" itemName="协同单位建设"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-1.5.1" itemName="协同单位建设"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-1.5.1" itemName="协同单位建设"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:60px;">
			<th style="height: 438px;" rowspan="10">2.培训管理<br>（25分）</th>
			<td rowspan="3">2.1培训体系</td>
			<td>2.1.1培训基地★</td>
			<td>
				院领导履职及组织管理情况
			</td>
			<td>
				1.查看文件及相关资料<br>
				2.查看医院年度计划、半年总结、年终总结、院办公会记录或党委会记录等材料
			</td>
			<td>
				1.落实“一把手”负责制，医院领导班子每年至少组织2次专题会议及时研究并有效解决住培工作相关问题，得1分；每年组织1次，得0.5分；未组织，不得分<br>
				2.建立住培工作领导小组且履职到位，每季度有效组织开展活动≥1次，得1分；履职不到位或未开展活动，不得分<br>
				3.培训基地、职能管理部门、专业基地（轮转科室）三级管理机构健全，得0.5分；组织不健全，不得分<br>
				4.医院年度工作计划、年度工作总结有明确的住培工作内容，得0.5分；没有，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2019-2.1.1" itemName="培训基地"></td>
			<td><input onchange="saveScore(this,3);" itemId="2019-2.1.1" itemName="培训基地"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2019-2.1.1" itemName="培训基地"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 38px;">2.1.2职能管理部门</td>
			<td>
				职能管理部门设置与协调工作落实情况
			</td>
			<td>
				1.查看文件及相关资料，查看原始资料<br>
				2.访谈职能部门管理人员和财务、人事等部门管理人员
			</td>
			<td>
				1.住培职能管理部门职责明确，与其他相关职能部门密切协作，共同落实好住培管理责任，得0.5分；职责不明确或作用发挥不好，不得分<br>
				2.有胜任岗位的专职管理人员，在培住院医师（含在读临床、口腔硕士专业学位研究生，下同）人数＜200，专职管理人员不少于2人；200≤在培住院医师数＜500，
				专职管理人员与在培住院医师比例应为1:100；在培住院医师数≥500，专职管理人员5人以上。达到上述要求，得1分；达不到上述要求，专职管理人员少1人，得0.5分；专职管理人员少2人，不得分
			</td>
			<td style="text-align: center;">1.5</td>
			<td name="file" itemId="2019-2.1.2" itemName="职能管理部门"></td>
			<td><input onchange="saveScore(this,1.5);" itemId="2019-2.1.2" itemName="职能管理部门"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1.5);" itemId="2019-2.1.2" itemName="职能管理部门"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 38px;">2.1.3专业基地</td>
			<td>
				人员设置及组织管理情况
			</td>
			<td>查看文件及相关资料，访谈指导医师和住院医师</td>
			<td>
				1.专业基地设置本专业基地负责人、教学主任、教学秘书和教学小组，轮转科室设置教学主任、教学秘书和临床带教小组，组织健全，职责明确，并有效发挥作用，得1.5分<br>
				2.组织健全，职责明确但未有效发挥作用，得0.5分<br>
				3.组织不健全或职责不明确，不得分
			</td>
			<td style="text-align: center;">1.5</td>
			<td name="file" itemId="2019-2.1.2" itemName="专业基地"></td>
			<td><input onchange="saveScore(this,1.5);" itemId="2019-2.1.2" itemName="专业基地"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1.5);" itemId="2019-2.1.2" itemName="专业基地"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:35px;">
			<td rowspan="7">2.2制度与落实</td>
			<td>2.2.1招收管理★</td>
			<td>招收实施情况</td>
			<td>查看容量统计、上年度招收计划，核实上年度的各专业实际招收情况，查看住院医师花名册、近三年招收住院医师人员名册</td>
			<td>
				1.各专业基地容量测算符合要求，得2分；1个专业基地不符合要求，得1.5分；2个专业基地不符合要求，得1分；3个及以上专业基地不符合要求，不得分<br>
				2.各专业基地在培住院医师人数不超过培训容量，得1分；超过容量，不得分<br>
				3.每完成1个紧缺专业（全科、儿科、精神科、妇产科、麻醉科）招收任务，得0.5分；紧缺专业招收任务均未完成，不得分<br>
				4.招收外单位委派培训对象和面向社会招收培训对象占比≥60%,且有一定数量的应届本科毕业生,得1分；40%≤占比<60%，得0.5分；占比＜40%，不得分<br>
				5.培训基地招收简章明确住院医师培训期间待遇且与实际一致，得1.5分；不明确或不一致，不得分
			</td>
			<td style="text-align: center;">8</td>
			<td name="file" itemId="2019-2.2.1" itemName="招收管理"></td>
			<td><input onchange="saveScore(this,8);" itemId="2019-2.2.1" itemName="招收管理"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,8);" itemId="2019-2.2.1" itemName="招收管理"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:35px;">
			<td>2.2.2入院和入科教育</td>
			<td>内容及落实情况</td>
			<td>查看相关资料，访谈指导医师和住院医师，访谈有关人员</td>
			<td>
				1.入院教育规范实施，包括医院情况、职业道德、公共理论、培养计划与要求、人际沟通与团队合作、临床基础知识和基本技能训练与考核等内容，
				且有专人严格组织实施，得1分；不规范实施或未实施，不得分<br>
				2.入科教育规范实施，包括科室情况、工作流程、规章制度、培养计划与要求、临床基础知识和基本技能训练与考核等内容，培训与考核要求体现科室岗位基本需求特点，
				且有专人严格组织实施，得1分；不规范实施或未实施或不体现科室岗位基本需求特点，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-2.2.2" itemName="入院和入科教育"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-2.2.2" itemName="入院和入科教育"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-2.2.2" itemName="入院和入科教育"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:59px;">
			<td style="height: 59px;">2.2.3轮转管理★</td>
			<td>轮转计划制定及落实情况</td>
			<td>查看相关资料，访谈管理人员、指导医师和住院医师，现场核对在岗人员情况</td>
			<td>
				1.根据《住院医师规范化培训内容与标准（试行）》要求,职能管理部门会同专业基地制定科学合理的轮转计划，体现岗位胜任、分层递进的培训理念，且严格落实，得3分<br>
				2.职能管理部门会同专业基地制定轮转计划，且严格落实，得2分<br>
				3.职能管理部门统一制定轮转计划，且落实，得1分<br>
				4.查出1人没有按照轮转计划轮转，随意调整轮转计划或不轮转，或科室轮转不符合要求，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2019-2.2.3" itemName="轮转管理"></td>
			<td><input onchange="saveScore(this,3);" itemId="2019-2.2.3" itemName="轮转管理"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2019-2.2.3" itemName="轮转管理"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:38px;">
			<td style="height: 59px;">2.2.4考核管理★</td>
			<td>过程考核制度与落实情况</td>
			<td>
				1.查看文件及相关资料<br>
				2.抽查2～3个轮转科室相关资料<br>
				3.查看过程考核相关原始记录
			</td>
			<td>
				1.有考核管理规定，内容包括医德医风、临床职业素养、出勤情况、临床实践能力、培训指标完成情况和参加业务学习情况等方面，并严格落实，得1分；无管理规定或不规范，不得分<br>
				2.出科考核（理论与技能）落实情况好，体现专业特点和岗位胜任、分层递进的培训理念，得1分；未落实或不规范或不体现专业特点或不体现岗位胜任、分层递进的培训理念，不得分<br>
				3.年度考核（理论和技能）落实情况好，体现岗位胜任、分层递进的培训理念，得1分；未落实或不规范或不体现岗位胜任、分层递进的培训理念，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2019-2.2.4" itemName="考核管理"></td>
			<td><input onchange="saveScore(this,3);" itemId="2019-2.2.4" itemName="考核管理"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2019-2.2.4" itemName="考核管理"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 32px;">2.2.5院级督导★</td>
			<td>制度及实施情况</td>
			<td>查看原始资料，访谈住院医师和指导医师</td>
			<td>
				1.每年开展4次及以上院级督导，每次督导有目标、有组织、有计划、有内容、有结果且有整改的具体措施和落实效果，得2分；按要求组织3次，得1分<br>
				2.按要求组织2次及以下，不按要求组织，无结果运用或形式化或无效果，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-2.2.5" itemName="院级督导"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-2.2.5" itemName="院级督导"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-2.2.5" itemName="院级督导"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 32px;">2.2.6住培月度监测工作</td>
			<td>填报情况</td>
			<td>查看住培信息管理平台相关工作记录</td>
			<td>
				1.月度监测填报及时、准确，且由主要负责人审核，得0.5分<br>
				2.不按要求填报，不得分
			</td>
			<td style="text-align: center;">0.5</td>
			<td name="file" itemId="2019-2.2.6" itemName="住培月度监测工作"></td>
			<td><input onchange="saveScore(this,0.5);" itemId="2019-2.2.6" itemName="住培月度监测工作"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,0.5);" itemId="2019-2.2.6" itemName="住培月度监测工作"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 32px;">2.2.7沟通反馈</td>
			<td>顺畅性和实用性</td>
			<td>查看原始资料，访谈住院医师和指导医师</td>
			<td>
				1.有顺畅的沟通反馈机制，能及时掌握住院医师和指导医师的意见建议，相关记录完整，且能有效反馈和解决具体问题，得0.5分<br>
				2.无沟通反馈机制或沟通不畅，不得分
			</td>
			<td style="text-align: center;">0.5</td>
			<td name="file" itemId="2019-2.2.7" itemName="沟通反馈"></td>
			<td><input onchange="saveScore(this,0.5);" itemId="2019-2.2.7" itemName="沟通反馈"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,0.5);" itemId="2019-2.2.7" itemName="沟通反馈"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px;">
			<th style="height: 438px;" rowspan="7">3.师资管理<br>（15分）</th>
			<td style="height:136px;" rowspan="2">3.1管理规定</td>
			<td>3.1.1师资制度</td>
			<td>管理机制运行情况</td>
			<td rowspan="2">查看文件及相关资料，访谈指导医师和住院医师</td>
			<td>
				1.科学制定住培师资管理规定，有遴选、培训、聘任、考核、激励和退出机制，轮转科室按规定为每位住院医师配置1名指导医师，并严格落实，得2分<br>
				2.有管理规定，部分落实管理机制，得1分<br>
				3.无管理规定或未落实管理机制，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-3.1.1" itemName="师资制度"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-3.1.1" itemName="师资制度"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-3.1.1" itemName="师资制度"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px;">
			<td>3.1.2导师管理</td>
			<td>管理机制及运行情况</td>
			<td>
				1.科学制定住培导师管理规定，有遴选、培训、聘任、考核、激励和退出机制，为每位住院医师配置1名相对固定的指导医师作为导师，负责培训期间的全程指导，并联系紧密，得1分<br>
				2.无管理规定或未落实管理机制，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-3.1.2" itemName="导师管理"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-3.1.2" itemName="导师管理"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-3.1.2" itemName="导师管理"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:40px;">
			<td style="height:136px;" rowspan="2">3.2师资培训</td>
			<td style="height: 40px;">3.2.1院级培训</td>
			<td>
				师资参加院级培训情况
			</td>
			<td>查看培训资料、培训名单和证书</td>
			<td>
				1.制定和落实师资培训制度，每年有规范的培训计划，指导医师参加院级培训率100%，得1分<br>
				2.不符合上述要求，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-3.2.1" itemName="院级培训"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-3.2.1" itemName="院级培训"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-3.2.1" itemName="院级培训"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:32px;">
			<td style="height: 32px;">3.2.2省级及以上培训</td>
			<td>师资参加省级及以上培训情况</td>
			<td>查看培训名单、证书，核查专业覆盖率</td>
			<td>
				1.近3年内，每个专业基地负责人、教学主任、教学秘书，每个轮转科室至少1名以上骨干指导医师经过省级及以上的师资培训，得2分<br>
				2.有1个轮转科室少于1名，得1分<br>
				3.有2个及以上轮转科室少于1名，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-3.2.2" itemName="省级及以上培训"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-3.2.2" itemName="省级及以上培训"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-3.2.2" itemName="省级及以上培训"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:48px;">
			<td style="height:136px;" rowspan="2">3.3师资评价</td>
			<td>3.3.1带教评价</td>
			<td>
				对指导医师的评价机制
			</td>
			<td>查看原始资料，访谈住院医师</td>
			<td>
				1.建立住院医师对指导医师评价机制，指标设置科学，能反映指导医师的带教意识、能力、作风和效果，评价结果真实客观，
				有反馈和整改措施，且将测评结果纳入指导医师总体评价，得3分<br>
				2.有评价，有整改，但未将测评结果纳入指导医师总体评价，得2分<br>
				3.仅有评价或未开展评价，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2019-3.3.1" itemName="带教评价"></td>
			<td><input onchange="saveScore(this,3);" itemId="2019-3.3.1" itemName="带教评价"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2019-3.3.1" itemName="带教评价"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:64px;">
			<td style="height:64px;">3.3.2同等施教</td>
			<td>
				指导医师对住院医师同等施教
			</td>
			<td>组织三类住院医师进行现场访谈</td>
			<td>
				1.指导医师对外单位委派的住院医师、面向社会招收的住院医师与本院住院医师一视同仁，使其享受同等教学资源和培训机会，得2分<br>
				2.未同等施教，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-3.3.2" itemName="同等施教"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-3.3.2" itemName="同等施教"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-3.3.2" itemName="同等施教"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:48px;">
			<td style="height:136px;">3.4绩效考核</td>
			<td style="height: 48px;">3.4.1带教活动考核★</td>
			<td>
				带教活动占绩效考核情况
			</td>
			<td>查看相关制度、文件和会议纪要等原始资料，抽查2～3名指导医师座谈与访谈</td>
			<td>
				1.建立带教活动绩效管理制度，将带教活动与专业基地绩效考核挂钩，并在科室二次分配中将专业基地负责人、教学主任、
				教学秘书的教学管理活动和指导医师的带教活动，纳入个人绩效考核范围，且绩效考核不低于考核总分的8%，考核结果与技术职务晋升挂钩，得4分<br>
				2.绩效考核占考核总分的5%～8%，且考核结果与技术职务晋升挂钩，得2分<br>
				3.低于5%或不纳入或与晋升不挂钩或与晋升挂钩但激励力度过弱，不得分
			</td>
			<td style="text-align: center;">4</td>
			<td name="file" itemId="2019-3.4.1" itemName="带教活动考核" ></td>
			<td><input onchange="saveScore(this,4);" itemId="2019-3.4.1" itemName="带教活动考核"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,4);" itemId="2019-3.4.1" itemName="带教活动考核"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:48px;">
			<th style="height:96px;" rowspan="4">4.培训质量<br>（25分）</th>
			<td>4.1住院医师评价</td>
			<td>4.1.1综合评价</td>
			<td>实施及运用情况</td>
			<td>查看综合评价原始资料</td>
			<td>
				1.指导医师、科室护士、其他有关专业人员和管理人员对住院医师实施综合评价，指标设置科学，能反映住院医师的实际表现，且进行有效分析和结果的正确运用，得3分<br>
				2.有综合评价，指标设置欠科学，部分指标未能反映住院医师的实际表现，分析和运用不充分，得1分<br>
				3.未实施综合评价，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2019-4.1.1" itemName="综合评价"></td>
			<td><input onchange="saveScore(this,3);" itemId="2019-4.1.1" itemName="综合评价"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2019-4.1.1" itemName="综合评价"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:48px;">
			<td rowspan="2">4.2培训通过率</td>
			<td style="height: 48px;">4.2.1结业考核★</td>
			<td>住院医师首次参加结业考核的通过率</td>
			<td>查看结业考核成绩记录及相关材料</td>
			<td>
				1.通过率≥85%，得5分<br>
				2.通过率＜85%，但通过率≥本省（区、市）平均通过率，得3.5分；在平均通过率的基础上，每提高5个百分点，加0.5分，最多得5分；通过率＜本省（区、市）平均通过率，不得分<br>
				（通过率=上一年度首次参加结业考核通过的人数/上一年度首次参加结业考核总人数）
			</td>
			<td style="text-align: center;">5</td>
			<td name="file" itemId="2019-4.2.1" itemName="结业考核"></td>
			<td><input onchange="saveScore(this,5);" itemId="2019-4.2.1" itemName="结业考核"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,5);" itemId="2019-4.2.1" itemName="结业考核"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:48px;">
			<td style="height: 48px;">4.2.2执业医师资格考试</td>
			<td>住院医师首次参加执业医师资格考试的通过率</td>
			<td>查看考试成绩记录及相关材料</td>
			<td>
				1.通过率≥85%，得2分<br>
				2.通过率＜85%，但通过率≥本省（区、市）平均通过率，得1分；在平均通过率的基础上，每提高5个百分点，加0.5分，最多得2分；通过率＜本省（区、市）平均通过率，不得分<br>
				（通过率=上一年度首次参加考试通过的人数/上一年度首次参加考试总人数）
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-4.2.2" itemName="执业医师资格考试"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-4.2.2" itemName="执业医师资格考试"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-4.2.2" itemName="执业医师资格考试"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:39px;">
			<td>4.3专业基地培训质量</td>
			<td>4.3.1专业基地现场评估★</td>
			<td>专业基地质量控制</td>
			<td>计算受评专业基地“质量控制”部分的平均得分率（平均得分/35）</td>
			<td>
				受评专业基地“质量控制（总分35分）”项的平均得分率×15，为实际得分
			</td>
			<td style="text-align: center;">15</td>
			<td name="file" itemId="2019-4.3.1" itemName="专业基地现场评估"></td>
			<td><input onchange="saveScore(this,15);" itemId="2019-4.3.1" itemName="专业基地现场评估"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,15);" itemId="2019-4.3.1" itemName="专业基地现场评估"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:45px;">
			<th style="height:96px;" rowspan="10">5.保障措施<br>（20分）</th>
			<td rowspan="3">5.1专项经费</td>
			<td style="height: 45px;">5.1.1专账管理</td>
			<td>
				住培经费使用的规范性
			</td>
			<td>查看本年度财务报表等相关资料</td>
			<td>
				1.建立住培经费专项账户，规范使用中央（年人均3万元的经常性补助经费）、地方财政补助经费，得1分<br>
				2.有1项不符合要求，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-5.1.1" itemName="专账管理"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-5.1.1" itemName="专账管理"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-5.1.1" itemName="专账管理"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:45px;">
			<td style="height: 45px;">5.1.2教学补助★</td>
			<td>
				住培专项经费用于教学活动补助使用情况
			</td>
			<td>查看本年度财务报表，访谈有关人员</td>
			<td>
				1.落实上级财政补助经费用于培训基地教学实践活动，主要包括讲课、带教、教学管理等教学补助，有院内使用规定，专款专用，规范使用，无跨年度积压，得3分<br>
				2.有1项不符合要求，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2019-5.1.2" itemName="教学补助"></td>
			<td><input onchange="saveScore(this,3);" itemId="2019-5.1.2" itemName="教学补助"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2019-5.1.2" itemName="教学补助"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:45px">
			<td style="height: 45px;">5.1.3住院医师补助★</td>
			<td>
				住院医师收入情况
			</td>
			<td>查看财务部门提供本年度待遇发放流水单,抽查3～5名住院医师收入情况</td>
			<td>
				1.培训基地制定相关办法，明确规定不同学历、不同年资住院医师培训期间的收入水平，且有效落实，得3分；达不到要求，不得分<br>
				2.面向社会招收的培训对象生活补助标准参照培训基地同等条件住院医师工资水平确定，由培训基地依考核发放，得2分；达不到要求，不得分<br>
				3.委派单位发放的工资低于培训基地同等条件住院医师工资水平的部分，由培训基地按照本院同等条件住院医师工资水平依考核发放，得2分；达不到要求，不得分<br>
				（全部为面向社会招收的培训对象或外单位委派培训对象的培训基地，参照对应要求，符合标准，得4分）
			</td>
			<td style="text-align: center;">7</td>
			<td name="file" itemId="2019-5.1.3" itemName="住院医师补助"></td>
			<td><input onchange="saveScore(this,7);" itemId="2019-5.1.3" itemName="住院医师补助"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,7);" itemId="2019-5.1.3" itemName="住院医师补助"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:41px;">
			<td style="height: 96px;" rowspan="7">5.2相关措施</td>
			<td>5.2.1住宿或住宿补贴</td>
			<td>
				为住院医师提供住宿或住宿补贴情况
			</td>
			<td>现场考查，访谈住院医师，查看相关资料</td>
			<td>
				1.为住院医师提供免费或低收费住宿，或提供适当住宿补贴，得1分<br>
				2.以上均无，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-5.2.1" itemName="住宿或住宿补贴"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-5.2.1" itemName="住宿或住宿补贴"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-5.2.1" itemName="住宿或住宿补贴"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:55px;">
			<td >5.2.2专业基地绩效考核</td>
			<td>
				与过程考核、结业考核挂钩及落实情况
			</td>
			<td>查看相关制度、文件、会议纪要和实施情况相关资料</td>
			<td>
				1.将住培年度业务水平测试、首次执业医师资格考试通过率和结业考核结果与专业基地年度综合目标绩效考核紧密挂钩，且严格有效落实，得2分<br>
				2.有挂钩且落实，但挂钩比例少于年度综合目标绩效考核总分的10%，得1分<br>
				3.无，或未落实，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-5.2.2" itemName="专业基地绩效考核"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-5.2.2" itemName="专业基地绩效考核"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-5.2.2" itemName="专业基地绩效考核"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:55px;">
			<td>5.2.3签订协议</td>
			<td>
				培训基地与住院医师签订协议情况
			</td>
			<td>查看相关资料、协议原件，访谈住院医师</td>
			<td>
				1.培训基地与招收的住院医师按规定签订培训协议，约定有关事项；培训基地未聘用培训中和服务期内的外单位委派住院医师；
				未招收服务期内的农村订单定向免费培养医学毕业生参加非全科专业住培，或未聘用服务期或违约中的农村订单定向免费培养医学毕业生，得1分<br>
				2.不签订协议，或未落实，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-5.2.3" itemName="签订协议"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-5.2.3" itemName="签订协议"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-5.2.3" itemName="签订协议"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:55px;">
			<td>5.2.3签订协议</td>
			<td>
				培训基地与省级卫生行政部门签订培训基地责任书
			</td>
			<td>查看相关资料</td>
			<td>
				1.签订培训基地责任书，按规定将住院医师培训期间待遇等向社会公示，且严格落实责任书内容，得1分<br>
				2.未签订责任书或未向社会公示或未落实责任书内容，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-5.2.3-1" itemName="签订协议-1"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-5.2.3-1" itemName="签订协议-1"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-5.2.3-1" itemName="签订协议-1"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:55px;">
			<td>5.2.4资助参加社会保障</td>
			<td>
				面向社会招收的住院医师参加社会保险情况
			</td>
			<td>查看社会保障卡号进行核查，访谈住院医师</td>
			<td>
				1.培训基地资助面向社会招收的住院医师参加社会保险，得1分<br>
				2.未面向社会招收住院医师或未资助面向社会招收的住院医师参加社会保险，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-5.2.4" itemName="资助参加社会保障"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-5.2.4" itemName="资助参加社会保障"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-5.2.4" itemName="资助参加社会保障"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:55px;">
			<td>5.2.5激励机制</td>
			<td>
				对指导医师和住院医师教学双方积极性的提高情况
			</td>
			<td>查看相关制度、文件、实施情况等原始资料，抽查2～3名指导医师座谈与访谈</td>
			<td>
				1.积极开展评优树先活动，对优秀的指导医师予以奖励，提高指导医师教学工作积极性，得1分；未落实，不得分<br>
				2.积极开展评优树先活动，对优秀的住院医师予以奖励，提高住院医师培训学习积极性，得1分；未落实，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-5.2.5" itemName="激励机制"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-5.2.5" itemName="激励机制"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-5.2.5" itemName="激励机制"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:55px;">
			<td>5.2.6住培宣传</td>
			<td>
				工作开展情况
			</td>
			<td>查看相关制度、稿件发表记录等资料</td>
			<td>
				1.有宣传工作制度、通讯员，得0.5分；无，不得分<br>
				2.每年在主流媒体上至少发表2篇宣传稿件，得0.5分；无，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-5.2.6" itemName="住培宣传"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-5.2.6" itemName="住培宣传"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-5.2.6" itemName="住培宣传"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:40px">
			<td style="text-align: right;" colspan="6">小计</td>
			<td style="text-align: center;">100</td>
			<td>&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;</td>
			<td style="text-align: center;"><span id="selfToal"></span></td>
			<td style="text-align: center;"><span id="expertToal"></span></td>
		</tr>
		<tr style="height:56px;">
			<th rowspan="7">6.加分项目<br>（10分）</th>
			<td>6.1招收工作</td>
			<td>6.1.1紧缺专业招收工作落实情况</td>
			<td>
				创新工作办法，采取有力措施，超额完成全科、儿科、精神科、妇产科、麻醉科等紧缺专业招收计划
			</td>
			<td>查看上年度招收计划，核实上年度的紧缺专业实际招收情况，查看住院医师花名册</td>
			<td>
				培训基地招收人数在未超培训容量的前提下，<br>
				1.超额完成包括全科在内的3个及以上紧缺专业招收任务的，得3分<br>
				2.超额完成包括全科在内的2个紧缺专业招收任务，得2分<br>
				3.超额完成全科专业招收任务，得1分<br>
				4.未超额完成全科专业招收任务，不得分
			</td>
			<td style="text-align: center;">3</td>
			<td name="file" itemId="2019-6.1.1" itemName="紧缺专业招收工作落实情况"></td>
			<td><input onchange="saveScore(this,3);" itemId="2019-6.1.1" itemName="紧缺专业招收工作落实情况" selfAdd="selfAdd"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,3);" itemId="2019-6.1.1" itemName="紧缺专业招收工作落实情况" expertAdd="expertAdd"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:51px;">
			<td>6.2培训特色</td>
			<td>6.2.1培训工作特色</td>
			<td>
				课题研究及工作创新
			</td>
			<td>查看原始材料</td>
			<td>
				1.近一年有住院医师规范化培训相关课题研究或论文成果（省级及以上级别），培训工作有探索创新，或在全国大会交流发言，得1分<br>
				2.无，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-6.2.1" itemName="培训工作特色"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-6.2.1" itemName="培训工作特色" selfAdd="selfAdd"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-6.2.1" itemName="培训工作特色" expertAdd="expertAdd"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:84px;">
			<td>6.3社会贡献</td>
			<td>6.3.1参与国家住培相关工作</td>
			<td>
				参与国家住培政策研究、评估督导、结业考核题库建设、年度业务水平测试等工作情况
			</td>
			<td rowspan="2">查看相关证明材料</td>
			<td>
				1.均有或人次数超过10次，得2分<br>
				2.有其中两项参与或人次数超过5次，得1分<br>
				3.有其中一项参与，得0.5<br>
				4.无，不得分
			</td>
			<td style="text-align: center;">2</td>
			<td name="file" itemId="2019-6.3.1" itemName="参与国家住培相关工作"></td>
			<td><input onchange="saveScore(this,2);" itemId="2019-6.3.1" itemName="参与国家住培相关工作" selfAdd="selfAdd"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,2);" itemId="2019-6.3.1" itemName="参与国家住培相关工作" expertAdd="expertAdd"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:53px;">
			<td>6.4评优评先</td>
			<td>6.4.1获得国家或省级优秀称号</td>
			<td>获得国家或省级先进、优秀指导医师、优秀住院医师等称号</td>
			<td>
				1.有2个及以上优秀称号，得1分<br>
				2.有1个优秀称号，得0.5分<br>
				3.无，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-6.4.1" itemName="获得国家或省级优秀称号"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-6.4.1" itemName="获得国家或省级优秀称号" selfAdd="selfAdd"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-6.4.1" itemName="获得国家或省级优秀称号" expertAdd="expertAdd"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:57px;">
			<td rowspan="3">6.5对口帮扶</td>
			<td>6.5.1援疆援藏</td>
			<td>援疆援藏住院医师结业考核通过率</td>
			<td></td>
			<td>
				1.通过率≥本省（区、市）平均通过率，得1分<br>
				2.通过率＜本省（区、市）平均通过率，不得分
			</td>
			<td style="text-align: center;">1</td>
			<td name="file" itemId="2019-6.5.1" itemName="援疆援藏"></td>
			<td><input onchange="saveScore(this,1);" itemId="2019-6.5.1" itemName="援疆援藏" selfAdd="selfAdd"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1);" itemId="2019-6.5.1" itemName="援疆援藏" expertAdd="expertAdd"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:57px;">
			<td>6.5.2省域内帮扶</td>
			<td>开展对口支援，接收贫困县等欠发达地区住院医师参加培训</td>
			<td rowspan="2">查看相关记录和接收的住院医师名单及协议</td>
			<td>
				1.有，得0.5分<br>
				2.无，不得分
			</td>
			<td style="text-align: center;">0.5</td>
			<td name="file" itemId="2019-6.5.2" itemName="省域内帮扶"></td>
			<td><input onchange="saveScore(this,0.5);" itemId="2019-6.5.2" itemName="省域内帮扶" selfAdd="selfAdd"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,0.5);" itemId="2019-6.5.2" itemName="省域内帮扶" expertAdd="expertAdd"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:57px;">
			<td>6.5.3省域间帮扶</td>
			<td>开展对口支援，培训中西部特别是西部边疆民族地区住院医师或指导医师</td>
			<td>
				1.接收中西部特别是西部边疆民族地区住院医师参加规范化培训，得1分<br>
				2.接收中西部特别是西部边疆民族地区指导医师参加师资培训，得0.5分<br>
				3.无，不得分
			</td>
			<td style="text-align: center;">1.5</td>
			<td name="file" itemId="2019-6.5.3" itemName="省域间帮扶"></td>
			<td><input onchange="saveScore(this,1.5);" itemId="2019-6.5.3" itemName="省域间帮扶" selfAdd="selfAdd"  name="self"  class="input" type="text"  style="height:20px;width: 25px; text-align: center"  /></td>
			<td><input onchange="saveScore4Expert(this,1.5);" itemId="2019-6.5.3" itemName="省域间帮扶" expertAdd="expertAdd"  name="expert"  class="input" type="text"   style="height:20px;width: 25px; text-align: center"  /></td>
		</tr>
		<tr style="height:40px;">
			<td colspan="6" style="text-align: right;">小计</td>
			<td style="text-align: center;">10</td>
			<td>&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;</td>
			<td style="text-align: center;"><span id="selfAdditionalToal"></span></td>
			<td style="text-align: center;"><span id="expertAdditionalToal"></span></td>
		</tr>
		<tr style="height:40px;">
			<td colspan="6" style="text-align: right;">合计</td>
			<td style="text-align: center;">110</td>
			<td>&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;&mdash;</td>
			<td style="text-align: center;"><span id="selfTotalled"></span></td>
			<td style="text-align: center;"><span id="expertTotalled"></span></td>
		</tr>
		<tr style="height:16px;">
			<td rowspan="2" colspan="2">存在的主要问题<br>（请详细填写）：</td>
			<td rowspan="2" colspan="8">
				<textarea class="input" type="text" id="speContentInput" name="speContentInput" style="width: 96%;height: 50px;text-indent: 3px;" onchange="saveSpeContent(this,'${orgFlow}')">${jsresBaseEvaluation.speContent}</textarea>
			</td>
		</tr>
		<tr style="height:16px;">
		</tr>
		<tr>
			<td colspan="10">
				注：1.一级指标5项，二级指标16项，三级指标39项，共100分，其中核心指标13项，共61分；另有加分项10分，加分项单列，不计入评估分值。<br>
				&#12288;&#12288;2. 单个核心指标达标判定：单个核心指标得分率≥70%为达标，＜70%为不达标。<br>
				&#12288;&#12288;3.评定结论分为合格、基本合格、黄牌警告、红牌撤销四个等级，具体评定如下：（1）合格：评估分值≥85分，且核心指标达标数≥10个。
				（2）基本合格：70分≤评估分值＜85分，且核心指标达标数≥7个。（3）黄牌警告：60分≤评估分值＜70分，或4＜核心指标达标数≤6个。
				（4）红牌撤销：评估分值＜60分，或核心指标达标数≤4个。<br>
				&#12288;&#12288;4.分层递进的培训理念，是指根据培养对象的不同培训年限和能力素质要求，设置并实施递进上升、符合岗位胜任的阶段性培训目标，
				以达到培养独立、规范地承担相关专业常见病多发病诊疗工作的合格临床医师的总目标。<br>
				&#12288;&#12288;5.培训基地聘用委培单位服务期内或违约农村订单定向免费培养医学毕业生的，以及招收违约农村订单定向免费培养医学毕业生参加非全科专业住培的，每聘用或招收1名扣10分。<br>
				&#12288;&#12288;6.在培住院医师均含在读临床、口腔专业学位硕士研究生数。
			</td>
		</tr>
		</tbody>
	</table>
</div>
</body>
</html>