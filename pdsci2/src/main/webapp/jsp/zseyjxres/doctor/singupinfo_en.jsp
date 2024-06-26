<script>
	function search(){
		var url = "<s:url value='/zseyjxres/doctor/singup_en?batchFlow='/>"+$('#batchFlow').val();
		jboxLoad("content", url, true);
	}
	//打印申请表
	function printApplForm(resumeFlow){
			jboxTip("打印中,请稍等...");
//        if(templeteName =='cd'){
//            templeteName = "成都中医药大学附属医院/四川省中医院";
//        }else if(templeteName =='xz'){
//            templeteName = "徐州中心医院";
//        }
			var url = '<s:url value="/zseyjxres/hospital/printApplForm?resumeFlow="/>'+resumeFlow;
			window.location.href = url;
	}
	//打印录取通知书
	function printRecruit(resumeFlow){
		jboxTip("打印中,请稍等...");
		var url = '<s:url value="/zseyjxres/doctor/recruitNotice?resumeFlow="/>'+resumeFlow+"&titleTypeId=${extInfo.titleTypeId}";
		window.location.href = url;
	}
</script>
<div id="singupContent">
	<div id='docTypeForm'>
		<p id="errorMsg" style="color: red;"></p>
		<div class="main_hd"><h2 class="underline">on-line registration</h2></div>
		<form id='doctorForm' style="position:relative;">
			<div class="main_bd">
				<div class="div_table">
					<div class="score_frame">
						<div class="div_table">
							<h4>Study batches&#12288;&#12288;&#12288;&#12288;&#12288;
								<select autocomplete="off" class="select" id="batchFlow" style="width: 160px;margin-left: 5px;" onchange="search()">
									<c:forEach items="${batchLst}" var="dict">
										<option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchDate}</option>
									</c:forEach>
								</select>
							</h4>
						</div>
						<div class="div_table">
							<h4>essential information</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="base_info">
									<colgroup>
										<col width="20%"/>
										<col width="15%"/>
										<col width="20%"/>
										<col width="15%"/>
										<col width="15%"/>
										<col width="15%"/>
									</colgroup>
									<tr>
										<th>name（first name，family name:</th>
										<td>${extInfo.userName}</td>
										<th>date of birth：</th>
										<td colspan="2">${extInfo.userBirthday}</td>
										<td rowspan="4" style="text-align: center;padding-top:5px;">
											<img src="${sysCfgMap['upload_base_url']}/${extInfo.userHeadImg}"style="max-height: 150px;max-width: 170px;" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
										</td>
									</tr>
									<tr>
										<th>gender：</th>
										<td>${extInfo.sexId}</td>
										<th>age：</th>
										<td colspan="2">${stuUser.userAge}</td>
									</tr>
									<tr>
										<th>Passport No：</th>
										<td>${extInfo.passportNo}</td>
										<th>nationality：</th>
										<td colspan="2">${extInfo.nationalityName}</td>
									</tr>
									<tr>
										<th>Work uniform:</th>
										<td>${extInfo.workClother}</td>
										<th>The uniform size:</th>
										<td>${stuUser.clotherSizeId}</td>
										<th></th>
										<td></td>
									</tr>
									<tr>
										<th>department of study purpose:</th>
										<td>
											<c:forEach items="${extInfo.speFormList}" var="speForm">
												<c:if test="${flag}">,</c:if>
												${speForm.speId}
												<c:set var="flag" value="true" scope="request"></c:set>
											</c:forEach>
										</td>
										<th>the length（calculate by weekdays:</th>
										<td>
											<c:forEach items="${extInfo.speFormList}" var="speForm">
												<c:if test="${flag2}">,</c:if>
												${speForm.stuTimeId}
												<c:set var="flag2" value="true" scope="request"></c:set>
											</c:forEach>
										</td>

										<th>Study batches</th>
										<td>${stuUser.stuBatName}</td>
									</tr>
									<tr>
										<th>phone number:</th>
										<td>${extInfo.userPhone}</td>
										<th></th>
										<td></td>
										<th></th>
										<td></td>
									</tr>
								</table>
							</div>
						</div>

						<div class="div_table">
							<h4>experience in medical education</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="grid">
									<colgroup>

										<col width="30%"/>
										<col width="20%"/>
										<col width="20%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="10%"/>
									</colgroup>
									<tr style="font-weight: bold;">
										<th style="text-align: center;">beginning and ending time</th>
										<th style="text-align: center;">name of school</th>
										<th style="text-align: center;">name of major</th>
										<th style="text-align: center;">length of schooling</th>
										<th style="text-align: center;">record of formal schooling</th>
										<th style="text-align: center;">degree</th>
									</tr>
									<c:forEach items="${eduDateList}" var="eduDate">
										<c:forEach var="resume" items="${extInfo.educationList}" varStatus="status">
											<c:if test="${eduDate eq resume.eduRoundDate}">
												<tr>
													<td>${resume.eduRoundDate}</td>
													<td>${resume.schoolName}</td>
													<td>${resume.speName}</td>
													<td>${resume.length}</td>
													<td>${resume.education}</td>
													<td>${resume.degree}</td>
												</tr>
											</c:if>
										</c:forEach>
									</c:forEach>
								</table>
							</div>
						</div>
						<div class="div_table">
							<h4>medical related social(work) experience</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="grid">
									<colgroup>
										<col width="40%"/>
										<col width="20%"/>
										<col width="20%"/>
										<col width="20%"/>
									</colgroup>
									<tr style="font-weight: bold;">
										<th style="text-align: center;">beginning and ending time</th>
										<th style="text-align: center;">name of the company</th>
										<th style="text-align: center;">major in work</th>
										<th style="text-align: center;">position</th>
									</tr>
									<c:forEach items="${workDateList}" var="workDate">
										<c:forEach var="resume" items="${extInfo.workResumeList}" varStatus="status">
											<c:if test="${workDate eq resume.clinicalRoundDate}">
												<tr>
													<td>${resume.clinicalRoundDate}</td>
													<td>${resume.hospitalName}</td>
													<td>${resume.workDescription}</td>
													<td>${resume.postName}</td>
												</tr>
											</c:if>
										</c:forEach>
									</c:forEach>
								</table>
							</div>
						</div>

						<div class="div_table">
							<h4>Documents and files</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="base_info">
									<tr>
										<th width="30%">Passport picture</th>
										<td colspan="3">
                                            <span style="display:${!empty extInfo.passportUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.passportUri}" target="_blank">view picture</a>]&#12288;
                                            </span>
										</td>
									</tr>

									<tr>
										<th><font color="red"></font>medical certificate or medical license number:</th>
										<td colspan="3">
											${empty extInfo.certifiedNo?"nothing":extInfo.certifiedNo}&#12288;
											<c:if test='${!empty extInfo.certifiedUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}" target="_blank">view picture</a>]&#12288;
										    	</span>
											</c:if>
										</td>
									</tr>

									<c:if test="${empty extInfo.regList}">
										<tr>
											<th>Other qualification certificat:</th>
											<td></td>
										</tr>
									</c:if>
									<c:forEach var="reg" items="${extInfo.regList}" varStatus="status">
										<tr>
											<th>Other qualification certificat ${status.index+1}:</th>
											<td>
												${empty reg.regNo?"nothing":reg.regNo}
												<span>
													[<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">view picture</a>]&#12288;
										    	</span>
											</td>
										</tr>
									</c:forEach>

								</table>
							</div>
						</div>
						<%--<div class="div_table">--%>
							<%--<h4>缴费信息</h4>--%>
							<%--<table border="0" cellpadding="0" cellspacing="0" class="base_info">--%>
								<%--<tr>--%>
									<%--<th>学费：</th>--%>
									<%--<td>--%>
										<%--${extInfo.tuition}元--%>
									<%--</td>--%>
									<%--<th>住宿费：</th>--%>
									<%--<td>${extInfo.hotelExpense}元</td>--%>
								<%--</tr>--%>
								<%--<tr>--%>
									<%--<th>押金：</th>--%>
									<%--<td>--%>
										<%--${extInfo.according}元--%>
									<%--</td>--%>
									<%--<th>工作服：</th>--%>
									<%--<td>--%>
										<%--${extInfo.coverallNum}*65元--%>
									<%--</td>--%>
								<%--</tr>--%>
							<%--</table>--%>
						<%--</div>--%>
					</div>
				</div>
			</div>
			<%--<div id="nextPage" class="button" style="margin: 30px;">--%>
					<%--<input class="btn_green" type="button" onclick="printApplForm('${stuUser.resumeFlow}');" value="打印申请表"/>--%>
					<%--<c:if test="${stuUser.stuStatusId eq 'Recruitted' and (stuUser.isPublish eq 'Y')}">--%>
						<%--<input class="btn_green" type="button" onclick="printRecruit('${stuUser.resumeFlow}');" value="打印报到通知书"/>--%>
					<%--</c:if>--%>
			<%--</div>--%>
		</form>
	</div>
</div>
