<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/sczyres/htmlhead-sczyres.jsp">
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style>
.base_info th,.base_info td{height:45px;}
.pxxx{position: absolute;width: 175px;background-color: white}
</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script>
String.prototype.htmlFormartById = function(data){
	var html = this;
	for(var key in data){
		var reg = new RegExp('\\{'+key+'\\}','g');
		html = html.replace(reg,data[key]);
	}
	return html;
};

$(document).ready(function(){
	//单位人展示为信息
	if ("${doctor.doctorTypeId}" != "") {
		var ac = $(".active");
 		doctorType("${doctor.doctorTypeId}",ac);
	}
	//工作经历默认一条空白记录
	//<c:if test="${empty extInfo.workResumeList}">
	//	add('work');
	//</c:if>
	
	$('.datepicker').datepicker(); 
	
// 	$("#pmoremessage,#contactway,#work").toggle();
	showYszz('${doctor.doctorLicenseFlag}'=='${GlobalConstant.FLAG_Y}');
	<%--setHospitalAtt("${extInfo.medicalHeaithOrg}");--%>
	addValidate("${user.cretTypeId}");
	checkIsPartner("${doctor.isPartner}");
	//隐藏毕业操作
	checkIsStudy("${extInfo.masterStatue}",'master');
	checkIsStudy("${extInfo.doctorStatue}",'doctor');
	//隐藏本科，硕士和博士操作
	checkBx("${extInfo.isMaster}",'master');
	checkBx("${extInfo.isDoctor}",'doctor');
	changeOrgLevel("${extInfo.medicalHeaithOrgId}");
	checkIsPartner("${doctor.isPartner}");
	if("${user.cretTypeId eq certificateTypeEnumShenfenzheng.id }"&&$("${user.userBirthday}").val()==""){
		$("#birthday").attr("disabled",true);
	}
	//如果不是身份证生日信息不根据IdNo自动带入
	if("${user.cretTypeId ne certificateTypeEnumShenfenzheng.id }"){
		$("#userBirthday").val("${user.userBirthday}");
	}

	var tabCourse = $('.icon-head');
	tabCourse.on('mouseover',function(){
		$("#changeInfo").show();
	});
	tabCourse.on('mouseout',function(){
		$(".pxxx").hide();
	});
});

function doctorType(doctorTypeId,active){
	$(".active").removeClass();
	$(active).addClass("active");
	$("#doctorTypeId").val(doctorTypeId);
	if(doctorTypeId == "${sczyRecDocTypeEnumAgency.id}" || doctorTypeId == "${sczyRecDocTypeEnumEntrust.id}"){
		$(".school").hide();
		$(".address").show();
		changeOrgLevel($("#medicalHeaithOrgId").val());
	}
	if(doctorTypeId == "${sczyRecDocTypeEnumIndustry.id}"){
		$(".school").hide();
		$(".hospital").hide();
		$(".medical").hide();
		$(".address").hide();
	}
	if(doctorTypeId == "${sczyRecDocTypeEnumGraduate.id}"){
		$(".address").hide();
		$(".hospital").hide();
		$(".medical").hide();
		$(".school").show();
		//“人员类型”如果是在校专硕，则是否为硕士必填，且为在读，其他人员类型（单位人、委培单位人、社会人）则该项不能为在读。
		$(".masterStatue[value='1']").attr("disabled",false);
		$(".isMaster[value='N']").attr("disabled",false);
		$(".masterStatue[value='2']").attr("disabled",false);
		$(".isMaster[value='Y']").click();
		$(".isMaster[value='N']").attr("disabled",true);
		$(".masterStatue[value='1']").click();
		$(".masterStatue[value='2']").attr("disabled",true);
	}else{
		$(".isMaster[value='N']").attr("disabled",false);
		$(".masterStatue[value='2']").attr("disabled",false);
		$(".masterStatue[value='2']").click();
		$(".masterStatue[value='1']").attr("disabled",true);
	}
	if(doctorTypeId==""){
		$(".school").hide();
		$(".address").hide();
	}
}
function changeOrgLevel(value){
	if(value=="1"){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").removeAttr("colspan");
		$(".hospital").show();
		$("#TD").attr("colspan",3);
	}
	if(value=="2"){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").attr("colspan",5);
		$(".hospital").hide();
	}
	if(value=="" | value=="4"){
		$(".medical").hide();
		$("#medicalHeaithOrgIdTd").attr("colspan",5);
		$(".hospital").hide();
	}
	if(value=="3"){
		$(".hospital").hide();
		$(".medical").show();
		$("#medicalHeaithOrgIdTd").removeAttr("colspan");
	}
}
function changeOrg(){
	var school=$("#psxx").val();
	var org=$("#work").val();
	var personType=$("#doctorTypeId").val();
	if(personType == "${sczyRecDocTypeEnumAgency.id}" || personType == "${sczyRecDocTypeEnumEntrust.id}"){
		$("#workOrgName").val(org);
	}
	if(personType== "${sczyRecDocTypeEnumGraduate.id}"){
		$("#workOrgName").val(school);
	}
	if(personType== "${sczyRecDocTypeEnumIndustry.id}"){
		$("#workOrgName").val("");
	}
}
function uploadImage(){
	$.ajaxFileUpload({
		url:"<s:url value='/sczyres/doctor/userHeadImgUpload'/>?userFlow=${user.userFlow}",
		secureuri:false,
		fileElementId:'imageFile',
		dataType: 'json',
		success: function (data, status){
			if(data.indexOf("success")==-1){
				jboxTip(data);
			}else{
				jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
				var arr = new Array();
				arr = data.split(":");
				$("#userImg").val(arr[1]);
				var url = "${sysCfgMap['upload_base_url']}/"+ arr[1];
				$("#showImg").attr("src",url);
				$("#headimgurl").val(arr[1]);
			}
		},
		error: function (data, status, e){
			jboxTip('${GlobalConstant.UPLOAD_FAIL}');
		},
		complete:function(){
			$("#imageFile").val("");
		}
	});
}

function add(tb){
	$("#add_a").remove();
	var cloneTr = $("#"+tb+"Template tr:eq(0)").clone();
	var index = $("#"+tb+"Tb tr").length;
	cloneTr.html(cloneTr.html().htmlFormartById({"index":index}));
 	$("#"+tb+"Tb").append(cloneTr);
 	$('.datepicker').datepicker();
}

function delTr(tb){
	var checkboxs = $("input[name='"+tb+"Ids']:checked");
	if(checkboxs.length==0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除?",function () {
		var trs = $('#'+tb+'Tb').find(':checkbox:checked');
		$.each(trs , function(i , n){
			$(n).parent('td').parent("tr").remove();
		});
		
		var reg = new RegExp('\\[\\d+\\]','g');
		$("#"+tb+"Tb tr").each(function(i){
			$("[name]",this).each(function(){
				this.name = this.name.replace(reg,"["+i+"]");
			});
		});
	});
}

function uploadFile(type,typeName) {
	jboxOpen("<s:url value='/inx/sczyres/uploadFile'/>?operType="+type,"上传"+typeName,450,150);
}

function delFile(type) {
	jboxConfirm("确认删除？" , function(){
		$("#"+type+"Del").hide();
		$("#"+type+"Span").hide();
		$("#"+type).text("上传");
		$("#"+type+"Value").val("");
	});
	
}

function getRecruit(){
	jboxLoad("content" , "<s:url value='/sczyres/doctor/getrecruit'/>" , true);
	$("#indexBody").scrollTo('.bd_bg',500, { offset:{ top:0} } );
}

function submitSingup(){
	if(!$("#doctorTypeId").val()){
		$("#indexBody").scrollTop("0px");
		return jboxTip("请选择人员类型！");
	}
	if(!$("#doctorForm").validationEngine("validate")){
		return false;
	}
	if($('${user.userHeadImg}'=="" &&"#headimgurl").val()==""){
		jboxTip("请上传头像照片!");
		return false;
	}
	if(!$("#idNoUriValue").val()){
		jboxTip("请上传身份证图片");
		return false;
	}
	if(!$("#certificateUriValue").val()){
		jboxTip("请上传毕业证书");
		return false;
	}
	if($("input[name='doctor.doctorLicenseFlag']:checked").val()=="Y" && !$("#qualifiedUriValue").val()){
		jboxTip("请上传医师资格证书");
		return false;
	}
	//如果不是身份证生日信息不根据IdNo自动带入
	if("${user.cretTypeId ne certificateTypeEnumShenfenzheng.id }"){
		$("#birthday").val($("#userBirthday").val());
	}
//	if($("input[name='doctor.doctorLicenseFlag']:checked").val()=="Y" && !$("#regUriValue").val()){
//		jboxTip("请上传医师资格证书");
//		return false;
//	}
	$("#birthProvName").val($("#birthProvId :selected").text());
	$("#birthCityName").val($("#birthCityId :selected").text());
	$("#birthAreaName").val($("#birthAreaId :selected").text());
	$("#birthAreaName").val($("#birthAreaId :selected").text());
	$("#nationName").val($("#nationId :selected").text());
	$("#degreeName").val($("#degree :selected").text());
	$("#masterDegreeName").val($("#masterDegreeId :selected").text());
	$("#doctorDegreeName").val($("#doctorDegreeId :selected").text());
	$("#medicalHeaithOrgName").val($("#medicalHeaithOrgId :selected").text());
	$("#hospitalAttrName").val($("#hospitalAttrId :selected").text());
	$("#basicHealthOrgName").val($("#basicHealthOrgId :selected").text());
	$("#basicHealthOrgLevelName").val($("#basicHealthOrgLevelId :selected").text());
	$("#hospitalCategoryName").val($("#hospitalCategoryId :selected").text());
	$("#baseAttributeName").val($("#baseAttributeId :selected").text());
	<%--if($("#doctorTypeId").val() != "${sczyRecDocTypeEnumAgency.id}"){--%>
		<%--$("#dispatchFileFValue,[name='doctor.workOrgName']").val("");--%>
	<%--}else {--%>
		<%--if(!$("#dispatchFileFValue").val()){--%>
			<%--jboxTip("请上传委培单位同意证明!");--%>
			<%--return false;--%>
		<%--}--%>
	<%--}--%>
	jboxPost("<s:url value='/sczyres/doctor/submitSingup'/>" , $("#doctorForm").serialize() , function(resp){
		if(resp=="1"){
			getRecruit();
		}else{
			jboxTip(resp);
		}
	} , null , false);
}

function saveSingup(){
	if(!$("#doctorTypeId").val()){
		$("#indexBody").scrollTop("0px");
		return jboxTip("请选择人员类型！");
	}
	if($("#userName").validationEngine("validate")){
		$("#indexBody").scrollTop("0px");
		return false;
	}
	//如果不是身份证生日信息不根据IdNo自动带入
	if("${user.cretTypeId ne certificateTypeEnumShenfenzheng.id }"){
		$("#birthday").val($("#userBirthday").val());
	}
	$("#birthProvName").val($("#birthProvId :selected").text());
	$("#birthCityName").val($("#birthCityId :selected").text());
	$("#birthAreaName").val($("#birthAreaId :selected").text());
	$("#nationName").val($("#nationId :selected").text());
	$("#cretTypeName").val($("#cretTypeId :selected").text());
	$("#sexName").val($("[name='user.sexId']:checked").text());
	$("#educationName").val($("#educationId :selected").text());
	$("#degreeName").val($("#degree :selected").text());
	$("#masterDegreeName").val($("#masterDegreeId :selected").text());
	$("#doctorDegreeName").val($("#doctorDegreeId :selected").text());
	$("#medicalHeaithOrgName").val($("#medicalHeaithOrgId :selected").text());
	$("#hospitalAttrName").val($("#hospitalAttrId :selected").text());
	$("#basicHealthOrgName").val($("#basicHealthOrgId :selected").text());
	$("#basicHealthOrgLevelName").val($("#basicHealthOrgLevelId :selected").text());
	$("#hospitalCategoryName").val($("#hospitalCategoryId :selected").text());
	$("#baseAttributeName").val($("#baseAttributeId :selected").text());
	jboxPost("<s:url value='/sczyres/doctor/submitSingup'/>" , $("#doctorForm").serialize() , function(resp){
		if(resp=="1"){
			jboxTip("保存成功");
		}else{
			jboxTip(resp);
		}
	} , null , false);
}

function showYszz(isShow){
	if(isShow){
		$(".yszz").show();
	}else {
		$(".yszz").hide();
	}
}

function addValidate(cretTypeId){
	if("${certificateTypeEnumShenfenzheng.id}" == cretTypeId){
		$("#idNo").addClass("validate[custom[chinaId]]");
		$("#idNo").attr("onchange","writeBirthday(this);checkUserUnique();");
		$("#userBirthday").attr("disabled",true);
		$("#birthday").attr("disabled",false);
		$("#birthday").val($("#userBirthday").val());
		//先算一遍生日
		var idNo =  $("#idNo").val();
		var birthDayObj = $("#userBirthday");
		var birthDay="";
		if(idNo.length==15){
			birthDay = "19"+idNo.substr(6,2)+"-"+idNo.substr(8,2)+"-"+idNo.substr(10,2);
		}else if(idNo.length==18){
			birthDay = idNo.substr(6,4)+"-"+idNo.substr(10,2)+"-"+idNo.substr(12,2);
		}else{
			return false;
		}
		birthDayObj.val(birthDay);
		$("#birthday").val(birthDay);
	}else{
		$("#idNo").removeClass("validate[custom[chinaId]]");
		$("#idNo").attr("onchange","checkUserUnique();");
		$("#userBirthday").attr("disabled",false);
	}
}
function writeBirthday(obj){
	var idNo = obj.value;
	var birthDayObj = $("#userBirthday");
	var birthDay="";
	if(idNo.length==15){
		birthDay = "19"+idNo.substr(6,2)+"-"+idNo.substr(8,2)+"-"+idNo.substr(10,2);
	}else if(idNo.length==18){
		birthDay = idNo.substr(6,4)+"-"+idNo.substr(10,2)+"-"+idNo.substr(12,2);
	}else{
		return false;
	}
	birthDayObj.val(birthDay);
	$("#birthday").val(birthDay);
}
function changeBirthday(vari){
	$("#birthday").val($(vari).val());
}
/**
 * 验证身份证号、手机号唯一
 */
function checkUserUnique(){
	var idNo = $("input[name='user.idNo']").val();
	var userPhone = $("input[name='user.userPhone']").val();
	var data = {
		userFlow:"${user.userFlow}",
		idNo:idNo,
		userPhone:userPhone
	};
	if(userPhone != "" || idNo != ""){
		var url = "<s:url value='/jszy/doctor/checkUserUnique'/>";
		jboxPost(url, data,
				function(resp){
					if(resp == "${GlobalConstant.USER_ID_NO_REPETE}"){
						var cretTypeId = $("#cretTypeId").val();
						if("${certificateTypeEnumShenfenzheng.id}" == cretTypeId){
							jboxTip(resp);
						}else{
							jboxTip("该证件号已经被注册！");
						}
						$("input[name='user.idNo']").val('${user.idNo}');
						$("input[name='user.idNo']").focus();
					}else if(resp == "${GlobalConstant.USER_PHONE_REPETE}"){
						jboxTip(resp);
						$("input[name='user.userPhone']").val('${user.userPhone}');
						$("input[name='user.userPhone']").focus();
					}
				}, null, false);
	}
}
function checkIsPartner (item){
	if(item == 'Y'){
		$(".sourceProvinces").eq(0).prev().removeAttr("colspan");
		$(".sourceProvinces").show();
	}else {
		$(".sourceProvinces").hide();
		$(".sourceProvinces").eq(0).prev().attr("colspan",4);
		$("#sourceProvincesName").val("");
		$("#orgProvId").attr("data-value","");
	}
}
function checkBx(value,type){
	if(value=="${GlobalConstant.FLAG_Y}"){
		$("."+type+"Info").show();
		$("."+type+"Info").eq(0).prev().removeAttr("colspan");
	}else{
		$("."+type+"Info").hide();
		$("."+type+"Info").eq(0).prev().attr("colspan",3);
	}
}
function checkIsStudy(value,typeId){
	if(value == "1"){
		$("."+typeId+"IsGrad").hide();
		$("."+typeId+"IsGrad").eq(0).prev().attr("colspan",3);
	}else {
		$("."+typeId+"IsGrad").show();
		$("."+typeId+"IsGrad").eq(0).prev().removeAttr("colspan");
	}
}
function changeDegreeType(obj,type){
	if(type=="master"&&$(obj).val()!=""){
		$("#masterDegreeTypeName").val($(obj).find("option:selected").text());
	}
	if(type=="master"&&$(obj).val()==""){
		$("#masterDegreeTypeName").val("");
	}
	if(type=="doctor"&&$(obj).val()!=""){
		$("#doctorDegreeTypeName").val($(obj).find("option:selected").text());
	}
	if(type=="doctor"&&$(obj).val()==""){
		$("#doctorDegreeTypeName").val("");
	}
}
function changeSourceProvincesName(){
	var sname = $("#orgProvId").find("option:selected").text();
	$("#sourceProvincesName").val(sname);
}

</script>
<div id="singupContent">
<div id='docTypeForm'>
    <p id="errorMsg" style="color: red;"></p>
    <div class="main_hd"><h2 class="underline">网上报名</h2></div>
    <form id='doctorForm' style="position:relative;">
    <input type="hidden" name="user.userFlow" value="${user.userFlow}"/>
    <input id="doctorTypeId" type="hidden" name="doctor.doctorTypeId" value="${doctor.doctorTypeId}"/>
	<!-- 生源省份Name-->
	<input type="hidden" id="sourceProvincesName" name="doctor.sourceProvincesName" value="${doctor.sourceProvincesName}"/>
	<!-- 学位类型Name-->
	<input type="hidden" id="masterDegreeTypeName" name="extInfo.masterDegreeTypeName" value="${extInfo.masterDegreeTypeName}"/>
	<input type="hidden" id="doctorDegreeTypeName" name="extInfo.doctorDegreeTypeName" value="${extInfo.doctorDegreeTypeName }"/>
	<!-- 最高学历相关信息-->
	<input type="hidden" id="educationName" name="user.educationName" value="${user.educationName}"/>
	<input type="hidden" id="birthday" name="user.userBirthday" value="${user.userBirthday }"/>
    <div class="main_bd">
       <div class="div_table">
          <div class="score_frame">
            
            <h1>信息填写</h1>
            
            <div class="sub_menu">
              <h3>学员类型选择：</h3>
              <ul class="ryxz">
              	<c:forEach items="${sczyRecDocTypeEnumList}" var="doctorType">
              		<li 
              			<c:if test="${doctorType.id eq doctor.doctorTypeId}">class="active"</c:if>
              		  onclick="doctorType('${doctorType.id}',this);"><a>${doctorType.name}</a></li>
			    </c:forEach>
              </ul>
            </div>
            
            <div class="div_table">
                <h4>基本信息</h4>
                <table border="0" cellpadding="0" cellspacing="0" class="base_info">
            <colgroup>
              <col width="12%"/>
              <col width="22%"/>
              <col width="12%"/>
              <col width="22%"/>
              <col width="12%"/>
              <col width="20%"/>
            </colgroup>
	               <tr>
	                   <th><font color="red">*</font>姓名：</th>
	                   <td><input type='text' class='input validate[required]' id="userName" name="user.userName" value="${user.userName}"/></td>
	                   <th><font color="red">*</font>出生日期：</th>
	                   <td colspan="2"><input id="userBirthday"  value="${user.userBirthday}"  class="validate[required] input datepicker" onchange="changeBirthday(this);" style="width: 149px;" readonly="readonly"/></td>
	                   <td rowspan="6" style="text-align: center;padding-top:5px;">
						   <img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="110px"
								height="130px" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
							<span style="cursor: pointer; display:block;">
								[<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
								<img  class="icon-head"  src="<s:url value='/css/skin/LightBlue/images/infoma.png'/>"/>
							</span>
							<input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
							<input type="hidden" id="headimgurl" value=""/>
						   <div id="changeInfo" class="pxxx" style="display: none;">
							   <div class="changeinfo" id="changeInfoContent" style="height: 150px;">
								   <i class="icon_up"></i>
								   <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%">
									   <caption style="border-bottom: none;line-height: 20px;color: black;font-size: xx-small;">
										   <label class="red">*&#12288;</label>请上传2吋白底彩色免冠照片，JPG格式，分辨率不小于160×210，大小需为20-45kb之间，人像正立！<br/>2、该照片用于结业证书，请慎重认真上传！</caption>
								   </table>
							   </div>
						   </div>
						   <%--<div style="font-size: 10px;line-height: 20px;text-align: left;color: red">--%>
						   <%--请上传2吋白底彩色免冠照片，JPG格式，分辨率不小于160×210，大小需为20-45kb之间，人像正立！<br/>--%>
						   <%--该照片将用于结业证书，请慎重认真上传！--%>
						   <%--</div>--%>
					   </td>
	               </tr>
				   <tr>
	                   <th><font color="red">*</font>民族：</th>
	                   <td style="padding-left:10px;">
						   <select name="user.nationId" id="nationId" class="validate[required] select" style="width: 160px;">
							   <option value="">请选择</option>
							   <c:forEach items="${userNationEnumList}" var="userNation">
								   <option value="${userNation.id}" ${userNation.id eq user.nationId?'selected':''}>${userNation.name}</option>
							   </c:forEach>
						   </select>
						   <input type='hidden' name="user.nationName" id="nationName" value="${user.nationName}"/>
					   </td>
					   <th><font color="red">*</font>性别：</th>
					   <td style="padding-left: 10px;" colspan="2">
						   <label>
							   <input type="radio" class='validate[required]' style="width:auto;" name="user.sexId" value='${userSexEnumMan.id}' ${user.sexId eq userSexEnumMan.id?'checked':''}/>&nbsp;${userSexEnumMan.name}
						   </label>
						   &nbsp;
						   <label>
							   <input type="radio" class='validate[required]' style="width:auto;" name="user.sexId" value='${userSexEnumWoman.id}' ${user.sexId eq userSexEnumWoman.id?'checked':''}/>&nbsp;${userSexEnumWoman.name}
						   </label>
						   <input type='hidden' name="user.sexName" id="sexName" value="${user.sexName}"/>
					   </td>
				   </tr>
				   <tr>
					   <th>外语能力：</th>
					   <td><input name="doctor.foreignSkills" value="${doctor.foreignSkills}" class="input"/></td>
					   <th>计算机能力：</th>
					   <td colspan="2"><input name="doctor.computerSkills" value="${doctor.computerSkills}" class="input"/></td>
				   </tr>

	               <tr>
					   <th><font color="red">*</font>是否是对口支援：</th>
					   <td style="padding-left: 10px;">
						   <label><input name="doctor.isPartner" class="validate[required]" type="radio" <c:if test="${doctor.isPartner eq GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" class="" onclick="checkIsPartner(this.value)"/>&nbsp;是</label>
						   <label><input name="doctor.isPartner" class="validate[required]" type="radio" <c:if test="${doctor.isPartner eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" class=""onclick="checkIsPartner(this.value)"/>&nbsp;否</label>
					   </td>
					   <th style="display: none" class="sourceProvinces"><font class="red">*</font>生源省份：</th>
					   <td style="display: none" class="sourceProvinces" colspan="2">
							   &nbsp;<span id="provCityAreaId">
							<select id="orgProvId" name="doctor.sourceProvincesId" onchange="changeSourceProvincesName();" class="province select validate[required]" data-value="${doctor.sourceProvincesId}" data-first-title="选择省" style="width:160px;"></select>
							</span>
						</td>
						   <script type="text/javascript">
							   // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
							   $.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>";
							   $.cxSelect.defaults.nodata = "none";

							   $("#provCityAreaId").cxSelect({
	//								selects : ["province", "city", "area"],
								   selects : ["province"],
								   nodata : "none",
								   firstValue : ""
							   });
						   </script>
	                   <%--<th>既往病史：</th>--%>
	                   <%--<td><input type='text' class='input ' name="extInfo.beforeCase" value="${extInfo.beforeCase}"/></td>--%>
	               </tr>
				   <tr>
					   <th><font color="red">*</font>证件类型：</th>
					   <td style="padding-left:10px;">
						   <select name="user.cretTypeId" id="cretTypeId" onchange="addValidate(this.value)" class="validate[required] select" style="width: 160px;">
							   <option value="">请选择</option>
							   <c:forEach items="${certificateTypeEnumList}" var="certificateType">
								   <option value="${certificateType.id}" ${certificateType.id eq user.cretTypeId?'selected':''}>${certificateType.name}</option>
							   </c:forEach>
						   </select>
						   <input type="hidden" value="${user.cretTypeName}" name="user.cretTypeName" id="cretTypeName">
					   </td>
					   <th><span class="red">*</span>证&ensp;件&ensp;号：</th>
					   <td colspan="2">
						   <%--<c:if test="${empty doctor.graduationStatusId}">--%>
							   <input type="text" name="user.idNo" id="idNo" value="${user.idNo}"  onchange="checkUserUnique();" class="validate[custom[chinaIdLoose],required] input"/>
						   <%--</c:if>--%>
						   <%--<c:if test="${!empty doctor.graduationStatusId}"><label>${user.idNo}</label><input name="user.idNo" value="${user.idNo}" type="hidden"></c:if>--%>
						   <font color="red">登录名,谨慎修改</font>
					   </td>
	               </tr>
				   <tr>
					   <th><font color="red">*</font>订单定向培养：</th>
					   <td style="padding-left: 10px;">
						   <label><input name="extInfo.isGeneralOrderOrientationTrainee" type="radio"<c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是</label>
						   <label><input name="extInfo.isGeneralOrderOrientationTrainee" type="radio" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否</label>
					   </td>
					   <th><font color="red">*</font>是否应届生：</th>
					   <td style="padding-left: 10px;" colspan="2">
						   <label><input type="radio" class='validate[required]' name="extInfo.yearGraduateFlag" value="${GlobalConstant.FLAG_Y}"
										 <c:if test="${extInfo.yearGraduateFlag eq GlobalConstant.FLAG_Y}">checked</c:if>
						   />&nbsp;是</label>
						   &nbsp;
						   <label><input type="radio" class='validate[required]' name="extInfo.yearGraduateFlag" value="${GlobalConstant.FLAG_N}"
										 <c:if test="${extInfo.yearGraduateFlag eq GlobalConstant.FLAG_N}">checked</c:if>
						   />&nbsp;否</label>
					   </td>
				   </tr>
	               <tr>
	               	   <th><font color="red">*</font>规培生源地：</th>
	                   <td colspan="3">
							<div id="provCityAreaId0" style="padding-left: 5px;">
								<select id="birthProvId" name="extInfo.birthProvId" style="width: 159px" class="province select validate[required]" data-value="${extInfo.birthProvId}" data-first-title="选择省"></select>
								<select id="birthCityId" name="extInfo.birthCityId" class="city select validate[required]" data-value="${extInfo.birthCityId}" data-first-title="选择市"></select>
								<select id="birthAreaId" name="extInfo.birthAreaId" class="area select validate[required]" data-value="${extInfo.birthAreaId}" data-first-title="选择地区"></select>
							</div>
							<input id="birthProvName" name="extInfo.birthProvName" type="hidden" value="${extInfo.birthProvName}">
							<input id="birthCityName" name="extInfo.birthCityName" type="hidden" value="${extInfo.birthCityName}">
							<input id="birthAreaName" name="extInfo.birthAreaName" type="hidden" value="${extInfo.birthAreaName}">
							<script type="text/javascript">
								// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件 
								$.cxSelect.defaults.url = "<s:url value='/js/provCityAreaJson.min.json'/>"; 
								$.cxSelect.defaults.nodata = "none"; 
	
								$("#provCityAreaId0").cxSelect({
								    selects : ["province", "city", "area"], 
								    nodata : "none",
									firstValue : ""
								}); 
							</script>
	                   </td>
					   <th ><font color="red">*</font>有无执业医师资格：</th>
					   <td style="padding-left: 10px;">
						   <label><input type="radio" class='validate[required]' name="doctor.doctorLicenseFlag" onclick="showYszz(true);" value="${GlobalConstant.FLAG_Y}"
										 <c:if test="${doctor.doctorLicenseFlag eq GlobalConstant.FLAG_Y}">checked</c:if>
						   />&nbsp;有</label>
						   &nbsp;
						   <label><input type="radio" class='validate[required]' name="doctor.doctorLicenseFlag" onclick="showYszz(false);" value="${GlobalConstant.FLAG_N}"
										 <c:if test="${doctor.doctorLicenseFlag eq GlobalConstant.FLAG_N}">checked</c:if>
						   />&nbsp;无</label>
					   </td>
	               </tr>
				   <tr>
					   <th class="school" style="display: none;"><span class="red">*</span>派送学校：</th>
					   <td class="school" colspan="5" style="display: none;">
						   <input id="psxx" value="${doctor.workOrgName}" class="toggleView input validate[required]" type="text" onchange="changeOrg();"/>
					   </td>
					   <th class="address" id="address" style="display: none;"><span class="red">*</span>派送单位：</th>
					   <td colspan="5" class="address" style="display: none;">
						   <input id="work" value="${doctor.workOrgName}" class="validate[required] input" onchange="changeOrg();"/>
					   </td>
					   <input type="hidden" id="workOrgName" name="doctor.workOrgName"  value="${doctor.workOrgName}"/>
				   </tr>
				   <tr class="address">
					   <th><span class="red">*</span>医疗卫生机构：</th>
					   <td colspan="5" id="medicalHeaithOrgIdTd">
						   &nbsp;<select class="select address validate[required]" id="medicalHeaithOrgId" name="extInfo.medicalHeaithOrgId" onchange="changeOrgLevel(this.value);" style="width: 160px">
						   <option value="">请选择</option>
						   <c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
							   <option value="${tra.dictId}" <c:if test="${extInfo.medicalHeaithOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						   </c:forEach>
					   </select>
					   <input type="hidden" name="extInfo.medicalHeaithOrgName" id="medicalHeaithOrgName">
					   </td>
					   <th class="hospital medical" style="display: none;"><span class="red">*</span>医院性质：</th>
					   <td class="hospital medical" style="display: none;" colspan="3" id="TD">
						   &nbsp;<select class="select hospital medical validate[required]" id="hospitalAttrId" name="extInfo.hospitalAttrId"style="width: 160px">
						   <option value="">请选择</option>
						   <c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
							   <option value="${tra.dictId}" <c:if test="${extInfo.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						   </c:forEach>
					   </select>
						   <input type="hidden" name="extInfo.hospitalAttrName" id="hospitalAttrName">
					   </td>
				   </tr>
				   <tr style="display: none;" class="medical">
					   <th class="medical" style="display: none;"><span class="red">*</span>基层医疗&#12288;<br/>卫生机构：</th>
					   <td class="medical" style="display: none;">
						   &nbsp;<select class="select medical validate[required]" id="basicHealthOrgId" name="extInfo.basicHealthOrgId"style="width: 160px">
						   <option value="">请选择</option>
						   <c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
							   <option value="${tra.dictId}" <c:if test="${extInfo.basicHealthOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						   </c:forEach>
					   </select>
						   <input type="hidden" name="extInfo.basicHealthOrgName" id="basicHealthOrgName">
					   </td>
					   <th><span class="red">*</span>基层医疗卫&#12288;<br/>生机构等级：</th>
					   <td colspan="3">
						   &nbsp;<select class="select medical validate[required]" id="basicHealthOrgLevelId" name="extInfo.basicHealthOrgLevelId"style="width: 160px">
						   <option value="">请选择</option>
						   <c:forEach items="${dictTypeEnumBasicHealthOrgLevelList}" var="level">
							   <option value="${level.dictId}" <c:if test="${extInfo.basicHealthOrgLevelId eq level.dictId}">selected="selected"</c:if>>${level.dictName}</option>
						   </c:forEach>
					   </select>
						   <input type="hidden" name="extInfo.basicHealthOrgLevelName" id="basicHealthOrgLevelName">
					   </td>
				   </tr>
				   <tr style="display: none;" class="hospital">
					   <th><span class="red">*</span>医院类别：</th>
					   <td>
						   &nbsp;<select class="select hospital  validate[required]" id="hospitalCategoryId" name="extInfo.hospitalCategoryId"style="width: 160px">
						   <option value="">请选择</option>
						   <c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
							   <option value="${tra.dictId}" <c:if test="${extInfo.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						   </c:forEach>
					   </select>
						   <input type="hidden" name="extInfo.hospitalCategoryName" id="hospitalCategoryName">
					   </td>
					   <th><span class="red">*</span>单位等级：</th>
					   <td colspan="3">
						   &nbsp;<select class="select hospital validate[required]" id="baseAttributeId" name="extInfo.baseAttributeId"style="width: 160px">
						   <option value="">请选择</option>
						   <c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
							   <option value="${tra.dictId}" <c:if test="${extInfo.baseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						   </c:forEach>
					   </select>
						   <input type="hidden" name="extInfo.baseAttributeName" id="baseAttributeName">
					   </td>
				   </tr>
              </table>
              </div>
              
            <div class="div_table" ID="contactway">
			<h4>本人联系方式</h4>
	        <table border="0" cellpadding="0" cellspacing="0" class="base_info">
	            <colgroup>
					<col width="12%"/>
					<col width="22%"/>
					<col width="12%"/>
					<col width="22%"/>
					<col width="12%"/>
					<col width="20%"/>
	            </colgroup>
		           <tbody>
		               <tr>
		                   <th><font color="red">*</font>手机号码：</th>
	                   	   <td><input type='text' class='input validate[required,custom[mobile]]' name="user.userPhone" value="${user.userPhone}" onchange="checkUserUnique();"/></td>
		                   <th><font color="red">*</font>通讯地址：</th>
	                   	   <td colspan="3"><input type='text' class='input validate[required]' name="user.userAddress" value="${user.userAddress}" style="width: 460px;"/></td>
		               </tr>
		               <tr>
		                   <th><font color="red">*</font>E-mail：</th>
	                   	   <td><input type='text' class='input validate[custom[email]]' name="user.userEmail" value="${user.userEmail}"/></td>
						   <th>QQ号：</th>
						   <td><input type='text' class='input' name="extInfo.qqCode" value="${extInfo.qqCode}"/></td>
		                   <th>其它方式：</th>
	                   	   <td><input type='text' class='input' name="extInfo.otherWay" value="${extInfo.otherWay}" /></td>
		               </tr>
					   <tr>
						   <th>家庭住址 ：</th>
						   <td><input type='text' class='input' name="extInfo.homeAddress" value="${extInfo.homeAddress}"/></td>
						   <th>家庭电话 ：</th>
						   <td><input type='text' class='input' name="extInfo.homePhome" value="${extInfo.homePhome}"/></td>
						   <th><font color="red">*</font>邮编 ：</th>
						   <td><input type='text' class='input validate[required]' name="extInfo.zipCode" value="${extInfo.zipCode}"/></td>
					   </tr>
		           </tbody>
		       </table>
		      </div>

			  <div class="div_table">
				  <h4>教育情况</h4>
				  <table border="0" cellpadding="0" cellspacing="0" class="base_info">
					  <colgroup>
						  <col width="16%"/>
						  <col width="30%"/>
						  <col width="16%"/>
						  <col width="38%"/>
					  </colgroup>
					  <tbody>
					  <tr class="graduateTh">
						  <th><span class="red">*</span>本科毕业院校：</th>
						  <td>
							  <input id="bkbyyx"  name="doctor.graduatedName" value="${doctor.graduatedName}" class="toggleView graduate input validate[required]" type="text"/>
						  </td>
						  <th><span class="red yy">*</span>本科毕业时间：</th>
						  <td ><input id="bkbysj"name="doctor.graduationTime" value="${doctor.graduationTime}" class="validate[required] input graduate datepicker"  style="width: 149px;" readonly="readonly"/></td>
					  </tr>
					  <tr class="graduateTh">
						  <th><span class="red yy">*</span>本科毕业专业：</th>
						  <td><input id="bkbyzy"name="doctor.specialized" value="${doctor.specialized}" class="input  graduate validate[required] bk"/></td>
						  <th><span class="red yy">*</span>学&#12288;&#12288;位：</th>
						  <td style="padding-left:10px;">
							  <select id="degree"name="extInfo.degreeId" class="select graduate validate[required] bk" style="width: 160px;">
								  <option value="">请选择</option>
								  <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
									  <option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.degreeId }">selected</c:if>>${dict.dictName}</option>
								  </c:forEach>
							  </select>
							  <input type="hidden" id="degreeName" name="extInfo.degreeName" value="">
						  </td>
					  </tr>
					  <tr class="graduateTh">
						  <th><span class="red yy">*</span>最高学历：</th>
						  <td colspan="3">
							  &nbsp;<select name="user.educationId" class="select graduate validate[required] " style="width: 160px;">
							  <option value="">请选择</option>
							  <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
								  <option value="${dict.dictId }" <c:if test="${user.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
							  </c:forEach>
						  </select>&#12288;&nbsp;
						  </td>
					  </tr>
					  <tr>
						  <th><span class="red">*</span>是否为硕士：</th>
						  <td>
							  <label>&#12288;<input name="extInfo.isMaster" type="radio"<c:if test="${extInfo.isMaster eq GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" class="validate[required] isMaster" onchange="checkBx(this.value,'master')"/>&nbsp;是&#12288;</label>
							  <label><input name="extInfo.isMaster" type="radio" <c:if test="${extInfo.isMaster eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" class="validate[required] isMaster"onchange="checkBx(this.value,'master')"/>&nbsp;否&nbsp;&nbsp;</label>
						  </td>
						  <th style="display: none" class="masterInfo"><span class="red">*</span>状&#12288;&#12288;态：</th>
						  <td style="display: none" class="masterInfo">
							  <label>&#12288;<input name="extInfo.masterStatue" type="radio"<c:if test="${extInfo.masterStatue eq '1' }">checked="checked"</c:if> value="1" class="validate[required] masterStatue" onchange="checkIsStudy(this.value,'master')"/>&nbsp;在读&#12288;</label>
							  <label><input name="extInfo.masterStatue" type="radio" <c:if test="${extInfo.masterStatue eq '2' }">checked="checked"</c:if> value="2" class="validate[required] masterStatue"onchange="checkIsStudy(this.value,'master')"/>&nbsp;已毕业&nbsp;&nbsp;</label>
						  </td>
					  </tr>
					  <tr style="display: none" class="masterInfo">
						  <th><span class="red masterRed">*</span>硕士就读院校：</th>
						  <td>
							  <input id="byyx"  name="extInfo.maSchool" value="${extInfo.maSchool}" class="toggleView input  master validate[required]" type="text"/>
						  </td>
						  <th><span class="red masterRed">*</span>硕士入学时间：</th>
						  <td colspan="2"><input id="ssrxsj"name="extInfo.masterStartTime" value="${extInfo.masterStartTime}" class="input datepicker validate[required] "  style="width: 149px;" readonly="readonly"/></td>
					  </tr>
					  <tr style="display: none" class="masterInfo">
						  <th><span class="red masterRed">*</span>所学专业：</th>
						  <td><input id="ssbyzy"name="extInfo.maMajor" value="${extInfo.maMajor}" class="input validate[required]"/></td>
						  <th class="masterIsGrad"><span class="red masterRed">*</span>学&#12288;&#12288;位：</th>
						  <td class="masterIsGrad" style="padding-left:10px;">
							  <select name="extInfo.masterDegreeId" id="masterDegreeId" class="select masterIsGrad validate[required]" style="width: 160px;">
								  <option value="">请选择</option>
								  <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
									  <option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.masterDegreeId}">selected</c:if>>${dict.dictName}</option>
								  </c:forEach>
							  </select>
							  <input type="hidden" id="masterDegreeName" name="extInfo.masterDegreeName">
						  </td>
					  </tr>
					  <tr style="display: none" class="masterInfo masterIsGrad">
						  <th class="masterIsGrad"><span class="red masterRed">*</span>硕士学位类型：</th>
						  <td class="masterIsGrad">
							  &nbsp;<select name="extInfo.masterDegreeTypeId" class="select validate[required]" style="width: 160px;" onchange="changeDegreeType(this,'master');">
							  <option value="">请选择</option>
							  <option value="2"${extInfo.masterDegreeTypeId eq 2?'selected':'' }>科学型</option>
							  <option value="1"${extInfo.masterDegreeTypeId eq 1?'selected':'' }>专业型</option>
						  	  </select>
						  </td>
						  <th class="masterIsGrad"><span class="red masterRed">*</span>硕士毕业时间：</th>
						  <td class="masterIsGrad" colspan="2"><input id="ssbysj"name="extInfo.maDate" value="${extInfo.maDate}" class="input datepicker validate[required] "  style="width: 149px;" readonly="readonly"/></td>
					  </tr>
					  <tr>
						  <th><span class="red">*</span>是否为博士：</th>
						  <td>
							  <label>&#12288;<input name="extInfo.isDoctor" type="radio"<c:if test="${extInfo.isDoctor eq GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" class="validate[required]" onchange="checkBx(this.value,'doctor')"/>&nbsp;是&#12288;</label>
							  <label><input name="extInfo.isDoctor" type="radio" <c:if test="${extInfo.isDoctor eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" class="validate[required]" onchange="checkBx(this.value,'doctor')"/>&nbsp;否&nbsp;&nbsp;</label>
						  </td>
						  <th style="display: none" class="doctorInfo"><span class="red">*</span>状&#12288;&#12288;态：</th>
						  <td style="display: none" class="doctorInfo">
							  <label>&#12288;<input name="extInfo.doctorStatue" type="radio"<c:if test="${extInfo.doctorStatue eq '1' }">checked="checked"</c:if> value="1" class="validate[required]" onchange="checkIsStudy(this.value,'doctor')"/>&nbsp;在读&#12288;</label>
							  <label><input name="extInfo.doctorStatue" type="radio" <c:if test="${extInfo.doctorStatue eq '2' }">checked="checked"</c:if> value="2" class="validate[required]"onchange="checkIsStudy(this.value,'doctor')"/>&nbsp;已毕业&nbsp;&nbsp;</label>
						  </td>
					  </tr>
					  <tr style="display: none" class="doctorInfo">
						  <th><span class="red">*</span>博士就读院校：</th>
						  <td>
							  <input id="bsbyyx"  name="extInfo.phdSchool" value="${extInfo.phdSchool}" class="toggleView input validate[required]" type="text"/>
						  </td>
						  <th><span class="red">*</span>博士入学时间：</th>
						  <td colspan="2"><input id="bsrxsj"name="extInfo.doctorStartTime" value="${extInfo.doctorStartTime}" class="input datepicker validate[required] "  style="width: 149px;" readonly="readonly"/></td>
					  </tr>
					  <tr style="display: none" class="doctorInfo">
						  <th><span class="red">*</span>所学专业：</th>
						  <td><input id="bsbyzy"name="extInfo.phdMajor" value="${extInfo.phdMajor}" class="input validate[required]"/></td>
						  <th class="doctorIsGrad"><span class="red">*</span>学&#12288;&#12288;位：</th>
						  <td class="doctorIsGrad" style="padding-left:10px;">
							  <select name="extInfo.doctorDegreeId" id="doctorDegreeId" class="select validate[required]" style="width: 160px;">
								  <option value="">请选择</option>
								  <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
									  <option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.doctorDegreeId }">selected</c:if>>${dict.dictName}</option>
								  </c:forEach>
							  </select>
							  <input type="hidden" id="doctorDegreeName" name="extInfo.doctorDegreeName" value="">
						  </td>
					  </tr>
					  <tr style="display: none" class="doctorInfo doctorIsGrad">
						  <th class="doctorIsGrad"><span class="red doctorRed">*</span>博士学位类型：</th>
						  <td class="doctorIsGrad">
							  &nbsp;<select name="extInfo.doctorDegreeTypeId" class="select validate[required]" style="width: 160px;" onchange="changeDegreeType(this,'doctor');">
							  <option value="">请选择</option>
							  <option value="2"${extInfo.doctorDegreeTypeId eq 2?'selected':'' }>科学型</option>
							  <option value="1"${extInfo.doctorDegreeTypeId eq 1?'selected':'' }>专业型</option>
						  </select>
						  </td>
						  <th class="doctorIsGrad"><span class="red doctorRed">*</span>博士毕业时间：</th>
						  <td class="doctorIsGrad" colspan="2"><input id="bsbysj"name="extInfo.phdDate" value="${extInfo.phdDate}" class="input doctorIsGrad datepicker validate[required] "  style="width: 149px;" readonly="readonly"/></td>
					  </tr>
					  </tbody>
				  </table>
			  </div>
           <div class="div_table">
				<h4>证书及文件</h4>
		        <table border="0" cellpadding="0" cellspacing="0" class="base_info">
		        
		           <tr>
			          <th  width="20%"><font color="red">*</font>身份证图片：</th>
			          <td colspan="3">
			          	<span id="idNoUriSpan" style="display:${!empty extInfo.idNoUri?'':'none'} ">
		              	      [<a href="${sysCfgMap['upload_base_url']}/${extInfo.idNoUri}" target="_blank">查看图片</a>]&#12288;
		            	  </span>
	              		  <a id="idNoUri" href="javascript:uploadFile('idNoUri','身份证图片');" class="btn">${empty extInfo.idNoUri?'':'重新'}上传</a>&#12288;
	              		  <a id="idNoUriDel" href="javascript:delFile('idNoUri');" class="btn" style="${empty extInfo.idNoUri?'display:none':''}">删除</a>&#12288;
              			  <input type="hidden" id="idNoUriValue" name="extInfo.idNoUri" value="${extInfo.idNoUri}">
			          </td>
			       </tr> 
			       <tr>
			          <th><font color="red">*</font>最高学位毕业证编号：</th>
			          <td colspan="3">
			          	<input type="text" class="input validate[required]" placeholder="请填写毕业证编号" style="width: 300px;" name="doctor.certificateNo" value='${doctor.certificateNo}'/>
			          
			              <span id="certificateUriSpan" style="display:${!empty extInfo.certificateUri?'':'none'} ">
		              	      [<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">查看图片</a>]&#12288;
		            	  </span>
	              		  <a id="certificateUri" href="javascript:uploadFile('certificateUri','毕业证书');" class="btn">${empty extInfo.certificateUri?'':'重新'}上传扫描件</a>&#12288;
	              		  <a id="certificateUriDel" href="javascript:delFile('certificateUri');" class="btn" style="${empty extInfo.certificateUri?'display:none':''}">删除</a>&#12288;
              			  <input type="hidden" id="certificateUriValue" name="extInfo.certificateUri" value="${extInfo.certificateUri}">
			          </td>
			       </tr> 
			       <tr>
			          <th>最高学位学位证编号：</th>
			          <td colspan="3"><input type='text' placeholder="请填写学位证编号" style="width: 300px;" class="input" name="doctor.degreeNo" value='${doctor.degreeNo}'/>
			              <span id="degreeUriSpan" style="display:${!empty extInfo.degreeUri?'':'none'} ">
		              	      [<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">查看图片</a>]&#12288;
		            	  </span>
	              		  <a id="degreeUri" href="javascript:uploadFile('degreeUri','学位证书');" class="btn">${empty extInfo.degreeUri?'':'重新'}上传扫描件</a>&#12288;
	              		  <a id="degreeUriDel" href="javascript:delFile('degreeUri');" class="btn" style="${empty extInfo.degreeUri?'display:none':''}">删除</a>&#12288;
              			  <input type="hidden" id="degreeUriValue" name="extInfo.degreeUri" value="${extInfo.degreeUri}">
			          </td>
			       </tr> 
			       <tr class="yszz">
			          <th><font color="red">*</font>执业医师资格证号：</th>
			          <td colspan="3"><input type='text'  placeholder="请填写执业医师资格证书编号" style="width: 300px;" class="input " id="qualifiedNo" name='doctor.qualifiedNo' value='${doctor.qualifiedNo}'/>
			              <span id="qualifiedUriSpan" style="display:${!empty extInfo.qualifiedUri?'':'none'} ">
		              	      [<a href="${sysCfgMap['upload_base_url']}/${extInfo.qualifiedUri}" target="_blank">查看图片</a>]&#12288;
		            	  </span>
	              		  <a id="qualifiedUri" href="javascript:uploadFile('qualifiedUri','医师资格证');" class="btn">${empty extInfo.qualifiedUri?'':'重新'}上传扫描件</a>&#12288;
	              		  <a id="qualifiedUriDel" href="javascript:delFile('qualifiedUri');" class="btn" style="${empty extInfo.qualifiedUri?'display:none':''}">删除</a>&#12288;
              			  <input type="hidden" id="qualifiedUriValue" name="extInfo.qualifiedUri" value="${extInfo.qualifiedUri}">
			          </td>
			       </tr>
					<tr>
						<th>其他材料(派送证明)：</th>
						<td style="padding-left: 10px;" colspan="3">
							<span id="dispatchFileFSpan" style="display:${!empty doctor.dispatchFile?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${doctor.dispatchFile}" target="_blank">查看图片</a>]&#12288;
							</span>
							<a id="dispatchFileF" href="javascript:uploadFile('dispatchFileF','派送单位证明材料');"
							   class="btn">${empty doctor.dispatchFile?'':'重新'}上传</a>&#12288;
							<a id="dispatchFileFDel" href="javascript:delFile('dispatchFileF');" class="btn"
							   style="${empty doctor.dispatchFile?'display:none':''}">删除</a>&#12288;
							<input type="hidden" id="dispatchFileFValue" name="doctor.dispatchFile"
								   value="${doctor.dispatchFile }">
						</td>
					</tr>
			       <%--<tr class="yszz">--%>
			          <%--<th><font color="red">*</font>医师执业证书编号：</th>--%>
			          <%--<td colspan="3"><input type='text' placeholder="请填写医师执业证书编号" style="width: 300px;" class="input validate[required]" name="doctor.regNo" value='${doctor.regNo}'/>--%>
			              <%--<span id="regUriSpan" style="display:${!empty extInfo.regUri?'':'none'} ">--%>
		              	      <%--[<a href="${sysCfgMap['upload_base_url']}/${extInfo.regUri}" target="_blank">查看图片</a>]&#12288;--%>
		            	  <%--</span>--%>
	              		  <%--<a id="regUri" href="javascript:uploadFile('regUri','医师执业证');" class="btn">${empty extInfo.regUri?'':'重新'}上传扫描件</a>&#12288;--%>
	              		  <%--<a id="regUriDel" href="javascript:delFile('regUri');" class="btn" style="${empty extInfo.regUri?'display:none':''}">删除</a>&#12288;--%>
              			  <%--<input type="hidden" id="regUriValue" name="extInfo.regUri" value="${extInfo.regUri}">--%>
			          <%--</td>--%>
			       <%--</tr>--%>
			    </table>
			</div>
             
            <%--<div class="div_table" style="display: none;" id="agencyDiv">--%>
				<%--<h4>单位信息</h4>--%>
				<%--<table border="0" cellpadding="0" cellspacing="0" class="base_info">--%>
					<%--<colgroup>--%>
						<%--<col width="30%"/>--%>
						<%--<col width="70%"/>--%>
					<%--</colgroup>--%>
					<%--<tbody>--%>
					<%--<tr>--%>
						<%--<th><font color="red">*</font>工作单位：</th>--%>
						<%--<td><input type="text" name="doctor.workOrgName" class="input validate[required]" value="${doctor.workOrgName}" style="width: 325px;"/></td>--%>
					<%--</tr>--%>
					<%--<tr>--%>
						<%--<th><font color="red">*</font>医疗卫生机构：</th>--%>
						<%--<td>--%>
							<%--<select name="extInfo.medicalHeaithOrg" class='select validate[required]' style="width: 331px;margin-left: 5px;" onchange="setHospitalAtt(this.value)">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="dict">--%>
									<%--<option value="${dict.dictId}" ${extInfo.medicalHeaithOrg eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr id="hospitalAttrtr">--%>
						<%--<th><font color="red" id="hospitalAttrtrFont">*</font>医院属性：</th>--%>
						<%--<td>--%>
							<%--<select id="hospitalAttr" name="extInfo.hospitalAttr" class='select validate[required]' style="width: 331px;margin-left: 5px;">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumHospitalAttrList}" var="dict">--%>
									<%--<option value="${dict.dictId}" ${extInfo.hospitalAttr eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr id="hospitalCategorytr">--%>
						<%--<th><font color="red" id="hospitalCategorytrFont">*</font>医院类别：</th>--%>
						<%--<td>--%>
							<%--<select id="hospitalCategory" name="extInfo.hospitalCategory" class='select validate[required]' style="width: 331px;margin-left: 5px;">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumHospitalCategoryList}" var="dict">--%>
									<%--<option value="${dict.dictId}" ${extInfo.hospitalCategory eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr id="baseAttributetr">--%>
						<%--<th><font color="red" id="baseAttributetrFont">*</font>单位性质：</th>--%>
						<%--<td>--%>
							<%--<select id="baseAttribute" name="extInfo.baseAttribute" class='select validate[required]' style="width: 331px;margin-left: 5px;">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumBaseAttributeList}" var="dict">--%>
									<%--<option value="${dict.dictId}" ${extInfo.baseAttribute eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--<tr id="basicHealthOrgtr">--%>
						<%--<th><font color="red" id="basicHealthOrgtrFont">*</font>基层医疗卫生机构：</th>--%>
						<%--<td>--%>
							<%--<select id="basicHealthOrg" name="extInfo.basicHealthOrg" class='select validate[required]' style="width: 331px;margin-left: 5px;">--%>
								<%--<option></option>--%>
								<%--<c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="dict">--%>
									<%--<option value="${dict.dictId}" ${extInfo.basicHealthOrg eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<%--</tbody>--%>
				<%--</table>--%>
			<%--</div>--%>
              
          </div>
       </div>
    </div>
        
	<div id="nextPage" class="button" style="margin: 30px;">
	    <input class="btn_blue" type="button" onclick="saveSingup();" value="暂&#12288;存"/>
        <input class="btn_blue" type="button" onclick="submitSingup();" value="下一步"/>
    </div>
    </form>
</div>
<div style="display: none">
		<!-- 工作经历模板 -->
		<table id="workTemplate">
       		<tr>
				<td><input type="checkbox" name="workIds"/></td>
				<td>
					<input type='text' class='validate[required] input ' placeholder="开始-结束时间" name="extInfo.workResumeList[{index}].clinicalRoundDate" value="" style="width: 100px;"/>
				</td>
				<td>
					<input type='text' class='input' name="extInfo.workResumeList[{index}].dateLength" value="" style="width: 50px;"/>
				</td>
				<td>
					<input type='text' class='validate[required] input' name="extInfo.workResumeList[{index}].hospitalName" value="" style="width: 100px;"/>
				</td>
				<td>
					<input type='text' class='input' name="extInfo.workResumeList[{index}].hospitalLevel" value="" style="width: 80px;"/>
				</td>
				<td>
					<input type='text' class='input' name="extInfo.workResumeList[{index}].deptName" value="" style="width: 80px;"/>
				</td>
				<td>
					<input type='text' class='input' name="extInfo.workResumeList[{index}].postName" value="" style="width: 50px;"/>
				</td>
				<td>
					<input type='text' class='input' name="extInfo.workResumeList[{index}].witness" value="" style="width: 50px;"/>
				</td>
				<td>
					<input type='text' class='input' name="extInfo.workResumeList[{index}].witnessPost" value="" style="width: 80px;"/>
				</td>
				<td>
					<input type='text' class='input' name="extInfo.workResumeList[{index}].witnessPhone" value="" style="width: 80px;"/>
				</td>
			</tr>
		</table>
	</div>
</div>
