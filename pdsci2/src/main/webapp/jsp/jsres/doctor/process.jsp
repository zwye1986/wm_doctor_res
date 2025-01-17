<script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	//同步
	function jboxGetAsync(geturl,getdata,funcOk,funcErr,showResp){
		$.ajax({
			type : "get",
			url : geturl,
			data : getdata,
			cache : false,
			async : false,
			beforeSend : function(){
				jboxStartLoading();
			},
			success : function(resp) {
				jboxEndLoading();
				if(showResp==false){

				}else{
					jboxTip(resp);
				}
				if(funcOk!=null){
					funcOk(resp);
				}
			},
			error : function() {
				jboxEndLoading();
				jboxTip("操作失败,请刷新页面后重试");
				if(funcErr!=null){
					funcErr();
				}
			},
			complete : function(){
				jboxEndLoading();
			}
		});
	}
$(document).ready(function(){
	$(".showInfo").on("mouseenter mouseleave",
			function(){
				$(".theInfo",this).toggle(100);
			}
	);
	$(".show").on("mouseenter mouseleave",
			function(){
				$(".info",this).toggle(100);
			}
	);
	$(".showCkk").on("mouseenter mouseleave",
			function(){
				$(".showCkkInfo",this).toggle(100);
			}
	);
    $(".showTip").on("mouseenter mouseleave",
            function(){
                $(".showTipInfo",this).toggle(100);
            }
    );

	<%--jboxEndLoading();--%>
	<%--<c:set value="jswjw_${doctor.orgFlow}_P001" var="orgFlow"/>--%>
	<%--<c:set value="jswjw_${doctor.orgFlow}_P008" var="orgSend"/>--%>
	<%--<c:set value="jswjw_sendSchool_${doctor.workOrgId}_P007" var="sendKey"/>--%>
	 <%--<c:forEach items="${groupList}" var="group">--%>
	  	<%--<c:forEach items="${rotationDeptMap[group.groupFlow]}" var="dept">--%>
						<%--<c:set var="f" value="N"/>--%>
	                	<%--<c:set var="JD_GC" value="${sysCfgMap[orgFlow]}"/>--%>
						<%--<c:set var="Send_GC" value="${sysCfgMap[sendKey]}"/>--%>
						<%--<c:set var="JDZS_GC" value="${sysCfgMap[orgSend]}"/>--%>
						<%--<c:choose>--%>
							<%--<c:when test="${'Graduate' eq doctor.doctorTypeId}">--%>
								<%--<c:if test="${(not empty doctor.workOrgId) and !(Send_GC eq 'N')}">--%>
									<%--<c:set var="f" value="Y"/>--%>
								<%--</c:if>--%>
								<%--<c:if test="${(f eq 'N') and (JD_GC eq 'Y') and (JDZS_GC eq 'Y')}">--%>
									<%--<c:set var="f" value="Y"/>--%>
								<%--</c:if>--%>
							<%--</c:when>--%>
							<%--<c:otherwise>--%>
								<%--<c:if test="${JD_GC eq GlobalConstant.RECORD_STATUS_Y}">--%>
									<%--<c:set var="f" value="Y"/>--%>
								<%--</c:if>--%>
							<%--</c:otherwise>--%>
						<%--</c:choose>--%>
			<%--<c:choose>--%>
				<%--<c:when test="${(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope--%>
					<%--or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope--%>
					<%--or GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope)--%>
					<%--and f != GlobalConstant.RECORD_STATUS_Y}">--%>
					<%--showCanvas('${dept.recordFlow}','0','0');--%>
				<%--</c:when>--%>
				<%--<c:otherwise>--%>
					<%--showCanvas('${dept.recordFlow}','${processPerMap[dept.recordFlow]}','${processingPerMap[dept.recordFlow]}');--%>
				<%--</c:otherwise>--%>
			<%--</c:choose>--%>
		<%--</c:forEach>--%>
	<%--</c:forEach>  --%>
});

function uploadAfterEvaluation(processFlow){
	var url = "<s:url value='/jsres/doctor/uploadAfterEvaluation'/>?processFlow="+processFlow;
	jboxOpen(url, "出科考核表",500,200);
}
function showCanvas (id,per,processingPer){
	per = per-0;
	processingPer = processingPer-0;
	var surplus = per-processingPer;
	var placeHolderStyle = {
		    normal : {
		        color: 'rgba(200,200,200,0.8)',
		        label: {show:false},
		        labelLine: {show:false}
		    },
// 		    emphasis : {
// 		        color: 'rgba(50,50,50,0)'
// 		    }
		};
		var option = {
// 		    title: {
// 		        text: '你幸福吗？',
// 		        x: 'center',
// 		        y: 'center',
// 		        textStyle : {
// 		            color : 'rgba(30,144,255,0.8)',
// 		            fontFamily : '微软雅黑',
// 		            fontSize : 12,
// 		        }
// 		    },
// 			legend: {
// 			        orient : 'vertical',
// 			        x : 10,
// 			        y : 10,
// 			        itemGap:10,
// 			        data:[per+'%',processingPer+'%',surplus+'%']
// 			    },
// 		    tooltip : {
// 		        show: true,
// 		        formatter: "{d}%"
// 		    },
		    series : [
		        {
		            name:per+'%',
		            type:'pie',
		            clockWise:false,
		            radius : [30, 37],
		            itemStyle : {
		    		    normal: {
		    		        label: {show:false},
		    		        labelLine: {show:false},
		    		        color:'rgba(255,120,50,1)'
		    		    }
		    		},
		            data:[
		                {
		                    value:per,
		                    name:per+'%'
		                },
		                {
		                    value:100-per,
		                    name:'invisible',
		                    itemStyle : placeHolderStyle
		                }
		            ]
		        },
		        {
		            name:processingPer+'%',
		            type:'pie',
		            clockWise:false,
		            radius : [20, 30],
		            itemStyle :  {
		    		    normal: {
		    		        label: {show:false},
		    		        labelLine: {show:false},
		    		        color:'rgba(100,200,255,1)'
		    		    }
		    		},
		            data:[
		                {
		                    value:processingPer, 
		                    name:processingPer+'%'
		                },
		                {
		                    value:100-processingPer,
		                    name:'invisible',
		                    itemStyle : placeHolderStyle
		                }
		            ]
		        },
		        {
		            name:surplus+'%',
		            type:'pie',
		            clockWise:false,
		            radius : [5, 20],
		            itemStyle :  {
		    		    normal: {
		    		        label: {show:false},
		    		        labelLine: {show:false},
		    		        color:'rgba(255,120,255,1)'
		    		    }
		    		},
		            data:[
		                {
		                    value:surplus, 
		                    name:surplus+'%'
		                },
		                {
		                    value:100-surplus,
		                    name:'invisible',
		                    itemStyle : placeHolderStyle
		                }
		            ]
		        }
		    ]
		};
    // 为echarts对象加载数据 
    var myChart = echarts.init($("#chart_"+id)[0]);
    myChart.setOption(option); 
}
function inDept(recordFlow){
	var url = "<s:url value='/jsres/doctor/inDept'/>?recordFlow="+recordFlow;
	jboxOpen(url, "入科",500,300);
}
function upload(recordFlow,userFlow,hideApprove){
	if(hideApprove==null){
		hideApprove=null;
	}
	if(userFlow==null){
		userFlow="";
	}
	var url = "<s:url value='/res/rec/upload'/>?recordFlow="+recordFlow+"&hideApprove="+hideApprove+"&userFlow="+userFlow;
	jboxOpen(url, "查看出科考核表",700,550);
}
function xiaZai(recordFlow) {
	<c:set var="key" value="jsres_doctor_app_menu_${doctor.doctorFlow}"/>
	<c:if test="${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y}">
		if ("${roleFlag}" == "doctor") {
			if (!checkedHadEvaluate("", recordFlow)) {
				return false;
			}
		}
	</c:if>
	var url = "<s:url value='/jsres/base/download4PDF'/>?recordFlow=" + recordFlow;
	window.location.href = url;
}
function daochu(doctorFlow){
	var url = '<s:url value="/jsres/doctor/daochu"/>?doctorFlow='+doctorFlow;
	jboxExpLoading(null,url);
}
function sincerity(){
	jboxOpenContent($("#toOutConfirm").html(),"诚信声明",700,400);
}
function sFile(){
	$("#file").click();
}
function checkFile(obj){
	var id = obj.id;
	$.ajaxFileUpload({
		url:"<s:url value='/res/rec/resRecImg'/>",
		secureuri:false,
		fileElementId:id,
		dataType: 'json',
		success: function (data){
			data=eval("("+data+")");
			if(data){
				var status=data.status;
				if(status=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
					$(".imgc").attr("src","${sysCfgMap['upload_base_url']}"+"/"+data.url);
					jboxTip("上传成功！");
					
				}else{
					jboxTip(data.error);
				};
			};
		},
		error: function (data, status, e){
			jboxTip('${GlobalConstant.UPLOAD_FAIL}');
		},
		complete:function(){
		}
	});
}
function relationDownload(resultFlow){
	var url = "<s:url value='/jsres/base/relationDownload'/>?resultFlow="+resultFlow;
	window.location.href = url;	
}
function verification(resultFlow,processFlow){
	if("${roleFlag}" == "doctor"){
		if(!checkedHadEvaluate(processFlow,"")){
			return false;
		}
	}
	var url = "<s:url value='/jsres/base/verification'/>?resultFlow="+resultFlow;
	jboxPost(url,null,function(resp){
		if(resp=='${GlobalConstant.OPRE_SUCCESSED_FLAG}'){
			relationDownload(resultFlow);
		}else{
			if(resp=="-1")
			{
				jboxTip("科主任还没有审核出科考核表！");
			}else if(resp=="-2")
			{
				jboxTip("出科考核表审核不通过，无法下载！");
			}else {
				jboxTip("带教老师还没有填写出科考核表！");
			}
		}
	},null,false);
}
$(function(){
	setIndexHeight();
});
//下载出科考核表前，判断是否进行“双向评价”
function checkedHadEvaluate(processFlow, deptRecordFlow) {
	var respVari = "";
	if(!processFlow){
		processFlow = "";
	}
	if(!deptRecordFlow){
		deptRecordFlow = "";
	}
	var url = "<s:url value='/jsres/doctor/checkedHadEvaluate'/>?processFlow=" + processFlow + "&deptRecordFlow=" + deptRecordFlow;
	jboxGetAsync(url, null, function (resp) {
		if (resp == '${GlobalConstant.FLAG_Y}') {
			respVari = true;
		} else {
			if (deptRecordFlow) {
				if (resp=='N'){
					jboxTip("请先完成相关科室的双向评价！");
				}else {
					jboxTip(resp);
				}
			} else {
				if (resp=='N'){
					jboxTip("请先完成本科室的双向评价！");
				}else {
					jboxTip(resp);
				}
			}
			respVari = false;
		}
	}, null, false);
	return respVari;
}

function setIndexHeight(){
	//$("#indexBody").css("min-height","900");
}
function catalogue(recordFlow,doctorFlow){
	var url="<s:url value='/jsres/manage/catalogue'/>?doctorFlow="+doctorFlow+"&schRotationDeptFlow="+recordFlow;
	jboxOpen(url,"登记详情", 900,550,true);
}

function GetUrlParms(query)
{
	var result="";
	//获取查询串
	var pairs=query.split("&");//在逗号处断开
	for(var   i=0;i<pairs.length;i++)
	{
		var pos=pairs[i].indexOf('=');//查找name=value
		if(pos==-1)   continue;//如果没有找到就跳过
		var argname=pairs[i].substring(0,pos);//提取name
		var value=pairs[i].substring(pos+1);//提取value
		if(value!=""&&value!=undefined)
		{
			if(value.indexOf(",")>=0)
			{

			}else{
				value= encodeURIComponent(encodeURIComponent(value));
			}
		}
		result+="&"+argname+"="+value;
	}
	return result;

}
function docback()
{
	var sarch="${search}";
	var roleFlag="${sessionScope.userListScope}";
	var url="<s:url value='/jsres/doctorRecruit/provinceDoctorListNew'/>?roleFlag="+roleFlag+"&currentPage=${param.currentPage}"+"&yearStr=${param.yearStr}"+"&baseFlag=${param.baseFlag}";
	<c:if test="${(not empty search) and !(search eq '')}">
		sarch=sarch.replaceAll("formAnd","&");
		sarch=sarch.replaceAll("formeq","=");
		sarch = GetUrlParms(sarch);
		url=url+sarch+"&isBack=Y";
	</c:if>
	jboxLoad("content",url,true);
}
function universityback()
{
	var roleFlag="${GlobalConstant.USER_LIST_UNIVERSITY}";
	var url="<s:url value='/jsres/doctorRecruit/universityDoctorList'/>?roleFlag="+roleFlag;
	jboxLoad("content",url,true);
}

function afterTest(processFlow,f){
	jboxTip("出科考试请关注微信公众号：“江苏住培”后，在微信公众号中进行考试！");
	return;
	if(f){
		jboxConfirm("上次考试分数已计算,确定参加考试？",function(){
			toTest(processFlow);
		},null);
	}else{
		toTest(processFlow);
	}
}

function toTest(processFlow){
	var url = '<s:url value="/res/test/toTest"/>?processFlow='+processFlow;
	window.open(url);
}

function doctorback(){
	var sarch="${search}";
	var roleFlag="${GlobalConstant.RES_ROLE_SCOPE_SCHOOL}";
	var url="<s:url value='/jsres/doctorRecruit/provinceDoctorList'/>?roleFlag="+roleFlag;
	<c:if test="${(not empty search) and !(search eq '')}">
	sarch=sarch.replaceAll("formAnd","&");
	sarch=sarch.replaceAll("formeq","=");
	sarch=sarch.replaceAll("formCo",",");
	sarch = GetUrlParms(sarch);
	url=url+sarch;
	</c:if>
	jboxLoad("content",url,true);
}
    /*查看错题*/
    function showCkkPapers(processFlow){
        jboxOpen("<s:url value='/res/exam/paper/list2'/>?processFlow="+processFlow, "${process.schDeptName}出科记录",800,600);
    }
	function openImport(recordFlow,processFlow) {
		var url = "<s:url value='/jsres/doctor/openImport'/>?doctorFlow=${doctor.doctorFlow}&recordFlow="+recordFlow+"&processFlow="+processFlow;
		jboxOpen(url, "填写数据导入",500,300);
	}

    function getProcess(rotationFlow, doctorFlow, hideApprove, roleFlag){
        jboxLoad("content","<s:url value='/jsres/doctor/trainRegister'/>?rotationFlow="+rotationFlow + "&doctorFlow=" + doctorFlow + "&hideApprove=" + hideApprove + "&roleFlag=" + roleFlag,true);
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
</script>
<style>
    .showTip{
        position: relative;
        color: red;
    }
    .showTipInfo {
        display: none;
        width: 250px;
        height: 80px;
        position: relative;
        border-style: solid;
        line-height: 25px;
        text-align: left;
        border-color: lightgrey;
        border-width: 1px;
        font-size: 0.8em;
        color: green;
        padding: 10px;
        background: white;
    }
</style>

<div class="main_hd">
	<h2>培训记录</h2>
	<div class="title_tab" id="toptab">
		<ul>
			<c:forEach items="${recruitList}" var="recruit">
				<%--<input type="hidden" class="${recruit.catSpeId}" value="${recruit.speId}"/>--%>
				<li <c:if test="${rotationFlow eq recruit.rotationFlow}">class="tab_select"</c:if> id="li_${recruit.rotationFlow}" onclick="getProcess('${recruit.rotationFlow}', '${recruit.doctorFlow}', '${hideApprove}', '${roleFlag}');"><a>${recruit.speName}（${recruit.sessionNumber}级）</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
<div class="main_hd">
    <h2>
<c:if test="${not empty hideApprove}"><B>学员：</B>${sysUser.userName}&#12288;<B>培养年限：</B>
<c:if test="${doctor.trainingYears eq 'OneYear'}">1年</c:if>
<c:if test="${doctor.trainingYears eq 'TwoYear'}">2年</c:if>
<c:if test="${doctor.trainingYears eq 'ThreeYear'}">3年</c:if>
	&#12288;</c:if>
		<B>培训基地：</B><c:if test="${!empty doctorRecruit.jointOrgFlow}">${doctorRecruit.jointOrgName}</c:if> <c:if test="${empty doctorRecruit.jointOrgFlow}">${doctorRecruit.orgName}</c:if>&#12288;<B>培训专业：</B>${doctorRecruit.speName }&#12288;
   <B> 轮转方案：</B>${rotation.rotationName } &#12288;<c:if test="${empty rotation }"><font color='red'>首次使用请登录APP填写培训数据(首页扫描二维码)!</font></c:if>
   <c:if test="${empty hideApprove}">
	   	<%--<input type="button" class="btn_green" value="诚信声明" name="sincerity" onclick="sincerity();"/>--%>
	   <c:if test="${showManual eq 'Y'}">
   			<a href="javascript:void(0);" onclick="daochu('${doctor.doctorFlow}');" id="printBtn" class="btn_green" >培训手册</a>
	   </c:if>
 	</c:if>
    <c:if test="${appMenu eq 'Y' and ( sessionScope.userListScope==GlobalConstant.USER_LIST_PERSONAL or sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL )}">
        <span  class="showTip btn" ><b style="font-size: 1.5em">？</b>
            <div style="width: 0px;height: 0px;overflow: visible;left: -105px;top: 35px;    position: absolute;">
                <div class="showTipInfo">
                    1.出科考成绩只展示最高分 <br>
                    2.若出科考核表被带教审核后成绩将不再更新 <br>
                    3.查看错题能查看全部考试错题 <br>
                </div>
            </div>
        </span>
    </c:if>
 	<c:if test="${not empty hideApprove}">
	 	<c:if test="${sessionScope.userListScope==GlobalConstant.RES_ROLE_SCOPE_SCHOOL}">
	 		<a href="javascript:doctorback();" class="btn_green">返回</a>
	 	</c:if>
	 	<c:if test="${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL and sessionScope.userListScope!=GlobalConstant.USER_LIST_UNIVERSITY}">
	 		<a href="javascript:docback();" class="btn_green">返回</a>
	 	</c:if>
		<c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_UNIVERSITY}">
			<a href="javascript:universityback();" class="btn_green">返回</a>
		</c:if>
 	</c:if> 			
   </h2>
</div>
<div id="toOutConfirm" style="display: none;">
	<center>
	<div class="imageOper" style="border: 1px solid #e3e3e3; margin-top: 5px; width: 600px;height: 350px;text-align: center;">
		<img class="imgc" src="${sysCfgMap['upload_base_url']}/${resRec.imageUrl}" onclick="sFile();" width="100%" height="100%"  title="点击重新上传"/>
		<input id="file" style="display: none;" type="file" name="checkFile" onchange="checkFile(this);"  class="validate[required]"/>
	</div>
	</center>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent" >
    	<%--<div style="margin-left: 30px;margin-bottom: 10px;">--%>
    	<%--Tip:<font style="color:rgba(255,120,50,1);">橙色</font>表示总登记比例,<font style="color:rgba(100,200,255,1);">蓝色</font>表示轮转中登记比例,<font style="color:rgba(255,120,255,1);">紫色</font>表示补登比例．--%>
    	<%--</div>--%>
    	<div class="search_table" style="overflow:auto;margin-left: 30px;margin-right: 30px;padding: 0px;min-height: 900px;" id="indexBody">
        <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: 100%;padding:0px;margin: 0px;">
        	 <colgroup>
	        		<col width="20%"/>
	        		<col width="15%"/>
	        		<col width="15%"/>
	        		<col width="10px;" />
	        		<col width="25%"/>
	        	</colgroup>
            <tr>
                <th>标准科室</th>
                <th>规定轮转时间(月)</th>
                <th>实际轮转时间(月)</th>
                <th>培训进度</th>
                <th>出科考核表</th>
            </tr>
			<c:set var="key" value="jsres_doctor_app_menu_${doctor.doctorFlow}"/>
			<c:set var="f" value="${pdfn:jsresPowerCfgMap(key)}"/>
			<c:set value="0" var="allSchMonth"></c:set>
			<c:set value="0" var="allRealMonth"></c:set>
			<%--<c:set var="f" value="N"/>--%>
			<%--<c:set value="jswjw_${doctor.orgFlow}_P001" var="orgFlow"/>--%>
			<%--<c:set value="jswjw_${doctor.orgFlow}_P008" var="orgSend"/>--%>
			<%--<c:set value="jswjw_sendSchool_${doctor.workOrgId}_P007" var="sendKey"/>--%>
			<%--<c:set var="JD_GC" value="${sysCfgMap[orgFlow]}"/>--%>
			<%--<c:set var="Send_GC" value="${sysCfgMap[sendKey]}"/>--%>
			<%--<c:set var="JDZS_GC" value="${sysCfgMap[orgSend]}"/>--%>
			<%--<c:choose>--%>
				<%--<c:when test="${'Graduate' eq doctor.doctorTypeId}">--%>
					<%--<c:if test="${(not empty doctor.workOrgId) and !(Send_GC eq 'N')}">--%>
						<%--<c:set var="f" value="Y"/>--%>
					<%--</c:if>--%>
					<%--<c:if test="${(f eq 'N') and (JD_GC eq 'Y') and (JDZS_GC eq 'Y')}">--%>
						<%--<c:set var="f" value="Y"/>--%>
					<%--</c:if>--%>
				<%--</c:when>--%>
				<%--<c:otherwise>--%>
					<%--<c:if test="${JD_GC eq GlobalConstant.RECORD_STATUS_Y}">--%>
						<%--<c:set var="f" value="Y"/>--%>
					<%--</c:if>--%>
				<%--</c:otherwise>--%>
			<%--</c:choose>--%>
            <c:forEach items="${groupList}" var="group">
            	<c:forEach items="${rotationDeptMap[group.groupFlow]}" var="dept">
					<c:set value="${allSchMonth+0+dept.schMonth}" var="allSchMonth"></c:set>
            		<c:set var="resultKey" value="${group.groupFlow}${dept.standardDeptId}"/>
					<c:set value="${allRealMonth+0+(empty realMonthMap[resultKey] ? 0 : realMonthMap[resultKey])}"
						   var="allRealMonth"></c:set>

            	 <tr>
	                <td style="padding-left: 25px;text-align: left;" class="showInfo">
	                <c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
						[${group.schStageName}${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}]
					</c:if>
	               		${dept.standardDeptName}
						<c:if test="${! empty resultMap[resultKey]}">
							<div style="width: 0px;height: 0px;overflow: visible;">
								<div style="height:auto;display: none;position: relative;width: 420px;" class="theInfo">
									<table class="grid" style="background: white;width: 400px;">
			              			  <tr>
										  <th style="width: 100px; ">科室名称</th>
			                				<th style='padding-left:5px;width: 100px;'>开始日期</th>
			                				<th style='padding-left:5px;width: 100px;'>结束日期</th>
										  	<c:if test="${daoRu eq 'Y' and  empty hideApprove}">
												<th style='padding-left:5px;width: 150px;'>操作</th>
											</c:if>
			                			</tr>
			                			<c:forEach items="${resultMap[resultKey]}" var="result">
				                			<tr>
				                				<td>${result.schDeptName}</td>
				                				<td style='padding-left:5px;'>${result.schStartDate}</td>
				                				<td style='padding-left:5px;'>${result.schEndDate}</td>
												<c:if test="${daoRu eq 'Y' and  empty hideApprove}">
													<td style='padding-left:5px;'><a class="btn" onclick="openImport('${dept.recordFlow}','${resultProcessMap[result.resultFlow].processFlow}')">数据导入</a></td>
												</c:if>
				                			</tr>
			                			</c:forEach>
		                			</table>
								</div>
							</div>
						</c:if>
	               </td>
	                <td>${dept.schMonth}</td>
	                <td>
                		${realMonthMap[resultKey]}
	                </td>
	               <td width="120px;">
	               <%--<div style="height: 20px;margin-top: 5px;">--%>
	               		<%--<div style="float: left;margin-left: 5px;">--%>
	               			<%--<div style="float: left;height: 10px;width: 10px;background: rgba(255,120,50,1);"></div>--%>
	               			<%--<div style="float: left;line-height: 10px;">--%>
								<%--<c:choose>--%>
									<%--<c:when test="${(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope--%>
										<%--or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope--%>
										<%--or GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope)--%>
										<%--and f != GlobalConstant.RECORD_STATUS_Y}">--%>
										<%--：0%--%>
									<%--</c:when>--%>
									<%--<c:otherwise>：${processPerMap[dept.recordFlow]+0}%</c:otherwise>--%>
								<%--</c:choose>--%>
							<%--</div>--%>
	               		<%--</div>--%>
	               		<%--<div style="float: left;margin-left: 5px;">--%>
	               			<%--<div style="float: left;height: 10px;width: 10px;background: rgba(100,200,255,1);"></div>--%>
	               			<%--<div style="float: left;line-height: 10px;">--%>
								<%--<c:choose>--%>
									<%--<c:when test="${(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope--%>
										<%--or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope--%>
										<%--or GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope)--%>
										<%--and f != GlobalConstant.RECORD_STATUS_Y}">--%>
										<%--：0%--%>
									<%--</c:when>--%>
									<%--<c:otherwise>：${processingPerMap[dept.recordFlow]+0}%</c:otherwise>--%>
								<%--</c:choose>--%>
							<%--</div>--%>
	               		<%--</div>--%>
	               		<%--<div style="float: left;margin-left: 5px;">--%>
	               			<%--<div style="float: left;height: 10px;width: 10px;background: rgba(255,120,255,1);"></div>--%>
	               			<%--<div style="float: left;line-height: 10px;">--%>
								<%--<c:choose>--%>
									<%--<c:when test="${(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope--%>
										<%--or GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope--%>
										<%--or GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope)--%>
										<%--and f != GlobalConstant.RECORD_STATUS_Y}">--%>
										<%--：0%--%>
									<%--</c:when>--%>
									<%--<c:otherwise>：${processPerMap[dept.recordFlow]-processingPerMap[dept.recordFlow]+0}%</c:otherwise>--%>
								<%--</c:choose>--%>
							<%--</div>--%>
	               		<%--</div>--%>
	               <%--</div>--%>
					   <div id="chart_${dept.recordFlow}" style="width: 100%;height: 20px;margin-top: 10px;margin-bottom: 5px;">
						   <div style="width: 30%;float:left;text-align:left;">
							   总比例:
						   </div>
						   <div style="width: 67%;float:left;border:1px solid #e7e7eb;">
							   <div style="width:${processPerMap[dept.recordFlow]+0}%;height:20px;background:rgba(100,200,255,1);float:left;text-align: center;line-height: 20px;">
								   <c:set value="${completeCount+0+(empty realMonthMap[resultKey] ? 0 : 1)}"
										  var="completeCount"></c:set>
								   <c:set value="${completeBi+0+(empty realMonthMap[resultKey] ? 0 : processPerMap[dept.recordFlow])}"
										  var="completeBi"></c:set>
								   <c:if test="${processPerMap[dept.recordFlow]+0 != 0}">
									   <font style="color:rgba(255,120,50,1);">${processPerMap[dept.recordFlow]+0}%</font>
								   </c:if>
							   </div>
						   </div>
					   </div>
					   <div style="width:100%;height:20px;text-align: left;margin-top: 10px;margin-bottom: 5px;border-top:1px solid #e7e7eb; ">
						   登记比例:<font style="color:rgba(100,200,255,1);">${processingPerMap[dept.recordFlow]+0}% </font>
						   &#12288;补登比例:<font style="color:rgba(255,120,255,1);">${processPerMap[dept.recordFlow]-processingPerMap[dept.recordFlow]+0}%</font>
					   </div>
				   </td>
	                <td style="text-align: left;">
	                	<%--<c:if test="${!empty resultMap[key] }">--%>
		                	<%--<a class="btn" onclick="uploadAfterEvaluation('${resultMap[key].processFlow}');">登记手册</a>--%>
		                	<%--&#12288;--%>
		                	<%--<a class="btn" onclick="uploadAfterEvaluation('${processMap[key].processFlow}');">出科考核表</a>--%>
	                	<%--</c:if>--%>
	                	<%--<c:if test="${empty resultMap[key] }">--%>
			                <c:if test="${ empty hideApprove}">
								<c:if test="${param.roleFlag eq GlobalConstant.RES_ROLE_SCOPE_DOCTOR}">
									<c:set value="jsres_${doctor.orgFlow }_guocheng" var="orgFlow"/>
									<c:if test="${pdfn:jsresPowerCfgMap(orgFlow) eq GlobalConstant.RECORD_STATUS_Y}">
										<a class="btn" onclick="catalogue('${dept.recordFlow}','${param.doctorFlow}');">登记详情</a>&#12288;
									</c:if>
								</c:if>
								<%--出科考核--%>
							 	<c:if test="${ckk}">
							 		<span class="showCkk btn" style=" color: #459ae9;">出科考核
										<c:if test="${! empty resultMap[resultKey]}">
											<div style="width: 0px;height: 0px;overflow: visible;">
												<div style="max-height: 300px;overflow: auto;display: none;position: relative;width: 300px;left: -100px;" class="showCkkInfo">
													<table class="grid" style="background: white;width: 300px;">
														<tr>
															<th style="width: 300px; ">科室名称(分数)</th>
														</tr>
														<c:forEach items="${resultMap[resultKey]}" var="result">
															<c:set var="ScoreKey" value="${result.resultFlow}OutScore"></c:set>
															<c:set var="ProcessKey" value="${result.resultFlow}Process"></c:set>
															<c:set var="Score" value="${outScoreMap[ScoreKey]}"></c:set>
															<c:set var="Process" value="${outScoreMap[ProcessKey]}"></c:set>
															<tr>
																<td>${result.schDeptName}（
																	<c:if test="${empty Score || empty Score.theoryScore}">
																		<a onclick="afterTest('${Process.processFlow}',false);">参加出科考试</a>
																	</c:if>
																	<c:if test="${!(empty Score || empty Score.theoryScore)}">
																		<a onclick="afterTest('${Process.processFlow}',true);">${Score.theoryScore}</a>
																	</c:if>
                                                                    ）
                                                                    <c:if test="${!(empty Score || empty Score.theoryScore)}">
                                                                        <a onclick="showCkkPapers('${Process.processFlow}');" style="color: red;">查看错题</a>&#12288;
																		<a onclick="afterTest('${Process.processFlow}',true);">重新考试</a>
                                                                    </c:if>
																	</td>
															</tr>
														</c:forEach>
													</table>
												</div>
											</div>
										</c:if>
									</span>
							 	</c:if>
								&#12288;
		                		<c:if test="${f eq GlobalConstant.RECORD_STATUS_Y}">
			                	<span class="show"><a class="btn" onclick="">下载
			                	<c:if test="${! empty resultMap[resultKey]}">
								<div style="width: 0px;height: 0px;position: relative;z-index:10000;float: right; ">
									<div style="display: none;width: 100px;" class="info">
										<table class="grid" style="background: white;margin-left:45px;margin-top:-11px;">
				              			 	<tr><td><a onclick="xiaZai('${dept.recordFlow}');" style="margin-left: 12px;">通用</a>&#12288;</td></tr>
				                			<c:forEach items="${resultMap[resultKey]}" var="result">
					                			<tr>
					                				<td onclick="verification('${result.resultFlow}','${resultProcessMap[result.resultFlow].processFlow}');"><a>${result.schDeptName}</a></td>
					                			</tr>
				                			</c:forEach>
			                			</table>
									</div>
								</div>
								</c:if>
								<c:if test="${empty resultMap[resultKey]}">
								<div style="width: 0px;height: 0px;position: relative;z-index:10000;float: right; ">
									<div style="display: none;width: 100px;" class="info">
										<table class="grid" style="background: white;margin-left:45px;margin-top:-11px;">
				              			 	<tr><td><a onclick="xiaZai('${dept.recordFlow}');" style="margin-left: 12px;">通用</a>&#12288;</td></tr>
			                			</table>
									</div>
								</div>
								</c:if>
			                	</a>&#12288;
			                	</span>
			                	</c:if>
			                	<c:if test="${f != GlobalConstant.RECORD_STATUS_Y}">
			                		<span class="show"><a class="btn" onclick="xiaZai('${dept.recordFlow}');">下载</a></span>
			                	</c:if>
								<a class="btn" style="width: 86px;" onclick="upload('${dept.recordFlow}','${sysUser.userFlow}');">上传</a>&#12288;
			                	</c:if>
			                	<%--</c:if>--%>
		                	<c:if test="${not empty hideApprove}">
								<c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
			                		<%--<c:set value="jswjw_${doctor.orgFlow}_P001" var="orgFlow"/>--%>
									<c:set value="jsres_${doctor.orgFlow }_guocheng" var="orgFlow"/>
			                		<c:if test="${pdfn:jsresPowerCfgMap(orgFlow) eq GlobalConstant.RECORD_STATUS_Y}">
			                			<a class="btn" onclick="catalogue('${dept.recordFlow}','${param.doctorFlow}');">登记详情</a>&#12288;
			                		</c:if>
			                	</c:if>
			                	<c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
			                			<a class="btn" onclick="catalogue('${dept.recordFlow}','${param.doctorFlow}');">登记详情</a>&#12288;
			                	</c:if>
								<c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_UNIVERSITY}">
									<a class="btn" onclick="catalogue('${dept.recordFlow}','${param.doctorFlow}');">登记详情</a>&#12288;
								</c:if>
			                	<c:if test="${param.roleFlag eq GlobalConstant.USER_LIST_CHARGE}">
			                		<c:set value="jswjw_${sessionScope.currUser.orgFlow}_P003" var="orgFlow"/>
			                		<%--<c:if test="${sysCfgMap[orgFlow] eq GlobalConstant.RECORD_STATUS_Y}">--%>
			                			<a class="btn" onclick="catalogue('${dept.recordFlow}','${param.doctorFlow}');">登记详情</a>&#12288;
			                		<%--</c:if>--%>
			                	</c:if>
								<c:set var="showAfter" value="N"></c:set>
								<c:if test="${afterMap[dept.recordFlow] eq 'Y'}">
									<c:set var="showAfter" value="Y"></c:set>
								</c:if>
								<c:if test="${showAfter eq 'Y'}">
									<a class="btn" onclick="upload('${dept.recordFlow}','${sysUser.userFlow}','${hideApprove}');">查看</a>&#12288;
								</c:if>
								<c:if test="${showAfter eq 'N'}">
									暂未上传
								</c:if>
		                	</c:if>
		                	<%-- <a class="btn" href="<s:url value='/jsp/jsres/doctor/ckkhb.docx'/>">下载</a> --%>
	                </td>
	            </tr>
	            </c:forEach>
            </c:forEach>
			<tr>
				<td>合计时间</td>
				<c:if test="${doctorRecruit.speId eq '2500' and doctorRecruit.rotationFlow eq '0215d10b29454bdf9b018a7b80b89031'}">
					<td maxFractionDigits="2">33</td>
				</c:if>
				<c:if test="${doctorRecruit.speId ne '2500' and doctorRecruit.rotationFlow ne '0215d10b29454bdf9b018a7b80b89031'}">
					<td><fmt:formatNumber type="number" value="${allSchMonth}" maxFractionDigits="2"/></td>
				</c:if>
				<td><fmt:formatNumber type="number" value="${allRealMonth}" maxFractionDigits="2"/></td>
				<td>平均总比例</td>
				<td><fmt:formatNumber type="number" value="${completeBi/completeCount}" maxFractionDigits="2"/>%</td>
			</tr>
        </table>
        
    </div>
    </div>
</div>
    
