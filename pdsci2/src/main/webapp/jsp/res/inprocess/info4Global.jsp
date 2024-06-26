<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
</script>
<style type="text/css">
	.table {
		border: 1px solid #e3e3e3;
		font-size:13px;
	}
	.table th, .table td {
		border-bottom: 1px solid #e3e3e3;
		border-right: 1px solid #e3e3e3;
		text-align: center;
		font-size:13px;
	}
	.table th {
		background: rgba(0, 0, 0, 0) url("<s:url value='/jsp/res/disciple/images/table.png'/>") repeat-x scroll 0 0;
		color: #585858;
		height: 30px;
		font-size:13px;
	}
	.table td {
		height: 30px;
		line-height: 25px;
		text-align: center;
		word-break: break-all;
		padding:10px;
		font-size:13px;
	}
</style>
<script type="text/javascript">

	function showext(){
		var src="<s:url value="/res/inprocess/info/${param.role}"></s:url>?deptFlow=${dept.deptFlow}";
		window.location.href= src;
	}
	function save()
	{
		var jsonData={};
		//科室人员简介
		var members=[];
		$("#memberList tr").each(function(){
			var recordFlow=$(this).find("[name='flows']").val();
			var memberPostId=$(this).find("[name='memberPostId'] option:selected").val();
			var memberPostName=$(this).find("[name='memberPostId'] option:selected").text();
			var memberName=$(this).find("[name='memberName']").val();
			var memberPhone=$(this).find("[name='memberPhone']").val();
			var memberTitleId=$(this).find("[name='memberTitleId'] option:selected").val();
			var memberTitleName=$(this).find("[name='memberTitleId'] option:selected").text();
			var member={};
			member.recordFlow=recordFlow;
			member.memberPostId=memberPostId;
			member.memberPostName=memberPostName;
			member.memberName=memberName;
			member.memberPhone=memberPhone;
			member.memberTitleId=memberTitleId;
			member.memberTitleName=memberTitleName;
			members.push(member);
		});
		jsonData.members=members;
		//科室固定学术及业务活动安排
		var teaching={};
		$("#teaching tr").each(function(i){
			var count=i+1;
			var addressKey="address"+count;
			var contentKey="content"+count;
			var address=$(this).find("[name="+addressKey+"]").val();
			var content=$(this).find("[name="+contentKey+"]").val();
			teaching[addressKey]=address;
			teaching[contentKey]=content;
		});
		jsonData.teaching=teaching;
		//已有文件的流水号
		var fileFlows=[];
		$("#files tr").each(function(){
			var fileFlow=$(this).find("[name='fileFlows']").val();
			if(fileFlow)
				fileFlows.push(fileFlow);
		});
		jsonData.fileFlows=fileFlows;
		if(!$("#saveForm").validationEngine("validate")) {
			jboxTip("请完善信息后，再保存！");
			return false;
		}
		jboxConfirm("确认保存?",function(){
			var url="<s:url value='/res/inprocess/save'/>?jsonData="+encodeURI(encodeURI(JSON.stringify(jsonData)));
			jboxSubmit($("#saveForm"),url,function(resp){
				if(resp=="${GlobalConstant.SAVE_SUCCESSED}") {
					if("${role}"=='head')
					{
						refresh();
					}else if("${role}"=='admin'){
						window.parent.frames['mainIframe'].window.refresh();
					}
				}
			},null,true);
		});
	}

	function refresh(){
		jboxStartLoading();
		window.location.reload(true);
		jboxEndLoading();
	}
	function moveTr(obj){
		jboxConfirm("确认删除？" , function(){
			var tr=obj.parentNode.parentNode;
			tr.remove();
		});
	}

	function addMember(){
		$('#memberList').append($("#moban tr:eq(0)").clone());
	}
	function addFile(){
		$('#files').append($("#fileTable tr:eq(0)").clone());
	}
	function search(deptFlow){
		location.href= "<s:url value='/res/inprocess/info/head?deptFlow='/>"+deptFlow;
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div width="100%" height="100%" style="margin-top: 20px">
			<table class="table" width="100%" border="0" style="border:0px;">
				<tr style="height: 58px">
					<td style="text-align: center; border:0px;">
						<span style="font-size:25px;${role eq 'head'?'margin-left:20px;':''}">${dept.deptName}入科教育信息规范</span>
					</td>
				</tr>
			</table>
			<br>
			<form id="saveForm" method="post" enctype="multipart/form-data">
				<input type="text" class=" inputText" value="${info.recordFlow}" name="recordFlow" hidden>
				<c:if test="${!( role eq 'head' and deptList.size() > 1) }">
					<input type="text" class=" inputText" value="${dept.deptFlow}" name="deptFlow" hidden>
				</c:if>
				<input type="text" class=" inputText" value="${dept.orgFlow}" name="orgFlow" hidden>
			<table class="table" width="100%">
				<c:if test="${role eq 'head' and deptList.size() > 1 }">
					<tr>
						<td style="text-align: right;font-weight: bold;" valign="middle">
							<span style="font-size: 13px;margin-right: 0px">所在科室：</span>
						</td>
						<td style="text-align: left;" colspan="2">
							<select name="deptFlow" class="xlselect" onchange="search(this.value)">
								<option></option>
								<c:forEach items="${deptList}" var="obj">
									<option value="${obj.deptFlow}"<c:if test="${param.deptFlow eq obj.deptFlow || dept.deptFlow eq obj.deptFlow}">selected</c:if>>${obj.deptName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</c:if>
				<tr>
					<td style="text-align: right;width:123px;font-weight: bold;" valign="middle">
						<span style="font-size: 13px;margin-right: 0px">科室简介：</span>
					</td>
					<td style="text-align: left;" colspan="2">${info.deptBriefing}</td>
				</tr>
				<tr>
					<td style="text-align: right;font-weight: bold;" valign="middle">
						<span style="font-size: 13px;margin-right: 0px">科室人员：</span>
					</td>
					<td style="text-align: left;" colspan="2">
						<table class="grid" style="font-size: 13px;width:100%;border:1px solid #e3e3e3;">
							<thead>
							<tr>
								<th>职务</th>
								<th>姓名</th>
								<th>联系方式</th>
								<th>职称</th>
							</tr>
							</thead>
							<tbody id="memberList">
							<c:if test="${not empty members}">
								<c:forEach items="${members}" var="member">
									<tr>
										<td>${member.memberPostName}</td>
										<td>${member.memberName}</td>
										<td>${member.memberPhone}</td>
										<td>${member.memberTitleName}</td>
									</tr>
								</c:forEach>
							</c:if>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-weight: bold;" valign="middle">
						<span style="font-size: 13px;margin-right: 0px">人员简介：</span>
					</td>
					<td style="text-align: left;" colspan="2">${info.deptFramework}</td>
				</tr>
				<tr>
					<td style="text-align: right;font-weight: bold;" valign="middle"><span
							style="font-size: 13px;margin-right: 0px">工作环境介绍：</span></td>
					<td style="text-align: left;" colspan="2">${info.workEnvironment}</td>
				</tr>
				<tr>
					<td style="text-align: right;font-weight: bold;" valign="middle"><span
							style="font-size: 13px;margin-right: 0px">诊治范围：</span></td>
					<td style="text-align: left;" colspan="2">${info.workScope}</td>
				</tr>
				<tr>
					<td style="text-align: right;font-weight: bold;" valign="middle"><span
							style="font-size: 13px;margin-right: 0px">科室固定学术及&#12288;<br/>业务活动安排：</span></td>
					<td style="text-align: left;" colspan="2">
						<table class="grid" style="font-size: 13px;width: 100%;border:1px solid #e3e3e3;">
							<thead>
							<tr>
								<th align="center">时间</th>
								<th align="center">地点</th>
								<th align="center">内容</th>
							</tr>
							</thead>
							<tbody id="teaching">
							<tr>
								<td>周一</td>
								<td>${arrangeMap['address1']}</td>
								<td>${arrangeMap['content1']}</td>
							</tr>
							<tr>
								<td>周二</td>
								<td>${arrangeMap['address2']}</td>
								<td>${arrangeMap['content2']}</td>
							</tr>
							<tr>
								<td>周三</td>
								<td>${arrangeMap['address3']}</td>
								<td>${arrangeMap['content3']}</td>
							</tr>
							<tr>
								<td>周四</td>
								<td>${arrangeMap['address4']}</td>
								<td>${arrangeMap['content4']}</td>
							</tr>
							<tr>
								<td>周五</td>
								<td>${arrangeMap['address5']}</td>
								<td>${arrangeMap['content5']}</td>
							</tr>
							<tr>
								<td>周六</td>
								<td>${arrangeMap['address6']}</td>
								<td>${arrangeMap['content6']}</td>
							</tr>
							<tr>
								<td>周日</td>
								<td>${arrangeMap['address7']}</td>
								<td>${arrangeMap['content7']}</td>
							</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-weight: bold;" valign="middle">
						<span style="font-size: 13px;margin-right: 0px">科室考勤制度：</span>
					</td>
					<td style="text-align: left;" colspan="2">${info.attendanceInfo}</td>
				</tr>
				<tr>
					<td style="text-align: right;font-weight: bold;" valign="middle">
						<span style="font-size: 13px;margin-right: 0px">入科教育文档：</span>
					</td>
					<td style="text-align: left;" colspan="2">
						<table id="files" class="grid" style="font-size: 13px;width: 100%;border:1px solid #e3e3e3;">
							<tr>
								<th style="width: 90%;">附件</th>
							</tr>
							<c:if test="${not empty files}">
								<c:forEach items="${files}" var="file">
									<tr>
										<td style="text-align: left;">
											<input hidden name="fileFlows" value="${file.fileFlow}">
											<a href="<s:url value='/jsres/kzr/fileDown'/>?fileFlow=${file.fileFlow}">${file.fileName}</a>
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="关&#12288;闭" class="search" onclick="top.jboxClose();" style="margin-top: 5px;margin-bottom: 5px;"/>
					</td>
				</tr>
			</table>
			</form>

			<table class="table" id="moban" style="display: none" style="width: 100%">
				<tr>
					<td>
						<input hidden name="flows">
						<select style="width: 100px;" name="memberPostId" class="xlselect">
							<option></option>
							<c:forEach items="${dictTypeEnumUserPostList}" var="post">
								<option value="${post.dictId}">${post.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<td><input style="width: 100px;" name="memberName" class="input validate[required]"></td>
					<td><input style="width: 100px;" name="memberPhone" class="input"></td>
					<td>
						<select style="width: 100px;" name="memberTitleId" class="xlselect ">
							<option value=""></option>
							<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
								<option value="${title.dictId}">${title.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<img class="opBtn" title="删除" src="/pdsci/css/skin/LightBlue/images/del1.png"
							 style="cursor: pointer;" onclick="moveTr(this);"/>
					</td>
				</tr>
			</table>
			<table class="table" id="fileTable" style="display: none" style="width: 100%">
				<tr>
					<td style="text-align: left;">
						<input hidden name="fileFlows">
						<input type='file' name='files' class='validate[required]' multiple='multiple'
							   accept=".pdf,.doc,.docx,.ppt,.PPT"/>
					</td>
					<td>
						<img class='opBtn' title='删除' style='cursor: pointer;'
							 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
</div>
</body>
</html>