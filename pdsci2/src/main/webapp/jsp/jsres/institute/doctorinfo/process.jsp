<script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
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
	jboxEndLoading();
	 <c:forEach items="${groupList}" var="group">
	  	<c:forEach items="${rotationDeptMap[group.groupFlow]}" var="dept">
	  		showCanvas('${dept.recordFlow}','${processPerMap[dept.recordFlow]}','${processingPerMap[dept.recordFlow]}');
		</c:forEach> 
	</c:forEach>  
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
		    }
		};
		var option = {
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
function xiaZai(recordFlow){
	var url = "<s:url value='/jsres/base/download'/>?recordFlow="+recordFlow;
	window.location.href = url;	
}
function daochu(doctorFlow){
	jboxTip("打印中,请稍等...");
	var url = '<s:url value="/jsres/doctor/daochu"/>?doctorFlow='+doctorFlow;
	window.location.href = url;
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
function verification(resultFlow){
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

function setIndexHeight(){
	$("#indexBody").css("height",document.documentElement.clientHeight);
}
function catalogue(recordFlow,doctorFlow){
	var url="<s:url value='/jsres/manage/catalogue'/>?doctorFlow="+doctorFlow+"&schRotationDeptFlow="+recordFlow;
	jboxOpen(url,"登记详情", 900,550,true);
}
</script>
<div class="main_hd">
    <h2><B>培训基地：</B>${doctor.orgName}&#12288;<B>培训专业：</B>${doctor.trainingSpeName }&#12288;
   <B> 轮转方案：</B>${rotation.rotationName } &#12288;&#12288;<c:if test="${empty rotation }"><font color='red'>首次使用请登录APP填写培训数据(首页扫描二维码)!</font></c:if>
   <c:if test="${hideApprove eq 'null'}">
	   	<input type="button" class="btn_green" value="诚信声明" name="sincerity" onclick="sincerity();"/>
   		<a href="javascript:void(0);" onclick="daochu('${doctor.doctorFlow}');" id="printBtn" class="btn_green" style="display: none">导出手册</a> 
 	</c:if> 
 	<c:if test="${hideApprove != 'null'}">
	 	<c:if test="${sessionScope.userListScope==GlobalConstant.RES_ROLE_SCOPE_SCHOOL}">
	 		<a href="javascript:doctorQuery();" class="btn_green">返回</a>
	 	</c:if>
	 	<c:if test="${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}">
	 		<a href="javascript:doctorList();" class="btn_green">返回</a>
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
    	<div style="margin-left: 30px;margin-bottom: 10px;">
    	Tip:<font style="color:rgba(255,120,50,1);">橙色</font>表示总登记比例,<font style="color:rgba(100,200,255,1);">蓝色</font>表示轮转中登记比例,<font style="color:rgba(255,120,255,1);">紫色</font>表示补登比例．
    	</div>
    	<div class="search_table" style="overflow:auto;margin-left: 30px;margin-right: 30px;padding: 0px;" id="indexBody">
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
                <th style="text-align: left;">出科考核表</th>
            </tr>
            <c:forEach items="${groupList}" var="group">
            	<c:forEach items="${rotationDeptMap[group.groupFlow]}" var="dept">
            		<c:set var="resultKey" value="${group.groupFlow}${dept.standardDeptId}"/>
            	 <tr>
	                <td style="padding-left: 25px;text-align: left;" class="showInfo">
	                <c:if test="${rotation.isStage eq GlobalConstant.FLAG_Y}">
						[${group.schStageName}${dept.isRequired == GlobalConstant.FLAG_N?"选科":"必轮"}]
					</c:if>
	               		${dept.standardDeptName}
						<c:if test="${! empty resultMap[resultKey]}">
							<div style="width: 0px;height: 0px;overflow: visible;">
								<div style="max-height: 300px;overflow: auto;display: none;position: relative;width: 320px;" class="theInfo">
									<table class="grid" style="background: white;width: 300px;">
			              			  <tr>
										  <th style="width: 100px; ">科室名称</th>
			                				<th style='padding-left:5px;width: 100px;'>开始日期</th>
			                				<th style='padding-left:5px;width: 100px;'>结束日期</th>
			                			</tr>
			                			<c:forEach items="${resultMap[resultKey]}" var="result">
				                			<tr>
				                				<td>${result.schDeptName}</td>
				                				<td style='padding-left:5px;'>${result.schStartDate}</td>
				                				<td style='padding-left:5px;'>${result.schEndDate}</td>
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
	               <div style="height: 20px;margin-top: 5px;">
	               		<div style="float: left;margin-left: 5px;">
	               			<div style="float: left;height: 10px;width: 10px;background: rgba(255,120,50,1);"></div>
	               			<div style="float: left;line-height: 10px;">：${processPerMap[dept.recordFlow]+0}%</div>
	               		</div>
	               		<div style="float: left;margin-left: 5px;">
	               			<div style="float: left;height: 10px;width: 10px;background: rgba(100,200,255,1);"></div>
	               			<div style="float: left;line-height: 10px;">：${processingPerMap[dept.recordFlow]+0}%</div>
	               		</div>
	               		<div style="float: left;margin-left: 5px;">
	               			<div style="float: left;height: 10px;width: 10px;background: rgba(255,120,255,1);"></div>
	               			<div style="float: left;line-height: 10px;">：${processPerMap[dept.recordFlow]-processingPerMap[dept.recordFlow]+0}%</div>
	               		</div>
	               </div>
	               <div id="chart_${dept.recordFlow}" style="width: 100%;height: 80px;">
	               </div></td>
	                <td style="text-align: left;">
	                	<c:if test="${!empty resultMap[key] }">
		                	<a class="btn" onclick="uploadAfterEvaluation('${resultMap[key].processFlow}');">登记手册</a>
		                	&#12288;
		                	<a class="btn" onclick="uploadAfterEvaluation('${processMap[key].processFlow}');">出科考核表</a>
	                	</c:if>
	                	
	                	<c:if test="${empty resultMap[key] }">
			                <c:if test="${hideApprove eq 'null'}">
			              		<c:set value="jswjw_${doctor.orgFlow}_P001" var="orgFlow"/>
		                		<c:if test="${sysCfgMap[orgFlow] eq GlobalConstant.RECORD_STATUS_Y}">
			                	<span class="show"><a class="btn" onclick="">下载
			                	<c:if test="${! empty resultMap[resultKey]}">
								<div style="width: 0px;height: 0px;position: relative;z-index:10000;float: right; ">
									<div style="display: none;width: 74px;" class="info">
										<table class="grid" style="background: white;margin-left:45px;margin-top:-11px;">
				              			 	<tr><td><a onclick="xiaZai('${dept.recordFlow}');" style="margin-left: 12px;">通用</a>&#12288;</td></tr>
				                			<c:forEach items="${resultMap[resultKey]}" var="result">
					                			<tr>
					                				<td onclick="verification('${result.resultFlow}');"><a>${result.schDeptName}</a></td>
					                			</tr>
				                			</c:forEach>
			                			</table>
									</div>
								</div>
								</c:if>
								<c:if test="${empty resultMap[resultKey]}">
								<div style="width: 0px;height: 0px;position: relative;z-index:10000;float: right; ">
									<div style="display: none;width: 74px;" class="info">
										<table class="grid" style="background: white;margin-left:45px;margin-top:-11px;">
				              			 	<tr><td><a onclick="xiaZai('${dept.recordFlow}');" style="margin-left: 12px;">通用</a>&#12288;</td></tr>
			                			</table>
									</div>
								</div>
								</c:if>
			                	</a>&#12288;
			                	</span>
			                	</c:if>
			                	<c:if test="${sysCfgMap[orgFlow] != GlobalConstant.RECORD_STATUS_Y}">
			                		<span class="show"><a class="btn" onclick="xiaZai('${dept.recordFlow}');">下载</a></span>
			                	</c:if>
			                			<a class="btn" onclick="upload('${dept.recordFlow}');">上传</a>&#12288;
			                	</c:if>
			                	</c:if>
		                	<c:if test="${hideApprove != 'null'}">
			                	<a class="btn" onclick="upload('${dept.recordFlow}','${sysUser.userFlow}','${hideApprove}');">查看</a>&#12288;
			                	<c:if test="${param.roleFlag eq 'institute'}">
			                			<a class="btn" onclick="catalogue('${dept.recordFlow}','${param.doctorFlow}');">登记详情</a>&#12288;
			                	</c:if>
		                	</c:if>
	                </td>
	            </tr>
	            </c:forEach>
            </c:forEach>
        </table>
        
    </div>
    </div>
</div>
    
