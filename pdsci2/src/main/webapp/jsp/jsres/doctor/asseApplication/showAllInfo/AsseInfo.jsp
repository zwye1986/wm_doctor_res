<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
<link rel="stylesheet" href="<s:url value="/jsp/jsres/css/detail.css"/>"/>

<link rel="stylesheet" type="text/css" href="<s:url value='/js/viewer/viewer.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/viewer/viewer-jquery.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<style type="text/css">
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red;}
	.grid.lz_table tbody th {padding:0}
	.sh_ul{
		margin: 36px 0;
	}
	.sh_ul li{
		height: 36px;
		line-height: 36px;
	}
	.sh_ul li .icon-blue{
		margin-right: 24px;
		color: #686868;
	}
	.sh_ul li>span{
		margin-right: 42px;
	}
	.icon-blue{
		width: 16px;
		height: 16px;
		border-radius: 100%;
		border: 1px solid #b4b4b4;
		background-color: lightgreen;
		box-shadow: inset 0 0 0 3px #f4f4f4;
		position: relative;
		top: 9px;
	}
</style>
<script type="text/javascript">
	function doclose()
	{
		top.jboxCloseMessager();
	}
	function  applyAudit(roleFlag) {
		if (!$("#myform").validationEngine("validate")) {
			return;
		}
		var reason="";
		var reason2="";
		var id=$("input[name='auditStatusId']:checked").val();
		if(id==""||id==undefined)
		{
			jboxTip("请选择审核结果！！");
			return false;
		}
		if(id=="${jsResAuditStatusEnumPassed.id}")
		{
			reason=$("#passReason1").val();
			reason2=$("#passReason2").val();
		}
		if(id=="${jsResAuditStatusEnumNotPassed.id}")
		{
			reason=$("#unpassReason1").val();
			reason2=$("#unpassReason2").val();
		}
		if(id=='Black'){
            reason=$("#blackReason").val();
            reason2=$("#blackReason2").val();
		}
		if(!reason&&id=="${jsResAuditStatusEnumNotPassed.id}")
		{
			jboxTip("请选择原因！！");
			return false;
		}
        if(!reason&&id=="Black")
        {
            jboxTip("请选择原因！！");
            return false;
        }
		if(reason=="不通过，无材料或缺某一个材料"&&!reason2)
		{
			jboxTip("请输入具体原因！！");
			return false;
		}
		$("#auditReason").val(reason+" "+reason2);
		jboxConfirm("确认审核？" , function(){
			jboxStartLoading();
			jboxPost("<s:url value='/jsres/asse/localSaveAudit'/>?roleFlag=" + roleFlag,
					$("#myform").serialize(),
					function(resp){
						top.jboxEndLoading();
						endloadIng();
						jboxTip(resp);
						if(resp=="审核成功"){
							var currentPage=window.parent.$("#currentPage").val();
							if(!currentPage)
							{
								currentPage=1;
							}
							var length=(window.parent.$("#dataTable tbody").find("tr").length||0)-1;
							if(length<=0&&currentPage>1)
							{
								currentPage=currentPage-1;
							}
							window.parent.toPage(currentPage);
							top.jboxClose();
						}

					},null,false);
		});
	}
	function endloadIng() {
		var openDialog = dialog.get('artLoading');
		if(openDialog !=null && openDialog.open){
			openDialog.close().remove();
		}
		openDialog = dialog.get('loadingDialog');
		if(openDialog !=null && openDialog.open){
			openDialog.close().remove();
		}
		var jboxMainIframeLoading = $("#jboxMainIframeLoading");
		if(jboxMainIframeLoading!=null){
			jboxMainIframeLoading.fadeOut(500,function(){
				$(jboxMainIframeLoading).remove();
			});
		}
	}
	function  check() {
		var id=$("input[name='auditStatusId']:checked").val();
		if(id=="${jsResAuditStatusEnumPassed.id}")
		{
			$("#passReason1").removeAttr("readonly");
			$("#passReason2").removeAttr("readonly");
			$("#unpassReason1").removeAttr("name");
			$("#unpassReason1").attr("readonly","readonly");
			$("#unpassReason1").removeClass("validate[required]");
			$("#passReason1").addClass("validate[required]");
			$("#unpassReason1").val("");
			$("#unpassReason2").removeAttr("name");
			$("#unpassReason2").attr("readonly","readonly");
			$("#unpassReason2").val("");

            $("#blackReason").removeAttr("name");
            $("#blackReason").attr("readonly","readonly");
            $("#blackReason").removeClass("validate[required]");
            $("#blackReason").val("");
            $("#blackReason2").removeAttr("name");
            $("#blackReason2").attr("readonly","readonly");
            $("#blackReason2").removeClass("validate[required]");
            $("#blackReason2").val("");
		}
		if(id=="${jsResAuditStatusEnumNotPassed.id}")
		{
			$("#passReason1").attr("readonly","readonly");
			$("#passReason1").removeAttr("name");
			$("#passReason1").val("");
			$("#passReason2").attr("readonly","readonly");
			$("#passReason2").removeAttr("name");
			$("#passReason2").val("");
			$("#unpassReason1").removeAttr("readonly");
			$("#passReason1").removeClass("validate[required]");
			$("#unpassReason1").addClass("validate[required]");
			$("#unpassReason2").removeAttr("readonly");

            $("#blackReason").removeAttr("name");
            $("#blackReason").attr("readonly","readonly");
            $("#blackReason").removeClass("validate[required]");
            $("#blackReason").val("");
            $("#blackReason2").removeAttr("name");
            $("#blackReason2").attr("readonly","readonly");
            $("#blackReason2").removeClass("validate[required]");
            $("#blackReason2").val("");
		}
		if(id=='Black'){
            $("#passReason1").attr("readonly","readonly");
            $("#passReason1").removeAttr("name");
            $("#passReason1").val("");
            $("#passReason2").attr("readonly","readonly");
            $("#passReason2").removeAttr("name");
            $("#passReason2").val("");

            $("#unpassReason1").attr("readonly","readonly");
            $("#unpassReason1").removeAttr("name");
            $("#unpassReason1").val("");
            $("#unpassReason2").attr("readonly","readonly");
            $("#unpassReason2").removeAttr("name");
            $("#unpassReason2").val("");

            $("#blackReason2").addClass("validate[required]");
		}
	}

	$(function(){
		var datas =[];//所有的分配的授课老师账号

		var arry ={"id":"无","text":"无"};
		datas.push(arry);
		var arry ={"id":"可考试，公共课合格后发证","text":"可考试，公共课合格后发证"};
		datas.push(arry);
		var arry ={"id":"可考试，提供医师执业证书后发证","text":"可考试，提供医师执业证书后发证"};
		datas.push(arry);
		var arry ={"id":"可考试，提供医师执业证书，公共课合格后发证","text":"可考试，提供医师执业证书，公共课合格后发证"};
		datas.push(arry);
		var arry ={"id":"可考试，补轮转后发证","text":"可考试，补轮转后发证"};
		datas.push(arry);
		var arry ={"id":"可考试，变更执业范围后发证","text":"可考试，变更执业范围后发证"};
		datas.push(arry);
		var arry ={"id":"可考试，出科表上传完整后发证","text":"可考试，出科表上传完整后发证"};
		datas.push(arry);
		var arry ={"id":"可考试，出科表上传完整，提供医师执业证书后发证","text":"可考试，出科表上传完整，提供医师执业证书后发证"};
		datas.push(arry);
		var arry ={"id":"可考试，出科表上传完整，公共课合格后发证","text":"可考试，出科表上传完整，公共课合格后发证"};
		datas.push(arry);
		var arry ={"id":"可考试，出科表上传完整，提供医师执业证书,公共课合格后发证","text":"可考试，出科表上传完整，提供医师执业证书,公共课合格后发证"};
		var arry ={"id":"我已审核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。","text":"我已审核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。"};
		datas.push(arry);
//	$.itemSelect("passReason",datas,null,null,null);
		var datas =[];
		var arry ={"id":"不通过，APP填写不符合要求或出科表上传不符合要求","text":"不通过，APP填写不符合要求或出科表上传不符合要求"};
		datas.push(arry);
		var arry ={"id":"不通过，超出执业范围","text":"不通过，超出执业范围"};
		datas.push(arry);
		var arry ={"id":"不通过，培训时间不足","text":"不通过，培训时间不足"};
		datas.push(arry);
		var arry ={"id":"不通过，无材料或缺某一个材料","text":"不通过，无材料或缺某一个材料"};
		datas.push(arry);
		var arry ={"id":"不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格","text":"不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格"};
		datas.push(arry);
		var arry ={"id":"不通过，培训手册填写不符合要求","text":"不通过，培训手册填写不符合要求"};
		datas.push(arry);
		var arry ={"id":"不通过，减免材料不符合要求","text":"不通过，减免材料不符合要求"};
		datas.push(arry);
		var arry ={"id":"其他","text":"其他"};
		datas.push(arry);
//	$.itemSelect("unpassReason",datas,null,null,null);
	});
</script>
<script type="text/javascript">
	//打印
	function print(doctorFlow)
	{
		jboxExp(null,"<s:url value='/jsres/doctor/applyDownload?doctorFlow='/>" + doctorFlow +"&recruitFlow=${recruitFlow}&applyYear=${param.applyYear}");
	}
	function showImages(deptFlow)
	{
		var url = "<s:url value='/jsres/asse/showImages?deptFlow='/>"+deptFlow+"&doctorFlow=${recruit.doctorFlow}&applyYear=${param.applyYear}";
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='800px' height='400px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'出科考核表',800,400);
	}

	function catalogue(recordFlow,doctorFlow){
		var url="<s:url value='/jsres/manage/catalogue'/>?doctorFlow="+doctorFlow+"&schRotationDeptFlow="+recordFlow;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='330px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,"登记详情",1000,400);
	}
</script>
<script type="text/javascript">
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
		if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
			return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
		} else {
			return this.replace(reallyDo, replaceWith);
		}
	}
	$(function () {
		$('.ratateImg').viewer();

		var score = '${publicScore.skillScore}';
		if(score != '') {
            var td = document.getElementById("mouseOver");
            var div = document.getElementById("showMouseOver");
            td.onmouseover = function () {
                div.style.display = 'block';
            }
            td.onmouseout = function () {
                div.style.display = 'none';
            }
            var td2 = document.getElementById("mouseOver2");
            var div2 = document.getElementById("showMouseOver2");
            td2.onmouseover = function () {
                div2.style.display = 'block';
            }
            td2.onmouseout = function () {
                div2.style.display = 'none';
            }
        }
	})


	function showReductionImg(recordFlow) {
		var url = "<s:url value ='/jsres/doctor/showReductionImg'/>?recordFlow="+recordFlow;
		jboxOpen(url, "减免证明", 700, 500);
	}

	function lookInfoData() {
		var url = "<s:url value='/jsres/asse/AsseFile'/>?recruitFlow=${recruit.recruitFlow}&applyYear=${param.applyYear}";
		jboxOpen(url, "各科室轮转情况", 1330, 650);
	}
</script>
<div class="search_table" style="margin-top:10px;">
	<div style="max-height: 600px;overflow: auto;">
		<div class="main_bd" id="div_table_0" >
			<h4 >基本信息</h4>
			<form id="fileForm" method="post" enctype="multipart/form-data">
				<input type="text" name="productFlow" value="${recruitFlow}" style="display: none;"/>
				<input type="file" id="file" name="file" class="validate[required]" style="display: none;"/>
			</form>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info basc_jb" >
				<colgroup>
					<%--					<col width="21%"/>--%>
					<%--					<col width="21%"/>--%>
					<%--					<col width="21%"/>--%>
					<%--					<col width="21%"/>--%>
					<%--					<col width="16%"/>--%>
					<col width="280" />
					<col width="280" />
					<col width="280" />
					<col />
					<col width="130"/>
				</colgroup>
				<tbody>
				<tr>
					<th>姓名：</th>
					<td>
						&nbsp;${user.userName}
					</td>
					<th>性别：</th>
					<td>
						<c:if test="${user.sexId eq userSexEnumMan.id}">&nbsp;${userSexEnumMan.name}</c:if>
						<c:if test="${user.sexId eq userSexEnumWoman.id}">&nbsp;${userSexEnumWoman.name}</c:if>
					</td>
					<td rowspan="${empty doctorRecruit.changeSpeId ? 6:7}" style="text-align: center;padding: 0">
						<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg"
							 width="130px" height="196px"
							 onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
						<br>

						<p style="line-height: 50px;" title="<div id='changeInfo' class='pxxx' >
							<div class='changeinfo' id='changeInfoContent' style='height: 65px;width:300px;'>
								<i class='icon_up'></i>
								<table border='0' cellpadding='0' cellspacing='0' class='grid' style='width: 100%'>
									1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，图片大小大于150K且小于300K，人像正立。
									<br>2、该照片用于结业证书，请慎重认真上传！
								</table>
							</div></div>">照片要求</p>
					</td>
				</tr>
				<tr>
					<th>证件类型：</th>
					<td>

						<c:forEach items="${certificateTypeEnumList}" var="certificateType">
							<c:if test="${user.cretTypeId eq certificateType.id}">&nbsp;${certificateType.name}</c:if>
						</c:forEach>

					</td>
					<th>证&nbsp;件&nbsp;号：</th>
					<td>
						&nbsp;${user.idNo}
					</td>
				</tr>
				<tr>
					<th>年级：</th>
					<td>
						&nbsp;${doctorRecruit.sessionNumber}
					</td>
					<th>结业年份：</th>
					<td>
						&nbsp;${doctorRecruit.graduationYear}
					</td>
				</tr>
				<tr>
					<th>人员类型：</th>
					<td>
						&nbsp;${resDoctor.doctorTypeName}
					</td>
					<th>培训基地：</th>
					<td>
						<c:if test="${empty doctorRecruit.jointOrgFlow}">&nbsp;${doctorRecruit.orgName}</c:if>
						<c:if test="${!empty doctorRecruit.jointOrgFlow}">&nbsp;${doctorRecruit.jointOrgName}</c:if>
					</td>
				</tr>
				<tr>
					<th>培训类别：</th>
					<td>
						&nbsp;${resDoctor.trainingTypeName}
					</td>
					<th>培训专业：</th>
					<td>
						&nbsp;${doctorRecruit.speName}
					</td>
				</tr>
				<tr>
					<th>学位：</th>
					<td>
						<c:choose>
							<c:when test="${ not empty userResumeExt.doctorDegreeName}">
								&nbsp;${userResumeExt.doctorDegreeName}
								<c:if test="${empty userResumeExt.doctorDegreeName}">
									<span style="color: red" >未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</c:when>
							<c:when test="${ not empty userResumeExt.masterDegreeName}">
								&nbsp;${userResumeExt.masterDegreeName}
								<c:if test="${empty userResumeExt.masterDegreeName}">
									<span style="color: red" >未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</c:when>
							<c:otherwise>
								&nbsp;${userResumeExt.degreeName}
								<c:if test="${empty userResumeExt.degreeName}">
									<span style="color: red" >未填写！</span>
									<c:set var="canApply" value="N"></c:set>
								</c:if>
							</c:otherwise>
						</c:choose>
					</td>
					<th>联系方式：</th>
					<td>
						&nbsp;${user.userPhone}
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<div class="main_bd" id="div_table_1" style="padding-top: 20px;" >
			<h4 >报考信息</h4>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info" >
				<colgroup>
					<col width="280"/>
					<col width="280"/>
					<col width="280"/>
					<col />
				</colgroup>
				<tbody>
				<tr>
					<th>学历：</th>
					<td>&nbsp;${user.educationName}</td>
					<th>毕业证书编号：</th>
					<td>&nbsp;${resDoctor.certificateNo}
					</td>
				</tr>
				<tr>
					<th>培训开始时间：</th>
					<td>
						&nbsp;${startDate}
					</td>
					<th>培训结束时间 ：</th>
					<td>
						&nbsp;${endDate}
					</td>
				</tr>
				<tr>
					<th>报考资格材料：</th>
					<td>
						&nbsp;${jsresGraduationApply.graduationMaterialName}
					</td>
					<th>报考资格材料编码：</th>
					<td>
						&nbsp;${jsresGraduationApply.graduationMaterialCode}
					</td>
				</tr>
				<tr>
					<th>取得资格时间：</th>
					<td >
						&nbsp;${empty userResumeExt.haveQualificationCertificateTime?'暂无':userResumeExt.haveQualificationCertificateTime}
					</td>
					<th>执业类型：</th>
					<td>
						&nbsp;${jsresGraduationApply.graduationCategoryName}
					</td>

				</tr>
				<tr>
					<th>执业范围：</th>
					<td>
						&nbsp;${jsresGraduationApply.graduationScopeName}
					</td>
					<th>培训年限：</th>
					<td>
						<c:if test="${'OneYear' eq doctorRecruit.trainYear}">&nbsp;一年</c:if>
						<c:if test="${'TwoYear' eq doctorRecruit.trainYear}">&nbsp;两年</c:if>
						<c:if test="${'ThreeYear' eq doctorRecruit.trainYear}">&nbsp;三年</c:if>
						<span style="padding-left: 30px">
							<c:if test="${empty doctorRecruit.trainYear ||  'ThreeYear' eq doctorRecruit.trainYear}">&nbsp;无减免证明</c:if>
							<c:if test="${'TwoYear' eq doctorRecruit.trainYear ||  'OneYear' eq doctorRecruit.trainYear}">
								<c:if test="${'DoctorTrainingSpe' eq doctorRecruit.catSpeId}">
									<c:if test="${empty reduction}">
										&nbsp;无减免证明
									</c:if>
									<c:if test="${not empty reduction}">
										[<a onclick="showReductionImg('${reduction.recordFlow}')" target="_blank">查看减免证明</a>]
									</c:if>
								</c:if>
								<c:if test="${'DoctorTrainingSpe' ne doctorRecruit.catSpeId}">
									&nbsp;无减免证明
								</c:if>
							</c:if>
							</span>
					</td>
				</tr>
				<tr>
					<th>培训登记手册完成情况：</th>
					<td>
						<c:if test="${(not empty userResumeExt.registeManua) and userResumeExt.registeManua eq GlobalConstant.PASS}">&nbsp;已完成</c:if>
						<c:if test="${(not empty userResumeExt.registeManua) and userResumeExt.registeManua eq GlobalConstant.UNPASS}">&nbsp;未完成</c:if>
						<c:if test="${(not empty userResumeExt.registeManua) and userResumeExt.registeManua eq GlobalConstant.NORMAL}">&nbsp;正常</c:if>
					</td>
					<th>实际轮转时间（月）：</th>
					<td>&nbsp;${allMonth}</td>
				</tr>
				<tr>
					<th>数据填写比例：</th>
					<td>
						<a style="cursor: pointer"
						   onclick="lookInfoData()"
						>
						&nbsp;${empty avgBiMap.avgComplete?0:avgBiMap.avgComplete}%
						</a>
					</td>
					<th>数据审核比例：</th>
					<td>&nbsp;${empty avgBiMap.avgAudit?0:avgBiMap.avgAudit}%</td>
				</tr>
<%--				<tr>--%>
<%--					<th>受疫情影响未完成的培训：</th>--%>
<%--					<td >&nbsp;${jsresGraduationApply.remark}</td>--%>
<%--                    <th>是否军队人员：</th>--%>
<%--                    <td>--%>
<%--                        <c:if test="${not empty userResumeExt.armyType}">--%>
<%--							&nbsp;${pdfn:getArmyTypeEnumName(userResumeExt.armyType)}--%>
<%--                        </c:if>--%>
<%--                    </td>--%>
<%--				</tr>--%>
				</tbody>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info" >
				<tbody>
				<c:if test="${isAssiGeneral eq 'N'}">
					<tr>
						<th style="text-align: center;width:20%" rowspan="2">公共科目成绩</th>
						<td style="text-align: center;">卫生法律和法规</td>
						<td style="text-align: center;">循证医学</td>
						<td style="text-align: center;">临床思维与人际沟通</td>
						<td style="text-align: center;">重点传染病防治知识</td>
						<td style="text-align: center;">是否合格</td>
					</tr>
					<tr>
						<td style=" text-align: center;">
							&nbsp;${extScore.lawScore}
						</td>
						<td style=" text-align: center;">
							&nbsp;${extScore.medicineScore}
						</td>
						<td style=" text-align: center;">
							&nbsp;${extScore.clinicalScore}
						</td>
						<td style=" text-align: center;">
							&nbsp;${extScore.ckScore}
						</td>
						<td style=" text-align: center;" id="mouseOver">以上传附件为准
<%--							<c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">&nbsp;合格</c:if>--%>
<%--							<c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">&nbsp;不合格</c:if>--%>
							<div style="display: none;color: #00a0ff" id="showMouseOver">
								成绩由${publicScoreUser}导入
							</div>
						</td>
					</tr>
				</c:if>
				<c:if test="${isAssiGeneral eq 'Y'}">
					<tr>
						<th style="text-align: center;"  width="20%" rowspan="2" >全科医学及相关理论知识考核</th>
						<td style="text-align: center;" >成绩</td>
						<td style="text-align: center;">是否合格</td>
					</tr>
					<tr>
						<td style="text-align: center;" >
							&nbsp;${publicScore.theoryScore}
						</td>
						<td style=" text-align: center;" id="mouseOver2">以上传附件为准
<%--							<c:if test="${publicScore.skillScore eq GlobalConstant.PASS}">&nbsp;合格</c:if>--%>
<%--							<c:if test="${publicScore.skillScore eq GlobalConstant.UNPASS}">&nbsp;不合格</c:if>--%>
							<div style="display: none;color: #00a0ff" id="showMouseOver2">
								成绩由${publicScoreUser}导入
							</div>
						</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
		<div class="main_bd"  style="padding-top: 20px;" >
			<h4  style="float:none;">证书材料</h4>
			<div style="display: flex;justify-content: space-between">
				<div class="div_table" >
					<div style="width: 280px;height: 200px">
						<c:if test="${not empty jsresGraduationApply.certificateUri}">
							<ul>
								<li class="ratateImg" style="height: 200px;">
									<img src="${sysCfgMap['upload_base_url']}/${jsresGraduationApply.certificateUri}"
										 style="width: 100%;height: 90%;">
								</li>
							</ul>
						</c:if>
						<c:if test="${empty jsresGraduationApply.certificateUri}">
							<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
						</c:if>
					</div>
					<h5 style="text-align: center;">毕业证书</h5>
				</div>
				<div class="div_table">
					<div style="width: 280px;height: 200px">
						<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_N }">
							<c:if test="${'暂无' ne practicingMap.graduationMaterialUri}">
								<c:if test="${not empty practicingMap.graduationMaterialUri}">
									<ul>
										<li class="ratateImg" style="height: 200px;">
											<img src="${sysCfgMap['upload_base_url']}/${jsresGraduationApply.certificateUri}"
												 style="width: 100%;height: 90%;">
										</li>
									</ul>
								</c:if>
								<c:if test="${empty practicingMap.graduationMaterialUri}">
									<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
								</c:if>
							</c:if>
							<c:if test="${'暂无' eq practicingMap.graduationMaterialUri}">
								<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
							</c:if>
						</c:if>
						<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
							<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_N }">
								<c:if test="${not  empty userResumeExt.qualificationMaterialId2Url}">
									<ul>
										<li class="ratateImg" style="height: 200px;">
											<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialId2Url}"
												 style="width: 100%;height: 90%;">
										</li>
									</ul>
								</c:if>
								<c:if test="${empty userResumeExt.qualificationMaterialId2Url}">
									<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
								</c:if>
							</c:if>
						</c:if>
						<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
							<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">
								<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_N }">
									<c:if test="${not  empty userResumeExt.doctorQualificationCertificateUrl}">
										<ul>
											<li class="ratateImg" style="height: 200px;">
												<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorQualificationCertificateUrl}"
													 style="width: 100%;height: 90%;">
											</li>
										</ul>
									</c:if>
									<c:if test="${empty userResumeExt.doctorQualificationCertificateUrl}">
										<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
									</c:if>
								</c:if>
							</c:if>
						</c:if>
						<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
							<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">
								<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_Y }">
									<c:if test="${not  empty userResumeExt.doctorPracticingCategoryUrl}">
										<ul>
											<li class="ratateImg" style="height: 200px;">
												<img src="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorPracticingCategoryUrl}"
													 style="width: 100%;height: 90%;">
											</li>
										</ul>
									</c:if>
									<c:if test="${empty userResumeExt.doctorPracticingCategoryUrl}">
										<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
									</c:if>
								</c:if>
							</c:if>
						</c:if>
					</div>
					<h5 style="text-align: center;">
						<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_N }">
							医师执业证书
						</c:if>
						<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
							<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_N }">
								成绩单
							</c:if>
						</c:if>
						<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
							<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">
								<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_N }">
									医师资格证书
								</c:if>
							</c:if>
						</c:if>
						<c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">
							<c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">
								<c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_Y }">
									医师执业证书
								</c:if>
							</c:if>
						</c:if>
					</h5>
				</div>
				<div class="div_table" >
					<div style="width: 280px;height: 200px" >
						<c:if test="${not empty file.filePath}">
							<ul>
								<li class="ratateImg" style="height: 200px;">
									<img src="${sysCfgMap['upload_base_url']}/${file.filePath}" style="width: 100%;height: 90%;">
								</li>
							</ul>
						</c:if>
						<c:if test="${empty file.filePath}">
							<span style="margin-top: 100px;margin-left: 110px;">暂未上传</span>
						</c:if>
					</div>
					<h5 style="text-align: center;">公共科目成绩</h5>
				</div>
			</div>
		</div>
		<%--<div class="main_bd" id="div_table_0">--%>
			<%--<h4>各科室轮转情况</h4>--%>
			<%--<table border="0" cellpadding="0" cellspacing="0" class="grid lz_table" style="width: 99%;padding:0px;margin: 0px;">--%>
				<%--<tr>--%>
					<%--<th style="width: 100px;min-width: 100px;max-width: 100px;">轮转类型</th>--%>
					<%--<th style="width: 100px;min-width: 100px;max-width: 100px;">标准科室</th>--%>
					<%--<th style="width: 100px;min-width: 100px;max-width: 100px;">要求时间/实<br>际时间(月)</th>--%>
					<%--<th style="width: 100px;min-width: 100px;max-width: 100px;">轮转科室</th>--%>
					<%--<th style="width: 200px;min-width: 200px;max-width: 200px;">轮转时间</th>--%>
					<%--<th style="width: 400px;min-width: 400px;max-width: 400px;">出科考核表</th>--%>
					<%--<th style="width: 60px;min-width: 60px;max-width: 60px;">总比例</th>--%>
					<%--<th style="width: 60px;min-width: 60px;max-width: 60px;">补填比例</th>--%>
					<%--<th style="width: 60px;min-width: 60px;max-width: 60px;">审核比例</th>--%>
				<%--</tr>--%>

				<%--<c:set value="" var="lastGroupFlow"></c:set>--%>
				<%--<c:set value="" var="lastDeptFlow"></c:set>--%>
				<%--<c:set value="0" var="allSchMonth"></c:set>--%>
				<%--<c:set value="0" var="allRealMonth"></c:set>--%>
				<%--<c:forEach items="${groupList}" var="group" varStatus="groupStatus">--%>
					<%--<c:forEach items="${rotationDeptMap[group.groupFlow]}" var="dept" varStatus="deptStatus">--%>
						<%--<c:set value="${allSchMonth+0+dept.schMonth}" var="allSchMonth"></c:set>--%>

						<%--<c:set var="resultKey" value="${group.groupFlow}${dept.standardDeptId}"/>--%>
						<%--<c:set value="${allRealMonth+0+(empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey])}"--%>
							   <%--var="allRealMonth"></c:set>--%>
						<%--<c:set var="bi" value="${biMap[dept.recordFlow]}"></c:set>--%>
						<%--<c:if test="${not empty resultMap[resultKey]}">--%>
							<%--<c:forEach items="${resultMap[resultKey]}" var="result" varStatus="resultStatus">--%>
								<%--<c:choose>--%>
									<%--<c:when test="${!(lastGroupFlow eq group.groupFlow) and--%>
							<%--!(lastDeptFlow eq dept.recordFlow) and resultStatus.first--%>
							<%--}">--%>
										<%--<tr>--%>
											<%--<td rowspan="${(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))<=0?0:(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))}">--%>
												<%--<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">--%>
													<%--${group.schStageName}：--%>
												<%--</c:if>--%>
													<%--${group.groupName}【${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}】--%>
												<%--<br>(轮转时间：${group.schMonth}月--%>
												<%--<c:if test="${group.isRequired eq GlobalConstant.FLAG_N}">--%>
													<%--&#12288;${group.selTypeName}：${group.deptNum}<c:if--%>
														<%--test="${group.selTypeId eq schSelTypeEnumFree.id}">~${group.maxDeptNum}</c:if>--%>
												<%--</c:if>)--%>
											<%--</td>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">--%>
													<%--${dept.standardDeptName}--%>
											<%--</td>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">${dept.schMonth}/${empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey]}</td>--%>
											<%--<td>${result.schDeptName}</td>--%>
											<%--<td>${result.schStartDate}~${result.schEndDate}</td>--%>
											<%--<c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">--%>
												<%--<div style="margin-top:10px; ">--%>
													<%--<c:forEach var="image" items="${imagelist}" varStatus="status">--%>
														<%--<ul>--%>
															<%--<li class="ratateImg">--%>
																<%--<img width="80px" height="80px" src="${image.imageUrl}">--%>
															<%--</li>--%>
														<%--</ul>--%>
													<%--</c:forEach>--%>
												<%--</div>--%>
											<%--</td>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">--%>
												<%--<a onclick="catalogue('${dept.recordFlow}','${recruit.doctorFlow}');">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</a>--%>
											<%--</td>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>--%>
										<%--</tr>--%>
									<%--</c:when>--%>
									<%--<c:when test="${(lastGroupFlow eq group.groupFlow) and--%>
							<%--!(lastDeptFlow eq dept.recordFlow) and resultStatus.first--%>
							<%--}">--%>
										<%--<tr>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">--%>
													<%--${dept.standardDeptName}--%>
											<%--</td>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">${dept.schMonth}/${empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey]}</td>--%>
											<%--<td>${result.schDeptName}</td>--%>
											<%--<td>${result.schStartDate}~${result.schEndDate}</td>--%>
											<%--<c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">--%>
												<%--<div style="margin-top:10px; ">--%>
													<%--<c:forEach var="image" items="${imagelist}" varStatus="status">--%>
														<%--<ul>--%>
															<%--<li class="ratateImg">--%>
																<%--<img width="80px"--%>
																	 <%--height="80px"--%>
																	 <%--src="${image.imageUrl}">--%>
															<%--</li>--%>
														<%--</ul>--%>
													<%--</c:forEach>--%>
												<%--</div>--%>
											<%--</td>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">--%>
												<%--<a onclick="catalogue('${dept.recordFlow}','${recruit.doctorFlow}');">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</a>--%>
											<%--</td>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>--%>
											<%--<td rowspan="${deptRowSpan[resultKey]}">${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>--%>
										<%--</tr>--%>
									<%--</c:when>--%>
									<%--<c:when test="${(lastGroupFlow eq group.groupFlow) and--%>
							<%--(lastDeptFlow eq dept.recordFlow) and !(resultStatus.first)}">--%>
										<%--<tr>--%>
											<%--<td>${result.schDeptName}</td>--%>
											<%--<td>${result.schStartDate}~${result.schEndDate}</td>--%>
										<%--</tr>--%>
									<%--</c:when>--%>
								<%--</c:choose>--%>
								<%--<c:set value="${group.groupFlow}" var="lastGroupFlow"></c:set>--%>
								<%--<c:set value="${dept.recordFlow}" var="lastDeptFlow"></c:set>--%>
							<%--</c:forEach>--%>
						<%--</c:if>--%>
						<%--<c:if test="${empty resultMap[resultKey]}">--%>
							<%--<tr>--%>
								<%--<c:if test="${deptStatus.first}">--%>
									<%--<td rowspan="${(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))<=0?0:(groupRowSpan[group.groupFlow]-fn:length(rotationDeptMap[group.groupFlow]))}">--%>
										<%--<c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">--%>
											<%--${group.schStageName}：--%>
										<%--</c:if>--%>
											<%--${group.groupName}【${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}】--%>
										<%--<br>(轮转时间：${group.schMonth}月--%>
										<%--<c:if test="${group.isRequired eq GlobalConstant.FLAG_N}">--%>
											<%--&#12288;${group.selTypeName}：${group.deptNum}<c:if--%>
												<%--test="${group.selTypeId eq schSelTypeEnumFree.id}">~${group.maxDeptNum}</c:if>--%>
										<%--</c:if>)--%>
									<%--</td>--%>
								<%--</c:if>--%>
								<%--<td>--%>
										<%--${dept.standardDeptName}--%>
								<%--</td>--%>
								<%--<td>${dept.schMonth}/0</td>--%>
								<%--<td></td>--%>
								<%--<td></td>--%>
								<%--<c:set var="imagelist" value="${afterImgMap[dept.recordFlow]}"></c:set>--%>
								<%--<td rowspan="${deptRowSpan[resultKey]}">--%>
									<%--<div style="margin-top:10px; ">--%>
										<%--<c:forEach var="image" items="${imagelist}" varStatus="status">--%>
											<%--<ul>--%>
												<%--<li class="ratateImg">--%>
													<%--<img width="80px" height="80px"--%>
														 <%--src="${image.imageUrl}">--%>
												<%--</li>--%>
											<%--</ul>--%>
										<%--</c:forEach>--%>
									<%--</div>--%>
								<%--</td>--%>
								<%--<td>--%>
									<%--<a onclick="catalogue('${dept.recordFlow}','${recruit.doctorFlow}');">${empty bi.completeBi ? 0: bi.completeBi}${bi.completeBi=='-' ? '':'%'}</a>--%>
								<%--</td>--%>
								<%--<td>${empty bi.outCompleteBi ? 0: bi.outCompleteBi}${bi.outCompleteBi=='-' ? '':'%'}</td>--%>
								<%--<td>${empty bi.auditBi ? 0:bi.auditBi}${bi.auditBi=='-' ? '':'%'}</td>--%>
							<%--</tr>--%>
							<%--<c:set value="${group.groupFlow}" var="lastGroupFlow"></c:set>--%>
							<%--<c:set value="${dept.recordFlow}" var="lastDeptFlow"></c:set>--%>
						<%--</c:if>--%>
					<%--</c:forEach>--%>
				<%--</c:forEach>--%>
				<%--<tr>--%>
					<%--<td colspan="2">合计时间</td>--%>
					<%--<td colspan="">${allSchMonth}/<fmt:formatNumber type="number" value="${allRealMonth}"--%>
																	<%--maxFractionDigits="2"/></td>--%>
					<%--<td colspan="3">平均比例</td>--%>
					<%--<td>${empty avgBiMap.avgComplete ? 0:avgBiMap.avgComplete}%</td>--%>
					<%--<td>${empty avgBiMap.avgOutComplete ? 0:avgBiMap.avgOutComplete}%</td>--%>
					<%--<td>${empty avgBiMap.avgAudit ? 0: avgBiMap.avgAudit}%</td>--%>
				<%--</tr>--%>
			<%--</table>--%>
		<%--</div>--%>
		<c:if test="${not empty delayNum or not empty signNum}">
		<div class="main_bd"  style="padding-top: 20px;" >
			<h4  style="float:none;">异常情况</h4>
			<div style="display: flex;justify-content: space-between">
				<p style="color: red">
					<c:if test="${not empty delayNum }">
						该学员已经延期${delayNum}次,
					</c:if>
					<c:if test="${not empty signNum }">
						该学员已经考核超过3次,这次为第${signNum}次,
					</c:if>
					存在异常情况
				</p>
			</div>
		</div>
		</c:if>
		<div class="main_bd" >
			<h4>审核意见<c:if test="${tempDoctorFlag eq 'Y'}"><font color="red">（该学员为异常报考人员，请谨慎审核！）</font></c:if></h4>
			<form id="myform">
				<table border="0" cellpadding="0" cellspacing="0" style="border:none; width:99%;text-align: center;"class="base_info" >
					<colgroup>
						<col width="25%"/>
						<col width="25%"/>
						<c:if test="${roleFlag eq 'global'}">
							<col width="25%"/>
						</c:if>
					</colgroup>
					<tbody>
					<tr style=" width:100%;text-align: center;">
						<td style="text-align: left;border:none;">&#12288;&#12288;<input type="radio" name="auditStatusId" value="${jsResAuditStatusEnumPassed.id}" <c:if test="${avgCompleteDouble < 80.00 and roleFlag eq 'charge' and recruit.catSpeId eq 'DoctorTrainingSpe'}">disabled="true"</c:if> onclick="check();">通过 </td>
						<td style="text-align: left;border:none;">&#12288;&#12288;<input type="radio" name="auditStatusId" value="${jsResAuditStatusEnumNotPassed.id}" onclick="check();">不通过</td>
						<c:if test="${roleFlag eq 'global'}">
							<td style="text-align: left;border:none;">&#12288;&#12288;<input type="radio" name="auditStatusId" value="Black" onclick="check();">驳回</td>
						</c:if>
					</tr>
					<tr style=" width:100%;text-align: center;">
						<td style="text-align: left;padding-left: 30px;border: none;">
							<%--<div style="min-width: 250px;float: left;border:1px">--%>
							<%--<input name="auditReason" id="passReason" type="text"class="input"  style="width: 100%;border: 1px solid #e7e7eb;margin-left: 0px;" readonly="readonly" placeholder="点击选择原因"/>--%>
							<%--</div>--%>
							<select id="passReason1" class="select" <c:if test="${avgCompleteDouble < 80.00   and roleFlag eq 'charge' and recruit.catSpeId eq 'DoctorTrainingSpe'}">disabled="true"</c:if> style="width: 250px;margin-bottom: 12px;">
								<option value=""></option>
								<option value="无">无</option>
                                <c:if test="${roleFlag eq 'global'}">
                                    <option value="可考试，公共课合格后发证">可考试，公共课合格后发证</option>
                                    <option value="可考试，提供医师执业证书后发证">可考试，提供医师执业证书后发证</option>
                                    <option value="可考试，提供医师执业证书，公共课合格后发证">可考试，提供医师执业证书，公共课合格后发证</option>
                                    <option value="可考试，补轮转后发证">可考试，补轮转后发证</option>
                                    <option value="可考试，变更执业范围后发证">可考试，变更执业范围后发证</option>
                                    <option value="可考试，出科表上传完整后发证">可考试，出科表上传完整后发证</option>
                                    <option value="可考试，出科表上传完整，提供医师执业证书后发证">可考试，出科表上传完整，提供医师执业证书后发证</option>
                                    <option value="可考试，出科表上传完整，公共课合格后发证">可考试，出科表上传完整，公共课合格后发证</option>
                                    <option value="可考试，出科表上传完整，提供医师执业证书,公共课合格后发证">可考试，出科表上传完整，提供医师执业证书,公共课合格后发证</option>
                                    <option value="其他">其他</option>
                                </c:if>
                                <c:if test="${roleFlag eq 'local' or roleFlag eq 'jointOrg'}">
									<option value="我已审核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。">我已审核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。</option>
								</c:if>
								<c:if test="${roleFlag eq 'charge'}">
									<option value="我已复核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。">我已复核该医师的结业考核资格相关材料原件，以上内容来源于上述材料，内容真实。</option>
								</c:if>
							</select>
							<textarea id="passReason2" ></textarea>
						</td>
						<td style="text-align: left;padding-left: 30px;border: none;" >
							<%--<div style="min-width: 250px;float: left;border:1px">--%>
							<%--<input name="auditReason" id="unpassReason" type="text"class="input"  style="width: 100%;border: 1px solid #e7e7eb;margin-left: 0px;" readonly="readonly" placeholder="点击选择原因"/>--%>
							<%--</div>--%>
							<select id="unpassReason1" class="select" style="width: 250px;    margin-bottom: 12px;">
								<option value=""></option>
								<option value="不通过，APP填写不符合要求或出科表上传不符合要求">不通过，APP填写不符合要求或出科表上传不符合要求</option>
								<option value="不通过，超出执业范围">不通过，超出执业范围</option>
								<option value="不通过，培训时间不足">不通过，培训时间不足</option>
								<option value="不通过，无材料或缺某一个材料">不通过，无材料或缺某一个材料</option>
								<option value="不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格">不通过，非${pdfn:getCurrYear()-1}年国家执业医师考试成绩单/国家执业医师考试成绩不合格</option>
								<option value="不通过，培训手册填写不符合要求">不通过，培训手册填写不符合要求</option>
                                <option value="不通过，减免材料不符合要求">不通过，减免材料不符合要求</option>
                                <option value="不通过，数据填写比例少于80%,请补填数据">不通过，数据填写比例少于80%,请补填数据</option>
                                <option value="其他">其他</option>
							</select>
							<textarea id="unpassReason2"  ></textarea>
						</td>
						<c:if test="${roleFlag eq 'global'}">
							<td style="text-align: left;padding-left: 30px;border: none;">
								<select id="blackReason" class="select" style="width: 250px;    margin-bottom: 12px;">
									<option value=""></option>
									<option value="重填资料，重新提交。">重填资料，重新提交。</option>
								</select>
								<textarea id="blackReason2"  ></textarea>
							</td>
						</c:if>
					</tr>
					<input name="auditReason" id="auditReason" value="" hidden>
					<input name="applyFlow" value="${param.applyFlow}" hidden>
					</tbody>
				</table>
			</form>
			<c:if test="${not  empty logs}">
                <c:set var="k" value="${jsresGraduationApply.testId}_asse_application"/>
				<ul class="sh_ul">
					<c:forEach items="${logs}" var="log">
                        <%-- 省厅意见 判断是否有权限展示，其他角色意见不受限制 需求人：徐开宏 时间：2020年6月24日 禅道需求编码：319--%>
						<c:choose>
							<c:when test="${sysCfgMap[k] eq 'Y'}">
								<li class="flex" style="height:auto;">
									<div class="icon-blue"style="width:1.5%;"></div>
									<span style="width:15%;">${log.auditTime}</span>
									<span style="width:5%;">${log.auditRoleName}</span>
									<span style="width:5%;">${log.auditStatusName}</span>
									<span style="width:73%;">${log.auditReason}</span>
								</li>
							</c:when>
							<c:when test="${sysCfgMap[k] eq 'N' or empty sysCfgMap[k]}">
								<c:if test="${(roleFlag ne 'global' and log.auditRoleId ne 'global') or roleFlag eq 'global'}">
									<li class="flex" style="height:auto;">
										<div class="icon-blue"style="width:1.5%;"></div>
										<span style="width:15%;">${log.auditTime}</span>
										<span style="width:5%;">${log.auditRoleName}</span>
										<span style="width:5%;">${log.auditStatusName}</span>
										<span style="width:73%;">${log.auditReason}</span>
									</li>
								</c:if>
							</c:when>
						</c:choose>
					</c:forEach>
				</ul>
			</c:if>
			<div class="main_bd" id="div_table_1" >
				<div align="center" style="margin-top: 20px; margin-bottom:20px;">
					<c:if test="${ flag eq 'Y'}">
						<input type="button" id="" class="btn_green" onclick="applyAudit('${roleFlag}');" value="提交"/>&nbsp;
					</c:if>
					<input type="button" class="btn_green" onclick="print('${resDoctor.doctorFlow}');" value="打印"/>
					<input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关闭"/>&nbsp;
				</div>
			</div>
		</div>

	</div>
</div>
