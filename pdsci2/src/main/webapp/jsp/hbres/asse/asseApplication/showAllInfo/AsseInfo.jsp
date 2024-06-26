<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
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
    <jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red;}
</style>
<script type="text/javascript">
	//打印
	function print(doctorFlow)
	{
		jboxExp(null,"<s:url value='/hbres/doctor/applyDownload?doctorFlow='/>" + doctorFlow +"&recruitFlow=${recruitFlow}&applyYear=${param.applyYear}");
	}

</script>
<div class="search_table" style="margin-top:10px;">
	<div id="" style="max-height: 400px;overflow: auto;">
		<div class="main_bd" id="div_table_0" >
			<h4 >基本信息</h4>
			<form id="fileForm" method="post" enctype="multipart/form-data">
				<input type="text" name="productFlow" value="${recruitFlow}" style="display: none;"/>
				<input type="file" id="file" name="file" class="validate[required]" style="display: none;"/>
			</form>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info" >
				<colgroup>
					<col width="20%"/>
					<col width="20%"/>
					<col width="20%"/>
					<col width="20%"/>
					<col width="20%"/>
				</colgroup>
				<tbody>
				<tr>
					<th>姓名：</th>
					<td>
						${user.userName}
					</td>
					<th>性别：</th>
					<td>
						<c:if test="${user.sexId eq userSexEnumMan.id}">&nbsp;${userSexEnumMan.name}</c:if><c:if test="${user.sexId eq userSexEnumWoman.id}">&nbsp;${userSexEnumWoman.name}</c:if>
					</td>
					<td rowspan="5" style="text-align: center;">
						<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg"
							 style="margin-top: 3px;" width="130px" height="150px"
							 onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
						<br>

						<%--<p title="<div id='changeInfo' class='pxxx' >--%>
							<%--<div class='changeinfo' id='changeInfoContent' style='height: 85px;width:300px;'>--%>
								<%--<i class='icon_up'></i>--%>
								<%--<table border='0' cellpadding='0' cellspacing='0' class='grid' style='width: 100%'>--%>
									<%--<tr><td>1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立。</td></tr>--%>
									<%--<tr><td>2、该照片用于结业证书，请慎重认真上传！</td></tr>--%>
								<%--</table>--%>
							<%--</div></div>">照片要求</p>--%>
					</td>
				</tr>
				<tr>
					<th>证件类型：</th>
					<td>

						<c:forEach items="${certificateTypeEnumList}" var="certificateType">
							<c:if test="${user.cretTypeId eq certificateType.id}">&nbsp;${certificateType.name}</c:if>
						</c:forEach>

					</td>
					<th>证&nbsp;件&nbsp;号：</th>
					<td>
						${user.idNo}
					</td>
				</tr>
				<tr>
					<th>培训基地：</th>
					<td>
						${doctor.orgName}
					</td>
					<th>培训专业：</th>
					<td>
						${doctor.trainingSpeName}
					</td>
				</tr>
				<tr>
					<th>入培年级：</th>
					<td>
						${doctor.sessionNumber}
					</td>
					<th>结业考核年份：</th>
					<td>
						${doctor.graduationYear}
					</td>
				</tr>
				<tr>
					<th>学位：</th>
					<td>
                        ${user.degreeName}
					</td>
					<th>联系方式：</th>
					<td>${user.userPhone}
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<div class="main_bd" id="div_table_1" style="padding-top: 20px;" >
			<h4 >报考信息</h4>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info" >
				<colgroup>
					<col width="20%"/>
					<col width="20%"/>
					<col width="20%"/>
					<col width="40%"/>
				</colgroup>
				<tbody>
				<tr>
					<th>学历：</th>
					<td>${user.educationName}</td>
					<th>毕业证书编号：</th>
					<td>${jsresGraduationApply.certificateNo}
					</td>
				</tr>
				<tr>
					<th>培训年限</th>
					<td>
						<c:if test="${'1' eq doctor.trainingYears}">一年</c:if>
						<c:if test="${'2' eq doctor.trainingYears}">两年</c:if>
						<c:if test="${'3' eq doctor.trainingYears}">三年</c:if>
					</td>
					<th>培训起止日期：</th>
					<td>${startDate}~${endDate}
					</td>
				</tr>
				<tr>
					<th>报考资格材料：</th>
					<td>
						${jsresGraduationApply.graduationMaterialName}
					</td>
					<th>报考资格材料编码：</th>
					<td>
						${jsresGraduationApply.graduationMaterialCode}
					</td>
				</tr>
				<tr>
					<th>取得资格日期：</th>
					<td colspan="3">
						${jsresGraduationApply.graduationMaterialTime}
					</td>
				</tr>
				<tr>
					<th>执业类型：</th>
					<td>
						${jsresGraduationApply.graduationCategoryName}
					</td>
					<th>执业范围：</th>
					<td>
						${jsresGraduationApply.graduationScopeName}
					</td>
				</tr>
				<tr>
					<th>培训登记手册完成情况：</th>
					<td>
						<c:if test="${(not empty jsresGraduationApply.registeManua) and jsresGraduationApply.registeManua eq GlobalConstant.PASS}">已完成</c:if>
						<c:if test="${(not empty jsresGraduationApply.registeManua) and jsresGraduationApply.registeManua eq GlobalConstant.UNPASS}">未完成</c:if>
					</td>
					<th>实际轮转时间（月）：</th>
					<td>${allMonth}</td>
				</tr>
				<%--<tr>--%>
					<%--<th>数据填写比例：</th>--%>
					<%--<td>${empty avgBiMap.avgComplete?0:avgBiMap.avgComplete}%</td>--%>
					<%--<th>数据审核比例：</th>--%>
					<%--<td>${empty avgBiMap.avgAudit?0:avgBiMap.avgAudit}%</td>--%>
				<%--</tr>--%>
				</tbody>
			</table>
			<table border="0" cellpadding="0" cellspacing="0"style="border-top:0px; " class="base_info" >
					<colgroup>
						<col width="20%"/>
						<col width="80%"/>
					</colgroup>
					<tbody>
					<c:if test="${not empty jsresGraduationApply.localAuditStatusId}">
						<tr>
							<th style="text-align: center;">
								基地意见
							</th>
							<td colspan="6">${jsresGraduationApply.localAuditStatusName}
										<c:if test="${not empty jsresGraduationApply.localReason}">
											(${jsresGraduationApply.localReason})
										</c:if>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty jsresGraduationApply.cityAuditStatusId}">
						<tr>
							<th style="text-align: center;">
								市局意见
							</th>
							<td colspan="6">${jsresGraduationApply.cityAuditStatusName}
								<c:if test="${not empty jsresGraduationApply.cityReason}">
									(${jsresGraduationApply.cityReason})
								</c:if>
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty jsresGraduationApply.globalAuditStatusId}">
						<tr>
							<th style="text-align: center;">
								省厅意见
							</th>
							<td colspan="6">${jsresGraduationApply.globalAuditStatusName}
								<c:if test="${not empty jsresGraduationApply.globalReason}">
									(${jsresGraduationApply.globalReason})
								</c:if>
							</td>
						</tr>
					</c:if>
					</tbody>
				</table>
		</div>
		<%--基地角色查看时，传一个不为空isShow的参数--%>
		<c:if test="${not empty param.isShow}">
			<div style="text-align: center;padding-top: 20px;padding-bottom: 50px;">
				<input type="button" class="btn_green" onclick="print('${resDoctor.doctorFlow}');" value="打印"/>
			</div>
		</c:if>
	</div>
</div>
