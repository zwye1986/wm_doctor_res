<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
	<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/css/inx_head.css'/>"></link>

	<c:set var="isCustomResult" value="${GlobalConstant.FLAG_Y eq sysCfgMap['res_custom_result_flag']}"/>
<script type="text/javascript" src="<s:url value='/js/echarts/echarts-all.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	function search(){
		$("#searchForm").submit();
	}
	function toDetail(resultFlow,rotationFlow,schDeptFlow){
		window.location.href="<s:url value='/res/doc/rotationDetail'/>?roleFlag=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}&resultFlow="+resultFlow+"&rotationFlow="+rotationFlow+"&schDeptFlow="+schDeptFlow;
	}
	function openChoose(resultFlow,preResultFlow,rotationFlow,schDeptFlow){
		var url="<s:url value='/res/doc/showChoose' />?resultFlow="+resultFlow+"&preResultFlow="+preResultFlow+"&rotationFlow="+rotationFlow+"&schDeptFlow="+schDeptFlow;
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='200' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe, "选择教学主任和带教老师", 400,200,null,true);
	}
	function showWarning(){
		jboxTip("请按轮转计划，顺序完成出科！");
	}
	function showMsg(){
		jboxTip("请先联系医院管理员，维护轮转计划所对应的标准科室！");
	}
	
	//轮转方案说明
	function openDetail(rotationName, rotationFlow){
		var url = "<s:url value='/sch/template/editRotation'/>?roleFlag=${param.roleFlag}&viewFlag=${GlobalConstant.FLAG_Y}&rotationFlow="+rotationFlow;
		jboxOpen(url, rotationName, 800, 500);
	}
	
	$(function(){
		$('[onclick]').on('click',function(e){
			e.stopPropagation();
		});
	});
</script>
<style type="text/css">
.top-tab,.content{margin:0 20px;}
.top-innder{ position:relative; height:50px;}
.top-sx{
	display: block;
	float: left;
	left: 35px;
	margin-left: 35px;
	margin-top: 10px;
}
.sch{width:245px;height: 216px;float:left;margin-right: 10px;border:1px #ccc solid;margin-top: 15px;cursor: pointer;}
.sch_top{padding:10px;border-bottom:1px #ccc solid;height: 46px;}
.sch_top h3{margin-bottom: 5px; color:#3d91d5; font-size:15px; font-weight:normal;}
.sch_top p,.reorder p{color:#5A5A5A;}
.sch_img_1{width:48px;float: right;margin-top: -29px;margin-left: -16px;}
.sch_img{width:48px;float: right;margin-top: -10px;margin-right: -16px;}
.sch_title,.sch_info{float:left;margin-left: 10px;width:157px; color:#A3A3A3;}
.sch_body{clear: both; padding:10px 10px;height: 60px;}
.sch_jd{width:55px;float: left;height:60px;line-height: 46px;text-align: center;vertical-align: middle;}
.sch_date{clear: both;text-align: center;width:245px;height: 40px;padding-top:15px;}
.sch_date_item{width:90px;height: 30px;text-align: center;vertical-align: middle;display:inline-block; line-height: 30px;border:1px #ccc solid; color:#A8A8A8; margin-right: 10px;}
.sch_info p{margin-bottom: 5px;}
.btn-group, .btn-group-vertical {
position: relative;
display: inline-block;
vertical-align: middle;
}
.select{ padding:4px 0;vertical-align: middle;border: 1px solid #e7e7eb;border-radius: 3px;-moz-border-radius: 3px;-webkit-border-radius: 3px;font-size: 14px; color:#363636; font-family: "Microsoft YaHei";}
.btn-sm{padding:0;line-height: 50px;}
.icon-th-large{background:url("<s:url value='/css/skin/${skinPath}/images/th_larger_g1.png'/>"); background-repeat:no-repeat; background-position: 0px center;padding: 12px 22px; }
.icon-reorder{background:url("<s:url value='/css/skin/${skinPath}/images/reorder_w1.png'/>"); background-repeat:no-repeat; background-position: 0px center;padding: 12px 22px; }
.icon-large{background:url("<s:url value='/css/skin/${skinPath}/images/th_larger_w1.png'/>"); background-repeat:no-repeat; background-position: 0px center;padding: 12px 22px; }
.icon-th-reorder{background:url("<s:url value='/css/skin/${skinPath}/images/reorder_g1.png'/>"); background-repeat:no-repeat; background-position: 0px center;padding: 12px 22px; }
.icon-head{background:url("<s:url value='/css/skin/${skinPath}/images/audit_tec.png'/>"); background-repeat:no-repeat; background-position: 0px center;padding: 12px 22px; }
.reorder{border: 1px solid #ddd;width: 100%;margin-bottom: 20px;height: 82px;cursor: pointer;}
.reorder h3{color:#3d91d5;font-size: 18px; font-weight:normal;}

#userInfo{position:absolute; background-color:#fff; padding:10px; border:1px solid  #dcdcdc; width:290px; right:-128px;z-index: 10000;}
.icon_up{background-image:url("<s:url value='/css/skin/${skinPath}/images/up2.png'/>"); background-repeat:no-repeat; background-position: top center; padding:5px;position: absolute;top: -6px;left: 150px;}
.xllist caption{padding-bottom: 10px;font-weight: bold;font-size: 15px;color: #3d91d5;}
.pxxx{position: absolute;right: 130px;}
</style>
 <style type="text/css">  

           .list,li{list-style:none; margin:0; padding:0;}  

           .scroll{ /*width:500px;*/ height:25px; overflow:hidden; }  

           .scroll li{ width:300px; height:25px; line-height:25px; overflow:hidden;}  

           .scroll li a{ font-size:14px;color:#333; text-decoration:none;}  

           .scroll li a:hover{ text-decoration:underline;}  

			#tags li{cursor: pointer;}
       </style>  

<script>

function change(type){
	if(type=="h"){
		$("#changeResultBtn").show();
		$("#horz").show();
		$("#vtc").hide();
		$(".modelButton_h").show();
		$(".modelButton_v").hide();
	}else if(type=="v"){
		$("#changeResultBtn").hide();
		$("#horz").hide();
		$("#vtc").show();
		$(".modelButton_h").hide();
		$(".modelButton_v").show();
		$("#tags li:first a").click();//默认加载第一个tab
	}
}

$(function(){
    var tabCourse = $('.icon-head');
    tabCourse.on('click',function(e){e.stopPropagation();});
    tabCourse.on('click',function(){
    	$("#userInfo").show();
    });
    $(document).on('click',function(){$("#userInfo").hide();});
});

$(document).ready(function(){
	<c:if test="${GlobalConstant.FLAG_Y eq sysCfgMap['res_reg_pre_flag']}">
		showCanvas('horz');
	</c:if>
	//showCanvas('vtc');
	change("h");
	$(".changeViewModel").toggle(!!$("#tags li").length);
});
function showCanvas (type){
	 <c:forEach items="${arrResultList }" var="result">
		 <c:set value="${result.resultFlow}finish" var="finishKey"/>
		 <c:set value="${result.resultFlow}req" var="reqKey"/>
		 <c:set value="${processMap[result.resultFlow]}" var="process"/>
		<c:set var="reqCount" value="0"/><!--要求总数-->
		<c:set var="compCount" value="0"/><!--完成总数-->

		<c:if test="${process.schFlag eq GlobalConstant.FLAG_Y && empty doctor.rotationFlow}">
		<c:set value="100" var="viewPer"/>
		</c:if>
		<c:if test="${!(process.schFlag eq GlobalConstant.FLAG_Y && empty doctor.rotationFlow)}">
		<c:set value="${finishPerMap[result.resultFlow]+0}" var="viewPer"/>
		<c:set value="${pdfn:percentRoundHalfUp(viewPer,2)}" var="viewPer"/>
		</c:if>
     var myChart = echarts.init(document.getElementById('chart_'+type+'_${result.resultFlow}'));
     var labelTop = {
     	    normal : {
     	        label : {
     	            show : true,
     	            position : 'center',
     	            formatter : '{b}',
     	            textStyle: {
     	                baseline : 'bottom'
     	            }
     	        },
     	        labelLine : {
     	            show : false
     	        }
     	    }
     	};
     	var labelFromatter = {
     	    normal : {
     	        label : {
     	            formatter : function (a,b,c){return "${viewPer}%"},
     	            textStyle: {
     	                baseline : 'center'
     	            }
     	        }
     	    },
		};
     	var labelBottom = {
     	    normal : {
     	        color: '#ccc',
     	        label : {
     	            show : true,
     	            position : 'center'
     	        },
     	        labelLine : {
     	            show : false
     	        }
     	    },
     	    emphasis: {
     	        color: 'rgba(0,0,0,0)'
     	    }
     	};
     	var radius = [25, 28];
     	option = {
     	    series : [
     	        {
     	            type : 'pie',
     	            radius : radius,
     	            x: '0%', // for funnel
     	            itemStyle : labelFromatter,
     	            data : [
						{name:'other', value:${100-viewPer},itemStyle : labelBottom},
						{name:'', value:${viewPer}, itemStyle : labelTop},
     	            ]
     	        }
     	    ]
     	};

     // 为echarts对象加载数据 
     myChart.setOption(option); 
  </c:forEach>
}

function changeResult(doctorFlow){
	jboxConfirm("确认变更？新的排班需要管理员审核后才能继续轮转！",function(){
		jboxPost("<s:url value='/res/doc/confirmRosting'/>",{
			doctorFlow:doctorFlow,
			schStatusId:"",
			schStatusName:"",
			schFlag:"${GlobalConstant.FLAG_N}"
		},function(resp){
			if(resp=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
				top.jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				location.href="<s:url value='/res/doc/selDeptAndRosteringHand'/>";
			}
		},null,false);
	},null);
}
</script>

</head>
<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>
<body>
<div>
	<script type="text/javascript">
		var code = '383840403739373966656665';//上38、上38、下40、下40、左37、右39、左37、右39、B66、A65、B66、A65
		var currCode = '';
		var timeoutObj ;
		$(function(){
			$(document).on('keyup',function(e){
				currCode+=e.keyCode;
				clearTimeout(timeoutObj);
				timeoutObj = setTimeout(function(){
					currCode = '';
				},500);
				if(currCode==code){
					currCode = '';
				}
			})
		});
	</script>
</div>
<div class="mainright">
  <div class="top-tab">
   <div class="top-innder">
	<div class="btn-group switch-tab j_goalView" style="float: right;padding-right: 30px;">
	    <div class="pxxx">
			<a class="btn btn-sm" data-status="vtc" href="#">
				<i class="icon-head" ></i>
			</a>
			<div id="userInfo" style="display:none;">
			    <i class="icon_up"></i>
				<table class="xllist">
					<caption>培训信息</caption>
					<c:if test="${doctor.doctorCategoryId eq recDocCategoryEnumGraduate.id}">
						<tr>
							<th>学号：</th>
							<td>${doctor.doctorCode}</td>
						</tr>
					</c:if>
					<tr>
						<th>年级：</th>
						<td>${doctor.sessionNumber}</td>
					</tr>
					<tr>
						<th><span class="trainNameSpan trainExtralSpan">培养</span>年限：</th>
						<td>${doctor.trainingYears}</td>
					</tr>
					<tr>
						<th><span class="trainNameSpan">培训</span>基地：</th>
						<td>${doctor.orgName}</td>
					</tr>
					<tr>
						<th><span class="trainNameSpan">培训</span>专业：</th>
						<td>${doctor.trainingSpeName}</td>
					</tr>
					<tr>
					    <th>轮转方案：</th>
						<td><a onclick="openDetail('${doctor.rotationName}','${doctor.rotationFlow}');" style="color: blue;cursor: pointer;">${doctor.rotationName }</a></td>
					</tr>
					<c:if test="${not empty doctor.secondSpeId}">
						<tr>
							<th>二级培训专业：</th>
							<td>${doctor.secondSpeName}</td>
						</tr>
					</c:if>
					<c:if test="${not empty doctor.secondRotationFlow}">
						<tr>
							<th>二级轮转方案：</th>
							<td><a onclick="openDetail('${doctor.secondRotationName}','${doctor.secondRotationFlow}');" style="color: blue;cursor: pointer;">${doctor.secondRotationName }</a></td>
						</tr>
					</c:if>
					<tr>
						<th><span class="userNameSpan">医师</span>状态：</th>
						<td>${doctor.doctorStatusName}</td>
					</tr>
					<c:if test="${doctor.doctorCategoryId eq recDocCategoryEnumGraduate.id}">
						<tr>
							<th>导师姓名：</th>
							<td>${doctor.tutorName}</td>
						</tr>
						<tr>
							<th>研究方向：</th>
							<td>${doctor.researchDirection}</td>
						</tr>
					</c:if>
				</table>
			</div>
		  </div>
		<div class="modelButton_h changeViewModel">
			<a class="btn btn-sm" data-status="horz" href="javascript:change('h');">
				<i class="icon-th-large"></i>
			</a>
			<a class="btn btn-sm active" data-status="vtc" href="javascript:change('v');">
				<i class="icon-reorder"></i>
			</a>
		</div>
		<div class="modelButton_v" style="display: none;">		  			

			<a class="btn btn-sm" data-status="horz" href="javascript:change('h');">
				<i class="icon-large"></i>
			</a>
			<a class="btn btn-sm active" data-status="vtc" href="javascript:change('v');">
				<i class="icon-th-reorder"></i>
			</a>
		</div>
	</div>
	<script>
	//${pdfn:signDaysBetweenTowDate(pdfn:getCurrDate(),pdfn:transDate(msg.createTime))<=7}
//      function autoScroll(obj){  
//          $(obj).find(".list").animate({  
//              marginTop : "-25px"  
//          },500,function(){  
//              $(this).css({marginTop : "0px"}).find("li:first").appendTo(this);  
//          });  
//      }  
//      $(function(){ 
//          setInterval('autoScroll(".scroll")',3000);  
//      }) 
		function scroll(){
			setTimeout(function(){
				$(".list li:first").animate({marginTop : "-25px"},500,function(){
					$(".list").append($(this).css({marginTop : "0px"}));
					scroll();
				});
			},3000);
		}
    var timer=null;
    function showButton()
    {
        if(getCookie("updateStatus")=="success"){
            clearInterval(timer);//每隔一秒的判断操作停止
            timer=null;
            delCookie("updateStatus");//删除cookie
            jboxEndLoading();
        }
    }
    //清除cookie
    function delCookie(name) {
        setCookie(name, "", -1);
    }
    //设置cookie
    function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays*60*60*1000));
        var expires = "expires="+d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires+";path=/";
    }
    //获取cookie
    function getCookie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for(var i=0; i<ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0)==' ') c = c.substring(1);
            if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
        }
        return "";
    }
    function daochu(doctorFlow){
        <c:if test="${ applicationScope.sysCfgMap['sys_index_url'] eq '/inx/nfykdx'}">
        var url = '<s:url value="/hbres/singup/admindaochu2"/>?doctorFlow='+doctorFlow;
        </c:if>
        <c:if test="${ applicationScope.sysCfgMap['sys_index_url'] eq '/inx/hbres'}">
        var url = '<s:url value="/hbres/singup/admindaochu"/>?doctorFlow='+doctorFlow;
        </c:if>
        <c:if test="${ applicationScope.sysCfgMap['sys_index_url'] ne '/inx/nfykdx'and applicationScope.sysCfgMap['sys_index_url'] ne '/inx/hbres'}">
        var url = '<s:url value="/hbres/singup/admindaochu3"/>?doctorFlow='+doctorFlow;
        </c:if>
        // if(getCookie("updateStatus")=="loading"){
        //     jboxTip("打印中,请稍等")
        //     return ;
        // }
		// setCookie("updateStatus", "loading", 1);
		// jboxStartLoading();
		jboxTip("打印中,请稍等...");
		window.open(url);
		// timer=setInterval(showButton,1000);
	}
		function exportInfo(doctorFlow){
			jboxTip("导出中,请稍等...");
			var url = '<s:url value="/schDept/export/exportInfo"/>?doctorFlow='+doctorFlow;
			window.location.href = url;
		}
		$(function(){
			if($(".list li").length>1){
				scroll();
			}
//			if($("#changeResultBtn").length > 0){
//				$("#daochu").attr("style","float:right;margin: 15px -160px 0 0;");
//			}else {
//				$("#daochu").attr("style","float:right;margin: 15px 188px 0 0;");
//			}
		});
	</script>
	   <div class="top-sx">
		   <jsp:include page="/res/doc/newNoticeList" flush="true">
			   <jsp:param name="fromSch" value="Y"></jsp:param>
			   <jsp:param name="isDoctor" value="Y"></jsp:param>
		   </jsp:include>
	</div>
	   <div class="top-sx">
		   <table class="" style="border:0px;">
			   <tbody>
			   <tr>
				   <td style="border:0px;">
					   <c:if test="${!empty arrResultList && GlobalConstant.FLAG_Y eq sysCfgMap['res_doc_rostering']}">
						   <c:set var="currDate" value="${pdfn:getCurrDate()}"/>
						   <c:set var="currDate" value="${currDate.substring(8)}"/>
						   <c:if test="${empty sysCfgMap['res_plan_locked_date'] || currDate <= sysCfgMap['res_plan_locked_date']}">
							   <input type="button" id="changeResultBtn" value="轮转变更" class="search" style="" onclick="changeResult('${doctor.doctorFlow}');">
						   </c:if>
					   </c:if>
					   <c:if test="${(sysCfgMap['training_manual_download'] eq GlobalConstant.FLAG_Y) and (open eq GlobalConstant.FLAG_Y)}">
						   <input id="daochu" type="button" value="导出培训手册" class="search" style="" onclick="daochu('${doctor.doctorFlow}');">
					   </c:if>

					   <c:if test="${sysCfgMap['sch_dept_detail_download'] eq GlobalConstant.FLAG_Y}">
						   <input id="daochu" type="button" value="导出轮转科室" class="search" style="" onclick="exportInfo('${doctor.doctorFlow}');">
					   </c:if>
				   </td>
			   </tr>
			   </tbody></table>
	   </div>
  </div>
	  <%--<div class="mpui-mod">--%>
		  <%--<div class="mpui-home-notice">--%>
			  <%--<div class="mpui-home-notice__info">--%>
				  <%--<a class="mpui-home-notice__title" href="" target="_blank">关于使用微信卡券开展储值相关业务的说明</a>--%>
			  <%--</div>--%>
			  <%--<div class="mpui-home-notice__extra">--%>
				  <%--<em class="mpui-home-notice__date">2017-08-17</em>--%>
				  <%--<a class="mpui-home-notice__readmore" href="" target="_blank">更多</a>--%>
			  <%--</div>--%>
		  <%--</div>--%>
	  <%--</div>--%>
	<div class="content" id="vtc" style="display: none;margin-top: 15px;">
		<script>
			function selectTag(url,obj){
				$(".selectTag").removeClass("selectTag");
	        	$(obj).parent().addClass("selectTag");
	        	jboxLoad("tagContent",url,true);
			}
		</script>
		
		<ul id="tags">
			<c:set value="res_registry_type_${globalRecTypeEnumEthics.id}" var="viewCfgKey"/>
			<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
				<li><a onclick="selectTag('<s:url value='/res/rec/showRecForm'/>?recTypeId=${globalRecTypeEnumEthics.id }&operUserFlow=${doctor.doctorFlow}&schDeptFlow=${doctor.deptFlow}&roleFlag=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}',this);">${globalRecTypeEnumEthics.name }</a></li>
			</c:if>
			
			<c:set value="res_registry_type_${globalRecTypeEnumDocument.id}" var="viewCfgKey"/>
			<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
				<li><a onclick="selectTag('<s:url value='/res/rec/showRecForm'/>?recTypeId=${globalRecTypeEnumDocument.id }&operUserFlow=${doctor.doctorFlow}&schDeptFlow=${doctor.deptFlow}&roleFlag=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}',this);">${globalRecTypeEnumDocument.name }</a></li>
			</c:if>
			
			<c:set value="res_registry_type_${globalRecTypeEnumAppraisal.id}" var="viewCfgKey"/>
			<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
				<li><a onclick="selectTag('<s:url value='/res/rec/showRecForm'/>?recTypeId=${globalRecTypeEnumAppraisal.id }&operUserFlow=${doctor.doctorFlow}&schDeptFlow=${doctor.deptFlow}&roleFlag=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}',this);">${globalRecTypeEnumAppraisal.name}</a></li>
			</c:if>
<%-- 			<li><a onclick="selectTag('<s:url value='/res/rec/showRecForm'/>?recTypeId=${resRecTypeEnumAppraisal.id }&operUserFlow=${doctor.doctorFlow}&schDeptFlow=${doctor.deptFlow}',this);">实习档案</a></li> --%>
			<c:if test="${groupRoleEnumLeader.id eq doctor.groupRoleId}">
	    		<li><a onclick="selectTag('<s:url value='/res/doc/appraisalList'/>?doctorFlow=${doctor.doctorFlow}',this);">小组成员</a></li>
	    	</c:if>
<%--     		<li><a onclick="selectTag('<s:url value='/res/doc/annualTrainForm'/>?doctorFlow=${doctor.doctorFlow}&roleFlag=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}',this);" href="javascript:void(0)" >年度培训记录</a></li> --%>
				
<%-- 	    		<li><a onclick="selectTag('<s:url value='/res/rec/showRecForm'/>?recTypeId=${resRecTypeEnumSpeAbilityAssess.id }&operUserFlow=${doctor.doctorFlow}&schDeptFlow=${doctor.deptFlow }',this);" href="javascript:void(0)" >专业能力评估表</a></li> --%>
   			
   			<c:set value="res_registry_type_${globalRecTypeEnumSpeAbilityAssess.id}" var="viewCfgKey"/>
			<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
   				<li><a onclick="selectTag('<s:url value='/res/doc/speAbilityAssessList'/>?recTypeId=${globalRecTypeEnumSpeAbilityAssess.id}&operUserFlow=${doctor.doctorFlow}&roleFlag=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}',this);">${globalRecTypeEnumSpeAbilityAssess.name}</a></li>
	    	</c:if>
	    	
	    	<c:set value="res_registry_type_${globalRecTypeEnumRegistryCheckForm.id}" var="viewCfgKey"/>
			<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
   				<li><a onclick="selectTag('<s:url value='/res/doc/speAbilityAssessList'/>?recTypeId=${globalRecTypeEnumRegistryCheckForm.id}&operUserFlow=${doctor.doctorFlow}&roleFlag=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}',this);">${globalRecTypeEnumRegistryCheckForm.name}</a></li>
	    	</c:if>
	    	
	    	<c:set value="res_registry_type_${globalRecTypeEnumAnnualActivity.id}" var="viewCfgKey"/>
			<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
	    		<li><a onclick="selectTag('<s:url value='/res/doc/speAbilityAssessList'/>?recTypeId=${globalRecTypeEnumAnnualActivity.id}&operUserFlow=${doctor.doctorFlow}&roleFlag=${GlobalConstant.RES_ROLE_SCOPE_DOCTOR}',this);">${globalRecTypeEnumAnnualActivity.name}</a></li>
	    	</c:if>
	    	
	    	<c:set value="res_registry_type_${resRecTypeEnumAnnualTrainForm.id}" var="viewCfgKey"/>
			<c:if test="${sysCfgMap[viewCfgKey] eq GlobalConstant.FLAG_Y}">
	    		<li><a onclick="selectTag('<s:url value='/res/doc/annualtrainShow'/>',this);">${resRecTypeEnumAnnualTrainForm.name}</a></li>
	    	</c:if>
	    </ul>
    <div id="tagContent" class="divContent">
    	
    </div>
</div>
<div  class="content" style="margin-top: 15px;" id="horz">
	<c:forEach items="${arrResultList}" var="result" varStatus="status">
		<c:if test="${!(sysCfgMap['res_doc_in_order'] eq GlobalConstant.FLAG_N)}">
			<c:choose>
				<c:when test="${empty processMap}">
					<c:choose>
						<c:when test="${status.first }">
							<c:set var="clickString" value="openChoose('${result.resultFlow }','','${result.rotationFlow}','${result.schDeptFlow}');"/>
						</c:when>
						<c:otherwise>
							<c:set var="clickString" value="showWarning();"/>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${!empty processMap[result.resultFlow] }">
							<c:set var="clickString" value="toDetail('${result.resultFlow }','${result.rotationFlow}','${result.schDeptFlow}');"/>
						</c:when>
						<c:otherwise>
						    <c:choose>
								<c:when test="${recContentMap[arrResultList[status.index-1].resultFlow] == GlobalConstant.FLAG_Y }">
									<c:set var="clickString" value="openChoose('${result.resultFlow }','${arrResultList[status.index-1].resultFlow}','${result.rotationFlow}','${result.schDeptFlow}');"/>
								</c:when>
								<c:otherwise>
									<c:set var="clickString" value="showWarning();"/>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${sysCfgMap['res_doc_in_order'] eq GlobalConstant.FLAG_N}">
			<c:if test="${!empty processMap[result.resultFlow] || (empty processMap[result.resultFlow].teacherUserFlow || empty processMap[result.resultFlow].headUserFlow) }">
				<c:set var="clickString" value="toDetail('${result.resultFlow }','${result.rotationFlow}','${result.schDeptFlow}');"/>
			</c:if>
			<c:if test="${empty processMap[result.resultFlow]}">
				<c:set var="clickString" value="openChoose('${result.resultFlow }','${arrResultList[status.index-1].resultFlow}','${result.rotationFlow}','${result.schDeptFlow}');"/>
			</c:if>
		</c:if>
		
		<c:set var="process" value="${processMap[result.resultFlow]}"/>
		<c:set var="startDate"  value="${result.schStartDate}"/>
		<c:set var="endDate"  value="${result.schEndDate}"/>

		<c:set var="clickContent"  value=""/>
		<c:if test="${(!(sysCfgMap['res_doc_in_by_self'] eq GlobalConstant.FLAG_N)) || (process.schFlag == GlobalConstant.FLAG_Y) || (process.isCurrentFlag == GlobalConstant.FLAG_Y)}">
			<c:set var="clickContent"  value='onclick="${clickString}"'/>
		</c:if>
		<c:if test="${ empty result.standardDeptId}">
			<c:set var="clickContent"  value='onclick="showMsg();"'/>
		</c:if>
	<table class="reorder">
		<tr>
			<td style="width: 47px;">
				<c:if test="${not empty process && process.schFlag eq GlobalConstant.FLAG_Y}">
					<img alt="" src="<s:url value='/css/skin/${skinPath}/images/yck_1.png'/>">
				</c:if>

				<c:if test="${not empty process && process.isCurrentFlag eq GlobalConstant.FLAG_Y}">
					<img alt="" src="<s:url value='/css/skin/${skinPath}/images/Rotary_1.png'/>">
				</c:if>

				<c:if test="${empty process && (empty arrResultList[status.index-1] || processMap[arrResultList[status.index-1].resultFlow].schFlag eq GlobalConstant.FLAG_Y)}">
					<img alt="" src="<s:url value='/css/skin/${skinPath}/images/drk_1.png'/>">
				</c:if>
				<c:if test="${not empty process &&(empty process.teacherUserFlow || empty process.headUserFlow) && (empty arrResultList[status.index-1] || processMap[arrResultList[status.index-1].resultFlow].schFlag eq GlobalConstant.FLAG_Y)}">
					<img alt="" src="<s:url value='/css/skin/${skinPath}/images/drk_1.png'/>">
				</c:if>
			</td>
			
			<td ${clickContent} style="text-align:left;width: 400px; padding-left:45px;" class="${result.resultFlow}Td">
				<h3>
					${result.standardDeptName }[${result.schDeptName }]
					<c:if test="${GlobalConstant.FLAG_Y eq schDeptMap[result.schDeptFlow].isExternal}">
						&nbsp;<font size="2">(${schDeptMap[result.schDeptFlow].externalOrgName})</font>
					</c:if>
					<c:if test="${GlobalConstant.FLAG_Y eq process.isExternal}">
						&nbsp;<font size="2">(${process.orgName})</font>
					</c:if>
				</h3>
				
				<p style="margin-top: 10px;">
					<span style="min-width: 140px;display:block;float: left; ">
						带教老师：<c:out value="${process.teacherUserName }" default="未选择"/>
					</span>
					&#12288;
					<span style="min-width: 140px;display:block;float: left; ">
						教学主任：<c:out value="${process.headUserName }" default="未选择"/>
					</span>
				</p>
			</td>
			
			<td ${clickContent} width="120px;" class="${result.resultFlow}Td">
				<div id="chart_horz_${result.resultFlow }" style="width: 100px;height: 80px;"></div>
			</td>
			
			<td ${clickContent} class="${result.resultFlow}Td">
				<p>
					轮转时间：${result.schMonth }${applicationScope[unitKey].name}
				</p>
				<p>
					出科成绩：<c:out value="${process.schScore}" default="暂无"/>
				</p>
			</td>
			
			<td ${clickContent} style="text-align: right;" class="${result.resultFlow}Td">
				<div class="sch_date_item">
					${startDate}
				</div>
				
				<div class="sch_date_item">
					${endDate}
				</div>
			</td>
		</tr>
	</table>
	</c:forEach>

	<c:if test="${empty arrResultList}">
        <c:if test="${sysCfgMap['is_show_jxres'] eq 'Y'}">
            <div><img alt="" src="<s:url value='/css/skin/${skinPath}/images/zwjl.jpg'/>"></div>
        </c:if>
        <c:if test="${sysCfgMap['is_show_jxres'] ne 'Y'}">
		<div style="padding-top:5%;"><img alt="" src="<s:url value='/css/skin/${skinPath}/images/zwjl.png'/>"></div>
	    </c:if>
    </c:if>
</div>
</div></div>
</body>
</html>