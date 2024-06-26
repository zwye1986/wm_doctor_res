<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<title>${sysCfgMap['sys_title_name']}</title>
	<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
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
			// getTrainCategory();

			//减免培养年限证明：
			showUploadFileTr();
		}

		/**
		 * 加载培训类别
		 */
		function getTrainCategory(){
			//$('#trainDate').click(function(e){e.preventDefault();});
			var cfgDate = "${applicationScope.sysCfgMap['jsres_trainCategory_cfgDate']}";
			var trainDate = $("#trainDate").val();
			var trainCategoryType = "${trainCategoryTypeEnumAfterCfgDate.id}";
			if(trainDate < cfgDate){
				trainCategoryType = "${trainCategoryTypeEnumBeforeCfgDate.id}";
			}
			$("#catSpeSpan").html($("#clone_"+trainCategoryType).html());
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
			if(catSpeId != "${trainCategoryEnumAssiGeneral.id}"){//一阶段/住院医师、二阶段
				$("#editForm .label_${trainCategoryEnumAssiGeneral.id}").hide();
				if("${isWMSecondRecPassed}" == "true"){//二阶段审核通过
					$("#editForm .label_${trainCategoryEnumWMFirst.id}").hide();
					$("#editForm .label_${trainCategoryEnumDoctorTrainingSpe.id}").hide();
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
			$("select[name=orgFlow] option[value != '']").remove();
			var cityId = $("#cityId").val();
			if(cityId==""){
				return false;
			}
			var rylx = $("#rylx").val();
			var psxx = $("#psxx").val();
			if (rylx=='Graduate' && psxx=='北京协和医学院'){
				if (cityId=='320100'){
					var orgFlow = "BYY0000002";
					var orgName = "南京鼓楼医院";
					$option =$("<option></option>");
					$option.attr("value", orgFlow);
					$option.text(orgName);
					$("select[name=orgFlow]").append($option);
				}
				return ;
			}

			jboxStartLoading();
			var catSpeId = '${catSpeId}';
			<%--var url = "<s:url value='/jsres/doctor/searchOrgList'/>?orgCityId=" + cityId+"&catSpeId="+catSpeId;--%>
            var url = "<s:url value='/jsres/doctor/searchOrgListNew'/>?orgCityId=" + cityId+"&catSpeId="+catSpeId;
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
		}


		/**
		 * 加载专业&协同基地
		 */
		function searchResOrgSpeList(val){
		    val = '${catSpeId}';
			var year=$("#sessionNumber").val();
			//清空原来专业！！！
			$("select[name=speId] option[value != '']").remove();

			//清空原来协同基地！！！
			$("select[name=jointOrgFlow] option[value != '']").remove();

			var orgFlow = $("#orgFlow").val();
			if(orgFlow==""){
				return false;
			}
			top.jboxStartLoading();
			<%--var url = "<s:url value='/jsres/doctor/searchResOrgSpeList?orgFlow='/>" + orgFlow +"&sessionNumber=2020";--%>
            var url = "<s:url value='/jsres/doctor/searchResOrgSpeListNew?orgFlow='/>" + orgFlow +"&sessionNumber=2020&trainCategoryTypeId="+val;
			top.jboxGet(url, null, function(resp){
				top.jboxEndLoading();
				// var dataObj = resp.main;
				// if(dataObj.length>0){
				// 	for(var i = 0; i<dataObj.length; i++){
				// 		var addFlag = true;
				// 		var speId = dataObj[i].speId;
                //
				// 		if(addFlag){
				// 			var speName = dataObj[i].speName;
				// 			$option =$("<option></option>");
				// 			$option.attr("value",speId);
				// 			$option.text(speName);
				// 			$("select[name=speId]").append($option);
				// 		}
				// 	}
				// }
				if(val == 'DoctorTrainingSpe'){
                    var dataObj = resp.main;
                    if(dataObj.length>0){
                    	for(var i = 0; i<dataObj.length; i++){
                    		var addFlag = true;
                    		var speId = dataObj[i].speId;

                    		if(addFlag){
                    			var speName = dataObj[i].speName;
                    			$option =$("<option></option>");
                    			$option.attr("value",speId);
                    			$option.text(speName);
                    			$("select[name=speId]").append($option);
                    		}
                    	}
                    }
				}else if(val == 'AssiGeneral'){
                    $("#AssiGeneral_select").find("option").each(function () {
                        $(this).clone().appendTo($("#speId"));
                    });
				}
				if(""!="${doctorRecruit.speId}"){
					$("select[name=speId] option[value='${doctorRecruit.speId}']").attr("selected",true);
				}
				var dataObj2 = resp.joint;
				if(dataObj2.length>0){
					for(var i = 0; i<dataObj2.length; i++){
						var addFlag = true;
						// var speId = dataObj2[i].jointFlow;
                        var speId = dataObj2[i].jointOrgFlow;

						if(addFlag){
							var speName = dataObj2[i].jointOrgName;
							$option =$("<option></option>");
							$option.attr("value",speId);
							$option.text(speName);
							$("select[name=jointOrgFlow]").append($option);
						}
					}
				}
			}, null , false);
		}

		/**
		 * 加载专业&协同基地
		 */
		function searchResOrgSpeSubList(){
			var year=$("#sessionNumber").val();
            var val = "${catSpeId}";
            // var radios = document.getElementsByName("catSpeId");
            // for(var i=0;i<radios.length;i++) {
            //     if(radios[i].checked) {
            //         val = radios[i].value;
            //     }
            // }
            //清空原来专业！！！
			$("select[name=speId] option[value != '']").remove();

			var orgFlowSub = $("#orgFlowSub").val();
			if(orgFlowSub==""){
				return false;
			}
			top.jboxStartLoading();
			<%--var url = "<s:url value='/jsres/doctor/searchResOrgSpeList?orgFlow='/>" + orgFlowSub +"&sessionNumber=2020";--%>
            var url = "<s:url value='/jsres/doctor/searchResOrgSpeListNew?orgFlow='/>" + orgFlowSub +"&sessionNumber=2020&trainCategoryTypeId="+val;
			top.jboxGet(url, null, function(resp){
				top.jboxEndLoading();
				var dataObj = resp.main;
				if(dataObj.length>0){
					for(var i = 0; i<dataObj.length; i++){
						var addFlag = true;
						var speId = dataObj[i].speId;

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
			}, null , false);
		}

		function validate(){
			var sessionNum=$("#sessionNumber").val();
			var doctorFlow=$("#doctorFlow").val();
			if(sessionNum !=""){
				jboxPost("<s:url value='/jsres/doctorRecruit/validate'/>?sessionNumber="+sessionNum+"&doctorFlow="+doctorFlow , null , function(resp){
					if("${GlobalConstant.FLAG_Y}"==resp){
						saveRecruit();
					}else{
						jboxTip("您在当前届别，已添加过培训记录，无法继续添加！");return false;
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
			var proveFileUrlFlag =  $("#proveFileUrlFlag").val();
			if(proveFileUrlFlag=="${GlobalConstant.FLAG_Y}"){
				var proveFileUrlValue = $('#proveFileUrlValue').val();
				if(!proveFileUrlValue){
					jboxTip("请上传减免培养年限证明!");
					return false;
				}
			}
			var completeFileUrlFlag =  $("#completeFileUrlFlag").val();
			if('${GlobalConstant.FLAG_Y}' == completeFileUrlFlag){
				var completeFileUrlValue = $('#completeFileUrlValue').val();
				if(!completeFileUrlValue){
					jboxTip("请上传住培结业证书!");
					return false;
				}
			}
			jboxStartLoading();
			// var $catSpeId = $("#editForm input[name='catSpeId']:checked");
			// var catSpeId = $catSpeId.val();
			// var catSpeName = $catSpeId.next().text();//类别名称
			// $("#catSpeName").val(catSpeName);

			var speName = $("select[name=speId] :selected").text();
			if(speName=="请选择"){
				speName = "";
			}
			$("#speName").val(speName);
			var orgName = $("select[name=orgFlow] :selected").text();
			if(orgName=="请选择"){
				orgName = "";
			}
			$("#orgName").val(orgName);
			//协同基地
			var jointOrgName = $("select[name=jointOrgFlow] :selected").text();
            if(jointOrgName=="请选择"){
                jointOrgName = "";
            }
            $("#jointOrgName").val(jointOrgName);
			//地区
			var cityName = $("#cityId option:selected").text();
			if(cityName=="选择市"){
				cityName = "";
			}
			var cityId = $("#cityId").val();
			$("#placeId").val(cityId);
			$("#placeName").val(cityName);
			var url = "<s:url value='/jsres/doctor/saveResDoctorRecruit'/>";
			var data = $("#editForm").serialize();
			$("#saveBtn").attr("disabled",true);
			var year=$("#sessionNumber").val();
			var msg="确认您报名届数是"+year+"届？";
			jboxConfirm(msg,function(){
				jboxPost(url, data, function(resp){
					jboxEndLoading();
					if("${GlobalConstant.SAVE_FAIL}" != resp){
						// window.parent.main(resp);
                        window.parent.parent.main(resp);
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
			return $("#file").click();
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
			var url = "<s:url value='/jsres/doctor/uploadTrainYearFile'/>";
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
			if("" == trainYearId || undefined == trainYearId || "${jsResTrainYearEnumThreeYear.id}" == trainYearId){
				$("#proveFileUrlTr").hide();
				$("#proveFileUrlFlag").val("${GlobalConstant.FLAG_N}");
			}else{
				var catSpeId = $("#editForm input[name='catSpeId']:checked").val();
				if("${trainCategoryEnumDoctorTrainingSpe.id}" == catSpeId){
					$("#proveFileUrlTr").show();
					$("#proveFileUrlFlag").val("${GlobalConstant.FLAG_Y}");
				}else{
					$("#proveFileUrlTr").hide();
					$("#proveFileUrlFlag").val("${GlobalConstant.FLAG_N}");
				}
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
		<c:set var="auditNotPassed" value="${jsResDoctorAuditStatusEnumNotPassed.id eq doctorRecruit.auditStatusId}"/>
		<form id="editForm" style="position: relative;" method="post">
			<input type="hidden" name="recruitFlow" value="${doctorRecruit.recruitFlow}"/>
			<input type="hidden" name="doctorFlow"  id="doctorFlow" value="${sessionScope.currUser.userFlow}"/>

			<input type="hidden" name="catSpeId"  id="catSpeId" value="${catSpeId}"/>
			<input type="hidden" name="catSpeName"  id="catSpeName" value="${catSpeName}"/>
			<input type="hidden" id="sessionNumber" name="sessionNumber" value="${doctorRecruit.sessionNumber}"/>

			<input type="hidden" id="orgName" name="orgName" value="${doctorRecruit.orgName}"/>
			<input type="hidden" id="jointOrgName" name="jointOrgName" value="${doctorRecruit.jointOrgName}"/>
			<%--<input type="hidden" id="catSpeName" name="catSpeName" value="${doctorRecruit.catSpeName}"/>--%>
			<input type="hidden" id="speName" name="speName" value="${doctorRecruit.speName}"/>
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
				<c:if test="${catSpeId eq 'DoctorTrainingSpe'}">
				<tr>
					<th><span class="red">*</span>当前学位类别：</th>
					<td>
						<select name="currDegreeCategoryId" id="currDegreeCategoryId" class="select validate[required]" style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${jsResDegreeCategoryEnumList}" var="degreeCategory">
								<option value="${degreeCategory.id}" ${doctorRecruit.currDegreeCategoryId eq degreeCategory.id?'selected':''}>${degreeCategory.name}</option>
							</c:forEach>
						</select>
					</td>
					<th style="${auditNotPassed?'color: red':'' }"><c:if test="${not empty doctorRecruit.auditStatusName }">审核状态：</c:if></th>
					<td class="${auditNotPassed?'red':'' }">${doctorRecruit.auditStatusName }</td>
				</tr>
				</c:if>
				<tr>
					<th><span class="red">*</span>规培起始日期：</th>
					<td><input type="text" id="trainDate" name="recruitDate" value="${doctorRecruit.recruitDate}" class="validate[required] input" onchange="" readonly="readonly" style="margin: 0;"/></td>
					<th>届别：</th>
					<td style="text-align: left;padding-left: 5px;" id="sessionNumberTd"></td>
				</tr>
				<tr>
					<input type="hidden" id="rylx" value="${resDoctor.doctorTypeId}">
					<input type="hidden" id="psxx" value="${resDoctor.workOrgName}">
					<th><span class="red">*</span>所在地区：</th>
					<td>
						<div id="provCityAreaId">
							<select id="cityId" name="cityId" onchange="searchOrgList();" class="city select validate[required]" data-value="" data-first-title="请选择"></select>
							<!-- <select id="areaId" name="areaId" class="area select validate[required]" data-value="" data-first-title="选择地区"></select> -->
						</div>
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th><span class="red">*</span>培训基地：</th>
					<td>
						<select id="orgFlow" name="orgFlow" class="validate[required] select" style="width: 160px;" onchange="searchResOrgSpeList();">
							<option value="">请选择</option>
							<%-- <c:forEach items="${orgList}" var="org">
                                <option value="${org.orgFlow}" ${doctorRecruit.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
                            </c:forEach> --%>
						</select>
					</td>
				<%--	<c:if test="${catSpeId eq 'DoctorTrainingSpe'}">
						<th>协同基地：</th>
						<td>
							<select id="orgFlowSub" name="jointOrgFlow" class="select" style="width: 160px;"onchange="searchResOrgSpeSubList();">
								<option value="">请选择</option>
							</select>&#12288;
						</td>
					</c:if>--%>
					<c:if test="${catSpeId ne 'DoctorTrainingSpe'}">
						<th></th>
						<td></td>
					</c:if>
				</tr>
				<tr>
					<th><span id="red" class="red">*</span>培训类别：</th>
					<%--<td id="catSpeTd">--%>
						<%--<span id="catSpeSpan"></span>--%>
						<%--<span id="catSpeSpan2"></span>--%>
					<%--</td>--%>
					<td id="catSpeTd">
						<span style="display:${catSpeId eq 'DoctorTrainingSpe'?'':'none'}">
							<input type="radio" name="catSpeId" value="DoctorTrainingSpe" disabled
							<c:if test="${catSpeId eq 'DoctorTrainingSpe'}">checked</c:if>/>住院医师
						</span>
						<span style="display:${catSpeId eq 'AssiGeneral'?'':'none'}">
							<input type="radio" name="catSpeId" value="AssiGeneral" disabled
							<c:if test="${catSpeId eq 'AssiGeneral'}">checked</c:if>/>助理全科
						</span>
					</td>
					<th class="trainSpe"><span class="red">*</span>培训专业：</th>
					<td>
						<select id="speId" name="speId" class="validate[required] select" style="width: 160px;">

							<c:if test="${catSpeId eq 'AssiGeneral'}">

								<option value="50">助理全科</option>
							</c:if>

							<%-- <c:forEach items="${speList}" var="spe">
                                <option value="${spe.speId}" ${doctorRecruit.speId eq spe.speId?'selected':''}>${spe.speName}</option>
                            </c:forEach> --%>
						</select>
					</td>
				</tr>
				<input hidden name="trainYear" value="${catSpeId eq 'DoctorTrainingSpe' ? 'ThreeYear' : 'TwoYear'}">
				<input hidden name="yetTrainYear" value="0">
				<%--<tr>
					<th><span class="red">*</span>培养年限：</th>
					<td id="trainYearTd">
						<select name="trainYear" id="trainYear" onchange="showUploadFileTr()" class="validate[required] select" style="width: 160px;"  >
							<option value="">请选择</option>
							<c:forEach items="${jsResTrainYearEnumList }" var="trainYear">
								<option value="${trainYear.id}" ${doctorRecruit.trainYear eq trainYear.id?'selected':''}>${trainYear.name}</option>
							</c:forEach>
						</select>
					</td>
					<th><span class="red">*</span>已培养年限：</th>
					<td>
						<select name="yetTrainYear" class="validate[required] select" style="width: 160px;" >
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumYetTrainYearList}">
								<option value="${dict.dictId}" ${doctorRecruit.yetTrainYear eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>&#12288;
					</td>
				</tr>
				<tr id="proveFileUrlTr" style="display: none;">
					<th><span class="red">*</span>减免培养年限证明：</th>
					<td colspan="3">
						<input type="hidden" id="proveFileUrlValue" name="proveFileUrl" value="${doctorRecruit.proveFileUrl}"/>
						<span id="proveFileUrlSpan" style="display:${!empty doctorRecruit.proveFileUrl?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.proveFileUrl}" target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="proveFileUrl" href="javascript:chooseFile('proveFileUrl');" class="btn">${empty doctorRecruit.proveFileUrl?'':'重新'}上传</a>&nbsp;
						<a id="proveFileUrlDel" href="javascript:delFile('proveFileUrl');" class="btn" style="${empty doctorRecruit.proveFileUrl?'display:none':''}">删除</a>
						&#12288;
					</td>
				</tr>--%>
				<script>
					function changeDocStatue(resp){
						var isChecked = $(".label_WMSecond").find("input[type=radio]").attr("checked");
						if(isChecked == "checked"){
							$("#uploadFileDiv").show();
							$("#completeFileUrlFlag").val("${GlobalConstant.FLAG_Y}");
							var docStatue = $(resp).find("option:selected").val();
							if(docStatue == "21"){
								$("#specialFile").show();
							}else {
								$("#specialFile").hide();
							}
						}else {
							var docStatue = $(resp).find("option:selected").val();
							if(docStatue == "21"){
								$("#uploadFileDiv").show();
								$(".red.noRequired").hide();
								$("input[name=prevCompleteCertNo]").attr("class","input");
							}else {
								$("#uploadFileDiv").hide();
							}
						}
					}
				</script>
				<tr>
					<th><span class="red">*</span>医师状态：</th>
					<td>
						<select name="doctorStatusId" onchange="changeDocStatue(this);" class="validate[required] select" style="width: 160px;">
							<option value="20">在培</option>
						</select>&#12288;
					</td>
					<th><span class="red">*</span>医师走向：</th>
					<td>
						<select name="doctorStrikeId" class="validate[required] select" style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumDoctorStrikeList}">
								<option value="${dict.dictId}" ${doctorRecruit.doctorStrikeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>&#12288;
					</td>
				</tr>

				<!-- 审核意见 -->
				<c:if test="${jsResDoctorAuditStatusEnumPassed.id eq doctorRecruit.auditStatusId or auditNotPassed}">
					<c:if test="${not empty doctorRecruit.admitNotice}">
						<tr>
							<th style="color: red;">审核意见：</th>
							<td colspan="3" class="red">${doctorRecruit.admitNotice}</td>
						</tr>
					</c:if>
				</c:if>
				</tbody>
			</table>

			<%--
                 * 注：1、二阶段的住培结业证书附件（二阶段报名需一阶段结业，二阶段的住培结业证书附件即一阶段的结业证书附件）
                 * 			存放在res_doctor_recruit中complete_file_url中
                 * 	   2、二阶段的专培结业证书附件（该条记录的结业附件）
                 * 			存放在res_doctor_recruit中special_file_url（该条记录的结业附件）中
                 * 	   3、除二阶段以外的住培结业证书附件（该条记录的结业附件）
                 * 			存放在res_doctor_recruit中special_file_url（该条记录的结业附件）中
                 *     4、由于页面字段展示问题现将非二阶段的该条记录的结业附件
                            name = prevCompleteFileUrl 存入special_file_url（该条记录的结业附件）中
            --%>
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
							<th><span class="red">*</span>结业培训类别：</th>
							<td colspan="3">
								<select name="prevRecruitFlow" class="validate[required] select" style="width: 160px;">
									<option value="">请选择</option>
									<c:forEach items="${prevPassedList}" var="recruit">
										<option value="${recruit.recruitFlow}" ${latestPrevPassed.recruitFlow eq recruit.recruitFlow?'selected':''}>${recruit.catSpeName}（${recruit.speName}）</option>
									</c:forEach>
								</select>
								&#12288;
							</td>
						</tr>
					</c:if>

					<tr>
						<th><span class="red noRequired">*</span>住培结业证书附件：</th>
						<td>
							<input type="hidden" id="completeFileUrlValue" name="prevCompleteFileUrl" value="${empty doctorRecruit.completeFileUrl ? doctorRecruit.specialFileUrl : doctorRecruit.completeFileUrl}"/>
							<span id="completeFileUrlSpan" style="display:${!empty doctorRecruit.completeFileUrl?'': (!empty doctorRecruit.specialFileUrl?"":"none")}">
								[<a href="${sysCfgMap['upload_base_url']}/${empty doctorRecruit.completeFileUrl ? doctorRecruit.specialFileUrl : doctorRecruit.completeFileUrl}" target="_blank">查看图片</a>]&nbsp;
							</span>
							<a id="completeFileUrl" href="javascript:chooseFile('completeFileUrl');" class="btn">${empty doctorRecruit.completeFileUrl?(empty doctorRecruit.specialFileUrl?"":"重新"):'重新'}上传</a>&nbsp;
							<a id="completeFileUrlDel" href="javascript:delFile('completeFileUrl');" class="btn" style="${empty doctorRecruit.completeFileUrl?(empty doctorRecruit.specialFileUrl?'display:none':''):''}">删除</a>
							&nbsp;
						</td>
						<th><span class="red noRequired">*</span>住培结业证书编号：</th>
						<td>
							<input name="prevCompleteCertNo" value="${empty doctorRecruit.completeCertNo ? doctorRecruit.specialCertNo : doctorRecruit.completeCertNo}"  class="validate[required] input"/>&#12288;
						</td>
					</tr>
					<tr id="specialFile" style="display: none;">
						<th>专培结业证书附件：</th>
						<td>
							<input type="hidden" id="specialFileUrlValue" name="specialFileUrl" value="${doctorRecruit.specialFileUrl}"/>
							<span id="specialFileUrlSpan" style="display:${!empty doctorRecruit.specialFileUrl?'':'none'}">
								[<a href="${sysCfgMap['upload_base_url']}/${doctorRecruit.specialFileUrl}" target="_blank">查看图片</a>]&nbsp;
							</span>
							<a id="specialFileUrl" href="javascript:chooseFile('specialFileUrl');" class="btn">${empty doctorRecruit.specialFileUrl?'':'重新'}上传</a>&nbsp;
							<a id="specialFileUrlDel" href="javascript:delFile('specialFileUrl');" class="btn" style="${empty doctorRecruit.specialFileUrl?'display:none':''}">删除</a>
							&nbsp;
						</td>
						<th>专培结业证书编号：</th>
						<td>
							<input name="specialCertNo" value="${doctorRecruit.specialCertNo}"  class="input"/>&#12288;
						</td>
					</tr>
				</table>
			</div>
			<div style="margin-top: 10px;margin-left: 10px;"><font color="red">注意：每年度注册时间为上一年11月1日至当前年10月31日，之后算入下一届学员。</font></div>
		</form>

		<div align="center" style="margin-top: 20px; margin-bottom:20px;">
			<c:if test="${empty doctorRecruit.auditStatusId or jsResDoctorAuditStatusEnumNotSubmit.id eq  doctorRecruit.auditStatusId or jsResDoctorAuditStatusEnumNotPassed.id eq  doctorRecruit.auditStatusId}">
				<input type="button" id="saveBtn" class="btn_green" onclick="validate();" value="保存"/>&nbsp;
			</c:if>

			<input type="button" class="btn_green" onclick="top.jboxClose();" value="关闭"/>&nbsp;

			<!-- <input type="button" class="btn_green" onclick="" value="打印"></input> -->
		</div>

		<!-- 文件上传 -->
		<form id="fileForm" method="post" enctype="multipart/form-data">
			<input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
		</form>

		<!-- 培训类别类型 -->
		<c:forEach items="${trainCategoryTypeEnumList }" var="trainCategoryType">
			<span id="clone_${trainCategoryType.id }" style="display: none;">
			<c:forEach items="${trainCategoryEnumList }" var="trainCategory">
				<c:if test="${trainCategory.typeId eq trainCategoryType.id }">
					<c:if test="${trainCategory.id ne 'WMFirst' and trainCategory.id ne 'WMSecond'}">
						<label class="label_${trainCategory.id}"><input type="radio" style="vertical-align: middle;" name="catSpeId" class="validate[required]" value="${trainCategory.id}" onchange="searchResOrgSpeList('${trainCategory.id}');"/><span>${trainCategory.name}</span>&nbsp;</label>
					</c:if>
				</c:if>
			</c:forEach>
			</span>
		</c:forEach>

		<c:forEach items="${trainCategoryEnumList }" var="trainCategory">
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
<div style="display: none;">
	<select id="WMFirst_select">
		<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
			<option
					<c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
					value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="WMSecond_select">
		<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
			<option
					<c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
					value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="AssiGeneral_select">
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			<option
					<c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
					value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="DoctorTrainingSpe_select">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<option
					<c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if>
					value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>

</div>
</body>
</html>