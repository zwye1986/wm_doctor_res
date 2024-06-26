<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/zseyjxres/htmlhead-gzzyjxres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>



<script type="text/javascript">

	function audit(statusId,title){

		if("${stuStatusEnumUnPassed.id}"==statusId){
			if($.trim($("#auditAgree").val())==""){
				jboxTip("审核意见不能为空!");
				return;
			}
		}
		jboxConfirm("确认"+title+"?",function(){
			var data = {
				resumeFlow:"${stuUser.resumeFlow}",
				userFlow:"${user.userFlow}",
				reason:$("#auditAgree").val(),
				statusId:statusId,
				batchId:"${batchId}",
				processFlow:"${processFlow}"
			};
			jboxPost("<s:url value='/zseyjxres/head/auditOption'/>",data,
					function(resp){
						if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
							window.parent.search();
							jboxClose();
						}
					}
					,null,true);
		});
	}

</script>

<div class="infoAudit2" style="padding:0;">
	<div class="main_bd">
		<ul class="div_table">
			<li class="score_frame">
				<div class="div_table">
					<h4>基本信息</h4>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="base_info">
							<colgroup>
								<col width="12%"/>
								<col width="22%"/>
								<col width="15%"/>
								<col width="18%"/>
								<col width="15%"/>
								<col width="18%"/>
							</colgroup>
							<tr>
								<th>姓名：</th>
								<td>${extInfo.userName}</td>
								<th>出生日期：</th>
								<td colspan="2">${extInfo.userBirthday}</td>
								<td rowspan="4" style="text-align: center;padding-top:5px;">
									<img src="${sysCfgMap['upload_base_url']}/${extInfo.userHeadImg}"style="max-width: 130px;max-height: 170px;" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
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
									<c:forEach items="${dictTypeEnumGzzyjxUserTitleList}" var="dict">
										${extInfo.titleTypeId eq dict.dictId?dict.dictName:''}
									</c:forEach>
								</td>
								<th>职称：</th>
								<td>${stuUser.titleName}</td>
								<th>所在科室：</th>
								<td>${stuUser.deptName}</td>
							</tr>
							<tr>
								<th>参加工作时间：</th>
								<td>${stuUser.jobYear}</td>
								<th>职务：</th>
								<td>${stuUser.postName}</td>
								<th>执业资格：</th>
								<td>${stuUser.certifiedTypeName}</td>
							</tr>

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
							</tr>
							<tr>
								<th>毕业时间：</th>
								<td>${extInfo.graduationDate}</td>
								<th>最高学历开始时间：</th>
								<td>${stuUser.maxEduBdate}</td>
								<th>最高学历结束时间：</th>
								<td>${stuUser.maxEduEdate}</td>
							</tr>
							<tr>
								<th>个人专业：</th>
								<td>${extInfo.userMajor}</td>
								<th colspan="2">是否我院教学基地或帮扶协议单位：</th>
								<td colspan="2">
									<c:if test="${extInfo.isOwnOrg eq 'own'}">我院教学基地</c:if>
									<c:if test="${extInfo.isOwnOrg eq 'other'}">帮扶协议单位</c:if>
									<c:if test="${extInfo.isOwnOrg eq 'N'}">否</c:if>
								</td>
							</tr>
							<tr>
								<th>单位性质：</th>
								<td>
									<c:forEach items="${unitPropertyEnumList}" var="unitProperty">
										${stuUser.unitpropertyId eq unitProperty.id?unitProperty.name:''}
									</c:forEach>
								</td>
								<th>单位等级：</th>
								<td>
									<c:forEach items="${unitRankEnumList}" var="unitRank">
										${stuUser.unitrankId eq unitRank.id?unitRank.name:''}
									</c:forEach>
								</td>
								<th>单位级别</th>
								<td>
									<c:forEach items="${unitLevelEnumList}" var="unitLevel">
										${stuUser.unitlevelId eq unitLevel.id?unitLevel.name:''}
									</c:forEach>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="div_table">
					<h4>进修专业信息</h4>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="grid">
							<colgroup>
								<col width="50%"/>
								<col width="30%"/>
								<col width="20%"/>
							</colgroup>
							<tr style="font-weight: bold;">
								<th style="text-align: center;">进修专业</th>
								<th style="text-align: center;">进修时段</th>
								<th style="text-align: center;">进修时间（月）</th>
							</tr>
							<c:forEach var="speForm" items="${extInfo.speFormList}">
								<tr>
									<td>
											${speForm.speName}
									</td>
									<td>
										<input   type="text" value="${speForm.beginDate}" readonly="readonly" style="width: 30%" />
										-
										<input   type="text"  value="${speForm.endDate}" readonly="readonly" style="width: 30%" />
									</td>
									<td>
											${speForm.stuTimeId}
									</td>
								</tr>
							</c:forEach>

						</table>
					</div>
				</div>
				<div class="div_table">
					<h4>本人联系方式</h4>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="base_info">
							<colgroup>
								<col width="12%"/>
								<col width="16%"/>
								<col width="16%"/>
								<col width="22%"/>
								<col width="12%"/>
								<col width="22%"/>
							</colgroup>
							<tr>
								<th>手机号码：</th>
								<td>${extInfo.userPhone}</td>
								<th>电子邮箱：</th>
								<td>${extInfo.userEmail}</td>
								<th>办公电话：</th>
								<td>${extInfo.oph}</td>
							</tr>
							<tr>
								<th>邮政编码：</th>
								<td>${extInfo.postCodes}</td>
								<th>长途区号：</th>
								<td>${extInfo.areaCode}</td>
								<th>传真电话：</th>
								<td>${extInfo.faxTelephone}</td>
							</tr>
							<tr>
								<th>选送单位：</th>
								<td>${stuUser.sendComName}</td>
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
							<c:if test="${empty extInfo.projectList}">
								<tr>
									<td colspan="7">暂无信息！</td>
								</tr>
							</c:if>
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
							<c:if test="${empty extInfo.thesisList}">
								<tr>
									<td colspan="8">暂无信息！</td>
								</tr>
							</c:if>
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
								<col width="30%"/>
								<col width="70%"/>
							</colgroup>
							<tr>
								<th>身份证图片：</th>
								<td>
                                            <span style="display:${!empty extInfo.idNoUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri}" target="_blank">查看图片</a>]&#12288;
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
							</tr>
							<tr>
								<th>单位介绍信：</th>
								<td>
                                            <span style="display:${!empty extInfo.introductionUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.introductionUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
								</td>
							</tr>
							<%--<tr>
								<th width="20%">感染四项检查结果(一周内有效)：</th>
								<td colspan="3">
                                            <span style="display:${!empty extInfo.testResultUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.testResultUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
								</td>
							</tr>
							<tr>
								<th width="28%">遵守纪律协议书：</th>
								<td colspan="3">
                                            <span style="display:${!empty extInfo.agreementUri?'':'none'} ">
                                                [<a href="${sysCfgMap['upload_base_url']}/${extInfo.agreementUri}" target="_blank">查看图片</a>]&#12288;
                                            </span>
								</td>
							</tr>--%>
							<tr>
								<th><font color="red" id="zgzs"></font>医师（护士、护师）资格证书编号：</th>
								<td>
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
								<td>
									${empty extInfo.certifiedNo?"无":extInfo.certifiedNo}&#12288;
									<c:if test='${!empty extInfo.certifiedUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}" target="_blank">查看图片</a>]&#12288;
										    	</span>
									</c:if>
								</td>
							</tr>
							<tr class="yszz">
								<th>执业证末次注册时间：</th>
								<td>
									${extInfo.cerLastRegisterDate}
								</td>
							</tr>
							<tr class="yszz">
								<th>医师执业地点、类别、范围：</th>
								<td>
									${extInfo.practiceContent}
								</td>
							</tr>
							<c:if test="${empty extInfo.regList}">
								<tr>
									<th>其它资质证书：</th>
									<td>无</td>
								</tr>
							</c:if>
							<c:forEach var="reg" items="${extInfo.regList}" varStatus="status">
								<tr>
									<th>其它资质证书${status.index+1}：</th>
									<td>
											${empty reg.regNo?"无":reg.regNo}
												<span>
													[<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">查看图片</a>]&#12288;
										    	</span>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<th>进修生申请登记表：</th>
								<td>
									<c:if test='${!empty extInfo.registerFormUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.registerFormUri}" target="_blank">查看图片</a>]&#12288;
										    	</span>
									</c:if>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</li>
		</ul>
	</div>
	<c:if test="${stuUser.stuStatusId eq stuStatusEnumRecruitted.id}">
	<div class="div_table">
		<form id="payInfoForm" enctype="multipart/form-data">
			<h4>缴费信息</h4>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<tr>
					<th>进修费：</th>
					<td>
						<input id="tuition" class='input validate[custom[number]]' readonly="readonly" type="text" name="extInfo.tuition" value="${extInfo.tuition}"  style="width: 150px;height: 22px;margin-left: 0px"/>元
					</td>
					<th>材料费：</th>
					<td>
						<input id="according" class='input validate[custom[number]]' readonly="readonly" type="text" name="extInfo.according" value="${extInfo.according}"  style="width: 150px;height: 22px; margin-left: 0px"/>元
					</td>
				</tr>
				<tr>
					<th>住宿费：</th>
					<td>
						<input id="hotelExpense" class='input validate[custom[number]]' readonly="readonly" type="text" name="extInfo.hotelExpense" value="${extInfo.hotelExpense}"  style="width: 150px;height: 22px;margin-left: 0px"/>元
					</td>
					<th>报到时间：</th>
					<td>
						<input id="reportDate" class='input' type="text" readonly="readonly" name="extInfo.reportDate"  value="${extInfo.reportDate}" style="width: 150px;height: 21px;margin-left: 2px"/>
					</td>
						<%--<th>工作服：</th>--%>
						<%--<td>--%>
							<%--<input id="coverallNum" class='input validate[custom[integer],min[0]]' readonly="readonly" type="text" name="extInfo.coverallNum" value="${extInfo.coverallNum}" placeholder="件数"  style="width: 80px;height: 22px;margin-left: 0px"/>*65元/件--%>
						<%--</td>--%>
				</tr>
			</table>
		</form>
	</div>
	</c:if>
	<div class="div_table" <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">style="display: none;" </c:if>>
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
		    <caption style="text-align: left;font-weight: bolder;">审核意见</caption>
			<tr>
			    <td colspan="4" style="padding-top:10px;padding-left:0;">
				    <textarea id="auditAgree"></textarea>
				</td>
			</tr>
		</table>
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<c:if test="${isShow eq GlobalConstant.FLAG_Y}">
				<c:if test="${statusId eq stuStatusEnumPassing.id}">
					<input type="button" style="width:110px;" class="btn_green" onclick="audit('${stuStatusEnumPassed.id}','审核通过');" value="通&#12288;过"/>
					<input type="button" style="width:110px;" class="btn_red" onclick="audit('${stuStatusEnumUnPassed.id}','审核不通过');" value="不通过"/>
				</c:if>
				<c:if test="${statusId eq stuStatusEnumPassed.id}">
					<input type="button" style="width:110px;" class="btn_green" onclick="reAudit('${stuUser.resumeFlow}')" value="重新审核"/>
				</c:if>
			</c:if>
			<input type="button" style="width:110px;" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
		</div>
	</div>
</div>
