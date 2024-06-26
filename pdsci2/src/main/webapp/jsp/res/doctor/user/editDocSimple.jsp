<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
	table{ margin:10px 0;border-collapse: collapse;}
	caption,th,td{height:40px;}
	caption{line-height:40px;text-align:left;font-weight:bold; padding-left:10px; border-bottom:1px solid #ddd;color:#1461c0;margin-bottom: 10px;}
	th{text-align:right;font-weight:500;padding-right:5px; color:#333;}
	td{text-align:left; padding-left:5px; }
	[type='text']{width:150px;height: 22px;}
	select{width: 153px;height: 27px;}
	.fuck:hover {
		background: black;
	}
	.cHeight th,.cHeight td{
		height:0;
	}
</style>

<script type="text/javascript">
	/**
	 *模糊查询加载
	 */
	(function($){
		$.fn.likeSearchInit2 = function(option){
			option.clickActive = option.clickActive || null;

			var searchInput = this;
			searchInput.on("keyup focus",function(){
				$("#boxHome2").show();
				if($.trim(this.value)){
					$("#boxHome2 .item").hide();
					var items = $("#boxHome2 .item[value*='"+this.value+"']").show();//模糊显示
					if(!items){
						$("#boxHome2").hide();
					}
				}else{
					$("#boxHome2 .item").show();
				}
			});
			searchInput.on("blur",function(){
				if(!$("#boxHome2.on").length){
					$("#boxHome2").hide();
				}
			});
			$("#boxHome2").on("mouseenter mouseleave",function(){
				$(this).toggleClass("on");
			});
			$("#boxHome2 .item").click(function(){
				$("#boxHome2").hide();
				var value = $(this).attr("value");
				searchInput.val(value);
				var deptId = $(this).attr("flow");
				$("#standardDeptId").val(deptId);
				if(option.clickActive){
					option['clickActive']($(this).attr("flow"));

				}
			});
		};
	})(jQuery);

	$(function(){
		$("#standardDeptName").likeSearchInit2({

		});
		<%--<c:if test="${param.role eq 'hospital'}">--%>
			<%--$("#resDoctor").find("input,select,a").removeClass("validate[required]")--%>
		<%--</c:if>--%>
	});
	var isSch = {
			<c:forEach items="${recDocCategoryEnumList}" var="category">
				<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
				<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
					<c:set var="isSchKey" value="res_doctor_category_${category.id}_sch"/>
					"${category.id}":"${sysCfgMap[isSchKey]}",
				</c:if>
			</c:forEach>
	};

	var condition = "._${doctor.doctorCategoryId}._${doctor.trainingSpeId}";
	function saveDoc(){
		if($("#resDoctor").validationEngine("validate")){
			var idNo=$("#idNo").val();
			if(idNo!=""){
				if (!(/^[0-9a-zA-Z]{8}$/.test(idNo)) &&!(/^[0-9a-zA-Z]{9}$/.test(idNo)) && !(/^[0-9a-zA-Z]{11}$/.test(idNo)) && !(/^[0-9a-zA-Z]{15}$/.test(idNo)) && !(/^[0-9a-zA-Z]{18}$/.test(idNo))) {
					jboxTip("请输入8位或9位或11位或15位或18位的证件号（只能是数字和字母组合）");
					return false;
				}
			}
			var age = $("#age").val();
			if(age){
                if(!/^[1-9][0-9]$/.test(age)){
                    jboxTip("年龄只能输入两位数，数字形式");
                    return false;
                }
            }
			var workOrgName = $("#workOrgId option:selected").text();
			$("#workOrgName").val(workOrgName);
			$("#doctorTypeId").attr("disabled",false);
            <c:if test="${sysCfgMap['Chinese_Western'] eq GlobalConstant.Chinese}">
            var discipleStartDate=$("#discipleStartDate").val();
            var discipleEndDate=$("#discipleEndDate").val();
            if(discipleStartDate>discipleEndDate)
            {
                jboxTip("跟师结束时间小于跟师结束时间，无法保存！");
                return false;
            }
            </c:if>
            //什么什么省份
            var locationOfProvinceName = $("#locationOfProvince option:selected").text();
            $("#locationOfProvinceName").val(locationOfProvinceName);
            //婚姻状态
            var maritalStatusName = $("#maritalStatus option:selected").text();
            $("#maritalStatusName").val(maritalStatusName);
            //本科学位
            var undergraduateDegreeName = $("#undergraduateDegreeId option:selected").text();
            $("#undergraduateDegreeName").val(undergraduateDegreeName);
            //医师资格类别
            var physicianQualificationClassName = $("#physicianQualificationClass option:selected").text();
            $("#physicianQualificationClassName").val(physicianQualificationClassName);
            //执业类别
            var practicingCategoryName = $("#practicingCategoryId option:selected").text();
            $("#practicingCategoryName").val(practicingCategoryName);
            //执业范围
            var practicingScopeName = $("#practicingScopeId option:selected").text();
            $("#practicingScopeName").val(practicingScopeName);
            //硕士学位
            var masterDegreeText = $("#masterDegreeId option:selected").text();
            $("#masterDegreeName").val(masterDegreeText);
            //硕士学位类型
            var masterDegreeTypeText = $("#masterDegreeTypeId option:selected").text();
            $("#masterDegreeTypeName").val(masterDegreeTypeText);
            //人员属性
            var personnelAttributeName = $("#personnelAttributeId option:selected").text();
            $("#personnelAttributeName").val(personnelAttributeName);
            //博士学位
            var doctorDegreeName = $("#doctorDegreeId option:selected").text();
            $("#doctorDegreeName").val(doctorDegreeName);
            //博士学位类型
            var doctorDegreeTypeName = $("#doctorDegreeType option:selected").text();
            $("#doctorDegreeTypeName").val(doctorDegreeTypeName);
            //医院属性
            var hospitalAttrText = $("#hospitalAttrId option:selected").text();
            $("#hospitalAttrName").val(hospitalAttrText);
            //医院类别
            var hospitalCategoryText = $("#hospitalCategoryId option:selected").text();
            $("#hospitalCategoryName").val(hospitalCategoryText);
            //单位性质
            var baseAttributeText = $("#baseAttributeId option:selected").text();
            $("#baseAttributeName").val(baseAttributeText);
            //医疗卫生机构
            var medicalHeaithOrgText = $("#medicalHeaithOrgId option:selected").text();
            $("#medicalHeaithOrgName").val(medicalHeaithOrgText);
            //基层医疗卫生机构
            var basicHealthOrgText = $("#basicHealthOrgId option:selected").text();
            $("#basicHealthOrgName").val(basicHealthOrgText);
			getUserResumeExtName();
			getFantasticFour();
			var url = "<s:url value='/res/doc/user/saveDocSimple'/>";
			var getdata = $('#resDoctor').serialize();
			jboxPost(url, getdata, function(data) {
				if('${GlobalConstant.SAVE_SUCCESSED}'==data){
					window.parent.frames['mainIframe'].window.searchUser();
					doClose();
				}
			},null,true);
		}
	}
	function showId(radio){
		if(radio=='Y') $(".qualifiedNoTd, .qualifiedNoTh").show();
		else $(".qualifiedNoTd, .qualifiedNoTh").hide();
	}
	function showId2(radio){
		if(radio=='Y') $(".PhysicianCertificateTr").show();
		else $(".PhysicianCertificateTr").hide();
	}

	function calculation(){
		var y;
		if($("#trainingYears").val()=='1') y=1;
		else if($("#trainingYears").val()=='2') y=2;
		else if($("#trainingYears").val()=='3') y=3;
		else y=0;
		var year = parseInt($("#sessionNumber").val())+parseInt(y);
		if(year!=0&&!isNaN(year)&&year>'${doctor.graduationYear}'){
			$("#graduationYear").val(year);
			$("#graduationYearLebal").text(year);
		}
	}

	$(function(){
		$(".xltext,.xlselect").css("margin-right","0px");
		$("._rotation").hide();
		selRotation();
		<c:if test="${param.editButtonFlag eq 'no'}">
		$(":input[type='button']").hide();
        $("#close").show();
		$("input[type!='button']").attr("disabled",true);
		$("select").attr("disabled",true);
		</c:if>
		<%--if('${doctor.graduationYear}'==""||'${doctor.graduationYear}'==null){--%>
			<%--calculation();--%>
		<%--}--%>
		$("#d").append($(".isOrg"));
		if('${doctor.doctorLicenseFlag}'!='Y') $(".qualifiedNoTh, .qualifiedNoTd").hide();
		if('${doctor.doctorLicenseFlag}'=='Y') $(".qualifiedNoTh, .qualifiedNoTd").show();
		if('${doctor.practPhysicianFlag}'!='Y') $(".PhysicianCertificateTr").hide();
		if('${doctor.practPhysicianFlag}'=='Y') $(".PhysicianCertificateTr").show();

		var isCollegeDegree = '${extInfo.isCollegeDegree}';
		isCollege(isCollegeDegree);
		var isUndergraduateDegree = '${extInfo.isUndergraduateDegree}';
		checkIsUndergraduateDegree(isUndergraduateDegree);
		showId3("${extInfo.isAssistance}");
		if('${extInfo.isAssistance}'=='Y'){
			showAssistanceCode('${extInfo.isMilitary}');
		}
		showTime("${extInfo.isPassQualifyingExamination}");
		examsTime("${doctor.doctorLicenseFlag}");
		showTime2("${doctor.practPhysicianFlag}");

		if('${role}'=='hospital'){
			$("#trainingSpeId").css("background-color","#ccc");
			$("#sessionNumber").css("background-color","#ccc");
			$("#rotationFlow").css("background-color","#ccc");
			$("#inHosDate").css("background-color","#ccc");
			$("#trainingSpeId").attr("disabled",true);
			$("#sessionNumber").attr("disabled",true);
			$("#rotationFlow").attr("disabled",true);
			$("#inHosDate").attr("disabled",true);
		}
	});
	function isCollege(value){
		if(value=='Y'){
			$(".collegeDegreeContent").show();
		}else{
			$(".collegeDegreeContent").hide();
			$(".collegeDegreeContent input[type!='radio'],.collegeDegreeContent select").each(function(){
				$(this).val("");
			});
		}
	}

	function checkIsUndergraduateDegree(value){
		if(value=='Y'){
			$(".undergraduateDegreeContent").show();
		}else{
			$(".undergraduateDegreeContent").hide();
			$(".undergraduateDegreeContent input[type!='radio'],.undergraduateDegreeContent select").each(function(){
				$(this).val("");
			});
		}
	}
	function cutBirthday(idNo){
		$("[name='userBirthday']").val(idNo.substr(6,4)+"-"+idNo.substr(10,2)+"-"+idNo.substr(12,2));
	}

	function changeDocCategory(){
		selRotation();
		var doctorCategoryId = $("#doctorCategoryId").val();
		changeUserInfo(doctorCategoryId);
		showSpe(doctorCategoryId);
	}

	function selRotation(){
		$("#rotationFlow").val("");
		$("._rotation").hide();
// 		var sessionNumber = $("#sessionNumber").val();
		var doctorCategoryId = $("#doctorCategoryId").val();
		var trainingSpeId = $("#trainingSpeId").val();
		if(doctorCategoryId && trainingSpeId){
			var selector = "._"+doctorCategoryId+"._"+trainingSpeId;
			$(selector).show();
			if(condition==selector){
				$(selector+"[value='${doctor.rotationFlow}']").attr("selected","selected");
			}else{
				$(selector+":first").attr("selected","selected");
			}
		}
	}

	function showSpe(doctorCategoryId) {
		if (doctorCategoryId=="${recDocCategoryEnumGraduate.id}") {//研究生
			$(".showSpe").show();
		}else {//
			$(".showSpe").hide();
		}
	}
	function changeUserInfo(doctorCategoryId){
		$("#workInfo").hide();
		$("#work").hide();
		$(".userNameSpan").html("学员");
		$(".schoolNameSpan").html("在读");
		$(".graduateSpan").hide();
		$(".educationSpan").show();
		$(".degreeSpan").hide();
		$(".deptSpan").hide();
		$(".trainNameSpan").html("培训");
		$(".trainExtralSpan").html("培养");
		if (doctorCategoryId=="${recDocCategoryEnumDoctor.id}" ||
				doctorCategoryId=="${recDocCategoryEnumInDoctor.id}" ||
				doctorCategoryId=="${recDocCategoryEnumOutDoctor.id}" ||
				doctorCategoryId=="${recDocCategoryEnumFieldTrain.id}" ||
				doctorCategoryId=="${recDocCategoryEnumUnderGrad.id}" ||
				doctorCategoryId=="${recDocCategoryEnumSpecialist.id}" ||
				doctorCategoryId=="${recDocCategoryEnumGeneralDoctor.id}")
		{//住院医师、本院规培、外院规培、外地委培、本科生、专科医师、普通医生
			$(".userNameSpan").html("医师");
			$(".schoolNameSpan").html("毕业");
			$(".graduateSpan").show();	//增加毕业证号和毕业时间
			$(".degreeSpan").show();	//增加学位
			$("#workInfo").show();	//增加工作单位和职务
			$("#work").show();	//增加工作经历
			$(".deptSpan").hide();
			$(".${recDocCategoryEnumGraduate.id}").hide();
		} else if (doctorCategoryId=="${recDocCategoryEnumScholar.id}") {//进修生
			$(".schoolNameSpan").html("毕业");
			$(".graduateSpan").show();	//增加毕业证号和毕业时间
			$(".degreeSpan").show();	//增加学位
			$(".trainNameSpan").html("进修");
			$("#work").show();	//增加工作经历
			$(".deptSpan").hide();
			$(".${recDocCategoryEnumGraduate.id}").hide();
		} else if (doctorCategoryId=="${recDocCategoryEnumEightYear.id}") {//八年制
			$(".educationSpan").hide();	//隐藏学历
			$(".${recDocCategoryEnumGraduate.id}").hide();
		} else if (doctorCategoryId=="${recDocCategoryEnumIntern.id}") {//实习生
			$(".trainNameSpan").html("实习");
			$(".deptSpan").show();
			$(".${recDocCategoryEnumGraduate.id}").hide();
		}else if (doctorCategoryId=="${recDocCategoryEnumGraduate.id}") {//研究生
			$(".${recDocCategoryEnumGraduate.id}").show();
		}

		$("#rotationInfo").toggle("${GlobalConstant.FLAG_N}"!=isSch[doctorCategoryId]);

		if("${GlobalConstant.FLAG_N}"!=isSch[doctorCategoryId])
		{
			var value=$("#doctorTypeId").val();
				if (value == 'Company') {
					$(".departMentFlow").show();
				}else{
					$(".departMentFlow").hide();
					$("#departMentFlow").val("");
				}
		}else{
			$(".departMentFlow").hide();
			$("#departMentFlow").val("");
		}
	}

	function checkCondition(){
		if(!$("#doctorCategoryId").val()){
			jboxTip("请选择人员类型!");
			return;
		}
// 		if(!$("#sessionNumber").val()){
// 			jboxTip("请选择年级!");
// 			return;
// 		}
		if(!$("#trainingSpeId").val()){
			jboxTip("请选择专业!");
			return;
		}
	}

	$(document).ready(function(){
		changeUserInfo("${doctor.doctorCategoryId}");
		showSpe("${doctor.doctorCategoryId}");
		changeTerminat("${doctor.doctorStatusId}");
		changeDepart("${doctor.orgFlow}");
		loadEdu();
	 	loadWork();
	});

	function loadEdu(){
		jboxLoad("edu","<s:url value='/pub/resume/loadView?type=education&userFlow=${user.userFlow}&editFlag=${GlobalConstant.FLAG_N}&source=resDoc'/>");
	}

	function loadWork(){
		jboxLoad("work","<s:url value='/pub/resume/loadView?type=work&userFlow=${user.userFlow}&editFlag=${GlobalConstant.FLAG_N}&source=resDoc'/>");
	}

	function lock(userFlow){
		jboxConfirm("确认锁定该用户吗？锁定后该用户将不能登录系统！",function () {
			var url = "<s:url value='/sys/user/lock?userFlow='/>"+userFlow;
			jboxGet(url,null,function(){
				var url="javascript:activate('${user.userFlow}');";
				$("#userStatusSpan").html('${userStatusEnumLocked.name}&#12288;[<a href='+url+'>解锁</a>]');
			});
		});
	}

	function activate(userFlow){
		jboxConfirm("确认解锁该用户吗？",function () {
			var url = "<s:url value='/sys/user/activate?userFlow='/>"+userFlow;
			jboxGet(url,null,function(){
				var url="javascript:lock('${user.userFlow}');";
				$("#userStatusSpan").html('${userStatusEnumActivated.name}&#12288;[<a href='+url+'>锁定</a>]');
			});
		});
	}

	//根据医师状态决定展示终止还是结业内容
	function changeTerminat(doctorStatusId){
		if ("${resDoctorStatusEnumTerminat.id}"==doctorStatusId) {
			$(".${resDoctorStatusEnumTerminat.id}").show();
			$(".${resDoctorStatusEnumGraduation.id}").hide();
		} else if ("${resDoctorStatusEnumGraduation.id}"==doctorStatusId) {
			$(".${resDoctorStatusEnumGraduation.id}").show();
			$(".${resDoctorStatusEnumTerminat.id}").hide();
		} else {
			$(".${resDoctorStatusEnumTerminat.id}").hide();
			$(".${resDoctorStatusEnumGraduation.id}").hide();
		}
	}

	function doClose(){
		jboxCloseMessager();
	}

	function resetPasswd(userFlow){
		jboxConfirm("确认将该用户的密码重置为:${pdfn:getInitPass()} 吗？",function () {
			var url = "<s:url value='/sys/user/resetPasswd?userFlow='/>"+userFlow;
			jboxGet(url,null,function(){
				//searchUser();
			});
		});
	}
	function option(obj){
		$("#d").append($(".isOrg"));
		$("#rotationFlow").append($("#d .isOrg."+obj));
	}
	function changeDepart(orgFlow){
		orgFlow=orgFlow||'';
		var url = "<s:url value='/sch/cfg/searchDept?orgFlow='/>"+orgFlow;
		var courseArray = [];
		$("#departMentFlow").empty();
		$("#departMentFlow").append("<option value=''></option>");
		jboxGetAsync(url, null, function (data) {
			if (data) {
				for (var i = 0; i < data.length; i++) {
					var deptFlow = data[i].deptFlow;
					var deptName = data[i].deptName;
					var s="${doctor.departMentFlow}"==deptFlow;
					if(s==true) {
						$("#departMentFlow").append("<option value='" + deptFlow + "' selected='selected' >" + deptName + "</option>");
					}else{
						$("#departMentFlow").append("<option value='" + deptFlow + "' >" + deptName + "</option>");
					}
				}
			}
		}, null, false);
	}

    /**
     *模糊查询加载
     */
    (function($){
        $.fn.likeSearchInit = function(option){
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            searchInput.on("keyup focus",function(){
                $("#boxHome").show();
                if($.trim(this.value)){
                    $("#boxHome .item").hide();
                    var items = $("#boxHome .item[value*='"+this.value+"']").show();
                    if(!items){
                        $("#boxHome").hide();
                    }
                }else{
                    $("#boxHome .item").show();
                }
            });
            searchInput.on("blur",function(){
                if(!$("#boxHome.on").length){
                    $("#boxHome").hide();
                }
            });
            $("#boxHome").on("mouseenter mouseleave",function(){
                $(this).toggleClass("on");
            });
            $("#boxHome .item").click(function(){
                $("#boxHome").hide();
                var value = $(this).attr("value");
                $("#itemName").val(value);
                searchInput.val(value);
                if(option.clickActive){
                    option['clickActive']($(this).attr("flow"));
                }
            });
        };
    })(jQuery);

    $(function(){
        $("#orgSel").likeSearchInit({
             clickActive:function(flow){
                $("#discipleTeacherFlow").val(flow);
             }
        });
    });
function getFantasticFour(){
	if($(':radio[name="userResumeExt.isDoctor"]:checked').val()=='${GlobalConstant.FLAG_Y}'){
		$("#uEducationName").val("博士");
		$("#dGraduatedName").val($("#bsbyyx").val());
		$("#dSpecialized").val($("#bsbyzy").val());
		$("#dGraduationTime").val($("#bsbysj").val());
	}else if($(':radio[name="userResumeExt.isMaster"]:checked').val()=='${GlobalConstant.FLAG_Y}'){
		$("#uEducationName").val("硕士");
		$("#dGraduatedName").val($("#byyx").val());
		$("#dSpecialized").val($("#ssbyzy").val());
		$("#dGraduationTime").val($("#ssbysj").val());
	}else{
		$("#uEducationName").val($("#degree  option:selected").text());
		$("#dGraduatedName").val($("#bkbyyx").val());
		$("#dSpecialized").val($("#bkbyzy").val());
		$("#dGraduationTime").val($("#bkbysj").val());
	}
}
function getUserResumeExtName(){
	var technologyQualificationId = $("#technologyQualificationId").val();
	var technologyQualificationName = "";
	if(technologyQualificationId != ""){
		technologyQualificationName = $(	"#technologyQualificationId :selected").text();
	}
	$("#technologyQualificationName").val(technologyQualificationName);

	var qualificationMaterialId = $("#qualificationMaterialId").val();
	var qualificationMaterialName = "";
	if(qualificationMaterialId != ""){
		qualificationMaterialName = $("#qualificationMaterialId :selected").text();
	}
	$("#qualificationMaterialName").val(qualificationMaterialName);

	var practicingCategoryId = $("#practicingCategoryId").val();
	var practicingCategoryName = "";
	if(practicingCategoryId != ""){
		practicingCategoryName = $("#practicingCategoryId :selected").text();
	}
	$("#practicingCategoryName").val(practicingCategoryName);

	var practicingScopeId = $("#practicingScopeId").val();
	var practicingScopeName= "";
	if(practicingScopeId != ""){
		practicingScopeName = $("#practicingScopeId :selected").text();
	}
	$("#practicingScopeName").val(practicingScopeName);
}

//文件功能
	function uploadFile(type,typeName,changeFlag) {
		jboxOpen("<s:url value='/inx/hbres/uploadFile'/>?operType="+type+"&changeFlag=Y","上传"+typeName,450,150);
	}
	function delFile(type) {
		$("#"+type+"Del").hide();
		$("#"+type+"Span").hide();
		$("#"+type).text("上传");
		if(type != "dispatchFileF" && type != "qualifiedFileF" && type != "degreeUriFile"){//委培证明,医师资格证,最高学历证,最高学位证 非必传
			$("#"+type).addClass("validate[required]");
		}
		$("#"+type+"Value").val("");
	}
	<c:set var="isFree" value="${pdfn:isFreeGlobal()}"></c:set>
	<c:if test="${isFree || param.isFormSch}">
	$(function () {
		$("[name='orgTypeId']").change();
		$('input').attr("disabled","disabled");
		$('textarea').attr("readonly","readonly");
		$("select").attr("disabled", "disabled");
		$(":checkbox").attr("disabled", "disabled");
		$(":radio").attr("disabled", "disabled");
		$("a[id*=File]").hide();
		$("a[id*=FileDel]").hide();
	});
	</c:if>

    function setNameById(id,val) {
        var name="";
        <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
        if("${dict.dictId}"==val)
        {
            name="${dict.dictName}"
        }
        </c:forEach>
        console.log($("#"+id));
        $("#"+id).val(name);
    }
	function showTime(flag){
		if(flag == "Y"){
			$(".examinationTime").show();
//			$("input[name='doctorLicenseFlag'][value='Y']").removeAttr("disabled");
//			$("input[name='practPhysicianFlag'][value='Y']").removeAttr("disabled");
		}else{
			$("#passQualifyingExaminationTime").val("");
			$(".examinationTime").hide();
//			examsTime("N");
//			$("input[name='doctorLicenseFlag'][value='Y']").attr("disabled","disabled");
//			$("input[name='doctorLicenseFlag'][value='N']").attr("checked","checked");
//			showTime2("N");
//			$("input[name='practPhysicianFlag'][value='Y']").attr("disabled","disabled");
//			$("input[name='practPhysicianFlag'][value='N']").attr("checked","checked");
		}
	}

	function examsTime(flag){
		if(flag == "Y"){
			$(".examTime").show();
//			$("input[name='practPhysicianFlag'][value='Y']").removeAttr("disabled");
		}else{
			$(".examTime input,.examTime select").each(function(){
				$(this).val("");
			});
			$(".examTime").hide();
//			showTime2("N");
//			$("input[name='practPhysicianFlag'][value='Y']").attr("disabled","disabled");
//			$("input[name='practPhysicianFlag'][value='N']").attr("checked","checked");
		}
	}

	function showTime2(flag){
		if(flag == "Y"){
			$(".showTime2").show();
		}else{
			$(".showTime2 input,.showTime2 select").each(function(){
				$(this).val("");
			});
			$(".showTime2").hide();
		}
	}
	function showId3(obj){
		if(obj == "Y"){
			$(".isAssistance").show();
		}else{
			$(".isAssistance").hide();
			$(".isAssistance input[type!='radio'],.isAssistance select").each(function(){
				$(this).val("");
			});
		}
	}

	function showAssistanceCode(value){
		if(value == "N"){
			$(".assistanceCode").show();
		}else{
			$(".assistanceCode").hide();
			$("input[name='assistanceCode']").val("");
		}
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content">
	<div class="title1 clearfix" style="padding:0; ">
		<form id="resDoctor" style="position: relative;">
		<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
		<input type="hidden" name="userFlow" value="${user.userFlow}"/>
		<input type="hidden" name="roleFlag" value="${param.roleFlag}"/>
		<c:set var="addFlag" value="${sysCfgMap['res_org_ad_doc'] eq GlobalConstant.FLAG_Y and (param.role eq 'hospital' or param.roleFlag eq 'global')}"/>
		<table style="width:100%;">
			<caption>人员类型信息</caption>
			<colgroup>
				<col width="12%"/>
				<col width="20%"/>
				<col width="12%"/>
				<col width="20%"/>
				<col width="16%"/>
				<col width="20%"/>
			</colgroup>
			<tr class="cHeight">
				<th></th><td></td><th></th><td></td><th></th><td></td>
			</tr>
			<tr>
				<th>
					<font color="red">*</font>学员类型：
				</th>
				<td>
					<select name="doctorTypeId" class="validate[required]" id="doctorTypeId" onchange="checkDoctorType(this.value),checkDoctorType2(this.value);" <c:if test="${addFlag and not empty doctor.doctorTypeId}">disabled</c:if>>
						<option></option>
						<c:forEach items="${dictTypeEnumDoctorTypeList}" var="dict">
							<option value="${dict.dictId}" <c:if test="${doctor.doctorTypeId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
						</c:forEach>
					</select>
				</td>
				<th class="workOrgNameTh"><c:if test="${!addFlag}"><font color="red">*</font></c:if>工作单位：</th>
				<td class="workOrgNameTd">
					<select name="workOrgId" id="workOrgId" class="<c:if test="${!addFlag}">validate[required]</c:if> workOrgNameTd1">
						<option></option>
						<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
							<option value="${dict.dictId}" <c:if test="${doctor.workOrgId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName }</option>
						</c:forEach>
					</select>
					<input name="workOrgName" id="workOrgName" value="${doctor.workOrgName}" type="hidden" class="workOrgNameTd1">

					<input name="workOrgName" value="${doctor.workOrgName}" class="<c:if test="${!addFlag}">validate[required]</c:if> workOrgNameTd2" type="text">
				</td>
				<th class="prove">委培单位同意证明：</th>
				<td class="prove">
						<span id="dispatchFileFSpan" style="display:${!empty doctor.dispatchFile?'':'none'} ">
						[<a href="${sysCfgMap['upload_base_url']}/${doctor.dispatchFile}" target="_blank">查看图片</a>]
					</span>
					<a id="dispatchFileF" href="javascript:uploadFile('dispatchFileF','单位同意证明');" style="color:blue">${empty doctor.dispatchFile?'':'重新'}上传</a>
					<a id="dispatchFileFDel" href="javascript:delFile('dispatchFileF');"  style="color:blue;${empty doctor.dispatchFile?'display:none':''}">删除</a>
					<input type="hidden" id="dispatchFileFValue" name="dispatchFile" value="${doctor.dispatchFile }">
				</td>
			</tr>
			<tr class="departMentFlow" style="display:none;">
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>所在科室：</th>
				<td>
					<select class="<c:if test="${!addFlag}">validate[required]</c:if>" name="departMentFlow" id="departMentFlow"  >
						<option value="">请选择</option>
						<c:forEach items="${depts}" var="tra">
							<option value="${tra.deptFlow}" <c:if test="${doctor.departMentFlow eq tra.deptFlow}">selected="selected"</c:if>>${tra.deptName}</option>
						</c:forEach>
					</select>
				</td>
				<th>
				</th>
				<td>
				</td>
				<th>
				</th>
				<td>
				</td>
			</tr>
			<tr>
				<th class="medicalInstitutionTh"><c:if test="${!addFlag}"><font color="red">*</font></c:if>医疗卫生机构：</th>
				<td class="medicalInstitutionTd">
					<select class="<c:if test="${!addFlag}">validate[required]</c:if>" name="medicalHeaithOrgId" id="medicalHeaithOrgId" onchange="changeMedicalThings(this.value);" >
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
							<option value="${tra.dictId}" <c:if test="${extInfo.medicalHeaithOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="medicalHeaithOrgName" name="medicalHeaithOrgName" value="${extInfo.medicalHeaithOrgName}">
				</td>
				<th class="basicmedicalInstitutionTypeTh">
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>基层医疗卫生机构类型：
				</th>
				<td class="basicmedicalInstitutionTypeTd">
					<select class="<c:if test="${!addFlag}">validate[required]</c:if>" id="basicHealthOrgId" name="basicHealthOrgId" >
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
							<option value="${tra.dictId}" <c:if test="${extInfo.basicHealthOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="basicHealthOrgName" name="basicHealthOrgName" value="${extInfo.basicHealthOrgName}">
				</td>
			</tr>
			<tr class="hospitalTr">
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>医院属性：</th>
				<td>
					<select class="<c:if test="${!addFlag}">validate[required]</c:if>" id="hospitalAttrId" name="hospitalAttrId">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
							<option value="${tra.dictId}" <c:if test="${extInfo.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="hospitalAttrName" name="hospitalAttrName" value="${extInfo.hospitalAttrName}">
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>医院类别：</th>
				<td>
					<select class="<c:if test="${!addFlag}">validate[required]</c:if>" id="hospitalCategoryId" name="hospitalCategoryId">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
							<option value="${tra.dictId}" <c:if test="${extInfo.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="hospitalCategoryName" name="hospitalCategoryName" value="${extInfo.hospitalCategoryName}">
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>单位性质：</th>
				<td>
					<select class="<c:if test="${!addFlag}">validate[required]</c:if>" id="baseAttributeId" name="baseAttributeId">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
							<option value="${tra.dictId}" <c:if test="${extInfo.baseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="baseAttributeName" name="baseAttributeName" value="${extInfo.baseAttributeName}">
				</td>
			</tr>
		</table>
		<table style="width:100%;">
			<caption>个人信息</caption>
			<colgroup>
				<col width="12%"/>
				<col width="20%"/>
				<col width="12%"/>
				<col width="20%"/>
				<col width="16%"/>
				<col width="20%"/>
			<tr>
				<th>
					<font color="red">*</font>培训类别：</th>
				<td>
					<select name="doctorCategoryId" class="validate[required]" <c:if test="${addFlag and not empty doctor.doctorCategoryId}">disabled</c:if> id="doctorCategoryId" onchange="changeDocCategory();">
						<option></option>
						<c:forEach items="${recDocCategoryEnumList}" var="category">
							<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id }"/>
							<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y }">
								<option value="${category.id}" ${doctor.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</td>
				<th>
					<c:if test="${empty user}">
						<font color="red">*</font>
					</c:if>用户名：</th>
				<td>
					<c:if test="${empty user}">
						<input type="text" name="userCode" value="${user.userCode}" class="validate[required<c:if test="${!(sysCfgMap['sys_user_check_user_code'] eq GlobalConstant.FLAG_N)}">custom[userCode]</c:if>]">
						<%--class="<c:if test="${!(sysCfgMap['sys_user_check_user_code'] eq GlobalConstant.FLAG_N)}">validate[required,custom[userCode]]</c:if>"/>--%>

					</c:if>
					<c:if test="${!empty user}">
						${user.userCode}
						<input type="hidden" name="userCode" value="${user.userCode}"/>
					</c:if>
				</td>
				<th><font color="red">*</font>真实姓名：</th>
				<td>
					<input type="text" name="userName" value="${user.userName}" class="validate[required]"/>
				</td>
			</tr>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>性别：</th>
				<td>
					<label><input type="radio" name="sexId" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${user.sexId eq userSexEnumMan.id}">checked="checked"</c:if>  value="${userSexEnumMan.id}" />男</label>&#12288;
					<label><input type="radio" name="sexId" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${user.sexId eq userSexEnumWoman.id}">checked="checked"</c:if> value="${userSexEnumWoman.id}" />女</label>

				</td>
				<th><c:if test="${!addFlag}">
					<font color="red">*</font>
				</c:if>年龄：</th>
				<td>
					<input id="age" type="text" name="age" value="${extInfo.age}" class="<c:if test="${!addFlag}">validate[required]</c:if>"/>
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>证件类型：</th>
				<td>
					<select name="cretTypeId" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<c:forEach items="${certificateTypeEnumList}" var="certType">
							<option value="${certType.id}"
									<c:if test="${user.cretTypeId eq certType.id}">selected</c:if>>${certType.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>证件号码：</th>
				<td>
					<input type="text" name="idNo" id="idNo" value="${user.idNo}" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
				<script>
					function changeNationName (){
						var nationName=$("#nationId").find("option:selected").text()
						$("#nationName").val(nationName);
					}
				</script>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>民族：</th>
				<td>
					<input type="hidden" id="nationName" name="nationName" value="${user.nationName}">
					<select id="nationId" name="nationId" class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="changeNationName();">
						<option/>
						<c:forEach items="${userNationEnumList}" var="nation">
							<option value="${nation.id}" ${user.nationId eq nation.id?'selected':''}>${nation.name}</option>
						</c:forEach>
					</select>
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>人员属性：</th>
				<td>
					<select id="personnelAttributeId" name="personnelAttributeId" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option value="">请选择</option>
						<c:forEach items="${personnelAttributeEnumList}" var="attr">
							<option value="${attr.id}" ${extInfo.personnelAttributeId eq attr.id?'selected':''}>${attr.name}</option>
						</c:forEach>
					</select>
					<input id="personnelAttributeName" name="personnelAttributeName" value="${extInfo.personnelAttributeName}" type="hidden">
				</td>
			</tr>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>手机：</th>
				<td>
					<input type="text" name="userPhone" value="${user.userPhone}" class="validate[<c:if test="${!addFlag}">required,</c:if>custom[mobile]]"/>
				</td>
				<th ><c:if test="${!addFlag}"><font color="red">*</font></c:if>邮箱：</th>
				<td>
					<input type="text" name="userEmail" value="${user.userEmail}" class="validate[<c:if test="${!addFlag}">required,</c:if>custom[email]]"/>
				</td>
				<th >
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>出生日期：</th>
				<td>
					<input name="userBirthday" value="${user.userBirthday}" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="validate[<c:if test='${!addFlag}'>required</c:if>]">
				</td>
			</tr>
			<tr>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>婚姻状况：
					<input type="hidden" name="maritalStatusName" id="maritalStatusName" value="${extInfo.maritalStatusName}">
				</th>
				<td>
					<select name="maritalStatus" id="maritalStatus" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option value="">请选择</option>
						<option value="1" ${extInfo.maritalStatus eq 1?"selected":"style=''"}>未婚</option>
						<option value="2" ${extInfo.maritalStatus eq 2?"selected":"style=''"}>已婚</option>
						<option value="3" ${extInfo.maritalStatus eq 3?"selected":"style=''"}>初婚</option>
						<option value="4" ${extInfo.maritalStatus eq 4?"selected":"style=''"}>再婚</option>
						<option value="5" ${extInfo.maritalStatus eq 5?"selected":"style=''"}>复婚</option>
						<option value="6" ${extInfo.maritalStatus eq 6?"selected":"style=''"}>丧偶</option>
						<option value="7" ${extInfo.maritalStatus eq 7?"selected":"style=''"}>离婚</option>
					</select>
				</td>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>QQ号码：
				</th>
				<td>
					<input type="text" name="userQq" value="${user.userQq}" class="<c:if test="${!addFlag}">validate[required]</c:if>" />
				</td>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>计算机能力：
				</th>
				<td>
					<input type="text" name="computerSkills" value="${doctor.computerSkills}" class="<c:if test="${!addFlag}">validate[required]</c:if>"/>
				</td>
			</tr>
			<tr>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>外语能力：
				</th>
				<td>
					<input type="text" name="foreignSkills" value="${doctor.foreignSkills}" class="<c:if test="${!addFlag}">validate[required]</c:if>"/>
				</td>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>通讯地址：
				</th>
				<td>
					<input type="text" name="userAddress" class="validate[<c:if test="${!addFlag}">required,</c:if>maxSize[30]]" value="${user.userAddress}"/>
				</td>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>户口所在地省行政区划：
					<input type="hidden" name="locationOfProvinceName" id="locationOfProvinceName" value="${extInfo.locationOfProvinceName}">
				</th>
				<td>
					<select name="locationOfProvince" id="locationOfProvince" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option value="">请选择</option>
						<option value="1" ${extInfo.locationOfProvince eq 1?"selected":"style=''"}>北京</option>
						<option value="2" ${extInfo.locationOfProvince eq 2?"selected":"style=''"}>天津</option>
						<option value="3" ${extInfo.locationOfProvince eq 3?"selected":"style=''"}>河北</option>
						<option value="4" ${extInfo.locationOfProvince eq 4?"selected":"style=''"}>山西</option>
						<option value="5" ${extInfo.locationOfProvince eq 5?"selected":"style=''"}>内蒙古</option>
						<option value="6" ${extInfo.locationOfProvince eq 6?"selected":"style=''"}>辽宁</option>
						<option value="7" ${extInfo.locationOfProvince eq 7?"selected":"style=''"}>吉林</option>
						<option value="8" ${extInfo.locationOfProvince eq 8?"selected":"style=''"}>黑龙江</option>
						<option value="9" ${extInfo.locationOfProvince eq 9?"selected":"style=''"}>上海</option>
						<option value="10" ${extInfo.locationOfProvince eq 10?"selected":"style=''"}>江苏</option>
						<option value="11" ${extInfo.locationOfProvince eq 11?"selected":"style=''"}>浙江</option>
						<option value="12" ${extInfo.locationOfProvince eq 12?"selected":"style=''"}>安徽</option>
						<option value="13" ${extInfo.locationOfProvince eq 13?"selected":"style=''"}>福建</option>
						<option value="14" ${extInfo.locationOfProvince eq 14?"selected":"style=''"}>江西</option>
						<option value="15" ${extInfo.locationOfProvince eq 15?"selected":"style=''"}>山东</option>
						<option value="16" ${extInfo.locationOfProvince eq 16?"selected":"style=''"}>河南</option>
						<option value="17" ${extInfo.locationOfProvince eq 17?"selected":"style=''"}>湖北</option>
						<option value="18" ${extInfo.locationOfProvince eq 18?"selected":"style=''"}>湖南</option>
						<option value="19" ${extInfo.locationOfProvince eq 19?"selected":"style=''"}>广东</option>
						<option value="20" ${extInfo.locationOfProvince eq 20?"selected":"style=''"}>广西</option>
						<option value="21" ${extInfo.locationOfProvince eq 21?"selected":"style=''"}>海南</option>
						<option value="22" ${extInfo.locationOfProvince eq 22?"selected":"style=''"}>重庆</option>
						<option value="23" ${extInfo.locationOfProvince eq 23?"selected":"style=''"}>四川</option>
						<option value="24" ${extInfo.locationOfProvince eq 24?"selected":"style=''"}>贵州</option>
						<option value="25" ${extInfo.locationOfProvince eq 25?"selected":"style=''"}>云南</option>
						<option value="26" ${extInfo.locationOfProvince eq 26?"selected":"style=''"}>西藏</option>
						<option value="27" ${extInfo.locationOfProvince eq 27?"selected":"style=''"}>陕西</option>
						<option value="28" ${extInfo.locationOfProvince eq 28?"selected":"style=''"}>甘肃</option>
						<option value="29" ${extInfo.locationOfProvince eq 29?"selected":"style=''"}>青海</option>
						<option value="30" ${extInfo.locationOfProvince eq 30?"selected":"style=''"}>宁夏</option>
						<option value="31" ${extInfo.locationOfProvince eq 31?"selected":"style=''"}>新疆</option>
						<option value="32" ${extInfo.locationOfProvince eq 32?"selected":"style=''"}>兵团</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>紧急联系人：
				</th>
				<td>
					<input type="text" name="emergencyName" value="${doctor.emergencyName}" class="<c:if test="${!addFlag}">validate[required]</c:if>"/>
				</td>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>紧急联系方式：
				</th>
				<td>
					<input type="text" name="emergencyPhone" value="${doctor.emergencyPhone}" class="<c:if test="${!addFlag}">validate[required]</c:if>"/>
				</td>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>与本人关系：
				</th>
				<td>
					<input type="text" name="emergencyRelation" value="${doctor.emergencyRelation}" class="<c:if test="${!addFlag}">validate[required]</c:if>"/>
				</td>
			</tr>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>证件照：</th>
				<td>
				  	<span id="doctorHeadImgFileSpan" style="display:${!empty doctor.doctorHeadImg?'':'none'} ">
					[<a href="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" target="_blank">查看图片</a>]
				  	</span>
					<a id="doctorHeadImgFile" href="javascript:uploadFile('doctorHeadImgFile','证件照');"style="color: blue"
					   title="照片要求：1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立；2、该照片用于结业证书，请慎重认真上传！"
					   class="${(empty user.userHeadImg && !addFlag)?'validate[required]':''}" >${empty user.userHeadImg?'':'重新'}上传</a>
					<a id="doctorHeadImgFileDel" href="javascript:delFile('doctorHeadImgFile');" style="color: blue;${empty doctor.doctorHeadImg?'display:none':''}">删除</a>
					<input type="hidden" id="doctorHeadImgFileValue" name="userHeadImg" value="${user.userHeadImg}">
				</td>
			</tr>
		</table>
		<table style="width:100%;">
			<caption>教育情况</caption>
			<colgroup>
				<col width="12%"/>
				<col width="20%"/>
				<col width="12%"/>
				<col width="20%"/>
				<col width="16%"/>
				<col width="20%"/>
			</colgroup>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>是否为应届生：</th>
				<td>
					<label><input type="radio" name="isYearGraduate" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${doctor.isYearGraduate eq 'Y'}">checked="checked"</c:if>  value="Y" />是</label>&#12288;
					<label><input type="radio" name="isYearGraduate" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${doctor.isYearGraduate eq 'N'}">checked="checked"</c:if> value="N" />否</label>

				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>是否全科定向&#12288;<br>学员：</th>
				<td>
					<label><input type="radio" name="isGeneralOrderOrientationTrainee" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'Y'}">checked="checked"</c:if>  value="Y" />是</label>&#12288;
					<label><input type="radio" name="isGeneralOrderOrientationTrainee" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${extInfo.isGeneralOrderOrientationTrainee eq 'N'}">checked="checked"</c:if> value="N" />否</label>
				</td>
			</tr>
			<tr>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>是否为专科：
				</th>
				<td>
					<label><input type="radio" name="isCollegeDegree" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${extInfo.isCollegeDegree eq 'Y'}">checked="checked"</c:if> value="Y" onchange="isCollege(this.value)"/>是</label>&#12288;
					<label><input type="radio" name="isCollegeDegree" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${extInfo.isCollegeDegree eq 'N'}">checked="checked"</c:if> value="N" onchange="isCollege(this.value)"/>否</label>
				</td>
				<th class="collegeDegreeContent">
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>专科毕业院校：
				</th>
				<td class="collegeDegreeContent">
					<input name="collegeName" value="${extInfo.collegeName}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
				<th class="collegeDegreeContent">
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>专科毕业时间：
				</th>
				<td class="collegeDegreeContent">
					<input name="collegeGraduationTime" value="${extInfo.collegeGraduationTime}" type="text" readonly="readonly" class="<c:if test="${!addFlag}">validate[required]</c:if>" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
				</td>
			</tr>
			<tr class="collegeDegreeContent">
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>专科毕业专业：
				</th>
				<td>
					<input name="collegeGraduationSpe" value="${extInfo.collegeGraduationSpe}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>专科学位：
				</th>
				<td>
					<input name="collegeDegree" value="${extInfo.collegeDegree}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
			</tr>
			<tr>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>是否为本科：
				</th>
				<td>
					<label><input type="radio" name="isUndergraduateDegree" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${extInfo.isUndergraduateDegree eq 'Y'}">checked="checked"</c:if> value="Y" onchange="checkIsUndergraduateDegree(this.value)"/>是</label>&#12288;
					<label><input type="radio" name="isUndergraduateDegree" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${extInfo.isUndergraduateDegree eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsUndergraduateDegree(this.value)"/>否</label>
				</td>
				<th class="undergraduateDegreeContent">
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>本科毕业院校：
				</th>
				<td class="undergraduateDegreeContent">
					<input name="graduatedName" value="${extInfo.graduatedName}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
				<th class="undergraduateDegreeContent">
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>本科毕业时间：
				</th>
				<td class="undergraduateDegreeContent">
					<input name="graduationTime" value="${extInfo.graduationTime}" type="text" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
			<tr class="undergraduateDegreeContent">
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>本科毕业专业：
				</th>
				<td>
					<%--<input id="zbkbySpe" name="zbkbySpe" value="${extInfo.zbkbySpe}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>">--%>
					<select name="zbkbySpe" class="validate[required]">
						<option value="">请选择</option>
						<option ${extInfo.zbkbySpe eq '临床医学'?'selected':''}>临床医学</option>
						<option ${extInfo.zbkbySpe eq '口腔医学'?'selected':''}>口腔医学</option>
						<option ${extInfo.zbkbySpe eq '医学影像学'?'selected':''}>医学影像学</option>
						<option ${extInfo.zbkbySpe eq '麻醉学'?'selected':''}>麻醉学</option>
						<option ${extInfo.zbkbySpe eq '其他'?'selected':''}>其他</option>
					</select>
				</td>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>本科学位：
					<input type="hidden" name="undergraduateDegreeName" id="undergraduateDegreeName" value="${extInfo.undergraduateDegreeName}">
				</th>
				<td>
					<select name="undergraduateDegreeId" id="undergraduateDegreeId" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
							<option value="${dict.dictId}" <c:if test="${dict.dictId eq extInfo.undergraduateDegreeId}">selected</c:if>>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<script>
				$(function(){
					var isMaster = '${extInfo.isMaster}';
					checkIsMaster(isMaster);
					var isDoctor = '${extInfo.isDoctor}';
					checkIsDoctor(isDoctor);
				})
				function checkIsMaster(value){
					if(value=='Y'){
						$(".masterTh1").show();
						$(".masterTh2").show();
						$(".masterTd1").show();
						$(".masterTd2").show();
						$(".masterTr").show();
					}else{
						$(".masterTh1").hide();
						$(".masterTh2").hide();
						$(".masterTd1").hide();
						$(".masterTd2").hide();
						$(".masterTr").hide();
					}
				}
				function checkIsDoctor(value){
					if(value=='Y'){
						$(".doctorTh1").show();
						$(".doctorTh2").show();
						$(".doctorTd1").show();
						$(".doctorTd2").show();
						$(".doctorTr").show();
					}else{
						$(".doctorTh1").hide();
						$(".doctorTh2").hide();
						$(".doctorTd1").hide();
						$(".doctorTd2").hide();
						$(".doctorTr").hide();
					}
				}
			</script>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>是否为硕士：</th>
				<td>
					<label><input type="radio" name="isMaster" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${extInfo.isMaster eq 'Y'}">checked="checked"</c:if> value="Y" onchange="checkIsMaster(this.value)"/>是</label>&#12288;
					<label><input type="radio" name="isMaster" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${extInfo.isMaster eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsMaster(this.value)"/>否</label>
				</td>
				<th class="masterTh1"><c:if test="${!addFlag}"><font color="red">*</font></c:if>硕士毕业院校：</th>
				<td class="masterTd1">
					<input name="masterGraSchoolName" value="${extInfo.masterGraSchoolName}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
				<th class="masterTh2"><c:if test="${!addFlag}"><font color="red">*</font></c:if>硕士毕业时间：</th>
				<td class="masterTd2">
					<input name="masterGraTime" value="${extInfo.masterGraTime}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
				</td>
			</tr>
			<tr class="masterTr">
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>硕士毕业专业：</th>
				<td>
					<input name="masterMajor" value="${extInfo.masterMajor}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>硕士学位：</th>
				<td>
					<input type="hidden" id="masterDegreeName" name="masterDegreeName" value="${extInfo.masterDegreeName}">
					<select id="masterDegreeId" name="masterDegreeId" class="select <c:if test="${!addFlag}">validate[required]</c:if> master">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
							<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.masterDegreeId}">selected</c:if>>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>硕士学位类型：</th>
				<td>
					<select name="masterDegreeTypeId" id="masterDegreeTypeId" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option value="1"
								<c:if test="${extInfo.masterDegreeTypeId eq '1'}">selected</c:if>
						>专业型</option>
						<option  value="2"
								 <c:if test="${extInfo.masterDegreeTypeId eq '2'}">selected</c:if>
						>科学型</option>
					</select>
					<input type="hidden" id="masterDegreeTypeName" name="masterDegreeTypeName" value="${extInfo.masterDegreeTypeName}">
				</td>
			</tr>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>是否为博士：</th>
				<td>
					<label><input type="radio" name="isDoctor" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${extInfo.isDoctor eq 'Y'}">checked="checked"</c:if>  value="Y" onchange="checkIsDoctor(this.value)"/>是</label>&#12288;
					<label><input type="radio" name="isDoctor" class="<c:if test="${!addFlag}">validate[required]</c:if>" <c:if test="${extInfo.isDoctor eq 'N'}">checked="checked"</c:if> value="N" onchange="checkIsDoctor(this.value)"/>否</label>

				</td>
				<th class="doctorTh1"><c:if test="${!addFlag}"><font color="red">*</font></c:if>博士毕业院校：</th>
				<td class="doctorTd1">
					<input name="doctorGraSchoolName" value="${extInfo.doctorGraSchoolName}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
				<th class="doctorTh2"><c:if test="${!addFlag}"><font color="red">*</font></c:if>博士毕业时间：</th>
				<td class="doctorTd2">
					<input name="doctorGraTime" value="${extInfo.doctorGraTime}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
				</td>
			</tr>
			<tr class="doctorTr">
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>博士毕业专业：</th>
				<td>
					<input name="doctorMajor" value="${extInfo.doctorMajor}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>博士学位：</th>
				<td>
					<select name="doctorDegreeId" id="doctorDegreeId" class="<c:if test="${!addFlag}">validate[required]</c:if>" >
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
							<option value="${dict.dictId}" <c:if test="${ dict.dictId eq extInfo.doctorDegreeId }">selected</c:if>>${dict.dictName}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="doctorDegreeName" name="doctorDegreeName" value="${extInfo.doctorDegreeName}">
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>博士学位类型：</th>
				<td>
					<select name="doctorDegreeType" id="doctorDegreeType" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option value="1"
								<c:if test="${extInfo.doctorDegreeTypeId eq '1'}">selected</c:if>
						>专业型</option>
						<option value="2"
								<c:if test="${extInfo.doctorDegreeTypeId eq '2'}">selected</c:if>
						>科学型</option>
					</select>
					<input type="hidden" id="doctorDegreeTypeName" name="doctorDegreeTypeName" value="${extInfo.doctorDegreeTypeName}">
				</td>
			</tr>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>最高学历：</th>
				<td>
					<input type="hidden" id="educationName" name="educationName" value="${user.educationName}"/>
					<select id="educationId" name="educationId" class="<c:if test="${!addFlag}">validate[required]</c:if> " onchange="changeEducation();">
						<option value="">请选择</option>
						<c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
							<option value="${dict.dictId }" <c:if test="${user.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
						</c:forEach>
					</select>
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>最高学位：</th>
				<td>
					<input id="degreeName" name="degreeName" value="${user.degreeName}" type="hidden">
					<select id="degree" name="degreeId" class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="setNameById('degreeName',this.value)">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
							<option value="${dict.dictId}" <c:if test="${ dict.dictId eq user.degreeId }">selected</c:if>>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>最高学历毕业专业：</th>
				<td>
					<select name="specialized" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option></option>
						<c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict">
							<option value="${dict.dictId}" ${doctor.specialized eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>最高毕业证书：</th>
				<td>
					<span id="certificateUriFileSpan" style="display:${!empty extInfo.certificateUri?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${extInfo.certificateUri}" target="_blank">查看图片</a>]
	            </span>
					<a id="certificateUriFile" href="javascript:uploadFile('certificateUriFile','毕业证书');" style="color: blue" class="${(empty extInfo.certificateUri && !addFlag)?'validate[required]':''}">${empty extInfo.certificateUri?'':'重新'}上传</a>
					<a id="certificateUriFileDel" href="javascript:delFile('certificateUriFile');" class="" style="color: blue;${empty extInfo.certificateUri?'display:none':''}">删除</a>
					<input type="hidden" id="certificateUriFileValue" name="certificateUri" value="${extInfo.certificateUri }">
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>最高毕业证书&emsp;<br/>编号：</th>
				<td>
					<input name="certificateCode" value="${extInfo.certificateCode}" type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
				<th>最高学位证书：</th>
				<td>
					<span id="degreeUriFileSpan" style="display:${!empty extInfo.degreeUri?'':'none'} ">
	              	[<a href="${sysCfgMap['upload_base_url']}/${extInfo.degreeUri}" target="_blank">查看图片</a>]
	            	</span>
					<a id="degreeUriFile" href="javascript:uploadFile('degreeUriFile','学位证书');" style="color: blue">${empty extInfo.degreeUri?'':'重新'}上传</a>
					<a id="degreeUriFileDel" href="javascript:delFile('degreeUriFile');" class="" style="color: blue;${empty extInfo.degreeUri?'display:none':''}">删除</a>
					<input type="hidden" id="degreeUriFileValue" name="degreeUri" value="${extInfo.degreeUri }">
				</td>
			</tr>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>最高学位证书&emsp;<br/>编号：</th>
				<td>
					<input type="text" name="degreeCode" value="${extInfo.degreeCode}" class="<c:if test="${!addFlag}">validate[required]</c:if>" >
				</td>
			</tr>
		</table>
		<table style="width:100%;">
			<caption>医师资格信息</caption>
			<colgroup>
				<col width="12%"/>
				<col width="20%"/>
				<col width="12%"/>
				<col width="20%"/>
				<col width="16%"/>
				<col width="20%"/>
			</colgroup>
			<tr class="cHeight">
				<th></th><td></td><th></th><td></td><th></th><td></td>
			</tr>
			<tr>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>是否通过医师资&emsp;<br>格考试：</th>
				<td>
					<label><input type="radio" name="isPassQualifyingExamination" <c:if test="${extInfo.isPassQualifyingExamination eq 'Y'}">checked="checked"</c:if> value="Y"  class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="showTime('Y')" />是</label>&#12288;
					<label><input type="radio" name="isPassQualifyingExamination" <c:if test="${extInfo.isPassQualifyingExamination eq 'N'}">checked="checked"</c:if> value="N"  class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="showTime('N')" />否</label>
				</td>
				<th class="examinationTime">
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>通过医师资格&emsp;<br>考试时间：
				</th>
				<td class="examinationTime" style="display: none">
					<input name="passQualifyingExaminationTime" value="${extInfo.passQualifyingExaminationTime}" type="text"
						   class="<c:if test="${!addFlag}">validate[required]</c:if>" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
				</td>
			</tr>
			<tr>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>是否获得医师&emsp;<br>资格证书：</th>
				<td>
					<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'Y'}">checked="checked"</c:if> value="Y"  class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="examsTime('Y');"/>是</label>&#12288;
					<label><input type="radio" name="doctorLicenseFlag" <c:if test="${doctor.doctorLicenseFlag eq 'N'}">checked="checked"</c:if> value="N"  class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="examsTime('N');"/>否</label>
				</td>
				<th style="display: none" class="examTime">
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>取得医师资格&emsp;<br>证书时间：
				</th>
				<td style="display: none" class="examTime">
					<input id="haveQualificationCertificateTime" name="haveQualificationCertificateTime" value="${extInfo.haveQualificationCertificateTime}"
						   type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
				</td>
				<th style="display: none" class="examTime">
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>医师资格级别：
				</th>
				<td style="display: none" class="examTime">
					<select name="physicianQualificationLevel" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option value="">请选择</option>
						<option value="zyys" ${extInfo.physicianQualificationLevel eq 'zyys'?'selected':''}>执业医师</option>
						<option value="zyzlys" ${extInfo.physicianQualificationLevel eq 'zyzlys'?'selected':''}>执业助理医师</option>
					</select>
				</td>
			</tr>
			<tr style="display: none" class="examTime">
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>医师资格类别：
					<input type="hidden" name="physicianQualificationClassName" id="physicianQualificationClassName" value="${extInfo.physicianQualificationClassName}">
				</th>
				<td>
					<select name="physicianQualificationClass" id="physicianQualificationClass" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option value="">请选择</option>
						<option value="lc" ${extInfo.physicianQualificationClass eq 'lc'?'selected':''}>临床</option>
						<option value="kq" ${extInfo.physicianQualificationClass eq 'kq'?'selected':''}>口腔</option>
						<option value="ggws" ${extInfo.physicianQualificationClass eq 'ggws'?'selected':''}>公共卫生</option>
						<option value="zy" ${extInfo.physicianQualificationClass eq 'zy'?'selected':''}>中医</option>
					</select>
				</td>
				<th>
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>医师资格证书&emsp;<br>编码：
				</th>
				<td>
					<input type="text" name="doctorLicenseNo" value="${doctor.doctorLicenseNo}" class="<c:if test="${!addFlag}">validate[custom[integer],required]</c:if>">
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>医师资格证上传：</th>
				<td>
				<span id="qualifiedFileFileSpan" style="display:${!empty doctor.qualifiedFile?'':'none'} ">
				[<a href="${sysCfgMap['upload_base_url']}/${doctor.qualifiedFile}" target="_blank">查看图片</a>]
				</span>
					<a id="qualifiedFileFile" href="javascript:uploadFile('qualifiedFileFile','执业医师资格证书');" style="color: blue"class="${(empty doctor.qualifiedFile && !addFlag)?'validate[required]':''}">${empty doctor.qualifiedFile?'':'重新'}上传</a>
					<a id="qualifiedFileFileDel" href="javascript:delFile('qualifiedFileFile');" class="" style="color: blue;${empty doctor.qualifiedFile?'display:none':''}">删除</a>
					<input type="hidden" id="qualifiedFileFileValue" name="qualifiedFile" value="${doctor.qualifiedFile}">
				</td>
			</tr>
			<script>
				$(function(){
					checkDoctorType('${doctor.doctorTypeId}');
					$(".workOrgNameTh,.workOrgNameTd").hide();
					checkDoctorType2('${doctor.doctorTypeId}');
				})
				function checkDoctorType(value) {
					if (value == 'Company'||value == 'CompanyEntrust'||value == 'ExternalEntrust') {
						$(".workOrgNameTh,.workOrgNameTd,.medicalInstitutionTh,.medicalInstitutionTd,.prove").show();
						changeMedicalThings($("#medicalHeaithOrgId option:selected").val());
					}else{
						$(".workOrgNameTh,.workOrgNameTd,.medicalInstitutionTh,.medicalInstitutionTd,.prove").hide();
						changeMedicalThings("");
					}

					var doctorCategoryId = $("#doctorCategoryId").val();
					if("${GlobalConstant.FLAG_N}"!=isSch[doctorCategoryId])
					{
						if (value == 'Company') {
							$(".departMentFlow").show();
						}else{
							$(".departMentFlow").hide();
							$("#departMentFlow").val("");
						}
					}else{
						$(".departMentFlow").hide();
						$("#departMentFlow").val("");
					}
				}
				function checkDoctorType2(value){
					if(value=='Graduate') {
						$(".workOrgNameTh").html("<font color='red'>*</font>在读院校：");
						$(".workOrgNameTh,.workOrgNameTd").show();
					}
					else if(value=='Company'||value == 'CompanyEntrust'||value == 'ExternalEntrust') {
						$(".workOrgNameTh").html("<font color='red'>*</font>工作单位：");
						$(".workOrgNameTh,.workOrgNameTd").show();
					}else{
						$(".workOrgNameTh,.workOrgNameTd").hide();
					}
				}
				function changeMedicalThings(name){
					if(name=='3'){
						$(".hospitalTr").hide();
						$(".basicmedicalInstitutionTypeTh").show();
						$(".basicmedicalInstitutionTypeTd").show();
					}else if(name=='1'){
						$(".hospitalTr").show();
						$(".basicmedicalInstitutionTypeTh").hide();
						$(".basicmedicalInstitutionTypeTd").hide();
					}else{
						$(".hospitalTr").hide();
						$(".basicmedicalInstitutionTypeTh").hide();
						$(".basicmedicalInstitutionTypeTd").hide();
					}
				}
			</script>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>是否获得医师&emsp;<br>执业证书：</th>
				<td>
					<label><input type="radio" name="practPhysicianFlag" <c:if test="${doctor.practPhysicianFlag eq 'Y'}">checked="checked"</c:if>  value="Y"  class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="showTime2('Y')"/>是</label>&#12288;
					<label><input type="radio" name="practPhysicianFlag" <c:if test="${doctor.practPhysicianFlag eq 'N'}">checked="checked"</c:if> value="N"  class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="showTime2('N')"/>否</label>
				</td>
				<th class="showTime2" style="display: none;">
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>取得医师执业&emsp;<br>证书时间：
				</th>

				<td class="showTime2" style="display: none;">
					<input id="havePracticingCategoryTime" name="havePracticingCategoryTime" value="${extInfo.havePracticingCategoryTime}"
						   type="text" class="<c:if test="${!addFlag}">validate[required]</c:if>" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
				</td>
				<th class="showTime2" style="display: none;">
					<c:if test="${!addFlag}"><font color="red">*</font></c:if>医师执业证书&emsp;<br>编码：
				</th>
				<td class="showTime2" style="display: none;">
					<input type="text" name="practPhysicianCertificateNo" value="${doctor.practPhysicianCertificateNo}" class="<c:if test="${!addFlag}">validate[custom[integer],required]</c:if>" >
				</td>
			</tr>
			<tr class="showTime2" style="display: none;">
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>医师执业证书：</th>
				<td>
				<span id="doctorPracticingCategoryUrlFileSpan" style="display:${!empty extInfo.doctorPracticingCategoryUrl?'':'none'} " >
				[<a href="${sysCfgMap['upload_base_url']}/${extInfo.doctorPracticingCategoryUrl}" target="_blank">查看图片</a>]
				</span>
					<a id="doctorPracticingCategoryUrlFile" href="javascript:uploadFile('doctorPracticingCategoryUrlFile','医师执业证书');" style="color: blue"class="${(empty extInfo.doctorPracticingCategoryUrl && !addFlag)?'validate[required]':''}">${empty extInfo.doctorPracticingCategoryUrl?'':'重新'}上传</a>
					<a id="doctorPracticingCategoryUrlFileDel" href="javascript:delFile('doctorPracticingCategoryUrlFile');" class="" style="color: blue;${empty extInfo.doctorPracticingCategoryUrl?'display:none':''}">删除</a>
					<input type="hidden" id="doctorPracticingCategoryUrlFileValue" name="doctorPracticingCategoryUrl" value="${extInfo.doctorPracticingCategoryUrl}">
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>执业类型：</th>
				<input type="hidden" name="practicingCategoryName" id="practicingCategoryName" value="${extInfo.practicingCategoryName}">
				<td>
					<select id="practicingCategoryId" name="practicingCategoryId" class="<c:if test="${!addFlag}">validate[required]</c:if>"onchange="changeCategoryId(this.value)">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">
							<option value="${dictTypeEnumPracticeType.dictId}"	 <c:if test='${extInfo.practicingCategoryId==dictTypeEnumPracticeType.dictId}'>selected</c:if>>
									${dictTypeEnumPracticeType.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>执业范围：</th>
				<input type="hidden" value="${extInfo.practicingScopeName}" name="practicingScopeName" id="practicingScopeName">
				<td>
					<select id="practicingScopeId" name="practicingScopeId" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
							<c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
							<c:forEach items="${applicationScope[dictKey]}" var="scope">
								<option class="${scope.dictTypeId}" value="${scope.dictId}" <c:if test='${extInfo.practicingScopeId==scope.dictId and dict.dictId == extInfo.practicingCategoryId}'>selected</c:if>>
										${scope.dictName}</option>
							</c:forEach>
						</c:forEach>
					</select>
				</td>
				<script>
					function changeCategoryId(dictId){
						$('#practicingScopeId').val("");
						$('#practicingScopeId option').hide();
						$('#practicingScopeId option[value=""]').show();
						$('#practicingScopeId'+' option.${dictTypeEnumPracticeType.id}\\.'+dictId).show();
					}
					$(function(){
						var practicingCategoryId = '${extInfo.practicingCategoryId}';
						var practicingScopeId = '${extInfo.practicingScopeId}';
						if(!practicingScopeId){
							$('#practicingScopeId').val("");
						}
						$('#practicingScopeId option').hide();
						$('#practicingScopeId option[value=""]').show();
						$('#practicingScopeId'+' option.${dictTypeEnumPracticeType.id}\\.'+practicingCategoryId).show();
					});
				</script>
			</tr>
		</table>
		<table style="width:100%;">
			<caption>西部支援情况</caption>
			<colgroup>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="16%"/>
			<col width="20%"/>
			</colgroup>
			<tr class="cHeight">
				<th></th><td></td><th></th><td></td><th></th><td></td>
			</tr>
			<tr>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>否为西部支援&#12288;<br>行动住院医师：</th>
				<td>
					<label><input type="radio" name="isAssistance" <c:if test="${extInfo.isAssistance eq 'Y'}">checked="checked"</c:if>  value="Y"  class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="showId3(this.value);"/>是</label>&#12288;
					<label><input type="radio" name="isAssistance" <c:if test="${extInfo.isAssistance eq 'N'}">checked="checked"</c:if> value="N"  class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="showId3(this.value);"/>否</label>
				</td>
				<th class="isAssistance"><c:if test="${!addFlag}"><font color="red">*</font></c:if>西部支援行动&#12288;<br>住院医师送出&#12288;<br>省份：</th>
				<td class="isAssistance"><input type="text" name="assistanceProvince" value="${extInfo.assistanceProvince}" class="<c:if test="${!addFlag}">validate[required]</c:if>" ></td>
				<th class="isAssistance"><c:if test="${!addFlag}"><font color="red">*</font></c:if>西部支援行动&#12288;<br>住院医师送出&#12288;<br>单位：</th>
				<td class="isAssistance"><input type="text" name="westernSupportResidentsSendWorkUnit" value="${extInfo.westernSupportResidentsSendWorkUnit}" class="<c:if test="${!addFlag}">validate[required]</c:if>" ></td>
			</tr>
			<tr class="isAssistance">
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>西部支援行动&#12288;<br>住院医师送出&#12288;<br>单位是否军队&#12288;<br>医院：</th>
				<td>
					<label><input type="radio" name="isMilitary" <c:if test="${extInfo.isMilitary eq 'Y'}">checked="checked"</c:if> value="Y"  class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="showAssistanceCode('Y')"/>是</label>&#12288;
					<label><input type="radio" name="isMilitary" <c:if test="${extInfo.isMilitary eq 'N'}">checked="checked"</c:if> value="N"  class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="showAssistanceCode('N')"/>否</label>
				</td>
				<th class="assistanceCode" style="display: none;"><c:if test="${!addFlag}"><font color="red">*</font></c:if>西部支援行动&#12288;<br>住院医师送出&#12288;<br>单位统一社会&#12288;<br>信用代码：</th>
				<td class="assistanceCode" style="display: none;">
					<input type="text" name="assistanceCode" value="${extInfo.assistanceCode}" class="<c:if test="${!addFlag}">validate[required]</c:if>">
				</td>
			</tr>
		</table>
			<script>
				$(function(){
					checkDoctorType('${doctor.doctorTypeId}');
					$(".workOrgNameTh,.workOrgNameTd").hide();
					checkDoctorType2('${doctor.doctorTypeId}');
				});
				function checkDoctorType(value) {
					if (value == 'Company'||value == 'CompanyEntrust'||value == 'ExternalEntrust') {
						$(".workOrgNameTh,.workOrgNameTd,.medicalInstitutionTh,.medicalInstitutionTd,.prove").show();
						changeMedicalThings($("#medicalHeaithOrgId option:selected").val());
					}else{
						$(".workOrgNameTh,.workOrgNameTd,.medicalInstitutionTh,.medicalInstitutionTd,.prove").hide();
						changeMedicalThings("");
					}
					var doctorCategoryId = $("#doctorCategoryId").val();
					if("${GlobalConstant.FLAG_N}"!=isSch[doctorCategoryId])
					{
						if (value == 'Company') {
							$(".departMentFlow").show();
						}else{
							$(".departMentFlow").hide();
							$("#departMentFlow").val("");
						}
					}else{
						$(".departMentFlow").hide();
						$("#departMentFlow").val("");
					}
				}
				function checkDoctorType2(value){
					if(value=='Graduate') {
						$(".workOrgNameTh").html("<c:if test="${!addFlag}"><font color='red'>*</font></c:if>在读院校：");
						$(".workOrgNameTh,.workOrgNameTd").show();
                        $(".workOrgNameTd1").show();
                        $(".workOrgNameTd1").attr("disabled",false);
                        $(".workOrgNameTd2").hide();
                        $(".workOrgNameTd2").attr("disabled",true);
					}
					else if(value=='Company'||value == 'CompanyEntrust'||value == 'ExternalEntrust') {
						$(".workOrgNameTh").html("<c:if test="${!addFlag}"><font color='red'>*</font></c:if>工作单位：");
						$(".workOrgNameTh,.workOrgNameTd").show();
                        $(".workOrgNameTd2").show();
                        $(".workOrgNameTd2").attr("disabled",false);
                        $(".workOrgNameTd1").hide();
                        $(".workOrgNameTd1").attr("disabled",true);
					}else{
						$(".workOrgNameTh,.workOrgNameTd").hide();
					}
				}
				function changeMedicalThings(name){
					if(name=='3'){
						$(".hospitalTr").hide();
						$(".basicmedicalInstitutionTypeTh").show();
						$(".basicmedicalInstitutionTypeTd").show();
					}else if(name=='1'){
						$(".hospitalTr").show();
						$(".basicmedicalInstitutionTypeTh").hide();
						$(".basicmedicalInstitutionTypeTd").hide();
					}else{
						$(".hospitalTr").hide();
						$(".basicmedicalInstitutionTypeTh").hide();
						$(".basicmedicalInstitutionTypeTd").hide();
					}
				}
			</script>
			<script>
				function changeEducation (){
					var educationName=$("#educationId").find("option:selected").text()
					$("#educationName").val(educationName);
				}
			</script>
		<div style="display:none">
			<table>
				<input type="hidden" id="technologyQualificationName" name="technologyQualificationName" value="${extInfo.technologyQualificationName}"/>
				<input type="hidden" id="qualificationMaterialName" name="qualificationMaterialName" value="${extInfo.qualificationMaterialName}"/>
				<!-- 最高学历相关信息-->
				<input type="hidden" id="uEducationName" name="user.educationName" value="${user.educationName}"/>
				<input type="hidden" id="dGraduatedName" name="doctor.graduatedName" value="${doctor.graduatedName}"/>
				<input type="hidden" id="dSpecialized" name="doctor.specialized" value="${doctor.specialized}"/>
				<input type="hidden" id="dGraduationTime" name="doctor.graduationTime" value="${doctor.graduationTime}"/>
				<th>最高毕业证书编号：</th>
				<td ><input name="doctor.certificateNo" value="${doctor.certificateNo}" class="input validate[]"/></td>
				<th>最高毕业证书上传：</th>
				<td>
					<%--<input type="hidden" id="certificateUriValue" name="certificateUri" value="${extInfo.certificateUri}"/>--%>
				</td>
				</tr>

			</table>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="14%"></col>
					<col width="36%"></col>
					<col width="14%"></col>
					<col width="36%"></col>
				</colgroup>
				<tbody>
				<tr>
					<th>专业技术资格：</th>
					<td style="padding-left:10px;">
						<select id="technologyQualificationId" name="technologyQualificationId" class="select" style="width: 160px;">
							<option value="">请选择</option>
							<option value="171" ${extInfo.technologyQualificationId eq '171'?'selected':''}>住院医师</option>
							<option value="172" ${extInfo.technologyQualificationId eq '172'?'selected':''}>主治医师</option>
							<option value="173" ${extInfo.technologyQualificationId eq '173'?'selected':''}>住院中医师</option>
							<option value="174" ${extInfo.technologyQualificationId eq '174'?'selected':''}>主治中医师</option>
							<option value="196" ${extInfo.technologyQualificationId eq '196'?'selected':''}>无</option>
						</select>
					</td>
					<th>取得日期：</th>
					<td colspan="2">
						<input name="getTechnologyQualificationDate" value="${extInfo.getTechnologyQualificationDate}" class="input datepicker" style="width: 149px;" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>执业资格材料：</th>
					<td style="padding-left:10px;">
						<select id="qualificationMaterialId" name="qualificationMaterialId" class="select" style="width: 160px;">
							<option value="">请选择</option>
							<option value="176" ${extInfo.qualificationMaterialId eq '176'?'selected':''}>医师执业证书</option>
							<option value="177" ${extInfo.qualificationMaterialId eq '177'?'selected':''}>医师资格证书</option>
							<option value="178" ${extInfo.qualificationMaterialId eq '178'?'selected':''}>已通过国家执业医师考试的成绩单</option>
							<option value="200" ${extInfo.qualificationMaterialId eq '200'?'selected':''}>助理执业医师证书（定向大专）</option>
							<option value="201" ${extInfo.qualificationMaterialId eq '201'?'selected':''}>已通过国家助理执业医师考试的成绩单（定向大专）</option>
							<option value="202" ${extInfo.qualificationMaterialId eq '202'?'selected':''}>无</option>
						</select>
					</td>
					<th>资格材料编码：</th>
					<td colspan="2"><input name="qualificationMaterialCode" value="${extInfo.qualificationMaterialCode}" class="input"/></td>
				</tr>

				<tr>
					<th>资格证书上传：</th>
					<td colspan="3">
						<input type="hidden" id="qualificationMaterialUriValue" name="qualificationMaterialUri" value="${extInfo.qualificationMaterialUri}"/>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<div id="d" style="display: none;">
			
		</div>
		<table id="rotationInfo" style="width:100%;">
			<caption>培训信息</caption>
			<colgroup>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="16%"/>
			<col width="20%"/>
			</colgroup>
			<tr>
				<th><font color="red">*</font>参培年份：</th>
				<td>
					<select name="sessionNumber" id="sessionNumber" onchange="selRotation();calculation();">
						<option></option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
							<option value="${dict.dictName}" ${doctor.sessionNumber eq dict.dictName?'selected="selected"':''}>${dict.dictName}</option>
						</c:forEach>
					</select>

				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if><span class="trainNameSpan trainExtralSpan">培养</span>年限：</th>
				<td>
					<select name="trainingYears" id="trainingYears" onchange="calculation();">
						<option></option>
						<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
							<option value="${dict.dictId}" ${doctor.trainingYears eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>

				</td>
				<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
				<th><span class="deptSpan"><c:if test="${!addFlag}"><font color="red">*</font></c:if>所属部门：</span></th>
				<td>
					<span class="deptSpan">
					<select name="deptFlow" class="<c:if test="${!addFlag}">validate[required]</c:if>">
						<option></option>
						<c:forEach items="${deptList}" var="dept">
							<option value="${dept.deptFlow}" ${user.deptFlow eq dept.deptFlow?'selected':''}>${dept.deptName}</option>
						</c:forEach>
					</select>
					</span>
				</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag}">
					<th><c:if test="${!addFlag}"><font color="red">*</font></c:if><span class="trainNameSpan">培训</span>基地：</th>
					<td>
						<select name="orgFlow" class="<c:if test="${!addFlag}">validate[required]</c:if>" onchange="option(this.value);changeDepart(this.value);">
							<option>
							<c:forEach items="${orgList}" var="org">
								<option value="${org.orgFlow}" ${org.orgFlow eq doctor.orgFlow?'selected':''}>${org.orgName}</option>
							</c:forEach>
						</select>

					</td>
				</c:if>
				<c:if test="${GlobalConstant.USER_LIST_GLOBAL != param.roleFlag}">

						<input name="orgFlow" type="hidden" value="${doctor.orgFlow}"/>
				</c:if>
				<th><font color="red">*</font><span class="trainNameSpan">培训</span>专业：</th>
				<td>
					<c:if test="${!rotationInUse}">
						<select name="trainingSpeId" id="trainingSpeId" onchange="selRotation();">
							<option></option>
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<option value="${dict.dictId}" ${doctor.trainingSpeId eq dict.dictId?'selected="selected"':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</c:if>
					<c:if test="${rotationInUse}">
						<label>${doctor.trainingSpeName}</label>
					</c:if>
				</td>
				<th><font color="red">*</font><label class="rotationAttr">轮转方案：</label></th>
				<td colspan="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag?'1':'3'}">
					<c:if test="${!rotationInUse}">
						<select name="rotationFlow" class=" rotationAttr" id="rotationFlow" onclick="checkCondition();">
							<option></option>
							<c:forEach items="${rotationList}" var="rotation">
									<option class="_${rotation.doctorCategoryId} _${rotation.speId} _rotation
									<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq param.roleFlag && rotation.isOrgView eq GlobalConstant.RECORD_STATUS_Y}">
										_ isOrg
										<c:forEach items="${map[rotation.rotationFlow]}" var="resRotationOrg">
										_ ${resRotationOrg.orgFlow}
										</c:forEach>
									</c:if>
									" value="${rotation.rotationFlow}" ${(doctor.rotationFlow eq rotation.rotationFlow)?'selected':''}>${rotation.rotationName}</option>
							</c:forEach>
						</select>
						<font class="rotationAttr" color="red"></font>
					</c:if>
					<c:if test="${rotationInUse}">
						<label>${doctor.rotationName}</label>
					</c:if>
				</td>
			</tr>
			<tr>
				<th><font color="red">*</font><span class="userNameSpan">医师</span>状态：</th>
				<td>
					<select name="doctorStatusId" class="validate[required]" onchange="changeTerminat(this.value);">
						<option></option>
						<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
							<option value="${dict.dictId}" ${doctor.doctorStatusId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>

				</td>
				<th class="${recDocCategoryEnumGraduate.id}">导师姓名：</th>
				<td class="${recDocCategoryEnumGraduate.id}">
					<select name="tutorFlow" onchange="$('#tutorName').val($(':selected',this).text());">
						<option/>
						<c:forEach items="${tutorList}" var="tutor">
							<option value="${tutor.userFlow}" <c:if test="${tutor.userFlow eq doctor.tutorFlow}">selected</c:if>>${tutor.userName}</option>
						</c:forEach>
					</select>
					<input type="hidden" id="tutorName" name="tutorName" value="${doctor.tutorName}"/>
				</td>
				<th class="${recDocCategoryEnumGraduate.id}">研究方向：</th>
				<td class="${recDocCategoryEnumGraduate.id}">
					<input type="text" name="researchDirection" value="${doctor.researchDirection}"/>
				</td>
				</tr>
				<tr>
				<th class="${resDoctorStatusEnumTerminat.id}">终止原因：</th>
				<td class="${resDoctorStatusEnumTerminat.id}">
					<input type="text" name="terminatReason" value="${doctor.terminatReason}"/>
				</td>
				<th class="${resDoctorStatusEnumTerminat.id}">终止时间：</th>
				<td class="${resDoctorStatusEnumTerminat.id}">
					<input type="text" name="terminatDate" value="${doctor.terminatDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/>
				</td>
				<th class="${resDoctorStatusEnumGraduation.id}">结业证书号：</th>
				<td class="${resDoctorStatusEnumGraduation.id}">
					<input type="text" name="completeNo" value="${doctor.completeNo}"/>
				</td>
				<th class="${resDoctorStatusEnumGraduation.id}">结业时间：</th>
				<td class="${resDoctorStatusEnumGraduation.id}">
					<input type="text" name="completeDate" value="${doctor.completeDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/>
				</td>
			</tr>
			<tr>
            <c:if test="${!empty applicationScope.sysCfgMap['res_disciple_role_flow']}">

                <th><c:if test="${!addFlag}"><font color="red">*</font></c:if>师承老师：</th>
                <td>
                    <input id="orgSel" class="toggleView <c:if test="${!addFlag}">validate[required]</c:if>" type="text" name="discipleTeacherName" value="${doctor.discipleTeacherName}" autocomplete="off"/>
                    <input class="toggleView xltext" hidden type="text" name="discipleTeacherFlow" id="discipleTeacherFlow" value="${doctor.discipleTeacherFlow}" autocomplete="off"/>
                    <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                            <c:forEach items="${discipleList}" var="disciple">
                                <p class="item" flow="${disciple.userFlow}" value="${disciple.userName}" style="height: 30px;padding-left: 10px;">${disciple.userName}</p>
                            </c:forEach>
                        </div>
                    </div>
				</td>
            </c:if>
				<th><label>实际培训&#12288;<br/>开始时间：</label></th>
				<td>
					<input type="text" value="${doctor.inHosDate}" name="inHosDate" id="inHosDate" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
				</td>
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>结业考核年份：</th>
				<td>
					<c:if test="${!(role eq 'hospital')}">
						<input type="text" readonly="readonly" name="graduationYear" id="graduationYear" value="${doctor.graduationYear}"
							   onclick="WdatePicker({dateFmt:'yyyy'})">
					</c:if>
					<c:if test="${role eq 'hospital'}">
						<label id="graduationYearLebal">${doctor.graduationYear}</label>
						<input type="hidden" name="graduationYear" id="graduationYear" value="${doctor.graduationYear}">
					</c:if>
				</td>
			</tr>
			<c:if test="${sysCfgMap['Chinese_Western'] eq GlobalConstant.Chinese}">
			<tr>
				<input type="hidden" value="${resDiscipleInfo.discipleFlow}" name="discipleFlow">
				<th>
					跟师时间：
				</th>
				<td>
					<input type="text" name="discipleStartDate" id="discipleStartDate"
						   value="${resDiscipleInfo.discipleStartDate}" readonly="readonly"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
				</td>
				<th style="text-align: center">~</th>
				<td>
					<input type="text" name="discipleEndDate" id="discipleEndDate"
						   value="${resDiscipleInfo.discipleEndDate}" readonly="readonly"
						   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
				</td>
				<th></th>
				<td></td>
			</tr>
			</c:if>
			<tr class="showSpe" style="display: none;">
				<th><c:if test="${!addFlag}"><font color="red">*</font></c:if>专业方向：</th>
				<td>
					<input  id="standardDeptId" class="toggleView" type="hidden" name="standardDeptId" value="${doctor.standardDeptId}"/>
					<input  id="standardDeptName" class="toggleView" type="text" name="standardDeptName" value="${doctor.standardDeptName}" autocomplete="off"/>
					<div style="width: 0px;height: 0px;overflow: visible;float: left; position:absolute;">
						<div id="boxHome2" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width:170px;border-top: none;position: relative;display: none;">
							<c:forEach items="${dictTypeEnumStandardDeptList}" var="dict">
								<c:set var="dictKey" value="dictTypeEnumStandardDept.${dict.dictId}List"/>
								<p class="item" flow="${dict.dictId}" value="${dict.dictName}">${dict.dictName}</p>
								<c:forEach items="${applicationScope[dictKey]}" var="sDict">
									<c:set var="sDictId" value="${dict.dictId}.${sDict.dictId}"/>
									<c:set var="sDictName" value="${dict.dictName}.${sDict.dictName}"/>
									<p class="item" flow="${sDictId}" value="${sDictName}">${sDictName}</p>
									<c:set var="dictKey" value="dictTypeEnumStandardDept.${sDictId}List"/>
									<c:forEach items="${applicationScope[dictKey]}" var="tDict">
										<c:set var="tDictId" value="${dict.dictId}.${sDict.dictId}.${tDict.dictId}"/>
										<c:set var="tDictName" value="${dict.dictName}.${sDict.dictName}.${tDict.dictName}"/>
										<p class="item" flow="${tDictId}" value="${tDictName}">${tDictName}</p>
									</c:forEach>
								</c:forEach>
							</c:forEach>
						</div>
					</div>
				</td>
			</tr>
			<c:if test="${(!empty user.userFlow)&&(param.editButtonFlag ne 'no')}">
			<tr>
				<th>账号状态：</th>
				<td colspan="5">
					<span id="userStatusSpan">
						${user.statusDesc }&#12288;

			<c:if test="${!(isFree ||param.isFormSch)}">
						<c:if test="${user.statusId==userStatusEnumActivated.id}">
							[<a href="javascript:lock('${user.userFlow}');" >锁定</a>]
						</c:if> 
						<c:if test="${user.statusId==userStatusEnumLocked.id}">
							[<a href="javascript:activate('${user.userFlow}');" >解锁</a>]
						</c:if>
						[<a href="javascript:resetPasswd('${user.userFlow}');">重置密码</a>]
						
						<c:if test="${(sysCfgMap['res_org_ad_doc'] eq GlobalConstant.FLAG_Y && !(param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL)) || param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
							<script type="text/javascript">
								function disabledDocAndUser(){
									jboxConfirm("确认停用该学员？",function(){
										jboxGet("<s:url value='/res/doc/disabledDocAndUser'/>?userFlow=${user.userFlow}",null,function(resp){
											if(resp="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
												top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
												top.document.mainIframe.location.reload();
												doClose();
											}
										},null,false);
									},null);
								}
							</script>
							
							[<a onclick="disabledDocAndUser();" style="cursor: pointer;">停用</a>]
						</c:if>
						</c:if>
					</span>
				</td>
			</tr>
			</c:if>
		</table>
		<%-- 
		<table style="width:100%;">
			<caption>教育背景</caption>
			<colgroup>
			<col width="14%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			</colgroup>
			<tr>
				<th><span class="schoolNameSpan">毕业</span>院校：</th>
				<td>
					<select name="graduatedId">
						<option></option>
						<c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict">
							<option value="${dict.dictId}" ${doctor.graduatedId eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<th><span class="educationSpan">学历：</span></th>
				<td>
					<span class="educationSpan">
						<select name="educationId">
							<option></option>
							<c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
								<option value="${dict.dictId}" ${user.educationId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</span>
				</td>
				<th><span class="degreeSpan">学位：</span></th>
				<td >
					<span class="degreeSpan">
						<select name="degreeId">
							<option></option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}" ${user.degreeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
							</c:forEach>
						</select>
					</span>
				</td>
			</tr>
			<tr>
				<th>所学专业：</th>
				<td>
					<input type="text" name="specialized" value="${doctor.specialized}"/>
				</td>
				<th><span class="graduateSpan">毕业证书号：</span></th>
				<td>
					<span class="graduateSpan"><input type="text" name="certificateNo" value="${doctor.certificateNo}"/></span>
				</td>
				<th><span class="graduateSpan">毕业时间：</span></th>
				<td>
					<span class="graduateSpan"><input type="text" name="graduationTime" value="${doctor.graduationTime}" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/></span>
				</td>
			</tr>
			<tr>
				<th>计算机能力：</th>
				<td>
					<input type="text" name="computerSkills" value="${doctor.computerSkills}"/>
				</td>
				<th>外语能力：</th>
				<td colspan="3">
					<input type="text" name="foreignSkills" value="${doctor.foreignSkills}"/>
				</td>
			</tr>
		</table>
		
		<div id="workInfo" style="display: ;">
		<table style="width:100%;">
			<caption>工作信息</caption>
			<colgroup>
			<col width="14%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			</colgroup>
			<tr>
				<th>工作单位：</th>
				<td><input type="text" name="workOrgName" value="${doctor.workOrgName}"/></td>
				<th>入院时间：</th>
				<td>
					<input type="text" name="inHosDate" value="${doctor.inHosDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/>
				</td>
				<!-- 
				<th>科室：</th>
				<td>
					<select name="deptFlow">
						<option></option>
						<c:forEach items="${deptList}" var="dept">
							<option value="${dept.deptFlow}" ${user.deptFlow eq dept.deptFlow?'selected="selected"':''}>${dept.deptName}</option>
						</c:forEach>
					</select>
				</td>
				 -->
				 <th></th>
				 <td></td>
			</tr>
			<tr>
				<th>职称：</th>
				<td>
					<select name="titleId">
						<option></option>
						<c:forEach items="${dictTypeEnumUserTitleList}" var="title">
       						<option value="${title.dictId}" ${user.titleId eq title.dictId?'selected="selected"':''}>${title.dictName}</option>
       					</c:forEach>
					</select>
				</td>
				<th>职务：</th>
				<td colspan="3">
					<select name="postId">
						<option></option>
						<c:forEach items="${dictTypeEnumUserPostList}" var="post">
     						<option value="${post.dictId}" ${user.postId eq post.dictId?'selected="selected"':''}>${post.dictName}</option>
       					</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		</div>
		
		<table style="width:100%;">
			<caption>证书情况</caption>
			<colgroup>
			<col width="14%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			<col width="12%"/>
			<col width="20%"/>
			</colgroup>
			<tr>
				<th>执业医师资格证号：</th>
				<td><input type="text" name="qualifiedNo" value="${doctor.qualifiedNo}"/></td>
				<th>获取时间：</th>
				<td colspan="3"><input type="text" name="qualifiedDate" value="${doctor.qualifiedDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/></td>
			</tr>
			<tr>
				<th>执业医师注册证号：</th>
				<td><input type="text" name="regNo" value="${doctor.regNo}"/></td>
				<th>获取时间：</th>
				<td><input type="text" name="regDate" value="${doctor.regDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" readonly="readonly"/></td>
				<th>执业地点：</th>
				<td><input type="text" name="regAddress" value="${doctor.regAddress}"/></td>
			</tr>
		</table>
		
		<!-- 教育经历 -->
		<div id="edu" style="padding-top: 10px;"></div>
		
		<!-- 工作经历 -->
		<div id="work" style="padding-top: 10px;"></div>
		--%>
		</form>
		<form id="returnFrom">
			<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
			<input type="hidden" name="typeId" value="${resRecTypeEnumReturnTraining.id}"/>
			<input type="hidden" name="typeName" value="${resRecTypeEnumReturnTraining.name}"/>
			<table id="returnTable" style="width:100%;display: none;">
				<caption>退培信息</caption>
				<colgroup>
					<col width="20%"/>
					<col width="20%"/>
					<col width="60%"/>
				</colgroup>
				<tr>
					<th>
						退培类型：
					</th>
					<td>
						<select class="<c:if test="${!addFlag}">validate[required]</c:if>"  name="policyId" style="width: 90%;">
							<option value="">请选择</option>
							<option value="1">协议退培</option>
							<option value="2">违约退培</option>
						</select>
					</td>
					<td>
						<input name="policy" value="" class="<c:if test="${!addFlag}">validate[required]</c:if>" style="width: 86%;height: 70%" /><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th>
						退培主要原因：
					</th>
					<td>
						<select class="<c:if test="${!addFlag}">validate[required]</c:if>" name="reasonId" style="width: 90%;" >
							<option value="">请选择</option>
							<option value="1">辞职</option>
							<option value="2">考研</option>
							<option value="3">其他</option>
						</select>
					</td>
					<td>
						<input name="reason" value="" class="<c:if test="${!addFlag}">validate[required]</c:if>" style="width: 86%;height: 70%" /><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th>学员去向：</th>
					<td colspan="2">
						<input name="dispositon" value="" class="<c:if test="${!addFlag}">validate[required]</c:if>" style="width: 90%;height: 70%"/><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th>备注（培训基地意见）：</th>
					<td colspan="2">
						<input name="remark"  value="" class="<c:if test="${!addFlag}">validate[required]</c:if>" style="width: 90%;height: 70%"/><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th>
						上传附件：
					</th>
					<td colspan="2">
						<input type="file" name="file" class="<c:if test="${!addFlag}">validate[required]</c:if>"/><span class="red">*</span>
					</td>
				</tr>
			</table>
		</form>
		<form id="delayForm">
			<input type="hidden" name="doctorFlow" value="${doctor.doctorFlow}"/>
			<table id="delayTable" style="width:100%;display: none;">
				<caption>延期信息</caption>
				<colgroup>
					<col width="10%"/>
					<col width="40%"/>
					<col width="10%"/>
					<col width="30%"/>
				</colgroup>
				<tr>
					<th>延期原因：</th>
					<td colspan="3">
						<input class="<c:if test="${!addFlag}">validate[required]</c:if>" id="delayreason"  name="delayreason" style="width: 91%;height: 70%;" value=""><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<th>上传附件：</th>
					<td colspan="3">
						<input type="file" id="file" name="file" class="<c:if test="${!addFlag}">validate[required]</c:if>"/><span class="red">*</span>
				</tr>
				<tr>
					<th>结业考核年份：</th>
					<td style="text-align: left">
						<input class="<c:if test="${!addFlag}">validate[required]</c:if>" name="delayYear" id="delayYear" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy'});"
							   style="width: 20%;height: 70%;" value=""><span class="red">*</span>
					</td>
				</tr>
				<tr>
					<input type="hidden" value="${resDiscipleInfo.discipleFlow}" name="discipleFlow">
					<th>
						跟师时间：
					</th>
					<td>
						<input type="text" name="discipleStartDate" id="discipleStartDate"
							   value="${resDiscipleInfo.discipleStartDate}" readonly="readonly"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
					<th style="text-align: center">~</th>
					<td>
						<input type="text" name="discipleEndDate" id="discipleEndDate"
							   value="${resDiscipleInfo.discipleEndDate}" readonly="readonly"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
					<th></th>
					<td></td>
				</tr>
			</table>
		</form>
		<p style="text-align: center;">

			<c:if test="${!(isFree ||param.isFormSch)}">
				<c:if test="${!(waitReturn eq 'waitReturn')}">
				<input type="button" onclick="saveDoc();" id="saveButton1" class="search" value="保&#12288;存"/>
				</c:if>
				<input type="button" onclick="saveRede();" id="saveButton2" style="display: none" class="search2" value="确认退培"/>
				<input type="button" onclick="saveDelay();" id="saveButton3" style="display: none" class="search2" value="确认延期"/>
				<c:if test="${!param.isMenu}">
					<input type="button" onclick="doClose();" id="close" class="search" value="关&#12288;闭"/>
				</c:if>
				<c:if test="${role eq 'hospital'}">
				<c:if test="${sysCfgMap['res_org_delay_return'] eq 'Y'}">
					<script>
						function reCheck(obj) {
							$(obj).hide();
							$("#down").hide();
							$("#file").show();
						}
						function saveDelay(){
							if($("#delayForm").validationEngine("validate")){
								if(!$("#delayreason").val())
								{
									jboxTip("请输入延期原因!");
									return false;
								}
								if(!$("#file").val())
								{
									jboxTip("请上传附件!");
									return false;
								}
								if("${doctor.graduationYear}">=$("#delayYear").val()){
									jboxTip("延期结业年份应大于现结业年份");
									return false;
								}
								jboxConfirm("确认延期？",function(){
									var url='<s:url value="/res/doc/delayDoc"/>';
									jboxSubmit($("#delayForm"),url,function(resp){
										if(resp==1) {
											jboxTip("延期成功!");
											window.parent.frames['mainIframe'].search();
											doClose();
										}else if(resp ==3){
											jboxTip("上传附件不得大于10M!");
										}else if(resp ==4){
											jboxTip("请上传附件!");
										}
										else  jboxTip("延期失败!");
									},null,false)
								})
							}
						}
						function saveRede(){
							if($("#returnFrom").validationEngine("validate")){
								jboxConfirm("确认退培？",function(){
									var url='<s:url value="/res/doc/returnDoc"/>';
									jboxSubmit($("#returnFrom"),url,function(resp){
										if(resp==1) {
											jboxTip("退培成功!");
											window.parent.frames['mainIframe'].search();
											doClose();
										}else if(resp ==3){
											jboxTip("上传附件不得大于10M!");
										}
										else jboxTip("退培失败!");
									},null,false)
								})
							}
						}
						var count = 0;
						var count2 = 0;
						function returnModel(){
							count2 = 0;
							$("#delayButton").val("延    期");
							$("#delayTable").hide();
							$("#saveButton3").hide();
							$("#returnTable").toggle();
							$("#saveButton2").toggle();
							if(count%2==0){
								$("#returnButton").val("取消退培");
								$("#saveButton1").hide();
							}else{
								$("#returnButton").val("退    培");
								$("#saveButton1").show();
							}
							count+=1;
						}

						function delayModel(){
							count = 0;
							$("#returnButton").val("退    培");
							$("#returnTable").hide();
							$("#saveButton2").hide();
							$("#delayTable").toggle();
							$("#saveButton3").toggle();
							if(count2%2==0){
								$("#delayButton").val("取消延期");
								$("#saveButton1").hide();
							}else{
								$("#delayButton").val("延    期");
								$("#saveButton1").show();
							}
							count2+=1;
						}
					</script>
					<c:if test="${!(waitReturn eq 'waitReturn')}">
						<input type="button" id="delayButton" onclick="delayModel();" class="search2" value="延&#12288;期"/>
						<input type="button" id="returnButton" onclick="returnModel();" class="search2" value="退&#12288;培"/>
					</c:if>
					<c:if test="${waitReturn eq 'waitReturn'}">
						<span style="font-size: inherit;color:red">退培待省厅审核中</span>
					</c:if>
				</c:if>
				</c:if>
			</c:if>
       </p>
		</div>
	</div>
</div>
</body>
</html>