<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/jsp/gzzyjxres/htmlhead-gzzyjxres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>

<script type="text/javascript">
	$(document).ready(function(){
		calculate();
	})

	/*
	计算进修总费用
	 */
	function calculate(){
		//押金
		var according = Number($("#according").val());
		//工作服
		var coveralFee = Number($("#coverallNum").val())*Number(${workCloths});

		//住宿费
		var  hotelExpense=0;

		if( typeof ($("#hotelExpense").val())!='undefined'){
			hotelExpense = Number($("#hotelExpense").val());
		}
		//学费
		var tuition = Number($("#tuition").val());

		$("#totalFee").val(Number(according+coveralFee+hotelExpense+tuition));
	}

	function audit(statusId,title){
		if(title=="录取"){
			if($("#coverallNum").val()==""){
				jboxTip("请填写工作服件数！");
				return;
			}
			if($("#hotelExpense").val()==""){
				jboxTip("请填写住宿费！");
				return;
			}
		}
		if(false == $("#payInfoForm").validationEngine("validate")){
			return;
		}
		if("${stuStatusEnumUnPassed.id}"==statusId||statusId=="${stuStatusEnumUnRecruitted.id}"){
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
				tuition:$("#tuition").val(),//学费
				according:$("#according").val(),//押金
				hotelExpense:$("#hotelExpense").val(), //住宿费
				coverallNum:$("#coverallNum").val(), //工作服数量
				totalFee:$("#totalFee").val() //进修总费用
			};
			jboxPost("<s:url value='/gzzyjxres/hospital/auditOption'/>",data,
					function(resp){
						if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
							window.parent.search();
							jboxClose();
						}
					}
					,null,true);
		});
	}
	//退回操作
	function returnInfo(resumeFlow){
		if(false == $("#payInfoForm").validationEngine("validate")){
			return;
		}
		if($.trim($("#auditAgree").val())==""){
			jboxTip("审核意见不能为空!");
			return;
		}
		var reason = $("#auditAgree").val();
		var userFlow = "${user.userFlow}";
		jboxConfirm("确认退回?", function(){
			jboxPost("<s:url value='/gzzyjxres/hospital/returnInfo'/>",{"resumeFlow":resumeFlow,"reason":reason,"userFlow":userFlow}, function(resp){
				jboxTip(resp);
				window.parent.search();
				jboxClose();
			} , null , true);
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
								<col width="15%"/>
								<col width="19%"/>
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
								<%--<th>所在科室：</th>--%>
								<%--<td>${stuUser.deptName}</td>--%>
								<th></th>
								<td></td>
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
								<%--<td>${stuUser.speName}</td>--%>
								<%--<th>进修时间：</th>--%>
								<%--<td>${stuUser.stuTimeName}</td>--%>
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
									<input type="hidden" id="isOwnOrg"value="${extInfo.isOwnOrg}"/>
									<c:if test="${extInfo.isOwnOrg eq 'own'}">我院教学基地</c:if>
									<c:if test="${extInfo.isOwnOrg eq 'other'}">帮扶协议单位</c:if>
									<c:if test="${extInfo.isOwnOrg eq 'N'}">否</c:if>
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
								<col width="60%"/>
								<col width="40%"/>
							</colgroup>
							<tr style="font-weight: bold;">
								<th style="text-align: center;">进修专业</th>
								<th style="text-align: center;">进修时间</th>
							</tr>
							<c:forEach var="speForm" items="${extInfo.speFormList}">
								<tr>
									<td>
											${speForm.speName}
									</td>
									<td>
										<c:set var="unit" value="个月"></c:set>
											${speForm.stuTimeId}${unit}
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
								<col width="20%"/>
								<col width="30%"/>
								<col width="20%"/>
								<col width="30%"/>
							</colgroup>
							<tr>
								<th>手机号码：</th>
								<td>${extInfo.userPhone}</td>
							<%--	<th>电子邮箱：</th>
								<td>${extInfo.userEmail}</td>--%>
								<th>选送单位：</th>
								<td colspan="3">${stuUser.sendComName}</td>
							</tr>
							<tr>
								<th>医院等级：</th>
								<td>${stuUser.hospitalLevelName}</td>
								<th>选送单位详细地址：</th>
								<td colspan="3">${stuUser.sendComAddress}</td>
							</tr>
							<tr>
								<th>邮政编码：</th>
								<td>${extInfo.postCodes}</td>
								<th></th>
								<td colspan="3"></td>
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
								<col width="26%"/>
								<col width="10%"/>
								<col width="15%"/>
								<col width="10%"/>
								<col width="24%"/>
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
								<th>最高学历证书图片：</th>
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
								<th>感染四项检查结果(一周内有效)：</th>
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
								<th>进修生申请表：</th>
								<td colspan="3">
									<c:if test='${!empty extInfo.registerFormUri}'>
											    <span>
													[<a href="${sysCfgMap['upload_base_url']}/${extInfo.registerFormUri}" target="_blank">查看附件</a>]&#12288;
										    	</span>
									</c:if>
								</td>
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
								<td colspan="5">无</td>
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
			</li>
		</ul>
	</div>
	<c:if test="${stuUser.stuStatusId ne stuStatusEnumPassing.id and stuUser.stuStatusId ne stuStatusEnumUnPassed.id}">
	<div class="div_table">
		<form id="payInfoForm" enctype="multipart/form-data">
			<h4>缴费信息</h4>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<tr>
					<th>押金：</th>
					<td>
						<input id="according" class='input validate[custom[number]]' <%--<c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">--%>readonly="readonly"<%--</c:if>--%> type="text" name="extInfo.according" value="${deposit}"  style="width: 150px;height: 22px; margin-left: 0px"/>元
					</td>
					<th>学费：</th>
					<td>
						<input id="tuition" class='input validate[custom[number]]' <%--<c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">--%>readonly="readonly"<%--</c:if> --%>type="text" name="extInfo.tuition" value="${fee}"  style="width: 150px;height: 22px;margin-left: 0px"/>元
					</td>
				</tr>
				<tr>
					<c:if test="${stuUser.isPutup eq '不住宿'}">
					<th>工作服：</th>
					<td>
						<input id="coverallNum" class='input validate[custom[integer],min[0]]' <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">readonly="readonly"</c:if> type="text" name="extInfo.coverallNum" value="${extInfo.coverallNum}" placeholder="件数"  style="width: 150px;height: 22px;margin-left: 0px" onchange="calculate()"/>件
					</td>
					<th>进修总费用：</th>
					<td>
							<input id="totalFee" class='input validate[custom[number]]' <%--<c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">--%>readonly="readonly"<%--</c:if>--%> type="text" name="extInfo.totalFee" <%--value="${extInfo.totalFee}" --%> style="width: 150px;height: 22px;margin-left: 0px"/>元
					</td>
					</c:if>
					<c:if test="${stuUser.isPutup ne '不住宿'}">
					<th>工作服：</th>
					<td>
						<input id="coverallNum" class='input validate[custom[integer],min[0]]' <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">readonly="readonly"</c:if> type="text" name="extInfo.coverallNum" value="${extInfo.coverallNum}" placeholder="件数"  style="width: 150px;height: 22px;margin-left: 0px" onchange="calculate()"/>件
					</td>
					<th>住宿费：</th>
					<td>
						<input id="hotelExpense" class='input validate[custom[number]]' <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">readonly="readonly"</c:if> type="text" name="extInfo.hotelExpense" value="${extInfo.hotelExpense}"  onchange="calculate()" style="width: 150px;height: 22px;margin-left: 0px" />元
					</td>
					</c:if>
				</tr>
				<c:if test="${stuUser.isPutup ne '不住宿'}">
				<tr>
					<th>进修总费用：</th>
					<td colspan="3">
						<input id="totalFee" class='input validate[custom[number]]' <%--<c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">--%>readonly="readonly"<%--</c:if>--%> type="text" name="extInfo.totalFee"  style="width: 150px;height: 22px;margin-left: 0px"/>元
					</td>
				</tr>
				</c:if>
			</table>
		</form>
	</div>
	</c:if>
	<div class="div_table" <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">style="display: none;" </c:if>>
		<table border="0" width="100%" cellspacing="0" cellpadding="0">
		    <caption style="text-align: left;font-weight: bolder;">审核意见</caption>
			<tr>
			    <td colspan="4" style="padding-top:10px;padding-left:0;">
				    <select id="auditAgree" class="select" style="width: 150px">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumGzzyjxSuggestionList}" var="dict">
							<option value="${dict.dictName}">${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<c:if test="${stuUser.stuStatusId eq stuStatusEnumPassing.id}">
			<input type="button" style="width:100px;" class="btn_green" onclick="audit('${stuStatusEnumPassed.id}','审核通过');" value="通&#12288;过"/>
			<input type="button" style="width:100px;" class="btn_red" onclick="audit('${stuStatusEnumUnPassed.id}','审核不通过');" value="不通过"/>
			<input type="button" style="width:100px;" class="btn_green" onclick="returnInfo('${stuUser.resumeFlow}')" value="退&#12288;回"/>
			</c:if>
			<c:if test="${stuUser.stuStatusId eq stuStatusEnumPassed.id}">
			<input type="button" style="width:100px;" class="btn_green" onclick="audit('${stuStatusEnumRecruitted.id}','录取');" value="录&#12288;取"/>
			<input type="button" style="width:100px;" class="btn_red" onclick="audit('${stuStatusEnumUnRecruitted.id}','不录取');" value="不录取"/>
			</c:if>
			<input type="button" style="width:100px;" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
		</div>
	</div>
</div>
