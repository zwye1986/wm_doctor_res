<script>
	function search(){
		var url = "<s:url value='/czyyjxres/doctor/singup?batchFlow='/>"+$('#batchFlow').val();
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
	function printRecruit(resumeFlow){
		jboxTip("打印中,请稍等...");
		var url = '<s:url value="/czyyjxres/doctor/recruitNotice?resumeFlow="/>'+resumeFlow+"&titleTypeId=${extInfo.titleTypeId}";
		window.location.href = url;
	}
</script>
<div id="singupContent">
	<div id='docTypeForm'>
		<p id="errorMsg" style="color: red;"></p>
		<div class="main_hd"><h2 class="underline">网上报名</h2></div>
		<form id='doctorForm' style="position:relative;">
			<div class="main_bd">
				<div class="div_table">
					<div class="score_frame">
						<div class="div_table">
							<h4>进修批次&#12288;&#12288;&#12288;&#12288;&#12288;
								<select autocomplete="off" class="select" id="batchFlow" style="width: 160px;margin-left: 5px;" onchange="search()">
									<c:forEach items="${batchLst}" var="dict">
										<option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
									</c:forEach>
								</select></h4>
						</div>
						<div class="div_table">
							<h4>基本信息</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="base_info">
									<colgroup>
										<col width="16%"/>
										<col width="18%"/>
										<col width="16%"/>
										<col width="18%"/>
										<col width="14%"/>
										<col width="18%"/>
									</colgroup>
									<tr>
										<th>姓名：</th>
										<td>${extInfo.userName}</td>
										<th>出生日期：</th>
										<td colspan="2">${extInfo.userBirthday}</td>
										<td rowspan="4" style="text-align: center;padding-top:5px;">
											<img src="${sysCfgMap['upload_base_url']}/${extInfo.userHeadImg}"style="max-height: 150px;max-width: 170px;" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
										</td>
									</tr>
									<tr>
										<th>性别：</th>
										<td>${extInfo.sexName}</td>
										<th>年龄：</th>
										<td colspan="2">${stuUser.userAge}</td>
									</tr>
									<tr>
										<th>身份证号：</th>
										<td>${extInfo.idNo}</td>
										<th>民族：</th>
										<td colspan="2">${extInfo.nationName}</td>
									</tr>
									<tr>
										<th>政治面貌：</th>
										<td>${extInfo.politicsStatusName}</td>
										<th>籍贯：</th>
										<td colspan="2">${extInfo.nativePlaceProvName}${extInfo.nativePlaceCityName}</td>
									</tr>
									<tr>
										<th>职称类别：</th>
										<td>
											<c:forEach items="${dictTypeEnumCzyyjxUserTitleList}" var="dict">
												${extInfo.titleTypeId eq dict.dictId?dict.dictName:''}
											</c:forEach>
										</td>
										<th>职称：</th>
										<td>${stuUser.titleName}</td>
                                        <th></th>
                                        <td></td>
										<%--<th>所在科室：</th>--%>
										<%--<td>${stuUser.deptName}</td>--%>
									</tr>
									<tr>
										<th>参加工作时间：</th>
										<td>${stuUser.jobYear}</td>
										<th>职务：</th>
										<td>${stuUser.postName}</td>
										<th>执业资格：</th>
										<td>${stuUser.certifiedTypeName}</td>
									</tr>
									<%--<tr>--%>
										<%--<th>进修专业：</th>--%>

										<%--<td>--%>
											<%--<c:forEach items="${extInfo.speFormList}" var="speForm">--%>
												<%--<c:if test="${flag}">,</c:if>--%>
												<%--${speForm.speId}--%>
												<%--<c:set var="flag" value="true" scope="request"></c:set>--%>
											<%--</c:forEach>--%>
										<%--</td>--%>
										<%--<th>进修时间：</th>--%>
										<%--<td>--%>
											<%--<c:forEach items="${extInfo.speFormList}" var="speForm">--%>
												<%--<c:if test="${flag2}">,</c:if>--%>
												<%--${speForm.stuTimeId}个月--%>
												<%--<c:set var="flag2" value="true" scope="request"></c:set>--%>
											<%--</c:forEach>--%>
										<%--</td>--%>

										<%--<th>进修批次：</th>--%>
										<%--<td>${stuUser.stuBatName}</td>--%>
									<%--</tr>--%>
									<tr>
										<th>工作服：</th>
										<td>${extInfo.workClother}</td>
										<th>工作服尺寸：</th>
										<td>${stuUser.clotherSizeName}</td>
										<th>是否住宿：</th>
										<td>${stuUser.isPutup}</td>
									</tr>
									<tr>
										<th>最高学历：</th>
										<td>${stuUser.maxEduName}</td>
										<th>毕业学校：</th>
										<td>${stuUser.schoolName}</td>
										<th>毕业专业：</th>
										<td>${stuUser.schoolSpeName}</td>
										<%--<th>--%>
											<%--是否熟练电脑：--%>
										<%--</th>--%>
										<%--<td>--%>
										<%--${stuUser.isComputer eq 'Y'? '是':'否'}--%>
										<%--</td>--%>
									</tr>
									<tr>
										<th>最高学历开始时间：</th>
										<td>${stuUser.maxEduBdate}</td>
										<th>最高学历结束时间：</th>
										<td>${stuUser.maxEduEdate}</td>
										<th>是否我院教学基地或帮扶协议单位：</th>
										<td>
											<c:if test="${extInfo.isOwnOrg eq 'own'}">我院教学基地</c:if>
											<c:if test="${extInfo.isOwnOrg eq 'other'}">帮扶协议单位</c:if>
											<c:if test="${extInfo.isOwnOrg eq 'N'}">否</c:if>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="div_table">
							<h4 style="width: 100%;float: left">进修专业</h4>
							<div>
								<table style="width: 100%;" class="grid" >
									<colgroup>
										<col width="50%"/>
										<col width="50%"/>
									</colgroup>

									<tr style="font-weight: bold;">
										<th style="text-align: center;">进修专业</th>
										<th style="text-align: center;">进修时间</th>
									</tr>
									<tbody id="speTb">
									<c:forEach var="speForm" items="${extInfo.speFormList}" varStatus="status">
										<tr>
											<td>
												${speForm.speName}
											</td>
											<td>
													${speForm.stuTimeId}个月
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="div_table">
							<h4>本人联系方式</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="base_info">
									<colgroup>
										<col width="20%"/>
										<col width="30%"/>
										<col width="20%"/>
										<col width="30%"/>
									</colgroup>
									<tr>
										<th>手机号码：</th>
										<td>${extInfo.userPhone}</td>
										<th>邮政编码：</th>
										<td>${extInfo.postCodes}</td>
										<%--<th>电子邮箱：</th>
										<td>${extInfo.userEmail}</td>--%>
									</tr>
									<tr>
										<th>医院等级：</th>
										<td>${stuUser.hospitalLevelName}</td>
										<th>选送单位：</th>
										<td>${stuUser.sendComName}</td>
									</tr>
									<tr>
										<th>选送单位详细地址：</th>
										<td colspan="3">${stuUser.sendComAddress}</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="div_table">
							<h4>工作经历（含外单位进修经历）</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="grid">
									<colgroup>
										<col width="22%"/>
										<col width="22%"/>
										<col width="22%"/>
										<col width="22%"/>
									</colgroup>
									<tr style="font-weight: bold;">
										<th>起止时间</th>
										<th>工作单位</th>
										<th>从事工作</th>
										<th>职务</th>
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
							<h4>学历信息（从高中学历写起）</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="grid">
									<colgroup>
										<col width="23%"/>
										<col width="20%"/>
										<col width="20%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="10%"/>
									</colgroup>
									<tr style="font-weight: bold;">
										<th style="text-align: center;">起止时间</th>
										<th style="text-align: center;">学校名称</th>
										<th style="text-align: center;">专业名称</th>
										<th style="text-align: center;">学制</th>
										<th style="text-align: center;">学历</th>
										<th style="text-align: center;">学位</th>
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
							<h4>科研信息</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="grid">
									<colgroup>
										<col width="10%"/>
										<col width="20%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="20%"/>
									</colgroup>
									<tr style="font-weight: bold;">
										<th style="text-align: left;" colspan="7">一、课题</th>
									</tr>
									<tr style="font-weight: bold;">
										<th>序号</th>
										<th style="text-align: center;">标题内容</th>
										<th style="text-align: center;">作者名次</th>
										<th style="text-align: center;">课题级别</th>
										<th style="text-align: center;">课题获批时间</th>
										<th style="text-align: center;">合同书号</th>
										<th style="text-align: center;">获奖情况（何部门批准<br/>及奖励名称、等级）</th>
									</tr>
									<c:forEach var="resume" items="${extInfo.projectList}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td>${resume.titleName}</td>
											<td>${resume.authorRank}</td>
											<td>${resume.projectLevel}</td>
											<td>${resume.projectDate}</td>
											<td>${resume.pubNumber}</td>
											<td>${resume.awardSituation}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="grid">
									<colgroup>
										<col width="7%"/>
										<col width="20%"/>
										<col width="10%"/>
										<col width="12%"/>
										<col width="10%"/>
										<col width="10%"/>
										<col width="20%"/>
										<col width="10%"/>
									</colgroup>
									<tr style="font-weight: bold;">
										<th style="text-align: left;" colspan="8">二、论文</th>
									</tr>
									<tr style="font-weight: bold;">
										<th>序号</th>
										<th style="text-align: center;">论文题目</th>
										<th style="text-align: center;">作者名次</th>
										<th style="text-align: center;">杂志名称</th>
										<th style="text-align: center;">杂志名称发表时间</th>
										<th style="text-align: center;">刊号</th>
										<th style="text-align: center;">获奖情况（何部门批准<br/>及奖励名称、等级）</th>
										<th style="text-align: center;">是否SCI或核心</th>
									</tr>
									<c:forEach var="resume" items="${extInfo.thesisList}" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td>${resume.titleName}</td>
											<td>${resume.authorRank}</td>
											<td>${resume.pubName}</td>
											<td>${resume.pubDate}</td>
											<td>${resume.pubNumber}</td>
											<td>${resume.awardSituation}</td>
											<td>${resume.isSCI}</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>
						<div class="div_table">
							<h4>说明</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="base_info">
									<colgroup>
										<col width="12%"/>
										<col width="22%"/>
										<col width="12%"/>
										<col width="22%"/>
										<col width="12%"/>
										<col width="20%"/>
									</colgroup>
									<tr>
										<th>进修目的：</th>
										<td colspan="5"><textarea style="width: 98%; height: 100px" placeholder="${extInfo.studyAim}" readonly="readonly"></textarea></td>
									</tr>
									<tr>
										<th>本人从事专业现有业务水平：</th>
										<td colspan="5"><textarea style="width: 98%; height: 100px" placeholder="${extInfo.vocationalLevel}" readonly="readonly"></textarea></td>
									</tr>
									<%--<tr>--%>
										<%--<th>本人学历及社会经历：</th>--%>
										<%--<td colspan="5"><textarea style="width: 98%; height: 100px" placeholder="${extInfo.socialExperience}" readonly="readonly"></textarea></td>--%>
									<%--</tr>--%>
								</table>
							</div>
						</div>
						<div class="div_table">
							<h4>证书及文件</h4>
							<div>
								<table border="0" cellpadding="0" cellspacing="0" class="base_info">
									<colgroup>
										<col width="25%"/>
										<col width="15%"/>
										<col width="15%"/>
										<col width="15%"/>
										<col width="15%"/>
										<col width="15%"/>
									</colgroup>
									<tr>
										<th>身份证正面：</th>
										<td>
                                            <span style="display:${!empty extInfo.idNoUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
										</td>
										<th>身份证反面：</th>
										<td>
                                            <span style="display:${!empty extInfo.idNoUri2?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri2}" target="_blank">查看图片</a>]&#12288;
                                            </span>
										</td>
										<th>手持身份证：</th>
										<td>
                                            <span style="display:${!empty extInfo.idNoUri3?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri3}" target="_blank">查看图片</a>]&#12288;
                                            </span>
										</td>
									</tr>
									<tr>
										<th>毕业证图片：</th>
										<td>
											<%--${empty extInfo.graduatedNo?"无":extInfo.graduatedNo}&#12288;--%>
											<c:if test='${!empty extInfo.graduatedUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.graduatedUri}" target="_blank">查看图片</a>]&#12288;
										    	</span>
											</c:if>
										</td>
										<th>单位介绍信：</th>
										<td>
                                            <span style="display:${!empty extInfo.introductionUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.introductionUri}" target="_blank">查看附件</a>]&#12288;
                                            </span>
										</td>
										<th >感染四项检查结果(一周内有效)：</th>
										<td>
                                            <span style="display:${!empty extInfo.testResultUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.testResultUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
										</td>
									</tr>
									<tr>
										<th>遵守纪律协议书：</th>
										<td>
                                            <span style="display:${!empty extInfo.agreementUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.agreementUri}" target="_blank">查看附件</a>]&#12288;
                                            </span>
										</td>
										<th>进修申请表：</th>
										<td>
											<c:if test='${!empty extInfo.registerFormUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.registerFormUri}" target="_blank">查看附件</a>]&#12288;
										    	</span>
											</c:if>
										</td>
										<th></th>
										<td></td>
									</tr>
									<tr>
										<th><font color="red" id="zgzs"></font>医师（护士、护师）资格证书编号：</th>
										<td colspan="5">
											${empty extInfo.qualifiedNo?"无":extInfo.qualifiedNo}&#12288;
											<c:if test='${!empty extInfo.qualifiedUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.qualifiedUri}" target="_blank">查看图片</a>]&#12288;
										    	</span>
											</c:if>
										</td>
									</tr>
									<tr class="yszz">
										<th>医师（护士）执业证书编号：</th>
										<td colspan="5">
											${empty extInfo.certifiedNo?"无":extInfo.certifiedNo}&#12288;
											<c:if test='${!empty extInfo.certifiedUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}" target="_blank">查看图片</a>]&#12288;
										    	</span>
											</c:if>
										</td>
									</tr>
									<c:if test="${empty extInfo.regList}">
										<tr>
											<th>其它资质证书：</th>
											<td colspan="5"></td>
										</tr>
									</c:if>
									<c:forEach var="reg" items="${extInfo.regList}" varStatus="status">
										<tr>
											<th>其它资质证书${status.index+1}：</th>
											<td colspan="5">
												${empty reg.regNo?"无":reg.regNo}
												<span>
													[<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">查看图片</a>]&#12288;
										    	</span>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</div>

					</div>
				</div>
			</div>
			<div id="nextPage" class="button" style="margin: 30px;">
					<input class="btn_green" type="button" onclick="printApplForm('${stuUser.resumeFlow}');" value="打印申请表"/>
					<c:if test="${stuUser.stuStatusId eq 'Recruitted' and (stuUser.isPublish eq 'Y')}">
						<input class="btn_green" type="button" onclick="printRecruit('${stuUser.resumeFlow}');" value="打印报到通知书"/>
					</c:if>
			</div>
		</form>
	</div>
</div>
