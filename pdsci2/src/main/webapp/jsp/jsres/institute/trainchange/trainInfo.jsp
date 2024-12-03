<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope and param.studyFlag eq GlobalConstant.FLAG_Y && doctorRecruit.orgFlow eq currUser.orgFlow}">
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
</c:if>

<script type="text/javascript">
$(function(){
    var tabCourse = $('.icon-head');
    var tab=$(".icon");
    tabCourse.on('mouseover',function(){
    	$("#changInfo").show();$("#changSpe").hide();
    	<c:if test="${param.openType=='open'}">
	    	$("#changInfo").css("right", "180px");//箭头右偏移
		</c:if>
    });
    tab.on('mouseover',function(){
    	$("#changSpe").show();$("#changInfo").hide();
    	<c:if test="${param.openType=='open'}">
	    	$("#changSpe").css("right", "180px");//箭头右偏移
		</c:if>
    });
    $(document).on('click',function(){$("#changInfo").hide();});
    $(document).on('click',function(){$("#changSpe").hide();});
    $("#returnNotice").hide();
});
</script>
<style>
.icon-head{ /* vertical-align: middle; */}
.changeinfo{position:absolute; background-color:#fff; padding:10px; border:1px solid  #dcdcdc; width:550px;}
.changeinfoContent{position:absolute; background-color:#fff; padding:10px; border:1px solid  #dcdcdc; width:650px;}
.icon_up{background-image:url("<s:url value='/css/skin/${skinPath}/images/up2.png'/>"); background-repeat:no-repeat; background-position: top center; padding:5px;position: absolute;top: -6px;left: 225px;}
.xllist caption{padding-bottom: 10px;font-weight: bold;font-size: 15px;color: #3d91d5;}
.pxxx{position: relative;top:30px; right: 175px;}
.pyyy{position: relative;top:30px; right: 175px;}
</style>
<!-- <div class="infoAudit"> -->
<div>
<div class="${'open' eq param.openType?'infoAudit':'main_bd' }">
<div class="div_table">
	     <input type="hidden" id="upFileId"/>
        <h4>培训信息</h4>
        	<c:set var="auditNotPassed" value="${resDoctorAuditStatusEnumNotPassed.id eq doctorRecruit.auditStatusId}"/>
			<form id="infoForm" style="position: relative;" method="post">
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
              <col width="15%"/>
              <col width="30%"/>
              <col width="15%"/>
              <col width="40%"/>
            </colgroup>
	           <tbody>
	           <tr>
	             <th>当前学位类别：</th>
	             <td>${doctorRecruit.currDegreeCategoryName }</td>
	             <th style="${auditNotPassed?'color: red':'' }">审核状态：</th>
	             <td class="${auditNotPassed?'red':'' }">${doctorRecruit.auditStatusName }</td>
	           </tr>
	           <tr>
	               <th>规培起始日期：</th>
	               <td>${doctorRecruit.recruitDate}</td>
	               <th>届别：</th>
	               <td>${doctorRecruit.sessionNumber }</td>
	           </tr>
	           <tr>
	               <th>所在地区：</th>
	               <td>${doctorRecruit.placeName}</td>
	               <th>
	               		<!--培训记录 ： 最新 && 审核通过 -->
	               		<c:if test="${resDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId}">
    		          		<!-- 变更申请记录 -->
    		          		<c:if test="${!empty docOrgHistoryList}">
 		          			<div class="pxxx" id="changInfo" style="display:none; ">
								<div class="changeinfo" id="changeinfoContent">
								    <i class="icon_up"></i>
									<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%">
										<caption>变更基地申请记录</caption>
										<thead>
											<tr>
												<th style="text-align: center;">转入前培训基地</th>
												<th style="text-align: center;">申请转入培训基地</th>
												<th style="text-align: center;">审核状态</th>
												<th style="text-align: center;">审核意见</th>
												<th style="text-align: center;">审核时间</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${docOrgHistoryList }" var="docOrgHistory">
											<tr>
												<td style="text-align: center;">${docOrgHistory.historyOrgName}</td>
												<td style="text-align: center;">${docOrgHistory.orgName}</td>
												<td style="text-align: center;">${jsResChangeApplyStatusEnumInApplyWaiting.id == docOrgHistory.changeStatusId?'转出审核通过':docOrgHistory.changeStatusName}</td>
												<td style="text-align: center;"><label title="${docOrgHistory.auditOpinion}">${pdfn:cutString(docOrgHistory.auditOpinion,10,true,3) }</label></td>
												<td style="text-align: center;">
													<c:if test="${!empty docOrgHistory.inDate }">${pdfn:transDate(docOrgHistory.inDate)}</c:if>
													<c:if test="${empty docOrgHistory.inDate }">${pdfn:transDate(docOrgHistory.outDate)}</c:if>
												</td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
							  </div>
							  <img id="changeIcon" class="icon-head" src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/>
		               		  </c:if>
	               		</c:if>
	               		培训基地：
	               </th>
	               <td>
	               		${doctorRecruit.orgName}
	               </td>
	           </tr>
	           <tr>
	               <th>培训类别：</th>
	               <td id="catSpeNameTd">${doctorRecruit.catSpeName}</td>
	               <th class="trainSpe">
	               		<c:if test="${resDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId}">
    		          		<c:if test="${!empty changeSpeList}">
 		          			<div class="pyyy" id="changSpe" style="display:none; ">
								<div class="changeinfoContent">
								    <i class="icon_up"></i>
									<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%">
										<caption>变更专业申请记录</caption>
										<thead>
											<tr>
												<th style="text-align: center;">变更前培养类型</th>
												<th style="text-align: center;">变更后培养类型</th>
												<th style="text-align: center;">变更前专业</th>
												<th style="text-align: center;">变更后专业</th>
												<th style="text-align: center;">审核状态</th>
												<th style="text-align: center;">审核时间</th>
												<th style="text-align: center;">审核意见</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach items="${changeSpeList }" var="changeSpe">
											<tr>
												<td style="text-align: center;">${changeSpe.historyTrainingTypeName}</td>
												<td style="text-align: center;">${changeSpe.trainingTypeName}</td>
												<td style="text-align: center;">${changeSpe.historyTrainingSpeName}</td>
												<td style="text-align: center;">${changeSpe.trainingSpeName}</td>
												<td style="text-align: center;">${changeSpe.changeStatusName}</td>
												<td style="text-align: center;">
													<c:if test="${!empty changeSpe.inDate }">${pdfn:transDate(changeSpe.inDate)}</c:if>
													<c:if test="${empty changeSpe.inDate }">${pdfn:transDate(changeSpe.outDate)}</c:if>
												</td>
												<td style="text-align: center;"><label title="${changeSpe.auditOpinion}">${pdfn:cutString(changeSpe.auditOpinion,10,true,3) }</label></td>
											</tr>
										</c:forEach>
										</tbody>
									</table>
								</div>
							  </div>
							  <img id="changeIcon" class="icon" src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/>
		               		  </c:if>
	               		</c:if>
	               		培训专业：</th>
	               <td id="speNameTd">
	               ${doctorRecruit.speName}
	               </td>
	           </tr>
	           <tr>
	               <th>培养年限：</th>
	               <td>
	                 <c:forEach items="${jsResTrainYearEnumList }" var="trainYear">
	                     <c:if test="${doctorRecruit.trainYear eq trainYear.id}">${trainYear.name }</c:if>
	                 </c:forEach>
	               </td>
	               <th>已培养年限：</th>
	               <td>
	                  <c:forEach var="dict" items="${dictTypeEnumYetTrainYearList}">
	                    <c:if test="${doctorRecruit.yetTrainYear eq dict.dictId}">
	                       ${dict.dictName }
	                    </c:if>
	                  </c:forEach>
	               </td>
	           </tr>
	           
				<tr id="proveFileUrlTr" style="display:none;">
					<th>减免培养年限证明：</th>
					<td colspan="3">
						<c:if test="${param.studyFlag==GlobalConstant.FLAG_Y}">
							<c:if test="${!(doctorRecruit.orgFlow eq currUser.orgFlow)}">
								<c:if test="${!empty doctorRecruit.proveFileUrl}">
									[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]
								</c:if>
							</c:if>
						</c:if>
						<c:if test="${GlobalConstant.USER_LIST_PERSONAL eq sessionScope.userListScope||param.auditFlag eq  GlobalConstant.FLAG_Y|| param.info eq GlobalConstant.FLAG_Y|| param.change eq GlobalConstant.FLAG_Y}">
							<c:if test="${!empty doctorRecruit.proveFileUrl}">
								[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]
							</c:if>
						</c:if>
					</td>
				</tr>
	           
	           <tr>
	               <th>医师状态：</th>
	               <td>${doctorRecruit.doctorStatusName}</td>
	               <th>医师走向：</th>
	               <td>${doctorRecruit.doctorStrikeName}</td>
	           </tr>
	            <tr>
	           		<th>结业考核年份：</th>
	           		<td colspan="3">${doctorRecruit.graduationYear}&#12288;<font color="red">(结业考核年份=当前届别+培养年限)</font></td>
	           </tr>
	           <!-- 审核通过 && 非二阶段 -->
	           <c:if test="${doctorRecruit.auditStatusId eq resDoctorAuditStatusEnumPassed.id && not empty doctorRecruit.completeFileUrl && not empty doctorRecruit.completeCertNo}">
		           <tr>
		             <th>结业证书附件：</th>
	                   <td>
	                     <c:if test="${not empty doctorRecruit.completeFileUrl }">
						  [<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.completeFileUrl}" target="_blank">查看图片</a>]&nbsp;
		                 </c:if>
		               </td>
		             <th>结业证书编号：</th>
		             <td>
		                ${doctorRecruit.completeCertNo}
		             </td>
		            </tr>
	            </c:if>

		           <c:if test="${not empty doctorRecruit.admitNotice}">
			           <tr>
			               <th style="color: red;"><c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope}">历史</c:if>审核意见：</th>
			               <td colspan="3" class="red">${doctorRecruit.admitNotice}</td>
			           </tr>
		           </c:if>
	           </tbody>
           </table>
           </form>
			<c:if test="${!(param.studyFlag eq GlobalConstant.FLAG_Y)}">
           <div>	
			<h4>诚信声明</h4>
			<table border="0" cellpadding="0" cellspacing="0" width="100%" class="base_info">
	            <colgroup>
	              <col width="15%"/>
	              <col width="75%"/>
	            </colgroup>
	        	<tr>
	        		<th>诚信声明：</th>
	        		<td>
  						<c:if test="${not empty resRec.recContent}">
							[<a href="${sysCfgMap['upload_base_url']}/${resRec.recContent}" target="_blank">查看图片</a>]
						</c:if>
  						<c:if test="${empty resRec.recContent}">
							暂未提交
						</c:if>
	        		</td>
	        	</tr>
            </table>
			</div>	
            </c:if>
          	<form id="fileForm" method="post" enctype="multipart/form-data">
				<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
			</form>

</div>
</div>
</div>