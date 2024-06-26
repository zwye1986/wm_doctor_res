<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	.label td{
		width:120px;height:35px;text-align:center;border:1px solid #E3E3E3;
	}
	.label td.on{background-color:#4195C5;color:#fff;}
</style>
<script type="text/javascript">
	$(function(){
		toPage1(1);
	});
	/*******************************预约学员信息***********************************/
	function toPage1(page){
		$("#currentPage1").val(page);
		jboxPostLoad("appointDiv","<s:url value="/study/hospital/detailList"/>",$("#appointForm").serialize(),true);
	}
	function checkAll(){
		if($("#checkAll").attr("checked")){
			$(".check").attr("checked",true);
		}else{
			$(".check").attr("checked",false);
		}
	}
	function checkSingel(obj){
		if(!$(obj).attr("checked")){
			$("#checkAll").attr("checked",false);
		}else{
			var checkAllLen = $("input[type='checkbox'][class='check']").length;
			var checkLen = $("input[type='checkbox'][class='check']:checked").length;
			if(checkAllLen == checkLen){
				$("#checkAll").attr("checked",true);
			}
		}
	}
	function auditOpt(){
		var checkLen = $(":checkbox[class='check']:checked").length;
		if(checkLen == 0){
			jboxTip("请勾选预约学员信息！");
			return;
		}else{
			var len = 0;
			$(":checkbox[class='check']:checked").each(function(){
				if($(this).attr("statusId") != "Passing"){
					len ++;
				}
			});
			if(len > 0){
				jboxTip("只能审核待审核状态的记录！");
				return;
			}
		}
		var recordLst = [];
		$(":checkbox[class='check']:checked").each(function(){
			recordLst.push(this.value);
		});
		jboxButtonConfirm("所勾选学员预约信息是否通过？","通过","不通过", function(){//通过
			var url = "<s:url value='/study/hospital/auditPassed'/>";
			jboxPostJson(url,JSON.stringify(recordLst), function (resp) {
				setTimeout(function(){
					toPage1(1);
				},1000);
			}, null, true);
		},function(){//不通过
			var url = "<s:url value='/study/hospital/auditUnPassed'/>";
			jboxPostJson(url,JSON.stringify(recordLst), function (resp) {
				setTimeout(function(){
					toPage1(1);
				},1000);
			}, null, true);
		},300);
	}
	function expAppoint(){
		var url = "<s:url value='/study/hospital/exportDetailList'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#appointForm"), url, null, null, false);
		jboxEndLoading();
	}
	function backOpt(){
		var checkLen = $(":checkbox[class='check']:checked").length;
		if(checkLen == 0){
			jboxTip("请勾选预约学员信息！");
			return;
		}
		var recordLst = [];
		$(":checkbox[class='check']:checked").each(function(){
			recordLst.push(this.value);
		});
		jboxConfirm("确认撤销成待审核状态？",function() {//通过
			var url = "<s:url value='/study/hospital/auditBack'/>";
			jboxPostJson(url,JSON.stringify(recordLst), function (resp) {
				setTimeout(function(){
					toPage1(1);
				},1000);
			}, null, true);
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div style="margin:20px 0px 10px;">
			<table class="label">
				<tr>
					<td class="on">预约学员信息</td>
				</tr>
			</table>
		</div>
		<div class="labelDiv" style="border: 1px solid #bbbbbb;min-height: 400px;">
			<div style="font:16px bold;padding-top: 10px;">&#12288;课程名称：${subject.subjectName}</div>
			<form id="appointForm" >
				<input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
				<input id="currentPage1" type="hidden" name="currentPage1" value="1"/>
				<table class="basic" style="width:100%;border:0px;margin:10px 0px;">
					<tr>
						<td style="border:0px;">
							<span style=""></span>审核状态：
							<select name="auditStatusId" style="width:137px;" class="select">
								<option value="">全部</option>
								<c:forEach items="${auditStatusEnumList}" var="status">
									<option value="${status.id}" ${param.auditStatusId eq status.id ?'selected':''}>${status.name}</option>
								</c:forEach>
							</select>
							<span style="padding-left:10px;"></span>姓名：
							<input type="text" name="doctorName" value="${param.doctorName}">
							<span style="padding-left:20px;"></span>
							<input id="search001" type="button" class="search" value="查&#12288;询" onclick="toPage1(1)"/>
							<input type="button" class="search" value="审&#12288;核" onclick="auditOpt()"/>
							<input type="button" class="search" value="撤&#12288;销" onclick="backOpt()"/>
							<input type="button" class="search" value="导&#12288;出" onclick="expAppoint()"/>
						</td>
					</tr>
				</table>
			</form>
			<div  id="appointDiv">
				
			</div>
		</div>
	</div>
</div>
</body>	
</html>