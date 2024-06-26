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


		function chooseTrainingType(typeId,typeName) {
			$("#trainingTypeId").val(typeId)
			$("#trainingTypeName").val(typeName)
		}


		function sub() {
			var trainingTypeId=$("#trainingTypeId").val();
			var trainingTypeName=$("#trainingTypeName").val();

			jboxPost("<s:url value='/jsres/doctor/subChooseType'/>",{trainingTypeId:trainingTypeId,trainingTypeName:trainingTypeName},function(resp){
				if('${GlobalConstant.SAVE_SUCCESSED}'==resp){
					jboxTip("保存成功！");


					if (trainingTypeId=='DoctorTrainingSpe'){
						window.parent.idhide('isDZ');
						window.parent.checkZk('N','junior');
					}else if (trainingTypeId=='AssiGeneral'){
						window.parent.$("#sfwyjs")[0].onclick=null;

						window.parent.idhide('doctorType_Graduate');
						window.parent.idhide('doctorTypeName_Graduate');
						window.parent.clhide('zl');
						window.parent.checkZk('N','undergrad');
						window.parent.checkZk('N','master');
						window.parent.checkZk('N','doctor');
					}
					jboxClose();
				}else{
					jboxTip("保存失败！");
				}
			},null,false);
		}


	</script>

</head>

<body>
<div>
	<div style="text-align: center;margin-top: 30px">
		<label>
			<input name="trainingTypeId" type="radio"
				   onclick="chooseTrainingType('DoctorTrainingSpe','住院医师');"
				   value="DoctorTrainingSpe" class="validate[required]"/>&nbsp;住院医师&#12288;</label>

		<span style="width: 80px"></span>

		<label><input name="trainingTypeId" type="radio"
					  onclick="chooseTrainingType('AssiGeneral','助理全科');"
					  value="AssiGeneral" class="validate[required]"/>&nbsp;助理全科&nbsp;&nbsp;</label>

		<div style="margin-top: 70px">
			<input class="btn_blue" type="button" value="确&#12288;定" onclick="sub();" />
		</div>

		<input type="hidden" id="trainingTypeId" value="${trainingTypeId}">
		<input type="hidden" id="trainingTypeName" value="${trainingTypeName}">
	</div>


</div>
</body>
</html>