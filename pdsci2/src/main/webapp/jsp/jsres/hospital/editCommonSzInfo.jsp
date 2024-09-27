<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="basic_bootstrap" value="true" />
	<jsp:param name="bootstrapSelect" value="true"/>
</jsp:include>
<style type="text/css">

	#boxHome .item:HOVER{background-color: #eee;}

	.text{
		margin-left: 0;
		width: auto;
		height: auto;
		line-height: inherit;
		color: black;
	}

	.selected a{
		padding: 0;
		background: white !important;
	}

	.base_info td a{
		color: black !important;
	}

	.btn-group.bootstrap-select {
		margin: 0 12px 0 4px !important;
		width: 147px !important;
		height: 30px;
	}

	.btn-default {
		background-color: white;
		border-color: black;
	}

	.pull-left {
		float: left !important;
		margin-left: -21px;
	}
</style>
<script type="text/javascript">
    $(document).ready(function () {
		$("#userDepts").selectpicker({
			deselectAllText: "全不选",
			selectAllText: "全选"
		});

        $('#trainingYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });

        $('#graduationTime,#workingTime,#certGrantedDate').datepicker({
			language:  'zh-CN',
			startView: 2,
			maxView: 4,
			minView: 2,
			format: 'yyyy-mm-dd',
			autoclose: true
        });
    });

	function saveCommonSzInfo() {
		if(false==$("#excelForm").validationEngine("validate")){
			return false ;
		}

		var url = "<s:url value='/jsres/manage/saveCommonSzInfo'/>";
		var data = $('#excelForm').serialize();
		jboxPost(url, data, function(data) {
			if('${GlobalConstant.SAVE_SUCCESSED}'==data){
				window.parent.search();
				setTimeout(function(){
					jboxClose();
				}, 1000);
			}else if('${GlobalConstant.USER_PHONE_REPETE}'==data) {
				jboxConfirm("改手机号已绑定用户，是否绑定新的账号？", function () {
					var url = "<s:url value='/sys/user/save4jsresteacher'/>";
					var data = $('#excelForm').serialize();
					data = data + "&coverPhone=Y"
					jboxPost(url, data, function(data) {
						if('${GlobalConstant.SAVE_SUCCESSED}'==data){
							window.parent.search();
							setTimeout(function(){
								jboxClose();
							}, 1000);
						}else {
						}
					},null,true);
				});
			}else {
			}
		},null,true);
	}

    function returnUrl(){
        window.parent.search();
        doClose();
    }

    $(document).ready(function(){
        if ("${GlobalConstant.FLAG_Y}"=="${result}") {
            window.parent.search();
            jboxClose();
        }else if ("${GlobalConstant.FLAG_N}"=="${result}"){
            jboxTip("保存失败");
        }else if("${GlobalConstant.FLAG_N}" == "${certificateNoFlag}"){
        	jboxTip("证书编号不能重复，请核对！");
		}
    });

	function getOrgName() {
		$("#orgName").val($("#orgFlow option:selected").text());
	}

    function searchDeptList(orgFlow){
        jboxPost("<s:url value='/jsres/teacher/searchDeptList'/>?orgFlow="+orgFlow, null, function (resp) {
            $("#deptFlow").empty();
            $("#deptFlow").append("<option value=''>请选择</option>");
            if (null != resp && resp.length > 0) {
                for(var i= 0;i<resp.length;i++){
					$("#deptFlow").append("<option value='" + resp[i].deptFlow + "'>" + resp[i].deptName + "</option>");
                }
            }
        },null,false);
    }

    function checkFile(file){
        var filePath = file.value;
        var types = ".jpg,.png,.jpeg".split(",");
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
            jboxTip("请上传&nbsp;.jpg,.png,.jpeg格式的图片");
        }
    }

	function delImg(recordFlow){
		jboxConfirm("确认删除？", function(){
			var url = "<s:url value='/jsres/statistic/deleteCerfiticateImg'/>?recordFlow=" + recordFlow;
			jboxPost(url,null,function(resp){
				if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
					$("input[name='certificateUrl']").val("");
					reuploadImg();
					jboxTip("删除图片成功！");
				}
			},null,true);
		});
	}
	function delImgOne(recordFlow){
		jboxConfirm("确认删除成果附件？", function(){
			var url = "<s:url value='/jsres/statistic/deleteAchievementImg'/>?recordFlow=" + recordFlow;
			jboxPost(url,null,function(resp){
				if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
					$("input[name='achievementUrl']").val("");
					reuploadImgOne();
					jboxTip("删除成果附件成功！");
				}
			},null,true);
		});
	}

	function reuploadImg(){
		$("#viewImgLink").hide();
		$("#delImgLink").hide();
		$("#reuploadImgLink").hide();
		$("#newFile").show();
	}
	function reuploadImgOne(){
		$("#viewImgLinkOne").hide();
		$("#delImgLinkOne").hide();
		$("#reuploadImgLinkOne").hide();
		$("#newFileOne").show();
	}

	function viewTrainAttachment(recordFlow,recType,title){
		var url = "<s:url value='/jsres/manage/attachment'/>?recFlow="+recordFlow + "&recType=" + recType;
		jboxOpen(url, title,700,550);
	}

	function jboxOpen(url,title,width,height,showClose){
		top.dialog({
			id:'openDialog2',
			fixed: true,
			padding: 5,
			title: title,
			url: url,
			width:width,
			height:height,
			cancelDisplay: showClose,
			cancelValue: '',
			backdropOpacity:0.1
		}).showModal();
		if(showClose==false){
			$(".ui-dialog-close",window.top.window.document).remove();
		}
	}

</script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data"
	style="height: 500px;overflow-y: auto">
	<table class="grid" style="width: 100%;margin-top: 15px">
		<input  type="text" name="recordFlow" id="recordFlow"  value="${teacher.recordFlow}" style="display: none;"/>
		<input  type="text" name="orgFlow" id="orgFlow"  value="${teacher.orgFlow}" style="display: none;"/>
		<input  type="text" name="roleFlag" id="roleFlag" value="${roleFlag}" style="text-align: left;display: none;width: 150px;"/>
		<tr>
			<th width="150px"><font color="red" >*</font>姓名</th>
			<td  style="text-align: left;">
				<input  type="text" name="doctorName" class="select validate[required]" value="${teacher.doctorName}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">手机号码</th>
			<td  style="text-align: left;">
				<input  type="text" id="userPhone" name="userPhone"  class="validate[custom[mobile]] input" value="${teacher.userPhone}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">身份证号</th>
			<td  style="text-align: left;">
				<input  type="text" id="idNo" name="idNo" class="validate[custom[chinaIdLoose]] input"  value="${teacher.idNo}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>性别</th>
			<td  style="text-align: left;">
				<select name="sexName" id="sexName" class="select validate[required]" style="width: 150px;" >
					<option value=""></option>
					<option value="男"<c:if test="${teacher.sexName=='男' }">selected="selected"</c:if>>男</option>
					<option value="女"<c:if test="${teacher.sexName=='女' }">selected="selected"</c:if>>女</option>
				</select>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>年龄</th>
			<td  style="text-align: left;">
				<input  type="text" name="doctorAge" class="select validate[required,custom[integer],min[0]]" value="${teacher.doctorAge}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>专业</th>
			<td  style="text-align: left;">
				<select class="select validate[required]" id="speId" name="speId" style="width: 150px">
					<option value="">请选择</option>
					<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
						<option <c:if test="${teacher.speId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>科室</th>
			<td  style="text-align: left;">
				<select multiple class="selectpicker" name="userDepts" id="userDepts" title="请选择科室" data-actions-box="true">
					<c:forEach items="${sysDeptList}" var="sysDept">
						<option value="${sysDept.deptFlow}"<c:if test="${!empty sysUserDeptMap[sysDept.deptFlow]}">selected="selected"</c:if>>${sysDept.deptName}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>培训年份</th>
			<td  style="text-align: left;">
				<input type="text" value="${teacher.trainingYear}" class="select validate[required]" name="trainingYear" id="trainingYear" style="width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>学历</th>
			<td  style="text-align: left;">
				<select class="select validate[required]" id="doctorEdu" name="doctorEdu" style="width: 150px">
					<option value="">请选择</option>
					<option value="专科"<c:if test="${teacher.doctorEdu eq '专科'}">selected="selected"</c:if>>专科</option>
					<option value="本科"<c:if test="${teacher.doctorEdu eq '本科'}">selected="selected"</c:if>>本科</option>
					<option value="硕士研究生"<c:if test="${teacher.doctorEdu eq '硕士研究生'}">selected="selected"</c:if>>硕士研究生</option>
					<option value="博士研究生"<c:if test="${teacher.doctorEdu eq '博士研究生'}">selected="selected"</c:if>>博士研究生</option>
					<option value="其他"<c:if test="${teacher.doctorEdu eq '其他'}">selected="selected"</c:if>>其他</option>
					<option value="无"<c:if test="${teacher.doctorEdu eq '无'}">selected="selected"</c:if>>无</option>
				</select>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>毕业学校</th>
			<td  style="text-align: left;">
				<input  type="text" name="graduationSchool" class="select validate[required]" value="${teacher.graduationSchool}" style="text-align: left;width: 150px"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>毕业时间</th>
			<td  style="text-align: left;">
				<input type="text" value="${teacher.graduationTime}" class="select validate[required]" name="graduationTime" id="graduationTime" style="width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>工作时间</th>
			<td  style="text-align: left;">
				<input type="text" value="${teacher.workingTime}" class="select validate[required]" name="workingTime" id="workingTime" style="width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>技术职称</th>
			<td  style="text-align: left;">
				<input  type="text" name="technicalTitle" class="select validate[required]" value="${teacher.technicalTitle}" style="text-align: left;width: 150px"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>师资培训会议名称</th>
			<td  style="text-align: left;">
				<input  type="text" name="meetingName" class="select validate[required]" value="${teacher.meetingName}"  style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>师资证书等级</th>
			<td  style="text-align: left;">
				<select class="select" name="teachingCertLevel" id="teachingCertLevel" style="width: 150px;">
					<option value="">请选择</option>
					<c:forEach items="${teachingCertLevelEnumList}" var="certLevel">
						<option value="${certLevel.code}" <c:if test="${teacher.teachingCertLevel eq certLevel.code}">selected</c:if> >${certLevel.name}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>证书取得时间</th>
			<td  style="text-align: left;">
				<input type="text" value="${teacher.certGrantedDate}" class="select validate[required]" name="certGrantedDate" id="certGrantedDate" style="width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>证书编号</th>
			<td  style="text-align: left;">
				<input  type="text" id="certificateNo" name="certificateNo" class="select validate[required]" value="${teacher.certificateNo}"  style="text-align: left;width: 150px;"/>
				<a style="cursor: pointer;" onclick="javascript:viewTrainAttachment('${teacher.recordFlow}','szzsAttach','证书附件')">上传附件</a>
			</td>
		</tr>
		<tr>
			<th width="150px">任现职务年限</th>
			<td  style="text-align: left;">
				<input  type="text" name="officeYear" class="select validate[custom[integer],min[0]]" value="${teacher.officeYear}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">从事本专业临床工作年限</th>
			<td  style="text-align: left;">
				<input  type="text" name="workYear" class="select validate[custom[integer],min[0]]" value="${teacher.workYear}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">带住院医师年限</th>
			<td  style="text-align: left;">
				<input  type="text" name="hosYear" class="select validate[custom[integer],min[0]]" value="${teacher.hosYear}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">带住院医师近三年累计人数</th>
			<td  style="text-align: left;">
				<input  type="text" name="threeHosYear" class="select validate[custom[integer],min[0]]" value="${teacher.threeHosYear}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">参加省级及以上住院医师规范化培训师资培训</th>
			<td  style="text-align: left;">
				<label><input name="isTrain" type="radio" value="${GlobalConstant.FLAG_Y}"
							  <c:if test="${teacher.isTrain ==GlobalConstant.FLAG_Y }">checked="checked"</c:if> />&#12288;有&#12288;</label>
				<label><input name="isTrain" type="radio" value="${GlobalConstant.FLAG_N}"
							  <c:if test="${teacher.isTrain ==GlobalConstant.FLAG_N }">checked="checked"</c:if> />&#12288;无</label>
			</td>
		</tr>

		<tr>
			<th width="150px">成果附件</th>
			<td >
				<a style="cursor: pointer;" onclick="javascript:viewTrainAttachment('${teacher.recordFlow}','szcgAttach','成果附件')">上传附件</a>
			</td>
		</tr>
	</table>

</form>
<div style="text-align: center; margin-top: 10px;">
	<input type="button" onclick="saveCommonSzInfo();" class="btn_green" value="保&#12288;存"/>
	<input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
</div>
