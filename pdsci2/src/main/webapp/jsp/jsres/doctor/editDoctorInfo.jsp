<style type="text/css">
	.boxHome .item:HOVER {background-color: #eee;}
	.cur {color: red;}
	.pxxx {position: relative;top: 30px;right: 175px;}
	.changeinfo {position: absolute;background-color: #fff;padding: 8px;border: 1px solid #dcdcdc;width: 148px;}
	.icon_up {background-image: url("<s:url value='/css/skin/${skinPath}/images/up2.png'/>");background-repeat: no-repeat;background-position: top center;padding: 5px;position: absolute;top: -6px;}
</style>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function () {

		$('#practicingScopeId option').hide();
		if ('${userResumeExt.isMaster}'){
			checkBx("${userResumeExt.isMaster}", 'master');
		}
		if ('${userResumeExt.isDoctor}'){
			checkBx("${userResumeExt.isDoctor}", 'doctor');
		}
		if ('${user.trainingTypeId}'=='DoctorTrainingSpe'){
			$(".doctorTypeNameTd2").hide();
			$("#isDZ").hide();
			checkZk('N','junior');
			checkBx("${userResumeExt.isUndergraduate}", 'undergrad');
			checkBx("${userResumeExt.isMaster}", 'master');
			checkBx("${userResumeExt.isDoctor}", 'doctor');
			showDiploma('undergrad','${userResumeExt.isCollegeHaveDiploma}');
			showDiploma('master','${userResumeExt.isMasterHaveDiploma}');
			showDiploma('doctor','${userResumeExt.isDoctorHaveDiploma}');

		}else if ('${user.trainingTypeId}'=='AssiGeneral'){
			checkZk('${userResumeExt.isJuniorCollege}','junior')
			$("#doctorType_Graduate").hide();
			$("#doctorTypeName_Graduate").hide();
			$(".doctorTypeNameTd1").hide();
			$(".zl").hide();
			checkZk('N','undergrad');
			checkZk('N','master');
			checkZk('N','doctor');
		}

	});

	function clshow(c) {
		$("."+c).show();
	}

	function clhide(c) {
		$("."+c).hide();
	}

	function idhide(c) {
		$("#"+c).hide();
	}

	(function ($) {
		$.fn.likeSearchInit = function (option) {
			option.clickActive = option.clickActive || null;

			var searchInput = this;
			var spaceId = this[0].id;
			searchInput.on("keyup focus", function () {
				var boxHome = $("#" + spaceId + "Sel");
				boxHome.show();.0
				if ($.trim(this.value)) {
					$("p." + spaceId + ".item", boxHome).hide();
					var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
					if (!items) {
						boxHome.hide();
					}
				} else {
					$("p." + spaceId + ".item", boxHome).show();
				}
			});
			searchInput.on("blur", function () {
				var boxHome = $("#" + spaceId + "Sel");
				if (!$("." + spaceId + ".boxHome.on").length) {
					boxHome.hide();
				}
			});
			$("." + spaceId + ".boxHome").on("mouseenter mouseleave", function () {
				$(this).toggleClass("on");
			});

			$("." + spaceId + ".boxHome .item").click(function () {
				var boxHome = $("." + spaceId + ".boxHome");
				boxHome.hide();
				var value = $(this).attr("value");
				var input = $("#" + spaceId);
				input.val(value);
				if (spaceId == 'psxx') {
					$("#school").val(value);
					compare(spaceId);
				}
				if (spaceId == 'psdw') {
					$("#work").val(value);
					compare(spaceId);
				}
				if (option.clickActive) {
					option.clickActive($(this).attr("flow"));
				}
// 			var content = $("#clone").html().replace("title",value);
// 			$("#"+input.attr("id")+"Div").append(content);
			});
			var isJuniorCollegeCertificate = $('input:radio[name="userResumeExt.isJuniorCollegeCertificate"]:checked').val();
			if(isJuniorCollegeCertificate == 'N'){
				$("input[name='userResumeExt.isJuniorCollegeCertificate']").change();
			}
		};
	})(jQuery);

	function compare(spaceId) {
		if (spaceId == "psxx") {
			if ($("#school").val() != $("#workOrgName").val()) {
				changeOrg();
			}
		}
		if (spaceId == "psdw") {
			if ($("#work").val() != $("#workOrgName").val()) {
				changeOrg();
			}
		}
	}

	// 切换证件类型为身份证时校验证件号码
	function changeCretType(type) {
		var idNo = $("input[name='user.idNo']").val();
		if (idNo && "${certificateTypeEnumShenfenzheng.id}" == type) {
			checkIdCard(idNo);
		}
	}

	// 身份证格式校验
	function checkIdCard(idcard) {
		const regIdCard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		if (!regIdCard.test(idcard)) {
			jboxTip('身份证号填写格式有误！');
			$("input[name='user.idNo']").val("");
			return false;
		}
	}

	/**
	 * 验证身份证号、手机号唯一
	 */
	function checkUserUnique() {
		var idNo = $("input[name='user.idNo']").val();
		var userPhone = $("input[name='user.userPhone']").val();
		var data = {
			userFlow: "${user.userFlow}",
			idNo: idNo,
			userPhone: userPhone
		};
		// 增加校验身份证号
		if (idNo) {
			var cretTypeId2 = $("#cretTypeId").val();
			if ("${certificateTypeEnumShenfenzheng.id}" == cretTypeId2) {
				checkIdCard(idNo);
			}
		}
		if (userPhone != "" || idNo != "") {
			var url = "<s:url value='/jsres/doctor/checkUserUnique'/>";
			jboxPost(url, data,
					function (resp) {
						if (resp == "${GlobalConstant.USER_ID_NO_REPETE}") {
							var cretTypeId = $("#cretTypeId").val();
							if ("${certificateTypeEnumShenfenzheng.id}" == cretTypeId) {
								jboxTip(resp);
							} else {
								jboxTip("该证件号已经被注册！");
							}
							$("input[name='user.idNo']").focus();
						} else if (resp == "${GlobalConstant.USER_PHONE_REPETE}") {
							jboxTip(resp);
							$("input[name='user.userPhone']").focus();
						}
					}, null, false);
		}
	}

	function looKTrainingTypeId() {
		$.ajax({
			url: "<s:url value='/jsres/doctor/looKTrainingTypeId'/>",
			type: "get",
			data: {},
			dataType: "json",
			success: function (res) {
				$("#trainingType").val(res);
			}
		});
	}

	/**
	 * 保存
	 */
	function saveDoctorInfo() {

		var userName = $('#userName').val();
		if (userName.indexOf('<')>-1) {
			jboxTip("姓名不能输入特殊字符！");
			return false;
		}
		var psdw = $('#psdw').val();
		if (psdw.indexOf('<')>-1) {
			jboxTip("派送单位不能输入特殊字符！");
			return false;
		}
		var emergencyAddress = $('#emergencyAddress').val();
		if (emergencyAddress.indexOf('<')>-1) {
			jboxTip("紧急联系人地址不能输入特殊字符！");
			return false;
		}
		var emergencyName = $('#emergencyName').val();
		if (emergencyName.indexOf('<')>-1) {
			jboxTip("紧急联系人不能输入特殊字符！");
			return false;
		}
		var userAddress = $('#userAddress').val();
		if (userAddress.indexOf('<')>-1) {
			jboxTip("本人通讯地址不能输入特殊字符！");
			return false;
		}
		var computerSkills = $('#computerSkills').val();
		if (computerSkills.indexOf('<')>-1) {
			jboxTip("计算机能力不能输入特殊字符！");
			return false;
		}
		if (false == $("#doctorForm").validationEngine("validate")) {
			return false;
		}
		if ($("#headimgurl").val() == "") {
			jboxTip("请上传头像！");
			return false;
		}
		if ($("#certificateUriValue").val() == "") {
			jboxTip("请上传最高毕业证书！");
			return false;
		}
		if ($("#idcardFrontImgValue").val() == "") {
			jboxTip("请上传身份证人像面！");
			return false;
		}
		if ($("#idcardOppositeImgValue").val() == "") {
			jboxTip("请上传身份证国徽面！");
			return false;
		}
		// 是否专科
		var isJuniorCollege = $('input:radio[name="userResumeExt.isJuniorCollege"]:checked').val();
		if ("Y" == isJuniorCollege) {
			var isJuniorCollegeCertificate = $('input:radio[name="userResumeExt.isJuniorCollegeCertificate"]:checked').val();
			if ("Y" == isJuniorCollegeCertificate && $("#juniorCollegeUrlValue").val() == "") {
				jboxTip("请上传专科毕业证书！");
				return false;
			}
		}
		// 是否本科
		var isUndergraduate = $('input:radio[name="userResumeExt.isUndergraduate"]:checked').val();
		if ("Y" == isUndergraduate) {
			var isCollegeHaveDiploma = $('input:radio[name="userResumeExt.isCollegeHaveDiploma"]:checked').val();
			if ("Y" == isCollegeHaveDiploma && $("#undergraduateUrlValue").val() == "") {
				jboxTip("请上传本科毕业证书！");
				return false;
			}
			var isCollegeDegreeCertificate = $('input:radio[name="userResumeExt.isCollegeDegreeCertificate"]:checked').val();
			if ("Y" == isCollegeDegreeCertificate && $("#undergraduateDegreeUrlValue").val() == "") {
				jboxTip("请上传本科学位证书！");
				return false;
			}
		}
		// 是否博士
		var isDoctor = $('input:radio[name="userResumeExt.isDoctor"]:checked').val();
		if ("Y" == isDoctor) {
			var isDoctorDegreeCertificate = $('input:radio[name="userResumeExt.isDoctorDegreeCertificate"]:checked').val();
			if ("Y" == isDoctorDegreeCertificate && $("#doctorCertificateUrlValue").val() == "") {
				jboxTip("请上传博士学位证书！");
				return false;
			}
			var isDoctorHaveDiploma = $('input:radio[name="userResumeExt.isDoctorHaveDiploma"]:checked').val();
			if ("Y" == isDoctorHaveDiploma && $("#doctorEducationUrlValue").val() == "") {
				jboxTip("请上传博士学历证书！");
				return false;
			}
		}

		// 是否硕士
		var isMaster = $('input:radio[name="userResumeExt.isMaster"]:checked').val();
		if ("Y" == isMaster) {
			var isMasterHaveDiploma = $('input:radio[name="userResumeExt.isMasterHaveDiploma"]:checked').val();
			if ("Y" == isMasterHaveDiploma && $("#masterEducationUrlValue").val() == "") {
				jboxTip("请上传硕士学历证书！");
				return false;
			}
			var isMasterDegreeCertificate = $('input:radio[name="userResumeExt.isMasterDegreeCertificate"]:checked').val();
			if ("Y" == isMasterDegreeCertificate && $("#masterCertificateUrlValue").val() == "") {
				jboxTip("请上传硕士学位证书！");
				return false;
			}
		}


		var nameResult = 0;
		var workOrgName = $("#workOrgName").val();
		var personType = $('input:radio[name="doctor.doctorTypeId"]:checked').val();
		if("${resDocTypeEnumGraduate.id}"==personType){
			<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
			if ("${dict.dictName}" == workOrgName) {
				nameResult = 1;
			}
			</c:forEach>
			if (nameResult == 0) {
//				$("#psxx").val("");
//				$("#workOrgName").val("");
//				jboxTip("派送学校的值与字典不符,请重新输入！");
//				return false;
			}
		}
		nameResult = 0;
		if ("${resDocTypeEnumCompanyEntrust.id}" == personType || "${resDocTypeEnumCompany.id}" == personType) {
			<c:forEach items="${dictTypeEnumWorkOrgList}" var="dict">
			if ("${dict.dictName}" == workOrgName) {
				nameResult = 1;
			}
			</c:forEach>
			if (nameResult == 0) {
//				$("#psdw").val("");
//				$("#workOrgName").val("");
//				jboxTip("派送单位的值与字典不符,请重新输入！");
//				return false;
			}
		}

		var bkbyyxResult = 0;
		var zkbyyxResult = 0;
		var byyxResult = 0;
		var bsbyyxResult = 0;
		var zdyxResult = 0;
		var zdyx = $("#zdyx").val();
		var bkbyyx = $("#bkbyyx").val();
		var zkbyyx = $("#zkbyyx").val();
		var byyx = $("#byyx").val();
		var bsbyyx = $("#bsbyyx").val();
		<c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" >
		if ("${dict.dictName}" == bkbyyx) {
			bkbyyxResult = 1;
		}
		if ("${dict.dictName}" == byyx) {
			byyxResult = 1;
		}
		if ("${dict.dictName}" == bsbyyx) {
			bsbyyxResult = 1;
		}
		if ("${dict.dictName}" == zkbyyx) {
			zkbyyxResult = 1;
		}
		if ("${dict.dictName}" == zdyx) {
			zdyxResult = 1;
		}
		</c:forEach>
		if (zdyxResult == 0 && zdyx != "") {
			$("#zdyx").val("");
			jboxTip("在读院校的值与字典不符，请重输！");
			return false;
		}
		if (zkbyyxResult == 0 && zkbyyx != "") {
			$("#zkbyyx").val("");
			jboxTip("专科毕业院校的值与字典不符，请重输！");
			return false;
		}
		if (bkbyyxResult == 0 && bkbyyx != "") {
			$("#bkbyyx").val("");
			jboxTip("本科毕业院校的值与字典不符，请重输！");
			return false;
		}
		if (byyxResult == 0 && byyx != "") {
			$("#byyx").val("");
			jboxTip("硕士毕业院校的值与字典不符，请重输！");
			return false;
		}
		if (bsbyyxResult == 0 && bsbyyx != "") {
			$("#bsbyyx").val("");
			jboxTip("博士毕业院校的值与字典不符，请重输！");
			return false;
		}
		/!* 专业信息 *!/
		var zdzyResult = 0;
		var zdzy = $("#zdzy").val();
		var majorsResult = 0;
		var majors = $("#majors").val();
		var bkbyzyResult = 0;
		var bkbyzy = $("#bkbyzy").val();
		var ssbyzyResult = 0;
		var ssbyzy = $("#ssbyzy").val();
		var bsbyzyResult = 0;
		var bsbyzy = $("#bsbyzy").val();

		<c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict" >
		if ("${dict.dictName}" == zdzy) {
			zdzyResult = 1;
		}
		if ("${dict.dictName}" == majors) {
			majorsResult = 1;
		}
		if ("${dict.dictName}" == bkbyzy) {
			bkbyzyResult = 1;
		}
		if ("${dict.dictName}" == ssbyzy) {
			ssbyzyResult = 1;
		}
		if ("${dict.dictName}" == bsbyzy) {
			bsbyzyResult = 1;
		}
		</c:forEach>
		if (zdzyResult == 0 && zdzy != "") {
			$("#zdzy").val("");
			jboxTip("在读专业的值与字典不符，请重输！");
			return false;
		}
		if (majorsResult == 0 && majors != "") {
			$("#majors").val("");
			jboxTip("专科毕业专业的值与字典不符，请重输！");
			return false;
		}
		if (bkbyzyResult == 0 && bkbyzy != "") {
			$("#bkbyzy").val("");
			jboxTip("本科毕业专业的值与字典不符，请重输！");
			return false;
		}
		if (ssbyzyResult == 0 && ssbyzy != "") {
			$("#ssbyzy").val("");
			jboxTip("硕士毕业专业的值与字典不符，请重输！");
			return false;
		}
		if (bsbyzyResult == 0 && bsbyzy != "") {
			$("#bsbyzy").val("");
			jboxTip("博士毕业专业的值与字典不符，请重输！");
			return false;
		}
		var isPassQualifyingExamination = $("input[name='userResumeExt.isPassQualifyingExamination']:checked").val() || "";
		var isHaveQualificationCertificate = $("input[name='userResumeExt.isHaveQualificationCertificate']:checked").val() || "";
		var isHavePracticingCategoryCheck = $("input[name='userResumeExt.isHavePracticingCategory']:checked").val() || "";
		if (isPassQualifyingExamination == "Y" && isHaveQualificationCertificate != "Y") {
			if ($("#qualificationMaterialId2UrlValue").val() == "") {
				jboxTip("请上传成绩单附件！");
				return false;
			}
		}
		/*if(isHaveQualificationCertificate == "Y"){
             var value = $("#CertificateCode").val();
             re = (/^[a-z0-9]{27}$/i).test(value) ;
             if(!re){
                 jboxTip("请按要求填写医师资格证书编码！");
                 return false;
             }
         }
         if(isHavePracticingCategoryCheck == "Y"){
             var value = $("#categoryCode").val();
             re = (/^[a-z0-9]{15}$/i).test(value) ;
             if(!re){
                 jboxTip("请按要求填写医师执业证书编码！");
                 return false;
             }
         }*/
		getUserResumeExtName();
		getFantasticFour();
		var info = $("#doctorForm").serialize();
		var url = "<s:url value='/jsres/doctor/saveDoctorInfo'/>";
		jboxPost(url, $("#doctorForm").serialize(),
				function (resp) {
					if (resp == "${GlobalConstant.USER_ID_NO_REPETE}") {
						var cretTypeId = $("#cretTypeId").val();
						if ("${certificateTypeEnumShenfenzheng.id}" == cretTypeId) {
							jboxTip(resp);
						} else {
							jboxTip("该证件号已经被注册！");
						}
						$("input[name='user.idNo']").focus();
					} else if (resp == "${GlobalConstant.USER_PHONE_REPETE}") {
						jboxTip(resp);
						$("input[name='user.userPhone']").focus();
					} else if (resp != "${GlobalConstant.SAVE_FAIL}") {
						jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
						setTimeout(function () {
							$("#topUserName").text(resp);
							doctorInfo('${user.userFlow}');
						}, 1500)
					}
				}, null, false);
	}

	function getFantasticFour() {
		if ($(':radio[name="userResumeExt.isDoctor"]:checked').val() == '${GlobalConstant.FLAG_Y}') {
//		$("#uEducationName").val("博士");
			$("#dGraduatedName").val($("#bsbyyx").val());
			$("#dSpecialized").val($("#bsbyzy").val());
			$("#dGraduationTime").val($("#bsbysj").val());
		} else if ($(':radio[name="userResumeExt.isMaster"]:checked').val() == '${GlobalConstant.FLAG_Y}') {
//		$("#uEducationName").val("硕士");
			$("#dGraduatedName").val($("#byyx").val());
			$("#dSpecialized").val($("#ssbyzy").val());
			$("#dGraduationTime").val($("#ssbysj").val());
		} else {
//		$("#uEducationName").val($("#degree  option:selected").text());
			$("#dGraduatedName").val($("#bkbyyx").val());
			$("#dSpecialized").val($("#bkbyzy").val());
			$("#dGraduationTime").val($("#bkbysj").val());
		}
	}

	function getUserResumeExtName() {
		var technologyQualificationId = $("#technologyQualificationId").val();
		var technologyQualificationName = "";
		if (technologyQualificationId != "") {
			technologyQualificationName = $("#technologyQualificationId :selected").text();
		}
		$("#technologyQualificationName").val(technologyQualificationName);

		var qualificationMaterialId = $("#qualificationMaterialId").val();
		var qualificationMaterialName = "";
		if (qualificationMaterialId != "") {
			qualificationMaterialName = $("#qualificationMaterialId :selected").text();
		}
		$("#qualificationMaterialName").val(qualificationMaterialName);

		var qualificationMaterialId2 = $("#qualificationMaterialId2").val();
		var qualificationMaterialName2 = "";
		if (qualificationMaterialId2 != "") {
			qualificationMaterialName2 = $("#qualificationMaterialId2 :selected").text();
		}
		$("#qualificationMaterialName2").val(qualificationMaterialName2);

		var practicingCategoryId = $("#practicingCategoryId").val();
		var practicingCategoryName = "";
		if (practicingCategoryId != "") {
			practicingCategoryName = $("#practicingCategoryId :selected").text();
		}
		$("#practicingCategoryName").val(practicingCategoryName);

		var practicingCategoryLevelId = $("#practicingCategoryLevelId").val();
		var practicingCategoryLevelName = "";
		if (practicingCategoryLevelId != "") {
			practicingCategoryLevelName = $("#practicingCategoryLevelId :selected").text();
		}
		$("#practicingCategoryLevelName").val(practicingCategoryLevelName);

		var practicingScopeId = $("#practicingScopeId").val();
		var practicingScopeName = "";
		if (practicingScopeId != "") {
			practicingScopeName = $("#practicingScopeId :selected").text();
		}
		$("#practicingScopeName").val(practicingScopeName);

		var practicingCategoryScopeId = $("#practicingCategoryScopeId").val();
		var practicingCategoryScopeName = "";
		if (practicingCategoryScopeId != "") {
			practicingCategoryScopeName = $("#practicingCategoryScopeId :selected").text();
		}
		$("#practicingCategoryScopeName").val(practicingCategoryScopeName);
	}

	/**
	 * 上传头像
	 */
	function uploadImage() {
		$.ajaxFileUpload({
			url: "<s:url value='/sys/user/userHeadImgUpload'/>?userFlow=${user.userFlow}",
			secureuri: false,
			fileElementId: 'imageFile',
			dataType: 'json',
			success: function (data, status) {
				if (data.indexOf("userImages") == -1) {
					jboxTip(data);
				} else {
					jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
					var arr = new Array();
					arr = data.split(":");
					$("#userImg").val(arr[0]);
					var url = "${sysCfgMap['upload_base_url']}/" + arr[0];
					$("#showImg").attr("src", url);
					$("#headimgurl").val(arr[0]);
				}
			},
			error: function (data, status, e) {
				jboxTip('${GlobalConstant.UPLOAD_FAIL}');
			},
			complete: function () {
				$("#imageFile").val("");
			}
		});
	}

	function uploadFile(type, typeName) {
		var url = "<s:url value='/jsres/doctor/uploadFile'/>?userFlow=${user.userFlow}&operType=" + type;
		jboxOpen(url, "上传" + typeName, 525, 200);
	}

	function uploadIdCardFile(type, typeName) {
		var url = "<s:url value='/jsres/doctor/uploadIdCardFile'/>?userFlow=${user.userFlow}&operType=" + type;
		jboxOpen(url, "上传" + typeName, 525, 200);
	}

	function uploadFinishingPhoto(type, typeName) {
		var url = "<s:url value='/jsres/doctor/uploadUserHeadPortrait'/>?userFlow=${user.userFlow}&operType=" + type;
		jboxOpen(url, "上传" + typeName, 560, 230);
	}

	function delFile(type) {
		jboxConfirm("确认删除？", function () {
			$("#" + type + "Del").hide();
			$("#" + type + "Span").hide();
			$("#" + type).text("上传");
			$("#" + type).addClass("validate[required]");
			$("#" + type + "Value").val("");
			$("#file").val(null);
		});
	}

	function delFileNotValidate(type) {
		jboxConfirm("确认删除？", function () {
			$("#" + type + "Del").hide();
			$("#" + type + "Span").hide();
			$("#" + type).text("上传");
			$("#" + type + "Value").val("");
			$("#file").val(null);
		});
	}

	function delFilets(type) {
		jboxConfirm("确认删除？", function () {
			$("#" + type + "Del").hide();
			$("#" + type + "Span").hide();
			$("#" + type).text("上传");
			$("#" + type + "Value").val("");
			$("#file").val(null);
		});
	}

	function addValidate(cretTypeId) {
		if ("${certificateTypeEnumShenfenzheng.id}" == cretTypeId) {
			// 校验身份证信息
			var idNo = $("input[name='user.idNo']").val();
			if (idNo) {
				checkIdCard(idNo);
			}
			$("#idNo").addClass("validate[custom[chinaId]]");
			$("#idNo").attr("onchange", "writeBirthday(this);checkUserUnique();");
			$("#userBirthday").attr("disabled", true);
//			$("#birthday").attr("disabled",false);
			$("#birthday").val($("#userBirthday").val());
			writeBirthday($("#idNo"));
		} else {
			$("#idNo").removeClass("validate[custom[chinaId]]");
			$("#idNo").attr("onchange", "checkUserUnique();");
			$("#userBirthday").attr("disabled", false);
//			$("#birthday").attr("disabled",true);
		}
	}

	function writeBirthday(obj) {
		var idNo = $(obj).val();
		var birthDayObj = $("#userBirthday");
		var birthDay = "";
		if (idNo.length == 15) {
			birthDay = "19" + idNo.substr(6, 2) + "-" + idNo.substr(8, 2) + "-" + idNo.substr(10, 2);
		} else if (idNo.length == 18) {
			birthDay = idNo.substr(6, 4) + "-" + idNo.substr(10, 2) + "-" + idNo.substr(12, 2);
		} else {
			return false;
		}
		birthDayObj.val(birthDay);
		$("#birthday").val(birthDay);
	}

	function changeWorkAdress(doctorTypeId) {

		if (doctorTypeId == "${resDocTypeEnumCompanyEntrust.id}") {
			$(".workUniteCreditCode").show();
		} else {
			$("#workUniteCreditCode").val("");
			$(".workUniteCreditCode").hide();
		}
		if (doctorTypeId == "${resDocTypeEnumCompany.id}" || doctorTypeId == "${resDocTypeEnumCompanyEntrust.id}") {
			if ($("#Td").is(":hidden")) {
				$("#medicalHeaithOrgIdTd").attr("colspan", 4);
			} else {
				$("#medicalHeaithOrgIdTd").removeAttr("colspan");
			}
			$(".school").hide();
			$(".address").show();
			$("#psxx").val("");
			$("#doctorTypeNameTd").removeAttr("colspan");
// 		changeOrgLevel("${userResumeExt.medicalHeaithOrgId}");
		}
		if (doctorTypeId == "${resDocTypeEnumSocial.id}") {
			$(".school").hide();
			$(".hospital").hide();
			$(".medical").hide();
			$(".hospital").val("");
			$(".medical").val("");
			$(".address").hide();
			$(".address").val("");
			$("#psdw").val("");
			$("#psxx").val("");
			$("#orgRank").val("");
			$("#orgLevel").val("");
			$("#doctorTypeNameTd").attr("colspan", 4);
		}
		if (doctorTypeId == "${resDocTypeEnumGraduate.id}") {
			$(".address").hide();
			$(".hospital").hide();
			$(".address").val("");
			$(".medical").hide();
			$(".hospital").val("");
			$(".medical").val("");
			$("#psdw").val("");
			$(".school").show();
			$("#orgRank").val("");
			$("#orgLevel").val("");
			$("#doctorTypeNameTd").removeAttr("colspan");
			// 如为“在校专硕”默认为在读，关闭应往届选项
			$("#isReadingY").attr("checked", true);
		}
		if (doctorTypeId == "") {
			$(".school").hide();
			$(".address").hide();
			$("#doctorTypeNameTd").attr("colspan", 4);
		}
	}

	$(function () {
		$('.datepicker').datepicker();
		addValidate("${user.cretTypeId}");
		changeWorkAdress("${doctor.doctorTypeId}");

		$("#zdzy").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		$("#byyx").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		$("#bkbyyx").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		$("#zdyx").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		$("#zkbyyx").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		$("#majors").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		$("#bkbyzy").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		$("#ssbyzy").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		$("#bsbyzy").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		/*	$("#psxx").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });*/
		$("#psdw").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		$("#bsbyyx").likeSearchInit({
			clickActive: function (flow) {
				$("." + flow).show();
			}
		});
		changeTitle();
		changeLevel("${doctor.workOrgLevelId}");
		$("#showImg").load(function () {
			$("#idNoWatermark").show();
		});
		if ("${user.cretTypeId eq certificateTypeEnumShenfenzheng.id }" && $("${user.userBirthday}").val() == "") {
//			$("#birthday").attr("disabled",true);
		}
		$("." + "377").remove();
		if ("${userResumeExt.graduatedName}" == "无") {
			$(".bk").removeClass("validate[required]");
			$(".yy").hide();
		}

		var tabCourse = $('.icon-head');
		tabCourse.on('mouseover', function () {
			$("#changeInfo").show();
		});
		tabCourse.on('mouseout', function () {
			$(".pxxx").hide();
		});
		//隐藏本科，硕士和博士操作
		/*checkBx("${userResumeExt.isMaster}", 'master');
		checkBx("${userResumeExt.isDoctor}", 'doctor');*/

		// 本科专科 在读
		<%--checkBx("${userResumeExt.isUndergraduate}", 'undergrad');--%>

		/*checkBx("${userResumeExt.isJuniorCollege}", 'junior');*/
		checkBx("${userResumeExt.isReading}", 'reading');
		<%--changeOrgLevel("${userResumeExt.medicalHeaithOrgId}");--%>


		// 是否本科
		if ("Y" == '${userResumeExt.isUndergraduate}') {
			showDegree("undergrad", "${userResumeExt.isCollegeDegreeCertificate}");
		}
		showDegree("undergrad", "${userResumeExt.isCollegeDegreeCertificate}");
		showDegree("master", "${userResumeExt.isMasterDegreeCertificate}");
		showDegree("doctor", "${userResumeExt.isDoctorDegreeCertificate}");
		westernSupport("${userResumeExt.westernSupportResidents}");

		showTime("${userResumeExt.isPassQualifyingExamination}");
		examsTime("${userResumeExt.isHaveQualificationCertificate}");
		showTime2("${userResumeExt.isHavePracticingCategory}");

		showOtherInfo("${userResumeExt.readingSchoolName}", 'reading', 'School');
		showOtherInfo("${userResumeExt.readingProfession}", 'reading', 'Profession');
		showOtherInfo("${userResumeExt.juniorCollegeSchoolName}", 'junior', 'School');
		showOtherInfo("${userResumeExt.juniorCollegeProfession}", 'junior', 'Profession');
		showOtherInfo("${userResumeExt.masterGraSchoolName}", 'master', 'School');
		showOtherInfo("${userResumeExt.masterMajor}", 'master', 'Profession');
		showOtherInfo("${userResumeExt.doctorGraSchoolName}", 'doctor', 'School');
		showOtherInfo("${userResumeExt.doctorMajor}", 'doctor', 'Profession');
		showOtherInfo("${userResumeExt.graduatedName}", 'undergra', 'School');
		showOtherInfo("${userResumeExt.specialized}", 'undergra', 'Profession');

		// 国籍、英语
		changeClassInfo("nationality", "${user.nationalityId}");
		changeClassInfo("gradeExamType", "${doctor.englishGradeExamType}");


	});

	// 展示本科，硕士、博士、专科、在读其他专业信息
	function showOtherInfo(code, type, value) {
		if (code) {
			if (code == '其他院校') {
				$("." + type + value + "Td").attr("colspan", "1");
				$("." + type + "ChangeSchool").show();
			} else if (code == '其他专业') {
				$("." + type + value + "Td").attr("colspan", "1");
				$("." + type + "ChangeProfession").show();
			}
		}
	}

	function changeTitle() {
		$(".doctorType").each(function () {
			if ($(this).text() == "${resDocTypeEnumCompany.name}") {
				$(this).attr("title", "已落实工作岗位参加住院医师规范化培训的人员");
			}
			if ($(this).text() == "${resDocTypeEnumCompanyEntrust.name}") {
				$(this).attr("title", "已落实工作岗位参加住院医师规范化培训的人员");
			}
			if ($(this).text() == "${resDocTypeEnumSocial.name}") {
				$(this).attr("title", "未落实工作岗位参加住院医师规范化培训的人员");
			}
			if ($(this).text() == "${resDocTypeEnumGraduate.name}") {
				$(this).attr("title", "考入高等医学院校临床专业学位硕士研究生的在校学生");
			}
		});

	}

	function changeBirthday() {
		$("#birthday").val($("#userBirthday").val());
//		$("#birthday").attr("disabled",true);
	}

	function changeLevel(orgLevel) {
		if (orgLevel != "") {
			$("#" + orgLevel).show();
		}
	}

	function changeOrgLevel(value) {
		if (value != "") {
			$(".dict").hide();
			$("#" + value).show();
		} else {
			$(".dict").hide();
		}
		if (value == "1") {
			$(".medical").hide();
			$(".medical").val("");
			$("#medicalHeaithOrgIdTd").removeAttr("colspan");
			$(".hospital").show();
			$("#TD").attr("colspan", 2);
		}
		if (value == "2") {
			$(".medical").hide();
			$(".medical").val("");
			$("#medicalHeaithOrgIdTd").attr("colspan", 4);
			$(".hospital").hide();
			$(".hospital").val("");
		}
		if (value == "") {
			$(".medical").hide();
			$(".medical").val("");
			$("#medicalHeaithOrgIdTd").attr("colspan", 4);
			$(".hospital").hide();
			$(".hospital").val("");
		}
		if (value == "3") {
			$(".medical").show();
			$(".hospital").hide();
			$(".hospital").val("");
			$("#medicalHeaithOrgIdTd").removeAttr("colspan");
		}
	}

	function changeOrg() {
		var school = $("#psxx").val();
		var org = $("#psdw").val();
		var personType = $('input:radio[name="doctor.doctorTypeId"]:checked').val();
		if (personType == "${resDocTypeEnumCompany.id}") {
			$("#workOrgName").val(org);
		}
		if (personType == "${resDocTypeEnumCompanyEntrust.id}") {
			$("#workOrgName").val(org);
		}
		if (personType == "${resDocTypeEnumGraduate.id}") {
			$("#workOrgName").val(school);
		}
		if (personType == "${resDocTypeEnumSocial.id}") {
			$("#workOrgName").val("");
		}
	}

	function checkWorkOrgName(obj) {
		var school = obj.value;
		var org = $("#psdw").val();
		var personType = $('input:radio[name="doctor.doctorTypeId"]:checked').val();
		if (personType == "${resDocTypeEnumCompany.id}") {
			$("#workOrgName").val(org);
		}
		if (personType == "${resDocTypeEnumCompanyEntrust.id}") {
			$("#workOrgName").val(org);
		}
		if (personType == "${resDocTypeEnumGraduate.id}") {
			$("#workOrgName").val(school);
		}
		if (personType == "${resDocTypeEnumSocial.id}") {
			$("#workOrgName").val("");
		}
	}
	function changeYx(obj) {
		var bkbyyx = $(obj).attr("value");
		if (bkbyyx == "无" || bkbyyx == "") {
			$(".bk").removeClass("validate[required]");
			$(".yy").hide();
		} else {
			$(".bk").addClass("validate[required]");
			$(".yy").show();
		}
	}

	function checkBx(value, type) {
		if (value == "${GlobalConstant.FLAG_N}") {
			$("." + type).hide();
			$("." + type + "Th").hide();
			$("." + type + "Td").attr("colspan", 3);
			$("." + type).val("");
			$("." + type + "iSCertificate").val("");
			$("." + type + "Red").hide();
			showDiploma(type, value);
			showDegree(type, value);
		} else {
			$("." + type).show();
			$("." + type + "Th").show();
			$("." + type + "Td").removeAttr("colspan");
			$("." + type + "Red").show();
			var val1 = $("." + type + "HaveDiploma:checked").val();
			if (val1 == "Y") {
				showDiploma(type, val1);
			}
			/*var val2 = $("."+type+"DegreeCertificate:checked").val();
            if(val2=="Y"){
                showDegree(type,val2);
            }*/
		}
	}

	// 填写其他院校或者专业
	function changeSchoolOrProfession(value, code, type) {
		if (code == '9999999999' || code == '99999999') {
			$("." + value + type + "Td").attr("colspan", "1");
			if (type == 'School') {
				// 其他院校
				$("." + value + "ChangeSchool").show();
			} else if (type == 'Profession') {
				// 其他专业
				$("." + value + "ChangeProfession").show();
			}
		} else {
			$("." + value + type + "Td").attr("colspan", "4");
			if (type == 'School') {
				$("." + value + "ChangeSchool").hide();

			} else if (type == 'Profession') {
				$("." + value + "ChangeProfession").hide();
			}
		}

	}

	// 应届生
	function checkYJS(value, type) {
		if (value == "${GlobalConstant.FLAG_Y}") {
			type = "master";
			$("." + type).hide();
			$("." + type + "Th").hide();
			$("." + type + "Td").attr("colspan", 3);
			$("." + type).val("");
			$("." + type + "Red").hide();
			$("." + type + "Th1").each(function () {
				$(this).val("");
			});
			$("#" + type + "Th1").hide();
			$("." + type + "Th1").hide();

			$("#" + type + "Th2").hide();
			$("." + type + "Th2").hide();
			$("." + type + "Th2").each(function () {
				$(this).val("");
			});

			$("." + type + "Th").each(function () { // +"Cls"
				$(this).val("");
			});
			type = "doctor";
			$("." + type).hide();
			$("." + type + "Th").hide();
			$("." + type + "Th1").hide();
			$("." + type + "Th2").hide();
			$("." + type + "Td").attr("colspan", 3);
			$("." + type).val("");
			$("." + type + "Red").hide();
			$("." + type + "Th1").each(function () {
				$(this).val("");
			});
			$("#" + type + "Th1").hide();
			$("." + type + "Th1").hide();

			$("#" + type + "Th2").hide();
			$("." + obj + "Th2").hide();
			$("." + type + "Th2").each(function () {
				$(this).val("");
			});
			$("." + type + "Th").each(function () { // +"Cls"
				$(this).val("");
			});
		} else {
			type = "master";
			$("." + type).show();
			$("." + type + "Th").show();
			$("." + type + "Td").removeAttr("colspan");
			$("." + type + "Red").show();
			var val1 = $("." + type + "HaveDiploma:checked").val();
			$("#" + type + "Th1").show();
			$("." + type + "Th1").show();
			$("#" + type + "Th2").show();
			$("." + type + "Th2").show();
			type = "doctor";
			$("." + type).show();
			$("." + type + "Th").show();
			$("." + type + "Th1").show();
			$("." + type + "Th2").show();
			$("." + type + "Td").removeAttr("colspan");
			$("." + type + "Red").show();
			var val1 = $("." + type + "HaveDiploma:checked").val();
			$("#" + type + "Th1").show();
			$("." + type + "Th1").show();
			$("#" + type + "Th2").show();
			$("." + type + "Th2").show();
		}
	}


	// 专科
	function checkZk(value, type) {
		if (value == "${GlobalConstant.FLAG_N}") {
			$("." + type).hide();
			$("." + type + "Th").hide();
			$("." + type + "Td").attr("colspan", 3);
			$("." + type).val("");
			$("." + type + "Red").hide();
			showDiploma(type, value);
			showDegree(type, value);
			$("." + type + "Th").each(function () { // +"Cls"
				$(this).val("");
			});
		} else {
			$("." + type).show();
			$("." + type + "Th").show();
			$("." + type + "Td").removeAttr("colspan");
			$("." + type + "Red").show();
			var val1 = $("." + type + "HaveDiploma:checked").val();
			if (val1 == "Y") {
				showDiploma(type, val1);
			}
			/*var val2 = $("."+type+"DegreeCertificate:checked").val();
            if(val2=="Y"){
                showDegree(type,val2);
            }*/
		}
	}

	function changeDegreeType(obj, type) {
		if (type == "master" && $(obj).val() != "") {
			$("#masterDegreeTypeName").val($(obj).find("option:selected").text());
		}
		if (type == "master" && $(obj).val() == "") {
			$("#masterDegreeTypeName").val("");
		}
		if (type == "doctor" && $(obj).val() != "") {
			$("#doctorDegreeTypeName").val($(obj).find("option:selected").text());
		}
		if (type == "doctor" && $(obj).val() == "") {
			$("#doctorDegreeTypeName").val("");
		}
	}

	function showDiploma(obj, flag) {
		if (flag == "Y") {
			$("#" + obj + "Th1").show();
			$("." + obj + "Th1").show();
		} else {
			$("." + obj + "Th1").each(function () {
				$(this).val("");
			});
			$("#" + obj + "Th1").hide();
			$("." + obj + "Th1").hide();
		}
	}

	function showDegree(obj, flag) {
		if (flag == "Y") {
			$("#" + obj + "Th2").show();
			$("." + obj + "Th2").show();
		} else {
			$("#" + obj + "Th2").hide();
			$("." + obj + "Th2").hide();
			$("." + obj + "Th2").each(function () {
				$(this).val("");
			});
		}
	}

	function showCertificate(obj, flag) {
		if (flag == "Y") {
			$("." + obj + "Th3").show();
		} else {
			$("." + obj + "Th3").hide();
		}
	}

	function westernSupport(flag) {
		if (flag == "Y") {
			$(".western").show();
		} else {
			$(".westernValue").each(function () {
				$(this).val("");
			});
			$(".western").hide();
		}
	}

	function examsTime(flag) {
		if (flag == "Y") {
			$(".examTime").show();
			$("input[name='userResumeExt.isHavePracticingCategory'][value='Y']").removeAttr("disabled");
		} else {
			$(".examTimeValue").each(function () {
				$(this).val("");
			});
			$(".examTime").hide();
			showTime2("N");
			$("input[name='userResumeExt.isHavePracticingCategory'][value='Y']").attr("disabled", "disabled");
			$("input[name='userResumeExt.isHavePracticingCategory'][value='N']").attr("checked", "checked");
		}
		showScoreType();
	}

	/**
	 * 专科或者本科
	 * @param flag
	 * @param value
	 */
	function examsCertificate(flag, inputName, classValue) {
		console.log(flag, inputName, classValue)
		if (flag == "Y") {
			$("." + classValue).show();
			$("input[name=inputName][value='Y']").removeAttr("disabled");
		} else {
			$("." + classValue).hide();
			//showTime2("N");
			$("input[name=inputName][value='Y']").attr("disabled", "disabled");
			$("input[name=inputName][value='N']").attr("checked", "checked");
		}
		showScoreType();
	}

	function showTime(flag) {
		if (flag == "Y") {
			$(".examinationTime").show();
			$("input[name='userResumeExt.isHaveQualificationCertificate'][value='Y']").removeAttr("disabled");
			$("input[name='userResumeExt.isHavePracticingCategory'][value='Y']").removeAttr("disabled");
			$(".tsgwzmcl").attr("colspan", "3");
		} else {
			$(".examinationTimeValue").each(function () {
				$(this).val("");
			});
			$(".examinationTime").hide();
			examsTime("N");
			$("input[name='userResumeExt.isHaveQualificationCertificate'][value='Y']").attr("disabled", "disabled");
			$("input[name='userResumeExt.isHaveQualificationCertificate'][value='N']").attr("checked", "checked");
			showTime2("N");
			$("input[name='userResumeExt.isHavePracticingCategory'][value='Y']").attr("disabled", "disabled");
			$("input[name='userResumeExt.isHavePracticingCategory'][value='N']").attr("checked", "checked");
			$(".tsgwzmcl").attr("colspan", "1");
		}
		showScoreType();
	}

	function showScoreType() {
		var isPassQualifyingExamination = $("input[name='userResumeExt.isPassQualifyingExamination']:checked").val() || "";
		var isHaveQualificationCertificate = $("input[name='userResumeExt.isHaveQualificationCertificate']:checked").val() || "";
		if (isPassQualifyingExamination == "Y" && isHaveQualificationCertificate != "Y") {
			$(".scoreType").show();
		} else {
			$(".scoreType").hide();
		}

	}

	function showTime2(flag) {
		if (flag == "Y") {
			$(".showTime2").show();
		} else {
			$(".showTime2Value").each(function () {
				$(this).val("");
			});
			$(".showTime2").hide();
		}
	}

	// 英语能力
	function changeEnglishAbility(value) {
		// 未通过
		if (value == '1') {
			$(".englishTr1").hide();
			//$(".englishTr2").hide();
		} else {
			$(".englishTr1").show();
			//$(".englishTr2").show();
		}
	}

	function changeClassInfo(type, value, requiredClass, divClass) {
		// 切换国籍，修改
		if ("nationality" == type) {
			console.log(value == "");
			if (value != 'CHN' && value != "") {
				$(".ofProvince").removeClass("validate[required]");
				$(".chnProvince").hide();
				//测试要求去除
				// $("#qtCountry").show();
			} else {
				$(".ofProvince").addClass("validate[required]");
				$(".chnProvince").show();
				//测试要求去除
				// $("#qtCountry").hide();
			}
		}
		var englishAbility =$("#englishAbility").find("option:selected").text();
		if ("gradeExamType" == type) {
			// 未参加考试
			console.log(value);
			if (value == '5') {
				$(".englishTr1").hide();
				$(".englishTr2").hide();
				$(".englishTh").hide();
				$(".englishClass").attr("colspan", "4");
			} else if (value == '4') {
				$(".englishClass").attr("colspan", "4");
				$(".englishTr1").show();
				$(".englishTr2").show();
				$(".englishTh").hide();
			} else if (value == '1' || value == '2' || value == '3') {
				$(".englishTr2").hide();
				$(".englishTh").show();
				$(".englishClass").attr("colspan", "");
				$(".englishTr1").show();
				if("未通过" == englishAbility){
					$(".englishTr1").hide();
				}
			}

		}
	}

	function checkValue(obj, number) {
		var value = $("#cretTypeId :selected").val();
		if('01' == value){
			var re = "";
			if (number == 27) {
				re = (/^[a-z0-9]{27}$/i).test(obj.value);
			} else if (number == 15) {
				re = (/^[a-z0-9]{15}$/i).test(obj.value);
			}
			if (!re) {
				$('#' + obj.id + "_span").html("请输入" + number + "位编码");
				$('#' + obj.id).val("")
			} else {
				$('#' + obj.id + "_span").html("");
			}
		}else{
			$('#' + obj.id + "_span").html("");
		}

	}

	function xz(){
		if(${empty doctor.trainingTypeId}){
			var url="<s:url value='/jsres/doctor/chooseType'/>";
			jboxOpen(url, "人员类型", 350, 200);
		}
	}
	$(document).ready(function(){
		xz();
	});
</script>
<div class="infoAudit" <c:if test="${empty param.openType}">style="height: 1150px;"</c:if>>
	<form id="doctorForm" style="position:relative;">
		<input type="hidden" name="user.userFlow" value="${user.userFlow}"/>
		<input type="hidden" name="doctor.doctorStatusId" value="${doctor.doctorStatusId}"/>
		<input type="hidden" id="doctorFlow" name="doctor.doctorFlow" value="${doctor.doctorFlow}"/>
		<input type="hidden" id="technologyQualificationName" name="userResumeExt.technologyQualificationName"
			   value="${userResumeExt.technologyQualificationName}"/>
		<input type="hidden" id="qualificationMaterialName" name="userResumeExt.qualificationMaterialName"
			   value="${userResumeExt.qualificationMaterialName}"/>
		<input type="hidden" id="qualificationMaterialName2" name="userResumeExt.qualificationMaterialName2"
			   value="${userResumeExt.qualificationMaterialName2}"/>
		<input type="hidden" id="practicingCategoryName" name="userResumeExt.practicingCategoryName"
			   value="${userResumeExt.practicingCategoryName}"/>
		<input type="hidden" id="practicingCategoryLevelName" name="userResumeExt.practicingCategoryLevelName"
			   value="${userResumeExt.practicingCategoryLevelName}"/>
		<input type="hidden" id="practicingScopeName" name="userResumeExt.practicingScopeName"
			   value="${userResumeExt.practicingScopeName}"/>
		<input type="hidden" id="practicingCategoryScopeName" name="userResumeExt.practicingCategoryScopeName"
			   value="${userResumeExt.practicingCategoryScopeName}"/>
		<input type="hidden" id="birthday" name="user.userBirthday" value="${user.userBirthday }"/>
		<!-- 学位类型Name-->
		<input type="hidden" id="masterDegreeTypeName" name="userResumeExt.masterDegreeTypeName"
			   value="${userResumeExt.masterDegreeTypeName}"/>
		<input type="hidden" id="doctorDegreeTypeName" name="userResumeExt.doctorDegreeTypeName"
			   value="${userResumeExt.doctorDegreeTypeName }"/>
		<!-- 培训登记手册完成情况-->
		<input type="hidden" id="registeManua" name="userResumeExt.registeManua"
			   value="${userResumeExt.registeManua }"/>
		<!-- 最高学历相关信息-->
		<input type="hidden" id="uEducationName" name="user.educationName" value="${user.educationName}"/>
		<input type="hidden" id="dGraduatedName" name="doctor.graduatedName" value="${doctor.graduatedName}"/>
		<input type="hidden" id="dSpecialized" name="doctor.specialized" value="${doctor.specialized}"/>
		<input type="hidden" id="dGraduationTime" name="doctor.graduationTime" value="${doctor.graduationTime}"/>
		<input type="hidden" id="trainingType" value="">

		<div class="search_table" style="margin-top:20px;">
			<h4>基本信息</h4><h6 style="color: red;padding-top: 5px;">（除头像外所有信息修改后需要点击下方保存之后生效）</h6>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="20%"/>
					<col width="26%"/>
					<col width="16%"/>
					<col width="23%"/>
					<col width="15%"/>
				</colgroup>
				<tbody>
				<tr>
					<th><span class="red">*</span>姓&#12288;&#12288;名：</th>
					<td>
						<input name="user.userName" id="userName" value="${user.userName}" class="validate[required] input"/>&#12288;
					</td>
					<th><span class="red">*</span>性&#12288;&#12288;别：</th>
					<td>
						&nbsp;<label><input type="radio" class='validate[required]' style="width:auto;"
											name="user.sexId"
											value='${userSexEnumMan.id}' ${user.sexId eq userSexEnumMan.id?'checked':''}/>&nbsp;${userSexEnumMan.name}
					</label>&#12288;
						<label><input type="radio" class='validate[required]' style="width:auto;" name="user.sexId"
									  value='${userSexEnumWoman.id}' ${user.sexId eq userSexEnumWoman.id?'checked':''}/>&nbsp;${userSexEnumWoman.name}
						</label>&#12288;
					</td>
					<%--<td rowspan="5" style="text-align: center;">
                        <font id="idNoWatermark" style="display:none;font-size:9px;color:red; position:relative; top: 110px;">${user.idNo }</font>
                        <img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" style="margin-top: -30px;" width="130px" height="150px"
                             onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
                        <div style="cursor: pointer; display:block;padding-top: 10px;">
                            [<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
                            <img  class="icon-head"  src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/>
                            <div id="changeInfo" class="pxxx" style="display: none;">
                                <div class="changeinfo" id="changeInfoContent" style="height: 150px;">
                                    <i class="icon_up"></i>
                                    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%">
                                        <caption style="border-bottom: none;line-height: 20px;color: black;font-size: xx-small;"><label class="red">*&#12288;</label>照片要求：<br/>1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立。<br/>2、该照片用于结业证书，请慎重认真上传！</caption>
                                    </table>
                                </div>
                            </div>

                        </div>
                        <input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
                        <input type="hidden" id="headimgurl" value=""/>
                    </td>--%>
					<td style="text-align: left;;width:200px" rowspan="5">
						<div style="width: 110px;height: 130px;margin: 5px auto 0px;">
							<img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg" width="100%"
								 height="100%" onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
						</div>
						<div style="text-align: center;">
							<span style="cursor: pointer;text-align: center;">
								[<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
							</span>
							<img class="icon-head" src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/>
							<div id="changeInfo" class="pxxx" style="display: none;">
								<div class="changeinfo" id="changeInfoContent" style="height: 150px;">
									<i class="icon_up"></i>
									<table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%">
										<caption
												style="border-bottom: none;line-height: 20px;color: black;font-size: xx-small;">
											<label class="red">*&#12288;</label>照片要求：<br/>1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，图片大小大于150K且小于300K，人像正立。<br/>2、该照片用于结业证书，请慎重认真上传！
										</caption>
									</table>
								</div>
							</div>
							<input type="file" id="imageFile" name="headImg" style="display: none"
								   onchange="uploadImage();"/>
							<input type="hidden" id="headimgurl" value="${user.userHeadImg}"/>
						</div>
					</td>
					<%--<td rowspan="5" style="text-align: center;">
                        <font id="idNoWatermark" style="display:none;font-size:9px;color:red; position:relative; top: 110px;">${user.idNo }</font>
                        <img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg"
                             style="margin-top: -30px;" width="130px" height="160px"
                             onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
                        <div style="cursor: pointer; display:block;padding-top: 0px;">
                            [<a id="userHeadImg" href="javascript:uploadFinishingPhoto('imageFile','头像');" >${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
                            &lt;%&ndash;[<a id="degreeUri" href="javascript:uploadFinishingPhoto('degreeUri','学位证书');" >${empty user.userHeadImg?'上传头像':'重新上传'}</a>]&ndash;%&gt;
                            &lt;%&ndash;[<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'头像2':'重新2'}</a>]&ndash;%&gt;
                            &lt;%&ndash;<img  class="icon-head"  src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/>
                            <div id="changeInfo" class="pxxx" style="display: none;">
                                <div class="changeinfo" id="changeInfoContent" style="height: 150px;">
                                    <i class="icon_up"></i>
                                    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%">
                                        <caption style="border-bottom: none;line-height: 20px;color: black;font-size: xx-small;"><label class="red">*&#12288;</label>照片要求：<br/>1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立。<br/>2、该照片用于结业证书，请慎重认真上传！</caption>
                                    </table>
                                </div>
                            </div>&ndash;%&gt;
                        </div>
                        &lt;%&ndash;<input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
                        <input  id="headimgurl" type="hidden" value=""/> &ndash;%&gt;
                    </td>--%>
				</tr>
				<tr>
					<th><span class="red">*</span>证件类型：</th>
					<td style="padding-left:10px;">
						<select name="user.cretTypeId" id="cretTypeId" onclick="addValidate(this.value)"
								<%--onchange="addValidate(this.value)" --%>class="validate[required] select"
								style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${certificateTypeEnumList}" var="certificateType">
								<option value="${certificateType.id}" ${certificateType.id eq user.cretTypeId?'selected':''}>${certificateType.name}</option>
							</c:forEach>
						</select>&#12288;&nbsp;
					</td>
					<th><span class="red">*</span>证&nbsp;&nbsp;件&nbsp;&nbsp;号：</th>
					<td>
						<c:if test="${empty doctor.graduationStatusId}">
							<input type="text" name="user.idNo" id="idNo" value="${user.idNo}" title="X请大写"
								   onchange="checkUserUnique();" class="validate[required] input"/>&#12288;
						</c:if>
						<c:if test="${!empty doctor.graduationStatusId}"><label>${user.idNo}</label><input id="idNo"
																										   name="user.idNo"
																										   value="${user.idNo}"
																										   type="hidden"></c:if>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>民&#12288;&#12288;族：</th>
					<td style="padding-left:10px;">
						<select name="user.nationId" class="validate[required] select" style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${userNationEnumList}" var="userNation">
								<option value="${userNation.id}" ${userNation.id eq user.nationId?'selected':''}>${userNation.name}</option>
							</c:forEach>
						</select>&nbsp;&#12288;
					</td>

					<th><span class="red">*</span>国&#12288;&#12288;籍：</th>
					<td>
						<select onclick="changeClassInfo('nationality',this.value,'ofProvince','chnProvince')"
								name="user.nationalityId" style="width: 160px;margin: 0 5px;"
								class="select validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumNationalityList}" var="nationalityEnum">
								<option value="${nationalityEnum.dictId}" ${nationalityEnum.dictId eq user.nationalityId ? 'selected':''} >${nationalityEnum.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<%--				<tr id="qtCountry">--%>
				<%--					<th><span class="red">*</span>国家或地区：</th>--%>
				<%--					<td><input type="text" name="qtCountry" value="${user.nationalityName}" class="input validate[required]"--%>
				<%--							   style="width: 97%;"/></td>--%>
				<%--				</tr>--%>
				<tr>
					<th><span class="red">*</span>婚姻状况：</th>
					<td style="padding-left:10px;">
						<select name="userResumeExt.maritalStatus" class="validate[required] select"
								style="width: 160px;">
							<option value="">请选择</option>
							<option value="1" ${userResumeExt.maritalStatus eq 1?"selected":"style=''"}>未婚</option>
							<option value="2" ${userResumeExt.maritalStatus eq 2?"selected":"style=''"}>已婚</option>
							<option value="3" ${userResumeExt.maritalStatus eq 3?"selected":"style=''"}>初婚</option>
							<option value="4" ${userResumeExt.maritalStatus eq 4?"selected":"style=''"}>再婚</option>
							<option value="5" ${userResumeExt.maritalStatus eq 5?"selected":"style=''"}>复婚</option>
							<option value="6" ${userResumeExt.maritalStatus eq 6?"selected":"style=''"}>丧偶</option>
							<option value="7" ${userResumeExt.maritalStatus eq 7?"selected":"style=''"}>离婚</option>
						</select>&nbsp;&#12288;
					</td>
					<th><span class="red">*</span>生&#12288;&#12288;日：</th>
					<td>
						<input id="userBirthday" value="${user.userBirthday}"
							   class="validate[required] input datepicker" onchange="changeBirthday();"
							   style="width: 149px;" readonly="readonly"/>&#12288;
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>手&#12288;&#12288;机：</th>
					<td><input readonly="readonly" name="user.userPhone" value="${user.userPhone}"
							   onchange="checkUserUnique();" class="validate[required,custom[mobile]] input"/>&#12288;
					</td>
					<th><span class="red">*</span>电子邮箱：</th>
					<td><input name="user.userEmail" value="${user.userEmail}"
							   class="validate[required,custom[email,tszf]] input"/></td>
				</tr>

				<tr>
					<th><span class="red">*</span>外语等级考试类型：</th>
					<td class="englishClass">
						<select name="doctor.englishGradeExamType" style="width: 160px;margin: 0 5px;"
								onclick="changeClassInfo('gradeExamType',this.value,'ofProvince','chnProvince')"
								class="select validate[required]">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumEnglishGradeExamTypeList}" var="nationalityEnum">
								<option value="${nationalityEnum.dictId}" ${nationalityEnum.dictId eq doctor.englishGradeExamType ? 'selected':''} >${nationalityEnum.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<%--<th><span class="red">*</span>外语能力(非英语)：</th>--%>
					<th id="englishTh" class="englishTh"><span class="red  englishSpan2">*</span>英语能力：</th>
					<td id="englishTd" class="englishTh" colspan="2">
						<select name="doctor.englishAbility" id ="englishAbility" style="width: 160px;margin: 0 5px;"
								onclick="changeEnglishAbility(this.value)"
								class="select validate[required] englishClass2">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumEnglishAbilityList}" var="nationalityEnum">
								<option value="${nationalityEnum.dictId}" ${nationalityEnum.dictId eq doctor.englishAbility ? 'selected':''} >${nationalityEnum.dictName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr id="englishTr1" class="englishTr1">
					<th class="englishTr1"><span class="red requiredSpan englishSpan3 ">*</span>外语等级考试证书编号：</th>
					<td class="englishTr1"><input name="doctor.englishCertificateNumber"
												  value="${doctor.englishCertificateNumber }"
												  class=" requiredClass englishClass3 input validate[required]"/></td>
					<th class="englishTr1"><span class="red requiredSpan  englishSpan4">*</span>外语等级考试证书取得日期：</th>
					<td class="englishTr1" colspan="2">
						<input id="englishCertificateDate" name="doctor.englishCertificateDate"
							   value="${doctor.englishCertificateDate}"
							   class=" englishClass4 requiredClass validate[required] input datepicker"
							   onchange="changeBirthday();" style="width: 149px;" readonly="readonly"/>&#12288;
					</td>
				</tr>
				<tr id="englishTr2" class="englishTr2">
					<th class="englishTr2" id="englishTh1"><span class="red  ">*</span>外语能力：</th>
					<td class="englishTr2" id="englishTd1" colspan="4"><input id="foreignSkills"
																			  name="doctor.foreignSkills"
																			  value="${doctor.foreignSkills }"
																			  class="input validate[required] englishClass1"/>
					</td>
					<%--
                    <th><span class="red">*</span>电子邮箱：</th>
                    <td colspan="4"><input name="user.userEmail" value="${user.userEmail}" class="validate[required,custom[email]] input" /></td>--%>
				</tr>
				<tr>
					<th><span class="red">*</span>身份证人像面：</th>
					<td>
						<input type="hidden" id="idcardFrontImgValue" name="user.idcardFrontImg"
							   value="${user.idcardFrontImg }" class="validate[required] input"/>
						<span id="idcardFrontImgSpan" style="display:${!empty user.idcardFrontImg?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${user.idcardFrontImg}" target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="idcardFrontImg" href="javascript:uploadIdCardFile('idcardFrontImg','身份证正面/人像页');"
						   class="btn" style="padding:0 9px">${empty user.idcardFrontImg?'':'重新'}上传</a>&nbsp;
						<a id="idcardFrontImgDel" href="javascript:delFile('idcardFrontImg');" class="btn"
						   style="${empty user.idcardFrontImg?'display:none':''};padding:0 9px">删除</a>&nbsp;
						&nbsp;
					</td>
					<th><span class="red">*</span>身份证国徽面：</th>
					<td colspan="2">
						<input type="hidden" id="idcardOppositeImgValue" name="user.idcardOppositeImg"
							   value="${user.idcardOppositeImg }" class="validate[required] input"/>
						<span id="idcardOppositeImgSpan" style="display:${!empty user.idcardOppositeImg?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${user.idcardOppositeImg}"
								target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="idcardOppositeImg" href="javascript:uploadIdCardFile('idcardOppositeImg','身份证反面/国徽页');"
						   class="btn" style="padding:0 9px">${empty user.idcardOppositeImg?'':'重新'}上传</a>&nbsp;
						<a id="idcardOppositeImgDel" href="javascript:delFile('idcardOppositeImg');" class="btn"
						   style="${empty user.idcardOppositeImg?'display:none':''};padding:0 9px">删除</a>&nbsp;
						&nbsp;
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>人员类型：</th>

					<td  style="padding-left:10px;" id="doctorTypeNameTd">
						<c:if test="${isPassed}">
							${doctor.doctorTypeName}
							<input type="radio" id="doctorType_${doctor.doctorTypeId }" name="doctor.doctorTypeId"
								   checked style="display: none" value="${doctor.doctorTypeId }">
						</c:if>
						<c:if test="${!isPassed}">
							<c:forEach items="${resDocTypeEnumList}" var="doctorType">

								<input type="radio" id="doctorType_${doctorType.id }" name="doctor.doctorTypeId"
									   class="validate[required]" onchange="changeWorkAdress(this.value);"
									   value="${doctorType.id}" style="width: 20px"
									   <c:if test="${doctor.doctorTypeId eq  doctorType.id }">checked="checked"</c:if>
								/>
								<label style="cursor: pointer;" id="doctorTypeName_${doctorType.id }" class="doctorType" for="doctorType_${doctorType.id }"
									   onmouseover="changeTitle(this);">${doctorType.name }
								</label>
							</c:forEach>&nbsp;&#12288;
						</c:if>
					</td>

					<th class="school"><span class="red">*</span>派送学校：</th>
					<td class="school" colspan="2">

						<select class="select  validate[required]"  style="width: 160px" onchange="checkWorkOrgName(this);">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
								<option value="${dict.dictName}"
										<c:if test="${doctor.workOrgName eq dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
						<%--<input id="psxx" value="${doctor.workOrgName}" class="toggleView input validate[required]"
							   type="text" onchange="changeOrg();"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;margin-left: 2px">
							<div class="boxHome psxx" id="psxxSel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
									<p class="item psxx" flow="${dict.dictId}" value="${dict.dictName}"
									   style="line-height: 20px; padding:10px 0;cursor: default; ">${dict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
						--%>
						<input type="hidden" id="school" value=""/>
					</td>
					<th class="address" id="address"><span class="red">*</span>派送单位：</th>
					<td colspan="4" class="address">
						<input id="psdw" value="${doctor.workOrgName}" class="toggleView input validate[required]"
							   type="text" onchange="changeOrg();"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;margin-left: 2px">
							<div class="boxHome psdw" id="psdwSel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumWorkOrgList}" var="dict">
									<p class="item psdw" flow="${dict.dictId}" value="${dict.dictName}"
									   style="line-height: 20px; padding:10px 0;cursor: default; ">${dict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
						<input type="hidden" id="work" value=""/>
						<input type="hidden" id="workOrgName" name="doctor.workOrgName" value="${doctor.workOrgName}"/>
					</td>
				</tr>
				<tr class="workUniteCreditCode" style="display: none;">
					<th class="address"><span class="red">*</span>工作单位：</th>
					<td><input name="userResumeExt.workUnit" value="${userResumeExt.workUnit}"
							   class="input validate[required]" style="width: 98%;"/></td>
					<th><span class="red">*</span>是否军队医院：</th>
					<td colspan="2">
						<label>&#12288;<input class="validate[required]" name="userResumeExt.armyHospital" type="radio"
											  <c:if test="${userResumeExt.armyHospital eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"/>&nbsp;是&#12288;</label>
						<label><input class="validate[required]" name="userResumeExt.armyHospital" type="radio"
									  <c:if test="${userResumeExt.armyHospital eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="workUniteCreditCode" style="display: none;">
					<th>工作单位统一信用代码(15或18位)：</th>
					<td style="padding-left:10px;" colspan="4">
						<input name="userResumeExt.workUniteCreditCode" id="workUniteCreditCode"
							   value="${userResumeExt.workUniteCreditCode}"
							   class="validate[minSize[15],maxSize[18]] input"/>
					</td>
					<%--<th><span class="red">*</span>工作单位：</th>
                    <td colspan="3">
                        <input name="workUnit" value="${doctorRecruit.workUnit}"  class="input"/>
                    </td>--%>
				</tr>
				<tr class="address">
					<th><span class="red">*</span>医疗卫生机构：</th>
					<td colspan="4" id="medicalHeaithOrgIdTd">
						&nbsp;<select class="select address validate[required]" name="userResumeExt.medicalHeaithOrgId"
									  style="width: 160px">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
							<option value="${tra.dictId}"
									<c:if test="${userResumeExt.medicalHeaithOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</select>&#12288;&nbsp;
					</td>
					<th class="hospital" style="display: none;"><span class="red">*</span>医院属性：</th>
					<td class="hospital" style="display: none;" id="TD">
						&nbsp;<select class="select hospital validate[required]" name="userResumeExt.hospitalAttrId"
									  style="width: 160px">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
							<option value="${tra.dictId}"
									<c:if test="${userResumeExt.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</select>&#12288;&nbsp;
					</td>
					<th class="medical" style="display: none;"><span class="red">*</span>基层医疗卫生机构：</th>
					<td class="medical" colspan="2" style="display: none;">
						&nbsp;<select class="select medical validate[required]" name="userResumeExt.basicHealthOrgId"
									  style="width: 160px">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
							<option value="${tra.dictId}"
									<c:if test="${userResumeExt.basicHealthOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</select>&#12288;&nbsp;
					</td>
				</tr>
				<tr style="display: none;" class="hospital">
					<th><span class="red">*</span>医院类别：</th>
					<td>
						&nbsp;<select class="select hospital  validate[required]"
									  name="userResumeExt.hospitalCategoryId" style="width: 160px">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
							<option value="${tra.dictId}"
									<c:if test="${userResumeExt.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</select>&#12288;&nbsp;

					</td>
					<th><span class="red">*</span>单位性质：</th>
					<td colspan="2">
						&nbsp;<select class="select hospital validate[required]" name="userResumeExt.baseAttributeId"
									  style="width: 160px">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
							<option value="${tra.dictId}"
									<c:if test="${userResumeExt.baseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
						</c:forEach>
					</select>&#12288;&nbsp;
					</td>
				</tr>
				<tr>
					<th>计算机能力：</th>
					<td><input name="doctor.computerSkills" id="computerSkills" value="${doctor.computerSkills }" class="input"/></td>
					<th>固定电话：</th>
					<td colspan="4"><input name="userResumeExt.telephone" value="${userResumeExt.telephone}"
										   class="validate[custom[phone2]] input"/></td>
				</tr>
				<tr>
					<th>本人通讯地址：</th>
					<td colspan="4"><input name="user.userAddress" id="userAddress" value="${user.userAddress}" class="input"
										   style="width: 98%;"/></td>
				</tr>
				<tr>
					<th><span class="red">*</span>紧急联系人：</th>
					<td><input name="doctor.emergencyName" id="emergencyName" value="${doctor.emergencyName}" class="validate[required] input"/> </td>
					<th><span class="red">*</span>紧急联系人手机：</th>
					<td colspan="2"><input name="doctor.emergencyPhone" value="${doctor.emergencyPhone}"
										   class="validate[required,custom[mobile]] input"/></td>
				</tr>
				<tr>
					<th>紧急联系人地址：</th>
					<td colspan="4"><input name="userResumeExt.emergencyAddress" id="emergencyAddress"
										   value="${userResumeExt.emergencyAddress}" class="input" style="width: 98%;"/>
					</td>
				</tr>
				<tr>
					<th><span class="red chnProvince">*</span>户口所在地省行政区划：</th>
					<td style="padding-left:10px;">
						<select name="userResumeExt.locationOfProvince" class="select validate[required] ofProvince"
								style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${provinceEnumList}" var="province">
								<option value="${province.id}"
										<c:if test="${userResumeExt.locationOfProvince eq province.id}">selected="selected"</c:if>>${province.name}</option>
							</c:forEach>
						</select>
					</td>
					<th><span class="red">*</span>是否军队人员：</th>
					<td style="padding-left:10px;" colspan="2">
						<select name="userResumeExt.armyType" class="select validate[required]" style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${armyTypeEnumList}" var="army">
								<option value="${army.id}"
										<c:if test="${userResumeExt.armyType eq army.id}">selected="selected"</c:if>>${army.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th colspan="5" style="text-align:left;"><span class="red">&#12288;军队人员参加国家住院医师规范化培训结业考核，理论考试命题在国家统一试卷基础上，增加军事医学部分，请如实填报，以免影响结业考核！</span>
					</th>
				</tr>
				</tbody>
			</table>
		</div>
		<div class="div_table">
			<h4>教育情况</h4>
			<table border="0" cellpadding="0" cellspacing="0" class="base_info" style="margin-top:10px">
				<colgroup>
					<col width="16%"/>
					<col width="30%"/>
					<col width="16%"/>
					<col width="38%"/>
				</colgroup>
				<tbody>
				<tr>
					<th><span class="red">*</span>是否为应届生：</th>
					<td>
						<label>&#12288;<input name="doctor.isYearGraduate" class="validate[required]"
											  onclick="checkYJS(this.value,'reading')" type="radio"
											  <c:if test="${doctor.isYearGraduate eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"/>&nbsp;是&#12288;</label>
						<label><input id="sfwyjs" name="doctor.isYearGraduate" class="validate[required]" type="radio"
								<c:if test="${user.trainingTypeId eq 'DoctorTrainingSpe'}">
									onclick="checkYJS(this.value,'reading')"
								</c:if>
									  <c:if test="${doctor.isYearGraduate eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>

					<th><span class="red">*</span>是否为农村订单定向免费医学毕业生：</th>
					<td>
						<label>&#12288;<input name="userResumeExt.isGeneralOrderOrientationTrainee" type="radio"
											  <c:if test="${userResumeExt.isGeneralOrderOrientationTrainee eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isGeneralOrderOrientationTrainee" type="radio"
									  <c:if test="${userResumeExt.isGeneralOrderOrientationTrainee eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>是否在读：</th>
					<td colspan="3">
						<label>&#12288;<input id="isReadingY" name="userResumeExt.isReading"
											  onclick="checkZk(this.value,'reading')" type="radio"
											  <c:if test="${userResumeExt.isReading eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
						<label><input id="isReadingN" name="userResumeExt.isReading"
									  onclick="checkZk(this.value,'reading')" type="radio"
									  <c:if test="${userResumeExt.isReading eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="readingTh">
					<th><span class="red yy">*</span>在读学历：</th>
					<td>
						&nbsp;<select name="userResumeExt.readingCollege" class="select reading validate[required] "
									  style="width: 160px;">
						<option value="">请选择</option>
						<c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
							<option value="${dict.dictId }"
									<c:if test="${userResumeExt.readingCollege eq dict.dictId }">selected="selected"</c:if>>${dict.dictName}</option>
						</c:forEach>
					</select>&#12288;&nbsp;
					</td>
					<th><span class="red yy">*</span>预计毕业时间：</th>
					<td>
						<input name="userResumeExt.readingDate" value="${userResumeExt.readingDate}"
							   class="input  datepicker validate[required]" readonly="readonly"/>&#12288;
					</td>
				</tr>
				<tr class="readingTh">
					<th><span class="red">*</span>在读院校：</th>
					<td style="padding-left:10px;" class="readingSchoolTd" colspan="4">
						<input id="zdyx" name="userResumeExt.readingSchoolName"
							   value="${userResumeExt.readingSchoolName}"
							   class="toggleView reading input validate[required]" type="text"
							   autocomplete="off" style="margin-left: 0px" oncontextmenu="return false"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome zdyx" id="zdyxSel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict">
									<p class="item zdyx ${dict.dictId }" flow="${dict.dictId}"
									   onclick="changeSchoolOrProfession('reading',${dict.dictId },'School')"
									   value="${dict.dictName}"
									   style="line-height: 40px; padding:10px 0;cursor: default;width: 100%  ">${dict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
					</td>
					<th style="display: none" class="readingChangeSchool"><span class="red">*</span>填写在读院校：</th>
					<td style="display: none" class="readingChangeSchool"><input
							name="userResumeExt.readingOtherSchoolsName"
							value="${userResumeExt.readingOtherSchoolsName }" class="input"/></td>

				</tr>
				<tr class="readingTh">
					<th><span class="red">*</span>在读专业：</th>
					<td style="padding-left:10px;" class="readingProfessionTd" colspan="4">
						<input id="zdzy" name="userResumeExt.readingProfession"
							   value="${userResumeExt.readingProfession}"
							   class="toggleView reading input validate[required]" type="text"
							   autocomplete="off" style="margin-left: 0px" oncontextmenu="return false"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome zdzy" id="zdzySel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumGraduateMajorList}" var="dict">
									<p class="item zdzy ${dict.dictId}" flow="${dict.dictId}"
									   onclick="changeSchoolOrProfession('reading',${dict.dictId },'Profession')"
									   value="${dict.dictName}"
									   style="line-height: 40px; padding:10px 0;cursor: default;width: 100%  ">${dict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
					</td>
					<th style="display: none" class="readingChangeProfession"><span class="red">*</span>填写在读专业：</th>
					<td style="display: none" class="readingChangeProfession"><input
							name="userResumeExt.readingOtherProfession" value="${userResumeExt.readingOtherProfession }"
							class="input"/></td>
				</tr>

				<%-- 大专 start--%>
				<tr id="isDZ">
					<th><span class="red">*</span> 是否大专：</th>
					<td colspan="3">
						<label>&#12288;<input name="userResumeExt.isJuniorCollege" type="radio"
											  <c:if test="${userResumeExt.isJuniorCollege eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" onclick="checkZk(this.value,'junior')"
											  class="validate[required]"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isJuniorCollege" type="radio"
									  <c:if test="${userResumeExt.isJuniorCollege eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" onclick="checkZk(this.value,'junior')"
									  class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="juniorTh"> <%--style="display: none" --%>
					<th><span class="red">*</span> 是否全日制：</th>
					<td>
						<label>&#12288;<input name="userResumeExt.juniorCollegeFullTime" type="radio"
											  <c:if test="${userResumeExt.juniorCollegeFullTime eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.juniorCollegeFullTime" type="radio"
									  <c:if test="${userResumeExt.juniorCollegeFullTime eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
					<th><span class="red">*</span>大专毕业时间：</th>
					<td>
						<input name="userResumeExt.juniorCollegeGradate" value="${userResumeExt.juniorCollegeGradate}"
							   class="input junior datepicker validate[required]" readonly="readonly"/>
					</td>
				</tr>
				<tr class="juniorTh"> <%--style="display: none"--%>
					<th><span class="red">*</span>大专毕业院校：</th>
					<td style="padding-left:10px;" class="juniorSchoolTd" colspan="4">
						<input id="zkbyyx" name="userResumeExt.juniorCollegeSchoolName"
							   value="${userResumeExt.juniorCollegeSchoolName}"
							   class="toggleView junior input validate[required]" type="text"
							   autocomplete="off" style="margin-left: 0px" oncontextmenu="return false"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome zkbyyx" id="zkbyyxSel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict">
									<p class="item zkbyyx" flow="${dict.dictId}"
									   onclick="changeSchoolOrProfession('junior',${dict.dictId },'School')"
									   value="${dict.dictName}"
									   style="line-height: 40px; padding:10px 0;cursor: default;width: 100% ">${dict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
					</td>
					<th style="display: none" class="juniorChangeSchool"><span class="red">*</span>填写大专毕业院校：</th>
					<td style="display: none" class="juniorChangeSchool"><input
							name="userResumeExt.juniorOtherSchoolsName" value="${userResumeExt.juniorOtherSchoolsName }"
							class="input validate[required]"/></td>
				</tr>
				<tr class="juniorTh"> <%--style="display: none"--%>
					<th><span class="red">*</span>大专毕业专业：</th>
					<td style="padding-left:10px;" class="juniorProfessionTd" colspan="4">
						<input id="majors" name="userResumeExt.juniorCollegeProfession"
							   value="${userResumeExt.juniorCollegeProfession}"
							   class="toggleView junior input validate[required]" type="text"
							   autocomplete="off" style="margin-left: 0px" oncontextmenu="return false"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome majors" id="majorsSel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumGraduateMajorList}" var="majorsDict">
									<p class="item majors" flow="${majorsDict.dictId}" value="${majorsDict.dictName}"
									   style="line-height: 40px; padding:10px 0;cursor: default;width: 100% "
									   onclick="changeSchoolOrProfession('junior',${majorsDict.dictId },'Profession')">${majorsDict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
					</td>
					<th style="display: none" class="juniorChangeProfession"><span class="red">*</span>填写大专毕业专业：</th>
					<td style="display: none" class="juniorChangeProfession"><input
							name="userResumeExt.juniorOtherProfession" value="${userResumeExt.juniorOtherProfession }"
							class="input validate[required]"/></td>
				</tr>

				<tr class="juniorTh"> <%--style="display: none"--%>
					<th><span class="red">*</span> 是否获得毕业证书：</th>
					<td class="isCertificateTd">
						<label>&#12288;<input name="userResumeExt.isJuniorCollegeCertificate" class="validate[required]"
											  onchange="showCertificate('isCertificate','Y')" type="radio"
											  <c:if test="${userResumeExt.isJuniorCollegeCertificate eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isJuniorCollegeCertificate" class="validate[required]"
									  onchange="showCertificate('isCertificate','N')" type="radio"
									  <c:if test="${userResumeExt.isJuniorCollegeCertificate eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
					<th class="isCertificateTh3"><span class="red">*</span> 证书照片：</th>
					<%--style="display: none"--%>
					<td class="isCertificateTh3">
						<input type="hidden" id="juniorCollegeUrlValue" name="userResumeExt.juniorCollegeUrl"
							   class="validate[required]" value="${userResumeExt.juniorCollegeUrl}"/>
						<span id="juniorCollegeUrlSpan"
							  style="display:${!empty userResumeExt.juniorCollegeUrl?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.juniorCollegeUrl}"
								target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="juniorCollegeUrl" href="javascript:uploadFile('juniorCollegeUrl','证书照片');"
						   class="btn">${empty userResumeExt.juniorCollegeUrl?'':'重新'}上传</a>&nbsp;
						<a id="juniorCollegeUrlDel" href="javascript:delFile('juniorCollegeUrl');" class="btn"
						   style="${empty userResumeExt.degreeUri?'display:none':''}">删除</a>&nbsp;
					</td>
				</tr>
				<tr class="juniorTh isCertificateTh3" style="display: none">
					<th class="isCertificateTh3"><span class="red yy">*</span>学历证书编号：</th>
					<td class="isCertificateTh3">
						<input name="userResumeExt.juniorCollegeCode" value="${userResumeExt.juniorCollegeCode}"
							   class="validate[required] input"/>&#12288;
					</td>
					<th class="isCertificateTh3"><span class="red">*</span>证书取得时间：</th>
					<td class="isCertificateTh3">
						<input name="userResumeExt.juniorCollegeDate" value="${userResumeExt.juniorCollegeDate}"
							   class="input junior datepicker validate[required] " readonly="readonly"/>&#12288;
					</td>
				</tr>
				<%-- 大专 end--%>

				<%-- 本科 start--%>
				<tr class="zl">
					<th><span class="red">*</span> 是否本科：</th>
					<td colspan="3">
						<label>&#12288;<input name="userResumeExt.isUndergraduate" type="radio"
											  <c:if test="${userResumeExt.isUndergraduate eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"
											  onclick="checkZk(this.value,'undergrad')" class="validate[required]"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isUndergraduate" type="radio"
									  <c:if test="${userResumeExt.isUndergraduate eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" onclick="checkZk(this.value,'undergrad')"
									  class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="undergradTh">
					<th><span class="red">*</span> 是否全日制：</th>
					<td>
						<label>&#12288;<input name="userResumeExt.undergraduateFullTime" type="radio"
											  <c:if test="${userResumeExt.undergraduateFullTime eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.undergraduateFullTime" type="radio"
									  <c:if test="${userResumeExt.undergraduateFullTime eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
					<th><span class="red yy">*</span>本科毕业时间：</th>
					<td><input id="bkbysj" name="userResumeExt.graduationTime" value="${userResumeExt.graduationTime}"
							   class="input graduate datepicker validate[required] bk" style="width: 149px;"
							   readonly="readonly"/>&#12288;
					</td>
				</tr>
				<tr class="undergradTh"> <%-- style="display: none"--%>
					<th><span class="red">*</span>本科毕业院校：</th>
					<td style="padding-left:10px;" class="undergraSchoolTd" colspan="4">
						<input id="bkbyyx" name="userResumeExt.graduatedName" value="${userResumeExt.graduatedName}"
							   class="toggleView graduate input validate[required]" type="text"
							   autocomplete="off" style="margin-left: 0px"
							   <%--onchange="changeYx(this);" onkeydown="changeYx(this);" onkeyup="changeYx(this);" --%>oncontextmenu="return false"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome bkbyyx" id="bkbyyxSel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict">
									<p class="item bkbyyx" flow="${dict.dictId}" value="${dict.dictName}"
									   style="line-height: 40px; padding:10px 0;cursor: default;width: 100% "
									   onclick="changeYx(this);changeSchoolOrProfession('undergra',${dict.dictId },'School')">${dict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
					</td>
					<th style="display: none" class="undergraChangeSchool"><span class="red">*</span>填写本科毕业院校：</th>
					<td style="display: none" class="undergraChangeSchool"><input
							name="userResumeExt.undergraOtherSchoolsName"
							value="${userResumeExt.undergraOtherSchoolsName }" class="input validate[required]"/></td>
				</tr>
				<tr class="undergradTh"><%--graduateTh style="display: none"--%>
					<th><span class="red">*</span>本科毕业专业：</th>
					<td style="padding-left:10px;" class="undergraProfessionTd" colspan="4">
						<input id="bkbyzy" name="userResumeExt.specialized" value="${userResumeExt.specialized}"
							   class="toggleView graduate input validate[required]" type="text"
							   autocomplete="off" style="margin-left: 0px" oncontextmenu="return false"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome bkbyzy" id="bkbyzySel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumGraduateMajorList}" var="majorsDict">
									<p class="item bkbyzy" flow="${majorsDict.dictId}"
									   onclick="changeSchoolOrProfession('undergra',${majorsDict.dictId },'Profession')"
									   value="${majorsDict.dictName}"
									   style="line-height: 40px; padding:10px 0;cursor: default;width: 100% ">${majorsDict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
					</td>
					<th style="display: none" class="undergraChangeProfession"><span class="red">*</span>填写本科毕业专业：</th>
					<td style="display: none" class="undergraChangeProfession"><input
							name="userResumeExt.undergraOtherProfession"
							value="${userResumeExt.undergraOtherProfession }" class="input validate[required]"/></td>
				</tr>
				<tr class="undergradTh">
					<th><span class="red yy">*</span>学位：</th>
					<td style="padding-left:10px;" colspan="4">
						<select id="degree" name="userResumeExt.degreeId" class="select graduate validate[required] bk"
								style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}"
										<c:if test="${ dict.dictId eq userResumeExt.degreeId }">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>&#12288;&nbsp;
					</td>
				</tr>
				<tr class="undergradTh"><%--style="display: none"--%>
					<%--<th><span class="red">*</span> 是否获得毕业证书：</th>
                    <td colspan="4">
                        <label>&#12288;<input name="userResumeExt.isUndergraduateCertificate" onchange="showCertificate('isUndergrad','Y')"  type="radio" <c:if test="${userResumeExt.isUndergraduateCertificate eq GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
                        <label><input name="userResumeExt.isUndergraduateCertificate" onchange="showCertificate('isUndergrad','N')"  type="radio" <c:if test="${userResumeExt.isUndergraduateCertificate eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
                    </td>--%>
					<th><span class="red yy">*</span>是否获得毕业证书：</th>
					<td colspan="4">
						<label>&#12288;<input class="undergradTh validate[required]"
											  name="userResumeExt.isCollegeHaveDiploma"
											  onchange="showDiploma('undergrad','Y')" type="radio"
											  <c:if test="${userResumeExt.isCollegeHaveDiploma eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"/>&nbsp;是&#12288;</label>
						<label><input class="undergradTh validate[required]" name="userResumeExt.isCollegeHaveDiploma"
									  onchange="showDiploma('undergrad','N')" type="radio"
									  <c:if test="${userResumeExt.isCollegeHaveDiploma eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="undergradTh1" style="display: none">
					<th class="red yy "><span class="red">*</span> 毕业证书照片：</th>
					<td class="isUndergradTh3" colspan="4">
						<input type="hidden" id="undergraduateUrlValue" name="userResumeExt.undergraduateUrl"
							   value="${userResumeExt.undergraduateUrl}"/>
						<span id="undergraduateUrlSpan"
							  style="display:${!empty userResumeExt.undergraduateUrl?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.undergraduateUrl}"
								target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="undergraduateUrl" href="javascript:uploadFile('undergraduateUrl','证书照片');"
						   class="btn">${empty userResumeExt.undergraduateUrl?'':'重新'}上传</a>&nbsp;
						<a id="undergraduateUrlDel" href="javascript:delFile('undergraduateUrl');" class="btn"
						   style="${empty userResumeExt.undergraduateUrl?'display:none':''}">删除</a>&nbsp;
					</td>
				</tr>
				<tr class="undergradTh1" style="display: none">
					<th><span class="red yy">*</span>学历证书编号：</th>
					<td>
						<input name="userResumeExt.collegeDiplomaNo" value="${userResumeExt.collegeDiplomaNo}"
							   class="validate[required] input undergradTh1"/>&#12288;
					</td>
					<th><span class="red yy">*</span>学历证书取得时间：</th>
					<td>
						<input name="userResumeExt.collegeDiplomaTime" value="${userResumeExt.collegeDiplomaTime}"
							   class="input undergrad datepicker validate[required]  undergradTh1" readonly="readonly"/>&#12288;
					</td>
				</tr>
				<tr class="undergradTh">
					<th><span class="red yy">*</span>是否获得学位证书：</th>
					<td colspan="4">
						<label>&#12288;<input class="undergradTh validate[required]"
											  name="userResumeExt.isCollegeDegreeCertificate"
											  onchange="showDegree('undergrad','Y')" type="radio"
											  <c:if test="${userResumeExt.isCollegeDegreeCertificate eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"/>&nbsp;是&#12288;</label>
						<label><input class="undergradTh validate[required]"
									  name="userResumeExt.isCollegeDegreeCertificate"
									  onchange="showDegree('undergrad','N')" type="radio"
									  <c:if test="${userResumeExt.isCollegeDegreeCertificate eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="undergradTh2" style="display: none">
					<th <%--class="isUndergradTh3"--%>><span class="red">*</span> 学位证书照片：</th>
					<td <%--class="isUndergradTh3" --%>colspan="4">
						<input type="hidden" id="undergraduateDegreeUrlValue"
							   name="userResumeExt.undergraduateDegreeUrl"
							   value="${userResumeExt.undergraduateDegreeUrl}"/>
						<span id="undergraduateDegreeUrlSpan"
							  style="display:${!empty userResumeExt.undergraduateDegreeUrl?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.undergraduateDegreeUrl}"
								target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="undergraduateDegreeUrl" href="javascript:uploadFile('undergraduateDegreeUrl','证书照片');"
						   class="btn">${empty userResumeExt.undergraduateDegreeUrl?'':'重新'}上传</a>&nbsp;
						<a id="undergraduateDegreeUrlDel" href="javascript:delFile('undergraduateDegreeUrl');"
						   class="btn" style="${empty userResumeExt.undergraduateDegreeUrl?'display:none':''}">删除</a>&nbsp;
					</td>
				</tr>
				<tr class="undergradTh2" style="display: none">
					<th><span class="red yy">*</span>学位证书编号：</th>
					<td>
						<input name="userResumeExt.collegeDegreeNo" value="${userResumeExt.collegeDegreeNo}"
							   class="validate[required] input undergradTh2"/>&#12288;
					</td>
					<th><span class="red yy">*</span>学位证书取得时间：</th>
					<td>
						<input name="userResumeExt.collegeDegreeTime" value="${userResumeExt.collegeDegreeTime}"
							   class="input undergrad datepicker validate[required]  undergradTh2" readonly="readonly"/>&#12288;
					</td>
				</tr>
				<%-- 本科 end--%>

				<%-- 硕士 start--%>
				<tr class="zl">
					<th><span class="red">*</span>是否为硕士：</th>
					<td colspan="3"><%--class="masterTd"  isMasterFullTime--%>
						<label>&#12288;<input name="userResumeExt.isMaster" type="radio"
											  <c:if test="${userResumeExt.isMaster eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" class="validate[required]"
											  onclick="checkBx(this.value,'master')"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isMaster" type="radio"
									  <c:if test="${userResumeExt.isMaster eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" class="validate[required]"
									  onclick="checkBx(this.value,'master')"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="masterTh"><%--style="display: none" --%>
					<th><span class="red">*</span>硕士学位类型：</th>
					<td>
						<select class="select  validate[required]" name="userResumeExt.masterDegreeTypeId"
								style="width: 160px">
							<option value="">请选择</option>
							<option value="1"${userResumeExt.masterDegreeTypeId eq 1?'selected':'' }>专业型</option>
							<option value="2"${userResumeExt.masterDegreeTypeId eq 2?'selected':'' }>学术型</option>
						</select>&#12288;&nbsp;
					</td>
					<th><span class="red">*</span> 是否全日制：</th>
					<td>
						<label>&#12288;<input name="userResumeExt.isMasterFullTime" type="radio"
											  <c:if test="${userResumeExt.isMasterFullTime eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isMasterFullTime" type="radio"
									  <c:if test="${userResumeExt.isMasterFullTime eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="masterTh">
					<th><span class="red masterRed">*</span>硕士毕业院校：</th>
					<td style="padding-left:10px;" class="masterSchoolTd" colspan="4">
						<input id="byyx" name="userResumeExt.masterGraSchoolName"
							   value="${userResumeExt.masterGraSchoolName}"
							   class="toggleView input  master validate[required]" type="text" autocomplete="off"
							   style="margin-left: 0px"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome byyx" id="byyxSel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict">
									<p class="item byyx ${dict.dictId }" flow="${dict.dictId}"
									   onclick="changeSchoolOrProfession('master',${dict.dictId },'School')"
									   value="${dict.dictName}"
									   style="line-height: 40px; padding:10px 0;cursor: default;width: 100%  ">${dict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
					</td>
					<th style="display: none" class="masterChangeSchool"><span class="red master">*</span>填写硕士毕业院校：</th>
					<td style="display: none" class="masterChangeSchool"><input
							name="userResumeExt.masterOtherSchoolsName" value="${userResumeExt.masterOtherSchoolsName }"
							class="input master validate[required]"/></td>

				</tr>
				<tr class="masterTh">
					<th><span class="red">*</span>硕士毕业专业：</th>
					<td style="padding-left:10px;" class="masterProfessionTd" colspan="4">
						<input id="ssbyzy" name="userResumeExt.masterMajor" value="${userResumeExt.masterMajor}"
							   class="toggleView graduate input validate[required]" type="text"
							   autocomplete="off" style="margin-left: 0px" oncontextmenu="return false"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome ssbyzy" id="ssbyzySel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumGraduateMajorList}" var="majorsDict">
									<p class="item ssbyzy" flow="${majorsDict.dictId}"
									   onclick="changeSchoolOrProfession('master',${majorsDict.dictId },'Profession')"
									   value="${majorsDict.dictName}"
									   style="line-height: 40px; padding:10px 0;cursor: default;width: 100% ">${majorsDict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
					</td>
					<th style="display: none" class="masterChangeProfession"><span class="red master">*</span>填写硕士毕业专业：
					</th>
					<td style="display: none" class="masterChangeProfession"><input
							name="userResumeExt.masterOtherProfession" value="${userResumeExt.masterOtherProfession }"
							class="input master validate[required]"/></td>

				</tr>
				<tr class="masterTh">
					<th><span class="red masterRed">*</span>学位：</th>
					<td style="padding-left:10px;">
						<select name="userResumeExt.masterDegreeId" class="select validate[required] master"
								style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}"
										<c:if test="${ dict.dictId eq userResumeExt.masterDegreeId}">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>&#12288;&nbsp;
					</td>
					<th><span class="red masterRed">*</span>硕士毕业时间：</th>
					<td colspan="2"><input id="ssbysj" name="userResumeExt.masterGraTime"
										   value="${userResumeExt.masterGraTime}"
										   class="input datepicker master validate[required] " style="width: 149px;"
										   readonly="readonly"/>&#12288;
					</td>
				</tr>
				<tr class="masterTh">
					<th><span class="red yy">*</span>是否获得毕业证书:</th>
					<td colspan="4">
						<label>&#12288;<input name="userResumeExt.isMasterHaveDiploma"
											  class="masteriSCertificate validate[required]"
											  onchange="showDiploma('master','Y')" type="radio"
											  <c:if test="${userResumeExt.isMasterHaveDiploma eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isMasterHaveDiploma"
									  class="masteriSCertificate validate[required]"
									  onchange="showDiploma('master','N')" type="radio"
									  <c:if test="${userResumeExt.isMasterHaveDiploma eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="masterTh1" style="display: none;">
					<th><span class="red">*</span> 学历证书照片：</th>
					<td>
						<input type="hidden" id="masterEducationUrlValue" name="userResumeExt.masterEducationUrl"
							   class="master" value="${userResumeExt.masterEducationUrl}"/>
						<span id="masterEducationUrlSpan"
							  style="display:${!empty userResumeExt.masterEducationUrl?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.masterEducationUrl}"
								target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="masterEducationUrl" href="javascript:uploadFile('masterEducationUrl','证书照片');"
						   class="btn">${empty userResumeExt.masterEducationUrl?'':'重新'}上传</a>&nbsp;
						<a id="masterEducationUrlDel" href="javascript:delFile('masterEducationUrl');" class="btn"
						   style="${empty userResumeExt.masterEducationUrl?'display:none':''}">删除</a>&nbsp;
					</td>
				</tr>
				<tr class="masterTh1" style="display: none">
					<th><span class="red yy">*</span>学历证书编号：</th>
					<td>
						<input name="userResumeExt.masterDiplomaNo" value="${userResumeExt.masterDiplomaNo}"
							   class="validate[required] input master masterTh1"/>&#12288;
					</td>
					<th><span class="red yy">*</span>学历证书取得时间：</th>
					<td>
						<input name="userResumeExt.masterDiplomaTime" value="${userResumeExt.masterDiplomaTime}"
							   class="input datepicker master validate[required] masterTh1" readonly="readonly"/>&#12288;
					</td>
				</tr>
				<tr class="masterTh">
					<th><span class="red yy">*</span>是否获得学位证书：</th>
					<td colspan="4">
						<label>&#12288;<input name="userResumeExt.isMasterDegreeCertificate"
											  class="masteriSCertificate validate[required]"
											  onchange="showDegree('master','Y')" type="radio"
											  <c:if test="${userResumeExt.isMasterDegreeCertificate eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" "/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isMasterDegreeCertificate"
									  class="masteriSCertificate validate[required]" onchange="showDegree('master','N')"
									  type="radio"
									  <c:if test="${userResumeExt.isMasterDegreeCertificate eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="masterTh2" style="display: none;">
					<th><span class="red">*</span> 学位证书照片：</th>
					<td>
						<input type="hidden" id="masterCertificateUrlValue" name="userResumeExt.masterCertificateUrl"
							   class="master" value="${userResumeExt.masterCertificateUrl}"/>
						<span id="masterCertificateUrlSpan"
							  style="display:${!empty userResumeExt.masterCertificateUrl?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.masterCertificateUrl}"
								target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="masterCertificateUrl" href="javascript:uploadFile('masterCertificateUrl','证书照片');"
						   class="btn">${empty userResumeExt.masterCertificateUrl?'':'重新'}上传</a>&nbsp;
						<a id="masterCertificateUrlDel" href="javascript:delFile('masterCertificateUrl');" class="btn"
						   style="${empty userResumeExt.masterCertificateUrl?'display:none':''}">删除</a>&nbsp;
					</td>
				</tr>
				<tr class="masterTh2" style="display: none">
					<th><span class="red yy">*</span>学位证书编号：</th>
					<td>
						<input name="userResumeExt.masterDegreeNo" value="${userResumeExt.masterDegreeNo}"
							   class="validate[required] input master"/>&#12288;
					</td>
					<th><span class="red yy">*</span>学位证书取得时间：</th>
					<td>
						<input name="userResumeExt.masterDegreeTime" value="${userResumeExt.masterDegreeTime}"
							   class="input datepicker master validate[required]" readonly="readonly"/>&#12288;
					</td>
				</tr>
				<%--硕士 end--%>

				<%-- 博士 start--%>
				<tr class="zl">
					<th><span class="red">*</span>是否为博士：</th>
					<td colspan="3"> <%--class="doctorTd"--%>
						<label>&#12288;<input name="userResumeExt.isDoctor" type="radio"
											  <c:if test="${userResumeExt.isDoctor eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" class="validate[required]"
											  onclick="checkBx(this.value,'doctor')"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isDoctor" type="radio"
									  <c:if test="${userResumeExt.isDoctor eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" class="validate[required]"
									  onclick="checkBx(this.value,'doctor')"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr style="display: none" class="doctorTh">
					<th><span class="red">*</span>博士学位类型：</th>
					<td>
						<select class="select  validate[required]" name="userResumeExt.doctorDegreeTypeId"
								style="width: 160px">
							<option value="">请选择</option>
							<option value="1"${userResumeExt.doctorDegreeTypeId eq 1?'selected':''}>专业型</option>
							<option value="2"${userResumeExt.doctorDegreeTypeId eq 2?'selected':''}>学术型</option>
						</select>&#12288;&nbsp;
					</td>

					<th><span class="red">*</span> 是否全日制：</th>
					<td>
						<label>&#12288;<input name="userResumeExt.isDoctorFullTime" type="radio"
											  <c:if test="${userResumeExt.isDoctorFullTime eq GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isDoctorFullTime" type="radio"
									  <c:if test="${userResumeExt.isDoctorFullTime eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="doctorTh">
					<th><span class="red">*</span>博士毕业院校：</th>
					<td style="padding-left:10px;" class="doctorSchoolTd" colspan="4">
						<input id="bsbyyx" name="userResumeExt.doctorGraSchoolName"
							   value="${userResumeExt.doctorGraSchoolName}"
							   class="toggleView doctor input validate[required]" type="text" autocomplete="off"
							   style="margin-left: 0px"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome bsbyyx" id="bsbyyxSel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict">
									<p class="item bsbyyx ${dict.dictId }" flow="${dict.dictId}"
									   onclick="changeSchoolOrProfession('doctor',${dict.dictId },'School')"
									   value="${dict.dictName}"
									   style="line-height: 40px; padding:10px 0;cursor: default;width: 100%  ">${dict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
					</td>
					<th style="display: none" class="doctorChangeSchool"><span class="red">*</span>填写博士毕业院校：</th>
					<td style="display: none" class="doctorChangeSchool"><input
							name="userResumeExt.doctorOtherSchoolsName" value="${userResumeExt.doctorOtherSchoolsName }"
							class="input validate[required]"/></td>

				</tr>
				<tr class="doctorTh">
					<th><span class="red">*</span>博士毕业专业：</th>
					<td style="padding-left:10px;" class="doctorProfessionTd" colspan="4">
						<input id="bsbyzy" name="userResumeExt.doctorMajor" value="${userResumeExt.doctorMajor}"
							   class="toggleView graduate input validate[required]" type="text"
							   autocomplete="off" style="margin-left: 0px" oncontextmenu="return false"/>
						<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
							<div class="boxHome bsbyzy" id="bsbyzySel"
								 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
								<c:forEach items="${dictTypeEnumGraduateMajorList}" var="majorsDict">
									<p class="item bsbyzy" flow="${majorsDict.dictId}"
									   onclick="changeSchoolOrProfession('doctor',${majorsDict.dictId },'Profession')"
									   value="${majorsDict.dictName}"
									   style="line-height: 40px; padding:10px 0;cursor: default;width: 100% ">${majorsDict.dictName}</p>
								</c:forEach>
							</div>
						</div>&nbsp;&nbsp;
					</td>

					<th style="display: none" class="doctorChangeProfession"><span class="red">*</span>填写硕士毕业专业：</th>
					<td style="display: none" class="doctorChangeProfession"><input
							name="userResumeExt.doctorOtherProfession" value="${userResumeExt.doctorOtherProfession }"
							class="input validate[required]"/></td>

				</tr>
				<tr class="doctorTh">
					<th><span class="red">*</span>学位：</th>
					<td style="padding-left:10px;">
						<select name="userResumeExt.doctorDegreeId" class="select doctor validate[required]"
								style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
								<option value="${dict.dictId}"
										<c:if test="${ dict.dictId eq userResumeExt.doctorDegreeId }">selected</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>&#12288;&nbsp;
					</td>
					<th><span class="red">*</span>博士毕业时间：</th>
					<td colspan="2"><input id="bsbysj" name="userResumeExt.doctorGraTime"
										   value="${userResumeExt.doctorGraTime}"
										   class="input doctor datepicker validate[required] " style="width: 149px;"
										   readonly="readonly"/>&#12288;
					</td>
				</tr>
				<tr class="doctorTh">
					<th><span class="red yy">*</span>是否获得毕业证书：</th>
					<td colspan="4">
						<label>&#12288;<input name="userResumeExt.isDoctorHaveDiploma"
											  class="doctoriSCertificate validate[required]"
											  onchange="showDiploma('doctor','Y')" type="radio"
											  <c:if test="${userResumeExt.isDoctorHaveDiploma eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isDoctorHaveDiploma"
									  class="doctoriSCertificate validate[required]"
									  onchange="showDiploma('doctor','N')" type="radio"
									  <c:if test="${userResumeExt.isDoctorHaveDiploma eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>

				<tr class="doctorTh1" style="display: none;">
					<th><span class="red yy">*</span> 学历证书照片：</th>
					<td>
						<input type="hidden" id="doctorEducationUrlValue" name="userResumeExt.doctorEducationUrl"
							   value="${userResumeExt.doctorEducationUrl}"/>
						<span id="doctorEducationUrlSpan"
							  style="display:${!empty userResumeExt.doctorEducationUrl?'':'none'} ">
                             [<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorEducationUrl}"
								 target="_blank">查看图片</a>]&nbsp;
                         </span>
						<a id="doctorEducationUrl" href="javascript:uploadFile('doctorEducationUrl','证书照片');"
						   class="btn">${empty userResumeExt.doctorEducationUrl?'':'重新'}上传</a>&nbsp;
						<a id="doctorEducationUrlDel" href="javascript:delFile('doctorEducationUrl');" class="btn"
						   style="${empty userResumeExt.doctorEducationUrl?'display:none':''}">删除</a>&nbsp;
					</td>
				</tr>
				<tr class="doctorTh1" style="display: none;">
					<th><span class="red yy">*</span>学历证书编号：</th>
					<td>
						<input name="userResumeExt.doctorDiplomaNo" value="${userResumeExt.doctorDiplomaNo}"
							   class="input doctor validate[required] "/>&#12288;
					</td>
					<th><span class="red yy">*</span>学历证书取得时间：</th>
					<td>
						<input name="userResumeExt.doctorDiplomaTime" value="${userResumeExt.doctorDiplomaTime}"
							   class="input datepicker doctor validate[required] " readonly="readonly"/>&#12288;
					</td>
				</tr>

				<tr class="doctorTh">
					<th><span class="red yy">*</span>是否获得学位证书：</th>
					<td colspan="4">
						<label>&#12288;<input name="userResumeExt.isDoctorDegreeCertificate"
											  class="doctoriSCertificate validate[required]"
											  onchange="showDegree('doctor','Y')" type="radio"
											  <c:if test="${userResumeExt.isDoctorDegreeCertificate eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isDoctorDegreeCertificate"
									  class="doctoriSCertificate validate[required]" onchange="showDegree('doctor','N')"
									  type="radio"
									  <c:if test="${userResumeExt.isDoctorDegreeCertificate eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>
				<tr class="doctorTh2" style="display: none;">
					<th><span class="red yy">*</span> 学位证书照片：</th>
					<td>
						<input type="hidden" id="doctorCertificateUrlValue" name="userResumeExt.doctorCertificateUrl"
							   value="${userResumeExt.doctorCertificateUrl}"/>
						<span id="doctorCertificateUrlSpan"
							  style="display:${!empty userResumeExt.doctorCertificateUrl?'':'none'} ">
                             [<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorCertificateUrl}"
								 target="_blank">查看图片</a>]&nbsp;
                         </span>
						<a id="doctorCertificateUrl" href="javascript:uploadFile('doctorCertificateUrl','证书照片');"
						   class="btn">${empty userResumeExt.doctorCertificateUrl?'':'重新'}上传</a>&nbsp;
						<a id="doctorCertificateUrlDel" href="javascript:delFile('doctorCertificateUrl');" class="btn"
						   style="${empty userResumeExt.doctorCertificateUrl?'display:none':''}">删除</a>&nbsp;
					</td>
				</tr>
				<tr class="doctorTh2" style="display: none;">
					<th><span class="red yy">*</span>学位证书编号：</th>
					<td>
						<input name="userResumeExt.doctorDegreeNo" value="${userResumeExt.doctorDegreeNo}"
							   class="validate[required] input "/>&#12288;
					</td>
					<th><span class="red yy">*</span>学位证书取得时间：</th>
					<td>
						<input name="userResumeExt.doctorDegreeTime" value="${userResumeExt.doctorDegreeTime}"
							   class="datepicker validate[required] input "/>&#12288;
					</td>
				</tr>

				<%-- 博士 end--%>

				<tr>
					<th><span class="red">*</span>最高毕业证书编号：</th>
					<td><input name="doctor.certificateNo" value="${doctor.certificateNo}"
							   class="input validate[required]"/>&#12288;
					</td>
					<th><span class="red">*</span>最高毕业证书上传：</th>
					<td>
						<input type="hidden" id="certificateUriValue" name="userResumeExt.certificateUri"
							   value="${userResumeExt.certificateUri}"/>
						<span id="certificateUriSpan" style="display:${!empty userResumeExt.certificateUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="certificateUri" href="javascript:uploadFile('certificateUri','毕业证书');"
						   class="btn">${empty userResumeExt.certificateUri?'':'重新'}上传</a>&nbsp;
						<a id="certificateUriDel" href="javascript:delFile('certificateUri');" class="btn"
						   style="${empty userResumeExt.certificateUri?'display:none':''}">删除</a>&nbsp;
						&nbsp;
					</td>
				</tr>
				<tr>
					<th>最高学位证书编号：</th>
					<td><input name="doctor.degreeNo" value="${doctor.degreeNo}" class="input"/></td>
					<th>最高学位证书上传：</th>
					<td>
						<input type="hidden" id="degreeUriValue" name="userResumeExt.degreeUri"
							   value="${userResumeExt.degreeUri}"/>
						<span id="degreeUriSpan" style="display:${!empty userResumeExt.degreeUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.degreeUri}"
								target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="degreeUri" href="javascript:uploadFile('degreeUri','学位证书');"
						   class="btn">${empty userResumeExt.degreeUri?'':'重新'}上传</a>&nbsp;
						<a id="degreeUriDel" href="javascript:delFileNotValidate('degreeUri');" class="btn"
						   style="${empty userResumeExt.degreeUri?'display:none':''}">删除</a>&nbsp;
					</td>
				</tr>
				<tr class="graduateTh">
					<th><span class="red yy">*</span>最高学历：</th>
					<td colspan="3">
						&nbsp;<select name="user.educationId" class="select graduate validate[required] "
									  style="width: 160px;">
						<option value="">请选择</option>
						<c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
							<option value="${dict.dictId }"
									<c:if test="${user.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName}</option>
						</c:forEach>
					</select>&#12288;&nbsp;
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<div class="div_table" style="display: none;">
			<h4>现有资格信息</h4>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="16%"/>
					<col width="30%"/>
					<col width="16%"/>
					<col width="38%"/>
				</colgroup>
				<tbody>
				<tr>
					<th>专业技术资格：</th>
					<td style="padding-left:10px;">
						<select id="technologyQualificationId" name="userResumeExt.technologyQualificationId"
								class="select" style="width: 160px;">
							<option value="">请选择</option>
							<option value="171" ${userResumeExt.technologyQualificationId eq '171'?'selected':''}>住院医师
							</option>
							<option value="172" ${userResumeExt.technologyQualificationId eq '172'?'selected':''}>主治医师
							</option>
							<option value="173" ${userResumeExt.technologyQualificationId eq '173'?'selected':''}>
								住院中医师
							</option>
							<option value="174" ${userResumeExt.technologyQualificationId eq '174'?'selected':''}>
								主治中医师
							</option>
							<option value="196" ${userResumeExt.technologyQualificationId eq '196'?'selected':''}>无
							</option>
						</select>
					</td>
					<th>取得日期：</th>
					<td colspan="2">
						<input name="userResumeExt.getTechnologyQualificationDate"
							   value="${userResumeExt.getTechnologyQualificationDate}" class="input datepicker"
							   style="width: 149px;" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th>执业资格材料：</th>
					<td style="padding-left:10px;">
						<select id="qualificationMaterialId" name="userResumeExt.qualificationMaterialId" class="select"
								style="width: 160px;">
							<option value="">请选择</option>
							<option value="176" ${userResumeExt.qualificationMaterialId eq '176'?'selected':''}>医师执业证书
							</option>
							<option value="177" ${userResumeExt.qualificationMaterialId eq '177'?'selected':''}>医师资格证书
							</option>
							<option value="178" ${userResumeExt.qualificationMaterialId eq '178'?'selected':''}>
								已通过国家执业医师考试的成绩单
							</option>
							<option value="200" ${userResumeExt.qualificationMaterialId eq '200'?'selected':''}>
								助理执业医师证书（定向大专）
							</option>
							<option value="201" ${userResumeExt.qualificationMaterialId eq '201'?'selected':''}>
								已通过国家助理执业医师考试成绩单
							</option>
							<option value="202" ${userResumeExt.qualificationMaterialId eq '202'?'selected':''}>无
							</option>
						</select>
					</td>
					<th>资格材料编码：</th>
					<td colspan="2"><input name="userResumeExt.qualificationMaterialCode"
										   value="${userResumeExt.qualificationMaterialCode}" class="input"/></td>
				</tr>
				<tr>
					<th>执业类型：</th>
					<td style="padding-left:10px;">

						<select id="practicingCategoryId" name="userResumeExt.practicingCategoryId" class="select"
								style="width: 160px;" onchange="changeCategoryId(this.value)">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">
								<option value="${dictTypeEnumPracticeType.dictId}"
										<c:if test='${userResumeExt.practicingCategoryId==dictTypeEnumPracticeType.dictId}'>selected</c:if>>
										${dictTypeEnumPracticeType.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th>执业范围：</th>
					<td style="padding-left:10px;">
						<select id="practicingScopeId" name="userResumeExt.practicingScopeId" class="select"
								style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
								<c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
								<c:forEach items="${applicationScope[dictKey]}" var="scope">
									<option class="${scope.dictTypeId}" value="${scope.dictId}"
											<c:if test='${userResumeExt.practicingScopeId==scope.dictId and dict.dictId == userResumeExt.practicingCategoryId}'>selected</c:if>>
											${scope.dictName}</option>
								</c:forEach>
							</c:forEach>
						</select>
					</td>
					<script>
						function changeCategoryId(dictId) {
							if (dictId == "") {
								$('#practicingScopeId').val("");
							}
							$('#practicingScopeId option').hide();
							$('#practicingScopeId option[value=""]').show();
							//$('#practicingScopeId').val("${userResumeExt.practicingScopeId}");
							$('#practicingScopeId' + ' option.${dictTypeEnumPracticeType.id}\\.' + dictId).show();
						}

						$(function () {
							changeCategoryId('${userResumeExt.practicingCategoryId}');
						});
					</script>
				</tr>
				<tr>
					<th>资格证书上传：</th>
					<td colspan="3">
						<input type="hidden" id="qualificationMaterialUriValue"
							   name="userResumeExt.qualificationMaterialUri"
							   value="${userResumeExt.qualificationMaterialUri}"/>
						<span id="qualificationMaterialUriSpan"
							  style="display:${!empty userResumeExt.qualificationMaterialUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialUri}"
								target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="qualificationMaterialUri"
						   href="javascript:uploadFile('qualificationMaterialUri','资格证书');"
						   class="btn">${empty userResumeExt.qualificationMaterialUri?'':'重新'}上传</a>&nbsp;
						<a id="qualificationMaterialUriDel" href="javascript:delFile('qualificationMaterialUri');"
						   class="btn" style="${empty userResumeExt.qualificationMaterialUri?'display:none':''}">删除</a>&nbsp;
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<div class="div_table">
			<h4>医师资格信息</h4>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="20%"/>
					<col width="30%"/>
					<col width="20%"/>
					<col width="30%"/>
				</colgroup>
				<tbody>
				<tr>
					<th><span class="red">*</span>是否通过医师资格考试：</th>
					<td style="padding-left:10px;">
						<label>&#12288;<input name="userResumeExt.isPassQualifyingExamination" onchange="showTime('Y')"
											  type="radio"
											  <c:if test="${userResumeExt.isPassQualifyingExamination eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"
											  class="validate[required] isPassQualifyingExamination"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isPassQualifyingExamination" onchange="showTime('N')"
									  type="radio"
									  <c:if test="${userResumeExt.isPassQualifyingExamination eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"
									  class="validate[required] isPassQualifyingExamination"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>

					<th style="display: none" class="examinationTime"><span class="red">*</span>通过医师资格考试时间：</th>
					<td style="display: none" class="examinationTime">
						<input name="userResumeExt.passQualifyingExaminationTime"
							   value="${userResumeExt.passQualifyingExaminationTime}"
							   class="input validate[required] datepicker examinationTimeValue" style="width: 149px;"
							   readonly="readonly"/>
					</td>
				</tr>

				<tr>
					<th><span class="red">*</span>是否获得医师资格证书：</th>
					<td style="padding-left:10px;">
						<label>&#12288;<input name="userResumeExt.isHaveQualificationCertificate"
											  onchange="examsTime('Y')" type="radio"
											  <c:if test="${userResumeExt.isHaveQualificationCertificate eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }"
											  class="validate[required] isHaveQualificationCertificate"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isHaveQualificationCertificate" onchange="examsTime('N')"
									  type="radio"
									  <c:if test="${userResumeExt.isHaveQualificationCertificate eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }"
									  class="validate[required] isHaveQualificationCertificate"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>

					<th style="display: none" class="examTime"><span class="red">*</span>取得医师资格证书时间：</th>
					<td style="display: none" class="examTime">
						<input name="userResumeExt.haveQualificationCertificateTime"
							   value="${userResumeExt.haveQualificationCertificateTime}"
							   class="validate[required] input datepicker examTimeValue" style="width: 149px;"
							   readonly="readonly"/>
					</td>
				</tr>
				<tr style="display: none" class="examTime">

					<th><span class="red">*</span>医师资格级别：</th>
					<td style="padding-left:10px;">
						<select name="userResumeExt.physicianQualificationLevel"
								class="validate[required] select examTimeValue" style="width: 160px;">
							<option value="">请选择</option>
							<option value="zyys" ${userResumeExt.physicianQualificationLevel eq 'zyys'?'selected':''}>
								执业医师
							</option>
							<option value="zyzlys" ${userResumeExt.physicianQualificationLevel eq 'zyzlys'?'selected':''}>
								执业助理医师
							</option>
						</select>
					</td>
					<th><span class="red">*</span>医师资格类别：</th>
					<td style="padding-left:10px;">
						<select name="userResumeExt.physicianQualificationClass"
								class="validate[required] select examTimeValue" style="width: 160px;">
							<option value="">请选择</option>
							<option value="lc" ${userResumeExt.physicianQualificationClass eq 'lc'?'selected':''}>临床
							</option>
							<option value="kq" ${userResumeExt.physicianQualificationClass eq 'kq'?'selected':''}>口腔
							</option>
							<option value="ggws" ${userResumeExt.physicianQualificationClass eq 'ggws'?'selected':''}>
								公共卫生
							</option>
							<option value="zy" ${userResumeExt.physicianQualificationClass eq 'zy'?'selected':''}>中医
							</option>
						</select>
					</td>
				</tr>
				<tr style="display: none" class="examTime">
					<th><span class="red">*</span>医师资格证书编码：</th>
					<td style="width: 300px">
						<input name="userResumeExt.doctorQualificationCertificateCode" id="CertificateCode"
							   onblur="checkValue(this,'27');" class="validate[required] input examTimeValue"
							   value="${userResumeExt.doctorQualificationCertificateCode }"/>
						<span id="CertificateCode_span" style="color: red;font-size: 5px"></span>
					</td>

					<th><span class="red">*</span>上传证书材料：</th>
					<td>
						<input type="hidden" id="doctorQualificationCertificateUrlValue"
							   name="userResumeExt.doctorQualificationCertificateUrl"
							   value="${userResumeExt.doctorQualificationCertificateUrl}"/>
						<span id="doctorQualificationCertificateUrlSpan"
							  style="display:${!empty userResumeExt.doctorQualificationCertificateUrl?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorQualificationCertificateUrl}"
								target="_blank">查看图片</a>]
						</span>
						<a id="doctorQualificationCertificateUrl"
						   href="javascript:uploadFile('doctorQualificationCertificateUrl','证书材料');"
						   class="btn ${empty userResumeExt.doctorQualificationCertificateUrl?'validate[required]':''}">${empty userResumeExt.doctorQualificationCertificateUrl?'':'重新'}上传</a>
						<a id="doctorQualificationCertificateUrlDel"
						   href="javascript:delFile('doctorQualificationCertificateUrl');" class="btn"
						   style="${empty userResumeExt.doctorQualificationCertificateUrl?'display:none':''}">删除</a>
					</td>
				</tr>
				<tr class="scoreType" style="display: none;">
					<th><span class="red">*</span>成绩单类型：</th>
					<td style="padding-left:10px;">
						<select id="qualificationMaterialId2" name="userResumeExt.qualificationMaterialId2"
								class="select validate[required]" style="width: 160px;">
							<option value="">请选择</option>
							<option value="178" ${userResumeExt.qualificationMaterialId2 eq '178'?'selected':''}>
								已通过国家执业医师考试的成绩单
							</option>
							<option value="201" ${userResumeExt.qualificationMaterialId2 eq '201'?'selected':''}>
								已通过国家助理执业医师考试成绩单
							</option>
						</select>
					</td>
					<th><span class="red">*</span>成绩单附件：</th>
					<td>
						<input type="hidden" id="qualificationMaterialId2UrlValue"
							   name="userResumeExt.qualificationMaterialId2Url"
							   value="${userResumeExt.qualificationMaterialId2Url}"/>
						<span id="qualificationMaterialId2UrlSpan"
							  style="display:${!empty userResumeExt.qualificationMaterialId2Url?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialId2Url}"
								target="_blank">查看图片</a>]
						</span>
						<a id="qualificationMaterialId2Url"
						   href="javascript:uploadFile('qualificationMaterialId2Url','成绩单附件');"
						   class="btn">${empty userResumeExt.qualificationMaterialId2Url?'':'重新'}上传</a>
						<a id="qualificationMaterialId2UrlDel" href="javascript:delFile('qualificationMaterialId2Url');"
						   class="btn"
						   style="${empty userResumeExt.qualificationMaterialId2Url?'display:none':''}">删除</a>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>是否获得医师执业证书：</th>
					<td style="padding-left:10px;" width="30%">
						<label>&#12288;<input name="userResumeExt.isHavePracticingCategory" onchange="showTime2('Y')"
											  type="radio"
											  <c:if test="${userResumeExt.isHavePracticingCategory eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.isHavePracticingCategory" onchange="showTime2('N')"
									  type="radio"
									  <c:if test="${userResumeExt.isHavePracticingCategory eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>

					<th class="showTime2" style="display: none;"><span class="red">*</span>取得医师执业证书时间：</th>
					<td class="showTime2" style="display: none;">
						<input name="userResumeExt.havePracticingCategoryTime"
							   value="${userResumeExt.havePracticingCategoryTime}"
							   class="validate[required] input datepicker showTime2Value" style="width: 149px;"
							   readonly="readonly"/>
					</td>

				</tr>

				<tr class="showTime2" style="display: none;">
					<th><span class="red">*</span>执业类型：</th>
					<td style="padding-left:10px;">

						<select id="practicingCategoryLevelId" name="userResumeExt.practicingCategoryLevelId"
								class="validate[required] select showTime2Value" style="width: 160px;"
								onchange="changePracticingCategoryId(this.value)">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">
								<option value="${dictTypeEnumPracticeType.dictId}"
										<c:if test='${userResumeExt.practicingCategoryLevelId==dictTypeEnumPracticeType.dictId}'>selected</c:if>>
										${dictTypeEnumPracticeType.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th><span class="red">*</span>执业范围：</th>
					<td style="padding-left:10px;">
						<select id="practicingCategoryScopeId" name="userResumeExt.practicingCategoryScopeId"
								class="validate[required] select showTime2Value" style="width: 160px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
								<c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
								<c:forEach items="${applicationScope[dictKey]}" var="scope">
									<option class="${scope.dictTypeId}" value="${scope.dictId}"
										<%--											<c:if test='${userResumeExt.practicingCategoryScopeId==scope.dictId}'>selected</c:if>>--%>
											<c:if test='${userResumeExt.practicingCategoryScopeId==scope.dictId and dict.dictId == userResumeExt.practicingCategoryLevelId}'>selected</c:if>>
											${scope.dictName}</option>
								</c:forEach>
							</c:forEach>
						</select>
					</td>
					<script>
						function changePracticingCategoryId(dictId) {
							if (dictId == "") {
								$('#practicingCategoryScopeId').val("");
							}
							$('#practicingCategoryScopeId option').hide();
							$('#practicingCategoryScopeId option[value=""]').show();
							//$('#practicingScopeId').val("${userResumeExt.practicingScopeId}");
							$('#practicingCategoryScopeId' + ' option.${dictTypeEnumPracticeType.id}\\.' + dictId).show();
						}

						$(function () {
							changePracticingCategoryId('${userResumeExt.practicingCategoryLevelId}');
						});
					</script>
				</tr>

				<tr class="showTime2" style="display: none;">

					<th><span class="red">*</span>医师执业证书编码：</th>
					<td>
						<input id="categoryCode" name="userResumeExt.doctorPracticingCategoryCode"
							   class="validate[required] input showTime2Value"
							   value="${userResumeExt.doctorPracticingCategoryCode }"/>
						<span id="categoryCode_span" style="color: red;font-size: 2px"></span>
					</td>

					<th><span class="red">*</span>上传证书材料：</th>
					<td>
						<input type="hidden" id="doctorPracticingCategoryUrlValue"
							   name="userResumeExt.doctorPracticingCategoryUrl"
							   value="${userResumeExt.doctorPracticingCategoryUrl}"/>
						<span id="doctorPracticingCategoryUrlSpan"
							  style="display:${!empty userResumeExt.doctorPracticingCategoryUrl?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.doctorPracticingCategoryUrl}"
								target="_blank">查看图片</a>]
						</span>
						<a id="doctorPracticingCategoryUrl"
						   href="javascript:uploadFile('doctorPracticingCategoryUrl','证书材料');"
						   class="btn ${empty userResumeExt.doctorPracticingCategoryUrl?'validate[required]':''}">${empty userResumeExt.doctorPracticingCategoryUrl?'':'重新'}上传</a>
						<a id="doctorPracticingCategoryUrlDel" href="javascript:delFile('doctorPracticingCategoryUrl');"
						   class="btn"
						   style="${empty userResumeExt.doctorPracticingCategoryUrl?'display:none':''}">删除</a>
					</td>
				</tr>
				<tr>
					<th>特殊岗位证明材料：</th>
					<td class="tsgwzmcl">
						<input type="hidden" id="specialCertificationUriValue"
							   name="userResumeExt.specialCertificationUri"
							   value="${userResumeExt.specialCertificationUri}"/>
						<span id="specialCertificationUriSpan"
							  style="display:${!empty userResumeExt.specialCertificationUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.specialCertificationUri}"
								target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="specialCertificationUri" accept=".jpg,.png,.jpeg"
						   href="javascript:uploadFile('specialCertificationUri','特殊岗位证明材料');"
						   class="btn">${empty userResumeExt.specialCertificationUri?'':'重新'}上传</a>&nbsp;
						<a id="specialCertificationUriDel" href="javascript:delFilets('specialCertificationUri');"
						   class="btn" style="${empty userResumeExt.specialCertificationUri?'display:none':''}">删除</a>&nbsp;
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<div class="div_table">
			<h4>西部支援情况</h4>
			<table border="0" align="center" cellpadding="0" cellspacing="0" class="base_info">
				<colgroup>
					<col width="50%"/>
					<col width="50%"/>
				</colgroup>
				<tbody>
				<tr>
					<th><span class="red">*</span>是否为西部支援行动住院医师：</th>
					<td>
						<label>&#12288;<input name="userResumeExt.westernSupportResidents"
											  onchange="westernSupport('Y')" type="radio"
											  <c:if test="${userResumeExt.westernSupportResidents eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if>
											  value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
						<label><input name="userResumeExt.westernSupportResidents" onchange="westernSupport('N')"
									  type="radio"
									  <c:if test="${userResumeExt.westernSupportResidents eq GlobalConstant.FLAG_N }">checked="checked"</c:if>
									  value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
					</td>
				</tr>

				<tr style="display: none" class="western">
					<th><span class="red">*</span>西部支援行动住院医师送出省份：</th>
					<td>
						<input name="userResumeExt.westernSupportResidentsSendProvince"
							   class="validate[required] input westernValue"
							   value="${userResumeExt.westernSupportResidentsSendProvince}"/>
					</td>
				</tr>
				<tr style="display: none" class="western">
					<th><span class="red">*</span>西部支援行动住院医师送出单位统一社会信用代码：</th>
					<td>
						<input name="userResumeExt.westernSupportResidentsSendWorkUnitCode"
							   class="validate[required] input westernValue"
							   value="${userResumeExt.westernSupportResidentsSendWorkUnitCode}"/>
					</td>
				</tr>
				<tr style="display: none" class="western">
					<th><span class="red">*</span>西部支援行动住院医师送出单位：</th>
					<td>
						<input name="userResumeExt.westernSupportResidentsSendWorkUnit"
							   class="validate[required] input westernValue"
							   value="${userResumeExt.westernSupportResidentsSendWorkUnit}"/>
					</td>
				</tr>
				<tr style="display: none" class="western">
					<th><span class="red">*</span>西部支援行动住院医师接收省份：</th>
					<td>
						<input name="userResumeExt.westernSupportResidentsReceiveProvince"
							   class="validate[required] input westernValue"
							   value="${userResumeExt.westernSupportResidentsReceiveProvince}"/>
					</td>
				</tr>
				<tr style="display: none" class="western">
					<th><span class="red">*</span>西部支援行动住院医师接收省份培训基地(医院)统一社会信用代码：</th>
					<td>
						<input name="userResumeExt.westernSupportResidentsReceiveHospitalCode"
							   class="validate[required] input westernValue"
							   value="${userResumeExt.westernSupportResidentsReceiveHospitalCode}"/>
					</td>
				</tr>
				<tr style="display: none" class="western">
					<th><span class="red">*</span>西部支援行动住院医师接收省份培训基地(医院)：</th>
					<td>
						<input name="userResumeExt.westernSupportResidentsReceiveHospital"
							   class="validate[required] input westernValue"
							   value="${userResumeExt.westernSupportResidentsReceiveHospital}"/>
					</td>
				</tr>

				</tbody>
			</table>
		</div>
	</form>
	<div class="btn_info">
		<input type="button" style="width:100px;" class="btn_green" onclick="saveDoctorInfo();"
			   value="保&#12288;存"></input><br/><br/>
		<%--		<font color="red">保存后请到学员报名菜单处报名并提交，否则基地无法审核.</font>--%>
		<c:if test="${param.openType eq 'open'}">
			<input type="button" style="width:100px;" class="btn_green" onclick="jboxClose();"
				   value="关&#12288;闭"></input>
		</c:if>
	</div>
</div>
