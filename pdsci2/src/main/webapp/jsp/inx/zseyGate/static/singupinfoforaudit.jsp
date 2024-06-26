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

	$(document).ready(function(){
		$('.datepicker').datepicker();
	});

	$('.datepicker').datepicker();
	function audit(statusId,title){
		if(title=="待科室审核"){
			if($("#tuition").val()==""){
				jboxTip("请填写进修费！");
				return;
			}
			if($("#according").val()==""){
				jboxTip("请填写材料费！");
				return;
			}
//			if($("#coverallNum").val()==""){
//				jboxTip("请填写工作服件数！");
//				return;
//			}
			if($("#hotelExpense").val()==""){
				jboxTip("请填写住宿费！");
				return;
			}
			if($("#reportDate").val()==""){
				jboxTip("请填写报到时间！");
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
				tuition:$("#tuition").val(), //进修费
				according:$("#according").val(),//材料费
//				coverallNum:$("#coverallNum").val()
				hotelExpense:$("#hotelExpense").val(),//住宿费
				reportDate:$("#reportDate").val()//报到时间

			};
			jboxPost("<s:url value='/zseyjxres/hospital/auditOption'/>",data,
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
			jboxPost("<s:url value='/zseyjxres/hospital/returnInfo'/>",{"resumeFlow":resumeFlow,"reason":reason,"userFlow":userFlow}, function(resp){
				jboxTip(resp);
				window.parent.search();
				jboxClose();
			} , null , true);
		});
	}

	function pass(){
		jboxTip('用户名密码已发送到邮箱');
		setTimeout(function(){
			jboxClose();
		},1000)
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
								<td>测试账号</td>
								<th>出生日期：</th>
								<td colspan="2">${extInfo.userBirthday}1999-11-11</td>
								<td rowspan="4" style="text-align: center;padding-top:5px;">
									<img src="${sysCfgMap['upload_base_url']}/${extInfo.userHeadImg}"style="max-width: 130px;max-height: 170px;" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
								</td>
							</tr>
							<tr>
								<th>性别：</th>
								<td>${extInfo.sexName}男</td>
								<th>年龄：</th>
								<td colspan="2">${stuUser.userAge}18</td>
							</tr>
							<tr>
								<th>身份证号：</th>
								<td>430482198811045871</td>
								<th>身份证图片：</th>
								<td colspan="2">[查看]</td>
							</tr>
							<tr>
								<th>政治面貌：</th>
								<td>${extInfo.politicsStatusName}党员</td>
								<th>籍贯：</th>
								<td colspan="2">${extInfo.nativePlaceProvName}${extInfo.nativePlaceCityName}内蒙古</td>
							</tr>
							<tr>
								<th>民族：</th>
								<td>
									内蒙古族
								</td>
								<th>手机号码：</th>
								<td>${stuUser.titleName}13333333333</td>
								<th>个人专业：</th>
								<td>${stuUser.deptName}内科</td>
							</tr>
							<tr>
								<th>最高学历：</th>
								<td>${stuUser.jobYear}本科</td>
								<th>最高学历证书图片：</th>
								<td>${stuUser.postName}[查看]</td>
								<th>电子邮箱：</th>
								<td>${stuUser.certifiedTypeName}www@qq.com</td>
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
								<th>拟报科室：</th>
								<td>${extInfo.workClother}内科</td>
								<th></th>
								<td></td>
								<th></th>
								<td></td>
							</tr>
							<%--<tr>--%>
								<%--<th>最高学历：</th>--%>
								<%--<td>${stuUser.maxEduName}</td>--%>
								<%--<th>毕业学校：</th>--%>
								<%--<td>${stuUser.schoolName}</td>--%>
								<%--<th>毕业专业：</th>--%>
								<%--<td>临床医学</td>--%>
								<%--<th>--%>
								<%--是否熟练电脑：--%>
								<%--</th>--%>
								<%--<td>--%>
								<%--${stuUser.isComputer eq 'Y'? '是':'否'}--%>
								<%--</td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<th>毕业时间：</th>--%>
								<%--<td>${extInfo.graduationDate}</td>--%>
								<%--<th>最高学历开始时间：</th>--%>
								<%--<td>${stuUser.maxEduBdate}</td>--%>
								<%--<th>最高学历结束时间：</th>--%>
								<%--<td>${stuUser.maxEduEdate}</td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<th>个人专业：</th>--%>
								<%--<td>${extInfo.userMajor}</td>--%>
								<%--<th colspan="2">是否我院教学基地或帮扶协议单位：</th>--%>
								<%--<td colspan="2">--%>
									<%--<c:if test="${extInfo.isOwnOrg eq 'own'}">我院教学基地</c:if>--%>
									<%--<c:if test="${extInfo.isOwnOrg eq 'other'}">帮扶协议单位</c:if>--%>
									<%--<c:if test="${extInfo.isOwnOrg eq 'N'}">否</c:if>--%>
								<%--</td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<th>单位性质：</th>--%>
								<%--<td>--%>
									<%--<c:forEach items="${unitPropertyEnumList}" var="unitProperty">--%>
										<%--${stuUser.unitpropertyId eq unitProperty.id?unitProperty.name:''}--%>
									<%--</c:forEach>--%>
								<%--</td>--%>
								<%--<th>单位等级：</th>--%>
								<%--<td>--%>
									<%--<c:forEach items="${unitRankEnumList}" var="unitRank">--%>
										 <%--${stuUser.unitrankId eq unitRank.id?unitRank.name:''}--%>
									<%--</c:forEach>--%>
								<%--</td>--%>
								<%--<th>单位级别</th>--%>
								<%--<td>--%>
									<%--<c:forEach items="${unitLevelEnumList}" var="unitLevel">--%>
										 <%--${stuUser.unitlevelId eq unitLevel.id?unitLevel.name:''}--%>
									<%--</c:forEach>--%>
								<%--</td>--%>
							<%--</tr>--%>
						</table>
					</div>
				</div>
				<%--<div class="div_table">--%>
					<%--<h4>进修专业信息</h4>--%>
					<%--<div>--%>
						<%--<table border="0" cellpadding="0" cellspacing="0" class="grid">--%>
							<%--<colgroup>--%>
								<%--<col width="50%"/>--%>
								<%--<col width="30%"/>--%>
								<%--<col width="20%"/>--%>
							<%--</colgroup>--%>
							<%--<tr style="font-weight: bold;">--%>
								<%--<th style="text-align: center;">进修专业</th>--%>
								<%--<th style="text-align: center;">进修时段</th>--%>
								<%--<th style="text-align: center;">进修时间（月）</th>--%>
							<%--</tr>--%>
							<%--<c:forEach var="speForm" items="${extInfo.speFormList}">--%>
								<%--<tr>--%>
									<%--<td>--%>
											<%--${speForm.speName}--%>
									<%--</td>--%>
									<%--<td>--%>
										<%--<input   type="text" value="${speForm.beginDate}" readonly="readonly" style="width: 30%" />--%>
										<%-----%>
										<%--<input   type="text"  value="${speForm.endDate}" readonly="readonly" style="width: 30%" />--%>
									<%--</td>--%>
									<%--<td>--%>
											<%--${speForm.stuTimeId}--%>
									<%--</td>--%>
								<%--</tr>--%>
							<%--</c:forEach>--%>

						<%--</table>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="div_table">--%>
					<%--<h4>本人联系方式</h4>--%>
					<%--<div>--%>
						<%--<table border="0" cellpadding="0" cellspacing="0" class="base_info">--%>
							<%--<colgroup>--%>
								<%--<col width="12%"/>--%>
								<%--<col width="16%"/>--%>
								<%--<col width="16%"/>--%>
								<%--<col width="22%"/>--%>
								<%--<col width="12%"/>--%>
								<%--<col width="22%"/>--%>
							<%--</colgroup>--%>
							<%--<tr>--%>
								<%--<th>手机号码：</th>--%>
								<%--<td>${extInfo.userPhone}</td>--%>
								<%--<th>电子邮箱：</th>--%>
								<%--<td>${extInfo.userEmail}</td>--%>
								<%--<th>办公电话：</th>--%>
								<%--<td>${extInfo.oph}</td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<th>邮政编码：</th>--%>
								<%--<td>${extInfo.postCodes}</td>--%>
								<%--<th>长途区号：</th>--%>
								<%--<td>${extInfo.areaCode}</td>--%>
								<%--<th>传真电话：</th>--%>
								<%--<td>${extInfo.faxTelephone}</td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<th>选送单位：</th>--%>
								<%--<td>${stuUser.sendComName}</td>--%>
								<%--<th>选送单位详细地址：</th>--%>
								<%--<td colspan="3">${stuUser.sendComAddress}</td>--%>
							<%--</tr>--%>
						<%--</table>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="div_table">--%>
					<%--<h4>工作经历（含外单位进修经历）</h4>--%>
					<%--<div>--%>
						<%--<table border="0" cellpadding="0" cellspacing="0" class="grid">--%>
							<%--<colgroup>--%>
								<%--<col width="22%"/>--%>
								<%--<col width="22%"/>--%>
								<%--<col width="22%"/>--%>
								<%--<col width="22%"/>--%>
							<%--</colgroup>--%>
							<%--<tr style="font-weight: bold;">--%>
								<%--<th>起止时间</th>--%>
								<%--<th>工作单位</th>--%>
								<%--<th>从事工作</th>--%>
								<%--<th>职务</th>--%>
							<%--</tr>--%>
							<%--<c:forEach items="${workDateList}" var="workDate">--%>
								<%--<c:forEach var="resume" items="${extInfo.workResumeList}" varStatus="status">--%>
									<%--<c:if test="${workDate eq resume.clinicalRoundDate}">--%>
										<%--<tr>--%>
											<%--<td>${resume.clinicalRoundDate}</td>--%>
											<%--<td>${resume.hospitalName}</td>--%>
											<%--<td>${resume.workDescription}</td>--%>
											<%--<td>${resume.postName}</td>--%>
										<%--</tr>--%>
									<%--</c:if>--%>
								<%--</c:forEach>--%>
							<%--</c:forEach>--%>
						<%--</table>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="div_table">--%>
					<%--<h4>学历信息（从高中学历写起）</h4>--%>
					<%--<div>--%>
						<%--<table border="0" cellpadding="0" cellspacing="0" class="grid">--%>
							<%--<colgroup>--%>
								<%--<col width="23%"/>--%>
								<%--<col width="20%"/>--%>
								<%--<col width="20%"/>--%>
								<%--<col width="10%"/>--%>
								<%--<col width="10%"/>--%>
								<%--<col width="10%"/>--%>
							<%--</colgroup>--%>
							<%--<tr style="font-weight: bold;">--%>
								<%--<th style="text-align: center;">起止时间</th>--%>
								<%--<th style="text-align: center;">学校名称</th>--%>
								<%--<th style="text-align: center;">专业名称</th>--%>
								<%--<th style="text-align: center;">学制</th>--%>
								<%--<th style="text-align: center;">学历</th>--%>
								<%--<th style="text-align: center;">学位</th>--%>
							<%--</tr>--%>
							<%--<c:forEach items="${eduDateList}" var="eduDate">--%>
								<%--<c:forEach var="resume" items="${extInfo.educationList}" varStatus="status">--%>
									<%--<c:if test="${eduDate eq resume.eduRoundDate}">--%>
										<%--<tr>--%>
											<%--<td>${resume.eduRoundDate}</td>--%>
											<%--<td>${resume.schoolName}</td>--%>
											<%--<td>${resume.speName}</td>--%>
											<%--<td>${resume.length}</td>--%>
											<%--<td>${resume.education}</td>--%>
											<%--<td>${resume.degree}</td>--%>
										<%--</tr>--%>
									<%--</c:if>--%>
								<%--</c:forEach>--%>
							<%--</c:forEach>--%>
						<%--</table>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="div_table">--%>
					<%--<h4>科研信息</h4>--%>
					<%--<div>--%>
						<%--<table border="0" cellpadding="0" cellspacing="0" class="grid">--%>
							<%--<colgroup>--%>
								<%--<col width="10%"/>--%>
								<%--<col width="20%"/>--%>
								<%--<col width="10%"/>--%>
								<%--<col width="10%"/>--%>
								<%--<col width="10%"/>--%>
								<%--<col width="10%"/>--%>
								<%--<col width="20%"/>--%>
							<%--</colgroup>--%>
							<%--<tr style="font-weight: bold;">--%>
								<%--<th style="text-align: left;" colspan="7">一、课题</th>--%>
							<%--</tr>--%>
							<%--<tr style="font-weight: bold;">--%>
								<%--<th>序号</th>--%>
								<%--<th style="text-align: center;">标题内容</th>--%>
								<%--<th style="text-align: center;">作者名次</th>--%>
								<%--<th style="text-align: center;">课题级别</th>--%>
								<%--<th style="text-align: center;">课题获批时间</th>--%>
								<%--<th style="text-align: center;">合同书号</th>--%>
								<%--<th style="text-align: center;">获奖情况（何部门批准<br/>及奖励名称、等级）</th>--%>
							<%--</tr>--%>
							<%--<c:if test="${empty extInfo.projectList}">--%>
								<%--<tr>--%>
									<%--<td colspan="7">暂无信息</td>--%>
								<%--</tr>--%>
							<%--</c:if>--%>
							<%--<c:forEach var="resume" items="${extInfo.projectList}" varStatus="status">--%>
								<%--<tr>--%>
									<%--<td>${status.count}</td>--%>
									<%--<td>${resume.titleName}</td>--%>
									<%--<td>${resume.authorRank}</td>--%>
									<%--<td>${resume.projectLevel}</td>--%>
									<%--<td>${resume.projectDate}</td>--%>
									<%--<td>${resume.pubNumber}</td>--%>
									<%--<td>${resume.awardSituation}</td>--%>
								<%--</tr>--%>
							<%--</c:forEach>--%>
						<%--</table>--%>
					<%--</div>--%>
					<%--<div>--%>
						<%--<table border="0" cellpadding="0" cellspacing="0" class="grid">--%>
							<%--<colgroup>--%>
								<%--<col width="7%"/>--%>
								<%--<col width="20%"/>--%>
								<%--<col width="10%"/>--%>
								<%--<col width="12%"/>--%>
								<%--<col width="10%"/>--%>
								<%--<col width="10%"/>--%>
								<%--<col width="20%"/>--%>
								<%--<col width="10%"/>--%>
							<%--</colgroup>--%>
							<%--<tr style="font-weight: bold;">--%>
								<%--<th style="text-align: left;" colspan="8">二、论文</th>--%>
							<%--</tr>--%>
							<%--<tr style="font-weight: bold;">--%>
								<%--<th>序号</th>--%>
								<%--<th style="text-align: center;">论文题目</th>--%>
								<%--<th style="text-align: center;">作者名次</th>--%>
								<%--<th style="text-align: center;">杂志名称</th>--%>
								<%--<th style="text-align: center;">杂志名称发表时间</th>--%>
								<%--<th style="text-align: center;">刊号</th>--%>
								<%--<th style="text-align: center;">获奖情况（何部门批准<br/>及奖励名称、等级）</th>--%>
								<%--<th style="text-align: center;">是否SCI或核心</th>--%>
							<%--</tr>--%>

							<%--<c:if test="${empty extInfo.thesisList}">--%>
								<%--<tr>--%>
									<%--<td colspan="8">暂无信息</td>--%>
								<%--</tr>--%>
							<%--</c:if>--%>
							<%--<c:forEach var="resume" items="${extInfo.thesisList}" varStatus="status">--%>
								<%--<tr>--%>
									<%--<td>${status.count}</td>--%>
									<%--<td>${resume.titleName}</td>--%>
									<%--<td>${resume.authorRank}</td>--%>
									<%--<td>${resume.pubName}</td>--%>
									<%--<td>${resume.pubDate}</td>--%>
									<%--<td>${resume.pubNumber}</td>--%>
									<%--<td>${resume.awardSituation}</td>--%>
									<%--<td>${resume.isSCI}</td>--%>
								<%--</tr>--%>
							<%--</c:forEach>--%>
						<%--</table>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="div_table">--%>
					<%--<h4>说明</h4>--%>
					<%--<div>--%>
						<%--<table border="0" cellpadding="0" cellspacing="0" class="base_info">--%>
							<%--<colgroup>--%>
								<%--<col width="12%"/>--%>
								<%--<col width="22%"/>--%>
								<%--<col width="12%"/>--%>
								<%--<col width="22%"/>--%>
								<%--<col width="12%"/>--%>
								<%--<col width="20%"/>--%>
							<%--</colgroup>--%>
							<%--<tr>--%>
								<%--<th>进修目的：</th>--%>
								<%--<td colspan="5"><textarea style="width: 98%; height: 100px" placeholder="${extInfo.studyAim}" readonly="readonly"></textarea></td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<th>本人从事专业现有业务水平：</th>--%>
								<%--<td colspan="5"><textarea style="width: 98%; height: 100px" placeholder="${extInfo.vocationalLevel}" readonly="readonly"></textarea></td>--%>
							<%--</tr>--%>
							<%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<th>本人学历及社会经历：</th>&ndash;%&gt;--%>
								<%--&lt;%&ndash;<td colspan="5"><textarea style="width: 98%; height: 100px" placeholder="${extInfo.socialExperience}" readonly="readonly"></textarea></td>&ndash;%&gt;--%>
							<%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
						<%--</table>--%>
					<%--</div>--%>
				<%--</div>--%>
				<%--<div class="div_table">--%>
					<%--<h4>证书及文件</h4>--%>
					<%--<div>--%>
						<%--<table border="0" cellpadding="0" cellspacing="0" class="base_info">--%>
							<%--<tr>--%>
								<%--<th width="20%">身份证图片：</th>--%>
								<%--<td colspan="3">--%>
                                            <%--<span style="display:${!empty extInfo.idNoUri?'':'none'} ">--%>
                                                <%--[<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri}" target="_blank">查看图片</a>]&#12288;--%>
                                            <%--</span>--%>
								<%--</td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<th>毕业证图片：</th>--%>
								<%--<td colspan="3">--%>
									<%--&lt;%&ndash;${empty extInfo.graduatedNo?"无":extInfo.graduatedNo}&#12288;&ndash;%&gt;--%>
									<%--<c:if test='${!empty extInfo.graduatedUri}'>--%>
											    <%--<span>--%>
													<%--[<a href="${sysCfgMap['upload_base_url']}/${extInfo.graduatedUri}" target="_blank">查看图片</a>]&#12288;--%>
										    	<%--</span>--%>
									<%--</c:if>--%>
								<%--</td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<th width="20%">单位介绍信：</th>--%>
								<%--<td colspan="3">--%>
                                            <%--<span style="display:${!empty extInfo.introductionUri?'':'none'} ">--%>
                                                <%--[<a href="${sysCfgMap['upload_base_url']}/${extInfo.introductionUri}" target="_blank">查看图片</a>]&#12288;--%>
                                            <%--</span>--%>
								<%--</td>--%>
							<%--</tr>--%>
						<%--&lt;%&ndash;	<tr>--%>
								<%--<th width="20%">感染四项检查结果(一周内有效)：</th>--%>
								<%--<td colspan="3">--%>
                                            <%--<span style="display:${!empty extInfo.testResultUri?'':'none'} ">--%>
                                                <%--[<a href="${sysCfgMap['upload_base_url']}/${extInfo.testResultUri}" target="_blank">查看图片</a>]&#12288;--%>
                                            <%--</span>--%>
								<%--</td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<th width="20%">遵守纪律协议书：</th>--%>
								<%--<td colspan="3">--%>
                                            <%--<span style="display:${!empty extInfo.agreementUri?'':'none'} ">--%>
                                                <%--[<a href="${sysCfgMap['upload_base_url']}/${extInfo.agreementUri}" target="_blank">查看图片</a>]&#12288;--%>
                                            <%--</span>--%>
								<%--</td>--%>
							<%--</tr>&ndash;%&gt;--%>
							<%--<tr>--%>
								<%--<th><font color="red" id="zgzs"></font>医师（护士、护师）资格证书编号：</th>--%>
								<%--<td colspan="3">--%>
									<%--${empty extInfo.qualifiedNo?"无":extInfo.qualifiedNo}&#12288;--%>
									<%--<c:if test='${!empty extInfo.qualifiedUri}'>--%>
											    <%--<span>--%>
													<%--[<a href="${sysCfgMap['upload_base_url']}/${extInfo.qualifiedUri}" target="_blank">查看图片</a>]&#12288;--%>
										    	<%--</span>--%>
									<%--</c:if>--%>
								<%--</td>--%>
							<%--</tr>--%>
							<%--<tr class="yszz">--%>
								<%--<th>医师（护士）执业证书编号：</th>--%>
								<%--<td colspan="3">--%>
									<%--${empty extInfo.certifiedNo?"无":extInfo.certifiedNo}&#12288;--%>
									<%--<c:if test='${!empty extInfo.certifiedUri}'>--%>
											    <%--<span>--%>
													<%--[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}" target="_blank">查看图片</a>]&#12288;--%>
										    	<%--</span>--%>
									<%--</c:if>--%>
								<%--</td>--%>
							<%--</tr>--%>
							<%--<tr class="yszz">--%>
								<%--<th>执业证末次注册时间：</th>--%>
								<%--<td colspan="3">--%>
									<%--${extInfo.cerLastRegisterDate}--%>
								<%--</td>--%>
							<%--</tr>--%>
							<%--<tr class="yszz">--%>
								<%--<th>医师执业地点、类别、范围：</th>--%>
								<%--<td colspan="3">--%>
									<%--${extInfo.practiceContent}--%>
								<%--</td>--%>
							<%--</tr>--%>
							<%--<c:if test="${empty extInfo.regList}">--%>
								<%--<tr>--%>
								<%--<th>其它资质证书：</th>--%>
								<%--<td>无</td>--%>
								<%--</tr>--%>
							<%--</c:if>--%>
							<%--<c:forEach var="reg" items="${extInfo.regList}" varStatus="status">--%>
								<%--<tr>--%>
									<%--<th>其它资质证书${status.index+1}：</th>--%>
									<%--<td>--%>
											<%--${empty reg.regNo?"无":reg.regNo}--%>
												<%--<span>--%>
													<%--[<a href="${sysCfgMap['upload_base_url']}/${reg.regUri}" target="_blank">查看图片</a>]&#12288;--%>
										    	<%--</span>--%>
									<%--</td>--%>
								<%--</tr>--%>
							<%--</c:forEach>--%>
							<%--<tr>--%>
								<%--<th width="20%">进修生申请登记表：</th>--%>
								<%--<td colspan="3">--%>
									<%--<c:if test='${!empty extInfo.registerFormUri}'>--%>
											    <%--<span>--%>
													<%--[<a href="${sysCfgMap['upload_base_url']}/${extInfo.registerFormUri}" target="_blank">查看图片</a>]&#12288;--%>
										    	<%--</span>--%>
									<%--</c:if>--%>
								<%--</td>--%>
							<%--</tr>--%>
						<%--</table>--%>
					<%--</div>--%>
				<%--</div>--%>
			</li>
		</ul>
	</div>

	<%--<c:if test="${stuUser.stuStatusId ne stuStatusEnumPassing.id}">--%>
	<%--<div class="div_table">--%>
		<%--<form id="payInfoForm" enctype="multipart/form-data">--%>
			<%--<h4>缴费信息</h4>--%>
			<%--<table border="0" cellpadding="0" cellspacing="0" class="base_info">--%>
				<%--<tr>--%>
					<%--<th>进修费：</th>--%>
					<%--<td>--%>
						<%--<input id="tuition" class='input validate[custom[number]]' <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">readonly="readonly"</c:if> type="text" name="extInfo.tuition" value="${extInfo.tuition}"  style="width: 150px;height: 22px;margin-left: 0px"/>元--%>
					<%--</td>--%>
					<%--<th>材料费：</th>--%>
					<%--<td>--%>
						<%--<input id="according" class='input validate[custom[number]]' <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">readonly="readonly"</c:if> type="text" name="extInfo.according" value="${extInfo.according}"  style="width: 150px;height: 22px; margin-left: 0px"/>元--%>
					<%--</td>--%>
					<%--&lt;%&ndash;<th>工作服：</th>&ndash;%&gt;--%>
					<%--&lt;%&ndash;<td>&ndash;%&gt;--%>
						<%--&lt;%&ndash;<input id="coverallNum" class='input validate[custom[integer],min[0]]' <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">readonly="readonly"</c:if> type="text" name="extInfo.coverallNum" value="${extInfo.coverallNum}" placeholder="件数"  style="width: 80px;height: 22px;margin-left: 0px"/>*65元/件&ndash;%&gt;--%>
					<%--&lt;%&ndash;</td>&ndash;%&gt;--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<c:if test="${stuUser.isPutup ne '不住宿'}">--%>
					<%--<th>住宿费：</th>--%>
					<%--<td>--%>
						<%--<input id="hotelExpense" class='input validate[required]' <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">readonly="readonly"</c:if> type="text" name="extInfo.hotelExpense" value="${extInfo.hotelExpense}"  style="width: 150px;height: 22px;margin-left: 0px"/>元--%>
					<%--</td>--%>
					<%--</c:if>--%>
					<%--<th>报到时间：</th>--%>
					<%--<td <c:if test="${stuUser.isPutup eq '不住宿'}">colspan="3"</c:if>>--%>
						<%--<input id="reportDate" class='input validate[required] datepicker' <c:if test="${param.isHide eq 'Y' or flag eq 'Y'}">disabled="disabled"</c:if>  type="text" name="extInfo.reportDate"  value="${extInfo.reportDate}" style="width: 150px;height: 21px;margin-left: 2px"/>--%>
					<%--</td>--%>
				<%--</tr>--%>
			<%--</table>--%>
		<%--</form>--%>
	<%--</div>--%>
	<%--</c:if>--%>
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
			<%--<c:if test="${stuUser.stuStatusId eq stuStatusEnumPassing.id}">--%>
			<input type="button" style="width:110px;" class="btn_green" onclick="pass()" value="通&#12288;过"/>
			<input type="button" style="width:110px;" class="btn_red" onclick="" value="不通过"/>
			<input type="button" style="width:110px;" class="btn_green" onclick="" value="退&#12288;回"/>
			<%--</c:if>--%>
			<c:if test="${stuUser.stuStatusId eq stuStatusEnumPassed.id}">
			<input type="button" style="width:110px;" class="btn_green" onclick="audit('${stuStatusEnumRecruitted.id}','录取');" value="录&#12288;取"/>
			<input type="button" style="width:110px;" class="btn_red" onclick="audit('${stuStatusEnumUnRecruitted.id}','不录取');" value="不录取"/>
			</c:if>
			<input type="button" style="width:110px;" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
		</div>
	</div>
</div>
