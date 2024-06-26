<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
</jsp:include>
	<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select2.js'/>?time=2"></script>
<style type="text/css">
	.xllist td{
		text-align: center;height: 35px;
	}
	.xllist{
		margin-top: 10px;
	}
	img{
		padding-right: 10px;
	}
</style>
<script type="text/javascript">
	var members=[];
	<c:forEach items="${members}" var="s">
	var d = {};
	d.id = "${s.userFlow}";
	d.text = "${s.userName}";
	var attrs=[];
	var attr={};
	attr.name="userCode";
	attr.value="${s.userCode}";
	attrs.push(attr);
	d.attrs = attrs;
	members.push(d);
	</c:forEach>
	var heads=[];
	<c:forEach items="${heads}" var="s">
	var d = {};
	d.id = "${s.userFlow}";
	d.text = "${s.userName}";
	var attrs=[];
	var attr={};
	attr.name="userCode";
	attr.value="${s.userCode}";
	attrs.push(attr);
	d.attrs = attrs;
	heads.push(d);
	</c:forEach>
	var secretarys=[];
	<c:forEach items="${secretarys}" var="s">
	var d = {};
	d.id = "${s.userFlow}";
	d.text = "${s.userName}";
	var attrs=[];
	var attr={};
	attr.name="userCode";
	attr.value="${s.userCode}";
	attrs.push(attr);
	d.attrs = attrs;
	secretarys.push(d);
	</c:forEach>
	var otherMembers=[];
	<c:forEach items="${otherMembers}" var="s">
	var d = {};
	d.id = "${s.userFlow}";
	d.text = "${s.userName}";
	var attrs=[];
	var attr={};
	attr.name="userCode";
	attr.value="${s.userCode}";
	attrs.push(attr);
	d.attrs = attrs;
	otherMembers.push(d);
	</c:forEach>

	function getuuid() {
		var s = [];
		var hexDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		for (var i = 0; i < 36; i++) {
			s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		}
		s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
		s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
		s[8] = s[13] = s[18] = s[23] = "-";

		var uuid = s.join("");
		return uuid;
	}
	function changeSTInfo()
	{
		var skillOrTheory="";
		$("input[name='skillOrTheory']:checked").each(function(i){
			if(i!=0)
				skillOrTheory+=","+$(this).val();
			else
				skillOrTheory+=$(this).val();
		});

		console.log(skillOrTheory);
		if(skillOrTheory!="")
		{
			if(skillOrTheory.indexOf("skill")>=0)
			{
				$("#skillExamTime").addClass("validate[required]");
				$("#skillExamTime").removeAttr("disabled");

				$("#GroupLeaderName").addClass("validate[required]");
				$("#GroupLeaderName").removeAttr("disabled");
				$("#addMember").show();

			}else{

				$("#skillExamTime").removeClass("validate[required]");
				$("#skillExamTime").attr("disabled","disabled");

				$("#GroupLeaderName").removeClass("validate[required]");
				$("#GroupLeaderName").attr("disabled","disabled");
				$("#addMember").hide();
				$("#MemberTable").html("");
				$("#skillExamTime").val("");
				$("#GroupLeaderName").val("");
				$("#GroupLeaderFlow").val("");
			}
			if(skillOrTheory.indexOf("theory")>=0)
			{
				$("#theoryExamTime").addClass("validate[required]");
				$("#theoryExamTime").removeAttr("disabled");

				$("#ChargeName").addClass("validate[required]");
				$("#ChargeName").removeAttr("disabled");
				$("#addInvigilator").show();

			}else{

				$("#theoryExamTime").removeClass("validate[required]");
				$("#theoryExamTime").attr("disabled","disabled");

				$("#ChargeName").removeClass("validate[required]");
				$("#ChargeName").attr("disabled","disabled");
				$("#addInvigilator").hide();
				$("#InvigilatorTable").html("");
				$("#theoryExamTime").val("");
				$("#ChargeName").val("");
				$("#ChargeFlow").val("");
			}
		}else{

			$("#theoryExamTime").removeClass("validate[required]");
			$("#theoryExamTime").attr("disabled","disabled");
			$("#ChargeName").removeClass("validate[required]");
			$("#ChargeName").attr("disabled","disabled");
			$("#addInvigilator").hide();
			$("#InvigilatorTable").html("");
			$("#theoryExamTime").val("");
			$("#ChargeName").val("");
			$("#ChargeFlow").val("");


			$("#skillExamTime").removeClass("validate[required]");
			$("#skillExamTime").attr("disabled","disabled");
			$("#GroupLeaderName").removeClass("validate[required]");
			$("#GroupLeaderName").attr("disabled","disabled");
			$("#addMember").hide();
			$("#MemberTable").html("");
			$("#skillExamTime").val("");
			$("#GroupLeaderName").val("");
			$("#GroupLeaderFlow").val("");
		}
	}
	function delUserItem(obj)
	{
        jboxConfirm("确认删除?", function () {
			$(obj).parent().parent().remove();
        });
	}
	function addUserItem(type)
	{
		var tableId=type+"Table";
		var id=getuuid();
		$("#"+tableId).append($("#"+type).clone());
		$addLastTr = $("#" + tableId).children("tr:last");
		$addLastTr.removeAttr("id");
		$addLastTr.find("input[name='examUserName']").attr("id",id+"Name");
		$addLastTr.find("input[name='examUserFlow']").attr("id", id+"Flow");
		initSelect(members,id+"Name", id+"Flow");
	}
	function addItem(type)
	{
		var tableId=type+"Table";
		var id=getuuid();
		$("#"+tableId).append($("#"+type).clone());
		$addLastTr = $("#" + tableId).children("tr:last");
		$addLastTr.removeAttr("id");
		$addLastTr.find("input[name='joinUserName']").attr("id",id+"Name");
		$addLastTr.find("input[name='joinUserFlow']").attr("id", id+"Flow");
		$addLastTr.find("input[name='userCode']").attr("id", id+"Code");
		if("${deptActivityItemTypeEnumDDAP.id}"==type)
		{
			initSelect(otherMembers, id + "Name", id + "Flow");
		}else {
			initSelect(members, id + "Name", id + "Flow", id + "Code");
		}
	}
	function initSelect(datas,nameId,id,codeId) {
		var itemSelectFuntion = function () {
			$("#"+id).val(this.id);
			if(codeId)
			{
				$("#"+codeId).val($(this).attr("usercode"));
			}
		};
		var attrFunction=null;
		if(codeId)
		{
			attrFunction= function(){
				var selectValue=$("#"+id).val();
				if(!selectValue)
					$("#"+codeId).val("");
			};
		}
		$.selectSuggest(nameId, datas, itemSelectFuntion, id, true,attrFunction);
	}

	function getDeptItems(tableId) {
		var datas=[];
		$("#"+tableId).find("tr").each(function(i){
			var itemFlow=$(this).find("input[name='itemFlow']").val()||'';
			var planTime=$(this).find("input[name='planTime']").val()||'';
			var joinUserFlow=$(this).find("input[name='joinUserFlow']").val()||'';
			var content=$(this).find("input[name='content']").val()||'';
			var address=$(this).find("input[name='address']").val()||'';
			var b={
				itemFlow:itemFlow,
				planTime:planTime,
				joinUserFlow:joinUserFlow,
				content:content,
				address:address
			};
			datas.push(b);
		});
		return datas;
	}
	function getScientificItems(tableId) {
		var datas=[];
		$("#"+tableId).find("tr").each(function(i){
			var itemFlow=$(this).find("input[name='itemFlow']").val()||'';
			var planTime=$(this).find("input[name='planTime']").val()||'';
			var joinUserFlow=$(this).find("input[name='joinUserFlow']").val()||'';
			var content=$(this).find("input[name='content']").val()||'';
			var address=$(this).find("input[name='address']").val()||'';
			var title=$(this).find("input[name='title']").val()||'';
			var b={
				itemFlow:itemFlow,
				planTime:planTime,
				joinUserFlow:joinUserFlow,
				content:content,
				address:address,
				title:title
			};
			datas.push(b);
		});
		return datas;
	}
	function save() {
		if(false==$("#editForm").validationEngine("validate")){
			return false;
		}
		//基本内容
		var bean={};
		var auditReason=$("#auditReason").val()||'';
		var planFlow=$("#planFlow").val()||'';
		var planTypeId=$("#planTypeId").val()||'';
		var deptFlow=$("#deptFlow").val()||'';
		var planDate=$("#planDate").val()||'';
		var skillOrTheory="";
		$("input[name='skillOrTheory']:checked").each(function(i){
			if(i!=0)
				skillOrTheory+=","+$(this).val();
			else
				skillOrTheory+=$(this).val();
		});
		//考核模块内容
		var ChargeName=$("#ChargeName").val()||'';
		var ChargeFlow=$("#ChargeFlow").val()||'';
		var GroupLeaderName=$("#GroupLeaderName").val()||'';
		var GroupLeaderFlow=$("#GroupLeaderFlow").val()||'';
		var skillExamTime=$("#skillExamTime").val()||'';
		var theoryExamTime=$("#theoryExamTime").val()||'';
		var planDemo=$("#planDemo").val()||'';
		var invigilators=[];
		var members=[];
		var jxcfs=[];
		var bltlaps=[];
		var xjkaps=[];
		var qthds=[];
		var dsbghaps=[];
		var jtbkaps=[];
		var tkaps=[];
		var ddaps=[];
		var sqthds=[];
		//教学查房安排
		jxcfs=getDeptItems("${deptActivityItemTypeEnumJXCFAP.id}Table");
		bltlaps=getDeptItems("${deptActivityItemTypeEnumBLTLAP.id}Table");
		xjkaps=getDeptItems("${deptActivityItemTypeEnumXJKAP.id}Table");
		qthds=getDeptItems("${deptActivityItemTypeEnumQTHD.id}Table");
		dsbghaps=getDeptItems("${deptActivityItemTypeEnumDSBGHAP.id}Table");
		if(planTypeId=="${deptActivityTypeEnumDept.id}")
		{
			<%--if(jxcfs.length==0)--%>
			<%--{--%>
				<%--jboxTip("请添加${deptActivityItemTypeEnumJXCFAP.name}!");--%>
				<%--return false;--%>
			<%--}--%>
			<%--if(bltlaps.length==0)--%>
			<%--{--%>
				<%--jboxTip("请添加${deptActivityItemTypeEnumBLTLAP.name}!");--%>
				<%--return false;--%>
			<%--}--%>
			<%--if(xjkaps.length==0)--%>
			<%--{--%>
				<%--jboxTip("请添加${deptActivityItemTypeEnumXJKAP.name}!");--%>
				<%--return false;--%>
			<%--}--%>
			<%--if(qthds.length==0)--%>
			<%--{--%>
				<%--jboxTip("请添加${deptActivityItemTypeEnumQTHD.name}!");--%>
				<%--return false;--%>
			<%--}--%>
			<%--if(dsbghaps.length==0)--%>
			<%--{--%>
				<%--jboxTip("请添加${deptActivityItemTypeEnumDSBGHAP.name}!");--%>
				<%--return false;--%>
			<%--}--%>
		}

		jtbkaps=getScientificItems("${deptActivityItemTypeEnumJTBKAP.id}Table");
		tkaps=getScientificItems("${deptActivityItemTypeEnumTKAP.id}Table");
		ddaps=getScientificItems("${deptActivityItemTypeEnumDDAP.id}Table");
		sqthds=getScientificItems("${deptActivityItemTypeEnumSQTHD.id}Table");
		if(planTypeId=="${deptActivityTypeEnumScientific.id}")
		{
			<%--if(jtbkaps.length==0)--%>
			<%--{--%>
				<%--jboxTip("请添加${deptActivityItemTypeEnumJTBKAP.name}!");--%>
				<%--return false;--%>
			<%--}--%>
			<%--if(tkaps.length==0)--%>
			<%--{--%>
				<%--jboxTip("请添加${deptActivityItemTypeEnumTKAP.name}!");--%>
				<%--return false;--%>
			<%--}--%>
			<%--if(ddaps.length==0)--%>
			<%--{--%>
				<%--jboxTip("请添加${deptActivityItemTypeEnumDDAP.name}!");--%>
				<%--return false;--%>
			<%--}--%>
			<%--if(sqthds.length==0)--%>
			<%--{--%>
				<%--jboxTip("请添加${deptActivityItemTypeEnumSQTHD.name}!");--%>
				<%--return false;--%>
			<%--}--%>
		}
//		if(skillOrTheory=="")
//		{
//			jboxTip("请选择出科考安排！");
//			return;
//		}
		//考核人
		var invigilatorId="${deptActivityUserTypeEnumInvigilator.id}Table";
		$("#"+invigilatorId).find("tr").each(function(i){
			var examFlow=$(this).find("input[name='examFlow']").val();
			var examUserName=$(this).find("input[name='examUserName']").val();
			var examUserFlow=$(this).find("input[name='examUserFlow']").val();
			var b={
				examFlow:examFlow,
				examUserName:examUserName,
				examUserFlow:examUserFlow
			};
			invigilators.push(b);
		});
		//成员
		var memberId="${deptActivityUserTypeEnumMember.id}Table";
		$("#"+memberId).find("tr").each(function(i){
			var examFlow=$(this).find("input[name='examFlow']").val();
			var examUserName=$(this).find("input[name='examUserName']").val();
			var examUserFlow=$(this).find("input[name='examUserFlow']").val();
			var b={
				examFlow:examFlow,
				examUserName:examUserName,
				examUserFlow:examUserFlow
			};
			members.push(b);
		});

		if(skillOrTheory.indexOf("theory")>=0&&invigilators.length==0)
		{
			jboxTip("请添加监考人！");
			return false;
		}
		if(skillOrTheory.indexOf("skill")>=0&&members.length==0)
		{
			jboxTip("请添加考核组成员！");
			return false;
		}
		bean.planFlow=planFlow;
		bean.auditReason=auditReason;
		bean.planTypeId=planTypeId;
		bean.deptFlow=deptFlow;
		bean.planDate=planDate;
		bean.skillOrTheory=skillOrTheory;
		//考核模块内容
		bean.chargeName=ChargeName;
		bean.chargeFlow=ChargeFlow;
		bean.groupLeaderName=GroupLeaderName;
		bean.groupLeaderFlow=GroupLeaderFlow;
		bean.skillExamTime=skillExamTime;
		bean.theoryExamTime=theoryExamTime;
		bean.invigilators=invigilators;
		bean.members=members;
		bean.planDemo=planDemo;
		bean.jxcfs=jxcfs;
		bean.bltlaps=bltlaps;
		bean.xjkaps=xjkaps;
		bean.qthds=qthds;
		bean.dsbghaps=dsbghaps;
		bean.jtbkaps=jtbkaps;
		bean.tkaps=tkaps;
		bean.ddaps=ddaps;
		bean.sqthds=sqthds;
		console.log(bean);

		var url = "<s:url value='/res/deptActivity/savePlan'/>";
		jboxPostJson(url, JSON.stringify(bean),
				function(resp){
					jboxTip(resp);
					if("${GlobalConstant.OPRE_SUCCESSED}" == resp){
						window.parent.frames['mainIframe'].window.toPage(1);
						jboxCloseMessager();
					}
				}, null, true);
	}
</script>
</head>
<body>
<div style="display: none;">
	<table>
		<tr id="${deptActivityItemTypeEnumJXCFAP.id}">
			<td>
				<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input  class="qtext validate[required]" name="joinUserName" type="text" autocomplete="off"/>
				<input  name="joinUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
				<input name="itemFlow"  class="input" type="text"  hidden style="margin-left: 0px;"/>
			</td>
			<td>
				<input type="text" name="userCode" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input type="text" name="content" class="qtext validate[required]"/>
			</td>
			<td>
				<input type="text" name="address" class="qtext validate[required]"/>
			</td>
			<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
		</tr>
		<tr id="${deptActivityItemTypeEnumBLTLAP.id}">
			<td>
				<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input  class="qtext validate[required]" name="joinUserName" type="text" autocomplete="off"/>
				<input  name="joinUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
				<input name="itemFlow"  class="input" type="text"  hidden style="margin-left: 0px;"/>
			</td>
			<td>
				<input type="text" name="userCode" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input type="text" name="content" class="qtext validate[required]"/>
			</td>
			<td>
				<input type="text" name="address" class="qtext validate[required]"/>
			</td>
			<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
		</tr>
		<tr id="${deptActivityItemTypeEnumXJKAP.id}">
			<td>
				<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input  class="qtext validate[required]" name="joinUserName" type="text" autocomplete="off"/>
				<input  name="joinUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
				<input name="itemFlow"  class="input" type="text"  hidden style="margin-left: 0px;"/>
			</td>
			<td>
				<input type="text" name="userCode" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input type="text" name="content" class="qtext validate[required]"/>
			</td>
			<td>
				<input type="text" name="address" class="qtext validate[required]"/>
			</td>
			<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
		</tr>
		<tr id="${deptActivityItemTypeEnumQTHD.id}">
			<td>
				<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input  class="qtext validate[required]" name="joinUserName" type="text" autocomplete="off"/>
				<input  name="joinUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
				<input name="itemFlow"  class="input" type="text"  hidden style="margin-left: 0px;"/>
			</td>
			<td>
				<input type="text" name="userCode" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input type="text" name="content" class="qtext validate[required]"/>
			</td>
			<td>
				<input type="text" name="address" class="qtext validate[required]"/>
			</td>
			<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
		</tr>
		<tr id="${deptActivityItemTypeEnumDSBGHAP.id}">
			<td>
				<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input  class="qtext validate[required]" name="joinUserName" type="text" autocomplete="off"/>
				<input  name="joinUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
				<input name="itemFlow"  class="input" type="text"  hidden style="margin-left: 0px;"/>
			</td>
			<td>
				<input type="text" name="userCode" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input type="text" name="content" class="qtext validate[required]"/>
			</td>
			<td>
				<input type="text" name="address" class="qtext validate[required]"/>
			</td>
			<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
		</tr>
		<tr id="${deptActivityItemTypeEnumJTBKAP.id}">
			<td>
				<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input  class="qtext validate[required]" name="joinUserName" type="text" autocomplete="off"/>
				<input  name="joinUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
				<input name="itemFlow"  class="input" type="text"  hidden style="margin-left: 0px;"/>
			</td>
			<td>
				<input type="text" name="userCode" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td colspan="2">
				<input type="text" name="content" class="qtext validate[required]"/>
			</td>
			<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
		</tr>
		<tr id="${deptActivityItemTypeEnumTKAP.id}">
			<td>
				<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input  class="qtext validate[required]" name="joinUserName" type="text" autocomplete="off"/>
				<input  name="joinUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
				<input name="itemFlow"  class="input" type="text"  hidden style="margin-left: 0px;"/>
			</td>
			<td>
				<input type="text" name="userCode" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input type="text" name="content" class="qtext validate[required]"/>
			</td>
			<td>
				<input type="text" name="title" class="qtext validate[required]"/>
			</td>
			<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
		</tr>
		<tr id="${deptActivityItemTypeEnumDDAP.id}">

			<td>
				<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input type="text" name="content" class="qtext validate[required]"/>
			</td>
			<td>
				<input  class="qtext validate[required]" name="joinUserName" type="text" autocomplete="off"/>
				<input  name="joinUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
				<input name="itemFlow"  class="input" type="text"  hidden style="margin-left: 0px;"/>
			</td>
			<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
		</tr>
		<tr id="${deptActivityItemTypeEnumSQTHD.id}">
			<td>
				<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td>
				<input  class="qtext validate[required]" name="joinUserName" type="text" autocomplete="off"/>
				<input  name="joinUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
				<input name="itemFlow"  class="input" type="text"  hidden style="margin-left: 0px;"/>
			</td>
			<td>
				<input type="text" name="userCode" class="qtext validate[required]" readonly="readonly"/>
			</td>
			<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
		</tr>
	</table>
</div>
<div class="infoAudit" style="overflow: auto;height: 500px;">
	<form id="editForm" style="position: relative;" method="post">
		<div id="infoDiv" class="div_table" style="padding-top: 5px;">
			<input type="hidden" id="planFlow" name="planFlow" value="${plan.planFlow }"/>
			<input type="hidden" id="auditReason" name="auditReason" value="${plan.auditReason }"/>
			<input type="hidden" id="planTypeId" name="planTypeId" value="${planTypeId }"/>
			<input type="hidden" id="planDate" name="planDate" value="${planDate }"/>
			<input type="hidden" id="deptFlow" name="deptFlow" value="${deptFlow }"/>
			<c:if test="${planTypeId eq deptActivityTypeEnumDept.id}">
			<table class="xllist" style="padding-top: 0px;">
				<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 20px;">${dept.deptName}${planTypeName}${planDate}教学工作安排计划表</th>
					</tr>
					<c:if test="${not empty heads}" >
						<c:forEach items="${heads}" var="user" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<td rowspan="${fn:length(heads)}">教学主任</td>
									<td>
										${user.userName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty heads}" >

						<tr>
							<td>教学主任</td>
							<td>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty secretarys}" >
						<c:forEach items="${secretarys}" var="user" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<td rowspan="${fn:length(secretarys)}">教学秘书</td>
									<td>
										${user.userName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty secretarys}" >
						<tr>
							<td>教学秘书</td>
							<td>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="6" style="text-align: center;font-size: 15px;">
							教学查房安排（2周/次）带组主任负责
						</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th>内容</th>
						<th>地点</th>
						<th style="width:35px;"><img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;" onclick="addItem('${deptActivityItemTypeEnumJXCFAP.id}');"/></th>
					</tr>
				</tbody>
				<tbody id="${deptActivityItemTypeEnumJXCFAP.id}Table">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumJXCFAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">

							<tr>
								<td>
									<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" value="${b.planTime}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input id="${deptActivityItemTypeEnumJXCFAP.id}Name${s.index}" class="qtext validate[required]" name="joinUserName" value="${userMap[b.joinUserFlow].userName}" type="text" autocomplete="off"/>
									<input id="${deptActivityItemTypeEnumJXCFAP.id}Flow${s.index}" name="joinUserFlow" value="${b.joinUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									<script>
										initSelect(members,"${deptActivityItemTypeEnumJXCFAP.id}Name${s.index}","${deptActivityItemTypeEnumJXCFAP.id}Flow${s.index}","${deptActivityItemTypeEnumJXCFAP.id}Code${s.index}");
									</script>
									<input name="itemFlow" value="${b.itemFlow}"  class="input" type="text"  hidden style="margin-left: 0px;"/>
								</td>
								<td>
									<input type="text" id="${deptActivityItemTypeEnumJXCFAP.id}Code${s.index}" name="userCode" value="${userMap[b.joinUserFlow].userCode}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input type="text" name="content" value="${b.content}" class="qtext validate[required]"/>
								</td>
								<td>
									<input type="text" name="address" value="${b.address}" class="qtext validate[required]"/>
								</td>
								<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<c:if test="${empty list and empty plan}" >
					<script>
						addItem('${deptActivityItemTypeEnumJXCFAP.id}');
					</script>
				</c:if>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="6" style="text-align: center;font-size: 15px;">
							病例讨论安排（2周/次）高级职称主持
						</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th>内容</th>
						<th>地点</th>
						<th style="width:35px;"><img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;" onclick="addItem('${deptActivityItemTypeEnumBLTLAP.id}');"/></th>
					</tr>
				</tbody>
				<tbody id="${deptActivityItemTypeEnumBLTLAP.id}Table">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumBLTLAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">

							<tr>
								<td>
									<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" value="${b.planTime}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input id="${deptActivityItemTypeEnumBLTLAP.id}Name${s.index}" class="qtext validate[required]" name="joinUserName" value="${userMap[b.joinUserFlow].userName}" type="text" autocomplete="off"/>
									<input id="${deptActivityItemTypeEnumBLTLAP.id}Flow${s.index}" name="joinUserFlow" value="${b.joinUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									<script>
										initSelect(members,"${deptActivityItemTypeEnumBLTLAP.id}Name${s.index}","${deptActivityItemTypeEnumBLTLAP.id}Flow${s.index}","${deptActivityItemTypeEnumBLTLAP.id}Code${s.index}");
									</script>
									<input name="itemFlow" value="${b.itemFlow}"  class="input" type="text"  hidden style="margin-left: 0px;"/>
								</td>
								<td>
									<input type="text" id="${deptActivityItemTypeEnumBLTLAP.id}Code${s.index}" name="userCode" value="${userMap[b.joinUserFlow].userCode}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input type="text" name="content" value="${b.content}" class="qtext validate[required]"/>
								</td>
								<td>
									<input type="text" name="address" value="${b.address}" class="qtext validate[required]"/>
								</td>
								<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<c:if test="${empty list and empty plan}" >
					<script>
						addItem('${deptActivityItemTypeEnumBLTLAP.id}');
					</script>
				</c:if>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="6" style="text-align: center;font-size: 15px;">
							小讲课安排（2周/次）主治/住院医师负责
						</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th>内容</th>
						<th>地点</th>
						<th style="width:35px;"><img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;" onclick="addItem('${deptActivityItemTypeEnumXJKAP.id}');"/></th>
					</tr>
				</tbody>
				<tbody id="${deptActivityItemTypeEnumXJKAP.id}Table">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumXJKAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">

							<tr>
								<td>
									<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" value="${b.planTime}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input id="${deptActivityItemTypeEnumXJKAP.id}Name${s.index}" class="qtext validate[required]" name="joinUserName" value="${userMap[b.joinUserFlow].userName}" type="text" autocomplete="off"/>
									<input id="${deptActivityItemTypeEnumXJKAP.id}Flow${s.index}" name="joinUserFlow" value="${b.joinUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									<script>
										initSelect(members,"${deptActivityItemTypeEnumXJKAP.id}Name${s.index}","${deptActivityItemTypeEnumXJKAP.id}Flow${s.index}","${deptActivityItemTypeEnumXJKAP.id}Code${s.index}");
									</script>
									<input name="itemFlow" value="${b.itemFlow}"  class="input" type="text"  hidden style="margin-left: 0px;"/>
								</td>
								<td>
									<input type="text" id="${deptActivityItemTypeEnumXJKAP.id}Code${s.index}" name="userCode" value="${userMap[b.joinUserFlow].userCode}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input type="text" name="content" value="${b.content}" class="qtext validate[required]"/>
								</td>
								<td>
									<input type="text" name="address" value="${b.address}" class="qtext validate[required]"/>
								</td>
								<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<c:if test="${empty list and empty plan}" >
					<script>
						addItem('${deptActivityItemTypeEnumXJKAP.id}');
					</script>
				</c:if>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="6" style="text-align: center;font-size: 15px;">
							其他活动
						</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th>内容</th>
						<th>地点</th>
						<th style="width:35px;"><img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;" onclick="addItem('${deptActivityItemTypeEnumQTHD.id}');"/></th>
					</tr>
				</tbody>
				<tbody id="${deptActivityItemTypeEnumQTHD.id}Table">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumQTHD.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
									<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" value="${b.planTime}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input id="${deptActivityItemTypeEnumQTHD.id}Name${s.index}" class="qtext validate[required]" name="joinUserName" value="${userMap[b.joinUserFlow].userName}" type="text" autocomplete="off"/>
									<input id="${deptActivityItemTypeEnumQTHD.id}Flow${s.index}" name="joinUserFlow" value="${b.joinUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									<script>
										initSelect(members,"${deptActivityItemTypeEnumQTHD.id}Name${s.index}","${deptActivityItemTypeEnumQTHD.id}Flow${s.index}","${deptActivityItemTypeEnumQTHD.id}Code${s.index}");
									</script>
									<input name="itemFlow" value="${b.itemFlow}"  class="input" type="text"  hidden style="margin-left: 0px;"/>
								</td>
								<td>
									<input type="text" id="${deptActivityItemTypeEnumQTHD.id}Code${s.index}" name="userCode" value="${userMap[b.joinUserFlow].userCode}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input type="text" name="content" value="${b.content}" class="qtext validate[required]"/>
								</td>
								<td>
									<input type="text" name="address" value="${b.address}" class="qtext validate[required]"/>
								</td>
								<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<c:if test="${empty list and empty plan}" >
					<script>
						addItem('${deptActivityItemTypeEnumQTHD.id}');
					</script>
				</c:if>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="6" style="text-align: center;font-size: 15px;">
							读书报告会安排（1月/次）
						</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th>内容</th>
						<th>地点</th>
						<th style="width:35px;"><img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;" onclick="addItem('${deptActivityItemTypeEnumDSBGHAP.id}');"/></th>
					</tr>
				</tbody>
				<tbody id="${deptActivityItemTypeEnumDSBGHAP.id}Table">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumDSBGHAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
									<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" value="${b.planTime}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input id="${deptActivityItemTypeEnumDSBGHAP.id}Name${s.index}" class="qtext validate[required]" name="joinUserName" value="${userMap[b.joinUserFlow].userName}" type="text" autocomplete="off"/>
									<input id="${deptActivityItemTypeEnumDSBGHAP.id}Flow${s.index}" name="joinUserFlow" value="${b.joinUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									<script>
										initSelect(members,"${deptActivityItemTypeEnumDSBGHAP.id}Name${s.index}","${deptActivityItemTypeEnumDSBGHAP.id}Flow${s.index}","${deptActivityItemTypeEnumDSBGHAP.id}Code${s.index}");
									</script>
									<input name="itemFlow" value="${b.itemFlow}"  class="input" type="text"  hidden style="margin-left: 0px;"/>
								</td>
								<td>
									<input type="text" id="${deptActivityItemTypeEnumDSBGHAP.id}Code${s.index}" name="userCode" value="${userMap[b.joinUserFlow].userCode}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input type="text" name="content" value="${b.content}" class="qtext validate[required]"/>
								</td>
								<td>
									<input type="text" name="address" value="${b.address}" class="qtext validate[required]"/>
								</td>
								<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<c:if test="${empty list and empty plan}" >
					<script>
						addItem('${deptActivityItemTypeEnumDSBGHAP.id}');
					</script>
				</c:if>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">出科考核安排（加地点）</th>
					</tr>
				</tbody>
					<tr>
						<th style="width: 150px;">理论考（系统考就无此项）<input type="checkbox" name="skillOrTheory" onchange="changeSTInfo();" value="theory" ${fn:indexOf(plan.skillOrTheory,'theory' )>=0?'checked':''} /> </th>
						<td style="width: 200px;"><input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="theoryExamTime" id="theoryExamTime" value="${plan.theoryExamTime}" class="qtext" readonly="readonly"/></td>
						<th style="width: 150px;">负责人</th>
						<td style="width: 200px;">
							<input id="ChargeName" class="qtext" name="ChargeName" type="text"
								   value="${examInfoMap[deptActivityUserTypeEnumCharge.id][0].examUserName}" autocomplete="off"/>
							<input id="ChargeFlow" name="ChargeFlow" class="input" value="${examInfoMap[deptActivityUserTypeEnumCharge.id][0].examUserFlow}" type="text"
								   hidden style="margin-left: 0px;"/>
							<script>
								initSelect(members,"ChargeName","ChargeFlow");
							</script>
						</td>
						<td>监考人<img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;" id="addInvigilator" onclick="addUserItem('${deptActivityUserTypeEnumInvigilator.id}');"/>
							<br/>15人以上2人监考</td>
					</tr>
				<tbody id="${deptActivityUserTypeEnumInvigilator.id}Table">
					<c:set var="list" value="${examInfoMap[deptActivityUserTypeEnumInvigilator.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
								<tr>
									<th style="width: 150px;"><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></th>
									<td colspan="4"  style="text-align: left;padding-left: 25px;">
										<input id="InvigilatorName${s.index}" class="qtext validate[required]" name="examUserName" value="${b.examUserName}" type="text" autocomplete="off"/>
										<input id="InvigilatorFlow${s.index}" name="examUserFlow" value="${b.examUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
										<script>
											initSelect(members,"InvigilatorName${s.index}","InvigilatorrFlow${s.index}");
										</script>
										<input name="examFlow" value="${b.examFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									</td>
								</tr>
						</c:forEach>
					</c:if>
				</tbody>
					<tr>
						<th style="width: 150px;">操作考<input type="checkbox" name="skillOrTheory" onchange="changeSTInfo();" value="skill" ${fn:indexOf(plan.skillOrTheory,'skill' )>=0?'checked':''} /></th>
						<td style="width: 200px;"><input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="skillExamTime" id="skillExamTime" value="${plan.skillExamTime}" class="qtext validate[required]" readonly="readonly"/></td>
						<th style="width: 150px;">组长</th>
						<td>
							<input id="GroupLeaderName" class="qtext validate[required]" name="GroupLeaderName" type="text"
								   value="${examInfoMap[deptActivityUserTypeEnumGroupLeader.id][0].examUserName}" autocomplete="off"/>
							<input id="GroupLeaderFlow" name="GroupLeaderFlow" class="input" value="${examInfoMap[deptActivityUserTypeEnumGroupLeader.id][0].examUserFlow}" type="text"
								   hidden style="margin-left: 0px;"/>
							<script>
								initSelect(members,"GroupLeaderName","GroupLeaderFlow");
							</script>
						</td>
						<td>
							考核组成员<img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" id="addMember" style="cursor: pointer;" onclick="addUserItem('${deptActivityUserTypeEnumMember.id}');"/>
						</td>
					</tr>

				<tbody id="${deptActivityUserTypeEnumMember.id}Table">
				<c:set var="list" value="${examInfoMap[deptActivityUserTypeEnumMember.id]}"></c:set>
					<c:if test="${not empty list}" >
							<c:forEach items="${list}" var="b" varStatus="s">
								<tr>
									<th style="width: 150px;"><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></th>
									<td colspan="4"  style="text-align: left;padding-left: 25px;">
										<input id="MemberName${s.index}" class="qtext validate[required]" name="examUserName" value="${b.examUserName}" type="text" autocomplete="off"/>
										<input id="MemberFlow${s.index}" name="examUserFlow" value="${b.examUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
										<script>
											initSelect(members,"MemberName${s.index}","MemberFlow${s.index}");
										</script>
										<input name="examFlow" value="${b.examFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									</td>
								</tr>
							</c:forEach>
					</c:if>
				</tbody>
				<tr>
					<td>备注</td>
					<td colspan="4"><textarea  style="resize:none;width:678px;margin-left: 25px;border:1px solid #bdbebe;	height:100px;margin:5px 5px 5px 0px" id="planDemo" maxlength="500">${plan.planDemo}</textarea> </td>
				</tr>
			</table>
			</c:if>
			<c:if test="${planTypeId eq deptActivityTypeEnumScientific.id}">
				<table class="xllist" style="padding-top: 0px;">
				<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 20px;">${dept.deptName}${planTypeName}${planDate}教学工作安排计划表</th>
					</tr>
					<c:if test="${not empty heads}" >
						<c:forEach items="${heads}" var="user" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<td rowspan="${fn:length(heads)}">教学主任</td>
									<td>
										${user.userName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty heads}" >

						<tr>
							<td>教学主任</td>
							<td>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty secretarys}" >
						<c:forEach items="${secretarys}" var="user" varStatus="s">
							<c:if test="${s.first}">
								<tr>
									<td rowspan="${fn:length(secretarys)}">教学秘书</td>
									<td>
										${user.userName}
									</td>
								</tr>
							</c:if>
							<c:if test="${!s.first}">
								<tr>
									<td>
											${user.userName}
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:if>
					<c:if test="${empty secretarys}" >
						<tr>
							<th>教学秘书</th>
							<td>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="6" style="text-align: center;font-size: 15px;">
							集体备课安排（月/次）
						</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th colspan="2">内容</th>
						<th style="width:35px;"><img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;" onclick="addItem('${deptActivityItemTypeEnumJTBKAP.id}');"/></th>
					</tr>
				</tbody>
				<tbody id="${deptActivityItemTypeEnumJTBKAP.id}Table">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumJTBKAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
									<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" value="${b.planTime}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input id="${deptActivityItemTypeEnumJTBKAP.id}Name${s.index}" class="qtext validate[required]" name="joinUserName" value="${userMap[b.joinUserFlow].userName}" type="text" autocomplete="off"/>
									<input id="${deptActivityItemTypeEnumJTBKAP.id}Flow${s.index}" name="joinUserFlow" value="${b.joinUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									<script>
										initSelect(members,"${deptActivityItemTypeEnumJTBKAP.id}Name${s.index}","${deptActivityItemTypeEnumJTBKAP.id}Flow${s.index}","${deptActivityItemTypeEnumJTBKAP.id}Code${s.index}");
									</script>
									<input name="itemFlow" value="${b.itemFlow}"  class="input" type="text"  hidden style="margin-left: 0px;"/>
								</td>
								<td>
									<input type="text" id="${deptActivityItemTypeEnumJTBKAP.id}Code${s.index}" name="userCode" value="${userMap[b.joinUserFlow].userCode}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td colspan="2">
									<input type="text" name="content" value="${b.content}" class="qtext validate[required]"/>
								</td>
								<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<c:if test="${empty list and empty plan}" >
					<script>
						addItem('${deptActivityItemTypeEnumJTBKAP.id}');
					</script>
				</c:if>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="6" style="text-align: center;font-size: 15px;">
							听课安排（月/次）

						</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>听课人</th>
						<th>工号</th>
						<th>主讲人</th>
						<th>授课题目</th>
						<th style="width:35px;"><img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;" onclick="addItem('${deptActivityItemTypeEnumTKAP.id}');"/></th>
					</tr>
				</tbody>
				<tbody id="${deptActivityItemTypeEnumTKAP.id}Table">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumTKAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
									<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" value="${b.planTime}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input id="${deptActivityItemTypeEnumTKAP.id}Name${s.index}" class="qtext validate[required]" name="joinUserName" value="${userMap[b.joinUserFlow].userName}" type="text" autocomplete="off"/>
									<input id="${deptActivityItemTypeEnumTKAP.id}Flow${s.index}" name="joinUserFlow" value="${b.joinUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									<script>
										initSelect(members,"${deptActivityItemTypeEnumTKAP.id}Name${s.index}","${deptActivityItemTypeEnumTKAP.id}Flow${s.index}","${deptActivityItemTypeEnumTKAP.id}Code${s.index}");
									</script>
									<input name="itemFlow" value="${b.itemFlow}"  class="input" type="text"  hidden style="margin-left: 0px;"/>
								</td>
								<td>
									<input type="text" id="${deptActivityItemTypeEnumTKAP.id}Code${s.index}" name="userCode" value="${userMap[b.joinUserFlow].userCode}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input type="text" name="content" value="${b.content}" class="qtext validate[required]"/>
								</td>
								<td>
									<input type="text" name="title" value="${b.title}" class="qtext validate[required]"/>
								</td>
								<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<c:if test="${empty list and empty plan}" >
					<script>
						addItem('${deptActivityItemTypeEnumTKAP.id}');
					</script>
				</c:if>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="6" style="text-align: center;font-size: 15px;">
							督导安排（月/次）教学秘书和主任各一次，交叉${planTypeName}
						</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>督导内容</th>
						<th>督导人</th>
						<th style="width:35px;"><img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;" onclick="addItem('${deptActivityItemTypeEnumDDAP.id}');"/></th>
					</tr>
				</tbody>
				<tbody id="${deptActivityItemTypeEnumDDAP.id}Table">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumDDAP.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
									<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" value="${b.planTime}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input type="text" name="content" value="${b.content}" class="qtext validate[required]"/>
								</td>
								<td>
									<input id="${deptActivityItemTypeEnumDDAP.id}Name${s.index}" class="qtext validate[required]" name="joinUserName" value="${userMap[b.joinUserFlow].userName}" type="text" autocomplete="off"/>
									<input id="${deptActivityItemTypeEnumDDAP.id}Flow${s.index}" name="joinUserFlow" value="${b.joinUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									<script>
										initSelect(otherMembers,"${deptActivityItemTypeEnumDDAP.id}Name${s.index}","${deptActivityItemTypeEnumDDAP.id}Flow${s.index}");
									</script>
									<input name="itemFlow" value="${b.itemFlow}"  class="input" type="text"  hidden style="margin-left: 0px;"/>
								</td>
								<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<c:if test="${empty list and empty plan}" >
					<script>
						addItem('${deptActivityItemTypeEnumDDAP.id}');
					</script>
				</c:if>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="6" style="text-align: center;font-size: 15px;">
							其他活动

						</th>
					</tr>
					<tr>
						<th>日期</th>
						<th>主持人</th>
						<th>工号</th>
						<th style="width:35px;"><img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;" onclick="addItem('${deptActivityItemTypeEnumSQTHD.id}');"/></th>
					</tr>
				</tbody>
				<tbody id="${deptActivityItemTypeEnumSQTHD.id}Table">
					<c:set var="list" value="${itemMap[deptActivityItemTypeEnumSQTHD.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<td>
									<input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="planTime" value="${b.planTime}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td>
									<input id="${deptActivityItemTypeEnumTKAP.id}Name${s.index}" class="qtext validate[required]" name="joinUserName" value="${userMap[b.joinUserFlow].userName}" type="text" autocomplete="off"/>
									<input id="${deptActivityItemTypeEnumTKAP.id}Flow${s.index}" name="joinUserFlow" value="${b.joinUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									<script>
										initSelect(members,"${deptActivityItemTypeEnumTKAP.id}Name${s.index}","${deptActivityItemTypeEnumTKAP.id}Flow${s.index}","${deptActivityItemTypeEnumTKAP.id}Code${s.index}");
									</script>
									<input name="itemFlow" value="${b.itemFlow}"  class="input" type="text"  hidden style="margin-left: 0px;"/>
								</td>
								<td>
									<input type="text" id="${deptActivityItemTypeEnumTKAP.id}Code${s.index}" name="userCode" value="${userMap[b.joinUserFlow].userCode}" class="qtext validate[required]" readonly="readonly"/>
								</td>
								<td><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
				<c:if test="${empty list and empty plan}" >
					<script>
						addItem('${deptActivityItemTypeEnumSQTHD.id}');
					</script>
				</c:if>
			</table>
			<table class="xllist">
				<tbody>
					<tr>
						<th colspan="5" style="text-align: center;font-size: 15px;">${planTypeName}考核安排</th>
					</tr>
				</tbody>
				<tr>
					<th style="width: 150px;">理论考（系统考就无此项）<input type="checkbox" name="skillOrTheory" onchange="changeSTInfo();" value="theory" ${fn:indexOf(plan.skillOrTheory,'theory' )>=0?'checked':''} /></th>
					<td style="width: 200px;"><input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="theoryExamTime" id="theoryExamTime" value="${plan.theoryExamTime}" class="qtext" readonly="readonly"/></td>
					<th style="width: 150px;">负责人</th>
					<td style="width: 200px;">
						<input id="ChargeName" class="qtext" name="ChargeName" type="text"
							   value="${examInfoMap[deptActivityUserTypeEnumCharge.id][0].examUserName}" autocomplete="off"/>
						<input id="ChargeFlow" name="ChargeFlow" class="input" value="${examInfoMap[deptActivityUserTypeEnumCharge.id][0].examUserFlow}" type="text"
							   hidden style="margin-left: 0px;"/>
						<script>
							initSelect(members,"ChargeName","ChargeFlow");
						</script>
					</td>
					<td>监考人<img  src="<s:url value="/css/skin/Blue/images/add3.png"/>" style="cursor: pointer;"  id="addInvigilator" onclick="addUserItem('${deptActivityUserTypeEnumInvigilator.id}');"/>
						<br/>15人以上2人监考</td>
				</tr>
				<tbody id="${deptActivityUserTypeEnumInvigilator.id}Table">
					<c:set var="list" value="${examInfoMap[deptActivityUserTypeEnumInvigilator.id]}"></c:set>
					<c:if test="${not empty list}" >
						<c:forEach items="${list}" var="b" varStatus="s">
							<tr>
								<th style="width: 150px;"><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></th>
								<td colspan="4"  style="text-align: left;padding-left: 25px;">

									<input id="InvigilatorName${s.index}" class="qtext validate[required]" name="examUserName" value="${b.examUserName}" type="text" autocomplete="off"/>
									<input id="InvigilatorFlow${s.index}" name="examUserFlow" value="${b.examUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									<script>
										initSelect(members,"InvigilatorName${s.index}","InvigilatorrFlow${s.index}");
									</script>
									<input name="examFlow" value="${b.examFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					</tbody>
					<tr>
						<th style="width: 150px;">操作考<input type="checkbox" name="skillOrTheory" onchange="changeSTInfo();" value="skill" ${fn:indexOf(plan.skillOrTheory,'skill' )>=0?'checked':''} /></th>
						<td style="width: 200px;"><input type="text"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="skillExamTime" id="skillExamTime" value="${plan.skillExamTime}" class="qtext validate[required]" readonly="readonly"/></td>
						<th style="width: 150px;">组长</th>
						<td >
							<input id="GroupLeaderName" class="qtext validate[required]" name="GroupLeaderName" type="text"
								   value="${examInfoMap[deptActivityUserTypeEnumGroupLeader.id][0].examUserName}" autocomplete="off"/>
							<input id="GroupLeaderFlow" name="GroupLeaderFlow" class="input" value="${examInfoMap[deptActivityUserTypeEnumGroupLeader.id][0].examUserFlow}" type="text"
								   hidden style="margin-left: 0px;"/>
							<script>
								initSelect(members,"GroupLeaderName","GroupLeaderFlow");
							</script>
						</td>
						<td>
							考核组成员<img  src="<s:url value="/css/skin/Blue/images/add3.png"/>"  id="addMember" style="cursor: pointer;" onclick="addUserItem('${deptActivityUserTypeEnumMember.id}');"/>
						</td>
					</tr>
				<tbody id="${deptActivityUserTypeEnumMember.id}Table">
					<c:set var="list" value="${examInfoMap[deptActivityUserTypeEnumMember.id]}"></c:set>
					<c:if test="${not empty list}" >
							<c:forEach items="${list}" var="b" varStatus="s">
								<tr>
									<th style="width: 150px;"><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></th>
									<td colspan="4"  style="text-align: left;padding-left: 25px;">

										<input id="MemberName${s.index}" class="qtext validate[required]" name="examUserName" value="${b.examUserName}" type="text" autocomplete="off"/>
										<input id="MemberFlow${s.index}" name="examUserFlow" value="${b.examUserFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
										<script>
											initSelect(members,"MemberName${s.index}","MemberFlow${s.index}");
										</script>
										<input name="examFlow" value="${b.examFlow}" class="input" type="text"  hidden style="margin-left: 0px;"/>
									</td>
								</tr>
							</c:forEach>
					</c:if>
				</tbody>
				<tr>
					<td>备注</td>
					<td colspan="4"><textarea  style="resize:none;width:678px;margin-left: 25px;border:1px solid #bdbebe;	height:100px;margin:5px 5px 5px 0px" id="planDemo" maxlength="500">${plan.planDemo}</textarea> </td>
				</tr>
			</table>
			</c:if>

			<div class="button">
				<input type="button" class="search" onclick="save();" value="保&#12288;存"/>&#12288;
				<input type="button" class="search" onclick="jboxCloseMessager();" value="关&#12288;闭"/>
			</div>
		</div>
	</form>
</div>
<div style="display: none;">
	<select class="qselect" id="members" >
		<c:forEach items="${members}" var="s">
			<option id="${s.userFlow}" value="${s.userFlow}" userCode="${s.userCode}">${s.userName}</option>
		</c:forEach>
	</select>
	<select class="qselect" id="deptHeadUsers" >
		<c:forEach items="${heads}" var="s">
			<option id="${s.userFlow}" value="${s.userFlow}" userCode="${s.userCode}">${s.userName}</option>
		</c:forEach>
	</select>
	<select class="qselect" id="deptSecretaryUsers" >
		<c:forEach items="${secretarys}" var="s">
			<option id="${s.userFlow}" value="${s.userFlow}" userCode="${s.userCode}">${s.userName}</option>
		</c:forEach>
	</select>
	<select class="qselect" id="otherMembers" >
		<c:forEach items="${otherMembers}" var="s">
			<option id="${s.userFlow}" value="${s.userFlow}" userCode="${s.userCode}">${s.userName}</option>
		</c:forEach>
	</select>
	<div>
		<table>
			<tr id="${deptActivityUserTypeEnumMember.id}">
				<th style="width: 150px;"><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></th>
				<td colspan="4" style="text-align: left;padding-left: 25px;">
					<input  class="qtext validate[required]" name="examUserName" type="text" autocomplete="off"/>
					<input  name="examUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
					<input name="examFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
				</td>
			</tr>
			<tr id="${deptActivityUserTypeEnumInvigilator.id}">
				<th style="width: 150px;"><img  src="<s:url value="/css/skin/Blue/images/del1.png"/>" style="cursor: pointer;" onclick="delUserItem(this);"/></th>
				<td colspan="4" style="text-align: left;padding-left: 25px;">
					<input  class="qtext validate[required]" name="examUserName" type="text" autocomplete="off"/>
					<input  name="examUserFlow" class="input" type="text"  hidden style="margin-left: 0px;"/>
					<input name="examFlow"  class="input" type="text"  hidden style="margin-left: 0px;"/>
				</td>
			</tr>
		</table>
	</div>
</div>
<script>
	$(function(){
		changeSTInfo();
	});
</script>
</body>
</html>