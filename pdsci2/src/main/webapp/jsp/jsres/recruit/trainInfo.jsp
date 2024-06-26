
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>

<script type="text/javascript">
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
<script>

	$(function(){
		showUploadFileTr();
	});
	function showUploadFileTr(){
		var trainYear = "${jsresRecruitDocInfo.trainYear}";
		if("" == trainYear || "${jsResTrainYearEnumThreeYear.id}" == trainYear){
			$("#proveFileUrlTr").hide();
		}else{
			var catSpeId = "${jsresRecruitDocInfo.catSpeId}";
			if("${trainCategoryEnumDoctorTrainingSpe.id}" == catSpeId){
				$("#proveFileUrlTr").show();
			}else{
				$("#proveFileUrlTr").hide();
			}
		}
	}
</script>
<div>
	<div class="infoAudit">
		<div class="div_table">
			<input type="hidden" id="upFileId"/>
			<h4>培训信息</h4>
			<form id="infoForm" style="position: relative;" method="post">
				<table border="0" cellpadding="0" cellspacing="0" class="base_info">
					<colgroup>
						<col width="15%"/>
						<col width="35%"/>
						<col width="15%"/>
						<col width="35%"/>
					</colgroup>
					<tbody>
					<tr>
						<th>当前学位类别：</th>
						<td>${jsresRecruitDocInfo.currDegreeCategoryName }</td>
						<th>审核状态：</th>
						<c:set var="modifyTime" value="${pdfn:transDateTime(jsresRecruitDocInfo.modifyTime)}"></c:set>
						<td>${jsresRecruitDocInfo.auditStatusName}<label>&#12288;${modifyTime}</label></td>
					</tr>
					<tr>
						<th>规培起始日期：</th>
						<td>${jsresRecruitDocInfo.recruitDate}</td>
						<th>届别：</th>
						<td>${jsresRecruitDocInfo.sessionNumber }</td>
					</tr>
					<tr>
						<th>所在地区：</th>
						<td>${jsresRecruitDocInfo.placeName}</td>
						<th>
							培训基地：
						</th>
						<td>
							${jsresRecruitDocInfo.orgName}
						</td>
					</tr>
					<tr>
						<th>培训类别：</th>
						<td id="catSpeNameTd">${jsresRecruitDocInfo.catSpeName}</td>
						<th class="trainSpe">培训专业：</th>
						<td id="speNameTd">
							${jsresRecruitDocInfo.speName}
						</td>
					</tr>
					<tr>
						<th>培训年限：</th>
						<td>
							<c:forEach items="${jsResTrainYearEnumList }" var="trainYear">
								<c:if test="${jsresRecruitDocInfo.trainYear eq trainYear.id}">${trainYear.name }</c:if>
							</c:forEach>
						</td>
						<th>已培训年限：</th>
						<td>
							<c:forEach var="dict" items="${dictTypeEnumYetTrainYearList}">
								<c:if test="${jsresRecruitDocInfo.yetTrainYear eq dict.dictId}">
									${dict.dictName }
								</c:if>
							</c:forEach>
						</td>
					</tr>

					<tr id="proveFileUrlTr">
						<th>减免培训年限证明：</th>
						<td colspan="3">
							<c:if test="${!empty jsresRecruitDocInfo.proveFileUrl}">
								[<a href="${sysCfgMap['upload_base_url']}/${jsresRecruitDocInfo.proveFileUrl}" target="_blank">查看图片</a>]
							</c:if>
							<c:if test="${empty jsresRecruitDocInfo.proveFileUrl}">
								未上传
							</c:if>
						</td>
					</tr>

					<tr>
						<th>医师状态：</th>
						<td>${jsresRecruitDocInfo.doctorStatusName}</td>
						<th>医师走向：</th>
						<td>${jsresRecruitDocInfo.doctorStrikeName}</td>
					</tr>
					<tr>
						<th>应考核年份：</th>
						<td>
							<c:set var="graduactionYear" value="" />
							<c:choose>
								<c:when test="${doctorRecruit.trainYear == 'OneYear'}">
									<c:set var="graduactionYear" value="${doctorRecruit.sessionNumber + 1}" />
								</c:when>
								<c:when test="${doctorRecruit.trainYear == 'TwoYear'}">
									<c:set var="graduactionYear" value="${doctorRecruit.sessionNumber + 2}" />
								</c:when>
								<c:otherwise>
									<c:set var="graduactionYear" value="${jsresRecruitDocInfo.sessionNumber + 3}" />
								</c:otherwise>
							</c:choose>
							${graduactionYear}&#12288;<font color="red">(结业考核年份=当前届别+培训年限)</font>
						</td>
						<th>实际考核年份：</th>
						<td>${jsresRecruitDocInfo.graduationYear}</td>
					</tr>
					<!-- 审核通过 && 非二阶段 -->
					<c:if test="${jsresRecruitDocInfo.auditStatusId eq jsResDoctorAuditStatusEnumPassed.id && not empty jsresRecruitDocInfo.completeFileUrl && not empty jsresRecruitDocInfo.completeCertNo}">
						<tr>
							<th>结业证书附件：</th>
							<td>
								<c:if test="${not empty jsresRecruitDocInfo.completeFileUrl }">
									[<a href="${sysCfgMap['upload_base_url']}/${jsresRecruitDocInfo.completeFileUrl}" target="_blank">查看图片</a>]&nbsp;
								</c:if>
							</td>
							<th>结业证书编号：</th>
							<td>
								${jsresRecruitDocInfo.completeCertNo}
							</td>
						</tr>
					</c:if>
					<c:if test="${not empty jsresRecruitDocInfo.admitNotice}">
						<tr>
							<th style="color: red;">审核意见：</th>
							<td colspan="3" class="red">${jsresRecruitDocInfo.admitNotice}</td>
						</tr>
					</c:if>
					</tbody>
				</table>
			</form>
				<div>
					<h4>诚信声明</h4>
					<table border="0" cellpadding="0" cellspacing="0" width="100%" class="base_info">
						<colgroup>
							<col width="15%"/>
							<col width="85%"/>
						</colgroup>
						<tr>
							<th>诚信声明：</th>
							<td>
								<c:if test="${not empty resRec.imageUrl}">
									[<a class="imgc" href="${sysCfgMap['upload_base_url']}/${resRec.imageUrl}" target="_blank">查看图片</a>]
								</c:if>
								<c:if test="${empty resRec.imageUrl}">
									暂未提交
								</c:if>
							</td>
						</tr>
					</table>
				</div>
		</div>
	</div>
</div>