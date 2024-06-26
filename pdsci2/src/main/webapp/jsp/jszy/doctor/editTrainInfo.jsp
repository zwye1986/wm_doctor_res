<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>${sysCfgMap['sys_title_name']}</title>
	<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="font" value="true"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_cxselect" value="true"/>
		<jsp:param name="jquery_form" value="true"/>
	</jsp:include>
	<script type="text/javascript">
		$(document).ready(function(){
			init();
			$("#file").live("change",function(){
				uploadFile();
			});
		});

		function init(){
			setSessionNumber($('#trainDate'));
			getCityArea();
			//地区回显
			<c:if test="${not empty doctorRecruit.placeId}">
			$("#cityId").attr("data-value","${doctorRecruit.placeId}");
			</c:if>
			$('#trainDate').datepicker({
				startDate:"${sysCfgMap['jsres_is_apply_start']}",
				endDate:"${sysCfgMap['jsres_is_apply_end']}",
			}).on('changeDate',function(d){
				getTrainCategory();
				setSessionNumber($('#trainDate'));
				searchOrgList();
			});
			//加载培训类别
			getTrainCategory();

			//减免培养年限证明：
			showUploadFileTr();
			changeSecondSpe("${doctorRecruit.catSpeId}",true);
		}

		/**
		 * 加载培训类别
		 */
		function getTrainCategory(){
			//$('#trainDate').click(function(e){e.preventDefault();});
			var cfgDate = "${applicationScope.sysCfgMap['jsres_trainCategory_cfgDate']}";
			$("#catSpeSpan2").html($("#clone_Independent").html());
			<c:if test="${!empty addRecord}">
			<c:forEach items="${catSpeIdList }" var="catSpeId">
			hideTrainCategory("${catSpeId}");
			</c:forEach>
			</c:if>
			//回显
			$("#catSpeTd").find("[name='catSpeId'][value='${doctorRecruit.catSpeId}']").attr("checked","checked");
			//searchResOrgSpeList();
		}

		function hideTrainCategory(catSpeId){
			//限制培训类别（隐藏）
			if(catSpeId != "${jszyTrainCategoryEnumAssiGeneral.id}"){//一阶段/住院医师、二阶段
				$("#editForm .label_${jszyTrainCategoryEnumAssiGeneral.id}").hide();
				if("${isWMSecondRecPassed}" == "true"){//二阶段审核通过
					$("#editForm .label_${jszyTrainCategoryEnumWMFirst.id}").hide();
					$("#editForm .label_${jszyTrainCategoryEnumDoctorTrainingSpe.id}").hide();
				}
			}
		}

		function getCityArea(){
			var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
			var provIds = "320000";
			jboxGet(url,null, function(json) {
				// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
				var newJsonData=new Array();
				var j=0;
				for(var i=0;i<json.length;i++){
					if(provIds==json[i].v){
						var citys=json[i].s;
						for(var k=0;k<citys.length;k++){
							newJsonData[j++]=citys[k];
						}
					}
				}
				$.cxSelect.defaults.url = newJsonData;
				$.cxSelect.defaults.nodata = "none";
				$("#provCityAreaId").cxSelect({
					selects : ["city"/* ,"area" */],
					nodata : "none",
					firstValue : ""
				});
			},null,false);
		}

		/**
		 * 加载培训基地
		 */
		function searchOrgList(){
			//清空原来培训基地！！！
			$("select[name='orgFlow'] option[value != '']").hide();
			var cityId = $("#cityId").val();
			if(cityId==""){
				return false;
			}
			$("select[name='orgFlow'] option[orgCityId='"+cityId+"']").show();
//			jboxStartLoading();
			/*var url = "<s:url value='/jszy/doctor/searchOrgList'/>?orgCityId=" + cityId;
			var sessionNumber = $("#sessionNumber").val();
			if(sessionNumber >= "2015"){
				url = url + "&jointFlag=${GlobalConstant.FLAG_Y}";//==>查询国家、省级、国家协同基地
			}
			jboxGet(url, null, function(resp){
				jboxEndLoading();
				if("" != resp){
					var dataObj = resp;
					for(var i = 0; i<dataObj.length; i++){
						var orgFlow = dataObj[i].orgFlow;
						var orgName = dataObj[i].orgName;
						$option =$("<option></option>");
						$option.attr("value", orgFlow);
						$option.text(orgName);
						$("select[name=orgFlow]").append($option);
					}
					if(""!="${doctorRecruit.orgFlow}"){
						$("select[name=orgFlow] option[value='${doctorRecruit.orgFlow}']").attr("selected",true);
						searchResOrgSpeList();
					}
				}
			}, null, false);
			 */
		}




		function changeSecondSpe(value,ifFistLoad)
		{
			console.log(value);
			if(value=='${jszyTrainCategoryEnumChineseMedicine.id}')
			{
				$("#secondSpeId").show();

			}else{
				$("#secondSpeId").hide();
			}
			if(!ifFistLoad){
				$("select[name=secondSpeId]").find("option[value='']").attr("selected","selected");
			}
			if(value=='${jszyTrainCategoryEnumTCMAssiGeneral.id}'){
				$("#trainYear").find("option[value='${jszyResTrainYearEnumTwoYear.id}']").attr("selected","selected");
				$("input[name='trainYear']").val(${jszyResTrainYearEnumTwoYear.id});
			}else {
				$("#trainYear").find("option[value='${jszyResTrainYearEnumThreeYear.id}']").attr("selected","selected");
				$("input[name='trainYear']").val(${jszyResTrainYearEnumThreeYear.id});
			}
		}
		/**
		 * 加载专业
		 */
		function searchResOrgSpeList(){
			var year=$("#sessionNumber").val();
			//清空原来专业！！！
			$("select[name=speId] option[value != '']").remove();
			var catSpeId = $("#editForm input[name='catSpeId']:checked").val();
			if(catSpeId==undefined || catSpeId == null || catSpeId ==""){
				return false;
			}
			showUploadFileTr();
			//过滤已保存专业
			/* var exitSpeIdArray = new Array();
			 var $exitCatSpeId =  window.parent.$("."+catSpeId);
			 $.each($exitCatSpeId, function(i,n){
			 var speId =  $(n).val();
			 exitSpeIdArray.push(speId);
			 }); */
			//二阶段：显示上传结业证书
			if("${jszyTrainCategoryEnumWMSecond.id}" == catSpeId){
				$("#uploadFileDiv").show();
				$("#completeFileUrlFlag").val("${GlobalConstant.FLAG_Y}");
			}else{
				$("#uploadFileDiv").hide();
				$("#completeFileUrlFlag").val("${GlobalConstant.FLAG_N}");
			}
			var orgFlow = $("#orgFlow").val();
			if(orgFlow==""){
				return false;
			}
			jboxStartLoading();
			var url = "<s:url value='/jszy/doctor/searchResOrgSpeList?orgFlow='/>" + orgFlow +"&speTypeId="+catSpeId+"&sessionNumber="+year;
			jboxGet(url, null, function(resp){
				jboxEndLoading();
				var dataObj = resp;
				if(dataObj.length>0){
					for(var i = 0; i<dataObj.length; i++){
						var addFlag = true;
						var speId = dataObj[i].speId;
						/* for(var j = 0; j<exitSpeIdArray.length; j++){//过滤专业
						 if(speId == exitSpeIdArray[j] && speId != "${doctorRecruit.speId}"){//不过滤当前专业
						 addFlag = false;
						 break;
						 }
						 }  */
						if(addFlag){
							var speName = dataObj[i].speName;
							$option =$("<option></option>");
							$option.attr("value",speId);
							$option.text(speName);
							$("select[name=speId]").append($option);
						}
					}
				}
				if(""!="${doctorRecruit.speId}"){
					$("select[name=speId] option[value='${doctorRecruit.speId}']").attr("selected",true);
				}
				changeSpeId( $("#editForm select[name='speId']"));
			}, null , false);
		}
		function validate(){
			var sessionNum=$("#sessionNumber").val();
			var doctorFlow=$("#doctorFlow").val();
			if(sessionNum !=""){
				jboxPost("<s:url value='/jszy/doctorRecruit/validate'/>?sessionNumber="+sessionNum+"&doctorFlow="+doctorFlow , null , function(resp){
					if("${GlobalConstant.FLAG_Y}"==resp){
						saveRecruit();
					}else{
						jboxTip("您在当前年级，已添加过培训记录，无法继续添加！");return false;
					}
				} , null , false);
			}
		}
		/**
		 * 保存
		 */
		function saveRecruit(){
			if(false==$("#editForm").validationEngine("validate")){
				return false;
			}
			<%--var proveFileUrlFlag =  $("#proveFileUrlFlag").val();--%>
			<%--if(proveFileUrlFlag=="${GlobalConstant.FLAG_Y}"){--%>
				<%--var proveFileUrlValue = $('#proveFileUrlValue').val();--%>
				<%--if(!proveFileUrlValue){--%>
					<%--jboxTip("请上传减免培养年限证明!");--%>
					<%--return false;--%>
				<%--}--%>
			<%--}--%>
			var completeFileUrlFlag =  $("#completeFileUrlFlag").val();
			if("${GlobalConstant.FLAG_Y}" == completeFileUrlFlag){
				var completeFileUrlValue = $('#completeFileUrlValue').val();
				if(!completeFileUrlValue){
					jboxTip("请上传上一阶段结业证书!");
					return false;
				}
			}
			jboxStartLoading();
			var $catSpeId = $("#editForm input[name='catSpeId']:checked");
			var catSpeId = $catSpeId.val();
			var catSpeName = $catSpeId.next().text();//类别名称
			$("#catSpeName").val(catSpeName);
			var speName = $("select[name=speId] :selected").text();
			if(speName=="请选择"){
				speName = "";
			}
			$("#speName").val(speName);
			var secondSpeName = $("select[name=secondSpeId] :selected").text();
			if(secondSpeName=="请选择"){
				secondSpeName = "";
			}
			$("#secondSpeName").val(secondSpeName);
			var orgName = $("select[name=orgFlow] :selected").text();
			if(orgName=="请选择"){
				orgName = "";
			}
			$("#orgName").val(orgName);
			//地区
			var cityName = $("#cityId option:selected").text();
			if(cityName=="选择市"){
				cityName = "";
			}
			var cityId = $("#cityId").val();
			$("#placeId").val(cityId);
			$("#placeName").val(cityName);
			var url = "<s:url value='/jszy/doctor/saveResDoctorRecruit'/>";
			var data = $("#editForm").serialize();
			$("#saveBtn").attr("disabled",true);
			var year=$("#sessionNumber").val();
			var msg="确认您报名届数是"+year+"届？";
			jboxConfirm(msg,function(){
				jboxPost(url, data, function(resp){
					jboxEndLoading();
					if("${GlobalConstant.SAVE_FAIL}" != resp){
						window.parent.main(resp);
						jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
						setTimeout(function(){
							jboxClose();
						}, 1000);
					}
				}, null, false);
			},function(){
				jboxEndLoading();
				$("#saveBtn").removeAttr("disabled");
			});
		}


		/*
		 * 选择文件：
		 */
		function chooseFile(id){
			$("#upFileId").val(id);
			$("#file").click();
		}

		/**
		 * 上传文件
		 */
		function uploadFile(){
			if(false == $("#fileForm").validationEngine("validate")){
				return false;
			}
			jboxStartLoading();
			var checkResult = checkFile($("#file")[0]);
			if(!checkResult){
				jboxEndLoading();
				return false;
			}
			var url = "<s:url value='/jszy/doctor/uploadTrainYearFile'/>";
			jboxSubmit($("#fileForm"),url,function(resp){
				if("${GlobalConstant.UPLOAD_FAIL}" != resp){
					jboxEndLoading();
					var index = resp.indexOf("/");
					if(index != -1){
						returnUrl(resp);
					}else{//验证文件信息
						jboxInfo(resp);
					}
				}
			}, null, false);
		}

		/**
		 * 检查文件格式
		 */
		function checkFile(file){
			var filePath = file.value;
			if(filePath==""){
				return false;
			}
			var types = "${sysCfgMap['inx_image_support_suffix']}".split(",");
			var regStr = "/";
			for(var i = 0 ;i<types.length;i++){
				if(types[i]){
					if(i==(types.length-1)){
						regStr = regStr+"\\"+types[i]+'$';
					}else{
						regStr = regStr+"\\"+types[i]+'$|';
					}
				}
			}
			regStr = regStr+'/i';
			regStr = eval(regStr);
			if($.trim(filePath)!="" && !regStr.test(filePath)){
				file.value = "";
				jboxTip("请上传&nbsp;${sysCfgMap['inx_image_support_suffix']}格式的图片");
				return false;
			}else{
				return true;
			}
		}

		/**
		 * 返回文件URL
		 */
		function returnUrl(filePath){
			var fileUrlId = $("#upFileId").val();
			$('#'+fileUrlId).text("重新上传");
			$('#'+fileUrlId+'Value').val(filePath);
			var filePath = "${sysCfgMap['upload_base_url']}/" + filePath;
			$('#'+fileUrlId+'Del').show();
			$('#'+fileUrlId+'Span').show();
			$('#'+fileUrlId+'Span').find("a").attr('href',filePath);
		}

		/**
		 * 界别
		 */
		function setSessionNumber(obj){
			var limitDate='${sysCfgMap["res_reg_date"]}';
			if(limitDate!=""){
				var yearFlag="";
				var trainYear=$(obj).val().substring(0,4);
				var trainDate=$(obj).val().substring(5,$(obj).val().length);
				var limitArray=limitDate.split("-");
				var trainArray=trainDate.split("-");
				if(trainArray[0]<limitArray[0]){
					yearFlag=false;
				}else if(trainArray[0]==limitArray[0]){
					if(trainArray[1]<=limitArray[1]){
						yearFlag=false;
					}else{
						yearFlag=true;
					}
				}else{
					yearFlag=true;
				}
				if(yearFlag){
					$("#sessionNumber").val(parseInt(trainYear)+1);
					$("#sessionNumberTd").text(parseInt(trainYear)+1);
				}else{
					$("#sessionNumber").val(trainYear);
					$("#sessionNumberTd").text(trainYear);
				}
			}
		}

		function showUploadFileTr(){
			var trainYearId = $("#trainYear").val();
			var catSpeId = $("input[name=catSpeId]:checked").val()
			if("${jszyTrainCategoryEnumTCMAssiGeneral.id}" != catSpeId){
				if("" == trainYearId || "${jszyResTrainYearEnumThreeYear.id}" == trainYearId){
					$("#proveFileUrlTr").hide();
					$("#proveFileUrlFlag").val("${GlobalConstant.FLAG_N}");
				}else{
					$("#proveFileUrlTr").show();
					$("#proveFileUrlFlag").val("${GlobalConstant.FLAG_Y}");
				}
			}else {
				$("#proveFileUrlTr").hide();
				$("#proveFileUrlFlag").val("${GlobalConstant.FLAG_N}");
			}
		}

		/**
		 * 删除文件
		 */
		function delFile(fileUrlId) {
			jboxConfirm("确认删除？" , function(){
				$("#"+fileUrlId+"Del").hide();
				$("#"+fileUrlId+"Span").hide();
				$("#"+fileUrlId).text("上传");
				$("#"+fileUrlId+"Value").val("");
				$("#file").val(null);
			});
		}


	</script>
</head>

<body>
<div class="infoAudit">
	<div class="div_table">
		<h4>培训信息(<font color="red">培训专业慎重选择，一经审核不可更改</font>)</h4>
		<input type="hidden" id="proveFileUrlFlag"/>
		<input type="hidden" id="completeFileUrlFlag"/>
		<input type="hidden" id="upFileId"/>
		<c:set var="auditNotPassed" value="${jszyResDoctorAuditStatusEnumNotPassed.id eq doctorRecruit.auditStatusId}"/>
		<form id="editForm" style="position: relative;" method="post">
			<input type="hidden" name="recruitFlow" value="${doctorRecruit.recruitFlow}"/>
			<input type="hidden" name="doctorFlow"  id="doctorFlow"value="${sessionScope.currUser.userFlow}"/>

			<input type="hidden" id="sessionNumber" name="sessionNumber" value="${doctorRecruit.sessionNumber}"/>

			<input type="hidden" id="orgName" name="orgName" value="${doctorRecruit.orgName}"/>
			<input type="hidden" id="catSpeName" name="catSpeName" value="${doctorRecruit.catSpeName}"/>
			<input type="hidden" id="speName" name="speName" value="${doctorRecruit.speName}"/>
			<input type="hidden" id="secondSpeName" name="secondSpeName" value="${doctorRecruit.secondSpeName}"/>
			<%-- 所在地区:市、地区 --%>
			<input type="hidden" id="placeId" name="placeId" value="${doctorRecruit.placeId}"/>
			<input type="hidden" id="placeName" name="placeName" value="${doctorRecruit.placeName}"/>

			<!-- 非二阶段修改为二阶段 ：生成一个创建时间在此之前的一阶段  -->
			<input type="hidden" name="createTime" value="${doctorRecruit.createTime}"/>

			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="17%"/>
					<col width="34%"/>
					<col width="15%"/>
					<col width="34%"/>
				</colgroup>
				<tbody>
				<tr>
					<th><span class="red">*</span>&#12288;当前学位类别：</th>
					<td>
						<select name="currDegreeCategoryId" id="currDegreeCategoryId" class="select validate[required]" style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${jszyResDegreeCategoryEnumList}" var="degreeCategory">
								<option value="${degreeCategory.id}" ${doctorRecruit.currDegreeCategoryId eq degreeCategory.id?'selected':''}>${degreeCategory.name}</option>
							</c:forEach>
						</select>
					</td>
					<th style="${auditNotPassed?'color: red':'' }"><c:if test="${not empty doctorRecruit.auditStatusName }">审核状态：</c:if></th>
					<td class="${auditNotPassed?'red':'' }">${doctorRecruit.auditStatusName }</td>
				</tr>
				<tr>
					<th><span class="red">*</span>&#12288;规培起始日期：</th>
					<td><input type="text" id="trainDate" name="recruitDate" value="${doctorRecruit.recruitDate}" class="validate[required] input" onchange="" readonly="readonly" style="margin: 0;"/></td>
					<th>届&#12288;&#12288;别：</th>
					<td style="text-align: left;padding-left: 5px;" id="sessionNumberTd"></td>
				</tr>
				<tr>
					<th><span class="red">*</span>&#12288;所在地区：</th>
					<td>
						<div id="provCityAreaId">
							<select id="cityId" name="cityId" onchange="searchOrgList();" style="width: 160px;" class="city select validate[required]" data-value="" data-first-title="选择市"></select>
							<!-- <select id="areaId" name="areaId" class="area select validate[required]" data-value="" data-first-title="选择地区"></select> -->
						</div>
					</td>
					<th><span class="red">*</span>&#12288;培训基地：</th>
					<td>
						<select id="orgFlow" name="orgFlow" class="validate[required] select" style="width: 160px;"onchange="searchResOrgSpeList();">
							<option value="">请选择</option>
							<option orgCityId="320100" value="7a6eb182b89d4cdfb5bb789524c8dff8" style="display: none;">江苏省中医院</option>
							<option orgCityId="320100" value="6bb5b7409fc14038bb28a874e5fd501b" style="display: none;">江苏省中西医结合医院</option>
							<option orgCityId="320100" value="01a31918bf6042a2ba48faffb1325af9" style="display: none;">南京市中医院</option>
							<option orgCityId="320500" value="7e4eb4aa1b1b45469b74b5a8ecfcec02" style="display: none;">苏州市中医医院</option>
							<option orgCityId="320900" value="cf12bc1c77c344ce9bd325bae4a4b97c" style="display: none;">盐城市中医院</option>
							<option orgCityId="321000" value="f1bc1adf9ab444479e33bc6264bb0640" style="display: none;">扬州市中医院</option>
							<option orgCityId="320200" value="7c9aed80f0db46609347c04862f14de3" style="display: none;">无锡市中医医院</option>
							<option orgCityId="320300" value="bc15fae3138541d995d9e03aa65e5a52" style="display: none;">徐州市中医院</option>
							<option orgCityId="320700" value="5b6e5c4254fa4e8a84124f7d41e60128" style="display: none;">连云港市中医院</option>
							<option orgCityId="321200" value="04ab0f5d56be461b9d780e30958b6e87" style="display: none;">泰州市中医院</option>
							<option orgCityId="320400" value="488fcea1f1af461c8f395f8866466e1c" style="display: none;">常州市中医医院</option>

							<%-- <c:forEach items="${orgList}" var="org">
                                <option value="${org.orgFlow}" ${doctorRecruit.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
                            </c:forEach> --%>
						</select>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>&#12288;培训专业：</th>
					<td id="catSpeTd">
						<span id="catSpeSpan"></span>
						<span id="catSpeSpan2"></span>
					</td>
					<th class="trainSpe"><span class="red">*</span>&#12288;对应专业：</th>
					<td>
						<select name="speId" class="validate[required] select" style="width: 160px;">
							<option value="">请选择</option>
							<%--<c:forEach items="${speList}" var="spe">
                                <option value="${spe.speId}" ${doctorRecruit.speId eq spe.speId?'selected':''}>${spe.speName}</option>
                            </c:forEach> --%>
						</select>
					</td>
				</tr>
				<tr id="secondSpeId" style="display:none;">
					<th class=""><span id="secondSpeIdSpan" class="red">*</span>&#12288;二级专业：</th>
					<td>
						<select name="secondSpeId" class="validate[required] select" style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="spe">
								<option value="${spe.dictId}" ${doctorRecruit.secondSpeId eq spe.dictId?'selected':''}>${spe.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th></th>
					<td >
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>&#12288;培养年限：</th>
					<td id="trainYearTd">
						<input type="hidden" name="trainYear" value="${jszyResTrainYearEnumThreeYear.id}"/>
						<select id="trainYear" onchange="showUploadFileTr()" class="validate[required] select" style="width: 160px;" disabled  >
							<option value="">请选择</option>
							<c:forEach items="${jszyResTrainYearEnumList }" var="trainYear">
								<option value="${trainYear.id}" ${jszyResTrainYearEnumThreeYear.id eq trainYear.id?'selected':''}>${trainYear.name}</option>
							</c:forEach>
						</select>
					</td>
					<th><span class="red">*</span>&#12288;已培养年限：</th>
					<td>
						<select name="yetTrainYear" class="validate[required] select" style="width: 160px;" >
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumYetTrainYearList}">
								<option value="${dict.dictId}" ${doctorRecruit.yetTrainYear eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<%--<tr id="proveFileUrlTr" style="display: none;">--%>
					<%--<th>减免培养年限证明：</th>--%>
					<%--<td colspan="3">--%>
						<%--<input type="hidden" id="proveFileUrlValue" name="proveFileUrl" value="${doctorRecruit.proveFileUrl}"/>--%>
						<%--<span id="proveFileUrlSpan" style="display:${!empty doctorRecruit.proveFileUrl?'':'none'} ">--%>
							<%--[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]&nbsp;--%>
						<%--</span>--%>
						<%--<a id="proveFileUrl" href="javascript:chooseFile('proveFileUrl');" class="btn">${empty doctorRecruit.proveFileUrl?'':'重新'}上传</a>&nbsp;--%>
						<%--<a id="proveFileUrlDel" href="javascript:delFile('proveFileUrl');" class="btn" style="${empty doctorRecruit.proveFileUrl?'display:none':''}">删除</a>--%>
					<%--</td>--%>
				<%--</tr>--%>
				<tr>
					<th><span class="red">*</span>&#12288;医师状态：</th>
					<td>
						<select name="doctorStatusId" class="validate[required] select" style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
								<option value="${dict.dictId}" ${doctorRecruit.doctorStatusId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th><span class="red">*</span>&#12288;医师走向：</th>
					<td>
						<select name="doctorStrikeId" class="validate[required] select" style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumDoctorStrikeList}">
								<option value="${dict.dictId}" ${doctorRecruit.doctorStrikeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>

				<!-- 审核意见 -->
				<c:if test="${jszyResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId or auditNotPassed}">
					<c:if test="${not empty doctorRecruit.admitNotice}">
						<tr>
							<th style="color: red;">审核意见：</th>
							<td colspan="3" class="red">${doctorRecruit.admitNotice}</td>
						</tr>
					</c:if>
				</c:if>
				</tbody>
			</table>


			<div id="uploadFileDiv" style="display:${empty latestPrevPassed.completeFileUrl?'none':''}">
				<h4>结业信息 </h4>
				<table border="0" cellpadding="0" cellspacing="0" class="base_info">
					<colgroup>
						<col width="17%"/>
						<col width="34%"/>
						<col width="15%"/>
						<col width="34%"/>
					</colgroup>


					<c:if test="${prevPassedList.size() == 1}">
						<input type="hidden"  name="prevRecruitFlow" value="${prevPassedList[0].recruitFlow}"/>
					</c:if>
					<c:if test="${prevPassedList.size() > 1}">
						<tr>
							<th><span class="red">*</span>&#12288;结业培训类别：</th>
							<td colspan="3">
								<select name="prevRecruitFlow" class="validate[required] select" style="width: 160px;">
									<option value="">请选择</option>
									<c:forEach items="${prevPassedList}" var="recruit">
										<option value="${recruit.recruitFlow}" ${latestPrevPassed.recruitFlow eq recruit.recruitFlow?'selected':''}>${recruit.catSpeName}（${recruit.speName}）</option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</c:if>

					<tr>
						<th><span class="red">*</span>&#12288;结业证书附件：</th>
						<td>
							<input type="hidden" id="completeFileUrlValue" name="prevCompleteFileUrl" value="${latestPrevPassed.completeFileUrl}"/>
                        <span id="completeFileUrlSpan" style="display:${!empty latestPrevPassed.completeFileUrl?'':'none'}">
							[<a href="${sysCfgMap['upload_base_url']}/${latestPrevPassed.completeFileUrl}" target="_blank">查看图片</a>]&nbsp;
						</span>
							<a id="completeFileUrl" href="javascript:void(0);" onclick="chooseFile('completeFileUrl');" class="btn">${empty latestPrevPassed.completeFileUrl?'':'重新'}上传</a>&nbsp;
							<a id="completeFileUrlDel" href="javascript:void(0);" onclick="delFile('completeFileUrl');" class="btn" style="${empty latestPrevPassed.completeFileUrl?'display:none':''}">删除</a>
						</td>
						<th><span class="red">*</span>&#12288;结业证书编号：</th>
						<td>
							<input name="prevCompleteCertNo" value="${latestPrevPassed.completeCertNo}"  class="validate[required] input"/>
						</td>
					</tr>
				</table>
			</div>
			<div style="margin-top: 10px;margin-left: 10px;"><font color="red">注意：每年度注册时间为上一年11月1日至当前年10月31日，之后算入下一届学员。</font></div>
		</form>

		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<c:if test="${empty doctorRecruit.auditStatusId or jszyResDoctorAuditStatusEnumNotSubmit.id eq  doctorRecruit.auditStatusId or jszyResDoctorAuditStatusEnumNotPassed.id eq  doctorRecruit.auditStatusId}">
				<input type="button" id="saveBtn" class="btn_brown" onclick="validate();" value="保&#12288;存"/>&nbsp;
			</c:if>
			<c:if test="${param.openType eq 'open'}">
				<input type="button" class="btn_brown" onclick="jboxClose();" value="关&#12288;闭"/>&nbsp;
			</c:if>
			<!-- <input type="button" class="btn_blue" onclick="" value="打印"></input> -->
		</div>

		<!-- 文件上传 -->
		<form id="fileForm" method="post" enctype="multipart/form-data">
			<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
		</form>

		<!-- 培训类别类型 -->
		<c:forEach items="${jszyTrainCategoryTypeEnumList }" var="trainCategoryType">
			<span id="clone_${trainCategoryType.id }" style="display: none;">
			<c:forEach items="${jszyTrainCategoryEnumList }" var="trainCategory">
				<c:if test="${trainCategory.typeId eq trainCategoryType.id }">
					<label class="label_${trainCategory.id}"><input type="radio" style="vertical-align: middle;" name="catSpeId" class="validate[required]"
																	value="${trainCategory.id}" onchange="searchResOrgSpeList();changeSecondSpe(this.value);"/><span>${trainCategory.name}</span>&nbsp;</label>
				</c:if>
			</c:forEach>
			</span>
		</c:forEach>

		<c:forEach items="${jszyTrainCategoryEnumList }" var="trainCategory">
			<span id="cloneSpe_${trainCategory.id }" style="display:none ;">
		    <select class="select" name="trainSpe" style="width: 160px;">
				<option value="">请选择</option>
				<c:set var="dictName" value="dictTypeEnum${trainCategory.id}List" />
				<c:forEach items="${applicationScope[dictName]}" var="trainSpe">
					<option value="${trainSpe.dictId }">${trainSpe.dictName }</option>
				</c:forEach>
			</select>
			</span>
		</c:forEach>
	</div>
</div>
</body>
</html>