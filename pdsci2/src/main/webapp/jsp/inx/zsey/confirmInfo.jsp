<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>湖北省住院医师规范化培训招录系统</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script type="text/javascript">
	function audit(statusId,title){
		jboxConfirm("请仔细确认您填写的信息,确保无误后提交.提交后信息将无法修改，<font color='red'>错误信息可能导致审核不通过，无法参加考试!</font>",function(){
			var data = {
					userFlow:"${user.userFlow}",
					statusId:statusId
			};
			jboxPost("<s:url value='/inx/hbres/userAudit'/>",data,
				function(resp){
					if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
						window.parent.$(".nextBut").hide();
						window.parent.location.href="<s:url value='/inx/hbres/completeUserInfo'/>?activationCode=${user.userFlow}";
						jboxClose();
					}
				}
			,null,true);
		});
	}
</script>
</head>
<body style="width:100%; min-width: inherit; height:100%;background-image:none; background:#fff;">

	<div class="infoAudit" >
	
		<table border="0" width="945" cellspacing="0" cellpadding="0">
			<caption>个人信息类型</caption>
	     	 <tr>
				<th width="15%">医师类型：</th>
				<td width="25%">${doctor.doctorTypeName}</td>
				<th width="15%"></th>
				<td width="25%"></td>
				<td width="20%" colspan="4"></td>
			</tr>
			<c:if test="${doctor.doctorTypeId eq recDocTypeEnumCompany.id}">
				<tr>
					<th>工作单位名称：</th>
					<td>${doctor.workOrgName}</td>
					<th>委培单位同意证明：</th>
					<td>
						<c:if test="${!empty doctor.dispatchFile}">
							[<a href="${sysCfgMap['upload_base_url']}/${doctor.dispatchFile}" target="_blank">点击查看</a>]
						</c:if>
					</td>
				</tr>
			</c:if>
			<c:if test="${doctor.doctorTypeId eq recDocTypeEnumGraduate.id}">
				<tr>
					<th>在读院校名称：</th>
					<td colspan="3">${doctor.workOrgName}</td>
				</tr>
			</c:if>
		</table>
		
		<table border="0" width="945" cellspacing="0" cellpadding="0">
			<caption>个人信息</caption>
	     	 <tr>
				<th width="15%">姓名：</th>
				<td width="25%">${user.userName}</td>
				<th width="15%">性别：</th>
				<td width="25%">${user.sexName}</td>
				<td width="20%" rowspan="6">
					<img src="${sysCfgMap['upload_base_url']}/${doctor.doctorHeadImg}" width="140px" height="200px"/>
				</td>
			</tr>
			<tr>
				<th>证件号码：</th>
				<td>${user.idNo}</td>
				<th>出生日期：</th>
				<td>${user.userBirthday}</td>
			</tr>
			<tr>
				<th>手机号码：</th>
				<td>${user.userPhone}</td>
				<th>电子邮箱：</th>
				<td>${user.userEmail} </td>
			</tr>
			<tr>
				<th>通讯地址：</th>
				<td colspan="3">${user.userAddress} </td>
			</tr>
			<tr>
				<th width="15%" style="line-height:20px; height:50px"  >紧急联系人：</th>
				<td width="25%" style=" height:50px">${doctor.emergencyName}</td>
				<th width="15%" style="line-height:20px; height:50px">紧急联系人<br/>联系方式：</th>
				<td width="25%" style=" height:50px">${doctor.emergencyPhone}</td>
			</tr>
			<tr>
				<th>与本人关系：</th>
				<td colspan="3">${doctor.emergencyRelation}</td>
			</tr>
		</table>
		
		<table border="0" width="945" cellspacing="0" cellpadding="0" >
			<caption>学历信息登记</caption>
			
			<tr>
				<th width="15%">毕业院校：</th>
				<td width="25%">${doctor.graduatedName}</td>
				<th>毕业时间：</th>
				<td>${doctor.graduationTime}</td>
				<td width="20%" colspan="4"></td>
			</tr>
			<tr>
				<th width="15%">毕业专业：</th>
				<td>
				    <c:forEach var="dict" items="${dictTypeEnumGraduateMajorList}">
              	        <c:if test='${doctor.specialized==dict.dictId}'>${dict.dictName}${dict.dictDesc}</c:if>
              	    </c:forEach>
				</td>
				<th>学历：</th>
				<td>${user.educationName}</td>
			</tr>
			<tr>
				<th>学位：</th>
				<td>${user.degreeName}</td>
				<th>学位类型：</th>
				<td>${doctor.degreeCategoryName}</td>
			</tr>
			<tr>
				<th>毕业证书：</th>
				<td>
					<c:if test="${!empty doctor.certificateNo}">
						[<a href="${sysCfgMap['upload_base_url']}/${doctor.certificateNo}" target="_blank">点击查看</a>]
					</c:if>
				</td>
				<th>学位证书：</th>
				<td>
					<c:if test="${!empty doctor.degreeNo}">
						[<a href="${sysCfgMap['upload_base_url']}/${doctor.degreeNo}" target="_blank">点击查看</a>]
					</c:if>
				</td>
			</tr>
			<tr>
				<th>医师资格证号：</th>
				<td>${doctor.qualifiedNo}</td>
				<th>医师资格证：</th>
				<td>
					<c:if test="${!empty doctor.qualifiedFile}">
						[<a href="${sysCfgMap['upload_base_url']}/${doctor.qualifiedFile}" target="_blank">点击查看</a>]
					</c:if>
				</td>
			</tr>
		</table>
		
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<a class="btn blue-btn" href="javascript:audit('${userStatusEnumActivated.id}');">&nbsp;提&nbsp;交&nbsp;</a>
			<a class="btn h-btn" href="javascript:jboxClose();">&nbsp;关&nbsp;闭&nbsp;</a>
		</div>
	</div>
</body>
</html>