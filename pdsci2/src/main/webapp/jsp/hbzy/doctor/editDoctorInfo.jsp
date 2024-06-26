<link rel="stylesheet" type="text/css" href="<s:url value='/css/validationEngine.jquery.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript" src="<s:url value='/js/jquery.validationEngine-zh_CN.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery.validationEngine${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var forms = $("form");
        $.each(forms , function(i , form){
            $(form).validationEngine("attach",{
                        promptPosition : "topRight",
                        scroll:true,
                        autoPositionUpdate: true,
                        //addPromptClass:"formError-noArrow formError-text"
                        autoHidePrompt:true,
                        maxErrorsPerField:1,
                        showOneMessage : true
                    }
            );
        });
    });
</script>
<script type="text/javascript" src="<s:url value='/js/jquery.cxselect${min}.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
    .boxHome .item:HOVER{background-color: #eee;}
    .cur{color:red}
    .pxxx{position: relative;top:30px; right: 175px;}
    .infoAudit h4{
        border-left: 4px solid #dc781d;
    }
    .changeinfo{position:absolute; background-color:#fff; padding:10px; border:1px solid  #dcdcdc; width:148px;}
    .icon_up{background-image:url("<s:url value='/css/skin/${skinPath}/images/up2.png'/>"); background-repeat:no-repeat; background-position: top center; padding:5px;position: absolute;top: -6px;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
        $('#practicingScopeId option').hide();
        showProve();
    });
    (function($){
        $.fn.likeSearchInit = function(option){
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus",function(){
                var boxHome = $("#"+spaceId+"Sel");
                boxHome.show();
                if($.trim(this.value)){
                    $("p."+spaceId+".item",boxHome).hide();
                    var items = boxHome.find("p."+spaceId+".item[value*='"+this.value+"']").show();
                    if(!items){
                        boxHome.hide();
                    }
                }else{
                    $("p."+spaceId+".item",boxHome).show();
                }
            });
            searchInput.on("blur",function(){
                var boxHome = $("#"+ spaceId+"Sel");
                if(!$("."+spaceId+".boxHome.on").length){
                    boxHome.hide();
                }
            });
            $("."+spaceId+".boxHome").on("mouseenter mouseleave",function(){
                $(this).toggleClass("on");
            });

            $("."+spaceId+".boxHome .item").click(function(){
                var boxHome = $("."+spaceId+".boxHome");
                boxHome.hide();
                var value = $(this).attr("value");
                var input = $("#"+spaceId);
                input.val(value);
                if(spaceId=='psxx'){
                    $("#school").val(value);
                    compare();
                }
                if(option.clickActive){
                    option.clickActive($(this).attr("flow"));
                }
// 			var content = $("#clone").html().replace("title",value);
// 			$("#"+input.attr("id")+"Div").append(content);
            });
        };
    })(jQuery);
    function showProve()
    {

        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        jboxGet(url,null, function(json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            <%--var newJsonData=new Array();--%>
            <%--var j=0;--%>
            <%--for(var i=0;i<json.length;i++){--%>
                <%--var provData = json[i];--%>
                <%--if(provData.v=='320000'){--%>
                    <%--if("${sendCityId}"!="") {--%>
                        <%--var s = provData.s;--%>
                        <%--var k = 0;--%>
                        <%--var newS = new Array();--%>
                        <%--for (var m = 0; m < s.length; m++) {--%>
                            <%--if(s[m].v=="${sendCityId}"){--%>
                                <%--newS[k++]=s[m];--%>
                            <%--}--%>
                        <%--}--%>
                        <%--provData.s=newS;--%>
                    <%--}--%>
                    <%--newJsonData[j++]=provData;--%>
                <%--}--%>
            <%--}--%>
            $.cxSelect.defaults.url = json;
            $.cxSelect.defaults.nodata = "none";
            if("${sessionScope.userListScope==GlobalConstant.USER_LIST_CHARGE}"=="true"){
                $.cxSelect.defaults.required = true;
            }
            $("#native").cxSelect({
                selects : ["province", "city"],
                nodata : "none",
                firstValue : ""
            });
        },null,false);
    }
    function compare(){
        if($("#school").val()!=$("#workOrgName").val()){
            changeOrg();
        }
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
            var url = "<s:url value='/hbzy/doctor/checkUserUnique'/>";
            jboxPost(url, data,
                    function(resp){
                        if(resp == "${GlobalConstant.USER_ID_NO_REPETE}"){
                            var cretTypeId = $("#cretTypeId").val();
                            if("${certificateTypeEnumShenfenzheng.id}" == cretTypeId){
                                jboxTip(resp);
                            }else{
                                jboxTip("该证件号已经被注册！");
                            }
                            $("input[name='user.idNo']").focus();
                        }else if(resp == "${GlobalConstant.USER_PHONE_REPETE}"){
                            jboxTip(resp);
                            $("input[name='user.userPhone']").focus();
                        }
                    }, null, false);
        }
    }

    /**
     * 保存
     */
    function saveDoctorInfo(){
        var sendProveName = $("#sendProvId option:selected").text();
        $("[name='doctor.sendProvName']").val(sendProveName);
        if(sendProveName == '请选择'){
            $("[name='doctor.sendProvName']").val("");
        }
        var sendCityName = $("#sendCityId option:selected").text();
        $("[name='doctor.sendCityName']").val(sendCityName);
        if(sendCityName == '请选择'){
            $("[name='doctor.sendCityName']").val("");
        }
//	var docCompanyType = $("#companyTypeValue").val();
//	if(docCompanyType == "" && $("#doctorType_Company").is(":checked")){
//		jboxTip("您勾选了单位人，请选择单位人类型！");
//		return false;
//	}

        var certificateUriValue = $("#certificateUriValue").val();
        if(!certificateUriValue){
            jboxTip("请上传最高毕业证书！");
            return false;
        }
//	var degreeUriValue = $("#degreeUriValue").val();
//	if(!degreeUriValue){
//		jboxTip("请上传最高学位证书！");
//		return false;
//	}
        if(false==$("#doctorForm").validationEngine("validate")){
            return false;
        }
        var nameResult=0;
        var workOrgName=$("#workOrgName").val();
        var personType=$('input:radio[name="doctor.doctorTypeId"]:checked').val();
        if("${jszyResDocTypeEnumGraduate.id}"==personType){
            <c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
            if("${dict.dictName}"==workOrgName){
                nameResult=1;
            }
            </c:forEach>
            if(nameResult==0){
                $("#psxx").val("");
                $("#workOrgName").val("");
                jboxTip("派送学校的值与字典不符,请重新输入！");
                return false;
            }
        }
        var bkbyyxResult=0;
        var byyxResult=0;
        var bsbyyxResult=0;
        var bkbyyx = $("#bkbyyx").val();
        var byyx=$("#byyx").val();
        var bsbyyx=$("#bsbyyx").val();
        <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" >
        if("${dict.dictName}"==bkbyyx){
            bkbyyxResult=1;
        }
        if("${dict.dictName}"==byyx){
            byyxResult=1;
        }
        if("${dict.dictName}"==bsbyyx){
            bsbyyxResult=1;
        }

        </c:forEach>
        if(bkbyyxResult==0&&bkbyyx!=""){
            $("#bkbyyx").val("");
            jboxTip("本科毕业院校的值与字典不符，请重输！");
            return false;
        }
        if(byyxResult==0&&byyx!=""){
            $("#byyx").val("");
            jboxTip("硕士毕业院校的值与字典不符，请重输！");
            return false;
        }
        if(bsbyyxResult==0&&bsbyyx!=""){
            $("#bsbyyx").val("");
            jboxTip("博士毕业院校的值与字典不符，请重输！");
            return false;
        }
        getUserResumeExtName();
        getFantasticFour();
        getRenYuanShuXing();
        //如果不是身份证生日信息不根据IdNo自动带入
        if("${user.cretTypeId ne certificateTypeEnumShenfenzheng.id }"){
            $("#birthday").val($("#userBirthday").val());
        }
        var url = "<s:url value='/hbzy/doctor/saveDoctorInfo'/>";
        jboxPost(url, $("#doctorForm").serialize(),
                function(resp){
                    if(resp == "${GlobalConstant.USER_ID_NO_REPETE}"){
                        var cretTypeId = $("#cretTypeId").val();
                        if("${certificateTypeEnumShenfenzheng.id}" == cretTypeId){
                            jboxTip(resp);
                        }else{
                            jboxTip("该证件号已经被注册！");
                        }
                        $("input[name='user.idNo']").focus();
                    }else if(resp == "${GlobalConstant.USER_PHONE_REPETE}"){
                        jboxTip(resp);
                        $("input[name='user.userPhone']").focus();
                    }else if(resp != "${GlobalConstant.SAVE_FAIL}"){
                        jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
                        $("#topUserName").text(resp);
                        doctorBaseInfo('${user.userFlow}');
                    }
                }, null, false);
    }
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
    function getRenYuanShuXing(){
        var personnelAttributeName = $("#personnelAttributeId").find("option:selected").text();
        $("#personnelAttributeName").val(personnelAttributeName);
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

    /**
     * 上传头像
     */
    function uploadImage(){
        $.ajaxFileUpload({
            url:"<s:url value='/sys/user/userHeadImgUpload'/>?userFlow=${user.userFlow}",
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

    function uploadFile(type,typeName) {
        var url = "<s:url value='/hbzy/doctor/uploadFile'/>?userFlow=${user.userFlow}&operType="+type;
        jboxOpen(url, "上传"+typeName, 525, 200);
    }

    function delFile(type) {
        jboxConfirm("确认删除？" , function(){
            $("#"+type+"Del").hide();
            $("#"+type+"Span").hide();
            $("#"+type).text("上传");
            $("#"+type+"Value").val("");
            $("#file").val(null);
        });
    }

    function addValidate(cretTypeId){
        if("${certificateTypeEnumShenfenzheng.id}" == cretTypeId){
            $("#idNo").addClass("validate[custom[chinaId]]");
            $("#idNo").attr("onchange","writeBirthday(this);checkUserUnique();");
            $("#userBirthday").attr("disabled",true);
            $("#birthday").attr("disabled",false);
            $("#birthday").val($("#userBirthday").val());
        }else{
            $("#idNo").removeClass("validate[custom[chinaId]]");
            $("#idNo").attr("onchange","checkUserUnique();");
            $("#userBirthday").attr("disabled",false);
//		$("#birthday").attr("disabled",true);
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

    function changeWorkAdress(doctorTypeId,isInit){
        if(doctorTypeId == "${jszyResDocTypeEnumCompany.id}"){
            $("#medicalHeaithOrgIdTd").attr("colspan",4);
            $("#address1").attr("colspan",4);
            $(".school").hide();
            $(".address").show();

            $(".cityId").hide();

//		$("#doctorTypeNameTd").removeAttr("colspan");
//		selectCompanyType();
            changeOrgLevel($("#medicalHeaithOrgId").val());
        }
        if(doctorTypeId == "${jszyResDocTypeEnumCompanyEntrust.id}"){
            $("#medicalHeaithOrgIdTd").attr("colspan",4);
            $("#address1").attr("colspan",1);
            $("#address2").attr("colspan",2);
            $(".school").hide();
            $(".address").show();
            $(".cityId").show();
//		$("#doctorTypeNameTd").removeAttr("colspan");
//		selectCompanyType();
            changeOrgLevel($("#medicalHeaithOrgId").val());
        }else{

            $("[name='doctor.sendProvName']").val("");
            $("[name='doctor.sendProvId']").val("");
            $("[name='doctor.sendCityName']").val("");
            $("[name='doctor.sendCityId']").val("");
        }
        if(doctorTypeId == "${jszyResDocTypeEnumSocial.id}"){
            $(".school").hide();
            $(".hospital").hide();
            $(".medical").hide();
            $(".address").hide();
            $(".cityId").hide();
//		$("#doctorTypeNameTd").attr("colspan",4);
        }
        if(doctorTypeId == "${jszyResDocTypeEnumGraduate.id}"){
            $(".address").hide();
            $(".hospital").hide();
            $(".medical").hide();
            $(".school").show();
            $(".cityId").hide();
//		$("#doctorTypeNameTd").removeAttr("colspan");
        }
        if(doctorTypeId==""){
            $(".school").hide();
            $(".address").hide();
            $(".cityId").hide();
//		$("#doctorTypeNameTd").attr("colspan",4);
        }

        if(doctorTypeId == "${jszyResDocTypeEnumGraduate.id}"){
            //alert("www");
            $("#isNotMaster").hide();
            $("#isMaster").show();
            $("#statue1").show();
            $("#statue2").hide();
            $("#isMaster1").attr("checked",true);
            $("#statueY").attr("checked",true);
            $("#statueN").removeAttr("checked");
            //隐藏本科，硕士和博士操作
            checkBx("Y",'master');
            checkIsStudy("1",'master');
        }else if(doctorTypeId != "${jszyResDocTypeEnumGraduate.id}"){
            if(!isInit)
            {
                $("#isMaster1").removeAttr("checked");
                $("#statueY").removeAttr("checked");
                $("#statueN").attr("checked",true);
                checkIsStudy("2",'master');
                checkBx("N",'master');
            }
            $("#isNotMaster").show();
            $("#isMaster").show();
            $("#statue1").hide();
            $("#statue2").show();
        }

//	showA4companyType(doctorTypeId);
    }

    function changeStatue(stat){
        if(stat == "yes"){
            $("#isNotMaster").show();
            $("#isMaster").show();
            $("#statue1").hide();
            $("#statue2").show();
            $("#statueN").attr("checked",true);
        }else{
            $("#statueN").removeAttr("checked");
            $("#statueY").removeAttr("checked");
        }
    }
    <%--function showA4companyType(item){--%>
    <%--var companyLable = $(".doctorType[for='doctorType_Company']");--%>
    <%--if(item == "${jszyResDocTypeEnumCompany.id}"){--%>
    <%--companyLable.wrap(function(){--%>
    <%--return '<a href=\"javascript:void(0);\" onclick=\"selectCompanyType();\">';--%>
    <%--});--%>
    <%--selectCompanyType();--%>
    <%--}else {--%>
    <%--$("#companyTypeValue").val("");--%>
    <%--$("#ownCompany").attr("checked",false);--%>
    <%--$("#otherCompany").attr("checked",false);--%>
    <%--if(companyLable.parent('a').length > 0){--%>
    <%--companyLable.unwrap();--%>
    <%--}--%>
    <%--}--%>
    <%--}--%>
    $(function(){
        $('.datepicker').datepicker();
        addValidate("${user.cretTypeId}");
        changeWorkAdress("${doctor.doctorTypeId}",true);
        <%--if("${doctor.doctorTypeId}"!="${jszyResDocTypeEnumCompany.id}"||$("#doctorTypeId").val()==''){--%>
        <%--$(".address").hide();--%>
        <%--//$("#workOrgName").val("");--%>
        <%--$("#doctorTypeNameTd").attr("colspan","4");--%>
        <%--}--%>
        <%--if('${doctor.doctorTypeId}' == "${jszyResDocTypeEnumCompany.id}"){--%>
        <%--$("#medicalHeaithOrgIdTd").attr("colspan",4);--%>
        <%--$(".school").hide();--%>
        <%--$(".address").show();--%>
        <%--$("#doctorTypeNameTd").removeAttr("colspan");--%>
        <%--//		selectCompanyType();--%>
        <%--// 		changeOrgLevel("${userResumeExt.medicalHeaithOrgId}");--%>
        <%--}--%>

        <%--if('${doctor.doctorTypeId}' == "${jszyResDocTypeEnumSocial.id}"){--%>
        <%--$(".school").hide();--%>
        <%--$(".hospital").hide();--%>
        <%--$(".medical").hide();--%>
        <%--$(".address").hide();--%>
        <%--$("#doctorTypeNameTd").attr("colspan",4);--%>
        <%--}--%>
        <%--if('${doctor.doctorTypeId}' == "${jszyResDocTypeEnumGraduate.id}"){--%>
        <%--$(".address").hide();--%>
        <%--$(".hospital").hide();--%>
        <%--$(".medical").hide();--%>
        <%--$(".school").show();--%>
        <%--$("#doctorTypeNameTd").removeAttr("colspan");--%>
        <%--}--%>
        <%--if('${doctor.doctorTypeId}'==""){--%>
        <%--$(".school").hide();--%>
        <%--$(".address").hide();--%>
        <%--$("#doctorTypeNameTd").attr("colspan",4);--%>
        <%--}--%>
        <%--showA4companyType('${doctor.doctorTypeId}');--%>

        $("#byyx").likeSearchInit({
            clickActive:function(flow){
                $("."+flow).show();
            }
        });
        $("#bkbyyx").likeSearchInit({
            clickActive:function(flow){
                $("."+flow).show();
            }
        });
        $("#psxx").likeSearchInit({
            clickActive:function(flow){
                $("."+flow).show();
            }
        });
        $("#bsbyyx").likeSearchInit({
            clickActive:function(flow){
                $("."+flow).show();
            }
        });
        changeTitle();
        changeLevel("${doctor.workOrgLevelId}");
        $("#showImg").load(function(){
            $("#idNoWatermark").show();
        });
        if("${user.cretTypeId eq certificateTypeEnumShenfenzheng.id }"&&$("${user.userBirthday}").val()==""){
            $("#birthday").attr("disabled",true);
        }
        $("."+"377").remove();
        if("${userResumeExt.graduatedName}"=="无"){
            $(".bk").removeClass("validate[required]");
            $(".yy").hide();
        }

        var tabCourse = $('.icon-head');
        tabCourse.on('mouseover',function(){
            $("#changeInfo").show();
        });
        tabCourse.on('mouseout',function(){
            $(".pxxx").hide();
        });
        //隐藏毕业操作
        checkIsStudy("${userResumeExt.masterStatue}",'master');
        checkIsStudy("${userResumeExt.doctorStatue}",'doctor');
        //隐藏本科，硕士和博士操作
        checkBx("${userResumeExt.isMaster}",'master');
        checkBx("${userResumeExt.isDoctor}",'doctor');
        changeOrgLevel("${userResumeExt.medicalHeaithOrgId}");
        checkIsPartner("${doctor.isPartner}");
        showId("${doctor.doctorLicenseFlag}");
        //如果不是身份证生日信息不根据IdNo自动带入
        if("${user.cretTypeId ne certificateTypeEnumShenfenzheng.id }"){
            $("#userBirthday").val("${user.userBirthday}");
        }
    });

    function showId(radio) {
        if (radio == 'Y') $(".qualifiedNoTd, .qualifiedNoTh").show();
        else $(".qualifiedNoTd, .qualifiedNoTh").hide();
    }
    function changeTitle(){
        $(".doctorType").each(function(){
            if($(this).text()=="${jszyResDocTypeEnumCompany.name}"){
                $(this).attr("title","已落实工作岗位参加住院医师规范化培训的人员");
            }
            if($(this).text()=="${jszyResDocTypeEnumCompanyEntrust.name}"){
                $(this).attr("title","已落实工作岗位参加住院医师规范化培训的人员");
            }
            if($(this).text()=="${jszyResDocTypeEnumSocial.name}"){
                $(this).attr("title","未落实工作岗位参加住院医师规范化培训的人员");
            }
            if($(this).text()=="${jszyResDocTypeEnumGraduate.name}"){
                $(this).attr("title","考入高等医学院校临床专业学位硕士研究生的在校学生");
            }
        });

    }
    function changeBirthday(vari){
        $("#birthday").val($(vari).val());
    }
    function changeLevel(orgLevel){
        if(orgLevel!=""){
            $("#"+orgLevel).show();
        }
    }
    function changeOrgLevel(value){
        if(value!=""){
            $(".dict").hide();
            $("#"+value).show();
        }else{
            $(".dict").hide();
        }
        if(value=="1"){
            $(".medical").hide();
            $("#medicalHeaithOrgIdTd").removeAttr("colspan");
            $(".hospital").show();
            $("#TD").attr("colspan",2);
        }
        if(value=="2"){
            $(".medical").hide();
            $("#medicalHeaithOrgIdTd").attr("colspan",4);
            $(".hospital").hide();
        }
        if(value=="" | value=="4"){
            $(".medical").hide();
            $("#medicalHeaithOrgIdTd").attr("colspan",4);
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
        var personType=$('input:radio[name="doctor.doctorTypeId"]:checked').val();
        if(personType == "${jszyResDocTypeEnumCompany.id}" || personType == "${jszyResDocTypeEnumCompanyEntrust.id}"){
            $("#workOrgName").val(org);
        }
        if(personType== "${jszyResDocTypeEnumGraduate.id}"){
            $("#workOrgName").val(school);
        }
        if(personType== "${jszyResDocTypeEnumSocial.id}"){
            $("#workOrgName").val("");
        }
    }
    function changeYx(obj){
        var bkbyyx =$(obj).attr("value");
        if(bkbyyx=="无"||bkbyyx==""){
            $(".bk").removeClass("validate[required]");
            $(".yy").hide();
        }else{
            $(".bk").addClass("validate[required]");
            $(".yy").show();
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
    //function selectCompanyType(){
    //	var html = $("#companyType").html();
    //	jboxOpenContent(html,"单位人类型" , 200,135,true);
    //}
    //function changeCompanyType(vari){
    //	$("#companyTypeValue").val(vari);
    //	if(vari == '1'){
    //		$("#ownCompany").attr("checked","checked");
    //		$("#otherCompany").attr("checked",false);
    //	}else{
    //		$("#ownCompany").attr("checked",false);
    //		$("#otherCompany").attr("checked","checked");
    //	}
    //}
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
    function changeSourceProvincesName(){
        var sname = $("#orgProvId").find("option:selected").text();
        $("#sourceProvincesName").val(sname);
    }
</script>
<div class="main_hd">
    <h2 class="underline">基本信息</h2>
</div>
<div class="infoAudit" <c:if test="${empty param.openType}">style="height: 1300px;"</c:if>>
    <form id="doctorForm" style="position:relative;">
        <input type="hidden" name="user.userFlow" value="${user.userFlow}"/>
        <input type="hidden" id="doctorFlow" name="doctor.doctorFlow" value="${doctor.doctorFlow}"/>
        <input type="hidden" id="technologyQualificationName" name="userResumeExt.technologyQualificationName" value="${userResumeExt.technologyQualificationName}"/>
        <input type="hidden" id="qualificationMaterialName" name="userResumeExt.qualificationMaterialName" value="${userResumeExt.qualificationMaterialName}"/>
        <input type="hidden" id="practicingCategoryName" name="userResumeExt.practicingCategoryName" value="${userResumeExt.practicingCategoryName}"/>
        <input type="hidden" id="practicingScopeName" name="userResumeExt.practicingScopeName" value="${userResumeExt.practicingScopeName}"/>
        <input type="hidden" id="birthday" name="user.userBirthday" value="${user.userBirthday }"/>
        <input type="hidden" name="userResumeExt.zbkbySpe" value="${userResumeExt.zbkbySpe}" />

        <!-- 学位类型Name-->
        <input type="hidden" id="masterDegreeTypeName" name="userResumeExt.masterDegreeTypeName" value="${userResumeExt.masterDegreeTypeName}"/>
        <input type="hidden" id="doctorDegreeTypeName" name="userResumeExt.doctorDegreeTypeName" value="${userResumeExt.doctorDegreeTypeName }"/>
        <!-- 最高学历相关信息-->
        <input type="hidden" id="uEducationName" name="user.educationName" value="${user.educationName}"/>
        <input type="hidden" id="dGraduatedName" name="doctor.graduatedName" value="${doctor.graduatedName}"/>
        <input type="hidden" id="dSpecialized" name="doctor.specialized" value="${doctor.specialized}"/>
        <input type="hidden" id="dGraduationTime" name="doctor.graduationTime" value="${doctor.graduationTime}"/>
        <!-- 单位人类型-->
        <%--<input type="hidden" id="companyTypeValue" name="doctor.companyType" value="${doctor.companyType}"/>--%>
        <!-- 生源省份Name-->
        <input type="hidden" id="sourceProvincesName" name="doctor.sourceProvincesName" value="${doctor.sourceProvincesName}"/>
        <%--人员属性--%>
        <input type="hidden" id="personnelAttributeName" name="userResumeExt.personnelAttributeName" value="${userResumeExt.personnelAttributeName}"/>
        <div class="search_table">
            <h4 >基本信息</h4>
            <table border="0" cellpadding="0" cellspacing="0" class="base_info" >
                <colgroup>
                    <col width="16%"/>
                    <col width="30%"/>
                    <col width="16%"/>
                    <col width="23%"/>
                    <col width="15%"/>
                </colgroup>
                <tbody>
                <tr>

                    <th><span class="red">*</span>&#12288;姓&#12288;&#12288;名：</th>
                    <td>
                        <input name="user.userName" value="${user.userName}" class="validate[required] input"/></td>
                    <th><span class="red">*</span>&#12288;性&#12288;&#12288;别：</th>
                    <td>
                        &nbsp;<label><input type="radio" class='validate[required]' style="width:auto;" name="user.sexId" value='${userSexEnumMan.id}' ${user.sexId eq userSexEnumMan.id?'checked':''}/>&nbsp;${userSexEnumMan.name}</label>&#12288;
                        <label><input type="radio" class='validate[required]' style="width:auto;" name="user.sexId" value='${userSexEnumWoman.id}' ${user.sexId eq userSexEnumWoman.id?'checked':''}/>&nbsp;${userSexEnumWoman.name}</label>
                    </td>
                    <td rowspan="5" style="text-align: center;">
                        <font id="idNoWatermark" style="display:none;font-size:9px;color:red; position:relative; top: 110px;">${user.idNo }</font>
                        <img src="${sysCfgMap['upload_base_url']}/${user.userHeadImg}" id="showImg"
                             style="margin-top: -30px;" width="130px" height="150px"
                             onerror="this.src='<s:url value="/css/skin/up-pic.jpg"/>'"/>
                        <div style="cursor: pointer; display:block;padding-top: 10px;">
                            [<a onclick="$('#imageFile').click();">${empty user.userHeadImg?'上传头像':'重新上传'}</a>]
                            <img  class="icon-head"  src="<s:url value='/css/skin/${skinPath}/images/infoma.png'/>"/>
                            <div id="changeInfo" class="pxxx" style="display: none;margin-left: 140px">
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
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>&#12288;证件类型：</th>
                    <td style="padding-left:10px;">
                        <select name="user.cretTypeId" id="cretTypeId" onchange="addValidate(this.value)" class="validate[required] select" style="width: 160px;">
                            <option value="">请选择</option>
                            <c:forEach items="${certificateTypeEnumList}" var="certificateType">
                                <option value="${certificateType.id}" ${certificateType.id eq user.cretTypeId?'selected':''}>${certificateType.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th><span class="red">*</span>&#12288;证&ensp;件&ensp;号：</th>
                    <td>
                        <c:if test="${empty doctor.graduationStatusId}">
                            <input type="text" name="user.idNo" id="idNo" value="${user.idNo}"  onchange="checkUserUnique();" class="validate[required] input"/>
                        </c:if>
                        <c:if test="${!empty doctor.graduationStatusId}"><label>${user.idNo}</label><input name="user.idNo" value="${user.idNo}" type="hidden"></c:if>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>&#12288;民&#12288;&#12288;族：</th>
                    <td style="padding-left:10px;">
                        <select name="user.nationId" class="validate[required] select" style="width: 160px;">
                            <option value="">请选择</option>
                            <c:forEach items="${userNationEnumList}" var="userNation">
                                <option value="${userNation.id}" ${userNation.id eq user.nationId?'selected':''}>${userNation.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th><span class="red">*</span>&#12288;生&#12288;&#12288;日：</th>
                    <td>
                        <input id="userBirthday"  value="${user.userBirthday}"  class="validate[required] input datepicker" onchange="changeBirthday(this);" style="width: 149px;" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th><font color="red">*</font>&#12288;年&#12288;&#12288;龄：</th>
                    <td>
                        <input type="text" name="userResumeExt.age" value="${userResumeExt.age}" class="validate[required] input"/>
                    </td>
                    <th><span class="red">*</span>&#12288;手&#12288;&#12288;机：</th>
                    <td><input name="user.userPhone" value="${user.userPhone}" onchange="checkUserUnique();" class="validate[required,custom[mobile]] input"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>&#12288;固定电话：</th>
                    <td><input name="userResumeExt.telephone" value="${userResumeExt.telephone}" class="input validate[required,custom[phone2]]"/></td>
                    <th><span class="red">*</span>&#12288;电子邮箱：</th>
                    <td><input name="user.userEmail" value="${user.userEmail}" class="validate[required,custom[email]] input" readonly="readonly" style="width: 98%; border: none;"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>&#12288;计算机能力：</th>
                    <td><input name="doctor.computerSkills" value="${doctor.computerSkills }" class="input validate[required]"/></td>
                    <th><span class="red">*</span>&#12288;外语能力：</th>
                    <td colspan="2"><input name="doctor.foreignSkills" value="${doctor.foreignSkills }" class="input validate[required]"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>&#12288;人员属性：</th>
                    <td style="padding-left:10px;">
                        <select id="personnelAttributeId" class="select validate[required]" name="userResumeExt.personnelAttributeId" style="width: 160px">
                            <option value="">请选择</option>
                            <c:forEach items="${personnelAttributeEnumList}" var="tra">
                                <option value="${tra.id}" <c:if test="${userResumeExt.personnelAttributeId eq tra.id}">selected="selected"</c:if>>${tra.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th><span class="red">*</span>&#12288;人员类型：</th>
                    <td colspan="2" id="doctorTypeNameTd">
                        <c:forEach items="${jszyResDocTypeEnumList}" var="doctorType">
                            <input type="radio" id="doctorType_${doctorType.id }" name="doctor.doctorTypeId" class="validate[required]" onchange="changeWorkAdress(this.value,false);"  value="${doctorType.id}"style="width: 20px"<c:if test="${doctor.doctorTypeId eq  doctorType.id }">checked="checked"</c:if>/>
                            <label style="cursor: pointer;" class="doctorType" for="doctorType_${doctorType.id }" onmouseover="changeTitle(this);">${doctorType.name }</label>
                        </c:forEach>
                        <%--<div style="display: none;">--%>
                        <%--<div id="companyType">--%>
                        <%--<span style="margin-left:25px;" class="red">Tip：单位人类型为必选！</span>--%>
                        <%--<input type="radio" id="ownCompany" name="doctorCompanyType" value="1" style="margin-left:50px;margin-top: 10px;" onchange="changeCompanyType(this.value);" <c:if test="${doctor.companyType eq '1' }">checked="checked"</c:if>/>--%>
                        <%--<label style="cursor: pointer;" style="margin-top: 10px;" for="ownCompany" >本单位人</label><br/>--%>
                        <%--<input type="radio" id="otherCompany" name="doctorCompanyType" value="2" style="margin-left:50px;margin-top: 10px;" onchange="changeCompanyType(this.value);" <c:if test="${doctor.companyType eq '2' }">checked="checked"</c:if>/>--%>
                        <%--<label style="cursor: pointer;" style="margin-top: 10px;"  for="otherCompany" >委培单位人</label><br/>--%>
                        <%--<input style="margin-left:60px;margin-top: 10px;" type="button" class="btn_brown" onclick="jboxClose();;" value="保存" />--%>
                        <%--</div>--%>
                        <%--</div>--%>
                    </td>
                </tr>
                <tr>
                    <th class="school" style="display: none;"><span class="red">*</span>&#12288;派送学校：</th>
                    <td class="school" colspan="4" style="display: none;">
                        <input id="psxx"  value="${doctor.workOrgName}" class="toggleView input validate[required]" type="text" onchange="changeOrg();"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;margin-left: 2px">
                            <div class="boxHome psxx" id="psxxSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                <c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
                                    <p  class="item psxx" flow="${dict.dictId}" value="${dict.dictName}" style="line-height: 20px; padding:10px 0;cursor: default; ">${dict.dictName}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <input type="hidden" id="school" value=""/>
                    </td>
                    <th class="address" id="address" style="display: none;"><span class="red">*</span>&#12288;派送单位：</th>
                    <td class="address" id="address1" style="display: none;"><input id="work" value="${doctor.workOrgName}" class="validate[required] input" onchange="changeOrg();"/>
                        <input type="hidden" id="workOrgName" name="doctor.workOrgName"  value="${doctor.workOrgName}"/>
                    </td>
                    <th class="cityId" style="display: none;"><span class="red">*</span>&#12288;所属地市：</th>
                    <td class="cityId" id="address2"  style="display: none;">
                        <%--<select id="cityId"  class="select validate[required] cityId" name="resDoctor.cityId" style="width: 162px">
                            <option value="">请选择</option>
                            <c:forEach items="${cityEnumList}" var="c">
                                <option value="${c.id}" <c:if test="${resDoctor.cityId eq c.id}">selected="selected"</c:if>>${c.name}</option>
                            </c:forEach>
                        </select>--%>
                        <div id="native" class="cityId">
                            <select id="sendProvId" class="notBlank province select validate[required]" style="width: 150px;" data-value="${doctor.sendProvId}" name="doctor.sendProvId">

                            </select>
                            <input type="hidden" name="doctor.sendProvName" value="${doctor.sendProvName}"/>
                            <select id="sendCityId" name="doctor.sendCityId" style="width: 150px;" class="notBlank city select validate[required]"  data-value="${doctor.sendCityId}">
                            </select>
                            <input type="hidden" name="doctor.sendCityName" value="${doctor.sendCityName}"/>
                        </div>
                    </td>
                </tr>
                <!-- 			    <tr class="address" > -->
                <!-- 			    	<th>单位等级：</th> -->
                <!-- 		    	    <td colspan="4" style="padding-left:10px;"> -->
                <!-- 		    	    	<select id="orgLevel" class="select validate[required]" name="doctor.workOrgLevelId" onchange="changeOrgLevel(this);" style="width: 160px"> -->
                <!-- 							<option value="">请选择</option> -->
                <%-- 							<c:forEach items="${dictTypeEnumBaseLevelList}" var="tra"> --%>
                <%-- 								<option value="${tra.dictId}" <c:if test="${doctor.workOrgLevelId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option> --%>
                <%-- 							</c:forEach> --%>
                <!-- 						</select>&#12288;&nbsp;<span class="red">*</span>&#12288; -->
                <%-- 						<c:forEach items="${dictTypeEnumBaseLevelList}" var="tra"> --%>
                <%-- 							<label style="display: none;" id="${tra.dictId}" class="dict"> --%>
                <%-- 								${tra.dictDesc} --%>
                <!-- 							</label> -->
                <%-- 						</c:forEach> --%>
                <!-- 		    	    </td> -->
                <!-- 			    </tr> -->
                <tr class="address">
                    <th><span class="red">*</span>&#12288;医疗卫生机构：</th>
                    <td colspan="4" id="medicalHeaithOrgIdTd">
                        &nbsp;<select class="select address validate[required]" id="medicalHeaithOrgId" name="userResumeExt.medicalHeaithOrgId" onchange="changeOrgLevel(this.value);" style="width: 160px">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumMedicalHeaithOrgList}" var="tra">
                            <option value="${tra.dictId}" <c:if test="${userResumeExt.medicalHeaithOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                        </c:forEach>
                    </select>
                    </td>
                    <th class="hospital medical" style="display: none;"><span class="red">*</span>&#12288;医院性质：</th>
                    <td class="hospital medical" style="display: none;" colspan="2" id="TD">
                        &nbsp;<select class="select hospital medical validate[required]" name="userResumeExt.hospitalAttrId"style="width: 160px">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumHospitalAttrList}" var="tra">
                            <option value="${tra.dictId}" <c:if test="${userResumeExt.hospitalAttrId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <tr style="display: none;" class="medical">
                    <th class="medical" style="display: none;"><span class="red">*</span>&#12288;基层医疗&#12288;<br/>卫生机构：</th>
                    <td class="medical" style="display: none;">
                        &nbsp;<select class="select medical validate[required]" name="userResumeExt.basicHealthOrgId"style="width: 160px">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumBasicHealthOrgList}" var="tra">
                            <option value="${tra.dictId}" <c:if test="${userResumeExt.basicHealthOrgId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                        </c:forEach>
                    </select>
                    </td>
                    <th><span class="red">*</span>&#12288;基层医疗卫&#12288;<br/>生机构等级：</th>
                    <td colspan="2">
                        &nbsp;<select class="select medical validate[required]" name="userResumeExt.basicHealthOrgLevelId"style="width: 160px">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumBasicHealthOrgLevelList}" var="level">
                            <option value="${level.dictId}" <c:if test="${userResumeExt.basicHealthOrgLevelId eq level.dictId}">selected="selected"</c:if>>${level.dictName}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <tr style="display: none;" class="hospital">
                    <th><span class="red">*</span>&#12288;医院类别：</th>
                    <td>
                        &nbsp;<select class="select hospital  validate[required]" name="userResumeExt.hospitalCategoryId"style="width: 160px">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumHospitalCategoryList}" var="tra">
                            <option value="${tra.dictId}" <c:if test="${userResumeExt.hospitalCategoryId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                        </c:forEach>
                    </select>

                    </td>
                    <th><span class="red">*</span>&#12288;单位等级：</th>
                    <td colspan="2">
                        &nbsp;<select class="select hospital validate[required]" name="userResumeExt.baseAttributeId"style="width: 160px">
                        <option value="">请选择</option>
                        <c:forEach items="${dictTypeEnumBaseAttributeList}" var="tra">
                            <option value="${tra.dictId}" <c:if test="${userResumeExt.baseAttributeId eq tra.dictId}">selected="selected"</c:if>>${tra.dictName}</option>
                        </c:forEach>
                    </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="red">*</span>&#12288;是否是对口支援：</th>
                    <td style="padding-left: 10px;">
                        <input name="doctor.isPartner" type="radio" <c:if test="${doctor.isPartner eq GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" style="width: 20px" class="validate[required]" onclick="checkIsPartner(this.value)"/><label>是</label>
                        <input name="doctor.isPartner" type="radio" <c:if test="${doctor.isPartner eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" style="width: 20px" class="validate[required]"onclick="checkIsPartner(this.value)"/><label>否</label>
                    </td>
                    <th style="display: none" class="sourceProvinces"><span class="red">*</span>&#12288;生源省份：</th>
                    <td style="display: none" class="sourceProvinces" colspan="2">
                        &nbsp;<span id="provCityAreaId">
							<select id="orgProvId" name="doctor.sourceProvincesId" onchange="changeSourceProvincesName();" class="province select validate[required]" data-value="${doctor.sourceProvincesId}" data-first-title="选择省" style="width:160px;"></select>
						</span>
                    </td>
                </tr>
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
                <tr>
                    <th><span class="red">*</span>&#12288;本人通讯地址：</th>
                    <td colspan="4"><input name="user.userAddress" value="${user.userAddress}" class="validate[required] input" style="width: 98%;"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>&#12288;紧急联系人：</th>
                    <td><input name="doctor.emergencyName" value="${doctor.emergencyName}" class="validate[required] input"/></td>
                    <th><span class="red">*</span>&#12288;紧急联系人手机：</th>
                    <td colspan="2"><input name="doctor.emergencyPhone" value="${doctor.emergencyPhone}" class="validate[required,custom[mobile]] input"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>&#12288;与紧急联&#12288;<br/>系人关系：</th>
                    <td><input name="userResumeExt.emergencyRelation" value="${userResumeExt.emergencyRelation}" class="validate[required] input"/></td>
                    <th><span class="red">*</span>&#12288;紧急联系人地址：</th>
                    <td colspan="2"><input name="userResumeExt.emergencyAddress" value="${userResumeExt.emergencyAddress}" class="validate[required] input" style="width: 98%;"/></td>
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
                <tr>
                    <th><span class="red">*</span>&#12288;是否为应届生：</th>
                    <td>
                        <label>&#12288;<input name="doctor.isYearGraduate" type="radio"<c:if test="${doctor.isYearGraduate eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
                        <label><input name="doctor.isYearGraduate" type="radio" <c:if test="${doctor.isYearGraduate eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
                    </td>
                    <th><span class="red">*</span>&#12288;订单定向培训：</th>
                    <td>
                        <label>&#12288;<input name="userResumeExt.isGeneralOrderOrientationTrainee" type="radio"<c:if test="${userResumeExt.isGeneralOrderOrientationTrainee eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" class="validate[required]"/>&nbsp;是&#12288;</label>
                        <label><input name="userResumeExt.isGeneralOrderOrientationTrainee" type="radio" <c:if test="${userResumeExt.isGeneralOrderOrientationTrainee eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" class="validate[required]"/>&nbsp;否&nbsp;&nbsp;</label>
                    </td>
                </tr>
                <tr class="graduateTh">
                    <th><span class="red">*</span>&#12288;本科毕业院校：</th>
                    <td style="padding-left:10px;">
                        <input id="bkbyyx"  name="userResumeExt.graduatedName" value="${userResumeExt.graduatedName}" class="toggleView graduate input validate[required]" type="text"  autocomplete="off"style="margin-left: 0px"  onchange="changeYx(this);" onkeydown="changeYx(this);" onkeyup="changeYx(this);" oncontextmenu="return false" />
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                            <div class="boxHome bkbyyx" id="bkbyyxSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" >
                                    <p  class="item bkbyyx" flow="${dict.dictId}" value="${dict.dictName}" style="line-height: 40px; padding:10px 0;cursor: default;width: 100% " onclick="changeYx(this);">${dict.dictName}</p>
                                </c:forEach>
                            </div>
                        </div>
                    </td>
                    <th><span class="red yy">*</span>&#12288;本科毕业时间：</th>
                    <td ><input id="bkbysj"name="userResumeExt.graduationTime" value="${userResumeExt.graduationTime}" class="validate[required] input graduate datepicker"  style="width: 149px;" readonly="readonly"/></td>
                </tr>
                <tr class="graduateTh">
                    <th><span class="red yy">*</span>&#12288;本科毕业专业：</th>
                    <td><input id="bkbyzy"name="userResumeExt.specialized" value="${userResumeExt.specialized}" class="input  graduate validate[required] bk"/></td>
                    <th><span class="red yy">*</span>&#12288;学&#12288;&#12288;位：</th>
                    <td style="padding-left:10px;">
                        <select id="degree"name="userResumeExt.degreeId" class="select graduate validate[required] bk" style="width: 160px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${ dict.dictId eq userResumeExt.degreeId }">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr class="graduateTh">
                    <th><span class="red yy">*</span>&#12288;最高学历：</th>
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
                    <th><span class="red">*</span>&#12288;是否为硕士：</th>
                    <td>
                        <label id="isMaster">&#12288;<input id="isMaster1" name="userResumeExt.isMaster" type="radio"<c:if test="${userResumeExt.isMaster eq GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" class="validate[required]" onchange="checkBx(this.value,'master');"/>&nbsp;是&#12288;</label>
                        <label id="isNotMaster"><input id="isNotMaster1"  name="userResumeExt.isMaster" type="radio" <c:if test="${userResumeExt.isMaster eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" class="validate[required]"onchange="checkBx(this.value,'master');"/>&nbsp;否&nbsp;&nbsp;</label>
                    </td>
                    <th style="display: none" class="masterInfo"><span class="red">*</span>&#12288;状&#12288;&#12288;态：</th>
                    <td style="display: none" class="masterInfo">
                        <label id="statue1">&#12288;<input id="statueY" name="userResumeExt.masterStatue" type="radio"<c:if test="${userResumeExt.masterStatue eq '1' }">checked="checked"</c:if> value="1" class="validate[required]" onchange="checkIsStudy(this.value,'master')"/>&nbsp;在读&#12288;</label>
                        <label id="statue2"><input id="statueN" name="userResumeExt.masterStatue" type="radio" <c:if test="${userResumeExt.masterStatue eq '2' }">checked="checked"</c:if> value="2" class="validate[required]"onchange="checkIsStudy(this.value,'master')"/>&nbsp;已毕业&nbsp;&nbsp;</label>
                    </td>
                </tr>
                <tr style="display: none" class="masterInfo">
                    <th><span class="red masterRed">*</span>&#12288;硕士就读院校：</th>
                    <td style="padding-left:10px;">
                        <input id="byyx"  name="userResumeExt.masterGraSchoolName" value="${userResumeExt.masterGraSchoolName}" class="toggleView input  master validate[required]" type="text"  autocomplete="off"style="margin-left: 0px"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                            <div class="boxHome byyx" id="byyxSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" >
                                    <p  class="item byyx ${dict.dictId }" flow="${dict.dictId}" value="${dict.dictName}" style="line-height: 40px; padding:10px 0;cursor: default;width: 100%  ">${dict.dictName}</p>
                                </c:forEach>
                            </div>
                        </div>
                    </td>
                    <th><span class="red masterRed">*</span>&#12288;硕士入学时间：</th>
                    <td colspan="2"><input id="ssrxsj"name="userResumeExt.masterStartTime" value="${userResumeExt.masterStartTime}" class="input datepicker validate[required] "  style="width: 149px;" readonly="readonly"/></td>
                </tr>
                <tr style="display: none" class="masterInfo">
                    <th><span class="red masterRed">*</span>&#12288;所学专业：</th>
                    <td><input id="ssbyzy"name="userResumeExt.masterMajor" value="${userResumeExt.masterMajor}" class="input validate[required]"/></td>
                    <th class="masterIsGrad"><span class="red masterRed">*</span>&#12288;学&#12288;&#12288;位：</th>
                    <td class="masterIsGrad" style="padding-left:10px;">
                        <select name="userResumeExt.masterDegreeId" class="select masterIsGrad validate[required]" style="width: 160px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${ dict.dictId eq userResumeExt.masterDegreeId}">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr style="display: none" class="masterInfo masterIsGrad">
                    <th class="masterIsGrad"><span class="red masterRed">*</span>&#12288;硕士学位类型：</th>
                    <td class="masterIsGrad">
                        &nbsp;<select name="userResumeExt.masterDegreeTypeId" class="select validate[required]" style="width: 160px;" onchange="changeDegreeType(this,'master');">
                        <option value="">请选择</option>
                        <option value="2"${userResumeExt.masterDegreeTypeId eq 2?'selected':'' }>科学型</option>
                        <option value="1"${userResumeExt.masterDegreeTypeId eq 1?'selected':'' }>专业型</option>
                    </select>
                    </td>
                    <th class="masterIsGrad"><span class="red masterRed">*</span>&#12288;硕士毕业时间：</th>
                    <td class="masterIsGrad" colspan="2"><input id="ssbysj"name="userResumeExt.masterGraTime" value="${userResumeExt.masterGraTime}" class="input datepicker validate[required] "  style="width: 149px;" readonly="readonly"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>&#12288;是否为博士：</th>
                    <td>
                        <label>&#12288;<input name="userResumeExt.isDoctor" type="radio"<c:if test="${userResumeExt.isDoctor eq 	GlobalConstant.FLAG_Y }">checked="checked"</c:if> value="${GlobalConstant.FLAG_Y }" class="validate[required]" onchange="checkBx(this.value,'doctor')"/>&nbsp;是&#12288;</label>
                        <label><input name="userResumeExt.isDoctor" type="radio" <c:if test="${userResumeExt.isDoctor eq GlobalConstant.FLAG_N }">checked="checked"</c:if> value="${GlobalConstant.FLAG_N }" class="validate[required]" onchange="checkBx(this.value,'doctor')"/>&nbsp;否&nbsp;&nbsp;</label>
                    </td>
                    <th style="display: none" class="doctorInfo"><span class="red">*</span>&#12288;状&#12288;&#12288;态：</th>
                    <td style="display: none" class="doctorInfo">
                        <label>&#12288;<input name="userResumeExt.doctorStatue" type="radio"<c:if test="${userResumeExt.doctorStatue eq '1' }">checked="checked"</c:if> value="1" class="validate[required]" onchange="checkIsStudy(this.value,'doctor')"/>&nbsp;在读&#12288;</label>
                        <label><input name="userResumeExt.doctorStatue" type="radio" <c:if test="${userResumeExt.doctorStatue eq '2' }">checked="checked"</c:if> value="2" class="validate[required]"onchange="checkIsStudy(this.value,'doctor')"/>&nbsp;已毕业&nbsp;&nbsp;</label>
                    </td>
                </tr>
                <tr style="display: none" class="doctorInfo">
                    <th><span class="red">*</span>&#12288;博士就读院校：</th>
                    <td style="padding-left:10px;">
                        <input id="bsbyyx"  name="userResumeExt.doctorGraSchoolName" value="${userResumeExt.doctorGraSchoolName}" class="toggleView input validate[required]" type="text"  autocomplete="off"style="margin-left: 0px"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                            <div class="boxHome bsbyyx" id="bsbyyxSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                <c:forEach items="${dictTypeEnumGraduateSchoolList}" var="dict" >
                                    <p  class="item bsbyyx ${dict.dictId }"  flow="${dict.dictId}" value="${dict.dictName}" style="line-height: 40px; padding:10px 0;cursor: default;width: 100%  ">${dict.dictName}</p>
                                </c:forEach>
                            </div>
                        </div>
                    </td>
                    <th><span class="red">*</span>&#12288;博士入学时间：</th>
                    <td colspan="2"><input id="bsrxsj"name="userResumeExt.doctorStartTime" value="${userResumeExt.doctorStartTime}" class="input datepicker validate[required] "  style="width: 149px;" readonly="readonly"/></td>
                </tr>
                <tr style="display: none" class="doctorInfo">
                    <th><span class="red">*</span>&#12288;所学专业：</th>
                    <td><input id="bsbyzy"name="userResumeExt.doctorMajor" value="${userResumeExt.doctorMajor}" class="input validate[required]"/></td>
                    <th class="doctorIsGrad"><span class="red">*</span>&#12288;学&#12288;&#12288;位：</th>
                    <td class="doctorIsGrad" style="padding-left:10px;">
                        <select name="userResumeExt.doctorDegreeId" class="select validate[required]" style="width: 160px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserDegreeList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${ dict.dictId eq userResumeExt.doctorDegreeId }">selected</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr style="display: none" class="doctorInfo doctorIsGrad">
                    <th class="doctorIsGrad"><span class="red doctorRed">*</span>&#12288;博士学位类型：</th>
                    <td class="doctorIsGrad">
                        &nbsp;<select name="userResumeExt.doctorDegreeTypeId" class="select validate[required]" style="width: 160px;" onchange="changeDegreeType(this,'doctor');">
                        <option value="">请选择</option>
                        <option value="2"${userResumeExt.doctorDegreeTypeId eq 2?'selected':'' }>科学型</option>
                        <option value="1"${userResumeExt.doctorDegreeTypeId eq 1?'selected':'' }>专业型</option>
                    </select>
                    </td>
                    <th class="doctorIsGrad"><span class="red doctorRed">*</span>&#12288;博士毕业时间：</th>
                    <td class="doctorIsGrad" colspan="2"><input id="bsbysj"name="userResumeExt.doctorGraTime" value="${userResumeExt.doctorGraTime}" class="input doctorIsGrad datepicker validate[required] "  style="width: 149px;" readonly="readonly"/></td>
                </tr>
                <tr>
                    <th><span class="red">*</span>&#12288;最高毕业&#12288;<br/>证书编号：</th>
                    <td ><input name="doctor.certificateNo" value="${doctor.certificateNo}" class="validate[required] input"/></td>
                    <th><span class="red">*</span>&#12288;最高毕业&#12288;<br/>证书上传：</th>
                    <td>
                        <input type="hidden" id="certificateUriValue" name="userResumeExt.certificateUri" value="${userResumeExt.certificateUri}"/>
						<span id="certificateUriSpan" style="display:${!empty userResumeExt.certificateUri?'':'none'} ;margin-left: 5px">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.certificateUri}" target="_blank">查看图片</a>]&nbsp;
						</span>
                        <a style="margin-left: 5px" id="certificateUri" href="javascript:uploadFile('certificateUri','毕业证书');" class="btn">${empty userResumeExt.certificateUri?'':'重新'}上传</a>&nbsp;
                        <a id="certificateUriDel" href="javascript:delFile('certificateUri');" class="btn" style="${empty userResumeExt.certificateUri?'display:none':''}">删除</a>&nbsp;
                    </td>
                </tr>
                <tr>
                    <th>最高学位&#12288;<br/>证书编号：</th>
                    <td ><input name="doctor.degreeNo" value="${doctor.degreeNo}" class="input"/>&#12288;</td>
                    <th>&#12288;最高学位&#12288;<br/>证书上传：</th>
                    <td >
                        <input type="hidden" id="degreeUriValue" name="userResumeExt.degreeUri" value="${userResumeExt.degreeUri}"/>
						<span id="degreeUriSpan" style="display:${!empty userResumeExt.degreeUri?'':'none'};margin-left: 5px">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.degreeUri}" target="_blank">查看图片</a>]&nbsp;
						</span>
                        <a style="margin-left: 5px" id="degreeUri" href="javascript:uploadFile('degreeUri','学位证书');" class="btn">${empty userResumeExt.degreeUri?'':'重新'}上传</a>&nbsp;
                        <a id="degreeUriDel" href="javascript:delFile('degreeUri');" class="btn" style="${empty userResumeExt.degreeUri?'display:none':''}">删除</a>&nbsp;
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="div_table">
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
                        <select id="technologyQualificationId" name="userResumeExt.technologyQualificationId" class="select" style="width: 160px;">
                            <option value="">请选择</option>
                            <option value="171" ${userResumeExt.technologyQualificationId eq '171'?'selected':''}>住院医师</option>
                            <option value="172" ${userResumeExt.technologyQualificationId eq '172'?'selected':''}>主治医师</option>
                            <option value="173" ${userResumeExt.technologyQualificationId eq '173'?'selected':''}>住院中医师</option>
                            <option value="174" ${userResumeExt.technologyQualificationId eq '174'?'selected':''}>主治中医师</option>
                            <option value="196" ${userResumeExt.technologyQualificationId eq '196'?'selected':''}>无</option>
                        </select>
                    </td>
                    <th>取得日期：</th>
                    <td colspan="2">
                        <input name="userResumeExt.getTechnologyQualificationDate" value="${userResumeExt.getTechnologyQualificationDate}" class="input datepicker" style="width: 149px;" readonly="readonly"/>
                    </td>
                </tr>
                <tr>
                    <th>执业资格材料：</th>
                    <td style="padding-left:10px;">
                        <select id="qualificationMaterialId" name="userResumeExt.qualificationMaterialId" class="select" style="width: 160px;">
                            <option value="">请选择</option>
                            <option value="176" ${userResumeExt.qualificationMaterialId eq '176'?'selected':''}>医师执业证书</option>
                            <option value="177" ${userResumeExt.qualificationMaterialId eq '177'?'selected':''}>医师资格证书</option>
                            <option value="178" ${userResumeExt.qualificationMaterialId eq '178'?'selected':''}>已通过国家执业医师考试的成绩单</option>
                            <option value="200" ${userResumeExt.qualificationMaterialId eq '200'?'selected':''}>助理执业医师证书（定向大专）</option>
                            <option value="201" ${userResumeExt.qualificationMaterialId eq '201'?'selected':''}>已通过国家助理执业医师考试的成绩单（定向大专）</option>
                            <option value="202" ${userResumeExt.qualificationMaterialId eq '202'?'selected':''}>无</option>
                        </select>
                    </td>
                    <th>资格材料编码：</th>
                    <td colspan="2"><input name="userResumeExt.qualificationMaterialCode" value="${userResumeExt.qualificationMaterialCode}" class="input"/></td>
                </tr>
                <tr>
                    <th><font color="red">*</font>是否取得&#12288;<br/>执业资格证：</th>
                    <td>
                        <label><input type="radio" name="doctor.doctorLicenseFlag"
                                      <c:if test="${doctor.doctorLicenseFlag eq 'Y'}">checked="checked"</c:if>
                                      value="Y" class="validate[required]" onchange="showId(this.value);"/>是</label>&#12288;
                        <label><input type="radio" name="doctor.doctorLicenseFlag"
                                      <c:if test="${doctor.doctorLicenseFlag eq 'N'}">checked="checked"</c:if>
                                      value="N" class="validate[required]" onchange="showId(this.value);"/>否</label>
                    </td>
                    <th class="qualifiedNoTh" style="display: none">
                        <font color="red">*</font>执业医师资格证号：
                    </th>
                    <td class="qualifiedNoTd" style="display: none">
                        <input type="text" name="doctor.doctorLicenseNo" value="${doctor.doctorLicenseNo}"
                               class="validate[required] input">
                    </td>
                </tr>
                <tr>
                    <th>执业类型：</th>
                    <td style="padding-left:10px;">

                        <select id="practicingCategoryId" name="userResumeExt.practicingCategoryId" class="select" style="width: 160px;" onchange="changeCategoryId(this.value)">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumPracticeTypeList}" var="dictTypeEnumPracticeType">
                                <option value="${dictTypeEnumPracticeType.dictId}"	 <c:if test='${userResumeExt.practicingCategoryId==dictTypeEnumPracticeType.dictId}'>selected</c:if>>
                                        ${dictTypeEnumPracticeType.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <th>执业范围：</th>
                    <td style="padding-left:10px;">
                        <select id="practicingScopeId" name="userResumeExt.practicingScopeId" class="select" style="width: 160px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
                                <c:set var="dictKey" value="dictTypeEnumPracticeType.${dict.dictId}List"/>
                                <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                    <option class="${scope.dictTypeId}" value="${scope.dictId}" <c:if test='${userResumeExt.practicingScopeId==scope.dictId and dict.dictId == userResumeExt.practicingCategoryId}'>selected</c:if>>
                                            ${scope.dictName}</option>
                                </c:forEach>
                            </c:forEach>
                        </select>
                    </td>
                    <script>
                        function changeCategoryId(dictId){
                            if(dictId=="") {
                                $('#practicingScopeId').val("");
                            }
                            $('#practicingScopeId option').hide();
                            $('#practicingScopeId option[value=""]').show();
                            //$('#practicingScopeId').val("${userResumeExt.practicingScopeId}");
                            $('#practicingScopeId'+' option.${dictTypeEnumPracticeType.id}\\.'+dictId).show();
                        }
                        $(function(){
                            changeCategoryId('${userResumeExt.practicingCategoryId}');
                        });
                    </script>
                </tr>
                <tr>
                    <th>资格证书上传：</th>
                    <td colspan="3">
                        <input type="hidden" id="qualificationMaterialUriValue" name="userResumeExt.qualificationMaterialUri" value="${userResumeExt.qualificationMaterialUri}"/>
						<span id="qualificationMaterialUriSpan" style="margin-left: 5px;display:${!empty userResumeExt.qualificationMaterialUri?'':'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${userResumeExt.qualificationMaterialUri}" target="_blank">查看图片</a>]&nbsp;
						</span>
                        <a style="margin-left: 5px" id="qualificationMaterialUri" href="javascript:uploadFile('qualificationMaterialUri','学位证书');" class="btn">${empty userResumeExt.qualificationMaterialUri?'':'重新'}上传</a>&nbsp;
                        <a id="qualificationMaterialUriDel" href="javascript:delFile('qualificationMaterialUri');" class="btn" style="${empty userResumeExt.qualificationMaterialUri?'display:none':''}">删除</a>&nbsp;
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
    <div class="btn_info">
        <input type="button" style="width:100px;" class="btn_brown" onclick="saveDoctorInfo();" value="保&#12288;存"/><br/><br/>
        <font color="red">保存后请填写/完善培训记录并提交，否则基地无法审核.</font>
        <c:if test="${param.openType eq 'open'}">
            <input type="button" style="width:100px;" class="btn_brown" onclick="jboxClose();" value="关&#12288;闭"/>
        </c:if>
    </div>
</div>
