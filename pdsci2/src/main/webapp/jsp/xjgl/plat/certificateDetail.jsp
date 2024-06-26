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
		var headstr = "<html><head><title></title></head><body>";
		var footstr = "</body>";
		var newstr = $(".content").html();
		var oldstr = document.body.innerHTML;
		document.body.innerHTML = headstr+newstr+footstr;
		window.print();
		document.body.innerHTML = oldstr;
		return false;
	}
	//证书模板导出
	function exportWord(){
		var url = "<s:url value='/xjgl/user/exportWord'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#certForm"), url, null, null, false);
		jboxEndLoading();
	}
	//上传证书照片
	function uploadCert(){
		$.ajaxFileUpload({
			url:"<s:url value='/xjgl/user/certImgUpload?userFlow=${userFlow}&certType=${certificateType}'/>",
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
				<div style="text-align:right;margin-right:20px;margin-bottom:10px;">
					<input type="button" class="search" value="打&#12288;印" onclick="printCertificate();"/>
					&#12288;<input type="button" class="search" value="导&#12288;出" onclick="exportWord();"/>
				</div>
				<div class="content">
					<div style="margin-top: 50px;">
					<div >
						<p style="text-align:center;font-size:16px;font-weight:bold">
							<c:if test="${certInfo.trainTypeId eq '1'}">Master Degree Certificate<input type="hidden" name="arg1" value="Master Degree Certificate"/></c:if>
							<c:if test="${certInfo.trainTypeId eq '2'}">Doctoral Degree Certificate<input type="hidden" name="arg1" value="Doctoral Degree Certificate"/></c:if></p>
						<p style="line-height:25px;">&#12288;&#12288;
							<%--<c:if test="${certInfo.sexId eq 'Man'}">Mr<input type="hidden" name="arg1" value="Mr"/></c:if>--%>
							<%--<c:if test="${certInfo.sexId eq 'Woman'}">Miss<input type="hidden" name="arg1" value="Miss"/></c:if>.--%>
								${certInfo.nameSpell}<input type="hidden" name="arg2" value="${certInfo.nameSpell}"/>,
							<c:if test="${certInfo.sexId eq 'Man'}">male<input type="hidden" name="arg3" value="male"/></c:if>
							<c:if test="${certInfo.sexId eq 'Woman'}">female<input type="hidden" name="arg3" value="female"/></c:if>,
							born on
							<c:forEach items="${eduMonthEnglisthEnumList}" var="obj">
								<c:if test="${obj.id eq month}">${obj.name}<input type="hidden" name="arg4" value="${obj.name}"/></c:if>
							</c:forEach>
							<c:choose>
								<c:when test="${day eq '1' || day eq '21' || day eq '31'}">
									${day}st<input type="hidden" name="arg5" value="${day}st"/>
								</c:when>
								<c:when test="${day eq '2' || day eq '22'}">
									${day}nd<input type="hidden" name="arg5" value="${day}nd"/>
								</c:when>
								<c:when test="${day eq '3' || day eq '23'}">
									${day}rd<input type="hidden" name="arg5" value="${day}rd"/>
								</c:when>
								<c:otherwise>
									${day}th<input type="hidden" name="arg5" value="${day}th"/>
								</c:otherwise>
							</c:choose>,
								${year}<input type="hidden" name="arg6" value="${year}"/>,
							having passed all the required examinations of the
							<c:if test="${certInfo.trainTypeId eq '1'}">Master<input type="hidden" name="arg7" value="Master"/></c:if>
							<c:if test="${certInfo.trainTypeId eq '2'}">Doctoral<input type="hidden" name="arg7" value="Doctor"/></c:if> degree
							and dissertation defense in Southern Medical University in major of ${dictNameEn}<input type="hidden" name="arg8" value="${dictNameEn}"/>
							,in accordance with the Academic Degree Regulations of the People's Republic of China, is awarded the
							<c:if test="${certInfo.trainTypeId eq '1'}">Master<input type="hidden" name="arg9" value="Master"/></c:if>
							<c:if test="${certInfo.trainTypeId eq '2'}">Doctoral<input type="hidden" name="arg9" value="Doctoral"/></c:if> Degree of
							<c:forEach items="${dictTypeEnumMedicineList}" var="dict">
								<c:if test="${dict.dictId eq certInfo.medicine}">${dict.dictName}<input type="hidden" name="arg10" value="${dict.dictName}"/></c:if>
							</c:forEach>.</p>
						<div style="height: 20px;"></div>
						<p style="line-height:25px;text-align:center;">
							${dictTypeEnumSchoolmasterList[0].dictName}<input type="hidden" name="arg11" value="${dictTypeEnumSchoolmasterList[0].dictName}"/><br/>
							Chairman of Academic Degree<br/>
							Evaluation Committee<br/>
							Southern Medical University</p>
						<p style="line-height:25px;"><span>No. ${certInfo.awardDegreeCertCode}<input type="hidden" name="arg12" value="${certInfo.awardDegreeCertCode}"/></span><span style="float:right;">Date:
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
								${currYear}<input type="hidden" name="arg15" value="${currYear}"/></span></p>
					</div>
					<div style="text-align:center;position: relative;">
						<img class="showImg" src="${sysCfgMap['upload_base_url']}${pubFile.fileRemark}" width="440px" height="315px"/>
				<span class="certTip" style="position:absolute;top:140px;left:200px;">
					<c:if test="${roleFlag eq 'student'}">${empty pubFile.fileRemark?'请上传学位证书照片':''}</c:if>
					<c:if test="${roleFlag eq 'school'}">${empty pubFile.fileRemark?'学员未上传学位证书照片':''}</c:if>
				</span>
					</div>
				</div>
			</div>
					<div style="text-align:center;margin-bottom: 10px;${roleFlag eq 'school'?'display:none':''}">
						<input type="button" class="search" onclick="$('#imageFile').click();" value="上传照片">
						<input type="file" id="imageFile" name="certImg" style="display:none" onchange="uploadCert();"/>
					</div>
			</div>
		</c:if>
		<c:if test="${certificateType eq 2}">
			<input type="hidden" name="certificateType" value="2"/>
			<div style="text-align:left;">
				<div style="text-align:right;margin-right:20px;margin-bottom:10px;">
					<input type="button" class="search" value="打&#12288;印" onclick="printCertificate();"/>
					&#12288;<input type="button" class="search" value="导&#12288;出" onclick="exportWord();"/>
				</div>
				<div class="content">
					<div style="margin-top: 80px;">
					<div  >
						<p style="text-align:center;font-size:16px;font-weight:bold">Certificate of Graduation</p>
						<p style="line-height:25px;">&#12288;&#12288;
								${certInfo.nameSpell}<input type="hidden" name="arg1" value="${certInfo.nameSpell}"/>,
							<c:if test="${certInfo.sexId eq 'Man'}">male<input type="hidden" name="arg2" value="male"/></c:if>
							<c:if test="${certInfo.sexId eq 'Woman'}">female<input type="hidden" name="arg2" value="female"/></c:if>,
							born on
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
								${year}<input type="hidden" name="arg5" value="${year}"/>,
							having studied in ${dictNameEn}<input type="hidden" name="arg6" value="${dictNameEn}"/>
							from Sep. ${certInfo.period}<input type="hidden" name="arg7" value="${certInfo.period}"/> to Jun. ${graduateYear}<input type="hidden" name="arg8" value="${graduateYear}"/>
							and having completed the three-year
							<c:if test="${certInfo.trainTypeId eq '1'}">postgraduate<input type="hidden" name="arg9" value="postgraduate"/></c:if>
							<c:if test="${certInfo.trainTypeId eq '2'}">doctoral<input type="hidden" name="arg9" value="doctoral"/></c:if>
							 program, is hereby granted graduation.</p>
						<div style="height: 20px;"></div>
						<p style="line-height:25px;text-align:center;">President: ${dictTypeEnumSchoolmasterList[0].dictName}<input type="hidden" name="arg10" value="${dictTypeEnumSchoolmasterList[0].dictName}"/><br/>
							Southern Medical University<br/>
						<p style="line-height:25px;"><span>No. ${certInfo.diplomaCode}<input type="hidden" name="arg11" value="${certInfo.diplomaCode}"/></span><span style="float:right;">Date:
					<c:forEach items="${eduMonthEnglisthEnumList}" var="obj">
						<c:if test="${obj.id eq currMonth}">${obj.name}<input type="hidden" name="arg12" value="${obj.name}"/></c:if>
					</c:forEach>
					<c:choose>
						<c:when test="${currDay eq '1' || currDay eq '21' || currDay eq '31'}">
							${currDay}st<input type="hidden" name="arg13" value="${currDay}st"/>
						</c:when>
						<c:when test="${currDay eq '2' || currDay eq '22'}">
							${currDay}nd<input type="hidden" name="arg13" value="${currDay}nd"/>
						</c:when>
						<c:when test="${currDay eq '3' || currDay eq '23'}">
							${currDay}rd<input type="hidden" name="arg13" value="${currDay}rd"/>
						</c:when>
						<c:otherwise>
							${currDay}th<input type="hidden" name="arg13" value="${currDay}th"/>
						</c:otherwise>
					</c:choose>,
								${currYear}<input type="hidden" name="arg14" value="${currYear}"/></span></p>
					</div>
					<div style="text-align:center;position: relative;">
						<img class="showImg" src="${sysCfgMap['upload_base_url']}${pubFile.filePath}" width="440px" height="315px"/>
					<span class="certTip" style="position:absolute;top:140px;left:220px;">
						<c:if test="${roleFlag eq 'student'}">${empty pubFile.filePath?'请上传毕业证书照片':''}</c:if>
						<c:if test="${roleFlag eq 'school'}">${empty pubFile.filePath?'学员未上传毕业证书照片':''}</c:if>
					</span>
					</div>
				</div>
				</div>
					<div style="text-align:center;margin-bottom: 10px;${roleFlag eq 'school'?'display:none':''}">
						<input type="button" class="search" onclick="$('#imageFile').click();" value="上传照片">
						<input type="file" id="imageFile" name="certImg" style="display:none" onchange="uploadCert();"/>
					</div>
			</div>
		</c:if>
	</form>
</div>
</body>
</html>