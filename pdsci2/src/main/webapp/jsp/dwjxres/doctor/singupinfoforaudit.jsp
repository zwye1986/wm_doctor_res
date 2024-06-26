<jsp:include page="/jsp/dwjxres/htmlhead-dwjxres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<script>

	function showImage(imgUrl)
	{
		var height=(window.screen.height)*0.9;
		var width=(window.screen.width)*0.9;
		var url="<s:url value='/jsp/dwjxres/doctor/showImage.jsp'/>?imgUrl="+imgUrl;
//		jboxOpen(url, "查看",1000,650);
		window.open(url);
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
								<col width="20%"/>
								<col width="15%"/>
								<col width="15%"/>
								<col width="15%"/>
								<col width="20%"/>
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
								<th>职称类别：</th>
								<td>
									<c:forEach items="${dictTypeEnumTitleGenreList}" var="dict">
										${extInfo.titleTypeId eq dict.dictId?dict.dictName:''}
									</c:forEach>
								</td>
								<th>职称：</th>
								<td colspan="2">${stuUser.titleName}</td>
							</tr>
							<tr>
								<th>
									选送单位纳税人识别号：
								</th>
								<td  colspan="5">
									${stuUser.identificationNumber}
								</td>
							</tr>
							<tr>
								<th>所在科室：</th>
								<td>${stuUser.deptName}</td>
								<th>工作年限：</th>
								<td>${stuUser.jobYear}</td>
								<th>职务：</th>
								<td>${stuUser.postName}</td>
							</tr>
							<tr>
								<th>执业资格：</th>
								<td>${stuUser.certifiedTypeName}</td>
								<c:if test="${extInfo.titleTypeName eq '护士' or stuUser.stuTimeId ne '短期培训'}">
									<th>进修专业：</th>
									<td>${stuUser.speName}</td>
								</c:if>
								<c:if test="${extInfo.titleTypeName ne '护士' and stuUser.stuTimeId eq '短期培训'}">
									<th>短期培训专业：</th>
									<td>${stuUser.speName}</td>
								</c:if>
								<c:if test="${extInfo.titleTypeName eq '护士' or stuUser.stuTimeId ne '短期培训'}">
									<th>进修时间：</th>
									<td>${stuUser.stuTimeName}</td>
								</c:if>
								<c:if test="${extInfo.titleTypeName ne '护士' and stuUser.stuTimeId eq '短期培训'}">
									<th>短期培训时间：</th>
									<td>${stuUser.stuTimeName}</td>
								</c:if>

							</tr>
							<tr>
								<c:if test="${extInfo.titleTypeName eq '护士' or stuUser.stuTimeId ne '短期培训'}">
									<th>进修批次：</th>
									<td>${stuUser.stuBatName}</td>
								</c:if>
								<c:if test="${extInfo.titleTypeName ne '护士' and stuUser.stuTimeId eq '短期培训'}">
									<th>短期培训批次：</th>
									<td>${stuUser.stuBatName}</td>
								</c:if>
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
								<th>最高学历开始时间：</th>
								<td>${stuUser.maxEduBdate}</td>
								<th>最高学历结束时间：</th>
								<td>${stuUser.maxEduEdate}</td>
								<th>是否熟练电脑：</th>
								<td>${stuUser.isComputer eq 'Y'? '是':'否'}</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="div_table">
					<h4>本人联系方式</h4>
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
								<th>手机号码：</th>
								<td>${extInfo.userPhone}</td>
							<%--	<th>电子邮箱：</th>
								<td>${extInfo.userEmail}</td>--%>
								<th>选送单位：</th>
								<td>${stuUser.sendComName}</td>
							</tr>
							<tr>
								<th>医院等级：</th>
								<td>${stuUser.hospitalLevelName}</td>
								<th>医院等级评定证明：</th>
								<td>
									<a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.hospitalLevelProveUri}');"class="btn showImg"  >查看图片</a>&#12288;
								</td>
							</tr>
							<tr>
								<th>选送单位详细地址：</th>
								<td colspan="3">${stuUser.sendComAddress}</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="div_table">
					<h4>工作经历</h4>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="grid">
							<colgroup>
								<col width="12%">
								<col width="22%"/>
								<col width="22%"/>
								<col width="22%"/>
								<col width="22%"/>
							</colgroup>
							<tr style="font-weight: bold;">
								<th>序号</th>
								<th>起止时间</th>
								<th>工作单位</th>
								<th>从事工作</th>
								<th>职务</th>
							</tr>
							<c:forEach var="resume" items="${extInfo.workResumeList}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td>${resume.clinicalRoundDate}</td>
									<td>${resume.hospitalName}</td>
									<td>${resume.workDescription}</td>
									<td>${resume.postName}</td>
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
								<c:if test="${extInfo.titleTypeName eq '护士' or stuUser.stuTimeId ne '短期培训'}">
									<th>进修目的：</th>
									<td colspan="5"><textarea style="width: 98%; height: 100px" placeholder="${extInfo.studyAim}" readonly="readonly"></textarea></td>
								</c:if>
								<c:if test="${extInfo.titleTypeName ne '护士' and stuUser.stuTimeId eq '短期培训'}">
									<th>短期培训目的：</th>
									<td colspan="5"><textarea style="width: 98%; height: 100px" placeholder="${extInfo.studyAim}" readonly="readonly"></textarea></td>
								</c:if>
							</tr>
							<tr>
								<th>本人从事专业现有业务水平：</th>
								<td colspan="5"><textarea style="width: 98%; height: 100px" placeholder="${extInfo.vocationalLevel}" readonly="readonly"></textarea></td>
							</tr>
						</table>
					</div>
				</div>
				<div class="div_table">
					<h4>证书及文件</h4>
					<div>
						<table border="0" cellpadding="0" cellspacing="0" class="base_info">
                            <colgroup>
                                <col width="30%"/>
                                <col width="20%"/>
                                <col width="20%"/>
                                <col width="30%"/>
                            </colgroup>
							<c:forEach var="reg" items="${extInfo.idNoUriList}" varStatus="status">
								<tr>
									<c:if test="${status.index eq 0}">
										<th rowspan="${fn:length(extInfo.idNoUriList)}">身份证图片：</th>
									</c:if>
									<td colspan="3">
                                                <span id="idNoUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  target="_blank">查看图片</a>&#12288;
                                                </span>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty extInfo.idNoUriList and not empty extInfo.idNoUri}">
								<tr class="yszz">
									<th>身份证图片：</th>
									<td colspan="3">
										<span id="idNoUri0Span" style="display:${!empty extInfo.idNoUri?'':'none'} ">
											<a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.idNoUri}');"class="btn showImg"  target="_blank">查看图片</a>&#12288;
										</span>
									</td>
								</tr>
							</c:if>
							<c:forEach var="reg" items="${extInfo.graduatedUriList}" varStatus="status">
								<tr>
									<c:if test="${status.index eq 0}">
										<th rowspan="${fn:length(extInfo.graduatedUriList)}">毕业证图片：</th>
									</c:if>
									<td colspan="3">
                                                <span id="graduatedUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  target="_blank">查看图片</a>&#12288;
                                                </span>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty extInfo.graduatedUriList and not empty extInfo.graduatedUri}">
								<tr class="yszz">
									<th>毕业证图片：</th>
									<td colspan="3">
										<span id="graduatedUri0Span" style="display:${!empty extInfo.graduatedUri?'':'none'} ">
											<a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.graduatedUri}');" class="btn showImg"  target="_blank">查看图片</a>&#12288;
										</span>
									</td>
								</tr>
							</c:if>
							<tr>
								<th><font color="red" id="zgzs"></font>医师（护士、护师）资格证书编号：</th>
								<td colspan="3">
									${empty extInfo.qualifiedNo?"无":extInfo.qualifiedNo}&#12288;
								</td>
							</tr>
							<c:forEach var="reg" items="${extInfo.qualifiedUriList}" varStatus="status">
								<tr>
									<c:if test="${status.index eq 0}">
										<th rowspan="${fn:length(extInfo.qualifiedUriList)}">医师（护士、护师）资格证书图片：</th>
									</c:if>
									<td colspan="3">
                                                <span id="qualifiedUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  target="_blank">查看图片</a>&#12288;
                                                </span>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty extInfo.qualifiedUriList and not empty extInfo.qualifiedUri}">
								<tr class="yszz">
									<th>医师（护士、护师）资格证书图片：</th>
									<td colspan="3">
										<span id="qualifiedUri0Span" style="display:${!empty extInfo.qualifiedUri?'':'none'} ">
											<a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.qualifiedUri}');"class="btn showImg"  target="_blank">查看图片</a>&#12288;
										</span>
									</td>
								</tr>
							</c:if>
							<tr class="yszz">
								<th>医师（护士）执业证书编号：</th>
								<td colspan="3">
									${empty extInfo.certifiedNo?"无":extInfo.certifiedNo}&#12288;
								</td>
							</tr>
							<c:forEach var="reg" items="${extInfo.certifiedUriList}" varStatus="status">
								<tr>
									<c:if test="${status.index eq 0}">
										<th rowspan="${fn:length(extInfo.certifiedUriList)}">医师（护士）执业证书图片：</th>
									</c:if>
									<td colspan="3">
                                                <span id="certifiedUri${status.index}Span" style="display:${!empty reg.regUri?'':'none'} ">
                                                    <a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  target="_blank">查看图片</a>&#12288;
                                                </span>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty extInfo.certifiedUriList and not empty extInfo.certifiedUri}">
								<tr class="yszz">
									<th>医师（护士）执业证书图片：</th>
									<td colspan="3">
										<span id="certifiedUri0Span" style="display:${!empty extInfo.certifiedUri?'':'none'} ">
											<a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.certifiedUri}');" class="btn showImg"  target="_blank">查看图片</a>&#12288;
										</span>
									</td>
								</tr>
							</c:if>
							<c:if test="${empty extInfo.regList}">
								<tr>
								<th>其它资质证书：</th>
								<td colspan="3"></td>
								</tr>
							</c:if>
							<c:forEach var="reg" items="${extInfo.regList}" varStatus="status">
								<tr>
									<th>其它资质证书${status.index+1}</th>
									<td colspan="3">
											${empty reg.regNo?"无":reg.regNo}
												<span>
													<a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${reg.regUri}');"class="btn showImg"  target="_blank">查看图片</a>&#12288;
                                                </span>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<th>进修生申请登记表：</th>
								<td colspan="3">
									<c:if test='${!empty extInfo.registerFormUri}'>
											    <span>
													<a href="javascript:void(0)" onclick="showImage('${sysCfgMap['upload_base_url']}/${extInfo.registerFormUri}');"class="btn showImg"  target="_blank">查看图片</a>&#12288;
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
			<c:if test="${stuUser.stuStatusId eq stuStatusEnumPassing.id}">
			<input type="button" style="width:100px;" class="btn_green" onclick="audit('${stuStatusEnumPassed.id}','审核通过','${extInfo.titleTypeName}');" value="通&#12288;过"/>
			<input type="button" style="width:100px;" class="btn_red" onclick="audit('${stuStatusEnumUnPassed.id}','审核不通过','${extInfo.titleTypeName}');" value="不通过"/>
			<input type="button" style="width:100px;" class="btn_green" onclick="returnInfo('${stuUser.resumeFlow}','${extInfo.titleTypeName}')" value="退&#12288;回"/>
			</c:if>
			<c:if test="${stuUser.stuStatusId eq stuStatusEnumPassed.id}">
			<input type="button" style="width:100px;" class="btn_green" onclick="audit('${stuStatusEnumRecruitted.id}','录取','${extInfo.titleTypeName}');" value="录&#12288;取"/>
			<input type="button" style="width:100px;" class="btn_red" onclick="audit('${stuStatusEnumUnRecruitted.id}','不录取','${extInfo.titleTypeName}');" value="不录取"/>
			</c:if>
			<input type="button" style="width:100px;" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
		</div>
	</div>
</div>
<script type="text/javascript">

	function audit(statusId,title,titleTypeName){
//	    console.log(titleTypeName);
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
				titleTypeName:titleTypeName,
			};
			jboxPost("<s:url value='/dwjxres/hospital/auditOption'/>",data,
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
	function returnInfo(resumeFlow,titleTypeName){
        console.log(titleTypeName);
		if($.trim($("#auditAgree").val())==""){
			jboxTip("审核意见不能为空!");
			return;
		}
		var reason = $("#auditAgree").val();
		var userFlow = "${user.userFlow}";
		jboxConfirm("确认退回?", function(){
			jboxPost("<s:url value='/dwjxres/hospital/returnInfo'/>",{"resumeFlow":resumeFlow,"reason":reason,"userFlow":userFlow,"titleTypeName":titleTypeName}, function(resp){
				jboxTip(resp);
				window.parent.search();
				jboxClose();
			} , null , true);
		});
	}
</script>