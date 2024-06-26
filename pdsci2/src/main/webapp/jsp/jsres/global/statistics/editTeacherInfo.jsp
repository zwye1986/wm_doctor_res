<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $('#trainingYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });

        $('#graduationTime').datepicker({
            startView: 1,
            maxViewMode: 1,
            minViewMode: 1,
            format: 'yyyy-mm'
        });

        $('#workingTime').datepicker({
            startView: 1,
            maxViewMode: 1,
            minViewMode: 1,
            format: 'yyyy-mm'
        });

        var deptId = '${teacher.deptFlow}';
        $("#deptFlow").empty();
        $("#deptFlow").append("<option value=''>请选择</option>");
        <c:forEach var="dept" items="${deptList}">
			var deptFlow = '${dept.deptFlow}';
			var deptName = '${dept.deptName}';
			if(deptFlow == deptId) {
				$("#deptFlow").append("<option selected value='" + deptFlow + "'>" + deptName + "</option>");
			}else{
				$("#deptFlow").append("<option value='" + deptFlow + "'>" + deptName + "</option>");
			}
		</c:forEach>
    });

    function checkUploadFile(){
    	var recordFlow = $("#recordFlow").val();
    	var userPhone = $("#userPhone").val();
        if ($("#excelForm").validationEngine("validate")) {
            $("#speName").val($("#speId option:selected").text());
            $("#deptName").val($("#deptFlow option:selected").text());
            jboxStartLoading();
            $(":file.auto:hidden").attr("disabled",true);
			jboxPost("<s:url value='/jsres/statistic/checkUserPhone'/>?userPhone="+userPhone+"&recordFlow="+recordFlow, null, function (resp) {
				jboxTip(resp);
				if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
					$("#excelForm").submit();
				}
				jboxEndLoading();
			},null,false);
        }
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
</script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" action="<s:url value='/jsres/statistic/checkUploadFile'/>" enctype="multipart/form-data"
	style="height: 500px;overflow-y: auto">
	<table class="grid" style="width: 100%;margin-top: 15px">
		<input  type="text" name="recordFlow" id="recordFlow"  value="${teacher.recordFlow}" style="display: none;"/>
		<input  type="text" name="speName" id="speName" value="${teacher.speName}" style="text-align: left;display: none;width: 150px;"/>
		<input  type="text" name="deptName" id="deptName" value="${teacher.deptName}" style="text-align: left;display: none;width: 150px;"/>
		<input  type="text" name="roleFlag" id="roleFlag" value="${roleFlag}" style="text-align: left;display: none;width: 150px;"/>
		<tr>
			<th width="150px"><font color="red" >*</font>师资培训会议名称</th>
			<td  style="text-align: left;">
				<input  type="text" name="meetingName" class="select validate[required]" value="${teacher.meetingName}"  style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>证书编号</th>
			<td  style="text-align: left;">
				<input  type="text" id="certificateNo" name="certificateNo" class="select validate[required]" value="${teacher.certificateNo}"  style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>师资类型</th>
			<td  style="text-align: left;">
				<input  type="text" name="teacherLevelId" class="select validate[required]" hidden value="${empty teacher.teacherLevelId ? 'GeneralFaculty' : teacher.teacherLevelId}"  style="text-align: left;width: 150px;"/>
				<input  type="text" name="teacherLevelName" class="select validate[required]" readonly value="${empty teacher.teacherLevelName ? '一般师资' : teacher.teacherLevelName}"  style="text-align: left;width: 150px;"/>
<%--				<select name="teacherLevelId" hidden id="teacherLevelId" class="select validate[required]" style="width: 150px;" >--%>
<%--					<option value="GeneralFaculty"<c:if test="${empty teacher.teacherLevelId || teacher.teacherLevelId=='GeneralFaculty' }">selected="selected"</c:if>>一般师资</option>--%>
<%--					<option value="KeyFaculty"<c:if test="${teacher.teacherLevelId=='KeyFaculty' }">selected="selected"</c:if>>骨干师资</option>--%>
<%--				</select>--%>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>基地</th>
			<td  style="text-align: left;">
				<select name="orgFlow" id="orgFlow" class="select validate[required]" style="width: 150px;" onchange="getOrgName();searchDeptList(this.value)" >
					<option value="">全部</option>
					<c:forEach items="${orgs}" var="org">
						<option value="${org.orgFlow}"<c:if test="${(teacher.orgFlow==org.orgFlow) or (teacher.orgName==org.orgName) }">selected="selected"</c:if>>${org.orgName}</option>
					</c:forEach>
				</select>
				<input  type="text" name="orgName" id="orgName" class="validate[required]" value="${teacher.orgName}" style="text-align: left;display: none;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>姓名</th>
			<td  style="text-align: left;">
				<input  type="text" name="doctorName" class="select validate[required]" value="${teacher.doctorName}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>手机号码</th>
			<td  style="text-align: left;">
				<input  type="text" id="userPhone" name="userPhone" <c:if test="${!empty teacher.userPhone}">readonly="readonly"</c:if> class="select validate[required]" value="${teacher.userPhone}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>年龄</th>
			<td  style="text-align: left;">
				<input  type="text" name="doctorAge" class="select validate[required,custom[integer],min[0]]" value="${teacher.doctorAge}" style="text-align: left;width: 150px;"/>
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
			<th width="150px"><font color="red" >*</font>技术职称</th>
			<td  style="text-align: left;">
				<input  type="text" name="technicalTitle" class="select validate[required]" value="${teacher.technicalTitle}" style="text-align: left;width: 150px"/>
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
			<th width="150px"><font color="red" >*</font>学历</th>
			<td  style="text-align: left;">
				<input  type="text" name="doctorEdu" class="select" value="${teacher.doctorEdu}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>科室</th>
			<td  style="text-align: left;">
				<select name="deptFlow" id="deptFlow" class="select validate[required]" style="width: 150px;" ></select>
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
			<th width="150px"><font color="red" >*</font>培训年份</th>
			<td  style="text-align: left;">
				<input type="text" value="${teacher.trainingYear}" class="select validate[required]" name="trainingYear" id="trainingYear" style="width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px"><font color="red" >*</font>工作时间</th>
			<td  style="text-align: left;">
				<input type="text" value="${teacher.workingTime}" class="select validate[required]" name="workingTime" id="workingTime" style="width: 150px;"/>
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
			<th width="150px">带实习生年限</th>
			<td  style="text-align: left;">
				<input  type="text" name="internYear" class="select validate[custom[integer],min[0]]" value="${teacher.internYear}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">带实习生近3年累计人数</th>
			<td  style="text-align: left;">
				<input  type="text" name="threeInternYear" class="select validate[custom[integer],min[0]]" value="${teacher.threeInternYear}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">带住院医师年限</th>
			<td  style="text-align: left;">
				<input  type="text" name="hosYear" class="select validate[custom[integer],min[0]]" value="${teacher.hosYear}" style="text-align: left;width: 150px;"/>
			</td>
		</tr>
		<tr>
			<th width="150px">带住院医师近3年累计人数</th>
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
			<th width="150px">证书附件</th>
			<c:if test="${empty teacher.certificateUrl}" >
				<td >
					<input type="file" name="uploadFile" style="margin-right: 115px" onchange="checkFile(this);"/>
				</td>
			</c:if>
			<c:if test="${not empty teacher.certificateUrl}" >
				<td >
					<input type="hidden" name="certificateUrl" value="${teacher.certificateUrl}"/>
					<input type="file" id="newFile" name="uploadFile" style="display: none;margin-right: 115px" onchange="checkFile(this);"/>
					<a style="margin-right: 115px" id="viewImgLink" href="${sysCfgMap['upload_base_url']}/${teacher.certificateUrl}" target="_blank" title="点击查看大图"><img src="${sysCfgMap['upload_base_url']}/${teacher.certificateUrl}" width="80px" height="80px"/></a>
					<a id="delImgLink" href="javascript:void(0)" onclick="delImg('${teacher.recordFlow}')" >[删除图片]</a>
					<a id="reuploadImgLink" href="javascript:void(0)" onclick="reuploadImg()">[重新上传图片]</a>
				</td>
			</c:if>
		</tr>
		<tr><td colspan="2" style="text-align: left;padding-left: 10px;">允许上传后缀格式：.jpg,.png,.jpeg</td></tr>
		<tr>
			<th width="150px">成果附件</th>
			<c:if test="${empty teacher.achievementUrl}" >
				<td >
					<input type="file" name="uploadFileOne" style="margin-right: 115px" onchange="checkFile(this);"/>
				</td>
			</c:if>
			<c:if test="${not empty teacher.achievementUrl}" >
				<td >
					<input type="hidden" name="achievementUrl" value="${teacher.achievementUrl}"/>
					<input type="file" id="newFileOne" name="uploadFileOne" style="display: none;margin-right: 115px" onchange="checkFile(this);"/>
					<a style="margin-right: 115px" id="viewImgLinkOne" href="${sysCfgMap['upload_base_url']}/${teacher.achievementUrl}" target="_blank" title="点击查看大图"><img src="${sysCfgMap['upload_base_url']}/${teacher.achievementUrl}" width="80px" height="80px"/></a>
					<a id="delImgLinkOne" href="javascript:void(0)" onclick="delImgOne('${teacher.recordFlow}')" >[删除图片]</a>
					<a id="reuploadImgLinkOne" href="javascript:void(0)" onclick="reuploadImg()">[重新上传图片]</a>
				</td>
			</c:if>
		</tr>
		<tr><td colspan="2" style="text-align: left;padding-left: 10px;">允许上传后缀格式：.jpg,.png,.jpeg</td></tr>
	</table>

</form>
<div style="text-align: center; margin-top: 10px;">
	<input type="button" onclick="checkUploadFile();" class="btn_green" value="保&#12288;存"/>
	<input type="button" class="btn_green" value="关&#12288;闭" onclick="jboxClose();"/>
</div>
