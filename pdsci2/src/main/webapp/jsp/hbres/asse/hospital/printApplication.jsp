<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>

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
		text-align: center;
		height:48px;
		font-size: 15px;
	}
	table th {
		border-top: 0;
		border-right: 1px solid #D3D3D3;
		border-bottom: 1px solid #D3D3D3;
		border-left: 0;
		text-align: center;
		height:48px;
		font-size: 15px;
	}
	table tr.lastrow td {
		border-bottom: 0;
	}
	table tr td.lastCol {
		border-right: 0;
	}
</style>
<script>

	$(document).ready(function() {
		window.print();
	});
</script>
<div style="padding:35px;">
	<div style="text-align: center;height: 90px;font-size: 18px;width: 100%;">
				<strong>
					湖北省住院医师规范化培训（西医）结业</br>
					理论统考资格审核情况表
				</strong>
	</div>
	<table style="width: 100%;">
		<colgroup>
			<col width="21%"/>
			<col width="21%"/>
			<col width="21%"/>
			<col width="21%"/>
			<col width="16%"/>
		</colgroup>
		<tbody>
		<tr>
			<td style="text-align: left;background-color: lightgrey;" colspan="5">
				<span>
					<strong>&nbsp;基本信息</strong>
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<span>
					姓&#12288;&#12288;名
				</span>
			</td>
			<td style="font-family:宋体">
				<span>
					${user.userName}
				</span>
			</td>
			<td>
				<span>性&#12288;&#12288;别</span>
			</td>
			<td style="font-family:宋体">
				<span>
					<c:if test="${user.sexId eq userSexEnumMan.id}">
						${userSexEnumMan.name}
					</c:if>
					<c:if test="${user.sexId eq userSexEnumWoman.id}">
						${userSexEnumWoman.name}
					</c:if>
				</span>
			</td>
			<td rowspan="5" style="min-width: 16%;max-width: 16%;">
				<div style="min-width: 16%;max-width: 16%;">
					<c:if test="${not empty user.userHeadImg}">
						<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" alt=""
							 style="margin-top: 3px;" width="130px" height="150px"/>
					</c:if>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<span>
					证件类型
				</span>
			</td>
			<td style="font-family:宋体">
				<span>
					<c:forEach items="${certificateTypeEnumList}" var="certificateType">
						<c:if test="${user.cretTypeId eq certificateType.id}">
							${certificateType.name}
						</c:if>
					</c:forEach>
				</span>
			</td>
			<td>
				<span>证&nbsp;件&nbsp;号</span>
			</td>
			<td style="font-family:宋体">
				<span>
					${user.idNo}
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<span>
					培训基地
				</span>
			</td>
			<td>
				<span style="font-family:宋体">
					${doctor.orgName}
				</span>
			</td>
			<td>
				<span>培训专业</span>
			</td>
			<td style="font-family:宋体">
				<span>
					${doctor.trainingSpeName}
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<span>
					入培年级
				</span>
			</td>
			<td  style="font-family:宋体">
				<span>
					${doctor.sessionNumber}
				</span>
			</td>
			<td>
				<span>结业考核年份</span>
			</td>
			<td style="font-family:宋体">
				<span>
					${doctor.graduationYear}
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<span>
					学&#12288;&#12288;位
				</span>
			</td>
			<td style="font-family:宋体">
				<span>
					${user.degreeName}
				</span>
			</td>
			<td>
				<span>联系方式</span>
			</td>
			<td style="font-family:宋体">
				<span>
					${user.userPhone}
				</span>
			</td>
		</tr>
		<tr>
			<td style="text-align: left;background-color: lightgrey;" colspan="5">
				<span>
					<strong>&nbsp;资格信息</strong>
				</span>
			</td>
		</tr>
		<tr>
			<td>
				<span>
					学&#12288;&#12288;历
				</span>
			</td>
			<td style="font-family:宋体">
				<span>
					${user.educationName}
				</span>
			</td>
			<td>
				<span>毕业证书编号</span>
			</td>
			<td style="font-family:宋体" colspan="2">
				<span>
					${userResumeExt.certificateCode}
				</span>
			</td>
		</tr>
		<tr>
			<td><span>培训年限</span></td>
			<td style="font-family:宋体">
				<c:if test="${'1' eq doctor.trainingYears}">一年</c:if>
				<c:if test="${'2' eq doctor.trainingYears}">两年</c:if>
				<c:if test="${'3' eq doctor.trainingYears}">三年</c:if>
			</td>
			<td><span>培训起止日期</span></td>
			<td style="font-family:宋体" colspan="2">
				${startDate}~${endDate}
			</td>
		</tr>
		<tr>
			<td><span>报考资格材料</span></td>
			<td style="font-family:宋体">
				${practicingMap.graduationMaterialName}
				<c:if test="${empty practicingMap.graduationMaterialName}">
					<span style="color: red" >未填写！</span>
					<c:set var="canApply" value="N"></c:set>
				</c:if>
			</td>
			<td><span>报考资格材料编码</span></td>
			<td style="font-family:宋体" colspan="2">
				${practicingMap.graduationMaterialCode}
				<c:if test="${empty practicingMap.graduationMaterialCode}">
					<span style="color: red" >&nbsp;未填写！</span>
					<c:set var="canApply" value="N"></c:set>
				</c:if>
			</td>
		</tr>
		<tr>
			<td><span>取得资格日期</span></td>
			<td colspan="4" style="font-family:宋体">
				${practicingMap.graduationMaterialTime}
				<c:if test="${empty practicingMap.graduationMaterialTime}">
					<span style="color: red" >未填写！</span>
					<c:set var="canApply" value="N"></c:set>
				</c:if>
			</td>
		</tr>
		<tr>
			<td><span>执业类型</span></td>
			<td style="font-family:宋体" >
				${practicingMap.graduationCategoryName}
				<c:if test="${empty practicingMap.graduationCategoryName}">
					<span style="color: red" >未填写！</span>
					<c:set var="canApply" value="N"></c:set>
				</c:if>
			</td>
			<td><span>执业范围</span></td>
			<td style="font-family:宋体"  colspan="2">
				${practicingMap.graduationScopeName}
				<c:if test="${empty practicingMap.graduationScopeName}">
					<span style="color: red" >未填写！</span>
					<c:set var="canApply" value="N"></c:set>
				</c:if>
			</td>
		</tr>
		</tbody>
	</table>
	<table style="border-top: none;width: 100%;">
		<colgroup>
			<col width="50%"/>
			<col width="50%"/>
		</colgroup>
		<tr>
			<td colspan="2">
				<span>培训登记手册完成情况</span>
				<c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.PASS}">
					已完成
				</c:if>
				<c:if test="${not empty userResumeExt.registeManua and userResumeExt.registeManua eq GlobalConstant.UNPASS}">
					未完成
				</c:if>
			</td>
		</tr>
		<tr>
			<td style="height: 180px;text-align: left;" valign="top">
				<p style="border: none;">
					<strong>培训基地初审意见：</strong><br/><br/>
					&nbsp;&#12288;&#12288;我已审核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。<br/>
					<br/>
					&nbsp;审核人签名：&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;单位公章：<br/>
					<br/>
					&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
					<span style="width:100%;text-align: right">年&#12288;&#12288;月&#12288;&#12288;日&#12288;</span>
				</p>
			</td>
			<td  style="height: 180px;text-align: left" valign="top">
				<p>
					<strong>市卫计委复审意见：</strong><br/><br/>
					&nbsp;&#12288;&#12288;我已复核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。<br/>
					<br/>
					&nbsp;复审人签名：&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;单位公章：<br/>
					<br/>
					&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;
					<span style="width:100%;text-align: right">年&#12288;&#12288;月&#12288;&#12288;日&#12288;</span>
				</p>
			</td>
		</tr>
	</table>
</div>
