<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
</jsp:include>
<style>
	.base_info th,.base_info td{height:45px;}
	.input{height:30px;}
</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
	$(document).ready(function(){
		<c:if test="${isMakeUp eq 'Y' && doctor.trainingSpeId eq '3'}">
			<c:if test="${makeUpTypeId eq '1'}">
				$(".skillTr").hide();
			</c:if>
			<c:if test="${makeUpTypeId eq '2'}">
        		$(".theoryTr").hide();
			</c:if>
		</c:if>
		<c:if test="${param.role eq 'doctor'}">
		$(".buttonDiv").hide();
		window.print();
		</c:if>
	});


	function uploadImage(){
		$.ajaxFileUpload({
			url:"<s:url value='/sys/user/userHeadImgUpload'/>?userFlow=${user.userFlow}",
			secureuri:false,
			fileElementId:'imageFile',
			dataType: 'json',
			success: function (data, status){
				if(data.indexOf("success")==-1){
					jboxTip(data);
				}else{
					jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
					var arr = new Array();
					arr = data.split(":");
					$("#userImg").val(arr[1]);
					var url = "${sysCfgMap['upload_base_url']}/"+ arr[1];
					$("#showImg").attr("src",url);
					$("#headimgurl").val(arr[1]);
				}
			},
			error: function (data, status, e){
				jboxTip('${GlobalConstant.UPLOAD_FAIL}');
			},
			complete:function(){
				$("#imageFile").val("");
			}
		});
	}

	function printTicket(){
		$(".buttonDiv").hide();
		window.print();
		$(".buttonDiv").show();
	}

	function saveTicket(){
		jboxConfirm("确认保存？",function(){
			jboxPost("<s:url value='/sczyres/manage/saveTicket'/>",$("#submitForm").serialize(),function(resp){
				if(resp==-1){
					jboxTip("准考证重复");
				}else if(resp==1){
					jboxTip("操作成功");
					window.parent.search();
					jboxClose();
				}
			},null,false)
		})
	}
	function changeText(obj){
		var val = $(obj).val();
		var id = $(obj).attr("name");
		$("#"+id).text(val);
	}
</script>
<body style="overflow: auto">
<div class="main_bd" style="padding: 15px;">
	<h1 style="text-align: center;font-size: 20px;margin-bottom: 10px;line-height: 35px;">
		<c:if test="${doctor.trainingSpeId eq '1' || doctor.trainingSpeId eq '2'}">四川省${sysCfgMap['res_graduation_year']}年中医住院医师规范化培训结业考核临床实践能力考核<br/></c:if>
		<c:if test="${doctor.trainingSpeId eq '3'}">四川省${sysCfgMap['res_graduation_year']}年中医类别助理全科医生培训结业考核<br/></c:if>
		准&#12288;考&#12288;证
	</h1>
	<form id="submitForm" style="position: relative">
	<input type="hidden" name="recordFlow" value="${ticket.recordFlow}">
	<input type="hidden" name="doctorFlow" value="${empty user?ticket.doctorFlow:user.userFlow}">
	<input type="hidden" name="applyFlow" value="${applyFlow}">
	<table border="0" cellpadding="0" cellspacing="0" class="base_info">
		<colgroup>
			<col width="20%"/>
			<col width="60%"/>
			<col width="15%"/>
		</colgroup>
		<tbody>
		<tr>
			<td>姓&#12288;&#12288;名：</th>
			<td><input type='text' class='input validate[required]' name="doctorName"
					  style="width: 120px;" value="${empty user?ticket.doctorName:user.userName}"/>
			（准考证编号：<input type="text" class="input validate[required]" name="ticketNumber" value="${ticket.ticketNumber}"/>）
			</td>
			<td rowspan="4">
				<div style="text-align: center"><img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="110px"
													 height="130px" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/></div>
				<input type="hidden" id="headimgurl" value="${user.userHeadImg}"/>
				<div style="text-align: center">（盖章）</div>
			</td>
		</tr>
		<tr>
			<td>身份证号：</td>
			<td>
				<input name="idNo" class='input validate[required]' type="text" value="${empty user?ticket.idNo:user.idNo}"/>
			</td>
		</tr>
		<tr class="skillTr">
			<td>
				<c:if test="${doctor.trainingSpeId eq '1' || doctor.trainingSpeId eq '2'}">临床实践能力考核时间：</c:if>
				<c:if test="${doctor.trainingSpeId eq '3'}">&nbsp;临床实践能力考核时间：</c:if>
			</td>
			<td>
				<input style="width: 298px;" type='text' class='input validate[required]' name="skillExamDate" value="${ticket.skillExamDate}"/>
			</td>
		</tr>
		<tr class="skillTr">
			<td><c:if test="${doctor.trainingSpeId eq '1' || doctor.trainingSpeId eq '2'}">考&#12288;点：</c:if>
				<c:if test="${doctor.trainingSpeId eq '3'}">技能考点：</c:if>
			</td>
			<td>
				<input type='text' style="width: 400px;" class='input validate[required]' name="orgName" value="${ticket.orgName}" onchange="changeText(this)"/>
			</td>
		</tr>
		<tr class="skillTr">
			<td><c:if test="${doctor.trainingSpeId eq '1' || doctor.trainingSpeId eq '2'}">考核地址：</c:if>
				<c:if test="${doctor.trainingSpeId eq '3'}">技能考核地址：</c:if>
			</td>
			<td colspan="2">
				<textarea  name="orgAddress" class="validate[required,maxSize[250]]" rows="3" cols="20" style="height: 80px;">${ticket.orgAddress}</textarea>
			</td>
		</tr>
		<c:if test="${doctor.trainingSpeId eq '3'}">
			<tr class="theoryTr">
				<td>理论考核时间：
				</td>
				<td>
					<input style="width: 298px;" type='text' class='input validate[required]' name="theoryExamDate" value="${ticket.theoryExamDate}"/>
				</td>
			</tr>
			<tr class="theoryTr">
				<td>理论考点：
				</td>
				<td>
					<input type='text' style="width: 400px;" class='input validate[required]' name="theoryOrgName" value="${ticket.theoryOrgName}" onchange="changeText(this)"/>
				</td>
			</tr>
			<tr class="theoryTr">
				<td>理论考核地址：
				</td>
				<td colspan="2">
					<textarea  name="theoryOrgAddress" class="validate[required,maxSize[250]]" rows="3" cols="20" style="height: 80px;">${ticket.theoryOrgAddress}</textarea>
				</td>
			</tr>
		</c:if>
		</tbody>
	</table>
	<h1 style="text-align: center;font-size: 15px;line-height: 40px;">
		考生须知
	</h1>
	<h4 style="line-height: 25px;">
		1、考生下载准考证后，请仔细核对身份证等信息，信息有误，请及时联系四川省中医药毕业后教育委员会办公室；请将右上角照片盖培训基地（或协同单位）公章。<br/>
		2、应考人员应提前30分钟凭准考证和二代身份证入场，考核开始15分钟后，考生不得入场。<br/>
	</h4>
	<c:if test="${doctor.trainingSpeId eq '1' || doctor.trainingSpeId eq '2'}">
		3、本次考核按准考证号分段进行考核，具体安排如下：
	<span id="orgName">${ticket.orgName}</span>考点
	<table border="0" cellpadding="0" cellspacing="0" class="base_info">
		<tr>
			<td style="width: 50%">临床实践能力考核时间</td>
			<td style="width: 50%">考核批次</td>
		</tr>
		<tr>
			<td><input style="width: 298px;" type='text' class='input validate[required]' name="skillExamTime1" value="${ticket.skillExamTime1}"/></td>
			<td><textarea style="height: 55px;" class="validate[required]" name="skillExamTime1Barch">${ticket.skillExamTime1Barch}</textarea></td>
		</tr>
		<tr>
			<td><input style="width: 298px;" type='text' class='input validate[required]' name="skillExamTime2" value="${ticket.skillExamTime2}"/></td>
			<td><textarea style="height: 55px;" class="validate[required]" name="skillExamTime2Barch">${ticket.skillExamTime2Barch}</textarea></td>
		</tr>
		<tr>
			<td><input style="width: 298px;" type='text' class='input validate[required]' name="theoryExamTime1" value="${ticket.theoryExamTime1}"/></td>
			<td><textarea style="height: 55px;" class="validate[required]" name="theoryExamTime1Barch">${ticket.theoryExamTime1Barch}</textarea></td>
		</tr>
	</table>
	</form>
		<h4 style="line-height: 25px;">
		4、考核中，严格遵守考场纪律，严禁将通讯工具、书本等带进考场。<br/>
		5、考核结束后请快速离开考场。
		</h4>
	</c:if>
	<c:if test="${doctor.trainingSpeId eq '3'}">
		<h4 style="line-height: 25px;">
			3、考核中，严格遵守考场纪律，严禁将通讯工具、书本等带进考场。<br/>
			4、考核结束后请快速离开考场。
		</h4>
	</c:if>
	<div style="margin-top: 10px;">
	<span style="font-size: 15px;font-weight: bold">	请使用A4纸打印，彩色或黑白打印均可！	</span>
	<span style="float: right;font-size: 15px;font-weight: bold">四川省中医药毕业后教育委员会办公室&#12288;制</span>
	</div>
</div>
<div style="text-align: center" class="buttonDiv">
	<input type="button" value="保&#12288;存" onclick="saveTicket();" class="btn_blue">
	<input type="button" value="打&#12288;印" onclick="printTicket();" class="btn_blue">
</div>
</body>

