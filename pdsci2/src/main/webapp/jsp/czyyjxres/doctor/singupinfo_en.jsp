<script>
	function search(){
		var url = "<s:url value='/czyyjxres/doctor/singup_en?batchFlow='/>"+$('#batchFlow').val();
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
			var url = '<s:url value="/czyyjxres/hospital/printApplForm?resumeFlow="/>'+resumeFlow;
			window.location.href = url;
	}
	//打印录取通知书
	function printRecruit(resumeFlow) {
		jboxTip("Download, please wait ...");
		var url = '<s:url value="/czyyjxres/doctor/printInvitationLetter?resumeFlow="/>' + resumeFlow;
		window.location.href = url;
	}
</script>
<div id="singupContent">
	<div id='docTypeForm'>
		<p id="errorMsg" style="color: red;"></p>
		<div class="main_hd"><h2 class="underline">On-line Registration</h2></div>
		<form id='doctorForm' style="position:relative;">
			<div class="main_bd">
				<div class="div_table">
					<div class="score_frame">
						<div class="div_table">
							<h4>Study Batches&#12288;&#12288;&#12288;&#12288;&#12288;
								<select autocomplete="off" class="select" id="batchFlow" style="width: 160px;margin-left: 5px;" onchange="search()">
									<c:forEach items="${batchLst}" var="dict">
										<option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchDate}</option>
									</c:forEach>
								</select>
							</h4>
						</div>
						<div class="div_table">
							<h4>Essential Information</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="base_info">
									<colgroup>
										<col width="20%"/>
										<col width="17%"/>
										<col width="20%"/>
										<col width="13%"/>
										<col width="15%"/>
										<col width="15%"/>
									</colgroup>
									<tr>
										<th>Name(first name,family name):</th>
										<td>${extInfo.userName}</td>
										<th>Date Of Birth：</th>
										<td colspan="2">${extInfo.userBirthday}</td>
										<td rowspan="4" style="text-align: center;padding-top:5px;">
											<img src="${sysCfgMap['upload_base_url']}/${extInfo.userHeadImg}"style="max-height: 150px;max-width: 170px;" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
										</td>
									</tr>
									<tr>
										<th>Gender：</th>
										<td>${extInfo.sexId}</td>
										<th>Age：</th>
										<td colspan="2">${stuUser.userAge}</td>
									</tr>
									<tr>
										<th>Phone Number:</th>
										<td>${extInfo.userPhone}</td>
										<th>China Phone Number:</th>
										<td colspan="2">${extInfo.chinaPhone}</td>
									</tr>
									<tr>
										<th>Work Uniform:</th>
										<td>${extInfo.workClother}</td>
										<th>The Uniform Size:</th>
										<td colspan="2">${stuUser.clotherSizeId}</td>
									</tr>
									<tr>
										<th>Nationality：</th>
										<td>${user.nationalityId}</td>
										<th>E-mail</th>
										<td colspan="3">${user.userEmail}</td>
									</tr>
								</table>
							</div>
						</div>

						<div class="div_table">
							<h4>Department Of Study Purpose</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="grid" >
									<colgroup>
										<col width="45%"/>
										<col width="55%"/>
									</colgroup>

									<tr style="font-weight: bold;">
										<th style="text-align: center;">Department Of Study Purpose</th>
										<th style="text-align: center;">The Length</th>
									</tr>
									<tbody id="speTb">
									<c:forEach var="speForm" items="${extInfo.speFormList}" varStatus="status">
										<tr>
											<td>${speForm.speId}</td>
											<td>${speForm.beginDate} to ${speForm.endDate}</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

						<div class="div_table">
							<h4>Experience In Medical Education</h4>
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
										<th style="text-align: center;">Beginning And Ending Time</th>
										<th style="text-align: center;">Name Of School</th>
										<th style="text-align: center;">Name Of Major</th>
										<th style="text-align: center;">Length Of Schooling</th>
										<th style="text-align: center;">Record Of Formal Schooling</th>
										<th style="text-align: center;">Degree</th>
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
							<h4>Experience In Medical Related Social(Work) </h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="grid">
									<colgroup>
										<col width="40%"/>
										<col width="20%"/>
										<col width="20%"/>
										<col width="20%"/>
									</colgroup>
									<tr style="font-weight: bold;">
										<th style="text-align: center;">Beginning And Ending Time</th>
										<th style="text-align: center;">Name Of The Company</th>
										<th style="text-align: center;">Major In Work</th>
										<th style="text-align: center;">Position</th>
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
							<h4>Documents And Files</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="base_info">
									<colgroup>
										<col width="40%"/>
										<col width="60%"/>
									</colgroup>
									<tr>
										<th>Passport:</th>
										<td>
											<%--${empty extInfo.passportNo?"nothing":extInfo.passportNo}&#12288;--%>
                                            <span style="display:${!empty extInfo.passportUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.passportUri}" target="_blank">View Picture</a>]&#12288;
                                            </span>
										</td>
									</tr>

									<tr>
										<th><font color="red"></font>Medical Certificate Or Medical License:</th>
										<td>
											<%--${empty extInfo.certifiedNo?"nothing":extInfo.certifiedNo}&#12288;--%>
											<c:if test='${!empty extInfo.certifiedUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}" target="_blank">View Picture</a>]&#12288;
										    	</span>
											</c:if>
										</td>
									</tr>

									<c:if test="${empty extInfo.regList}">
										<tr>
											<th>Other Qualification Certificat:</th>
											<td></td>
										</tr>
									</c:if>
									<c:forEach var="reg" items="${extInfo.regList}" varStatus="status">
										<tr>
											<th>Other Qualification Certificat ${status.index+1}:</th>
											<td>
												<span>
													[<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">View Picture</a>]&#12288;
										    	</span>
											</td>
										</tr>
									</c:forEach>

								</table>
							</div>
						</div>
						<%--<c:if test="${stuUser.stuStatusId eq stuStatusEnumRecruitted.id}">--%>
							<%--<div class="div_table">--%>
								<%--<form id="payInfoForm" enctype="multipart/form-data">--%>
									<%--<h4>Payment Information</h4>--%>
									<%--<table border="0" cellpadding="0" cellspacing="0" class="base_info">--%>
										<%--<tr>--%>
											<%--<th>Deposit:</th>--%>
											<%--<td>--%>
												<%--<input id="according" class='input validate[custom[number]]' &lt;%&ndash;<c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">&ndash;%&gt;readonly="readonly"&lt;%&ndash;</c:if>&ndash;%&gt; type="text" name="extInfo.according" value="${deposit}"  style="width: 150px;height: 22px; margin-left: 0px"/>CNY--%>
											<%--</td>--%>
											<%--<th>Tuition Fee:</th>--%>
											<%--<td>--%>
												<%--<input id="tuition" class='input validate[custom[number]]' &lt;%&ndash;<c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">&ndash;%&gt;readonly="readonly"&lt;%&ndash;</c:if>&ndash;%&gt; type="text" name="extInfo.tuition" value="${tuition}"  onchange="calculate()" style="width: 150px;height: 22px;margin-left: 0px"/>CNY--%>
											<%--</td>--%>
										<%--</tr>--%>
										<%--<tr>--%>
											<%--<th>Work Uniform:</th>--%>
											<%--<td>--%>
												<%--<input id="coverallNum" class='input validate[custom[integer],min[0]]' <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">readonly="readonly"</c:if> type="text" name="extInfo.coverallNum" value="${extInfo.coverallNum}" placeholder="number"  onchange="calculate()" style="width: 150px;height: 22px;margin-left: 0px"/>Piece--%>
											<%--</td>--%>
											<%--<th>Total Cost:</th>--%>
											<%--<td>--%>
												<%--<input id="totalFee" class='input validate[custom[number]]' &lt;%&ndash;<c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">&ndash;%&gt;readonly="readonly"&lt;%&ndash;</c:if>&ndash;%&gt; type="text" &lt;%&ndash;name="extInfo.totalFee" value="${extInfo.totalFee}"&ndash;%&gt;  style="width: 150px;height: 22px;margin-left: 0px"/>CNY--%>
											<%--</td>--%>
										<%--</tr>--%>
									<%--</table>--%>
								<%--</form>--%>
							<%--</div>--%>
						<%--</c:if>--%>
					</div>
				</div>
			</div>
			<div id="nextPage" class="button" style="margin: 30px;">
					<%--<input class="btn_green" type="button" onclick="printApplForm('${stuUser.resumeFlow}');" value="打印申请表"/>--%>
					<c:if test="${stuUser.stuStatusId eq 'Recruitted' and (stuUser.isPublish eq 'Y')}">
						<input class="btn_green" type="button" onclick="printRecruit('${stuUser.resumeFlow}');" value="Download the invitation letter"/>
					</c:if>
			</div>
		</form>
	</div>
</div>
