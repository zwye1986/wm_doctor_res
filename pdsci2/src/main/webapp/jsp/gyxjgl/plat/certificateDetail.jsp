<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="application/javascript">
	//证书打印
	function printCertificate(){
		var headstr = "<html><head><title></title></head><body><div style='width: 98%;height: 98%;text-align: center;margin-top: 100px;'>";
		var footstr = "</div></body>";
		var newstr = $(".content").html();
		var oldstr = document.body.innerHTML;
		document.body.innerHTML = headstr+newstr+footstr;
		window.print();
		document.body.innerHTML = oldstr;
		return false;
	}
	//证书模板导出
	function exportWord(){
		var url = "<s:url value='/gyxjgl/user/exportWord'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#certForm"), url, null, null, false);
		jboxEndLoading();
	}
	//上传证书照片
	function uploadCert(){
		$.ajaxFileUpload({
			url:"<s:url value='/gyxjgl/user/certImgUpload?userFlow=${userFlow}&certType=${certificateType}'/>",
			secureuri:false,
			fileElementId:'imageFile',
			dataType: 'json',
			success: function (data, status){
				$('.certTip').attr("style","display:none");
				if(data.indexOf("success")==-1){
					jboxTip(data);
				}else{
					jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
					var arr = new Array();
					arr = data.split(":");
					var url = "${sysCfgMap['upload_base_url']}/"+ arr[1];
					$(".showImg").attr("src",url);
				}
			},
			error: function (data, status, e){
				jboxTip('${GlobalConstant.UPLOAD_FAIL}');
			},
			complete:function(){
				$('#imageFile').val("");
			}
		});
	}
</script>
</head>
<body>
<div class="mainright">
	<form method="post" id="certForm">
		<input type="hidden" name="userFlow" value="${userFlow}"/>
		<c:if test="${certificateType eq 1}">
			<input type="hidden" name="certificateType" value="1"/>
			<div style="text-align:left;">
				<div style="text-align:right;margin-right:20px;margin-bottom:10px;${roleFlag eq 'student'?'display:none':''}">
					<input type="button" class="search" value="打&#12288;印" onclick="printCertificate();"/>
					<%--&#12288;<input type="button" class="search" value="导&#12288;出" onclick="exportWord();"/>--%>
				</div>
				<div class="content">
					<div style="${roleFlag eq 'student'?'display:none':''}">
						<c:if test="${certInfo.trainTypeId eq '1'}"><p style="text-align:center;font-size:17px;font-weight:bold">Certificate of Master's Degree</p><input type="hidden" name="top" value="Certificate of Master's Degree"/></c:if>
						<c:if test="${certInfo.trainTypeId eq '2'}"><p style="text-align:center;font-size:17px;font-weight:bold">Certificate of Docto's Degree</p><input type="hidden" name="top" value="Certificate of Docto's Degree"/></c:if>
						<c:if test="${certInfo.trainTypeId eq '1' and certInfo.trainCategoryId eq '5'}"><p style="text-align:center;font-size:17px;font-weight:bold">(Professional Degree)</p><input type="hidden" name="type" value="(Professional Degree)"/></c:if>
						<p style="line-height:25px;font-size:14px;text-align: left;">&#12288;&#12288;
							This is to certify that ${certInfo.nameSpell},<input type="hidden" name="arg1" value="${certInfo.nameSpell}"/>
							<c:if test="${certInfo.sexId eq 'Man'}">male<input type="hidden" name="arg2" value="male"/></c:if>
							<c:if test="${certInfo.sexId eq 'Woman'}">female<input type="hidden" name="arg2" value="female"/></c:if>, born on
							<c:forEach items="${eduMonthEnglisthEnumList}" var="obj">
								<c:if test="${obj.id eq month}">${obj.name}<input type="hidden" name="arg3" value="${obj.name}"/></c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${day eq '1' || day eq '21' || day eq '31'}">
									${day}st<input type="hidden" name="arg4" value="${day}st"/>
								</c:when>
								<c:when test="${day eq '2' || day eq '22'}">
									${day}nd<input type="hidden" name="arg4" value="${day}nd"/>
								</c:when>
								<c:when test="${day eq '3' || day eq '23'}">
									${day}rd<input type="hidden" name="arg4" value="${day}rd"/>
								</c:when>
								<c:otherwise>
									${day}th<input type="hidden" name="arg4" value="${day}th"/>
								</c:otherwise>
							</c:choose>,
								${year}<input type="hidden" name="arg5" value="${year}"/>, having studied a postgraduate program of
							<c:forEach items="${dictTypeEnumGyMajorList}" var="dict">
								<c:if test="${dict.dictId eq certInfo.majorId}">${dict.dictNameEn}<input type="hidden" name="arg6" value="${dict.dictNameEn}"/></c:if>
							</c:forEach> in Guangzhou Medical University and completed all the courses with qualified scores and passed the dissertation defense,
							is conferred upon the
							<c:if test="${certInfo.trainTypeId eq '1'}">master<input type="hidden" name="arg7" value="master"/></c:if>
							<c:if test="${certInfo.trainTypeId eq '2'}">doctor<input type="hidden" name="arg7" value="doctor"/></c:if>'s degree of
							${levelEn}<input type="hidden" name="arg8" value="${levelEn}"/>
							according to regulations of the People's Republic of China on academic degrees.</p>
						<div style="height: 20px;"></div>
						<p style="line-height:25px;font-size:14px;text-align:right;">${dictTypeEnumGySchoolmasterList[0].dictName}<input type="hidden" name="arg9" value="${dictTypeEnumGySchoolmasterList[0].dictName}"/><br/>
							President, Guangzhou Medical University<br/>
							Chairman, Academic Degree Evaluation Committee of Guangzhou Medical University<br/>
							Issued on Date:
							<c:forEach items="${eduMonthEnglisthEnumList}" var="obj">
								<c:if test="${obj.id eq currMonth}">${obj.name}<input type="hidden" name="arg10" value="${obj.name}"/></c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${currDay eq '1' || currDay eq '21' || currDay eq '31'}">
									${currDay}st<input type="hidden" name="arg11" value="${currDay}st"/>
								</c:when>
								<c:when test="${currDay eq '2' || currDay eq '22'}">
									${currDay}nd<input type="hidden" name="arg11" value="${currDay}nd"/>
								</c:when>
								<c:when test="${currDay eq '3' || currDay eq '23'}">
									${currDay}rd<input type="hidden" name="arg11" value="${currDay}rd"/>
								</c:when>
								<c:otherwise>
									${currDay}th<input type="hidden" name="arg11" value="${currDay}th"/>
								</c:otherwise>
							</c:choose>,
								${currYear}<input type="hidden" name="arg12" value="${currYear}"/>
						</p>
						<p style="line-height:25px;font-size:14px;text-align: left;"><span>Certificate number:  ${certInfo.awardDegreeCertCode}<input type="hidden" name="arg13" value="${certInfo.awardDegreeCertCode}"/></span></p>
					</div>
					<div style="margin-top: 50px;"></div>
					<div style="text-align:center;position: relative;">
						<img class="showImg" src="${sysCfgMap['upload_base_url']}${pubFile.fileRemark}" width="600px" height="426px"/>
				<span class="certTip" style="position:absolute;top:140px;left:220px;">
					<c:if test="${roleFlag eq 'student'}">${empty pubFile.fileRemark?'请上传学位证书照片':''}</c:if>
					<c:if test="${roleFlag eq 'school'}">${empty pubFile.fileRemark?'学员未上传学位证书照片':''}</c:if>
				</span>
					</div>
					<div style="text-align:right;margin-top:20px;${roleFlag eq 'school'?'display:none':''}">
						<input type="button" class="search" onclick="$('#imageFile').click();" value="上传照片">
						<input type="file" id="imageFile" name="certImg" style="display:none" onchange="uploadCert();"/>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${certificateType eq 2}">
			<input type="hidden" name="certificateType" value="2"/>
			<div style="text-align:left;">
				<div style="text-align:right;margin-right:20px;margin-bottom:10px;${roleFlag eq 'student'?'display:none':''}">
					<input type="button" class="search" value="打&#12288;印" onclick="printCertificate();"/>
					<%--&#12288;<input type="button" class="search" value="导&#12288;出" onclick="exportWord();"/>--%>
				</div>
				<div class="content">
					<div style="${roleFlag eq 'student'?'display:none':''}">
						<p style="text-align:center;font-size:17px;font-weight:bold">DIPLOMA</p>
						<p style="line-height:25px;font-size:14px;text-align: left;">&#12288;&#12288;This is to certify that
							${certInfo.nameSpell}<input type="hidden" name="arg1" value="${certInfo.nameSpell}"/>,
							<c:if test="${certInfo.sexId eq 'Man'}">male<input type="hidden" name="arg2" value="male"/></c:if>
							<c:if test="${certInfo.sexId eq 'Woman'}">female<input type="hidden" name="arg2" value="female"/></c:if>, born on
							<c:forEach items="${eduMonthEnglisthEnumList}" var="obj">
								<c:if test="${obj.id eq month}">${obj.name}<input type="hidden" name="arg3" value="${obj.name}"/></c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${day eq '1' || day eq '21' || day eq '31'}">
									${day}st<input type="hidden" name="arg4" value="${day}st"/>
								</c:when>
								<c:when test="${day eq '2' || day eq '22'}">
									${day}nd<input type="hidden" name="arg4" value="${day}nd"/>
								</c:when>
								<c:when test="${day eq '3' || day eq '23'}">
									${day}rd<input type="hidden" name="arg4" value="${day}rd"/>
								</c:when>
								<c:otherwise>
									${day}th<input type="hidden" name="arg4" value="${day}th"/>
								</c:otherwise>
							</c:choose>,
								${year}<input type="hidden" name="arg5" value="${year}"/>, having studied the ${studyYear}<input type="hidden" name="arg6" value="${studyYear}"/>-year
							<c:if test="${certInfo.trainTypeId eq '1'}">master's<input type="hidden" name="arg7" value="master's"/></c:if>
							<c:if test="${certInfo.trainTypeId eq '2'}">doctoral<input type="hidden" name="arg7" value="doctoral"/></c:if>
							 program of
							<c:forEach items="${dictTypeEnumGyMajorList}" var="dict">
								<c:if test="${dict.dictId eq certInfo.majorId}">${dict.dictNameEn}<input type="hidden" name="arg8" value="${dict.dictNameEn}"/></c:if>
							</c:forEach>
							in Guangzhou Medical University from September ${certInfo.period}<input type="hidden" name="arg9" value="${certInfo.period}"/> to June ${graduateYear}<input type="hidden" name="arg10" value="${graduateYear}"/>
							and completed all the courses with qualified scores and passed the dissertation defense, is hereby awarded the Certificate of Graduation.</p>
						<div style="height: 20px;"></div>
						<div style="width: 48%;float: left;font-size:14px;text-align: left;"><p>University: Guangzhou Medical University</p></div>
						<div style="width: 48%;float: right;font-size:14px;text-align: right;"><p>President: ${dictTypeEnumGySchoolmasterList[0].dictName}&emsp;<input type="hidden" name="arg11" value="${dictTypeEnumGySchoolmasterList[0].dictName}"/></p></div>
						<p style="line-height:25px;font-size:14px;text-align: right;"><span>Issued on Date:
							<c:forEach items="${eduMonthEnglisthEnumList}" var="obj">
								<c:if test="${obj.id eq currMonth}">${obj.name}<input type="hidden" name="arg13" value="${obj.name}"/></c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${currDay eq '1' || currDay eq '21' || currDay eq '31'}">
									${currDay}st<input type="hidden" name="arg14" value="${currDay}st"/>
								</c:when>
								<c:when test="${currDay eq '2' || currDay eq '22'}">
									${currDay}nd<input type="hidden" name="arg14" value="${currDay}nd"/>
								</c:when>
								<c:when test="${currDay eq '3' || currDay eq '23'}">
									${currDay}rd<input type="hidden" name="arg14" value="${currDay}rd"/>
								</c:when>
								<c:otherwise>
									${currDay}th<input type="hidden" name="arg14" value="${currDay}th"/>
								</c:otherwise>
							</c:choose>,
										${currYear}&emsp;<input type="hidden" name="arg15" value="${currYear}"/>
						</span></p>
						<p style="line-height:25px;font-size:14px;text-align: left;"><span>Certificate number: ${certInfo.diplomaCode}<input type="hidden" name="arg12" value="${certInfo.diplomaCode}"/></span></p>
					</div>
					<div style="margin-top: 50px;"></div>
					<div style="text-align:center;position: relative;">
						<img class="showImg" src="${sysCfgMap['upload_base_url']}${pubFile.filePath}" width="600px" height="426px"/>
					<span class="certTip" style="position:absolute;top:140px;left:220px;">
						<c:if test="${roleFlag eq 'student'}">${empty pubFile.filePath?'请上传毕业证书照片':''}</c:if>
						<c:if test="${roleFlag eq 'school'}">${empty pubFile.filePath?'学员未上传毕业证书照片':''}</c:if>
					</span>
					</div>
					<div style="text-align:right;margin-top:20px;${roleFlag eq 'school'?'display:none':''}">
						<input type="button" class="search" onclick="$('#imageFile').click();" value="上传照片">
						<input type="file" id="imageFile" name="certImg" style="display:none" onchange="uploadCert();"/>
					</div>
				</div>
			</div>
		</c:if>
		<c:if test="${certificateType eq 3}">
			<input type="hidden" name="certificateType" value="3"/>
			<div style="text-align:left;">
				<div style="text-align:right;margin-right:20px;margin-bottom:10px;${roleFlag eq 'student'?'display:none':''}">
					<input type="button" class="search" value="打&#12288;印" onclick="printCertificate();"/>
					<%--&#12288;<input type="button" class="search" value="导&#12288;出" onclick="exportWord();"/>--%>
				</div>
				<div style="margin-top: 50px;"></div>
				<div class="content">
					<div style="text-align:center;position: relative;">
						<img class="showImg" src="${sysCfgMap['upload_base_url']}${pubFile.fileSuffix}" width="600px" height="426px"/>
					<span class="certTip" style="position:absolute;top:140px;left:220px;">
						<c:if test="${roleFlag eq 'student'}">${empty pubFile.fileSuffix?'请上传规培证书照片':''}</c:if>
						<c:if test="${roleFlag eq 'school'}">${empty pubFile.fileSuffix?'学员未上传规培证书照片':''}</c:if>
					</span>
					</div>
					<div style="text-align:right;margin-top:20px;${roleFlag eq 'school'?'display:none':''}">
						<input type="button" class="search" onclick="$('#imageFile').click();" value="上传照片">
						<input type="file" id="imageFile" name="certImg" style="display:none" onchange="uploadCert();"/>
					</div>
				</div>
			</div>
		</c:if>

		<c:if test="${certificateType eq 4}">
			<input type="hidden" name="certificateType" value="4"/>
			<div style="text-align:left;">
				<div style="text-align:right;margin-right:20px;margin-bottom:10px;${roleFlag eq 'student'?'display:none':''}">
					<input type="button" class="search" value="打&#12288;印" onclick="printCertificate();"/>
					<%--&#12288;<input type="button" class="search" value="导&#12288;出" onclick="exportWord();"/>--%>
				</div>
				<div style="margin-top: 50px;"></div>
				<div class="content">
					<div style="text-align:center;position: relative;">
						<img class="showImg" src="${sysCfgMap['upload_base_url']}${pubFile.productType}" width="600px" height="426px"/>
					<span class="certTip" style="position:absolute;top:140px;left:220px;">
						<c:if test="${roleFlag eq 'student'}">${empty pubFile.productType?'请上传执医证书照片':''}</c:if>
						<c:if test="${roleFlag eq 'school'}">${empty pubFile.productType?'学员未上传执医证书照片':''}</c:if>
					</span>
					</div>
					<div style="text-align:right;margin-top:20px;${roleFlag eq 'school'?'display:none':''}">
						<input type="button" class="search" onclick="$('#imageFile').click();" value="上传照片">
						<input type="file" id="imageFile" name="certImg" style="display:none" onchange="uploadCert();"/>
					</div>
				</div>
			</div>
		</c:if>
	</form>
</div>
</body>
</html>